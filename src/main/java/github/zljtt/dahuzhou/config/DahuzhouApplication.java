package github.zljtt.dahuzhou.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.key.LocalDateTimeKeyDeserializer;
import com.mysql.cj.xdevapi.JsonArray;
import github.zljtt.dahuzhou.controller.UserController;
import github.zljtt.dahuzhou.resp.FileResp;
import github.zljtt.dahuzhou.resp.UserLoginResp;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.juli.logging.Log;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.naming.Name;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Time;
import java.text.Format;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ComponentScan(basePackages = "github.zljtt")
@SpringBootApplication()
@MapperScan("github.zljtt.dahuzhou.mapper")
@EnableAsync
@EnableScheduling
public class DahuzhouApplication {

    public static final Logger LOG = LoggerFactory.getLogger(DahuzhouApplication.class);
    public static final Map<String, UserController.UserToken> tokenMap = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DahuzhouApplication.class);
        Environment environment = application.run(args).getEnvironment();
        LOG.info("Start successfully at port \thttp://{}:{}", environment.getProperty("server.address"), environment.getProperty("server.port"));
    }

    @Value("${path.users.tokens}")
    private String userTokenPath;

    private LocalDateTime currentTime;


    @Async
    @PostConstruct
    public void ReadTokens() {
        DahuzhouApplication.LOG.info("Reading user tokens");
        try {
            Path path = Path.of(userTokenPath);
            if (Files.exists(path)) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                JsonNode jsonArray = mapper.readTree(new FileReader(userTokenPath));
                for (int i = 0; i < jsonArray.size(); i++) {
                    String token = jsonArray.get(i).get("token").asText();
                    UserController.UserToken userData = mapper.treeToValue(jsonArray.get(i).get("data"), UserController.UserToken.class);
                    tokenMap.put(token, userData);
                }
                tokenMap.forEach((token, userData) -> {
                    LOG.info("Read user token : " + token + " " + userData);
                });
            }
        } catch (IOException e) {
            DahuzhouApplication.LOG.error("Error reading user tokens");
            e.printStackTrace();
        }
    }

    @Async
    @PreDestroy
    @Scheduled(fixedDelay = 20000)
    public void saveTokens() {
        LocalDateTime time = LocalDateTime.now();
        tokenMap.entrySet().removeIf(entry -> {
            if (entry.getValue().getCreated().isBefore(time.minusDays(1))) {
                return true;
            }
            return false;
        });
        try {
            File file = new File(userTokenPath);
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            ArrayNode arrayNode = mapper.createArrayNode();
            for (Map.Entry<String, UserController.UserToken> entry : tokenMap.entrySet()) {
                ObjectNode node = mapper.createObjectNode();
                node.put("token", entry.getKey());
                node.putIfAbsent("data", mapper.valueToTree(entry.getValue()));
                arrayNode.add(node);
            }
            String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(arrayNode);
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(indented);
            } catch (IOException e) {
                DahuzhouApplication.LOG.error("Error saving user tokens");
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            DahuzhouApplication.LOG.error("Error saving user tokens");
            e.printStackTrace();
        }
    }

}

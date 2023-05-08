package github.zljtt.dahuzhou.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.zljtt.dahuzhou.config.DahuzhouApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Expose the website
 */
@RestController
@RequestMapping("/editor/dialog")
public class DialogController {

    @Value("${path.resourcepack}")
    private String resourceLocation;

    @GetMapping("/list")
    public List<String> listNPCNames(String prefix) {
        File directory = new File(resourceLocation + "/legendofthegreatlake/dialogs");
        File[] files = directory.listFiles();
        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().contains(".json") && file.getName().contains(prefix)) {
                    fileNames.add(file.getName().split(".json")[0]);
                }
            }
        }
        return fileNames;
    }

    @GetMapping("/get")
    public ResponseEntity<byte[]> getNPCDialog(String name) throws IOException {
        File file = new File(resourceLocation + "/legendofthegreatlake/dialogs/" + name + ".json");
        if (file.exists()) {
            byte[] jsonData = Files.readAllBytes(file.toPath());
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonData);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> saveNPCDialog(@RequestBody String jsonString, String name) throws IOException {
        File file = new File(resourceLocation + "/legendofthegreatlake/dialogs/" + name + ".json");
        DahuzhouApplication.LOG.info(jsonString);
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(jsonString, Object.class);
        String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(indented);
            System.out.println("JSON string has been written to the file.");
        } catch (IOException e) {
            return ResponseEntity.ok("文件接受失败，请联系技术部");
        }
        return ResponseEntity.ok("文件接受成功");
    }

}

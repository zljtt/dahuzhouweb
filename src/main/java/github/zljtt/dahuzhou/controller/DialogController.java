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

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
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

    @Value("${path.dialogs}")
    private String dialogLocation;

    @GetMapping("/list")
    public List<String> listNPCNames(String prefix) {
        File directory = new File(dialogLocation);
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
    public ResponseEntity<String> getNPCDialog(String name) throws IOException {
        File file = new File(dialogLocation + FileSystems.getDefault().getSeparator() + name + ".json");
        if (file.exists()) {
            ObjectMapper mapper = new ObjectMapper();
            Object jsonNode = mapper.readValue(new FileReader(dialogLocation + FileSystems.getDefault().getSeparator() + name + ".json"), Object.class);
            String jsonString = mapper.writeValueAsString(jsonNode);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonString);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteNPCDialog(String name) throws IOException {
        File file = new File(dialogLocation + FileSystems.getDefault().getSeparator() + name + ".json");
        if (file.exists()) {
            Files.delete(file.toPath());
            return ResponseEntity.ok("文件删除成功");
        } else {
            return ResponseEntity.ok("服务器没有这个文件");
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<String> saveNPCDialog(@RequestBody String jsonString, String name) throws IOException {
        File file = new File(dialogLocation + FileSystems.getDefault().getSeparator() + name + ".json");
        DahuzhouApplication.LOG.info("Received file: " + jsonString);
        ObjectMapper mapper = new ObjectMapper();
        Object json = mapper.readValue(jsonString, Object.class);
        String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        DahuzhouApplication.LOG.info("Try to save to location " + file.getAbsolutePath());
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(indented);
            return ResponseEntity.ok("文件接受成功");
        } catch (IOException e) {
            return ResponseEntity.ok("文件接受失败，请联系技术部");
        }
    }

}

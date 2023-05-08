package github.zljtt.dahuzhou.controller;

import github.zljtt.dahuzhou.config.DahuzhouApplication;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Expose the website
 */
@RestController
@RequestMapping("/editor/file")
public class FileController {

    @Value("${path.resourcepack}")
    private String resourceLocation;

    @PostMapping("/texture")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        DahuzhouApplication.LOG.info("save file + " + resourceLocation);
        if (file.isEmpty()) {
            return "No file uploaded";
        }
        try {
            // Save the file to a desired location
            String fileName = file.getOriginalFilename();
            String filePath = resourceLocation + "/legendofthegreatlake/texture/npc" + fileName;
            file.transferTo(new File(filePath));
            return "File uploaded successfully";
        } catch (IOException e) {
            return "File upload failed: " + e.getMessage();
        }
    }

}

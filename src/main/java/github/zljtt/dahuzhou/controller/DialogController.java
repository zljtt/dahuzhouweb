package github.zljtt.dahuzhou.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import github.zljtt.dahuzhou.config.DahuzhouApplication;
import github.zljtt.dahuzhou.req.FileNameReq;
import github.zljtt.dahuzhou.resp.FileResp;
import github.zljtt.dahuzhou.service.FileService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

/**
 * Expose the website
 */
@RestController
@RequestMapping("/editor/dialog")
public class DialogController {

    @Resource
    private FileService fileService;
    @Value("${path.dialogs}")
    private String dialogLocation;

    @GetMapping("/list")
    public ResponseEntity<List<String>> listNPCNames(String prefix) {
        List<String> fileNames = fileService.listFileNameWithoutSuffix(dialogLocation, prefix, ".json");
        if (fileNames.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(fileNames);
    }

    @GetMapping("/get")
    public ResponseEntity<FileResp> getNPCDialog(FileNameReq request) {
        String path = dialogLocation + FileSystems.getDefault().getSeparator() + request.getName() + ".json";
        FileResp resp = fileService.readJsonFile(path);
        return ResponseEntity.status(resp.getFileStatus().getHttpStatus()).body(resp);
    }

    @PostMapping("/delete")
    public ResponseEntity<FileResp> deleteNPCDialog(FileNameReq request) {
        String path = dialogLocation + FileSystems.getDefault().getSeparator() + request.getName() + ".json";
        FileResp resp = fileService.deleteFile(path);
        return ResponseEntity.status(resp.getFileStatus().getHttpStatus()).body(resp);
    }

    @PostMapping("/upload")
    public ResponseEntity<FileResp> saveNPCDialog(@RequestBody String body, FileNameReq request) {
        String path = dialogLocation + FileSystems.getDefault().getSeparator() + request.getName() + ".json";
        FileResp resp = fileService.saveJsonFile(body, path);
        return ResponseEntity.status(resp.getFileStatus().getHttpStatus()).body(resp);
    }

}

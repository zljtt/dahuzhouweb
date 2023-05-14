package github.zljtt.dahuzhou.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.zljtt.dahuzhou.resp.FileResp;
import jdk.jfr.consumer.RecordedThread;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    public FileResp deleteFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                Files.delete(file.toPath());
                return new FileResp().setFileStatus(FileResp.FileStatus.DELETE);
            }
            return new FileResp().setFileStatus(FileResp.FileStatus.NOT_FOUND);
        } catch (JsonProcessingException e) {
            return new FileResp().setFileStatus(FileResp.FileStatus.WRONG_FORMAT);
        } catch (IOException e) {
            return new FileResp().setFileStatus(FileResp.FileStatus.IO_EXCEPTION);
        }
    }

    public FileResp saveJsonFile(String body, String path) {
        try {
            File file = new File(path);
            boolean fileExist = file.exists();
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(body, Object.class);
            String indented = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(indented);
                return new FileResp().setFileStatus(fileExist ? FileResp.FileStatus.OVERRIDE : FileResp.FileStatus.CREATE);
            } catch (IOException e) {
                return new FileResp().setFileStatus(FileResp.FileStatus.IO_EXCEPTION);
            }
        } catch (JsonProcessingException e) {
            return new FileResp().setFileStatus(FileResp.FileStatus.WRONG_FORMAT);
        }
    }


    public FileResp readJsonFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                ObjectMapper mapper = new ObjectMapper();
                Object jsonNode = mapper.readValue(new FileReader(path), Object.class);
                String jsonString = mapper.writeValueAsString(jsonNode);
                return new FileResp().setFileStatus(FileResp.FileStatus.FOUND).setContent(jsonString);
            } else {
                return new FileResp().setFileStatus(FileResp.FileStatus.NOT_FOUND);
            }
        } catch (IOException e) {
            return new FileResp().setFileStatus(FileResp.FileStatus.IO_EXCEPTION);
        }
    }


    public List<String> listFileNameWithoutSuffix(String path, String contain, String suffix) {
        File directory = new File(path);
        File[] files = directory.listFiles();
        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().contains(suffix) && file.getName().contains(contain)) {
                    fileNames.add(file.getName().split(suffix)[0]);
                }
            }
        }
        return fileNames;
    }


}

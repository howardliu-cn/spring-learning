package cn.howardliu.springLearning.simple.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * <br>created at 18-3-7
 *
 * @author liuxh
 * @since 0.1.0
 */
@Controller
public class RestUploadController {
    @GetMapping("/rest")
    public String index() {
        return "restUpload";
    }

    @PostMapping(value = "/api/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity<String> uploadFile(@RequestParam(value = "file", required = false) MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("please select a file!", HttpStatus.OK);
        }
        try {
            saveUploadedFiles(Collections.singletonList(file));
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Successfully uploaded - " + file.getOriginalFilename(), new HttpHeaders(),
                HttpStatus.OK);
    }

    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue; //next pls
            }

            System.out.println(file.getName());
            System.out.println(file.getContentType());
            System.out.println(file.getOriginalFilename());
            IOUtils.copy(file.getInputStream(),
                    new FileOutputStream(new File("/home/liuxh/" + file.getOriginalFilename())));
        }
    }

}

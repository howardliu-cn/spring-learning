package cn.howardliu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <br>created at 18-6-26
 *
 * @author liuxh
 * @since 0.1.0
 */
@RestController
@RequestMapping("file")
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "upload")
    public String upload(@RequestParam("file") MultipartFile file,
            @RequestParam(value = "fileName", required = false) String fileName) {
        logger.info("original file name: {}", file.getOriginalFilename());
        logger.info("fileName: {}", fileName == null ? "" : fileName);
        return "upload success\n";
    }
}

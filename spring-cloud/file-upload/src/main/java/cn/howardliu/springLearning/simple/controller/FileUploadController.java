package cn.howardliu.springLearning.simple.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;

/**
 * <br>created at 17-11-15
 *
 * @author liuxh
 * @since 1.0.0
 */
@Controller
public class FileUploadController {
    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file,
            HttpServletResponse response,
            RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:status";
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        try {
            System.out.println(file.getName());
            System.out.println(file.getContentType());
            System.out.println(file.getOriginalFilename());
            IOUtils.copy(file.getInputStream(),
                    new FileOutputStream(new File("/home/liuxh/" + file.getOriginalFilename())));

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:status";
    }

    @GetMapping("status")
    public String status() {
        return "status";
    }
}

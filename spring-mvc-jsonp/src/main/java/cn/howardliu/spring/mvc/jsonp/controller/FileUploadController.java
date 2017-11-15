package cn.howardliu.spring.mvc.jsonp.controller;

import cn.howardliu.spring.mvc.jsonp.pojo.User;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 * <br>created at 17-11-15
 *
 * @author liuxh
 * @since 1.0.0
 */
@Controller
@RequestMapping("file")
public class FileUploadController {
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public JSONObject upload(User user, @RequestParam(value = "files", required = false) MultipartFile[] files) {
        JSONObject json = new JSONObject();
        try {
            for (MultipartFile file : files) {
                System.out.println(file.getName());
                System.out.println(file.getContentType());
                System.out.println(file.getOriginalFilename());
                IOUtils.copy(file.getInputStream(),
                        new FileOutputStream(new File("/home/liuxh/" + file.getOriginalFilename())));
            }
            json.put("success", true);
        } catch (Exception e) {
            json.put("success", false);
        }
        json.put("length", files.length);
        json.put("user", user == null ? "null" : user);
        return json;
    }
}

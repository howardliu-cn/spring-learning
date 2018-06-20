package cn.howardliu.spring.mvc.jsonp;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.params.HttpClientParams;

import java.io.File;
import java.io.IOException;

/**
 * <br>created at 17-11-15
 *
 * @author liuxh
 * @since 1.0.0
 */
public class FileUploadMain {
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod("http://127.0.0.1:8000/file/upload");
        postMethod.addParameter("username", "abc");
        postMethod.addParameter("nickname", "def");
        Part[] parts = {new FilePart("favicon.png", new File("/home/liuxh/Pictures/default1.png"))};
        postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        int status = httpClient.executeMethod(postMethod);
        System.out.println(status);
        System.out.println(postMethod.getResponseBodyAsString());
        postMethod.releaseConnection();
    }
}

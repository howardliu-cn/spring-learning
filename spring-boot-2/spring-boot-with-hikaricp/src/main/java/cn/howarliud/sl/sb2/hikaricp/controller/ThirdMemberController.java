package cn.howarliud.sl.sb2.hikaricp.controller;

import cn.howarliud.sl.sb2.hikaricp.dao.ThirdMemberRepository;
import cn.howarliud.sl.sb2.hikaricp.entity.ThirdMember;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * <br>created at 18-7-13
 *
 * @author liuxh
 * @since 0.1.0
 */
@RestController
@RequestMapping("third")
public class ThirdMemberController {
    @Autowired
    private ThirdMemberRepository thirdMemberRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ThreadPoolTaskExecutor executor;

    @RequestMapping("update")
    public String update() {
        String accessToken = "13_SSeWEAP5nGZSdMYPSS6_As8mlTf_8lrUD9Mwp76PQEQ0HKcEy1yY0D8OlYIZAION4FBAp1kj-wj5bOZwr9y5PDNDlEk7kUEPJs4MbkUeDAiUsjSgBhO-zwB4td6-eXFcLjDLHBz7auJ37kauOMOhACAAYA";
        for (ThirdMember thirdMember : thirdMemberRepository.findAll()) {
            executor.submit(()->{
                Map<String, Object> params = new HashMap<>();
                params.put("access_token", accessToken);
                params.put("openid", thirdMember.getOpenid());
                ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                        "https://api.weixin.qq.com/cgi-bin/user/info?access_token={access_token}&openid={openid}&lang=zh_CN",
                        String.class, params);
                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    JSONObject info = JSON.parseObject(responseEntity.getBody());
                    String nickname = info.getString("nickname");
                    String avatar = info.getString("headimgurl");
                    String unionid = info.getString("unionid");
                    if (StringUtils.isEmpty(unionid)) {
                        System.err.println("error: " + thirdMember.getOpenid());
                        return;
                    }
                    thirdMember.setUnionid(unionid);
                    thirdMember.setAvatar(avatar);
                    thirdMember.setNickname(nickname);
                    thirdMemberRepository.update(thirdMember);
                    System.out.println("ok: " + thirdMember.getOpenid() + " => " + thirdMember.getNickname());
                }
            });
        }
        return "success";
    }

    @RequestMapping("update/unionid")
    public String updateUnionid() {
        String accessToken = "11_s6D0rhlsScJcs7Apojajo6QNxGFdYBTbNxX3margbEerK3yCRaG-41SgAMq4vT1FXvY5CgJOrjIiSZLYt4wRgKbIPanz_e43yXceNIGZS-dZCJhCR5ttj8oINLCzGCoTu1nbsoNDhHGDtqmqXSWdAHALGK";
        Map<String, Object> params;
        for (ThirdMember thirdMember : thirdMemberRepository.findAllForUnionid()) {
            params = new HashMap<>();
            params.put("access_token", accessToken);
            params.put("openid", thirdMember.getOpenid());
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                    "https://api.weixin.qq.com/cgi-bin/user/info?access_token={access_token}&openid={openid}&lang=zh_CN",
                    String.class, params);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                JSONObject info = JSON.parseObject(responseEntity.getBody());
                String unionid = info.getString("unionid");
                if (StringUtils.isEmpty(unionid)) {
                    System.err.println("error: " + thirdMember.getOpenid());
                    continue;
                }
                thirdMember.setUnionid(unionid);
                thirdMemberRepository.updateUnionid(thirdMember);
                System.out.println("ok: " + thirdMember.getOpenid());
            }
        }
        return "success";
    }
}

package io.renren.modules.app.controller;

import io.renren.common.utils.R;
import io.renren.modules.app.entity.TemplateData;
import io.renren.modules.app.entity.WxMssVo;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/app/wx")
public class AccessTokenController {
    @Value("${application.wxpay.app}")
    private String appId;

    @Value("${application.wxpay.secret}")
    private String appSecret;

    @GetMapping("/at")
    public R getAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" +
                "client_credential&appid="+ appId + "&secret=" + appSecret;
        RestTemplate restTemplate = new RestTemplate();
        String template = restTemplate.getForObject(url, String.class);
        assert template != null;
        String json = new String(template.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String access_token = JSONObject.fromObject(json).getString("access_token");
        log.info("template={}",json);
        log.info("access_token={}",access_token);
        return R.ok(json);
    }

    @GetMapping("/push")
    public String pushOneUser() {
        return push("o8NsR5dnEnZxl6fJsR5CjMmHlfLw");
    }

    public String push(String openid) {

        String ur = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" +
                "client_credential&appid="+ appId + "&secret=" + appSecret;
        RestTemplate rest = new RestTemplate();
        String template = rest.getForObject(ur, String.class);
        assert template != null;
        String json = new String(template.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        String access_token = JSONObject.fromObject(json).getString("access_token");


        RestTemplate restTemplate = new RestTemplate();
        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + access_token;
        //拼接推送的模版
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser(openid);//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        wxMssVo.setTemplate_id("03WixDSDkfomb-kVf1Zz5KUF_p8uEdMZZXE2XBAKnpE");//订阅消息模板id
        wxMssVo.setPage("pages/index/index");

        Map<String, TemplateData> m = new HashMap<>(5);
        m.put("character_string1", new TemplateData("123"));
        m.put("thing3", new TemplateData("张亦弛"));
        m.put("thing4", new TemplateData("宾利"));
        m.put("number5", new TemplateData("1"));
        m.put("date6", new TemplateData("2020-7-10"));
        wxMssVo.setData(m);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, String.class);
        return responseEntity.getBody();
    }

    @GetMapping("/cookie")
    public void cookie(HttpServletResponse response) {
        String user = "zh";
        Cookie cookie = new Cookie("user", user);
        int expire = 60*3;
        cookie.setMaxAge(expire);
        response.addCookie(cookie);
    }
}
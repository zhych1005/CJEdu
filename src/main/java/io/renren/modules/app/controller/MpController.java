package io.renren.modules.app.controller;

import io.renren.common.utils.R;
import io.renren.modules.app.config.ProjectParamConfig;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.app.service.UserService;
import io.renren.modules.app.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/mp")
@Api("APP公众号登录接口")
@Slf4j
@CrossOrigin
public class MpController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final ProjectParamConfig paramConfig;

    @Autowired
    public MpController(UserService userService, JwtUtils jwtUtils, ProjectParamConfig paramConfig) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.paramConfig = paramConfig;
    }


    @GetMapping("/login")
    @ApiOperation("登录")
    public String getCode() {
//        String uri = "redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId +
//                "&redirect_uri=" + url + "/life/app/mp/code&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
        return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + paramConfig.appId +
                "&redirect_uri=" + paramConfig.uri + "&response_type=code&scope=snsapi_base" +
                "&state=STATE&connect_redirect=1#wechat_redirect";
    }

    @GetMapping("/code")
    public R getCode(String code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + paramConfig.appId + "&secret=" + paramConfig.appSecret + "&code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String template = restTemplate.getForObject(url, String.class);
        String openId = JSONObject.fromObject(template).getString("openid");
        return R.ok().put("openid", openId);
    }

//    @GetMapping("/auth")
//    @ResponseBody
//    @Transactional
//    public R auth(WxMpLoginForm form, String mobile) {
//        if (mobile == null) {
//            return R.error("手机号不能为空!");
//        }
//        UserEntity userEntity = userService.selectByMobile(mobile);
//        if (userEntity == null) {
//            return R.error("非法用户!");
//        }
//
//        if (!"".equals(userEntity.getOpenId()) && userEntity.getOpenId() != null) {
//            //生成token
//            return generateToken(userEntity, userEntity.getOpenId());
//        }
//
//        ValidatorUtils.validateEntity(form);
//        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
//                + paramConfig.appId + "&secret=" + paramConfig.appSecret + "&code=" + form.getCode() + "&grant_type=authorization_code";
//        RestTemplate restTemplate = new RestTemplate();
//        String template = restTemplate.getForObject(url, String.class);
//        String openId = JSONObject.fromObject(template).getString("openid");
//        if (openId == null || openId.length() == 0) {
//            return R.error("openid获取异常！");
//        }
//        UserEntity findOpenid = userService.selectByOpenid(openId);
//        if (findOpenid != null) {
//            return R.error("请使用本人微信、手机号登录");
//        }
//
//        //将用户的信息保存到数据库，执行登录操作
//        userEntity.setOpenId(openId);
//        userEntity.setMobile(mobile);
//        int count = userService.addOpenid(userEntity);
//        if (count == 0) {
//            return R.error("用户openid添加失败");
//        }
//
//        //生成token
//        UserEntity user = userService.selectByMobile(mobile);
//        return generateToken(userEntity, user.getOpenId());
//    }

    private R generateToken(UserEntity userEntity, String openId2) {
        String token = jwtUtils.generateMpToken(userEntity.getUserId(), openId2, userEntity.getType(), userEntity.getStatus(),userEntity.getAreaId());
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("expire", jwtUtils.getExpire() * 1000);
        result.put("type", userEntity.getType());
        result.put("status", userEntity.getStatus());
        result.put("mobile", userEntity.getMobile());
        result.put("openid", openId2);
        result.put("areaId", userEntity.getAreaId());
        return R.ok(result);
    }
}
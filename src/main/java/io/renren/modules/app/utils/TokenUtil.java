package io.renren.modules.app.utils;

import io.renren.modules.app.vo.TokenVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class TokenUtil {

    private final JwtUtils jwtUtils;

    @Autowired
    public TokenUtil(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public TokenVO tokenResolver(String token) {
        String subject = jwtUtils.getClaimByToken(token).getSubject();
        Map<String, String> map = MapUtils.mapStringToMap(subject);
        int userId = Integer.parseInt(map.get("userId"));
        int areaId = Integer.parseInt(map.get("areaId"));
        String openid = map.get("openid");
        int status = Integer.parseInt(map.get("status"));
        int type = Integer.parseInt(map.get("type"));
        TokenVO tokenVO = new TokenVO();
        tokenVO.setAreaId(areaId);
        tokenVO.setOpenid(openid);
        tokenVO.setStatus(status);
        tokenVO.setType(type);
        tokenVO.setUserId(userId);
        return tokenVO;
    }
}
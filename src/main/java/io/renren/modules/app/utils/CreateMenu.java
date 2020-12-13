package io.renren.modules.app.utils;

import net.sf.json.JSONObject;

import java.io.IOException;

public class CreateMenu {
    public static void main(String[] args) {
        try {
            AccessToken token = WeixinUtil.getAccessToken();
            System.out.println("票据" + token.getToken());
            System.out.println("有效时间" + token.getExpiresIn());

            String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
            int result = WeixinUtil.createMenu(token.getToken(), menu);
//            int result = WeixinUtil.deleteMenu(token.getToken());
            if (result == 0) {
                System.out.println("创建菜单成功");
            } else {
                System.out.println("错误码" + result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
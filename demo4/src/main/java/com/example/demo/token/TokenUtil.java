package com.example.demo.token;

import com.example.demo.util.HttpUtil;
import com.fasterxml.jackson.databind.util.JSONPObject;
import net.sf.json.JSONObject;

public class TokenUtil {
    private static final String APP_ID = "wx2a2797c4ec7cd334";
    private static final String APP_SECRET = "5637a2aa274c0b298082e004df44f6ac";
    private static AccessToken accessToken=new AccessToken();
//    {"access_token":"79_XUJqFF0_ijgft2fXhbUudxLgKZ1tid2Iy-qV_jrGY-v9NnVxj_TwaUdl0erlAmmPTuIAdSCmglGDORGBqgAsqYP-S9iVqE_Awyu5lNqkZwaAjGy88iGkFqlb9f4YAXiADACEB","expires_in":7200}

    public static void main(String[] args){
        System.out.println(getAccessToken());
        System.out.println(getAccessToken());
    }

    public static void getToken(){
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
        APP_ID,
        APP_SECRET);
        String result = HttpUtil.doGet(url);
        JSONObject jsonObject=JSONObject.fromObject(result);
        String token=jsonObject.getString("access_token");
        long expiresIn=jsonObject.getLong("expires_in");
        accessToken.setToken(token);
        accessToken.setExpireTime(expiresIn);
    }

    public static String getAccessToken(){
        if(accessToken==null||accessToken.isExpired()){
            getToken();
        }
        return accessToken.getToken();
    }

}

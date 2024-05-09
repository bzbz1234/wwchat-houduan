package com.example.demo.button;

import com.example.demo.token.TokenUtil;
import com.example.demo.util.HttpUtil;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TestButton {
    public static void main(String[] args){
        Button button =new Button();
        List<AbstractButton> buttons=new ArrayList<>();

        ClickButton clickButton =new ClickButton("企业查询");
        clickButton.setKey("1");

        ClickButton clickButton1 =new ClickButton("岗位查询");
        clickButton1.setKey("2");

        SubButton subButton =new SubButton("更多");
        List<AbstractButton> subButtons=new ArrayList<>();

        ClickButton clickButton2 =new ClickButton("添加岗位");
        clickButton2.setKey("3");
        subButtons.add(clickButton2);

        ClickButton clickButton3 =new ClickButton("删除岗位");
        clickButton3.setKey("4");
        subButtons.add(clickButton3);

        ClickButton clickButton4 =new ClickButton("修改岗位");
        clickButton4.setKey("5");
        subButtons.add(clickButton4);

        ClickButton clickButton5 =new ClickButton("关于我们");
        clickButton5.setKey("6");
        subButtons.add(clickButton5);

        buttons.add(clickButton);
        buttons.add(clickButton1);
        buttons.add(subButton);

        subButton.setSub_button(subButtons);

        button.setButton(buttons);

        JSONObject jsonObject =JSONObject.fromObject(button);
        String json=jsonObject.toString();
        String url=String.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s",TokenUtil.getAccessToken());

        System.out.println(json);
        String token = TokenUtil.getAccessToken();
        System.out.println(token);
        String result = HttpUtil.doPost(url,json);
        System.out.println(result);

//        79_CQVwtzZlOIul3xEkGBhFhs7lJg6a5y_Cj-srhojYmH4aIpVVI1BF21HSga8_kkTNRrbJBqCRP-9Q9MK2drdR_7XR1hdxfbwR1cbSowp0RPLTUzLmr9ETygXfETUZZNgAEALLZ

    }
}

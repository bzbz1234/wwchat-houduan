package com.example.demo.controller;

import com.example.demo.message.TextMessage;
import com.example.demo.Service.MyUserService;
import com.example.demo.user.MyUser;
import com.thoughtworks.xstream.XStream;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
public class MyUserController {
    @Value("${global.myValue1}")
    private int key1;

    @Value("${global.myBooleanValue2}")
    private boolean key2;

    @Value("${global.myBooleanValue3}")
    private boolean key3;

    @Value("${global.myBooleanValue4}")
    private boolean key4;

    @Value("${global.myBooleanValue5}")
    private boolean key5;

    @Value("${global.myBooleanValue6}")
    private boolean key6;

    @Autowired
    private MyUserService myUserService;

    @GetMapping("/hello")
    public String hello() {
        return "hello wechat";
    }

    @GetMapping("/")
    public String check(String signature, String timestamp, String nonce, String echostr) {
        //（1）将token、timestamp、nonce三个参数进行字典序排序
        String token = "java";
        List<String> list = Arrays.asList(token, timestamp, nonce);
        //排序
        Collections.sort(list);
        //（2）将三个参数字符串拼接成一个字符串进行sha1加密
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s);
        }
        //加密
        try {
            MessageDigest instance = MessageDigest.getInstance("sha1");
            //使用sha1进行加密，获得byte数组
            byte[] digest = instance.digest(stringBuilder.toString().getBytes());
            StringBuilder sum = new StringBuilder();
            for (byte b : digest) {
                sum.append(Integer.toHexString((b >> 4) & 15));
                sum.append(Integer.toHexString(b & 15));
            }
            System.out.println("signature:" + signature);
            System.out.println("sum:" + sum);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //（3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
        return echostr;
    }

    @PostMapping("/")
    public String receiveMessage(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        Map<String, String> map = new HashMap<>();
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> elements = root.elements();
            for (Element element : elements) {
                map.put(element.getName(), element.getStringValue());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        System.out.println(map);

        String msgType = map.get("MsgType");
        String message = getReplyMessage(map);
        String reply1 = getReply1(map);
        String reply2 = getReply2(map);
        String reply3 = getReply3(map);
        String reply4 = getReply4(map);
        String reply5 = getReply5(map);
        String reply6 = getReply6(map);

        switch (msgType) {

            case "text":
                if (key1 == 1) {
                    System.out.println("111");
                    return getReply11(map);
                }
                if (key2) {
                    return getReply22(map);
                }
                if (key3) {
                    return getReply33(map);
                }
                if (key4) {
                    return getReply44(map);
                }
                if (key5) {
                    return getReply55(map);
                }
                if (key6) {
                    return getReply66(map);
                }
                else return message;

            case "event":
            String eventKey = map.get("EventKey");

        switch (eventKey) {
            case "1":
                key1 = 1;
                key2 = false;
                key3 = false;
                key4 = false;
                key5 = false;
                key6 = false;

                System.out.println(key1);
                return reply1;
            case "2":
                key1 = 0;
                key2 = true;
                key3 = false;
                key4 = false;
                key5 = false;
                key6 = false;

                return reply2;
            case "3":
                key1 = 0;
                key3 = true;
                key2 = false;
                key4 = false;
                key5 = false;
                key6 = false;
                return reply3;
            case "4":
                key1 = 0;
                key4 = true;
                key2 = false;
                key3 = false;
                key5 = false;
                key6 = false;
                return reply4;
            case "5":
                key1 = 0;
                key5 = true;
                key2 = false;
                key3 = false;
                key4 = false;
                key6 = false;
                return reply5;
            case "6":
                key1 = 0;
                key6 = true;
                key2 = false;
                key3 = false;
                key4 = false;
                key5 = false;
                return reply6;
            default:
                break;
        }
        break;
    }
        String message1="";
        return message1;
        }


    private String getReplyMessage(Map<String,String> map){
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        textMessage.setContent("欢迎关注本公众号！本公众号提供岗位查询，企业查询等服务，可点击下方选项选择服务类型。");
        textMessage.setCreateTime(System.currentTimeMillis()/1000);

        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        return xStream.toXML(textMessage);
    }

    private String getReply1(Map<String,String> map){
        TextMessage textMessage1=new TextMessage();
        textMessage1.setToUserName(map.get("FromUserName"));
        textMessage1.setFromUserName(map.get("ToUserName"));
        textMessage1.setMsgType("text");
        textMessage1.setContent("请输入企业名称如华为，字节跳动等进行查询。");
        textMessage1.setCreateTime(System.currentTimeMillis()/1000);

        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml =xStream.toXML(textMessage1);
        return xml;
    }

    private String getReply2(Map<String,String> map){
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        textMessage.setContent("请输入岗位名称如前端，后端等进行查询。");
        textMessage.setCreateTime(System.currentTimeMillis()/1000);

        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml =xStream.toXML(textMessage);
        return xml;
    }

    private String getReply3(Map<String,String> map){
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        textMessage.setContent("请分行输入公司名，岗位名，及岗位介绍进行增加岗位。");
        textMessage.setCreateTime(System.currentTimeMillis()/1000);

        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml =xStream.toXML(textMessage);
        return xml;
    }

    private String getReply4(Map<String,String> map){
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        textMessage.setContent("请分行输入公司名，岗位名进行删除岗位。");
        textMessage.setCreateTime(System.currentTimeMillis()/1000);

        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml =xStream.toXML(textMessage);
        return xml;
    }

    private String getReply5(Map<String,String> map){
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        textMessage.setContent("请分行输入您要修改的公司名，岗位名及修改后的公司名及岗位名进行修改岗位。");
        textMessage.setCreateTime(System.currentTimeMillis()/1000);

        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml =xStream.toXML(textMessage);
        return xml;
    }

    private String getReply6(Map<String,String> map){
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        textMessage.setContent("本公众号由个人开发，数据较少，请谅解");
        textMessage.setCreateTime(System.currentTimeMillis()/1000);

        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml =xStream.toXML(textMessage);
        return xml;
    }

    private String getReply11(Map<String,String> map){
        String content =map.get("Content");
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        if(content!=null){
        String content1 = myUserService.findUserByCompany(map.get("Content")).toString();
        textMessage.setContent(content1 + "如果要查看下一页，请输入 翻页");
        System.out.println(content1);}
        textMessage.setCreateTime(System.currentTimeMillis()/1000);

        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml =xStream.toXML(textMessage);
        return xml;
    }

    private String getReply22(Map<String,String> map){
        String content =map.get("Content");
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        if(content!=null){
            String content1 = myUserService.findUserByPost(map.get("Content")).toString();
            textMessage.setContent(content1 + "如果要查看下一页，请输入 翻页");
            System.out.println(content1);}
        textMessage.setCreateTime(System.currentTimeMillis()/1000);

        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        return xStream.toXML(textMessage);
    }

    private String getReply33(Map<String,String> map){
        String content =map.get("Content");
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        if(content!=null) {
            MyUser user = MyUser.parseString(content);
            myUserService.save(user);
            textMessage.setContent("添加岗位成功");
        }
            textMessage.setCreateTime(System.currentTimeMillis() / 1000);


        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml =xStream.toXML(textMessage);
        return xml;
    }

    private String getReply44(Map<String,String> map){
        String content =map.get("Content");
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        if(content!=null){
            MyUser user = MyUser.parseString(content);
            myUserService.delete(user);
            textMessage.setContent("删除岗位成功");
            }
        textMessage.setCreateTime(System.currentTimeMillis()/1000);

        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml =xStream.toXML(textMessage);
        return xml;
    }



    private String getReply55(Map<String,String> map){
        String content =map.get("Content");
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        if(content!=null) {
            MyUser user = MyUser.parseString(content);
            MyUser user1 = MyUser.parseString1(content);
            myUserService.delete(user);
            myUserService.save(user1);
            textMessage.setContent("修改岗位成功");
            textMessage.setCreateTime(System.currentTimeMillis() / 1000);
        }
        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml =xStream.toXML(textMessage);
        return xml;
    }

    private String getReply66(Map<String,String> map){
        String content =map.get("Content");
        TextMessage textMessage=new TextMessage();
        textMessage.setToUserName(map.get("FromUserName"));
        textMessage.setFromUserName(map.get("ToUserName"));
        textMessage.setMsgType("text");
        if(content!=null){
            textMessage.setContent("本公众号由个人开发，数据较少请谅解，老师多给点分吧o(╥﹏╥)o");
            }
        textMessage.setCreateTime(System.currentTimeMillis()/1000);

        //转成XML格式
        XStream xStream = new XStream();
        xStream.processAnnotations(TextMessage.class);
        String xml =xStream.toXML(textMessage);
        return xml;
    }

}

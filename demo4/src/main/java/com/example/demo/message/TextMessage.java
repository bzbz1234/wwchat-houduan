package com.example.demo.message;

//<xml>
//<ToUserName><![CDATA[toUser]]></ToUserName>
//<FromUserName><![CDATA[fromUser]]></FromUserName>
//<CreateTime>12345678</CreateTime>
//<MsgType><![CDATA[text]]></MsgType>
//<Content><![CDATA[你好]]></Content>
//</xml>

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class TextMessage {
    @XStreamAlias("ToUserName")
    private String toUserName;
    @XStreamAlias("FromUserName")
    private String fromUserName;
    @XStreamAlias("CreateTime")
    private long createTime;
    @XStreamAlias("MsgType")
    private String msgType;
    @XStreamAlias("Content")
    private String content;

    public String getToUserName(){
        return toUserName;
    }

    public void setToUserName(String fromUserName){
        this.toUserName= fromUserName;
    }

    public String getFromUserName(){
        return fromUserName;
    }

    public void setFromUserName(String fromUserName){
        this.fromUserName= fromUserName;
    }

    public long getCreateTime(){
        return createTime;
    }

    public void setCreateTime(long createTime){
        this.createTime= createTime;
    }

    public String getMsgType(){
        return msgType;
    }

    public void setMsgType(String msgType){
        this.msgType= msgType;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content= content;
    }

    @Override
    public String toString(){
        return "TextMessage{" +
                "ToUserName'" + toUserName + '\'' +
                ", FromUserName'" + fromUserName + '\'' +
                ", CreateName'" + createTime + '\'' +
                ", MsgType'" + msgType + '\'' +
                ", Content'" + content + '\'' +
                '}';
    }
}

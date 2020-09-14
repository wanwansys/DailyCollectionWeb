package com.shkj.bean;

import com.fasterxml.jackson.databind.util.JSONPObject;

public class DailyMsgModel {
    private String msgId;
    private String msgType;
    private long timestamp;
    private String fromId;
    private String toId;
    private long groupId;
    private String groupName;
    private String msgContent;
    private JSONPObject imageInfo;
    private JSONPObject customInfo;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public JSONPObject getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(JSONPObject imageInfo) {
        this.imageInfo = imageInfo;
    }

    public JSONPObject getCustomInfo() {
        return customInfo;
    }

    public void setCustomInfo(JSONPObject customInfo) {
        this.customInfo = customInfo;
    }

    @Override
    public String toString() {
        return "DailyMsgModel{" +
                "msgId='" + msgId + '\'' +
                ", msgType='" + msgType + '\'' +
                ", timestamp=" + timestamp +
                ", fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", msgContent='" + msgContent + '\'' +
                ", imageInfo=" + imageInfo +
                ", customInfo=" + customInfo +
                '}';
    }
}

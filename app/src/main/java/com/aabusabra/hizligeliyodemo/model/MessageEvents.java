package com.aabusabra.hizligeliyodemo.model;


public class MessageEvents {

    private MessageTypes type;
    private Object data;


    public MessageEvents(MessageTypes type, Object data) {
        this.type = type;
        this.data = data;
    }

    public MessageEvents(MessageTypes type) {
        this.type = type;
        this.data = null;
    }

    public MessageTypes getType() {
        return type;

    }

    public void setType(MessageTypes type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}


package com.furnisoft.pojos;

public class Message {
    
    private boolean messageStatus;
    private String message;

    public Message() {
        this.messageStatus = false;
        this.message = "";
    }
    
    public Message(boolean messageStatus, String message) {
        this.messageStatus = messageStatus;
        this.message = message;
    }
    
    public Message(Message msg) {
        this.messageStatus = msg.messageStatus;
        this.message = msg.message;
    }

    public void setMessageStatus(boolean messageStatus) {
        this.messageStatus = messageStatus;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isMessageStatus() {
        return messageStatus;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" + "messageStatus=" + messageStatus + ", message=" + message + '}';
    }
}

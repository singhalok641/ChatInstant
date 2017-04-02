package com.example.root.chatinstant;


import java.util.Random;

/**
 * Created by root on 3/30/17.
 */

public class ChatMessage {

    public String body, sender, receiver, senderName;
    public String Date, Time;
    public String msgid;
    public boolean isMine;// Did I send the message.

    public ChatMessage(String Sender, String Receiver, String messageString,
                       String ID, boolean isMINE) {
        body = messageString;
        isMine = isMINE;
        sender = Sender;
        msgid = ID;
        receiver = Receiver;
        senderName = sender;
    }

    public void setMsgID() {

        msgid += "-" + String.format("%02d", new Random().nextInt(100));
    }

    public String getBody() {
        return body;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public String getMsgid() {
        return msgid;
    }

    public boolean isMine() {
        return isMine;
    }
}

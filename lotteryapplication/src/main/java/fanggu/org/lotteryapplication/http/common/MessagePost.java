package fanggu.org.lotteryapplication.http.common;


import fanggu.org.lotteryapplication.util.JSONHelper;
import fanggu.org.lotteryapplication.util.StringUtils;

/**
 * Created by long on 2015/11/7.
 */
public class MessagePost {
    public MessageHead messagehead;
    public MessageBody messagebody;


    public  MessagePost(){  //不能删除

    }


    public MessagePost(int msgType, boolean isencryot, String MessageBodyContent) {
        this.messagehead = new MessageHead(msgType, isencryot);
        if (isencryot && !StringUtils.isBlank(MessageBodyContent)) {
            try {
                this.messagebody = new MessageBody( CryptoHelper.GetHandle().encode(MessageBodyContent));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.messagebody = new MessageBody(MessageBodyContent);
        }
    }


    public MessageHead getMessagehead() {
        return messagehead;
    }

    public void setMessagehead(MessageHead messagehead) {
        this.messagehead = messagehead;
    }

    public MessageBody getMessagebody() {
        return messagebody;
    }

    public void setMessagebody(MessageBody messagebody) {
        this.messagebody = messagebody;
    }

    public String toJSON(){
        return JSONHelper.toJSON(this);
    }





}

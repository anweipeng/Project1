package fanggu.org.lotteryapplication.http.common;


import java.util.Date;

import fanggu.org.lotteryapplication.util.DateUtils;

/**
 * Created by long on 2015/11/7.
 */
public class MessageHead {

    /// <summary>
    /// 消息类型
    /// </summary>
    public int messagetype;
    /// <summary>
    /// 是否加密
    /// </summary>
    public Boolean isencrypt;

    public String currentdate;

    public String userIPAddress;

    public MessageHead(){}

    public MessageHead(int messagetype, Boolean isencrypt) {
        this.messagetype = messagetype;
        this.isencrypt = isencrypt;
        this.currentdate = DateUtils.date2Str(new Date());
    }

    public MessageHead(int messagetype, Boolean isencrypt, Date date) {
        this.messagetype = messagetype;
        this.isencrypt = isencrypt;
        this.currentdate = DateUtils.date2Str(date);
    }


    public void setMessagetype(int messagetype) {
        this.messagetype = messagetype;
    }

    public Boolean getIsencrypt() {
        return isencrypt;
    }

    public void setIsencrypt(Boolean isencrypt) {
        this.isencrypt = isencrypt;
    }

    public String getCurrentdate() {
        return currentdate;
    }

    public void setCurrentdate(String currentdate) {
        this.currentdate = currentdate;
    }

    public String getUserIPAddress() {
        return userIPAddress;
    }

    public void setUserIPAddress(String userIPAddress) {
        this.userIPAddress = userIPAddress;
    }

    public int getMessagetype() {
        return messagetype;
    }
}

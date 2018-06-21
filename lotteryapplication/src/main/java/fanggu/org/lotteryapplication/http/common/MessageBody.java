package fanggu.org.lotteryapplication.http.common;

/**
 * Created by long on 2015/11/7.
 */
public class MessageBody {
    public String content;

    public MessageBody(){}
    public MessageBody(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package fanggu.org.lotteryapplication.http.buyNOOrderRequest;

public class BuyNOOrderRequest
{
    /// <summary>
    /// 用户ID
    /// </summary>
    public int UserID ;


    /// <summary>
    /// 实体店编码
    /// </summary>
    public String ShopCode ;


    /// <summary>
    /// 托（购买）数字 !分割
    /// 前二前三的玩法不同位之间用@分割 
    /// 例如 任四玩法 1!2!3!4   前三玩法 1!2@3!4@5!6
    /// </summary>
    public String TuoNOs ;


    /// <summary>
    /// 胆数字 非胆拖玩法不用传值 !分割
    /// </summary>
    public String DanNOs ;


    /// <summary>
    /// 是否组选玩法
    /// </summary>
    public boolean isGroup;


    /// <summary>
    /// 期号信息
    /// </summary>
    public String TimeNO ;


    /// <summary>
    /// 购买倍数
    /// </summary>
    public int Mutiple ;


    /// <summary>
    /// 玩法名称
    /// </summary>
    public String PlayName ;


    /// <summary>
    /// 购买金额
    /// </summary>
    public int Money ;


    /// <summary>
    /// 支付密码
    /// </summary>
    public String PayPassword ;


    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getShopCode() {
        return ShopCode;
    }

    public void setShopCode(String shopCode) {
        ShopCode = shopCode;
    }

    public String getTuoNOs() {
        return TuoNOs;
    }

    public void setTuoNOs(String tuoNOs) {
        TuoNOs = tuoNOs;
    }

    public String getDanNOs() {
        return DanNOs;
    }

    public void setDanNOs(String danNOs) {
        DanNOs = danNOs;
    }

    public boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }

    public String getTimeNO() {
        return TimeNO;
    }

    public void setTimeNO(String timeNO) {
        TimeNO = timeNO;
    }

    public int getMutiple() {
        return Mutiple;
    }

    public void setMutiple(int mutiple) {
        Mutiple = mutiple;
    }

    public String getPlayName() {
        return PlayName;
    }

    public void setPlayName(String playName) {
        PlayName = playName;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }

    public String getPayPassword() {
        return PayPassword;
    }

    public void setPayPassword(String payPassword) {
        PayPassword = payPassword;
    }
}
package fanggu.org.lotteryapplication.http.login;


import java.math.BigDecimal;

import fanggu.org.lotteryapplication.http.common.ResultInfo;

/**
 * Created by Administrator on 2015/11/9.
 */
public class UserLoginResponse extends ResultInfo {
    public static final int CODE_1001 = 1001;//成功Code
    public static final int CODE_1010 = 1010;//成功Code
    public static final int CODE_1005 = 1005;//密码输入错误5次

    /// <summary>
    /// 登录名
    /// </summary>
    public String LoginName ;
    /// <summary>
    /// 用户ID
    /// </summary>
    public Long UserID ;
    /// <summary>
    /// 账户
    /// </summary>
    public BigDecimal Acount ;
    /// <summary>
    /// 红包
    /// </summary>
    public BigDecimal RedBag ;
    /// <summary>
    /// 昵称
    /// </summary>
    public String NickName ;

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public Long getUserID() {
        return UserID;
    }

    public void setUserID(Long userID) {
        UserID = userID;
    }

    public BigDecimal getAcount() {
        return Acount;
    }

    public void setAcount(BigDecimal acount) {
        Acount = acount;
    }

    public BigDecimal getRedBag() {
        return RedBag;
    }

    public void setRedBag(BigDecimal redBag) {
        RedBag = redBag;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }
}

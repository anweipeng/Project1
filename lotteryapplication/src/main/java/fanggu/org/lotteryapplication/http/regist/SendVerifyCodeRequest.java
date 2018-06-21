package fanggu.org.lotteryapplication.http.regist;

public class SendVerifyCodeRequest
{
    /// <summary>
    /// 验证途径 1 手机短信 2 邮箱
    /// </summary>
    public int Verifytype ;
    /// <summary>
    /// 用户 1注册 2 找回密码  3 完善信息
    /// </summary>
    public int UserType ;
    /// <summary>
    /// 手机号码
    /// </summary>
    public String PhoneNumber ;
    /// <summary>
    /// 电子邮件
    /// </summary>
    public String Email ;
}


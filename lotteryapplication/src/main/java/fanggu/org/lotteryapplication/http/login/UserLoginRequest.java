package fanggu.org.lotteryapplication.http.login;

/**
 * Created by long on 2015/11/7.
 */
public class UserLoginRequest {

    private String LoginName;
    private String Password;

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public UserLoginRequest(){}
    public UserLoginRequest(String loginName, String password) {
        LoginName = loginName;
        Password = password;
    }
}

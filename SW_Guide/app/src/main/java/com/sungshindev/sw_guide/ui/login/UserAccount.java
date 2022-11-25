package com.sungshindev.sw_guide.ui.login;

public class UserAccount
{
    private String idToken;     //  Firebase Uid (고유 토큰정보)
    private String emailID;     // 이메일 아이디
    private String password;    // 비밀번호
    private String name;        // 이름
    private String pnum;        // 전화번호

    public UserAccount() { }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenum() {
        return pnum;
    }

    public void setPhonenum(String phonenum) {
        this.pnum = phonenum;
    }
}

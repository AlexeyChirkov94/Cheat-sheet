package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.hardcode.BeforeAdvice2;

public class UserInfo {

    private final String userName;
    private final String password;

    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

}

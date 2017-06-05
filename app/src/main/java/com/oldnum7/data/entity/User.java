package com.oldnum7.data.entity;

/**
 * <pre>
 *       author : denglin
 *       time   : 2017/06/05/14:04
 *       desc   :
 *       version: 1.0
 * </pre>
 */
public class User {
    
    private String headImg;
    private String token;
    private String userName;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "User{" +
                "headImg='" + headImg + '\'' +
                ", token='" + token + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}

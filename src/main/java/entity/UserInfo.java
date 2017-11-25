package entity;

import java.io.Serializable;

/**
 * Created by gaoyunfan on 2017/11/24
 **/
public class UserInfo implements Serializable
{
    private int id;
    private String userName;
    private String password;

    @Override
    public String toString()
    {
        return "UserInfo{" + "id=" + id + ", userName='" + userName + '\'' + ", password='" + password + '\'' + '}';
    }

    public UserInfo(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public UserInfo()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}

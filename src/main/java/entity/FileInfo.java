package entity;

import java.io.Serializable;

/**
 * Created by gaoyunfan on 2017/11/24
 **/
public class FileInfo implements Serializable
{
    private int id;
    private int userId;
    private byte[] picInfo;

    @Override
    public String toString()
    {
        return "FileInfo{" + "id=" + id + ", userId=" + userId + '}';
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public byte[] getPicInfo()
    {
        return picInfo;
    }

    public void setPicInfo(byte[] picInfo)
    {
        this.picInfo = picInfo;
    }
}

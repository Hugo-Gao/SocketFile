package dao;

import entity.UserInfo;

/**
 * Created by gaoyunfan on 2017/11/25
 **/
public interface IUserInfo
{
    int saveUserInfo(UserInfo userInfo);

    UserInfo checkUser(UserInfo userInfo);
}

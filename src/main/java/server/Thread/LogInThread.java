package server.Thread;

import dao.IUserInfo;
import db.DBAccess;
import entity.UserInfo;
import org.apache.ibatis.session.SqlSession;

import java.io.*;
import java.net.Socket;

/**
 * Created by gaoyunfan on 2017/11/25
 **/
public class LogInThread extends Thread
{
    private Socket socket;

    public LogInThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            UserInfo userInfo = (UserInfo) objectInputStream.readObject();
            System.out.println("收到了登陆信息" + userInfo);
            //查询数据库 TODO

            SqlSession sqlSession = DBAccess.getSqlSession();
            IUserInfo iUserInfo = sqlSession.getMapper(IUserInfo.class);
            UserInfo checkedUserInfo = iUserInfo.checkUser(userInfo);
            System.out.println("用户在数据库信息为"+checkedUserInfo);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            if (checkedUserInfo == null)
            {
                printWriter.write(-1+"");
            } else
            {
                printWriter.write(checkedUserInfo.getId()+"");//返回用户id
            }
            printWriter.flush();
            printWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}

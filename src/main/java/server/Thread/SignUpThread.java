package server.Thread;

import dao.IUserInfo;
import db.DBAccess;
import entity.UserInfo;
import org.apache.ibatis.session.SqlSession;

import javax.swing.plaf.ScrollBarUI;
import java.io.*;
import java.net.Socket;

/**
 * Created by gaoyunfan on 2017/11/25
 **/
public class SignUpThread extends Thread
{
    private Socket socket;

    public SignUpThread(Socket socket)
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
            System.out.println("我是服务器,客户端传来登陆信息"+userInfo.toString());
            //存入数据库
            SqlSession sqlSession = DBAccess.getSqlSession();
            IUserInfo iUserInfo = sqlSession.getMapper(IUserInfo.class);
            int id=iUserInfo.saveUserInfo(userInfo);
            sqlSession.commit();
            sqlSession.close();
            System.out.println("User信息存入成功");
            //返回数据
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("YES");
            printWriter.flush();
            printWriter.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }finally
        {
            try
            {
                socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

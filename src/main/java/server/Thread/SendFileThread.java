package server.Thread;

import dao.IFileInfo;
import db.DBAccess;
import entity.FileInfo;
import org.apache.ibatis.session.SqlSession;

import java.io.*;
import java.net.Socket;

/**
 * Created by gaoyunfan on 2017/11/25
 **/
public class SendFileThread extends Thread
{
    private Socket socket;

    public SendFileThread(Socket socket)
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
            FileInfo fileInfo = (FileInfo) objectInputStream.readObject();
            System.out.println("收到文件大小"+fileInfo.getPicInfo().length/1024+"KB");
            //TODO 存入数据库
            SqlSession sqlSession = DBAccess.getSqlSession();
            IFileInfo iFileInfo = sqlSession.getMapper(IFileInfo.class);
            iFileInfo.insertFileInfo(fileInfo);
            System.out.println("存入成功");
            sqlSession.commit();
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("true");
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

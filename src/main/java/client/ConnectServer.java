package client;

import constpack.ConstPort;
import entity.FileInfo;
import entity.UserInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by gaoyunfan on 2017/11/24
 * 与服务器交流相关类
 **/
public class ConnectServer
{
    //向服务器发送登陆信息
    public static boolean signToServer(UserInfo userInfo)
    {
        boolean ret = false;
        Socket socket = null;
        try
        {
            socket = new Socket("localhost", ConstPort.SIGN_PORT);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(userInfo);
            objectOutputStream.flush();
            socket.shutdownOutput();
            //获取回复
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String result = scanner.next();
            if (result.equals("YES"))
            {
                ret = true;
            } else
            {
                return false;
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        return ret;
    }

    public static int logToServer(UserInfo userInfo)
    {
        int result=-1;
        Socket socket = null;
        try
        {
            socket = new Socket("localhost", ConstPort.LOG_PORT);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(userInfo);
            objectOutputStream.flush();
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            result = Integer.parseInt(scanner.next());
            scanner.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(socket!=null) socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static boolean sendFileToServer(FileInfo fileInfo)
    {
        boolean result=false;
        Socket socket = null;
        try
        {
            socket = new Socket("localhost", ConstPort.SEND_FILE);
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(fileInfo);
            objectOutputStream.flush();
            InputStream inputStream = socket.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            result = Boolean.parseBoolean(scanner.next());
            scanner.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if(socket!=null) socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }
}

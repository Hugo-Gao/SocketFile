package client;

import entity.FileInfo;
import entity.UserInfo;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.io.*;
import java.util.*;

/**
 * Created by gaoyunfan on 2017/11/24
 * 客户端实现
 **/
public class Client
{
    private static UserInfo logInUserInfo = null;

    public static void main(String[] args)
    {

        showMenu();
    }

    private static void showMenu()
    {
        System.out.println("请输入数字:");
        System.out.println("1.登陆\n2.注册");
        Scanner scanner = new Scanner(System.in);
        int code = scanner.nextInt();
        if (code == 1)
        {
            logIn();
        } else if (code == 2)
        {
            signIn();
        } else
        {
            System.out.println("输入错误！！");
            showMenu();
        }
    }

    /**
     * 处理注册业务
     */
    private static void signIn()
    {
        System.out.println("开始注册:");
        System.out.println("请输入用户名:");
        String userName, password;
        Scanner scanner = new Scanner(System.in);
        userName = scanner.next();
        System.out.println("请输入密码:");
        password = scanner.next();
        UserInfo userInfo = new UserInfo(userName, password);
        if (ConnectServer.signToServer(userInfo))
        {
            System.out.println("注册成功");
            logIn();
        } else
        {
            System.out.println("服务器连接失败请重试");
            signIn();
        }
    }

    /**
     * 处理登陆业务
     */
    private static void logIn()
    {
        System.out.println("开始登陆:");
        System.out.println("请输入用户名:");
        String userName, password;
        Scanner scanner = new Scanner(System.in);
        userName = scanner.next();
        System.out.println("请输入密码:");
        password = scanner.next();
        UserInfo userInfo = new UserInfo(userName, password);
        int userId = ConnectServer.logToServer(userInfo);
        if (userId == -1)
        {
            System.out.println("用户名密码错误请重试");
            logIn();
        } else
        {
            userInfo.setId(userId);
            logInUserInfo = userInfo;
            System.out.println("注册成功");
            sendFile();
        }
    }

    private static void sendFile()
    {
        try
        {
            System.out.println("开始发送文件");
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入文件所在路径:");
            String path = scanner.next();
            scanner.close();
            byte[] fileByte = toByteArray(path);
            FileInfo fileInfo = new FileInfo();
            fileInfo.setPicInfo(fileByte);
            fileInfo.setUserId(logInUserInfo.getId());
            if(ConnectServer.sendFileToServer(fileInfo))
            {
                System.out.println("发送成功");
            }else {
                System.out.println("发送失败");
                sendFile();
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static byte[] toByteArray(String path) throws IOException
    {
        File f = new File(path);
        if (!f.exists())
        {
            throw new FileNotFoundException(path);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;

        try
        {
            in = new BufferedInputStream(new FileInputStream(f));
            int size = 1024;
            byte[] buffer = new byte[size];
            int len ;
            while (-1 != (len = in.read(buffer, 0, size)))
            {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                in.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }
}



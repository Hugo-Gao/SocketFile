package server.serverImpl;

import constpack.ConstPort;
import server.Thread.SignUpThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by gaoyunfan on 2017/11/25
 **/
public class GetSignInfoServerRunnable implements Runnable
{
    private ServerSocket serverSocket;

    public GetSignInfoServerRunnable(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }

    public void run()
    {
        System.out.println("注册服务器启动成功...");
        while (true)
        {
            try
            {
                Socket socket = serverSocket.accept();
                System.out.println("注册服务启动");
                Thread thread = new SignUpThread(socket);
                thread.start();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

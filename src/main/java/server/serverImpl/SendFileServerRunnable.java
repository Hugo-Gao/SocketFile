package server.serverImpl;

import server.Thread.LogInThread;
import server.Thread.SendFileThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by gaoyunfan on 2017/11/25
 **/
public class SendFileServerRunnable implements Runnable
{
    private ServerSocket serverSocket;

    public SendFileServerRunnable(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }

    public void run()
    {
        System.out.println("文件服务器启动成功...");
        while (true)
        {
            try
            {
                Socket socket = serverSocket.accept();
                System.out.println("文件服务启动");
                Thread thread = new SendFileThread(socket);
                thread.start();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}

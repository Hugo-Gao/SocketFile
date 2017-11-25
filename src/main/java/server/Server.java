package server;

import constpack.ConstPort;
import server.Thread.SignUpThread;
import server.serverImpl.GetSignInfoServerRunnable;
import server.serverImpl.LoginServerRunnable;
import server.serverImpl.SendFileServerRunnable;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by gaoyunfan on 2017/11/25
 **/
public class Server
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket signServerSocket = new ServerSocket(ConstPort.SIGN_PORT);
        Thread signThread = new Thread(new GetSignInfoServerRunnable(signServerSocket));
        signThread.start();

        ServerSocket logInServerSocket = new ServerSocket(ConstPort.LOG_PORT);
        Thread logThread = new Thread(new LoginServerRunnable(logInServerSocket));
        logThread.start();

        ServerSocket sendFileServerSocket = new ServerSocket(ConstPort.SEND_FILE);
        Thread fileThread = new Thread(new SendFileServerRunnable(sendFileServerSocket));
        fileThread.start();
    }
}

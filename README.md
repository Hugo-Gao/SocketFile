这是一个由Java编写的，使用Socket+Mybtis+Maven技术构建的带登录功能的文件上传服务器。首先运行server/Server.java，然后运行client/Client.java。其效果如下图所示。

![客户端](https://i.loli.net/2017/11/25/5a19493b28cd2.png)

![服务器](https://i.loli.net/2017/11/25/5a1949add7972.png)

可以看到数据库中存入了用户信息和文件信息：

![文件数据库](https://i.loli.net/2017/11/25/5a1949eee9025.png)

![用户数据库](https://i.loli.net/2017/11/25/5a1949ef1cab7.png)

---

由于本工程是由Maven管理的，所以首先要在POM.xml文件中添加Mybits和JDBC依赖。这个项目并没有用Servlet或者Spring等技术，我们就使用底层的Socket实现。最开始我们要确定实现功能：1.用户注册；2.用户登陆；3.上传文件，而且必须要用户登录后才能上传文件。

那么问题随之就来了，我们以前实现的Socket好像只能完成单一功能，而这里我们有三个功能需要实现。我最开始想的是像Spring和Servlet一样通过指定不同的Socket路径来区分不同的功能，但是我Google后发现好像不行，InetAddress和Socket都没有提供指定路径的功能。于是我就打了端口号的注意，一个Socket绑定一个端口号，那么我们可以创建多个Socket，一个Socket实现一个功能并且指定一个端口号就行了。我通过创建三个线程分别指定创建三个不同功能的Socket，在各个Socket线程里循环运行对应的服务，就实现了多线程服务客户端。

```java
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
```

具体的实现可以看源码 https://github.com/Hugo-Gao/SocketFile


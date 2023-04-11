package site.neware.socket_demo.server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Nick
 * @date 2023/3/6
 */
public class Server  {

    public static void main(String[] args) {
        ServerSocket server = null;
        Socket s = null;

        try {
            server = new ServerSocket(4000);
            System.out.println ("监听用户连接......");
            while (true) {
                s = server.accept ();//监听,阻塞连接
                System.out.println ("已监听到客户连接到[远程主机" + s.getRemoteSocketAddress ()
                        + ":端口" +
                        s.getPort () + "]");
                new Thread (new Service (s)).start ();
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }
}

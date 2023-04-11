package site.neware.socket_demo.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public  class HssServer1 extends Thread{
    ServerSocket server = null;
    Socket socket = null;
    public HssServer1(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run(){

        super.run();
        try{
            System.out.println("wait client connect...");
            socket = server.accept();
            new sendMessThread().start();//连接并返回socket后，再启用发送消息线程
            System.out.println(socket.getInetAddress().getHostAddress() + ": 4000 " + "SUCCESS TO CONNECT...");
            InputStream in = socket.getInputStream();
            int len = 0;
            byte[] buf = new byte[1024];
            while ((len=in.read(buf))!=-1){
                System.out.println("（4000）接收到客户端消息:"+new String(buf,0,len));
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    class sendMessThread extends Thread{
        @Override
        public void run(){
            super.run();
            Scanner scanner=null;
            OutputStream out = null;
            try{
                if(socket != null){
                    scanner = new Scanner(System.in);
                    out = socket.getOutputStream();
                    InputStream inputStream = socket.getInputStream();

                    String in = "";
                    do {
                        in = scanner.next();
                        if(in.equals("6") ){
                            in = "`SC`005C1.00ZXG10000HLRAGENT000056FFDLGCONFFFF00000001TXEND FFFFACK:LOGIN:RETN=000000, DESC=success A4CAA59A";
                        }else {
                            in = "`SC`00601.00ZXG10000HLRAGENT000056FFDLGCONFFFF00000002TXEND FFFFACK:MOD STATE:RETN=000000, DESC=success FD9DE7A7";
                        }
                        out.write(in.getBytes("GB2312"));
                        out.flush();//清空缓存区的内容
                    }while (!in.equals("q"));
                    scanner.close();
                    try{
                        out.close();
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    //函数入口
    public static void main(String[] args) {
        HssServer1 server = new HssServer1(4000);
        server.start();
    }
}

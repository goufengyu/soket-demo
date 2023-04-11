package site.neware.socket_demo.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Nick
 * @date 2023/3/6
 */
public class Service implements Runnable {

    private InputStream input = null;
    private OutputStream output = null;

    private Socket socket = null;

    public Service(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {

            try {
                output = socket.getOutputStream();
                InputStream in = socket.getInputStream();
                int len = 0;
                byte[] buf = new byte[1024];
                String str = null;
                while ((len=in.read(buf))!=-1){
                    str = new String(buf,0,len);
                    System.out.println("（4000）接收到客户端消息: "+new String(buf,0,len));
                    if(str.contains("LOGIN")){
                        str = "`SC`005C1.00ZXG10000HLRAGENT000056FFDLGCONFFFF00000001TXEND FFFFACK:LOGIN:RETN=000000, DESC=success A4CAA59A";
                    }else if(str.contains("QRY DYNUSAGE")){
                       // str = "`SC`01331.00ZXG10000HLRAGENT000056FFDLGCONFFFF00000003TXEND FFFFACK:Qry DynUsage:RETN=000000,DESC=success,IMSI=454005267801023,ISDN=85258306,UsgTypeIDList=10000434,UsgValueList=20971520,UsgUpdateTimeList=20230301165251,REALTIMEFLAG=1,PAKIDLIST=10000434,UsgNextResetTimeList=20230307180000,UsgQuotaStatusList=Normal 9A98E58E";
                        str = "`SC`010F1.00ZXG10000HLRAGENT000056FFDLGCONFFFF00000003TXEND FFFFACK:Qry DynUsage:RETN=000000,DESC=success,IMSI=454005267801023,ISDN=85258306,UsgTypeIDList=10000434,UsgValueList=20971520,UsgUpdateTimeList=20230301165251,REALTIMEFLAG=1,PAKIDLIST=10000434,UsgQuotaStatusList=Normal 9A98E58E";
                    }else {
                        str = "`SC`00601.00ZXG10000HLRAGENT000056FFDLGCONFFFF00000002TXEND FFFFACK:MOD STATE:RETN=000000, DESC=success FD9DE7A7";
                    }
                    output.write(str.getBytes("GB2312"));
                    output.flush();//清空缓存区的内容
                }
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(output != null){
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(input != null){
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


}

package site.neware.socket_demo.client;

/**
 * @author Nick
 * @date 2023/3/7
 */
public class Test {
    public static void main(String[] args) {

        System.out.println("1.00ZXG10000HLRAGENT000056FFDLGCONFFFF00000002TXEND FFFFACK:Qry DynUsage:RETN=000000,DESC=success,IMSI=454005267801023,ISDN=85258306,UsgTypeIDList=10000434,UsgValueList=20971520,UsgUpdateTimeList=20230301165251,REALTIMEFLAG=1,PAKIDLIST=10000434,UsgNextResetTimeList=20230301180000,UsgQuotaStatusList=Normal".length());
        int msgLength = Integer.valueOf("005C", 16);
        int ss = "1.00ZXG10000HLRAGENT000056FFDLGCONFFFF00000001TXEND FFFFACK:LOGIN:RETN=000000, DESC=success".length();
        System.out.println(ss);
        System.out.println(msgLength);
    }
}

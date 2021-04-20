package com.zjty.productshow.util;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * <h4>Description : 校验mac</h4>
 *
 * @Author czq
 * @Date 2/3/21 9:56 AM
 * @Version 1.0
 */
public class MacAddressToolUtil {



    private static String OS = System.getProperty("os.name").toLowerCase();

    public static boolean check(String checkCode) throws Exception {
        String code = getOS();
        if(code.equals("OTHER")){
            return false;
        }else return checkCode.equals(code);

    }

    public static String getOS() throws Exception {
        String mac;
        if (OS.contains("windows")){
            System.out.println("windows");
            mac = getMACAddressByWindows();
        }else if (OS.contains("linux")){
            System.out.println("linux");
            mac =  getMACAddressByLinux();
        }else {
            System.out.println("other");
            mac = getMacAddress();
        }
        return workMac(mac);
    }




    public static String getMacAddress() {
      try {
          Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
          byte[] mac = null;
          while (allNetInterfaces.hasMoreElements()) {
              NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
              if (netInterface.isLoopback() || netInterface.isVirtual() || netInterface.isPointToPoint() || !netInterface.isUp()) {
                  continue;
              } else {
                  mac = netInterface.getHardwareAddress();
                  if (mac != null) {
                      StringBuilder sb = new StringBuilder();
                      for (int i = 0; i < mac.length; i++) {
                          sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
                      }
                      if (sb.length() > 0) {
                          return sb.toString();
                      }
                  }
              }
          }
      } catch (Exception e) {
          e.printStackTrace();
          //logger.error("MAC地址获取失败", e);
      }
      return "";
    }
private static String getMACAddressByLinux() throws Exception {
    String[] cmd = {"ifconfig"};
    Process process = Runtime.getRuntime().exec(cmd);
    process.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
    StringBuffer sb = new StringBuffer();
    String line;
    while ((line = br.readLine()) != null) sb.append(line);
    String str1 = sb.toString();
    String str2 = str1.split("ether")[1].trim();
    String result = str2.split("txqueuelen")[0].trim();
    br.close();
    return result;
}


private static String getMACAddressByWindows() throws Exception {
    String result = "";
    Process process = Runtime.getRuntime().exec("ipconfig /all");
    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GBK"));
    int index = -1;
    String line;
    while ((line = br.readLine()) != null) {
        index = line.toLowerCase().indexOf("物理地址");
        if (index >= 0) {
            index = line.indexOf(":");
            if (index >= 0)
                result = line.substring(index + 1).trim();
            break;
        }
    }
    br.close();
    return result;
}


    /**
     * 生成检验码
     * @param Mac
     * @return
     */
    public static String workMac(String Mac) {
        StringBuilder stringBuilder = new StringBuilder();
        byte[] hmac = SM3Util.hmac("AB12".getBytes(), Mac.getBytes());

        for (byte b : hmac) {
            String hex;
            Integer x = new Integer(b);
            if (x.intValue() < 0) {
                hex = Integer.toHexString(-x.intValue());
            } else {
                hex = Integer.toHexString(x.intValue());
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }

}

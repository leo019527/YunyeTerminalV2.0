package com.youngyeah.code.payAndPrintSingleton;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

/**
 * Created by 李凌耀 on 2017/9/22.
 */
public class linkClient {
    Socket client = null;
    DataOutputStream outputStream = null;
    DataInputStream inputStream = null;
    private static linkClient ourInstance = new linkClient();

    public static linkClient getInstance() {
        return ourInstance;
    }

    private linkClient() {
        try {
            client = new Socket("123.206.43.66",12345);
            outputStream = new DataOutputStream(client.getOutputStream());
            inputStream = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //state:状态码：1：正常，2：未支付，3：不存在该验证码
    public void getFile(String verify)
    {
        try {
            outputStream.writeUTF("getfiles");
            outputStream.writeUTF(verify);
            int state = inputStream.readInt();
            if(state == 1) {
//                JOptionPane.showMessageDialog(null,"接收文件中","信息窗口",JOptionPane.INFORMATION_MESSAGE);
                int i = inputStream.readInt();
                for(int j=0;j<i;j++){
                    //文件名
                    String name = inputStream.readUTF();
                    File file = new File("D:\\" + name);
                    FileOutputStream fout = new FileOutputStream(file);
                    //打印信息 双面\001页数\001份数
                    String info = inputStream.readUTF();
                    String[] split = info.split("\001");
                    boolean isdouble = split[0].equals("1") ? true : false;
                    int pages = Integer.parseInt(split[1]);
                    int copies = Integer.parseInt(split[2]);
                    //设置打印信息
                    PrintSetting printSettingInstance = Factory.getInstance().getPrintSettingInstance();
                    printSettingInstance.setFile(file, pages);
                    printSettingInstance.setDoublepages(isdouble);
                    printSettingInstance.setCopys(copies);
                    printSettingInstance.setAll(true);
                    //接收文件
                    byte[] inputByte = new byte[1024];
                    System.out.println("开始接收数据...");
                    int length = 0;
                    while (true) {
                        if (inputStream != null) {
                            length = inputStream.read(inputByte, 0, inputByte.length);
                        }
                        if (length == -1) {
                            break;
                        }
                        System.out.println(length);
                        fout.write(inputByte, 0, length);
                        fout.flush();
                    }
                    System.out.println("完成接收");
                    fout.close();
                    printSettingInstance.print();
                }
            }
            else if(state == 2)
            {
                JOptionPane.showMessageDialog(null,"未支付","信息窗口",JOptionPane.INFORMATION_MESSAGE);
            }
            else if(state == 3)
            {
                JOptionPane.showMessageDialog(null,"验证码错误","信息窗口",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        linkClient instance = linkClient.getInstance();
        instance.getFile("159367");
    }
}

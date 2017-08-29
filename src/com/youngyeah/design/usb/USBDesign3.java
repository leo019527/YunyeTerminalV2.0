package com.youngyeah.design.usb;

import com.alipay.demo.trade.Main;
import com.youngyeah.code.payAndPrintSingleton.*;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 李凌耀 on 2017/8/29.
 */
public class USBDesign3 extends JFrame {
    private String ordername;
    public USBDesign3() throws HeadlessException {
        String pay = PayInfo.pay();
        this.ordername = pay;
        this.getContentPane().setLayout(null);
        //<editor-fold desc="插入背景图">
        //插入背景图
        ImageIcon indexBG = new ImageIcon("pic/usb/usb_bg4.jpg");//背景图index_bg.jpg
        indexBG.setImage(indexBG.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,Image.SCALE_DEFAULT));
        JLabel label = new JLabel();
        label.setIcon(indexBG);
        label.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        this.add(label);
        //</editor-fold>

        ImageIcon qr = new ImageIcon("D:\\out.png");
        qr.setImage(qr.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2),Image.SCALE_DEFAULT));
        JLabel qrlable = new JLabel();
        qrlable.setIcon(qr);
        qrlable.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.61),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.3),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.2));
        label.add(qrlable);

        //<editor-fold desc="设置窗口大小并全屏显示">
        //设置窗口基本大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height));
        //以下两句完成了全屏显示
        this.setUndecorated(true);
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        this.setVisible(true);
        //</editor-fold>
        //</editor-fold>
        confirm c = new confirm(ordername,this);
        c.start();
    }
}

class confirm extends Thread
{
    private String ordername;
    private JFrame jFrame;

    public confirm(String ordername, JFrame jFrame) {

        this.ordername = ordername;
        this.jFrame = jFrame;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i=0;
        while(!Main.confirm(ordername))
        {
            System.out.println("第"+i+"次查询");
            i++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i >= 59)
            {
                System.out.println("支付查询失败");
                jFrame.dispose();
                return;
            }
        }
        Factory.getInstance().getPrintSettingInstance().print();
        jFrame.dispose();
    }
}

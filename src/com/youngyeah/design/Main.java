package com.youngyeah.design;

import com.youngyeah.code.createcomponents.Create;
import com.youngyeah.code.usb.Res;
import com.youngyeah.code.usb.USBMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by 李凌耀 on 2017/8/6.
 */
public class Main extends JFrame {
    public static final int TERMINALID=1;

    public Main() throws HeadlessException {
        this.getContentPane().setLayout(null);

        //插入背景图
        ImageIcon indexBG = new ImageIcon("pic/main/main.png");//背景图index_bg.jpg
        indexBG.setImage(indexBG.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,Image.SCALE_DEFAULT));
        JLabel label = new JLabel();
        label.setIcon(indexBG);
        label.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        this.add(label);

        //USB打印
        JButton USBPrint = Create.CreateButton("pic/main/main_usbButtom.png",
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.633),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.286),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.269),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.101));
        USBPrint.addActionListener(new USBActionListener(label));
        label.add(USBPrint);

        //线上打印
        JButton OnlinePrint = Create.CreateButton("pic/main/main_onlineButtom.png",
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.633),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.471),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.269),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.101));
        OnlinePrint.addActionListener(null);
        label.add(OnlinePrint);

        //剩余纸张
        JLabel remaining = new JLabel();
        Font font = new Font("宋体",Font.PLAIN,20);
        remaining.setFont(font);
        remaining.setForeground(Color.white);
        remaining.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.031),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.911),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.205),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.068));
        remaining.setText("剩余纸张：500 张");
        label.add(remaining);

        //联系我们
        JButton ContectUS = Create.CreateButton("pic/main/main_contactus.png",
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.850),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.911),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.100),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.068));
        ContectUS.addActionListener(new contactusActionListener());
        label.add(ContectUS);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height));
        this.setUndecorated(true);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}

//<editor-fold desc="联系我们的部分代码">
class contactusActionListener implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
        //TODO:插入联系我们代码
        JFrame main = new JFrame();

        //<editor-fold desc="插入背景图">
        //插入背景图
        ImageIcon indexBG = new ImageIcon("pic/main/contactus.png");//背景图index_bg.jpg
        indexBG.setImage(indexBG.getImage().getScaledInstance((int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.566),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.531),Image.SCALE_DEFAULT));
        JLabel label = new JLabel();
        label.setIcon(indexBG);
        label.setBounds(-1, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        main.add(label);
        //</editor-fold>

        JButton usb_choose_exitButton = Create.CreateButton("pic/main/main_back.png", (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.44),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.007),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.100),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.051));
        usb_choose_exitButton.addActionListener(new ExitListener(main));
        label.add(usb_choose_exitButton);
        //</editor-fold>


        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.566),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.531));
        main.setLocation((int) (Toolkit.getDefaultToolkit().getScreenSize().width*0.22),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.23));
        //以下两句完成了全屏显示
        main.setAlwaysOnTop(true);
        main.setUndecorated(true);
        main.setVisible(true);
    }
}

class ExitListener implements ActionListener
{
    JFrame jFrame;

    public ExitListener(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jFrame.dispose();
    }
}
//</editor-fold>

//<editor-fold desc="USB打印部分">
class USBActionListener implements ActionListener
{
    JLabel jLabel2;

    public USBActionListener(JLabel jLabel) {
        this.jLabel2 = jLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //<editor-fold desc="显示查询图片">
        JLabel jLabel = new JLabel();
        ImageIcon indexBG = new ImageIcon("pic/main/main_usb.png");//背景图index_bg.jpg
        indexBG.setImage(indexBG.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4), Image.SCALE_DEFAULT));
        jLabel.setIcon(indexBG);
        jLabel.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4));
        jLabel.addMouseListener(new removeAfromBMouseListener(jLabel,jLabel2));
        jLabel2.add(jLabel);
        jLabel2.repaint();
        jLabel2.setVisible(true);
        //</editor-fold>
        System.out.println("显示查询图片");

        //<editor-fold desc="创建usb错误图片">
        JLabel jLabe3 = new JLabel();
        ImageIcon imageIcon = new ImageIcon("pic/main/main_usbError.png");//背景图index_bg.jpg
        imageIcon.setImage(imageIcon.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4), Image.SCALE_DEFAULT));
        jLabe3.setIcon(imageIcon);
        jLabe3.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.4));
        //</editor-fold>



        //<editor-fold desc="创建USB处理类">
        Res res = new Res();
        USBMain usbMain = new USBMain(res,jLabel2,jLabel,jLabe3);
        usbMain.start();
        //</editor-fold>
    }
}

class removeAfromB extends Thread
{
    JLabel A;
    JLabel B;

    public removeAfromB(JLabel a, JLabel b) {
        A = a;
        B = b;
    }

    @Override
    public void run() {
        B.remove(A);
        B.repaint();
        B.setVisible(true);
    }
}

class removeAfromBMouseListener implements MouseListener
{
    JLabel A;
    JLabel B;

    public removeAfromBMouseListener(JLabel a, JLabel b) {
        A = a;
        B = b;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new removeAfromB(A,B).run();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
//</editor-fold>
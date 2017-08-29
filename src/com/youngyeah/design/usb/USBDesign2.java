package com.youngyeah.design.usb;

import com.youngyeah.code.payAndPrintSingleton.Factory;
import com.youngyeah.code.payAndPrintSingleton.PrintSetting;
import com.youngyeah.code.staticveriable.StaticVeriables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by 李凌耀 on 2017/8/28.
 */
public class USBDesign2 extends JFrame {
    public USBDesign2() throws HeadlessException {
        //插入背景图
        ImageIcon usb_bac = new ImageIcon("pic/usb/usb_bg3.jpg");
        usb_bac.setImage(usb_bac.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_DEFAULT));
        JLabel background_lable = new JLabel();
        background_lable.setIcon(usb_bac);
        background_lable.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        this.add(background_lable);

        //<editor-fold desc="返回按钮">
        ImageIcon backButtonImg = new ImageIcon("pic/usb/usb_bac.jpg");
        backButtonImg.setImage(backButtonImg.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.092),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.044), Image.SCALE_DEFAULT));
        JButton backButton = new JButton();
        backButton.setIcon(backButtonImg);
        backButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.888),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.025),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.092),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.044));
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(new backButtonActionListener(this));
        background_lable.add(backButton);
        //</editor-fold>

        //<editor-fold desc="全文打印选择">
        ImageIcon Radio = new ImageIcon("pic/usb/radio.png");
        Radio.setImage(Radio.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.022),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.022), Image.SCALE_DEFAULT));
        ImageIcon Radio2 = new ImageIcon("pic/usb/radio2.png");
        Radio2.setImage(Radio2.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.022),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.022), Image.SCALE_DEFAULT));
        JButton all = new JButton(Radio2);
        JButton part = new JButton(Radio);
        all.setBorderPainted(false);
        all.setContentAreaFilled(false);
        part.setBorderPainted(false);
        part.setContentAreaFilled(false);
        all.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.505),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.25),Radio.getIconWidth(),Radio.getIconHeight());
        part.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.505),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.32),Radio.getIconWidth(),Radio.getIconHeight());
        all.addActionListener(new RadioButtonActionListener(Radio,Radio2,part,all,true));
        part.addActionListener(new RadioButtonActionListener(Radio,Radio2,all,part,false));
        background_lable.add(all);
        background_lable.add(part);
        //</editor-fold>

        //<editor-fold desc="三个输入框">
        JTextField scope = new JTextField(" ");//scop:打印范围
        JTextField copies = new JTextField(" ");//copies:打印份数
        scope.setFont(new Font("Times New Roman", Font.BOLD, 30) );
        copies.setFont(new Font("Times New Roman", Font.BOLD, 30) );
        scope.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.621),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.33),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.1127),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.04385));
        copies.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.621),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.411),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.1127),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.04385));
        scope.addMouseListener(new TextMouseListener(1));
        copies.addMouseListener(new TextMouseListener(2));
        copies.setText(" 1");
        background_lable.add(scope);
        background_lable.add(copies);
        StaticVeriables.getInstance().setUSBDesign2Choosen(1);

        //<editor-fold desc="单双面打印按钮">
        Factory.getInstance().getPrintSettingInstance().setDoublepages(false);
        ImageIcon Single1 = new ImageIcon("pic/usb/onepage1.png");
        ImageIcon Single2 = new ImageIcon("pic/usb/onepage2.png");
        ImageIcon Double1 = new ImageIcon("pic/usb/doublepage1.png");
        ImageIcon Double2 = new ImageIcon("pic/usb/doublepage2.png");
        Single1.setImage(Single1.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0895),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0403), Image.SCALE_DEFAULT));
        Single2.setImage(Single2.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0895),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0403), Image.SCALE_DEFAULT));
        Double1.setImage(Double1.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0895),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0403), Image.SCALE_DEFAULT));
        Double2.setImage(Double2.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0895),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0403), Image.SCALE_DEFAULT));
        JButton SingleButton = new JButton();
        JButton DoubleButton = new JButton();
        SingleButton.setIcon(Single2);
        DoubleButton.setIcon(Double1);
        SingleButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.5226),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.6294),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0895),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.0403));
        DoubleButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.6542),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.6294),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0895),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.0403));
        SingleButton.setBorderPainted(false);
        DoubleButton.setBorderPainted(false);
        SingleButton.setContentAreaFilled(false);
        DoubleButton.setContentAreaFilled(false);
        SingleButton.addActionListener(new SingleOrDouble1(SingleButton,DoubleButton,Single1,Single2,Double1,Double2));
        DoubleButton.addActionListener(new SingleOrDouble2(SingleButton,DoubleButton,Single1,Single2,Double1,Double2));
        background_lable.add(SingleButton);
        background_lable.add(DoubleButton);
        //</editor-fold>

        //<editor-fold desc="数字键盘">
        for (int i=1;i<13;i++)
        {
            ImageIcon num = new ImageIcon("pic/usb/num" + i + ".png");
            num.setImage(num.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.043),
                    (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.043), Image.SCALE_DEFAULT));
            numJButton numJButton = new numJButton(i);
            numJButton.setIcon(num);
            numJButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.785)+(i-1)%3*(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0594),
                    (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.249)+(i-1)/3*(int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.076),
                    (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.043),
                    (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.043));
            numJButton.setBorderPainted(false);
            numJButton.setContentAreaFilled(false);
            numJButton.addActionListener(new numJButtonActionListener(i,scope,copies));
            background_lable.add(numJButton);
        }
        //</editor-fold>

        ImageIcon GoPay = new ImageIcon("pic/usb/gopaying.png");
        GoPay.setImage(GoPay.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.11785),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.0693), Image.SCALE_DEFAULT));
        JButton GoPayButton = new JButton();
        GoPayButton.setIcon(GoPay);
        GoPayButton.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.5725),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.7192),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.11785),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.0693));
        GoPayButton.setBorderPainted(false);
        GoPayButton.setContentAreaFilled(false);
        GoPayButton.addActionListener(new GoPayButtonActionListener(scope,copies,this));
        background_lable.add(GoPayButton);

        //<editor-fold desc="设置窗口大小并全屏显示">
        //设置窗口基本大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height));
        //以下两句完成了全屏显示
        this.setUndecorated(true);
        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        this.setVisible(true);
        //</editor-fold>


    }

    public static void main(String[] args) {
        new USBDesign2();
    }
}

class GoPayButtonActionListener implements ActionListener
{
    JTextField scope;
    JTextField copies;
    JFrame Design2;

    public GoPayButtonActionListener(JTextField scope, JTextField copies,JFrame Design2) {
        this.scope = scope;
        this.copies = copies;
        this.Design2 = Design2;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        PrintSetting printSettingInstance = Factory.getInstance().getPrintSettingInstance();
        if(printSettingInstance.isAll())
        {
            printSettingInstance.setCopys(Integer.parseInt(copies.getText().replace(" ","")));
        }
        else
        {
            String[] split = scope.getText().replace(" ","").split("-",-1);
            printSettingInstance.setStart(Integer.parseInt(split[0]));
            printSettingInstance.setEnd(Integer.parseInt(split[1]));
        }
        Design2.dispose();
    }
}

class RadioButtonActionListener implements ActionListener
{
    ImageIcon radio;
    ImageIcon radio2;
    JButton button1;
    JButton button2;
    boolean all;

    public RadioButtonActionListener(ImageIcon radio, ImageIcon radio2, JButton button1, JButton button2,boolean all) {
        this.radio = radio;
        this.radio2 = radio2;
        this.button1 = button1;
        this.button2 = button2;
        this.all = all;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        button1.setIcon(radio);
        button2.setIcon(radio2);
        if(all) System.out.println("全文打印");
        else System.out.println("分页打印");
        Factory.getInstance().getPrintSettingInstance().setAll(all);
    }
}

class TextMouseListener implements MouseListener
{
    int num;

    @Override
    public void mouseClicked(MouseEvent e) {
        StaticVeriables.getInstance().setUSBDesign2Choosen(num);
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

    public TextMouseListener(int num) {
        this.num = num;
    }
}

class SingleOrDouble1 implements ActionListener
{
    JButton Button1;
    JButton Button2;
    ImageIcon single1;
    ImageIcon single2;
    ImageIcon double1;
    ImageIcon double2;
    @Override
    public void actionPerformed(ActionEvent e) {
        PrintSetting instance = Factory.getInstance().getPrintSettingInstance();
        if(instance.isDoublepages())
        {
            Button1.setIcon(single2);
            Button2.setIcon(double1);
        }
        instance.setDoublepages(false);
        System.out.println("设置单面打印");
    }

    public SingleOrDouble1(JButton button1, JButton button2, ImageIcon single1, ImageIcon single2, ImageIcon double1, ImageIcon double2) {
        Button1 = button1;
        Button2 = button2;
        this.single1 = single1;
        this.single2 = single2;
        this.double1 = double1;
        this.double2 = double2;
    }
}

class SingleOrDouble2 implements ActionListener
{
    JButton Button1;
    JButton Button2;
    ImageIcon single1;
    ImageIcon single2;
    ImageIcon double1;
    ImageIcon double2;
    @Override
    public void actionPerformed(ActionEvent e) {
        PrintSetting instance = Factory.getInstance().getPrintSettingInstance();
        if(!instance.isDoublepages())
        {
            Button1.setIcon(single1);
            Button2.setIcon(double2);
        }
        instance.setDoublepages(true);
        System.out.println("设置双面打印");
    }

    public SingleOrDouble2(JButton button1, JButton button2, ImageIcon single1, ImageIcon single2, ImageIcon double1, ImageIcon double2) {
        Button1 = button1;
        Button2 = button2;
        this.single1 = single1;
        this.single2 = single2;
        this.double1 = double1;
        this.double2 = double2;
    }
}

class numJButton extends JButton
{
    private int num;

    public numJButton(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}

class numJButtonActionListener implements ActionListener
{
    int num;
    String s="";
    JTextField jTextFields[];

    public numJButtonActionListener(int num, JTextField a,JTextField b) {
        this.num = num;
        if(this.num == 11)
        {
            this.num = 0;
            s += 0;
        }
        else if(this.num == 10)
        {
            s += "-";
        }
        else
        {
            s += num;
        }
        this.jTextFields = new JTextField[3];
        jTextFields[0] = a;
        jTextFields[1] = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("按钮" + num + "被按下");
        StaticVeriables instance = StaticVeriables.getInstance();
        int textFields = instance.getUSBDesign2Choosen() - 1;
        String old = jTextFields[textFields].getText();
        System.out.println(textFields + ":原有字符串" + old);
        String news;
        if (num == 12) {
            news = old.substring(0, old.length() - 2);
        } else {
            news = old + " " + s;
        }
        System.out.println(textFields + ":现有字符串" + news);
        jTextFields[textFields].setText(news);
        if(Factory.getInstance().getPrintSettingInstance().isAll())
        {
            jTextFields[0].setText("");
        }
    }
}
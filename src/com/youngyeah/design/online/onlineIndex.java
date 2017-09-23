package com.youngyeah.design.online;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.youngyeah.code.payAndPrintSingleton.*;

/**
 * Created by 李凌耀 on 2017/3/7.
 */
public class onlineIndex extends JFrame{
    ImageIcon onlineBG;//背景图online_bg.jpg
    JTextField onlineInput;//界面里那个最大的白框online_input.png
    JButton onlineBack;//右上角的返回按钮online_back.png
    JButton onlinePrint;//打印按钮online_print.png
    JButton[] onlineNum;//数字按钮online_0-9.jpg
    JButton onlineBackSpace;//数字键盘中的删除按钮online_backspace.png
    public onlineIndex() throws HeadlessException {
        this.getContentPane().setLayout(null);

        //插入背景图
        onlineBG = new ImageIcon("pic/online/online_bg.png");
        onlineBG.setImage(onlineBG.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height,Image.SCALE_DEFAULT));
        JLabel label = new JLabel(onlineBG);// 把背景图片显示在一个标签里面
        label.setBounds(0, 0, onlineBG.getIconWidth(),onlineBG.getIconHeight());
        this.add(label);
        this.setLayout(null);

        //插入右上角返回按钮
        ImageIcon imageIcon1 = new ImageIcon("pic/online/online_back.png");
        imageIcon1.setImage(imageIcon1.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.15),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.075), Image.SCALE_DEFAULT));
        onlineBack = new JButton(imageIcon1);
        onlineBack.setBounds((int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.75),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.13),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.15),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.075));
        onlineBack.addActionListener(new onlineBackActionListener(this));
        onlineBack.setBorderPainted(false);
        onlineBack.setContentAreaFilled(false);
        label.add(onlineBack);

        //插入输入框
        Font font = new Font("宋体",Font.PLAIN,130);
        ImageIcon imageIcon3 = new ImageIcon("pic/online/online_input.png");
        imageIcon3.setImage(imageIcon3.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.1), Image.SCALE_DEFAULT));
        onlineInput = new JTextField();
        onlineInput.setFont(font);
        onlineInput.setHorizontalAlignment(JTextField.CENTER);
        onlineInput.setBounds((int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.1),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.5-imageIcon3.getIconHeight()*0.5),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.4),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.1));
        label.add(onlineInput);

        //插入打印按钮
        ImageIcon imageIcon2 = new ImageIcon("pic/online/online_print.png");
        imageIcon2.setImage(imageIcon2.getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.08), Image.SCALE_DEFAULT));
        onlinePrint = new JButton(imageIcon2);
        onlinePrint.setBounds((int)(Toolkit.getDefaultToolkit().getScreenSize().width*0.2),
                (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.7),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.2),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.08));
        onlinePrint.setBorderPainted(false);
        onlinePrint.setContentAreaFilled(false);
        onlinePrint.addActionListener(new onlinePrintActionListener(onlineInput,this));
        label.add(onlinePrint);

        //插入数字按钮
        ImageIcon[] imageIconNum = new ImageIcon[10];
        onlineNum = new JButton[10];
        for(int i=0;i<10;i++)
        {
            imageIconNum[i] = new ImageIcon("pic/online/online_"+i+".png");
            imageIconNum[i].setImage(imageIconNum[i].getImage().getScaledInstance((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.08),
                    (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.08), Image.SCALE_DEFAULT));
            onlineNum[i] = new JButton(String.valueOf(i),imageIconNum[i]);
            if(i == 0) {
                onlineNum[i].setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.1 + imageIcon3.getIconWidth() + 2*imageIconNum[i].getIconWidth()),
                        (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.45 - imageIcon3.getIconHeight() * 0.5) + 3 * imageIconNum[i].getIconHeight(),
                        (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.08),
                        (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.08));
            }
            else
            {
                onlineNum[i].setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.1 + imageIcon3.getIconWidth() + imageIconNum[i].getIconWidth()) + ((i-1) % 3) * imageIconNum[i].getIconWidth(),
                        (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.45 - imageIcon3.getIconHeight() * 0.5) + ((i-1) / 3) * imageIconNum[i].getIconHeight(),
                        imageIconNum[i].getIconWidth(), imageIconNum[i].getIconHeight());
            }
            onlineNum[i].setBorderPainted(false);
            onlineNum[i].setContentAreaFilled(false);
            onlineNum[i].addActionListener(new onlineNumActionListener(onlineInput));
            label.add(onlineNum[i]);
        }

        //插入数字键盘中的删除按钮
        ImageIcon imageIcon4 = new ImageIcon("pic/online_backspace.png");
        onlineBackSpace = new JButton(imageIcon4);
        onlineBackSpace.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.2 + imageIcon3.getIconWidth() + (int)(imageIconNum[0].getIconWidth()*2.2)),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.5 - imageIcon3.getIconHeight() * 0.5) + (int)(imageIconNum[0].getIconHeight()*3.3),
                imageIcon4.getIconWidth(),imageIcon4.getIconHeight());
        onlineBackSpace.setBorderPainted(false);
        onlineBackSpace.setContentAreaFilled(false);
        onlineBackSpace.addActionListener(new onlineBackspaceActionListener(this.onlineInput));
        label.add(onlineBackSpace);

        //设置窗口基本大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(onlineBG.getIconWidth(),onlineBG.getIconHeight());
        //以下两句完成了全屏显示
        this.setUndecorated(true);
//        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        this.setVisible(true);
    }

    //测试函数
    public static void main(String[] args) {
        new onlineIndex();
    }

}

//以下为各种按钮的监听函数

//右上角返回按钮的监听函数
class onlineBackActionListener implements ActionListener
{
    JFrame jFrame;

    public onlineBackActionListener(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jFrame.dispose();
    }
}

//数字键的监听函数
class onlineNumActionListener implements ActionListener
{
    JTextField jTextField;

    public onlineNumActionListener(JTextField jTextField) {
        this.jTextField = jTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        switch (jButton.getText())
        {
            case "1":
                setText(1);
                break;
            case "2":
                setText(2);
                break;
            case "3":
                setText(3);
                break;
            case "4":
                setText(4);
                break;
            case "5":
                setText(5);
                break;
            case "6":
                setText(6);
                break;
            case "7":
                setText(7);
                break;
            case "8":
                setText(8);
                break;
            case "9":
                setText(9);
                break;
            case "0":
                setText(0);
                break;
            default:
                break;
        }
    }

    public void setText(int num)
    {
        String str = this.jTextField.getText();
        if(str.length()>=6)
        {
            return;
        }
        else
        {
            this.jTextField.setText(str + num);
        }
    }
}

//数字键盘中的删除按钮的监听函数
class onlineBackspaceActionListener implements ActionListener
{
    JTextField jTextField;

    public onlineBackspaceActionListener(JTextField jTextField) {
        this.jTextField = jTextField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = this.jTextField.getText();
        if(str.length()<=0)
        {
            return;
        }
        str = str.substring(0,str.length()-1);
        this.jTextField.setText(str);
    }
}

//插入打印按钮监听函数
class onlinePrintActionListener implements ActionListener
{
    JTextField jTextField;
    JFrame jFrame;

    public onlinePrintActionListener(JTextField jTextField,JFrame jFrame) {
        this.jTextField = jTextField;
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int num = Integer.parseInt(jTextField.getText());
        if(num<=99999)
        {
            JOptionPane.showConfirmDialog (null, "输入六位验证码", "友情提示", JOptionPane.OK_OPTION);
        }
        else
        {
            linkClient linkClient = Factory.getInstance().getlinkClient();
            linkClient.getFile(jTextField.getText());
        }
        this.jFrame.dispose();
    }
}


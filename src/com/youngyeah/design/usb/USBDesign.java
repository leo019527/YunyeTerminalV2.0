package com.youngyeah.design.usb;

import com.youngyeah.code.createcomponents.Create;
import com.youngyeah.code.others.GetFiles;
import com.youngyeah.code.payAndPrintSingleton.Factory;
import com.youngyeah.code.staticveriable.StaticVeriables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by 李凌耀 on 2017/8/16.
 */
public class USBDesign extends JFrame {
    File USB;

    public USBDesign(File USB) throws HeadlessException {
        this.USB = USB;
        ArrayList<File> files = GetFiles.getFiles(USB);
        //<editor-fold desc="插入背景图">
        //插入背景图
        ImageIcon usb_bac = new ImageIcon("pic/usb/usb_bg1.jpg");
        usb_bac.setImage(usb_bac.getImage().getScaledInstance(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height, Image.SCALE_DEFAULT));
        JLabel background_lable = new JLabel();
        background_lable.setIcon(usb_bac);
        background_lable.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        this.add(background_lable);
        //</editor-fold>

        //返回按钮
        JButton Back = Create.CreateButton("pic/usb/usb_bac.jpg", (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.888),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.025),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.092),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.044));
        Back.addActionListener(new backButtonActionListener(this));
        background_lable.add(Back);

        //加入文件选择界面
        JPanel fileListed = new JPanel();
        fileListed.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.165),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.137),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.425),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.749));
        fileListed.setOpaque(false);
        fileListed.setLayout(new GridLayout(0,6));
        for(File a:files)
        {
            FileButton fileButton = new FileButton(a);
            fileButton.setIcon(USBDesign.getBigIcon(fileButton.getFile()));
            fileListed.add(fileButton);
            JLabel jLabel = new JLabel(fileButton.getFile().getName());
            jLabel.setFont(new   java.awt.Font("Dialog",   1,   15));
            jLabel.setForeground(Color.white);
            jLabel.setOpaque(false);
            fileListed.add(jLabel);
        }
        background_lable.add(fileListed);

        //直接打印
        JButton directPrint = Create.CreateButton("pic/usb/usb_choose_print.jpg", (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.764),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.334),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.151),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.072));
        directPrint.addActionListener(new DirectPrintActionListener(this));
        background_lable.add(directPrint);


        //设置窗口基本大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().width),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().height));
        //以下两句完成了全屏显示
        this.setUndecorated(true);
//        this.getGraphicsConfiguration().getDevice().setFullScreenWindow(this);
        this.setVisible(true);
    }

    private static ImageIcon getBigIcon( File f )
    {
        if ( f != null && f.exists() )
        {
            try {
                sun.awt.shell.ShellFolder sf = sun.awt.shell.ShellFolder.getShellFolder( f );
                ImageIcon imageIcon1 = new ImageIcon(sf.getIcon(true));
                Image bigImage = imageIcon1.getImage().getScaledInstance(imageIcon1.getIconWidth()*2,imageIcon1.getIconHeight()*2,Image.SCALE_DEFAULT);
                ImageIcon imageIcon = new ImageIcon(bigImage);
                return imageIcon;
            } catch ( FileNotFoundException e ) {
                e.printStackTrace();
            }
        }
        return(null);
    }

    public static void main(String[] args) {
        new USBDesign(new File("I://"));
    }
}


//返回按钮
class backButtonActionListener implements ActionListener {
    JFrame ToClose = null;

    public backButtonActionListener(JFrame toClose) {
        ToClose = toClose;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ToClose.dispose();
        //TODO：插入u盘退出方法
    }
}

//直接打印
class DirectPrintActionListener implements ActionListener
{
    JFrame jFrame;

    public DirectPrintActionListener(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File choosenFile = StaticVeriables.getInstance().getChoosenFile();
        Factory.getInstance().getPrintSettingInstance().setFile(choosenFile);
        //TODO:打开下一个界面
        this.jFrame.dispose();
    }
}

class FileButton extends JButton
{
    File file;

    public FileButton(File file) {
        this.file = file;
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        this.addActionListener(new action(file));
    }

    public File getFile() {
        return file;
    }

    class action implements ActionListener
    {
        File file;

        public action(File file) {
            this.file = file;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Factory.getInstance().getPrintSettingInstance().setFile(this.file);
        }
    }
}
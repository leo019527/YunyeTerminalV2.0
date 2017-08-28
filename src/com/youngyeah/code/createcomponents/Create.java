package com.youngyeah.code.createcomponents;

import javax.swing.*;
import java.awt.*;

/**
 * Created by 李凌耀 on 2017/8/8.
 */
public class Create {
    //创建按钮
    public static JButton CreateButton(String pic,int X,int Y,int width,int height){
        ImageIcon imageIcon = new ImageIcon(pic);
        JButton jButton = new JButton();
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width,height,
                Image.SCALE_DEFAULT));
        jButton.setIcon(imageIcon);
        jButton.setBounds(X,Y,width,height);
        jButton.setBorderPainted(false);
        jButton.setContentAreaFilled(false);
        return jButton;
    }
}

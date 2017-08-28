package com.youngyeah.code.usb;

import com.youngyeah.design.usb.USBDesign;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * Created by 李凌耀 on 2017/4/7.
 */

public class USBMain extends Thread{

    public static final int _TIME_ = 5;//USB插入时间（单位：秒）

    private File USB = null;
    private Res resFileBy = null;
    private JLabel base = null;
    private JLabel remove = null;
    private JLabel insert = null;

    public USBMain(Res resFileBy,JLabel base,JLabel remove,JLabel insert) {
        this.resFileBy = resFileBy;
        this.base = base;
        this.remove = remove;
        this.insert = insert;
    }

    private File SearchUSB() throws InterruptedException
    {
        int i = 0;
        while (true)
        {
            i++;
            System.out.println("查找中");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean is = resFileBy.searchFile();
            if(is)
            {
                break;
            }
            if(i>_TIME_)
            {
                break;
            }
        }
        USB = resFileBy.getUSB();
        return USB;
    }

    @Override
    public void run() {
        try {
            this.SearchUSB();
            if(USB == null)
            {
                new ChangeUI(this.base,this.remove,this.insert).run();
            }
            else
            {
                USBDesign usbDesign = new USBDesign(this.USB);
                System.out.println("打开U盘打印界面");
                new remove(this.base,this.remove).run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ChangeUI extends Thread
{
    private JLabel base = null;
    private JLabel remove = null;
    private JLabel insert = null;

    public ChangeUI(JLabel base, JLabel remove, JLabel insert) {
        this.base = base;
        this.remove = remove;
        this.insert = insert;
        insert.addMouseListener(new USBError(this.base,this.insert));
    }

    @Override
    public void run() {
        base.remove(remove);
        base.add(insert);
        base.repaint();
        base.setVisible(true);
    }
}

class USBError implements MouseListener
{
    private JLabel base = null;
    private JLabel remove = null;

    public USBError(JLabel base, JLabel remove) {
        this.base = base;
        this.remove = remove;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        new remove(base,remove).run();
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

class remove extends Thread
{
    JLabel base = null;
    JLabel remove = null;

    public remove(JLabel base, JLabel remove) {
        this.base = base;
        this.remove = remove;
    }

    @Override
    public void run() {
        base.remove(remove);
        base.repaint();
        base.setVisible(true);
    }
}
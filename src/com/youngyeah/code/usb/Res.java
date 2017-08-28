package com.youngyeah.code.usb;

import java.io.File;

/**
 * 资源
 *  参数解释：
 *  dirs:现阶段盘符
 *  pre:之前的盘符
 *  USB:USB
 * Created by 李凌耀 on 2017/4/7.
 */
public class Res {
    private int count = 0;
    private boolean flag = false;
    private File[] dirs;
    private File[] pre;
    private File USB = null;

    public File getUSB() {
        return USB;
    }



    public Res() {
        pre = new File[2];
        pre[0] = new File("C:\\");
        pre[1] = new File("D:\\");
//        pre[2] = new File("E:\\");
        count = pre.length;
        System.out.println("获取原有的盘符数量："+count);
    }



    public synchronized boolean searchFile()
    {
        if(flag)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        dirs = File.listRoots();
        if(dirs.length > count)
        {
            for(int i=0;i<pre.length;i++)
            {
                if(!dirs[i].getPath().equals(pre[i].getPath()))
                {
                    USB = dirs[i];
                }
            }
            if(USB == null)
            {
                USB = dirs[pre.length];
            }
            flag=true;
            System.out.println("获得USB： " + USB.getPath());
            notify();
        }


        return flag;
    }


}

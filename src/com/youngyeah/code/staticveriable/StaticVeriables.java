package com.youngyeah.code.staticveriable;

import javax.swing.*;
import java.io.File;

/**
 * Created by 李凌耀 on 2017/8/8.
 */
public class StaticVeriables {
    private JLabel remainingPaper;
    private int remainingPapers;
    private File choosenFile;

    public File getChoosenFile() {
        return choosenFile;
    }

    public void setChoosenFile(File choosenFile) {
        this.choosenFile = choosenFile;
    }

    public void decreasePaper(int i)
    {
        this.remainingPapers -= i;
        remainingPaper.setText("剩余纸张："+remainingPapers+" 张");
        //TODO:纸张过少时提醒
    }

    public void setRemainingPaper(JLabel remainingPaper) {
        this.remainingPaper = remainingPaper;
    }

    private static StaticVeriables ourInstance = new StaticVeriables();

    public static StaticVeriables getInstance() {
        return ourInstance;
    }

    private StaticVeriables() {
        remainingPapers = 500;
    }
}

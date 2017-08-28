package com.youngyeah.code.others;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by 李凌耀 on 2017/8/16.
 */
public class GetFiles {
    public static ArrayList<File> getFiles(File start)
    {
        ArrayList<File> result = new ArrayList<>();
        File file = start;
        if (file.exists()) {
            LinkedList<File> list = new LinkedList<File>();
            File[] files = file.listFiles();
            for (File file2 : files) {
                if(file2.isHidden())
                    continue;
                if (file2.isDirectory()) {
                    System.out.println("文件夹:" + file2.getAbsolutePath());
                    list.add(file2);
                } else {
                    if (GetFiles.charge(file2)) {
                        result.add(file2);
                    }
                }
            }
        File temp_file;
        while (!list.isEmpty()) {
            temp_file = list.removeFirst();
            files = temp_file.listFiles();
            for (File file2 : files) {
                if(file2.isHidden())
                    continue;
                if (file2.isDirectory()) {
                    list.add(file2);
                } else {
                    if (GetFiles.charge(file2)) {
                        result.add(file2);
                    }
                }
            }
        }
    } else {
        System.out.println("文件不存在!");
    }
        return result;
    }

    private static boolean charge(File file)
    {
        String path = file.getPath();
        String split = path.substring(path.indexOf('.') + 1, path.length());
        split = split.toLowerCase();
        if(split.equals("doc") || split.equals("docx") || split.equals("pdf") || split.equals("ppt") || split.equals("pptx"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}

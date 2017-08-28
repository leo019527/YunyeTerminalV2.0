package com.youngyeah.code.payAndPrintSingleton;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by 李凌耀 on 2017/8/6.
 */
public class PrintSetting {
    private File file;
    private boolean all;//全文打印
    private int pages;//文件页码
    private int start;//开始打印页码
    private int end;//结束打印页码
    private int copys;//打印份数
    private boolean doublepages;//是否双面打印

    public PrintSetting() {
        all = true;
        pages = 0;
        start = 1;
        end = pages;
        copys = 1;
        doublepages = false;
    }

    //<editor-fold desc="get-set Function">
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        String path = file.getPath();
        String split = path.substring(path.indexOf('.') + 1, path.length());
        split = split.toLowerCase();
        try{
            switch (split){
                case "doc":
                    WordExtractor doc;
                    doc = new WordExtractor(new FileInputStream(file.getPath()));
                    pages = doc.getSummaryInformation().getPageCount();
                    break;
                case "docx":
                    XWPFDocument docx;
                    docx= new XWPFDocument(POIXMLDocument.openPackage(file.getPath()));
                    pages = docx.getProperties().getExtendedProperties().getUnderlyingProperties().getPages();
                    break;
                case "pdf":
                    PDDocument pdf = PDDocument.load(file);
                    pages = pdf.getNumberOfPages();
                    break;
                case "ppt":
                    HSLFSlideShow ppt = new HSLFSlideShow(new FileInputStream(file));
                    List<HSLFSlide> page = ppt.getSlides();
                    pages = page.size();
                    break;
                case "pptx":
                    XMLSlideShow xmlSlideShow = new XMLSlideShow(new FileInputStream(file));
                    List<XSLFSlide> page2 = xmlSlideShow.getSlides();
                    pages = page2.size();
                    break;
                default:
                    System.out.println("文件类型错误");
                    break;
            }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public int getPages() {
        return pages;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getCopys() {
        return copys;
    }

    public void setCopys(int copys) {
        this.copys = copys;
    }

    public boolean isDoublepages() {
        return doublepages;
    }

    public void setDoublepages(boolean doublepages) {
        this.doublepages = doublepages;
    }
    //</editor-fold>
}

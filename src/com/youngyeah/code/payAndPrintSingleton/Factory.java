package com.youngyeah.code.payAndPrintSingleton;

/**
 * 单例工厂，用于返回打印参数和支付参数
 * Created by 李凌耀 on 2017/8/6.
 */
public class Factory {
    private static final Factory instance = new Factory();

    private PrintSetting printSettingInstance;

    private linkClient linkClient;

    private Factory() {
        this.printSettingInstance = new PrintSetting();
        this.linkClient = linkClient.getInstance();
    }

    public static Factory getInstance(){
        return instance;
    }

    public PrintSetting getPrintSettingInstance() {
        return printSettingInstance;
    }

    public linkClient getlinkClient(){
        return linkClient;
    }
}

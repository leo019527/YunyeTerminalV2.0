package com.youngyeah.code.payAndPrintSingleton;

import com.alipay.demo.trade.Main;

/**
 * Created by 李凌耀 on 2017/8/6.
 */
public class PayInfo {
    //计算价格
    private static double calculate(){
        Factory instance = Factory.getInstance();
        PrintSetting printSettingInstance = instance.getPrintSettingInstance();
        double page = 0;
        double price = 0.0;
        if(printSettingInstance.isAll())
        {
            page = printSettingInstance.getPages()*printSettingInstance.getCopys();
        }
        else
        {
            page = printSettingInstance.getEnd()-printSettingInstance.getStart();
            page *= printSettingInstance.getCopys();
        }
        return page*0.1;
    }

    //支付
    public static String pay(){
        Main m = new Main();
        String ordername = m.pay(String.valueOf(calculate()));
        return ordername;
    }
}

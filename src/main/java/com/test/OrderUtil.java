
package com.test;

import java.text.SimpleDateFormat;


/**
 * 订单号生成工具
 * 枚举相关信息，查看 https://www.showdoc.cc/web/#/page/495445295769339
 */
public class OrderUtil {

    public static String getUserPoolOrder(String pre) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyMMddHHmmssSSS");
        StringBuffer sb = new StringBuffer(pre);
        return sb.append(dateformat.format(System.currentTimeMillis()))
                .append((int) (Math.random() * 1000)).toString();
    }

    public static String createConsumerNo(String pre) {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyMMddHHmmssSSS");
        StringBuffer sb = new StringBuffer(pre + "_");
        return sb.append(dateformat.format(System.currentTimeMillis()))
                .append((int) (Math.random() * 1000)).toString();
    }

    public static void main(String[] args) {
        System.out.println((int) (Math.random() * 10000));
    }
}

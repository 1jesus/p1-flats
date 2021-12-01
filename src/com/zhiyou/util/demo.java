package com.zhiyou.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.zhiyou.model.contract.Contract;
import com.zhiyou.util.DBUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname demo
 * @Date 2021/9/13 10:17
 */
public class demo {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("new出来的日期 = "+date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        System.out.println("格式化后的日期 = "+format);

        System.out.println(date.getTime());
        System.out.println(new Date((date.getTime())));
    }
}

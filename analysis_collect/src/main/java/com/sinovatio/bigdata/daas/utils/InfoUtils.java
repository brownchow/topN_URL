package com.sinovatio.bigdata.daas.utils;

import cn.hutool.core.util.RandomUtil;

/**
 * 造数工具类
 *
 * @Author: brown
 * @Since: 2021-08-01
 */
public class InfoUtils {

    /**
     * 手机号开头
     */
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");

    /**
     * URL池
     */
    private static String[] urls = {"/a", "/b", "/c", "/d", "/e", "/f", "/g", "/h", "/i", "/g", "/k", "/l"};

    /**
     * 生成11位手机号码  3 + 4 + 4
     *
     * @return string
     */
    public static String getTel() {
        // 左闭右开
        int index = RandomUtil.randomInt(0, telFirst.length);
        String first = telFirst[index];
        String second = String.valueOf(RandomUtil.randomInt(1, 888) + 10000).substring(1);
        String third = String.valueOf(RandomUtil.randomInt(1, 9100) + 10000).substring(1);
        return first + second + third;
    }

    /**
     * 随机生成URL
     *
     * @return string
     */
    public static String getURL() {
        int index = RandomUtil.randomInt(0, telFirst.length);
        return urls[index];
    }

}

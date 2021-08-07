package com.sinovatio.bigdata.daas.kafka;

import com.sinovatio.bigdata.daas.utils.InfoUtils;
import com.sinovatio.bigdata.daas.utils.KafkaProducer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 模拟 nginx 日志生成
 * 该数据用于Flink测试统计nginx实时统计每小时排名前N的URL
 *
 * @Author: brown
 * @Since: 2021-08-02
 */

public class MockNginxLog {

    public static void main(String[] args) throws Exception {
        genKafkaData();
    }

    /**
     * 生成文件数据
     */
    private static void genFileData() {
        String logPath = "/home/brown/nginxlog.txt";
        StringBuffer content = new StringBuffer();
        int times = 10000;
        for (int i = 0; i < times; i++) {
            content.append("127.0.0.1 ")
                    .append(System.currentTimeMillis()).append(" ")
                    .append(InfoUtils.getURL()).append("\n");
        }
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(logPath));
            out.write(content.toString());
            out.close();
            System.out.println("文件创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 每间1s钟，向kafka发送一条随机消息
     */
    private static void genKafkaData() {
        try {
            KafkaProducer producer = KafkaProducer.getInstance();
            String topic = "test-top10";
            while (true) {
                String msg = "127.0.0.1" + " " + System.currentTimeMillis() + " " + InfoUtils.getURL();
                System.out.println(msg);
                producer.sendMessage(topic, msg);
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
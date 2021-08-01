package com.sinovatio.bigdata.daas.utils;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * kafka工具类
 *
 * @author brown
 */
public class KafkaProducer {

    public static Producer<String, String> producer;


    static {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("serializer.class", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "0");
        props.put("retries", "0");
        props.put("linger.ms", "1");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
    }

    public static void main(String[] args) throws Exception {
        KafkaProducer producer = KafkaProducer.getInstance();
        producer.sendMessage("test", "test");
    }

    /**
     * 向kafka发送消息
     *
     * @param topic 主题
     * @param value 值
     * @throws Exception
     */
    public void sendMessage(String topic, String value) throws Exception {
        sendMessage(new ProducerRecord<String, String>(topic, value));
    }

    /**
     * 向kafka发送消息
     *
     * @param topic 主题
     * @param value 值
     * @throws Exception
     */
    public void sendMessage(String topic, String key, String value) throws Exception {
        sendMessage(new ProducerRecord(topic, key, value));
    }

    /**
     * 向kafka发送消息，内部实现
     *
     * @param message
     * @return
     */
    private void sendMessage(ProducerRecord message) throws Exception {
        producer.send(message, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                //log.info("向kafka发送数据返回偏移量: {}" , recordMetadata.offset());
            }
        });
    }

    /**
     * 刷新缓存
     */
    public void flush() {
        producer.flush();
    }

    /**
     * 关闭连接
     */
    public void close() {
        producer.close();
    }

    /**
     * 单例模式确保全局中只有一份该实例
     */
    private static class ProducerKafkaHolder {
        private static KafkaProducer instance = new KafkaProducer();
    }

    /**
     * 延迟加载，避免启动加载
     *
     * @return
     */
    public static KafkaProducer getInstance() {
        return ProducerKafkaHolder.instance;
    }

}
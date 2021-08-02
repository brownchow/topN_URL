package com.sinovatio.bigdata.daas.flink.urltopn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * nginx 生产日志
 * 127.0.0.1       1595492263309               /k
 * 52.30.46.215    2020-07-15T05:48:42+00:00   /u3d/appsflyer/pushdata   127.0.0.1:8080  200                         "http-kit/2.0"
 *
 * @Description:
 * @Author: brown
 * @Since: 2021-08-03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApacheLogEvent {

    private String ip;

    private Long eventTime;

    private String url;

    private String host;

    private String httpStatus;

    private String httpUserAgent;

    public ApacheLogEvent(long parseLong, String url) {
        this.eventTime = parseLong;
        this.url = url;
    }
}
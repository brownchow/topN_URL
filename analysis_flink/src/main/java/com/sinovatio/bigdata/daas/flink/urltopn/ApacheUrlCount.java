package com.sinovatio.bigdata.daas.flink.urltopn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 开窗聚合后结果
 *
 * @Author: brown
 * @Since: 2021-08-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApacheUrlCount {

    private String url;

    private Long windowEnd;

    private Long count;
}
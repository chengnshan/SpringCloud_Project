package com.cxp.springcloudstreamrabbitmqconsumer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-02 22:18
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo {

    private Integer id;
    private String stuName;
    private int age;
}

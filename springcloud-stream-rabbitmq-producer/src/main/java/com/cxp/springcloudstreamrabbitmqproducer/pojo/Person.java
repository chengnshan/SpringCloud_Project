package com.cxp.springcloudstreamrabbitmqproducer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-01 12:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    String name;
    int age;
}

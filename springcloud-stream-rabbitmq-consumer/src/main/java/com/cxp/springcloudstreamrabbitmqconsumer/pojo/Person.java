package com.cxp.springcloudstreamrabbitmqconsumer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-01 14:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Person {
    String name;
    int age;
}
package com.cxp.springcloudstreamrabbitmqproducer.streanconfig;

import org.springframework.cloud.stream.binder.PartitionSelectorStrategy;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-03 11:53
 */
public class MyCustomPartitionSelectorStrategy implements PartitionSelectorStrategy {
    /**
     * 一旦计算了消息密钥，分区选择过程就将目标分区确定为0和之间的值partitionCount - 1。适用于大多数情况的默认计算基于以下公式：
     * key.hashCode() % partitionCount。这可以在绑定上自定义，可以通过设置要根据'key'（通过partitionSelectorExpression属性）
     * 计算的SpEL表达式，也可以通过配置org.springframework.cloud.stream.binder.PartitionSelectorStrategy作为bean 的实现（
     * 通过使用@Bean注释）来定制。与此类似，当应用程序上下文中有多个此类bean时PartitionKeyExtractorStrategy，
     * 可以使用该spring.cloud.stream.bindings.output.producer.partitionSelectorName属性进一步过滤它
     *
     * spring.cloud.stream.bindings.output.producer.partitionKeyExtractorName = customPartitionKeyExtractor
     * @Bean
     * public CustomPartitionKeyExtractorClass customPartitionKeyExtractor（）{
     *     return new CustomPartitionKeyExtractorClass（）;
     * }
     *
     * @param key
     * @param partitionCount
     * @return 自定义根据key,partitionCount来计算指定发送消息到分区
     */
    @Override
    public int selectPartition(Object key, int partitionCount) {
        return 0;
    }
}

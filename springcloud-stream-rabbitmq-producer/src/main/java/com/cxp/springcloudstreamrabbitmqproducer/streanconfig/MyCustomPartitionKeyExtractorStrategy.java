package com.cxp.springcloudstreamrabbitmqproducer.streanconfig;

import org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy;
import org.springframework.messaging.Message;

/**
 * @program: SpringCloud_Project
 * @description:
 * @author: cheng
 * @create: 2019-08-03 12:20
 */
public class MyCustomPartitionKeyExtractorStrategy implements PartitionKeyExtractorStrategy {

    /**
     * 根据发送到分区输出通道的每条消息计算分区键的值partitionKeyExpression。这partitionKeyExpression是一个SpEL表达式，它根据出站消息
     * 进行评估，以提取分区键。
     *
     * 如果SpEL表达式不足以满足您的需要，您可以通过提供实现org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy并将
     * 其配置为bean（通过使用@Bean注释）来计算分区键值。如果org.springframework.cloud.stream.binder.PartitionKeyExtractorStrategy在
     * 应用程序上下文中有多个类型的bean 可用，则可以通过使用partitionKeyExtractorName属性指定其名称来进一步过滤它，如以下示例所示：
     *
     * spring.cloud.stream.bindings.output.producer.partitionKeyExtractorName = customPartitionKeyExtractor
     * @Bean
     * public CustomPartitionKeyExtractorClass customPartitionKeyExtractor（）{
     *     return new CustomPartitionKeyExtractorClass（）;
     * }
     *
     * @param message
     * @return 获取键key
     */
    @Override
    public Object extractKey(Message<?> message) {
        return null;
    }
}

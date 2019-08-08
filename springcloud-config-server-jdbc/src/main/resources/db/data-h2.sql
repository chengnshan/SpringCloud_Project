delete from config_properties ;

INSERT INTO config_properties (id,`key`,`value`,application,profile,label,remark)
VALUES (2, 'eureka.client.serviceUrl.defaultZone', '${EUREKA_SERVICE_URL:http://localhost:8762}/eureka/', 'eureka-client', 'dev', 'v0.0.1', '配置中心地址');
INSERT INTO config_properties (id,`key`,`value`,application,profile,label,remark)
VALUES (3, 'management.endpoint.conditions.enabled', 'true', 'eureka-client', 'dev', 'v0.0.1', '启用终结点');
INSERT INTO config_properties (id,`key`,`value`,application,profile,label,remark)
VALUES (4, 'eureka.instance.prefer-ip-address', 'true', 'eureka-client', 'dev', 'v0.0.1', '使用IP地址注册到注册中心');
INSERT INTO config_properties (id,`key`,`value`,application,profile,label,remark)
VALUES (5, 'spring.application.name', 'eureka-client', 'eureka-client', 'dev', 'v0.0.1', '应用名称');
INSERT INTO config_properties (id,`key`,`value`,application,profile,label,remark)
VALUES (6, 'eureka.instance.instanceId', '${spring.application.name}@${spring.cloud.client.ip-address}@${server.port}', 'eureka-client', 'dev', 'v0.0.1', '在注册中心的实例ID');
INSERT INTO config_properties (id,`key`,`value`,application,profile,label,remark)
VALUES (7, 'management.endpoints.web.exposure.include', '*', 'eureka-client', 'dev', 'v0.0.1', '开放哪些监控端口');
INSERT INTO config_properties (id,`key`,`value`,application,profile,label,remark)
VALUES (8, 'server.port', '8000', 'eureka-client', 'dev', 'v0.0.1', '应用服务端口号');





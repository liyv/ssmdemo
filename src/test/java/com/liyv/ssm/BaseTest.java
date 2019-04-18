package com.liyv.ssm;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置 Spring 和 Junit 整合，junit 启动时加载 spring ioc容器，spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring 配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BaseTest {
}

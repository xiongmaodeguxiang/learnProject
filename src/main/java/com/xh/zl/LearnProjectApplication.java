package com.xh.zl;

import com.xh.zl.config.ServiceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan(value = "com.xh.zl.mapper*")
public class LearnProjectApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(LearnProjectApplication.class,args);
    }
}

package org.zhou.forever.truegrave;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.zhou.forever.truegrave.mapper")
public class TrueGraveApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrueGraveApplication.class, args);
    }

}

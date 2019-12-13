package com.univ.basic.condition.onclass;

import org.junit.Test;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author univ
 * @date 2019/1/28 5:14 PM
 * @description @ConditionalOnClass：当类路径下有指定的类(.class文件)时匹配
 */
@Configuration
public class ConditionalOnClassTest {

    @Test
    public void test1() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ConditionalOnClassTest.class);
        // 这里要求只有一个DBService的实例，可以使用applicationContext.getBean( "mysql", DBService.class)来获取指定的实例
        DBService dbService = applicationContext.getBean(DBService.class);
        System.out.println(dbService.getDBName());
    }

    /**
     * 如果类路径下有MysqlDB.class，则创建MysqlDB实例
     *
     * 经实践，不能和下面的getOracleDB共存，因为此时会有两个DBService的实例，
     * 当然可以使用applicationContext.getBean( "mysql", DBService.class)来获取指定的实例
     * @return
     */
    @Bean("mysql")
    @ConditionalOnClass(MysqlDB.class)
    public DBService getMysqlDB() {
        return new MysqlDB();
    }

    /**
     * 如果类路径下有OracleDB.class，则创建OracleDB实例
     *
     * 经实践，不能和上面的getMysqlDB共存
     * @return
     */
    /*@Bean
    @ConditionalOnClass(OracleDB.class)
    public DBService getOracleDB() {
        return new OracleDB();
    }*/
}


interface DBService {
    String getDBName();
}

@Component
class MysqlDB implements DBService {

    @Override
    public String getDBName() {
        return "this is mysql db";
    }
}

@Component
class OracleDB implements DBService {

    @Override
    public String getDBName() {
        return "this is oracle db";
    }
}
package com.shkj.datasource;

import javax.sql.DataSource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration//注册到springboot容器，相当于原来xml文件里的<beans>
//下面要进行扫包，目的是标清楚为谁添加的数据源，这样对应的包里函数执行数据库操作的时候，就知道要执行的数据库账号
//密码，表，以及事务处理之类的。
@MapperScan(basePackages= {"com.shkj.slave.mapper"},sqlSessionFactoryRef="slaveSqlSessionFactory")
public class DataSourceSlaveConfig {
	
	@Bean(name="slaveDataSource")//注入到这个容器
    @ConfigurationProperties(prefix="spring.datasource.databaseslave")//表示取application.properties配置文件中的前缀
    //@Primary//primary是设置优先，因为有多个数据源，在没有明确指定用哪个的情况下，会用带有primary的，这个注解必须有一个数据源要添加
    public DataSource testDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name="slaveSqlSessionFactory")
    //@Primary
    //@Qualifier("xxx")的含义是告诉他使用哪个DataSource
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }
    
    @Bean(name="slaveTransactionManager")//配置事务
    //@Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("slaveDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
    
    @Bean(name="slaveSqlSessionTemplate")
    //@Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

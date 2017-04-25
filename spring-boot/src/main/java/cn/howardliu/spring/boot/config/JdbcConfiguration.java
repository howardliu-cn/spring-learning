package cn.howardliu.spring.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * <br>created at 17-3-14
 *
 * @author liuxh
 * @since 1.0.0
 */
@Configuration
@MapperScan(basePackages = "cn.howardliu.spring.boot.mapper")
public class JdbcConfiguration {
    @Bean(initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() throws SQLException {
        DruidDataSource ds = new DruidDataSource();
        String driverClassName = "com.mysql.jdbc.Driver";
        ds.setDriverClassName(driverClassName);
        String jdbcUrl = "jdbc:mysql://localhost:3306/itmonitor?useUnicode=true&characterEncoding=UTF8";
        ds.setUrl(jdbcUrl);
        String username = "root";
        ds.setUsername(username);
        String password = "root";
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        sqlSessionFactory.setDataSource(dataSource);
        return sqlSessionFactory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

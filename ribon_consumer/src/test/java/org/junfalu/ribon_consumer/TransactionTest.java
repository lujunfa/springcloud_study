package org.junfalu.ribon_consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Author: lujunfa  2018/12/11 17:45
 * 编程式事务管理，减小事物管理粒度
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {  RibonConsumerApplication.class})
public class TransactionTest {

    @Autowired
    private PlatformTransactionManager txManager;
    @Autowired
    private DataSource dataSource;

    private static JdbcTemplate jdbcTemplate;

    Logger logger=LoggerFactory.getLogger(TransactionTest.class);
    private static final String INSERT_SQL = "insert into testtranstation(sd) values(?)";
    private static final String COUNT_SQL = "select count(*) from testtranstation";

    /**
     * 第一种编程事物管理
     */
    @Test
    public void testdelivery(){
        //定义事务隔离级别，传播行为，
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        //事务状态类，通过PlatformTransactionManager的getTransaction方法根据事务定义获取；获取事务状态后，Spring根据传播行为来决定如何开启事务
        TransactionStatus status = txManager.getTransaction(def);
        jdbcTemplate = new JdbcTemplate(dataSource);
        Map<String,Object> i = jdbcTemplate.queryForMap(COUNT_SQL);
        System.out.println("表中记录总数："+i);
        try {
            jdbcTemplate.update(INSERT_SQL, "1");
            txManager.commit(status);  //提交status中绑定的事务
        }catch (Exception e){
            txManager.rollback(status);  //回滚
        }
        i = jdbcTemplate.queryForMap(COUNT_SQL);
        System.out.println("表中记录总数："+i);
    }

    /**
     * 第二种编程式事物
     */
    @Test
    public void testTransActionManager(){
        jdbcTemplate = new JdbcTemplate(dataSource);
        Map<String,Object> i  = jdbcTemplate.queryForMap(COUNT_SQL);
        System.out.println("表中记录总数："+i);
        //构造函数初始化TransactionTemplate
        TransactionTemplate template = new TransactionTemplate(txManager);
        //设置隔离级别
        template.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        template.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus transactionStatus) {
                jdbcTemplate.update(INSERT_SQL, "饿死");   //字段sd为int型，所以插入肯定失败报异常，自动回滚，代表TransactionTemplate自动管理事务
                return true;
            }
        });
        i = jdbcTemplate.queryForMap(COUNT_SQL);
        System.out.println("表中记录总数："+i);
    }
}

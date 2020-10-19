package shineyue.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @PackageName: shineyue.com.mybatisplus.config
 * @Description:
 * @author: 罗秉荣
 * @date: 2020/10/12
 */
@EnableTransactionManagement//开启事务
//有配置类的话把扫描的注解放到配置类上
@MapperScan("shineyue.mapper")
@Configuration//标志是一个配置类
public class MybatisPlusConfig {
    //乐观锁插件
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
    //分页查询的插件
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
    /**
     * SQL执行效率插件
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        //直接配置是看不出功能的，需要单独的new出对象
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //设置sql语句执行的最长时间，如果超过这个时间，SQL就不执行了 单位毫秒
        performanceInterceptor.setMaxTime(100);
        //SQL格式化 是否开启格式化
        performanceInterceptor.setFormat(true);//开启SQL格式化
        return performanceInterceptor;
    }
}

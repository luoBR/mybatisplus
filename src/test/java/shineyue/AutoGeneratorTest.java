package shineyue;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;

/**
 * @PackageName: shineyue.com.mybatisplus
 * @Description:
 * @author: 罗秉荣
 * @date: 2020/10/13
 */
public class AutoGeneratorTest {
    public static void main(String[] args) {

        //代码自动生成器 对象
        AutoGenerator auto = new AutoGenerator();
    //配置策略

        //1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String property = System.getProperty("user.dir");//当前程序所在目录
        gc.setOutputDir(property+"/src/main/java");//代码生成到的目录
        gc.setAuthor("罗秉荣");//设置作者
        gc.setOpen(false);//是否打开文件夹
        gc.setFileOverride(true);//是否覆盖
        gc.setServiceName("%sService");//一般的服务生成都是Ixxx 现在需要去掉I 可以这样配置
        gc.setIdType(IdType.ID_WORKER);//id主键的生成策略
        gc.setDateType(DateType.ONLY_DATE);//日期类型
        gc.setSwagger2(true);//
        auto.setGlobalConfig(gc);//配置好之后放到代码生成器对象中


        //2.数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUrl("jdbc:mysql:///mybatis_plus?characterEncoding=utf-8&useSSL=false&useUnicode=true&serverTimezone=GMT%2B8");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);//设置连接的是什么样的数据库
        auto.setDataSource(dsc);

        //3.包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("shineyue.com");
        pc.setModuleName("blog");//设置模块名称
        pc.setEntity("entity");//自动生成entity包以及对应的表类
        pc.setMapper("mapper");//自动生成mapper包以及对应的表类
        pc.setService("service");//自动生成service包以及对应的表类
        pc.setController("controller");//自动生成controller包以及对应的表类
        auto.setPackageInfo(pc);

        //4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        //映射表名，也就是要以哪个表生成对应的 entity service controller 等等
        strategy.setInclude("hello");
        strategy.setNaming(NamingStrategy.underline_to_camel);//自动把下划线转为驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//列名也支持下划线转驼峰的形式
        strategy.setEntityLombokModel(true);//是否使用lombok

        strategy.setLogicDeleteFieldName("deleted");//逻辑删除的名字
        //自动填充
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> list = new ArrayList<>();
        list.add(gmtCreate);
        list.add(gmtModified);
        strategy.setTableFillList(list);

        //乐观锁配置
        strategy.setVersionFieldName("version");//使用乐观锁控制版本的策略
        strategy.setRestControllerStyle(true);//开启驼峰命名
        strategy.setControllerMappingHyphenStyle(true);//表示controller访问 的形式会以_隔开 loca
        auto.setStrategy(strategy);

        //执行
        auto.execute();


    }
}

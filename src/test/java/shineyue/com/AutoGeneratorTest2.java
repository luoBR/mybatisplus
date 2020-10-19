package shineyue.com;

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

import java.util.ArrayList;

/**
 * @PackageName: shineyue.com
 * @Description: 做代码自动生成器测试
 * @author: 罗秉荣
 * @date: 2020/10/14
 */
public class AutoGeneratorTest2 {
    public static void main(String[] args) {
        AutoGenerator ag = new AutoGenerator();
        //配置策略
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        String property = System.getProperty("user.dir");
        gc.setOutputDir(property+"/src/main/java");//创建的放在哪个目录
        gc.setAuthor("罗秉荣2");
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setIdType(IdType.AUTO);//设置id自增策略
        gc.setDateType(DateType.ONLY_DATE);//设置时间类型
        ag.setGlobalConfig(gc);
        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql:///mybatis_plus?characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);//设置数据库类型:mysql
        ag.setDataSource(dsc);
        //配置包
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("blog");
        pc.setParent("shineyue.com");
        pc.setController("controller");
        pc.setService("service");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        ag.setPackageInfo(pc);

        //策略配置
        StrategyConfig sc = new StrategyConfig();
        sc.setVersionFieldName("version");//设置同步锁字段
        sc.setEntityLombokModel(true);//设置使用lombok
        sc.setControllerMappingHyphenStyle(true);//设置访问时是否使用localhost:8080/hello_is这样的下划线隔开形式，而不是驼峰命名的形式
        sc.setRestControllerStyle(true);//是否使用RestController风格
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        sc.setTableFillList(tableFills);//设置需要自动填充的字段

        ag.execute();
    }
}

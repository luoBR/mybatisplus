package shineyue.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @PackageName: shineyue.com.mybatisplus.handler
 * @Description: 这个类是用来测试在使用代码级别的自动填充
 * @author: 罗秉荣
 * @date: 2020/10/12
 *
 */
@Slf4j
@Component//必须把处理器加入到IOC容器中
public class MyMetaObjectHandler implements MetaObjectHandler {
    //插入时的填充策略
    @Override
    public void insertFill(MetaObject metaObject) {
        //通过名字去填充
        //setFieldValByName(String fieldName(字段名), Object fieldVal(字段值), MetaObject metaObject)
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
    //更新时的填充策略
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}

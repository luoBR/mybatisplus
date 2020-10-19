package shineyue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import shineyue.pojo.User;

//在对应的mapper上面实现基本接口BaseMapper;
@Repository //表示持久层的 写完之后的启动类加一个扫描注解扫描这个mapper
public interface UserMapper extends BaseMapper<User> {
    //所有的CRUD已经完成 不需要像使用mybaits那样配置一大堆文件
}

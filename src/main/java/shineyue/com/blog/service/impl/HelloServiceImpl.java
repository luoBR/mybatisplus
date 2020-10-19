package shineyue.com.blog.service.impl;

import shineyue.com.blog.entity.Hello;
import shineyue.com.blog.mapper.HelloMapper;
import shineyue.com.blog.service.HelloService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 罗秉荣
 * @since 2020-10-16
 */
@Service
public class HelloServiceImpl extends ServiceImpl<HelloMapper, Hello> implements HelloService {

}

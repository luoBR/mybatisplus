package shineyue;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shineyue.mapper.UserMapper;
import shineyue.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: shineyue.com.mybatisplus
 * @Description: 测试使用wrapper 条件构造器做复制操作
 * @author: 罗秉荣
 * @date: 2020/10/12
 */
@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;
    //使用Wrapper进行查询，isNotNull()某字段不为空 ge()条件大于等于的
    @Test
    public void testAllEq(){
        //查询name不为空的，并且邮箱不为空的 年龄大于等于12岁的
        //因为我们的selectList()使用高级查询需要一个Wrapper对象，所以创建new QueryWrapper<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //我们需要的条件，就是创建的对象中的方法， isNotNull表示不为空的 ge("age",12)g:表示大于 e:等于
        wrapper.isNotNull("name")
                .isNotNull("email").ge("age",12);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
    //使用wrapper查询一个用户 eq("name",张三):表示查询name等于张三的
    @Test
    void testEq(){
        //需要对象，创建Wrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name","张三");
        User user = userMapper.selectOne(wrapper);//查询一个数据的使用selectOne 查询多个的使用SelectList 或者使用Map
        System.out.println(user);
    }
    //查询在某个区间的数据  between("age",12,30): 表示查询年龄在12到30之间的
    @Test
    void testBetween(){
        //需要对象，创建Wrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.between("age",12,30);//表示查询年龄在12 - 30 岁之间的数据
        Integer count = userMapper.selectCount(wrapper);//统计数量
        //查询的语句 SELECT COUNT(1) FROM user WHERE age BETWEEN 12 AND 30
        System.out.println(count);
    }
    //模糊查询
    @Test
    void testLike(){
        //需要对象，创建Wrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //查询名字中不包含"e"的数据 notLike
        //查询名字中右模糊查询 t% 百分号在右边
        wrapper.notLike("name","e")
                .likeRight("email","t");
        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        maps.forEach(System.out::println);
    }
    //嵌套查询，SQL里面嵌套SQL
    @Test
    void testInSql(){
        //需要对象，创建Wrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //id 在子查询中查询出来
        wrapper.inSql("id","select id from user where id<3");
        //查询SQLSELECT id,name,age,email,deleted,version,gmt_create,gmt_modified
        // FROM user WHERE id IN (select id from user where id<3)
        List<Object> objects = userMapper.selectObjs(wrapper);
        objects.forEach(System.out::println);
    }
    @Test
    void test(){
        //需要对象，创建Wrapper对象
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        //通过id进行排序orderByDesc()降序  orderByAsc():升序
        wrapper.orderByDesc("id");
        //查询sql：FROM user ORDER BY id DESC
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }
}

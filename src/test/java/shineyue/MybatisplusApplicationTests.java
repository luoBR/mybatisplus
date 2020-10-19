package shineyue;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shineyue.mapper.HelloMapper;
import shineyue.mapper.UserMapper;
import shineyue.pojo.Hello;
import shineyue.pojo.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisplusApplicationTests {
    //继承了BaseMapper 所有的方法都来自父类  可以新增自己的方法
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HelloMapper helloMapper;
    //测试查询
    @Test
    public void testSelect() {
        //查询全部用户 括号里面的参数是条件构造器 null表示查询全部
        List<User> users = userMapper.selectList(null);
        //遍历集合打印输出
        users.forEach(System.out::println);
    }

    /**
     * 测试mybatis-plus是如何识别到底查询的是库中的哪个表？
     *  它是根据所写类的名称，以实体类的名称作为表去查询。
     */
    @Test
    void test1(){
        List<Hello> helloMappers = helloMapper.selectList(null);
        System.out.println(helloMappers);
    }
    //测试插入
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        user.setEmail("qq@love");
        userMapper.insert(user);//会自动插入id
    }
    //测试更新 update
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1315487725125812228L);
        user.setName("dufu1");
        int i = userMapper.updateById(user);
    }
    //测试乐观锁成功!单线程
    @Test
    public void testOptimisticLocker(){
        //1.查询用户信息
        User user = userMapper.selectById(1L);
        //2.修改用户信息
        user.setName("likui");
        //3.执行更新操作
        userMapper.updateById(user);
    }
    //测试乐观锁失败!多线程
    @Test
    public void testOptimisticLocker2(){
        //线程一
        User user = userMapper.selectById(1L);
        user.setName("likui111");
        //模拟另一个线程执行了插队擦操作
        User user2 = userMapper.selectById(1L);
        user2.setName("likui222");
        userMapper.updateById(user2);
        userMapper.updateById(user);
        //最终会执行user2 在两个都拿到系统版本之后，user2修改了
        //user1在比对版本时版本不对，会自动回滚事务。

    }
    //测试单个查询
    @Test
    public void testSelectById(){
        //使用id查询 查询单个id
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }
    //测试批量查询
    @Test
    public void testSelectByBatchId(){
        //使用id查询 查询多个id信息selectBatchIds("<集合>") 这里使用了一个工具类转为集合
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1L,2L,3L));
        users.forEach(System.out::println);
    }
    //测试条件查询 map
    @Test
    public void testSelectByBatchIds(){
        HashMap<String , Object> hashMap = new HashMap<>();
        //做为条件查询，集合的key作为条件查询的字段，value作为查询的条件值
        //相当于SELECT id,name,age,email,version,gmt_create,gmt_modified FROM user WHERE name = libai
        hashMap.put("name","张三");
        //在加上一条 相当于user WHERE name = ? AND age = ?
//        hashMap.put("age",18);
        List<User> users = userMapper.selectByMap(hashMap);
        users.forEach(System.out::println);
    }
    //测试分页查询
    @Test
    public void testPage(){
        //底层源码IPage<T> selectPage(IPage<T> var1, @Param("ew") Wrapper<T>(高级查询) var2);
        //参数IPage<T> 导包时导入的是mybatisPlus下的Page
        // page new Page<>(1,5)这个对象表示查询当前页码，每页五个数据
        Page<User> page = new Page<>(1,5);
        //底层还是使用limit分页查询
        //SELECT id,name,age,email,version,gmt_create,gmt_modified FROM user LIMIT 0,5
        IPage<User> userIPage = userMapper.selectPage(page, null);
    }
    //测试单个删除
    @Test
    public void testDeleteById(){
        //通过id删除
        userMapper.deleteById(1315487725125812228L);
    }
    //测试批量删除
    @Test
    public void testDeleteByIds(){
        //使用集合的形式存放要删除的多个id
        int i = userMapper.deleteBatchIds(Arrays.asList(1315481792421171202L, 1315486541321310210L));
        System.out.println("删除几行:"+i);
    }
    //通过map删除，也就是根据其他条件删除
    @Test
    public void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","张三");
        //map.put("id",1315487002363326465L);
        int i = userMapper.deleteByMap(map);
        System.out.println("删除："+i+"行");
    }


}

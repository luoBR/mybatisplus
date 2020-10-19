package shineyue.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @PackageName: shineyue.com.mybatisplus.pojo
 * @Description:
 * @author: 罗秉荣
 * @date: 2020/10/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User  {
    /**对应数据库中的主键(uuid、自增id 、雪花算法、redis、zookeeper)
        雪花算法几乎可以保证全球唯一
        使用注解指定主键策略 默认使用的是:ID_WORKER 表示全局唯一id
        可以自己设置主键唯一策略 AUTO自增策略
     AUTO(0), 数据库id自增，使用时在主键上设计要设计自增策略
     NONE(1), 未设置主键
     INPUT(2),手动输入
     ID_WORKER(3), 默认的全局唯一id
     UUID(4),全局唯一id
     ID_WORKER_STR(5); 字符串表示法
     */
    @TableId(type = IdType.AUTO) //主键自动生成策略
    private Long id;
    private String name;
    private Integer age;
    private String email;

    // @TableLogic注解描述表示逻辑删除，也就是改变对应的deleted值（0:没删除，1:已经删除）
    @TableLogic
    private Integer deleted;

    //代表是乐观锁的注解
    @Version
    private Integer version;

    /**
     * fill 填充: 它有几个值
     * public enum FieldFill {
     *     DEFAULT,默认的，不操作
     *     INSERT, 插入时操作
     *     UPDATE, 修改时操作
     *     INSERT_UPDATE; 插入或者修改时操作
     */
    //表示在插入时自动记录时间
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    //在修改和插入时都会触发更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}

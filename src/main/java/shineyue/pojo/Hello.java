package shineyue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.PipedReader;

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
public class Hello {
    private Integer id;
    private String usename;
}

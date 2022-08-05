import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件测试
 * @author HuanyuLee
 * @date 2022/8/5
 */
public class ConfigTest {
    @Test
    void config() {
        Properties props = new Properties();
        try {
            props.load(ConfigTest.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = (String) props.get("initTankCount");
        System.out.println("str = " + str);
    }
}

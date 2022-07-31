import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 图片操作的测试
 * @author HuanyuLee
 * @date 2022/7/27
 */
public class ImageTest {
    /**
     * 测试加载图片
     */
    @Test
    public void imageLoadTest() {
        try {
            BufferedImage image = ImageIO.read(new File("src/images/BadTank1.png"));
            assertNotNull(image);
            // 利用反射
            BufferedImage image2 = ImageIO.read(Objects.requireNonNull(ImageTest.class.getClassLoader().getResourceAsStream("images/BadTank1.png")));
            assertNotNull(image2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试旋转图片
     */
    @Test
    public void imageRotateTest() {
        try {
            BufferedImage tankL = ImageIO.read(Objects.requireNonNull(ImageTest.class.getClassLoader().getResourceAsStream("images/GoodTank1.png")));
            tankL = rotateImage(tankL, 90);
            assertNotNull(tankL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage rotateImage(final BufferedImage bufferedimage, final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d = (img = new BufferedImage(w, h, type)).createGraphics()).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
}

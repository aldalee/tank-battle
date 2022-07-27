package com.aleecoder;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 图片工具类 旋转图片
 * @author HuanyuLee
 * @date 2022/7/27
 */
public class ImageUtil {
    public static BufferedImage rotateImage(final BufferedImage bufferedimage, final int degree) {
        int w = bufferedimage.getWidth();
        int h = bufferedimage.getHeight();
        int type = bufferedimage.getColorModel().getTransparency();
        BufferedImage img;
        Graphics2D graphics2d;
        (graphics2d =
                (img = new BufferedImage(w, h, type))
                        .createGraphics()
        ).setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2d.rotate(Math.toRadians(degree), (double) w / 2, (double) h / 2);
        graphics2d.drawImage(bufferedimage, 0, 0, null);
        graphics2d.dispose();
        return img;
    }
}

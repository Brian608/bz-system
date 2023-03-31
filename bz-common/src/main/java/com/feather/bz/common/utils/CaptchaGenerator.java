package com.feather.bz.common.utils;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
/**
 * @projectName: bz-system
 * @package: com.feather.bz.common.utils
 * @className: CaptchaGenerator
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2023-03-31 16:27
 * @version: 1.0
 */

public class CaptchaGenerator {

    public static BufferedImage generateCaptchaImage(String captchaText) {
        int width = 150;
        int height = 50;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        // 设置验证码背景颜色为浅色
        Color backgroundColor = new Color(230, 230, 230);
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, width, height);

        // 设置字体和大小
        Font font = new Font("Arial", Font.PLAIN, 24);
        g2d.setFont(font);

        // 设置文字颜色
        Color textColor = new Color(0, 0, 0, 128); // 半透明黑色
        g2d.setColor(textColor);

        // 绘制验证码文字
        g2d.drawString(captchaText, 10, 30);

        // 绘制干扰线
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int x2 = random.nextInt(width);
            int y2 = random.nextInt(height);

            Color lineColor = new Color(200, 200, 200);
            g2d.setColor(lineColor);
            g2d.drawLine(x1, y1, x2, y2);
        }

        g2d.dispose();

        return bufferedImage;

    }

}

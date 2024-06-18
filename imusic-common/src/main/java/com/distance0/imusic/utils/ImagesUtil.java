package com.distance0.imusic.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: XiangJing
 * @date: 2024/6/15 下午3:49
 * @description:
 */
public class ImagesUtil {

    public static String readImages(String file) throws IOException {
        URL url = new URL(file);
        BufferedImage image = ImageIO.read(url);
        Map<Integer, Integer> colorCountMap = new HashMap<>();

        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getRGB(x, y);
                Color color = new Color(pixel);
                if (!isUnwantedColor(color)) {
                    colorCountMap.put(pixel, colorCountMap.getOrDefault(pixel, 0) + 1);
                }
            }
        }

        // 找到出现次数最多的颜色
        int maxCount = 0;
        int mostFrequentColor = 0;

        for (Map.Entry<Integer, Integer> entry : colorCountMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostFrequentColor = entry.getKey();
            }
        }

        return String.format("#%06X", mostFrequentColor & 0xFFFFFF);
    }

    private static boolean isUnwantedColor(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        // 判断白色
        if (r > 200 && g > 200 && b > 200) {
            return true;
        }

        // 判断黑色
        if (r < 50 && g < 50 && b < 50) {
            return true;
        }

        // 判断褐色 (简单版，可以根据需要调整)
        if (r > 100 && r < 150 && g < 100 && b < 100) {
            return true;
        }

        return false;
    }
}

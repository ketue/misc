package com.monical.spring.designPattern.flyweight;

import java.awt.*;

/**
 * @author zijie.cao
 * @date 2018-01-24 10:48:41
 */
public interface Shape {
    void draw(Graphics g, int x, int y, int width, int height, Color color);
}

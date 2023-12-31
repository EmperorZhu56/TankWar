package com.element.map;

import com.element.enums.MapElementType;
import com.game.Game;
import com.scene.Scene;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Brick extends MapElement {
    public boolean[] flag;

    public Brick(int x, int y) {
        super(x, y, MapElementType.BRICK);
        this.flag = new boolean[]{true, true, true, true};
    }

    @Override
    public void draw(Graphics g) {
        this.draw(g, Game.getStage());
    }

    public void draw(Graphics g, Scene scene) {
        if (getIsLive()) {
            boolean flag_temp = false;
            BufferedImage part;
            for (int i = 0; i < 4; i++) {
                if (flag[i]) {
                    int x_temp = (i % 2) * 8;
                    int y_temp = (i / 2) * 8;
                    part = getImage().getSubimage(x_temp, y_temp, 8, 8);
                    g.drawImage(part, getX() + x_temp, getY() + y_temp, 8, 8, Game.getStage());
                    flag_temp = true;
                }
            }
            if (!flag_temp) {
                setIsLive(false);
            }
        }
    }
}

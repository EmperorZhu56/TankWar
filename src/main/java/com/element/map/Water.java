package com.element.map;

import com.element.enums.MapElementType;
import com.game.Game;
import com.util.RangeUtil;

import java.awt.*;

public class Water extends MapElement {

    int flag = 0;

    public Water(int x, int y) {
        super(x, y, MapElementType.WATER);
    }

    @Override
    public void draw(Graphics g) {
        if (getIsLive()) {
            setImage(Game.getMaterial("map").getSubimage(3 * 16, (flag % 2) * 16, 16, 16));
            g.drawImage(getImage(), getX(), getY(), 16, 16, Game.getStage());
            flag = RangeUtil.right(0, flag, 4);
        }
    }
}

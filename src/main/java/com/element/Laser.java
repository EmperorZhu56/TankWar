package com.element;

import com.element.enums.Direct;
import com.element.tank.Tank;
import com.game.Game;
import com.history.core.util.stream.Ztream;

import java.awt.*;

public class Laser extends Bullet {

    public Laser(Tank master, Direct direct) {
        super(master);
        setReach(24);
        this.setLife(8);
        int reach = getReach();
        switch (direct.ordinal()) {
            case 0 -> {
                setHeight(16 * reach);
                this.setY(master.getY() - getHeight());
            }
            case 2 -> {
                setHeight(16 * reach);
                this.setY(master.getY() + 32);
            }
            case 1 -> {
                setWidth(16 * reach);
                this.setX(master.getX() + 32);
            }
            case 3 -> {
                setWidth(16 * reach);
                this.setX(master.getX() - getWidth());
            }
        }
        length();
    }

    @Override
    public void draw(Graphics g) {
        setImage(Game.getMaterial("bullet_2").getSubimage(getDirect().ordinal() * 16, 0, 16, 16));
        g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), Game.getStage());
        if (getLife() > 0) {
            setLife(getLife() - 1);
        } else {
            setIsLive(false);
        }
        if (getIsLive()) {
            if (!Game.pause) {
                bitTank();
            }
            if (!isInStage()) {
                setIsLive(false);
            }
        } else {
            getMaster().decrBulletNum();
            Game.getStage().bullets.remove(this);
        }
    }

    private void length() {
        Tank master = getMaster();
        Ztream.of(Game.getStage().elements).forEach(e -> {
            if (isTouch(e) && !Bullet.GO_MAP.get(e.getMapType())) {
                int eX = e.getX();
                int eY = e.getY();
                int eWidth = e.getWidth();
                int eHeight = e.getHeight();
                int masterX = master.getX();
                int masterY = master.getY();
                int masterWidth = master.getWidth();
                int masterHeight = master.getHeight();
                switch (getDirect()) {
                    case UP -> {
                        eHeight = masterY - (eY + eHeight);
                        setY(masterY - eHeight);
                    }
                    case DOWN -> {
                        setHeight(eY - (masterY + masterHeight));
                        setY(masterY + 32);
                    }
                    case RIGHT -> {
                        setWidth(eX - (masterX + masterWidth));
                        setX(masterX + 32);
                    }
                    case LEFT -> {
                        eWidth = masterX - (eX + eWidth);
                        setX(masterX - eWidth);
                    }
                }
            }
        });
    }
}
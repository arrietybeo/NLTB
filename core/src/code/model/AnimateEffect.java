package code.model;

import code.effect.new_skill.EffectSkill;
import code.main.GameCanvas;
import code.screen.Res;
import code.screen.screen.GameScr;

import javax.microedition.lcdui.Image;

import lib.mGraphics;
import lib.mSystem;
import lib.mVector;

public class AnimateEffect extends Effect {
    private static final byte RAIN = 0;
    private static final byte FALLING_LEAVES = 1;
    private static final byte SNOW = 2;
    private static final byte FALLING_FLOWER = 3;
    private static final byte FIREWORK = 4;
    private byte type = 0;
    private int number = 0;
    private int timeLimit;
    private int curTime;
    mVector list = new mVector();
    public boolean isStop;
    private static int wind = 5;
    private static int countWind;
    private static int dirWind = Res.rnd(1, -1);
    private short idImg;

    public void close() {
        GameScr.effAnimate.removeElement(this);
    }

    public void stop() {
        this.isStop = true;
    }

    public AnimateEffect(byte type, boolean isStart, int number, int timeLimit) {
        this.timeLimit = timeLimit;
        this.curTime = (int) (mSystem.currentTimeMillis() / 1000L);
        this.type = type;
        this.number = number;
        switch (type) {
            case 0:
            default:
                break;
            case 1:
                this.idImg = 122;
                break;
            case 2:
                this.idImg = 123;
                break;
            case 3:
                this.idImg = 121;
                this.type = 3;
                break;
            case 4:
                this.idImg = 124;
                this.type = 3;
                break;
            case 5:
                this.idImg = 125;
                this.type = 3;
        }

        for (int i = 0; i < number; ++i) {
            Point pos = null;
            if (isStart) {
                pos = new Point((GameScr.cmx - GameCanvas.hw + Res.rnd(GameCanvas.w * 2)) * 10, (GameScr.cmy - GameCanvas.h * 2 + Res.rnd(GameCanvas.h * 2)) * 10);
            } else {
                pos = new Point();
                this.rndPos(pos);
            }

            if (type != 2 && this.type != 3) {
                pos.h = Res.rnd(4);
            } else {
                pos.h = Res.rnd(3);
            }

            pos.limitY = 16 + Res.rnd(3) * 4;
            pos.v = Res.rnd(-1, 1);
            pos.color = Res.rnd(pos.limitY);
            pos.dis = (byte) Res.rnd(20);
            this.list.addElement(pos);
        }

    }

    public void paint(mGraphics g) {
        GameCanvas.resetTrans(g);
        g.translate(-GameScr.cmx, -GameScr.cmy);
        switch (this.type) {
            case 0:
                this.paintRain(g);
                break;
            case 1:
                this.paintFallingLeaves(g);
                break;
            case 2:
                this.paintSnow(g);
                break;
            case 3:
                this.paintFallingFlower(g);
        }

    }

    private void paintSnow(mGraphics g) {
        Image img = EffectSkill.getImage(this.idImg);
        if (img != null) {
            for (int i = 0; i < this.number; ++i) {
                Point pos = (Point) this.list.elementAt(i);
                if (pos.x / 10 > GameScr.cmx && pos.x / 10 < GameScr.cmx + GameCanvas.w && pos.y / 10 > GameScr.cmy) {
                    g.drawRegion(img, 0, 7 * pos.dis, 7, 7, 0, pos.x / 10, pos.y / 10, 3, false);
                }
            }
        }

    }

    private void paintFallingFlower(mGraphics g) {
        Image img = EffectSkill.getImage(this.idImg);
        int aa = 0;
        if (img != null)
            for (int i = 0; i < this.number; i++) {
                Point pos = (Point) this.list.elementAt(i);
                if (pos.x / 10 > GameScr.cmx && pos.x / 10 < GameScr.cmx + GameCanvas.w &&
                        pos.y / 10 > GameScr.cmy) {
                    aa = 2 - pos.h + 1;
                    if (aa < 2)
                        aa = pos.dis / 10;
                    g.drawRegion(img, 0, aa * 10, 10, 10, 0, pos.x / 10, pos.y / 10, 3, false);
                    pos.dis = (byte) (pos.dis + 1);
                    if (pos.dis >= 20)
                        pos.dis = 0;
                }
            }
    }

    private void paintFallingLeaves(mGraphics g) {
        Image img = EffectSkill.getImage(this.idImg);
        if (img != null) {
            for (int i = 0; i < this.number; ++i) {
                Point p = (Point) this.list.elementAt(i);
                if (p.x / 10 > GameScr.cmx && p.x / 10 < GameScr.cmx + GameCanvas.w && p.y / 10 > GameScr.cmy) {
                    g.drawRegion(img, 0, p.color / (p.limitY / 4) * 10, 16, 10, 0, p.x / 10, p.y / 10, 3, false);
                }
            }
        }

    }

    private void paintRain(mGraphics g) {
        g.setColor(-2236963);

        for (int i = 0; i < this.number; ++i) {
            Point pos = (Point) this.list.elementAt(i);
            if (pos.x / 10 > GameScr.cmx && pos.x / 10 < GameScr.cmx + GameCanvas.w && pos.y / 10 > GameScr.cmy) {
                g.fillRect(pos.x / 10, pos.y / 10, 1, pos.h + 2, false);
            }
        }

    }

    public static void updateWind() {
        int aa = 1;
        if (GameCanvas.gameTick % 6 == 3) {
            aa = Res.rnd(15);
        }

        if (aa == 0 && wind == 5) {
            wind = 5 + Res.rnd(20);
            countWind = 50 + Res.rnd(100);
        }

        if (countWind > 0) {
            --countWind;
        }

        if (countWind == 0 && wind > 5 && GameCanvas.gameTick % 4 == 2) {
            --wind;
        }

    }

    public void update() {
        if (this.timeLimit > 0 && mSystem.currentTimeMillis() / 1000L - (long) this.curTime > (long) this.timeLimit) {
            this.isStop = true;
        }

        switch (this.type) {
            case 0:
                this.updateRain();
                break;
            case 1:
                this.updateFallingLeaves();
                break;
            case 2:
                this.updateSnow();
                break;
            case 3:
                this.updateFlower();
        }

    }

    private void updateSnow() {
        for (int i = 0; i < this.number; ++i) {
            Point pos = (Point) this.list.elementAt(i);
            pos.y += (pos.h + 4) * 3;
            pos.x += (pos.h + 1) * 2 + wind * dirWind;
            if (pos.y / 10 < GameScr.cmy - GameCanvas.hw || pos.y / 10 > GameScr.cmy + GameCanvas.h + GameCanvas.hh - (4 - pos.h) * 50 || pos.x / 10 < GameScr.cmx - GameCanvas.hw || pos.x / 10 > GameScr.cmx + GameCanvas.w + GameCanvas.hw) {
                this.rndPos(pos);
            }
        }

    }

    private void updateFlower() {
        for (int i = 0; i < this.number; ++i) {
            Point pos = (Point) this.list.elementAt(i);
            pos.y += (pos.h + 2) * 5;
            pos.x += (pos.h + 1) * 2 + wind * dirWind;
            if (pos.y / 10 < GameScr.cmy - GameCanvas.hw || pos.y / 10 > GameScr.cmy + GameCanvas.h + GameCanvas.hh - (4 - pos.h) * 50 || pos.x / 10 < GameScr.cmx - GameCanvas.hw || pos.x / 10 > GameScr.cmx + GameCanvas.w + GameCanvas.hw) {
                this.rndPos(pos);
            }
        }

    }

    private void updateFallingLeaves() {
        for (int i = 0; i < this.number; ++i) {
            Point pos = (Point) this.list.elementAt(i);
            pos.y += 10;
            pos.x += pos.v * 10 + wind * dirWind;
            ++pos.color;
            if (pos.color >= pos.limitY) {
                pos.color = 0;
            }

            if (pos.y / 10 < GameScr.cmy - GameCanvas.hw || pos.y / 10 > GameScr.cmy + GameCanvas.h + GameCanvas.hh - (4 - pos.h) * 50 || pos.x / 10 < GameScr.cmx - GameCanvas.hw || pos.x / 10 > GameScr.cmx + GameCanvas.w + GameCanvas.hw) {
                this.rndPos(pos);
            }
        }

    }

    private void updateRain() {
        for (int i = 0; i < this.number; ++i) {
            Point pos = (Point) this.list.elementAt(i);
            pos.y += (pos.h + 1) * 15 + (3 - pos.h) * 3;
            ++pos.g;
            pos.x += (3 - pos.h + 1) * 2 + wind * dirWind;
            if (pos.y / 10 < GameScr.cmy - GameCanvas.hw || pos.y / 10 > GameScr.cmy + GameCanvas.h + GameCanvas.hh - (4 - pos.h) * 50 || pos.x / 10 < GameScr.cmx - GameCanvas.hw || pos.x / 10 > GameScr.cmx + GameCanvas.w + GameCanvas.hw) {
                this.rndPos(pos);
            }
        }

    }

    private void rndPos(Point pos) {
        if (this.isStop) {
            this.list.removeElement(pos);
            this.number = this.list.size();
            if (this.list.size() == 0) {
                this.close();
            }
        } else {
            pos.y = (GameScr.cmy - GameCanvas.hh + Res.rnd(GameCanvas.h * 2)) * 10;
            pos.x = (GameScr.cmx - GameCanvas.hw + Res.rnd(GameCanvas.w * 2)) * 10;
            if (this.type != 2 && this.type != 3) {
                pos.h = Res.rnd(4);
            } else {
                pos.h = Res.rnd(3);
            }
        }

    }
}

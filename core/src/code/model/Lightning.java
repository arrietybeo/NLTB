package code.model;

import code.screen.Util;
import code.screen.Utils;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mVector;

public class Lightning extends Effect {
    private int toX;
    private int toY;
    int fRemove;
    byte timeAddNum;
    byte type;
    mVector VecEff = new mVector();
    mVector VecSubEff = new mVector();

    public Lightning(Actor target) {
        this.target = target;
        if (target != null) {
            this.toX = target.x;
            this.toY = target.y;
        }

        this.fRemove = 5;
        this.timeAddNum = 7;
        this.createLighting(this.toX + Utils.random_Am_0(20), GameScr.cmy - 5, this.toX, this.toY, true);
        this.type = 0;
    }

    public Lightning(Actor target, int type) {
        this.target = target;
        if (target != null) {
            this.toX = target.x;
            this.toY = target.y;
        }

        this.fRemove = 5;
        this.timeAddNum = 7;
        this.createLighting(this.toX + Utils.random_Am_0(20), GameScr.cmy - 5, this.toX, this.toY, true);
        this.type = (byte) type;
    }

    public Lightning(Actor From, Actor target, int type) {
        this.target = target;
        if (target != null) {
            this.toX = target.x;
            this.toY = target.y;
        }

        this.fRemove = 5;
        this.timeAddNum = 7;
        this.createLighting(From.x, From.y, this.toX, this.toY, true);
        this.type = (byte) type;
    }

    private void createLighting(int xFrom, int yFrom, int xTo, int yTo, boolean isEnd) {
        int gocmax = 0;
        int gocLight = 0, gocpaint = 0;
        int maxline = Utils.getDistance(xFrom, yFrom, xTo, yTo) / 15 + Utils.random(3), tRandom = Utils.random(2);
        gocLight = Util.angle(xTo - xFrom, yTo - yFrom);
        int lLight = 20;
        for (int i = 0; i < maxline; i++) {
            Point p = new Point();
            if (i == 0) {
                p.x = xFrom * 1000;
                p.y = yFrom * 1000;
            } else {
                Point p2 = (Point) this.VecEff.elementAt(i - 1);
                lLight = 20 + Utils.random_Am_0(10);
                int t = Utils.getDistance(p2.x / 1000, p2.y / 1000, xTo,
                        yTo);
                if ((t < 25 || i == maxline - 1) && isEnd) {
                    p.x = xTo * 1000;
                    p.y = yTo * 1000;
                    this.VecEff.addElement(p);
                    break;
                }
                gocpaint = gocLight;
                if (isEnd) {
                    if (Utils.abs(gocmax) > 50) {
                        if (gocmax > 0) {
                            gocpaint -= Utils.random(5, 50);
                        } else {
                            gocpaint += Utils.random(5, 50);
                        }
                    } else if (i % 2 == tRandom) {
                        gocpaint -= Utils.random(5, 50);
                    } else {
                        gocpaint += Utils.random(5, 50);
                    }
                } else if (Utils.abs(gocmax) > 50) {
                    if (gocmax > 0) {
                        gocpaint -= Utils.random(5, 50);
                    } else {
                        gocpaint += Utils.random(5, 50);
                    }
                } else if (i % 2 == tRandom) {
                    gocpaint -= Utils.random(10, 40);
                } else {
                    gocpaint += Utils.random(10, 40);
                }
                gocmax += gocpaint - gocLight;
                gocpaint = Util.fixangle(gocpaint);
                p2.x += Util.cos(gocpaint) * lLight;
                p2.y += Util.sin(gocpaint) * lLight;
                if (Utils.random(6) < 5) {
                    this.VecSubEff.addElement(p);
                    Point ptam = new Point();
                    ptam = p;
                    int lSmall = 10, gocSmall = gocpaint, numSmall =
                            Utils.random(3, 7);
                    for (int j = 0; j < numSmall; j++) {
                        lSmall = 5 + Utils.random_Am_0(3);
                        gocSmall = gocpaint;
                        if (j % 2 == tRandom) {
                            gocSmall -= Utils.random(10, 40);
                        } else {
                            gocSmall += Utils.random(10, 40);
                        }
                        gocSmall = Util.fixangle(gocSmall);
                        Point p3 = new Point();
                        ptam.x += Util.cos(gocSmall) * lSmall;
                        ptam.y += Util.sin(gocSmall) * lSmall;
                        this.VecSubEff.addElement(p3);
                        ptam = p3;
                    }
                    ptam.x = -1;
                    this.VecSubEff.addElement(ptam);
                }
            }
            this.VecEff.addElement(p);
        }
    }


    public void paint(mGraphics g) {
        int i;
        Point p;
        Point p2;
        switch (this.type) {
            case 0:
                if (this.f % 5 < 2 || this.f % 5 == 3) {
                    for (i = 1; i < this.VecEff.size(); ++i) {
                        p = (Point) this.VecEff.elementAt(i - 1);
                        p2 = (Point) this.VecEff.elementAt(i);
                        g.setColor(-463122);
                        g.drawLine(p.x / 1000, p.y / 1000 - 1, p2.x / 1000, p2.y / 1000 - 1, false);
                        g.drawLine(p.x / 1000, p.y / 1000 + 1, p2.x / 1000, p2.y / 1000 + 1, false);
                        g.drawLine(p.x / 1000, p.y / 1000 + 2, p2.x / 1000, p2.y / 1000 + 2, false);
                        g.setColor(-460760);
                        g.drawLine(p.x / 1000, p.y / 1000, p2.x / 1000, p2.y / 1000, false);
                    }

                    for (i = 1; i < this.VecSubEff.size(); ++i) {
                        p = (Point) this.VecSubEff.elementAt(i - 1);
                        p2 = (Point) this.VecSubEff.elementAt(i);
                        if (p2.x != -1 && p.x != -1) {
                            g.setColor(-460760);
                            g.drawLine(p.x / 1000, p.y / 1000, p2.x / 1000, p2.y / 1000, false);
                            g.drawLine(p.x / 1000, p.y / 1000 + 1, p2.x / 1000, p2.y / 1000 + 1, false);
                        }
                    }
                }
                break;
            case 1:
                if (this.f % 5 < 2 || this.f % 5 == 3) {
                    for (i = 1; i < this.VecEff.size(); ++i) {
                        p = (Point) this.VecEff.elementAt(i - 1);
                        p2 = (Point) this.VecEff.elementAt(i);
                        g.setColor(-463122);
                        g.drawLine(p.x / 1000, p.y / 1000 - 1, p2.x / 1000, p2.y / 1000 - 1, false);
                        g.drawLine(p.x / 1000, p.y / 1000 + 1, p2.x / 1000, p2.y / 1000 + 1, false);
                        g.drawLine(p.x / 1000, p.y / 1000 + 2, p2.x / 1000, p2.y / 1000 + 2, false);
                        g.setColor(-513832);
                        g.drawLine(p.x / 1000, p.y / 1000, p2.x / 1000, p2.y / 1000, false);
                    }

                    for (i = 1; i < this.VecSubEff.size(); ++i) {
                        p = (Point) this.VecSubEff.elementAt(i - 1);
                        p2 = (Point) this.VecSubEff.elementAt(i);
                        if (p2.x != -1 && p.x != -1) {
                            g.setColor(-513832);
                            g.drawLine(p.x / 1000, p.y / 1000, p2.x / 1000, p2.y / 1000, false);
                            g.drawLine(p.x / 1000, p.y / 1000 + 1, p2.x / 1000, p2.y / 1000 + 1, false);
                        }
                    }
                }
        }

    }

    public void update() {
        ++this.f;
        if (this.f >= this.fRemove) {
            this.wantDestroy = true;
        }

    }
}

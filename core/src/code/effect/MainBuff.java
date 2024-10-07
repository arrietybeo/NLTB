package code.effect;

import code.main.GameCanvas;
import code.screen.Res;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;

public class MainBuff {
    boolean isPaintLast = false;
    public boolean wantDestroy = false;
    public int typeBuff;
    int framebegin;
    public long timelive;
    int x0;
    int y0;
    int x1000;
    int y1000;
    mVector vecEff = new mVector();
    public int[][] colorStar = new int[][]{{-466912, -479160, -1}, {-9732096, -4133256, -1}, {-433415, -2651656, -1}, {-16125704, -16125704, -1}, {-195323, -866361, -1}, {-34816, -34816, -1}};

    public MainBuff(int type, long time) {
        this.typeBuff = type;
        this.x0 = 0;
        this.y0 = 0;
        this.framebegin = Res.random(9);
        this.isPaintLast = true;
        this.timelive = time;
    }

    public void paint(mGraphics g, int x, int y) {
        for (int i = this.vecEff.size() - 1; i >= 0; i--) {
            Line l = (Line) this.vecEff.elementAt(i);
            if (l != null) {
                int color = 0;
                color = this.colorStar[this.typeBuff][l.idColor];
                g.setColor(color);
                g.drawLine(l.x0, l.y0, l.x1, l.y1, false);
                if (l.is2Line)
                    g.drawLine(l.x0 + 1, l.y0, l.x1 + 1, l.y1, false);
            }
        }
        this.x1000 = x;
        this.y1000 = y;
    }

    public void update() {
        if (GameCanvas.gameTick % 2 == 0) {
            this.create_Line_NHANBAN_LV2();
        }

        for (int i = 0; i < this.vecEff.size(); ++i) {
            Line l = (Line) this.vecEff.elementAt(i);
            l.update();
            if (l.f >= l.fRe) {
                this.vecEff.removeElement(l);
                --i;
            }
        }

        if (this.timelive - mSystem.currentTimeMillis() < 0L) {
            this.wantDestroy = true;
        }

    }

    public void create_Line_NHANBAN_LV2() {
        int fT = Res.random(1, 4);
        for (int i = 0; i < fT; i++) {
            Line p = new Line();
            int lT = Res.random(3, 12);
            int vlt = 0;
            if (lT <= 5) {
                p.fRe = 16;
                vlt = 2;
            } else if (lT <= 8) {
                vlt = 4;
                p.fRe = 12;
            } else {
                vlt = 5;
                p.fRe = 9;
            }
            p.is2Line = (Res.random(5) == 0);
            int xp = this.x1000 + Res.random_Am_0(15);
            int yp = this.y1000 - Res.random_Am_0(10);
            p.setLine(xp, yp, xp, yp - lT, 0, -vlt, p.is2Line);
            p.idColor = Res.random(3);
            this.vecEff.addElement(p);
        }
    }
}

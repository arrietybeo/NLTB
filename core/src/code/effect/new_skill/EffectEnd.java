package code.effect.new_skill;

import code.model.Effect;
import code.model.FrameImage;
import code.model.Point;
import code.screen.Res;
import code.screen.Util;
import lib.mGraphics;
import lib.mVector;

public class EffectEnd extends Effect {
    public static final byte END_PHAO_HOA = 0;

    public static final byte END_DA_VANG = 1;

    public static final byte END_DA_LUA = 2;

    public static final byte END_EFF_BANG = 3;

    public static final byte END_EFF_TRUC_LAM = 4;

    public FrameImage fraImgEff;

    public int type;

    public int f;

    int fRemove;

    mVector VecEffEnd;

    int vMax;

    int CFrame;

    mVector VecEff;

    public EffectEnd(int type, int x, int y) {
        int i;
        this.VecEffEnd = new mVector();
        this.VecEff = new mVector();
        this.type = type;
        this.x = x;
        this.y = y;
        int tphao = 0;
        int idimg = 0;
        switch (type) {
            case 1:
                this.fraImgEff = new FrameImage(113);
                tphao = Res.random(4, 6);
                this.fRemove = 12;
                for (i = 0; i < tphao; i++) {
                    Point p = new Point();
                    p.x = x + Res.random_Am_0(4);
                    p.y = y + Res.random_Am_0(5);
                    p.frame = Res.random(this.fraImgEff.nFrame);
                    p.vy = -Res.random(6, 9);
                    p.vx = Res.random(1, 4);
                    if (i % 2 == 0)
                        p.vx = -p.vx;
                    this.VecEffEnd.addElement(p);
                }
                break;
            case 0:
            case 2:
            case 3:
                if (type == 0) {
                    idimg = 39;
                } else if (type == 2) {
                    idimg = 114;
                } else if (type == 3) {
                    idimg = 112;
                }
                this.fraImgEff = new FrameImage(idimg);
                tphao = Res.random(7, 12);
                this.fRemove = 12;
                for (i = 0; i < tphao; i++) {
                    Point p = new Point();
                    p.x = x + Res.random_Am_0(4);
                    p.y = y + Res.random_Am_0(5);
                    p.frame = Res.random(this.fraImgEff.nFrame);
                    p.vy = -Res.random(5, 9);
                    p.vx = Res.random(0, 3);
                    if (i % 2 == 0)
                        p.vx = -p.vx;
                    this.VecEffEnd.addElement(p);
                }
                break;
            case 4:
                this.fraImgEff = new FrameImage(130);
                create_Ice_Arc();
                break;
        }
    }

    public void paint(mGraphics g) {
        int i;
        switch (this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                for (i = 0; i < this.VecEffEnd.size(); i++) {
                    Point p = (Point) this.VecEffEnd.elementAt(i);
                    if (this.f < this.fRemove / 3 * 2) {
                        if (this.fraImgEff != null)
                            this.fraImgEff.drawFrame(p.frame, p.x, p.y, 0, 3, g);
                    } else if (this.fraImgEff != null) {
                        this.fraImgEff.drawFrame((p.frame == 0) ? 3 : p.frame, p.x, p.y, 0, 3, g);
                    }
                }
                break;
            case 4:
                if (this.fraImgEff == null)
                    return;
                for (i = 0; i < this.VecEff.size(); i++) {
                    Point p = (Point) this.VecEff.elementAt(i);
                    if (p.f == 0)
                        this.fraImgEff.drawFrame(this.CFrame, p.x / 1000, p.y / 1000, 0, 3, g);
                }
                break;
        }
    }

    public void update() {
        int i;
        boolean isRe;
        int j;
        this.f++;
        switch (this.type) {
            case 0:
            case 1:
            case 2:
            case 3:
                for (i = 0; i < this.VecEffEnd.size(); i++) {
                    Point p = (Point) this.VecEffEnd.elementAt(i);
                    p.update();
                    p.vy++;
                }
                if (this.f >= this.fRemove)
                    this.wantDestroy = true;
                break;
            case 4:
                this.CFrame++;
                if (this.CFrame > 2)
                    this.CFrame = 0;
                isRe = true;
                for (j = 0; j < this.VecEff.size(); j++) {
                    Point p = (Point) this.VecEff.elementAt(j);
                    if (p.f == 0) {
                        isRe = false;
                        if (this.f >= this.fRemove) {
                            p.f = 1;
                        } else {
                            p.x += p.vx;
                            p.y += p.vy;
                        }
                    } else if (p.f > 0) {
                        p.f++;
                        if ((p.f - 1) / 2 >= 5) {
                            p.f = -1;
                            this.VecEff.removeElement(p);
                        }
                        isRe = false;
                    }
                }
                if (isRe)
                    this.wantDestroy = true;
                break;
        }
    }

    private void create_Ice_Arc() {
        this.fRemove = 25;
        this.vMax = 5;
        for (int i = 0; i < 16; i++) {
            Point p = new Point();
            p.x = this.x * 1000;
            p.y = this.y * 1000;
            p.vx = 2 * Util.cos(225 * i / 10) * this.vMax;
            p.vy = 1 * Util.sin(225 * i / 10) * this.vMax;
            p.f = 0;
            this.VecEff.addElement(p);
        }
    }
}

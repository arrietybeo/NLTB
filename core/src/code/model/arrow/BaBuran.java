package code.model.arrow;

import code.effect.new_skill.EffectSkill;
import code.main.GameCanvas;
import code.model.Actor;
import code.model.EffectManager;
import code.model.LiveActor;
import code.screen.Util;
import lib.mGraphics;

public class BaBuran extends IArrow {
    private int imgIndex;
    private int transform;
    int frame;
    public int power;
    public byte effect;
    public byte typeEffEnd = -1;
    private Actor target;
    private Actor owner;
    private int[] xw;
    private int[] yw;
    int pos;

    public BaBuran(int imgIndex) {
        this.imgIndex = imgIndex;
    }

    public void paint(mGraphics g) {
        if (EffectSkill.getImage(this.imgIndex) != null) {
            g.drawRegion(EffectSkill.getImage(this.imgIndex), 0, this.frame * EffectSkill.getHight(this.imgIndex), EffectSkill.getWidth(this.imgIndex), EffectSkill.getHight(this.imgIndex), this.transform, this.xw[this.pos], this.yw[this.pos], 3, false);
        }

    }

    public void set(int type, int x, int y, int power, byte effect, Actor owner, Actor target) {
        this.effect = effect;
        this.target = target;
        this.power = power;
        this.owner = owner;
        int dx = 0, dy = 0;
        dx = target.x - x;
        dy = target.y + target.getDy() - y - target.getYfly();
        int nPosition = (Math.abs(dx) + Math.abs(dy)) / 20;
        if (nPosition < 2)
            nPosition = 2;
        this.xw = new int[nPosition];
        this.yw = new int[nPosition];
        for (int i = 1; i < nPosition; i++) {
            this.xw[i] = x + i * dx / nPosition;
            this.yw[i] = y + i * dy / nPosition;
        }
        int d = Arrow.findDirIndexFromAngle(Util.angle(dx, -dy));
        this.frame = Arrow.FRAME[d];
        this.transform = Arrow.TRANSFORM[d];
    }

    public void setAngle(int angle) {
    }

    public void onArrowTouchTarget() {
        if (this.target.getHp() <= 0) {
            LiveActor.startDeadFly(this.target, this.owner.ID);
            this.wantDestroy = true;
        } else {
            this.target.jumpVang(this.owner);
            if (this.target != null) {
                if (this.target.getState() != 8 && this.target.getState() != 5) {
                    this.target.setMove2Target(this.owner);
                    this.target.jumpVang(this.owner);
                }

                EffectManager.addHiDataeffectSkill(133, this.target.x, this.target.y - this.target.getHeight() / 2, 1);
                this.wantDestroy = true;
                if (this.power > 0) {
                    GameCanvas.gameScr.startFlyText("- " + this.power, 0, this.target.x, this.target.y, -1, 2);
                }
            } else {
                EffectManager.addHiDataeffectSkill(133, this.xw[this.pos], this.yw[this.pos], 1);
            }

        }
    }

    public void update() {
        if (this.target == null) {
            this.wantDestroy = true;
            this.onArrowTouchTarget();
        }

        ++this.pos;
        if (this.pos >= this.xw.length) {
            this.pos = this.xw.length;
        }

        if (this.pos == this.xw.length) {
            this.onArrowTouchTarget();
        }
    }

    public void set(int type, int x, int y, int power, Actor owner, Actor target) {
    }

    public void set(int type, int x, int y, int power, Actor owner, Actor target, int eff) {
    }

    public void setIDHEAD(int idHead) {
    }
}

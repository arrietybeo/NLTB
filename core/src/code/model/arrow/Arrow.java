package code.model.arrow;

import code.effect.new_skill.EffectSkill;
import code.main.GameCanvas;
import code.model.Actor;
import code.model.DataSkillEff;
import code.model.EffectManager;
import code.model.LiveActor;
import code.screen.Util;
import lib.mGraphics;

public class Arrow extends IArrow {
    public static final int[] ARROWINDEX = new int[]{0, 15, 37, 52, 75, 105, 127, 142, 165, 195, 217, 232, 255, 285, 307, 322, 345, 370};
    public int power;
    public int xto;
    public int yto;
    public int idEff;
    public byte effect;
    public byte typeEffEnd = -1;
    private Actor target;
    private Actor Owner;
    private int[] xw;
    private int[] yw;
    int frame;
    int transform;
    int pos;
    private byte typeEnd;
    public static final byte[][] ARROWSIZE = new byte[][]{{16, 24, 36, 24, 24, 36, 17, 28, 28, 73, 32, 14, 21, 28, 31, 16, 32}, {16, 24, 36, 24, 24, 36, 17, 28, 28, 73, 32, 13, 16, 28, 22, 16, 32}};
    public static final int[] TRANSFORM = new int[]{0, 0, 0, 7, 6, 6, 6, 2, 2, 3, 3, 4, 5, 5, 5, 1};
    public static final byte[] FRAME = new byte[]{0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0, 1, 2, 1, 0};
    private short endeff;
    private short follow = -1;
    private DataSkillEff arrow;

    public Arrow(int imgIndex) {
    }

    public Arrow() {
    }

    public void set(int x, int y, Actor owner, Actor target, int power, int eff0, int eff1, int eff2, int endEff, byte typeEnd, byte speed) {
        this.target = target;
        this.power = power;
        this.Owner = owner;
        this.endeff = (short) endEff;
        int dx = 0, dy = 0;
        this.xto = target.x;
        this.yto = target.y - target.getHeight() / 2;
        dx = target.x - x;
        dy = (short) (this.yto - 0 - y);
        int nPosition = (Math.abs(dx) + Math.abs(dy)) / speed;
        if (nPosition < 2)
            nPosition = 2;
        this.xw = new int[nPosition];
        this.yw = new int[nPosition];
        for (int i = 1; i < nPosition; i++) {
            this.xw[i] = x + i * dx / nPosition;
            this.yw[i] = y + i * dy / nPosition;
        }
        this.typeEffEnd = typeEnd;
        int d = findDirIndexFromAngle(Util.angle(dx, -dy));
        this.frame = FRAME[d];
        this.transform = TRANSFORM[d];
        if (this.frame == 0) {
            this.arrow = new DataSkillEff(eff0, (byte) this.transform);
        } else if (this.frame == 1) {
            this.arrow = new DataSkillEff(eff1, (byte) this.transform);
        } else if (this.frame == 2) {
            this.arrow = new DataSkillEff(eff2, (byte) this.transform);
        }
    }

    public void setDragon(int x, int y, Actor target, int power, int eff0, int endEff, int follow) {
        this.target = target;
        this.power = power;
        this.endeff = (short) endEff;
        int dx = 0, dy = 0;
        dx = target.x - x;
        dy = target.y + target.getDy() - y;
        int nPosition = (Math.abs(dx) + Math.abs(dy)) / 20;
        if (nPosition < 2)
            nPosition = 2;
        this.xw = new int[nPosition];
        this.yw = new int[nPosition];
        for (int i = 1; i < nPosition; i++) {
            this.xw[i] = x + i * dx / nPosition;
            this.yw[i] = y + i * dy / nPosition;
        }
        int d = findDirIndexFromAngle(Util.angle(dx, -dy));
        this.frame = FRAME[d];
        this.transform = TRANSFORM[d];
        this.arrow = new DataSkillEff(eff0, (byte) this.transform);
        this.follow = (short) follow;
    }

    public void setAngle(int angle) {
    }

    public void onArrowTouchTarget() {
        if (this.power > 0) {
            GameCanvas.gameScr.startFlyText("- " + this.power, 0, this.xto, this.yto, -1, 2);
        }

        if (this.typeEffEnd == 0) {
            EffectSkill eff = new EffectSkill(this.xto, this.yto - 20, this.endeff);
            EffectManager.addHiEffect(eff);
        } else {
            EffectManager.addHiDataeffectSkill(this.endeff, this.xto, this.yto, 1);
        }

        if (this.target.getHp() <= 0) {
            LiveActor.startDeadFly(this.target, this.Owner.ID);
        } else {
            this.target.jumpVang(this.Owner);
        }

        this.wantDestroy = true;
    }

    public void paint(mGraphics g) {
        if (this.arrow != null) {
            this.arrow.paintTopArrowFly(g, this.xw[this.pos], this.yw[this.pos]);
        }

    }

    public void set(int type, int x, int y, int power, byte effect, Actor owner, Actor target) {
        this.effect = effect;
        this.target = target;
        this.power = power;
        int dx = 0, dy = 0;
        dx = target.x - x;
        dy = target.y + target.getDy() - y;
        if (type == 7) {
            dx = target.x + 10 - x;
            dy = target.y + target.getDy() - y;
        } else if (type == 8) {
            dx = target.x - 10 - x;
            dy = target.y - 10 + target.getDy() - y;
        }
        int nPosition = (Math.abs(dx) + Math.abs(dy)) / 20;
        if (nPosition < 2)
            nPosition = 2;
        this.xw = new int[nPosition];
        this.yw = new int[nPosition];
        for (int i = 1; i < nPosition; i++) {
            this.xw[i] = x + i * dx / nPosition;
            this.yw[i] = y + i * dy / nPosition;
        }
        int d = findDirIndexFromAngle(Util.angle(dx, -dy));
        this.frame = FRAME[d];
        this.transform = TRANSFORM[d];
        this.Owner = owner;
    }

    public static int findDirIndexFromAngle(int angle) {
        for (int i = 0; i < ARROWINDEX.length - 1; ++i) {
            if (angle >= ARROWINDEX[i] && angle <= ARROWINDEX[i + 1]) {
                if (i >= 16) {
                    return 0;
                }

                return i;
            }
        }

        return 0;
    }

    public void update() {
        if (this.arrow != null) {
            this.arrow.updateArrow();
        }

        ++this.pos;
        if (this.pos >= this.xw.length) {
            this.pos = this.xw.length;
        }

        if (this.follow != -1 && this.pos < this.xw.length - 1 && (this.pos % 3 == 0 || this.pos == 1)) {
            EffectManager.addLowDataeffectSkill(20, this.xw[this.pos], this.yw[this.pos], 0);
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

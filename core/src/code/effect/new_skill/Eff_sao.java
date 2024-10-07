package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Effect;
import code.model.EffectManager;
import code.screen.Util;

import javax.microedition.lcdui.Image;

import lib.mGraphics;

public class Eff_sao extends Effect {
    public short R;
    public short angle;
    public short r;
    public short BK;
    public byte frame;
    public byte loai;
    public byte[] arr_frame = new byte[]{0, 1, 2, 3};
    public byte[] bk = new byte[]{40, 70};
    public boolean no;
    public short[] radian = new short[]{0, 90, 180, 270};
    Actor target;

    public Eff_sao(Actor target, byte type, short bk) {
        this.target = target;
        this.x = target.x;
        this.y = target.y;
        this.loai = type;
        this.BK = bk;
        if (type == 23) {
            this.R = bk;
            this.r = -8;
        }

        if (type == 39) {
            this.R = 1;
            this.r = (short) GameCanvas.random(3, 7);
        }

    }

    public Eff_sao(short xto, short yto, byte type, short bk) {
        this.x = xto;
        this.y = yto;
        this.loai = type;
        this.BK = bk;
        if (type == 23) {
            this.R = bk;
            this.r = -8;
        }

        if (type == 39) {
            this.R = 1;
            this.r = (short) GameCanvas.random(3, 7);
        }

    }

    public void paint(mGraphics g) {
        for (int i = 0; i < this.radian.length; ++i) {
            this.frame = (byte) Math.abs(GameCanvas.r.nextInt() % this.arr_frame.length);
            Image img = EffectSkill.getImage(this.loai);
            if (img != null) {
                g.drawRegion(img, 0, this.arr_frame[this.frame] * EffectSkill.getHight(this.loai), EffectSkill.getWidth(this.loai), EffectSkill.getHight(this.loai), 0, Util.cos(this.radian[i]) * this.R / 1024 + this.x, Util.sin(this.radian[i]) * this.R / 1024 + this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
            }

            this.angle = (short) (this.angle + 45);
        }

    }

    public void update() {
        this.R += this.r;
        int i;
        if (this.loai == 23) {
            for (i = 0; i < this.radian.length; ++i) {
                EffectSkill.createLowEfect(Util.cos(this.radian[i]) * this.R / 1024 + this.x, Util.sin(this.radian[i]) * this.R / 1024 + this.y, 40);
            }
        }

        for (i = 0; i < this.radian.length; ++i) {
            short[] var10000 = this.radian;
            var10000[i] = (short) (var10000[i] + 10);
            if (this.radian[i] >= 359) {
                this.radian[i] = 0;
            }
        }

        if (this.R <= 0 && this.loai == 23) {
            EffectSkill.createLowEfect(this.x, this.y, 51);
            Effect_dien e = new Effect_dien(this.x, this.y, 11, 40);
            EffectManager.addHiEffect(e);
            this.wantDestroy = true;
            if (this.target != null) {
                this.target.settimevang((short) 8);
            }
        }

        if (this.R > 40 && this.loai == 39) {
            this.wantDestroy = true;
        }

    }
}

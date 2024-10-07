package code.effect;

import code.effect.new_skill.EffectSkill;
import code.effect.new_skill.Effect_Sao_chop;
import code.effect.new_skill.Skill_Boss_BBCT_Nem_Cau;
import code.effect.new_skill.Skill_Boss_BBCT_Tha_Doi;
import code.effect.new_skill.Skill_Dao_T4;
import code.effect.new_skill.Skill_DefaultBoss_BBCT;
import code.effect.new_skill.Skill_Default_Boss_TruongDoTe;
import code.main.GameCanvas;
import code.model.Actor;
import code.model.BossBienBucCongTu;
import code.model.Effect;
import code.model.EffectManager;
import code.model.Point;
import code.model.arrow.Arrow;
import code.screen.Res;
import code.screen.Util;
import code.screen.Utils;
import code.screen.screen.GameScr;

import javax.microedition.lcdui.Image;

import lib.mGraphics;
import lib.mVector;

public class EffectSkillMonster extends Effect {
    private int idImage;

    private int dame;

    private Actor target;

    private Actor from;

    private Actor arrowTarget;

    public int transform;

    public short dx;

    public short dy;

    public short angle;

    public short xto;

    public short yto;

    public short pos;

    public byte frame;

    public byte frame1;

    public byte type;

    public byte effect;

    public byte Position;

    public byte style;

    private int[] xw;

    private int[] yw;

    private int[] Sdame;

    public static final byte CICLE = 0;

    public static final byte EFFECT = 1;

    public static final byte ARROW = 2;

    public static final byte EFF_BOSS_HAT_TUNG = 3;

    public static final byte EFF_BOSS_THA_RAN = 4;

    public static final byte EFF_BOSS_0 = 5;

    public static final byte EFF_BOSS_1 = 6;

    public static final byte EFF_BOSS_2 = 7;

    public static final byte EFF_BOSS_3 = 8;

    public static final byte EFF_BOSS_4 = 9;

    public static final byte EFF_BOSS_5 = 10;

    public static final byte EFF_BOSS_11 = 11;

    public static final byte EFF_BOSS_12 = 12;

    public static final byte EFF_BOSS_13 = 13;

    public static final byte EFF_BOSS_6X1 = 14;

    public static final byte EFF_BOSS_6X2 = 15;

    private mVector VecEff;

    private mVector ntarget;

    public boolean isJum;

    int x1000;

    int y1000;

    int toX;

    int toY;

    int vMax;

    int timeAddNum;

    int vx;

    int vy;

    int test;

    public EffectSkillMonster(int type, Actor from, mVector target, int[] dame) {
        int i;
        Skill_Boss_BBCT_Nem_Cau sknc;
        Skill_Boss_BBCT_Tha_Doi sk;
        int j;
        Actor objBeKillMain;
        this.VecEff = new mVector();
        this.ntarget = new mVector();
        this.type = (byte) type;
        this.from = from;
        this.ntarget = target;
        switch (type) {
            case 0:
                for (i = 0; i < this.ntarget.size(); i++) {
                    Actor ac = (Actor) this.ntarget.elementAt(i);
                    if (ac != null) {
                        EffectSkill.createHiEfect(ac.x, ac.y - ac.getHeight() / 2, 30);
                        GameCanvas.gameScr.startFlyText("-" + dame[i], 2,
                                ac.x, ac.y - 35, -1, -2);
                    }
                }
                this.wantDestroy = true;
                break;
            case 5:
                for (i = 0; i < this.ntarget.size(); i++) {
                    Actor ac = (Actor) this.ntarget.elementAt(i);
                    if (ac != null) {
                        GameCanvas.gameScr.StartNewLightning(ac, 1);
                        GameCanvas.gameScr.startFlyText("-" + dame[i], 2,
                                ac.x, ac.y - 35, -1, -2);
                        EffectSkill.createHiEfect(ac.x, ac.y - ac.getHeight() / 2, 30);
                        EffectManager.addLowEffect(ac.x, ac.y, 55);
                    }
                }
                this.wantDestroy = true;
                break;
            case 6:
                for (i = 0; i < this.ntarget.size(); i++) {
                    Actor ac = (Actor) this.ntarget.elementAt(i);
                    if (ac != null) {
                        GameCanvas.gameScr.startNewMagicBeam(17, from, ac, from.x, from.y, dame[i] / 3, 157);
                        for (int k = 0; k < 2; k++)
                            GameCanvas.gameScr.startNewMagicBeam(17, from, ac, from.x + Res.random(32, 60), from.y + Res.random(32, 60), dame[i] / 3, 157);
                    }
                }
                this.wantDestroy = true;
                break;
            case 7:
                for (i = 0; i < this.ntarget.size(); i++) {
                    Actor ac = (Actor) this.ntarget.elementAt(i);
                    Skill_Dao_T4 n = new Skill_Dao_T4(from.x, from.y, ac.x, ac.y, i * 5, dame[i]);
                    EffectManager.addHiEffect((Effect) n);
                }
                this.wantDestroy = true;
                break;
            case 8:
                sknc = new Skill_Boss_BBCT_Nem_Cau();
                sknc.type = 119;
                sknc.arrDame = dame;
                sknc.allTarget = this.ntarget;
                sknc.boss = (BossBienBucCongTu) from;
                EffectManager.addHiEffect((Effect) sknc);
                this.wantDestroy = true;
                break;
            case 9:
                sk = new Skill_Boss_BBCT_Tha_Doi(this.ntarget, (BossBienBucCongTu) from, dame);
                EffectManager.addHiEffect((Effect) sk);
                this.wantDestroy = true;
                break;
            case 10:
                for (j = 0; j < this.ntarget.size(); j++) {
                    Actor ac = (Actor) this.ntarget.elementAt(j);
                    Skill_DefaultBoss_BBCT e = new Skill_DefaultBoss_BBCT(from.x, (short) (from.y - from.getHeight()), ac, (byte) 96, (byte) 45, (byte) -2, (byte) 30, ac.getHeight() * 2 / 3);
                    e.dame = dame[j];
                    e.idEffectAuto = 221;
                    e.setOwner(from);
                    EffectManager.addHiEffect((Effect) e);
                }
                this.wantDestroy = true;
                break;
            case 11:
                for (j = 0; j < this.ntarget.size(); j++) {
                    Actor ac = (Actor) this.ntarget.elementAt(j);
                    Skill_Default_Boss_TruongDoTe e = new Skill_Default_Boss_TruongDoTe(ac, 235);
                    e.dame = dame[j];
                    e.setOwner(from);
                    EffectManager.addHiEffect((Effect) e);
                }
                this.wantDestroy = true;
                break;
            case 12:
                this.wantDestroy = true;
                break;
            case 13:
                for (j = 0; j < this.ntarget.size(); j++) {
                    Actor ac = (Actor) this.ntarget.elementAt(j);
                    Skill_Default_Boss_TruongDoTe e = new Skill_Default_Boss_TruongDoTe(ac, 4);
                    e.dame = dame[j];
                    e.setOwner(from);
                    EffectManager.addHiEffect((Effect) e);
                }
                this.wantDestroy = true;
                break;
            case 14:
                this.style = -1;
                this.Sdame = dame;
                objBeKillMain = (Actor) target.elementAt(0);
                this.arrowTarget = objBeKillMain;
                if (objBeKillMain != null) {
                    this.toX = objBeKillMain.x;
                    this.toY = objBeKillMain.y;
                }
                this.y1000 = GameScr.cmy -
                        Utils.random(10, 20);
                this.vMax = 18;
                this.fRemove = 20;
                this.timeAddNum = 11;
                create_Arrow_Rain();
                this.f = 0;
                this.fRemove = 10;
                break;
            case 15:
                this.style = -1;
                break;
        }
    }

    private void create_Arrow_Rain() {
        if (this.f == -1)
            this.VecEff.removeAllElements();
        for (int j = 0; j < this.ntarget.size(); j++) {
            Actor obj = (Actor) this.ntarget
                    .elementAt(j);
            if (obj != null)
                if (obj != null) {
                    int tAR = Utils.random(1, 4);
                    if (GameCanvas.lowGraphic)
                        tAR = Utils.random(1, 3);
                    for (int i = 0; i < tAR; i++) {
                        this.vMax = Utils.random(14, 18);
                        Point p = new Point();
                        p.x = obj.x - 70 + Utils.random_Am_0(15);
                        p.y = this.y1000;
                        int xdich = obj.x - p.x + Utils.random_Am_0(30);
                        int ydich = obj.y - p.y + 8 + Utils.random_Am_0(10);
                        if (ydich / this.vMax > 8)
                            this.vMax = ydich / 8;
                        Point pf = new Point();
                        pf = create_Speed(xdich, ydich, pf);
                        p.vx = pf.vx;
                        p.vy = pf.vy;
                        p.fRe = pf.fRe;
                        p.f = 0;
                        this.VecEff.addElement(p);
                    }
                }
        }
    }

    public Point create_Speed(int xdich, int ydich, Point p) {
        if (ydich == 0)
            ydich = 1;
        if (xdich == 0)
            xdich = 1;
        int vx = 0, vy = 0;
        int dis = Utils.getDistance(xdich, ydich) / this.vMax;
        if (dis == 0)
            dis = 1;
        vx = xdich / dis;
        vy = ydich / dis;
        if (Utils.abs(vx) > Utils.abs(xdich))
            vx = xdich;
        if (Utils.abs(vy) > Utils.abs(ydich))
            vy = ydich;
        if (this.arrowTarget != null) {
            this.toX = this.arrowTarget.x;
            this.toY = this.arrowTarget.y - this.arrowTarget.getHeight() / 2;
        }
        if (p != null) {
            p.vx = vx;
            p.vy = vy;
            p.toX = this.toX;
            p.toY = this.toY;
            p.fRe = dis;
        } else {
            this.fRemove = dis;
            this.vx = vx;
            this.vy = vy;
        }
        return p;
    }

    public EffectSkillMonster(int typeAttack, int idimage, Actor from, Actor target, int dame) {
        this.VecEff = new mVector();
        this.ntarget = new mVector();
        this.from = from;
        this.target = target;
        this.dame = dame;
        this.x = from.x;
        this.y = from.y;
        this.xto = target.x;
        int dy = 40;
        if (typeAttack == 2)
            this.style = 2;
        if (typeAttack == 3)
            this.style = 0;
        this.yto = (short) (target.y - dy);
        this.idImage = idimage;
        if (this.idImage == 103)
            dy = 0;
        if (this.idImage == 104)
            dy = -40;
        if (this.idImage == -1)
            this.style = 0;
        if (this.style == 2) {
            this.Position = 20;
            setspeed(this.Position);
        }
    }

    public void setspeed(int Positions) {
        this.dx = (short) (this.xto - this.x);
        this.dy = (short) (this.yto - 0 - this.y);
        this.angle = (short) Util.angle(this.dx, this.dy);
        int nPosition = (Math.abs(this.dx) + Math.abs(this.dy)) / 20;
        if (nPosition < 2)
            nPosition = 2;
        this.xw = new int[nPosition];
        this.yw = new int[nPosition];
        for (int i = 0; i < nPosition; i++) {
            this.xw[i] = this.x + i * this.dx / nPosition;
            this.yw[i] = this.y + i * this.dy / nPosition;
        }
        int d = Arrow.findDirIndexFromAngle(Util.angle(this.dx, -this.dy));
        this.frame1 = Arrow.FRAME[d];
        this.transform = Arrow.TRANSFORM[d];
    }

    public void paint(mGraphics g) {
        int i;
        Image img;
        Image img1;
        int j;
        if (this.type == -1)
            return;
        switch (this.type) {
            case 14:
                for (i = 0; i < this.VecEff.size(); i++) {
                    Point p = (Point) this.VecEff.elementAt(i);
                    if (p != null && !p.isRemove)
                        if (p.dis == 0) {
                            g.setColor(10549488);
                            g.drawLine(p.x, p.y, p.x + 6, p.y + 8, false);
                            g.drawLine(p.x, p.y, p.x + 5, p.y + 8, false);
                        } else if (p.dis == 1) {

                        }
                }
                break;
        }
        switch (this.style) {
            case 2:
                img = EffectSkill.getImage(this.idImage);
                if (img != null)
                    g.drawRegion(img, 0, this.frame1 * EffectSkill.getHight(this.idImage),
                            EffectSkill.getWidth(this.idImage), EffectSkill.getHight(this.idImage), this.transform, this.xw[this.pos], this.yw[this.pos], mGraphics.VCENTER | mGraphics.HCENTER, false);
                break;
            case 0:
            case 1:
                img1 = EffectSkill.getImage(this.idImage);
                if (img1 != null)
                    g.drawRegion(img1, 0, this.frame * EffectSkill.getHight(this.idImage),
                            EffectSkill.getWidth(this.idImage), EffectSkill.getHight(this.idImage), this.transform, this.x, this.y, mGraphics.VCENTER | mGraphics.HCENTER, false);
                break;
            case 3:
                for (j = 0; j < this.VecEff.size(); j++) {
                    Point p = (Point) this.VecEff.elementAt(j);
                    if (this.f <= this.fRemove) {
                        int fpaint = 0;
                        int partf = 3;
                        if (this.f < 2)
                            fpaint = this.f;
                        if (this.f > this.fRemove - 5) {
                            fpaint = this.fRemove - this.f;
                            partf = 5;
                        } else {
                            fpaint = 2;
                        }
                        Image img2 = EffectSkill.getImage(this.idImage);
                        if (img2 != null)
                            g.drawRegion(img2, 0, this.frame * EffectSkill.getHight(this.idImage),
                                    EffectSkill.getWidth(this.idImage), EffectSkill.getHight(this.idImage) /
                                            partf * (fpaint + 1), 0, p.x, p.y,
                                    mGraphics.BOTTOM | mGraphics.HCENTER, false);
                    }
                }
                break;
        }
    }

    public void onArrowTouchTarget() {
        EffectSkill.createHiEfect(this.xto, this.yto + 10, 30);
    }

    public void update() {
        int i;
        short vx;
        short vy;
        short dx1;
        short dy1;
        boolean isMatchX;
        boolean isMatchY;
        int disx;
        int j;
        switch (this.type) {
            case 14:
                this.f++;
                for (i = 0; i < this.VecEff.size(); i++) {
                    Point p = (Point) this.VecEff.elementAt(i);
                    if (p.dis == 0) {
                        p.update();
                        if (p.f >= p.fRe) {
                            p.dis = 1;
                            p.f = 0;
                            if (Utils.random(4) == 0) {
                                Effect_Sao_chop ef = new Effect_Sao_chop(p.x, p.y, 24);
                                EffectManager.addHiEffect((Effect) ef);
                            }
                        }
                    } else if (p.dis == 1) {
                        p.f++;
                        if (p.f >= 2 &&
                                this.test == 0) {
                            this.VecEff.removeElement(p);
                            i--;
                        }
                    }
                }
                if (this.f < this.fRemove) {
                    if (!GameCanvas.lowGraphic || GameCanvas.gameTick % 2 == 0) {
                        this.y1000 = GameScr.cmy -
                                Utils.random(10, 20);
                        create_Arrow_Rain();
                    }
                    break;
                }
                if (this.VecEff.size() == 0) {
                    for (i = 0; i < this.ntarget.size(); i++) {
                        Actor ac = (Actor) this.ntarget.elementAt(i);
                        if (ac != null) {
                            EffectSkill.createHiEfect(ac.x, ac.y - ac.getHeight() / 2, 30);
                            GameCanvas.gameScr.startFlyText("-" + this.Sdame[i], 2,
                                    ac.x, ac.y - 35, -1, -2);
                        }
                    }
                    this.wantDestroy = true;
                }
                break;
        }
        switch (this.style) {
            case 2:
                if (this.pos < this.xw.length)
                    this.pos = (short) (this.pos + 1);
                if (this.pos >= this.xw.length) {
                    this.pos = (short) (this.xw.length - 1);
                    this.xw[this.pos] = this.xto;
                    this.yw[this.pos] = this.yto;
                    this.wantDestroy = true;
                    onArrowTouchTarget();
                    if (this.target != null)
                        this.target.jum();
                    GameCanvas.gameScr.startFlyText("-" + this.dame, 2,
                            this.xto + 0, this.yto - 15, -1, -2);
                }
                break;
            case 0:
            case 1:
                this.frame = (byte) (this.frame + 1);
                if (this.frame > EffectSkill.getFrame(this.idImage) - 1)
                    this.frame = 0;
                if (this.target != null) {
                    this.xto = this.target.x;
                    this.yto = (short) (this.target.y - 20);
                } else {
                    this.wantDestroy = true;
                }
                this.dx = (short) (this.xto - this.x);
                this.dy = (short) (this.yto - 0 - this.y);
                this.angle = (short) Util.angle(this.dx, this.dy);
                vx = (short) (12 * Util.cos(this.angle) >> 10);
                vy = (short) (12 * Util.sin(this.angle) >> 10);
                this.x += vx;
                this.y += vy;
                dx1 = (short) Math.abs(this.x - this.xto);
                dy1 = (short) Math.abs(this.y - this.yto);
                isMatchX = false;
                isMatchY = false;
                disx = Utils.getDistance(this.x, this.y, this.xto, this.yto);
                if (dx1 <= vx) {
                    this.x = this.xto;
                    isMatchX = true;
                }
                if (dy1 <= vy) {
                    this.y = this.yto;
                    isMatchY = true;
                }
                if ((isMatchX && isMatchY) || disx < 20) {
                    this.wantDestroy = true;
                    GameCanvas.gameScr.startFlyText("-" + this.dame, 2,
                            this.xto + 0, this.yto - 15, -1, -2);
                    if (this.target != null) {
                        onArrowTouchTarget();
                        if (GameScr.r.nextInt(11) < 12)
                            this.target.jum();
                    }
                }
                if (this.idImage == -1 && GameCanvas.gameTick % 3 == 0)
                    EffectManager.addLowDataeffectSkill(4, this.x, this.y + 3, 1);
                if (this.style == 1)
                    EffectSkill.createHiEfect(this.x, this.y - 20, this.idImage);
                break;
            case 3:
                this.f++;
                for (j = 0; j < this.VecEff.size(); j++) {
                    Point p = (Point) this.VecEff.elementAt(j);
                    if (this.f == 1)
                        GameScr.timeVibrateScreen = 103;
                    p.f++;
                }
                if (this.f >= this.fRemove)
                    this.wantDestroy = true;
                break;
        }
    }
}

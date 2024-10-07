package code.model;

import code.effect.EffectSkillMonster;
import code.effect.new_skill.EffectSkill;
import code.main.GameCanvas;
import code.network.GameService;
import code.screen.Res;
import code.screen.Util;
import code.screen.Utils;
import code.screen.screen.GameScr;
import lib.Tilemap;
import lib.mGraphics;
import lib.mRandom;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class Monster extends LiveActor {
    public int idChar = -1;

    public short range = 40;

    public short xTo;

    public short yTo;

    public short p1;

    public short p2;

    public short p3;

    public short monster_type;

    public short mtoX;

    public short xTarget;

    public short yTarget;

    public short mtoY;

    public static final byte STAND = 0;

    public static final byte DEAD = 1;

    public static final byte WALK = 2;

    public static final byte ATTACK = 3;

    public static final byte INJURE = 4;

    public static final byte DEADFLY = 5;

    public static final byte MOVE_TO_TARGET = 6;

    public static final byte GO_TO_XYFIRST = 7;

    public static final byte WAIT = 8;

    public static final byte MOVE_AROUND_TARGET = 9;

    public static final byte FLASH_TO_TARGET = 11;

    public static final byte RETURN = 12;

    public short saveXfirst;

    public short saveYfirst;

    public mVector ntarget = new mVector();

    public static final byte MOVE_AND_FIRE = 0;

    public static final byte FLASH = 1;

    public static final byte FIRE_ARROW = 2;

    public static final byte FIRE_MAGIC = 3;

    public byte typeattack;

    public byte typeDie;

    public short idEffect;

    int timeWait;

    int timeLimit;

    short lastDir = -100;

    public static final byte TYPE0 = 4;

    private int timeStand;

    byte vx;

    byte vy;

    byte idShadow;

    byte countAttack;

    public short savex;

    public short savey;

    public int tickDie;

    public int f;

    public byte Typepaint;

    private long timeDelay;

    public boolean isMineral;

    public boolean isSetInfo;

    short idTemplate;

    public short timepaint = 10;

    public short saveXvang;

    public short saveYvang;

    public short timefly;

    public static mRandom r = new mRandom(mSystem.currentTimeMillis());

    public boolean isbossOffline = false;

    short dx;

    short dy;

    public int[] mDame;

    public String oldname;

    byte huongY;

    byte flip;

    byte frame;

    private boolean isFreeze;

    private boolean canrevives;

    public Actor target;

    byte idSkill;

    short[] angle;

    boolean isv;

    public static final byte COLOR_MONSTER_WHITE = 0;

    public static final byte COLOR_MONSTER_GREEN = 1;

    public static final byte COLOR_MONSTER_OGRANGE = 2;

    public static final byte COLOR_MONSTER_YELLOW = 3;

    public static final byte COLOR_MONSTER_BLUE = 4;

    public short dyPaintPk;

    byte changSpeed;

    boolean getMonster_Template;

    long timeBeginDie;

    long timeRevive;

    public long timeGetInfo;

    long timeCreate;

    byte[] sp;

    public short timevang;

    public short ang;

    int vxx;

    int vyy;

    public short[] rx;

    public short[] ry;

    byte currentFrame;

    byte rangestop;

    byte cout;

    boolean isStand;

    private int xAround;

    private int yAround;

    private int tickmove;

    private int[] dmove;

    byte totalWay;

    private String nameTieu;

    int vxDie;

    int vyDie;

    int vyStyleDie;

    int vyStyleStand;

    public int frameDie;

    public int timeBienmat;

    private byte countDie;

    public boolean canpaint;

    public int getTypeMove() {
        return this.typeattack;
    }

    public Monster() {
        this.huongY = 0;
        this.flip = 0;
        this.canrevives = true;
        this.idSkill = 0;
        this.angle = new short[]{0, 45, 90, 135, 180, 225, 270};
        this.isv = false;
        this.dyPaintPk = 0;
        this.getMonster_Template = false;
        this.timeGetInfo = mSystem.currentTimeMillis();
        this.sp = new byte[]{-2, -1, 1, 2};
        this.timevang = 0;
        this.rx = new short[]{-16, 16, 8, -8, 12, -12, 20, -20};
        this.ry = new short[]{-16, 16, 8, -8, 12, -12, 20, -20};
        this.currentFrame = 0;
        this.rangestop = 25;
        this.tickmove = 20;
        this.dmove = new int[]{5, -5, 16, -16};
        this.totalWay = 2;
        this.nameTieu = "";
    }

    public Monster(int idTemplate) {
        short[] sArray = new short[7];
        sArray[1] = 45;
        sArray[2] = 90;
        sArray[3] = 135;
        sArray[4] = 180;
        sArray[5] = 225;
        sArray[6] = 270;
        this.angle = sArray;
        this.isv = false;
        this.dyPaintPk = 0;
        this.getMonster_Template = false;
        this.timeGetInfo = mSystem.currentTimeMillis();
        byte[] byArray = new byte[5];
        byArray[0] = -2;
        byArray[1] = -1;
        byArray[3] = 1;
        byArray[4] = 2;
        this.sp = byArray;
        this.timevang = 0;
        this.rx = new short[]{-16, 16, 8, -8, 12, -12, 20, -20};
        this.ry = new short[]{-16, 16, 8, -8, 12, -12, 20, -20};
        this.currentFrame = 0;
        this.rangestop = (byte) 25;
        this.tickmove = 20;
        this.dmove = new int[]{5, -5, 16, -16};
        this.totalWay = (byte) 2;
        this.nameTieu = "";
        this.catagory = 1;
        this.idTemplate = (short) idTemplate;
        this._isDie = false;
        this.dir = (byte) r.nextInt(4);
        this.timeLimit = Res.random(10, 20);
        this.state = 0;
        this.timeWait = 0;
        this.p3 = 0;
        this.p2 = 0;
        this.p1 = 0;
        System.out.println("idTemplate= " + idTemplate);
        MonsterTemplate mons = MonsterTemplate.getMonsterTemplate(idTemplate);
        this.oldname = this.name = mons.name;
        this.hp = 100;
        this.canmove = true;
        this.canpaint = true;
        this.speed = mons.speed;
        if (!Tilemap.isMapIntro()) {
            this.typeattack = mons.typeMove;
            this.idEffect = mons.idEff;
            this.idShadow = mons.idShadow;
        } else {
            this.setSkillFire(mons.idImg);
            this.idShadow = 0;
        }
        if (this.typeattack == 5 || this.typeattack == 4) {
            this.canmove = false;
            this.speed = 0;
        }
    }

    private void setSkillFire(int idTem) {
        switch (idTem) {
            case 0:
                this.typeattack = 2;
                this.idEffect = 26;
                return;
            case 3:
                this.typeattack = 2;
                this.idEffect = 7;
                return;
            case 6:
                this.typeattack = 2;
                this.idEffect = 27;
                return;
            case 7:
                this.typeattack = 2;
                this.idEffect = 25;
                return;
            case 18:
                this.typeattack = 2;
                this.idEffect = 2;
                return;
            case 20:
                this.typeattack = 2;
                this.idEffect = 4;
                return;
            case 23:
                this.typeattack = 2;
                this.idEffect = 25;
                return;
            case 24:
                this.typeattack = 2;
                this.idEffect = 21;
                return;
            case 25:
                this.typeattack = 2;
                this.idEffect = 0;
                return;
            case 27:
                this.typeattack = 2;
                this.idEffect = 27;
                return;
            case 28:
                this.typeattack = 2;
                this.idEffect = 5;
                return;
            case 29:
                this.typeattack = 2;
                this.idEffect = 17;
                return;
            case 30:
                this.typeattack = 2;
                this.idEffect = 9;
                return;
            case 32:
                this.typeattack = 2;
                this.idEffect = 7;
                return;
            case 36:
                this.typeattack = 2;
                this.idEffect = 24;
                return;
            case 38:
                this.typeattack = 2;
                this.idEffect = 20;
                return;
            case 42:
                this.typeattack = 2;
                this.idEffect = 11;
                return;
            case 45:
                this.typeattack = 2;
                this.idEffect = 1;
                return;
            case 77:
                this.typeattack = 2;
                this.idEffect = 31;
                return;
            case 72:
                this.typeattack = 2;
                this.idEffect = -1;
                return;
            case 1:
            case 11:
            case 15:
            case 16:
            case 33:
                this.typeattack = 0;
                return;
            case 2:
            case 4:
            case 5:
            case 8:
            case 9:
            case 10:
            case 12:
            case 13:
            case 14:
            case 17:
            case 19:
            case 21:
            case 22:
            case 26:
            case 31:
            case 34:
            case 35:
            case 37:
            case 39:
            case 40:
            case 41:
            case 43:
            case 44:
                this.typeattack = 1;
                return;
        }
        this.typeattack = 2;
        this.idEffect = 26;
    }

    public void setMove2Target(Actor target) {
        this.target = target;
        this.state = 4;
        this.p1 = 0;
        this.timeStand = 0;
        if (!this.isbossOffline && this.timeDelay - mSystem.currentTimeMillis() < 0L)
            this.timeDelay = mSystem.currentTimeMillis() + 1500L;
    }

    public void DropHP(int hp) {
        this.hp -= hp;
    }

    public void setRealHP(int realHP) {
        this.realHP = realHP;
        this.hp = this.realHP;
        this.realHPSyncTime = 20;
    }

    public void startAttack() {
        if (this.state == 12) return;
        if (this.typeattack == 1) {
            this.state = 11;
            this.savex = this.x;
            this.savey = this.y;
        } else if (this.typeattack == 0) {
            this.state = 6;
            this.xTarget = (short) (this.target.x + ((Res.random(10) < 5) ? 16 : -16));
            this.yTarget = (short) (this.target.y + ((Res.random(10) < 5) ? 16 : -16));
        } else {
            this.state = 6;
            this.xTarget = (short) (this.target.x + ((Res.random(10) < 5) ? 40 : -40));
            this.yTarget = (short) (this.target.y + ((Res.random(10) < 5) ? 40 : -40));
        }
        if (this.timeLive == -1) this.state = 3;
        this.p1 = this.p2 = this.p3 = 0;
        this.countAttack = 0;
        this.countAttack = 0;
    }

    public void startAttack(int idSkill) {
        this.idSkill = (byte) idSkill;
        this.canpaint = true;
        if (this.state == 12) return;
        this.currentFrame = 0;
        if (this.timeLive > 0 || this.timeLive == -2) if (this.typeattack == 1) {
            this.savex = this.x;
            this.savey = this.y;
            this.state = 11;
        } else if (this.typeattack == 0) {
            this.state = 6;
            this.xTarget = (short) (this.target.x + ((Res.random(10) < 5) ? 16 : -16));
            this.yTarget = (short) (this.target.y + ((Res.random(10) < 5) ? 16 : -16));
        } else {
            this.state = 3;
        }
        if (this.timeLive == -1) {
            this.state = 3;
            this.idEffect = (short) idSkill;
        }
        this.p1 = this.p2 = this.p3 = 0;
        this.countAttack = 0;
    }

    public short getIDSkillBoss() {
        return this.idSkill;
    }

    public void vang() {
        if (!Tilemap.tileTypeAtPixel(this.x - this.vxx, this.y - this.vyy, 2) && !this.isv) {
            this.x = (short) (this.x - this.vxx);
            this.y = (short) (this.y - this.vyy);
            this.vxx--;
            this.vyy--;
        } else {
            this.x = (short) (this.x + this.vxx / 2);
            this.y = (short) (this.y + this.vyy / 2);
            this.isv = true;
        }
        if (this.target != null && Utils.getDistance(this.x, this.y, this.target.x, this.target.y) > 36) {
            this.vxx = 0;
            this.vyy = 0;
            this.timevang = 0;
        }
    }

    public boolean isFly() {
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get(this.idTemplate);
        if (mons1 != null) data = mons1.getDataMonster();
        DataEffect deff = (DataEffect) data.elementAt(0);
        if (deff != null && deff.isFly <= -5) return true;
        return false;
    }

    public int getStartPointPaintFly() {
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get(this.idTemplate);
        if (mons1 != null) data = mons1.getDataMonster();
        DataEffect deff = (DataEffect) data.elementAt(0);
        if (deff != null && deff.isFly <= -5) return deff.isFly;
        return 0;
    }

    public void paint(mGraphics g) {
        if (this.state == 8 && this.tickDie <= 0) return;
        if (!this.canpaint) return;
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get(this.idTemplate);
        if (mons1 != null) data = mons1.getDataMonster();
        if (data.size() == 0) return;
        try {
            paintIconPK(g);
            DataEffect deff = (DataEffect) data.elementAt(0);
            byte idShadow = 0;
            if (deff != null) {
                idShadow = deff.idShadow;
                if (GameCanvas.gameScr.focusedActor != null && equals(GameCanvas.gameScr.focusedActor))
                    g.drawRegion(GameScr.imgtinhanh, 0, this.f * 14, 36, 14, 0, this.x, this.y + 7, 3, false);
                paintBottomDataEff(g, getStartPointPaintFly());
                MonsterTemplate mons = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get(this.idTemplate);
                if (mons != null) {
                    if (this.width == 0 || this.height == 0) {
                        this.width = deff.getWith();
                        this.height = deff.getHeight();
                    }
                    int status = this.state;
                    if (this.state == 9 && this.isStand) status = 0;
                    if (idShadow > -1) {
                        byte[] dxdyshadow = deff.getDxDyShadow(deff.getFrame(this.frame, status, this.huongY));
                        if (status == 3 && mons.fAttack != null && mons.fAttack.length > 0)
                            dxdyshadow = deff.getDxDyShadow(this.frame);
                        if (isFly()) {
                            if (canPaint()) {
                                int xsd = this.x + this.vangx + dxdyshadow[0];
                                int ysd = this.y - this._jum + this.vangy + this.vyStyleDie + dxdyshadow[1];
                                g.drawImage(GameScr.imgShadow[idShadow], xsd, ysd, 0, false);
                            }
                        } else if (!this.isWater && canPaint()) {
                            int xsd = this.x + this.vangx + dxdyshadow[0];
                            int ysd = this.y - this._jum + this.vangy + this.vyStyleDie + dxdyshadow[1];
                            g.drawImage(GameScr.imgShadow[idShadow], xsd, ysd, 0, false);
                        }
                    }
                    if (canPaint()) if (status == 3 && mons.fAttack != null && mons.fAttack.length > 0) {
                        deff.paint(g, this.frame, this.x + this.vangx, this.y - this._jum + this.vangy - this.yFly + this.vyStyleDie, this.flip, mons.getImage(0));
                    } else {
                        deff.paint(g, deff.getFrame(this.frame, status, this.huongY), this.x + this.vangx, this.y - this._jum + this.vangy - this.yFly + this.vyStyleDie, this.flip, mons.getImage(0));
                    }
                }
            }
            paintTopDataEff(g, getStartPointPaintFly());
            if (this.isWater && !isFly()) {
                int ystart = (this.state == 0 || this.dir == 1 || this.dir == 0) ? 0 : 40;
                int xs = 0;
                g.drawRegion(GameScr.imgWater, 0, ystart + GameCanvas.gameTick / 2 % 2 * 20, 44, 20, 0, this.x + xs, this.y, 3, false);
            }
            super.paint(g);
        } catch (Exception e) {
            mSystem.println(" loi ne monster: " + this.ID + " > " + this.idTemplate + " >> " + e.toString());
        }
    }

    public void paintIconPK(mGraphics g) {
        if (!this.nameTieu.equals("")) {
            mFont.tahoma_7_black.drawString(g, this.nameTieu, this.x + 1, this.y + 15 - getHeight() + 15 - getYfly() + getDyWater() + this.dyPaintPk, 2, false);
            mFont.tahoma_7_yellow.drawString(g, this.nameTieu, this.x, this.y + 15 - getHeight() + 15 - getYfly() + getDyWater() + this.dyPaintPk, 2, false);
        }
        if (this.typePK >= 0 && this.typePK <= 6)
            g.drawRegion(GameScr.imgPK, 0, 12 * (this.typePK * 3 + GameCanvas.gameTick / 3 % 3), 12, 12, 0, this.x, this.y + 30 - getHeight() + 15 - getYfly() + getDyWater() + this.dyPaintPk, 3, false);
    }

    public void setType(short type) {
        this.monster_type = type;
        if (Res.monsterTemplates != null && Res.monsterTemplates[type] == null) {
            Res.monsterTemplates[type] = new MonsterTemplate();
            GameService.gI().sendGetMonsterTEmplate(0, this.monster_type, "");
        }
        this.timeCreate = System.currentTimeMillis() + 10000L;
    }

    public void settimevang(short tv) {
        this.timevang = tv;
    }

    public void startAttack(mVector target, int idskill) {
    }

    public void actorDie() {
        this.hp = 0;
        removeAllEff();
        this.state = 1;
    }

    public void comehome() {
        this.xAround = 0;
        this.yAround = 0;
        this.target = null;
        if (this.state == 8 || this.state == 1 || this.state == 5) return;
        if (Utils.getDistance(this.x, this.y, this.xFirst, this.yFirst) <= 50) {
            this.state = 7;
        } else {
            EffectSkill.createHiEfect(this.x, this.y, 81);
            this.x = this.xFirst;
            this.y = this.yFirst;
            this.state = 0;
            EffectSkill.createHiEfect(this.x, this.y, 81);
        }
    }

    public boolean canMove2NextPos(int xl, int xr, int yu, int yd, int dir, Actor target) {
        if (target != null) {
            Actor ac = target;
            if (dir == 2) {
                if (Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yd, 32))
                    return false;
            } else if (dir == 3) {
                if (Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yd, 32))
                    return false;
            } else if (dir == 1) {
                if (Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yu, 32))
                    return false;
            } else if (dir == 0 && (Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yd, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yd, 32))) {
                return false;
            }
        }
        for (int i = 0; i < GameCanvas.gameScr.actors.size(); i++) {
            Actor ac = (Actor) GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null && ac.catagory == 0) if (dir == 2) {
                if (Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yd, 32))
                    return false;
            } else if (dir == 3) {
                if (Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yd, 32))
                    return false;
            } else if (dir == 1) {
                if (Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yu, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yu, 32))
                    return false;
            } else if (dir == 0 && (Res.inRangeActor(ac.x - 16, ac.y - 16, xl, yd, 32) || Res.inRangeActor(ac.x - 16, ac.y - 16, xr, yd, 32))) {
                return false;
            }
        }
        return true;
    }

    public int[] getXYMoveTo(int xto, int yto, int dir, int speed) {
        int xx = 0, yy = 0;
        if (dir == 1) {
            yy = this.y;
            for (int i = this.y; i > yto; i -= speed) {
                if (Tilemap.tileTypeAtPixel(xto, yy - speed, 2)) return new int[]{xto, yy};
                yy -= speed;
            }
            if (yy < yto) yy = yto;
            return new int[]{this.x, yy};
        }
        if (dir == 0) {
            yy = this.y;
            for (int i = this.y; i < yto; i += speed) {
                if (Tilemap.tileTypeAtPixel(xto, yy + speed, 2)) return new int[]{xto, yy};
                yy += speed;
            }
            if (yy > yto) yy = yto;
            return new int[]{this.x, yy};
        }
        if (dir == 2) {
            xx = this.x;
            for (int i = this.x; i > xto; i -= speed) {
                if (Tilemap.tileTypeAtPixel(xx - speed, yto, 2)) return new int[]{xx, yto};
                xx -= speed;
            }
            if (xx < xto) xx = xto;
            return new int[]{xx, this.y};
        }
        if (dir == 3) {
            xx = this.x;
            for (int i = this.x; i < xto; i += speed) {
                if (Tilemap.tileTypeAtPixel(xx + speed, yto, 2)) return new int[]{xx, yto};
                xx += speed;
            }
            if (xx > xto) xx = xto;
            return new int[]{xx, this.y};
        }
        return new int[]{xto, yto};
    }

    public void UpdateAttack() {
        MonsterTemplate mons = getMonsterTemplate();
        if (mons != null && mons.fAttack != null && mons.fAttack.length > 0) {
            byte[] tem = mons.fAttack[this.idSkill][this.huongY];
            this.frame = tem[this.currentFrame];
            this.currentFrame = (byte) (this.currentFrame + 1);
            System.out.println("frame: " + this.frame + " " + this.currentFrame + " " + tem.length);
            if (this.currentFrame > tem.length - 1) {
                this.currentFrame = 0;
                this.frame = 0;
                this.state = 0;
            }
            return;
        }
        this.p1 = (short) (this.p1 + 1);
        if (this.countAttack > 2) {
            this.state = 0;
            return;
        }
        if (this.timeLive < 0 && this.timeLive != -2) {
            if (this.p1 >= 6) {
                EffectSkillMonster skill = new EffectSkillMonster(this.idEffect, this, this.ntarget, this.mDame);
                EffectManager.addHiEffect((Effect) skill);
                this.state = 0;
            }
            if (this.p1 > 6) this.p1 = 0;
        } else {
            if (this.p1 > 6) {
                if ((this.typeattack == 2 || this.typeattack == 3) && this.countAttack == 0) {
                    if (this.ntarget.size() > 0) {
                        for (int i = 0; i < this.ntarget.size(); i++) {
                            Actor ac = (Actor) this.ntarget.elementAt(i);
                            if (ac != null) {
                                int damef = 0;
                                if (GameScr.isIntro) {
                                    damef = Res.random(200, 250);
                                } else {
                                    damef = this.mDame[i];
                                }
                                EffectSkillMonster skill = new EffectSkillMonster(this.typeattack, this.idEffect, this, ac, damef);
                                EffectManager.addHiEffect((Effect) skill);
                                if (i == 0) this.target = ac;
                            }
                        }
                    } else {
                        int damef = 0;
                        if (GameScr.isIntro) {
                            damef = 200;
                        } else {
                            damef = this.mDame[0];
                        }
                        EffectSkillMonster skill = new EffectSkillMonster(this.typeattack, this.idEffect, this, this.target, damef);
                        if (this.idEffect == 21) {
                            EffectManager.addLowEffect((Effect) skill);
                        } else {
                            EffectManager.addHiEffect((Effect) skill);
                        }
                    }
                } else if (this.typeattack == 1) {
                    this.state = 0;
                }
                if (this.timeLive == -1) this.state = 0;
                this.p1 = 0;
                this.countAttack = (byte) (this.countAttack + 1);
            }
            if (this.typeattack == 1 && this.p1 > 5 && this.countAttack == 0) {
                int damef = 0;
                if (GameScr.isIntro) {
                    damef = 200;
                } else {
                    damef = this.mDame[0];
                }
                GameCanvas.gameScr.startFlyText("-" + damef, 2, this.target.x + 0, this.target.y - 35, -1, -2);
                if (this.target != null) this.target.jum();
                EffectSkill.createHiEfect(this.target.x, this.target.y - 35, 30);
            }
            if (this.typeattack == 0 && this.p1 > 5) {
                int damef = 0;
                if (GameScr.isIntro) {
                    damef = 200;
                } else {
                    damef = this.mDame[0];
                }
                if (this.countAttack == 0) {
                    GameCanvas.gameScr.startFlyText("-" + damef, 2, this.target.x + 0, this.target.y - 15, -1, -2);
                    this.target.jum();
                    EffectSkill.createHiEfect(this.target.x, this.target.y - 20, 30);
                }
                if (this.state != 0 && this.countAttack > 2) this.state = 0;
                this.p1 = 0;
                this.countAttack = (byte) (this.countAttack + 1);
            }
        }
    }

    public void doChangeFrameBoss() {
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get(this.idTemplate);
        if (mons1 != null) data = mons1.getDataMonster();
        DataEffect deff = null;
        if (data.size() > 0) {
            deff = (DataEffect) data.elementAt(0);
            int status = this.state;
            if (this.isStand) status = 0;
            this.frame = (byte) ((this.frame + 1) % (deff.getAnim(status, this.huongY)).frame.length);
        }
    }

    public void update() {
        if (this.state != 5 && this.state != 8 && this.state != 1 && !this.canpaint) this.canpaint = true;
        if (GameCanvas.gameTick % 2 == 0) this.f = (this.f + 1) % 3;
        try {
            int xff, yf;
            if (this.tickDie >= 0) this.tickDie--;
            if (this._jum >= 0) this._jum = (short) (this._jum - 5);
            if (this.timevang >= 0) {
                vang();
                this.timevang = (short) (this.timevang - 1);
            } else {
                this.isv = false;
            }
            if (!this.canmove && this.yFly <= 0) this.timeStand++;
            if (GameScr.isIntro && Utils.getDistance(this.saveXfirst, this.saveYfirst, this.x, this.y) > 160 && this.state != 7 && !this.isbossOffline) {
                this.target = null;
                this.state = 7;
            }
            if (this.timeStand > 15 && this.typeattack != 4 && this.typeattack != 5 && this.timeLive != -1) {
                this.timeStand = 0;
                this.canmove = true;
            }
            doChangeFrameBoss();
            if ((!this.isSetInfo || this.hp > this.maxhp) && mSystem.currentTimeMillis() - this.timeGetInfo > 15000L) {
                this.timeGetInfo = mSystem.currentTimeMillis();
                GameCanvas.gameScr.gameService.requestMonsterInfo(this.ID);
            }
            if (Res.monsterTemplates != null && Res.monsterTemplates[this.monster_type] != null)
                if (!this.getMonster_Template) {
                    setType(this.monster_type);
                    this.getMonster_Template = true;
                    this.speed = (Res.monsterTemplates[this.monster_type]).speed;
                    if (Res.monsterTemplates[this.monster_type].getImage(this.monster_type) == null)
                        Res.monsterTemplates[this.monster_type].loadImage();
                }
            if (this.beStune && mSystem.currentTimeMillis() > this.timeBeStune) this.beStune = false;
            if (this.timeLive == -1 && this.state != 3) moveToXY();
            switch (this.state) {
                case 4:
                    this.p1 = (short) (this.p1 + 1);
                    if (this.p1 > 5) {
                        this.p1 = 0;
                        if (this.typeattack != 4 && this.typeattack != 5) this.state = 0;
                    }
                    break;
                case 0:
                    if (this.target != null && this.timeLive != -1 && this.canmove) {
                        if (this.x > this.target.x) {
                            this.dir = 2;
                        } else {
                            this.dir = 3;
                        }
                        setHuong(this.target.x, this.target.y);
                    }
                    if (this.target == null && this.canmove && Utils.getDistance(this, GameScr.mainChar) <= 80)
                        setHuong(GameScr.mainChar.x, GameScr.mainChar.y);
                    if (!this.canmove || this.timeLive == -1) {
                        this.vx = 0;
                        this.vy = 0;
                        break;
                    }
                    this.changSpeed = this.sp[r.nextInt(this.sp.length - 1)];
                    this.timeWait++;
                    if (this.timeWait > this.timeLimit && this.idChar == -1) {
                        this.range = (short) Res.random(16, 32);
                        short xL = (short) (this.xFirst - this.range);
                        short xR = (short) (this.xFirst + this.range);
                        short yU = (short) (this.yFirst - this.range);
                        short yD = (short) (this.yFirst + this.range);
                        if (this.target != null) {
                            xL = (short) ((this.x + this.target.x) / 2 - this.range);
                            xR = (short) ((this.x + this.target.x) / 2 + this.range);
                            yU = (short) ((this.y + this.target.y) / 2 - this.range);
                            yD = (short) ((this.y + this.target.y) / 2 + this.range);
                        }
                        byte mDir = (byte) r.nextInt(4);
                        if (this.lastDir != -100 && this.lastDir == mDir) if (mDir == 2) {
                            mDir = 3;
                        } else if (mDir == 3) {
                            mDir = 2;
                        } else if (mDir == 1) {
                            mDir = 0;
                        } else if (mDir == 0) {
                            mDir = 1;
                        }
                        if (mDir == 2) {
                            this.mtoX = xL;
                            this.mtoY = this.y;
                        } else if (mDir == 3) {
                            this.mtoX = xR;
                            this.mtoY = this.y;
                        } else if (mDir == 1) {
                            this.mtoX = this.x;
                            this.mtoY = (short) (yU - 15);
                        } else if (mDir == 0) {
                            this.mtoX = this.x;
                            this.mtoY = yD;
                        }
                        int[] posto = getXYMoveTo(this.mtoX, this.mtoY, mDir, this.speed);
                        this.mtoX = (short) posto[0];
                        this.mtoY = (short) posto[1];
                        if (checkmoveChar(this.mtoX, this.mtoY) && !Tilemap.tileTypeAtPixel(this.x, this.y, 2)) {
                            this.dir = mDir;
                            this.state = 2;
                            this.timeWait = 0;
                            this.lastDir = mDir;
                        }
                        if (Tilemap.tileTypeAtPixel(this.x, this.y, 2)) this.state = 7;
                    }
                    break;
                case 3:
                    UpdateAttack();
                    break;
                case 5:
                    this.frame = 3;
                    this.p1 = (short) (this.p1 + 1);
                    if (!this.isMineral) {
                        this.x = (short) (this.x + this.p2);
                        this.y = (short) (this.y + this.p3);
                    }
                    this.p2 = (short) (this.p2 >> 1);
                    this.p3 = (short) (this.p3 >> 1);
                    setDie();
                    break;
                case 8:
                    if (GameCanvas.gameScr.focusedActor != null && GameCanvas.gameScr.focusedActor == this && GameScr.mainChar.state == 0)
                        GameCanvas.gameScr.focusedActor = null;
                    if ((this.timeLive == -1 || this.timeLive == -2) && !Tilemap.isMapIntro()) {
                        this.wantDestroy = true;
                        return;
                    }
                    if (!this.canrevives) {
                        this.wantDestroy = true;
                        return;
                    }
                    if (this.timeRevive - mSystem.currentTimeMillis() < 0L && this.canrevives) Revive();
                    break;
                case 6:
                    if (this.target != null) movetoTarget(this.xTarget, this.yTarget);
                    break;
                case 2:
                case 7:
                    if (!this.canmove) {
                        this.vx = 0;
                        this.vy = 0;
                        break;
                    }
                    this.p1 = (short) (this.p1 + 1);
                    if (this.p1 > 6) this.p1 = 0;
                    if (!this.isFreeze) {
                        if (this.idChar > -1) {
                            move();
                            break;
                        }
                        if (this.state == 7) {
                            setMove(this.xFirst, this.yFirst);
                            break;
                        }
                        if (this.state == 2) move();
                    }
                    break;
                case 11:
                    EffectSkill.createHiEfect(this.x, this.y, 81);
                    xff = 16;
                    yf = -16;
                    if (GameCanvas.gameTick % 2 == 0) {
                        xff *= -1;
                        yf *= -1;
                    }
                    this.x = (short) (this.target.x + xff);
                    this.y = (short) (this.target.y + yf);
                    this.state = 3;
                    break;
                case 12:
                    EffectSkill.createHiEfect(this.x, this.y, 81);
                    this.x = this.savex;
                    this.y = this.savey;
                    this.state = 0;
                    break;
            }
            if (this.state != 8 && this.state != 1 && this.state != 5) {
                int xv = this.x + this.dvangx;
                int yv = this.y + this.dvangy;
                int dxvangcu = this.dvangx;
                int dyvangcu = this.dvangy;
                if (!Tilemap.tileTypeAtPixel(xv, yv, 2)) {
                    this.x = (short) (this.x + this.dvangx);
                    this.y = (short) (this.y + this.dvangy);
                } else {
                    this.dvangx = 0;
                    this.dvangy = 0;
                }
                if (this.dvangx > 0) this.dvangx--;
                if (this.dvangx < 0) this.dvangx++;
                if (this.dvangy > 0) this.dvangy--;
                if (this.dvangy < 0) this.dvangy++;
                if (this.dvangx == 0 && this.dvangy == 0 && (dxvangcu != 0 || dyvangcu != 0))
                    moveTo(this.saveXvang, this.saveYvang);
            }
            updateDataEff();
            if (GameScr.isIntro && this.hp <= 0 && this.state != 5 && this.state != 1 && this.state != 8) die();
        } catch (Exception exception) {
        }
        super.update();
    }

    private void move() {
        if (!mcanmove()) return;
        if (this.timeLive == -1) return;
        if (!this.canmove) return;
        movetoXY(this.mtoX, this.mtoY);
    }

    private void moveAroundTarget() {
    }

    public MonsterTemplate getMonsterTemplate() {
        return (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get(this.idTemplate);
    }

    public void setInfo(MonsterInfo monsterInfo) {
        this.timeLive = monsterInfo.timeLive;
        this.xFirst = monsterInfo.default_x;
        this.yFirst = monsterInfo.default_y;
        this.saveXfirst = monsterInfo.default_x;
        this.saveYfirst = monsterInfo.default_y;
        this.x = monsterInfo.x;
        this.y = monsterInfo.y;
        this.rangestop = (byte) (r.nextInt(10) + 6);
        this.maxhp = this.hp = monsterInfo.hp;
        this.he = monsterInfo.he;
        this.p1 = 0;
        this.p2 = 0;
        this.p3 = 0;
        this.level = (byte) monsterInfo.lv;
        this.isSetInfo = true;
        this.x = monsterInfo.default_x;
        this.y = monsterInfo.default_y;
        this.xTo = this.x;
        this.yTo = this.y;
        setCanFocus(monsterInfo.canFocus);
        this.colorName = monsterInfo.colorName;
        this._beFire = monsterInfo.beFire;
        if (this.colorName == 3) this.name = String.valueOf(this.oldname) + " " + T.tinhanh;
        if (this.colorName == 1) this.name = String.valueOf(this.oldname) + " " + T.thulinh;
        if (this.hp <= 0) {
            this.hp = 0;
            this.state = 8;
            this.timeRevive = System.currentTimeMillis() + this.timeLive;
            this._isDie = true;
        }
        this.totalWay = monsterInfo.totalWay;
        this.dyPaintPk = monsterInfo.dyPaintPk;
        this.nameTieu = monsterInfo.nameTieu;
    }

    public void setMove(int i, int j) {
        boolean isMatchX = false, isMatchY = false;
        int dx1 = Math.abs(this.x - i);
        int dy1 = Math.abs(this.y - j);
        if (dx1 <= this.speed) {
            this.x = (short) i;
            isMatchX = true;
        }
        if (dy1 < this.speed) {
            this.y = (short) j;
            isMatchY = true;
        }
        if ((isMatchX && isMatchY) || !checkmoveChar(i, j)) {
            this.p1 = this.p2 = this.p3 = 0;
            this.state = 0;
            this.timeLimit = Res.random(20, 30);
            this.vx = this.vy = 0;
        } else if (this.x < i) {
            this.x = (short) (this.x + this.speed);
            this.dir = 3;
        } else if (this.x > i) {
            this.x = (short) (this.x - this.speed);
            this.dir = 2;
        } else if (this.y > j) {
            this.y = (short) (this.y - this.speed);
            this.dir = 1;
        } else if (this.y < j) {
            this.dir = 0;
            this.y = (short) (this.y + this.speed);
        }
        setHuong(i, j);
    }

    public void movetoXY(int i, int j) {
        if (!mcanmove()) return;
        if (i == 0 || j == 0) {
            this.state = 0;
            return;
        }
        if (this.timeLive == -1) return;
        boolean isMatchX = false, isMatchY = false;
        int dx1 = Math.abs(this.x - i);
        int dy1 = Math.abs(this.y - j);
        if (dx1 <= this.speed) {
            this.x = (short) i;
            isMatchX = true;
        }
        if (dy1 < this.speed) {
            this.y = (short) j;
            isMatchY = true;
        }
        if (Tilemap.tileTypeAtPixel(this.x, this.y, 2) && !isFly()) {
            EffectSkill.createHiEfect(this.x, this.y, 81);
            int xff = 16, yf = -16;
            if (GameCanvas.gameTick % 2 == 0) {
                xff *= -1;
                yf *= -1;
            }
            this.x = (short) (i + xff);
            this.y = (short) (j + yf);
            isMatchX = true;
            isMatchY = true;
        }
        if (isMatchX && isMatchY) {
            this.p1 = this.p2 = this.p3 = 0;
            this.state = 0;
            this.vx = this.vy = 0;
            if (this.target != null) if (this.x > this.target.x) {
                this.dir = 2;
            } else {
                this.dir = 3;
            }
            this.timeLimit = Res.random(50, 70);
            if (this.target != null) this.dir = Util.findDirection(this, this.target);
        } else if (this.x < i) {
            this.x = (short) (this.x + this.speed);
            this.dir = 3;
        } else if (this.x > i) {
            this.x = (short) (this.x - this.speed);
            this.dir = 2;
        } else if (this.y > j) {
            this.y = (short) (this.y - this.speed);
            this.dir = 1;
        } else if (this.y < j) {
            this.dir = 0;
            this.y = (short) (this.y + this.speed);
        }
        setHuong(i, j);
    }

    public void movetoTarget(int i, int j) {
        if (!mcanmove()) return;
        if (this.timeLive == -1) return;
        boolean isMatchX = false, isMatchY = false;
        int dx1 = Math.abs(this.x - i);
        int dy1 = Math.abs(this.y - j);
        if (dx1 <= this.speed * 2) {
            this.x = (short) i;
            isMatchX = true;
        }
        if (dy1 < this.speed * 2) {
            this.y = (short) j;
            isMatchY = true;
        }
        if ((Tilemap.tileTypeAtPixel(this.x, this.y, 2) && !isFly()) || ((dx1 >= 80 || dy1 >= 80) && this.typeattack == 0)) {
            EffectSkill.createHiEfect(this.x, this.y, 81);
            int xff = 16, yf = -16;
            if (GameCanvas.gameTick % 2 == 0) {
                xff *= -1;
                yf *= -1;
            }
            this.x = (short) (i + xff);
            this.y = (short) (j + yf);
            isMatchX = true;
            isMatchY = true;
        }
        if ((this.typeattack == 2 || this.typeattack == 3) && (dx1 <= 70 || dy1 <= 70)) {
            isMatchX = true;
            isMatchY = true;
        }
        if ((dx1 <= 10 && dy1 <= 10) || (isMatchX && isMatchY)) {
            if (this.x > this.target.x) {
                this.dir = 2;
            } else {
                this.dir = 3;
            }
            this.p1 = this.p2 = this.p3 = 0;
            this.state = 3;
            this.frame = 0;
            this.vx = this.vy = 0;
            if (this.target != null) this.dir = Util.findDirection(this, this.target);
            this.lastDir = this.dir;
        } else if (this.x < i) {
            this.x = (short) (this.x + this.speed);
            this.dir = 3;
        } else if (this.x > i) {
            this.x = (short) (this.x - this.speed);
            this.dir = 2;
        } else if (this.y > j) {
            this.y = (short) (this.y - this.speed);
            this.dir = 1;
        } else if (this.y < j) {
            this.dir = 0;
            this.y = (short) (this.y + this.speed);
        }
        setHuong(i, j);
    }

    public void moveTo(short xTo, short yTo) {
        if (this.state == 8 || this.state == 1 || this.state == 5) return;
        if (this.state == 3) return;
        if (this.xTo == xTo && this.yTo == yTo) {
            this.state = 0;
            return;
        }
        if (!mcanmove()) return;
        this.xTo = xTo;
        this.yTo = yTo;
        this.state = 2;
    }

    public void moveToXY() {
        if (!mcanmove()) return;
        if (this.xTo == this.x && this.yTo == this.y) {
            this.state = 0;
            return;
        }
        boolean isMatchX = false, isMatchY = false;
        int dx1 = Math.abs(this.x - this.xTo);
        int dy1 = Math.abs(this.y - this.yTo);
        if (dx1 <= this.speed) {
            this.x = this.xTo;
            isMatchX = true;
        }
        if (dy1 < this.speed) {
            this.y = this.yTo;
            isMatchY = true;
        }
        if (Tilemap.tileTypeAtPixel(this.x, this.y, 2)) {
            EffectSkill.createHiEfect(this.x, this.y, 81);
            this.x = this.xTo;
            this.y = this.yTo;
            isMatchX = true;
            isMatchY = true;
            this.state = 0;
        }
        int disrang = Utils.getDistance(this.x, this.y, this.xTo, this.yTo);
        if ((isMatchX && isMatchY) || disrang <= 25) {
            this.p1 = this.p2 = this.p3 = 0;
            this.state = 0;
            this.vx = this.vy = 0;
        } else if (this.x < this.xTo) {
            this.x = (short) (this.x + this.speed);
            this.dir = 3;
        } else if (this.x > this.xTo) {
            this.x = (short) (this.x - this.speed);
            this.dir = 2;
        } else if (this.y > this.yTo) {
            this.y = (short) (this.y - this.speed);
            this.dir = 1;
        } else if (this.y < this.yTo) {
            this.dir = 0;
            this.y = (short) (this.y + this.speed);
        }
        setHuong(this.xTo, this.yTo);
    }

    public void setHuong(int xto, int yto) {
        this.huongY = (byte) ((this.y > yto) ? 1 : 0);
        this.flip = (byte) ((this.x - xto > 0) ? 0 : 2);
        this.flip = 0;
        if (this.dir == 3) {
            this.flip = 2;
            if (this.totalWay == 3) this.huongY = 2;
        } else if (this.dir == 2) {
            if (this.totalWay == 3) this.huongY = 2;
        } else if (this.dir == 0) {
            this.huongY = 0;
        } else if (this.dir == 1) {
            this.huongY = 1;
        }
    }

    public boolean isMonster() {
        return true;
    }

    public void setPosition(int x, int y) {
        this.x = (short) x;
        this.y = (short) y;
    }

    public void addEffectWhenActorDie(int ideffect) {
    }

    public boolean inCamera(int cmx, int cmy) {
        return (this.x + 50 >= cmx && this.x - 50 <= cmx + GameCanvas.w && this.y + 50 >= cmy && this.y - 50 <= cmy + GameCanvas.h);
    }

    public int getState() {
        return this.state;
    }

    public void setMove(boolean canmove) {
        this.canmove = canmove;
        this.timeStand = 0;
    }

    public String getName() {
        return this.name;
    }

    public void setCanrevives(boolean canrevives) {
        this.canrevives = canrevives;
    }

    public void setmuntiTarget(mVector target) {
        if (this.hp <= 0 || this.state == 1 || this.state == 5 || this.state == 8) Revive();
        this.ntarget = target;
        Actor ac = (Actor) this.ntarget.elementAt(0);
        if (ac != null) this.target = ac;
        this.dir = Util.findDirection(this, this.target);
        setHuong(this.target.x, this.target.y);
        if (this.timeLive > 0) {
            this.state = 6;
            this.xTarget = (short) (this.target.x + ((Res.random(10) < 5) ? 16 : -16));
            this.yTarget = (short) (this.target.y + ((Res.random(10) < 5) ? 16 : -16));
        }
    }

    public void setDie() {
        if (this.tickDie > 0) return;
        removeAllEff();
        if (this.typeDie == 0) {
            this.countDie = (byte) (this.countDie - 1);
            if (this.countDie > 0) if (this.countDie % 2 == 0) {
                this.canpaint = true;
            } else {
                this.canpaint = false;
            }
            if (this.countDie <= 0 && this.timeLive != -1 && this.state != 8) {
                this.state = 8;
                this.timeRevive = System.currentTimeMillis() + this.timeLive;
                EffectSkill.createHiEfect(this.x, this.y, 81);
                this._isDie = true;
            }
        } else if (this.typeDie > 0) {
            this.frameDie++;
            if (!this._isDie) {
                this.x = (short) (this.x + this.vxDie);
                this.y = (short) (this.y + this.vyDie);
                if (this.frameDie >= this.timeBienmat) {
                    this.countDie = (byte) (this.countDie - 1);
                    if (this.countDie > 0) if (this.countDie % 2 == 0) {
                        this.canpaint = true;
                    } else {
                        this.canpaint = false;
                    }
                    if (this.countDie <= 0 && this.timeLive != -1 && this.state != 8) {
                        this.state = 8;
                        this.timeRevive = System.currentTimeMillis() + this.timeLive;
                        EffectSkill.createHiEfect(this.x, this.y, 81);
                        this._isDie = true;
                    }
                }
            }
        }
    }

    private void die() {
        this._isDie = false;
        this.countDie = 0;
        this.vx = 0;
        this.vy = 0;
        this.p1 = 0;
        if (this.state != 8) {
            this.state = 8;
            this.timeRevive = System.currentTimeMillis() + this.timeLive;
            this._isDie = true;
        }
    }

    public void resetXY() {
        this.xTo = this.x;
        this.yTo = this.y;
        this.vx = 0;
        this.vy = 0;
        this.vx = 0;
        this.vy = 0;
        this.p1 = 0;
        this.vxDie = this.dx;
        this.vyDie = this.dy;
        this.frameDie = 0;
        this._isDie = false;
        this.countDie = 0;
        this.canpaint = true;
        this.vangx = 0;
        this.vangy = 0;
    }

    public void setFlyDame(int[] dame) {
        this.mDame = new int[dame.length];
        for (int i = 0; i < this.mDame.length; i++)
            this.mDame[i] = dame[i];
    }

    public void jumpVang(Actor causeBy) {
        if (this.isbossOffline) return;
        if (this.typeattack == 5 || this.typeattack == 4 || !this.canmove || !mcanmove()) return;
        if (causeBy == null) return;
        if (this.state == 6) return;
        if (Utils.getDistance(this.xFirst, this.yFirst, this.x, this.y) > 80) return;
        this.saveXvang = this.x;
        this.saveYvang = this.y;
        this.z = -3;
        this.dz = -3;
        this.state = 4;
        this.dvangx = (short) ((this.x - causeBy.x) * 3);
        this.dvangy = (short) ((this.y - causeBy.y) * 3);
        while (this.dvangx > 4 || this.dvangy > 4 || this.dvangx < -4 || this.dvangy < -4) {
            this.dvangx >>= 1;
            this.dvangy >>= 1;
        }
        if (GameScr.isIntro) {
            if (causeBy == GameScr.mainChar) {
                int value = 4;
                if (this.dvangx > 0) this.dvangx += value;
                if (this.dvangx < 0) this.dvangx -= value;
                if (this.dvangy > 0) this.dvangy += value;
                if (this.dvangy < 0) this.dvangy -= value;
            } else {
                this.dvangx = 0;
                this.dvangy = 0;
            }
        } else if (causeBy == GameScr.mainChar) {
            if (Res.inRangeActor(this, GameScr.mainChar, 32)) {
                int value = 4;
                if (this.dvangx > 0) this.dvangx += value;
                if (this.dvangx < 0) this.dvangx -= value;
                if (this.dvangy > 0) this.dvangy += value;
                if (this.dvangy < 0) this.dvangy -= value;
            }
        } else {
            int value = 4;
            if (this.dvangx > 0) this.dvangx += value;
            if (this.dvangx < 0) this.dvangx -= value;
            if (this.dvangy > 0) this.dvangy += value;
            if (this.dvangy < 0) this.dvangy -= value;
        }
    }

    public byte getColorName() {
        return this.colorName;
    }

    public boolean mcanmove() {
        if (!this.canmove) return false;
        return super.mcanmove();
    }

    public void Revive() {
        removeAllEff();
        this.wantDestroy = false;
        this._isDie = false;
        this.canpaint = true;
        this.tickDie = -1;
        this.dir = (byte) r.nextInt(4);
        this.timeLimit = Res.random(10, 20);
        this.state = 0;
        this.timeWait = 0;
        this.timeRevive = 0L;
        this.p1 = this.p2 = this.p3 = 0;
        this.hp = this.maxhp;
        this.vyStyleDie = 0;
        this.vyStyleStand = 0;
        this.vyDie = 0;
        this.vxDie = 0;
        this.frameDie = 0;
        resetXY();
        this.x = this.xTo = this.xFirst;
        this.y = this.yTo = this.yFirst;
        this.target = null;
    }

    public void dieActor(Actor from) {
        removeAllEff();
        this.hp = 0;
        if (this.idTemplate == 88) {
            GameScr.Ghost = null;
            GameScr.isGost = false;
            GameCanvas.gameScr.actors.removeElement(this);
            GameCanvas.gameScr.focusedActor = null;
            return;
        }
        if (GameCanvas.gameScr.focusedActor != null && GameCanvas.gameScr.focusedActor.equals(this))
            GameCanvas.gameScr.focusedActor = null;
        if (this.timeLive == -1 || this.timeLive == -2) {
            this.wantDestroy = true;
            return;
        }
        if (from == null) {
            this._isDie = true;
            EffectSkill.createHiEfect(this.x, this.y, 81);
            this.state = 8;
            this.timeRevive = mSystem.currentTimeMillis() + this.timeLive;
        } else {
            this.typeDie = (byte) Res.random(3);
            this.state = 5;
            if (this.typeDie == 0) {
                this.countDie = 10;
            } else if (this.typeDie > 0) {
                this._isDie = false;
                this.frameDie = 0;
                this.vxDie = (this.x - from.x) * 2;
                this.vyDie = (this.y - from.y) * 2;
                this.vxDie = this.vxDie * 2 / 3;
                this.vyDie = this.vyDie * 2 / 3;
                this.vyDie = this.dy;
                int fdx = this.x - from.x;
                int fdy = this.y - from.y;
                int angle = Util.angle(fdx, fdy);
                int vvv = Res.random(5, 8);
                this.vxDie = vvv * Util.cos(angle) >> 10;
                this.vyDie = vvv * Util.sin(angle) >> 10;
                int vs = Res.random(3, 6);
                this.vyStyleDie = vs;
                this.vyStyleStand = vs;
                this.countDie = 10;
                this.timeBienmat = 5;
            }
        }
        if (GameCanvas.gameScr.focusedActor != null && GameCanvas.gameScr.focusedActor.equals(this))
            GameCanvas.gameScr.focusedActor = null;
    }

    public int getHeight() {
        return this.height;
    }

    public void setTickDie(int value) {
        this.hp = 0;
        if (this.timeLive == -1 || this.timeLive == -2) this.wantDestroy = true;
        this.tickDie = value;
    }

    public boolean checkmoveChar(int mx, int my) {
        for (int i = 0; i < GameCanvas.gameScr.actors.size(); i++) {
            Actor ac = (Actor) GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null && ac.catagory == 0 && Res.inRangeActor(ac.x - 16, ac.y - 16, mx, my, 32)) return false;
        }
        return true;
    }

    public boolean canFocusMonster() {
        return (this.state != 1 && this.state != 5 && this.state != 8);
    }

    public int getWidth() {
        return this.width;
    }

    public int getYmonster() {
        return (this.target != null) ? this.y : this.yFirst;
    }

    public int getXmonster() {
        return (this.target != null) ? this.x : this.xFirst;
    }

    public boolean isBoss() {
        return false;
    }

    public int getHeNguHanh() {
        return this.he;
    }
}

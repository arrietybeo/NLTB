package code.model;

import code.main.GameCanvas;
import code.screen.screen.GameScr;
import lib.Tilemap;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class LiveActor extends Actor {
    public short poinson;

    public long timeOutPoinson;

    public short tDelay = 0;

    public boolean die;

    public short _jum = 0;

    public short vjum;

    public short vfail;

    public String infoGround;

    public short centerX;

    public short centerY;

    public short killer;

    public int timeLive = 1000;

    public int z;

    public int dz;

    public int dvangx;

    public int dvangy;

    public int vangx;

    public int vangy;

    public int dyWater;

    public short idClan = -1;

    public long timeBeStune;

    public boolean _isDie = false;

    public byte he;

    public byte frame;

    public short idBot = -1;

    public String name = "";

    public int realHP;

    public int realHPSyncTime;

    public short level;

    public short lastLv;

    public short xBegin;

    public short yBegin;

    public short height;

    public short width;

    public short xFirst;

    public short yFirst;

    public short yFly;

    public short saveXDie;

    public short saveYdie;

    public short dy;

    public short speed;

    public boolean isWearing = false;

    public boolean isMaininfo = false;

    public boolean beStune;

    public boolean isFocus;

    public boolean iskiller;

    public byte pk = -1;

    public byte nationID = -1;

    public byte inCountry = -1;

    public byte typeLimit;

    public byte state;

    public short dir;

    public boolean isjum;

    public boolean canmove;

    public int hp;

    public int mp;

    public int maxhp;

    public int maxmp;

    public int dame;

    private long timePaintinfo;

    public boolean _canFocus = true;

    public byte typePK = -1;

    public byte colorName;

    public boolean isWater;

    public static final byte TYPE_PK_DO_SAT = 0;

    private mVector DataEff = new mVector();

    private mVector vecEFfect_Skill = new mVector();

    public boolean _beFire;

    int mjum;

    int range;

    public void update() {
        if (this.chat != null) {
            this.chat.setPos(this.x, this.y - this.height);
            if (this.chat.setOut())
                this.chat = null;
        }
        if (this.isjum) {
            this._jum = (short) (this._jum + this.vjum);
            this.vjum = (short) (this.vjum + this.mjum);
            if (this._jum > this.range) {
                this._jum = (short) this.range;
                this.vjum = (short) -this.vfail;
                this.mjum = (short) (-1 * (this.vfail - 2));
            }
            if (this._jum <= 0) {
                this._jum = 0;
                this.isjum = false;
            }
        }
        if (Tilemap.isTileWater(this.x, this.y)) {
            this.isWater = true;
            this.dyWater = 6;
        } else {
            this.isWater = false;
            this.dyWater = 0;
        }
        updateDataEff();
    }

    public void setRealHP(int realHP) {
        this.realHP = realHP;
        this.realHPSyncTime = 20;
    }

    public void paintName(mGraphics g) {
        if (this.chat != null)
            this.chat.paintAnimal(g);
    }

    public void removechat() {
        this.chat = null;
    }

    public void paint(mGraphics g) {
        paintInfoFocus(g);
    }

    public void setDame(int dame) {
        this.dame = dame;
    }

    public int getDame() {
        return this.dame;
    }

    public void paintInfoFocus(mGraphics g) {
        if (this.timePaintinfo - mSystem.currentTimeMillis() > 0L) {
            int w = this.width - this.width / 2;
            g.setColor(0);
            int dx = 0;
            if (this.dir == 3)
                dx = -5;
            if (this.dir == 1)
                dx = 2;
            if (this.dir == 2)
                dx = 3;
            if (this.dir == 0)
                dx = 3;
            g.fillRect(this.x - w / 2 + dx, this.y - getHeight() + 10 - getYfly(), w, 3, false);
            int wpPaint = 0;
            long tile = w;
            long objFocushp = this.hp;
            long maxHp = tile * objFocushp;
            wpPaint = (int) (maxHp / this.maxhp);
            if (wpPaint <= 0) {
                wpPaint = 1;
            } else if (wpPaint > w) {
                wpPaint = w;
            }
            int color = GameScr.getColorFocus(GameScr.mainChar.level, this.level, this.catagory);
            g.setColor(color);
            g.fillRect(this.x - w / 2 + 1 + dx, this.y - this.height + 10 + 1 - getYfly(), wpPaint - 2, 1, false);
            mFont.tahoma_7_yellow.drawString(g, this.name, this.x, this.y + 10 - this.height + 15 - getYfly(), 3, false);
        }
    }

    public int getX() {
        return this.x;
    }

    public int getDy() {
        return this.dy;
    }

    public int getDir() {
        return this.dir;
    }

    public int getTimeLive() {
        return this.timeLive;
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public boolean isDie() {
        return this._isDie;
    }

    public void setDie() {
        this._isDie = true;
    }

    public void setIsFocus(boolean isFocus) {
        this.isFocus = isFocus;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int state) {
        this.state = (byte) state;
    }

    public boolean isKiller() {
        return this.iskiller;
    }

    public int getIdClan() {
        return this.idClan;
    }

    public byte getNation() {
        return this.nationID;
    }

    public byte getPk() {
        return this.pk;
    }

    public int getRealHp() {
        return this.realHP;
    }

    public int getHp() {
        return this.hp;
    }

    public int getMp() {
        return this.mp;
    }

    public int getMaxHp() {
        return this.maxhp;
    }

    public int getMaxMp() {
        return this.maxmp;
    }

    public byte getTypeLimit() {
        return this.typeLimit;
    }

    public int getIdBoss() {
        return this.idBot;
    }

    public int getLevel() {
        return this.level;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setKiller(int killer) {
        this.killer = (short) killer;
    }

    public void setYfly(int fly) {
        this.yFly = (short) fly;
    }

    public int getYfly() {
        return this.yFly;
    }

    public void SetPaintFocus() {
        this.timePaintinfo = mSystem.currentTimeMillis() + 3000L;
    }

    public void jum() {
        this.isjum = true;
        this._jum = 0;
        this.vjum = 10;
        this.mjum = 5;
        this.range = 10;
        this.vfail = 5;
    }

    public void jumpVang(Actor causeBy) {
        if (causeBy == null)
            return;
        this.z = -3;
        this.dz = -3;
        this.dvangx = (short) ((this.x - causeBy.x) * 20);
        this.dvangy = (short) ((this.y - causeBy.y) * 20);
        while (this.dvangx > 4 || this.dvangy > 4 || this.dvangx < -4 || this.dvangy < -4) {
            this.dvangx >>= 1;
            this.dvangy >>= 1;
        }
    }

    public void setMove(boolean canmove) {
        this.canmove = canmove;
    }

    public void addDataeff(int id) {
        DataSkillEff eff = new DataSkillEff(id);
        this.DataEff.addElement(eff);
    }

    public void addEffectSkill(DataSkillEff eff, int x, int y) {
        if (eff == null)
            return;
        eff.x = (short) x;
        eff.y = (short) y;
        this.vecEFfect_Skill.addElement(eff);
    }

    public void addEffectSkill(int id, int x, int y) {
        DataSkillEff eff = new DataSkillEff(id, x, y);
        this.vecEFfect_Skill.addElement(eff);
    }

    public void addEffectSkillTime(int id, int x, int y, long timelive) {
        DataSkillEff eff = new DataSkillEff(id, x, y, timelive);
        this.vecEFfect_Skill.addElement(eff);
    }

    public void addEffectSkillTime(int id, int x, int y, long timelive, boolean canmove, boolean canpaint, boolean canfight, int loop, byte WaitLoop) {
        int size = this.vecEFfect_Skill.size();
        if (size > 0)
            for (int i = 0; i < size; i++) {
                DataSkillEff data = (DataSkillEff) this.vecEFfect_Skill.elementAt(i);
                if (data != null && data.indexEffect == id) {
                    data.timelive = timelive;
                    return;
                }
            }
        DataSkillEff eff = new DataSkillEff(id, x, y, timelive, canmove, canpaint, canfight, loop);
        eff.setWaitLoop(WaitLoop);
        this.vecEFfect_Skill.addElement(eff);
    }

    public int getStartPointPaintFly() {
        return 0;
    }

    public void paintTopDataEff(mGraphics g, int dy) {
        int i;
        for (i = 0; i < this.DataEff.size(); i++) {
            DataSkillEff dataeff = (DataSkillEff) this.DataEff.elementAt(i);
            if (dataeff != null)
                dataeff.paintTop(g, dy);
        }
        for (i = 0; i < this.vecEFfect_Skill.size(); i++) {
            DataSkillEff dataeff = (DataSkillEff) this.vecEFfect_Skill.elementAt(i);
            if (dataeff != null) {
                int h = 0;
                if (isMonster()) {
                    String pos = (String) GameScr.hashEffInfo.get((new StringBuilder(String.valueOf(dataeff.idEff))).toString());
                    if (pos != null) {
                        int check = Integer.parseInt(pos);
                        if (check == 1) {
                            h = 55 - getHeight();
                        } else if (check == 2) {
                            h = 27 - getHeight() / 2;
                        } else if (check > 0) {
                            h = 27 - getHeight() / 2 - check;
                        }
                    }
                }
                dataeff.paintTopEff(g, this.x, this.y + dy + h);
            }
        }
    }

    public void paintBottomDataEff(mGraphics g, int dy) {
        int i;
        for (i = 0; i < this.DataEff.size(); i++) {
            DataSkillEff dataeff = (DataSkillEff) this.DataEff.elementAt(i);
            if (dataeff != null)
                dataeff.paintBottom(g, dy);
        }
        for (i = 0; i < this.vecEFfect_Skill.size(); i++) {
            DataSkillEff dataeff = (DataSkillEff) this.vecEFfect_Skill.elementAt(i);
            if (dataeff != null) {
                int h = 0;
                if (isMonster()) {
                    String pos = (String) GameScr.hashEffInfo.get((new StringBuilder(String.valueOf(dataeff.idEff))).toString());
                    if (pos != null) {
                        int check = Integer.parseInt(pos);
                        if (check == 1) {
                            h = 55 - getHeight();
                        } else if (check == 2) {
                            h = 27 - getHeight() / 2;
                        } else if (check > 0) {
                            h = 27 - getHeight() / 2 - check;
                        }
                    }
                }
                dataeff.paintBottomEff(g, this.x, this.y + dy + h);
            }
        }
    }

    public void updateDataEff() {
        int i;
        for (i = 0; i < this.DataEff.size(); i++) {
            DataSkillEff dataeff = (DataSkillEff) this.DataEff.elementAt(i);
            if (dataeff != null) {
                dataeff.update();
                if (dataeff.wantDestroy)
                    this.DataEff.removeElement(dataeff);
            }
        }
        for (i = 0; i < this.vecEFfect_Skill.size(); i++) {
            DataSkillEff dataeff = (DataSkillEff) this.vecEFfect_Skill.elementAt(i);
            if (dataeff != null) {
                dataeff.update();
                if (dataeff.wantDestroy)
                    this.vecEFfect_Skill.removeElement(dataeff);
            }
        }
    }

    public void setDir(byte dir) {
        this.dir = dir;
    }

    public static void startDeadFly(Actor m, int attacker) {
        Actor c = GameCanvas.gameScr.findActor((short) attacker, 0);
        if (m.catagory == 1) {
            m.dieActor(c);
            m.setTickDie(0);
            if (GameCanvas.gameScr.focusedActor != null && m.equals(GameCanvas.gameScr.focusedActor))
                GameCanvas.gameScr.focusedActor = null;
            return;
        }
        if (m.catagory == 0)
            m.dieActor(c);
    }

    public void setCanFocus(boolean canFocus) {
        this._canFocus = canFocus;
    }

    public boolean canFocus() {
        return this._canFocus;
    }

    public void setTypePK(byte typePK) {
        this.typePK = typePK;
    }

    public byte getTypePK() {
        return this.typePK;
    }

    public boolean mcanmove() {
        for (int i = 0; i < this.vecEFfect_Skill.size(); i++) {
            DataSkillEff data = (DataSkillEff) this.vecEFfect_Skill.elementAt(i);
            if (data != null &&
                    data.canActorMove)
                return false;
        }
        return true;
    }

    public boolean canPaint() {
        for (int i = 0; i < this.vecEFfect_Skill.size(); i++) {
            DataSkillEff data = (DataSkillEff) this.vecEFfect_Skill.elementAt(i);
            if (data != null &&
                    data.canPaintActor)
                return false;
        }
        return true;
    }

    public boolean canUseSkill() {
        for (int i = 0; i < this.vecEFfect_Skill.size(); i++) {
            DataSkillEff data = (DataSkillEff) this.vecEFfect_Skill.elementAt(i);
            if (data != null &&
                    data.canActorFight)
                return false;
        }
        return true;
    }

    public void removeAllEff() {
        this.vecEFfect_Skill.removeAllElements();
        this.DataEff.removeAllElements();
    }

    public int getTypeMove() {
        return 0;
    }

    public boolean beFire() {
        return this._beFire;
    }

    public void SetbeFire(boolean beFire) {
        this._beFire = beFire;
    }

    public void setMaxHP(int maxhp) {
        this.maxhp = maxhp;
    }

    public void setMaxMP(int maxmp) {
        this.maxmp = maxmp;
    }

    public int getSpeed() {
        return this.speed;
    }

    public int getColorMiniMap() {
        return 0;
    }
}

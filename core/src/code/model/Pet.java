package code.model;

import code.effect.EffectEclip;
import code.effect.new_skill.Skill_Boss_Noel_1;
import code.main.GameCanvas;
import code.model.arrow.WeaponsLazer;
import code.screen.Res;
import code.screen.Utils;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSystem;
import lib.mVector;

public class Pet extends LiveActor {
    public static final byte ROAM = 0;
    public static final byte FOLLOW = 1;
    public static final byte ATTACK = 2;
    public static final byte RETURN = 3;
    public static final byte WAIT = 4;
    public static final byte DEATH = 5;
    public static final byte ATTRACTION = 6;
    protected final byte DIS_TO_ATTRACT = 80;
    protected final byte DIS_TO_FOLLOW = 40;
    protected boolean isSequenceAttack;
    protected boolean isDoneAttack;
    protected short type;
    protected byte imageId;
    protected byte state;
    protected byte Action;
    protected byte preState;
    protected byte attackType;
    protected int attackCount;
    protected int oldPosX;
    protected int oldPosY;
    protected int ownerX;
    protected int p1;
    protected int ownerY;
    protected int time;
    protected int timeAutoAction;
    protected int vx;
    protected int vy;
    protected int xAnchor;
    protected int yAnchor;
    protected int limitMove;
    public int xtam;
    public int vatam = 6;
    public int vMax;
    public int idTemplate;
    public short yfly;
    public short range;
    public short dyattack;
    public byte typemove;
    public byte f;
    public byte mAction;
    public byte idSkill;
    protected mVector wayPoint = new mVector("Pet wayPoint");
    private mVector ntarget = new mVector();
    private int[] dame;
    protected Actor owner;
    public static mHashtable HashImagePet = new mHashtable();
    public static byte[] mTypemove = new byte[]{2, 1, 0, 2, 0, 1};
    boolean isBeginAttack;
    public static final byte M_STAND = 0;
    public static final byte M_DEAD = 1;
    public static final byte M_WALK = 2;
    public static final byte M_ATTACK = 3;
    public static final byte AC_STAND = 0;
    public static final byte AC_MOVE = 1;
    public static final byte AC_FIRE = 2;
    public static final byte AC_HIT = 3;
    public static final byte AC_DIE = 4;
    public static final byte DIR_UP = 1;
    public static final byte DIR_DOWN = 0;
    public static final byte DIR_LEFT = 2;
    public static final byte DIR_RIGHT = 3;
    byte huongY = 0;
    byte flip = 0;
    byte frame;
    int toX;
    int toY;
    int cmove;
    boolean isRunAttack;
    public long timeBeginMoveAttack;

    public Pet(Actor owner, int idtemplate) {
        this.idTemplate = idtemplate;
        this.owner = owner;
        this.xAnchor = owner.x;
        this.yAnchor = owner.y;
        this.x = owner.x;
        this.y = owner.y;
        this.oldPosX = owner.x;
        this.oldPosY = owner.y;
        this.limitMove = 48;
        this.dir = 0;
        this.vMax = 4;
        this.speed = (short) (owner.getSpeed() - 1);
        this.state = 0;
        this.Action = 0;
        this.mAction = 0;
        this.range = 30;
        this.timeAutoAction = Res.random(200, 250);
        this.catagory = 12;
        this.ID = owner.ID;
    }

    public Pet(int idtemplate) {
        this.idTemplate = idtemplate;
        this.dir = 0;
        this.state = 0;
        this.Action = 0;
        this.mAction = 0;
        this.range = 30;
        this.timeAutoAction = Res.random(200, 250);
        this.catagory = 12;
    }

    public Pet(short typePet, byte nFrame, byte imageId, byte typemove) {
    }

    public void setAnim() {
    }

    public void clearWayPoints() {
        this.wayPoint.removeAllElements();
    }

    public void setState(byte state) {
        this.state = state;
    }

    public void paintnoHeight(mGraphics g, int x, int y) {
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get(this.idTemplate);
        if (mons1 != null)
            data = mons1.getDataMonster();
        if (data.size() == 0)
            return;
        try {
            DataEffect deff = (DataEffect) data.elementAt(0);
            byte idShadow = 0;
            if (deff != null) {
                int status = this.mAction;
                if (this.state == 2 && this.isBeginAttack)
                    status = 3;
                idShadow = deff.idShadow;
                byte[] dxdyshadow = deff.getDxDyShadow(deff.getFrame(this.frame, status, this.huongY));
                if (isFly()) {
                    if (canPaint()) {
                        int xsd = x + dxdyshadow[0];
                        int ysd = y + dxdyshadow[1];
                        g.drawImage(GameScr.imgShadow[idShadow], xsd, ysd, 0, false);
                    }
                } else if (!this.isWater &&
                        canPaint()) {
                    int xsd = x + this.vangx + dxdyshadow[0];
                    int ysd = y - this._jum + this.vangy + dxdyshadow[1];
                    g.drawImage(GameScr.imgShadow[idShadow], xsd, ysd, 0, false);
                }
                paintBottomDataEff(g, getStartPointPaintFly());
                MonsterTemplate mons = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get(this.idTemplate);
                if (mons != null) {
                    if (this.width == 0 || this.height == 0) {
                        this.width = deff.getWith();
                        this.height = deff.getHeight();
                    }
                    deff.paintnoHeight(g, deff.getFrame(this.frame, status, this.huongY), x, y, this.flip, mons.getImage(0), isFly());
                }
            }
            paintTopDataEff(g, getStartPointPaintFly());
        } catch (Exception exception) {
        }
    }

    public void paint(mGraphics g) {
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get(this.idTemplate);
        if (mons1 != null)
            data = mons1.getDataMonster();
        if (data.size() == 0)
            return;
        try {
            DataEffect deff = (DataEffect) data.elementAt(0);
            byte idShadow = 0;
            if (deff != null) {
                int status = this.mAction;
                if (this.state == 2 && this.isBeginAttack)
                    status = 3;
                idShadow = deff.idShadow;
                byte[] dxdyshadow = deff.getDxDyShadow(deff.getFrame(this.frame, status, this.huongY));
                if (isFly()) {
                    if (canPaint()) {
                        int xsd = this.x + dxdyshadow[0];
                        int ysd = this.y + dxdyshadow[1];
                        g.drawImage(GameScr.imgShadow[idShadow], xsd, ysd, 0, false);
                    }
                } else if (!this.isWater &&
                        canPaint()) {
                    int xsd = this.x + this.vangx + dxdyshadow[0];
                    int ysd = this.y - this._jum + this.vangy + dxdyshadow[1];
                    g.drawImage(GameScr.imgShadow[idShadow], xsd, ysd, 0, false);
                }
                paintBottomDataEff(g, getStartPointPaintFly());
                MonsterTemplate mons = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get(this.idTemplate);
                if (mons != null) {
                    if (this.width == 0 || this.height == 0) {
                        this.width = deff.getWith();
                        this.height = deff.getHeight();
                    }
                    deff.paintPet(g, deff.getFrame(this.frame, status, this.huongY), this.x, this.y, this.flip, mons.getImage(0), this.dyattack);
                }
            }
            paintTopDataEff(g, getStartPointPaintFly());
        } catch (Exception exception) {
        }
    }

    public boolean isFly() {
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + this.idTemplate);
        if (mons1 != null) {
            data = mons1.getDataMonster();
        }

        DataEffect deff = (DataEffect) data.elementAt(0);
        return deff != null && deff.isFly <= -5;
    }

    protected void roam() {
        this.vMax = 1;
        if (this.Action == 1) {
            if (this.time > this.timeAutoAction || Res.random(16) == 0 || Utils.getDistance(this.x + this.vx, this.y + this.vy, this.xAnchor, this.yAnchor) >= this.limitMove) {
                this.time = 0;
                this.Action = 0;
                this.mAction = 0;
                this.vx = 0;
                this.vy = 0;
            }
        } else if (this.Action == 0) {
            this.vx = 0;
            this.vy = 0;
            if (this.time > this.timeAutoAction / 2 || Res.random(12) == 0) {
                this.time = 0;
                this.Action = 1;
                this.mAction = 2;
                this.dir = (short) Res.random(4);
                this.setSpeedInDirection(this.vMax);
            }
        }

        if (this.owner != null) {
            if (this.owner.getState() == 1 && Utils.getDistance(this.x, this.y, this.ownerX, this.ownerY) > 40) {
                this.setState((byte) 1);
            }

            if (this.owner.getState() == 0 && Utils.getDistance(this.x, this.y, this.ownerX, this.ownerY) > this.limitMove * 2) {
                this.setState((byte) 3);
            }
        } else {
            int disToPlayer = Utils.getDistance(this.x, this.y, GameScr.mainChar.x, GameScr.mainChar.y);
            if (disToPlayer < 80 && disToPlayer > 40 && Res.random(6) == 0) {
                this.setState((byte) 6);
            }
        }

    }

    public void setSpeedInDirection(int max) {
        int vplus = Res.random_Am_0(3);
        if (Utils.abs(vplus) > 1) {
            --max;
        }

        switch (this.dir) {
            case 0:
                this.vy = max;
                this.vx = vplus;
                break;
            case 1:
                this.vy = -max;
                this.vx = vplus;
                break;
            case 2:
                this.vy = vplus;
                this.vx = -max;
                break;
            case 3:
                this.vy = vplus;
                this.vx = max;
        }

        if (this.vx == 0 && Res.random(3) == 0) {
            this.time = 0;
            this.state = 0;
            this.vx = 0;
            this.vy = 0;
            this.mAction = 0;
        }

        if (this.vx > 0) {
            this.dir = 3;
        } else {
            this.dir = 2;
        }

        this.huongY = (byte) (this.vy > 0 ? 1 : 0);
        this.flip = (byte) (this.vx > 0 ? 0 : 2);
        this.flip = 0;
        if (this.dir == 3) {
            this.flip = 2;
        } else if (this.dir != 2) {
            if (this.dir == 0) {
                this.huongY = 0;
            } else if (this.dir == 1) {
                this.huongY = 1;
            }
        }

        this.mAction = 2;
    }

    public void setHuong(int xto, int yto) {
        this.huongY = (byte) (this.y > yto ? 1 : 0);
        this.flip = (byte) (this.x - xto > 0 ? 0 : 2);
        this.flip = 0;
        if (this.dir == 3) {
            this.flip = 2;
        } else if (this.dir != 2) {
            if (this.dir == 0) {
                this.huongY = 0;
            } else if (this.dir == 1) {
                this.huongY = 1;
            }
        }

    }

    protected void standStill() {
        this.vx = 0;
        this.vy = 0;
        this.state = 4;
        if (this.owner != null && this.owner.getState() == 0) {
            this.setState((byte) 0);
        }

    }

    protected void follow() {
        this.vMax = this.owner.getSpeed();
        this.Action = 1;
        if (this.wayPoint.size() <= 0) {
            ++this.cmove;
            if (this.cmove > 20) {
                this.setState((byte) 0);
            }
        }

        if (Utils.getDistance(this.oldPosX, this.oldPosY, this.ownerX, this.ownerY) > 40) {
            Point p = new Point(this.ownerX, this.ownerY);
            this.oldPosX = this.ownerX;
            this.oldPosY = this.ownerY;
            this.wayPoint.addElement(p);
        } else if (Utils.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) < 40) {
            this.wayPoint.removeAllElements();
            this.setState((byte) 4);
        }

        if (this.wayPoint.elementAt(0) != null) {
            this.toX = ((Point) this.wayPoint.elementAt(0)).x;
            this.toY = ((Point) this.wayPoint.elementAt(0)).y;
            this.setHuong(this.toX, this.toY);
            this.move_to_XY();
        }

    }

    public void move_to_XY() {
        boolean isMatchX = false;
        boolean isMatchY = false;
        int dx1 = Math.abs(this.x - this.toX);
        int dy1 = Math.abs(this.y - this.toY);
        if (dx1 <= this.speed) {
            this.x = (short) this.toX;
            isMatchX = true;
        }

        if (dy1 < this.speed) {
            this.y = (short) this.toY;
            isMatchY = true;
        }

        if (this.x < this.toX) {
            this.x += this.speed;
            this.dir = 3;
        } else if (this.x > this.toX) {
            this.x -= this.speed;
            this.dir = 2;
        } else if (this.y > this.toY) {
            this.y -= this.speed;
            this.dir = 1;
        } else if (this.y < this.toY) {
            this.dir = 0;
            this.y += this.speed;
        }

        this.huongY = (byte) (this.vy > 0 ? 1 : 0);
        this.flip = (byte) (this.vx > 0 ? 0 : 2);
        this.flip = 0;
        if (this.dir == 3) {
            this.flip = 2;
        } else if (this.dir != 2) {
            if (this.dir == 0) {
                this.huongY = 0;
            } else if (this.dir == 1) {
                this.huongY = 1;
            }
        }

        if (this.state != 3 && (this.owner.getDir() == 1 || this.owner.getDir() == 0)) {
            this.xtam += this.vatam;
            if (this.vatam > 0) {
                this.dir = 3;
            }

            if (this.vatam < 0) {
                this.dir = 2;
            }

            if (this.xtam + this.vatam >= 48 && this.vatam > 0 || this.xtam + this.vatam < -48 && this.vatam < 0) {
                this.vatam *= -1;
            }
        }

    }

    public void initAttackState(byte skillId) {
        try {
            this.attackType = skillId;
            this.isRunAttack = true;
            this.timeBeginMoveAttack = mSystem.currentTimeMillis();
            this.attackCount = 0;
            this.isSequenceAttack = false;
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    protected void attack() {
        if (this.ntarget != null && this.ntarget.size() > 0) {
            Actor target = (Actor) this.ntarget.elementAt(0);
            if (target == null) {
                this.setState((byte) 1);
                return;
            }

            if (Utils.getDistance(this.x + this.speed, this.y + this.speed, target.x, target.y) > this.range && !this.isBeginAttack && target.getHp() > 0) {
                this.toX = target.x;
                this.toY = target.y;
                this.setHuong(this.toX, this.toY);
                this.move_to_XY();
                this.mAction = 2;
                if (this.dyattack < 40 && this.isFly()) {
                    ++this.dyattack;
                }
            } else {
                this.isBeginAttack = true;
            }

            if (this.isBeginAttack) {
                if (this.dyattack < 50 && this.isFly()) {
                    this.dyattack = (short) (this.dyattack + 10);
                }

                ++this.p1;
                if (this.p1 > 6) {
                    this.p1 = 0;
                    this.isBeginAttack = false;
                    this.setState((byte) 4);

                    for (int i = 0; i < this.ntarget.size(); ++i) {
                        Actor ac = (Actor) this.ntarget.elementAt(i);
                        if (ac != null) {
                            switch (this.idSkill) {
                                case 0:
                                    GameCanvas.gameScr.startFlyText("- " + this.dame[i], 2, ac.x, ac.y, 1, -2, this.x > ac.x ? -1 : 1);
                                    EffectManager.addHiDataeffectSkill(135, ac.x, ac.y, 1);
                                    break;
                                case 1:
                                    GameCanvas.gameScr.startFlyText("- " + this.dame[i], 2, ac.x, ac.y, 1, -2, this.x > ac.x ? -1 : 1);
                                    EffectManager.addHiDataeffectSkill(135, ac.x, ac.y, 1);
                                    GameCanvas.gameScr.StartNewLightning(this, ac, 0);
                                    break;
                                case 2:
                                    GameCanvas.gameScr.startNewMagicBeam(20, this, ac, this.x, this.y, this.dame[i], (int) -1);
                                    break;
                                case 3:
                                    if (i == 0) {
                                        EffectEclip ecl = new EffectEclip(this.x, this.y);
                                        EffectManager.addHiEffect(ecl);
                                        EffectManager.addHiDataeffectSkill(120, this.x, this.y, 1);
                                    }

                                    GameCanvas.gameScr.startFlyText("- " + this.dame[i], 2, ac.x, ac.y, 1, -2, this.x > ac.x ? -1 : 1);
                                    EffectManager.addHiDataeffectSkill(135, ac.x, ac.y, 1);
                                    break;
                                case 4:
                                    Skill_Boss_Noel_1 sk = new Skill_Boss_Noel_1(ac, this.dame[i]);
                                    EffectManager.hiEffects.addElement(sk);
                                    break;
                                case 5:
                                    WeaponsLazer wp = new WeaponsLazer(ac, 3);
                                    EffectManager.hiEffects.addElement(wp);
                                    GameCanvas.gameScr.startFlyText("- " + this.dame[i], 2, ac.x, ac.y, 1, -2, this.x > ac.x ? -1 : 1);
                                    break;
                                case 6:
                                    if (i == 0) {
                                        GameCanvas.gameScr.SkillofBoss_Snake(this, ac, 4, 52);
                                        EffectManager.addHiDataeffectSkill(135, ac.x, ac.y, 1);
                                    }

                                    GameCanvas.gameScr.startFlyText("- " + this.dame[i], 2, ac.x, ac.y, 1, -2, this.x > ac.x ? -1 : 1);
                            }
                        }
                    }
                }
            }
        } else {
            ++this.p1;
            this.isBeginAttack = true;
            if (this.p1 > 6) {
                this.p1 = 0;
                this.isBeginAttack = false;
                this.setState((byte) 4);
            }
        }

    }

    protected void returnToPlayer() {
        this.vMax = this.owner.getSpeed();
        this.toX = this.owner.x;
        this.toY = this.owner.y;
        this.setHuong(this.toX, this.toY);
        this.move_to_XY();
        if (Utils.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) < 40 && this.owner.getState() != 2) {
            this.setState((byte) 0);
        }

    }

    protected void waitForCommand() {
        this.vx = 0;
        this.vy = 0;
        this.Action = 1;
        if (this.owner != null) {
            if (this.owner.getState() == 0) {
                this.wayPoint.removeAllElements();
                this.setState((byte) 0);
            } else if (this.owner.getState() == 1 && Utils.getDistance(this.x, this.y, this.xAnchor, this.yAnchor) > 40) {
                this.wayPoint.removeAllElements();
                this.setState((byte) 1);
            }
        }

    }

    public void addAttackEffect() {
    }

    private void attract() {
        this.vMax = 3;
        this.state = 1;
        this.toX = GameScr.mainChar.x;
        this.toY = GameScr.mainChar.y;
        this.move_to_XY();
        if (Utils.getDistance(this.x, this.y, this.toX, this.toY) < 40) {
            this.setState((byte) 0);
        }

    }

    public void doChangeFrmaeBoss() {
        mVector data = new mVector();
        MonsterTemplate mons1 = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + this.idTemplate);
        if (mons1 != null) {
            data = mons1.getDataMonster();
        }

        DataEffect deff = null;
        if (data.size() > 0) {
            deff = (DataEffect) data.elementAt(0);
            int status = this.mAction;
            if (this.state == 2 && this.isBeginAttack) {
                status = 3;
            }

            this.frame = (byte) ((this.frame + 1) % deff.getAnim(status, this.huongY).frame.length);
        }

    }

    public void updateMenu() {
        if (GameCanvas.gameTick % 2 == 0) {
            this.f = (byte) ((this.f + 1) % 3);
        }

        if (this.state != 2) {
            this.x = (short) (this.x + this.vx);
            this.y = (short) (this.y + this.vy);
        }

        this.doChangeFrmaeBoss();
        if (GameCanvas.gameTick % 200 == 0) {
            this.state = 2;
        }

        if (this.state == 2) {
            this.attack();
        }

    }

    public void update() {
        try {
            if (!this.isBeginAttack && this.dyattack > 0) {
                this.dyattack = (short) (this.dyattack - 3);
                if (this.dyattack < 0) {
                    this.dyattack = 0;
                }
            }

            if (GameCanvas.gameTick % 2 == 0) {
                this.f = (byte) ((this.f + 1) % 3);
            }

            if (this.state != 2) {
                this.x = (short) (this.x + this.vx);
                this.y = (short) (this.y + this.vy);
            }

            this.doChangeFrmaeBoss();
            if (this.owner != null) {
                if (Utils.getDistance(this.x, this.y, this.toX, this.toY) <= 10) {
                    this.wayPoint.removeElementAt(0);
                }

                this.xAnchor = this.owner.x;
                this.yAnchor = this.owner.y;
                this.ownerX = this.owner.x;
                this.ownerY = this.owner.y;
            }

            ++this.time;
            switch (this.state) {
                case 0:
                    this.roam();
                    break;
                case 1:
                    this.mAction = 2;
                    this.follow();
                    break;
                case 2:
                    this.attack();
                    break;
                case 3:
                    this.returnToPlayer();
                    this.mAction = 2;
                    break;
                case 4:
                    this.mAction = 0;
                    this.waitForCommand();
                case 5:
                default:
                    break;
                case 6:
                    this.attract();
            }

            if (this.state != this.preState) {
                this.preState = this.state;
            }
        } catch (Exception var2) {
        }

    }

    public boolean canFocus() {
        return false;
    }

    public void startAttack(mVector target, int idskill) {
        this.state = 2;
    }

    public void setidTemplatePet(short id) {
        this.idTemplate = id;
    }

    public void petAttack(byte idSkill, mVector ntarget, int[] dame, short range) {
        this.range = range;
        this.idSkill = idSkill;
        this.ntarget = ntarget;
        this.dame = dame;
        this.state = 2;
        this.dyattack = 0;
    }

    public boolean isMypet() {
        return this.owner.ID == GameScr.mainChar.ID;
    }

    public int getIDTem() {
        return this.idTemplate;
    }
}

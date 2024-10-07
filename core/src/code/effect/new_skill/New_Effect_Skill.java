package code.effect.new_skill;

import code.main.GameCanvas;
import code.model.Actor;
import code.model.Char;
import code.model.DataSkillEff;
import code.model.Effect;
import code.model.EffectManager;
import code.model.LiveActor;
import code.model.Point;
import code.screen.Res;
import code.screen.Util;
import code.screen.Utils;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mVector;

public class New_Effect_Skill extends Effect {
    private int dx;
    private int dy;
    private Char mychar;
    private int angle;
    private int va;
    private int vy;
    private int speed;
    private int yFly;
    private int frame;
    private int delay;
    private mVector mst;
    private short imgIndex;
    private byte type;
    private byte countFlyDame;
    private int rotate;
    private int countRotate;
    private mVector Point_Target = new mVector();
    private Point mpoint;
    private static byte[] splashDuaX = new byte[]{-2, 2, -8, 8};
    private static byte[] splashDuaY = new byte[]{-10, -30, -10, -10};
    private static byte[] x2 = new byte[]{0, 20, 20, 0, 0, 20, 20, 20, 20};
    private static byte[] y2 = new byte[]{0, 0, 0, 20, 20, 20, 20, 20, 20};
    private static short[][] goc = new short[][]{{90, 315, 225, 270, 315, 225, 90, 315, 225, 315, 225, 225}, {270, 135, 45, 90, 135, 45, 270, 135, 45, 135, 45, 45}, {180, 45, 315, 180, 45, 315, 180, 45, 315, 45, 315, 315}, {0, 135, 225, 0, 135, 225, 0, 135, 225, 135, 225, 225}};
    private DataSkillEff skilleff;
    private int[] Arr_rotate = new int[]{0, 5, 3, 6};
    public static final byte SKILL_THIEU_LAM_1 = 0;
    public static final byte SKILL_VO_DANG_1 = 1;
    public static final byte SKILL_NGA_MI_1 = 2;
    public static final byte SKILL_NGU_DOC_1 = 3;
    public static final byte SKILL_CAI_BANG_1 = 4;
    public static final byte SKILL_THIEU_LAM_2 = 5;
    public static final byte SKILL_VO_DANG_2 = 6;
    public static final byte SKILL_NGA_MI_2 = 7;
    public static final byte SKILL_NGU_DOC_2 = 8;
    public static final byte SKILL_CAI_BANG_2 = 9;
    public static final byte EFF_WHEN_CHAR_DIE = 10;
    public static final byte SKILL_THIEU_LAM_3 = 11;
    public static final byte SKILL_NGU_DOC_3 = 12;
    public static final byte SKILL_NGA_MI_3 = 13;
    public static final byte SKILL_THIEU_LAM_4 = 14;
    public static final byte SKILL_NGU_DOC_4 = 15;
    public static final byte SKILL_NGA_MI_4 = 16;
    public static final byte SKILL_THIEU_LAM_5 = 17;
    public static final byte SKILL_THIEU_LAM_6 = 18;
    public int countDame;
    private int[] xArray = new int[]{-10, 0, 10, 0};
    private int xto;
    private int yto;
    private int countDelay;
    private int save_x;
    private int save_y;
    private int countCLD;
    private int maxCLD;
    private boolean canMove;
    private boolean isFall;
    private boolean isaddEffect;

    public New_Effect_Skill(Char c, mVector ntarget, byte type) {
        this.x = c.x;
        this.y = c.y;
        this.mychar = c;
        this.mst = new mVector();

        for (int i = 0; i < ntarget.size(); ++i) {
            this.mst.addElement((Actor) ntarget.elementAt(i));
        }

        this.type = type;
        this.setInfo();
    }

    public New_Effect_Skill(Char c, mVector ntarget, byte type, int[] arrDame) {
        this.x = c.x;
        this.y = c.y;
        this.mychar = c;
        this.mst = new mVector();

        for (int i = 0; i < ntarget.size(); ++i) {
            this.mst.addElement((Actor) ntarget.elementAt(i));
        }

        this.type = type;
        this.arrDame = arrDame;
        this.setInfo();
    }

    public New_Effect_Skill(Char c, byte type) {
        this.type = type;
        this.mychar = c;
        this.setInfo();
    }

    private void setInfo() {
        Actor ac1;
        int j;
        Actor ac;
        int i;
        int dx;
        int k;
        switch (this.type) {
            case 0:
                if (!this.mychar.getCanpaint()) {
                    this.wantDestroy = true;
                    return;
                }
                this.countFlyDame = (byte) this.mst.size();
                this.save_x = this.mychar.x;
                this.save_y = this.mychar.y;
                this.skilleff = new DataSkillEff(127);
                this.imgIndex = 25;
                this.va = 25;
                this.mychar.setCanPaint(false);
                if (this.mst.size() == 1) {
                    Actor actor = (Actor) this.mst.elementAt(0);
                    if (actor != null)
                        for (int m = 0; m < 4; m++) {
                            actor.x = (short) (actor.x + this.xArray[m]);
                            this.mst.addElement(actor);
                        }
                }
                if (this.mst.size() == 2) {
                    Actor actor1 = (Actor) this.mst.elementAt(0);
                    Actor actor2 = (Actor) this.mst.elementAt(1);
                    if (actor1 != null && actor2 != null) {
                        this.mst.addElement(actor2);
                        this.mst.addElement(actor1);
                    }
                }
                ac1 = (Actor) this.mst.elementAt(0);
                if (ac1 != null) {
                    this.mpoint = new Point(ac1.x, ac1.y);
                    this.xto = this.mpoint.x;
                    this.yto = this.mpoint.y;
                    this.target = ac1;
                    this.mst.removeElement(ac1);
                }
                for (j = 0; j < this.mst.size(); j++) {
                    Actor actor = (Actor) this.mst.elementAt(j);
                    if (actor != null) {
                        Point p = new Point(actor.x, actor.y);
                        this.Point_Target.addElement(p);
                    }
                }
                this.maxCLD = this.Point_Target.size();
                break;
            case 1:
                for (j = 0; j < this.mst.size(); j++) {
                    Actor actor = (Actor) this.mst.elementAt(j);
                    for (int m = 0; m < 4; m++)
                        GameCanvas.gameScr.startNewMagicBeam(15, (Actor) this.mychar, actor, this.x + splashDuaX[this.mychar.dir] + x2[m], this.y + splashDuaY[this.mychar.dir] + y2[m], 0, (byte) 0, goc[this.mychar.dir][m]);
                }
                this.wantDestroy = true;
                break;
            case 2:
                if (GameCanvas.gameScr.focusedActor != null) {
                    for (j = 0; j < this.mst.size(); j++) {
                        Actor actor = (Actor) this.mst.elementAt(j);
                        if (actor != null && actor.equals(GameCanvas.gameScr.focusedActor)) {
                            this.target = actor;
                            this.xto = this.target.x;
                            this.yto = this.target.y;
                            this.mst.removeElement(actor);
                            break;
                        }
                    }
                } else {
                    Actor actor = (Actor) this.mst.elementAt(0);
                    if (actor != null) {
                        this.target = actor;
                        this.xto = this.target.x;
                        this.yto = this.target.y;
                        this.mst.removeElement(actor);
                    }
                }
                if (this.target != null) {
                    this.mychar.y = this.target.y;
                    this.mychar.x = (short) (this.mychar.x + ((this.target.x > this.mychar.x) ? -80 : 80));
                    this.mychar.dir = (short) ((this.target.x > this.mychar.x) ? 3 : 2);
                    this.mychar.setYfly(60);
                    this.va = 20;
                    break;
                }
                this.wantDestroy = true;
                break;
            case 3:
                this.imgIndex = 18;
                this.va = 20;
                if (GameCanvas.gameScr.focusedActor != null) {
                    for (j = 0; j < this.mst.size(); j++) {
                        Actor actor = (Actor) this.mst.elementAt(j);
                        if (actor != null && actor.equals(GameCanvas.gameScr.focusedActor)) {
                            this.target = actor;
                            this.xto = this.target.x;
                            this.yto = this.target.y;
                            this.mst.removeElement(actor);
                            break;
                        }
                    }
                    break;
                }
                ac = (Actor) this.mst.elementAt(0);
                if (ac != null) {
                    this.target = ac;
                    this.xto = this.target.x;
                    this.yto = this.target.y;
                    this.mst.removeElement(ac);
                }
                break;
            case 4:
                this.mychar.setCanPaint(false);
                this.delay = 10;
                this.imgIndex = 47;
                break;
            case 7:
                for (i = 0; i < this.mst.size(); i++) {
                    Actor actor = (Actor) this.mst.elementAt(i);
                    if (actor != null)
                        GameCanvas.gameScr.StartNewLazer(this.mychar.x, this.mychar.y, actor.x, actor.y, (Actor) this.mychar, actor);
                }
                this.wantDestroy = true;
                break;
            case 8:
                for (i = 0; i < this.mst.size(); i++) {
                    Actor actor = (Actor) this.mst.elementAt(i);
                    if (actor != null) {
                        GameCanvas.gameScr.StartNewLightning(actor);
                        actor.setMove2Target((Actor) this.mychar);
                    }
                }
                EffectManager.addHiDataeffectSkill(33, this.mychar.x, this.mychar.y, 1);
                this.wantDestroy = true;
                break;
            case 9:
                for (i = 0; i < this.mst.size(); i++) {
                    Actor actor = (Actor) this.mst.elementAt(i);
                    if (actor != null) {
                        EffectManager.addHiDataeffectSkill_(55, actor.x, actor.y, 0, this.mychar);
                        actor.setMove2Target((Actor) this.mychar);
                        if (i == this.mst.size() - 1) {
                            this.mychar.x = actor.x;
                            this.mychar.y = actor.y;
                        }
                    }
                }
                this.mychar.setCanPaint(false);
                this.wantDestroy = true;
                break;
            case 5:
                if (GameCanvas.gameScr.focusedActor != null) {
                    for (i = 0; i < this.mst.size(); i++) {
                        Actor actor = (Actor) this.mst.elementAt(i);
                        if (actor != null && actor.equals(GameCanvas.gameScr.focusedActor)) {
                            this.target = actor;
                            this.mst.removeElement(actor);
                            break;
                        }
                    }
                } else {
                    Actor actor = (Actor) this.mst.elementAt(0);
                    if (actor != null) {
                        this.target = actor;
                        this.xto = this.target.x;
                        this.yto = this.target.y;
                        this.mst.removeElement(actor);
                    }
                }
                this.mychar.dir = Util.findDirection((Actor) this.mychar, this.target);
                this.va = 40;
                this.yFly = 0;
                this.speed = 10;
                this.delay = 3;
                break;
            case 10:
                this.imgIndex = 34;
                dx = 38;
                if (Res.random(0, 10) % 2 == 0)
                    dx *= -1;
                this.x = this.mychar.x + dx;
                this.y = this.mychar.y;
                this.vy = -15;
                this.speed = 0;
                break;
            case 11:
                for (k = 0; k < this.mst.size(); k++) {
                    Actor actor = (Actor) this.mst.elementAt(k);
                    if (actor != null)
                        actor.addEffectSkill(9, 0, 0);
                }
                this.wantDestroy = true;
                break;
            case 12:
                for (k = 0; k < this.mst.size(); k++) {
                    Actor actor = (Actor) this.mst.elementAt(k);
                    if (actor != null)
                        GameCanvas.gameScr.startNewArrow(this.mychar.x, this.mychar.y, (Actor) this.mychar, actor, 0, 80, 81, 82, 53, 0, 20);
                }
                this.wantDestroy = true;
                break;
            case 13:
                for (k = 0; k < this.mst.size(); k++) {
                    Actor actor = (Actor) this.mst.elementAt(k);
                    if (actor != null)
                        actor.addEffectSkill(76, 0, 0);
                }
                this.wantDestroy = true;
                break;
            case 14:
                for (k = 0; k < this.mst.size(); k++) {
                    Actor actor = (Actor) this.mst.elementAt(k);
                    if (actor != null)
                        actor.addEffectSkill(11, 0, 0);
                }
                this.wantDestroy = true;
                break;
            case 15:
                for (k = 0; k < this.mst.size(); k++) {
                    Actor actor = (Actor) this.mst.elementAt(k);
                    if (actor != null)
                        actor.addEffectSkill(53, 0, 0);
                }
                this.wantDestroy = true;
                break;
            case 16:
                for (k = 0; k < this.mst.size(); k++) {
                    Actor actor = (Actor) this.mst.elementAt(k);
                    if (actor != null)
                        actor.addEffectSkill(13, -96, 0);
                }
                this.wantDestroy = true;
                break;
            case 17:
                for (k = 0; k < this.mst.size(); k++) {
                    Actor actor = (Actor) this.mst.elementAt(k);
                    if (actor != null)
                        actor.addEffectSkill(12, 0, 0);
                }
                this.wantDestroy = true;
                break;
            case 18:
                for (k = 0; k < this.mst.size(); k++) {
                    Actor actor = (Actor) this.mst.elementAt(k);
                    if (actor != null)
                        GameCanvas.gameScr.startDragon(actor.x - 200, actor.y, actor, 0, 20, 0, 73);
                }
                this.wantDestroy = true;
                break;
        }
    }

    public void paint(mGraphics g) {
        try {
            switch (this.type) {
                case 0:
                    if (this.skilleff != null) {
                        this.skilleff.paintTopArrow(g, this.x, this.y);
                    }
                case 1:
                case 2:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                default:
                    break;
                case 3:
                    if (EffectSkill.getImage(this.imgIndex) != null) {
                        g.drawRegion(EffectSkill.getImage(this.imgIndex), 0, this.frame * EffectSkill.getHight(this.imgIndex), EffectSkill.getWidth(this.imgIndex), EffectSkill.getHight(this.imgIndex), 0, this.x, this.y, 3, false);
                    }
                    break;
                case 4:
                    if (this.mychar.yFly > 0 && EffectSkill.getImage(this.imgIndex) != null) {
                        g.drawRegion(EffectSkill.getImage(this.imgIndex), 0, this.frame * EffectSkill.getHight(this.imgIndex), EffectSkill.getWidth(this.imgIndex), EffectSkill.getHight(this.imgIndex), 0, this.x, this.y - this.mychar.yFly, 3, false);
                    }
                    break;
                case 10:
                    if (EffectSkill.getImage(this.imgIndex) != null) {
                        g.drawRegion(EffectSkill.getImage(this.imgIndex), 0, this.frame * EffectSkill.getHight(this.imgIndex), EffectSkill.getWidth(this.imgIndex), EffectSkill.getHight(this.imgIndex), this.rotate, this.x, this.y, 3, false);
                    }
            }
        } catch (Exception var3) {
            System.out.println("LOI SKILL new_effect_skill: " + this.type + " >> " + (this.skilleff != null ? this.skilleff.idSkill : 0));
            var3.printStackTrace();
        }

    }

    private void updateva() {
        if (!this.wantDestroy) {
            Char var10000;
            int i;
            Actor ac;
            short dx1;
            short dy1;
            boolean isMatchX;
            boolean isMatchY;
            short vy;
            short vx;
            switch (this.type) {
                case 0:
                    if (this.skilleff != null) {
                        this.skilleff.updateArrow();
                    }

                    if (this.mpoint == null) {
                        this.wantDestroy = true;
                        this.mychar.x = (short) this.save_x;
                        this.mychar.y = (short) this.save_y;
                        this.mychar.state = 0;
                        this.mychar.setCanPaint(true);
                    }

                    ++this.frame;
                    if (this.frame > 3) {
                        this.frame = 0;
                    }

                    if (this.mpoint != null) {
                        this.xto = this.mpoint.x;
                        this.yto = this.mpoint.y;
                        this.dx = (short) (this.xto - this.x);
                        this.dy = (short) (this.yto - 5 - this.y);
                        this.angle = (short) Util.angle(this.dx, this.dy);
                        vx = (short) (this.va * Util.cos(this.angle) >> 10);
                        vy = (short) (this.va * Util.sin(this.angle) >> 10);
                        this.x += vx;
                        this.y += vy;
                        var10000 = this.mychar;
                        var10000.x += vx;
                        var10000 = this.mychar;
                        var10000.y += vy;
                        dx1 = (short) Math.abs(this.x - this.xto);
                        dy1 = (short) Math.abs(this.y - this.yto);
                        isMatchX = false;
                        isMatchY = false;
                        if (dx1 <= vx) {
                            this.x = (short) this.xto;
                            isMatchX = true;
                        }

                        if (dy1 <= vy) {
                            this.y = (short) this.yto;
                            isMatchY = true;
                        }

                        if (isMatchX && isMatchY) {
                            ++this.countCLD;
                            this.x = this.xto;
                            this.y = this.yto;
                            if (this.arrDame != null && this.arrDame.length > 0) {
                                this.dame = this.arrDame[this.countDame];
                                ++this.countDame;
                                if (this.countDame > this.arrDame.length - 1) {
                                    this.countDame = 0;
                                }
                            }

                            EffectManager.addHiDataeffectSkill(59, this.xto, this.yto, 1);
                            if (this.target.getHp() <= 0) {
                                LiveActor.startDeadFly(this.target, this.mychar.ID);
                            } else {
                                this.target.jumpVang(this.Owner);
                            }

                            if (this.countFlyDame > 0) {
                                if (this.dame == 0) {
                                    this.isDame0 = true;
                                }

                                this.startFlyText(this.dame, 0, this.xto, this.yto - 10, 0, 0);
                                --this.countFlyDame;
                            }

                            if (this.mst.size() > 0) {
                                this.mpoint = null;
                                this.target = null;
                                this.target = (Actor) this.mst.elementAt(0);
                                this.mpoint = (Point) this.Point_Target.elementAt(0);
                                if (this.mpoint != null) {
                                    this.xto = this.mpoint.x;
                                    this.yto = this.mpoint.y;
                                }

                                this.mst.removeElementAt(0);
                                this.Point_Target.removeElementAt(0);
                            } else {
                                this.wantDestroy = true;
                                this.mychar.x = (short) this.save_x;
                                this.mychar.y = (short) this.save_y;
                                this.mychar.state = 0;
                                this.target = null;
                                this.mpoint = null;
                                this.mychar.setCanPaint(true);
                                this.Point_Target = null;
                                this.mst = null;
                            }
                        }
                    }

                    if (this.countCLD > this.maxCLD) {
                        this.wantDestroy = true;
                        this.mychar.x = (short) this.save_x;
                        this.mychar.y = (short) this.save_y;
                        this.mychar.state = 0;
                        this.target = null;
                        this.mpoint = null;
                        this.mychar.setCanPaint(true);
                        this.Point_Target = null;
                        this.mst = null;
                    }
                case 1:
                case 6:
                case 7:
                case 8:
                case 9:
                default:
                    break;
                case 2:
                    if (this.target == null) {
                        this.mychar.yFly = 0;
                        this.wantDestroy = true;
                        return;
                    }

                    if (this.countDelay > 0) {
                        --this.countDelay;
                    }

                    if (this.mychar.getYfly() > 0 && this.countDelay <= 0 && !this.canMove) {
                        if (this.mychar.state != 7) {
                            this.mychar.state = 7;
                            this.mychar.p1 = 0;
                        }

                        if (this.mychar.p1 == 0) {
                            GameScr.addEffectByDir(this.mychar.dir, this.mychar.clazz, this.mychar);
                        }

                        if (this.mychar.p1 == 2) {
                            GameCanvas.gameScr.StartNewBaburan(0, this.mychar, this.target, this.mychar.x, this.mychar.y - 40 - this.mychar.getYfly(), 0, (byte) 0, 18);
                            this.mychar.p1 = 0;
                            if (this.mst.size() > 1) {
                                this.target = null;
                                this.target = (Actor) this.mst.elementAt(0);
                                this.xto = this.target.x;
                                this.yto = this.target.y;
                                this.mst.removeElementAt(0);
                                this.countDelay = 5;
                            }

                            if (this.mst.size() <= 1) {
                                this.target = null;
                                this.target = (Actor) this.mst.elementAt(0);
                                if (this.target != null) {
                                    this.xto = this.target.x;
                                    this.yto = this.target.y;
                                    this.mst.removeElementAt(0);
                                    this.canMove = true;
                                }
                            }
                        }
                    }

                    if (this.canMove && this.target != null) {
                        this.xto = this.target.x;
                        this.yto = this.target.y;
                        this.dx = (short) (this.xto - this.x);
                        this.dy = (short) (this.yto - 5 - this.y);
                        this.angle = (short) Util.angle(this.dx, this.dy);
                        vx = (short) (this.va * Util.cos(this.angle) >> 10);
                        vy = (short) (this.va * Util.sin(this.angle) >> 10);
                        var10000 = this.mychar;
                        var10000.x += vx;
                        var10000 = this.mychar;
                        var10000.y += vy;
                        this.x += vx;
                        this.y += vy;
                        if (this.mychar.yFly > 0) {
                            var10000 = this.mychar;
                            var10000.yFly = (short) (var10000.yFly - Utils.abs(vy));
                        }

                        if (this.mychar.yFly < 0) {
                            this.mychar.yFly = 0;
                        }

                        this.mychar.dir = Util.findDirection(this.mychar, this.target);
                        dx1 = (short) Math.abs(this.x - this.xto);
                        dy1 = (short) Math.abs(this.y - this.yto);
                        isMatchX = false;
                        isMatchY = false;
                        if (dx1 <= vx) {
                            this.x = (short) this.xto;
                            isMatchX = true;
                        }

                        if (dy1 <= vy) {
                            this.y = (short) this.yto;
                            isMatchY = true;
                        }

                        if (isMatchX && isMatchY) {
                            this.x = this.xto;
                            this.y = this.yto;
                            this.mychar.x = (short) this.xto;
                            this.mychar.y = (short) this.yto;
                            EffectManager.addHiDataeffectSkill(55, this.xto, this.yto - this.target.getHeight() / 2, 1);
                            this.wantDestroy = true;
                            this.mychar.yFly = 0;
                        }
                    }
                    break;
                case 3:
                    ++this.frame;
                    if (this.frame > 3) {
                        this.frame = 0;
                    }

                    if (this.target != null) {
                        if (this.target != null) {
                            this.xto = this.target.x;
                            this.yto = this.target.y;
                        }

                        this.dx = (short) (this.xto - this.x);
                        this.dy = (short) (this.yto - 5 - this.y);
                        this.angle = (short) Util.angle(this.dx, this.dy);
                        vx = (short) (this.va * Util.cos(this.angle) >> 10);
                        vy = (short) (this.va * Util.sin(this.angle) >> 10);
                        this.x += vx;
                        this.y += vy;
                        dx1 = (short) Math.abs(this.x - this.xto);
                        dy1 = (short) Math.abs(this.y - this.yto);
                        isMatchX = false;
                        isMatchY = false;
                        if (dx1 <= vx) {
                            this.x = (short) this.xto;
                            isMatchX = true;
                        }

                        if (dy1 <= vy) {
                            this.y = (short) this.yto;
                            isMatchY = true;
                        }

                        if (isMatchX && isMatchY) {
                            this.x = this.xto;
                            this.y = this.yto;
                            EffectManager.addHiDataeffectSkill(54, this.xto, this.yto - this.target.getHeight() / 2, 1);
                            if (this.mst.size() > 0) {
                                this.target = null;
                                this.target = (Actor) this.mst.elementAt(0);
                                this.xto = this.target.x;
                                this.yto = this.target.y;
                                this.mst.removeElementAt(0);
                            } else {
                                this.wantDestroy = true;
                            }
                        }
                    }
                    break;
                case 4:
                    ++this.frame;
                    if (this.frame >= 2) {
                        this.frame = 0;
                    }

                    if (this.mychar.yFly < 120 && !this.isFall) {
                        var10000 = this.mychar;
                        var10000.yFly = (short) (var10000.yFly + this.va);
                        this.va += 5;
                    }

                    if (this.mychar.yFly >= 120 && !this.isFall) {
                        this.isFall = true;
                        this.va = 5;
                    }

                    if (this.isFall) {
                        var10000 = this.mychar;
                        var10000.yFly = (short) (var10000.yFly - this.va);
                        this.va += 15;
                        if (this.mychar.yFly <= 10) {
                            this.mychar.yFly = 0;
                            --this.delay;
                            if (this.delay <= 0) {
                                EffectSkill.createHiEfect(this.mychar.x, this.mychar.y - 30, 4);
                                this.wantDestroy = true;
                                this.mychar.p1 = 0;
                                this.mychar.state = 2;

                                for (i = 0; i < this.mst.size(); ++i) {
                                    ac = (Actor) this.mst.elementAt(i);
                                    GameCanvas.gameScr.StartNewBaburan(0, this.mychar, ac, this.mychar.x, this.mychar.y - 30, 0, (byte) 0, 48);
                                }

                                this.mychar.setCanPaint(true);
                            }

                            if (!this.isaddEffect) {
                                this.isaddEffect = true;
                                EffectManager.addHiDataeffectSkill(59, this.mychar.x, this.mychar.y, 1);
                            }
                        }
                    }
                    break;
                case 5:
                    if (this.va > 0) {
                        for (i = 0; i < this.mst.size(); ++i) {
                            ac = (Actor) this.mst.elementAt(i);
                            if (ac != null && ac.getYfly() < 80) {
                                ac.setYfly(this.yFly);
                            }
                        }
                    }

                    if (this.yFly < 80) {
                        this.yFly += this.va;
                    }

                    if (this.va > 0) {
                        this.va /= 2;
                    }

                    if (this.va <= 0 && !this.isFall) {
                        for (i = 0; i < this.mst.size(); ++i) {
                            ac = (Actor) this.mst.elementAt(i);
                            if (ac != null) {
                                GameCanvas.gameScr.StartNewLazer(this.mychar.x, this.mychar.y - 40, ac.x, ac.y - ac.getYfly(), this.mychar, ac);
                                this.isFall = true;
                            }
                        }

                        EffectSkill.createHiEfect(this.mychar.x, this.mychar.y - 40, 30);
                        this.va = 0;
                        this.vy = 80;
                    }

                    if (this.isFall) {
                        if (this.delay >= 0) {
                            --this.delay;
                        }

                        if (this.delay < 0) {
                            for (i = 0; i < this.mst.size(); ++i) {
                                ac = (Actor) this.mst.elementAt(i);
                                if (ac != null) {
                                    ac.setYfly(this.vy);
                                    if (this.vy <= 0) {
                                        ac.setYfly(0);
                                        this.wantDestroy = true;
                                    }
                                }
                            }

                            this.vy -= this.speed;
                            this.speed += 5;
                        }
                    }
                    break;
                case 10:
                    this.y += this.vy;
                    if (this.vy <= 0) {
                        ++this.vy;
                        this.rotate = this.Arr_rotate[this.countRotate];
                        if (GameCanvas.gameTick % 3 == 0) {
                            ++this.countRotate;
                        }

                        if (this.countRotate > this.Arr_rotate.length - 1) {
                            this.countRotate = 0;
                        }
                    }

                    if (this.vy > 0) {
                        this.vy += this.speed;
                        ++this.speed;
                        this.rotate = 0;
                    }

                    if (this.vy > 40 && !this.isaddEffect) {
                        this.vy = 0;
                        this.wantDestroy = true;
                        EffectSkill.createLowEfect(this.x, this.y - 16, 27, 0);
                        EffectSkill.createHiEfect(this.x, this.y - 16, 43);
                    }
            }

        }
    }

    public void update() {
        this.updateva();
    }
}

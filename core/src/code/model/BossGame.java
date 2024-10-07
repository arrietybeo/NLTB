package code.model;

import code.main.GameCanvas;
import code.screen.Res;
import code.screen.screen.GameScr;
import lib.Cout;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;

public class BossGame extends Monster {
    public mVector AllSkillTemplate = new mVector();
    public DataSkillEff dataEff;
    public boolean isCreateEffAtMe;
    public boolean isCreateWeapon;
    int indexFrame;
    int currentFrame;

    public BossGame(int idTemplate) {
        super(idTemplate);
    }

    public BossGame() {
    }

    public boolean isBoss() {
        return true;
    }

    public void update() {
        super.update();
    }

    public boolean isBossBienBucCongTu() {
        return this.idTemplate == 101;
    }

    public boolean isBossTruongDoTe() {
        return this.idTemplate == 91;
    }

    public void doChangeFrameAttack() {
        int idSkill = this.getIDSkillBoss();
        Object obj = GameScr.ALL_SKILL_TEMPLATE_BOSS.get(String.valueOf(idSkill));
        if (obj != null) {
            SkillBossTemplate sk = (SkillBossTemplate) obj;
            byte[][] arrayFrame = sk.arrayAnimAttackUp;
            if (this.huongY == 1) {
                arrayFrame = sk.arrayAnimAttackDown;
            }

            byte[] tem = arrayFrame[this.indexFrame];
            this.frame = tem[this.currentFrame];
            ++this.currentFrame;
            if (this.currentFrame > tem.length - 1) {
                ++this.indexFrame;
                this.currentFrame = 0;
                if (this.indexFrame > arrayFrame.length - 1) {
                    this.indexFrame = 0;
                    this.frame = 0;
                    this.state = 0;
                    if (this.dataEff != null) {
                        this.dataEff.doDestroy();
                    }
                }
            }
        } else {
            Cout.Debug("KHONG TIM THAY SKILL DE DIEN");
        }

    }

    public void UpdateAttack() {
        ++this.p1;
        if (this.countAttack > 2) {
            this.state = 0;
        } else {
            this.doChangeFrameAttack();
        }
    }

    public void paint(mGraphics g) {
        if (this.state != 8 || this.tickDie > 0) {
            if (this.canpaint) {
                mVector data = new mVector();
                MonsterTemplate mons1 = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + this.idTemplate);
                if (mons1 != null) {
                    data = mons1.getDataMonster();
                }

                if (data.size() != 0) {
                    try {
                        this.paintIconPK(g);
                        DataEffect deff = (DataEffect) data.elementAt(0);
                        byte idShadow = 0;
                        if (deff != null) {
                            idShadow = deff.idShadow;
                            if (GameCanvas.gameScr.focusedActor != null && this.equals(GameCanvas.gameScr.focusedActor)) {
                                g.drawRegion(GameScr.imgtinhanh, 0, this.f * 14, 36, 14, 0, this.x, this.y + 7, 3, false);
                            }

                            this.paintBottomDataEff(g, this.getStartPointPaintFly());
                            MonsterTemplate mons = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + this.idTemplate);
                            if (mons != null) {
                                if (this.width == 0 || this.height == 0) {
                                    this.width = deff.getWith();
                                    this.height = deff.getHeight();
                                }

                                int status = this.state;
                                if (this.state == 9 && this.isStand) {
                                    status = 0;
                                }

                                if (idShadow > -1) {
                                    byte[] dxdyshadow = deff.getDxDyShadow(deff.getFrame(this.frame, status, this.huongY));
                                    int xsd;
                                    int ysd;
                                    if (this.isFly()) {
                                        if (this.canPaint()) {
                                            xsd = this.x + this.vangx + dxdyshadow[0];
                                            ysd = this.y - this._jum + this.vangy + this.vyStyleDie + dxdyshadow[1];
                                            g.drawImage(GameScr.imgShadow[idShadow], xsd, ysd, 0, false);
                                        }
                                    } else if (!this.isWater && this.canPaint()) {
                                        xsd = this.x + this.vangx + dxdyshadow[0];
                                        ysd = this.y - this._jum + this.vangy + this.vyStyleDie + dxdyshadow[1];
                                        g.drawImage(GameScr.imgShadow[idShadow], xsd, ysd, 0, false);
                                    }
                                }

                                if (this.canPaint()) {
                                    if (status != 3) {
                                        deff.paint(g, deff.getFrame(this.frame, status, this.huongY), this.x + this.vangx, this.y - this._jum + this.vangy - this.yFly + this.vyStyleDie, this.flip, mons.getImage(0));
                                    } else {
                                        try {
                                            deff.paint(g, this.frame, this.x + this.vangx, this.y - this._jum + this.vangy - this.yFly + this.vyStyleDie, this.flip, mons.getImage(0));
                                        } catch (Exception var11) {
                                            Cout.Debug("LOI NE " + this.frame + ". Trang thai : " + status);
                                        }
                                    }
                                }
                            }
                        }

                        this.paintTopDataEff(g, this.getStartPointPaintFly());
                        if (this.isWater && !this.isFly()) {
                            int ystart = this.state != 0 && this.dir != 1 && this.dir != 0 ? 40 : 0;
                            int xs = 0;
                            g.drawRegion(GameScr.imgWater, 0, ystart + GameCanvas.gameTick / 2 % 2 * 20, 44, 20, 0, this.x + xs, this.y, 3, false);
                        }

                        this.paintInfoFocus(g);
                    } catch (Exception var12) {
                        mSystem.println(" loi ne monster: " + this.ID + " > " + this.idTemplate + " >> " + var12.toString());
                    }

                }
            }
        }
    }

    public void doChangeFrameBoss() {
        if (this.state != 3) {
            mVector data = new mVector();
            MonsterTemplate mons1 = (MonsterTemplate) MonsterTemplate.ALL_MONSTER_TEMPLATE.get("" + this.idTemplate);
            if (mons1 != null) {
                data = mons1.getDataMonster();
            }

            DataEffect deff = null;
            if (data.size() > 0) {
                deff = (DataEffect) data.elementAt(0);
                int status = this.state;
                if (this.isStand) {
                    status = 0;
                }

                this.frame = (byte) ((this.frame + 1) % deff.getAnim(status, this.huongY).frame.length);
            }
        }

    }

    public int getFrameAttack(int f, int way) {
        int idSkill = this.getIDSkillBoss();
        Object obj = GameScr.ALL_SKILL_TEMPLATE_BOSS.get(String.valueOf(idSkill));
        if (obj != null) {
            SkillBossTemplate skill = (SkillBossTemplate) obj;
            byte[][] frame;
            if (way == 0) {
                frame = skill.arrayAnimAttackUp;
                return frame[this.indexFrame][f];
            } else {
                frame = skill.arrayAnimAttackDown;
                return frame[this.indexFrame][f];
            }
        } else {
            return 0;
        }
    }

    public byte[][] getArrayAnimFrameByIDSkill(int idSkill) {
        int size = this.AllSkillTemplate.size();

        for (int i = 0; i < size; ++i) {
            SkillBossTemplate tem = (SkillBossTemplate) this.AllSkillTemplate.elementAt(i);
            if (idSkill == tem.idSkill) {
                return tem.arrayAnimAttackUp;
            }
        }

        return null;
    }

    public void startAttack(int idSkill) {
        this.idSkill = (byte) idSkill;
        this.canpaint = true;
        if (this.state != 12) {
            if (this.timeLive > 0 || this.timeLive == -2) {
                if (this.typeattack == 1) {
                    this.savex = this.x;
                    this.savey = this.y;
                    this.state = 11;
                } else if (this.typeattack == 0) {
                    this.state = 6;
                    this.xTarget = (short) (this.target.x + (Res.random(10) < 5 ? 16 : -16));
                    this.yTarget = (short) (this.target.y + (Res.random(10) < 5 ? 16 : -16));
                } else {
                    this.state = 3;
                }
            }

            this.state = 3;
            this.idEffect = (short) idSkill;
            this.indexFrame = 0;
            this.isCreateEffAtMe = false;
            this.frame = 0;
            this.currentFrame = 0;
            this.isCreateWeapon = false;
            if (this.dataEff != null) {
                this.dataEff.doDestroy();
            }

            this.p1 = this.p2 = this.p3 = 0;
            this.countAttack = 0;
        }
    }
}

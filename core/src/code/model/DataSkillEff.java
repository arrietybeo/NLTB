package code.model;

import code.effect.EffectData;
import code.effect.new_skill.BigLaze;
import code.effect.new_skill.Eff_set;
import code.effect.new_skill.EffectSkill;
import code.effect.new_skill.Effect_Cung_T4;
import code.effect.new_skill.Effect_Sao_chop;
import code.effect.new_skill.Me_Arrow;
import code.effect.new_skill.New_Effect_Skill;
import code.effect.new_skill.Skill_Bua_T3;
import code.effect.new_skill.Skill_Bua_T4;
import code.effect.new_skill.Skill_Bua_T5;
import code.effect.new_skill.Skill_Cung_T3;
import code.effect.new_skill.Skill_Cung_T5;
import code.effect.new_skill.Skill_Dao_T1;
import code.effect.new_skill.Skill_Dao_T5;
import code.effect.new_skill.Skill_Dao_T6;
import code.effect.new_skill.Skill_Kiem_2019;
import code.effect.new_skill.Skill_Kiem_T2;
import code.effect.new_skill.Skill_Kiem_T4;
import code.effect.new_skill.Skill_Kiem_T5;
import code.effect.new_skill.Skill_Kiem_T7;
import code.effect.new_skill.Skill_Normal;
import code.effect.new_skill.Skill_Phapsu_T3;
import code.effect.new_skill.Skill_Phapsu_T5;
import code.effect.new_skill.Skill_TrucLam_2019;
import code.effect.new_skill.Skill_bua_T1;
import code.effect.new_skill.Skill_doc_2019;
import code.main.GameCanvas;
import code.model.arrow.Arrow;
import code.screen.Res;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Enumeration;

import lib.Tilemap;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSound;
import lib.mSystem;
import lib.mVector;

public class DataSkillEff extends Effect {
    public static mHashtable ALL_DATA_SKILL_EFF = new mHashtable();

    public byte Frame;

    public byte f;

    public boolean IsStop;

    public byte idSkill;

    public mVector mst = new mVector();

    public static byte TYPE_EFFECT_END = 0;

    public static byte TYPE_EFFECT_STARTSKILL = 1;

    public static byte TYPE_EFFECT_BUFF = 2;

    private int loop;

    public byte waitLoop;

    public int[] arrDame;

    public byte leavelPaint;

    public boolean isLoadData;

    public short indexEffect = 0;

    public static byte TYPE_NGA_MI_0 = 0;

    public static byte TYPE_NGA_MI_1 = 1;

    public static byte TYPE_NGA_MI_2 = 2;

    public static byte TYPE_NGU_DOC_0 = 3;

    public static byte TYPE_CAI_BANG_0 = 4;

    public static byte TYPE_CAI_BANG_1 = 5;

    public static byte TYPE_NGU_DOC_1 = 6;

    public static byte TYPE_THIEU_LAM_0 = 7;

    public static byte TYPE_THIEU_LAM_1 = 8;

    public static byte TYPE_THIEU_LAM_2 = 9;

    public static byte TYPE_VO_DANG_0 = 10;

    public static byte TYPE_VO_DANG_1 = 11;

    public static byte TYPE_NGU_DOC_2 = 12;

    public static byte TYPE_NGA_MI_3 = 13;

    public static byte TYPE_CAI_BANG_2 = 14;

    public static byte TYPE_VO_DANG_2 = 15;

    public static byte TYPE_NGU_DOC_3 = 16;

    public static byte TYPE_THIEU_LAM_3 = 17;

    public static byte TYPE_THIEU_LAM_4 = 18;

    public static byte TYPE_NGA_MI_4 = 19;

    public static byte TYPE_VO_DANG_3 = 20;

    public static byte TYPE_NGU_DOC_4 = 21;

    public static byte TYPE_NGA_MI_5 = 22;

    public static byte TYPE_CAI_BANG_3 = 23;

    public static byte TYPE_24 = 24;

    private Char mychar;

    New_Effect_Skill sk = null;

    public short idEff = 0;

    public boolean canremove;

    public boolean canActorMove;

    public boolean canPaintActor;

    public boolean canActorFight;

    public static byte ACTOR_NORMAL = 0;

    public static byte ACTOR_CAN_NOT_MOVE = 1;

    public static byte ACTOR_CAN_NOT_PAINT = 2;

    public byte typeupdate = 0;

    public static final byte NORMAL = 0;

    public static final byte REMOVE_BY_FRAME = 1;

    public static final byte REMOVE_BY_TIME = 2;

    public static final byte NO_REMOVE = 3;

    static int idTest = 0;

    public int idEffServer;

    public byte state_actor = 0;

    public boolean isbuff;

    public boolean isAddEffect;

    private byte transform;

    boolean isTrans;

    public int idSound;

    public int timeWaitSound;

    public long TimeLoopSound;

    byte delay;

    int idEffectData;

    public mVector vecmySkil;

    short[] arrRadian;

    public DataSkillEff() {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
    }

    public DataSkillEff(int id) {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
        this.indexEffect = (short) id;
        load();
        this.idSkill = -1;
    }

    public DataSkillEff(int id, byte transform) {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
        this.indexEffect = (short) id;
        load();
        this.idSkill = -1;
        this.transform = transform;
    }

    public DataSkillEff(int id, byte transform, int x, int y) {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
        this.indexEffect = (short) id;
        load();
        this.idSkill = -1;
        this.transform = transform;
        this.x = (short) x;
        this.y = (short) y;
        this.isTrans = true;
    }

    public DataSkillEff(int id, int x, int y) {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
        this.indexEffect = (short) id;
        load();
        this.idSkill = -1;
    }

    public DataSkillEff(int id, int x, int y, long timelive, boolean canmove, boolean canpaint, boolean canFight, int loop) {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
        this.idSound = -1;
        this.timelive = timelive;
        this.indexEffect = (short) id;
        load();
        this.idSkill = -1;
        if (timelive == -1L) {
            this.typeupdate = 3;
        } else if (timelive == 0L) {
            this.typeupdate = 1;
        } else {
            this.typeupdate = 2;
        }
        this.canActorMove = canmove;
        this.canPaintActor = canpaint;
        this.canActorFight = canFight;
        this.loop = loop;
    }

    public DataSkillEff(int id, int x, int y, long timelive) {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
        this.idSound = -1;
        this.timelive = timelive;
        this.indexEffect = (short) id;
        load();
        this.idSkill = -1;
        this.x = (short) x;
        this.y = (short) y;
        if (timelive == -1L) {
            this.typeupdate = 3;
            if (id == 164) {
                this.timeWaitSound = 5;
                this.idSound = 18;
                this.TimeLoopSound = mSystem.currentTimeMillis() + (this.timeWaitSound * 1000);
            }
            if (id == 210) {
                Tilemap.IsThac = true;
                this.timeWaitSound = 3;
                this.idSound = 20;
                mSound.playSound(this.idSound, mSound.volumeSound);
                this.TimeLoopSound = mSystem.currentTimeMillis() + (this.timeWaitSound * 1000);
            }
        } else if (timelive == 0L) {
            this.typeupdate = 1;
        } else {
            this.typeupdate = 2;
        }
    }

    public DataSkillEff(int id, int x, int y, long timelive, byte typeLoop) {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
        this.idSound = -1;
        this.timelive = timelive;
        this.indexEffect = (short) id;
        load();
        this.idSkill = -1;
        this.x = (short) x;
        this.y = (short) y;
        if (timelive == -1L) {
            this.typeupdate = 3;
            if (id == 164) {
                this.timeWaitSound = 5;
                this.idSound = 18;
                this.TimeLoopSound = mSystem.currentTimeMillis() + (this.timeWaitSound * 1000);
            }
            if (id == 210) {
                Tilemap.IsThac = true;
                this.timeWaitSound = 3;
                this.idSound = 20;
                mSound.playSound(this.idSound, mSound.volumeSound);
                this.TimeLoopSound = mSystem.currentTimeMillis() + (this.timeWaitSound * 1000);
            }
        } else if (timelive == 0L) {
            this.typeupdate = 1;
        } else {
            this.typeupdate = 2;
        }
        this.loop = typeLoop;
    }

    public DataSkillEff(int type, int x, int y, int lvpaint) {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
        this.indexEffect = (short) type;
        this.x = (short) x;
        this.y = (short) y;
        this.leavelPaint = (byte) lvpaint;
        load();
    }

    public DataSkillEff(int type, int x, int y, int lvpaint, int delay) {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
        this.indexEffect = (short) type;
        this.x = (short) x;
        this.y = (short) y;
        this.leavelPaint = (byte) lvpaint;
        load();
        this.delay = (byte) delay;
    }

    public DataSkillEff(int type, int x, int y, byte[] arr) {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
        this.indexEffect = (short) type;
        this.x = (short) x;
        this.y = (short) y;
        loadData(arr);
    }

    public DataSkillEff(byte[] array) {
        this.isbuff = false;
        this.idEffectData = 0;
        this.vecmySkil = new mVector();
        this.arrRadian = new short[]{0, 60, 120, 180, 240, 300};
        loadData(array);
    }

    public void load() {
        try {
            this.idEff = this.indexEffect;
            EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
            if (eff == null) {
                InputStream is = mSystem.getResourceAsStream("/datahd/effskill/" + this.idEff);
                if (is != null) {
                    DataInputStream dis = new DataInputStream(is);
                    short lenarr = (short) dis.available();
                    byte[] data = new byte[lenarr];
                    dis.read(data, 0, data.length);
                    EffectData efdt = new EffectData();
                    efdt.setdata(data);
                    efdt.timeremove = mSystem.currentTimeMillis() + 60000L;
                    GameData.listbyteData.put(this.idEff + Res.ID_START_SKILL, efdt);
                    setInfodata(eff);
                    try {
                        is.close();
                        dis.close();
                    } catch (Exception exception) {
                    }
                } else {
                    GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
                    return;
                }
            } else {
                eff.timeremove = mSystem.currentTimeMillis() + 60000L;
                setInfodata(eff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkremove() {
        Enumeration<String> k = GameData.listbyteData.keys();
        while (k.hasMoreElements()) {
            String keyset = k.nextElement();
            EffectData data = (EffectData) GameData.listbyteData.get(keyset);
            if (mSystem.currentTimeMillis() - data.timeremove >= 0L)
                GameData.listbyteData.remove(keyset);
        }
    }

    public void setInfo(int idskill, mVector target, int idStart) {
        this.idSkill = (byte) idskill;
        this.mst = target;
        if (idskill != 0) {
            this.indexEffect = (short) idStart;
            load();
        }
    }

    public void setInfo(int idskill, mVector target, int idStart, int idEff) {
        this.idSkill = (byte) idskill;
        this.mst = target;
        if (idskill != 0) {
            this.indexEffect = (short) idStart;
            load();
        }
        this.idEffServer = idEff;
    }

    public void setInfo(int idskill, mVector target) {
        this.idSkill = (byte) idskill;
        this.mst = target;
        if (idskill != 0) {
            setStartEff();
            load();
        }
    }

    public void setStartEff() {
        switch (this.idSkill) {
            case 1:
                this.indexEffect = 117;
                return;
            case 2:
                this.indexEffect = 128;
                return;
            case 3:
                this.indexEffect = 73;
                return;
            case 4:
                this.indexEffect = 68;
                return;
            case 5:
                this.indexEffect = 126;
                return;
            case 6:
                this.indexEffect = 48;
                return;
            case 7:
                this.indexEffect = 34;
                return;
            case 8:
                this.indexEffect = 43;
                return;
            case 9:
                this.indexEffect = 52;
                return;
            case 10:
                this.indexEffect = 65;
                return;
            case 11:
                this.indexEffect = 126;
                return;
            case 12:
                this.indexEffect = 126;
                return;
            case 13:
                this.indexEffect = 126;
                return;
            case 14:
                this.indexEffect = 126;
                return;
            case 15:
                this.indexEffect = 126;
                return;
            case 16:
                this.indexEffect = 126;
                return;
        }
        this.indexEffect = 126;
    }

    public void setInfodata(EffectData dinfo) {
        if (dinfo != null && dinfo.isLoadData)
            this.isLoadData = dinfo.isLoadData;
    }

    public void loadData(byte[] array) {
        this.isLoadData = true;
    }

    public boolean isHavedata() {
        if (this.isLoadData)
            return true;
        load();
        return false;
    }

    public byte[][] getFrameChar() {
        EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
        if (eff == null || (eff != null && !eff.isLoadData))
            return new byte[4][];
        return eff.frameChar;
    }

    public byte[] getIndexSplash() {
        EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
        if (eff == null || (eff != null && !eff.isLoadData))
            return null;
        return eff.indexSplash;
    }

    public void paintTopSkillChar(mGraphics g, int x, int y, boolean isPaint) {
        if (this.idSkill == 0)
            return;
        if (!isHavedata())
            return;
        if (!isPaint)
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(this.Frame);
            try {
                mVector listPart = frame.listPartTop;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        g.drawRegion(img.img, xx, yy, w, h, (part.flip == 1) ? 2 : 0, x + dx, y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTop(mGraphics g, int x, int y) {
        if (this.idSkill == 0)
            return;
        if (!isHavedata())
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(this.Frame);
            try {
                mVector listPart = frame.listPartTop;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        g.drawRegion(img.img, xx, yy, w, h, (part.flip == 1) ? 2 : 0, x + dx, y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTopEff(mGraphics g, int x, int y) {
        if (!isHavedata())
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(this.Frame);
            try {
                mVector listPart = frame.listPartTop;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        g.drawRegion(img.img, xx, yy, w, h, (part.flip == 1) ? 2 : 0, x + dx, y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                mSystem.println(" loi tai e  " + this.idEff);
            }
        }
    }

    public void paintTopArrow(mGraphics g, int x, int y) {
        if (!isHavedata())
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(this.Frame);
            try {
                mVector listPart = frame.listPartTop;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        g.drawRegion(img.img, xx, yy, w, h, (part.flip == 1) ? 2 : 0, x + dx, y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                mSystem.println(" loi tai e  " + this.idEff);
            }
        }
    }

    public void paintTopArrowFly(mGraphics g, int x, int y) {
        if (!isHavedata())
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(this.Frame);
            try {
                mVector listPart = frame.listPartTop;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        g.drawRegion(img.img, xx, yy, w, h, this.transform, x + dx, y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                mSystem.println(" loi tai e  " + this.idEff);
            }
        }
    }

    public void paintTop(mGraphics g, int dy) {
        if (!isHavedata())
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(this.Frame);
            try {
                mVector listPart = frame.listPartTop;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        g.drawRegion(img.img, xx, yy, w, h, (part.flip == 1) ? 2 : 0, this.x + dx, this.y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                mSystem.println(" loi tai e  " + this.idEff);
            }
        }
    }

    public void paintBottom(mGraphics g, int dyy) {
        if (!isHavedata())
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(this.Frame);
            try {
                mVector listPart = frame.listPartBottom;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        g.drawRegion(img.img, xx, yy, w, h, (part.flip == 1) ? 2 : 0, this.x + dx, this.y + part.dy + dyy, 0, false);
                    }
                }
            } catch (Exception e) {
                mSystem.println("loi ne " + this.indexEffect);
            }
        }
    }

    public void paintTop(mGraphics g, int x, int y, int flip, int Frame) {
        if (this.idSkill == 0)
            return;
        if (!isHavedata())
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(Frame);
            try {
                mVector listPart = frame.listPartTop;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        int rota = (part.flip == 1) ? 2 : 0;
                        if (flip == 2 || flip == 6) {
                            if (rota == 2) {
                                rota = 0;
                            } else {
                                rota = 2;
                            }
                            dx = -(dx + w);
                        }
                        g.drawRegion(img.img, xx, yy, w, h, rota, x + dx, y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottom(mGraphics g, int x, int y, int flip, int Frame) {
        if (!isHavedata())
            return;
        if (this.idSkill == 0)
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(Frame);
            try {
                mVector listPart = frame.listPartBottom;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        int rota = (part.flip == 1) ? 2 : 0;
                        if (flip == 2 || flip == 6) {
                            if (rota == 2) {
                                rota = 0;
                            } else {
                                rota = 2;
                            }
                            dx = -(dx + w);
                        }
                        g.drawRegion(img.img, xx, yy, w, h, rota, x + dx, y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                mSystem.println("loi ne " + this.indexEffect);
            }
        }
    }

    public void paintBottom(mGraphics g, int x, int y, boolean isPaint) {
        if (!isHavedata())
            return;
        if (this.idSkill == 0)
            return;
        if (!isPaint)
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(this.Frame);
            try {
                mVector listPart = frame.listPartBottom;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        g.drawRegion(img.img, xx, yy, w, h, (part.flip == 1) ? 2 : 0, x + dx, y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                mSystem.println("loi ne " + this.indexEffect);
            }
        }
    }

    public void paintBottomEff(mGraphics g, int x, int y) {
        if (!isHavedata())
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(this.Frame);
            try {
                mVector listPart = frame.listPartBottom;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        g.drawRegion(img.img, xx, yy, w, h, (part.flip == 1) ? 2 : 0, x + dx, y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                mSystem.println("loi ne " + this.indexEffect);
            }
        }
    }

    public void setFrame(int fr) {
        this.Frame = (byte) fr;
    }

    public void paint(mGraphics g) {
        if (this.idEff == 394 && this.delay >= 0)
            return;
        if (!isHavedata())
            return;
        if (this.isEffAuto && !GameCanvas.menuSelectitem.isTabFocus[2])
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(this.Frame);
            try {
                mVector listPart = frame.getListPartPaint();
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        g.drawRegion(img.img, xx, yy, w, h, (part.flip == 1) ? 2 : 0, this.x + dx, this.y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintTopHead(mGraphics g, int x, int y, int flip, int Frame) {
        if (this.idSkill == 0)
            return;
        if (!isHavedata())
            return;
        EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(Frame);
            try {
                mVector listPart = frame.listPartTop;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        int rota = (part.flip == 1) ? 2 : 0;
                        if (flip == 2 || flip == 6) {
                            if (rota == 2) {
                                rota = 0;
                            } else {
                                rota = 2;
                            }
                            dx = -(dx + w);
                        }
                        g.drawRegion(img.img, xx, yy, w, h, rota, x + dx, y, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottomHead(mGraphics g, int x, int y, int flip, int Frame) {
        if (!isHavedata())
            return;
        if (this.idSkill == 0)
            return;
        EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(Frame);
            try {
                mVector listPart = frame.listPartBottom;
                ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        int rota = (part.flip == 1) ? 2 : 0;
                        if (flip == 2 || flip == 6) {
                            if (rota == 2) {
                                rota = 0;
                            } else {
                                rota = 2;
                            }
                            dx = -(dx + w);
                        }
                        g.drawRegion(img.img, xx, yy, w, h, rota, x + dx, y, 0, false);
                    }
                }
            } catch (Exception e) {
                mSystem.println("loi ne " + this.indexEffect);
            }
        }
    }

    public void paint2(mGraphics g) {
        if (!isHavedata())
            return;
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(this.Frame);
            try {
                mVector listPart = frame.getListPartPaint();
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        g.drawRegion(img.img, xx, yy, w, h, (part.flip == 1) ? 2 : 0, this.x, this.y, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addDame() {
        if (this.idSkill != 2 && this.idSkill != 8 && this.idSkill != 9)
            for (int i = 0; i < this.vecmySkil.size(); i++) {
                Effect eff = (Effect) this.vecmySkil.elementAt(i);
                if (eff != null)
                    if (eff.getDame() <= 0)
                        eff.setDame(this.arrDame[i]);
            }
        if (this.idSkill == 2 || this.idSkill == 8) {
            Effect eff = (Effect) this.vecmySkil.elementAt(0);
            eff.arrDame = this.arrDame;
        }
        if (this.idSkill == 9)
            for (int i = 0; i < this.vecmySkil.size(); i++) {
                Arrow eff = (Arrow) this.vecmySkil.elementAt(i);
                if (eff != null)
                    if (eff.power <= 0)
                        eff.power = this.arrDame[i];
            }
    }

    public static byte idskillnew = 12;

    public long timelive;

    private long lasttime;

    public void updateSkillChar(Char c) {
        try {
            EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
            if (eff == null || (eff != null && !eff.isLoadData))
                return;
            if (this.idSkill == 30)
                c.setCanPaint(false);
            if (this.idSkill != 0 && eff.sequence != null) {
                this.f = (byte) (this.f + 1);
                if (this.f > eff.sequence.length)
                    this.f = 0;
                if (this.f < eff.sequence.length)
                    this.Frame = eff.sequence[this.f];
            }
            if (this.isAddEffect)
                return;
            if (this.idSkill == 0) {
                Actor tg1 = (Actor) this.mst.elementAt(0);
                if (tg1 != null) {
                    c.p1 = 0;
                    if (c.clazz == Char.THIEU_LAM)
                        mSound.playSound(5, mSound.volumeSound);
                    if (c.clazz == Char.VO_DANG || c.clazz == Char.NGA_MI)
                        mSound.playSound(2, mSound.volumeSound);
                    if (c.clazz == Char.CAI_BANG)
                        mSound.playSound(3, mSound.volumeSound);
                    if (c.clazz == Char.NGU_DOC)
                        mSound.playSound(4, mSound.volumeSound);
                    Skill_Normal skt8 = new Skill_Normal(c, tg1);
                    if (this.arrDame != null && this.arrDame.length > 0)
                        skt8.setDame(this.arrDame[0]);
                    if (c.equals(GameScr.mainChar))
                        this.vecmySkil.addElement(skt8);
                    EffectManager.addHiEffect((Effect) skt8);
                    this.isAddEffect = true;
                }
            }
            if (!this.isLoadData)
                return;
            int dir1 = c.dir;
            if (dir1 == 2)
                dir1 = 3;
            if (c.currentSkill != null && !c.currentSkill.isbuff && (this.idSkill > 21 || this.idSkill < 0))
                if (this.idSkill != 36 && this.idSkill != 35 && this.idSkill != 37 && this.idSkill != 34 && this.idSkill != 31) {
                    c.resetAllSkill();
                    c.setState(0);
                }
            if (c.currentSkill.getFrameChar()[dir1] != null && c.frameEff >= c.currentSkill.getIndexSplash()[dir1]) {
                this.isAddEffect = true;
                if (this.idSkill != 2 && !this.isbuff)
                    GameScr.addEffectByDir(c.dir, c.clazz, c);
                if (!c.isMainChar() && !GameCanvas.menuSelectitem.isTabFocus[2]) {
                    this.mst.removeAllElements();
                    return;
                }
                if (this.mst != null && this.mst.size() > 0) {
                    int i;
                    New_Effect_Skill nefk;
                    New_Effect_Skill ne30;
                    Skill_Phapsu_T3 PT3;
                    Eff_set s;
                    int k;
                    New_Effect_Skill ne32;
                    New_Effect_Skill ne33;
                    int m;
                    Skill_doc_2019 d35;
                    int n;
                    int size;
                    int j;
                    switch (this.idSkill) {
                        case 1:
                            mSound.playSound(6, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null) {
                                    Skill_Dao_T1 DT1 = new Skill_Dao_T1(c.x, c.y, ac.x, ac.y, 65);
                                    DT1.settarget(ac);
                                    DT1.setOwner(c);
                                    if (this.arrDame != null &&
                                            i < this.arrDame.length)
                                        DT1.setDame(this.arrDame[i]);
                                    if (c.equals(GameScr.mainChar))
                                        this.vecmySkil.addElement(DT1);
                                    EffectManager.addHiEffect((Effect) DT1);
                                    DT1 = null;
                                }
                            }
                        case 2:
                            mSound.playSound(7, mSound.volumeSound);
                            if (this.arrDame != null) {
                                New_Effect_Skill ne12 = new New_Effect_Skill(c, this.mst, (byte) 0, this.arrDame);
                                EffectManager.addHiEffect((Effect) ne12);
                                ne12 = null;
                            } else {
                                New_Effect_Skill ne12 = new New_Effect_Skill(c, this.mst, (byte) 0);
                                this.vecmySkil.addElement(ne12);
                                EffectManager.addHiEffect((Effect) ne12);
                                ne12 = null;
                            }
                        case 3:
                            mSound.playSound(8, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null) {
                                    Me_Arrow e = new Me_Arrow(c.x, (short) (c.y - 30), ac, (byte) 2, (byte) -2, (byte) 0, (byte) 30);
                                    if (this.arrDame != null &&
                                            i < this.arrDame.length)
                                        e.setDame(this.arrDame[i]);
                                    if (c.equals(GameScr.mainChar))
                                        this.vecmySkil.addElement(e);
                                    e.setOwner(c);
                                    EffectManager.addHiEffect((Effect) e);
                                    e = null;
                                }
                            }
                        case 4:
                            mSound.playSound(9, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null)
                                    if (this.arrDame != null && this.arrDame.length > 0) {
                                        Skill_Cung_T3 CT3 = new Skill_Cung_T3(c.x, (short) (c.y - 30), ac, c, (i < this.arrDame.length) ? this.arrDame[i] : 0);
                                        EffectManager.addHiEffect((Effect) CT3);
                                        CT3 = null;
                                    } else {
                                        Skill_Cung_T3 CT3 = new Skill_Cung_T3(c.x, (short) (c.y - 30), ac, c);
                                        EffectManager.addHiEffect((Effect) CT3);
                                        CT3 = null;
                                        if (c.equals(GameScr.mainChar))
                                            this.vecmySkil.addElement(CT3);
                                    }
                            }
                        case 5:
                            mSound.playSound(10, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null) {
                                    Me_Arrow b = new Me_Arrow(c.x, (short) (c.y - 30), ac.x, ac.y, (byte) 6, (byte) -3, (byte) 0, (byte) 30);
                                    b.setOwner(c);
                                    if (c.equals(GameScr.mainChar))
                                        this.vecmySkil.addElement(b);
                                    if (this.arrDame != null &&
                                            i < this.arrDame.length)
                                        b.setDame(this.arrDame[i]);
                                    b.settarget(ac);
                                    EffectManager.addHiEffect((Effect) b);
                                    b = null;
                                }
                            }
                        case 6:
                            mSound.playSound(11, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null) {
                                    Skill_Bua_T3 BT3 = new Skill_Bua_T3(c.x, c.y, ac);
                                    if (c.equals(GameScr.mainChar))
                                        this.vecmySkil.addElement(BT3);
                                    if (this.arrDame != null &&
                                            i < this.arrDame.length)
                                        BT3.setDame(this.arrDame[i]);
                                    BT3.setOwner(c);
                                    EffectManager.addLowEffect((Effect) BT3);
                                    BT3 = null;
                                }
                            }
                        case 7:
                            mSound.playSound(12, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null) {
                                    Skill_bua_T1 SBT1 = new Skill_bua_T1(c.x, c.y, ac, 0);
                                    if (c.equals(GameScr.mainChar))
                                        this.vecmySkil.addElement(SBT1);
                                    if (this.arrDame != null &&
                                            i < this.arrDame.length)
                                        SBT1.setDame(this.arrDame[i] / 3);
                                    SBT1.setOwner(c);
                                    EffectManager.addLowEffect((Effect) SBT1);
                                    SBT1 = null;
                                    Skill_bua_T1 SBT2 = new Skill_bua_T1(c.x, c.y, ac, 3);
                                    SBT2.setOwner(c);
                                    if (c.equals(GameScr.mainChar))
                                        this.vecmySkil.addElement(SBT2);
                                    if (this.arrDame != null &&
                                            i < this.arrDame.length)
                                        SBT2.setDame(this.arrDame[i] / 3);
                                    EffectManager.addLowEffect((Effect) SBT2);
                                    SBT2 = null;
                                    Skill_bua_T1 SBT3 = new Skill_bua_T1(c.x, c.y, ac, 6);
                                    SBT3.setOwner(c);
                                    if (c.equals(GameScr.mainChar))
                                        this.vecmySkil.addElement(SBT3);
                                    if (this.arrDame != null &&
                                            i < this.arrDame.length)
                                        SBT3.setDame(this.arrDame[i] / 3);
                                    EffectManager.addLowEffect((Effect) SBT3);
                                    SBT3 = null;
                                }
                            }
                        case 8:
                            mSound.playSound(13, mSound.volumeSound);
                            if (this.arrDame != null) {
                                Skill_Bua_T5 BT5 = new Skill_Bua_T5(c.x, c.y, this.mst, this.arrDame);
                                BT5.setOwner(c);
                                EffectManager.addLowEffect((Effect) BT5);
                                BT5 = null;
                            } else {
                                Skill_Bua_T5 BT51 = new Skill_Bua_T5(c.x, c.y, this.mst);
                                BT51.setOwner(c);
                                EffectManager.addLowEffect((Effect) BT51);
                                this.vecmySkil.addElement(BT51);
                                BT51 = null;
                            }
                        case 9:
                            mSound.playSound(14, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null)
                                    if (this.arrDame != null) {
                                        Arrow a = new Arrow();
                                        int dame = 0;
                                        if (i < this.arrDame.length)
                                            dame = this.arrDame[i];
                                        a.set(c.x, c.y, c, ac, dame, 122, 123, 124, 135, (byte) 1, (byte) 20);
                                        GameScr.arrowsUp.addElement(a);
                                    } else {
                                        Arrow a2 = new Arrow();
                                        a2.set(c.x, c.y, c, ac, 0, 122, 123, 124, 135, (byte) 1, (byte) 20);
                                        GameScr.arrowsUp.addElement(a2);
                                        this.vecmySkil.addElement(a2);
                                    }
                            }
                        case 10:
                            mSound.playSound(15, mSound.volumeSound);
                            GameScr.timeVibrateScreen = 103;
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null) {
                                    Skill_Bua_T4 SBT4 = new Skill_Bua_T4(c.x, c.y, ac);
                                    SBT4.setOwner(c);
                                    if (c.equals(GameScr.mainChar))
                                        this.vecmySkil.addElement(SBT4);
                                    if (this.arrDame != null &&
                                            i < this.arrDame.length)
                                        SBT4.setDame(this.arrDame[i]);
                                    EffectManager.addHiEffect((Effect) SBT4);
                                    SBT4 = null;
                                }
                            }
                        case 11:
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null) {
                                    Skill_Dao_T5 s11 = new Skill_Dao_T5((short) (ac.x - 80), (short) (ac.y - 40), ac);
                                    if (this.arrDame != null &&
                                            i < this.arrDame.length)
                                        s11.setDame(this.arrDame[i]);
                                    if (c.equals(GameScr.mainChar))
                                        this.vecmySkil.addElement(s11);
                                    EffectManager.addHiEffect((Effect) s11);
                                    s11 = null;
                                }
                            }
                        case 12:
                            mSound.playSound(21, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null) {
                                    EffectManager.addHiDataeffectSkill(63, ac.x, ac.y, 1);
                                    if (this.arrDame != null && i < this.arrDame.length)
                                        startFlyText(this.arrDame[i], 0, ac.x, ac.y, 0, 0, (c.x > ac.x) ? -1 : 1);
                                }
                            }
                        case 13:
                            mSound.playSound(22, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null)
                                    ac.jumpVang(this.Owner);
                                for (int i1 = 0; i1 < 5; i1++) {
                                    Effect_Cung_T4 effect_Cung_T4 = new Effect_Cung_T4(c.x, c.y - 25, ac.x, ac.y, i1 * 3, 13, c, ac);
                                    if (this.arrDame != null && i < this.arrDame.length)
                                        effect_Cung_T4.setDame(this.arrDame[i] / 5);
                                    if (c.equals(GameScr.mainChar))
                                        this.vecmySkil.addElement(effect_Cung_T4);
                                    EffectManager.addHiEffect((Effect) effect_Cung_T4);
                                }
                            }
                        case 14:
                            mSound.playSound(24, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                Skill_Kiem_T4 KT4 = new Skill_Kiem_T4(c.x, c.y, ac.x, ac.y, c, ac);
                                if (this.arrDame != null && i < this.arrDame.length)
                                    KT4.setDame(this.arrDame[i]);
                                if (c.equals(GameScr.mainChar))
                                    this.vecmySkil.addElement(KT4);
                                EffectManager.addHiEffect((Effect) KT4);
                            }
                        case 15:
                            mSound.playSound(25, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                Skill_Dao_T6 DT6 = new Skill_Dao_T6(c.x, c.y, c, ac);
                                if (this.arrDame != null && i < this.arrDame.length)
                                    DT6.setDame(this.arrDame[i]);
                                if (c.equals(GameScr.mainChar))
                                    this.vecmySkil.addElement(DT6);
                                EffectManager.addLowEffect((Effect) DT6);
                            }
                        case 16:
                            mSound.playSound(23, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                Skill_Kiem_T7 skill_Kiem_T7 = new Skill_Kiem_T7(ac.x, ac.y, c);
                                if (this.arrDame != null && i < this.arrDame.length)
                                    skill_Kiem_T7.setDame(this.arrDame[i] / 4);
                                EffectManager.addHiEffect((Effect) skill_Kiem_T7);
                            }
                        case 17:
                            mSound.playSound(30, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null) {
                                    EffectManager.addHiDataeffectSkill(56, ac.x, ac.y, 1);
                                    if (this.arrDame != null && i < this.arrDame.length)
                                        startFlyText(this.arrDame[i], 0, ac.x, ac.y, 0, 0, (c.x > ac.x) ? -1 : 1);
                                }
                            }
                        case 18:
                            mSound.playSound(31, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                int dam = 0;
                                if (this.arrDame != null && i < this.arrDame.length)
                                    dam = this.arrDame[i];
                                Skill_Kiem_T5 KT5 = new Skill_Kiem_T5(ac.x, ac.y, dam, c, ac);
                                EffectManager.addHiEffect((Effect) KT5);
                            }
                        case 19:
                            mSound.playSound(32, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                int dam = 0;
                                if (ac != null) {
                                    if (this.arrDame != null && i < this.arrDame.length)
                                        dam = this.arrDame[i] / 2;
                                    EffectSkill.createHiEfect(ac.x, ac.y, 67);
                                    EffectSkill.createLowEfect(ac.x, ac.y, 14);
                                    startFlyText(dam, 0, ac.x, ac.y, 0, 0, (c.x > ac.x) ? -1 : 1);
                                    EffectManager.addHiDataeffectSkill(57, ac.x, ac.y, 1);
                                    Skill_Kiem_T2 KT2 = new Skill_Kiem_T2(c, ac, dam);
                                    EffectManager.addHiEffect((Effect) KT2);
                                }
                            }
                        case 20:
                            mSound.playSound(33, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor targ = (Actor) this.mst.elementAt(i);
                                if (targ != null) {
                                    int dam = 0;
                                    if (this.arrDame != null && i < this.arrDame.length)
                                        dam = this.arrDame[i];
                                    Skill_Cung_T5 CT5 = new Skill_Cung_T5(c.x, c.y, targ, c, dam);
                                    EffectManager.addHiEffect((Effect) CT5);
                                }
                            }
                        case 21:
                            mSound.playSound(34, mSound.volumeSound);
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                if (ac != null) {
                                    int dam = 0;
                                    if (this.arrDame != null && i < this.arrDame.length)
                                        dam = this.arrDame[i] / 2;
                                    startFlyText(dam, 0, ac.x, ac.y, 0, 0, (c.x > ac.x) ? -1 : 1);
                                    EffectManager.addHiDataeffectSkill(11, ac.x, ac.y, 1);
                                    GameCanvas.gameScr.startNewMagicBeam(18, c, ac, c.x, c.y, dam, 16);
                                }
                            }
                        case 22:
                            return;
                        case 23:
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 24:
                            this.mst.removeAllElements();
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 25:
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                Skill_Phapsu_T5 PT5 = new Skill_Phapsu_T5(c, ac);
                                EffectManager.addHiEffect((Effect) PT5);
                            }
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 26:
                            for (i = 0; i < this.mst.size(); i++) {
                                Actor ac = (Actor) this.mst.elementAt(i);
                                Skill_bua_T1 SBT1 = new Skill_bua_T1(c.x, c.y, ac, 0);
                                EffectManager.addLowEffect((Effect) SBT1);
                            }
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 27:
                            nefk = new New_Effect_Skill(c, this.mst, (byte) 11);
                            EffectManager.addHiEffect((Effect) nefk);
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 28:
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 29:
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 30:
                            c.setCanPaint(true);
                            ne30 = new New_Effect_Skill(c, this.mst, (byte) 14);
                            EffectManager.addHiEffect((Effect) ne30);
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 31:
                            PT3 = new Skill_Phapsu_T3(c.x, c.y, this.mst);
                            EffectManager.addHiEffect((Effect) PT3);
                            s = new Eff_set(c.x, c.y);
                            EffectManager.addHiEffect((Effect) s);
                            c.resetAllSkill();
                            for (k = 0; k < this.mst.size(); k++) {
                                Actor ac = (Actor) this.mst.elementAt(k);
                                if (ac != null) {
                                    int dam = 0;
                                    if (this.arrDame != null && k < this.arrDame.length)
                                        dam = this.arrDame[k];
                                    startFlyText(dam, 0, ac.x, ac.y, 0, 0, (c.x > this.x) ? -1 : 1);
                                    if (ac.getHp() <= 0)
                                        LiveActor.startDeadFly(ac, c.ID);
                                }
                            }
                        case 32:
                            ne32 = new New_Effect_Skill(c, this.mst, (byte) 10);
                            EffectManager.addHiEffect((Effect) ne32);
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 33:
                            ne33 = new New_Effect_Skill(c, this.mst, (byte) 17);
                            EffectManager.addHiEffect((Effect) ne33);
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 34:
                            for (m = 0; m < this.mst.size(); m++) {
                                Actor ac = (Actor) this.mst.elementAt(m);
                                if (ac != null) {
                                    int dam = 0;
                                    if (this.arrDame != null && m < this.arrDame.length)
                                        dam = this.arrDame[m];
                                    startFlyText(dam, 0, ac.x, ac.y, 0, 0, (c.x > this.x) ? -1 : 1);
                                    if (ac.getHp() <= 0)
                                        LiveActor.startDeadFly(ac, c.ID);
                                    BigLaze b = new BigLaze(ac.x, ac.y, ac, m * 3);
                                    EffectManager.addHiEffect((Effect) b);
                                }
                            }
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 35:
                            d35 = new Skill_doc_2019(this.mst);
                            EffectManager.addHiEffect((Effect) d35);
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                            for (n = 0; n < this.mst.size(); n++) {
                                Actor ac = (Actor) this.mst.elementAt(n);
                                if (ac != null) {
                                    int dam = 0;
                                    if (this.arrDame != null && n < this.arrDame.length)
                                        dam = this.arrDame[n];
                                    startFlyText(dam, 0, ac.x, ac.y, 0, 0, (c.x > this.x) ? -1 : 1);
                                    if (ac.getHp() <= 0)
                                        LiveActor.startDeadFly(ac, c.ID);
                                }
                            }
                        case 36:
                            for (n = 0; n < this.mst.size(); n++) {
                                Actor ac = (Actor) this.mst.elementAt(n);
                                if (ac != null) {
                                    int dam = 0;
                                    if (this.arrDame != null && n < this.arrDame.length)
                                        dam = this.arrDame[n];
                                    startFlyText(dam, 0, ac.x, ac.y, 0, 0, (c.x > this.x) ? -1 : 1);
                                    if (ac.getHp() <= 0)
                                        LiveActor.startDeadFly(ac, c.ID);
                                    Skill_TrucLam_2019 tl = new Skill_TrucLam_2019(ac.x, ac.y, n * 5);
                                    EffectManager.addHiEffect((Effect) tl);
                                }
                            }
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 37:
                            for (n = 0; n < this.mst.size(); n++) {
                                Actor ac = (Actor) this.mst.elementAt(n);
                                if (ac != null) {
                                    int dam = 0;
                                    if (this.arrDame != null && n < this.arrDame.length)
                                        dam = this.arrDame[n];
                                    startFlyText(dam, 0, ac.x, ac.y, 0, 0, (c.x > this.x) ? -1 : 1);
                                    if (ac.getHp() <= 0)
                                        LiveActor.startDeadFly(ac, c.ID);
                                    Skill_Kiem_2019 tl = new Skill_Kiem_2019(ac.x, ac.y, n * 5);
                                    EffectManager.addHiEffect((Effect) tl);
                                    for (int i1 = 0; i1 < 3; i1++) {
                                        Skill_Kiem_2019 tl2 = new Skill_Kiem_2019(ac.x + Res.random_Am(40, 50), ac.y + Res.random_Am(20, 50), (n == 0) ? 1 : (n * 7));
                                        EffectManager.addHiEffect((Effect) tl2);
                                    }
                                }
                            }
                            size = 5;
                            if (this.mst.size() == 1)
                                size = 8;
                            for (j = 0; j < size; j++) {
                                Actor ac = (Actor) this.mst.elementAt(0);
                                if (ac != null) {
                                    Skill_Kiem_2019 tl2 = new Skill_Kiem_2019(ac.x + Res.random_Am(40, 50), ac.y + Res.random_Am(20, 50), (j == 0) ? 1 : (j * 7));
                                    EffectManager.addHiEffect((Effect) tl2);
                                }
                            }
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 38:
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 39:
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 40:
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 41:
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 42:
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 43:
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 44:
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                        case 45:
                            if (c.equals(GameScr.mainChar))
                                c.resetAllSkill();
                    }
                    if (c.equals(GameScr.mainChar))
                        c.resetAllSkill();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMychar(Char mychar) {
        this.mychar = mychar;
    }

    public void updateArrow() {
        if (!this.isLoadData)
            return;
        EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        try {
            this.f = (byte) (this.f + 1);
            if (this.f > eff.sequence.length - 1)
                this.f = 0;
            this.Frame = eff.sequence[this.f];
        } catch (Exception exception) {
        }
    }

    public void updateEffCharDie() {
        if (!this.isLoadData)
            return;
        EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (this.f < eff.sequence.length)
            this.f = (byte) (this.f + 1);
        if (this.f > eff.sequence.length - 1)
            this.f = (byte) (eff.sequence.length - 1);
        this.Frame = eff.sequence[this.f];
    }

    public void setLoop(int loop) {
        this.loop = loop;
    }

    public byte[] getSequence() {
        EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
        if (eff == null || (eff != null && !eff.isLoadData))
            return null;
        if (eff != null && !this.isLoadData)
            this.isLoadData = eff.isLoadData;
        return eff.sequence;
    }

    public void update() {
        if (this.idEff == 394 &&
                this.delay >= 0) {
            this.delay = (byte) (this.delay - 1);
            return;
        }
        EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (eff != null && !this.isLoadData)
            this.isLoadData = eff.isLoadData;
        if (eff.listFrame.size() <= 0)
            return;
        try {
            switch (this.typeupdate) {
                case 0:
                    this.f = (byte) (this.f + 1);
                    if (this.idEff == 11 &&
                            this.f == 15) {
                        EffectSkill.createLowEfect(this.x, this.y + 20, 55);
                        GameScr.timeVibrateScreen = Res.random(1, 5);
                    }
                    if (this.idEff == 394 &&
                            this.f == 10)
                        for (int i = 0; i < 3; i++) {
                            Effect_Sao_chop ef = new Effect_Sao_chop(this.x, this.y, 24);
                            EffectManager.addHiEffect((Effect) ef);
                        }
                    if (this.f > eff.sequence.length) {
                        if (!this.canremove)
                            this.wantDestroy = true;
                        this.f = 0;
                        if (this.idEff == 63)
                            GameScr.timeVibrateScreen = Res.random(1, 5);
                        if (this.mychar != null && !this.mychar.canPaint_)
                            this.mychar.setCanPaint(true);
                    }
                    this.Frame = eff.sequence[this.f];
                    break;
                case 1:
                    this.f = (byte) (this.f + 1);
                    if (this.f > eff.sequence.length) {
                        this.f = 0;
                        this.wantDestroy = true;
                    }
                    this.Frame = eff.sequence[this.f];
                    break;
                case 2:
                    this.f = (byte) (this.f + 1);
                    if (this.f > eff.sequence.length)
                        if (this.loop == 0) {
                            this.f = (byte) (eff.sequence.length - 1);
                        } else if (this.waitLoop > 0) {
                            if (mSystem.currentTimeMillis() - this.lasttime > (this.waitLoop * 1000)) {
                                this.f = 0;
                                this.lasttime = mSystem.currentTimeMillis();
                            }
                        } else {
                            this.f = 0;
                        }
                    if (this.timelive - mSystem.currentTimeMillis() < 0L)
                        this.wantDestroy = true;
                    if (this.f < eff.sequence.length)
                        this.Frame = eff.sequence[this.f];
                    break;
                case 3:
                    if (this.idSound != -1 && this.idSound > 0 &&
                            this.TimeLoopSound - mSystem.currentTimeMillis() <= 0L) {
                        mSound.playSound(this.idSound, mSound.volumeSound);
                        this.TimeLoopSound = mSystem.currentTimeMillis() + (this.timeWaitSound * 1000);
                    }
                    this.f = (byte) (this.f + 1);
                    if (this.f > eff.sequence.length)
                        if (this.waitLoop > 0) {
                            if (mSystem.currentTimeMillis() - this.lasttime > (this.waitLoop * 1000)) {
                                this.f = 0;
                                this.lasttime = mSystem.currentTimeMillis();
                            }
                        } else {
                            this.f = 0;
                        }
                    if (this.f < eff.sequence.length)
                        this.Frame = eff.sequence[this.f];
                    break;
            }
        } catch (Exception exception) {
        }
    }

    public void update(int frame) {
        this.Frame = this.Frame;
    }

    public boolean isFlydame() {
        for (int i = 0; i < this.vecmySkil.size(); i++) {
            Effect eff = (Effect) this.vecmySkil.elementAt(i);
            if (eff.isDame0)
                return true;
        }
        return false;
    }

    public int getWidth() {
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return 0;
        return eff.fw;
    }

    public int getHeight() {
        EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
        if (eff == null || (eff != null && !eff.isLoadData))
            return 0;
        return eff.fh;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setWaitLoop(byte wait) {
        this.waitLoop = wait;
    }

    public void paintTopPhiPhong(mGraphics g, int x, int y, int flip, int Frame) {
        if (this.idSkill == 0)
            return;
        if (!isHavedata())
            return;
        EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(Frame);
            try {
                mVector listPart = frame.listPartTop;
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        int rota = (part.flip == 1) ? 2 : 0;
                        if (flip == 2 || flip == 6) {
                            if (rota == 2) {
                                rota = 0;
                            } else {
                                rota = 2;
                            }
                            dx = -(dx + w);
                        }
                        g.drawRegion(img.img, xx, yy, w, h, rota, x + dx, y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintBottomPhiPhong(mGraphics g, int x, int y, int flip, int Frame) {
        if (!isHavedata())
            return;
        if (this.idSkill == 0)
            return;
        EffectData eff = GameData.getByteData((short) (this.idEff + Res.ID_START_SKILL));
        if (eff == null || (eff != null && !eff.isLoadData))
            return;
        if (Frame < eff.listFrame.size()) {
            FrameEff frame = (FrameEff) eff.listFrame.elementAt(Frame);
            try {
                mVector listPart = frame.listPartBottom;
                ImageIcon img = GameData.getImgIcon((short) (this.idEff + Res.ID_START_SKILL));
                for (int i = 0; i < listPart.size(); i++) {
                    PartFrame part = (PartFrame) listPart.elementAt(i);
                    SmallImage small = eff.smallImage[part.idSmallImg];
                    if (img != null && img.img != null) {
                        int dx = part.dx;
                        int w = small.w;
                        int h = small.h;
                        int xx = small.x;
                        int yy = small.y;
                        if (xx > img.img.getWidth())
                            xx = 0;
                        if (yy > img.img.getHeight())
                            yy = 0;
                        if (xx + w > img.img.getWidth())
                            w = img.img.getWidth() - xx;
                        if (yy + h > img.img.getHeight())
                            h = img.img.getHeight() - yy;
                        int rota = (part.flip == 1) ? 2 : 0;
                        if (flip == 2 || flip == 6) {
                            if (rota == 2) {
                                rota = 0;
                            } else {
                                rota = 2;
                            }
                            dx = -(dx + w);
                        }
                        g.drawRegion(img.img, xx, yy, w, h, rota, x + dx, y + part.dy, 0, false);
                    }
                }
            } catch (Exception e) {
                mSystem.println("loi ne " + this.indexEffect);
            }
        }
    }

    public int countTotalFrame() {
        EffectData eff = (EffectData) GameData.listbyteData.get(this.idEff + Res.ID_START_SKILL);
        if (eff == null || (eff != null && !eff.isLoadData))
            return 0;
        return eff.listFrame.size();
    }
}

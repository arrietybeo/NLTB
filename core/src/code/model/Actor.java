package code.model;

import code.main.GameCanvas;
import lib.mGraphics;
import lib.mRandom;
import lib.mSystem;
import lib.mVector;

public abstract class Actor {
    public static final byte CAT_PLAYER = 0;
    public static final byte CAT_MONSTER = 1;
    public static final byte CAT_NPC = 2;
    public static final byte CAT_ITEM = 3;
    public static final byte CAT_POTION = 4;
    public static final byte CAT_PARTY = 5;
    public static final byte CAT_MATERIAL = 6;
    public static final byte CAT_MY_GROUND = 10;
    public static final byte CAT_EXPLOTION = 126;
    public static final byte CAT_MY_TREE = 11;
    public static final byte CAT_MY_PET = 12;
    public static final byte CAT_TREE = 13;
    public static final byte CAT_ITEM_QUEST = 14;
    public static final byte CAT_GEM_ITEM = 6;
    public static final byte PK_NORMAL = -1;
    public static final byte PK_DO_SAT = 0;
    public static final byte PK_RED = 1;
    public static final byte PK_GREEN = 2;
    public static final byte PK_BLUE = 3;
    public static final byte PK_YELLOW = 4;
    public static final byte PK_ORANGE = 5;
    public static final byte PK_DO_SAT_VANG = 6;
    public ChatPopup chat = null;
    public boolean wantDestroy = false;
    public byte catagory;
    public byte typeFocus;
    public short ID;
    public short x;
    public short y;
    public static mRandom r = new mRandom(mSystem.currentTimeMillis());

    public void paint(mGraphics g) {
    }

    public void paintInfoFocus(mGraphics g) {
    }

    public void update() {
    }

    public byte getItemcolor() {
        return 0;
    }

    public void setColorname(byte color) {
    }

    public void jum() {
    }

    public void setYfly(int yFly) {
    }

    public int getYfly() {
        return 0;
    }

    public void jumvang() {
    }

    public void jumpVang(Actor causeBy) {
    }

    public void vang() {
    }

    public void setXYflyto(short x, short y) {
    }

    public void settimevang(short x) {
    }

    public void setPosition(int x, int y) {
    }

    public boolean allwayPaint() {
        return false;
    }

    public void setRealHP(int realHP) {
    }

    public String getDisplayName() {
        return "Actor C=" + this.catagory + " ID=" + this.ID;
    }

    public void paintCorner(mGraphics g, int xCorner, int yCorner) {
        g.translate(-this.x + xCorner, -this.y + yCorner);
        this.paint(g);
    }

    public void paintName(mGraphics g) {
    }

    public void actorDie() {
    }

    public boolean isDropItem() {
        return false;
    }

    public MonsterTemplate getMonsterTemplate() {
        return null;
    }

    public byte getPlus() {
        return 0;
    }

    public int getTypeNpc() {
        return 32000;
    }

    public boolean isNPC() {
        return false;
    }

    public boolean isMonster() {
        return false;
    }

    public boolean isMineral() {
        return false;
    }

    public int getX() {
        return this.x;
    }

    public void startAttack(mVector target, int idskill, int idStart) {
    }

    public void startAttack(mVector target, int idskill, int idStart, int[] arrdame) {
    }

    public void startAttack(mVector target, int idskill) {
    }

    public int getY() {
        return this.y;
    }

    public boolean isPlayer() {
        return false;
    }

    public int getNpcType() {
        return 0;
    }

    public boolean isItem() {
        return false;
    }

    public boolean isPotion() {
        return false;
    }

    public boolean isGem() {
        return false;
    }

    public boolean isItemCanGet() {
        return false;
    }

    public void setMove(int x, int y) {
    }

    public void moveTo(short xTo, short yTo) {
    }

    public boolean isNpcChar() {
        return false;
    }

    public void setInfoWearing(short[] listPart) {
    }

    public void setEffWeapon(int id) {
    }

    public boolean isTree() {
        return false;
    }

    public boolean inCamera(int cmx, int cmy) {
        return this.x + 50 >= cmx && this.x - 50 <= cmx + GameCanvas.w && this.y + 50 >= cmy && this.y - 50 <= cmy + GameCanvas.h;
    }

    public void setName(String name) {
    }

    public String getName() {
        return "";
    }

    public int getHeight() {
        return 0;
    }

    public int getWidth() {
        return 0;
    }

    public int getDx() {
        return 0;
    }

    public int getDy() {
        return 0;
    }

    public int getDir() {
        return 0;
    }

    public int getTimeLive() {
        return -1;
    }

    public boolean isDie() {
        return false;
    }

    public void setDie() {
    }

    public void setIsFocus(boolean isFocus) {
    }

    public int getState() {
        return 0;
    }

    public void setState(int state) {
    }

    public boolean isKiller() {
        return false;
    }

    public int getIdClan() {
        return -1;
    }

    public byte getNation() {
        return -1;
    }

    public byte getPk() {
        return -1;
    }

    public int getRealHp() {
        return 0;
    }

    public int getHp() {
        return 0;
    }

    public int getMaxHp() {
        return 0;
    }

    public int getMp() {
        return 0;
    }

    public int getMaxMp() {
        return 0;
    }

    public byte getTypeLimit() {
        return 0;
    }

    public int getIdBoss() {
        return -1;
    }

    public int getLevel() {
        return -1;
    }

    public void setHp(int hp) {
    }

    public void addEffectWhenActorDie(int ideffect) {
    }

    public void setKiller(int nKiller) {
    }

    public void setMove2Target(Actor target) {
    }

    public void SetPaintFocus() {
    }

    public void setpaint(boolean canPaint) {
    }

    public void setMove(boolean canmove) {
    }

    public void addDataeff(int id) {
    }

    public void addEffectSkill(int id, int x, int y) {
    }

    public void addEffectSkill(DataSkillEff eff, int x, int y) {
    }

    public void DropHP(int hp) {
    }

    public void setCanrevives(boolean canrevives) {
    }

    public void setTypeItem(byte type) {
    }

    public int getTypeItem() {
        return 5;
    }

    public boolean isMainChar() {
        return false;
    }

    public void setEquipChar(Item[] it) {
    }

    public void setCoolDown(int time) {
    }

    public long getCoolDown() {
        return 0L;
    }

    public byte getClazz() {
        return 0;
    }

    public boolean getCanpaint() {
        return true;
    }

    public void starAttackbossOffline(int idskill, mVector target) {
    }

    public void setDir(byte dir) {
    }

    public void setmuntiTarget(mVector target) {
    }

    public void startDeadFly(int dx, int dy, int time, int vyStyle) {
    }

    public void resetAllSkill() {
    }

    public void setDame(int dame) {
    }

    public void comehome() {
    }

    public int getDame() {
        return 0;
    }

    public String getStrTalk() {
        return "";
    }

    public String getCmdName() {
        return "";
    }

    public void removechat() {
    }

    public int getStateQuest() {
        return 0;
    }

    public short getIDicon() {
        return -1;
    }

    public int getIDNpc() {
        return -1;
    }

    public void setTimelive(long time) {
    }

    public void setlvPercent(int lv) {
    }

    public void setLV(int lv) {
    }

    public void addEffectSkillTime(int id, int x, int y, long timelive) {
    }

    public void setMP(int mp) {
    }

    public void startBuff(int idStart) {
    }

    public boolean canFire() {
        return false;
    }

    public boolean canFocus() {
        return true;
    }

    public void setCanFocus(boolean canFocus) {
    }

    public void setTypePK(byte typePK) {
    }

    public byte getTypePK() {
        return 0;
    }

    public int getDyWater() {
        return 0;
    }

    public void addEffectSkillTime(int id, int x, int y, long timelive, boolean canmove, boolean canpaint, boolean canFight, int loop, byte waitLoop) {
    }

    public byte getColorName() {
        return 0;
    }

    public void setidNhom(int id) {
    }

    public void setFlyDame(int[] dame) {
    }

    public int getTypeMove() {
        return -1;
    }

    public boolean beFire() {
        return false;
    }

    public void SetbeFire(boolean beFire) {
    }

    public void dieActor(Actor from) {
    }

    public boolean isTruRong() {
        return false;
    }

    public void startAttackMainChar(mVector target, int idskill, int idStart, int idEffect) {
    }

    public void setTickDie(int value) {
    }

    public void setIsCatSkill(boolean iscat) {
    }

    public boolean getIsCatSkill() {
        return false;
    }

    public void Flash(int mXto, int mYto, int range) {
    }

    public int getColorItem() {
        return -1;
    }

    public boolean canFocusMonster() {
        return true;
    }

    public boolean isBangKhu() {
        return false;
    }

    public int getXmonster() {
        return this.x;
    }

    public int getYmonster() {
        return this.y;
    }

    public int getIDparty() {
        return -1;
    }

    public void setMaxHP(int maxhp) {
    }

    public void setMaxMP(int maxmp) {
    }

    public boolean canInvite() {
        return false;
    }

    public int getidClan() {
        return -1;
    }

    public int getSpeed() {
        return 0;
    }

    public void setPhihanh(boolean isPhihanh) {
    }

    public void setidTemplatePet(short id) {
    }

    public void setPhiPhong(int idEffPhiPhong) {
    }

    public void petAttack(byte idSkill, mVector ntarget, int[] dame, short range) {
    }

    public void addMainBuff(int type, int timelive) {
    }

    public boolean isMypet() {
        return false;
    }

    public int getIDTem() {
        return -1;
    }

    public void setinfoNPC(int id, int x, int y, String name, int imgid, byte[] data, short idicon) {
    }

    public int getHeNguHanh() {
        return -1;
    }

    public int getColorMiniMap() {
        return 0;
    }

    public void setidHead(short id) {
    }
}

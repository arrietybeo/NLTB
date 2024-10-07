package code.model;

import code.effect.DynamicEffect;
import code.effect.MainBuff;
import code.effect.new_skill.EffectSkill;
import code.effect.new_skill.SkillManager;
import code.main.GameCanvas;
import code.network.GameService;
import code.screen.Res;
import code.screen.Skill;
import code.screen.SkillRun;
import code.screen.Util;
import code.screen.Utils;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;
import code.screen.screen.MainMenu;

import javax.microedition.lcdui.Image;

import lib.Cout;
import lib.Rms;
import lib.Tilemap;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class Char extends LiveActor {
    public static final byte DOWN = 0;
    public static final byte UP = 1;
    public static final byte LEFT = 2;
    public static final byte RIGHT = 3;
    public byte frame = 0;
    public byte framePhiPhong;
    public byte tickPhiPhong;
    public byte frameAnimal;
    public static final byte FRAME_ADD_EFF_ATTACK = 6;
    public static short CHAR_POTION = 25;
    public short questID = -1;
    public mVector soars = new mVector();
    public short idQuestNPC = -1;
    public short baokich;
    public int[][] monster_kill;
    public Image imgWpa;
    public Image imgAnimal;
    public byte[] npc_response;
    public byte[] npc_quest;
    public boolean[] talked;
    public byte nextNpc = 0;
    public String[][] info_response;
    public String[] info_npc_quest;
    public byte typeQuest = 3;
    private DataSkillEff effCharDie;
    public static Image imgFlgPk = null;
    public static Image imgLight;
    public byte[] buffType = new byte[]{-1, -1, -1, -1, -1, -1, -1};
    public byte[] lvBuff = new byte[7];
    public short[] countDown = new short[7];
    public boolean useBuff = false;
    public static byte[] levelSkill;
    public short p1;
    public short p2;
    public short p3;
    public short p4;
    public short xTo;
    public short yTo;
    public short weapon_frame = -1;
    public short wName;
    public short Flashx;
    public short Flashy;
    public short rangeStop;
    public mVector dynamicEffTop = new mVector();
    public mVector dynamicEffBottom = new mVector();
    public DataSkillEff currentSkill = null;
    public boolean movebyTouchMove = false;
    public boolean isCatSkill;
    public boolean canPaint_;
    public short idNhom;
    public static boolean ishaveParty = false;
    public static byte Max_Skill_Learn = 0;
    public static short Skill_Point;
    public static short Diemtiemnang;
    public static short sucmanh;
    public static short linhkhi;
    public static short sinhkhi;
    public static short thanphap;
    private mVector vecListSkill = new mVector();
    public short idIconClan = -1;
    public short PartHead;
    public byte chuc_vu_clan;
    public String aliasNameClan;
    public String chucvu;
    public byte online;
    public byte status;
    public short idiConList;
    public static final byte BANG_CHU = 0;
    public static final byte PHO_BANG_CHU = 1;
    public static final byte TRUONG_LAO = 2;
    public static final byte THANH_VIEN = 3;
    public boolean isPhiHanh;
    MainBuff buff;
    public static final byte[][] STANDFRAME = new byte[][]{{0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, {2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3}, {6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7}, {4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5}};
    public static byte[] DIE_FRAME = new byte[]{0, 0, 0, 0, 1, 1, 1, 2, 3, 4, 5};
    public static final byte[][] RUNFRAME = new byte[][]{{8, 9, 10, 11, 12, 13}, {14, 15, 16, 17, 18, 19}, {26, 27, 28, 29, 30, 31}, {20, 21, 22, 23, 24, 25}};
    public static final byte[][] RUNFRAME_THU_CUOI = new byte[][]{{8, 9, 10, 11}, {14, 15, 16, 17}, {26, 27, 28, 29}, {20, 21, 22, 23}};
    public static final byte[][] RUNFRAMEWAL = new byte[][]{{8, 9, 10, 11, 12, 13}, {14, 15, 16, 17, 18, 19}, {26, 27, 28, 29, 30, 31}, {20, 21, 22, 23, 24, 25}};
    public static final byte[][] FRAME_LEFT_RIGHT = new byte[][]{{6, 7, 26, 27, 28, 29, 30, 31, 42, 43, 44, 45, 46}, {4, 5, 20, 21, 22, 23, 24, 25, 37, 38, 39, 40, 41}};
    public static final byte[][] ATTACK_FRAME = new byte[][]{{32, 32, 32, 32, 32, 32, 32, 32, 33, 34, 35, 36}, {47, 47, 47, 47, 47, 47, 47, 47, 48, 49, 50, 51}, {42, 42, 42, 42, 42, 42, 42, 42, 43, 44, 45, 46}, {37, 37, 37, 37, 37, 37, 37, 37, 38, 39, 40, 41}};
    public byte[] dxHorse = new byte[]{-1, -1, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, -9, -7, -4, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public byte[] dyHorse = new byte[]{-5, -5, -2, -2, -2, -2, 0, 0, -12, -12, -12, -12, 0, 0, -5, -5, -3, -5, 0, 0, -9, -8, -9, -8, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public short bodyStyle = -1;
    public short legStyle = -1;
    public short hatStyle = -1;
    public short currentHead = -1;
    public short coatStyle = -1;
    public byte idAnimal = -1;
    public byte idPaintIconAnimal = 0;
    public byte upSpeed;
    public byte currentSkillType;
    public byte clazz;
    public byte idFashion = -1;
    public byte timeChangeFrame = 1;
    public byte weaponIndex = 0;
    public byte numFrame;
    public byte tickCatSkill;
    public long timeComeHome;
    public long timeWearScarves;
    public boolean isAnimal;
    public long charXu;
    public boolean justStopFromRun = false;
    public boolean paintHat = true;
    public int attack;
    public int defend;
    public int defend_magic;
    public short accurate;
    public short dodge;
    public short critical;
    public short dxWear;
    public short dyWear;
    public int conghien;
    public static boolean dissolved = false;
    public short lvpercent;
    public short oldpercent;
    public int lastXAuto;
    public int lastYAuto;
    public int delay = -1;
    public static byte[] nSkill = new byte[5];
    public static mVector inventory = new mVector();
    public static mVector animal = new mVector();
    public static mVector party = new mVector();
    public static mVector itembag = new mVector();
    public boolean receiveQuest = false;
    public short basePointLeft;
    public short skillPointLeft;
    public short strength;
    public short spirit;
    public short agility;
    public short health;
    public short luck;
    public boolean finishQuest = false;
    public long timeWait2Board;
    public long timeUseGolgTicket;
    public IsAnimal myAnimal = null;
    public AnimalMove myPet;
    public short[] idModel = new short[]{-1, -1, -1, -1, -1};
    public static Potion[] listPotion;
    public long[] timeLastUseSkills = new long[11];
    public long[] coolDownSkill = new long[11];
    public long[] potionLastTimeUse;
    public int[] potions;
    public mSkillManager currentSkillAnimate;
    public static byte[] skillLevelLearnt;
    public short[] posTransRoad;
    public String infoAnimal;
    public int countRoad;
    public short totalTime;
    public int attkPower;
    public int wimg;
    public int himg;
    public int luong;
    public int luongKhoa;
    public int weaponStyle;
    public int weaponType;
    public static String goldTime = "";
    public long stand;
    public long xu;
    public byte isMaster;
    public boolean isTrade;
    public boolean gotoBoard;
    public boolean isNo;
    public boolean isDoing;
    public boolean indexWearing;
    public static final byte A_STAND = 0;
    public static final byte A_RUN = 1;
    public static final byte A_ATTACK = 2;
    public static final byte A_DEAD = 3;
    public static final byte A_COME_HOME = 4;
    public static final byte MOVETOFIRST = 5;
    public static final byte RUN_AND_ATTACK = 6;
    public static final byte A_RUN_FRAME_ATTACK = 7;
    public static final byte A_FLASH = 8;
    public int charDBID;
    public boolean isWearing;
    public boolean isMaininfo;
    public boolean beStune;
    public boolean isFocus;
    public boolean iskiller;
    public static WeaponInfo[][][] imgWeapone;
    public WPSplashInfo wpsplashInfo;
    public ObjCharWearing[] wearingAni;
    public boolean comeHome;
    public boolean isDie;
    public static int goldTimeMinute = 0;
    private long coolDown;
    static final byte[] hwp = new byte[]{20, 41, 13, 28, 20};
    static final byte[] fixY = new byte[]{0, -1, 0, 0, 0};
    public ObjCharWearing[] wearing;
    int time;
    int idLight;
    public static byte[] lightFrame = new byte[]{0, 1, 2, 3, 2, 1};
    public int ifHorse;
    public byte useHorse;
    public byte anmFly;
    public static byte[][] countX = new byte[][]{{7, -15, 7, -12}, {6, -14, -4, -1}, {-12, 3, 8, -12}, {9, -15, 7, -10}, {-14, -13, 7, -11}};
    public static byte[][] countY = new byte[][]{{-12, -8, -10, -1}, {-12, -8, -15, -11}, {-6, -6, -6, -6}, {-12, -5, -19, 0}, {-18, 3, 0, -4}};
    public static final byte[][] dxs = new byte[][]{{0, 0, -2, 2}, {0, 0, -2, 2}, {0, 0, -2, 2}, {0, 0, -2, 2}, {0, 0, -2, 2}, {0, 0, -2, 2}, {0, 0, -2, 2}, {0, 0, -2, 2}};
    public byte frameattack;
    public byte idImgHorse;
    byte xShadow;
    public static boolean paintOrtherChar = true;
    static byte HEAD = 0;
    static byte BODY = 1;
    static byte LEG = 2;
    static byte WEAPON = 3;
    static byte PHI_PHONG = 4;
    public static byte THIEU_LAM = 0;
    public static byte CAI_BANG = 1;
    public static byte NGA_MI = 2;
    public static byte VO_DANG = 3;
    public static byte NGU_DOC = 4;
    public static short[][][] idPartTest = new short[][][]{{{33, 71, 73, 22}, {33, 249, 250, 12}, {33, 251, 252, 12}, {33, 253, 254, 12}, {33, 255, 256, 12}}, {{51, 267, 268, 58}, {51, 269, 270, 12}, {51, 271, 272, 12}, {51, 273, 274, 12}, {51, 275, 276, 12}}, {{34, 209, 210, 14}, {34, 211, 212, 14}, {34, 213, 214, 14}, {34, 215, 216, 14}, {34, 217, 218, 14}}, {{60, 277, 278, 12}, {60, 279, 280, 12}, {60, 281, 282, 12}, {60, 283, 284, 12}, {60, 285, 286, 12}}, {{52, 229, 230, 297}, {52, 231, 232, 12}, {52, 233, 234, 12}, {52, 235, 236, 12}, {52, 237, 238, 12}}};
    public static final byte[][][] FRAME_START_ATTACK = new byte[][][]{{{35, 35, 35, 35, 35, 35, 35, 35, 34, 32, 33, 33}, {47, 47, 47, 47, 47, 47, 47, 48, 49, 50, 51, 51}, {42, 42, 42, 42, 42, 42, 42, 43, 44, 46, 46, 46, 46}, {37, 37, 37, 37, 37, 37, 37, 38, 39, 41, 41, 41, 41}}, {{35, 34, 32, 33, 33, 33, 33, 33, 33, 33, 33}, {47, 48, 49, 50, 51, 51, 51, 51, 51, 51, 51, 51}, {42, 43, 44, 46, 46, 46, 46, 46, 46, 46, 46}, {37, 38, 39, 41, 41, 41, 41, 41, 41, 41, 41}}, {{35, 34, 32, 33, 33}, {47, 48, 49, 50, 51, 51}, {42, 43, 44, 46, 46}, {37, 38, 39, 41, 41}}, {{35, 35, 35, 35, 35, 35, 34, 34, 32, 32, 33, 33, 33, 33, 33, 33}, {47, 47, 47, 47, 47, 47, 48, 48, 49, 49, 50, 50, 51, 51, 51, 51, 51, 51}, {42, 42, 42, 42, 42, 42, 43, 43, 44, 44, 46, 46, 46, 46, 46, 46}, {37, 37, 37, 37, 37, 37, 38, 38, 39, 39, 41, 41, 41, 41, 41, 41}}, {{35, 34, 32, 36, 33}, {47, 48, 49, 50, 51}, {42, 43, 44, 45, 46}, {37, 38, 39, 40, 41}}};
    public short lv;
    public mVector partPaint;
    byte frameWearing;
    public static byte[][] ORDER_PAINT;
    public static Chunk[][] defaultChunk;
    public static mVector[] partPaintDefault;
    int frameHead;
    byte indexEffVk;
    public byte[] dxyPhiphong;
    short frameThuCuoi;
    int idHead;
    static byte[] fHead;
    DataSkillEff effWeaPhone;
    public static mHashtable imgs;
    public int idcurrentSkill;
    public int Delay;
    public boolean isFreeze;
    public Effect EffFace;
    static int count;
    byte countNo;
    public int realTime;
    public long timeDelayRequestCharInfo;
    public long timeGoToLastXY;
    public long timeRemoEffanimal;
    public Effect effPhap;
    public Effect effAnimal;
    public boolean isresetSkill;
    public byte[] TRANSFROM;
    public byte frameEff;
    public byte frameEffVk;
    long timeAttack;
    public static mHashtable ALL_EFF_PHIPHONG;
    public int idEffPhiPhong;
    public int idHorse;
    public int idEffVukhi;
    public mVector partShopPaint;
    public static byte[] ID_FRAME_CREATE_CHAR;
    public static String[] NAME_PART;
    public static mVector ALL_KEY_FRAME_CHAR;
    public Image img;
    public SkillRun skillRun;
    public Skill myskill;
    short[] myListPart;
    public Item[] equip;
    public static final byte[][] FRAME_PHI_PHONG_RUN;

    static {
        ORDER_PAINT = new byte[][]{{WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {LEG, BODY, HEAD, WEAPON}, {LEG, BODY, HEAD, WEAPON}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, BODY, LEG, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {BODY, HEAD, LEG, WEAPON}, {BODY, HEAD, LEG, WEAPON}, {BODY, HEAD, LEG, WEAPON}, {BODY, HEAD, LEG, WEAPON}, {BODY, HEAD, LEG, WEAPON}, {BODY, HEAD, LEG, WEAPON}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, HEAD, BODY}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, HEAD, BODY}, {WEAPON, LEG, BODY, HEAD}, {WEAPON, LEG, HEAD, BODY}, {HEAD, LEG, BODY, WEAPON}, {WEAPON, LEG, HEAD, BODY}, {WEAPON, LEG, BODY, HEAD}, {HEAD, LEG, BODY, WEAPON}, {LEG, WEAPON, BODY, HEAD}, {HEAD, LEG, BODY, WEAPON}, {WEAPON, LEG, HEAD, BODY}, {WEAPON, LEG, HEAD, BODY}, {HEAD, LEG, BODY, WEAPON}, {LEG, WEAPON, BODY, HEAD}, {LEG, BODY, HEAD, WEAPON}, {WEAPON, LEG, BODY, HEAD}, {LEG, BODY, HEAD, WEAPON}, {LEG, BODY, HEAD, WEAPON}, {WEAPON, LEG, BODY, HEAD}};
        defaultChunk = null;
        partPaintDefault = null;
        fHead = new byte[]{0, 1, 2, 1};
        imgs = null;
        count = 0;
        ALL_EFF_PHIPHONG = new mHashtable();
        ID_FRAME_CREATE_CHAR = new byte[]{0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 47, 48, 49, 50, 51};
        NAME_PART = new String[]{"HEAD", "BODY", "LEG", "WP", "COAT", "WP"};
        ALL_KEY_FRAME_CHAR = new mVector();
        FRAME_PHI_PHONG_RUN = new byte[][]{{8, 8, 8, 9, 9, 9, 10, 10, 10, 11, 11, 11, 12, 12, 12, 13, 13, 13}, {14, 14, 14, 15, 15, 15, 16, 16, 16, 17, 17, 17, 18, 18, 18, 19, 19, 19}, {26, 26, 26, 27, 27, 27, 28, 28, 28, 29, 29, 29, 30, 30, 30, 31, 31, 31}, {20, 20, 20, 21, 21, 21, 22, 22, 22, 23, 23, 23, 24, 24, 24, 25, 25, 25}};
    }

    public byte[] getOrderPaint(int dir) {
        return null;
    }

    public void resetQuest() {
        this.nextNpc = 0;
        this.questID = -1;
        this.idQuestNPC = -1;
        this.receiveQuest = false;
        this.finishQuest = false;
        this.info_npc_quest = null;
        this.info_response = null;
        this.typeQuest = -1;
        this.monster_kill = null;
        this.npc_response = null;
        this.npc_quest = null;
    }

    public int FullInventory() {
        return inventory.size();
    }

    public int getCountPotion() {
        int c = 0;

        for (int i = 1; i < this.potions.length; ++i) {
            if (this.potions[i] > 0) {
                ++c;
            }
        }

        return c;
    }

    public void addnewItem(Item it) {
        inventory.addElement(it);
    }

    public void deleteItem(short id, byte cat) {
        for (int i = 0; i < inventory.size(); ++i) {
            Item it = (Item) inventory.elementAt(i);
            if (it.ID == id && it.catagory == cat) {
                inventory.removeElement(it);

                for (int j = 0; j < MainChar.mQuickslot.length; ++j) {
                    if (MainChar.mQuickslot[j].quickslotType != 1 && MainChar.mQuickslot[j].idicon == it.idIcon) {
                        MainChar.mQuickslot[j].reset();
                        Rms.saveQuickSlot();
                    }
                }
            }
        }

        MainMenu.gI().addPotiontoQL();
    }

    public void updateAllitemInInventory(mVector item) {
        inventory.removeAllElements();
        inventory = item;
        if (GameCanvas.currentScreen == MainMenu.gI()) {
            MainMenu.gI().closeText_invent();
            MainMenu.gI().resetAllInven();
            MainMenu.gI().addPotiontoQL();
        }

    }

    public void updateItemInventory(Item it) {
        for (int i = 0; i < inventory.size(); ++i) {
            Item it0 = (Item) inventory.elementAt(i);
            if (it0.ID == it.ID && it.catagory == it0.catagory) {
                it0.ID = it.ID;
                it0.catagory = it.catagory;
                it0.name = it.name;
                it0.lock = it.lock;
                it0.idIcon = it.idIcon;
                it0.colorname = it.colorname;
                it0.cantrade = it.cantrade;
                it0.cansell = it.cansell;
                it0.priceShop = it.priceShop;
                it0.quantity = it.quantity;
                it0.options = it.options;
                it0.plus = it.plus;
            }
        }

        MainMenu.gI().addPotiontoQL();
    }

    public int getAttack() {
        int pecent = 0;
        return this.attack + this.attack * pecent / 100;
    }

    public byte getnSkill() {
        return 10;
    }

    public Char() {
        this.potionLastTimeUse = new long[CHAR_POTION];
        this.potions = new int[CHAR_POTION];
        this.currentSkillAnimate = new mSkillManager();
        this.infoAnimal = "";
        this.countRoad = 0;
        this.totalTime = 36;
        this.luong = 0;
        this.luongKhoa = -1;
        this.weaponStyle = -1;
        this.weaponType = -1;
        this.isMaster = 3;
        this.isWearing = false;
        this.isMaininfo = false;
        this.wearingAni = new ObjCharWearing[5];
        this.wearing = new ObjCharWearing[14];
        this.time = 0;
        this.ifHorse = 0;
        this.useHorse = -1;
        this.anmFly = 0;
        this.frameattack = 0;
        this.idImgHorse = 0;
        this.xShadow = 0;
        this.lv = 0;
        this.partPaint = new mVector();
        this.frameWearing = 0;
        this.frameHead = 0;
        this.indexEffVk = 0;
        this.dxyPhiphong = new byte[104];
        this.frameThuCuoi = 0;
        this.idHead = -1;
        this.isFreeze = false;
        this.countNo = 0;
        this.realTime = 0;
        this.timeDelayRequestCharInfo = 0L;
        this.timeRemoEffanimal = 0L;
        this.TRANSFROM = new byte[]{5, 6, 2, 0};
        this.frameEff = 0;
        this.frameEffVk = 0;
        this.timeAttack = System.currentTimeMillis();
        this.idEffPhiPhong = -1;
        this.idHorse = -1;
        this.idEffVukhi = -1;
        this.partShopPaint = new mVector();
        this.myListPart = new short[]{51, 277, 278, 12};
        this.equip = new Item[30];
        this.catagory = 0;
        this.canPaint_ = true;
        this.yFly = 0;
        this.width = 40;
        this.height = 65;
    }

    public byte getHorse() {
        return Tilemap.isOfflineMap ? -1 : this.useHorse;
    }

    public int[] getTime() {
        long aa = mSystem.currentTimeMillis() - this.timeWait2Board;
        return new int[]{this.delay, 59 - (int) aa / 1000 % 60};
    }

    public String getGoldTime() {
        String st = "";
        int t = goldTimeMinute * '\uea60';
        long time = mSystem.currentTimeMillis() - this.timeUseGolgTicket;
        long aaa = (long) t - time;
        if (aaa > 0L) {
            long h = aaa / 3600000L;
            long m = aaa % 3600000L / 1000L / 60L;
            st = h + " : " + m;
            if (h == 0L && m == 0L) {
                st = "";
            }
        }

        return st;
    }

    private int getStyleWeapon() {
        return -1;
    }

    public byte getHorse(boolean isShowInfoAnimal) {
        return Tilemap.isOfflineMap && !isShowInfoAnimal ? -1 : this.useHorse;
    }

    public byte[] getOrderPaint(int dir, int typeAnimal) {
        boolean var10000;
        if (dir != 1 && this.frame < 4) {
            var10000 = true;
        } else {
            var10000 = false;
        }

        return null;
    }

    public void moveTo(short xTo, short yTo) {
        if (this.state != 3) {
            if (!this.isFreeze) {
                if (this.isMainChar()) {
                    GameScr.mainChar.sendMove = false;
                }

                this.xTo = xTo;
                this.yTo = yTo;
                if (this.x == xTo && this.y == yTo) {
                    this.stand = System.currentTimeMillis();
                    if (this.state == 2 || this.state == 7) {
                        return;
                    }

                    if (this.state != 3) {
                        this.state = 0;
                        if (this.isMainChar()) {
                            GameScr.mainChar.sendMove = false;
                        }
                    }
                } else {
                    this.state = 1;
                }

            }
        }
    }

    public boolean isPaint() {
        if (this.x < GameScr.cmx) {
            return false;
        } else if (this.x > GameScr.cmx + GameCanvas.w) {
            return false;
        } else if (this.y < GameScr.cmy) {
            return false;
        } else {
            return this.y <= GameScr.cmy + GameCanvas.h + 30;
        }
    }

    public boolean isLoadAnimalOk() {
        return this.myAnimal != null && IsAnimal.img != null;
    }

    public void setInventory(long charXu0, int[] potions, mVector items, mVector anm, int type) {
        if (type == 0) {
            this.potions = potions;
            this.charXu = charXu0;
        }

        if (type == 1) {
            inventory.removeAllElements();
            inventory = items;
        }

        if (type == 2) {
            animal.removeAllElements();
            animal = anm;
        }

    }

    public void actorDie() {
        if (GameScr.isIntro || !this.isCatSkill) {
            this.BeginDie();
        }

    }

    public void BeginDie() {
        if (this.state != 3) {
            this.timeOutPoinson = 0L;
            this.tDelay = 0;
            this.totalTime = 36;
            this.state = 3;
            this.poinson = 0;
            int ideffDie = 0;
            if (this.clazz == CAI_BANG) {
                ideffDie = 143;
            } else if (this.clazz == NGA_MI) {
                ideffDie = 144;
            } else if (this.clazz == NGU_DOC) {
                ideffDie = 145;
            } else if (this.clazz == THIEU_LAM) {
                ideffDie = 146;
            } else if (this.clazz == VO_DANG) {
                ideffDie = 147;
            }

            if (this.effCharDie == null) {
                this.effCharDie = new DataSkillEff(ideffDie, this.x, this.y, 0);
            } else {
                this.effCharDie.x = this.x;
                this.effCharDie.y = this.y;
                this.effCharDie.f = 0;
            }

            this.currentSkill = null;
            this.vecListSkill.removeAllElements();
            this.frame = 0;
            this.removeAllEff();
            this.hp = 0;
            this.mp = 0;
            this.resetAllSkill();
            if (this.equals(GameScr.mainChar)) {
                GameCanvas.gameScr.comHome();
            }

        }
    }

    public void recevie() {
        this.state = 0;
        this.currentSkill = null;
        this.vecListSkill.removeAllElements();
        this.frame = 0;
        this.effCharDie = null;
        this.isDie = false;
    }

    public void paintShadow(mGraphics g) {
        if (this.isPhiHanh) {
            Image image = getShadow();
            if (image != null)
                g.drawImage(image, this.x, this.y - 2, 3, false);
            return;
        }
        if (this.isWater)
            return;
        if (!canPaint())
            return;
        int xs = 0, ys = 0;
        if (this.state == 0 && this.dir == 0) {
            xs = 0;
            ys = -1;
        }
        if (this.state == 0 && this.dir == 1) {
            xs = 1;
            ys = 0;
        }
        if (this.state == 0 && this.dir == 2) {
            xs = -1;
            ys = -2;
        }
        if (this.state == 0 && this.dir == 3) {
            xs = 0;
            ys = -2;
        }
        if ((this.state == 1 || this.state == 6 || this.state == 7 || this.state == 2) && this.dir == 2) {
            xs = 10;
            if (this.state == 2 || this.state == 7)
                xs = 0;
        }
        if ((this.state == 1 || this.state == 6 || this.state == 7 || this.state == 2) && this.dir == 3) {
            xs = -7;
            if (this.state == 2 || this.state == 7)
                xs = 1;
        }
        if ((this.state == 1 || this.state == 6 || this.state == 7 || this.state == 2) && this.dir == 0) {
            xs = -3;
            if (this.state == 2 || this.state == 7) {
                xs = 4;
                ys = -1;
            }
        }
        if ((this.state == 1 || this.state == 6 || this.state == 7 || this.state == 2) && this.dir == 1 && (this.state == 2 || this.state == 7)) {
            xs = 4;
            ys = -2;
        }
        DataSkillEff partThuCuoi = loadPartPhiPhongThuCuoi(this.idHorse);
        Image shadow = getShadow();
        if (shadow != null) {
            xs = 0;
            ys = -3;
            if (this.state == 1)
                ys = 0;
            int xsd = this.x + xs;
            int ysd = this.y - 2 + ys;
            if (partThuCuoi != null) {
                xsd = this.x;
                ysd = this.y + 6;
            }
            g.drawImage(shadow, xsd, ysd, mGraphics.VCENTER | mGraphics.HCENTER, false);
        }
    }

    public Image getShadow() {
        Image img = GameScr.imgShadow[1];
        if (this.state == 0) {
            return GameScr.imgShadow[1];
        } else if (this.state != 1 && this.state != 6 && this.state != 7 || this.dir != 2 && this.dir != 3) {
            if ((this.state == 1 || this.state == 6 || this.state == 7) && (this.dir == 2 || this.dir == 3)) {
                return GameScr.imgShadow[0];
            } else {
                return this.state != 1 && this.state != 6 && this.state != 7 || this.dir != 1 && this.dir != 0 ? img : GameScr.imgShadow[2];
            }
        } else {
            return GameScr.imgShadow[0];
        }
    }

    byte getFrameCharLeftByFrameRight(int idFrame) {
        for (int i = 0; i < ATTACK_FRAME[2].length; ++i) {
            if (ATTACK_FRAME[2][i] == idFrame) {
                return ATTACK_FRAME[3][i];
            }
        }

        return (byte) idFrame;
    }

    public void paintOtherCharName(mGraphics g) {
        int ddy = 75;
        if (this.isNPC()) {
            mFont.tahoma_7_black.drawString(g, this.name, this.x + 1, this.y - this.yFly - ddy + 1, 2, false);
            mFont f = mFont.tahoma_7_yellow;
            f.drawString(g, this.name, this.x, this.y - this.yFly - ddy, 2, false);
        } else {
            String np = this.name;
            ImageIcon img = null;
            boolean var5 = false;

            try {
                if (this.idClan > -1) {
                    np = np + "[" + this.aliasNameClan + "]";
                    img = GameData.getImgIcon((short) (this.idIconClan + Res.ID_ICON_CLAN));
                }

                if (img != null && img.img != null) {
                    int xx = img.getWidth();
                    int xicon = this.x - mFont.name_Black.getWidth(np) / 2 - xx - 3;
                    if (mSystem.isIP) {
                        xicon -= 2;
                    }

                    int num = -1;
                    if (this.chuc_vu_clan == 0) {
                        num = 15;
                    } else if (this.chuc_vu_clan == 1) {
                        num = 8;
                    } else if (this.chuc_vu_clan == 2) {
                        num = 6;
                    }

                    if (num != -1) {
                        Item.eff_UpLv.paintUpgradeEffect(xicon - 4 + 7, this.y - this.yFly - ddy + 2 + 4, num, 16, g, 1);
                    }

                    g.drawImage(img.img, xicon - 3, this.y - this.yFly - ddy + (num != -1 ? 1 : 4) - mGraphics.getImageHeight(img.img) / 2 + 4, 0, false);
                }
            } catch (Exception var8) {
            }

            mFont.name_Black.drawString(g, np, this.x + 1, this.y - this.yFly - ddy + 1, 2, false);
            mFont.name_White.drawString(g, np, this.x, this.y - this.yFly - ddy, 2, false);
        }

    }

    public void paintCharAvatar(mGraphics g, int x, int y, int idPartHair, int gender) {
        mVector vtPartTem = new mVector();
        vtPartTem.addElement(Chunk.getHead(idPartHair, gender));
        int size = this.partPaint.size();
        if (size > 0) {
            vtPartTem.addElement(this.partPaint.elementAt(1));
            vtPartTem.addElement(this.partPaint.elementAt(2));
            if (size > 3) {
                vtPartTem.addElement(this.partPaint.elementAt(3));
            }
        }

        this.paintCharByChunk(g, x, y - this.yFly - this._jum + this.dyWater, vtPartTem.size() == 0 ? partPaintDefault[this.getGender()] : vtPartTem, this.dir, this.frame, false);
        Image shadow = this.getShadow();
        if (shadow != null) {
            int ysd = y - 2;
            g.drawImage(shadow, x, ysd, 3, false);
        }

    }

    public void paintChar(mGraphics g, int x, int y) {
        int idPartHair = this.PartHead;
        int gender = this.getGender();
        mVector vtPartTem = new mVector();
        vtPartTem.addElement(Chunk.getHead(idPartHair, gender));
        int size = this.partPaint.size();
        if (size > 0) {
            vtPartTem.addElement(this.partPaint.elementAt(1));
            vtPartTem.addElement(this.partPaint.elementAt(2));
            if (size > 3) {
                vtPartTem.addElement(this.partPaint.elementAt(3));
            }
        }

        this.paintCharByChunk(g, x, y - this.yFly - this._jum + this.dyWater, vtPartTem.size() == 0 ? partPaintDefault[this.getGender()] : vtPartTem, this.dir, this.frame, false);
        Image shadow = this.getShadow();
        if (shadow != null) {
            int ysd = y - 2;
            g.drawImage(shadow, x, ysd, 3, false);
        }

    }

    public void paint(mGraphics g) {
        DataSkillEff partPhiphong = loadPartPhiPhongThuCuoi(this.idEffPhiPhong);
        this.paintIconPK(g);
        this.paintOtherCharName(g);
        if (!this.equals(GameScr.mainChar) && !paintOrtherChar && !this.isNPC() && mSystem.isj2me) {
            if (this.canPaint_) {
                this.paintShadow(g);
            }

        } else if (this.state == 3) {
            if (this.effCharDie != null) {
                this.effCharDie.paint(g);
            }

        } else {
            this.framePhiPhong = this.frame;
            if (this.canPaint_) {
                this.paintShadow(g);
            }

            if (this.buff != null) {
                this.buff.paint(g, this.x + 1, this.y + 2);
            }

            this.paintBottomDataEff(g, this.getStartPointPaintFly());
            boolean isPaint;
            if (this.currentSkill != null && this.state == 2) {
                isPaint = true;
                if (this.ID != GameScr.mainChar.ID && !GameCanvas.menuSelectitem.isTabFocus[2]) {
                    isPaint = false;
                }

                this.currentSkill.paintBottom(g, this.x, this.y, isPaint);
                int dir = this.dir;
                if (dir == 2) {
                    dir = 3;
                }

                if (this.currentSkill.isLoadData && this.currentSkill.getFrameChar() != null && this.frameEff < this.currentSkill.getFrameChar()[dir].length) {
                    this.frame = this.currentSkill.getFrameChar()[dir][this.frameEff];
                    if (this.dir == 2) {
                        this.frame = this.getFrameCharLeftByFrameRight(this.frame);
                        if (partPhiphong != null) {
                            this.framePhiPhong = this.frame;
                        }
                    }
                }
            } else if (this.dir == 2) {
                this.frame = getFramePhiPhongLeftByright(this.frame);
                if (partPhiphong != null) {
                    this.framePhiPhong = this.frame;
                }
            }

            if (this.canPaint_) {
                int xw = 0;
                if (this.isWater && this.state == 1) {
                    if (this.dir == 2) {
                        xw = -10;
                    }

                    if (this.dir == 3) {
                        xw = 7;
                    }
                }

                if (this.canPaint()) {
                    this.paintCharByChunkHide(g, this.x + xw, this.y - this.yFly - this._jum + this.dyWater, this.partPaint.size() == 0 ? partPaintDefault[this.getGender()] : this.partPaint, this.dir, this.frame, false);
                }
            }

            if (this.currentSkill != null && this.state == 2) {
                isPaint = true;
                if (this.ID != GameScr.mainChar.ID && !GameCanvas.menuSelectitem.isTabFocus[2]) {
                    isPaint = false;
                }

                this.currentSkill.paintTopSkillChar(g, this.x, this.y, isPaint);
            }

            this.paintTopDataEff(g, this.getStartPointPaintFly());
            if (this.buff != null) {
                this.buff.paint(g, this.x + 1, this.y + 2);
            }

            DataSkillEff partThuCuoi = loadPartPhiPhongThuCuoi(this.idHorse);
            int frameQuest;
            if (this.isWater && !this.isPhiHanh) {
                frameQuest = this.state != 0 && this.dir != 1 && this.dir != 0 ? 40 : 0;
                int xs = 0;
                int ythu = 0;
                if (partThuCuoi != null) {
                    xs = 0;
                    if (this.dir == 2) {
                        xs = -4;
                    } else if (this.dir == 3) {
                        xs = 4;
                    }

                    if (this.dir == 0) {
                        ythu = 15;
                    } else {
                        ythu = 5;
                    }
                }

                g.drawRegion(GameScr.imgWater, 0, frameQuest + GameCanvas.gameTick / 2 % 2 * 20, 44, 20, 0, this.x + xs, this.y + ythu, 3, false);
            }

            this.paintPartyInfo(g);
            if (this.isNPC()) {
                frameQuest = GameScr.getIdImgQuest(this.idBot);
                if (frameQuest != -1) {
                    int ystart = 0;
                    if (frameQuest == 1) {
                        ystart = 28;
                    }

                    if (frameQuest == 2) {
                        ystart = 56;
                    }

                    g.drawRegion(GameScr.imgquest, 0, ystart + (GameCanvas.gameTick % 5 == 0 ? 1 : 0) * 14, 12, 14, 0, this.x, this.y - this.yFly - 100 + GameCanvas.gameTick % 8, 3, false);
                }
            }

        }
    }

    public int getStateQuest() {
        if (this.isNPC()) {
            int state = GameScr.getIdImgQuest(this.idBot);
            if (state == 0) {
                return 0;
            }

            if (state == 1) {
                return 2;
            }

            if (state == 2) {
                return 1;
            }
        }

        return -1;
    }

    public void paint(mGraphics g, int x, int y, int pos) {
        this.paintCharAVT(g, x, y, this.partPaint, 0, this.frameWearing, false);
    }

    public void updateCharFrame() {
        if (GameCanvas.gameTick % 5 == 0) {
            this.frameWearing = (byte) ((this.frameWearing + 1) % 2);
        }

    }

    public int getGender() {
        return this.clazz != 0 && this.clazz != 1 && this.clazz != 3 ? 1 : 0;
    }

    public static void load() {
        try {
            defaultChunk = new Chunk[2][4];
            defaultChunk[0][0] = Chunk.getHead(51, 0);
            defaultChunk[0][1] = Chunk.getBody(277, 0);
            defaultChunk[0][2] = Chunk.getLeg(278, 0);
            defaultChunk[0][3] = Chunk.getWeapon(12);
            defaultChunk[1][0] = Chunk.getHead(34, 0);
            defaultChunk[1][1] = Chunk.getBody(27, 0);
            defaultChunk[1][2] = Chunk.getLeg(29, 0);
            defaultChunk[1][3] = Chunk.getWeapon(12);
            partPaintDefault = new mVector[2];
            partPaintDefault[0] = new mVector();
            partPaintDefault[0].addElement(defaultChunk[0][0]);
            partPaintDefault[0].addElement(defaultChunk[0][1]);
            partPaintDefault[0].addElement(defaultChunk[0][2]);
            partPaintDefault[0].addElement(defaultChunk[0][3]);
            partPaintDefault[1] = new mVector();
            partPaintDefault[1].addElement(defaultChunk[1][0]);
            partPaintDefault[1].addElement(defaultChunk[1][1]);
            partPaintDefault[1].addElement(defaultChunk[1][2]);
            partPaintDefault[1].addElement(defaultChunk[1][3]);
        } catch (Exception var1) {
            var1.printStackTrace();
        }

    }

    public void paintCharShop(mGraphics g, int x, int y) {
        mVector vtPartTem = new mVector();
        int size = this.partShopPaint.size();
        if (size > 0) {
            vtPartTem.addElement(this.partShopPaint.elementAt(0));
            vtPartTem.addElement(this.partShopPaint.elementAt(1));
            vtPartTem.addElement(this.partShopPaint.elementAt(2));
            if (size > 3) {
                vtPartTem.addElement(this.partShopPaint.elementAt(3));
            }
        }

        this.paintCharByChunkShop(g, x, y - this.yFly - this._jum + this.dyWater, vtPartTem.size() == 0 ? partPaintDefault[this.getGender()] : vtPartTem, this.dir, this.frame, false);
        Image shadow = this.getShadow();
        if (shadow != null) {
            int ysd = y - 2;
            g.drawImage(shadow, x, ysd, 3, false);
        }

    }

    static byte getFramePhiPhongLeftByright(int frame) {
        for (int i = 0; i < FRAME_LEFT_RIGHT[0].length; ++i) {
            if (FRAME_LEFT_RIGHT[0][i] == frame) {
                return FRAME_LEFT_RIGHT[1][i];
            }
        }

        return (byte) frame;
    }

    public void paintCharByChunkShop(mGraphics g, int xStart, int yStart, mVector chunks, int dir, int frame, boolean isWater) {
        int dx, dy;
        DataSkillEff partPhiphong = loadPartPhiPhongThuCuoi(this.idEffPhiPhong);
        Chunk chunk = null;
        int dxpp = this.dxyPhiphong[frame * 2];
        int dypp = this.dxyPhiphong[frame * 2 + 1];
        ImageIcon imgPaint = null;
        if (dir != 1 && partPhiphong != null) {
            partPhiphong.paintBottomPhiPhong(g, xStart + dxpp, yStart - dypp, dir == 2 ? 2 : 0, dir == 2 ? getFramePhiPhongLeftByright(this.framePhiPhong) : this.framePhiPhong);
        }

        for (int i = 0; i < ORDER_PAINT[frame].length; ++i) {
            if (i < chunks.size()) {
                if (partPhiphong != null && ORDER_PAINT[frame][i] == WEAPON && this.dir == 1) {
                    partPhiphong.paintTopPhiPhong(g, xStart + dxpp, yStart - dypp, dir == 2 ? 2 : 0, dir == 2 ? getFramePhiPhongLeftByright(this.framePhiPhong) : this.framePhiPhong);
                }

                chunk = (Chunk) chunks.elementAt(ORDER_PAINT[frame][i]);
                dx = 0;
                dy = 0;
                if (chunk == null) {
                    continue;
                }

                imgPaint = GameData.getImgIcon((short) (chunk.getBigId() + Res.ID_CHAR));
                if (imgPaint == null || imgPaint.img == null) {
                    chunk = defaultChunk[this.getGender()][ORDER_PAINT[frame][i]];
                }

                for (int j = 0; j < chunk.chunkData.length; ++j) {
                    if (chunk.chunkData[j].index == frame) {
                        dx = chunk.chunkData[j].dx;
                        dy = chunk.chunkData[j].dy;
                        break;
                    }
                }

                short rotale = chunk.getTemplate()[frame].rotate;
                if (ORDER_PAINT[frame][i] == WEAPON && (this.isMainChar() && GameCanvas.gameScr.charclass == 4 || this.clazz == 4)) {
                    rotale = 2;
                    if (dir == 3 || dir == 0) {
                        rotale = 0;
                    }
                }

                if (isWater && ORDER_PAINT[frame][i] == LEG) {
                    this.drawRegion(g, chunk, chunk.getBigId(), chunk.getIcon(frame), 0, 0, Res.arr[chunk.getIcon(frame)][3], 5, xStart + chunk.getTemplate()[frame].dx + dx, yStart + chunk.getTemplate()[frame].dy - 10, rotale, mGraphics.BOTTOM | mGraphics.HCENTER);
                } else {
                    this.drawImage(g, chunk, chunk.getBigId(), chunk.getIcon(frame), xStart + chunk.getTemplate()[frame].dx + dx, yStart + chunk.getTemplate()[frame].dy + dy, rotale, mGraphics.BOTTOM | mGraphics.HCENTER, false);
                }
            }

            if (partPhiphong != null && dir != 1) {
                partPhiphong.paintTopPhiPhong(g, xStart + dxpp, yStart - dypp, dir == 2 ? 2 : 0, dir == 2 ? getFramePhiPhongLeftByright(this.framePhiPhong) : this.framePhiPhong);
            }
        }

    }

    public int getFrameHeadDynamic(int mframe) {
        return mframe + fHead[this.frameHead] * 52;
    }

    public void setidHead(short id) {
        this.idHead = id;
    }

    public void paintCharByChunkHide(mGraphics g, int xStart, int yStart, mVector chunks, int dir, int frame, boolean isWater) {
        try {
            int ms;
            if (GameCanvas.menuSelectitem.isTabFocus[3] && !this.isMainChar() && !this.isNPC() && !this.isNpcChar()) {
                return;
            }
            if (!this.isMainChar() && this.partPaint.size() == 0 && paintOrtherChar) {
                this.setInfoWearing(this.myListPart);
            }
            DataSkillEff partPhiphong = Char.loadPartPhiPhongThuCuoi(this.idEffPhiPhong);
            DataSkillEff partThuCuoi = Char.loadPartPhiPhongThuCuoi(this.idHorse);
            DataSkillEff effPhatsangVk = Char.loadPartPhiPhongThuCuoi(this.idEffVukhi);
            DataSkillEff headDynamic = Char.loadPartPhiPhongThuCuoi(this.idHead);
            if (GameCanvas.gameTick % 10 == 0 && headDynamic != null && (ms = headDynamic.countTotalFrame() / 52 + 1) > 0) {
                this.frameHead = (this.frameHead + 1) % ms;
            }
            int dxpp = this.dxyPhiphong[frame * 2];
            int dypp = this.dxyPhiphong[frame * 2 + 1];
            int dx = 0;
            int dy = 0;
            Chunk chunk = null;
            ImageIcon imgPaint = null;
            byte dxhorse = 0;
            byte dyhorse = 0;
            if (partThuCuoi != null) {
                dxhorse = this.dxHorse[frame];
                dyhorse = this.dyHorse[frame];
                if (dir == 2) {
                    dxhorse = (byte) -this.dxHorse[frame];
                    dyhorse = this.dyHorse[frame];
                }
            }
            if (partThuCuoi != null && dir != 2) {
                dxpp += this.dxHorse[frame];
                dypp -= this.dyHorse[frame];
            }
            if (dir != 1 && partPhiphong != null) {
                if (dir == 2) {
                    dxpp = -this.dxyPhiphong[frame * 2];
                    if (partThuCuoi != null) {
                        dxpp -= this.dxHorse[frame];
                        dypp -= this.dyHorse[frame];
                    }
                }
                partPhiphong.paintBottomPhiPhong(g, this.x + dxpp, this.y - dypp, dir == 2 ? 2 : 0, this.framePhiPhong);
            }
            if (partThuCuoi != null && partThuCuoi.countTotalFrame() >= 52) {
                if (GameCanvas.gameTick % 6 == 0) {
                    this.frameThuCuoi = (short) ((this.frameThuCuoi + 1) % (partThuCuoi.countTotalFrame() / 52));
                }
                partThuCuoi.paintBottomPhiPhong(g, this.x, this.y, dir == 2 ? 2 : 0, frame + this.frameThuCuoi * 52);
            }

            if (this.isWater) {
                dypp -= 5;
            }
            int i = 0;
            while (i < ORDER_PAINT[frame].length) {
                block38:
                {
                    block36:
                    {
                        byte[] sq;
                        block37:
                        {
                            block40:
                            {
                                block39:
                                {
                                    if (i >= chunks.size()) break block36;
                                    if (partPhiphong != null && ORDER_PAINT[frame][i] == WEAPON && this.dir == 1) {
                                        partPhiphong.paintTopPhiPhong(g, this.x + dxpp, this.y - dypp, dir == 2 ? 2 : 0, this.framePhiPhong);
                                    }
                                    chunk = (Chunk) chunks.elementAt(ORDER_PAINT[frame][i]);
                                    dx = 0;
                                    dy = 0;
                                    if (chunk != null) break block37;
                                    if (!mSystem.isj2me) break block38;
                                    if (ORDER_PAINT[frame][i] != HEAD) break block39;
                                    chunk = Chunk.getHead(this.myListPart[HEAD], this.getGender());
                                    this.partPaint.setElementAt(chunk, 0);
                                    break block38;
                                }
                                if (ORDER_PAINT[frame][i] != BODY) break block40;
                                chunk = Chunk.getHead(this.myListPart[BODY], this.getGender());
                                this.partPaint.setElementAt(chunk, 1);
                                break block38;
                            }
                            if (ORDER_PAINT[frame][i] == LEG) {
                                chunk = Chunk.getHead(this.myListPart[LEG], this.getGender());
                                this.partPaint.setElementAt(chunk, 2);
                                break block38;
                            } else if (ORDER_PAINT[frame][i] == WEAPON) {
                                chunk = Chunk.getHead(this.myListPart[WEAPON], this.getGender());
                                this.partPaint.setElementAt(chunk, 3);
                            }
                            break block38;
                        }
                        imgPaint = GameData.getImgIcon((short) (chunk.getBigId() + Res.ID_CHAR));
                        if (imgPaint == null || imgPaint.img == null) {
                            chunk = defaultChunk[this.getGender()][ORDER_PAINT[frame][i]];
                        }
                        int j = 0;
                        while (j < chunk.chunkData.length) {
                            if (chunk.chunkData[j].index == frame) {
                                dx = chunk.chunkData[j].dx;
                                dy = chunk.chunkData[j].dy;
                                break;
                            }
                            ++j;
                        }
                        dx += chunk.getTemplate()[frame].dx;
                        int rotale = chunk.getTemplate()[frame].rotate;
                        SmallImage sm = chunk.allSmallImg[chunk.getIcon(frame)];
                        int wframe = Res.getWidthRotate(rotale, sm.w, sm.h);
                        int hframe = Res.getHeightRotate(rotale, sm.w, sm.h);
                        dy -= hframe;
                        dx -= wframe / 2;
                        int anchor = mGraphics.TOP | mGraphics.LEFT;
                        if (dir == 2) {
                            rotale = Res.getRotateLeftFromRight(rotale);
                            dx = -(dx + wframe);
                        }
                        if (ORDER_PAINT[frame][i] == WEAPON && (this.isMainChar() && GameCanvas.gameScr.charclass == 4 || this.clazz == 4)) {
                            rotale = 2;
                            if (dir == 3 || dir == 0) {
                                rotale = 0;
                            }
                        }
                        if (ORDER_PAINT[frame][i] == WEAPON && effPhatsangVk != null && (sq = effPhatsangVk.getSequence()) != null) {
                            if (GameCanvas.gameScr.charclass == 4 || this.clazz == 4) {
                                effPhatsangVk.paintBottom(g, xStart + dxhorse, dyhorse + yStart + chunk.getTemplate()[frame].dy, rotale, sq[this.frameEffVk]);
                            } else {
                                effPhatsangVk.paintBottom(g, xStart + dx + dxhorse + wframe / 2, dyhorse + yStart + chunk.getTemplate()[frame].dy, rotale, sq[this.frameEffVk]);
                            }
                        }
                        if (ORDER_PAINT[frame][i] == HEAD && headDynamic != null) {
                            headDynamic.paintBottomHead(g, xStart + dxhorse, dyhorse + yStart + chunk.getTemplate()[frame].dy + dy, dir == 2 ? 2 : 0, this.getFrameHeadDynamic(frame));
                            headDynamic.paintTopHead(g, xStart + dxhorse, dyhorse + yStart + chunk.getTemplate()[frame].dy + dy, dir == 2 ? 2 : 0, this.getFrameHeadDynamic(frame));
                        } else if (isWater && ORDER_PAINT[frame][i] == LEG) {
                            this.drawRegion(g, chunk, chunk.getBigId(), chunk.getIcon(frame), 0, 0, Res.arr[chunk.getIcon(frame)][3], 5, xStart + dx + dxhorse, dyhorse + yStart + chunk.getTemplate()[frame].dy - 10, rotale, anchor);
                        } else if (partThuCuoi == null || partThuCuoi != null && ORDER_PAINT[frame][i] != LEG) {
                            this.drawImage(g, chunk, chunk.getBigId(), chunk.getIcon(frame), xStart + dx + dxhorse, dyhorse + yStart + chunk.getTemplate()[frame].dy + dy, rotale, anchor, false);
                        }
                        if (ORDER_PAINT[frame][i] == WEAPON && effPhatsangVk != null && (sq = effPhatsangVk.getSequence()) != null) {
                            if (GameCanvas.gameScr.charclass == 4 || this.clazz == 4) {
                                effPhatsangVk.paintTop(g, xStart + dxhorse, dyhorse + yStart + chunk.getTemplate()[frame].dy, rotale, sq[this.frameEffVk]);
                            } else {
                                effPhatsangVk.paintTop(g, xStart + dx + dxhorse + wframe / 2, dyhorse + yStart + chunk.getTemplate()[frame].dy, rotale, sq[this.frameEffVk]);
                            }
                        }
                    }
                    if (partPhiphong != null && dir != 1) {
                        partPhiphong.paintTopPhiPhong(g, this.x + dxpp, this.y - dypp, dir == 2 ? 2 : 0, this.framePhiPhong);
                    }
                    if (partThuCuoi != null) {
                        partThuCuoi.paintTopPhiPhong(g, this.x, this.y, dir == 2 ? 2 : 0, frame + this.frameThuCuoi * 52);
                    }
                }
                ++i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void paintCharByChunk(mGraphics g, int xStart, int yStart, mVector chunks, int dir, int frame, boolean isWater) {
        int dx = 0, dy = 0;
        DataSkillEff partPhiphong = loadPartPhiPhongThuCuoi(this.idEffPhiPhong);
        DataSkillEff partThuCuoi = loadPartPhiPhongThuCuoi(this.idHorse);
        int dxpp = this.dxyPhiphong[frame * 2], dypp = this.dxyPhiphong[frame * 2 + 1];
        Chunk chunk = null;
        ImageIcon imgPaint = null;
        if (partThuCuoi != null) {
            if (GameCanvas.gameTick % 6 == 0)
                this.frameThuCuoi = (short) ((this.frameThuCuoi + 1) % partThuCuoi.countTotalFrame() / 52);
            partThuCuoi.paintBottomPhiPhong(g, this.x, this.y, (dir == 2) ? 2 : 0, ((dir == 2) ? getFramePhiPhongLeftByright(frame) : frame) + this.frameThuCuoi * 52);
        }
        if (dir != 1 && partPhiphong != null)
            partPhiphong.paintBottomPhiPhong(g, this.x + dxpp, this.y - dypp, (dir == 2) ? 2 : 0, (dir == 2) ? getFramePhiPhongLeftByright(this.framePhiPhong) : this.framePhiPhong);
        for (int i = 0; i < (ORDER_PAINT[frame]).length; i++) {
            if (i < chunks.size()) {
                if (partPhiphong != null && ORDER_PAINT[frame][i] == WEAPON && this.dir == 1)
                    partPhiphong.paintTopPhiPhong(g, this.x + dxpp, this.y - dypp, (dir == 2) ? 2 : 0, (dir == 2) ? getFramePhiPhongLeftByright(this.framePhiPhong) : this.framePhiPhong);
                chunk = (Chunk) chunks.elementAt(ORDER_PAINT[frame][i]);
                dx = 0;
                dy = 0;
                if (chunk == null)
                    continue;
                imgPaint = GameData.getImgIcon((short) (chunk.getBigId() + Res.ID_CHAR));
                if (imgPaint == null || imgPaint.img == null)
                    chunk = defaultChunk[getGender()][ORDER_PAINT[frame][i]];
                for (int j = 0; j < chunk.chunkData.length; j++) {
                    if ((chunk.chunkData[j]).index == frame) {
                        dx = (chunk.chunkData[j]).dx;
                        dy = (chunk.chunkData[j]).dy;
                        break;
                    }
                }
                short rotale = (chunk.getTemplate()[frame]).rotate;
                if (ORDER_PAINT[frame][i] == WEAPON && ((isMainChar() && GameCanvas.gameScr.charclass == 4) || this.clazz == 4)) {
                    rotale = 2;
                    if (dir == 3 || dir == 0)
                        rotale = 0;
                }
                if (isWater && ORDER_PAINT[frame][i] == LEG) {
                    drawRegion(g, chunk, chunk.getBigId(), chunk.getIcon(frame), 0, 0, Res.arr[chunk.getIcon(frame)][3], 5, xStart + (chunk.getTemplate()[frame]).dx + dx, yStart + (chunk.getTemplate()[frame]).dy - 10, rotale, mGraphics.BOTTOM | mGraphics.HCENTER);
                } else {
                    drawImage(g, chunk, chunk.getBigId(), chunk.getIcon(frame), xStart + (chunk.getTemplate()[frame]).dx + dx, yStart + (chunk.getTemplate()[frame]).dy + dy, rotale, mGraphics.BOTTOM | mGraphics.HCENTER, false);
                }
            }
            if (partPhiphong != null && dir != 1)
                partPhiphong.paintTopPhiPhong(g, this.x + dxpp, this.y - dypp, (dir == 2) ? 2 : 0, (dir == 2) ? getFramePhiPhongLeftByright(this.framePhiPhong) : this.framePhiPhong);
            if (partThuCuoi != null)
                partThuCuoi.paintTopPhiPhong(g, this.x, this.y, (dir == 2) ? 2 : 0, ((dir == 2) ? getFramePhiPhongLeftByright(frame) : frame) + this.frameThuCuoi * 52);
        }
    }


    public void paintCharAVT(mGraphics g, int xStart, int yStart, mVector chunks, int dir, int frame, boolean isWater) {
        int dx = 0, dy = 0;
        DataSkillEff partPhiphong = loadPartPhiPhongThuCuoi(this.idEffPhiPhong);
        int dxpp = this.dxyPhiphong[frame * 2], dypp = this.dxyPhiphong[frame * 2 + 1];
        Chunk chunk = null;
        if (partPhiphong != null && dir != 1)
            partPhiphong.paintBottomPhiPhong(g, xStart + dxpp, yStart - dypp, (dir == 2) ? 2 : 0, frame);
        for (int i = 0; i < (ORDER_PAINT[frame]).length; i++) {
            if (i < chunks.size()) {
                if (partPhiphong != null && ORDER_PAINT[frame][i] == WEAPON && this.dir == 1)
                    partPhiphong.paintTopPhiPhong(g, xStart + dxpp, yStart - dypp, (dir == 2) ? 2 : 0, frame);
                chunk = (Chunk) chunks.elementAt(ORDER_PAINT[frame][i]);
                if (chunk != null) {
                    dx = 0;
                    dy = 0;
                    for (int j = 0; j < chunk.chunkData.length; j++) {
                        if ((chunk.chunkData[j]).index == frame) {
                            dx = (chunk.chunkData[j]).dx;
                            dy = (chunk.chunkData[j]).dy;
                            break;
                        }
                    }
                    if (isWater && ORDER_PAINT[frame][i] == LEG) {
                        drawRegion(g, chunk, chunk.getBigId(), chunk.getIcon(frame), 0, 0, Res.arr[chunk.getIcon(frame)][3], 5, xStart + (chunk.getTemplate()[frame]).dx + dx, yStart + (chunk.getTemplate()[frame]).dy - 10, (chunk.getTemplate()[frame]).rotate, mGraphics.BOTTOM | mGraphics.HCENTER);
                    } else {
                        drawImage(g, chunk, chunk.getBigId(), chunk.getIcon(frame), xStart + (chunk.getTemplate()[frame]).dx + dx, yStart + (chunk.getTemplate()[frame]).dy + dy, (chunk.getTemplate()[frame]).rotate, mGraphics.BOTTOM | mGraphics.HCENTER, true);
                    }
                }
            }
        }
        if (partPhiphong != null && dir != 1) {
            partPhiphong.paintTopPhiPhong(g, xStart + dxpp, yStart - dypp, (dir == 2) ? 2 : 0, frame);
        }
    }

    public void paintCharHead(mGraphics g, int x, int y, int pos) {
        this.paintHead(g, x, y, this.partPaint, 0, this.frameWearing, false);
    }

    public void paintHead(mGraphics g, int x, int y) {
        this.paintHeadClan(g, x, y, this.partPaint, 0, this.frameWearing, false);
    }

    public void paintHead(mGraphics g, int xStart, int yStart, mVector chunks, int dir, int frame, boolean isWater) {
        int dx = 0, dy = 0;
        Chunk chunk = null;
        for (int i = 0; i < (ORDER_PAINT[frame]).length; i++) {
            if (i < chunks.size()) {
                chunk = (Chunk) chunks.elementAt(ORDER_PAINT[frame][i]);
                dx = 0;
                dy = 0;
                for (int j = 0; j < chunk.chunkData.length; j++) {
                    if ((chunk.chunkData[j]).index == frame) {
                        dx = (chunk.chunkData[j]).dx;
                        dy = (chunk.chunkData[j]).dy;
                        break;
                    }
                }
                if (ORDER_PAINT[frame][i] == HEAD)
                    drawImage(g, chunk, chunk.getBigId(), chunk.getIcon(frame), xStart + (chunk.getTemplate()[frame]).dx + dx, yStart + (chunk.getTemplate()[frame]).dy + dy, (chunk.getTemplate()[frame]).rotate, mGraphics.BOTTOM | mGraphics.HCENTER, true);
            }
        }
    }

    public void paintHeadClan(mGraphics g, int xStart, int yStart, mVector chunks, int dir, int frame, boolean isWater) {
        Chunk head = Chunk.getHead(this.PartHead, 0);
        int dx = 0;
        int dy = 0;
        this.drawImage(g, head, head.getBigId(), head.getIcon(frame), xStart + head.getTemplate()[frame].dx + dx, yStart + head.getTemplate()[frame].dy + dy, head.getTemplate()[frame].rotate, mGraphics.BOTTOM | mGraphics.HCENTER, true);
    }

    public void paintSelect(mGraphics g, int xStart, int yStart, mVector chunks, int dir, int frame, boolean isWater) {
        int dx = 0, dy = 0;
        Chunk chunk = null;
        for (int i = 0; i < (ORDER_PAINT[frame]).length; i++) {
            if (i < chunks.size()) {
                chunk = (Chunk) chunks.elementAt(ORDER_PAINT[frame][i]);
                dx = 0;
                dy = 0;
                for (int j = 0; j < chunk.chunkData.length; j++) {
                    if ((chunk.chunkData[j]).index == frame) {
                        dx = (chunk.chunkData[j]).dx;
                        dy = (chunk.chunkData[j]).dy;
                        break;
                    }
                }
                if (isWater && ORDER_PAINT[frame][i] == LEG) {
                    drawRegion(g, chunk, chunk.getBigId(), chunk.getIcon(frame), 0, 0, Res.arr[chunk.getIcon(frame)][3], 5, xStart + (chunk.getTemplate()[frame]).dx + dx, yStart + (chunk.getTemplate()[frame]).dy - 10, (chunk.getTemplate()[frame]).rotate, mGraphics.BOTTOM | mGraphics.HCENTER);
                } else if (ORDER_PAINT[frame][i] != WEAPON) {
                    drawImage(g, chunk, chunk.getBigId(), chunk.getIcon(frame), xStart + (chunk.getTemplate()[frame]).dx + dx, yStart + (chunk.getTemplate()[frame]).dy + dy, (chunk.getTemplate()[frame]).rotate, mGraphics.BOTTOM | mGraphics.HCENTER, true);
                }
            }
        }
    }

    public void drawRegion(mGraphics g, Chunk chunk, int idbig, int idSmall, int xStart, int yStart, int width, int height, int x, int y, int transform, int anchor) {
        try {
            ImageIcon imgPaint = GameData.getImgIcon((short) (idbig + Res.ID_CHAR));
            if (imgPaint != null && imgPaint.img != null) {
                SmallImage sm = chunk.allSmallImg[idSmall];
                g.drawRegion(imgPaint.img, sm.x, sm.y, sm.w, sm.h, transform, x, y, anchor, false);
            }
        } catch (Exception var15) {
            mSystem.println(idSmall + " >>>>drawreion1 " + idbig);
        }

    }

    public void drawImage(mGraphics g, Chunk chunk, int idbig, int idSmall, int x, int y, int transform, int anchor, boolean isclip) {
        try {
            ImageIcon imgPaint = GameData.getImgIcon((short) (idbig + Res.ID_CHAR));
            if (imgPaint != null && imgPaint.img != null) {
                SmallImage sm = chunk.allSmallImg[idSmall];

                try {
                    int ww = sm.w;
                    if (ww + sm.x > imgPaint.img.getWidth()) {
                        ww = imgPaint.img.getWidth() - sm.x;
                    }

                    int hh = sm.h;
                    if (hh + sm.y > imgPaint.img.getHeight()) {
                        hh = imgPaint.img.getHeight() - sm.y;
                    }

                    g.drawRegion(imgPaint.img, sm.x, sm.y, ww, hh, transform, x, y, anchor, isclip);
                } catch (Exception var14) {
                }
            }
        } catch (Exception var15) {
            Cout.println(idbig + " loi paint id: " + idSmall);
            var15.printStackTrace();
        }

    }

    public static void sortChunk(mVector list, int frame) {
        for (int i = 0; i < list.size() - 1; ++i) {
            Chunk data1 = (Chunk) list.elementAt(i);

            for (int j = i + 1; j < list.size(); ++j) {
                Chunk data2 = (Chunk) list.elementAt(j);
                if (data1.getTemplate()[frame].paintId > data2.getTemplate()[frame].paintId) {
                    Chunk temp = data2;
                    data2 = data1;
                    data1 = temp;
                    list.setElementAt(temp, i);
                    list.setElementAt(data2, j);
                }
            }
        }

    }

    public void startAttack(mVector target, int idskill, int idStart) {
        this.isresetSkill = false;
        if (this.vecListSkill.size() <= 10) {
            if (target.size() > 0) {
                Actor ac = (Actor) target.elementAt(0);
                if (target != null) {
                    this.dir = Util.findDirection(this, ac);
                    if (this.state != 2) {
                        this.state = 2;
                    }

                    DataSkillEff dataskill = new DataSkillEff();
                    dataskill.setInfo(idskill, target, idStart);
                    this.vecListSkill.addElement(dataskill);
                }
            }

        }
    }

    public void startAttack(mVector target, int idskill, int idStart, int[] arrdame) {
        this.isresetSkill = false;
        if (this.vecListSkill.size() <= 10) {
            if (target.size() > 0) {
                Actor ac = (Actor) target.elementAt(0);
                if (target != null) {
                    this.dir = Util.findDirection(this, ac);
                    if (this.state != 2) {
                        this.state = 2;
                    }

                    DataSkillEff dataskill = new DataSkillEff();
                    dataskill.setInfo(idskill, target, idStart);
                    dataskill.arrDame = arrdame;
                    this.vecListSkill.addElement(dataskill);
                }
            }

        }
    }

    public void startAttackMainChar(mVector target, int idskill, int idStart, int idEffect) {
        this.isresetSkill = false;
        if (this.vecListSkill.size() <= 10) {
            if (target.size() > 0) {
                Actor ac = (Actor) target.elementAt(0);
                if (target != null) {
                    this.dir = Util.findDirection(this, ac);
                    if (this.state != 2) {
                        this.state = 2;
                    }

                    DataSkillEff dataskill = new DataSkillEff();
                    dataskill.setInfo(idskill, target, idStart, idEffect);
                    this.vecListSkill.addElement(dataskill);
                }
            }

        }
    }

    public void startBuff(int idStart) {
        this.state = 2;
        DataSkillEff dataskill = new DataSkillEff(idStart);
        dataskill.isbuff = true;
        this.vecListSkill.addElement(dataskill);
    }

    public void startAttack(mVector target, int idskill) {
        this.isresetSkill = false;
        if (this.vecListSkill.size() <= 10) {
            if (this.ID != GameScr.mainChar.ID) {
                Actor ac = (Actor) target.elementAt(0);
                this.dir = Util.findDirection(this, ac);
            }

            if (target != null && target.size() > 0) {
                this.state = 2;
                DataSkillEff dataskill = new DataSkillEff();
                dataskill.setInfo(idskill, target);
                this.vecListSkill.addElement(dataskill);
            }

        }
    }

    public int fullSet() {
        if (this.legStyle != -1 && this.bodyStyle != -1) {
            return this.currentHead == -1 ? 2 : 1;
        } else {
            return 0;
        }
    }

    public void isPoinson() {
        if (mSystem.currentTimeMillis() - this.timeOutPoinson >= (long) (this.tDelay * 1000) && this.tDelay > 0) {
            this.hp -= this.poinson;
            this.totalTime -= this.tDelay;
            this.timeOutPoinson = mSystem.currentTimeMillis();
            if (this.totalTime == 0) {
                this.tDelay = 0;
                this.totalTime = 36;
            }

            if (this.hp <= 0 && this.state != 3) {
                this.hp = 1;
                this.poinson = 0;
                this.tDelay = 0;
                this.totalTime = 36;
            }
        }

    }

    public void addEffectWhenCharRun() {
        if (!this.isPhiHanh) {
            if (!GameCanvas.menuSelectitem.isTabFocus[3] || this.isMainChar()) {
                if (!mSystem.isj2me) {
                    if (this.equals(GameScr.mainChar) && GameCanvas.gameTick % 10 == 0 && !this.isWater) {
                        mSound.playSound(0, mSound.volumeSound);
                    }

                    if (GameCanvas.gameTick % 10 == 0 && this.isWater) {
                        mSound.playSound(1, mSound.volumeSound);
                    }

                    if (!this.isWater) {
                        DataSkillEff partThuCuoi = loadPartPhiPhongThuCuoi(this.idHorse);
                        if (GameCanvas.gameTick % 5 == 0) {
                            int yy = 0;
                            int xx = 0;
                            if (this.dir == 2) {
                                yy = -7;
                                xx = 9;
                                if (partThuCuoi != null) {
                                    yy = 0;
                                    xx = 15;
                                }
                            } else if (this.dir == 3) {
                                yy = -7;
                                xx = -9;
                                if (partThuCuoi != null) {
                                    yy = 0;
                                    xx = -15;
                                }
                            } else if (this.dir == 0) {
                                yy = -7;
                            }

                            EffectSkill.createLowEfect(this.x + xx, this.y + yy, 75, this.TRANSFROM[this.dir]);
                        }

                    }
                }
            }
        }
    }

    public void setCurrentSkillByID(byte skillID) {
        this.currentSkillType = skillID;
        if (this.isDoing) {
            this.currentSkillAnimate = SkillManager.getSkillAnimate(0, this.clazz);
        } else {
            this.currentSkillAnimate = SkillManager.getSkillAnimate(this.currentSkillType, this.clazz);
        }

        this.coolDownSkill[this.currentSkillType] = SkillManager.getSkillCooldown(this.currentSkillType, this.clazz, skillLevelLearnt[skillID]);
    }

    public void resetAllSkill() {
        this.p1 = this.p2 = this.p3 = 0;
        this.currentSkill = null;
        this.vecListSkill.removeAllElements();
        if (this.state != 3) {
            this.state = 0;
        }

        this.frame = 0;
        this.frameEff = 0;
        this.isresetSkill = true;
    }

    public boolean canFire() {
        return this.state != 6 && this.state != 2 && this.state != 7;
    }

    public void jum() {
        if (this.state != 3) {
            super.jum();
        }
    }

    public void update() {
        DataSkillEff effPhatsangVk = loadPartPhiPhongThuCuoi(this.idEffVukhi);
        if (effPhatsangVk != null) {
            byte[] sq = effPhatsangVk.getSequence();
            if (sq != null) {
                this.frameEffVk = (byte) ((this.frameEffVk + 1) % sq.length);
            }
        }

        if (this.buff != null) {
            this.buff.update();
            if (this.buff.wantDestroy) {
                this.buff = null;
            }
        }

        if (this._jum > 0) {
            this._jum = (short) (this._jum - 5);
        }

        if (this.isCatSkill) {
            ++this.tickCatSkill;
        }

        if (this.tickCatSkill >= 10) {
            this.tickCatSkill = 0;
            this.isCatSkill = false;
        }

        if (this.hp > 0 && this.state == 3) {
            GameCanvas.hideAllDialog();
            this.recevie();
        }

        if (this.ID != 32001) {
            if (!this.isFreeze) {
                if (this.EffFace != null) {
                    this.EffFace.update();
                }

                if (this.effPhap != null) {
                    this.effPhap.update();
                }

                if (this.beStune && mSystem.currentTimeMillis() > this.timeBeStune) {
                    this.beStune = false;
                }

                if (this.hp < 0) {
                    this.hp = 0;
                }

                this.isPoinson();
                if (this.delay >= 0) {
                    int kk = (int) (mSystem.currentTimeMillis() - this.timeWait2Board) / '\uea60';
                    this.delay = this.realTime - kk;
                }

                boolean isCanGoto = false;
                if (this.ID == GameScr.mainChar.ID) {
                    if (GameScr.autoFight && GameScr.typeOptionFocus == 1 && mSystem.currentTimeMillis() - this.timeGoToLastXY < 0L) {
                        this.timeGoToLastXY = mSystem.currentTimeMillis() + 30000L;
                        isCanGoto = true;
                    }

                    if (isCanGoto && GameScr.mainChar.posTransRoad == null && GameScr.timeRemovePos <= 0 && (Math.abs(this.x - this.lastXAuto) > 120 || Math.abs(this.y - this.lastYAuto) > 120)) {
                        GameCanvas.gameScr.findRoad2(this.lastXAuto, this.lastYAuto);
                    }
                }
            }

            DataSkillEff partPhiphong = loadPartPhiPhongThuCuoi(this.idEffPhiPhong);
            switch (this.state) {
                case 0:
                    if (this.vecListSkill.size() > 0) {
                        this.state = 2;
                    } else {
                        ++this.p1;
                        if (this.p1 >= STANDFRAME[this.dir].length) {
                            this.p1 = 0;
                        }

                        this.frame = STANDFRAME[this.dir][this.p1];
                        this.weapon_frame = this.frame;
                        if (partPhiphong != null) {
                            this.framePhiPhong = this.frame;
                        }
                    }
                    break;
                case 1:
                    boolean isMatchX = false;
                    boolean isMatchY = false;
                    int dx1 = GameCanvas.abs(this.x - this.xTo);
                    int dy1 = GameCanvas.abs(this.y - this.yTo);
                    int spd = this.speed;
                    if (dx1 <= 100 && dy1 <= 100) {
                        if (dx1 <= 70 && dy1 <= 70) {
                            if (dx1 <= 50 && dy1 <= 50) {
                                if (dx1 > 30 || dy1 > 30) {
                                    spd = this.speed + 1;
                                }
                            } else {
                                spd = this.speed + 2;
                            }
                        } else {
                            spd = this.speed + 3;
                        }
                    } else {
                        spd = this.speed + 4;
                    }

                    if (dx1 < spd) {
                        this.x = this.xTo;
                        isMatchX = true;
                    }

                    if (dy1 < spd) {
                        this.y = this.yTo;
                        isMatchY = true;
                    }

                    if (isMatchX && isMatchY) {
                        this.state = 0;
                        if (this.isMainChar() && GameScr.mainChar.posTransRoad == null) {
                            GameScr.mainChar.sendMove = false;
                        }

                        if (GameScr.isIntro) {
                            this.dir = 3;
                        }

                        this.stand = mSystem.currentTimeMillis();
                    } else {
                        int oldDir = this.dir;
                        if (this.x < this.xTo) {
                            this.x = (short) (this.x + spd);
                            this.dir = 3;
                        } else if (this.x > this.xTo) {
                            this.x = (short) (this.x - spd);
                            this.dir = 2;
                        }

                        if (this.y > this.yTo) {
                            this.y = (short) (this.y - spd);
                            this.dir = 1;
                        } else if (this.y < this.yTo) {
                            this.y = (short) (this.y + spd);
                            this.dir = 0;
                        }

                        boolean isChangeWay = oldDir != this.dir;
                        if (this.isPhiHanh) {
                            ++this.p1;
                            if (this.p1 >= STANDFRAME[this.dir].length) {
                                this.p1 = 0;
                            }

                            this.frame = STANDFRAME[this.dir][this.p1];
                        } else {
                            if (GameCanvas.gameTick % 3 == 0 || isChangeWay) {
                                DataSkillEff partThuCuoi = loadPartPhiPhongThuCuoi(this.idHorse);
                                if (partThuCuoi != null) {
                                    ++this.p1;
                                    if (this.p1 >= RUNFRAME_THU_CUOI[this.dir].length) {
                                        this.p1 = 0;
                                    }

                                    this.frame = RUNFRAME_THU_CUOI[this.dir][this.p1];
                                } else {
                                    ++this.p1;
                                    if (this.p1 >= RUNFRAMEWAL[this.dir].length) {
                                        this.p1 = 0;
                                    }

                                    this.frame = RUNFRAMEWAL[this.dir][this.p1];
                                }
                            }

                            if (partPhiphong != null) {
                                ++this.tickPhiPhong;
                                if (this.tickPhiPhong >= FRAME_PHI_PHONG_RUN[this.dir].length) {
                                    this.tickPhiPhong = 0;
                                }

                                this.framePhiPhong = FRAME_PHI_PHONG_RUN[this.dir][this.tickPhiPhong];
                            }
                        }

                        this.weapon_frame = this.frame;
                        this.addEffectWhenCharRun();
                    }
                    break;
                case 2:
                    if (this.currentSkill == null && this.vecListSkill.size() > 0) {
                        this.currentSkill = (DataSkillEff) this.vecListSkill.elementAt(0);
                        this.vecListSkill.removeElementAt(0);
                        this.frameEff = 0;
                        if (this.currentSkill.idEff == -1) {
                            this.currentSkill.mst.removeAllElements();
                            this.currentSkill = null;
                            this.frameEff = 0;
                            this.state = 0;
                            this.setCanPaint(true);
                            return;
                        }
                    }

                    if (this.currentSkill != null) {
                        this.currentSkill.updateSkillChar(this);
                    }

                    int dir1 = this.dir;
                    if (dir1 == 2) {
                        dir1 = 3;
                    }

                    if (this.currentSkill != null && this.currentSkill.isLoadData) {
                        ++this.frameEff;
                    }

                    try {
                        if (this.currentSkill != null && this.currentSkill.isLoadData && this.frameEff >= this.currentSkill.getFrameChar()[dir1].length) {
                            this.currentSkill.mst.removeAllElements();
                            this.currentSkill = null;
                            this.frameEff = 0;
                            this.state = 0;
                            this.setCanPaint(true);
                        }
                    } catch (Exception var16) {
                        this.currentSkill.mst.removeAllElements();
                        this.currentSkill = null;
                        this.frameEff = 0;
                        this.state = 0;
                        this.setCanPaint(true);
                    }

                    if (this.currentSkill == null) {
                        this.frameEff = 0;
                        this.state = 0;
                    }

                    this.weapon_frame = this.frame;
                    break;
                case 3:
                    ++this.p1;
                    if (this.p1 == 40 && this.ID == GameScr.mainChar.ID) {
                        this.delay = -1;
                        this.EffFace = null;
                    }

                    if (this.effCharDie != null) {
                        this.effCharDie.updateEffCharDie();
                    }

                    this.isAnimal = false;
                    this.myAnimal = null;
                    break;
                case 4:
                    if (this.comeHome) {
                        long n = mSystem.currentTimeMillis() - this.timeComeHome;
                        if (n > 10000L) {
                            GameService.gI().comeHomeWhenDie();
                            this.comeHome = false;
                        }

                        this.time = (int) (n / 1000L);
                        this.time = this.time * 32 / 10;
                        if (this.time > 32) {
                            this.time = 32;
                        }
                    }
                case 5:
                default:
                    break;
                case 6:
                    ++this.p1;
                    if (this.p1 >= RUNFRAMEWAL[this.dir].length) {
                        this.p1 = 0;
                    }

                    this.frame = RUNFRAMEWAL[this.dir][this.p1];
                    if (partPhiphong != null) {
                        ++this.tickPhiPhong;
                        if (this.tickPhiPhong >= FRAME_PHI_PHONG_RUN[this.dir].length) {
                            this.tickPhiPhong = 0;
                        }

                        this.framePhiPhong = FRAME_PHI_PHONG_RUN[this.dir][this.tickPhiPhong];
                    }

                    this.weapon_frame = this.frame;
                    break;
                case 7:
                    ++this.p1;
                    if (this.p1 >= FRAME_START_ATTACK[this.clazz][this.dir].length) {
                        this.vecListSkill.removeElement(this.currentSkill);
                        this.currentSkill = null;
                        this.p1 = 0;
                        if (this.vecListSkill.size() > 0) {
                            this.state = 2;
                        } else {
                            this.state = 0;
                        }

                        this.frameEff = 0;
                    }

                    this.frame = FRAME_START_ATTACK[this.clazz][this.dir][this.p1];
                    this.weapon_frame = this.frame;
                    break;
                case 8:
                    int fdx = this.Flashx - this.x;
                    int fdy = this.Flashy - this.y;
                    int angle = Util.angle(fdx, fdy);
                    int vx = this.speed * Util.cos(angle) >> 10;
                    int vy = this.speed * Util.sin(angle) >> 10;
                    if (Utils.getDistance(this.x, this.y, this.Flashx, this.Flashy) <= this.rangeStop || Tilemap.tileTypeAtPixel(this.x + vx, this.y + vy, 2)) {
                        this.state = 0;
                        if (this.isMainChar()) {
                            GameScr.mainChar.sendMove = false;
                        }

                        this.p1 = 0;
                        return;
                    }

                    if (this.isMainChar()) {
                        GameScr.mainChar.sendMove = false;
                    }

                    ++this.p1;
                    if (this.p1 >= RUNFRAMEWAL[this.dir].length) {
                        this.p1 = 0;
                    }

                    this.frame = RUNFRAMEWAL[this.dir][this.p1];
                    if (partPhiphong != null) {
                        ++this.tickPhiPhong;
                        if (this.tickPhiPhong >= FRAME_PHI_PHONG_RUN[this.dir].length) {
                            this.tickPhiPhong = 0;
                        }

                        this.framePhiPhong = FRAME_PHI_PHONG_RUN[this.dir][this.tickPhiPhong];
                    }

                    this.weapon_frame = this.frame;
                    this.x = (short) (this.x + vx);
                    this.y = (short) (this.y + vy);
            }

            super.update();
        }
    }

    public static DataSkillEff loadPartPhiPhongThuCuoi(int idEffPhiPhong) {
        if (idEffPhiPhong == -1) {
            return null;
        } else {
            DataSkillEff ef = (DataSkillEff) ALL_EFF_PHIPHONG.get(String.valueOf(idEffPhiPhong));
            if (ef == null) {
                ef = new DataSkillEff(idEffPhiPhong);
            }

            return ef;
        }
    }

    public void setPhiPhong(int idEffPhiPhong) {
        this.idEffPhiPhong = idEffPhiPhong;
    }

    public void setHorse(int idHorse) {
        this.dxHorse = new byte[52];
        this.dyHorse = new byte[52];
        this.idHorse = idHorse;
    }

    public void setInfoWearingShopModel(short[] listPart) {
        this.partShopPaint.removeAllElements();
        if (this.partShopPaint.size() == 0) {
            Chunk ph = Chunk.getHead(listPart[HEAD], this.getGender());
            Chunk pb = Chunk.getBody(listPart[BODY], this.getGender());
            Chunk pl = Chunk.getLeg(listPart[LEG], this.getGender());
            Chunk pw = Chunk.getWeapon(listPart[WEAPON]);
            this.partShopPaint.addElement(ph);
            this.partShopPaint.addElement(pb);
            this.partShopPaint.addElement(pl);
            this.partShopPaint.addElement(pw);
        }

    }

    public void setInfoWearing(short[] listPart) {
        this.partPaint.removeAllElements();
        if (this.partPaint.size() == 0) {
            this.myListPart = listPart;
            if (!this.isMainChar() && !this.isNPC() && !paintOrtherChar) {
                return;
            }

            Chunk ph = Chunk.getHead(listPart[HEAD], this.getGender());
            Chunk pb = Chunk.getBody(listPart[BODY], this.getGender());
            Chunk pl = Chunk.getLeg(listPart[LEG], this.getGender());
            Chunk pw = Chunk.getWeapon(listPart[WEAPON]);
            this.partPaint.addElement(ph);
            this.partPaint.addElement(pb);
            this.partPaint.addElement(pl);
            this.partPaint.addElement(pw);
        }

    }

    public void setInfoEffPhatsang(short[] listphatSang) {
    }

    public void setEffWeapon(int id) {
        this.idEffVukhi = id;
    }

    public int getIDNpc() {
        return this.isNPC() ? this.idBot : -1;
    }

    public short getIDicon() {
        return this.isNPC() ? (short) (this.idBot + Res.ID_ICON_NPC) : -1;
    }

    public String getStrTalk() {
        return this.isNPC() ? T.npcCharTalk : "";
    }

    public String getCmdName() {
        return this.isNPC() ? T.giaotiep : "";
    }

    public boolean isNPC() {
        return this.idBot != -1;
    }

    public boolean isNpcChar() {
        return this.isNPC();
    }

    public int getTypeNpc() {
        return this.isNPC() ? this.idBot : super.getTypeNpc();
    }

    public boolean setWay(int vX, int vY) {
        short xx = this.x;
        if (vX != 0) {
            if (!Tilemap.tileTypeAtPixel(this.x + vX, this.y - 16, 2) && !Tilemap.tileTypeAtPixel(this.x, this.y - 16, 2)) {
                this.moveTo(xx, (short) (this.y - 16));
                return true;
            }

            if (!Tilemap.tileTypeAtPixel(this.x + vX, this.y + 16, 2) && !Tilemap.tileTypeAtPixel(this.x, this.y + 16, 2)) {
                this.moveTo(xx, (short) (this.y + 16));
                return true;
            }
        } else if (vY != 0) {
            if (!Tilemap.tileTypeAtPixel(this.x - 16, this.y + vY, 2) && !Tilemap.tileTypeAtPixel(this.x - 16, this.y, 2)) {
                this.moveTo((short) (this.x - 16), this.y);
                return true;
            }

            if (!Tilemap.tileTypeAtPixel(this.x + 16, this.y + vY, 2) && !Tilemap.tileTypeAtPixel(this.x + 16, this.y, 2)) {
                this.moveTo((short) (this.x + 16), this.y);
                return true;
            }
        }

        return false;
    }

    public void addDynamicEffBuffTop(DynamicEffect d, int add_remove) {
        for (int i = 0; i < this.dynamicEffTop.size(); ++i) {
            if (((DynamicEffect) this.dynamicEffTop.elementAt(i)).id == d.id) {
                if (add_remove == 1) {
                    ((DynamicEffect) this.dynamicEffTop.elementAt(i)).timeExist = 0L;
                }

                return;
            }
        }

        if (add_remove == 0) {
            this.dynamicEffTop.addElement(d);
        }

    }

    public void addDynamicEffBuffBottom(DynamicEffect d, int add_remove) {
        for (int i = 0; i < this.dynamicEffBottom.size(); ++i) {
            if (((DynamicEffect) this.dynamicEffBottom.elementAt(i)).id == d.id) {
                if (add_remove == 1) {
                    ((DynamicEffect) this.dynamicEffBottom.elementAt(i)).timeExist = 0L;
                }

                return;
            }
        }

        if (add_remove == 0) {
            this.dynamicEffBottom.addElement(d);
        }

    }

    public void updateDynamicBuff() {
        int i;
        DynamicEffect dym;
        if (this.dynamicEffTop != null) {
            for (i = 0; i < this.dynamicEffTop.size(); ++i) {
                dym = (DynamicEffect) this.dynamicEffTop.elementAt(i);
                dym.update();
                if (dym.canDestroy()) {
                    this.dynamicEffTop.removeElementAt(i);
                }
            }
        }

        if (this.dynamicEffBottom != null) {
            for (i = 0; i < this.dynamicEffBottom.size(); ++i) {
                dym = (DynamicEffect) this.dynamicEffBottom.elementAt(i);
                dym.update();
                if (dym.canDestroy()) {
                    this.dynamicEffBottom.removeElementAt(i);
                }
            }
        }

    }

    public int getDir() {
        return this.dir;
    }

    public boolean isMe() {
        if (GameScr.mainChar == null) {
            return false;
        } else {
            return this.ID == GameScr.mainChar.ID;
        }
    }

    public static Char Me() {
        return GameScr.mainChar;
    }

    public void setCanPaint(boolean canPaint) {
        this.canPaint_ = canPaint;
    }

    public void resetSkill() {
        this.currentSkill = null;
    }

    public void setEquipChar(Item[] it) {
        for (int i = 0; i < it.length; ++i) {
            this.equip[i] = it[i];
        }

    }

    public void setCoolDown(int time) {
        this.coolDown = mSystem.currentTimeMillis() + (long) (time * 1000);
    }

    public long getCoolDown() {
        return this.coolDown - mSystem.currentTimeMillis();
    }

    public byte getClazz() {
        return this.clazz;
    }

    public boolean getCanpaint() {
        return this.canPaint_;
    }

    public void DropHP(int hp) {
        this.hp -= hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMP(int mp) {
        this.mp = mp;
    }

    public void setlvPercent(int lv) {
        this.lvpercent = (short) lv;
    }

    public void setLV(int lv) {
        this.lv = (short) lv;
    }

    public String getName() {
        return this.name;
    }

    public void paintFriend(mGraphics g, int x, int y, int pos) {
        if (GameCanvas.gameTick % 5 == 0) {
            if (pos == 0) {
                this.frameWearing = (byte) ((this.frameWearing + 1) % 2);
            } else {
                this.frameWearing = 0;
            }
        }

        this.paintCharAVTFriend(g, x, y, this.partPaint, 0, this.frameWearing, false);
    }

    public void paintSelectChar(mGraphics g, int x, int y, int pos) {
        if (GameCanvas.gameTick % 5 == 0) {
            if (pos == 0) {
                this.frameWearing = (byte) ((this.frameWearing + 1) % 2);
            } else {
                this.frameWearing = 0;
            }
        }

        this.paintSelect(g, x, y, this.partPaint, 0, this.frameWearing, false);
    }


    public void paintCharAVTFriend(mGraphics g, int xStart, int yStart, mVector chunks, int dir, int frame, boolean isWater) {
        int dx = 0, dy = 0;
        Chunk chunk = null;
        for (int i = 0; i < (ORDER_PAINT[frame]).length; i++) {
            if (i < chunks.size()) {
                chunk = (Chunk) chunks.elementAt(ORDER_PAINT[frame][i]);
                dx = 0;
                dy = 0;
                for (int j = 0; j < chunk.chunkData.length; j++) {
                    if ((chunk.chunkData[j]).index == frame) {
                        dx = (chunk.chunkData[j]).dx;
                        dy = (chunk.chunkData[j]).dy;
                        break;
                    }
                }
                if (ORDER_PAINT[frame][i] == HEAD)
                    drawImage(g, chunk, chunk.getBigId(), chunk.getIcon(frame), xStart + (chunk.getTemplate()[frame]).dx + dx, yStart + (chunk.getTemplate()[frame]).dy + dy, (chunk.getTemplate()[frame]).rotate, mGraphics.BOTTOM | mGraphics.HCENTER, true);
            }
        }
    }

    public void paintIconPK(mGraphics g) {
        if (this.typePK >= 0 && this.typePK <= 6) {
            g.drawRegion(GameScr.imgPK, 0, 12 * (this.typePK * 3 + GameCanvas.gameTick / 3 % 3), 12, 12, 0, this.x, this.y - 85 - this.yFly - this._jum + this.dyWater, 3, false);
        }

    }

    public void paintPartyInfo(mGraphics g) {
    }

    public void setidNhom(int id) {
        this.idNhom = (short) id;
        this.isCatSkill = false;
    }

    public void dieActor(Actor from) {
        this.BeginDie();
    }

    public boolean getIsCatSkill() {
        return this.isCatSkill;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setIsCatSkill(boolean iscat) {
        this.isCatSkill = iscat;
    }

    public void Flash(int mXto, int mYto, int range) {
        this.state = 8;
        this.Flashx = (short) mXto;
        this.Flashy = (short) mYto;
        this.rangeStop = (short) range;
        if (this.isMainChar()) {
            GameScr.mainChar.sendMove = false;
        }

    }

    public int getIDparty() {
        return this.idNhom;
    }

    public boolean canInvite() {
        return this.chuc_vu_clan == 2 || this.chuc_vu_clan == 1 || this.chuc_vu_clan == 0;
    }

    public int getidClan() {
        return this.idIconClan;
    }

    public void setPhihanh(boolean isPhihanh) {
        this.isPhiHanh = isPhihanh;
    }

    public void addMainBuff(int type, int timelive) {
        this.buff = new MainBuff(type, mSystem.currentTimeMillis() + (long) timelive);
    }

    public int getColorMiniMap() {
        if (this.isNPC()) {
            int[] colorPaint = new int[]{16520709, 16499718, 396543, 14812156};
            int frameQuest = GameScr.getIdImgQuest(this.idBot);
            return frameQuest == -1 ? colorPaint[3] : colorPaint[frameQuest];
        } else {
            return 0;
        }
    }
}

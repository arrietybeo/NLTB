package code.screen.screen;

import code.effect.FlyText;
import code.effect.new_skill.EffectEnd;
import code.effect.new_skill.EffectSkill;
import code.main.GameCanvas;
import code.model.Actor;
import code.model.AnimateEffect;
import code.model.BossBienBucCongTu;
import code.model.BossGame;
import code.model.BossTruongDoTe;
import code.model.Boss_6x;
import code.model.Boss_Nguoi_Tuyet;
import code.model.Char;
import code.model.CharCountDown;
import code.model.ChatInfo;
import code.model.ChatPopup;
import code.model.DataSkillEff;
import code.model.Dropable;
import code.model.EffectManager;
import code.model.IActionListener;
import code.model.ImageIcon;
import code.model.Item;
import code.model.Lightning;
import code.model.MainChar;
import code.model.Monster;
import code.model.MonsterInfo;
import code.model.MonsterTemplate;
import code.model.NPC;
import code.model.Npc_Server;
import code.model.Point;
import code.model.Position;
import code.model.Quest;
import code.model.QuestInfo;
import code.model.QuickSlot;
import code.model.ReadMessenge;
import code.model.SkillClan;
import code.model.T;
import code.model.Target;
import code.model.TimecountDown;
import code.model.TreeTopBottom;
import code.model.mCommand;
import code.model.arrow.Arrow;
import code.model.arrow.BaBuran;
import code.model.arrow.IArrow;
import code.model.arrow.MagicBeam;
import code.model.arrow.WeaponsAngleBoss;
import code.model.arrow.WeaponsFire;
import code.model.arrow.WeaponsLazer;
import code.network.GameService;
import code.screen.MenuLogin;
import code.screen.MsgChat;
import code.screen.Res;
import code.screen.SkillTemplate;
import code.screen.Util;
import code.screen.Utils;
import code.screen.event.EventScreen;
import code.screen.event.MainEvent;

import java.util.Enumeration;
import javax.microedition.lcdui.Image;

import lib.Cout;
import lib.MainUnity;
import lib.Rms;
import lib.Session_ME;
import lib.Tilemap;
import lib.Tree;
import lib.mGraphics;
import lib.mHashtable;
import lib.mRandom;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.Message;
import lib2.TField;
import lib2.mFont;

public class GameScr extends ScreenTeam implements IActionListener {
    public static String nameMap = "";
    public static final byte KILL_MOSTER = 0;
    public static final byte GET_ITEM = 2;
    public static final byte TRANSPORT = 1;
    public int gssx;
    public int gssy;
    public int gssw;
    public int gssh;
    public int gssxe;
    public int gssye;
    int[] CMVIBRATEX = new int[]{-1, 2, 1, -2};
    int[] CMVIBRATEY = new int[]{-3, 2, -1, 1};
    public static byte[] SERVER_ID = new byte[2];
    int currentCmVibrate = -1;
    public static String version = "2.9.9";
    public static int idNPCshopInven;
    public static int catNPCshopInven;
    public static boolean lockcmx;
    public static boolean lockcmy;
    public mVector actors = new mVector();
    public mVector npc_limit = new mVector();
    public static mVector arrowsUp = new mVector();
    public mVector arrowsDown = new mVector();
    public int hideGUI = 0;
    public boolean readyGetGameLogic = true;
    public boolean isFillScr = false;
    public boolean isCloud;
    public static mVector shop = new mVector();
    public int timerung;
    public int timeQouckSlot;
    public static String[] strHelpNPC = null;
    public static byte StepHelpServer = 0;
    boolean changeSinceLastUpdate;
    public static int gsw;
    public static int gsh;
    public static int gscmdBarY;
    public static int gshw;
    public static int gshh;
    public static int gswd3;
    public static int gshd3;
    public static int gsw2d3;
    public static int gsh2d3;
    public static int gsw3d4;
    public static int gsh3d4;
    public static int gswd6;
    public static int gshd6;
    public mVector vecCharParty = new mVector();
    public static mVector serverInfo = new mVector();
    public GameService gameService = GameService.gI();
    public String[] flyTextString;
    public int[] flyTextX;
    public int[] flyTextY;
    public int[] flyTextDx;
    public int[] flyTextDy;
    public int[] flyTextState;
    public int[] flyTextColor;
    public static mVector VecInfoServer = new mVector();
    public static mVector VecTime = new mVector();
    public short saveIDViewInfoAnimal = -1;
    public static Image backFocus;
    public static Image imghpmp;
    public static Image imglv;
    public static Image backNum;
    public static Image imgRect;
    public static Image imgLbtn;
    public static Image imgLbtnFocus;
    public static Image imgLbtn2;
    public static Image imgLbtnFocus2;
    public static Image imgAnalog1;
    public static Image imgAnalog2;
    public static Image imgMSG;
    public static Image imgPK;
    public static Image imgBackArena;
    public static Image imgTexFeil;
    public static Image imgtinhanh;
    public static Image imgthulinh;
    public static Image imgEvent;
    public static Image imgSao;
    public static mRandom r = new mRandom(mSystem.currentTimeMillis());
    public boolean chatMode = false;
    public boolean isBuffAuto;
    public boolean chatWorld;
    public TField tfChat = new TField();
    public TField tfChatWorld = new TField();
    mVector chatHistory = new mVector();
    int CHAT_HISTORY_SIZE = 5;
    public static int tempIDActorParty = -1;
    public short questID = 1;
    public short current_npc_talk = -1;
    public int indexContent = -1;
    public int countMainCharAttack;
    public short idNpcReceive = 5;
    public static byte idItemClanquest = -1;
    public static Position posNpcReceiveClan;
    public boolean receiveQuest = false;
    public boolean ispaint12Plus;
    public boolean isDis = false;
    public String[] content_quest = null;
    public static String info_gif_quest = null;
    public static String titleQuest = "";
    public static Image[] imgfocus;
    public static Image[] imgCong;
    public static Image[] imgTouchMove;
    public static Image[] imgSo;
    public static Image[] imgBoder;
    public static Image[] imgBk;
    public static Image[] imgArrow;
    public static Image[] imgMenuList;
    public static Image[] imgArrowMenu;
    public static Image[] imgFly;
    public static Image[] imgMenu;
    public static Image[] imgMenuListClan;
    public static Image[] imgMenuListHD;
    public static Image[] imgMenuListSettings;
    public static Image[] imgButton;
    public static Image[] imghealth;
    public static Image[] imgShadow;
    public static Image[] imgCloud;
    public static Image bt_Speak;
    public static Image imgWater;
    public static Image imgBlock;
    public static Image coloritem;
    public static Image imgquest;
    public static Image imgStart;
    public static Image imgBackItem;
    public static Image imgTextfileld;
    public static Image imgStone;
    public static Image imgBkEp;
    public static Image imgPointMinimap;
    public static Image imgPointQuest;
    public static Image imgIconGold;
    public static Image imgCheck;
    public static Image imgIncrease;
    public static Image coloritem_small;
    public static Image imgMoney;
    public static Image imgPoint;
    public static Image imgInfo;
    public static Image imgHpMonster;
    public static Image imgW;
    public static Image imgIconTN;
    public static Image imgMainMenu;
    public static Image imgMainMenu1;
    public static Image imgBgMainMenu;
    public static Image imgloading;
    public static Image imghand;
    public static Image imgBack;
    public static Image statusInfo;
    public static Image imgCheckevent;
    public static Image imgPlus;
    public static Image imgauto;
    public static Image peticon;
    public static Image petinfo;
    public static Image imgNguhanh;
    public Position posNpcRequest;
    public static mVector vec_skill = new mVector();
    public mVector vecCloud = new mVector();
    int yMsgChat = 0;
    int toYchat = -18;
    int startMsg;
    public int indexBuff = -1;
    public int timetouch;
    private boolean isTouchMenu;
    private boolean isAutoChangeFocus;
    public static long timeBuff = -1L;
    public static int indexTabSlot = 0;
    public static int xSlot = 0;
    public static int idMapPK = -1;
    public static int iTaskAuto = 0;
    public Actor focusedActor;
    public static byte xArena;
    public static byte yArena;
    public static byte wArena;
    public static byte hArena;
    public static int arena;
    public static int[] timeChiemThanh;
    public static int[] curTimeCT;
    public static short[] idChiemThanh;
    public static String[] nameChiemThanh;
    public static String[] nameClan;
    public static String[] questContent;
    public static String[] textQuest;
    public static byte CountQuestConten;
    public static byte numMSG;
    private static boolean isFindChar = false;
    private static boolean isActorMove = false;
    private static boolean isFindMonster = false;
    private static boolean isQuest = false;
    public static boolean isGost;
    public static mVector effAnimate = new mVector();
    public static mVector effManager;
    public static mVector msgWorld = new mVector();
    public static mVector savemsgWorld = new mVector();
    public static byte typeOptionFocus = 0;
    public static GameScr me;
    public static boolean isIntro;
    public static boolean instruction = false;
    public static boolean isSetinfo = false;
    public static boolean canmove;
    public static int CountIntro = 0;
    public static int CountMoveFirst = 0;
    public static int steep;
    public static int Next;
    public static mVector VecInfoChar = new mVector();
    public static final byte STATE_FINISH = 1;
    public static final byte STATE_WORKING = 2;
    public static final byte STATE_UN_RECEIVE = 0;
    public static final byte STATE_DONE = 3;
    public int xSaveAuto;
    public int ySaveAuto;
    public int countTouchmove;
    public int xp;
    public int yp;
    public static mVector allQuestCanReceive = new mVector();
    public static mVector allQuestWorking = new mVector();
    public static mVector allQuestFinish = new mVector();
    public static Quest mainQuest;
    public static Quest subQuest;
    public static Quest clanQuest;
    public static mHashtable hashQuestCanReceive = new mHashtable();
    public static mHashtable hashQuestWorking = new mHashtable();
    public static mHashtable hashQuestFinish = new mHashtable();
    public static mHashtable hashEffInfo = new mHashtable();
    int[] mKeyMove = new int[]{4, 6, 2, 8};
    public static int timeremoveItem;
    public static DataSkillEff[] dataColorItem = new DataSkillEff[4];
    public mVector ALL_SKILL = new mVector();
    public int charclass = 0;
    public int fFocus;
    public int tickicon;
    public int waitFocus;
    public int idMapColor;
    public static int numMess;
    public static int hShowEvent = 0;
    public static int wShowEvent = 122;
    public static int timeEvent;
    MainEvent eventShow = null;
    public static mVector vFlytext = new mVector();
    public static int lastMap = -1;
    public String textinfomainChar = "";
    public int inDexfont = 0;
    public int tickpaintFont;
    public int tickHP;
    public int tickMP;
    public int inDexHP;
    public int inDexMP;
    public int tickConnect;
    public static Image imgGhost;
    public static Image[] imgBigMap;
    public static Actor Ghost;
    public static short[] TIME_BETWEEN_ATTACK = new short[]{2000, 500, 1000, 1500, 1000};
    public static long coolDown = 0L;
    public static String[] infoAutoGetItem;
    public short[] idNPC_Sell;
    public mCommand cmdLogin;
    public mCommand cmdmenu;
    public mCommand cmdskip;
    public mCommand cmdchat;
    public mCommand cmdChangeFocus;
    public mCommand cmdCloseChat;
    public mCommand cmdDochat;
    public mCommand cmdhoisinhtaicho;
    public mCommand cmdvelang;
    public mCommand cmdwait;
    public mCommand cmdDisconect;
    public byte countQuest;
    public static final byte INFO_CLAN = 0;
    public static final byte INVITE_MEMBER = 1;
    public static final byte ACCEPT_MEMBER = 2;
    public static final byte KICK_MEMBER = 3;
    public static final byte DEL_CLAN = 4;
    public static final byte OUT_CLAN = 5;
    public static final byte PROMOTE_MEMBER = 6;
    public static final byte DONATE_XU = 7;
    public static final byte DONATE_LUONG = 8;
    public static final byte PHAT_LUONG_CA_NHAN = 9;
    public static final byte PHAT_LUONG_CHUC_VU = 10;
    public static final byte CLAN_INFO_FOR_MEMBER = 11;
    public static final byte CLAN_INVENTORY = 12;
    public static mCommand cmdTinden;
    public static CharCountDown charcountdonw;
    public short idIconX2;
    public static int xL;
    public static int xR;
    public static int xU;
    public static int xD;
    public static int yL;
    public static int yR;
    public static int yU;
    public static int yD;
    public static int yTouchBar;
    public static int xChat;
    public static int yChat;
    public static int wChat;
    public static int hChat;
    public static int xFire;
    public static int yFire;
    public static int xSkill1;
    public static int ySkill1;
    public static int xSkill2;
    public static int ySkill2;
    public static int xSkill3;
    public static int ySkill3;
    public static int xSkill4;
    public static int ySkill4;
    public static int xMenu;
    public static int yMenu;
    public static int xMove;
    public static int yMove;
    public static int xCenter;
    public static int yCenter;
    public static int xTouchMove;
    public static int yTouchMove;
    public static int yms;
    public static int xpress;
    public static int ypress;
    public static String[] SV_IP = new String[]{"27.0.14.122", "115.84.183.56"};
    public static String[] PORT = new String[]{"19129", "19129"};
    public int[] ypc;
    public static int R;
    public static int angle;
    public static int curAngle;
    public static boolean isTouchMove;
    boolean isPress;
    int idtouch = -1;
    public static int[] disFlytext = new int[]{15, 25, 35, 40};
    public static int tickFly;
    int HPBARW;
    int EXPBARW;
    int HPBARW_MONSTER;
    private Position posMiniMap;
    public static int wMiniMap;
    public static int hMiniMap;
    private Position[] posQuickSlot;
    private int[] numPC;
    public long lastTimePing;
    public long pingDelay;
    private int pingNextIn = 10;
    public static long timeStandWhenAuto;
    short idmap = 0;
    byte idskilltest = 0;
    int lvmap = 0;
    public static long countdowm = 0L;
    public static boolean startCountdow = false;
    public static byte bossfire;
    public static Monster Bossintro;
    public boolean isBigFocus;
    public boolean isTouchChat;
    int xMsgServer = 0;
    static final int[][] C = new int[][]{{-90, 90, -90, 90}, {-90, 90, -90, 90}, {-90, 90, -90, 90}, {-90, 90, -90, 90}};
    public mVector nearActors = new mVector();
    public static byte[][] DX = new byte[][]{{0, 0, -48, 48}, {0, 0, -32, 32}, {0, 0, -16, 16}, new byte[4]};
    public static byte[][] DY = new byte[][]{{48, -48, 0, 0}, {32, -32, 0, 0}, {16, -16, 0, 0}, new byte[4]};
    public static final byte MAX_INVENTORY = 42;
    public int[] QUICKSLOT_KEY = new int[]{1, 3, 5, 7, 9};
    public int[] QUICKSLOT_KEY_PC = new int[]{5, 1, 3, 7, 9};
    boolean finishQuest = false;
    public static boolean autoFight = false;
    public static boolean saveAutoFight;
    public static boolean isBeginAutoBuff;
    public static boolean isAutoBomHp;
    public static boolean isAutoBomMp;
    public static boolean autoBomHMP;
    public boolean isAutoRangeWhenAuto;
    public static long timeDelayHpMp;
    public static Quest currQuest = null;
    public static int timeDelayTask = 50;
    public static boolean cheat = false;
    int[] SLOTINDEXOFKEY = new int[]{0, 0, 0, 1, 0, 2, 0, 3, 0, 4};
    public mVector vecSkill = new mVector();
    public int indexSkill = 0;
    boolean first = false;
    public static int timeRemovePos;
    public int a = 0;
    public int b = 0;
    public int coutChangeFocusWhenDoing;
    boolean paintMapPopup = false;
    mVector menuItems = new mVector();
    private int[] colorPaint = new int[]{16520709, 16499718, 396543, 1101907};
    private static int[] colorMini = new int[]{6898216, 11897430, 14469298};
    public static int dx = 0;
    public static int dy = 0;
    public static int timeVibrateScreen = 0;
    byte idInfo = 0;
    byte xInfo;
    byte yInfo;
    public static int[] postSkill;
    public int indexTouch = 0;
    public static int[] ColorHpFocus = new int[]{15829117, 15756897, 15685704, 15089711, 13904665, 11409416};
    public static int[] ColorHpFocus1 = new int[]{10943139, 7399019, 4509758, 2799908, 1416463, 555523};
    public short map = -1;
    public boolean isNewVersionAvailable;
    public static boolean isDisConect = false;
    public static boolean isMeLogin = false;
    public static long timeReconnect = System.currentTimeMillis();
    public static int paintCay = 1;
    public static int paintChar = 1;
    public mVector decriptNap = new mVector();
    public mVector syntaxNap = new mVector();
    public mVector centerNap = new mVector();
    public static int pTicket = 1000;
    public static int cmtoYmini;
    public static int cmyMini;
    public static int cmdyMini;
    public static int cmvyMini;
    public static int cmtoXMini;
    public static int cmxMini;
    public static int cmdxMini;
    public static int cmvxMini;
    public static long timeTranMini;
    private Position posCam;
    private Target tg = new Target();
    public short xBeginFrame = 0;
    public short yBeginFrame = 0;
    public short xTheendFrame = 0;
    public short yTheendFrame = 0;
    public static byte[][] mapFind;
    public static byte xStart;
    public static byte yStart;
    public static boolean isFixBugAutoQuest;
    public int iTaskAutoLast;
    mVector listMSGServer;
    public SkillClan[] skillClan;
    public SkillClan[] skillClanPrivate;
    public static byte[][] TYPE_MP_HP = new byte[2][];
    public static int[][] VALUE_MP_HP = new int[2][];
    public static boolean isSendFinishQuest = false;
    int treeIndex = 0;
    int actorIndex = 0;
    int npcLimit = 0;
    public static int[] DELTACAMERAX = new int[]{0, 0, -20, 20};
    public static int[] DELTACAMERAY = new int[]{20, -20, 0, 0};
    int[] xChange = new int[]{4, 0, -4};
    int countChang;
    public static mVector PosNPCQuest = new mVector();
    byte lastDir = 0;
    byte dir = 0;
    byte countStep = 0;
    short moveToX = 0;
    short moveToY = 0;
    public mVector vMonster = new mVector();
    public static mHashtable ID_BOSS;
    public static mHashtable ALL_SKILL_TEMPLATE_BOSS;
    public static short dtmove = 250;
    public static long timeMove = System.currentTimeMillis();
    public static int[] postLoginX = new int[]{192, 200, 204, 208, 168, 176};
    public static int[] postLoginY = new int[]{704, 712, 716, 720, 256, 256};
    public static Location[] allLocation = null;
    public static MainChar mainChar = new MainChar();
    public static byte[] idCharClazz = new byte[]{1, 0, 3, 2, 4};
    public static String[] nameClazz = new String[]{"hoalongbang", "truclamtu", "vocucdao", "thuylienvien", "machucoc"};
    public static int[] xxx = new int[]{0, 0, 0, 50, 50};
    public static int[] yyy = new int[]{60, 120, 180, 90, 150};
    public static short[] ToxFirst = new short[]{315, 343, 520, 496};
    public static short[] ToyFirst = new short[]{160, 344, 159, 335};
    int[] xit = new int[]{814, 774, 854, 814, 774};
    int[] yit = new int[]{259, 275, 319, 339, 319};
    int[] xLogin = new int[]{5, 10, 15, 20, 25};
    int[] yLogin = new int[]{5, 10, 15, 20, 25};
    int[] xit_mons = new int[]{1004, 774, 854, 914, 774};
    public static int[] postCharLoginX = new int[]{688, 224, 192, 592, 496};
    public static int[] postCharLoginY = new int[]{704, 672, 176, 416, 80};
    public static mVector vecCharintro = new mVector();
    int count = 0;
    int tickwait;
    int wait;
    public static mVector tam = new mVector();
    public static boolean ismovefirst;
    public static boolean fireIntro;
    public static int[] posRange = new int[]{-16, 16, -32, 32, 10, 48, 5, -48};
    public int[] possIntroX = new int[]{-30, 30, -40, 40, -50, 50, -60, 60, -70, 70};
    public int[] possIntroY = new int[]{-10, 10, -15, 15, -20, 20, -25, 25, 5, -5};
    public mVector charlogin = new mVector();
    public static int[][] arrSkill = new int[][]{{0, 1, 2}, {0, 9, 10}, {0, 5, 6}, {0, 7, 8}, {0, 3, 4}};
    public static int co = 0;
    public static int timepopup;
    public static int clazz;
    public static int SteepCount;
    public static int chatcount;
    public static boolean canfire;
    public static boolean finishTalk;
    public int countAuto;
    public int indexKeyTouchAuto;
    public int xStartAuto;
    public int yStartAuto;
    public int rangeAuto = 100;
    public int indexIdFocus;
    public boolean isStartAutoAttack;
    public boolean beginAuto;
    public boolean isFindNexTarget;
    public boolean isMovebyTouch;
    public static boolean isnextmap = false;
    public long WaitTips;

    public static GameScr gI() {
        return me == null ? (me = new GameScr()) : me;
    }

    public static void CheckIP() {
        if (mSystem.isIos) {
            SV_IP = new String[]{"5l.teamobi.com", "5l2.teamobi.com"};
        }

        Rms.LoadIndexSv();
    }

    public void initTouchMove() {
        if (GameCanvas.isTouch) {
            yTouchBar = GameCanvas.h - 100;
            xL = 3;
            yms = 18;
            xTouchMove = 52;
            yTouchMove = yTouchBar + yms;
            xpress = 20;
            ypress = yTouchMove;
            yL = yTouchBar + 17;
            xU = 37;
            xMove = 37;
            yMove = GameCanvas.h - 50;
            yU = yTouchBar - 17;
            xR = 72;
            yR = yL;
            xD = xU;
            yD = yTouchBar + 52;
            xCenter = xU;
            yCenter = yU;
            int x00 = GameCanvas.w - 62;
            int y00 = yTouchBar + 15;
            xFire = x00 + 10;
            yFire = y00 + 15;
            xSkill1 = x00 - 16;
            ySkill1 = y00 + 62;
            xSkill2 = x00 - 16;
            ySkill2 = y00 + 25;
            xSkill3 = x00 + 4;
            ySkill3 = y00 - 6;
            xSkill4 = x00 + 40;
            ySkill4 = y00 - 15;
            xChat = 4;
            yChat = 40;
            wChat = 30;
            hChat = 30;
            xMenu = GameCanvas.w / 2 - 22;
            yMenu = GameCanvas.h - 12;
            if (mSystem.isPC) {
                xFire = GameCanvas.w - 50;
                yFire = GameCanvas.h - 49 - 2;
                xSkill1 = GameCanvas.w - 70;
                ySkill1 = GameCanvas.h - 23 - 2;
                xSkill2 = GameCanvas.w - 110;
                ySkill2 = GameCanvas.h - 23 - 2;
                xSkill3 = GameCanvas.w - 150;
                ySkill3 = GameCanvas.h - 23 - 2;
                xSkill4 = GameCanvas.w - 190;
                ySkill4 = GameCanvas.h - 23 - 2;
                this.ypc = new int[5];
                this.ypc[0] = -7;
                this.ypc[1] = -12;
                this.ypc[2] = -12;
                this.ypc[3] = -12;
                this.ypc[4] = -10;
                this.numPC = new int[5];
                xMenu = (GameCanvas.w - ySkill4) / 2 + 40;
                this.numPC[0] = xFire + 21;
                this.numPC[1] = xSkill1;
                this.numPC[2] = xSkill2;
                this.numPC[3] = xSkill3;
                this.numPC[4] = xSkill4;
            }

            curAngle = -1;
        }
    }

    public void paintTouchMove(mGraphics g) {
        if (!this.isMovebyTouch) {
            if (!mSystem.isPC) {
                if (!GameCanvas.menuSelectitem.showMenu) {
                    if (!GameCanvas.menu2.isShow) {
                        if (!GameCanvas.menu.showMenu) {
                            if (!MainChar.blockkey) {
                                if (this.hideGUI != 2) {
                                    if (GameCanvas.currentDialog == null) {
                                        if (!this.chatMode && !this.chatWorld) {
                                            g.drawImage(imgTouchMove[0], 20, yTouchMove, 0, false);
                                            if (keyTouch == 0) {
                                                g.drawImage(imgTouchMove[1], xpress + 16, ypress, 0, false);
                                            }

                                            if (keyTouch == 1) {
                                                g.drawRegion(imgTouchMove[1], 0, 0, mGraphics.getImageWidth(imgTouchMove[1]), mGraphics.getImageHeight(imgTouchMove[1]), 7, xpress + 50, ypress + 15, 0, false);
                                            }

                                            if (keyTouch == 2) {
                                                g.drawRegion(imgTouchMove[1], 0, 0, mGraphics.getImageWidth(imgTouchMove[1]), mGraphics.getImageHeight(imgTouchMove[1]), 4, xpress + 1, ypress + 15, 0, false);
                                            }

                                            if (keyTouch == 3) {
                                                g.drawRegion(imgTouchMove[1], 0, 0, mGraphics.getImageWidth(imgTouchMove[1]), mGraphics.getImageHeight(imgTouchMove[1]), 3, xpress + 16, ypress + 49, 0, false);
                                            }

                                            if (!Tilemap.isMapIntro() && !GameCanvas.isTouch) {
                                                g.drawImage(imgMenu[this.isTouchMenu ? 1 : 0], xMenu, yMenu, 0, false);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void switchToMe() {
        super.switchToMe();
        GameCanvas.keyHold[2] = false;
        this.init();
        vecCharintro.removeAllElements();
        this.charlogin.removeAllElements();
        tam.removeAllElements();
    }

    public mVector getSkilltarget(int rangeSkill, int maxtarget, byte cat) {
        mVector ntarget = new mVector();
        int i;
        Actor ac;
        if (isIntro) {
            if (this.focusedActor != null && this.focusedActor.catagory == 1) {
                ntarget.addElement(this.focusedActor);
            }

            for (i = 0; i < this.actors.size(); ++i) {
                ac = (Actor) this.actors.elementAt(i);
                if (ac != null && ac.catagory == 1 && ac.getState() != 5 && ac.getState() != 1) {
                    ntarget.addElement(ac);
                    ac.DropHP(100);
                }
            }

            return ntarget;
        } else if (!Res.inRangeActor(mainChar, this.focusedActor, rangeSkill)) {
            return ntarget;
        } else {
            if (cat == 1) {
                if (this.focusedActor.getState() != 8 && this.focusedActor.getState() != 5 && this.focusedActor.getState() != 1 && mainChar.setFireChar(this.focusedActor)) {
                    ntarget.addElement(this.focusedActor);
                }

                if (maxtarget == 1) {
                    return ntarget;
                }

                for (i = 0; i < this.actors.size(); ++i) {
                    ac = (Actor) this.actors.elementAt(i);
                    if (ac != null && ac.getState() != 8 && !ac.equals(this.focusedActor) && ac.getState() != 5 && ac.getState() != 1 && Res.inRangeActor(mainChar.x, mainChar.y, ac.getXmonster(), ac.getYmonster(), rangeSkill)) {
                        if (ntarget.size() >= maxtarget) {
                            break;
                        }

                        if (mainChar.setFireChar(ac)) {
                            ntarget.addElement(ac);
                        }
                    }
                }
            }

            if (cat == 0) {
                if (this.focusedActor.equals(mainChar)) {
                    ntarget.removeAllElements();
                    return ntarget;
                }

                if (this.focusedActor.getState() != 3 && mainChar.setFireChar(this.focusedActor)) {
                    ntarget.addElement(this.focusedActor);
                }

                if (maxtarget == 1) {
                    return ntarget;
                }

                for (i = 0; i < this.actors.size(); ++i) {
                    ac = (Actor) this.actors.elementAt(i);
                    if (ac != null && ac.catagory == 0 && !ac.equals(this.focusedActor) && ac.getState() != 3 && Utils.getDistance(mainChar, ac) <= rangeSkill && ac.canFocus() && mainChar.setFireChar(ac)) {
                        if (ntarget.size() >= maxtarget) {
                            break;
                        }

                        ntarget.addElement(ac);
                    }
                }
            }

            return ntarget;
        }
    }

    public void checkAutoAttack(byte isauto) {
        if (isauto == 1 && GameCanvas.menuSelectitem.isAutoDanh[0] && !Tilemap.ismapLang) {
            this.beginAuto = true;
        }

    }

    public void doAttack(int idskill, int idQuickSlot) {
        if (idskill != -1) {
            if (this.focusedActor != null) {
                if (mainChar.state != 3) {
                    if (this.focusedActor == null || this.focusedActor.catagory == 0 || this.focusedActor.catagory == 1) {
                        new mVector();
                        SkillTemplate skill = (SkillTemplate) vec_skill.elementAt(idskill);
                        if (skill != null) {
                            short ntarget = skill.getTarget(MainChar.levelSkill[idskill]);
                            int mplose = skill.getmplose(MainChar.levelSkill[idskill]);
                            if (mplose > mainChar.mp && !isIntro) {
                                GameCanvas.addNotify(T.khongdump, (byte) 0);
                                return;
                            }

                            if (!Res.inRangeActor(mainChar, this.focusedActor, skill.range)) {
                                mainChar.Flash(this.focusedActor.x, this.focusedActor.y, skill.range);
                                mainChar.dir = Util.findDirection(mainChar.x, mainChar.y, this.focusedActor.x, this.focusedActor.y);
                                return;
                            }

                            mVector mons = this.getSkilltarget(skill.range, ntarget, this.focusedActor.catagory);
                            if (mons.size() <= 0) {
                                return;
                            }

                            if (mons != null && mons.size() > 0) {
                                if (this.focusedActor != null) {
                                    mainChar.dir = Util.findDirection(mainChar, this.focusedActor);
                                } else {
                                    Actor act = (Actor) mons.elementAt(0);
                                    mainChar.dir = Util.findDirection(mainChar, act);
                                }

                                this.countMainCharAttack = (this.countMainCharAttack + 1) % 100;
                                mainChar.startAttackMainChar(mons, skill.idSkillCode, skill.idEffStartSkill, this.countMainCharAttack);
                                int time = 0;
                                if (!Tilemap.isMapIntro()) {
                                    if (this.focusedActor.catagory == 1) {
                                        GameService.gI().attackMonster(mons, idskill, this.countMainCharAttack);
                                    } else if (this.focusedActor.catagory == 0) {
                                        GameService.gI().attackPlayer(mons, idskill, this.countMainCharAttack);
                                    }

                                    if (!this.beginAuto) {
                                        this.beginAuto = true;
                                        this.xSaveAuto = mainChar.x;
                                        this.ySaveAuto = mainChar.y;
                                    }

                                    this.indexKeyTouchAuto = (this.indexKeyTouchAuto + 1) % 5;
                                    coolDown = mSystem.currentTimeMillis() + (long) TIME_BETWEEN_ATTACK[mainChar.clazz];
                                    time = TIME_BETWEEN_ATTACK[mainChar.clazz];

                                    for (int i = 0; i < MainChar.mQuickslot.length; ++i) {
                                        if (MainChar.mQuickslot[i] != null && !isGost) {
                                            MainChar.mQuickslot[i].setCoolDownChar(time);
                                        }
                                    }
                                }

                                if (!isGost) {
                                    MainChar.mQuickslot[idQuickSlot].startCoolDown(time);
                                }
                            }
                        }

                    }
                }
            }
        }
    }

    public void doTouchQuickSlot(int id) {
        if (!MainChar.blockkey) {
            if (coolDown - mSystem.currentTimeMillis() <= 0L) {
                if (GameCanvas.isTouch && !mSystem.isPC) {
                    id -= 4;
                }

                if (!this.chatMode || GameCanvas.isTouch) {
                    if (this.focusedActor != null && this.focusedActor.isNPC() && id == 0) {
                        this.doFire();
                    } else if (this.focusedActor != null && this.focusedActor.getTypeMove() == 5) {
                        GameService.gI().startCountdown(this.focusedActor.catagory, this.focusedActor.ID);
                    } else if (id >= 0 && id <= MainChar.mQuickslot.length - 1) {
                        QuickSlot ql = MainChar.mQuickslot[id];
                        if (this.focusedActor != null && this.focusedActor.catagory == 0 && !this.focusedActor.isNPC() && !this.focusedActor.beFire()) {
                            if (Tilemap.ismapLang) {
                                this.doParty();
                                return;
                            }

                            if (!mainChar.setFireChar(this.focusedActor)) {
                                this.doParty();
                                return;
                            }
                        }

                        if (ql.quickslotType == 2) {
                            if (ql.typePotion == Item.TYPE_HP && mainChar.hp >= mainChar.maxhp) {
                                return;
                            }

                            if (ql.typePotion == Item.TYPE_MP && mainChar.mp >= mainChar.maxmp) {
                                return;
                            }

                            if (ql.canUsePotion()) {
                                GameService.gI().useItem(ql.getidPotion());
                                ql.startCoolDown(0);
                            }
                        } else if (ql.quickslotType == 1) {
                            if (ql != null && ql.canfight() && ql.isBuff()) {
                                if (mainChar.canFire() && mainChar.canUseSkill()) {
                                    SkillTemplate skill = (SkillTemplate) vec_skill.elementAt(ql.getSkillType());
                                    if (skill != null && ql.canfight() && mainChar.canFire()) {
                                        int mplose = skill.getmplose(MainChar.levelSkill[ql.getSkillType()]);
                                        if (mplose > mainChar.mp && !isIntro) {
                                            GameCanvas.addNotify(T.khongdump, (byte) 0);
                                            return;
                                        }

                                        mainChar.startBuff(skill.idEffStartSkill);
                                        ql.startCoolDown(0);
                                        if (!this.beginAuto) {
                                            this.beginAuto = true;
                                            this.xSaveAuto = mainChar.x;
                                            this.ySaveAuto = mainChar.y;
                                        }

                                        this.indexKeyTouchAuto = (this.indexKeyTouchAuto + 1) % 5;
                                        GameService.gI().use_Buff(ql.getSkillType());
                                    }

                                    return;
                                }

                                return;
                            }

                            if (this.focusedActor != null && this.focusedActor.catagory > 2) {
                                if (!mainChar.isFullInven()) {
                                    GameService.gI().getDropableFromGround(this.focusedActor.ID);
                                }

                                return;
                            }

                            if (this.focusedActor != null && this.focusedActor.isNPC() && id == 0) {
                                this.doFire();
                                return;
                            }

                            if (!mainChar.canFire() || !mainChar.canUseSkill()) {
                                return;
                            }

                            if (ql != null && ql.canfight() && !ql.isBuff()) {
                                this.doAttack(ql.getSkillType(), id);
                            }
                        }

                    }
                }
            }
        }
    }

    public void doUseItem(int itemType) {
    }

    public void updateTouchMove() {
        if (charcountdonw != null)
            return;
        if (isIntro && mainChar.posTransRoad != null)
            return;
        if (GameCanvas.menuSelectitem.showMenu)
            return;
        if (GameCanvas.currentDialog != null)
            return;
        if (GameCanvas.menu2.isShow || GameCanvas.menu.showMenu || GameCanvas.currentDialog != null)
            return;
        int rangle = 34;
        this.isPress = false;
        for (int i = 0; i < GameCanvas.isPointerDown.length; i++) {
            if (!this.isMovebyTouch)
                if (GameCanvas.isPointerDown[i] && !mSystem.isPC) {
                    this.idtouch = i;
                    if (GameCanvas.isPointer(0, yTouchMove - 10, 100, 84, i))
                        if (Utils.getDistance(xpress + 32, ypress + 32, GameCanvas.px[i], GameCanvas.py[i]) > 10) {
                            int gocMove = Util.angle(GameCanvas.px[i] - xpress + 32,
                                    GameCanvas.py[i] - ypress + 32);
                            int value = 0;
                            if (gocMove > 45 && gocMove <= 135) {
                                value = 3;
                            } else if (gocMove > 135 && gocMove <= 225) {
                                value = 0;
                            } else if (gocMove > 225 && gocMove <= 315) {
                                value = 2;
                            } else {
                                value = 1;
                            }
                            GameCanvas.clearKeyHold();
                            if (value == 0) {
                                keyTouch = 2;
                            } else if (value == 1) {
                                keyTouch = 1;
                            } else if (value == 2) {
                                keyTouch = 0;
                            } else if (value == 3) {
                                keyTouch = 3;
                            }
                            this.isPress = true;
                            this.timetouch = 3;
                            this.timeQouckSlot = 3;
                            GameCanvas.keyHold[this.mKeyMove[value]] = true;
                        } else {
                            GameCanvas.clearKeyHold();
                        }
                } else if (this.idtouch == i) {
                    this.idtouch = -1;
                    xpress = 20;
                    ypress = yTouchMove;
                    this.countTouchmove = 0;
                    this.xp = -1;
                    this.yp = -1;
                }
            if (GameCanvas.isPointerHoldIn(0, 0, rangle, rangle, i)) {
                keyTouch = 9;
                this.isPress = true;
            } else {
                boolean b1 = GameCanvas.isPointerDown[i];
            }
            if (GameCanvas.isPointerClick[i] &&
                    GameCanvas.isPointer(xFire, yFire, 42, 42, i) && GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 4;
                if (mSystem.isPC)
                    keyTouch -= 4;
                doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i] &&
                        !mSystem.isPC)
                    GameCanvas.keyPressedz[5] = true;
                GameCanvas.isPointerClick[i] = false;
            }
            if (GameCanvas.isPointerClick[i] &&
                    GameCanvas.isPointer(xSkill1 - 15, ySkill1 - 15, 30, 30, 0) &&
                    GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 5;
                if (mSystem.isPC)
                    keyTouch -= 4;
                doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i])
                    GameCanvas.keyPressedz[1] = true;
                GameCanvas.isPointerClick[i] = false;
            }
            if (GameCanvas.isPointerClick[i] &&
                    GameCanvas.isPointer(xSkill2 - 15, ySkill2 - 15, 30, 30, i) &&
                    GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 6;
                if (mSystem.isPC)
                    keyTouch -= 4;
                doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i])
                    GameCanvas.keyPressedz[3] = true;
                GameCanvas.isPointerClick[i] = false;
            }
            if (GameCanvas.isPointerClick[i] &&
                    GameCanvas.isPointerHoldIn(xSkill3 - 15, ySkill3 - 15, 30, 30, i) &&
                    GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 7;
                if (mSystem.isPC)
                    keyTouch -= 4;
                doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i])
                    GameCanvas.keyPressedz[7] = true;
                GameCanvas.isPointerClick[i] = false;
            }
            if (GameCanvas.isPointerClick[i] &&
                    GameCanvas.isPointerHoldIn(xSkill4 - 15, ySkill4 - 15, 30, 30, i) &&
                    GameCanvas.canTouch()) {
                this.timeQouckSlot = 3;
                keyTouch = 8;
                if (mSystem.isPC)
                    keyTouch -= 4;
                doTouchQuickSlot(keyTouch);
                this.isPress = true;
                if (GameCanvas.isPointerJustRelease[i] && GameCanvas.isPointerClick[i])
                    GameCanvas.keyPressedz[9] = true;
                GameCanvas.isPointerClick[0] = false;
            }
            if (!GameCanvas.isPointerDown[i] &&
                    keyTouch == 9)
                if (this.right != null)
                    this.right.performAction();
            if (GameCanvas.isPointerJustRelease[i]) {
                GameCanvas.keyHold[8] = false;
                GameCanvas.keyHold[6] = false;
                GameCanvas.keyHold[4] = false;
                GameCanvas.keyHold[2] = false;
            }
        }
    }

    public static int abs(int i) {
        return i > 0 ? i : -i;
    }

    public static void loadBegin() {
        try {
            try {
                ReadMessenge.versionImage = Byte.parseByte(Rms.loadRMSString("vsImg"));
            } catch (Exception var1) {
            }

            imgfocus = new Image[4];

            int i;
            for (i = 0; i < imgfocus.length; ++i) {
                imgfocus[i] = GameCanvas.loadImage("/interface/f" + i + ".png");
            }

            backNum = GameCanvas.loadImage("/interface/backNum.png");
            backFocus = GameCanvas.loadImage("/interface/back.png");
            imgTexFeil = GameCanvas.loadImage("/hd/imgTextfileld.png");
            ChatPopup.imgPopup = GameCanvas.loadImage("/interface/popup.png");
            imgBackArena = GameCanvas.loadImage("/interface/khu.png");
            imgSao = GameCanvas.loadImage("/interface/sao.png");
            bt_Speak = GameCanvas.loadImage("/interface/bt_Speak.png");
            imgPK = GameCanvas.loadImage("/interface/pk.png");
            imgRect = GameCanvas.loadImage("/interface/rect.png");
            imgMSG = GameCanvas.loadImage("/interface/msg.png");
            imgWater = GameCanvas.loadImage("/interface/water.png");
            imgBlock = GameCanvas.loadImage("/interface/lock.png");
            coloritem = GameCanvas.loadImage("/interface/color.png");
            coloritem_small = GameCanvas.loadImage("/interface/color1.png");
            imgStart = GameCanvas.loadImage("/interface/start.png");
            imgquest = GameCanvas.loadImage("/interface/quest.png");
            if (imgButton == null) {
                imgShadow = new Image[6];

                for (i = 0; i < imgShadow.length; ++i) {
                    imgShadow[i] = GameCanvas.loadImage("/interface/b" + i + ".png");
                }

                imgCloud = new Image[2];

                for (i = 0; i < imgCloud.length; ++i) {
                    imgCloud[i] = GameCanvas.loadImage("/interface/c" + i + ".png");
                }

                imgBack = GameCanvas.loadImage("/interface/backinfo.png");
                imghealth = new Image[4];

                for (i = 0; i < imghealth.length; ++i) {
                    imghealth[i] = GameCanvas.loadImage("/interface/m" + i + ".png");
                }

                imghand = GameCanvas.loadImage("/interface/selected_hand.png");
                imgBkEp = GameCanvas.loadImage("/hd/bg1.png");
                imgButton = new Image[7];

                for (i = 0; i < imgButton.length; ++i) {
                    imgButton[i] = GameCanvas.loadImage("/hd/imgCommand" + i + ".png");
                }

                imgBackItem = GameCanvas.loadImage("/hd/imgBackItem.png");
                imgPoint = GameCanvas.loadImage("/interface/move.png");
                statusInfo = GameCanvas.loadImage("/hd/statusInfo.png");
                imgTouchMove = new Image[4];

                for (i = 0; i < 4; ++i) {
                    imgTouchMove[i] = GameCanvas.loadImage("/hd/k" + i + ".png");
                }

                imgMainMenu = GameCanvas.loadImage("/hd/mainmenu.png");
                imgMainMenu1 = GameCanvas.loadImage("/hd/mainmenu1.png");
                imgBgMainMenu = GameCanvas.loadImage("/hd/bg.png");
                imgSo = new Image[4];
                imgSo[0] = GameCanvas.loadImage("/hd/ar0.png");
                imgSo[1] = GameCanvas.loadImage("/hd/ar1.png");
                imgSo[2] = GameCanvas.loadImage("/hd/ar2.png");
                imgSo[3] = GameCanvas.loadImage("/hd/ar3.png");
                imgBoder = new Image[10];

                for (i = 0; i < imgBoder.length; ++i) {
                    imgBoder[i] = GameCanvas.loadImage("/hd/bd/imgBD" + i + ".png");
                }

                imgBk = new Image[3];

                for (i = 0; i < imgBk.length; ++i) {
                    imgBk[i] = GameCanvas.loadImage("/hd/imgBackground" + i + ".png");
                }

                imgArrow = new Image[2];

                for (i = 0; i < imgArrow.length; ++i) {
                    imgArrow[i] = GameCanvas.loadImage("/hd/imgArrowBig" + i + ".png");
                }

                imgMoney = GameCanvas.loadImage("/hd/money.png");
                imgMenuList = new Image[2];

                for (i = 0; i < imgMenuList.length; ++i) {
                    imgMenuList[i] = GameCanvas.loadImage("/hd/main/" + (i == 0 ? 3 : 5) + ".png");
                }

                imgArrowMenu = new Image[2];

                for (i = 0; i < imgArrowMenu.length; ++i) {
                    imgArrowMenu[i] = GameCanvas.loadImage("/hd/imgArrowMenu" + i + ".png");
                }

                imgTextfileld = GameCanvas.loadImage("/hd/imgTextfileld.png");
                imgW = GameCanvas.loadImage("/hd/iconCharWearing.png");
                imgIconTN = GameCanvas.loadImage("/hd/iconTN.png");
                imgMenu = new Image[2];

                for (i = 0; i < 2; ++i) {
                    imgMenu[i] = GameCanvas.loadImage("/hd/menu/bt_menu" + i + ".png");
                }

                imgIconGold = GameCanvas.loadImage("/hd/imggold.png");
                imgloading = GameCanvas.loadImage("/interface/waiting.png");
                imgCheck = GameCanvas.loadImage("/hd/check.png");
                imgIncrease = GameCanvas.loadImage("/hd/increase.png");
                imgPointMinimap = GameCanvas.loadImage("/w.png");
                imgPointQuest = GameCanvas.loadImage("/q.png");
                imgCheckevent = GameCanvas.loadImage("/check.png");
                imgPlus = GameCanvas.loadImage("/plus.png");
                imgauto = GameCanvas.loadImage("/interface/auto.png");
                imgtinhanh = GameCanvas.loadImage("/0.png");
                imgthulinh = GameCanvas.loadImage("/1.png");
                imglv = GameCanvas.loadImage("/lv.png");
                imghpmp = GameCanvas.loadImage("/interface/hpmp.png");
                imgEvent = GameCanvas.loadImage("/event.png");
                if (MainMenu.isPet) {
                    peticon = GameCanvas.loadImage("/peticon.png");
                }

                petinfo = GameCanvas.loadImage("/petinfo.png");
                imgNguhanh = GameCanvas.loadImage("/h.png");
                imgFly = new Image[4];

                for (i = 0; i < imgFly.length; ++i) {
                    imgFly[i] = GameCanvas.loadImage("/text/" + i + ".png");
                }

                ChangScr.load();
            }
        } catch (Exception var2) {
            System.out.println(" loi load hinh GameScr");
        }

    }

    public void addChat(ChatInfo ci) {
        if (this.chatHistory.size() > 50) {
            this.chatHistory.removeElementAt(0);
            this.yMsgChat -= 18;
            this.toYchat -= 18;
        }

        this.chatHistory.addElement(ci);
        this.toYchat += 18;
    }

    public final void loadFlyText() {
        this.flyTextX = new int[15];
        this.flyTextY = new int[15];
        this.flyTextDx = new int[15];
        this.flyTextDy = new int[15];
        this.flyTextState = new int[15];
        this.flyTextColor = new int[15];
        this.flyTextString = new String[15];

        for (int i = 0; i < 15; ++i) {
            this.flyTextState[i] = -1;
        }

    }

    public static void addFlyText(String text, int x, int y, int colorText, boolean isFont3d) {
        FlyText t = new FlyText(text, x, y, colorText, isFont3d, disFlytext[tickFly]);
        tickFly = (tickFly + 1) % (disFlytext.length - 1);
        vFlytext.addElement(t);
    }

    public final void startFlyText(String flyString, int flyColor, int x, int y, int dx, int dy) {
        if (!flyString.equals("-0") && !flyString.equals("- 0")) {
            int n = -1;

            for (int i = 0; i < 15; ++i) {
                if (this.flyTextState[i] == -1) {
                    n = i;
                    break;
                }
            }

            if (n != -1) {
                this.flyTextString[n] = flyString;
                this.flyTextX[n] = x;
                this.flyTextY[n] = y;
                this.flyTextDx[n] = dx * (r.nextInt(2) == 1 ? -2 : 2);
                this.flyTextDy[n] = dy;
                this.flyTextState[n] = 0;
                this.flyTextColor[n] = flyColor;
            }
        }
    }

    public final void startFlyText(String flyString, int flyColor, int x, int y, int dx, int dy, int dir) {
        if (!flyString.equals("-0") && !flyString.equals("- 0")) {
            int n = -1;

            for (int i = 0; i < 15; ++i) {
                if (this.flyTextState[i] == -1) {
                    n = i;
                    break;
                }
            }

            if (n != -1) {
                this.flyTextString[n] = flyString;
                this.flyTextX[n] = x;
                this.flyTextY[n] = y;
                this.flyTextDx[n] = dx * dir;
                this.flyTextDy[n] = dy;
                this.flyTextState[n] = 0;
                this.flyTextColor[n] = flyColor;
            }
        }
    }

    public final void updateFlyText() {
        int i;
        for (i = 0; i < 15; ++i) {
            if (this.flyTextState[i] != -1) {
                int[] var10000 = this.flyTextState;
                var10000[i] += Util.abs(this.flyTextDy[i]);
                if (this.flyTextState[i] > 30) {
                    this.flyTextState[i] = -1;
                }

                var10000 = this.flyTextX;
                var10000[i] += this.flyTextDx[i];
                var10000 = this.flyTextY;
                var10000[i] += this.flyTextDy[i];
            }
        }

        for (i = 0; i < vFlytext.size(); ++i) {
            FlyText t = (FlyText) vFlytext.elementAt(i);
            if (t != null) {
                t.update();
                if (t.wantDestroy) {
                    vFlytext.removeElement(t);
                }
            }
        }

    }

    public void paintFlyText(mGraphics g) {
        int i;
        for (i = 0; i < 15; ++i) {
            if (this.flyTextState[i] != -1) {
                if (this.flyTextColor[i] != 0) {
                    if (this.flyTextColor[i] == 2) {
                        if (mSystem.isAndroid) {
                            mFont.tahoma_7_black.drawString(g, this.flyTextString[i], this.flyTextX[i] + 1, this.flyTextY[i] + 1, 0, false);
                            mFont.tahoma_7_red.drawString(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 0, false);
                        } else {
                            mFont.paintStaccato_red(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 0, false);
                        }
                    } else if (mSystem.isAndroid) {
                        mFont.tahoma_7_black.drawString(g, this.flyTextString[i], this.flyTextX[i] + 1, this.flyTextY[i] + 1, 0, false);
                        mFont.tahoma_7_yellow.drawString(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 0, false);
                    } else {
                        mFont.paintStaccato_yeallow(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 0, false);
                    }
                } else if (mSystem.isAndroid) {
                    mFont.tahoma_7_black.drawString(g, this.flyTextString[i], this.flyTextX[i] + 1, this.flyTextY[i] + 1, 0, false);
                    mFont.tahoma_7_yellow.drawString(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 0, false);
                } else {
                    mFont.paintStaccato_yeallow(g, this.flyTextString[i], this.flyTextX[i], this.flyTextY[i], 0, false);
                }
            }
        }

        for (i = 0; i < vFlytext.size(); ++i) {
            FlyText t = (FlyText) vFlytext.elementAt(i);
            if (t != null) {
                t.paint(g);
            }
        }

    }

    public void init() {
        this.initTouchMove();
        this.tfChat.x = 2;
        this.tfChat.y = GameCanvas.h - (GameCanvas.isTouch ? 30 : 40);
        this.tfChat.width = GameCanvas.w - 4;
        TField var10000;
        if (mSystem.isPC) {
            var10000 = this.tfChat;
            var10000.y -= 12;
            this.tfChat.x = 25;
            this.tfChat.width = GameCanvas.w - 4 - 46;
        }

        this.tfChat.setStringNull(T.chatcongcong);
        this.tfChatWorld.x = 2;
        this.tfChatWorld.y = GameCanvas.h - (GameCanvas.isTouch ? 30 : 40);
        this.tfChatWorld.width = GameCanvas.w - 4;
        this.tfChatWorld.height = 25;
        this.tfChatWorld.setStringNull(T.hoiKTG);
        if (mSystem.isPC) {
            var10000 = this.tfChatWorld;
            var10000.y -= 12;
            this.tfChatWorld.x = 25;
            this.tfChatWorld.width = GameCanvas.w - 4 - 46;
        }

        if (this.posQuickSlot == null) {
            this.posQuickSlot = new Position[5];

            for (int i = 0; i < 5; ++i) {
                this.posQuickSlot[i] = new Position();
            }
        }

        this.posQuickSlot[0].x = GameCanvas.hw - 47;
        this.posQuickSlot[0].y = GameCanvas.h - 19;
        this.posQuickSlot[1].x = GameCanvas.hw - 28;
        this.posQuickSlot[1].y = GameCanvas.h - 19;
        this.posQuickSlot[2].x = GameCanvas.hw - 8;
        this.posQuickSlot[2].y = GameCanvas.h - 20;
        this.posQuickSlot[3].x = GameCanvas.hw + 12;
        this.posQuickSlot[3].y = GameCanvas.h - 19;
        this.posQuickSlot[4].x = GameCanvas.hw + 31;
        this.posQuickSlot[4].y = GameCanvas.h - 19;
        this.setPosMiniMap();
        this.isTouchMenu = false;
        this.cmdmenu = new mCommand("", this, 1);
        this.cmdskip = new mCommand("", this, 0);
        if (GameCanvas.isTouch) {
            this.cmdskip.setXY(5, 0);
            this.cmdskip.caption = T.boqua;
            this.cmdskip.setLine();
        }

        if (!GameCanvas.isTouch) {
            this.left = this.cmdmenu;
            this.cmdskip.caption = T.boqua;
        }

        this.cmdchat = new mCommand("", this, 3);
        this.cmdchat.setindexImage(5);
        this.cmdchat.setXY(GameCanvas.w - 25 + 1, hMiniMap);
        this.cmdChangeFocus = new mCommand("", this, 7);
        this.cmdChangeFocus.setindexImage(6);
        this.cmdChangeFocus.setXY(GameCanvas.w - 8 - this.cmdChangeFocus.wCmd + 2, GameCanvas.h - 145);
        if (!GameCanvas.isTouch) {
            int xdiv = 35;
            postSkill = new int[5];
            int mid = GameCanvas.w / 2;
            postSkill[2] = mid;
            postSkill[1] = mid - xdiv;
            postSkill[0] = mid - xdiv * 2;
            postSkill[3] = mid + xdiv;
            postSkill[4] = mid + xdiv * 2;
        }

        this.cmdCloseChat = new mCommand(T.close, this, 20);
        this.cmdDochat = new mCommand("Chat", this, 21);
    }

    public GameScr() {
        this.loadCamera();
        this.countQuest = 0;
        this.tfChat = new TField();
        this.tfChat.height = ScreenTeam.ITEM_HEIGHT + (GameCanvas.isTouch ? 8 : 2);
        this.tfChat.isFocus = true;
        timeremoveItem = 60;
        this.init();
        this.isBuffAuto = false;
        this.isAutoChangeFocus = true;
        this.loadFlyText();
        this.setPosMiniMap();
        this.HPBARW = 45;
        this.EXPBARW = 71;
        this.HPBARW_MONSTER = 50;
        if (this.HPBARW_MONSTER < 30) {
            this.HPBARW_MONSTER = 30;
        }

        this.xMsgServer = GameCanvas.w - 20;
        this.indexKeyTouchAuto = 0;
        this.WaitTips = mSystem.currentTimeMillis() + 60000L;
    }

    public void startIntro() {
        countdowm = 0L;
        startCountdow = false;
        bossfire = 0;
        steep = 0;
        isIntro = true;
        isSetinfo = false;
        EffectManager.lowEffects.removeAllElements();
        EffectManager.hiEffects.removeAllElements();
        this.cmdLogin = new mCommand(T.login, this, 106);
        if (GameCanvas.isTouch) {
            this.cmdLogin.setPos(GameCanvas.w - this.cmdLogin.wCmd / 2 - 17, 0);
            this.cmdLogin.setLine();
        }

    }

    public void setPosMiniMap() {
        if (Tilemap.imgMap != null) {
            hMiniMap = GameCanvas.h / 5;
            if (hMiniMap > 100) {
                hMiniMap = 100;
            }

            this.posMiniMap = new Position(GameCanvas.hw + 50, GameCanvas.h - deltaY - hMiniMap);
            wMiniMap = GameCanvas.w - this.posMiniMap.x - 1;
            if (wMiniMap > 100) {
                wMiniMap = 100;
            }

            if (Tilemap.imgMap != null) {
                if (wMiniMap > Tilemap.imgMap.getWidth()) {
                    wMiniMap = Tilemap.imgMap.getWidth();
                }

                if (hMiniMap > Tilemap.imgMap.getHeight()) {
                    hMiniMap = Tilemap.imgMap.getHeight();
                }
            }

            this.posMiniMap.x = GameCanvas.w - wMiniMap;
            this.posMiniMap.y = 0;
        }
    }

    public void loadCamera() {
        gsw = GameCanvas.w;
        gsh = GameCanvas.h;
        gshw = gsw >> 1;
        gshh = gsh >> 1;
        gswd3 = gsw / 3;
        gshd3 = gsh / 3;
        gsw2d3 = 2 * gsw / 3;
        gsh2d3 = 2 * gsh / 3;
        gsw3d4 = 3 * gsw / 4;
        gsh3d4 = 3 * gsh / 4;
        gswd6 = gsw / 6;
        gshd6 = gsh / 6;
        this.gssw = (gsw >> 4) + 2;
        this.gssh = (gsh >> 4) + 2;
        if (gsw % 24 != 0) {
            ++this.gssw;
        }

        cmxLim = (Tilemap.w << 4) - gsw;
        cmyLim = (Tilemap.h << 4) - gsh;
        if (mainChar != null) {
            cmx = cmtoX = mainChar.x - gshw;
            cmy = cmtoY = mainChar.y - gshh;
        }

        if (cmx < 0) {
            cmx = 0;
        }

        if (cmx > cmxLim) {
            cmx = cmxLim;
        }

        if (cmy < 0) {
            cmy = 0;
        }

        if (cmy > cmyLim) {
            cmy = cmyLim;
        }

        this.gssx = (cmx >> 4) - 1;
        if (this.gssx < 0) {
            this.gssx = 0;
        }

        this.gssy = cmy >> 4;
        this.gssxe = this.gssx + this.gssw;
        this.gssye = this.gssy + this.gssh;
        if (this.gssy < 0) {
            this.gssy = 0;
        }

        if (this.gssye > Tilemap.h - 1) {
            this.gssye = Tilemap.h - 1;
        }

        lockcmy = false;
        lockcmx = false;
        if (Tilemap.pxw < gsw + 32) {
            lockcmx = true;
            cmx = -(gsw - Tilemap.pxw) >> 1;
        }

        if (Tilemap.pxh < gsh) {
            lockcmy = true;
            cmy = -(gsh - Tilemap.pxh) >> 1;
        }

    }

    public final void updateCamera() {
        if (cmx != cmtoX && !lockcmx) {
            cmvx = cmtoX - cmx << 2;
            cmdx += cmvx;
            cmx += cmdx >> 4;
            cmdx &= 15;
            if (cmx < 0) {
                cmx = 0;
            }

            if (cmx > cmxLim) {
                cmx = cmxLim;
            }
        }

        if (cmy != cmtoY && !lockcmy) {
            cmvy = cmtoY - cmy << 2;
            cmdy += cmvy;
            cmy += cmdy >> 4;
            cmdy &= 15;
            if (cmy < 0) {
                cmy = 0;
            }

            if (cmy > cmyLim) {
                cmy = cmyLim;
            }
        }

        if (this.currentCmVibrate >= 0) {
            cmx += this.CMVIBRATEX[this.currentCmVibrate];
            cmy += this.CMVIBRATEY[this.currentCmVibrate];
            ++this.currentCmVibrate;
            if (this.currentCmVibrate == 4) {
                this.currentCmVibrate = -1;
            }

            if (cmx < 0) {
                cmx = 0;
            }

            if (cmx > cmxLim) {
                cmx = cmxLim;
            }

            if (cmy < 0) {
                cmy = 0;
            }

            if (cmy > cmyLim) {
                cmy = cmyLim;
            }
        }

        this.gssx = (cmx >> 4) - 1;
        if (this.gssx < 0) {
            this.gssx = 0;
        }

        this.gssy = cmy >> 4;
        this.gssxe = this.gssx + this.gssw;
        if (this.gssxe > Tilemap.w) {
            this.gssxe = Tilemap.w;
        }

        if (this.gssye >= Tilemap.h) {
            this.gssye = Tilemap.h;
        }

        this.gssye = this.gssy + this.gssh;
        if (this.gssy < 0) {
            this.gssy = 0;
        }

        if (this.gssye > Tilemap.h - 1) {
            this.gssye = Tilemap.h;
        }

    }

    public void updateMainCharWhenAuto() {
        if (!autoFight) {
            this.isAutoRangeWhenAuto = false;
        } else {
            if (timeStandWhenAuto - mSystem.currentTimeMillis() / 1000L <= 0L) {
                timeStandWhenAuto = mSystem.currentTimeMillis() / 1000L + 10L;
            }

        }
    }

    void tesskill() {
        if (GameCanvas.isKeyPressed(5)) {
            this.doAttack(this.idskilltest, 0);
        }

        if (GameCanvas.isKeyPressed(0)) {
            ++this.idskilltest;
            if (this.idskilltest > 26) {
                this.idskilltest = 1;
            }
        }

        if (GameCanvas.isKeyPressed(9)) {
            --this.idskilltest;
            if (this.idskilltest < 1) {
                this.idskilltest = 26;
            }
        }

        if (GameCanvas.isKeyPressed(7)) {
            MainChar var10000 = mainChar;
            var10000.clazz = (byte) (var10000.clazz + 1);
            if (mainChar.clazz > 5) {
                mainChar.clazz = 0;
            }

            mainChar.setInfoWearing(Char.idPartTest[mainChar.clazz][0]);
        }

    }

    public void updateIntro() {
        if (this.cmdskip != null && GameCanvas.isTouch && getCmdPointerLast(this.cmdskip) && Tilemap.isMapIntro()) {
            GameCanvas.isPointerJustRelease[0] = false;
            if (this.cmdskip != null)
                this.cmdskip.performAction();
        }
        if (this.cmdLogin != null && GameCanvas.isTouch && getCmdPointerLast(this.cmdLogin) && Tilemap.isMapIntro()) {
            GameCanvas.isPointerJustRelease[0] = false;
            if (this.cmdLogin != null)
                this.cmdLogin.performAction();
        }
        if (timepopup >= 0)
            timepopup--;
        if (!isSetinfo)
            CountIntro++;
        if (!isSetinfo && CountIntro > 30) {
            createCharIntro();
            isSetinfo = true;
        }
        if (isIntro && CountIntro <= 90 && !fireIntro)
            CountMoveFirst++;
        if (!fireIntro && CountMoveFirst > 90 && steep == 0) {
            if (timepopup <= 0 && !finishTalk)
                doTalk();
            if (timepopup <= 0 && finishTalk)
                doTalk();
            if (GameCanvas.isKeyPressed(5) || GameCanvas.isPointerClick[0]) {
                timepopup = 0;
                for (int i = 0; i < vecCharintro.size(); i++) {
                    Actor ac = (Actor) vecCharintro.elementAt(i);
                    if (ac != null)
                        ac.removechat();
                }
            }
        }
        if (fireIntro && steep == 0)
            doAttackChartest();
        if (!fireIntro && steep == 4)
            doAttackChartest();
        if (steep == 1) {
            if (this.tickwait > 0)
                this.tickwait--;
            if (this.tickwait <= 0) {
                Bossintro = createBossOffline();
                this.actors.addElement(Bossintro);
                SteepCount = 2;
                Bossintro.moveTo((short) 950, (short) 259);
                steep++;
                mainChar.resetAllSkill();
                mainChar.moveTo((short) 854, (short) 259);
                MainChar.blockkey = true;
                for (int i = 0; i < vecCharintro.size(); i++) {
                    Actor ac = (Actor) vecCharintro.elementAt(i);
                    if (ac != null) {
                        ac.resetAllSkill();
                        ac.moveTo((short) this.xit[i], (short) (this.yit[i] - 20));
                    }
                }
            }
        }
        if (Bossintro != null && Bossintro.getState() == 2 && steep == 2) {
            addChat((Actor) Bossintro, T.hungcanh[0], 20);
            timepopup = 20;
            steep++;
        }
        if (steep == 3) {
            SteepCount = 2;
            if (timepopup <= 0)
                doTalk();
            if (clazz == 5 && timepopup <= 0)
                steep++;
            Bossintro.setState(0);
        }
        if (Bossintro != null && steep == 4 && GameCanvas.gameTick % 25 == 0) {
            mVector ntarget = new mVector();
            for (int i = 0; i < this.actors.size(); i++) {
                Actor ac = (Actor) this.actors.elementAt(i);
                if (ac != null && ac.catagory == 0 && Res.inRangeActor((Actor) Bossintro, ac, 150) && ac.getState() != 3)
                    ntarget.addElement(ac);
            }
            if (ntarget.size() > 0) {
                Bossintro.setmuntiTarget(ntarget);
                Bossintro.startAttack();
                bossfire = (byte) (bossfire + 1);
            }
        }
        if (bossfire >= 20 && mainChar.getState() != 3) {
            StartNewLightning((Actor) mainChar);
            mainChar.actorDie();
        }
        if (mainChar.getState() == 3)
            for (int i = 0; i < this.actors.size(); i++) {
                Actor ac = (Actor) this.actors.elementAt(i);
                if (ac != null && !ac.equals(mainChar) && ac.catagory == 0 && ac.getState() != 3) {
                    StartNewLightning(ac);
                    ac.resetAllSkill();
                    ac.actorDie();
                    vecCharintro.removeElement(ac);
                }
            }
        if (bossfire >= 20 && !startCountdow) {
            addChat((Actor) Bossintro, T.hungcanh[2], 200);
            Bossintro.moveTo(mainChar.x, mainChar.y);
            countdowm = 5000L + mSystem.currentTimeMillis();
            startCountdow = true;
            steep++;
            Bossintro.setState(0);
        }
        if (startCountdow && countdowm - mSystem.currentTimeMillis() <= 0L) {
            CharSelectScr.gI().switchToMe();
            vecCharintro.removeAllElements();
            this.actors.removeAllElements();
            EffectManager.lowEffects.removeAllElements();
            EffectManager.hiEffects.removeAllElements();
            GameData.listbyteData.clear();
            GameData.listImgIcon.clear();
            Bossintro = null;
            isIntro = false;
        }
        if (Bossintro != null)
            this.focusedActor = (Actor) Bossintro;
    }

    public static boolean isInScreen(Actor obj) {
        return obj.getX() >= cmx - obj.getWidth() && obj.getX() <= cmx + GameCanvas.w + obj.getWidth() && obj.getY() >= cmy - obj.getHeight() && obj.getY() <= cmy + GameCanvas.h + obj.getHeight() * 3 / 2;
    }

    public static boolean isInScreen(int x, int y, int w, int h) {
        return x >= cmx - w && x <= cmx + GameCanvas.w && y >= cmy - h && y <= cmy + GameCanvas.h;
    }

    public static void playNew() {
        arrowsUp.removeAllElements();
        GameCanvas.gameScr.actors.removeAllElements();
        vecCharintro.removeAllElements();
        GameCanvas.gameScr.charlogin.removeAllElements();
        EffectManager.lowEffects.removeAllElements();
        EffectManager.hiEffects.removeAllElements();
        String add = ServerListScr.address[ServerListScr.index];
        String port = String.valueOf(ServerListScr.port[ServerListScr.index]);
        GameCanvas.gameScr.onMapOffline(39, 0, 0);
        GameCanvas.gameScr.startIntro();
        String user = "-1";
        String pass = "-1";
        GameCanvas.loginScr.doLogin2(user, pass, add, port);
        CountIntro = 0;
        Next = 0;
        CountMoveFirst = 0;
        finishTalk = false;
        co = 0;
        timepopup = 0;
        SteepCount = 0;
        chatcount = 0;
        clazz = 0;
        canmove = false;
        ismovefirst = false;
        fireIntro = false;
        tam.removeAllElements();
    }

    public void updateParty() {
    }

    public void update() {
        try {
            if ((mainChar.hp <= 0 || mainChar.getState() == 3) && (GameCanvas.isPointerClick[0] || GameCanvas.isKeyPressed(5)) && !GameCanvas.menu2.isShow)
                comHome();
            if (!(Session_ME.gI()).connected && !this.isDis && !isIntro && GameCanvas.currentScreen != null && GameCanvas.currentScreen.equals(GameCanvas.gameScr)) {
                this.isDis = true;
                onDisconnect("gamescr");
            }
            updateClound();
            if (this.tickHP >= 0)
                this.tickHP--;
            if (this.tickMP >= 0)
                this.tickMP--;
            if (this.tickpaintFont >= 0)
                this.tickpaintFont--;
            if (this.timeQouckSlot >= 0)
                this.timeQouckSlot--;
            this.tickicon = (this.tickicon + 1) % 40;
            updateEvent();
            updateTime();
            if (charcountdonw != null) {
                charcountdonw.update();
                if (charcountdonw.WantDestroy)
                    charcountdonw = null;
            }
            if (GameCanvas.isTouch) {
                if (this.cmdchat != null && GameCanvas.currentScreen == this && !isIntro && getCmdPointerLast(this.cmdchat)) {
                    GameCanvas.isPointerJustRelease[0] = false;
                    if (this.cmdchat != null)
                        this.cmdchat.performAction();
                }
                if (this.cmdChangeFocus != null && GameCanvas.currentScreen == this && !isIntro && !mSystem.isPC && getCmdPointerLast(this.cmdChangeFocus)) {
                    GameCanvas.isPointerJustRelease[0] = false;
                    if (this.cmdChangeFocus != null)
                        this.cmdChangeFocus.performAction();
                }
            }
            if (isIntro)
                updateIntro();
            timeRemovePos--;
            if (timeRemovePos < 0)
                timeRemovePos = 0;
            if (this.tg != null)
                this.tg.update();
            updateCamera();
            cmtoX = mainChar.x - gshw + DELTACAMERAX[mainChar.dir];
            cmtoY = mainChar.y - gshh + DELTACAMERAY[mainChar.dir] - 20;
            updateCmMini();
            int i;
            for (i = arrowsUp.size() - 1; i >= 0; i--) {
                IArrow a = (IArrow) arrowsUp.elementAt(i);
                if (a != null) {
                    a.update();
                    if (a.wantDestroy)
                        arrowsUp.removeElementAt(i);
                }
            }
            for (i = this.arrowsDown.size() - 1; i >= 0; i--) {
                IArrow a = (IArrow) this.arrowsDown.elementAt(i);
                if (a != null) {
                    a.update();
                    if (a.wantDestroy)
                        this.arrowsDown.removeElementAt(i);
                }
            }
            PosNPCQuest.removeAllElements();
            mVector removeactor = new mVector();
            int j;
            for (j = this.actors.size() - 1; j >= 0; j--) {
                Actor a = (Actor) this.actors.elementAt(j);
                if (a != null)
                    if (a.wantDestroy) {
                        if (a.equals(this.focusedActor))
                            this.focusedActor = null;
                        removeactor.addElement(a);
                    } else {
                        if (a.isNPC())
                            getAllPosNPCMInimap(a);
                        if (a.catagory == 2 || a.catagory == 10 || a.catagory == 11 || a.catagory == 12) {
                            a.update();
                        } else {
                            a.update();
                            if (a.wantDestroy && a.getTimeLive() == -1) {
                                if (this.focusedActor != null && a != null && this.focusedActor.equals(a))
                                    this.focusedActor = null;
                                if (!isFindChar && !isActorMove && !isFindMonster && a.catagory != 100) {
                                    if (a.catagory == 0)
                                        a.setDie();
                                    removeactor.addElement(a);
                                }
                            }
                        }
                    }
            }
            Util.quickSort(this.actors);
            if (GameCanvas.gameTick % 3 == 0) {
                Actor ac = FindFocusActorAuto();
                if (ac != null && ac.canFocusMonster() && GameCanvas.currentDialog == null && ac.catagory != 0 && !ac.isNPC())
                    this.focusedActor = ac;
            }
            if (this.focusedActor != null && (this.focusedActor.catagory == 100 || this.focusedActor.wantDestroy))
                this.focusedActor = null;
            EffectManager.lowEffects.updateAll();
            EffectManager.hiEffects.updateAll();
            updateFlyText();
            if (this.pingNextIn > 0) {
                this.pingNextIn--;
                if (this.pingNextIn == 0)
                    this.lastTimePing = mSystem.currentTimeMillis();
            }
            if (this.chatMode && GameCanvas.currentScreen == this)
                this.tfChat.update();
            if (this.chatWorld && GameCanvas.currentScreen == this)
                this.tfChatWorld.update();
            if (this.indexBuff != -1 && timeBuff != -1L && mSystem.currentTimeMillis() > timeBuff) {
                this.indexBuff = -1;
                timeBuff = -1L;
            }
            if (xSlot > 0) {
                xSlot -= Res.wKhung / 10;
                if (xSlot < 0)
                    xSlot = 0;
            }
            for (j = 0; j < removeactor.size(); j++) {
                if (removeactor.elementAt(j) != null)
                    this.actors.removeElement(removeactor.elementAt(j));
            }
            updateMsgServer();
            setRemoveIconImg();
            if (this.timerung > 0) {
                this.timerung--;
            } else {
                this.timerung = 0;
            }
            if (Tilemap.isOfflineMap && this.map == 29)
                if (mainChar.x >= this.xBeginFrame - 16 && mainChar.x <= this.xTheendFrame + 40 && mainChar.y >= this.yBeginFrame - 16 && mainChar.y <= this.yTheendFrame + 40) {
                    this.focusedActor = isFocusMyGround((Actor) mainChar);
                } else {
                    int size6 = this.actors.size();
                    Actor obj = null;
                    for (int k = 0; k < size6; k++) {
                        obj = (Actor) this.actors.elementAt(k);
                        if (obj != null && obj.catagory == 10)
                            obj.setIsFocus(false);
                    }
                }
            if (savemsgWorld.size() > 30)
                savemsgWorld.removeElementAt(0);
            if (mainChar.state != 3 && autoFight && GameCanvas.currentScreen instanceof GameScr && GameCanvas.currentDialog != null && !mainChar._isDie && Tilemap.lv != 0 && Tilemap.lv != 201 && Tilemap.lv != 70 && Tilemap.lv != 80)
                GameCanvas.currentDialog = null;
            if (Tilemap.lv == 0 || Tilemap.lv == 70 || Tilemap.lv == 80)
                autoFight = false;
            if (mainChar.posTransRoad != null && this.posCam != null) {
                this.tg.x = (short) this.posCam.x;
                this.tg.y = (short) this.posCam.y;
            }
            if (!mainChar.isDie() && GameCanvas.menuSelectitem.isAutoDanh[0] && this.beginAuto && mainChar.state != 3) {
                AutoDanh();
                AutoBuff();
            }
            if (GameCanvas.menuSelectitem.isAutoDanh[1])
                AutoBomHP(GameCanvas.menuSelectitem.indexPerHp);
            if (GameCanvas.menuSelectitem.isAutoDanh[3])
                AutoBomMP(GameCanvas.menuSelectitem.indexPerMp);
            if (GameCanvas.gameTick % 20 == 0)
                DataSkillEff.checkremove();
            if (numMSG > 0 && GameCanvas.isTouch && mainChar.getState() != 3 && GameCanvas.isPointerClick[0] && GameCanvas.isPointer(GameCanvas.w - 5 - wMiniMap - imgMSG.getWidth(), hMiniMap - imgMSG.getHeight() / 2, 18, 20, 0)) {
                numMSG = 0;
                if (this.eventShow != null) {
                    int t = EventScreen.setIndexEvent(this.eventShow.nameEvent, (byte) this.eventShow.IDCmd);
                    if (t >= 0)
                        GameCanvas.mevent.idSelect = t;
                }
                GameCanvas.mevent.init();
                GameCanvas.mevent.switchToMe(this);
                GameCanvas.isPointerClick[0] = false;
            }
            if (GameCanvas.isKeyPressed(10) && !this.chatMode) {
                this.chatMode = true;
                this.right = this.tfChat.cmdClear;
                this.left = this.cmdCloseChat;
                this.tfChat.clearAllText();
                this.center = this.cmdDochat;
                GameCanvas.clearKeyHold();
            }
            if (GameCanvas.isKeyPressed(10) && !this.chatWorld) {
                this.chatMode = true;
                this.right = this.tfChatWorld.cmdClear;
                this.left = this.cmdCloseChat;
                this.tfChatWorld.clearAllText();
                this.center = this.cmdDochat;
                GameCanvas.clearKeyHold();
            }
            if (Ghost != null) {
                if (this.focusedActor == null)
                    this.focusedActor = Ghost;
                if (this.focusedActor != null && !this.focusedActor.equals(Ghost))
                    this.focusedActor = Ghost;
            }
            if (!this.chatMode && !GameCanvas.menu.showMenu && !GameCanvas.menu2.isShow && !GameCanvas.menuSelectitem.showMenu && GameCanvas.currentDialog == null && !MainMenu.isShow && !isIntro && GameCanvas.currentScreen == this)
                if (GameCanvas.isKeyPressed(13))
                    this.cmdChangeFocus.performAction();
            if (hShowEvent <= 0 && isQuest && GameCanvas.isPointerClick[0] && GameCanvas.isTouch && !isIntro && mainChar.getState() != 3 && GameCanvas.currentScreen == this && (this.focusedActor == null || (this.focusedActor != null && this.focusedActor.isNPC())) && GameCanvas.isPointer(GameCanvas.w - mGraphics.getImageWidth(imgPointQuest) - 5 - wMiniMap, 5, 30, 30, 0)) {
                MainMenu.isBeginQuest = true;
                MainMenu.delay = 5;
                MainMenu.gI().switchToMe(this);
                MainMenu.gI().PutItemSHop(shop);
                this.countQuest = 0;
            }
            super.updateKey();
            if (this.cmdDisconect != null) {
                this.tickConnect++;
                if (this.tickConnect > 10) {
                    this.cmdDisconect.performAction();
                    this.cmdDisconect = null;
                    this.tickConnect = 0;
                }
            }
            updateParty();
            if (effAnimate.size() > 0) {
                AnimateEffect.updateWind();
                AnimateEffect ani = null;
                for (int k = 0; k < effAnimate.size(); k++) {
                    ani = (AnimateEffect) effAnimate.elementAt(k);
                    if (ani != null)
                        ani.update();
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void updatefocus() {
        if (isIntro) {
            this.fFocus = 0;
        } else {
            if (this.isAutoChangeFocus) {
                if (this.focusedActor != null && this.focusedActor.catagory == 0) {
                    this.focusedActor.beFire();
                }

                if (this.focusedActor != null && this.focusedActor.isNPC() && (this.focusedActor.isBangKhu() || this.focusedActor.isTruRong()) && Utils.getDistance(mainChar, this.focusedActor) > 60) {
                    this.focusedActor = null;
                }

                if (this.focusedActor != null && Utils.getDistance(mainChar, this.focusedActor) > 192) {
                    this.focusedActor = null;
                    this.isAutoChangeFocus = false;
                }
            }

            if (this.focusedActor != null) {
                if (this.focusedActor.catagory != 0 && this.focusedActor.catagory != 1) {
                    this.fFocus = 0;
                } else if (this.focusedActor.getMaxHp() > 0) {
                    long objFocushp = (long) this.focusedActor.getHp();
                    long tile = 4L;
                    long maxHP = objFocushp * tile;
                    this.fFocus = (byte) ((int) (maxHP / (long) this.focusedActor.getMaxHp()));
                    if (this.fFocus > 3) {
                        this.fFocus = 3;
                    }

                    this.fFocus = (byte) Math.abs(this.fFocus - 3);
                }
            } else {
                this.fFocus = 0;
            }

            if (Ghost == null) {
                if (GameCanvas.currentDialog != null) {
                    return;
                }

                if ((this.focusedActor == null || this.focusedActor != null && Utils.getDistance(mainChar, this.focusedActor) > 192) && !this.isAutoChangeFocus) {
                    this.isAutoChangeFocus = true;
                }

                if (this.waitFocus >= 0) {
                    --this.waitFocus;
                }

                if (this.isAutoChangeFocus && this.waitFocus <= 0 && !this.beginAuto) {
                    int min = 100000000;
                    int index = -1;
                    this.waitFocus = 30;
                    int i = 0;

                    while (true) {
                        if (i >= this.actors.size()) {
                            if (index == -1) {
                                this.isAutoChangeFocus = false;
                            }

                            if (index != -1) {
                                Actor ac1 = (Actor) this.actors.elementAt(index);
                                if (!ac1.equals(this.focusedActor)) {
                                    if (ac1.catagory == 1 && (ac1.getState() == 1 || ac1.getState() == 5 || ac1.getState() == 8 || ac1.getTypeMove() == 4 || ac1.getTypeMove() == 6)) {
                                        return;
                                    }

                                    if (ac1.isNPC() && Utils.getDistance(ac1, mainChar) > 60) {
                                        return;
                                    }

                                    if (ac1.canFocus()) {
                                        this.focusedActor = ac1;
                                    }
                                }
                            }
                            break;
                        }

                        Actor ac = (Actor) this.actors.elementAt(i);
                        if (ac != null && !ac.equals(mainChar) && (ac.catagory != 0 || ac.isNPC() || ac.beFire()) && (ac.catagory != 1 || ac.getState() != 1 && ac.getState() != 5 && ac.getState() != 8 && ac.getTypeMove() != 4 && ac.getTypeMove() != 6)) {
                            int dist = Utils.getDistance(mainChar, ac);
                            if (dist < min) {
                                min = dist;
                                index = i;
                            }
                        }

                        ++i;
                    }
                }
            }

        }
    }

    public void updateTime() {
        for (int i = 0; i < VecTime.size(); ++i) {
            TimecountDown t = (TimecountDown) VecTime.elementAt(i);
            if (t != null) {
                t.update();
                if (t.wantdestroy) {
                    VecTime.removeElement(t);
                }
            }
        }

    }

    public void paintCharParty(mGraphics g) {
        int size = this.vecCharParty.size();
        if (size > 0)
            for (int i = 0; i < size; i++) {
                Actor ac = (Actor) this.vecCharParty.elementAt(i);
                if (ac != null) {
                    mFont.tahoma_7_black.drawString(g, ac.getName(), 3, 50 + i * 20 + 1 - 3, 0, false);
                    mFont.tahoma_7_white.drawString(g, ac.getName(), 2, 50 + i * 20 - 3, 0, false);
                    int w = 30;
                    g.setColor(0);
                    g.fillRect(2, 62 + i * 20 - 3, w, 3, false);
                    int wpPaint = 0;
                    long tile = w;
                    long objFocushp = ac.getHp();
                    long maxHp = tile * objFocushp;
                    wpPaint = (int) (maxHp / ac.getMaxHp());
                    if (wpPaint <= 3) {
                        wpPaint = 3;
                    } else if (wpPaint > w) {
                        wpPaint = w;
                    }
                    g.setColor(-8780515);
                    g.fillRect(3, 62 + i * 20 + 1 - 3, w - 2, 1, false);
                    g.setColor(-645304);
                    g.fillRect(3, 62 + i * 20 + 1 - 3, wpPaint - 2, 1, false);
                }
            }
    }

    public void paintTime(mGraphics g) {
        for (int i = 0; i < VecTime.size(); ++i) {
            TimecountDown t = (TimecountDown) VecTime.elementAt(i);
            if (t != null) {
                t.paint(g, GameCanvas.w, hMiniMap + i * 18 + imgButton[5].getHeight() / 2);
            }
        }

    }

    public boolean isPointerPressInside(int x, int y, int w, int h, int type) {
        if (type == 0) {
            this.isBigFocus = false;
        } else if (type == 1) {
            this.isTouchChat = false;
        }

        if (GameCanvas.isPointerHoldIn(x, y, w, h, 0)) {
            if (GameCanvas.isPointerDown[0]) {
                if (type == 0) {
                    this.isBigFocus = true;
                } else if (type == 1) {
                    this.isTouchChat = true;
                }
            }

            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                return true;
            }
        }

        return false;
    }

    public Actor isFocusMyGround(Actor ac) {
        Actor result = null;
        if (Tilemap.isOfflineMap) {
            for (int i = 0; i < this.actors.size(); ++i) {
                Actor obj = (Actor) this.actors.elementAt(i);
                if (obj.catagory == 10) {
                    if (ac.x >= obj.x + 2 && ac.x <= obj.x + 30 && ac.y >= obj.y && ac.y + 2 <= obj.y + 30) {
                        obj.setIsFocus(true);
                        result = obj;
                    } else {
                        obj.setIsFocus(false);
                    }
                }
            }
        }

        return result;
    }

    private void updateMsgServer() {
        if (this.listMSGServer != null && this.listMSGServer.size() > 0) {
            this.xMsgServer -= 2;
            String str = (String) this.listMSGServer.elementAt(0);
            if (this.xMsgServer + FontTeam.arialFontW.getWidth(str) < 0) {
                this.listMSGServer.removeElementAt(0);
                this.xMsgServer = GameCanvas.w - 20;
            }
        }

    }

    private void setRemoveIconImg() {
        Enumeration k = GameData.listImgIcon.keys();

        while (k.hasMoreElements()) {
            String key = (String) k.nextElement();
            ImageIcon img = (ImageIcon) GameData.listImgIcon.get(key);
            if (!img.isLoad && mSystem.currentTimeMillis() - img.timeRemove >= 0L) {
                img.reset();
                GameData.listImgIcon.remove(key);
            }
        }

    }

    public static int getIdImgQuest(int idnpc) {
        String str = (String) hashQuestFinish.get(String.valueOf(idnpc));
        String str2 = (String) hashQuestWorking.get(String.valueOf(idnpc));
        String str3 = (String) hashQuestCanReceive.get(String.valueOf(idnpc));
        boolean var4;
        int type;
        if (str != null) {
            var4 = true;

            try {
                type = Integer.parseInt(str);
            } catch (Exception var8) {
                type = -1;
            }

            if (type == 1) {
                return 2;
            }
        }

        if (str2 != null) {
            var4 = true;

            try {
                type = Integer.parseInt(str2);
            } catch (Exception var7) {
                type = -1;
            }

            if (type != 1) {
                return 1;
            }
        }

        if (str3 != null) {
            var4 = true;

            try {
                type = Integer.parseInt(str3);
            } catch (Exception var6) {
                type = -1;
            }

            if (type != 1) {
                return 0;
            }
        }

        return -1;
    }

    private void updateCmMini() {
        if (Tilemap.w >= wMiniMap || Tilemap.h >= hMiniMap) {
            if (cmyMini != cmtoYmini) {
                cmvyMini = cmtoYmini - cmyMini << 2;
                cmdyMini += cmvyMini;
                cmyMini += cmdyMini >> 4;
                cmdyMini &= 15;
            }

            if (cmxMini != cmtoXMini) {
                cmvxMini = cmtoXMini - cmxMini << 2;
                cmdxMini += cmvxMini;
                cmxMini += cmdxMini >> 4;
                cmdxMini &= 15;
            }
        }

    }

    public boolean isAutoFight() {
        return !mainChar.isDie() && GameCanvas.menuSelectitem.isAutoDanh[0] && !Tilemap.ismapLang && this.beginAuto;
    }

    public Actor FindFocusActorAuto() {
        int left = mainChar.x + C[mainChar.dir][0];
        int right = mainChar.x + C[mainChar.dir][1];
        int up = mainChar.y + C[mainChar.dir][2];
        int down = mainChar.y + C[mainChar.dir][3];
        int shorted = 10000;
        int shortedIndex = -1;
        this.nearActors.removeAllElements();
        int size0 = this.actors.size();
        Actor a = null;

        for (int i = 0; i < size0; ++i) {
            a = (Actor) this.actors.elementAt(i);
            if (a.x > left && a.x < right && a.y > up && a.y < down && (a.catagory != 0 || a.ID != mainChar.ID) && (!a.isMonster() || a.getState() != 1 && a.getState() != 5 && a.getState() != 8) && a.canFocus() && (!a.isBangKhu() && !a.isTruRong() || Utils.getDistance(a, mainChar) >= 32) && (!this.isAutoFight() || a.getState() != 3 && (a.catagory != 3 || !mainChar.isFullInven())) && (mainChar.isDie() || !GameCanvas.menuSelectitem.isAutoDanh[0] || Tilemap.ismapLang || !this.beginAuto || a.catagory <= 2 || !mainChar.isFullInven())) {
                if (typeOptionFocus == 1) {
                    if (a.catagory == 0 && (!a.isKiller() || a.isNPC()) || a.catagory != 1 && a.catagory == 0 && !a.isKiller()) {
                        continue;
                    }
                } else if (typeOptionFocus == 2) {
                    if (!a.isNPC()) {
                        continue;
                    }
                } else if (typeOptionFocus == 3) {
                    if (a.catagory == 0 && a.getIdClan() == mainChar.idClan || a.isNPC()) {
                        continue;
                    }
                } else if (typeOptionFocus == 4 && (a.catagory == 0 && a.getNation() == mainChar.nationID || a.isNPC())) {
                    continue;
                }

                this.nearActors.addElement(a);
                int distance = Util.abs(mainChar.x - a.x) + Util.abs(mainChar.y - a.y);
                if (a.catagory == 3 || a.catagory == 4) {
                    distance <<= 1;
                }

                if (distance < shorted) {
                    shorted = distance;
                    shortedIndex = i;
                }
            }
        }

        if (shortedIndex == -1) {
            return null;
        } else if (this.focusedActor == null && shortedIndex < this.actors.size()) {
            return (Actor) this.actors.elementAt(shortedIndex);
        } else if (!GameCanvas.instance.hasPointerEvents() && shortedIndex < this.actors.size()) {
            if (this.nearActors.contains(this.focusedActor)) {
                return this.focusedActor;
            } else {
                return (Actor) this.actors.elementAt(shortedIndex);
            }
        } else {
            if (Util.distance(mainChar.x, mainChar.y, this.focusedActor.x, this.focusedActor.y) > GameCanvas.w / 2) {
                this.focusedActor = null;
            }

            return this.focusedActor;
        }
    }

    public Actor findFocusActor() {
        int left = mainChar.x + C[mainChar.dir][0];
        int right = mainChar.x + C[mainChar.dir][1];
        int up = mainChar.y + C[mainChar.dir][2];
        int down = mainChar.y + C[mainChar.dir][3];
        int shorted = 10000;
        int shortedIndex = -1;
        this.nearActors.removeAllElements();
        int size0 = this.actors.size();
        Actor a = null;

        for (int i = 0; i < size0; ++i) {
            a = (Actor) this.actors.elementAt(i);
            if (a.x > left && a.x < right && a.y > up && a.y < down && (a.catagory != 0 || a.ID != mainChar.ID) && (!a.isMonster() || a.getState() != 1 && a.getState() != 5 && a.getState() != 8) && a.canFocus() && (!a.isBangKhu() && !a.isTruRong() || Utils.getDistance(a, mainChar) >= 32) && a.getState() != 8 && (!this.isAutoFight() || a.getState() != 3 && (a.catagory != 3 || !mainChar.isFullInven())) && (mainChar.isDie() || !GameCanvas.menuSelectitem.isAutoDanh[0] || Tilemap.ismapLang || !this.beginAuto || a.catagory <= 2 || !mainChar.isFullInven())) {
                if (typeOptionFocus == 1) {
                    if (a.catagory == 0 && (!a.isKiller() || a.isNPC()) || a.catagory != 1 && a.catagory == 0 && !a.isKiller()) {
                        continue;
                    }
                } else if (typeOptionFocus == 2) {
                    if (!a.isNPC()) {
                        continue;
                    }
                } else if (typeOptionFocus == 3) {
                    if (a.catagory == 0 && a.getIdClan() == mainChar.idClan || a.isNPC()) {
                        continue;
                    }
                } else if (typeOptionFocus == 4 && (a.catagory == 0 && a.getNation() == mainChar.nationID || a.isNPC())) {
                    continue;
                }

                this.nearActors.addElement(a);
                int distance = Util.abs(mainChar.x - a.x) + Util.abs(mainChar.y - a.y);
                if (a.catagory == 3 || a.catagory == 4) {
                    distance <<= 1;
                }

                if (distance < shorted) {
                    shorted = distance;
                    shortedIndex = i;
                }
            }
        }

        if (shortedIndex == -1) {
            return null;
        } else if (this.focusedActor == null && shortedIndex < this.actors.size()) {
            return (Actor) this.actors.elementAt(shortedIndex);
        } else if (!GameCanvas.instance.hasPointerEvents() && shortedIndex < this.actors.size()) {
            if (this.nearActors.contains(this.focusedActor)) {
                return this.focusedActor;
            } else {
                return (Actor) this.actors.elementAt(shortedIndex);
            }
        } else {
            if (Util.distance(mainChar.x, mainChar.y, this.focusedActor.x, this.focusedActor.y) > GameCanvas.w / 2) {
                this.focusedActor = null;
            }

            return this.focusedActor;
        }
    }

    public Actor findFocusActorTouch() {
        int left = mainChar.x + C[mainChar.dir][0];
        int right = mainChar.x + C[mainChar.dir][1];
        int up = mainChar.y + C[mainChar.dir][2];
        int down = mainChar.y + C[mainChar.dir][3];
        int shorted = 10000;
        int shortedIndex = -1;
        this.nearActors.removeAllElements();
        int size0 = this.actors.size();
        Actor a = null;

        for (int i = 0; i < size0; ++i) {
            a = (Actor) this.actors.elementAt(i);
            if (a.x > left && a.x < right && a.y > up && a.y < down && (a.catagory != 0 || a.ID != mainChar.ID) && (!a.isMonster() || a.getState() != 1 && a.getState() != 5 && a.getState() != 8) && a.canFocus() && a.getState() != 8 && (!this.isAutoFight() || a.getState() != 3 && (a.catagory != 3 || !mainChar.isFullInven())) && (mainChar.isDie() || !GameCanvas.menuSelectitem.isAutoDanh[0] || Tilemap.ismapLang || !this.beginAuto || a.catagory <= 2 || !mainChar.isFullInven())) {
                if (typeOptionFocus == 1) {
                    if (a.catagory == 0 && (!a.isKiller() || a.isNPC()) || a.catagory != 1 && a.catagory == 0 && !a.isKiller()) {
                        continue;
                    }
                } else if (typeOptionFocus == 2) {
                    if (!a.isNPC()) {
                        continue;
                    }
                } else if (typeOptionFocus == 3) {
                    if (a.catagory == 0 && a.getIdClan() == mainChar.idClan || a.isNPC()) {
                        continue;
                    }
                } else if (typeOptionFocus == 4 && (a.catagory == 0 && a.getNation() == mainChar.nationID || a.isNPC())) {
                    continue;
                }

                this.nearActors.addElement(a);
                int distance = Util.abs(mainChar.x - a.x) + Util.abs(mainChar.y - a.y);
                if (a.catagory == 3 || a.catagory == 4) {
                    distance <<= 1;
                }

                if (distance < shorted) {
                    shorted = distance;
                    shortedIndex = i;
                }
            }
        }

        if (shortedIndex == -1) {
            return null;
        } else if (this.focusedActor == null && shortedIndex < this.actors.size()) {
            return (Actor) this.actors.elementAt(shortedIndex);
        } else if (!GameCanvas.instance.hasPointerEvents() && shortedIndex < this.actors.size()) {
            if (this.nearActors.contains(this.focusedActor)) {
                return this.focusedActor;
            } else {
                return (Actor) this.actors.elementAt(shortedIndex);
            }
        } else {
            if (Util.distance(mainChar.x, mainChar.y, this.focusedActor.x, this.focusedActor.y) > GameCanvas.w / 2) {
                this.focusedActor = null;
            }

            return this.focusedActor;
        }
    }

    public void changeToNextFocusActor(boolean isBack) {
        if (this.focusedActor == null) {
            this.focusedActor = this.findFocusActor();
            if (this.focusedActor != null && this.focusedActor.catagory == 100) {
                this.focusedActor = null;
            }

        } else {
            if (isBack && !this.focusedActor.isKiller() && this.isAutoFight() && typeOptionFocus == 1) {
                this.focusedActor = this.findFocusActor();
                if (this.focusedActor != null && this.focusedActor.isKiller()) {
                    return;
                }
            }

            int i = this.nearActors.indexOf(this.focusedActor);
            int nextIndex = i + 1;
            if (nextIndex >= this.nearActors.size() || nextIndex < 0) {
                nextIndex = 0;
            }

            if (this.nearActors.size() > 0) {
                this.focusedActor = (Actor) this.nearActors.elementAt(nextIndex);
            }

            if (this.focusedActor != null && this.focusedActor.catagory == 100) {
                this.focusedActor = null;
            }

        }
    }

    public static void addChat(Actor ac, String str, int time) {
        if (!str.equals("")) {
            ac.chat = new ChatPopup(time, str, 1);
            ac.chat.setPos(ac.x, ac.y - ac.getHeight());
        }
    }

    public void set_npc_request(short type) {
        if (this.idNpcReceive != -1) {
            type = this.idNpcReceive;
        } else {
            this.current_npc_talk = type;
        }

        int size0 = this.actors.size();
        Actor ac = null;

        for (int i = 0; i < size0; ++i) {
            ac = (Actor) this.actors.elementAt(i);
            if (ac instanceof NPC && ((NPC) ac).type == type) {
                this.posNpcRequest = new Position(ac.x, ac.y);
                return;
            }
        }

    }

    public void paintPosNPCQuest(mGraphics g) {
        for (int i = 0; i < PosNPCQuest.size(); ++i) {
            Position p = (Position) PosNPCQuest.elementAt(i);
            this.paintPoint(g, p, p.indexColor);
        }

    }

    public void removeFocusWhenPutKey() {
        if (this.map == 17 && mainChar.isDoing) {
            this.focusedActor = null;
            this.coutChangeFocusWhenDoing = 0;
        }

    }

    public mVector getTargetFirst() {
        mVector target = new mVector();

        for (int i = 0; i < this.actors.size(); ++i) {
            Actor act = (Actor) this.actors.elementAt(i);
            if (act.catagory == 1) {
                target.addElement(act);
                return target;
            }
        }

        return target;
    }

    public void updateKey() {
        this.updatefocus();
        this.isTouchMenu = false;
        if (GameCanvas.currentDialog == null && GameCanvas.isPointer(xMenu, yMenu, mGraphics.getImageWidth(imgMenu[0]), mGraphics.getImageHeight(imgMenu[0]), 0) && GameCanvas.canTouch() && !Tilemap.isMapIntro()) {
            this.isTouchMenu = true;
            if (!GameCanvas.menu.showMenu && GameCanvas.currentDialog == null && !GameCanvas.menu2.isShow && GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && mainChar.getState() != 3) {
                GameCanvas.menuSelectitem.startAt();
            }
        }

        if (GameCanvas.isPointer(0, 0, 35, 35, 0) && !Tilemap.isMapIntro() && GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
            this.cmdmenu.performAction();
        }

        String text;
        if (this.chatMode && GameCanvas.isTouch && GameCanvas.isPointerClick[0]) {
            switch (GameCanvas.collisionCmdBar()) {
                case 0:
                    GameCanvas.clearKeyHold();
                    GameCanvas.clearKeyPressed();
                    this.chatMode = false;
                    GameCanvas.isPointerClick[0] = false;
                    return;
                case 1:
                    text = this.tfChat.getText();
                    this.tfChat.setText("");
                    this.doChatFromMe(text);
                    GameCanvas.isPointerClick[0] = false;
                    return;
                case 2:
                    this.tfChat.clear();
                    GameCanvas.isPointerClick[0] = false;
                    return;
            }
        }

        if (this.chatWorld && GameCanvas.isTouch && GameCanvas.isPointerClick[0]) {
            switch (GameCanvas.collisionCmdBar()) {
                case 0:
                    GameCanvas.clearKeyHold();
                    GameCanvas.clearKeyPressed();
                    this.chatWorld = false;
                    GameCanvas.isPointerClick[0] = false;
                    return;
                case 1:
                    text = this.tfChatWorld.getText();
                    this.tfChatWorld.setText("");
                    this.doChatFromMe(text);
                    GameCanvas.isPointerClick[0] = false;
                    return;
                case 2:
                    this.tfChatWorld.clear();
                    GameCanvas.isPointerClick[0] = false;
                    return;
            }
        }

        if (this.chatMode && GameCanvas.isTouch) {
            if (GameCanvas.isPointerClick[0] && GameCanvas.py[0] < this.tfChat.y) {
                this.chatMode = false;
                this.tfChat.setText("");
                GameCanvas.isPointerClick[0] = false;
            }

        } else if (this.chatWorld && GameCanvas.isTouch) {
            if (GameCanvas.isPointerClick[0] && GameCanvas.py[0] < this.tfChatWorld.y) {
                this.chatWorld = false;
                this.tfChatWorld.setText("");
                GameCanvas.isPointerClick[0] = false;
            }

        } else {
            if (!this.isTouchMenu) {
                this.updateTouchMove();
                if (GameCanvas.instance.hasPointerEvents() && !this.isPress) {
                    this.updatePoint();
                }
            }

            int i;
            if (!mSystem.isPC && !GameCanvas.isTouch) {
                for (i = 0; i < this.QUICKSLOT_KEY.length; ++i) {
                    if (GameCanvas.isKeyPressed(this.QUICKSLOT_KEY[i])) {
                        if (mainChar.state != 3 && (this.focusedActor == null || !this.focusedActor.isNPC() || i != 2)) {
                            this.doTouchQuickSlot(i);
                        } else {
                            this.doFire();
                        }
                        break;
                    }
                }
            } else if (mSystem.isPC) {
                for (i = 0; i < this.QUICKSLOT_KEY_PC.length; ++i) {
                    if (GameCanvas.isKeyPressed(this.QUICKSLOT_KEY_PC[i])) {
                        if (mainChar.state != 3 && (this.focusedActor == null || !this.focusedActor.isNPC() || i != 0)) {
                            this.doTouchQuickSlot(i);
                        } else {
                            this.doFire();
                        }
                        break;
                    }
                }
            }

            if (GameCanvas.isKeyPressed(11)) {
                GameCanvas.menuSelectitem.startAt();
            } else {
                if (autoFight) {
                    if (this.indexBuff != -1 && timeBuff - mSystem.currentTimeMillis() / 1000L > 0L) {
                        isBeginAutoBuff = true;
                    } else {
                        isBeginAutoBuff = false;
                    }
                }

                if (mainChar.state != 3 && (autoFight || autoBomHMP) && !mainChar._isDie && Tilemap.lv != 0 && Tilemap.lv != 201 && Tilemap.lv != 70 && Tilemap.lv != 80 && !Tilemap.isOfflineMap) {
                    if (GameCanvas.keyPressedz[7]) {
                        GameCanvas.keyPressedz[7] = false;
                        this.doFire();
                    }

                    if (GameCanvas.keyPressedz[9]) {
                        GameCanvas.keyPressedz[9] = false;
                        this.doFire();
                    }
                }

                if (GameCanvas.menu.showMenu) {
                    GameCanvas.keyPressedz[5] = false;
                } else if (!GameCanvas.isKeyPressed(2) && !GameCanvas.isKeyPressed(4) && !GameCanvas.isKeyPressed(6) && !GameCanvas.isKeyPressed(8)) {
                    if (mainChar.state == 1 || mainChar.state == 0 || mainChar.state == 4) {
                        this.doActorMove();
                    }

                }
            }
        }
    }

    public boolean checkCanChangeMap(int x, int y, int dir) {
        return false;
    }

    public void comHome() {
        GameCanvas.endDlg();
        this.indexBuff = -1;
        timeBuff = 0L;
        GameService.gI().comeHomeWhenDie();
    }

    public void doFire() {
        if (mainChar.state != 3 || isIntro) {
            mainChar.posTransRoad = null;
            if (this.focusedActor != null && this.focusedActor.catagory > 2) {
                if (!mainChar.isFullInven()) {
                    GameService.gI().getDropableFromGround(this.focusedActor.ID);
                }

            } else {
                SetInfoData s5;
                if (this.focusedActor != null && this.focusedActor.catagory == 1) {
                    this.vecSkill.removeAllElements();
                    if (GameCanvas.isTouch) {
                        for (int i = 0; i < MainChar.mQuickslot.length; ++i) {
                            if (MainChar.mQuickslot[i] != null && MainChar.mQuickslot[i].quickslotType == 1) {
                                s5 = new SetInfoData();
                                s5.index = i + 4;
                                this.vecSkill.addElement(s5);
                            }
                        }

                        if (this.vecSkill.size() == 0) {
                            return;
                        }

                        SetInfoData sd;
                        if (this.vecSkill.size() == 1) {
                            sd = (SetInfoData) this.vecSkill.elementAt(0);
                            if (sd != null) {
                                if (mSystem.isPC) {
                                    sd.index -= 4;
                                }

                                this.doTouchQuickSlot(sd.index);
                                return;
                            }
                        } else {
                            sd = (SetInfoData) this.vecSkill.elementAt(this.indexSkill);
                            if (sd != null) {
                                if (mSystem.isPC) {
                                    sd.index -= 4;
                                }

                                this.doTouchQuickSlot(sd.index);
                                ++this.indexSkill;
                                if (this.indexSkill > this.vecSkill.size() - 1) {
                                    this.indexSkill = 0;
                                }

                                return;
                            }
                        }
                    }
                }

                if (this.focusedActor != null && this.focusedActor.isNpcChar()) {
                    if (!this.focusedActor.getStrTalk().equals("") && !this.focusedActor.getCmdName().equals("")) {
                        if (this.focusedActor != null && !this.focusedActor.getStrTalk().equals("")) {
                            mainChar.posTransRoad = null;
                            if (this.focusedActor.getStateQuest() != 1 && this.focusedActor.getStateQuest() != 2 && this.focusedActor.getStateQuest() != 0) {
                                GameService.gI().requestNPCInfo(this.focusedActor.getIDNpc());
                            } else {
                                mVector menu = new mVector();
                                s5 = new SetInfoData();
                                s5.ID = this.focusedActor.getIDNpc();
                                s5.idIcon = this.focusedActor.getIDicon();
                                s5.stateQuest = this.focusedActor.getStateQuest();
                                s5.str = this.focusedActor.getStrTalk();
                                mCommand cmd2 = new mCommand(T.nhiemvu, this, 11, s5);
                                if (this.isNPCshop((short) this.focusedActor.getIDNpc())) {
                                    mSound.playSound(28, mSound.volumeSound);
                                    mCommand cmd1 = new mCommand(T.trochuyen, this, 10);
                                    menu.addElement(cmd1);
                                    menu.addElement(cmd2);
                                    GameCanvas.menu.startAt_MenuOption(menu, -1, -1, this.focusedActor.getStrTalk(), this.focusedActor.getIDicon());
                                    GameCanvas.endDlg();
                                } else {
                                    if (this.focusedActor.getStateQuest() == 0 || this.focusedActor.getStateQuest() == 1) {
                                        mSound.playSound(28, mSound.volumeSound);
                                    }

                                    cmd2.performAction();
                                }
                            }
                        }

                    } else {
                        GameService.gI().requestNPCInfo(this.focusedActor.getIDNpc());
                    }
                }
            }
        }
    }

    public int getMpHpPutKey(int type, int idPotion) {
        for (int i = 0; i < TYPE_MP_HP[type].length; ++i) {
            if (TYPE_MP_HP[type][i] == idPotion) {
                return VALUE_MP_HP[type][i];
            }
        }

        return 0;
    }

    public void doParty() {
        if (this.focusedActor != null) {
            if (this.focusedActor.catagory == 0) {
                mVector menuItems = new mVector();
                menuItems.addElement(new mCommand(T.tfocus[0], 19));
                menuItems.addElement(new mCommand(T.tfocus[1], 16));
                menuItems.addElement(new mCommand(T.tfocus[2], 15));
                menuItems.addElement(new mCommand(T.tfocus[3], 22));
                menuItems.addElement(new mCommand(T.tfocus[4], 23));
                if (mainChar.canInvite() && this.focusedActor.getidClan() == -1) {
                    menuItems.addElement(new mCommand(T.moivaobang, 24));
                }

                GameCanvas.menu.startAt(menuItems, 2);
                return;
            }

            GameCanvas.keyPressedz[5] = false;
        } else {
            this.unRideHorse();
        }

    }

    public void unRideHorse() {
        if (mainChar.useHorse != -1) {
            mCommand cmd = new mCommand("Có", 21);
            GameCanvas.startYesNoDlg("Ngựa sẽ bị mất nếu rương đồ không còn chỗ. Bạn có muốn xuống ngựa không ?", cmd);
        }

    }

    public void doHuyBanHang() {
        GameCanvas.endDlg();
        mCommand cmd = new mCommand("Có", 108);
        GameCanvas.startYesNoDlg("Bạn có muốn hủy gian hàng không?", cmd);
    }

    public void doNapXu() {
        if (this.decriptNap.size() == 0) {
            GameCanvas.startOKDlg("Chức năng này hiện đang tạm khóa");
        }
    }

    public void doNapMoney(String decription) {
        mVector menuItem = new mVector();
        menuItem.addElement(new mCommand("Nạp lượng", 70, decription));
        menuItem.addElement(new mCommand("Nạp xu", 71, decription));
        GameCanvas.menu.startAt(menuItem, 3);
    }

    private void paintPoint(mGraphics g, Position pos, int index) {
        if (pos != null) {
            int x1 = pos.x / 16;
            int y1 = pos.y / 16;
            if (x1 - 4 <= cmxMini) {
                x1 = cmxMini + 4;
            } else if (x1 + 6 > cmxMini + wMiniMap) {
                x1 = cmxMini + wMiniMap - 6;
            }

            if (y1 - 4 <= cmyMini) {
                y1 = cmyMini + 4;
            } else if (y1 + 6 > cmyMini + hMiniMap) {
                y1 = cmyMini + hMiniMap - 6;
            }

            g.setColor(this.colorPaint[index]);
            g.fillRect(x1, y1, 3, 3, false);
            g.setColor(-1);
            g.drawRect(x1 - 1, y1 - 1, 4, 4, false);
        }

    }

    public void paintMiniMap(mGraphics g, mVector npc) {
        try {
            if (allLocation == null) {
                return;
            }
            if (Tilemap.w < GameCanvas.w / 16 && Tilemap.h < GameCanvas.h / 16) {
                return;
            }
            GameCanvas.resetTrans((mGraphics) g);
            if (this.indexBuff != -1 && timeBuff - System.currentTimeMillis() / 1000L > 0L) {
                GameData.imgSkillIcon.drawFrame(this.indexBuff, GameCanvas.w, GameCanvas.h - hMiniMap - deltaY, 0, mGraphics.BOTTOM | mGraphics.RIGHT, g);
                FontTeam.smallFontColor[0].drawString(g, String.valueOf((timeBuff - System.currentTimeMillis()) / 1000L), GameCanvas.w - 10, GameCanvas.h - hMiniMap - deltaY - 28, 2, false);
            }
            g.translate(this.posMiniMap.x, this.posMiniMap.y);
            int x0 = GameScr.mainChar.x / 16;
            int y0 = GameScr.mainChar.y / 16;
            int i = 0;
            while (i < 3) {
                g.setColor(colorMini[i]);
                g.drawRect(i, i, wMiniMap - i * 2, hMiniMap - i * 2, true);
                ++i;
            }
            int xts = g.getTranslateX();
            int yts = g.getTranslateY();
            mGraphics.resetTransAndroid((mGraphics) g);
            g.setClip(3, 3, wMiniMap - 5, hMiniMap - 5);
            g.saveCanvas();
            g.translateAndroid(xts, yts);
            g.ClipRec2(3, 3, wMiniMap - 5, hMiniMap - 5);
            g.translate(-cmxMini, -cmyMini);
            if (Tilemap.imgMap != null) {
                g.drawImage(Tilemap.imgMap, 0, 0, 0, true);
                g.setColor(4004892);
                int i2 = 0;
                while (i2 < allLocation.length) {
                    g.fillRect(GameScr.allLocation[i2].x / 16 - GameScr.allLocation[i2].vx * 2, GameScr.allLocation[i2].y / 16 - GameScr.allLocation[i2].vy * 2, 3, 3, false);
                    ++i2;
                }
                i2 = 0;
                while (i2 < 3) {
                    g.setColor(colorMini[i2]);
                    g.drawRect(i2, i2, wMiniMap - i2 * 2, hMiniMap - i2 * 2, true);
                    ++i2;
                }
                i2 = 0;
                while (i2 < npc.size()) {
                    Actor ac = (Actor) npc.elementAt(i2);
                    g.setColor(ac.getColorMiniMap());
                    g.fillRect(ac.x / 16 - 1, ac.y / 16 - 1, 3, 3, true);
                    ++i2;
                }
            }
            g.setColor(0xFFFFFF);
            g.fillRect(x0, y0 - 2, 5, 5, true);
            g.setColor(255);
            g.fillRect(x0 + 1, y0 - 1, 3, 3, true);
            this.paintPosNPCQuest(g);
            this.paintPoint(g, posNpcReceiveClan, 1);
            g.setColor(16516117);
            if (GameScr.mainChar.posTransRoad != null) {
                if (this.posCam != null) {
                    g.setColor(15198737);
                    g.fillRect(this.posCam.x, this.posCam.y, 3, 3, false);
                    this.tg.x = (short) this.posCam.x;
                    this.tg.y = (short) this.posCam.y;
                }
                int size1 = GameScr.mainChar.posTransRoad.length;
                int i3 = 0;
                while (i3 < size1) {
                    g.setColor(15198737);
                    byte xx = (byte) (xStart + (GameScr.mainChar.posTransRoad[i3] >> 8));
                    byte yy = (byte) (yStart + (GameScr.mainChar.posTransRoad[i3] & 0xFF));
                    if (xx != -1) {
                        g.fillRect(xx + 1, yy + 1, 1, 1, false);
                    }
                    ++i3;
                }
            }
            g.setColor(16317005);
            int size2 = Tilemap.pointPop.size();
            mGraphics.resetTransAndroid((mGraphics) g);
            g.restoreCanvas();
            GameCanvas.resetTrans((mGraphics) g);
            FontTeam.normalFontColor.drawString(g, nameMap, 1, GameCanvas.h - mFont.tahoma_7b_black.getHeight() * 2, 0, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void VibrateScreen(mGraphics g) {
        dx = 0;
        dy = 0;
        if (timeVibrateScreen > 0) {
            if (timeVibrateScreen > 100) {
                dy = Res.random_Am_0(3);
                if (timeVibrateScreen == 101) {
                    timeVibrateScreen = 0;
                }
            } else {
                dy = Res.random_Am_0(3);
                dx = Res.random_Am(0, 2);
            }

            --timeVibrateScreen;
        }

        g.translate(dx, dy);
    }

    public static void Font3d(mGraphics g, String str, int x, int y, int ar, mFont font) {
        mFont.tahoma_7b_black.drawString(g, str, x + 1, y + 1, ar, true);
        font.drawString(g, str, x, y, ar, true);
    }

    public static void Font3dNormal(mGraphics g, String str, int x, int y, int ar, mFont font, boolean isClip) {
        mFont.tahoma_7_black.drawString(g, str, x + 1, y + 1, ar, isClip);
        font.drawString(g, str, x, y, ar, isClip);
    }

    public static void Font3d(mGraphics g, String str, int x, int y, int ar, mFont font, boolean isClip) {
        mFont.tahoma_7b_black.drawString(g, str, x + 1, y + 1, ar, isClip);
        font.drawString(g, str, x, y, ar, isClip);
    }

    public void paint(mGraphics g) {
        try {
            if (!this.readyGetGameLogic) {
                return;
            }

            GameCanvas.resetTrans(g);
            this.VibrateScreen(g);
            if (this.isFillScr) {
                return;
            }

            if (this.idMapColor != -1) {
                g.setColor(-16333831);
                g.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
            }

            if (!lockcmx) {
            }

            int rung = 0;
            if (this.timerung > 0) {
                rung = r.nextInt(2);
            }

            g.translate(-cmx + rung, -cmy - rung);

            try {
                this.paintClound(g, 0);
                Tilemap.paintTile(g);
            } catch (Exception var15) {
            }

            if (mainChar.posTransRoad != null && this.posCam != null) {
                this.tg.paint(g);
            }

            EffectManager.lowEffects.paintAll(g);

            IArrow arrow;
            int treeIndex1;
            for (treeIndex1 = 0; treeIndex1 < this.arrowsDown.size(); ++treeIndex1) {
                arrow = (IArrow) this.arrowsDown.elementAt(treeIndex1);
                if (arrow != null) {
                    arrow.paint(g);
                }
            }

            treeIndex1 = 0;
            int actorIndex = 0;

            int b;
            TreeTopBottom tre;
            try {
                if (Tilemap.trees != null) {
                    b = this.gssy - (Res.maxHTree / (Tilemap.size * mGraphics.zoomLevel) + 1);

                    while (true) {
                        if (b >= this.gssye + Res.maxHTree / (Tilemap.size * mGraphics.zoomLevel) + 1) {
                            Tilemap.paintTileTop(g);
                            break;
                        }

                        int size3;
                        if (paintCay == 1) {
                            for (size3 = Tilemap.trees.size(); treeIndex1 < size3; ++treeIndex1) {
                                Tree t = (Tree) Tilemap.trees.elementAt(treeIndex1);
                                if (t == null) {
                                    break;
                                }

                                if (t.y == b) {
                                    if (t.checkInCmx(cmx) && !t.isLow(1)) {
                                        tre = null;
                                        if (!mSystem.isj2me) {
                                            tre = (TreeTopBottom) Tilemap.treeTop_bottom.get(String.valueOf(b) + t.x);
                                            if (tre != null) {
                                                tre.paintBottom(g);
                                            }
                                        }

                                        t.paint(g);
                                        if (!mSystem.isj2me && tre != null) {
                                            tre.paintTop(g, b);
                                        }
                                    }
                                } else if (t.y > b) {
                                    break;
                                }
                            }
                        }

                        size3 = this.actors.size();
                        if (b >= this.gssy && b <= this.gssye + 2) {
                            for (; actorIndex < size3; ++actorIndex) {
                                Actor actor = (Actor) this.actors.elementAt(actorIndex);
                                if (actor != null && actor.y >> 4 > b) {
                                    break;
                                }

                                if (actor != null && isInScreen(actor) && !actor.wantDestroy) {
                                    actor.paint(g);
                                }
                            }
                        }

                        ++b;
                    }
                }
            } catch (Exception var16) {
                var16.printStackTrace();
            }

            this.paintClound(g, 1);
            this.paintFocus(g);

            for (b = 0; b < arrowsUp.size(); ++b) {
                arrow = (IArrow) arrowsUp.elementAt(b);
                if (arrow != null) {
                    arrow.paint(g);
                }
            }

            Actor ac = null;
            mVector allnpc = new mVector();
            int size5 = this.actors.size();

            int i;
            for (i = 0; i < size5; ++i) {
                ac = (Actor) this.actors.elementAt(i);
                if (ac != null) {
                    ac.paintName(g);
                }

                if (ac != null && ac.isNPC()) {
                    allnpc.addElement(ac);
                }
            }

            byte fms;
            if (allLocation != null) {
                for (i = 0; i < allLocation.length; ++i) {
                    fms = 0;
                    if (allLocation[i] != null) {
                        if (allLocation[i].x >= Tilemap.w * 16 - 64) {
                            fms = 1;
                        }

                        if (allLocation[i].y <= 0 || allLocation[i].y >= Tilemap.h * 16 - 64) {
                            fms = 2;
                        }

                        allLocation[i].paint(g, fms);
                    }
                }
            }

            EffectManager.hiEffects.paintAll(g);

            try {
                this.paintFlyText(g);
            } catch (Exception var14) {
                var14.printStackTrace();
            }

            if (effAnimate.size() > 0 && !Tilemap.isOfflineMap && effAnimate.size() > 0) {
                tre = null;

                for (int c = 0; c < effAnimate.size(); ++c) {
                    AnimateEffect ani = (AnimateEffect) effAnimate.elementAt(c);
                    if (ani != null) {
                        ani.paint(g);
                    }
                }
            }

            try {
                if (this == GameCanvas.currentScreen) {
                    this.paintMiniMap(g, allnpc);
                    this.paintQuickSlot(g);
                }
            } catch (Exception var13) {
                var13.printStackTrace();
            }

            try {
                this.paintFocusActorInfo(g);
                GameCanvas.resetTrans(g);
            } catch (Exception var12) {
                var12.printStackTrace();
            }

            this.paintMainCharInfo(g);
            super.paint(g);
            if (this.listMSGServer != null && this.listMSGServer.size() > 0) {
                this.paintMsgServer(g);
            }

            GameCanvas.resetTrans(g);
            if (Tilemap.isMapIntro() && GameCanvas.isTouch && this.cmdLogin != null) {
                this.cmdskip.paint(g);
                this.cmdLogin.paint(g);
            }

            GameCanvas.resetTrans(g);
            this.paintcmd(g);
            this.paintShowEvent(g);
            if (mSystem.isPC) {
                if (this.chatMode) {
                    this.tfChat.paint(g);
                }

                if (this.chatWorld) {
                    this.tfChatWorld.paint(g);
                }
            } else {
                if (this.chatMode && !mSystem.isIP) {
                    this.tfChat.paint(g);
                }

                if (this.chatWorld && !mSystem.isIP) {
                    this.tfChatWorld.paint(g);
                }
            }

            this.paintArena(g);
            this.paintGhost(g);
            if (charcountdonw != null) {
                charcountdonw.paint(g, GameCanvas.w / 2 - 31, GameCanvas.h - 30);
            }

            this.paintTime(g);
            int fq = 0;
            if (!Tilemap.isMapPhoban() && hShowEvent <= 0 && isQuest && !isIntro && mainChar.getState() != 3 && GameCanvas.currentScreen == this && (this.focusedActor == null || this.focusedActor != null && this.focusedActor.isNPC())) {
                if (this.tickicon > 20) {
                    fq = 1;
                }

                g.drawRegion(imgPointQuest, 0, fq * 19, 18, 19, 0, GameCanvas.w - mGraphics.getImageWidth(imgPointQuest) - 5 - wMiniMap, 5, 0, false);
            }

            if (numMSG > 0 && !isIntro && mainChar.getState() != 3 && GameCanvas.currentScreen != null && GameCanvas.currentScreen.equals(this)) {
                fms = 0;
                if (this.tickicon < 20) {
                    fms = 1;
                }

                g.drawRegion(imgMSG, 0, fms * 13, 18, 13, 0, GameCanvas.w - 5 - wMiniMap - imgMSG.getWidth(), hMiniMap - imgMSG.getHeight() / 2, 0, false);
            }

            this.paintIcon(g);
            if (mSystem.isPC && GameCanvas.menu2.isShow && GameCanvas.currentScreen != MainMenu.gI() && !isIntro) {
                g.fillRectAlpha(0, 0, GameCanvas.w, GameCanvas.h, 0, 60, false);
            }

            if (GameCanvas.isTouch && GameCanvas.currentScreen == this) {
                this.paintTouchMove(g);
            }

            this.paintIconX2(g);

            try {
                ScreenTeam var10000 = GameCanvas.currentScreen;
            } catch (Exception var11) {
            }
        } catch (Exception var17) {
            var17.printStackTrace();
        }

    }

    public void paintIconX2(mGraphics g) {
        if (this.idIconX2 != -1 && !isIntro) {
            ImageIcon img = GameData.getImgIcon(this.idIconX2);
            if (img != null && img.img != null) {
                g.drawImage(img.img, 125, 2, 0, false);
                if (MainChar.itemOptionMainChar != null && MainChar.itemOptionMainChar.value > 0) {
                    FontTeam.numberSmall_white.drawString(g, String.valueOf(MainChar.itemOptionMainChar.value), 143, 6, 0, false);
                }
            }
        }

    }

    public void paintGhost(mGraphics g) {
        if (isGost) {
            g.drawImage(imgGhost, GameCanvas.w - 104, 35, 0, false);
        }
    }

    public void paintArena(mGraphics g) {
        if (!Tilemap.isMapIntro()) {
            if (!isIntro) {
                if (GameCanvas.currentScreen == null || GameCanvas.currentScreen == this) {
                    if (this.hideGUI != 2) {
                        if (!GameCanvas.menuSelectitem.showMenu) {
                            if (!GameCanvas.menu.showMenu) {
                                if (!MainChar.blockkey) {
                                    if (GameCanvas.currentDialog == null) {
                                        if (!this.chatMode && !this.chatWorld) {
                                            if (!GameCanvas.menu2.isShow) {
                                                if (GameCanvas.currentScreen == null || GameCanvas.currentScreen == this) {
                                                    g.drawImage(imgBackArena, 2, GameCanvas.h - 5 - 4, 0, false);
                                                    FontTeam.numberSmall_green.drawString(g, "" + arena, 2 + mGraphics.getImageWidth(imgBackArena) + 2, GameCanvas.h - 5 - 5, 0, false);
                                                    this.paintCharParty(g);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void paintcmd(mGraphics g) {
        if (mainChar.lv < 10 && this.cmdmenu != null) {
            this.cmdmenu.paint(g);
        }

        if (this.cmdchat != null && !isIntro && GameCanvas.currentScreen == this) {
            this.cmdchat.paint(g);
        }

    }

    public void paintSkillTest(mGraphics g) {
        String t = "";
        if (this.idskilltest >= 1 && this.idskilltest < 5) {
            t = "Thiếu Lâm: ";
        } else if (this.idskilltest >= 5 && this.idskilltest < 12) {
            t = "Ngũ Độc: ";
        } else if (this.idskilltest >= 12 && this.idskilltest < 17) {
            t = "Nga Mi: ";
        } else if (this.idskilltest >= 17 && this.idskilltest < 21) {
            t = "Võ Đang: ";
        } else if (this.idskilltest >= 21 && this.idskilltest <= 26) {
            t = "Cái Bang: ";
        }

        mFont.tahoma_7b_white.drawString(g, t + this.idskilltest, GameCanvas.w / 2, 50, 3, false);
    }

    public boolean canPaintNPC_SV(Actor npc) {
        return npc.x + npc.getWidth() / 2 >= cmx && npc.x - npc.getWidth() / 2 <= cmx + GameCanvas.w && npc.y - npc.getHeight() <= cmy + GameCanvas.h && npc.y >= cmy;
    }

    private void paintMsgServer(mGraphics g) {
        GameCanvas.resetTrans(g);
        if (!GameCanvas.isTouch) {
            g.drawImage(Res.imgCmdBar, 0, GameCanvas.h + 3, mGraphics.LEFT | mGraphics.BOTTOM, false);
        }

        g.setClip(15, GameCanvas.h - 20, GameCanvas.w - 30, 20);
        g.ClipRec(15, GameCanvas.h - 20, GameCanvas.w - 30, 20);
        String str = (String) this.listMSGServer.elementAt(0);
        FontTeam.arialFontW.drawString(g, str, this.xMsgServer, GameCanvas.h - 10 - FontTeam.arialFontW.getHeight() / 2, 0, false);
        g.restoreCanvas();
        g.setClip(0, 0, GameCanvas.h, GameCanvas.w);
    }

    public void doChat() {
        String text;
        if (this.chatMode) {
            if (GameCanvas.isTouch) {
                text = this.tfChat.getText();
                this.chatMode = false;
                if (text.equals("")) {
                    return;
                }

                this.tfChat.setText("");
                this.doChatFromMe(text);
                this.tfChat.isFocus = true;
            } else {
                text = this.tfChat.getText();
                if (text.equals("")) {
                    return;
                }

                this.tfChat.setText("");
                this.doChatFromMe(text);
                this.tfChat.isFocus = true;
            }
        }

        if (this.chatWorld) {
            text = this.tfChatWorld.getText();
            this.chatWorld = false;
            if (text.equals("")) {
                return;
            }

            this.tfChatWorld.setText("");
            this.tfChatWorld.isFocus = false;
            GameService.gI().doChatWorld(text);
        }

    }

    private void doChatFromMe(String text) {
        mainChar.chat = null;
        addChat(mainChar, text, 50);
        MessageScr.gI().addTab(mainChar.name + ": " + text, (String) null, 0);
        GameService.gI().chat(text);
        if (text != null && mSystem.isj2me && text.equals("pchar")) {
            Char.paintOrtherChar = !Char.paintOrtherChar;
        }

    }

    public static String getTextPaintHP(int num) {
        int n1;
        int n2;
        if (num >= 1000000000) {
            n1 = num / 1000000000;
            n2 = num / 100000000 % 10;
            return n2 != 0 ? n1 + "." + n2 + "B" : n1 + "B";
        } else if (num >= 1000000) {
            n1 = num / 1000000;
            n2 = num / 100000 % 10;
            return n2 != 0 ? n1 + "." + n2 + "m" : n1 + "m";
        } else if (num >= 1000) {
            n1 = num / 1000;
            n2 = num / 100 % 10;
            return n2 != 0 ? n1 + "." + n2 + "k" : n1 + "k";
        } else {
            return String.valueOf(num);
        }
    }

    private void paintMainCharInfo(mGraphics g) {
        if (GameCanvas.isTouch && mGraphics.zoomLevel > 1 && !mSystem.isPC && GameCanvas.currentScreen.equals(MainMenu.gI()))
            return;
        if (Tilemap.isMapIntro())
            return;
        GameCanvas.resetTrans(g);
        g.drawImage(imghealth[0], 0, 0, mGraphics.TOP | mGraphics.LEFT, true);
        int h123 = 34;
        int xp = 47;
        g.drawRegion(imghealth[2], 0, 0, mGraphics.getImageWidth(imghealth[2]), mGraphics.getImageHeight(imghealth[2]) / 2, 0, xp, h123 - 30, 0, false);
        g.drawRegion(imghealth[2], 0, mGraphics.getImageHeight(imghealth[2]) / 2, mGraphics.getImageWidth(imghealth[2]), mGraphics.getImageHeight(imghealth[2]) / 2, 0, xp, h123 - 19, 0, false);
        int hpPaint = 0, mpPaint = 0, exppaint = 0;
        if (mainChar.hp > 0 && mainChar.maxhp > 0) {
            hpPaint = mainChar.hp * 60 / mainChar.maxhp;
            if (hpPaint <= 0) {
                hpPaint = 1;
            } else if (hpPaint > 60) {
                hpPaint = 60;
            }
            g.drawRegion(imghealth[1], 0, 0, hpPaint, mGraphics.getImageHeight(imghealth[1]) / 2, 0, xp + 1, h123 - 29, 0, true);
        }
        if (mainChar.mp > 0 && mainChar.maxmp > 0) {
            mpPaint = mainChar.mp * 60 / mainChar.maxmp;
            if (mpPaint <= 0) {
                mpPaint = 1;
            } else if (mpPaint > 60) {
                mpPaint = 60;
            }
            g.drawRegion(imghealth[1], 0, mGraphics.getImageHeight(imghealth[1]) / 2, mpPaint, mGraphics.getImageHeight(imghealth[1]) / 2, 0, xp + 1, h123 - 18, 0, true);
        }
        if (mainChar.delay >= 0) {
            int[] t = mainChar.getTime();
            FontTeam.normalFont[0].drawString(g, String.valueOf(t[0]) + " : " + t[1], 5, h123 + 10, 0, false);
        }
        String tm = mainChar.getGoldTime();
        if (!tm.equals("")) {
            FontTeam.normalFont[0].drawString(g, tm, 5, h123 + 20, 0, false);
            FontTeam.smallFontColor[0].drawString(g, Char.goldTime, 5, h123 + 34, 0, false);
        }
        if (GameCanvas.gameTick % 10 > 3) {
            if (mainChar.basePointLeft > 0)
                g.drawImage(imgCong[0], GameCanvas.w - 2, h123 + 5, mGraphics.RIGHT | mGraphics.TOP, false);
            if (mainChar.skillPointLeft > 0)
                g.drawImage(imgCong[1], GameCanvas.w - 2, h123 + 16, mGraphics.RIGHT | mGraphics.TOP, false);
        }
        GameCanvas.resetTrans(g);
        g.setClip(5, 1, 30, 34);
        g.ClipRec(5, 1, 30, 34);
        if (mSystem.isPC || mSystem.isIP)
            g.fillRect(0, 0, 0, 0, false);
        mainChar.paint(g, 17, 60, 1);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        FontTeam ftHP = FontTeam.numberSmall_white;
        int yadd = 0;
        boolean isPcX1 = false;
        if (isPcX1) {
            ftHP = FontTeam.smallFont;
            yadd = 2;
        }
        if (this.tickHP > 0)
            if (this.inDexHP == 1) {
                ftHP = FontTeam.numberSmall_green;
            } else if (this.inDexHP == 2) {
                ftHP = FontTeam.numberSmall_red;
            }
        ftHP.drawString(g, String.valueOf(getTextPaintHP(mainChar.hp)) + "/" + getTextPaintHP(mainChar.maxhp), xp + 30, h123 - 31 + 2 + dy - 1 + yadd, 2, false);
        FontTeam ftMP = FontTeam.numberSmall_white;
        if (isPcX1)
            ftMP = FontTeam.smallFont;
        if (this.tickMP > 0)
            if (this.inDexMP == 1) {
                ftHP = FontTeam.numberSmall_green;
            } else if (this.inDexMP == 2) {
                ftHP = FontTeam.numberSmall_red;
            }
        ftMP.drawString(g, String.valueOf(getTextPaintHP(mainChar.mp)) + "/" + getTextPaintHP(mainChar.maxmp), xp + 30, h123 - 20 + 2 + dy - 1 + yadd, 2, false);
        FontTeam fExp = FontTeam.numberSmall_white;
        if (isPcX1)
            fExp = FontTeam.smallFont;
        fExp.drawString(g, String.valueOf(mainChar.level) + "+" + mainChar.getPercent(), 48, h123 - 11 + 2 + yadd, 0, false);
        g.drawImage(imglv, 36, h123 - 11 + 3, 0, false);
        if (mainChar.lvpercent > 0) {
            exppaint = mainChar.lvpercent / 10 * this.EXPBARW / 100;
            if (mSystem.isIP) {
                g.setColor(-14429405);
                g.fillRect(35, h123, exppaint, 1, false);
                g.setColor(-15836390);
                g.fillRect(35, h123, exppaint, 1, false);
            } else {
                g.setColor(-14429405);
                g.fillRect(35, h123, exppaint, 1, false);
            }
        }
        if (this.tickpaintFont > 0) {
            FontTeam ft = FontTeam.numberSmall_white;
            if (this.inDexfont == 1) {
                ft = FontTeam.numberSmall_red;
            } else if (this.inDexfont == 2) {
                ft = FontTeam.numberSmall_green;
            } else if (this.inDexfont == 3) {
                ft = FontTeam.numberSmall_blue;
            }
            ft.drawString(g, this.textinfomainChar, 90, h123 - 9, 0, false);
        }
    }

    private void paintQuickSlot(mGraphics g) {
        if (this.hideGUI != 2) {
            if (!GameCanvas.menuSelectitem.showMenu) {
                if (!GameCanvas.menu.showMenu) {
                    if (!MainChar.blockkey) {
                        if (GameCanvas.currentDialog == null) {
                            if (!this.chatMode && !this.chatWorld) {
                                if (!GameCanvas.menu2.isShow) {
                                    GameCanvas.resetTrans(g);
                                    if (GameCanvas.currentScreen == this) {
                                        int hImg = mGraphics.getImageHeight(imgTouchMove[3]) / 2;
                                        int wImg = mGraphics.getImageWidth(imgTouchMove[3]);
                                        int i;
                                        if (GameCanvas.isTouch) {
                                            i = mGraphics.getImageHeight(imgTouchMove[2]) / 2;
                                            int w0 = mGraphics.getImageWidth(imgTouchMove[2]);
                                            int[] xx = new int[]{xFire + 6, xSkill1 - 14, xSkill2 - 14, xSkill3 - 14, xSkill4 - 14};
                                            int[] yy = new int[]{yFire + 6, ySkill1 - 14, ySkill2 - 14, ySkill3 - 14, ySkill4 - 14};
                                            g.drawRegion(imgTouchMove[2], 0, i, w0, i, 0, xFire + w0 / 2, yFire + i / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                            if (!mSystem.isPC) {
                                                if (keyTouch == 4) {
                                                    g.drawRegion(imgTouchMove[2], 0, 0, w0, i, 0, xFire + w0 / 2, yFire + i / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                                }
                                            } else if (keyTouch == 0) {
                                                g.drawRegion(imgTouchMove[2], 0, 0, w0, i, 0, xFire + w0 / 2, yFire + i / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                            }

                                            g.drawRegion(imgTouchMove[3], 0, hImg, wImg, hImg, 0, xSkill1, ySkill1, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                            g.drawRegion(imgTouchMove[3], 0, hImg, wImg, hImg, 0, xSkill2, ySkill2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                            g.drawRegion(imgTouchMove[3], 0, hImg, wImg, hImg, 0, xSkill3, ySkill3, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                            g.drawRegion(imgTouchMove[3], 0, hImg, wImg, hImg, 0, xSkill4, ySkill4, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                            if (!mSystem.isPC) {
                                                if (keyTouch == 5) {
                                                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill1, ySkill1, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                                }

                                                if (keyTouch == 6) {
                                                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill2, ySkill2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                                }

                                                if (keyTouch == 7) {
                                                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill3, ySkill3, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                                }

                                                if (keyTouch == 8) {
                                                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill4, ySkill4, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                                }
                                            } else {
                                                if (keyTouch == 1) {
                                                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill1, ySkill1, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                                }

                                                if (keyTouch == 2) {
                                                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill2, ySkill2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                                }

                                                if (keyTouch == 3) {
                                                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill3, ySkill3, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                                }

                                                if (keyTouch == 4) {
                                                    g.drawRegion(imgTouchMove[3], 0, 0, wImg, hImg, 0, xSkill4, ySkill4, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                                }
                                            }

                                            if (this.cmdChangeFocus != null && !isIntro && !mSystem.isPC) {
                                                this.cmdChangeFocus.paint(g);
                                            }

                                            for (int cc = 0; cc < MainChar.mQuickslot.length; ++cc) {
                                                if (cc == 0) {
                                                    if (this.focusedActor != null && !mainChar.setFireChar(this.focusedActor) && !isIntro) {
                                                        g.drawImage(bt_Speak, xx[cc] + 3, yy[cc] + 3, 0, false);
                                                    } else if (!mSystem.isPC) {
                                                        MainChar.mQuickslot[cc].paint(g, xx[cc] + 2, yy[cc] + 2, 0);
                                                    } else {
                                                        MainChar.mQuickslot[cc].paint(g, xx[cc] + 2, yy[cc] + 2, this.ypc[cc]);
                                                    }
                                                } else if (!mSystem.isPC) {
                                                    MainChar.mQuickslot[cc].paint(g, xx[cc] + 2, yy[cc] + 2, 0);
                                                } else {
                                                    MainChar.mQuickslot[cc].paint(g, xx[cc] + 2, yy[cc] + 2, this.ypc[cc]);
                                                }
                                            }
                                        } else {
                                            for (i = 0; i < postSkill.length; ++i) {
                                                MainChar.mQuickslot[i].paint(g, postSkill[i], GameCanvas.h - 20, 0);
                                                g.drawRegion(imgTouchMove[3], 0, hImg, wImg, hImg, 0, postSkill[i], GameCanvas.h - 20, mGraphics.VCENTER | mGraphics.HCENTER, false);
                                            }
                                        }

                                        if (mSystem.isPC) {
                                            for (i = 0; i < this.numPC.length; ++i) {
                                                FontTeam.numberSmall_white.drawString(g, String.valueOf(i + 1), this.numPC[i], yFire + 41, 2, false);
                                            }
                                        }

                                        if (keyTouch != -1 && this.timeQouckSlot <= 0 && this.countTouchmove == 0) {
                                            keyTouch = -1;
                                        }

                                        if (!Tilemap.isMapIntro() && GameCanvas.isTouch) {
                                            g.drawImage(imgMenu[this.isTouchMenu ? 1 : 0], xMenu, yMenu, 0, false);
                                        }

                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static int getColorFocus(int charLv, int FocusActorLv, byte cat) {
        int value;
        if (cat == 0) {
            if (charLv < FocusActorLv) {
                value = Utils.abs(charLv - FocusActorLv);
                if (value >= 15) {
                    return ColorHpFocus[5];
                }

                if (value > 10) {
                    return ColorHpFocus[4];
                }

                if (value > 5) {
                    return ColorHpFocus[3];
                }
            } else if (charLv > FocusActorLv) {
                value = Utils.abs(charLv - FocusActorLv);
                if (value >= 15) {
                    return ColorHpFocus[0];
                }

                if (value > 10) {
                    return ColorHpFocus[1];
                }

                if (value > 5) {
                    return ColorHpFocus[2];
                }
            }

            return ColorHpFocus[0];
        } else if (cat == 1) {
            if (charLv < FocusActorLv) {
                value = Utils.abs(charLv - FocusActorLv);
                if (value >= 15) {
                    return ColorHpFocus1[5];
                }

                if (value > 10) {
                    return ColorHpFocus1[4];
                }

                if (value > 5) {
                    return ColorHpFocus1[3];
                }
            } else if (charLv > FocusActorLv) {
                value = Utils.abs(charLv - FocusActorLv);
                if (value >= 15) {
                    return ColorHpFocus1[0];
                }

                if (value > 10) {
                    return ColorHpFocus1[1];
                }

                if (value > 5) {
                    return ColorHpFocus1[2];
                }
            }

            return ColorHpFocus1[0];
        } else {
            return 0;
        }
    }

    public void paintFocus(mGraphics g) {
        try {
            if (this.focusedActor != null) {
                if (this.focusedActor == null || Utils.getDistance(mainChar, this.focusedActor) <= 192) {
                    if (isIntro) {
                        if (this.focusedActor != null) {
                            g.drawRegion(imgfocus[2], 0, this.fFocus * 10, 15, 10, 0, this.focusedActor.x, this.focusedActor.y - this.focusedActor.getHeight() + GameCanvas.gameTick % 7, 3, false);
                        }

                    } else {
                        if (this.focusedActor != null) {
                            if (this.focusedActor.catagory == 1 && this.focusedActor.getState() == 8) {
                                return;
                            }

                            if (this.focusedActor.isNpcChar()) {
                                if (this.focusedActor.chat == null) {
                                    g.drawRegion(imgfocus[2], 0, this.fFocus * 10, 15, 10, 0, this.focusedActor.x, this.focusedActor.y - this.focusedActor.getHeight() - 20 + GameCanvas.gameTick % 7, 3, false);
                                }

                                return;
                            }

                            if (this.focusedActor.catagory != 2 && this.focusedActor.catagory <= 2 && !this.focusedActor.isNpcChar()) {
                                if (this.focusedActor.getHp() >= 0 && this.focusedActor.getMaxHp() > 0 && this.focusedActor.chat == null) {
                                    int yss = 0;
                                    if (this.focusedActor.catagory == 1 && this.focusedActor.getColorName() > 0) {
                                        yss = -10;
                                    }

                                    g.drawRegion(imgfocus[2], 0, this.fFocus * 10, 15, 10, 0, this.focusedActor.x, this.focusedActor.y - this.focusedActor.getHeight() + GameCanvas.gameTick % 7 + yss, 3, false);
                                }
                            } else if (this.focusedActor.catagory > 2) {
                                if (this.focusedActor.chat == null) {
                                    g.drawRegion(imgfocus[2], 0, this.fFocus * 10, 15, 10, 0, this.focusedActor.x, this.focusedActor.y - 20 + GameCanvas.gameTick % 7, 3, false);
                                }
                            } else if (this.focusedActor.chat == null) {
                                g.drawRegion(imgfocus[2], 0, this.fFocus * 10, 15, 10, 0, this.focusedActor.x, this.focusedActor.y - this.focusedActor.getHeight() - 30 + GameCanvas.gameTick % 7, 3, false);
                            }
                        }

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void paintFocusActorInfo(mGraphics g) {
        try {
            if (isIntro) {
                return;
            }
            if (this.focusedActor != null && Utils.getDistance((Actor) mainChar, (Actor) this.focusedActor) > 192) {
                return;
            }
            if (this.focusedActor != null) {
                if (this.focusedActor.isNPC()) {
                    return;
                }
            }
            if (this.focusedActor == null) {
                GameCanvas.resetTrans((mGraphics) g);
                GameScr.Font3d(g, nameMap, GameCanvas.w - 2, 2, 1, mFont.tahoma_7b_white, false);
            }
            if (this.focusedActor != null && this.focusedActor.catagory == 1 && this.focusedActor.getHp() < 0) {
                return;
            }
            if (this.focusedActor != null) {
                if (this.focusedActor.catagory == 1 && this.focusedActor.getState() == 8) {
                    return;
                }
                if (this.focusedActor.isNpcChar()) {
                    GameCanvas.resetTrans((mGraphics) g);
                    GameScr.Font3d(g, this.focusedActor.getName(), GameCanvas.w - 2 - wMiniMap, 2, 1, mFont.tahoma_7b_white, false);
                    return;
                }
                if (this.focusedActor.catagory == 2 || this.focusedActor.catagory > 2 || this.focusedActor.isNpcChar()) {
                    if (this.focusedActor.catagory <= 2) {
                        GameCanvas.resetTrans((mGraphics) g);
                        GameScr.Font3d(g, this.focusedActor.getName(), GameCanvas.w - 2 - wMiniMap, 2, 1, mFont.tahoma_7b_white, false);
                    }
                } else if (this.focusedActor.getHp() >= 0 && this.focusedActor.getMaxHp() > 0) {
                    GameCanvas.resetTrans((mGraphics) g);
                    if (this.focusedActor.getTypeMove() == 5) {
                        mFont f = mFont.tahoma_7b_white;
                        if (this.focusedActor.getColorName() == 1) {
                            f = mFont.tahoma_7b_green;
                        }
                        if (this.focusedActor.getColorName() == 2) {
                            f = mFont.tahoma_7b_orange;
                        }
                        if (this.focusedActor.getColorName() == 3) {
                            f = mFont.tahoma_7b_yellow;
                        }
                        GameScr.Font3d(g, this.focusedActor.getName(), GameCanvas.w - 5 - wMiniMap, 2, 1, f, false);
                    } else {
                        g.drawRegion(imghealth[2], 0, 0, 62, 9, 0, GameCanvas.w - 65 - wMiniMap, 14, 0, false);
                        int w = mGraphics.getImageWidth((Image) imghealth[1]);
                        int wpPaint = 0;
                        long tile = w;
                        long objFocushp = this.focusedActor.getHp();
                        long maxHp = tile * objFocushp;
                        wpPaint = (int) (maxHp / (long) this.focusedActor.getMaxHp());
                        if (wpPaint <= 3) {
                            wpPaint = 3;
                        } else if (wpPaint > w) {
                            wpPaint = w;
                        }
                        mFont f = mFont.tahoma_7b_white;
                        if (this.focusedActor.getColorName() == 1) {
                            f = mFont.tahoma_7b_green;
                        }
                        if (this.focusedActor.getColorName() == 2) {
                            f = mFont.tahoma_7b_orange;
                        }
                        if (this.focusedActor.getColorName() == 3) {
                            f = mFont.tahoma_7b_yellow;
                        }
                        g.drawRegion(imghealth[1], 0, 0, wpPaint, 7, 0, GameCanvas.w - 64 - wMiniMap, 15, 0, false);
                        FontTeam.numberSmall_white.drawString(g, String.valueOf(GameScr.getTextPaintHP(this.focusedActor.getHp())) + "/" + GameScr.getTextPaintHP(this.focusedActor.getMaxHp()), GameCanvas.w - 64 + 30 - wMiniMap, 14, 2, false);
                        int xs = 0;
                        int xnguhanh = 0;
                        if (this.focusedActor.catagory == 1 && this.focusedActor.getHeNguHanh() > -1) {
                            xnguhanh = 10;
                        }
                        if (this.focusedActor.getColorName() != 0) {
                            mFont.name_Black.drawString(g, this.focusedActor.getName(), GameCanvas.w - 2 + 1 - 3 - wMiniMap, 2, 1, false);
                            mFont.name_White.drawString(g, this.focusedActor.getName(), GameCanvas.w - 2 - 3 - wMiniMap, 1, 1, false);
                            xs = FontTeam.numberSmall_white.getWidth("" + this.focusedActor.getLevel()) + 2;
                            FontTeam.numberSmall_white.drawString(g, "" + this.focusedActor.getLevel(), GameCanvas.w - 6 - wMiniMap, 24, 1, false);
                        } else {
                            GameScr.Font3d(g, this.focusedActor.getName(), GameCanvas.w - 2 - 3 - xnguhanh - wMiniMap, 1, 1, f, false);
                            if (this.focusedActor.getColorName() == 1) {
                                FontTeam.numberSmall_green.drawString(g, "" + this.focusedActor.getLevel(), GameCanvas.w - 6 - wMiniMap, 24, 1, false);
                            } else if (this.focusedActor.getColorName() == 3) {
                                FontTeam.numberSmall_yeallow.drawString(g, "" + this.focusedActor.getLevel(), GameCanvas.w - 6 - wMiniMap, 24, 1, false);
                            } else if (this.focusedActor.getColorName() == 0) {
                                FontTeam.numberSmall_white.drawString(g, "" + this.focusedActor.getLevel(), GameCanvas.w - 6 - wMiniMap, 24, 1, false);
                            } else {
                                GameScr.Font3d(g, "" + this.focusedActor.getLevel(), GameCanvas.w - 5 - wMiniMap, 21, 1, f, false);
                            }
                            xs = f.getWidth("" + this.focusedActor.getLevel()) + 2;
                        }
                        if (this.focusedActor != null && this.focusedActor.catagory == 1 && this.focusedActor.getHeNguHanh() > -1) {
                            g.drawRegion(imgNguhanh, 0, 10 * this.focusedActor.getHeNguHanh(), 10, 10, 0, GameCanvas.w - 12 - wMiniMap, 2, 1, false);
                        }
                        g.drawImage(imglv, GameCanvas.w - 15 - xs - wMiniMap, 25, 0, false);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    public void startNewMagicBeam(int type, Actor from, Actor to, int x, int y, int power, byte effect) {
        IArrow a = new MagicBeam();
        a.set(type, x, y, power, effect, from, to);
        arrowsUp.addElement(a);
    }

    public void startNewMagicBeam(int type, Actor from, Actor to, int x, int y, int power, byte effect, int angle) {
        IArrow a = new MagicBeam();
        a.set(type, x, y, power, effect, from, to);
        a.setAngle(angle);
        arrowsUp.addElement(a);
    }

    public void startNewMagicBeam(int type, Actor from, Actor to, int x, int y, int power, byte effect, int angle, int indexhead) {
        IArrow a = new MagicBeam();
        a.set(type, x, y, power, effect, from, to);
        a.setAngle(angle);
        a.setIDHEAD(indexhead);
        arrowsUp.addElement(a);
    }

    public void startNewMagicBeam(int type, Actor from, Actor to, int x, int y, int power, int eff) {
        IArrow a = new MagicBeam();
        a.set(type, x, y, power, from, to, eff);
        a.setAngle(angle);
        arrowsUp.addElement(a);
    }

    public void startNewMagicBeam_New(int type, Actor to, int x, int y, int power, int eff) {
        IArrow a = new MagicBeam();
        a.set(type, x, y, power, (byte) -1, (Actor) null, to);
        a.setAngle(angle);
        arrowsUp.addElement(a);
    }

    public void startNewArrow(int x, int y, Actor owner, Actor target, int power, int eff0, int eff1, int eff2, int endEff, int typeEnd, int speed) {
        Arrow a = new Arrow();
        a.set(x, y, owner, target, power, eff0, eff1, eff2, endEff, (byte) typeEnd, (byte) speed);
        arrowsUp.addElement(a);
    }

    public void startNewArrow(int x, int y, Actor owner, Actor target, int power, int eff0, int eff1, int eff2, int endEff, int typeEnd, int speed, int idEff) {
        Arrow a = new Arrow();
        a.idEff = idEff;
        a.set(x, y, owner, target, power, eff0, eff1, eff2, endEff, (byte) typeEnd, (byte) speed);
        arrowsUp.addElement(a);
    }

    public void startNewArrow_Dow(int x, int y, Actor owner, Actor target, int power, int eff0, int eff1, int eff2, int endEff, int typeEnd, int speed) {
        Arrow a = new Arrow();
        a.set(x, y, owner, target, power, eff0, eff1, eff2, endEff, (byte) typeEnd, (byte) speed);
        this.arrowsDown.addElement(a);
    }

    public void startDragon(int x, int y, Actor target, int power, int eff0, int endEff, int follow) {
        Arrow a = new Arrow();
        a.setDragon(x, y, target, power, eff0, endEff, follow);
        this.arrowsDown.addElement(a);
    }

    public void StartNewBaburan(int type, Actor from, Actor to, int x, int y, int power, byte effect, int imgIndex) {
        BaBuran b = new BaBuran(imgIndex);
        b.set(type, x, y, power, effect, from, to);
        arrowsUp.addElement(b);
    }

    public void StartNewEffectEnd(int type, int x, int y) {
        EffectEnd eff = new EffectEnd(type, x, y);
        EffectManager.addHiEffect(eff);
    }

    public void StartNewEffectEnd_Low(int type, int x, int y) {
        EffectEnd eff = new EffectEnd(type, x, y);
        EffectManager.addLowEffect(eff);
    }

    public void StartNewLightning(Actor target) {
        Lightning light = new Lightning(target);
        EffectManager.addHiEffect(light);
    }

    public void StartNewLightning(Actor from, Actor target, int type) {
        Lightning light = new Lightning(from, target, type);
        EffectManager.addHiEffect(light);
    }

    public void StartNewLightning(Actor target, int type) {
        Lightning light = new Lightning(target, type);
        EffectManager.addHiEffect(light);
    }

    public void StartNewLazer(int x, int y, int xto, int yto, Actor from, Actor acto) {
        WeaponsLazer wp = new WeaponsLazer(x, y, xto, yto, from, acto);
        EffectManager.addHiEffect(wp);
    }

    public Actor findCharByID(short id) {
        isFindChar = true;

        for (int i = this.actors.size() - 1; i >= 0; --i) {
            Actor a = (Actor) this.actors.elementAt(i);
            if (a.catagory == 0 && a.ID == id) {
                isFindChar = false;
                return a;
            }
        }

        isFindChar = false;
        return null;
    }

    public Monster findMonsterByID(short id) {
        isFindMonster = true;
        Actor a = null;

        for (int i = this.actors.size() - 1; i >= 0; --i) {
            a = (Actor) this.actors.elementAt(i);
            if (a.catagory == 1 && a.ID == id) {
                isFindMonster = false;
                return (Monster) a;
            }
        }

        isFindMonster = false;
        return null;
    }

    public Actor findActorByID(short id, byte cat) {
        Actor a = null;

        for (int i = this.actors.size() - 1; i >= 0; --i) {
            a = (Actor) this.actors.elementAt(i);
            if (a.ID == id && a.catagory == cat) {
                return a;
            }
        }

        return null;
    }

    private Actor findItemByID(short id, short cat) {
        Actor a = null;

        for (int i = this.actors.size() - 1; i >= 0; --i) {
            a = (Actor) this.actors.elementAt(i);
            if (a.catagory == cat && a.ID == id) {
                return a;
            }
        }

        return null;
    }

    private Dropable findPotionByID(short id, int catagory) {
        for (int i = this.actors.size() - 1; i >= 0; --i) {
            Actor a = (Actor) this.actors.elementAt(i);
            if (a.catagory == catagory && a.ID == id) {
                return (Dropable) a;
            }
        }

        return null;
    }

    public void onMonsterInfo(MonsterInfo monsterInfo) {
        if (this.readyGetGameLogic) {
            Monster m = this.findMonsterByID(monsterInfo.id);
            if (m != null) {
                m.setInfo(monsterInfo);
            }

        }
    }

    public void charOutGame(short charID) {
        Actor a = null;

        int i;
        for (i = 0; i < this.vecCharParty.size(); ++i) {
            a = (Actor) this.vecCharParty.elementAt(i);
            if (a != null && a.catagory == 0 && a.ID == charID) {
                this.vecCharParty.removeElement(a);
            }
        }

        for (i = 0; i < this.actors.size(); ++i) {
            a = (Actor) this.actors.elementAt(i);
            if (a.catagory == 0 && a.ID == charID) {
                a.wantDestroy = true;
                this.removepet(a.ID);
                EffectManager.addHiDataeffectSkill(156, a.x, a.y, 1);
                break;
            }
        }

    }

    public void removepet(short charID) {
        for (int i = 0; i < GameCanvas.gameScr.actors.size(); ++i) {
            Actor ac = (Actor) GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null && ac.catagory == 12 && ac.ID == charID) {
                ac.wantDestroy = true;
            }
        }

    }

    public void onConnectOK() {
        if (!mSystem.isj2me) {
            GameService.gI().doRequestDataChar(Res.VERSION_CHUNK, -1);
        }

    }

    public void onConnectFail() {
        if (GameCanvas.currentScreen != MenuLogin.gI()) {
            ChangScr.gI().switchToMe();
            if (GameCanvas.currentDialog == null) {
                GameCanvas.startOKDlg(T.khongtheketnoi, new mCommand("", this, 72));
            }
        } else {
            GameCanvas.gameScr.clearloadMap();
            removeAllchat();
            MenuLogin.gI().switchToMe();
        }

        this.cmdDisconect = null;
        if (!isMeLogin) {
            isDisConect = true;
            timeReconnect = System.currentTimeMillis() + 30000L;
        } else {
            isDisConect = false;
        }

        if (!isMeLogin) {
            isDisConect = true;
            timeReconnect = System.currentTimeMillis() + 30000L;
        } else {
            isDisConect = false;
        }

    }

    public void onDisconnect(String pos) {
        this.cmdDisconect = new mCommand("", this, 73);
        this.tickConnect = 0;
        mainChar.removeAllEff();
        if (!isMeLogin) {
            isDisConect = true;
            timeReconnect = System.currentTimeMillis() + 30000L;
        } else {
            isDisConect = false;
        }

        GameCanvas.gameScr.clearloadMap();
        removeAllchat();
        MenuLogin.gI().switchToMe();
        GameCanvas.hideAllDialog();
    }

    public void onViewAnimalInfo(Char ch) {
    }

    public void onAnimalWearingInfo(short charID, mVector items, Image imgAnimal0, byte numFrame0, int wimg0, int himg0, byte timeChangeFrame0) {
    }

    public void onGetItemFromGround(short whoGet, short id, short cat) {
        Actor item = this.findItemByID(id, cat);
        if (item != null) {
            if (this.focusedActor != null && this.focusedActor.equals(item)) {
                this.focusedActor = null;
            }

            Char c = (Char) this.findCharByID(whoGet);
            if (c != null) {
                item.setXYflyto(c.x, (short) (c.y - 30));
            } else {
                item.wantDestroy = true;
            }
        }

    }

    public void dellPotionQuest() {
        for (int i = 10; i < 14; ++i) {
            mainChar.potions[i] = 0;
        }

    }

    public void onNPCKeepItem(mVector itemBag) {
        Char.itembag = itemBag;
        this.showWindow(0, true, new byte[]{22});
    }

    public void onNPCSellGemItem(mVector gem) {
        this.showWindow(1, true, new byte[]{0, 10});
    }

    public void oncancelTrade() {
        this.cancelTrade();
        GameCanvas.startOKDlg("Giao dịch bị hủy.");
    }

    private void cancelTrade() {
        mainChar.isTrade = false;
        mainChar.tItems.removeAllElements();
        mainChar.rItems.removeAllElements();
        if (MainChar.listPotion != null) {
            for (int i = 0; i < MainChar.listPotion.length; ++i) {
                MainChar.listPotion[i].numTrade = 0;
            }
        }

    }

    public void onListShopOfNPC(byte[] idShop) {
        GameCanvas.currentDialog = null;
        mVector menu = new mVector();

        for (int i = 0; i < 3; ++i) {
            String a = i + ":" + idShop[idShop.length - 1] + ":" + idShop[idShop.length - 2];
            menu.addElement(new mCommand("Gian hàng " + (i + 1), 85, a));
        }

        GameCanvas.menu.startAt(menu, 3);
    }

    public void showWindow(int focus, boolean isSell, byte[] index) {// debug

//        byte var10000 = index[focus];
//        var10000 = index[focus];
//        var10000 = index[focus];
        if (GameCanvas.currentScreen != MainMenu.gI()) {
            MainMenu.gI().switchToMe(this);
        }

        focus = 0;
        MainMenu.gI().setInfo(focus, isSell, index);
    }

    public static void loadConfig(int type) {
        switch (type) {
            case 0:
                paintCay = 0;
                paintChar = 1;
                break;
            case 1:
            case 2:
                paintCay = 1;
                paintChar = 1;
                Char.paintOrtherChar = true;
                break;
            case 3:
                paintCay = 0;
                paintChar = 0;
                break;
            case 4:
                paintCay = 0;
                Char.paintOrtherChar = false;
        }

    }

    public void showConfig() {
        mVector menuItem = new mVector();
        String[] str = new String[]{"Thấp", "Vừa", "Cao", "Rất thấp"};

        for (int i = 0; i < 4; ++i) {
            mCommand cmd = new mCommand(str[i], 89, String.valueOf(i));
            menuItem.addElement(cmd);
        }

        GameCanvas.menu.startAt(menuItem, 3);
    }

    public void setNPC_server_limit(String name0, short ID0, short idImg0, short x0, short y0, short wimg0, short himg0, byte nFrame0, byte typeLimit) {
        if (typeLimit == 1) {
            int size0 = this.npc_limit.size();
            Actor ac = null;

            for (int i = 0; i < size0; ++i) {
                ac = (Actor) this.npc_limit.elementAt(i);
                if (ac != null && ac.catagory == 2) {
                    NPC npc = (NPC) ac;
                    if (npc.npcType == 1) {
                        Npc_Server npc_sever = (Npc_Server) npc;
                        if (npc_sever.ID == ID0) {
                            npc_sever.name = name0;
                            npc_sever.type = npc_sever.ID = ID0;
                            npc_sever.idImg = idImg0;
                            npc_sever.x = x0;
                            npc_sever.y = y0;
                            npc_sever.width = wimg0;
                            npc_sever.height = himg0;
                            npc_sever.nFrame = nFrame0;
                            npc.typeLimit = typeLimit;
                            return;
                        }
                    }
                }
            }

            Npc_Server bos = new Npc_Server();
            bos.name = name0;
            bos.type = bos.ID = ID0;
            bos.idImg = idImg0;
            bos.x = x0;
            bos.y = y0;
            bos.width = wimg0;
            bos.height = himg0;
            bos.nFrame = nFrame0;
            bos.typeLimit = typeLimit;
            this.npc_limit.addElement(bos);
        }
    }

    public void setNPC_server(String name0, short ID0, short idImg0, short x0, short y0, short wimg0, short himg0, byte nFrame0, byte typeLimit) {
        this.setNPC_server_limit(name0, ID0, idImg0, x0, y0, wimg0, himg0, nFrame0, typeLimit);
        int size0 = this.actors.size();
        Actor ac = null;

        for (int i = 0; i < size0; ++i) {
            ac = (Actor) this.actors.elementAt(i);
            if (ac != null && ac.catagory == 2) {
                NPC npc = (NPC) ac;
                if (npc.npcType == 1) {
                    Npc_Server npc_sever = (Npc_Server) npc;
                    if (npc_sever.ID == ID0) {
                        npc_sever.name = name0;
                        npc_sever.type = npc_sever.ID = ID0;
                        npc_sever.idImg = idImg0;
                        npc_sever.x = x0;
                        npc_sever.y = y0;
                        npc_sever.width = wimg0;
                        npc_sever.height = himg0;
                        npc_sever.nFrame = nFrame0;
                        npc.typeLimit = typeLimit;
                        return;
                    }
                }
            }
        }

        Npc_Server bos = new Npc_Server();
        bos.name = name0;
        bos.type = bos.ID = ID0;
        bos.idImg = idImg0;
        bos.x = x0;
        bos.y = y0;
        bos.width = wimg0;
        bos.height = himg0;
        bos.nFrame = nFrame0;
        bos.typeLimit = typeLimit;
        this.actors.addElement(bos);
    }

    public boolean checkMoveLimit(int xTo, int yTo) {
        if (this.npc_limit.size() == 0) {
            return false;
        } else {
            int size0 = this.npc_limit.size();
            Actor a = null;

            for (int i = 0; i < size0; ++i) {
                a = (Actor) this.npc_limit.elementAt(i);
                if (a != null) {
                    Npc_Server npc = (Npc_Server) a;
                    if (xTo >= npc.getLimxL() && xTo <= npc.getLimxR() && yTo >= npc.getLimyU() && yTo <= npc.getLimyD()) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    private void updatePoint() {
        if (!GameCanvas.menuSelectitem.showMenu) {
            if (!isIntro || canmove) {
                if (GameCanvas.msgchat == null || !GameCanvas.msgchat.isShow) {
                    if (!MainChar.blockkey) {
                        if (!isTouchMove) {
                            if (GameCanvas.currentDialog == null) {
                                if (!GameCanvas.menu.showMenu) {
                                    if (!GameCanvas.menu2.isShow) {
                                        if (!GameCanvas.isPointer(xFire, yFire, 50, 50, 0)) {
                                            if (!GameCanvas.isPointer(xSkill4 - 14, ySkill4 - 14, 28, 28, 0)) {
                                                if (!GameCanvas.isPointer(xSkill3 - 14, ySkill3 - 14, 28, 28, 0)) {
                                                    if (!GameCanvas.isPointer(xSkill2 - 14, ySkill2 - 14, 28, 28, 0)) {
                                                        if (!GameCanvas.isPointer(xSkill1 - 14, ySkill1 - 14, 28, 28, 0)) {
                                                            if (!GameCanvas.isPointer(GameCanvas.w - mGraphics.getImageWidth(imgPointQuest) - 5 - wMiniMap, 5, 30, 30, 0)) {
                                                                if (charcountdonw == null) {
                                                                    if (!GameCanvas.isPointer(GameCanvas.w - 30, 40, 30, 30, 0) || mSystem.isPC) {
                                                                        if (this.timetouch >= 0) {
                                                                            --this.timetouch;
                                                                        }

                                                                        if (GameCanvas.isPointer(0, 0, GameCanvas.w, GameCanvas.h, 0)) {
                                                                            int aa;
                                                                            int bb;
                                                                            if (GameCanvas.isPointer(this.posMiniMap.x, this.posMiniMap.y, wMiniMap, hMiniMap, 0) && GameCanvas.isPointerClick[0]) {
                                                                                aa = GameCanvas.pxLast[0] - GameCanvas.px[0];
                                                                                bb = GameCanvas.pyLast[0] - GameCanvas.py[0];
                                                                                if (Util.abs(bb) < 10 && Util.abs(aa) < 10) {
                                                                                    if (this.posCam == null) {
                                                                                        this.posCam = new Position(0, 0);
                                                                                    }

                                                                                    this.posCam.x = (short) (cmtoXMini + GameCanvas.px[0] - this.posMiniMap.x);
                                                                                    this.posCam.y = (short) (cmtoYmini + GameCanvas.py[0] - this.posMiniMap.y);
                                                                                    mainChar.posTransRoad = null;
                                                                                    if (!Tilemap.tileTypeAtPixel(this.posCam.x * 16 + 2, this.posCam.y * 16 + 2, 2) && !this.checkMoveLimit(this.posCam.x * 16 + 2, this.posCam.y * 16 + 2)) {
                                                                                        mainChar.xTo = mainChar.x;
                                                                                        mainChar.yTo = mainChar.y;
                                                                                        mainChar.xBegin = mainChar.x;
                                                                                        mainChar.yBegin = mainChar.y;
                                                                                        this.tg.index = 8;
                                                                                        mainChar.posTransRoad = this.updateFindRoad(mainChar.x / 16, mainChar.y / 16, this.posCam.x, this.posCam.y);
                                                                                        mainChar.countRoad = 0;
                                                                                        GameCanvas.isPointerClick[0] = false;
                                                                                        return;
                                                                                    }
                                                                                }
                                                                            }

                                                                            this.isAutoChangeFocus = false;
                                                                            if (this.timetouch < 0 && GameCanvas.isPointerClick[0]) {
                                                                                aa = cmx + GameCanvas.px[0];
                                                                                bb = cmy + GameCanvas.py[0];
                                                                                int size1;
                                                                                Actor ac;
                                                                                int i;
                                                                                if (this.focusedActor != null) {
                                                                                    if (this.focusedActor.catagory != 10) {
                                                                                        size1 = this.actors.size();
                                                                                        ac = null;

                                                                                        for (i = 0; i < size1; ++i) {
                                                                                            ac = (Actor) this.actors.elementAt(i);
                                                                                            if (Util.abs(ac.x - aa) <= 20 && Util.abs(ac.y - 20 - bb) <= 40) {
                                                                                                this.isAutoChangeFocus = false;
                                                                                                this.beginAuto = false;
                                                                                                GameCanvas.isPointerClick[0] = false;
                                                                                                if (this.focusedActor.ID == ac.ID) {
                                                                                                    if (mainChar.setFireChar(this.focusedActor)) {
                                                                                                        this.doFire();
                                                                                                    }

                                                                                                    if (this.focusedActor.catagory == 1) {
                                                                                                        this.doFire();
                                                                                                        return;
                                                                                                    }

                                                                                                    if (this.focusedActor.isNPC()) {
                                                                                                        this.doFire();
                                                                                                        return;
                                                                                                    }

                                                                                                    if (ac.catagory == 0) {
                                                                                                        if (((Char) this.focusedActor).idBot == -1 && this.canParty()) {
                                                                                                            if (!mainChar.setFireChar(this.focusedActor)) {
                                                                                                                this.doParty();
                                                                                                            }

                                                                                                            return;
                                                                                                        }

                                                                                                        this.doTouchQuickSlot(4);
                                                                                                        return;
                                                                                                    }

                                                                                                    if (this.focusedActor.catagory != 2 || ac.catagory != 2) {
                                                                                                        this.doFire();
                                                                                                        if ((!GameCanvas.menuSelectitem.isAutoDanh[0] || this.focusedActor.catagory == 2) && ac.catagory == 2) {
                                                                                                            this.isStartAutoAttack = true;
                                                                                                        } else {
                                                                                                            if (!this.isStartAutoAttack) {
                                                                                                                this.xStartAuto = this.focusedActor.x;
                                                                                                                this.yStartAuto = this.focusedActor.y;
                                                                                                            }

                                                                                                            this.isStartAutoAttack = true;
                                                                                                        }

                                                                                                        return;
                                                                                                    }

                                                                                                    if (((NPC) this.focusedActor).type == ((NPC) ac).type) {
                                                                                                        if (((NPC) ac).type != 4) {
                                                                                                            this.doFire();
                                                                                                            return;
                                                                                                        }

                                                                                                        if (((NPC) this.focusedActor).idLinhGac == ((NPC) ac).idLinhGac) {
                                                                                                            this.doFire();
                                                                                                            return;
                                                                                                        }

                                                                                                        if (ac.ID != mainChar.ID) {
                                                                                                            this.focusedActor = ac;
                                                                                                            this.doFire();
                                                                                                            return;
                                                                                                        }
                                                                                                    }
                                                                                                }

                                                                                                if (ac.ID != mainChar.ID) {
                                                                                                    boolean isContinue = false;
                                                                                                    if (typeOptionFocus == 1) {
                                                                                                        if (ac.catagory == 0 && ac.isNPC()) {
                                                                                                            isContinue = true;
                                                                                                        }
                                                                                                    } else if (typeOptionFocus == 2) {
                                                                                                        if (!ac.isNPC()) {
                                                                                                            isContinue = true;
                                                                                                        }
                                                                                                    } else if (typeOptionFocus == 3) {
                                                                                                        if (ac.catagory == 0 || ac.isNPC()) {
                                                                                                            isContinue = true;
                                                                                                        }
                                                                                                    } else if (typeOptionFocus == 4 && (ac.catagory == 0 || ac.isNPC())) {
                                                                                                        isContinue = true;
                                                                                                    }

                                                                                                    if (!ac.canFocus()) {
                                                                                                        isContinue = true;
                                                                                                    }

                                                                                                    if (!isContinue) {
                                                                                                        this.focusedActor = ac;
                                                                                                        this.isAutoChangeFocus = false;
                                                                                                    }

                                                                                                    if (Util.abs(ac.x - mainChar.x) > 30 || Util.abs(ac.y - mainChar.y) > 30) {
                                                                                                        if ((this.focusedActor.catagory != 2 || ac.catagory != 2) && GameCanvas.menuSelectitem.isAutoDanh[0]) {
                                                                                                            this.isStartAutoAttack = true;
                                                                                                        }

                                                                                                        if (this.isMovebyTouch) {
                                                                                                            this.findRoad(ac.x, ac.y);
                                                                                                        }

                                                                                                        this.isAutoChangeFocus = false;
                                                                                                        if (mainChar.posTransRoad != null) {
                                                                                                            this.beginAuto = false;
                                                                                                        }
                                                                                                    }

                                                                                                    if (ac.canFocus()) {
                                                                                                        this.focusedActor = ac;
                                                                                                    }

                                                                                                    if (GameCanvas.menuSelectitem.isAutoDanh[0] && this.focusedActor.catagory != 2 || ac.catagory != 2) {
                                                                                                        this.isStartAutoAttack = true;
                                                                                                    }
                                                                                                }

                                                                                                return;
                                                                                            }
                                                                                        }
                                                                                    } else if (mainChar.x >= this.xBeginFrame && mainChar.x <= this.xTheendFrame + 32 && mainChar.y >= this.yBeginFrame && mainChar.y <= this.yTheendFrame + 32) {
                                                                                        size1 = this.actors.size();
                                                                                        ac = null;

                                                                                        for (i = 0; i < size1; ++i) {
                                                                                            ac = (Actor) this.actors.elementAt(i);
                                                                                            if (aa >= ac.x + 2 && aa <= ac.x + 30 && bb >= ac.y && bb <= ac.y + 30) {
                                                                                                GameCanvas.isPointerClick[0] = false;
                                                                                                if (this.focusedActor.ID != -1 && this.focusedActor.ID == ac.ID) {
                                                                                                    if (mainChar.state == 0) {
                                                                                                        this.doFire();
                                                                                                    }

                                                                                                    return;
                                                                                                }

                                                                                                if (ac.ID != mainChar.ID && (this.isMovebyTouch || mSystem.isPC)) {
                                                                                                    this.findRoad(aa, bb);
                                                                                                    if (mainChar.posTransRoad != null) {
                                                                                                        this.beginAuto = false;
                                                                                                    }
                                                                                                }

                                                                                                return;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                } else {
                                                                                    size1 = this.actors.size();
                                                                                    ac = null;

                                                                                    for (i = 0; i < size1; ++i) {
                                                                                        ac = (Actor) this.actors.elementAt(i);
                                                                                        if (ac != null && ac.canFocus() && Util.abs(ac.x - aa) <= 20 && Util.abs(ac.y - 20 - bb) <= 40 && !ac.equals(mainChar)) {
                                                                                            if (ac.isNPC()) {
                                                                                                this.focusedActor = ac;
                                                                                            }

                                                                                            if (ac.catagory == 1 && !ac.canFocusMonster()) {
                                                                                                this.focusedActor = ac;
                                                                                            }

                                                                                            if (ac.catagory == 0) {
                                                                                                this.focusedActor = ac;
                                                                                            }

                                                                                            this.isAutoChangeFocus = false;
                                                                                            this.beginAuto = false;
                                                                                        }
                                                                                    }
                                                                                }

                                                                                if (this.isMovebyTouch || mSystem.isPC) {
                                                                                    this.findRoad(aa, bb);
                                                                                    if (mainChar.posTransRoad != null) {
                                                                                        if (mainChar.isNPC()) {
                                                                                            mainChar.posTransRoad = null;
                                                                                            this.doHuyBanHang();
                                                                                        }

                                                                                        this.beginAuto = false;
                                                                                    }
                                                                                }
                                                                            }
                                                                        }

                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void findRoad(int aa, int bb) {
        if (mainChar.mcanmove()) {
            aa /= 16;
            bb /= 16;
            if (!Tilemap.tileTypeAtPixel(cmx + GameCanvas.px[0], cmy + GameCanvas.py[0], 2) || isIntro) {
                if (this.posCam == null) {
                    this.posCam = new Position(0, 0);
                }

                this.posCam.x = (short) aa;
                this.posCam.y = (short) bb;
                GameCanvas.isPointerClick[0] = false;
                mainChar.xTo = mainChar.x;
                mainChar.yTo = mainChar.y;
                mainChar.xBegin = mainChar.x;
                mainChar.yBegin = mainChar.y;
                this.tg.index = 0;
                if (MainUnity.isJava) {
                    mainChar.posTransRoad = this.updateFindRoad(mainChar.x / 16, mainChar.y / 16, aa, bb);
                } else {
                    mainChar.posTransRoad = this.updateFindRoad(mainChar.x / 16, mainChar.y / 16, aa, bb);
                }

                mainChar.countRoad = 0;
            }

        }
    }

    public void findRoad2(int aa, int bb) {
        if (mainChar.mcanmove()) {
            aa /= 16;
            bb /= 16;
            if (this.posCam == null) {
                this.posCam = new Position(0, 0);
            }

            this.posCam.x = (short) aa;
            this.posCam.y = (short) bb;
            mainChar.xTo = mainChar.x;
            mainChar.yTo = mainChar.y;
            mainChar.xBegin = mainChar.x;
            mainChar.yBegin = mainChar.y;
            this.tg.index = 8;
            System.out.println("tim duong di findroad 2 " + bb);
            mainChar.posTransRoad = this.updateFindRoad(mainChar.x / 16, mainChar.y / 16, aa, bb);
            mainChar.countRoad = 0;
            timeRemovePos = 100;
        }
    }

    private int setDis(int x1, int y1, int x2, int y2) {
        return Util.abs(x1 - x2) + Util.abs(y1 - y2);
    }

    private short[] updateFindRoad(int xF, int yF, int xTo, int yTo) {
        if (xTo == 0 || yTo == 0)
            return null;
        try {
            if (Util.distance(xF * 16, yF * 16, xTo * 16, yTo * 16) <= 16)
                return null;
            if (!mainChar.canFire()) {
                mSystem.println("khong the tim duong ne " + mainChar.canFire());
                return null;
            }
            xStart = (byte) cmxMini;
            yStart = (byte) cmyMini;
            xF -= xStart;
            yF -= yStart;
            xTo -= xStart;
            yTo -= yStart;
            int s = mapFind.length;
            for (int i = 0; i < s; i++) {
                for (int j = 0; j < (mapFind[i]).length; j++) {
                    int aa = (yStart + j) * Tilemap.w + xStart + i;
                    if (aa < Tilemap.type.length - 1)
                        if (Tilemap.type[aa] == 2) {
                            mapFind[i][j] = -1;
                        } else {
                            mapFind[i][j] = 0;
                        }
                }
            }
            short index = 0;
            int xFrom = xF;
            int yFrom = yF;
            short xGoc = (short) xFrom, yGoc = (short) yFrom;
            if (xFrom < 0 || xFrom >= mapFind.length)
                return null;
            if (xFrom < mapFind.length && yFrom < (mapFind[xFrom]).length)
                mapFind[xFrom][yFrom] = 1;
            index = 2;
            int iL = mapFind.length, jL = (mapFind[0]).length;
            int count = 0;
            while (true) {
                count++;
                if (count > 100) {
                    if (iTaskAuto == 2) {
                        this.iTaskAutoLast = iTaskAuto;
                        isFixBugAutoQuest = true;
                        count = 0;
                    }
                    iTaskAuto = 1;
                    Cout.println("Cout DAY ROI COUNT------: " + count);
                    return null;
                }
                int xCur = -1;
                int yCur = -1;
                if (xFrom + 1 < iL && mapFind[xFrom + 1][yFrom] == 0) {
                    mapFind[xFrom + 1][yFrom] = (byte) index;
                    xCur = xFrom + 1;
                    yCur = yFrom;
                }
                if (xFrom - 1 >= 0 && mapFind[xFrom - 1][yFrom] == 0) {
                    mapFind[xFrom - 1][yFrom] = (byte) index;
                    if (xCur != -1) {
                        if (setDis(xCur, yCur, xTo, yTo) > setDis(xFrom - 1, yFrom, xTo, yTo)) {
                            xCur = xFrom - 1;
                            yCur = yFrom;
                        }
                    } else {
                        xCur = xFrom - 1;
                        yCur = yFrom;
                    }
                }
                if (yFrom + 1 < jL && mapFind[xFrom][yFrom + 1] == 0) {
                    mapFind[xFrom][yFrom + 1] = (byte) index;
                    if (xCur != -1) {
                        if (setDis(xCur, yCur, xTo, yTo) > setDis(xFrom, yFrom + 1, xTo, yTo)) {
                            xCur = xFrom;
                            yCur = yFrom + 1;
                        }
                    } else {
                        xCur = xFrom;
                        yCur = yFrom + 1;
                    }
                }
                if (yFrom - 1 >= 0 && mapFind[xFrom][yFrom - 1] == 0) {
                    mapFind[xFrom][yFrom - 1] = (byte) index;
                    if (xCur != -1) {
                        if (setDis(xCur, yCur, xTo, yTo) > setDis(xFrom, yFrom - 1, xTo, yTo)) {
                            xCur = xFrom;
                            yCur = yFrom - 1;
                        }
                    } else {
                        xCur = xFrom;
                        yCur = yFrom - 1;
                    }
                }
                int aa = -1;
                if (xCur != -1) {
                    aa = 0;
                    xFrom = xCur;
                    yFrom = yCur;
                } else {
                    xFrom = yFrom = 1000;
                }
                for (short s1 = 0; s1 < iL; s1 = (short) (s1 + 1)) {
                    for (short j = 0; j < jL; j = (short) (j + 1)) {
                        if (mapFind[s1][j] > 1 && setContinue(s1, j, mapFind) && mapFind[s1][j] + setDis(s1, j, xTo, yTo) < index + setDis(xFrom, yFrom, xTo, yTo)) {
                            xFrom = s1;
                            yFrom = j;
                            index = mapFind[s1][j];
                            aa = 0;
                        }
                    }
                }
                if (xFrom == xTo && yFrom == yTo)
                    break;
                if (aa == 0) {
                    index = (short) (index + 1);
                    continue;
                }
                Cout.println("TOI DAY ROI------: " + xCur);
                return null;
            }
            if (index >= 127)
                return null;
            int ia = 0;
            short[] success = new short[index];
            while (true) {
                success[ia] = (short) ((xFrom << 8) + yFrom);
                if (xFrom + 1 < iL && mapFind[xFrom + 1][yFrom] == mapFind[xFrom][yFrom] - 1) {
                    xFrom = (short) (xFrom + 1);
                } else if (xFrom - 1 >= 0 && mapFind[xFrom - 1][yFrom] == mapFind[xFrom][yFrom] - 1) {
                    xFrom = (short) (xFrom - 1);
                } else if (yFrom + 1 < jL && mapFind[xFrom][yFrom + 1] == mapFind[xFrom][yFrom] - 1) {
                    yFrom = (short) (yFrom + 1);
                } else if (yFrom - 1 >= 0 && mapFind[xFrom][yFrom - 1] == mapFind[xFrom][yFrom] - 1) {
                    yFrom = (short) (yFrom - 1);
                }
                if (xFrom == xGoc && yFrom == yGoc)
                    break;
                ia++;
            }
            return success;
        } catch (Exception e) {
            return null;
        }
    }


    private boolean setContinue(int i, int j, byte[][] mapFind) {
        if (i + 1 < mapFind.length && mapFind[i + 1][j] == 0) {
            return true;
        } else if (i - 1 >= 0 && mapFind[i - 1][j] == 0) {
            return true;
        } else if (j + 1 < mapFind[i].length && mapFind[i][j + 1] == 0) {
            return true;
        } else {
            return j - 1 >= 0 && mapFind[i][j - 1] == 0;
        }
    }

    public int getIndexClassFromItemID(int itemID) {
        if (itemID >= 79 && itemID <= 113) {
            return (itemID - 79) / 7;
        } else if (itemID >= 174 && itemID <= 213) {
            return (itemID - 174) / 8;
        } else {
            return itemID >= 214 && itemID <= 263 ? (itemID - 214) / 10 : 0;
        }
    }

    public void onMSGServer(String readUTF) {
        if (this.listMSGServer == null) {
            this.listMSGServer = new mVector();
        }

        this.listMSGServer.addElement(readUTF);
    }

    public void onSkillClan(SkillClan[] skillclan, SkillClan[] skillClanPrivate) {
        this.skillClan = skillclan;
        this.skillClanPrivate = skillClanPrivate;
    }

    public static int isNpcQuest(int idNpc) {
        return -1;
    }

    public void getAllPosNPCMInimap(Actor npc) {
    }

    public static mVector getAllQuestNpc(int idNpc) {
        mVector allquest = new mVector();
        return allquest;
    }

    public void setPosNPC(int id, int indexColor, int x, int y) {
        for (int i = 0; i < PosNPCQuest.size(); ++i) {
            Position p = (Position) PosNPCQuest.elementAt(i);
            if (p != null && p.id == id) {
                return;
            }
        }

        Position pp = new Position();
        pp.id = id;
        pp.indexColor = (short) indexColor;
        pp.x = x;
        pp.y = y;
        PosNPCQuest.addElement(pp);
    }

    public void onFruit(Message msg) {
    }

    public static void removeAllchat() {
        VecInfoServer.removeAllElements();
        MsgChat.vecChatTab.removeAllElements();
    }

    public void perform(int idAction, Object p) {
        int findAll;
        SetInfoData s11, s112, s13, s14;
        MainEvent ev;
        mSystem.println("index perfom gamescr " + idAction);
        switch (idAction) {
            case 0:
                CharSelectScr.gI().switchToMe();
                Bossintro = null;
                isIntro = false;
                MainChar.blockkey = false;
                EffectManager.lowEffects.removeAllElements();
                EffectManager.hiEffects.removeAllElements();
                vecCharintro.removeAllElements();
                GameData.listbyteData.clear();
                GameData.listImgIcon.clear();
                this.actors.removeAllElements();
                break;
            case 1:
                if (Tilemap.isMapIntro()) {
                    mVector list = new mVector();
                    list.addElement(this.cmdLogin);
                    if (!GameCanvas.isTouch)
                        list.addElement(this.cmdskip);
                    GameCanvas.menu.startAt(list, 0);
                    break;
                }
                if (!mainChar.isNPC()) {
                    MainMenu.captionServer = "";
                    MainMenu.infoBuySellServer = "";
                }
                MainMenu.isInven = true;
                MainMenu.gI().switchToMe(this);
                (MainMenu.gI()).isRestartAutoFight = this.isStartAutoAttack;
                this.isStartAutoAttack = false;
                MainMenu.gI().PutItemSHop(shop);
                (MainMenu.gI()).idNPC_Shop = idNPCshopInven;
                (MainMenu.gI()).catNPC_Shop = catNPCshopInven;
                changeToNextFocusActor(false);
                break;
            case 2:
                doFire();
                break;
            case 3:
                this.chatMode = true;
                this.tfChat.doChangeToTextBox();
                if (mSystem.isPC)
                    this.tfChat.isFocus = true;
                break;
            case 4:
                GameCanvas.endDlg();
                break;
            case 5:
                if (this.focusedActor != null)
                    if (this.focusedActor.getStateQuest() == 1 || this.focusedActor.getStateQuest() == 2 || this.focusedActor.getStateQuest() == 0) {
                        mVector menu = new mVector();
                        SetInfoData s5 = new SetInfoData();
                        s5.ID = this.focusedActor.getIDNpc();
                        s5.idIcon = this.focusedActor.getIDicon();
                        s5.stateQuest = this.focusedActor.getStateQuest();
                        mCommand cmd1 = new mCommand(T.trochuyen, this, 10);
                        mCommand cmd2 = new mCommand(T.nhiemvu, this, 11, s5);
                        menu.addElement(cmd1);
                        menu.addElement(cmd2);
                        GameCanvas.menu.startAt(menu, 2);
                    } else {
                        GameService.gI().requestNPCInfo(this.focusedActor.getIDNpc());
                    }
                GameCanvas.endDlg();
                break;
            case 7:
                this.isAutoChangeFocus = false;
                findAll = 0;
                if (Ghost != null)
                    return;
                if (!mainChar.isDie() && GameCanvas.menuSelectitem.isAutoDanh[0] && !Tilemap.ismapLang && this.beginAuto) {
                    Actor ac = findFocusActorTouch();
                    if (ac != null && ac.canFocusMonster() && GameCanvas.currentDialog == null && ac.canFocus())
                        this.focusedActor = ac;
                    return;
                }
                changeToNextFocusActor(false);
                break;
            case 10:
                GameService.gI().requestNPCInfo(this.focusedActor.getIDNpc());
                GameCanvas.endDlg();
                break;
            case 11:
                s11 = (SetInfoData) p;
                if (s11 != null) {
                    if (s11.stateQuest == 0 && allQuestCanReceive.size() > 0) {
                        mVector menu = new mVector();
                        for (int i = 0; i < allQuestCanReceive.size(); i++) {
                            Quest q = (Quest) allQuestCanReceive.elementAt(i);
                            if (q != null && q.idNpcReceive == this.focusedActor.getIDNpc()) {
                                SetInfoData s12 = new SetInfoData();
                                s12.stateQuest = s11.stateQuest;
                                s12.idIcon = s11.idIcon;
                                s12.ID = this.focusedActor.getIDNpc();
                                s12.idQuest = i;
                                mCommand cmd = new mCommand(String.valueOf(T.nhiemvu) + " " + q.name, this, 12, s12);
                                menu.addElement(cmd);
                            }
                        }
                        if (menu.size() == 1) {
                            mCommand cmd1 = (mCommand) menu.elementAt(0);
                            if (cmd1 != null)
                                cmd1.performAction();
                        } else {
                            GameCanvas.menu.startAt_MenuOption(menu, -1, -1, s11.str, s11.idIcon);
                        }
                    }
                    if (s11.stateQuest == 2 && allQuestWorking.size() > 0) {
                        mVector menu = new mVector();
                        for (int i = 0; i < allQuestWorking.size(); i++) {
                            Quest q = (Quest) allQuestWorking.elementAt(i);
                            if (q != null && q.idNpcResponse == this.focusedActor.getIDNpc()) {
                                SetInfoData s12 = new SetInfoData();
                                s12.stateQuest = s11.stateQuest;
                                s12.idIcon = s11.idIcon;
                                s12.idQuest = i;
                                s12.ID = this.focusedActor.getIDNpc();
                                mCommand cmd = new mCommand(String.valueOf(T.nhiemvu) + " " + q.name, this, 12, s12);
                                menu.addElement(cmd);
                            }
                        }
                        if (menu.size() == 1) {
                            mCommand cmd1 = (mCommand) menu.elementAt(0);
                            if (cmd1 != null)
                                cmd1.performAction();
                        } else {
                            GameCanvas.menu.startAt_MenuOption(menu, -1, -1, s11.str, s11.idIcon);
                        }
                    }
                    if (s11.stateQuest == 1 && allQuestFinish.size() > 0) {
                        mVector menu = new mVector();
                        for (int i = 0; i < allQuestFinish.size(); i++) {
                            Quest q = (Quest) allQuestFinish.elementAt(i);
                            if (q != null && q.idNpcResponse == this.focusedActor.getIDNpc()) {
                                SetInfoData s12 = new SetInfoData();
                                s12.stateQuest = s11.stateQuest;
                                s12.idIcon = s11.idIcon;
                                s12.idQuest = i;
                                s12.ID = this.focusedActor.getIDNpc();
                                mCommand cmd = new mCommand(String.valueOf(T.nhiemvu) + " " + q.name, this, 12, s12);
                                menu.addElement(cmd);
                            }
                        }
                        if (menu.size() == 1) {
                            mCommand cmd1 = (mCommand) menu.elementAt(0);
                            if (cmd1 != null)
                                cmd1.performAction();
                            break;
                        }
                        GameCanvas.menu.startAt_MenuOption(menu, -1, -1, s11.str, s11.idIcon);
                    }
                }
                break;
            case 12:
                s112 = (SetInfoData) p;
                questContent = null;
                CountQuestConten = 0;
                if (s112.stateQuest == 0) {
                    Quest q12 = (Quest) allQuestCanReceive.elementAt(s112.idQuest);
                    if (q12 != null && q12.idNpcReceive == this.focusedActor.getIDNpc()) {
                        questContent = new String[q12.content.size()];
                        s112.idQuest = q12.idQuest;
                        s112.str = q12.name;
                        s112.mainSub = q12.main_sub;
                        s112.type = q12.type;
                        for (int i = 0; i < questContent.length; i++) {
                            String str = (String) q12.content.elementAt(i);
                            if (str != null)
                                questContent[i] = str;
                        }
                    }
                } else if (s112.stateQuest == 2) {
                    Quest q12 = (Quest) allQuestWorking.elementAt(s112.idQuest);
                    if (q12 != null && q12.idNpcResponse == this.focusedActor.getIDNpc()) {
                        s112.idQuest = q12.idQuest;
                        s112.str = q12.name;
                        s112.decript = q12.decript;
                        s112.mainSub = q12.main_sub;
                        s112.type = q12.type;
                        addChat(this.focusedActor, q12.decript, 100);
                    }
                } else if (s112.stateQuest == 1) {
                    Quest q12 = (Quest) allQuestFinish.elementAt(s112.idQuest);
                    if (q12 != null && q12.idNpcResponse == this.focusedActor.getIDNpc()) {
                        questContent = new String[q12.rescontent.size()];
                        s112.idQuest = q12.idQuest;
                        s112.str = q12.name;
                        s112.decript = q12.decript;
                        s112.mainSub = q12.main_sub;
                        s112.type = q12.type;
                        for (int i = 0; i < questContent.length; i++) {
                            String str = (String) q12.rescontent.elementAt(i);
                            if (str != null)
                                questContent[i] = str;
                        }
                    }
                }
                if (questContent != null)
                    GameCanvas.StartNextDiaLog(questContent[CountQuestConten], new mCommand(T.next, this, 13, s112), this.focusedActor.getIDicon());
                break;
            case 13:
                s13 = (SetInfoData) p;
                if (s13.stateQuest == 0) {
                    CountQuestConten = (byte) (CountQuestConten + 1);
                    if (CountQuestConten > questContent.length - 1) {
                        if (s13.type == 3) {
                            GameService.gI().receiveQuestNew(1, s13.idQuest, s13.mainSub);
                            GameCanvas.endDlg();
                        } else {
                            GameService.gI().receiveQuestNew(0, s13.idQuest, s13.mainSub);
                            GameCanvas.endDlg();
                        }
                    } else {
                        GameCanvas.StartNextDiaLog(questContent[CountQuestConten], new mCommand(T.next, this, 13, s13), this.focusedActor.getIDicon());
                    }
                }
                if (s13.stateQuest == 1) {
                    CountQuestConten = (byte) (CountQuestConten + 1);
                    if (CountQuestConten > questContent.length - 1) {
                        isSendFinishQuest = true;
                        GameCanvas.endDlg();
                        GameService.gI().receiveQuestNew(1, s13.idQuest, s13.mainSub);
                    } else {
                        GameCanvas.StartNextDiaLog(questContent[CountQuestConten], new mCommand(T.next, this, 13, s13), this.focusedActor.getIDicon());
                    }
                }
                if (s13.stateQuest == 2)
                    GameCanvas.startOKDlg(s13.decript);
                break;
            case 14:
                s14 = (SetInfoData) p;
                GameService.gI().receiveQuestNew(0, s14.idQuest, s14.mainSub);
                GameCanvas.endDlg();
                break;
            case 15:
                if (this.focusedActor != null)
                    GameCanvas.addChat(this.focusedActor.getName(), "", true);
                break;
            case 16:
                GameService.gI().Friend((byte) 0, this.focusedActor.getName());
                break;
            case 17:
                ev = EventScreen.setEvent(this.eventShow.nameEvent, (byte) this.eventShow.IDCmd);
                if (ev != null)
                    GameCanvas.mevent.doEvent(false, ev);
                if (timeEvent > 40)
                    timeEvent = 40;
                numMSG = 0;
                timeEvent = 0;
                break;
            case 19:
                if (this.focusedActor != null) {
                    if (this.focusedActor.isNPC()) {
                        doFire();
                        break;
                    }
                    this.gameService.requestSomeOneInfo(this.focusedActor.ID, (byte) 0);
                }
                break;
            case 20:
                this.chatMode = false;
                this.left = this.cmdmenu;
                this.right = null;
                this.center = null;
                break;
            case 21:
                doChat();
                break;
            case 22:
                GameService.gI().doCreateParty((byte) 1, this.focusedActor.ID, (short) -1, "");
                break;
            case 23:
                GameService.gI().dosendThachdau((byte) 0, this.focusedActor.ID);
                break;
            case 24:
                GameService.gI().Clan((byte) 1, this.focusedActor.ID);
                break;
            case 72:
                GameCanvas.endDlg();
                if (GameCanvas.currentScreen != MenuLogin.gI()) {
                    Session_ME.gI().close();
                    GameCanvas.gameScr.clearloadMap();
                    removeAllchat();
                    MenuLogin.gI().switchToMe();
                }
                break;
            case 73:
                this.cmdDisconect = null;
                GameCanvas.endDlg();
                Session_ME.gI().close();
                GameCanvas.gameScr.clearloadMap();
                removeAllchat();
                MenuLogin.gI().switchToMe();
                this.isDis = false;
                break;
            case 106:
                GameCanvas.endDlg();
                Res.resetImgMonsTemp();
                isIntro = false;
                MainChar.blockkey = false;
                MenuLogin.gI().switchToMe();
                break;
            case 107:
                GameCanvas.endDlg();
                isIntro = false;
                MainChar.blockkey = false;
                Session_ME.gI().close();
                Res.resetImgMonsTemp();
                MenuLogin.gI().switchToMe();
                break;
            case 108:
                GameService.gI().doHuybanHang();
                GameCanvas.endDlg();
                break;
        }
    }


    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
        String inFo = null;
        switch (idAction) {
            case 90:
                inFo = (String) paint;
                GameData.paintIcon(g, Short.parseShort(inFo), x, y);
            default:
        }
    }

    private void doActorMove() {
        if (charcountdonw == null) {
            if (mainChar.mcanmove()) {
                if (GameCanvas.currentDialog == null) {
                    if (!this.chatMode) {
                        if (!GameCanvas.menu2.isShow) {
                            if (mainChar.state != 3) {
                                if (!isIntro || mainChar.posTransRoad == null) {
                                    if (!MainChar.blockkey) {
                                        boolean changeDirection = false;
                                        if (GameCanvas.isKeyPressed(2)) {
                                            changeDirection = true;
                                            this.dir = 1;
                                        }

                                        if (GameCanvas.isKeyPressed(4)) {
                                            changeDirection = true;
                                            this.dir = 2;
                                        }

                                        if (GameCanvas.isKeyPressed(6)) {
                                            changeDirection = true;
                                            this.dir = 3;
                                        }

                                        if (GameCanvas.isKeyPressed(8)) {
                                            changeDirection = true;
                                            this.dir = 4;
                                        }

                                        boolean move = false;
                                        boolean getKeyMove = false;
                                        int oldx = mainChar.x;
                                        int oldy = mainChar.y;
                                        int olddir = mainChar.dir;
                                        int xx = 0;
                                        int yy = 0;
                                        int v = mainChar.speed;
                                        if (GameCanvas.keyHold[2]) {
                                            if (mainChar.isNPC()) {
                                                this.doHuyBanHang();
                                                return;
                                            }

                                            this.beginAuto = false;
                                            this.removeFocusWhenPutKey();
                                            autoFight = false;
                                            yy = -16;
                                            move = true;
                                            getKeyMove = true;
                                            mainChar.dir = 1;
                                            this.moveToX = mainChar.x;
                                            this.moveToY = (short) (mainChar.y - v);
                                            mainChar.posTransRoad = null;
                                            if (this.moveToY <= 0) {
                                                changeDirection = false;
                                                this.moveToY = mainChar.y;
                                                return;
                                            }

                                            try {
                                                if (Tilemap.tileTypeAtPixel(this.moveToX, this.moveToY, 2)) {
                                                    this.moveToY = mainChar.y;
                                                    if (mainChar.setWay(0, -8)) {
                                                        this.changeSinceLastUpdate = true;
                                                        mainChar.comeHome = false;
                                                        return;
                                                    }

                                                    changeDirection = false;
                                                    move = false;
                                                }
                                            } catch (Exception var14) {
                                                changeDirection = false;
                                                this.moveToY = mainChar.y;
                                                var14.printStackTrace();
                                            }
                                        } else if (GameCanvas.keyHold[8]) {
                                            if (mainChar.isNPC()) {
                                                this.doHuyBanHang();
                                                return;
                                            }

                                            this.beginAuto = false;
                                            this.removeFocusWhenPutKey();
                                            autoFight = false;
                                            yy = 16;
                                            move = true;
                                            getKeyMove = true;
                                            mainChar.dir = 0;
                                            this.moveToX = mainChar.x;
                                            mainChar.posTransRoad = null;
                                            this.moveToY = (short) (mainChar.y + v);
                                            if (this.moveToY > Tilemap.h * 16) {
                                                changeDirection = false;
                                                this.moveToY = mainChar.y;
                                                return;
                                            }

                                            try {
                                                if (Tilemap.tileTypeAtPixel(this.moveToX, this.moveToY, 2)) {
                                                    this.moveToY = mainChar.y;
                                                    if (mainChar.setWay(0, 8)) {
                                                        this.changeSinceLastUpdate = true;
                                                        mainChar.comeHome = false;
                                                        return;
                                                    }

                                                    changeDirection = false;
                                                    move = false;
                                                }
                                            } catch (Exception var13) {
                                                changeDirection = false;
                                                this.moveToY = mainChar.y;
                                                var13.printStackTrace();
                                            }
                                        } else if (GameCanvas.keyHold[4]) {
                                            if (mainChar.isNPC()) {
                                                this.doHuyBanHang();
                                                return;
                                            }

                                            this.beginAuto = false;
                                            this.removeFocusWhenPutKey();
                                            autoFight = false;
                                            xx = -16;
                                            move = true;
                                            getKeyMove = true;
                                            mainChar.dir = 2;
                                            this.moveToX = (short) (mainChar.x - v);
                                            this.moveToY = mainChar.y;
                                            mainChar.posTransRoad = null;
                                            if (this.moveToX <= 0) {
                                                changeDirection = false;
                                                this.moveToX = mainChar.x;
                                                return;
                                            }

                                            try {
                                                if (Tilemap.tileTypeAtPixel(this.moveToX, this.moveToY, 2)) {
                                                    this.moveToX = mainChar.x;
                                                    if (mainChar.setWay(-8, 0)) {
                                                        this.changeSinceLastUpdate = true;
                                                        mainChar.comeHome = false;
                                                        return;
                                                    }

                                                    changeDirection = false;
                                                    move = false;
                                                }
                                            } catch (Exception var12) {
                                                changeDirection = false;
                                                this.moveToX = mainChar.x;
                                                var12.printStackTrace();
                                            }
                                        } else if (GameCanvas.keyHold[6]) {
                                            if (mainChar.isNPC()) {
                                                this.doHuyBanHang();
                                                return;
                                            }

                                            this.beginAuto = false;
                                            this.removeFocusWhenPutKey();
                                            autoFight = false;
                                            xx = 16;
                                            move = true;
                                            getKeyMove = true;
                                            mainChar.dir = 3;
                                            this.moveToY = mainChar.y;
                                            mainChar.posTransRoad = null;
                                            this.moveToX = (short) (mainChar.x + v);
                                            if (this.moveToX >= Tilemap.w * 16) {
                                                changeDirection = false;
                                                this.moveToX = mainChar.x;
                                                return;
                                            }

                                            try {
                                                if (Tilemap.tileTypeAtPixel(this.moveToX, this.moveToY, 2)) {
                                                    this.moveToX = mainChar.x;
                                                    if (mainChar.setWay(8, 0)) {
                                                        this.changeSinceLastUpdate = true;
                                                        mainChar.comeHome = false;
                                                        return;
                                                    }

                                                    changeDirection = false;
                                                    move = false;
                                                }
                                            } catch (Exception var11) {
                                                changeDirection = false;
                                                this.moveToX = mainChar.x;
                                                var11.printStackTrace();
                                            }
                                        }

                                        if (!mainChar.beStune) {
                                            if (!getKeyMove || !this.checkCanChangeMap(oldx + xx, oldy + yy, olddir)) {
                                                if (move) {
                                                    mainChar.sendMove = false;
                                                    if (this.moveToX < 0) {
                                                        this.moveToX = mainChar.x;
                                                    }

                                                    if (this.moveToY < 0) {
                                                        this.moveToY = mainChar.y;
                                                    }

                                                    if (this.moveToX > Tilemap.w * 16) {
                                                        this.moveToX = mainChar.x;
                                                    }

                                                    if (this.moveToY > Tilemap.h * 16) {
                                                        this.moveToX = mainChar.y;
                                                    }

                                                    mainChar.moveTo(this.moveToX, this.moveToY);
                                                    if (saveAutoFight && typeOptionFocus == 1) {
                                                        mainChar.lastXAuto = this.moveToX;
                                                        mainChar.lastYAuto = this.moveToY;
                                                    }

                                                    this.changeSinceLastUpdate = true;
                                                    mainChar.comeHome = false;
                                                    ++this.countStep;
                                                }

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void findAllmonster() {
        for (int i = 0; i < this.actors.size(); ++i) {
            Actor mst = (Actor) this.actors.elementAt(i);
            if (mst.catagory == 1) {
                this.vMonster.addElement(mst);
            }
        }

    }

    public static Actor CreateActor(byte catagory, short type, short id, short x, short y, byte typePK) {
        Actor ac = null;
        if (catagory == 0) {
            ac = new Char();
            ac.setTypePK(typePK);
            GameService.gI().requestCharInfo(id);
            if (type == -1) {
                ac.setDir((byte) Res.random(3));
            }
        } else if (catagory == 1) {
            String key = String.valueOf(type);
            Object _idBoss = ID_BOSS.get((Object) key);
            if (_idBoss != null) {
                if (type == 91) {
                    ac = new BossTruongDoTe(type);
                } else if (type == 101) {
                    ac = new BossBienBucCongTu(type);
                }
                if (type == 74) {
                    ac = new Boss_6x(type);
                }
                ac = type == 139 ? new Boss_Nguoi_Tuyet(type) : new BossGame(type);
            } else {
                ac = new Monster(type);
            }
            GameService.gI().requestMonsterInfo(id);
            ac.setTypePK(typePK);
        } else if (catagory > 2) {
            ac = new Item();
            ac.setTimelive(mSystem.currentTimeMillis() + (long) (timeremoveItem * 1000));
            ((Item) ac).idIcon = type;
            ac.catagory = catagory;
            GameService.gI().requestItemInfo(id);
        }
        if (ac != null) {
            ac.catagory = catagory;
            ac.ID = id;
            ac.x = x;
            ac.y = y;
        }
        return ac;
    }

    public void onMonsterTemplateOffline() {
        try {
            short nmonster = 78;

            for (int i = 0; i < nmonster; ++i) {
                String name = "Quai 1";
                short idtemplate = (short) i;
                byte typemove = 0;
                MonsterTemplate monsterTemplate = new MonsterTemplate(idtemplate, name, typemove, i);
                MonsterTemplate.ALL_MONSTER_TEMPLATE.put("" + idtemplate, monsterTemplate);
                MonsterTemplate.MONSTER.add(monsterTemplate);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }

    public void createMonsterOffline() {
        this.actors.removeAllElements();
        this.onMonsterTemplateOffline();

        int i;
        Monster mons;
        for (i = 0; i < postLoginX.length; ++i) {
            mons = new Monster(1);
            mons.catagory = 1;
            mons.ID = (short) i;
            mons.x = (short) postLoginX[i];
            mons.y = (short) postLoginY[i];
            mons.xFirst = (short) postLoginX[i];
            mons.yFirst = (short) postLoginY[i];
            mons.saveXfirst = (short) postLoginX[i];
            mons.saveYfirst = (short) postLoginY[i];
            mons.hp = 2500;
            mons.level = 10;
            mons.he = 0;
            mons.maxhp = 2500;
            mons.timeLive = 5000;
            mons.height = 30;
            mons.name = "Mons " + i;
            mons.speed = (short) Res.random(3, 5);
            mons.setCanrevives(false);
            GameCanvas.gameScr.actors.addElement(mons);
        }

        for (i = 0; i < 5; ++i) {
            mons = new Monster(1);
            mons.catagory = 1;
            mons.ID = (short) i;
            mons.x = (short) (this.xLogin[i] + 688);
            mons.y = (short) (this.yLogin[i] + 96);
            mons.xFirst = (short) (this.xLogin[i] + 672);
            mons.yFirst = (short) (this.yLogin[i] + 90);
            mons.saveXfirst = (short) (this.xLogin[i] + 688);
            mons.saveYfirst = (short) (this.yLogin[i] + 96);
            mons.hp = 2500;
            mons.level = 10;
            mons.he = 0;
            mons.maxhp = 2500;
            mons.timeLive = 5000;
            mons.height = 30;
            mons.name = "Mons " + i;
            mons.speed = (short) Res.random(3, 5);
            mons.setCanrevives(false);
            GameCanvas.gameScr.actors.addElement(mons);
        }

    }

    public void onMapOffline(int idMapload, int xc, int yc) {
        isIntro = true;
        CountIntro = 0;
        fireIntro = false;
        CountMoveFirst = 0;
        GameCanvas.instance.loadSize();
        this.onMainCharInfoOffline();
        this.onCharWearing();
        this.actors.removeAllElements();
        vecCharintro.removeAllElements();
        nameMap = "";
        this.focusedActor = null;
        mainChar.x = 0;
        mainChar.y = 217;
        mainChar.xTo = mainChar.x;
        mainChar.yTo = mainChar.y;
        MainChar.blockkey = true;
        canmove = false;
        allLocation = null;
        nameMap = "Demo";
        mainChar.clazz = 3;
        mainChar.setInfoWearing(Char.idPartTest[mainChar.clazz][0]);
        GameData.removeAllImgTree();
        Tilemap.loadMap(idMapload, (byte[]) null);
        this.actors.addElement(mainChar);
        this.onMonsterTemplateOffline();

        for (int i = 0; i < 5; ++i) {
            Monster mons = new Monster(54);
            mons.catagory = 1;
            mons.ID = (short) i;
            int xss = 0;
            mons.x = (short) (this.xit_mons[i] - 100 - xss);
            mons.y = (short) (this.yit[i] - 35);
            mons.xFirst = (short) (this.xit_mons[i] - 100 - xss);
            mons.yFirst = (short) (this.yit[i] - 35);
            mons.saveXfirst = (short) (this.xit_mons[i] - 100 - xss);
            mons.saveYfirst = (short) (this.yit[i] - 35 - xss);
            mons.hp = 2500;
            mons.level = 10;
            mons.he = 0;
            mons.maxhp = 2500;
            mons.timeLive = 5000;
            mons.height = 30;
            mons.name = "Mons " + i;
            mons.speed = (short) Res.random(3, 5);
            mons.setCanrevives(false);
            GameCanvas.gameScr.actors.addElement(mons);
        }

        GameCanvas.instance.loadSize();
        GameCanvas.gameScr.loadCamera();
        GameCanvas.gameScr.switchToMe();
    }

    public static Monster createBossOffline() {
        Monster mons = new Monster(72);
        mons.catagory = 1;
        mons.ID = 0;
        mons.x = 950;
        mons.y = 248;
        mons.xFirst = 950;
        mons.yFirst = 275;
        mons.saveXfirst = 700;
        mons.saveYfirst = 275;
        mons.hp = 500000;
        mons.level = 10;
        mons.he = 0;
        mons.maxhp = 500000;
        mons.timeLive = 500000;
        mons.height = 30;
        mons.name = T.namehungcanh;
        mons.isbossOffline = true;
        return mons;
    }

    public void createCharIntro() {
        MainChar chartest = null;

        for (short i = 0; i < 5; ++i) {
            chartest = new MainChar();
            chartest.ID = i;
            chartest.name = nameClazz[i];
            chartest.hp = 100;
            chartest.maxhp = 100;
            chartest.mp = 100;
            chartest.maxmp = 100;
            chartest.x = 0;
            chartest.y = (short) (150 + yyy[i]);
            chartest.clazz = idCharClazz[i];
            chartest.moveTo((short) (260 + xxx[i]), chartest.y);
            chartest.setInfoWearing(Char.idPartTest[chartest.clazz][0]);
            this.actors.addElement(chartest);
            vecCharintro.addElement(chartest);
            chartest = null;
        }

        mainChar.moveTo((short) 288, (short) 217);
    }

    public void createCharLogin() {
        this.actors.removeAllElements();
        vecCharintro.removeAllElements();
        this.charlogin.removeAllElements();
        MainChar chartest = null;

        for (short i = 0; i < 5; ++i) {
            chartest = new MainChar();
            chartest.ID = i;
            chartest.name = nameClazz[i];
            chartest.hp = 100;
            chartest.maxhp = 100;
            chartest.mp = 100;
            chartest.maxmp = 100;
            chartest.x = (short) postCharLoginX[i];
            chartest.y = (short) postCharLoginY[i];
            chartest.clazz = idCharClazz[i];
            chartest.speed = 7;
            chartest.setInfoWearing(Char.idPartTest[chartest.clazz][0]);
            this.actors.addElement(chartest);
            vecCharintro.addElement(chartest);
            chartest = null;
        }

    }

    public void doAttackChartest() {
        boolean haveMonster = false;
        MainChar.blockkey = false;

        int i;
        Actor ac;
        for (i = 0; i < this.actors.size(); ++i) {
            ac = (Actor) this.actors.elementAt(i);
            if (ac != null && ac.catagory == 1 && ac.getState() != 5 && ac.getState() != 8) {
                haveMonster = true;
                break;
            }
        }

        if (!haveMonster) {
            this.tickwait = 15;
            ++steep;
            fireIntro = false;
        }

        if (tam.size() <= 0) {
            for (i = 0; i < vecCharintro.size(); ++i) {
                ac = (Actor) vecCharintro.elementAt(i);
                tam.addElement(ac);
                ac.startAttack(this.getTarget(getidSkill(ac.getClazz())), getidSkill(ac.getClazz()));
            }
        }

        if (this.wait >= 0) {
            --this.wait;
        } else {
            if (tam.size() > 0) {
                for (i = 0; i < tam.size(); ++i) {
                    ac = (Actor) tam.elementAt(i);
                    if (ac.getState() == 0 && ac != null && ac.getCoolDown() <= 0L && ac.getState() != 3) {
                        mVector tg = this.getTarget(getidSkill(ac.getClazz()), ac);
                        Actor mons = null;
                        int j;
                        if (tg.size() > 1) {
                            for (j = 0; j < tg.size(); ++j) {
                                int min = 1000000;
                                Actor mons1 = (Actor) tg.elementAt(j);
                                if (mons1 != null && Res.inRangeActor(ac, mons1, min)) {
                                    Res.getRange(ac, mons1);
                                    mons = mons1;
                                }
                            }
                        } else {
                            mons = (Actor) tg.elementAt(0);
                        }

                        if (mons != null) {
                            if (Util.distance(ac.x, ac.y, mons.x, mons.y) > 90) {
                                if (Bossintro == null) {
                                    this.wait = 10;
                                }

                                ac.moveTo(mons.x, mons.y);
                                return;
                            }

                            Actor mac;
                            if (Bossintro != null) {
                                if (r.random(0, 10) < 5) {
                                    ac.setDir((byte) Util.findDirection(ac, mons));
                                    ac.startAttack(tg, getidSkill(ac.getClazz()));
                                    ac.setCoolDown(2);

                                    for (j = 0; j < tg.size(); ++j) {
                                        mac = (Actor) tg.elementAt(j);
                                        if (mac != null) {
                                            mac.DropHP(100);
                                        }
                                    }
                                } else {
                                    if (Bossintro == null) {
                                        ac.moveTo((short) (mons.x + r.random(20, 25) * (r.random(0, 2) == 0 ? 1 : -1)), (short) (mons.y + r.random(10, 30) * (r.random(0, 2) == 0 ? 1 : -1)));
                                    } else {
                                        ac.moveTo((short) (mons.x + this.possIntroX[Res.random(this.possIntroX.length - 1)]), (short) (mons.y + this.possIntroY[Res.random(this.possIntroY.length - 1)]));
                                    }

                                    ac.setCoolDown(1);
                                }
                            } else {
                                ac.setDir((byte) Util.findDirection(ac, mons));
                                ac.startAttack(tg, getidSkill(ac.getClazz()));
                                ac.setCoolDown(r.random(0, 6));

                                for (j = 0; j < tg.size(); ++j) {
                                    mac = (Actor) tg.elementAt(j);
                                    if (mac != null) {
                                        mac.DropHP(5);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    public void doAttackLogin() {
        if (GameCanvas.gameTick % 50 == 0) {
            Actor ac = null;
            int j;
            if (this.charlogin.size() == 0) {
                for (j = 0; j < this.actors.size(); ++j) {
                    ac = (Actor) this.actors.elementAt(j);
                    if (ac != null && ac.catagory == 0) {
                        this.charlogin.addElement(ac);
                    }
                }
            }

            for (j = 0; j < this.charlogin.size(); ++j) {
                ac = (Actor) this.charlogin.elementAt(j);
                if (ac != null) {
                    mVector mtg = new mVector();

                    for (int i = 0; i < this.actors.size(); ++i) {
                        Actor mons = (Actor) this.actors.elementAt(i);
                        if (mons != null && mons.catagory == 1 && Util.distance(ac.x, ac.y, mons.x, mons.y) <= 120) {
                            mtg.addElement(mons);
                            break;
                        }
                    }

                    if (mtg.size() > 0) {
                        ac.startAttack(mtg, getidSkill(ac.getClazz()));
                    }
                }
            }
        }

    }

    public mVector getTarget(int id) {
        mVector mons = new mVector();

        for (int i = 0; i < this.actors.size(); ++i) {
            Actor mac = (Actor) this.actors.elementAt(i);
            if (mac != null && mac.getHp() > 0 && mac.catagory == 1 && mac.getState() != 5 && mac.getState() != 8 && mac.getState() != 1) {
                mons.addElement(mac);
                if (id == 0) {
                    return mons;
                }
            }
        }

        return mons;
    }

    public mVector getTarget(int id, Actor from) {
        mVector mons = new mVector();
        if (Bossintro != null) {
            mons.addElement(Bossintro);
            return mons;
        } else {
            if (id == 0) {
                Actor mons_ad = null;

                for (int i = 0; i < this.actors.size(); ++i) {
                    Actor mac = (Actor) this.actors.elementAt(i);
                    if (mac != null && mac.getHp() > 0 && mac.catagory == 1 && mac.getState() != 5 && mac.getState() != 8 && mac.getState() != 1) {
                        int min = 1000000;
                        if (Res.inRangeActor(from, mac, min)) {
                            Res.getRange(from, mac);
                            mons_ad = mac;
                        }
                    }
                }

                if (mons_ad != null) {
                    mons.addElement(mons_ad);
                    return mons;
                }
            } else {
                for (int i = 0; i < this.actors.size(); ++i) {
                    Actor mac = (Actor) this.actors.elementAt(i);
                    if (mac != null && mac.getHp() > 0 && mac.catagory == 1 && mac.getState() != 5 && mac.getState() != 8 && mac.getState() != 1) {
                        mons.addElement(mac);
                    }
                }
            }

            return mons;
        }
    }

    public static int getidSkill(int clazz) {
        return arrSkill[clazz][r.nextInt(arrSkill[clazz].length)];
    }

    public void doTalk() {
        Actor ac;
        switch (SteepCount) {
            case 0:
                if (clazz <= 5) {
                    Actor actor = (Actor) vecCharintro.elementAt(clazz);
                    if (actor != null) {
                        addChat(actor, T.Texintro[actor.getClazz()][Next], 30);
                        if (clazz == 0 || clazz == 1 || clazz == 2 || clazz == 3)
                            Next++;
                        if (clazz == 4)
                            clazz++;
                        timepopup = 30;
                        if (actor.getClazz() == Char.CAI_BANG && Next == 5) {
                            clazz++;
                            Next = 0;
                        }
                        if (actor.getClazz() == Char.THIEU_LAM && Next == 2) {
                            clazz++;
                            Next = 0;
                        }
                        if (actor.getClazz() == Char.NGA_MI && Next == 2) {
                            clazz++;
                            Next = 0;
                        }
                        if (actor.getClazz() == Char.VO_DANG && Next == 2) {
                            clazz++;
                            Next = 0;
                        }
                    }
                }
                if (clazz == 5 && timepopup <= 0) {
                    clazz++;
                    timepopup = 3;
                    SteepCount++;
                    findRoad(666, 168);
                    for (int i = 0; i < vecCharintro.size(); i++) {
                        Actor actor = (Actor) vecCharintro.elementAt(i);
                        if (actor != null) {
                            addChat(actor, T.di, 40);
                            finishTalk = true;
                        }
                    }
                }
                break;
            case 1:
                clazz = 0;
                Next = 0;
                moveAndfire();
                canmove = true;
                break;
            case 2:
                ac = (Actor) vecCharintro.elementAt(clazz);
                if (ac != null) {
                    addChat(ac, T.talkwhenfight[ac.getClazz()][0], 20);
                    timepopup = 20;
                    if (clazz == 4)
                        addChat((Actor) Bossintro, T.hungcanh[1], 10);
                    clazz++;
                    if (clazz == 5)
                        addChat((Actor) Bossintro, T.hungcanh[3], 10);
                }
                break;
        }
    }

    public void moveAndfire() {
        if (!ismovefirst) {
            for (int j = 0; j < vecCharintro.size(); j++) {
                Actor ac = (Actor) vecCharintro.elementAt(j);
                int index = 0;
                if (j % 2 == 0) {
                    index = 0;
                } else {
                    index = 1;
                }
                ac.moveTo(ToxFirst[index], ToyFirst[index]);
            }
            ismovefirst = true;
        }
        if (ismovefirst && co <= 0) {
            for (int j = 0; j < vecCharintro.size(); j++) {
                Actor ac = (Actor) vecCharintro.elementAt(j);
                if ((ac.x == ToxFirst[0] || ac.x == ToxFirst[1]) && (ac.y == ToyFirst[0] || ac.y == ToyFirst[1])) {
                    int index = 3;
                    if (j % 2 == 0)
                        index = 2;
                    ac.moveTo(ToxFirst[index], ToyFirst[index]);
                }
            }
            canfire = true;
        }
        if (canfire) {
            mVector target = getTargetFirst();
            for (int j = 0; j < vecCharintro.size(); j++) {
                Actor ac = (Actor) vecCharintro.elementAt(j);
                if ((ac.x == ToxFirst[2] || ac.x == ToxFirst[3]) && (ac.y == ToyFirst[2] || ac.y == ToyFirst[3])) {
                    ac.startAttack(target, 0, -1);
                    co++;
                }
            }
        }
        if (co == 5)
            fireIntro = true;
    }

    public void onMainCharInfoOffline() {
        try {
            mainChar.ID = 0;
            mainChar.name = "anhhung";
            mainChar.hp = 100;
            mainChar.maxhp = 100;
            mainChar.mp = 100;
            mainChar.maxmp = 100;
            MainChar.loadQuickSlot();
        } catch (Exception var2) {
        }

    }

    public void onCharWearing() {
        try {
            short id = 0;
            Actor ac = this.findActor(id, 0);
            if (id == mainChar.ID) {
                ac = mainChar;
            }

            if (ac != null) {
                short[] listpart = new short[]{0, 0, 0, -1, -1, -1};
                ((Actor) ac).setInfoWearing(listpart);
            }
        } catch (Exception var4) {
        }

    }

    public Actor findActor(short id, int cat) {
        for (int i = 0; i < this.actors.size(); ++i) {
            Actor ac = (Actor) this.actors.elementAt(i);
            if (ac.ID == id && ac.catagory == cat) {
                return ac;
            }
        }

        return null;
    }

    public boolean isGameScreen() {
        return true;
    }

    public static void addEffectByDir(int dir, int clazz, Actor mychar) {
        if (dir == 1) {
            if (clazz == Char.NGA_MI || clazz == Char.VO_DANG || clazz == Char.THIEU_LAM) {
                EffectSkill.createHiEfect(mychar.x, mychar.y - 60 - mychar.getYfly(), 76);
            }

            if (clazz == Char.NGU_DOC) {
                EffectSkill.createHiEfect(mychar.x, mychar.y - 70 - mychar.getYfly(), 77);
            }

            if (clazz == Char.CAI_BANG) {
                EffectSkill.createHiEfect(mychar.x, mychar.y - 70 - mychar.getYfly(), 78);
            }
        } else if (dir == 0) {
            if (clazz == Char.NGA_MI || clazz == Char.VO_DANG || clazz == Char.THIEU_LAM) {
                EffectSkill.createHiEfectWithTransform(mychar.x, mychar.y - mychar.getYfly(), 76, 3);
            }

            if (clazz == Char.NGU_DOC) {
                EffectSkill.createHiEfectWithTransform(mychar.x, mychar.y - mychar.getYfly(), 77, 3);
            }

            if (clazz == Char.CAI_BANG) {
                EffectSkill.createHiEfectWithTransform(mychar.x, mychar.y - mychar.getYfly(), 78, 3);
            }
        } else if (dir == 2) {
            if (clazz == Char.NGA_MI || clazz == Char.VO_DANG || clazz == Char.THIEU_LAM) {
                EffectSkill.createHiEfectWithTransform(mychar.x - 35, mychar.y - 40 - mychar.getYfly(), 76, 6);
            }

            if (clazz == Char.NGU_DOC) {
                EffectSkill.createHiEfectWithTransform(mychar.x - 35, mychar.y - 30 - mychar.getYfly(), 77, 6);
            }

            if (clazz == Char.CAI_BANG) {
                EffectSkill.createHiEfectWithTransform(mychar.x - 35, mychar.y - 40 - mychar.getYfly(), 78, 6);
            }
        } else if (dir == 3) {
            if (clazz == Char.NGA_MI || clazz == Char.VO_DANG || clazz == Char.THIEU_LAM) {
                EffectSkill.createHiEfectWithTransform(mychar.x + 25, mychar.y - 40 - mychar.getYfly(), 76, 5);
            }

            if (clazz == Char.NGU_DOC) {
                EffectSkill.createHiEfectWithTransform(mychar.x + 35, mychar.y - 30 - mychar.getYfly(), 77, 5);
            }

            if (clazz == Char.CAI_BANG) {
                EffectSkill.createHiEfectWithTransform(mychar.x + 25, mychar.y - 40 - mychar.getYfly(), 78, 5);
            }
        }

    }

    public void findNearTarget() {
        if (!isGost) {
            int min = 100000000;
            int index = -1;

            for (int i = 0; i < this.actors.size(); ++i) {
                Actor ac = (Actor) this.actors.elementAt(i);
                if (ac != null && ac.catagory == 1 && ac.getTypeMove() != 5) {
                    int dist = Utils.getDistance(mainChar, ac);
                    if (dist <= 160 && dist < min) {
                        min = dist;
                        index = i;
                    }
                }
            }

            if (index != -1) {
                Actor ac1 = (Actor) this.actors.elementAt(index);
                if (!ac1.equals(this.focusedActor) && ac1.catagory == 1 && ac1.canFocusMonster()) {
                    this.focusedActor = ac1;
                }
            }

        }
    }

    public void AutoDanh() {
        if (charcountdonw == null) {
            if (!isIntro) {
                if (!isGost) {
                    if (this.focusedActor != null && this.focusedActor.getTypeMove() == 5) {
                        this.findNearTarget();
                    }

                    if (this.focusedActor != null && !this.focusedActor.beFire() && !mainChar.setFireChar(this.focusedActor)) {
                        this.findNearTarget();
                    }

                    if (this.focusedActor != null && Utils.getDistance(mainChar, this.focusedActor) > 120) {
                        this.findNearTarget();
                    }

                    if (this.focusedActor != null && this.focusedActor.getTypeMove() == 5) {
                        this.findNearTarget();
                    }

                    if (this.focusedActor == null) {
                        this.findNearTarget();
                    }

                    if (mainChar.posTransRoad == null) {
                        if (coolDown - mSystem.currentTimeMillis() <= 0L) {
                            if (this.focusedActor != null && !Res.inRangeActor(mainChar, this.focusedActor, 160)) {
                                this.findNearTarget();
                            } else {
                                if (mainChar.getState() == 0) {
                                    ++this.countAuto;
                                } else {
                                    this.countAuto = 0;
                                }

                                if (mainChar.canFire() && mainChar.canUseSkill()) {
                                    if (this.countAuto > 100) {
                                        this.countAuto = 0;
                                        this.findRoad(this.xSaveAuto, this.ySaveAuto);
                                    }

                                    int rangemove = Utils.getDistance(mainChar.x, mainChar.y, this.xSaveAuto, this.ySaveAuto);
                                    if (rangemove >= 160 && mainChar.posTransRoad == null) {
                                        this.findRoad(this.xSaveAuto, this.ySaveAuto);
                                    }

                                    if (this.focusedActor != null && this.focusedActor.catagory == 1) {
                                        int[] key = new int[]{4, 5, 6, 7, 8, 9};
                                        QuickSlot ql = MainChar.mQuickslot[this.indexKeyTouchAuto];
                                        if (ql.idSkill == -1 || !ql.canfight()) {
                                            this.indexKeyTouchAuto = (this.indexKeyTouchAuto + 1) % 5;
                                            return;
                                        }

                                        if (ql.quickslotType == 1) {
                                            int mkeytouch = GameCanvas.isTouch ? key[this.indexKeyTouchAuto] : this.indexKeyTouchAuto;
                                            if (mSystem.isPC) {
                                                mkeytouch -= 4;
                                            }

                                            SkillTemplate skill = (SkillTemplate) vec_skill.elementAt(ql.getSkillType());
                                            if (skill.type == SkillTemplate.TYPE_ATTACK && !Res.inRangeActor(mainChar, this.focusedActor, skill.range)) {
                                                this.findRoad(this.focusedActor.x, this.focusedActor.y);
                                                if (mainChar.posTransRoad != null) {
                                                    return;
                                                }

                                                if (mainChar.posTransRoad == null) {
                                                    this.findNearTarget();
                                                }
                                            }

                                            this.doTouchQuickSlot(mkeytouch);
                                        }
                                    }
                                }

                            }
                        }
                    }
                }
            }
        }
    }

    public void AutoNhat() {
    }

    public void AutoBomHP(int maxhp) {
        if (mainChar.getState() != 3) {
            if (mainChar.maxhp > 0) {
                if (mainChar.hp < mainChar.maxhp) {
                    if (mainChar.maxhp > 0 && mainChar.hp * 10 / mainChar.maxhp < maxhp && mSystem.currentTimeMillis() - MainChar.timeStartCoolDow_HP > 2000L) {
                        MainChar.timeStartCoolDow_HP = mSystem.currentTimeMillis();

                        int i;
                        for (i = 0; i < MainChar.mQuickslot.length; ++i) {
                            if (MainChar.mQuickslot[i] != null && MainChar.mQuickslot[i].typePotion == Item.TYPE_HP && MainChar.mQuickslot[i].canUsePotion()) {
                                GameService.gI().useItem(MainChar.mQuickslot[i].getidPotion());
                                MainChar.mQuickslot[i].startCoolDown(0);
                                return;
                            }
                        }

                        for (i = 0; i < MainChar.MaxInven; ++i) {
                            if (i < Char.inventory.size()) {
                                Item it = (Item) Char.inventory.elementAt(i);
                                if (it != null && it.type == Item.TYPE_HP) {
                                    GameService.gI().useItem(it.ID);
                                    break;
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    public void AutoBomMP(int maxmp) {
        if (mainChar.getState() != 3) {
            if (mainChar.maxmp > 0) {
                if (mainChar.mp < mainChar.maxmp) {
                    if (mainChar.mp * 10 / mainChar.maxmp < maxmp && mSystem.currentTimeMillis() - MainChar.timeStartCoolDow_MP > 2000L) {
                        MainChar.timeStartCoolDow_MP = mSystem.currentTimeMillis();

                        int i;
                        for (i = 0; i < MainChar.mQuickslot.length; ++i) {
                            if (MainChar.mQuickslot[i] != null && MainChar.mQuickslot[i].typePotion == Item.TYPE_MP && MainChar.mQuickslot[i].canUsePotion()) {
                                GameService.gI().useItem(MainChar.mQuickslot[i].getidPotion());
                                MainChar.mQuickslot[i].startCoolDown(0);
                                return;
                            }
                        }

                        for (i = 0; i < MainChar.MaxInven; ++i) {
                            if (i < Char.inventory.size()) {
                                Item it = (Item) Char.inventory.elementAt(i);
                                if (it.type == Item.TYPE_MP) {
                                    GameService.gI().useItem(it.ID);
                                    break;
                                }
                            }
                        }
                    }

                }
            }
        }
    }

    public boolean keyPress(int keyCode) {
        boolean swallow;
        if (this.tfChat.isFocus && this.chatMode) {
            swallow = this.tfChat.keyPressed(keyCode);
            if (swallow) {
                return true;
            }
        }

        if (this.tfChatWorld.isFocus && this.chatWorld) {
            swallow = this.tfChatWorld.keyPressed(keyCode);
            if (swallow) {
                return true;
            }
        }

        return super.keyPress(keyCode);
    }

    public void AutoBuff() {
        if (charcountdonw == null) {
            if (mainChar.getState() != 3) {
                long now = mSystem.currentTimeMillis();
                if (mainChar.state == 0) {
                    for (int i = 0; i < GameCanvas.menuSelectitem.idSkillBuff.length; ++i) {
                        long times = GameCanvas.menuSelectitem.CoolDownSkillBuff[i] - now;
                        if (times <= 0L) {
                            for (int j = 0; j < MainChar.mQuickslot.length; ++j) {
                                if (MainChar.mQuickslot[j] != null && MainChar.mQuickslot[j].isBuff() && MainChar.mQuickslot[j].idSkill == GameCanvas.menuSelectitem.idSkillBuff[i][0]) {
                                    return;
                                }
                            }

                            if (GameCanvas.menuSelectitem.idSkillBuff[i][0] != -1 && GameCanvas.menuSelectitem.CoolDownSkillBuff[i] - now <= 0L) {
                                if (Char.levelSkill[GameCanvas.menuSelectitem.idSkillBuff[i][0]] > 0) {
                                    SkillTemplate skill = (SkillTemplate) vec_skill.elementAt(GameCanvas.menuSelectitem.idSkillBuff[i][0]);

                                    try {
                                        if (skill.isSkillBuff() && skill.mp[Char.levelSkill[GameCanvas.menuSelectitem.idSkillBuff[i][0]]] <= mainChar.mp) {
                                            GameCanvas.menuSelectitem.CoolDownSkillBuff[i] = now + (long) skill.coolDown[Char.levelSkill[GameCanvas.menuSelectitem.idSkillBuff[i][0]] - 1];
                                            GameCanvas.menuSelectitem.idSkillBuff[i][3] = skill.coolDown[Char.levelSkill[GameCanvas.menuSelectitem.idSkillBuff[i][0]] - 1];
                                            mainChar.startBuff(GameCanvas.menuSelectitem.idSkillBuff[i][1]);
                                            GameService.gI().use_Buff(GameCanvas.menuSelectitem.idSkillBuff[i][0]);
                                            mSystem.println("buff ne eeeeee");
                                        }
                                    } catch (Exception var8) {
                                    }
                                }
                                break;
                            }
                        }
                    }
                }

            }
        }
    }

    public void paintIcon(mGraphics g) {
        if (!isIntro) {
            if (GameCanvas.currentScreen == null || GameCanvas.currentScreen == this) {
                if (this.hideGUI != 2) {
                    if (!GameCanvas.menuSelectitem.showMenu) {
                        if (!GameCanvas.menu.showMenu) {
                            if (!MainChar.blockkey) {
                                if (GameCanvas.currentDialog == null) {
                                    if (!this.chatMode && !this.chatWorld) {
                                        if (!GameCanvas.menu2.isShow) {
                                            int xss = -10;
                                            if (Char.Diemtiemnang > 0 || Char.Skill_Point > 0 && Char.Max_Skill_Learn > 1) {
                                                g.drawImage(imgPlus, 67 + xss + 10, GameCanvas.h - 5 - 4, 0, false);
                                            }

                                            if (GameCanvas.menuSelectitem.isAutoDanh[0]) {
                                                g.drawImage(imgauto, 79 + xss + 8, GameCanvas.h - 5 - 4, 0, false);
                                            }

                                            FontTeam.numberSmall_white.drawString(g, mainChar.x / 16 + "/" + mainChar.y / 16, 34, GameCanvas.h - 5 - 5, 0, false);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void paintShowEvent(mGraphics g) {
        if (this.eventShow != null || hShowEvent > 0) {
            GameCanvas.resetTrans(g);
            int x = GameCanvas.w - wShowEvent - 4;
            int yev = -6 + hShowEvent + 3;
            int hevemt = 35;
            int yt = 0;
            x = 0;
            yev += imghealth[0].getHeight() + 3;
            yt = imghealth[0].getHeight() + 3;
            g.setColor(-11197952);
            g.fillRect(x - 2, yev - hevemt - 2, wShowEvent + 4, hevemt + 4, false);
            g.setColor(-3377408);
            g.fillRect(x - 1, yev - hevemt - 1, wShowEvent + 2, hevemt + 2, false);
            g.setClip(x, yev - hevemt, wShowEvent, hevemt);
            g.ClipRec(x, yev - hevemt, wShowEvent, hevemt);
            for (int i = 0; i < wShowEvent / 32 + 1; i++) {
                for (int j = 0; j < hevemt / 32 + 1; j++)
                    g.drawImage(imgBgMainMenu, x + i * 32, yev - hevemt + j * 32 - 5, 0, true);
            }
            g.restoreCanvas();
            GameCanvas.resetTrans(g);
            g.setColor(-14194607);
            g.drawRegion(imgBoder[1], 0, 0, 8, 8, 7, x + wShowEvent, yev, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            g.drawRegion(imgBoder[1], 0, 0, 8, 8, 1, x, yev, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(imgBoder[1], 0, 0, 8, 8, 0, x, yev - hevemt, mGraphics.TOP | mGraphics.LEFT, false);
            g.drawRegion(imgBoder[1], 0, 0, 8, 8, 2, x + wShowEvent, yev - hevemt, mGraphics.TOP | mGraphics.RIGHT, false);
            g.drawImage(imgCheckevent, x + wShowEvent - 8 - 5, yev - 17, 3, false);
            if (this.eventShow != null)
                if (mSystem.isAndroid) {
                    mFont.mfont_tile_Android.drawString(g, this.eventShow.nameEvent,
                            x + 8, -35 + hShowEvent + 3 + yt, 0, false);
                    mFont.mfont_tile_Android.drawString(g, this.eventShow.contentEvent,
                            x + 8, -35 + hShowEvent + 13 + 3 + yt, 0, false);
                } else {
                    FontTeam.fontTile.drawString(g, this.eventShow.nameEvent,
                            x + 8, -35 + hShowEvent + 3 + yt, 0, false);
                    FontTeam.fontTile.drawString(g, this.eventShow.contentEvent,
                            x + 8, -35 + hShowEvent + 13 + 3 + yt, 0, false);
                }
        }
    }


    public void updateEvent() {
        if (EventScreen.vecEventShow.size() > 0) {
            if (this.eventShow == null) {
                this.eventShow = (MainEvent) EventScreen.vecEventShow.elementAt(0);
                timeEvent = 130;
                hShowEvent = 0;
            } else {
                --timeEvent;
                if (timeEvent <= 0) {
                    this.eventShow = null;
                    EventScreen.vecEventShow.removeElementAt(0);
                }

                if (hShowEvent < 35) {
                    hShowEvent += 10;
                }

                if (hShowEvent > 41) {
                    hShowEvent = 41;
                }
            }

            int x = GameCanvas.w - wShowEvent - 2;
            int yev = -6 + hShowEvent;
            int hevemt = 35;
            x = 0;
            yev += imghealth[0].getHeight() + 3;
            if (GameCanvas.isPointer(x, yev - hevemt, wShowEvent, hevemt, 0) && this.eventShow != null) {
                MainEvent ev = EventScreen.setEvent(this.eventShow.nameEvent, (byte) this.eventShow.IDCmd);
                if (ev != null) {
                    GameCanvas.mevent.doEvent(false, ev);
                }

                if (timeEvent > 40) {
                    timeEvent = 40;
                }

                GameCanvas.isPointerJustRelease[0] = false;
            }

            if (GameCanvas.isKeyPressed(9)) {
                GameCanvas.clearKeyHold(9);
                int t = EventScreen.setIndexEvent(this.eventShow.nameEvent, (byte) this.eventShow.IDCmd);
                if (t >= 0) {
                    GameCanvas.mevent.idSelect = t;
                }

                GameCanvas.mevent.init();
                GameCanvas.mevent.switchToMe(this);
            }
        } else if (hShowEvent > 0) {
            hShowEvent -= 20;
        }

    }

    public boolean canParty() {
        if (Tilemap.ismapLang) {
            return true;
        } else if (mainChar.typePK == 6) {
            return true;
        } else if (mainChar.typePK == -1 && this.focusedActor.getTypePK() != 0 && this.focusedActor.getTypePK() != 6) {
            return true;
        } else {
            return mainChar.typePK != -1 && mainChar.typePK != 0 && mainChar.typePK != 6 && (mainChar.typePK == this.focusedActor.getTypePK() || this.focusedActor.getTypePK() == -1);
        }
    }

    public static void checkQuest() {
        isQuest = false;

        for (int i = 0; i < MainMenu.ListQuest.length; ++i) {
            for (int j = 0; j < MainMenu.ListQuest[i].size(); ++j) {
                QuestInfo q = (QuestInfo) MainMenu.ListQuest[i].elementAt(j);
                if (q != null && (q.status == 1 || q.status == 2 || q.status == 4)) {
                    isQuest = true;
                    String[] data = Util.split(q.info, "\n");
                    textQuest = null;
                    if (data.length > 7) {
                        textQuest = mFont.name_Black.splitFontArray(data[7], 110);
                    } else {
                        textQuest = mFont.name_Black.splitFontArray(q.info, 110);
                    }

                    return;
                }
            }
        }

    }

    public void QuestAgain() {
        if (this.focusedActor != null) {
            mVector menu;
            int i;
            Quest q;
            SetInfoData s12;
            mCommand cmd;
            if (this.focusedActor.getStateQuest() == 0 && allQuestCanReceive.size() > 0) {
                menu = new mVector();

                for (i = 0; i < allQuestCanReceive.size(); ++i) {
                    q = (Quest) allQuestCanReceive.elementAt(i);
                    if (q != null && q.idNpcReceive == this.focusedActor.getIDNpc()) {
                        s12 = new SetInfoData();
                        s12.stateQuest = this.focusedActor.getStateQuest();
                        s12.idIcon = this.focusedActor.getIDicon();
                        s12.ID = this.focusedActor.getIDNpc();
                        s12.idQuest = i;
                        cmd = new mCommand(q.name, this, 12, s12);
                        menu.addElement(cmd);
                    }
                }

                GameCanvas.menu.startAt_MenuOption(menu, -1, -1, this.focusedActor.getStrTalk(), this.focusedActor.getIDicon());
            }

            if (this.focusedActor.getStateQuest() == 2 && allQuestWorking.size() > 0) {
                menu = new mVector();

                for (i = 0; i < allQuestWorking.size(); ++i) {
                    q = (Quest) allQuestWorking.elementAt(i);
                    if (q != null && q.idNpcResponse == this.focusedActor.getIDNpc()) {
                        s12 = new SetInfoData();
                        s12.stateQuest = this.focusedActor.getStateQuest();
                        s12.idIcon = this.focusedActor.getIDicon();
                        s12.idQuest = i;
                        s12.ID = this.focusedActor.getIDNpc();
                        cmd = new mCommand(q.name, this, 12, s12);
                        menu.addElement(cmd);
                    }
                }

                GameCanvas.menu.startAt_MenuOption(menu, -1, -1, this.focusedActor.getStrTalk(), this.focusedActor.getIDicon());
            }

            if (this.focusedActor.getStateQuest() == 1 && allQuestFinish.size() > 0) {
                menu = new mVector();

                for (i = 0; i < allQuestFinish.size(); ++i) {
                    q = (Quest) allQuestFinish.elementAt(i);
                    if (q != null && q.idNpcResponse == this.focusedActor.getIDNpc()) {
                        s12 = new SetInfoData();
                        s12.stateQuest = this.focusedActor.getStateQuest();
                        s12.idIcon = this.focusedActor.getIDicon();
                        s12.idQuest = i;
                        s12.ID = this.focusedActor.getIDNpc();
                        cmd = new mCommand(q.name, this, 12, s12);
                        menu.addElement(cmd);
                    }
                }

                GameCanvas.menu.startAt_MenuOption(menu, -1, -1, this.focusedActor.getStrTalk(), this.focusedActor.getIDicon());
            }
        }

    }

    public boolean isNPCshop(short id) {
        if (this.idNPC_Sell != null) {
            for (int i = 0; i < this.idNPC_Sell.length; ++i) {
                if (id == this.idNPC_Sell[i]) {
                    return true;
                }
            }
        }

        return false;
    }

    public void clearloadMap() {
        isnextmap = false;
        Bossintro = null;
        isIntro = false;
        MainChar.blockkey = false;
        if (vecCharintro != null) {
            vecCharintro.removeAllElements();
        }

        GameCanvas.clearKeyHold();
        MonsterTemplate.ALL_DATE_NEW_MONSTER.clear();
        GameCanvas.currentDialog = null;
        if (GameCanvas.gameScr.actors != null) {
            GameCanvas.gameScr.actors.removeAllElements();
        }

        if (EffectManager.hiEffects != null) {
            EffectManager.hiEffects.removeAllElements();
        }

        if (EffectManager.lowEffects != null) {
            EffectManager.lowEffects.removeAllElements();
        }

        nameMap = "";
        GameCanvas.gameScr.focusedActor = null;
        GameData.removeAllImgTree();
        GameData.listbyteData.clear();
        GameData.listImgIcon.clear();
        imgBigMap = null;
        MonsterTemplate.ALL_DATE_NEW_MONSTER.clear();
    }

    public static void paintinfo18plush(mGraphics g) {
        if (GameCanvas.gameScr.ispaint12Plus) {
            if (mSystem.isPC || mSystem.isIP) {
                g.fillRect(0, 0, 0, 0, false);
            }

            GameCanvas.resetTrans(g);
            int xpaint = 115;
            int ypaint = 0;
            if (GameCanvas.currentScreen != GameCanvas.gameScr) {
                xpaint = 0;
            }

            if (isIntro) {
                xpaint = 0;
                ypaint = 15;
            }

            int wStr = mFont.tahoma_7_white.getWidth("12+ Chơi quá 180 phút mỗi ngày sẽ hại sức khỏe.");
            g.setClip(xpaint - 2, ypaint, wStr, 15);
            g.ClipRec(xpaint - 2, ypaint, wStr, 15);
            g.drawImage(imgBack, xpaint, ypaint, 0, true);
            g.drawImage(imgBack, xpaint + 140, ypaint, 0, true);
            mFont.tahoma_7_white.drawString(g, "12+ Chơi quá 180 phút mỗi ngày sẽ hại sức khỏe.", xpaint, ypaint + 3, 0, true);
            g.restoreCanvas();
            GameCanvas.resetTrans(g);
        }
    }

    public static boolean canupdatenotify() {
        return GameCanvas.notify != null && !isIntro && (GameCanvas.currentScreen == ShowinfoItem.gI() || GameCanvas.currentScreen == ScreenClan.gI() || GameCanvas.currentScreen == InfoOtherCharScr.gI() || GameCanvas.currentScreen == GameCanvas.msgchat || GameCanvas.currentScreen == GameCanvas.gameScr || GameCanvas.currentScreen == MainMenu.gI() || GameCanvas.currentScreen == ShopHairScreen.gI()) && GameCanvas.currentScreen != ChangScr.gI();
    }

    public static void playSound1() {
        if (Tilemap.lv == 0) {
            mSound.playMus(0, mSound.volumeMusic, true);
        }

        if (Tilemap.lv != 0) {
            if (Tilemap.idTile == 0) {
                mSound.playMus(1, mSound.volumeMusic, true);
            }

            if (Tilemap.idTile == 5) {
                mSound.playMus(2, mSound.volumeMusic, true);
            }

            if (Tilemap.idTile == 4) {
                mSound.playMus(3, mSound.volumeMusic, true);
            }
        }

    }

    public void updateTips() {
    }

    public static void onWeather(byte weather2, int number, int timeLimit) {
        if (weather2 != -1) {
            if (weather2 == 2) {
                effAnimate.addElement(new AnimateEffect(weather2, true, number, timeLimit));
                effAnimate.addElement(new AnimateEffect((byte) 0, true, number, timeLimit));
            } else {
                effAnimate.addElement(new AnimateEffect(weather2, true, number, timeLimit));
            }
        } else {
            for (int i = 0; i < effAnimate.size(); ++i) {
                AnimateEffect ef = (AnimateEffect) effAnimate.elementAt(i);
                ef.isStop = true;
            }
        }

    }

    public void createCloud() {
        for (int i = 0; i < 6; ++i) {
            Point p = new Point(Tilemap.w * 16 + r.random(50, 100), 70 + 16 * r.nextInt(Tilemap.h), r.random(1, 3), r.nextInt(2));
            this.vecCloud.addElement(p);
        }

    }

    public void paintClound(mGraphics g, int player) {
        if (this.isCloud) {
            for (int i = 0; i < this.vecCloud.size(); ++i) {
                Point p = (Point) this.vecCloud.elementAt(i);
                if (p != null && p.player == player) {
                    g.drawImage(imgCloud[p.id], p.x, p.y, 0, false);
                }
            }

        }
    }

    public void updateClound() {
        if (this.isCloud) {
            for (int i = 0; i < this.vecCloud.size(); ++i) {
                Point p = (Point) this.vecCloud.elementAt(i);
                if (p != null) {
                    p.x -= p.speed;
                    if (p.x <= -(mGraphics.getImageWidth(imgCloud[p.id]) + 100)) {
                        p.x = p.sx + r.random(100, 200);
                        p.y = 70 + 16 * r.nextInt(Tilemap.h);
                        p.player = (byte) r.nextInt(2);
                        p.speed = r.random(1, 3);
                    }
                }
            }

        }
    }

    public static Actor isHavePet(short petid) {
        for (int i = 0; i < GameCanvas.gameScr.actors.size(); ++i) {
            Actor ac = (Actor) GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null && ac.catagory == 12 && ac.ID == petid) {
                return ac;
            }
        }

        return null;
    }

    public void startWeapsAngleBoss(int angle, int power, Actor acFr, Actor acTo, int type, boolean isUpDown, int addw, int addh, int xbg, int ybg, boolean lastActo) {
        WeaponsAngleBoss a;
        if (isUpDown) {
            if (acFr != null && acTo != null) {
                a = new WeaponsAngleBoss(angle, power, acFr, acTo, type, addw, addh, xbg, ybg, lastActo);
                arrowsUp.addElement(a);
            }
        } else {
            a = new WeaponsAngleBoss(angle, power, acFr, acTo, type, addw, addh, xbg, ybg, lastActo);
            this.arrowsDown.addElement(a);
        }

    }

    public void SkillofBoss_Snake(Actor acFr, Actor acTo, int w, int h) {
        for (int i = 0; i < 12; ++i) {
            this.startWeapsAngleBoss(i * 30, 10, acFr, acTo, 0, true, w, h, 0, 0, i == 11);
        }

        this.timerung = 20;
    }

    public void creatWeaponFire(Actor live_From, Actor live_To, int type) {
        WeaponsFire w = new WeaponsFire(live_From, live_To, type);
        if (live_From.getDir() != 1) {
            arrowsUp.addElement(w);
        } else {
            this.arrowsDown.addElement(w);
        }

    }

    class Ticker {
        int x;
        int y;
        int y1;
        public boolean end = false;
        public String info = "";

        public Ticker(String info, int x, int y) {
            this.info = info;
            this.x = x;
            this.y = y;
        }

        public void paint(mGraphics g) {
            FontTeam.normalFont[0].drawString(g, this.info, this.x, this.y, 0, false);
        }

        public void update() {
            if (this.x + FontTeam.normalFont[0].getWidth(this.info) + 10 < 0) {
                this.y1 -= 2;
            }

            if (this.y1 < -18) {
                this.end = true;
            }

            this.x -= 2;
        }
    }
}

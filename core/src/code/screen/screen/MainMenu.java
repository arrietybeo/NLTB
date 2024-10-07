package code.screen.screen;

import code.effect.new_skill.EffectEnd;
import code.effect.new_skill.EffectSkill;
import code.main.GameCanvas;
import code.model.Actor;
import code.model.Char;
import code.model.DataSkillEff;
import code.model.Effect;
import code.model.GemTemp;
import code.model.IActionListener;
import code.model.ImageIcon;
import code.model.Item;
import code.model.ItemOption;
import code.model.ItemTemplate;
import code.model.MainChar;
import code.model.PartyInfo;
import code.model.Pet;
import code.model.Quest;
import code.model.QuestInfo;
import code.model.Scroll;
import code.model.ScrollResult;
import code.model.T;
import code.model.arrow.WeaponsLazer;
import code.model.mCommand;
import code.network.GameService;
import code.screen.Res;
import code.screen.SkillTemplate;
import code.screen.Util;

import javax.microedition.lcdui.Image;

import lib.Cout;
import lib.MainUnity;
import lib.Rms;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSound;
import lib.mSystem;
import lib.mVector;
import lib2.TField;
import lib2.mFont;

public class MainMenu extends ScreenTeam implements IActionListener {
    public static MainMenu me;

    public int x;

    public int y;

    public int w;

    public int h;

    public int xbg;

    public int size;

    public int indexMainTab;

    public int xScItem;

    public int yScItem;

    public int hScItem;

    public int wScItem;

    public int indexSubTab;

    public int countFrame;

    public int sizeH;

    public int indexTabMiniShop;

    public int totalLineQuest;

    public int xcenter;

    public int ycenter;

    public int miniItem;

    public int wait;

    public int xSao;

    public int ySao;

    public int typePhiPhong;

    public int indexPet;

    public static int indexTypeQuest;

    public static int indexQuest;

    public static int typeresult;

    public static int idicon;

    public int prizeImbue;

    public short idCharSell;

    public static MainMenu gI() {
        return (me == null) ? (me = new MainMenu()) : me;
    }

    public static boolean isDapdo = false;

    public static boolean isCheDo = false;

    public static boolean isPhiPhong = false;

    public static boolean isChuyenHoa = false;

    public static boolean isHopda = false;

    public static final byte SHOP = 0;

    public static final byte HANHTRANG = 1;

    public static final byte TRANGBI = 2;

    public static final byte SKILL = 3;

    public static final byte QUEST = 4;

    public static final byte NAP_XU = 5;

    public static final byte BANG_HOI = 6;

    public static final byte HOAT_DONG = 7;

    public static final byte DAP_DO = 8;

    public static final byte CHE_DO = 9;

    public static final byte CHUYEN_HOA = 10;

    public static final byte TAO_PHI_PHONG = 11;

    public static final byte PET_ITEM = 12;

    public static final byte BAG = 22;

    public static final byte SKILL_CLAN = 29;

    public int[] maptopaintMenuIcon;

    public int[] maptopaintIconTrangBi = new int[]{5, 9, 3, 11, 2, 7, 8, 1, 6, 10, 14, 12, 13, -1};

    public static int[] indexpaintIconSettings = new int[]{5};

    public static short ID_CUONG_HOA;

    public static short ID_BAO_HO;

    public static short ID_MAY_MAN;

    public static String[] mainTab;

    public static String[] myInfo;

    public static String[] bangHoi;

    public static String[] hoat_dong;

    public static String[] settings;

    public static String[] tabHanhTrang;

    public static String[] feaTures;

    public static String[] currnentTabDetail;

    String currentTileMainTab = "";

    TField tfStrength = new TField();

    TField tfAgility = new TField();

    TField tfSpirit = new TField();

    TField tfHealth = new TField();

    TField tfLucky = new TField();

    public static boolean isPet = true;

    private int lastSelect = -1;

    public int colum = 7;

    public int hIP;

    public int xShowText;

    public int yShowText;

    public int wShowText;

    public int hShowText;

    public int indexShowInfo;

    public int xShowTextSkill;

    public int yShowTextSkill;

    private mVector showText;

    private byte[] arrayKhamNgoc;

    public mVector list = new mVector();

    public mVector readyBuy = new mVector();

    public mVector listTemp = new mVector();

    public mVector listEp = new mVector();

    public static final short MAX_POTION = 999;

    public mVector inFoNap = new mVector();

    public mVector sellItem = new mVector();

    public mVector listItemAnimal = new mVector();

    Scroll cmrItem = new Scroll();

    Scroll cmrSubTab = new Scroll();

    Scroll cmrShowText = new Scroll();

    Scroll cmrShowInfoMainChar = new Scroll();

    public static byte khoaImbue;

    public static byte idMonsterClanQuest = -1;

    public boolean isLoad = false;

    public boolean isSkill;

    public boolean first;

    public boolean isFeatures;

    public boolean isCharWearing;

    public boolean isShowInFoChar;

    public boolean isQuest;

    public boolean isMoveQuest;

    public boolean isAnimal;

    public boolean isHanhTrang;

    public boolean isgangphim;

    public boolean isMiniShop;

    public boolean isFocusClose;

    public boolean isFocusCloseItemFill;

    public boolean isSkillClan;

    public boolean isQuestClan;

    public boolean Waitcreate;

    public static short numberQuestAll;

    public static short numberQuestGet = 0;

    public int[][] arrFrame = new int[][]{{0, 1, 2}, {0, 1}};

    public int[][] xyTiemNang = new int[4][];

    public int[][] xyDapDo = new int[3][];

    public int[] sizeEff = new int[]{2, 2, 2, 2, 2, 2};

    int[] xsai = new int[]{38, 38, -4, -4, -4, 38};

    int[] listNum = new int[6];

    int[] postItem_PhiPhong_X;

    int[] postItem_PhiPhong_Y;

    int[] postItem = new int[]{30, 90, 150, 210, 270, 330};

    Item[] listItem = new Item[6];

    public static int xposItem;

    public static int ypostItem;

    public static mCommand cmdCongDiem;

    public static mCommand cmdcong1;

    public static mCommand cmdcong5;

    public static mCommand cmdcong10;

    public static mCommand cmdGangphim;

    public static mCommand cmdBuy;

    public static mCommand cmdMapScr;

    public static mCommand cmdHuyQuest;

    public static mCommand cmdChangeMapScr;

    public static mCommand cmdban;

    public static mCommand cmdvut;

    public static mCommand cmdsudung;

    public static mCommand cmdbanNhieu;

    public static mCommand cmdbovao;

    public static mCommand cmdlayra;

    public static mCommand cmdtao;

    public static mCommand cmdlayraPhiPhong;

    public static mCommand cmdChoan;

    public static mCommand cmdTB1;

    public static mCommand cmdTB2;

    public boolean isRestartAutoFight;

    public boolean beginChedo;

    public boolean isTouchCenter;

    public static mVector vecItemDapDo = new mVector();

    public static mVector vecItemCreate = new mVector();

    public static mVector vecPetEat = new mVector();

    public static mVector vecMaterial = new mVector();

    public static Item mItem;

    public static Item itemStone;

    public static Item itemBaohiem;

    public static Item itemBua;

    public static Item itemChuyenHoa0;

    public static Item itemChuyenHoa1;

    public static Item itemChuyenHoa2;

    public static int maxDap;

    public static int tabDapdo;

    public static int xuCuongHoa;

    public static int moneyType;

    public mVector vHanhTrang = new mVector();

    public mVector vEffect = new mVector();

    public static DataSkillEff effDapDo = new DataSkillEff();

    public static String textPercent = "0";

    public static boolean isBeginQuest = false;

    public static boolean isInven;

    public static int delay = 0;

    public Item itemPP;

    public Pet mpet;

    public static short[] petInfo;

    Scroll scrDapdo = new Scroll();

    Scroll scrSkill = new Scroll();

    Scroll scrMainmenu = new Scroll();

    public int numStone;

    public byte indexWearing;

    public mVector mShop;

    int maxNumW;

    public MainMenu() {
        this.mShop = new mVector();
        this.maxNumW = 3;
        this.totalItemFill = new mVector();
        this.sizeDapdo = 26;
        this.petfont = new mFont[]{mFont.tahoma_7_green, mFont.tahoma_7_red, mFont.tahoma_7_blue, mFont.tahoma_7_violet};
        this.currentNameSkill = "";
        this.disString = 12;
        this.numItem = 0;
        this.tile = new String[2];
        this.infochar = new mVector();
        this.potionShop = new mVector();
        this.oldSelect = 0;
        this.totalInfoshow = 0;
        this.cmdSelectItem = new mCommand(this);
        this.right = new mCommand("Đóng", this, 1);
        if (GameCanvas.isTouch) {
            this.right.caption = "";
            this.right.idAction = 1000;
        }
        cmdTB1 = new mCommand(T.trangbi1, this, 48);
        cmdTB2 = new mCommand(T.trangbi2, this, 49);
        this.indexWearing = 0;
        cmdCongDiem = new mCommand(T.congDiem, this, 334);
        cmdcong1 = new mCommand(String.valueOf(T.congDiem) + " 1", this, 19);
        cmdcong5 = new mCommand(String.valueOf(T.congDiem) + " 5", this, 20);
        cmdcong10 = new mCommand(String.valueOf(T.congDiem) + " 10", this, 21);
        cmdGangphim = new mCommand(T.Gangphim, this, 40);
        cmdBuy = new mCommand(T.Buy, this, 5);
        cmdMapScr = new mCommand(T.map, this, 15);
        if (!GameCanvas.isTouch) cmdChangeMapScr = new mCommand(T.map, this, 18);
        cmdHuyQuest = new mCommand(T.cancel, this, 17);
        cmdbanNhieu = new mCommand(T.bannhieu, this, 25);
    }

    public void PutItemSHop(mVector shop) {
        this.mShop = shop;
    }

    public void setInven() {
        this.indexMainTab = 1;
    }

    public static byte[] index = new byte[1];

    byte[] arrSell;

    public void set_maptopaintMenuIcon(byte[] numTab) {
        this.maptopaintMenuIcon = null;
        this.maptopaintMenuIcon = new int[numTab.length];
        for (int i = 0; i < numTab.length; i++)
            this.maptopaintMenuIcon[i] = numTab[i];
    }

    public static String captionServer = "";

    public static String infoBuySellServer = "";

    public int[] xWearing;

    public int[] yWearing;

    int xInfoWearing;

    int yInfoWearing;

    int wInfoWearing;

    int hInfoWearing;

    int xItemFill;

    int yItemFill;

    int wItemFill;

    int hItemFill;

    private String[] animalInfo;

    public mVector totalItemFill;

    boolean isShowFill;

    int sizeDapdo;

    int slDapdo1;

    int slDapdo2;

    int tickChuyenhoa;

    int FocusPhiPhong;

    int FocusPet;

    public static String getInfoBuySell(String utf1, String utf2, Item it) {
        if (it == null) return "";
        utf2 = Util.replaceString(utf2, "#", String.valueOf(utf1) + " " + it.getName());
        utf2 = Util.replaceString(utf2, "@", String.valueOf(utf1) + " " + it.getName());
        return utf2;
    }

    public void setInfo(int id, boolean isSell, byte[] info) {
        this.isSkillClan = false;
        int c = 0;
        if (!this.isMe) {
            int i;
            for (i = 0; i < info.length; i++) {
                if (info[i] != 0) c++;
            }
            index = new byte[c];
            for (i = 0; i < info.length; i++) {
                if (info[i] != 0) for (int j = 0; j < index.length; j++) {
                    if (index[j] == 0) {
                        index[j] = info[i];
                        break;
                    }
                }
            }
        }
        this.isSell = isSell;
        if (isSell) this.arrSell = index;
        if (this.isMe) {
            setSelectTab("setInfo 1");
            return;
        }
        this.indexTabMiniShop = 0;
        this.isMiniShop = true;
        this.indexMainTab = id;
        setInfoTab(index[id]);
        setSelectTab("setInfo 2");
    }

    public void setInfoTab(int id) {
        switch (id) {
            case 9:
                currnentTabDetail = new String[]{"Quầy 1"};
                this.isHanhTrang = true;
                break;
            case 11:
                mainTab = new String[]{"Cửa hàng", "Hành trang"};
                currnentTabDetail = new String[]{"Áo", "Quần", "Nón", "Nhẫn", "Dây chuyền", "Giày", "Găng tay", "Ngọc bội"};
                this.isHanhTrang = true;
                break;
            case 10:
            case 19:
            case 23:
            case 24:
                mainTab = new String[]{"Cửa hàng", "Hành trang"};
                currnentTabDetail = new String[]{"Quầy 1"};
                this.isHanhTrang = true;
                break;
            case 22:
                mainTab = new String[]{"Kho", "Hành trang"};
                currnentTabDetail = new String[]{""};
                this.isHanhTrang = true;
                break;
            case 29:
                currnentTabDetail = new String[]{bangHoi[2]};
                this.isSkillClan = true;
                break;
        }
    }

    public void initName() {
        if (GameScr.mainChar.idClan == -1) {
            bangHoi = new String[]{"Top", "Đăng ký"};
        } else if (GameScr.mainChar.isMaster == 0) {
            bangHoi = new String[]{"Thông tin", "Tin nhắn", "Kỹ năng", " N.vụ", "Q.góp", "T.viên", "Top", "Chat", "Giải tán"};
        } else {
            bangHoi = new String[]{"Thông tin", "Tin nhắn", "Kỹ năng", " N.vụ", "Q.góp", "T.viên", "Top", "Chat", "Rời bang"};
        }
    }

    public void setPosTfFeatures(int x, int y, String Strength, String Agility, String Spirit, String Health, String Lucky) {
        this.tfStrength = new TField();
        this.tfStrength.x = x;
        this.tfStrength.y = y;
        this.tfStrength.isFocus = false;
        this.tfStrength.setIputType(1);
        this.tfStrength.width = this.size + 10;
        this.tfStrength.height = this.size - 5;
        this.tfStrength.setText(Strength);
        y += this.size + 2;
        this.tfAgility = new TField();
        this.tfAgility.x = x;
        this.tfAgility.y = y;
        this.tfAgility.isFocus = false;
        this.tfAgility.setIputType(1);
        this.tfAgility.width = this.size + 10;
        this.tfAgility.height = this.size - 5;
        this.tfAgility.setText(Agility);
        y += this.size + 2;
        this.tfSpirit = new TField();
        this.tfSpirit.x = x;
        this.tfSpirit.y = y;
        this.tfSpirit.isFocus = false;
        this.tfSpirit.setIputType(1);
        this.tfSpirit.width = this.size + 10;
        this.tfSpirit.height = this.size - 5;
        this.tfSpirit.setText(Spirit);
        y += this.size + 2;
        this.tfHealth = new TField();
        this.tfHealth.x = x;
        this.tfHealth.y = y;
        this.tfHealth.isFocus = false;
        this.tfHealth.setIputType(1);
        this.tfHealth.width = this.size + 10;
        this.tfHealth.height = this.size - 5;
        this.tfHealth.setText(Lucky);
        y += this.size + 2;
        this.tfLucky = new TField();
        this.tfLucky.x = x;
        this.tfLucky.y = y;
        this.tfLucky.isFocus = false;
        this.tfLucky.setIputType(1);
        this.tfLucky.width = this.size + 10;
        this.tfLucky.height = this.size - 5;
        this.tfLucky.setText(Health);
    }

    public boolean keyPress(int keyCode) {
        return super.keyPress(keyCode);
    }

    public boolean isPaintSub() {
        if (this.isSkill || this.isFeatures || this.isCharWearing || this.isQuest || this.isAnimal || this.isHanhTrang || this.isQuestClan || this.indexMainTab == 0 || isCheDo || isChuyenHoa || isPhiPhong || this.indexMainTab == 12)
            return true;
        return false;
    }

    public void setPosWearing(boolean isSkill) {
        int ybg = this.y + this.h - 2 * this.size - 3 - (isSkill ? 12 : 0);
        if (isSkill) ybg = this.y + this.h / 2 - 30;
        this.xWearing = new int[isSkill ? GameScr.vec_skill.size() : 15];
        this.yWearing = new int[isSkill ? GameScr.vec_skill.size() : 15];
        int xbgg = this.xbg;
        if (this.isAnimal) {
            this.xWearing = new int[6];
            this.yWearing = new int[6];
            for (int j = 0; j < this.xWearing.length; j++) {
                this.xWearing[j] = xbgg + j % 6 * (this.size + 1);
                this.yWearing[j] = ybg + j / 6 * this.size + this.size + this.hIP / 2;
            }
        } else if (isSkill) {
            int kc = (this.colum - 4) * this.size / 5 + 12;
            for (int j = 0; j < this.xWearing.length; j++) {
                this.xWearing[j] = xbgg + j % (isSkill ? 3 : 7) * this.size + (j % (isSkill ? 3 : 7) + 1) * kc + (this.colum - 4) * this.size % 5 / 2;
                this.yWearing[j] = ybg + j / (isSkill ? 3 : 7) * (this.size + 12) - this.size + this.hIP / 2;
            }
        } else {
            this.xWearing[0] = xbgg;
            this.yWearing[0] = ybg - this.size + this.hIP / 2;
            this.xWearing[1] = xbgg + this.size;
            this.yWearing[1] = ybg - this.size + this.hIP / 2;
            for (int j = 0; j < this.xWearing.length; j++) {
                this.xWearing[j] = xbgg + j % 5 * this.size + 16;
                this.yWearing[j] = ybg + j / 5 * (this.size - this.hIP / 4) - this.size + this.hIP / 2;
            }
        }
        if (this.indexPet == 1) {
            this.xWearing[0] = xbgg;
            this.yWearing[0] = ybg - this.size + this.hIP / 2;
            this.xWearing[1] = xbgg + this.size;
            this.yWearing[1] = ybg - this.size + this.hIP / 2;
            for (int j = 0; j < this.xWearing.length; j++) {
                this.xWearing[j] = xbgg + j % 5 * this.size + 16;
                this.yWearing[j] = ybg + j / 5 * (this.size - this.hIP / 4) - this.size + this.hIP / 2;
            }
        }
        this.xInfoWearing = xbgg + 2 * this.size;
        this.yInfoWearing = this.y + this.sizeH;
        this.wInfoWearing = 3 * this.size + 5;
        this.hInfoWearing = this.h - this.sizeH - 2 * this.size;
        int ybgTN = 0;
        for (int i = 0; i < this.xyTiemNang.length; i++) {
            this.xyTiemNang[i] = new int[2];
            ybgTN = this.y + this.sizeH + this.size / 4 + ((GameCanvas.w < 200) ? 30 : 34) * ((i > 1) ? 1 : 0);
            this.xyTiemNang[i][0] = this.xInfoWearing + 10 * i % 2 + (10 + (this.wInfoWearing - 42) / 3) * (i % 2 + 1) + 2;
            int ys = 0, ys2 = 3;
            if (i < 2 && mSystem.isIP && mGraphics.zoomLevel == 3) ys = 3;
            if (mSystem.isIP && mGraphics.zoomLevel == 3) ys2 = 0;
            this.xyTiemNang[i][1] = ybgTN + 28 - this.hIP + 3 + ys - ys2;
        }
        this.xyDapDo[0] = new int[2];
        this.xyDapDo[0][0] = this.xyTiemNang[0][0] + 18;
        this.xyDapDo[0][1] = this.xyTiemNang[0][1] - 12 + 5;
        this.xyDapDo[1] = new int[2];
        this.xyDapDo[1][0] = this.xyTiemNang[2][0];
        this.xyDapDo[1][1] = this.xyTiemNang[2][1] - 10 + 5;
        this.xyDapDo[2] = new int[2];
        this.xyDapDo[2][0] = this.xyTiemNang[3][0];
        this.xyDapDo[2][1] = this.xyTiemNang[3][1] - 10 + 5;
        xposItem = this.xyDapDo[0][0] - 70;
        ypostItem = this.xyDapDo[0][1];
    }

    void dosetIDCmdTouch(String caption, int id) {
        if (!GameCanvas.isTouch) return;
        this.cmdSelectItem.caption = "";
        this.cmdSelectItem.idAction = id;
        dosetXYCmddTouch();
    }

    void dosetXYCmddTouch() {
        if (!GameCanvas.isTouch) return;
        this.cmdSelectItem.setXY(this.xShowText + this.wShowText / 2 - this.cmdSelectItem.wCmd / 2, this.y + this.h - this.cmdSelectItem.hCmd - 2);
        if (this.cmdSelectItem.caption.equals("")) this.cmdSelectItem.x = -100;
    }

    public static int getPos(ItemTemplate it, int pos) {
        if (it == null) return -1;
        switch (it.type) {
            case 0:
            case 14:
                return 0;
            case 1:
            case 16:
                return 1;
            case 2:
            case 17:
                return 2;
            case 3:
                return 9;
            case 4:
                return 9;
            case 5:
                return 9;
            case 6:
                return 9;
            case 7:
                return 9;
            case 8:
                if (pos != 1) return 6;
                return 5;
            case 9:
            case 18:
                return 4;
            case 10:
                return 7;
            case 11:
                return 3;
            case 12:
                return 8;
            case 13:
            case 15:
                return 3;
        }
        return -1;
    }

    private void paintDapDo(mGraphics g, boolean isChar) {
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing + 2;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2;
        g.setColor(-13232632);
        g.fillRect(xShowText1, yShowText1, wShowText1, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xShowText1 + 1, yShowText1 + 1, wShowText1 - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xShowText1 + 2, yShowText1 + 2, wShowText1 - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xShowText1 + 3, yShowText1 + 3, wShowText1 - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xShowText1, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xShowText1, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1, yShowText1, mGraphics.TOP | mGraphics.RIGHT, false);
        int xbg = this.x + this.size + 8;
        int ybg = yShowText1;
        if (GameCanvas.w > 200) {
            g.setColor(-9751532);
            int hh0 = hShowText1 + (this.isAnimal ? this.size : 0);
            g.fillRect(xbg + 3, ybg, 2 * this.size - 6, hh0, false);
            g.fillRect(xbg, ybg + 3, 2 * this.size, hh0 - 6, false);
            g.setColor(-4891370);
            g.drawLine(xbg + 3, ybg + 3 + hh0 - 6 + 1, xbg + 2 * this.size - 6, ybg + 3 + hh0 - 6 + 1, false);
            if (isFocusCharWearing && GameCanvas.gameTick / 4 % 4 != 0) {
                g.setColor(-12246258);
            } else {
                g.setColor(-14864849);
            }
            g.fillRect(xbg + 3, ybg + 3, 2 * this.size - 6, hh0 - 6, false);
            g.setColor(-110);
            g.fillRect(xbg + 3, ybg + 1, 2 * this.size - 6, 1, false);
            g.setColor(-4034289);
            g.fillRect(xbg + 1, ybg + 16, 1, hh0 - 18, false);
            g.fillRect(xbg - 2 + 2 * this.size, ybg + 16, 1, hh0 - 18, false);
        }
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        GameCanvas.resetTrans(g);
        int maxx = vecItemDapDo.size();
        if (maxx < 45) maxx = 45;
        int t = maxx / this.colum + ((MainChar.MaxInven % this.colum != 0) ? 1 : 0);
        int hip3 = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) hip3 = -5;
        this.scrDapdo.setStyle(t + 3, this.sizeDapdo + 2, this.xWearing[0] - 10, this.yWearing[0] + 9, this.colum * this.sizeDapdo, 5 * this.sizeDapdo, true, this.colum);
        g.ClipRec(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74 + hip3);
        this.scrDapdo.setClip(g, this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74 + hip3);
        int i;
        for (i = 0; i < maxx; i++) {
            g.setColor(-14458807);
            g.drawRect(this.xWearing[0] + i % this.colum * this.sizeDapdo + 2 - 5 - 10, this.yWearing[0] + i / this.colum * this.sizeDapdo + 10 - 2 + 5 - 9, this.sizeDapdo, this.sizeDapdo, true);
        }
        for (i = 0; i < vecItemDapDo.size(); i++) {
            Item it = (Item) vecItemDapDo.elementAt(i);
            if (it != null)
                it.paint(g, this.xWearing[0] + i % this.colum * this.sizeDapdo + 1, this.yWearing[0] + i / this.colum * this.sizeDapdo + this.sizeDapdo - 9, true);
        }
        if (GameCanvas.isTouch) {
            if (this.slDapdo1 > -1 && this.slDapdo1 < vecItemDapDo.size())
                paintFocus(g, this.xWearing[0] + this.slDapdo1 % this.colum * this.sizeDapdo + 2 - 2, 1 + this.yWearing[0] + this.slDapdo1 / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4, 3, true);
            paintFocus(g, this.xWearing[0] - 40, 1 + this.yWearing[0], 3, true);
        }
        if (!GameCanvas.isTouch && this.slDapdo1 > -1 && this.slDapdo1 < vecItemDapDo.size() && tabDapdo == 1)
            paintFocus(g, this.xWearing[0] + this.slDapdo1 % this.colum * this.sizeDapdo + 2 - 1, this.yWearing[0] + this.slDapdo1 / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4, 3, true);
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        for (i = 0; i < this.xyDapDo.length; i++)
            g.drawImage(GameScr.imgBackItem, this.xyDapDo[i][0], this.xyDapDo[i][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
        if (itemStone != null) {
            itemStone.paintIconWearing(g, this.xyDapDo[0][0], this.xyDapDo[0][1]);
            FontTeam.numberSmall_yeallow.drawString(g, (new StringBuilder(String.valueOf(this.numStone))).toString(), this.xyDapDo[0][0] - 2, this.xyDapDo[0][1] + 12, 2, false);
        }
        if (itemBaohiem != null) itemBaohiem.paintIconWearing(g, this.xyDapDo[1][0], this.xyDapDo[1][1]);
        if (itemBua != null) itemBua.paintIconWearing(g, this.xyDapDo[2][0], this.xyDapDo[2][1]);
        if (!GameCanvas.isTouch && selected > 0 && selected < 4 && tabDapdo == 0)
            paintFocus(g, this.xyDapDo[selected - 1][0] - this.size / 2 + this.size / 2, this.xyDapDo[selected - 1][1] - this.size / 2 + this.size / 2, 0, true);
        g.drawImage(GameScr.imgBackItem, xposItem, ypostItem, mGraphics.VCENTER | mGraphics.HCENTER, false);
        if (mItem != null) mItem.paintIconWearing(g, xposItem, ypostItem);
        if (isHopda && ID_CUONG_HOA != -1) {
            ImageIcon img = GameData.getImgIcon((short) (ID_CUONG_HOA + Res.ID_ITEM));
            if (img != null && img.img != null) g.drawImage(img.img, xposItem, ypostItem, 3, true);
        }
        if (selected == 3 && GameCanvas.isTouch)
            paintFocus(g, xposItem - this.size / 2 + this.size / 2, ypostItem - this.size / 2 + this.size / 2, 0, true);
        if (!GameCanvas.isTouch && selected == 0 && tabDapdo == 0)
            paintFocus(g, xposItem - this.size / 2 + this.size / 2, ypostItem - this.size / 2 + this.size / 2, 0, true);
        if (!textPercent.equals("")) {
            mFont.tahoma_7_green.drawString(g, T.tile, xposItem, ypostItem + 13 + 20, 2, false);
            mFont.tahoma_7_green.drawString(g, textPercent, xposItem, ypostItem + 10 + 13 + 20, 2, false);
        }
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 0, xbg, ybg, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.TOP | mGraphics.RIGHT, false);
        ybg += hShowText1 + (this.isAnimal ? this.size : 0);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 0, xbg, ybg, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        if (effDapDo != null) effDapDo.paintTop(g, xposItem, ypostItem);
        if (xuCuongHoa > 0) {
            g.drawRegion(GameScr.imgMoney, 0, (moneyType == 1) ? 0 : 7, 9, 7, 0, this.xyDapDo[1][0] - 15, this.xyDapDo[1][1] + 19, 0, false);
            FontTeam.numberSmall_white.drawString(g, Res.getDotNumber(xuCuongHoa), this.xyDapDo[1][0] - 5, this.xyDapDo[1][1] + 18, 0, false);
        }
    }

    public static byte POS_BODY = 5;

    public static byte POS_HAT = 0;

    public static byte POS_SHOES = 8;

    public static byte POS_GLOVE = 7;

    public static byte POS_WEAPON = 9;

    public static byte POS_RING_LEFT = 3;

    public static byte POS_RING_RIGHT = 4;

    public static byte POS_CHAIN = 1;

    public static byte POS_JEWELRY = 2;

    public static byte POS_BELT = 6;

    public static byte POS_THU_CUOI = 11;

    public static byte POS_PHI_PHONG = 10;

    public static byte POS_AN = 13;

    public static byte POS_PET = 14;

    public static byte POS_THOI_TRANG = 12;

    public static byte[] POS_ITEM_IN_EQUIP = new byte[]{POS_BODY, POS_HAT, POS_SHOES, POS_GLOVE, POS_WEAPON, POS_WEAPON, POS_WEAPON, POS_WEAPON, POS_WEAPON, POS_RING_LEFT, POS_CHAIN, POS_JEWELRY, POS_BELT, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, POS_PET, -1, -1, POS_AN, -1, -1, -1, -1, POS_PET, POS_THOI_TRANG, -1, -1, -1, -1, -1, -1};

    public mFont[] petfont;

    int coutFc;

    public String currentNameSkill;

    int f;

    int r;

    int goc;

    int disString;

    int indexShadow;

    int numItemStart;

    int laststar;

    int speedStart;

    int numItem;

    int indexPaintLineSkill;

    int numItemStart2;

    int laststar2;

    int speedStart2;

    boolean isHalfstart;

    boolean runStart;

    boolean isTextmua;

    boolean canbuy;

    boolean isHalfstart2;

    boolean runStart2;

    public String[] arrayText;

    public String textHoimua;

    public static boolean isFocusDetailMenu;

    public static boolean isChangeSubTab;

    public static boolean isFocusCharWearing;

    private int countL;

    private int countR;

    private byte focusTab;

    int timeAuToShowText;

    public boolean beGinShowText;

    public boolean isSell;

    public boolean isFocusPetWearing;

    private int idFrame;

    public static Char charWearing;

    public int countTextmua;

    boolean isMe;

    public static boolean isShow;

    public int numtab;

    mCommand cmdShowText;

    mCommand cmdSelectItem;

    String[] tile;

    mVector infochar;

    private boolean isShowText;

    private boolean isUseCmr;

    private boolean isSetXYCmdSelect;

    private boolean isPaintMoney;

    public Image imgWeaponAvatar;

    public mVector potionShop;

    private mVector questInfo;

    public static byte lvThu;

    public static final byte TAM = 8;

    public static final byte MUOI = 10;

    public static final byte MUOICHIN = 19;

    public static final byte HAIMOT = 21;

    public static final byte HAIHAI = 22;

    public static final byte HAILAM = 25;

    public int idSeller;

    public int shopIdSell;

    public int idNPC_Shop;

    public int catNPC_Shop;

    short oldSelect;

    byte totalInfoshow;

    Image imgCharWear;

    public String name;

    public static final short SELECT_ITEM_INVENTORY = 79;

    public static final short SELECT_ITEM_SHOP = -2;

    public static final short SELECT_ITEM_WEARING = 4;

    public static String[] QuestTile;

    public static mVector[] ListQuest;

    private void paintCharWearing(mGraphics g, boolean isChar) {
        try {
            int yShowText1 = this.yInfoWearing + 2;
            int xShowText1 = this.xInfoWearing;
            int wShowText1 = this.wInfoWearing + 2;
            int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2;
            g.setColor(-13232632);
            g.fillRect(xShowText1, yShowText1, wShowText1, hShowText1, false);
            g.setColor(-1596632);
            g.fillRect(xShowText1 + 1, yShowText1 + 1, wShowText1 - 2, hShowText1 - 2, false);
            g.setColor(-13232632);
            g.fillRect(xShowText1 + 2, yShowText1 + 2, wShowText1 - 4, hShowText1 - 4, false);
            g.setColor(-14864849);
            g.fillRect(xShowText1 + 3, yShowText1 + 3, wShowText1 - 6, hShowText1 - 6, false);
            g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xShowText1, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xShowText1, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1, yShowText1, mGraphics.TOP | mGraphics.RIGHT, false);
            int xbg = this.x + this.size + 8;
            int ybg = yShowText1;
            if (GameCanvas.w > 200) {
                g.setColor(-9751532);
                int hh0 = hShowText1 + (this.isAnimal ? this.size : 0);
                g.fillRect(xbg + 3, ybg, 2 * this.size - 6, hh0, false);
                g.fillRect(xbg, ybg + 3, 2 * this.size, hh0 - 6, false);
                g.setColor(-4891370);
                g.drawLine(xbg + 3, ybg + 3 + hh0 - 6 + 1, xbg + 2 * this.size - 6, ybg + 3 + hh0 - 6 + 1, false);
                if (isFocusCharWearing && GameCanvas.gameTick / 4 % 4 != 0) {
                    g.setColor(-12246258);
                } else {
                    g.setColor(-14864849);
                }
                g.fillRect(xbg + 3, ybg + 3, 2 * this.size - 6, hh0 - 6, false);
                g.setColor(-110);
                g.fillRect(xbg + 3, ybg + 1, 2 * this.size - 6, 1, false);
                g.setColor(-4034289);
                g.fillRect(xbg + 1, ybg + 16, 1, hh0 - 18, false);
                g.fillRect(xbg - 2 + 2 * this.size, ybg + 16, 1, hh0 - 18, false);
            }
            g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 0, xbg, ybg, mGraphics.TOP | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.TOP | mGraphics.RIGHT, false);
            ybg += hShowText1 + (this.isAnimal ? this.size : 0);
            g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 0, xbg, ybg, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            FontTeam.numberSmall_white.drawString(g, this.tile[0], xbg + this.size, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.hIP + 2, 2, false);
            if (isChar && charWearing != null) {
                charWearing.paint(g, xbg + this.size - 1, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.hIP, 0);
            } else {
                charWearing.paint(g, xbg + this.size - 1, this.y + this.h - 3 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.size + this.hIP, 0);
            }
            int i;
            for (i = 0; i < this.xWearing.length; i++)
                g.drawImage(GameScr.imgBackItem, this.xWearing[i], this.yWearing[i] + this.size / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
            if (isChar) {
                if (this.indexWearing <= 0) for (i = 0; i < charWearing.wearing.length; i++) {
                    if (charWearing == null || charWearing.equip == null || this.maptopaintIconTrangBi[i] >= charWearing.equip.length || charWearing.equip[this.maptopaintIconTrangBi[i]] == null)
                        if (GameScr.imgW != null && i < 12) {
                            g.drawRegion(GameScr.imgW, 0, (i + 1) * 17, 17, 17, 0, this.xWearing[this.maptopaintIconTrangBi[i]], this.yWearing[this.maptopaintIconTrangBi[i]] + this.size / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                        } else if (this.maptopaintIconTrangBi[i] != -1) {
                            g.drawRegion(GameScr.imgW, 0, 0, 17, 17, 0, this.xWearing[this.maptopaintIconTrangBi[i]], this.yWearing[this.maptopaintIconTrangBi[i]] + this.size / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                        }
                    if (i == 2 && GameScr.imgW != null && i < 12)
                        g.drawRegion(GameScr.imgW, 0, (i + 1) * 17, 17, 17, 0, this.xWearing[4], this.yWearing[4] + this.size / 2, mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
                for (i = 0; i < this.xWearing.length; i++) {
                    if (charWearing != null && charWearing.equip != null && i + this.indexWearing < charWearing.equip.length && charWearing.equip[i + this.indexWearing] != null)
                        charWearing.equip[i + this.indexWearing].paintIconWearing(g, this.xWearing[i], this.yWearing[i] + this.size / 2);
                }
            }
            if (this.isShowFill) {
                int h0 = GameCanvas.isTouch ? 24 : 0;
                g.setColor(-13232632);
                g.fillRect(this.xItemFill, this.yItemFill - h0, this.wItemFill - 1, this.hItemFill + h0, false);
                g.setColor(-3377408);
                g.drawRect(this.xItemFill, this.yItemFill - h0, this.wItemFill - 1, this.hItemFill + h0, false);
                if (GameCanvas.isTouch) {
                    int hc = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
                    int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
                    g.drawRegion(GameScr.imgButton[4], 0, (this.isFocusCloseItemFill ? 1 : 0) * hc, wc, hc, 0, this.xItemFill + this.wItemFill - wc - 2, this.yItemFill - h0 + 2, 0, false);
                    FontTeam.normalFont[0].drawString(g, "Vật phẩm", this.xItemFill + this.wItemFill / 2, this.yItemFill - h0 / 2 - 5, 2, false);
                    g.fillRect(this.xItemFill + 2, this.yItemFill, this.wItemFill - 5, 1, false);
                }
                int size0 = this.totalItemFill.size();
                GameCanvas.resetTrans(g);
                this.cmrItem.setStyle(size0, this.size, this.xItemFill, this.yItemFill, this.wItemFill, this.hItemFill, false, this.colum);
                g.ClipRec(this.cmrItem.xPos + 1, this.cmrItem.yPos, this.cmrItem.width - 2, this.cmrItem.height);
                this.cmrItem.setClip(g, this.cmrItem.xPos + 1, this.cmrItem.yPos, this.cmrItem.width - 2, this.cmrItem.height);
                if (selected > -1 && selected <= this.totalItemFill.size() - 1 && !isFocusCharWearing && !isFocusDetailMenu)
                    paintFocus(g, this.xItemFill + selected * this.size - 4 + this.size / 2, this.yItemFill - 2 + this.size / 2, 0, true);
                mGraphics.resetTransAndroid(g);
                g.restoreCanvas();
            }
            if (selected > -1 && selected < this.xWearing.length && !isFocusCharWearing && !isFocusDetailMenu)
                paintFocus(g, this.xWearing[selected] - this.size / 2 + this.size / 2, this.yWearing[selected] + this.size / 2, 0, true);
            if (!GameCanvas.isTouch) GameCanvas.resetTrans(g);
            int w0 = FontTeam.smallFontArr[0].getWidth("Điểm: ");
            FontTeam.smallFontArr[0].drawString(g, "Điểm: ", this.xyTiemNang[0][0] - this.size / 2 + 3, this.y + this.size + 18 - this.hIP / 3 - 1, 0, false);
            FontTeam.normalFont[1].drawString(g, "" + Char.Diemtiemnang, this.xyTiemNang[0][0] - this.size / 2 + 3 + w0, this.y + this.size + 18 - this.hIP / 3 - 1, 0, false);
            String[] a = {String.valueOf(Char.sucmanh) + "".trim(), String.valueOf(Char.thanphap) + "".trim(), String.valueOf(Char.linhkhi) + "".trim(), String.valueOf(Char.sinhkhi) + "".trim()};
            int ysss = 0;
            if (mSystem.isIP && mGraphics.zoomLevel == 3) ysss = 1;
            for (int j = 0; j < 4; j++) {
                g.drawImage(GameScr.imgBackItem, this.xyTiemNang[j][0], this.xyTiemNang[j][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
                g.drawRegion(GameScr.imgIconTN, 0, 19 * j, 19, 19, 0, this.xyTiemNang[j][0], this.xyTiemNang[j][1], mGraphics.VCENTER | mGraphics.HCENTER, false);
                if (a != null && j < a.length)
                    FontTeam.numberSmall_white.drawString(g, a[j], this.xyTiemNang[j][0], this.xyTiemNang[j][1] + this.size / 2 - 2 - ysss, 2, false);
                if (selected == j && selected > -1 && selected < this.xyTiemNang.length && isFocusDetailMenu)
                    paintFocus(g, this.xyTiemNang[j][0] - this.size / 2 + this.size / 2, this.xyTiemNang[j][1] - this.size / 2 + this.size / 2, 0, true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void paintPetWearing(mGraphics g, boolean isChar) {
        if (!isPet) return;
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing + 2;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2;
        g.setColor(-13232632);
        g.fillRect(xShowText1, yShowText1, wShowText1, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xShowText1 + 1, yShowText1 + 1, wShowText1 - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xShowText1 + 2, yShowText1 + 2, wShowText1 - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xShowText1 + 3, yShowText1 + 3, wShowText1 - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xShowText1, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xShowText1, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1, yShowText1, mGraphics.TOP | mGraphics.RIGHT, false);
        int xbg = this.x + this.size + 8;
        int ybg = yShowText1;
        if (GameCanvas.w > 200) {
            g.setColor(-9751532);
            int hh0 = hShowText1 + (this.isAnimal ? this.size : 0);
            g.fillRect(xbg + 3, ybg, 2 * this.size - 6, hh0, false);
            g.fillRect(xbg, ybg + 3, 2 * this.size, hh0 - 6, false);
            g.setColor(-4891370);
            g.drawLine(xbg + 3, ybg + 3 + hh0 - 6 + 1, xbg + 2 * this.size - 6, ybg + 3 + hh0 - 6 + 1, false);
            if (this.isFocusPetWearing && GameCanvas.gameTick / 4 % 4 != 0) {
                g.setColor(-12246258);
            } else {
                g.setColor(-14864849);
            }
            g.fillRect(xbg + 3, ybg + 3, 2 * this.size - 6, hh0 - 6, false);
            g.setColor(-110);
            g.fillRect(xbg + 3, ybg + 1, 2 * this.size - 6, 1, false);
            g.setColor(-4034289);
            g.fillRect(xbg + 1, ybg + 16, 1, hh0 - 18, false);
            g.fillRect(xbg - 2 + 2 * this.size, ybg + 16, 1, hh0 - 18, false);
        }
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 0, xbg, ybg, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 0, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.TOP | mGraphics.RIGHT, false);
        ybg += hShowText1 + (this.isAnimal ? this.size : 0);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 0, xbg, ybg, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, xbg + 2 * this.size, ybg, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        for (int i = 0; i < 4; i++) {
            g.drawRegion(GameScr.petinfo, 0, 10 * i, 10, 10, 0, this.xyTiemNang[0][0] - 20, this.xyTiemNang[0][1] + i * 18 - 18, 0, false);
            if (petInfo != null)
                this.petfont[i].drawString(g, getPercent(petInfo[i]), this.xyTiemNang[0][0] - 5, this.xyTiemNang[0][1] + i * 18 - 18 + 1, 0, false);
        }
        g.fillRect(0, 0, 0, 0, false);
        paintCell(g);
        if (this.mpet != null)
            this.mpet.paintnoHeight(g, xbg + this.size - 1, this.y + this.h - 2 * this.size - this.size / 2 - (GameCanvas.isSmallScreen ? 24 : 38) + this.hIP);
        if (cmdChoan != null && !GameCanvas.isTouch) cmdChoan.paint(g);
    }

    public void paintCell(mGraphics g) {
        GameCanvas.resetTrans(g);
        int maxx = vecPetEat.size();
        int t = maxx / this.colum + ((vecPetEat.size() % this.colum != 0) ? 1 : 0);
        int hip3 = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) hip3 = -5;
        this.scrDapdo.setStyle(t + 3, this.sizeDapdo + 2, this.xWearing[0] - 10, this.yWearing[0] + 9, this.colum * this.sizeDapdo, 5 * this.sizeDapdo, true, this.colum);
        g.ClipRec(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74 + hip3);
        this.scrDapdo.setClip(g, this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74 + hip3);
        int i;
        for (i = 0; i < maxx; i++) {
            g.setColor(-14458807);
            g.drawRect(this.xWearing[0] + i % this.colum * this.sizeDapdo + 2 - 5 - 10, this.yWearing[0] + i / this.colum * this.sizeDapdo + 10 - 2 + 5 - 9, this.sizeDapdo, this.sizeDapdo, true);
        }
        for (i = 0; i < vecPetEat.size(); i++) {
            Item it = (Item) vecPetEat.elementAt(i);
            if (it != null)
                it.paint(g, this.xWearing[0] + i % this.colum * this.sizeDapdo + 1, this.yWearing[0] + i / this.colum * this.sizeDapdo + this.sizeDapdo - 9, true);
        }
        if (GameCanvas.isTouch) {
            if (selected > -1 && selected < vecPetEat.size())
                paintFocus(g, this.xWearing[0] + selected % this.colum * this.sizeDapdo + 2 - 2, 1 + this.yWearing[0] + selected / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4, 3, true);
            paintFocus(g, this.xWearing[0] - 40, 1 + this.yWearing[0], 3, true);
        }
        if (!GameCanvas.isTouch && selected > -1 && selected < vecPetEat.size())
            paintFocus(g, this.xWearing[0] + selected % this.colum * this.sizeDapdo + 2 - 1, this.yWearing[0] + selected / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4, 3, true);
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    public void paintFeatures(mGraphics g) {
        int ybg = this.y + this.sizeH + 10;
        int w0 = FontTeam.smallFontArr[0].getWidth("Điểm: ");
        FontTeam.smallFontArr[0].drawString(g, "Điểm: ", this.xbg + 2, this.y + this.h - 15, 0, false);
        FontTeam.normalFont[1].drawString(g, "" + GameScr.mainChar.basePointLeft, this.xbg + 4 + w0, this.y + this.h - 14, 0, false);
        String[] a = {String.valueOf(GameScr.mainChar.strength) + "".trim(), String.valueOf(GameScr.mainChar.agility) + "".trim(), String.valueOf(GameScr.mainChar.spirit) + "".trim(), String.valueOf(GameScr.mainChar.health) + "".trim(), String.valueOf(GameScr.mainChar.luck) + "".trim()};
        for (int i = 0; i < feaTures.length; i++) {
            int id = 0;
            g.drawRegion(GameScr.imgTextfileld, 0, id * 18, 76, 18, 0, this.xbg + this.colum * this.size / 2, ybg + 6, mGraphics.VCENTER | mGraphics.HCENTER, false);
            if (a != null && i < a.length - 1)
                FontTeam.fontTile.drawString(g, String.valueOf(feaTures[i]) + ":" + a[i], this.xbg + this.colum * this.size / 2 - 35, ybg, 0, false);
            ybg += (GameCanvas.w < 200) ? 22 : 30;
        }
    }

    public void paintFocus(mGraphics g, int x, int y, int idimage, boolean isSetclip) {
        g.drawImage(GameScr.imgfocus[idimage], x, y, 3, isSetclip);
    }

    public void paintFocus(mGraphics g, int x, int y, int idimage, int ar, boolean isSetclip) {
        g.drawImage(GameScr.imgfocus[idimage], x, y, ar, isSetclip);
    }

    public void paintFocusInvem(mGraphics g, int x, int y, int w, int h, int color, boolean isClip) {
        g.setColor(color);
        g.fillRect(x, y, w, 1, isClip);
        g.fillRect(x, y, 1, h, isClip);
        g.fillRect(x + w, y, 1, h, isClip);
        g.fillRect(x, y + h, w, 1, isClip);
    }

    public void paintFocus(mGraphics g, int x, int y, int w, int h) {
        g.drawImage(GameScr.imgBoder[2], x - 2 + this.coutFc, y - 2 + this.coutFc, 0, false);
        g.drawRegion(GameScr.imgBoder[2], 0, 0, 8, 9, 2, x + w + 3 - this.coutFc, y - 2 + this.coutFc, mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[2], 0, 0, 8, 9, 7, x + w + 3 - this.coutFc, y + h + 3 - this.coutFc, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[2], 0, 0, 8, 9, 6, x - 2 + this.coutFc, y + h + 3 - this.coutFc, mGraphics.BOTTOM | mGraphics.LEFT, false);
    }

    private void paintSkill(mGraphics g) {
        int hkill = mGraphics.getImageHeight(GameScr.imgTouchMove[3]) / 2;
        int wkill = mGraphics.getImageWidth(GameScr.imgTouchMove[3]);
        GameCanvas.resetTrans(g);
        int sizesk = GameScr.vec_skill.size();
        this.scrSkill.setStyle((sizesk + 1) / 3, 40, this.xWearing[0] + 5 - 15, this.yWearing[0] + this.size * 2 - 15, this.wInfoWearing + 20 + this.size, this.size * 5, true, 3);
        g.ClipRec(this.xWearing[0] - this.size / 2, this.yWearing[0] + this.size + 7, this.wInfoWearing + 20 + this.size, this.size * 5 - 10);
        this.scrSkill.setClip(g, this.xWearing[0] - this.size / 2, this.yWearing[0] + this.size + 7, this.wInfoWearing + 20 + this.size, this.size * 5 - 10);
        for (int i = 0; i < sizesk; i++) {
            g.drawRegion(GameScr.imgTouchMove[3], 0, hkill, wkill, hkill, 0, this.xWearing[i] + 5, this.yWearing[i] + this.size * 2, 3, true);
            if (selected == i) paintFocus(g, this.xWearing[i] + 5, this.yWearing[i] + this.size * 2, 1, true);
            SkillTemplate skill = (SkillTemplate) GameScr.vec_skill.elementAt(i);
            ImageIcon imgskill = GameData.getImgIcon((short) (skill.iconId + ((Char.levelSkill[i] == -1) ? 200 : 0) + Res.ID_ICON_SKILL));
            if (imgskill != null && imgskill.img != null) {
                g.drawImage(imgskill.img, this.xWearing[i] + 5, this.yWearing[i] + this.size * 2, 3, true);
            } else {
                g.drawRegion(GameScr.imgloading, 0, GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0, this.xWearing[i] + 5, this.yWearing[i] + this.size * 2, 3, true);
            }
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        mFont.tahoma_7b_white.drawString(g, T.diem, this.xWearing[1] + this.size / 4, this.yWearing[0], 2, false);
        FontTeam.normalFont[1].drawString(g, "" + Char.Skill_Point, this.xWearing[1] + this.size / 4 + 20, this.yWearing[0], 0, false);
        g.setColor(-1136094);
        g.fillRect(this.xWearing[0], this.yWearing[0] + this.size - 3, this.wInfoWearing + 20, 1, false);
    }

    public void paintLoop(mGraphics g, int x, int y, Image img) {
        for (int i = GameCanvas.w % 64; i < GameCanvas.w + 64; i += 64) {
            for (int j = GameCanvas.h % 64; j < GameCanvas.h + 64; j += 64)
                g.drawImage(GameScr.imgBgMainMenu, GameCanvas.w - i, GameCanvas.h - j, mGraphics.TOP | mGraphics.LEFT, false);
        }
    }

    public void paintMoney(mGraphics g) {
        if (GameCanvas.isTouch) paintHP(g);
        if (GameCanvas.isTouch) {
            int xss = 0, yp = 0;
            yp = (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 4;
            xss = this.xShowText + this.wShowText - 42;
            String xu = Res.getDotNumber(GameScr.mainChar.charXu);
            String luong = Res.getDotNumber(GameScr.mainChar.luong);
            int ypp = 0;
            if (mSystem.isAndroid) ypp = -3;
            if (mSystem.isIP) ypp = -1;
            g.drawRegion(GameScr.imgMoney, 0, 7, 9, 7, 0, xss + 5, yp, 0, false);
            FontTeam.numberSmall_white.drawString(g, xu, xss, yp + ypp - 1, 1, false);
            g.drawRegion(GameScr.imgMoney, 0, 0, 9, 7, 0, xss + 5, yp + 11, 0, false);
            FontTeam.numberSmall_white.drawString(g, luong, xss, yp + 10 + ypp, 1, false);
        } else {
            int xpxu = 0, xpluong = 0, xss = 0, xmn = 0;
            String xu = Res.getDotNumber(GameScr.mainChar.charXu);
            String luong = Res.getDotNumber(GameScr.mainChar.luong);
            xpxu = FontTeam.numberSmall_white.getWidth(xu);
            xpluong = FontTeam.numberSmall_white.getWidth(luong);
            xss = (xpxu > xpluong) ? xpxu : xpluong;
            xmn = GameCanvas.w - 50 - xss;
            if (!GameCanvas.isTouch) xmn = 10;
            paintMoney(g, xmn, 5, xmn, 5, xu, luong);
        }
    }

    public void paintMoney(mGraphics g, int x1, int y1, int x2, int y2, String xu, String luong) {
        g.drawRegion(GameScr.imgMoney, 0, 7, 9, 7, 0, x1, y1, 0, false);
        FontTeam.numberSmall_white.drawString(g, xu, x1 + 10, y1 - 2, 0, false);
        g.drawRegion(GameScr.imgMoney, 0, 0, 9, 7, 0, x2, y2 + 9, 0, false);
        FontTeam.numberSmall_white.drawString(g, luong, x2 + 10, y2 + 9, 0, false);
    }

    public void setTile() {
        if (this.indexMainTab == 12 || this.indexMainTab == 8 || this.indexMainTab == 9 || this.indexMainTab == 10 || this.indexMainTab == 11)
            return;
        if (this.indexMainTab < mainTab.length) {
            this.currentTileMainTab = mainTab[this.indexMainTab];
        } else {
            this.currentTileMainTab = "";
        }
    }

    private void setSelectedKeepAndSellItem() {
        this.center = new mCommand("", this, 33);
    }

    public void paintHP(mGraphics g) {
        if (mSystem.isPC) return;
        int h123 = (this.y + this.sizeH - 18) / 2 + (this.y + this.sizeH - 18) / 2 - 1;
        int xp = this.x;
        g.drawRegion(GameScr.imghealth[2], 0, 0, mGraphics.getImageWidth(GameScr.imghealth[2]), mGraphics.getImageHeight(GameScr.imghealth[2]) / 2, 0, xp, h123, 0, false);
        g.drawRegion(GameScr.imghealth[2], 0, mGraphics.getImageHeight(GameScr.imghealth[2]) / 2, mGraphics.getImageWidth(GameScr.imghealth[2]), mGraphics.getImageHeight(GameScr.imghealth[2]) / 2, 0, xp, h123 + 10, 0, false);
        int hpPaint = 0, mpPaint = 0;
        if (GameScr.mainChar.hp > 0) {
            hpPaint = GameScr.mainChar.hp * 60 / GameScr.mainChar.maxhp;
            if (hpPaint <= 0) {
                hpPaint = 1;
            } else if (hpPaint > 60) {
                hpPaint = 60;
            }
            g.drawRegion(GameScr.imghealth[1], 0, 0, hpPaint, mGraphics.getImageHeight(GameScr.imghealth[1]) / 2, 0, xp + 1, h123 + 1, 0, true);
        }
        if (GameScr.mainChar.mp > 0) {
            mpPaint = GameScr.mainChar.mp * 60 / GameScr.mainChar.maxmp;
            if (mpPaint <= 0) {
                mpPaint = 1;
            } else if (mpPaint > 60) {
                mpPaint = 60;
            }
            g.drawRegion(GameScr.imghealth[1], 0, mGraphics.getImageHeight(GameScr.imghealth[1]) / 2, mpPaint, mGraphics.getImageHeight(GameScr.imghealth[1]) / 2, 0, xp + 1, h123 + 11, 0, true);
        }
        FontTeam.numberSmall_white.drawString(g, String.valueOf(GameScr.mainChar.hp) + "/" + GameScr.mainChar.maxhp, xp + 30, h123, 2, false);
        FontTeam.numberSmall_white.drawString(g, String.valueOf(GameScr.mainChar.mp) + "/" + GameScr.mainChar.maxmp, xp + 30, h123 + 10, 2, false);
    }

    public void paintChuyenhoa(mGraphics g) {
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing + 2;
        int xbg = this.x + this.size + 9;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        g.setColor(-13232632);
        g.fillRect(xbg, yShowText1, ws, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xbg + 1, yShowText1 + 1, ws - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xbg + 2, yShowText1 + 2, ws - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xbg + 3, yShowText1 + 3, ws - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1 - 1, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xbg, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xbg, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1 - 1, yShowText1, mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, -100, -100, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        GameCanvas.resetTrans(g);
        int maxx = vecItemCreate.size();
        if (maxx < 45) maxx = 45;
        int t = maxx / this.colum + ((MainChar.MaxInven % this.colum != 0) ? 1 : 0);
        int hip3 = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) hip3 = -5;
        this.scrDapdo.setStyle(t + 3, this.sizeDapdo + 2, this.xWearing[0] - 10, this.yWearing[0] + 9 + 10, this.colum * this.sizeDapdo, 5 * this.sizeDapdo, true, this.colum);
        g.ClipRec(this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        this.scrDapdo.setClip(g, this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        int i;
        for (i = 0; i < maxx; i++) {
            g.setColor(-14458807);
            g.drawRect(this.xWearing[0] + i % this.colum * this.sizeDapdo + 2 - 5 - 10, this.yWearing[0] + i / this.colum * this.sizeDapdo + 10 - 2 + 5 - 9 + 20, this.sizeDapdo, this.sizeDapdo, true);
        }
        for (i = 0; i < vecItemCreate.size(); i++) {
            Item it = (Item) vecItemCreate.elementAt(i);
            if (it != null)
                it.paint(g, this.xWearing[0] + i % this.colum * this.sizeDapdo + 1, this.yWearing[0] + i / this.colum * this.sizeDapdo + this.sizeDapdo - 9 + 20, true);
        }
        if (this.slDapdo1 > -1) {
            g.setColor(-1);
            g.drawRect(this.xWearing[0] + this.slDapdo1 % this.colum * this.sizeDapdo + 2 - 1 - 13, 1 + this.yWearing[0] + this.slDapdo1 / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4 + 7, 25, 25, true);
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        if (!mSystem.isj2me) {
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x + 1 + this.wShowText - 15, this.y + this.h, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x + this.wShowText + 1 - 15, this.y + this.sizeH, mGraphics.TOP | mGraphics.LEFT, false);
        }
        g.drawImage(GameScr.imgSao, this.xSao, this.ySao, 3, false);
        g.drawImage(GameScr.imgBackItem, this.xcenter, this.ycenter - 30 + 5, 3, false);
        g.drawImage(GameScr.imgBackItem, this.xcenter - 42 + 10, this.ycenter + 30, 3, false);
        g.drawImage(GameScr.imgBackItem, this.xcenter + 42 - 10, this.ycenter + 30, 3, false);
        if (itemChuyenHoa0 != null) itemChuyenHoa0.paintIconWearing(g, this.xcenter, this.ycenter - 30 + 5);
        if (itemChuyenHoa1 != null) itemChuyenHoa1.paintIconWearing(g, this.xcenter - 42 + 10, this.ycenter + 30);
        if (itemChuyenHoa2 != null) itemChuyenHoa2.paintIconWearing(g, this.xcenter + 42 - 10, this.ycenter + 30);
        g.setColor(-1);
        if (this.tickChuyenhoa == 0) g.drawRect(this.xcenter - 11, this.ycenter - 11 - 30 + 5, 21, 21, true);
        if (this.tickChuyenhoa == 1) g.drawRect(this.xcenter - 42 - 11 + 10, this.ycenter - 11 + 30, 21, 21, true);
        if (this.tickChuyenhoa == 2) g.drawRect(this.xcenter + 42 - 11 - 10, this.ycenter - 11 + 30, 21, 21, true);
        if (!mSystem.isj2me) {
            g.setColor(-4034289);
            g.fillRect(this.x + this.wShowText - 15, this.y + this.sizeH + 2, 1, this.h - this.sizeH - 3, false);
            g.setColor(-11262464);
            g.fillRect(this.x + this.wShowText - 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2, false);
            g.fillRect(this.x + this.wShowText + 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2, false);
        }
        if (effDapDo != null) effDapDo.paintTop(g, this.xcenter, this.ycenter - 30);
        paintEffectChuyenHoa(g);
    }

    public void paintEffectChuyenHoa(mGraphics g) {
        for (int i = 0; i < this.vEffect.size(); i++) {
            Effect e = (Effect) this.vEffect.elementAt(i);
            if (e != null) e.paint(g);
        }
        if (this.beginChedo) {
            Image img = EffectSkill.getImage(37);
            Image img2 = EffectSkill.getImage(42);
            try {
                if (img != null)
                    g.drawRegion(img, 0, this.f * EffectSkill.h[37], EffectSkill.w[37], EffectSkill.h[37], 0, this.xcenter, this.ycenter - 30 + 10, mGraphics.VCENTER | mGraphics.HCENTER, false);
                if (img2 != null) {
                    g.drawRegion(img2, 0, this.f * EffectSkill.h[42], EffectSkill.w[42], EffectSkill.h[42], 0, this.xcenter - 42 + 10, this.ycenter + 30, mGraphics.VCENTER | mGraphics.HCENTER, false);
                    if (itemChuyenHoa2 != null)
                        g.drawRegion(img2, 0, this.f * EffectSkill.h[42], EffectSkill.w[42], EffectSkill.h[42], 0, this.xcenter + 42 - 10, this.ycenter + 30, mGraphics.VCENTER | mGraphics.HCENTER, false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintPhiPhong(mGraphics g) {
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing + 2;
        int xbg = this.x + this.size + 9;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        g.setColor(-13232632);
        g.fillRect(xbg, yShowText1, ws, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xbg + 1, yShowText1 + 1, ws - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xbg + 2, yShowText1 + 2, ws - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xbg + 3, yShowText1 + 3, ws - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1 - 1, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xbg, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xbg, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1 - 1, yShowText1, mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, -100, -100, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        GameCanvas.resetTrans(g);
        int maxx = vecItemCreate.size();
        if (maxx < 45) maxx = 45;
        int t = maxx / this.colum + ((MainChar.MaxInven % this.colum != 0) ? 1 : 0);
        int hip3 = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) hip3 = -5;
        this.scrDapdo.setStyle(t + 3, this.sizeDapdo + 2, this.xWearing[0] - 10, this.yWearing[0] + 9 + 10, this.colum * this.sizeDapdo, 5 * this.sizeDapdo, true, this.colum);
        g.ClipRec(this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        this.scrDapdo.setClip(g, this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        int i;
        for (i = 0; i < maxx; i++) {
            g.setColor(-14458807);
            g.drawRect(this.xWearing[0] + i % this.colum * this.sizeDapdo + 2 - 5 - 10, this.yWearing[0] + i / this.colum * this.sizeDapdo + 10 - 2 + 5 - 9 + 20, this.sizeDapdo, this.sizeDapdo, true);
        }
        for (i = 0; i < vecItemCreate.size(); i++) {
            Item it = (Item) vecItemCreate.elementAt(i);
            if (it != null)
                it.paint(g, this.xWearing[0] + i % this.colum * this.sizeDapdo + 1, this.yWearing[0] + i / this.colum * this.sizeDapdo + this.sizeDapdo - 9 + 20, true);
        }
        if (this.slDapdo1 > -1)
            paintFocus(g, this.xWearing[0] + this.slDapdo1 % this.colum * this.sizeDapdo + 2 - 2, 1 + this.yWearing[0] + this.slDapdo1 / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4 + 20 - 1, 3, true);
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        if (!mSystem.isj2me) {
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x + 1 + this.wShowText - 15, this.y + this.h, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x + this.wShowText + 1 - 15, this.y + this.sizeH, mGraphics.TOP | mGraphics.LEFT, false);
        }
        g.drawImage(GameScr.imgBackItem, this.xcenter, this.ycenter, 3, false);
        g.drawImage(GameScr.imgSao, this.xcenter, this.ycenter, 3, false);
        int r = 40;
        for (int j = 0; j < 6; j++) {
            g.drawImage(GameScr.imgBackItem, Util.cos(j * 60 + 30) * r / 1024 + xbg + ws / 2, Util.sin(j * 60 + 30) * r / 1024 + yShowText1 + hShowText1 / 2, 3, false);
            if (this.typePhiPhong == 0) {
                Item itp = (Item) vecMaterial.elementAt(j);
                if (itp != null)
                    itp.paintIcon(g, Util.cos(j * 60 + 30) * r / 1024 + xbg + ws / 2 - 14, Util.sin(j * 60 + 30) * r / 1024 + yShowText1 + hShowText1 / 2 - 13);
            }
            if (this.typePhiPhong == 1 && this.listItem[j] != null)
                this.listItem[j].paintIconPP(g, this.postItem_PhiPhong_X[j], this.postItem_PhiPhong_Y[j]);
            FontTeam f = FontTeam.numberSmall_green;
            if (this.listNum[j] < this.miniItem || this.listNum[j] == 0) f = FontTeam.numberSmall_red;
            f.drawString(g, (new StringBuilder(String.valueOf(this.miniItem))).toString(), Util.cos(j * 60 + 30) * r / 1024 + xbg + ws / 2 - 14 + this.xsai[j] - 13, Util.sin(j * 60 + 30) * r / 1024 + yShowText1 + hShowText1 / 2 - 13 + 8, 0, false);
            if (this.FocusPhiPhong == j) {
                g.setColor(-1);
                g.drawRect(this.postItem_PhiPhong_X[j] + 2, this.postItem_PhiPhong_Y[j] + 2, 21, 21, true);
            }
        }
        if (idicon != -1) {
            if (this.typePhiPhong == 1 && this.itemPP != null)
                this.itemPP.paintIconWearing(g, this.xcenter, this.ycenter);
            if (this.typePhiPhong == 0) {
                ImageIcon img = GameData.getImgIcon((short) (idicon + Res.ID_ITEM));
                if (img != null && img.img != null) {
                    g.drawImage(img.img, this.xcenter, this.ycenter, 3, false);
                } else {
                    g.drawRegion(GameScr.imgloading, 0, GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0, this.xcenter, this.ycenter, 3, true);
                }
            }
        }
        if (!mSystem.isj2me) {
            g.setColor(-4034289);
            g.fillRect(this.x + this.wShowText - 15, this.y + this.sizeH + 2, 1, this.h - this.sizeH - 3, false);
            g.setColor(-11262464);
            g.fillRect(this.x + this.wShowText - 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2, false);
            g.fillRect(this.x + this.wShowText + 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2, false);
        }
        if (effDapDo != null) effDapDo.paintTop(g, this.xcenter, this.ycenter);
        paintEffect(g);
        if (xuCuongHoa > 0) {
            g.drawRegion(GameScr.imgMoney, 0, (moneyType == 1) ? 0 : 7, 9, 7, 0, this.xyDapDo[1][0] - 15 - 58, this.xyDapDo[1][1] + 19 + 15, 0, false);
            FontTeam.numberSmall_white.drawString(g, Res.getDotNumber(xuCuongHoa), this.xyDapDo[1][0] - 5 - 58, this.xyDapDo[1][1] + 18 + 15, 0, false);
        }
    }

    public void paintCreateItem(mGraphics g) {
        int yShowText1 = this.yInfoWearing + 2;
        int xShowText1 = this.xInfoWearing;
        int wShowText1 = this.wInfoWearing + 2;
        int xbg = this.x + this.size + 9;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        g.setColor(-13232632);
        g.fillRect(xbg, yShowText1, ws, hShowText1, false);
        g.setColor(-1596632);
        g.fillRect(xbg + 1, yShowText1 + 1, ws - 2, hShowText1 - 2, false);
        g.setColor(-13232632);
        g.fillRect(xbg + 2, yShowText1 + 2, ws - 4, hShowText1 - 4, false);
        g.setColor(-14864849);
        g.fillRect(xbg + 3, yShowText1 + 3, ws - 6, hShowText1 - 6, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 7, xShowText1 + wShowText1 - 1, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 1, xbg, yShowText1 + hShowText1, mGraphics.BOTTOM | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 0, xbg, yShowText1, mGraphics.TOP | mGraphics.LEFT, false);
        g.drawRegion(GameScr.imgBoder[6], 0, 0, 9, 9, 2, xShowText1 + wShowText1 - 1, yShowText1, mGraphics.TOP | mGraphics.RIGHT, false);
        g.drawRegion(GameScr.imgBoder[4], 0, 16, 16, 16, 2, -100, -100, mGraphics.BOTTOM | mGraphics.RIGHT, false);
        GameCanvas.resetTrans(g);
        int maxx = vecItemCreate.size();
        if (maxx < 45) maxx = 45;
        int t = maxx / this.colum + ((MainChar.MaxInven % this.colum != 0) ? 1 : 0);
        int hip3 = 0;
        if (mSystem.isIP && mGraphics.zoomLevel == 3) hip3 = -5;
        this.scrDapdo.setStyle(t + 3, this.sizeDapdo + 2, this.xWearing[0] - 10, this.yWearing[0] + 9 + 10, this.colum * this.sizeDapdo, 5 * this.sizeDapdo, true, this.colum);
        g.ClipRec(this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        this.scrDapdo.setClip(g, this.xWearing[0] - 20, this.yWearing[0] + 3 + 20, 142, 74 + hip3 - 20);
        int i;
        for (i = 0; i < maxx; i++) {
            g.setColor(-14458807);
            g.drawRect(this.xWearing[0] + i % this.colum * this.sizeDapdo + 2 - 5 - 10, this.yWearing[0] + i / this.colum * this.sizeDapdo + 10 - 2 + 5 - 9 + 20, this.sizeDapdo, this.sizeDapdo, true);
        }
        for (i = 0; i < vecItemCreate.size(); i++) {
            Item it = (Item) vecItemCreate.elementAt(i);
            if (it != null)
                it.paint(g, this.xWearing[0] + i % this.colum * this.sizeDapdo + 1, this.yWearing[0] + i / this.colum * this.sizeDapdo + this.sizeDapdo - 9 + 20, true);
        }
        if (this.slDapdo1 > -1)
            paintFocus(g, this.xWearing[0] + this.slDapdo1 % this.colum * this.sizeDapdo + 2 - 2, 1 + this.yWearing[0] + this.slDapdo1 / this.colum * this.sizeDapdo + this.sizeDapdo / 2 + 4 + 20 - 1, 3, true);
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        if (!mSystem.isj2me) {
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x + 1 + this.wShowText - 15, this.y + this.h, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x + this.wShowText + 1 - 15, this.y + this.sizeH, mGraphics.TOP | mGraphics.LEFT, false);
        }
        g.drawImage(GameScr.imgBackItem, this.xcenter, this.ycenter, 3, false);
        g.drawImage(GameScr.imgSao, this.xcenter, this.ycenter, 3, false);
        int r = 40;
        for (int j = 0; j < 6; j++) {
            g.drawImage(GameScr.imgBackItem, Util.cos(j * 60 + 30) * r / 1024 + xbg + ws / 2, Util.sin(j * 60 + 30) * r / 1024 + yShowText1 + hShowText1 / 2, 3, false);
            Item itp = (Item) vecMaterial.elementAt(j);
            if (itp != null)
                itp.paintIcon(g, Util.cos(j * 60 + 30) * r / 1024 + xbg + ws / 2 - 14, Util.sin(j * 60 + 30) * r / 1024 + yShowText1 + hShowText1 / 2 - 13);
            FontTeam.numberSmall_white.drawString(g, (new StringBuilder(String.valueOf(this.miniItem))).toString(), Util.cos(j * 60 + 30) * r / 1024 + xbg + ws / 2 - 14 + this.xsai[j], Util.sin(j * 60 + 30) * r / 1024 + yShowText1 + hShowText1 / 2 - 13 + 8, 0, false);
            FontTeam.numberSmall_white.drawString(g, "/", Util.cos(j * 60 + 30) * r / 1024 + xbg + ws / 2 - 14 + this.xsai[j] - 5, Util.sin(j * 60 + 30) * r / 1024 + yShowText1 + hShowText1 / 2 - 13 + 8, 0, false);
            FontTeam f = FontTeam.numberSmall_green;
            if (this.listNum[j] < this.miniItem || this.listNum[j] == 0) f = FontTeam.numberSmall_red;
            f.drawString(g, (new StringBuilder(String.valueOf(this.listNum[j]))).toString(), Util.cos(j * 60 + 30) * r / 1024 + xbg + ws / 2 - 14 + this.xsai[j] - 13, Util.sin(j * 60 + 30) * r / 1024 + yShowText1 + hShowText1 / 2 - 13 + 8, 0, false);
        }
        if (idicon != -1) {
            ImageIcon img = GameData.getImgIcon((short) (idicon + Res.ID_ITEM));
            if (img != null && img.img != null) {
                g.drawImage(img.img, this.xcenter, this.ycenter, 3, false);
            } else {
                g.drawRegion(GameScr.imgloading, 0, GameCanvas.gameTick % 12 * ChangScr.himg, ChangScr.wimg, ChangScr.himg, 0, this.xcenter, this.ycenter, 3, true);
            }
        }
        if (!mSystem.isj2me) {
            g.setColor(-4034289);
            g.fillRect(this.x + this.wShowText - 15, this.y + this.sizeH + 2, 1, this.h - this.sizeH - 3, false);
            g.setColor(-11262464);
            g.fillRect(this.x + this.wShowText - 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2, false);
            g.fillRect(this.x + this.wShowText + 1 - 15, this.y + this.sizeH + 2 + 1, 1, this.h - this.sizeH - 3 - 2, false);
        }
        if (effDapDo != null) effDapDo.paintTop(g, this.xcenter, this.ycenter);
        paintEffect(g);
    }

    public void paintEffect(mGraphics g) {
        for (int i = 0; i < this.vEffect.size(); i++) {
            Effect e = (Effect) this.vEffect.elementAt(i);
            if (e != null) e.paint(g);
        }
        if (this.beginChedo) {
            Image img = EffectSkill.getImage(37);
            Image img2 = EffectSkill.getImage(42);
            try {
                if (img != null)
                    g.drawRegion(img, 0, this.f * EffectSkill.h[37], EffectSkill.w[37], EffectSkill.h[37], 0, this.xcenter, this.ycenter, mGraphics.VCENTER | mGraphics.HCENTER, false);
                if (img2 != null) for (int j = 0; j < 6; j++)
                    g.drawRegion(img2, 0, this.f * EffectSkill.h[42], EffectSkill.w[42], EffectSkill.h[42], 0, Util.cos(j * 60 + this.goc) * this.r / 1024 + this.xcenter, Util.sin(j * 60 + this.goc) * this.r / 1024 + this.ycenter, mGraphics.VCENTER | mGraphics.HCENTER, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paint(mGraphics g) {
        try {
            int t, ybg, i;
            if (mSystem.isPC || (mSystem.isIP && mGraphics.zoomLevel > 3)) {
                if (this.lastSCR != null) this.lastSCR.paint(g);
                GameCanvas.resetTrans(g);
                g.fillRectAlpha(0, 0, GameCanvas.w, GameCanvas.h, 0, 60, false);
                g.setClip(this.x, this.y - 5 + 40, this.w + this.wShowText - 15, this.h + 5 - 40);
                g.ClipRec(this.x, this.y - 5 + 40, this.w + this.wShowText - 15, this.h + 5 - 40);
                g.fillRect(0, 0, 0, 0, false);
                for (int j = 0; j < (this.w + this.wShowText) / 32 + 1; j++) {
                    for (int k = 0; k < this.h / 32 + 1; k++)
                        g.drawImage(GameScr.imgBgMainMenu, this.x + j * 32, this.y + k * 32 - 5, 0, true);
                }
                g.restoreCanvas();
            } else {
                GameCanvas.resetTrans(g);
                for (int j = GameCanvas.w % 32; j < GameCanvas.w + 32; j += 32) {
                    for (int k = GameCanvas.h % 32; k < GameCanvas.h + 32; k += 32)
                        g.drawImage(GameScr.imgBgMainMenu, GameCanvas.w - j, GameCanvas.h - k, mGraphics.TOP | mGraphics.LEFT, false);
                }
            }
            int xbg = this.x + this.size + 8;
            int x000 = this.x;
            int wwz = GameCanvas.isTouch ? (this.xShowText + this.wShowText - xbg - 1) : (this.w - this.size - 8);
            GameCanvas.resetTrans(g);
            if (GameCanvas.w > 200) {
                g.setColor(-11262464);
                g.drawRect(this.x, this.y + this.sizeH, this.size + 6, this.h - this.sizeH - 1, false);
                g.drawRect(this.x + 2, this.y + this.sizeH + 2, this.size + 2, this.h - this.sizeH - 5, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.x + this.size + 6, this.y + this.h - 1, mGraphics.BOTTOM | mGraphics.RIGHT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x, this.y + this.h - 1, mGraphics.BOTTOM | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x, this.y + this.sizeH, mGraphics.TOP | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.x + this.size + 6, this.y + this.sizeH, mGraphics.TOP | mGraphics.RIGHT, false);
                paintBgSub(g, this.x + this.size + 6, this.y + this.sizeH, wwz + 2, this.h - this.sizeH - 1, false);
                if (!isPaintSub()) {
                    g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.x + this.size + 8 + wwz + 1, this.y + this.h, mGraphics.BOTTOM | mGraphics.RIGHT, false);
                    g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x + this.size + 8 - 2, this.y + this.h, mGraphics.BOTTOM | mGraphics.LEFT, false);
                    g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x + this.size + 8 - 2, this.y + this.sizeH, mGraphics.TOP | mGraphics.LEFT, false);
                    g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.x + this.size + 8 + wwz + 1, this.y + this.sizeH, mGraphics.TOP | mGraphics.RIGHT, false);
                }
                g.setColor(-4034289);
                g.drawRect(this.x + 1, this.y + this.sizeH + 1, this.size + 4, this.h - this.sizeH - 3, false);
            }
            if (GameCanvas.isTouch) {
                GameCanvas.resetTrans(g);
                int hc = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
                int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
                g.drawRegion(GameScr.imgButton[4], 0, (this.isFocusClose ? 1 : 0) * hc, wc, hc, 0, this.xShowText + this.wShowText - 21, (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 7, 0, false);
            }
            GameCanvas.resetTrans(g);
            int www = (GameCanvas.isTouch ? (this.xShowText + this.wShowText - 1) : (this.w + this.x)) - x000;
            if (!GameCanvas.isTouch) www = this.w;
            if (!GameCanvas.isTouch) {
                g.setColor(-9751532);
                g.drawRect(this.x, this.y + 2, www, this.sizeH - 5, false);
                g.setColor(-4034800);
                g.drawRect(this.x + 1, this.y + 2 + 1, www - 2, this.sizeH - 7, false);
                g.drawRect(this.x + 2, this.y + 2, www - 4, this.sizeH - 4, false);
                for (int j = 0; j < 7; j++)
                    g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, GameCanvas.hw - 42 + j * 12, this.y + this.sizeH / 2, mGraphics.VCENTER | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, GameCanvas.hw - 44, this.y + this.sizeH / 2, mGraphics.VCENTER | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, GameCanvas.hw + 44 + 1, this.y + this.sizeH / 2 + 1, mGraphics.VCENTER | mGraphics.RIGHT, false);
                FontTeam.fontTile.drawString(g, this.currentTileMainTab, GameCanvas.w / 2, this.y + this.sizeH / 2 - 6, 2, false);
            }
            switch (this.indexMainTab) {
                case 0:
                    t = this.mShop.size() / this.colum + ((this.mShop.size() % this.colum != 0) ? 1 : 0);
                    ybg = this.y + this.h - 5 * this.size - 4 + this.hIP;
                    this.cmrItem.setStyle(t, this.size, xbg + 2, ybg, this.colum * this.size, 5 * this.size - this.hIP, true, this.colum);
                    g.ClipRec(this.cmrItem.xPos - 1, this.cmrItem.yPos, this.cmrItem.width + 2, this.cmrItem.height + 2);
                    this.cmrItem.setClip(g, this.cmrItem.xPos - 1, this.cmrItem.yPos, this.cmrItem.width + 2, this.cmrItem.height + 2);
                    for (i = 0; i < this.mShop.size(); i++) {
                        g.setColor(-16114410);
                        g.fillRect(xbg + i % this.colum * this.size + 2, ybg + i / this.colum * this.size, this.size, this.size, true);
                    }
                    for (i = 0; i < this.mShop.size(); i++) {
                        g.setColor(-14458807);
                        g.drawRect(xbg + i % this.colum * this.size + 2, ybg + i / this.colum * this.size, this.size, this.size, true);
                        if (i < this.mShop.size()) {
                            Item itp = (Item) this.mShop.elementAt(i);
                            itp.paint(g, xbg + i % this.colum * this.size + this.size / 2 + 3, ybg + i / this.colum * this.size + this.size / 2, true);
                        }
                    }
                    if (selected > -1 && selected < this.mShop.size())
                        paintFocus(g, xbg + selected % this.colum * this.size + 2 + this.size / 2, ybg + selected / this.colum * this.size + this.size / 2, 3, true);
                    mGraphics.resetTransAndroid(g);
                    g.restoreCanvas();
                    GameCanvas.resetTrans(g);
                    paintBgSub(g, this.cmrItem.xPos - 3, this.cmrItem.yPos - 3, this.cmrItem.width + 7, this.cmrItem.height + 6, false);
                    break;
                case 1:
                    t = MainChar.MaxInven / this.colum + ((MainChar.MaxInven % this.colum != 0) ? 1 : 0);
                    ybg = this.y + this.h - 5 * this.size - 4 + this.hIP;
                    this.cmrItem.setStyle(t, this.size, xbg + 2, ybg, this.colum * this.size, 5 * this.size - this.hIP, true, this.colum);
                    g.ClipRec(this.cmrItem.xPos - 1, this.cmrItem.yPos, this.cmrItem.width + 2, this.cmrItem.height + 2);
                    this.cmrItem.setClip(g, this.cmrItem.xPos - 1, this.cmrItem.yPos, this.cmrItem.width + 2, this.cmrItem.height + 2);
                    for (i = 0; i < MainChar.MaxInven; i++) {
                        g.setColor(-16114410);
                        g.fillRect(xbg + i % this.colum * this.size + 2, ybg + i / this.colum * this.size, this.size, this.size, true);
                    }
                    for (i = 0; i < MainChar.MaxInven; i++) {
                        g.setColor(-14458807);
                        g.drawRect(xbg + i % this.colum * this.size + 2, ybg + i / this.colum * this.size, this.size, this.size, true);
                        if (i < Char.inventory.size()) {
                            Item it = (Item) Char.inventory.elementAt(i);
                            it.paint(g, xbg + i % this.colum * this.size + this.size / 2 + 3, ybg + i / this.colum * this.size + this.size / 2, true);
                        }
                    }
                    if (selected > -1 && selected < Char.inventory.size())
                        paintFocus(g, xbg + selected % this.colum * this.size + 2 + this.size / 2, ybg + selected / this.colum * this.size + this.size / 2, 3, true);
                    mGraphics.resetTransAndroid(g);
                    g.restoreCanvas();
                    GameCanvas.resetTrans(g);
                    paintBgSub(g, this.cmrItem.xPos - 3, this.cmrItem.yPos - 3, this.cmrItem.width + 7, this.cmrItem.height + 6, false);
                    break;
                case 11:
                    paintPhiPhong(g);
                    break;
                case 9:
                    paintCreateItem(g);
                    break;
                case 10:
                    paintChuyenhoa(g);
                    break;
                case 8:
                    paintDapDo(g, false);
                    break;
                case 2:
                    paintCharWearing(g, true);
                    break;
                case 12:
                    paintPetWearing(g, true);
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    if (isPaintSub()) {
                        switch (this.indexMainTab) {
                            case 3:
                                if (this.isSkill) {
                                    paintSkill(g);
                                    break;
                                }
                                if (!this.isFeatures && !this.isCharWearing) {
                                    if (this.isQuest) {
                                        GameCanvas.resetTrans(g);
                                        int ww = GameCanvas.isTouch ? this.wShowText : (6 * this.size);
                                        paintListQuest(g, xbg, this.y + this.sizeH + 4, ww, false);
                                        break;
                                    }
                                    if (this.isAnimal) paintCharWearing(g, false);
                                }
                                break;
                            case 4:
                                if (this.isQuest) {
                                    GameCanvas.resetTrans(g);
                                    int ww = GameCanvas.isTouch ? this.wShowText : (6 * this.size);
                                    paintListQuest(g, xbg, this.y + this.sizeH + 4, ww, false);
                                }
                                break;
                            case 6:
                                if (this.isQuestClan) {
                                    int ww = GameCanvas.isTouch ? this.wShowText : (6 * this.size);
                                    paintQuest(g, xbg, this.y + this.sizeH + 4, ww, true);
                                }
                                break;
                        }
                        break;
                    }
                    if (this.indexMainTab == 5) {
                        int yyy = this.y + this.sizeH + 6;
                        this.cmrItem.setStyle(currnentTabDetail.length, 50, xbg - 1, yyy, this.colum * this.size, this.h - this.sizeH - 6, true, 0);
                        this.cmrItem.setClip(g);
                        int j;
                        for (j = 0; j < currnentTabDetail.length; j++) {
                            g.drawImage(GameScr.imgIconGold, xbg + 25, yyy + j * 50 + 20, mGraphics.VCENTER | mGraphics.HCENTER, false);
                            FontTeam.fontTile.drawString(g, currnentTabDetail[j], xbg + 48, yyy + j * 50 + 20 - FontTeam.fontTile.getHeight() / 2, 0, false);
                        }
                        for (j = 0; j < currnentTabDetail.length; j++) {
                            g.drawImage(GameScr.imgBoder[6], xbg + 4, yyy + j * 50, 0, false);
                            g.drawRegion(GameScr.imgBoder[6], 0, 0, 20, 40, 2, xbg + 44, yyy + j * 50, mGraphics.TOP | mGraphics.RIGHT, false);
                        }
                        if (selected >= 0 && selected <= currnentTabDetail.length - 1)
                            paintFocus(g, xbg + 4, yyy + selected * 50, 40, 40);
                        GameCanvas.resetTrans(g);
                        paintBgSub(g, xbg - 1, this.y + this.sizeH, this.size * this.colum + 7, this.h - this.sizeH, false);
                        break;
                    }
                    if (this.indexMainTab != 12) {
                        GameCanvas.resetTrans(g);
                        int ww = GameCanvas.isTouch ? this.wShowText : (6 * this.size);
                        paintListQuest(g, xbg, this.y + this.sizeH + 4, ww, false);
                        if (isPaintSub())
                            paintBgSub(g, xbg - 1, this.y + this.sizeH, this.size * this.colum + 7, this.h - this.sizeH, false);
                    }
                    break;
            }
            if (isPaintSub()) paintShowText(g);
        } catch (Exception e) {
            e.printStackTrace();
        }
        GameCanvas.resetTrans(g);
        if (this.indexMainTab == 0 || this.indexMainTab == 1 || isTabQuest()) {
            int h00 = this.h - 5 * this.size - 9 - this.sizeH;
            int yIP = 0;
            if (mSystem.isIP && mGraphics.zoomLevel == 3) yIP = 3;
            int yss = 3;
            if (this.isQuest) {
                yss = 0;
                g.drawImage(GameScr.imgArrow[0], this.xbg - this.countL + 6, this.y + this.sizeH + h00 / 2 + this.hIP / 2 + yIP + yss, mGraphics.VCENTER | mGraphics.LEFT, false);
                int w0 = mGraphics.getImageWidth(GameScr.imgArrow[0]);
                int h0 = mGraphics.getImageHeight(GameScr.imgArrow[0]);
                g.drawRegion(GameScr.imgArrow[0], 0, 0, w0, h0, 2, this.xbg + this.colum * this.size + this.countR - 6, this.y + this.sizeH + h00 / 2 + this.hIP / 2 + yIP + yss, mGraphics.VCENTER | mGraphics.RIGHT, false);
            }
            if (!isTabQuest()) {
                mFont.tahoma_7b_white.drawString(g, this.currentTileMainTab, this.xbg + (this.size * this.colum + 7) / 2 - 3, this.y + this.sizeH + h00 / 2 - 6 + this.hIP / 2 + yIP + yss - 1, 2, false);
            } else if (indexTypeQuest < QuestTile.length) {
                mFont.tahoma_7b_white.drawString(g, QuestTile[indexTypeQuest], this.xbg + (this.size * this.colum + 7) / 2 - 3, this.y + this.sizeH + h00 / 2 - 6 + this.hIP / 2 + yIP + 1 + yss - 1, 2, false);
            }
        }
        if (GameCanvas.isTouch) {
            GameCanvas.resetTrans(g);
            int hc = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
            int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
            g.drawRegion(GameScr.imgButton[4], 0, (this.isFocusClose ? 1 : 0) * hc, wc, hc, 0, this.xShowText + this.wShowText - 21, (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 7, 0, false);
            if (this.cmdSelectItem != null && this.isSetXYCmdSelect) this.cmdSelectItem.paint(g);
        }
        paintMoney(g);
        if (this.isTextmua) paintPopUp(g, this.xShowText, this.y + this.hShowText - this.arrayText.length * 8);
        paintIconMain(g);
        super.paint(g);
    }

    public void paintIconMain(mGraphics g) {
        GameCanvas.resetTrans(g);
        if (mSystem.isPC || mSystem.isIP) g.fillRect(0, 0, 0, 0, false);
        this.scrMainmenu.setStyle(this.numtab, 30, this.x, this.y + 37, 30, this.h - 48, true, 1);
        g.ClipRec(this.x, this.y + 48, 30, this.h - 50);
        this.scrMainmenu.setClip(g, this.x, this.y + 48, 30, this.h - 50);
        if (this.indexMainTab != 8 && this.indexMainTab != 9 && this.indexMainTab != 10 && this.indexMainTab != 11)
            for (int i = 0; i < this.numtab; i++) {
                if (this.maptopaintMenuIcon[i] != -1) {
                    g.drawRegion(GameScr.imgMainMenu1, 0, this.maptopaintMenuIcon[i] * mGraphics.getImageHeight(GameScr.imgMainMenu1) / 8, mGraphics.getImageWidth(GameScr.imgMainMenu1), mGraphics.getImageHeight(GameScr.imgMainMenu1) / 8, 0, this.x + this.size / 2 + 3, this.y + i * this.size + this.sizeH + this.size / 2 + ((GameCanvas.w > 200) ? 10 : 8), mGraphics.VCENTER | mGraphics.HCENTER, true);
                } else {
                    g.drawRegion(GameScr.peticon, 0, 0, 22, 22, 0, this.x + this.size / 2 + 3, this.y + 5 * this.size + this.sizeH + this.size / 2 + ((GameCanvas.w > 200) ? 10 : 8), 3, true);
                }
            }
        if (this.indexMainTab < this.maptopaintMenuIcon.length && this.indexMainTab != -1 && this.indexMainTab != 8 && this.indexMainTab != 9 && this.indexMainTab != 10 && this.indexMainTab != 11) {
            int ys = 0;
            if (this.isQuest && GameCanvas.isTouch) ys = 1;
            g.drawRegion(GameScr.imgMainMenu, 0, this.maptopaintMenuIcon[this.indexMainTab + ys] * mGraphics.getImageHeight(GameScr.imgMainMenu1) / 8, mGraphics.getImageWidth(GameScr.imgMainMenu1), mGraphics.getImageHeight(GameScr.imgMainMenu1) / 8, 0, this.x + this.size / 2 + 3, this.y + (this.indexMainTab + ys) * this.size + this.sizeH + this.size / 2 + ((GameCanvas.w > 200) ? 10 : 8), mGraphics.VCENTER | mGraphics.HCENTER, true);
        } else if (this.indexMainTab != -1 && this.indexMainTab != 8 && this.indexMainTab != 9 && this.indexMainTab != 10 && this.indexMainTab != 11) {
            g.drawRegion(GameScr.peticon, 0, this.indexPet * 22, 22, 22, 0, this.x + this.size / 2 + 3, this.y + 5 * this.size + this.sizeH + this.size / 2 + ((GameCanvas.w > 200) ? 10 : 8), 3, true);
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
    }

    private void paintQuest(mGraphics g, int x, int y, int ww, boolean isClan) {
    }

    private void paintCell(mGraphics g, int total, String[] info) {
    }

    public void paintBgSub(mGraphics g, int x, int y, int w, int h, boolean isBoder) {
        g.setColor(-9751532);
        g.drawRect(x, y, w, h, false);
        g.drawRect(x + 2, y + 2, w - 4, h - 4, false);
        if (isBoder) {
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, x + w, y + h, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, x, y + h, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, x, y, mGraphics.TOP | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, x + w, y, mGraphics.TOP | mGraphics.RIGHT, false);
        }
        g.setColor(-4034289);
        g.drawRect(x + 1, y + 1, w - 2, h - 2, false);
    }

    public void showTextmua() {
        this.isTextmua = true;
        cmdBuy.caption = T.yes;
        this.canbuy = true;
        this.arrayText = mFont.tahoma_7_white.splitFontArray(this.textHoimua, this.wShowText - 15);
    }

    public void paintPopUp(mGraphics g, int xp, int yp) {
        int wp = this.wShowText;
        int hp = 0;
        hp = this.yShowText + this.hShowText - yp;
        g.setColor(-9751532);
        g.fillRect(xp + 2, yp, wp - 4, 1, false);
        g.setColor(-4034289);
        g.fillRect(xp + 1 + 2, yp + 1, wp - 2 - 4, 1, false);
        g.setColor(-9751532);
        g.fillRect(xp + 2 + 1, yp + 2, wp - 4 - 2, 1, false);
        g.setColor(-16114410);
        g.fillRect(xp + 3 + 2, yp + 3, wp - 6 - 2, hp - 6, false);
        for (int i = 0; i < this.arrayText.length; i++)
            mFont.tahoma_7_white.drawString(g, this.arrayText[i], xp + 10, yp + 10 + i * 15, 0, false);
    }

    public void paintShowText(mGraphics g) {
        if (this.indexMainTab == 8 && effDapDo != null) return;
        GameCanvas.resetTrans(g);
        boolean isAlway = GameCanvas.isTouch;
        if (this.isShowText || (this.isSkill && GameCanvas.isTouch)) isAlway = true;
        if (isAlway) {
            g.setColor(-9751532);
            g.fillRect(this.xShowText, this.yShowText, this.wShowText, this.hShowText, false);
            g.setColor(-4034289);
            g.fillRect(this.xShowText + 1, this.yShowText + 1, this.wShowText - 2, this.hShowText - 2, false);
            g.setColor(-9751532);
            g.fillRect(this.xShowText + 2, this.yShowText + 2, this.wShowText - 4, this.hShowText - 4, false);
            g.setColor(-16114410);
            g.fillRect(this.xShowText + 3, this.yShowText + 3, this.wShowText - 6, this.hShowText - 6, false);
            if (GameCanvas.isTouch) {
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.xShowText + this.wShowText, this.yShowText + this.hShowText, mGraphics.BOTTOM | mGraphics.RIGHT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.xShowText, this.yShowText + this.hShowText, mGraphics.BOTTOM | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.xShowText, this.yShowText, mGraphics.TOP | mGraphics.LEFT, false);
                g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.xShowText + this.wShowText, this.yShowText, mGraphics.TOP | mGraphics.RIGHT, false);
            }
        }
        if (!this.isShowText) {
            GameCanvas.resetTrans(g);
            if (this.vHanhTrang != null && GameCanvas.isTouch && this.vHanhTrang.size() > 0 && GameCanvas.currentDialog == null && !GameCanvas.menu.showMenu)
                for (int k = 0; k < this.vHanhTrang.size(); k++) {
                    mCommand cmd = (mCommand) this.vHanhTrang.elementAt(k);
                    if (cmd != null) cmd.paint(g);
                }
            return;
        }
        int yy = 0;
        int yKham = 0, hCmdHanhTrang = 0;
        if (this.arrayKhamNgoc != null) yKham = 20;
        this.cmrShowText.setStyle(this.totalInfoshow + this.numItem * 2 + ((this.indexPaintLineSkill > 0) ? 1 : 0) + 1, this.disString, this.xShowText, this.yShowText, this.wShowText, this.hShowText - 8 - yKham - ((GameCanvas.isTouch && !this.cmdSelectItem.caption.equals("")) ? ScreenTeam.cmdH : 0) - (GameCanvas.isTouch ? hCmdHanhTrang : 0), true, 0);
        g.ClipRec(this.cmrShowText.xPos, this.cmrShowText.yPos + 2, this.cmrShowText.width, this.cmrShowText.height + 4 + yKham);
        this.cmrShowText.setClip(g, this.cmrShowText.xPos, this.cmrShowText.yPos + 2, this.cmrShowText.width, this.cmrShowText.height + 4 + yKham);
        for (int i = 0; i < this.showText.size(); i++) {
            InfoTextShow info = (InfoTextShow) this.showText.elementAt(i);
            if (info != null && info.info != null) {
                mFont f = info.f;
                if (i == 1 && (this.indexMainTab == 1 || this.indexMainTab == 0 || this.indexMainTab == 2 || this.indexMainTab == 8 || this.indexMainTab == 10 || this.indexMainTab == 11) && (this.numItemStart > 0 || this.numItemStart2 > 0)) {
                    yy += this.disString;
                    if (this.laststar > 1) for (int k = 0; k < this.numItemStart; k++)
                        g.drawRegion(GameScr.imgStart, 0, 0, 10, 10, 0, this.xShowText + 15 + k * 11, this.yShowText + yy + 3, 3, true);
                    if (this.isHalfstart)
                        g.drawRegion(GameScr.imgStart, 0, 40, 10, 10, 0, this.xShowText + 15 + ((this.laststar > 1) ? (this.numItemStart * 11) : 0), this.yShowText + yy + 3, 3, true);
                    if (this.runStart) {
                        if (this.speedStart < this.numItemStart)
                            g.drawRegion(GameScr.imgStart, 0, 10, 10, 10, 0, this.xShowText + 15 + this.speedStart * 11, this.yShowText + yy + 3, 3, true);
                        if (this.speedStart >= 1 && this.speedStart < this.numItemStart + 1)
                            g.drawRegion(GameScr.imgStart, 0, 20, 10, 10, 0, this.xShowText + 15 + (this.speedStart - 1) * 11, this.yShowText + yy + 3, 3, true);
                        if (this.speedStart >= 2 && this.speedStart < this.numItemStart + 2)
                            g.drawRegion(GameScr.imgStart, 0, 30, 10, 10, 0, this.xShowText + 15 + (this.speedStart - 2) * 11, this.yShowText + yy + 3, 3, true);
                    }
                    if (this.numItemStart2 > 0) {
                        yy += this.disString;
                        if (this.laststar2 > 1) for (int k = 0; k < this.numItemStart2; k++)
                            g.drawRegion(GameScr.imgStart, 0, 0, 10, 10, 0, this.xShowText + 15 + k * 11, this.yShowText + yy + 3, 3, true);
                        if (this.isHalfstart2)
                            g.drawRegion(GameScr.imgStart, 0, 40, 10, 10, 0, this.xShowText + 15 + ((this.laststar2 > 1) ? (this.numItemStart2 * 11) : 0), this.yShowText + yy + 3, 3, true);
                        if (this.runStart2) {
                            if (this.speedStart2 < this.numItemStart2)
                                g.drawRegion(GameScr.imgStart, 0, 10, 10, 10, 0, this.xShowText + 15 + this.speedStart2 * 11, this.yShowText + yy + 3, 3, true);
                            if (this.speedStart2 >= 1 && this.speedStart2 < this.numItemStart2 + 1)
                                g.drawRegion(GameScr.imgStart, 0, 20, 10, 10, 0, this.xShowText + 15 + (this.speedStart2 - 1) * 11, this.yShowText + yy + 3, 3, true);
                            if (this.speedStart2 >= 2 && this.speedStart2 < this.numItemStart2 + 2)
                                g.drawRegion(GameScr.imgStart, 0, 30, 10, 10, 0, this.xShowText + 15 + (this.speedStart2 - 2) * 11, this.yShowText + yy + 3, 3, true);
                        }
                    }
                }
                if (info.info != null && info.info[info.info.length - 1] != null && f != null && info.info[info.info.length - 1] != null) {
                    if (info.idCompare > -1 && info.idCompare == 3) f = mFont.tahoma_7_gray;
                    int ws = f.getWidth(info.info[info.info.length - 1]);
                    for (int j = 0; j < info.info.length; j++) {
                        if (this.isCharWearing && i == 0 && f == mFont.tahoma_7_white) f = mFont.tahoma_7b_white;
                        f.drawString(g, info.info[j], this.xShowText + 10, this.yShowText + 10 + yy, 0, true);
                        yy += this.disString;
                    }
                    if (info.idCompare > -1 && info.idCompare != 3)
                        g.drawImage(GameScr.imgSo[info.idCompare], this.xShowText + 10 + ws + 1, this.yShowText + 12 + yy - this.disString, 0, false);
                }
            } else {
                yy += this.disString / 2;
                g.setColor(-1136094);
                g.fillRect(this.xShowText + 15, this.yShowText + 10 + yy, this.wShowText - 30, 1, true);
                yy += this.disString / 2;
            }
        }
        mGraphics.resetTransAndroid(g);
        g.restoreCanvas();
        GameCanvas.resetTrans(g);
        if (this.isUseCmr) {
            g.drawImage(GameScr.imgSo[2], this.xShowText + this.wShowText - 3, this.yShowText + this.hShowText - 13, mGraphics.TOP | mGraphics.RIGHT, false);
            g.drawRegion(GameScr.imgSo[2], 0, 0, 7, 5, 3, this.xShowText + this.wShowText - 3, this.yShowText + 5, mGraphics.TOP | mGraphics.RIGHT, false);
        }
        GameCanvas.resetTrans(g);
        if (this.vHanhTrang != null && GameCanvas.isTouch && this.vHanhTrang.size() > 0 && GameCanvas.currentDialog == null && !GameCanvas.menu.showMenu)
            for (int k = 0; k < this.vHanhTrang.size(); k++) {
                mCommand cmd = (mCommand) this.vHanhTrang.elementAt(k);
                if (cmd != null) cmd.paint(g);
            }
    }

    public static boolean isDegit(char c) {
        return (c >= '0' && c <= '9');
    }

    public void setAutoActionCmd(mCommand cmd) {
    }

    public void switchTabDapDo(int index) {
        ID_CUONG_HOA = -1;
        effDapDo = null;
        textPercent = "";
        xuCuongHoa = 0;
        this.indexMainTab = index;
        this.slDapdo1 = -1;
        selected = -1;
        this.indexSubTab = 0;
        this.isCharWearing = false;
        this.isShowInFoChar = false;
        isFocusDetailMenu = false;
        this.isAnimal = false;
        this.isQuest = false;
        this.isFeatures = false;
        this.isHanhTrang = false;
        this.isSkill = false;
        this.isShowFill = false;
        this.isSkillClan = false;
        this.isQuestClan = false;
        itemStone = null;
        mItem = null;
        itemBaohiem = null;
        itemBua = null;
        this.numStone = 0;
        if (this.isMiniShop) this.isHanhTrang = true;
        reSetAllCmr();
        setPosScroll(this.indexMainTab, -1);
        setSelectTab("switchTabDapDo");
        setCmdCenter("switchTabDapDo");
        setTile();
        maxDap = vecItemDapDo.size();
        if (!GameCanvas.isTouch) {
            tabDapdo = 0;
            selected = -1;
            closeText();
            this.scrDapdo.moveTo(0);
            this.numItem = 0;
        }
        this.isFocusClose = false;
        this.currentTileMainTab = "Cường Hóa";
    }

    public void updateTouch() {
        if (delay >= 0) {
            delay--;
            return;
        }
        if (GameCanvas.currentDialog != null) return;
        if (this.indexMainTab == 8) return;
        this.isFocusClose = false;
        int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
        if (GameCanvas.isPointer(this.xShowText + this.wShowText - 21, (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 6, wc, wc, 0) && GameCanvas.canTouch()) {
            this.isFocusClose = true;
            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                doClose();
                GameCanvas.isPointerClick[0] = false;
            }
        }
        if (this.cmdSelectItem != null) {
            this.cmdSelectItem.isFocus = false;
            if (GameCanvas.isPointer(this.cmdSelectItem.x, this.cmdSelectItem.y, this.cmdSelectItem.wCmd, this.cmdSelectItem.hCmd, 0) && GameCanvas.canTouch()) {
                this.cmdSelectItem.isFocus = false;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0])
                    this.cmdSelectItem.performAction();
            }
        }
        int x0 = this.x + this.w - 7 - this.colum * this.size;
        int xTouch = this.x;
        int yTouch = this.y + this.sizeH + ((GameCanvas.w > 200) ? 10 : 8);
        int h0 = (mainTab.length + 1) * this.size;
        if (!isPet) h0 = mainTab.length * this.size;
        int w0 = x0 - this.x - 5;
        if (this.indexMainTab == 0 || (this.isHanhTrang && this.indexMainTab == 1)) {
            if (GameCanvas.canTouch()) if (GameCanvas.isPointer(x0 - 15, this.y + this.sizeH + 3, 25, 28, 0)) {
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    isChangeSubTab = true;
                    isFocusDetailMenu = true;
                    GameCanvas.keyPressedz[4] = true;
                    selected = this.lastSelect = -1;
                }
            } else if (GameCanvas.isPointer(x0 + this.size * this.colum - 40, this.y + this.sizeH + 3, 30, 28, 0) && GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                isChangeSubTab = true;
                isFocusDetailMenu = true;
                GameCanvas.keyPressedz[6] = true;
                selected = this.lastSelect = -1;
            }
        } else if (this.indexMainTab == 3) {
            if (this.isQuest) {
                if (GameCanvas.canTouch()) if (GameCanvas.isPointer(x0 - 15, this.y + this.sizeH + 3, 25, 28, 0)) {
                    if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                        isChangeSubTab = true;
                        isFocusDetailMenu = false;
                        this.countL = 5;
                        indexTypeQuest--;
                        if (indexTypeQuest < 0) indexTypeQuest = 0;
                        closeText();
                        movecmrQuest();
                        selected = this.lastSelect = -1;
                        GameCanvas.isPointerClick[0] = false;
                    }
                } else if (GameCanvas.isPointer(x0 + this.size * this.colum - 40, this.y + this.sizeH + 3, 30, 28, 0) && GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    isChangeSubTab = true;
                    isFocusDetailMenu = false;
                    this.countR = 5;
                    indexTypeQuest++;
                    if (indexTypeQuest > QuestTile.length - 1) indexTypeQuest = QuestTile.length - 1;
                    closeText();
                    movecmrQuest();
                    selected = this.lastSelect = -1;
                    GameCanvas.isPointerClick[0] = false;
                }
                if (selected != -1) {
                    indexQuest = selected;
                    if (ListQuest != null && indexTypeQuest >= 0 && indexTypeQuest < ListQuest.length) {
                        mVector currQuest = ListQuest[indexTypeQuest];
                        QuestInfo q = (QuestInfo) currQuest.elementAt(indexQuest);
                        mVector minfo = new mVector();
                        if (q != null) {
                            InfoTextShow in = new InfoTextShow(new String[]{q.name}, 0);
                            minfo.addElement(in);
                            String[] data = Util.split(q.info, "\n");
                            try {
                                for (int i = 0; i < data.length; i++) {
                                    if (data[i].length() > 5) {
                                        byte color = (byte) (data[i].charAt(0) - 48);
                                        if (!isDegit(data[i].charAt(0))) {
                                            color = 0;
                                        } else {
                                            data[i] = data[i].substring(1);
                                        }
                                        in = new InfoTextShow(new String[]{data[i]}, color);
                                        minfo.addElement(in);
                                    }
                                }
                            } catch (Exception exception) {
                            }
                        }
                        setShowText(minfo, this.xShowText, this.yShowText, null, true, false);
                        selected = -1;
                        this.vHanhTrang.removeAllElements();
                        this.vHanhTrang.addElement(cmdMapScr);
                        if (q != null && q.status == 2) this.vHanhTrang.addElement(cmdHuyQuest);
                        SortCmdItem();
                    }
                }
            } else if ((this.isAnimal && !this.isShowFill) || this.isSkill) {
                int hh = (this.isSkill ? 6 : 0) + 21;
                int hh1 = 0;
                if (this.isSkill) hh1 = this.size * 2 - this.size / 2;
                for (int i = 0; i < this.xWearing.length; i++)
                    ;
                if (GameCanvas.menu.showMenu) return;
                if (this.isSkill) this.cmdSelectItem.setXY(-100, -100);
                if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - this.size / 2, this.yWearing[0] + this.size + 7, this.wInfoWearing + 20 + this.size, this.size * 5 - 10, 0) && this.scrSkill.selectedItem != -1)
                    if (selected != this.scrSkill.selectedItem) {
                        setCmdCenter("updateTouch 1");
                        setCmdShowText();
                        selected = this.scrSkill.selectedItem;
                        this.cmdSelectItem.performAction();
                    } else {
                        this.cmdSelectItem.performAction();
                    }
            } else if (this.isFeatures) {
                w0 = mGraphics.getImageWidth(GameScr.imgTextfileld);
                h0 = mGraphics.getImageHeight(GameScr.imgTextfileld) / 2;
                xTouch = this.x + this.w - 7 - this.colum * this.size - w0 / 2 + 6 * this.size / 2;
                yTouch = this.y + this.sizeH + 16 - h0;
                int size1 = (GameCanvas.w < 200) ? 22 : 30;
                if (GameCanvas.isPointer(xTouch, yTouch, w0, feaTures.length * size1, 0) && GameCanvas.canTouch()) {
                    selected = (GameCanvas.py[0] - yTouch) / size1;
                    if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0])
                        if (this.lastSelect != selected) {
                            setCmdCenter("updateTouch 2");
                            this.lastSelect = selected;
                        } else {
                            this.cmdSelectItem.performAction();
                        }
                }
            }
            this.isFocusCloseItemFill = false;
            if (this.isShowFill) {
                int hc1 = mGraphics.getImageHeight(GameScr.imgButton[4]) / 2;
                int wc1 = mGraphics.getImageWidth(GameScr.imgButton[4]);
                if (GameCanvas.isPointer(this.xItemFill + this.wItemFill - wc - 2, this.yItemFill - 22, wc1, hc1, 0) && GameCanvas.canTouch()) {
                    this.isFocusCloseItemFill = true;
                    if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                        doClose();
                        this.cmrItem.clear();
                        this.lastSelect = -1;
                        selected = -1;
                    }
                }
            }
        } else if (this.indexMainTab == 1) {
            for (int i = 0; i < this.vHanhTrang.size(); i++) {
                mCommand cmd = (mCommand) this.vHanhTrang.elementAt(i);
                if (cmd != null && getCmdPointerLast(cmd)) {
                    GameCanvas.isPointerJustRelease[0] = false;
                    cmd.performAction();
                    break;
                }
                Cout.println(String.valueOf(i) + " getCmdPointerLast(cmd)  " + getCmdPointerLast(cmd));
            }
            int hh = (this.isSkill ? 6 : 0) + 21;
            for (int j = 0; j < this.xWearing.length; j++) {
                if (GameCanvas.isPointer(this.xWearing[j], this.yWearing[j], hh, hh, 0) && GameCanvas.canTouch()) {
                    selected = j;
                    if (this.isSkill) this.cmdSelectItem.setXY(-100, -100);
                    if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                        if (this.lastSelect != selected) {
                            setCmdCenter("updateTouch 3");
                            this.cmdShowText.performAction();
                            this.lastSelect = selected;
                            break;
                        }
                        this.cmdSelectItem.performAction();
                    }
                    break;
                }
            }
        } else if (this.indexMainTab == 2) {
            int hh = (this.isSkill ? 6 : 0) + 21;
            if (GameCanvas.isPointer(this.xbg, this.yInfoWearing, 2 * this.size, this.hInfoWearing - 36, 0) && GameCanvas.canTouch()) {
                int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y00 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                mVector minfo = new mVector();
                isFocusCharWearing = true;
                isFocusDetailMenu = false;
                minfo = this.infochar;
                this.vHanhTrang.removeAllElements();
                setShowText(minfo, x00, y00, null, false, true);
                this.numItemStart = 0;
                this.lastSelect = selected = -1;
            }
            int i;
            for (i = 0; i < this.xyTiemNang.length; i++) {
                if (GameCanvas.isPointer(this.xyTiemNang[i][0] - this.size / 2, this.xyTiemNang[i][1] - this.size / 2, this.size, this.size, 0) && GameCanvas.canTouch()) {
                    if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                        GameCanvas.isPointerClick[0] = false;
                        selected = i;
                        mVector minfo = new mVector();
                        InfoTextShow in = new InfoTextShow(new String[]{T.infoTiemNang[selected]}, 0);
                        minfo.addElement(in);
                        setShowText(minfo, this.xyTiemNang[selected][0] + 12, this.xyTiemNang[selected][1] + 12, null, true, true);
                        this.vHanhTrang.removeAllElements();
                        if (Char.Diemtiemnang > 0 && !this.isTextmua) {
                            this.vHanhTrang.addElement(cmdcong1);
                            if (Char.Diemtiemnang >= 5) this.vHanhTrang.addElement(cmdcong5);
                            if (Char.Diemtiemnang >= 10) this.vHanhTrang.addElement(cmdcong10);
                            SortCmdItem();
                        }
                        this.lastSelect = selected;
                    }
                    isFocusCharWearing = false;
                    isFocusDetailMenu = true;
                    break;
                }
            }
            for (i = 0; i < this.xWearing.length; i++) {
                if (GameCanvas.isPointer(this.xWearing[i] - this.size / 2, this.yWearing[i], hh, hh, 0) && GameCanvas.canTouch()) {
                    selected = i;
                    if (selected == 14) GameService.gI().dochangeCharWearing();
                    isFocusCharWearing = false;
                    isFocusDetailMenu = false;
                    this.vHanhTrang.removeAllElements();
                    if (this.isSkill) this.cmdSelectItem.setXY(-100, -100);
                    if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                        if (this.lastSelect != selected) {
                            setCmdCenter("updateTouch 4");
                            this.cmdShowText.performAction();
                            this.lastSelect = selected;
                            break;
                        }
                        this.cmdSelectItem.performAction();
                    }
                    break;
                }
            }
        }
    }

    public void doShowInfoItemWearing() {
        int x01 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
        int y01 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
        Item sItem1 = charWearing.equip[selected + this.indexWearing];
        if (sItem1 != null) showItemInventoryInfo(sItem1, this.isSell, x01, y01, null);
    }

    public void doShowInfoItemShop() {
        if (this.mShop.size() > 0) {
            int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            Item sItem = (Item) this.mShop.elementAt(selected);
            if (sItem != null) {
                showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                this.left = cmdBuy;
            }
        }
    }

    public void doShowInfoItemInventory() {
        if (this.indexMainTab == 1 && selected >= 0 && Char.inventory.size() > 0) {
            int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            Item sItem = (Item) Char.inventory.elementAt(selected);
            if (sItem != null) {
                int type = sItem.getTypeItem();
                Item itcp = null;
                if (POS_ITEM_IN_EQUIP[type] > -1) itcp = GameScr.mainChar.equip[POS_ITEM_IN_EQUIP[type]];
                showItemInventoryInfo(sItem, this.isSell, x0, y0, itcp);
            }
        }
    }

    public void doShowInfoPet() {
        if (GameScr.mainChar.equip != null && GameScr.mainChar.equip[14] != null) {
            int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            showItemInventoryInfo(GameScr.mainChar.equip[14], this.isSell, x0, y0, null);
        }
    }

    public void updatePetWearing() {
        if (!isPet) return;
        if (this.mpet != null) this.mpet.updateMenu();
        if (GameCanvas.isPointer(this.xbg, this.yInfoWearing, 2 * this.size, this.hInfoWearing - 36, 0) && GameCanvas.canTouch()) {
            this.FocusPet = -1;
            selected = -1;
            this.vHanhTrang.removeAllElements();
            this.isFocusPetWearing = true;
            if (GameScr.mainChar.equip != null && GameScr.mainChar.equip[14] != null) {
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                showItemInventoryInfo(GameScr.mainChar.equip[14], this.isSell, x0, y0, null);
            }
        }
        ScrollResult r = this.scrDapdo.updateKey();
        this.scrDapdo.updatecm();
        if (!GameCanvas.isTouch) {
            if (GameCanvas.isKeyPressed(6)) {
                selected++;
                if (selected > vecPetEat.size() - 1) selected = vecPetEat.size() - 1;
                if (selected >= 0) {
                    if (selected < vecPetEat.size() - 1 && selected > 1 && selected % 5 == 0)
                        this.scrDapdo.moveTo((selected / 5 + 1) * this.sizeDapdo);
                    int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecPetEat.elementAt(selected);
                    if (sItem != null) {
                        showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        setCmdCenter("updateDapDo 0");
                    }
                }
            }
            if (GameCanvas.isKeyPressed(4)) {
                if (selected >= 0) selected--;
                if (selected < 0) selected = 0;
                if (selected >= 0) {
                    int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecPetEat.elementAt(selected);
                    if (sItem != null) {
                        showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        setCmdCenter("updateDapDo 0");
                    }
                    if (selected < vecPetEat.size() - 1) this.scrDapdo.moveTo(selected / 5 * this.sizeDapdo);
                }
            }
        }
        if (GameCanvas.isKeyPressed(12) && !GameCanvas.isTouch && cmdChoan != null) cmdChoan.performAction();
        if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74, 0) && this.scrDapdo.selectedItem != -1 && selected != this.scrDapdo.selectedItem) {
            selected = this.scrDapdo.selectedItem;
            this.FocusPet = -1;
            this.isFocusPetWearing = false;
            this.vHanhTrang.removeAllElements();
            setCmdCenter("updateDapDo 0");
        }
    }

    public void updateKey() {
        if (GameCanvas.currentDialog != null) return;
        if (this.indexMainTab == 8) return;
        if (GameCanvas.isTouch) {
            updateTouch();
            updateMainTab();
        }
        if (GameCanvas.keyReleasedz[5] && !GameCanvas.isTouch) {
            if (this.indexMainTab == 12) {
                doShowInfoPet();
            } else if (this.indexMainTab == 1) {
                doShowInfoItemInventory();
            } else if (this.indexMainTab == 0) {
                doShowInfoItemShop();
            }
            GameCanvas.keyReleasedz[5] = false;
        }
        if (this.indexMainTab == 2 && mSystem.isj2me && GameCanvas.isKeyPressed(5)) if (this.indexWearing <= 0) {
            cmdTB2.performAction();
        } else {
            cmdTB1.performAction();
        }
        if (!this.isShowInFoChar || (GameCanvas.isTouch && this.isShowFill)) {
            if (this.cmrItem != null && !this.isFeatures && !this.isSkill) {
                ScrollResult scrollResult = this.cmrItem.updateKey();
                if (scrollResult.isDowning || scrollResult.isFinish) {
                    if (!this.isSkill) selected = scrollResult.selected;
                    if (this.isQuest) indexQuest = scrollResult.selected;
                }
                if (scrollResult.isFinish && GameCanvas.isTouch && scrollResult.selected != -1 && !this.isSkill) {
                    selected = scrollResult.selected;
                    this.cmdSelectItem.setXY(-100, -100);
                    this.isSetXYCmdSelect = true;
                    this.vHanhTrang.removeAllElements();
                    closeText();
                    switch (this.indexMainTab) {
                        case 0:
                            if (this.mShop.size() > 0) {
                                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                                Item sItem = (Item) this.mShop.elementAt(selected);
                                if (sItem != null) {
                                    showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                                    this.vHanhTrang.removeAllElements();
                                    this.vHanhTrang.addElement(cmdBuy);
                                    if (!captionServer.equals("")) {
                                        cmdBuy.caption = captionServer;
                                    } else {
                                        cmdBuy.caption = T.Buy;
                                    }
                                    this.canbuy = false;
                                    SortCmdItem();
                                }
                            }
                            break;
                        case 3:
                            if (selected <= currnentTabDetail.length - 1 && !isPaintSub()) {
                                isFocusDetailMenu = true;
                                setCmdCenter("updateKey 0");
                                if (this.center != null) this.center.performAction();
                            }
                            if (this.isQuest) {
                                indexQuest = selected;
                                break;
                            }
                            if (this.isShowFill) {
                                if (this.lastSelect != selected) {
                                    this.lastSelect = selected;
                                    setCmdCenter("updateKey 1");
                                    this.cmdShowText = new mCommand("", this, -4);
                                    this.timeAuToShowText = 2;
                                    this.beGinShowText = false;
                                    break;
                                }
                                setCmdCenter("updateKey 2");
                                this.cmdSelectItem.performAction();
                            }
                            break;
                        case 5:
                            isFocusDetailMenu = true;
                            if (this.lastSelect != selected) {
                                this.lastSelect = selected;
                                setPosScroll(this.indexMainTab, selected);
                                setCmdCenter("updateKey 3");
                                break;
                            }
                            setCmdCenter("updateKey 4");
                            this.cmdSelectItem.performAction();
                            break;
                        case 4:
                        case 6:
                        case 7:
                            isFocusDetailMenu = true;
                            if (this.lastSelect != selected) {
                                this.lastSelect = selected;
                                setCmdCenter("updateKey 5");
                                break;
                            }
                            setCmdCenter("updateKey 6");
                            this.cmdSelectItem.performAction();
                            break;
                        case 1:
                            if (this.lastSelect != selected) {
                                this.lastSelect = selected;
                                setCmdCenter("updateKey 7");
                                this.cmdShowText.performAction();
                                break;
                            }
                            if (GameCanvas.isTouch) {
                                setCmdCenter("updateKey 8");
                                this.cmdShowText.performAction();
                                break;
                            }
                            this.cmdSelectItem.performAction();
                            break;
                        case 2:
                            if (this.lastSelect != selected) {
                                this.lastSelect = selected;
                                setCmdCenter("updateKey 9");
                                this.cmdShowText.performAction();
                                break;
                            }
                            this.cmdSelectItem.performAction();
                            break;
                    }
                }
                if (!this.isSkill) this.cmrItem.updatecm();
            }
            if (this.cmrSubTab != null) {
                ScrollResult scrollResult = this.cmrSubTab.updateKey();
                if (!scrollResult.isDowning) ;
                this.cmrSubTab.updatecm();
            }
        } else if (this.cmrShowInfoMainChar != null) {
            ScrollResult scrollResult = this.cmrShowInfoMainChar.updateKey();
            this.cmrShowInfoMainChar.updatecm();
        }
        ScrollResult r = this.cmrShowText.updateKey();
        this.cmrShowText.updatecm();
        if (!isFocusDetailMenu) this.timeAuToShowText = 0;
        this.timeAuToShowText--;
        if (this.timeAuToShowText <= 0) this.timeAuToShowText = 0;
        setAutoActionCmd(this.cmdShowText);
        if (GameCanvas.isTouch) return;
        if (this.isShowFill) {
            if (GameCanvas.isKeyPressed(4)) {
                this.isMoveQuest = false;
                if (this.isUseCmr) {
                    closeText();
                    this.isShowFill = false;
                    return;
                }
                selected--;
                if (selected < 0) selected = this.totalItemFill.size() - 1;
                this.cmdShowText = new mCommand("", this, -4);
                this.timeAuToShowText = 15;
                this.beGinShowText = false;
            } else if (GameCanvas.isKeyPressed(6)) {
                if (this.isUseCmr) {
                    closeText();
                    this.isShowFill = false;
                    return;
                }
                selected++;
                if (selected > this.totalItemFill.size() - 1) selected = 0;
                this.cmdShowText = new mCommand("", this, -4);
                this.timeAuToShowText = 15;
                this.beGinShowText = false;
            } else if (GameCanvas.isKeyPressed(2)) {
                if (this.isUseCmr) this.cmrShowText.moveCmrTo(-1);
            } else if (GameCanvas.isKeyPressed(8) && this.isUseCmr) {
                this.cmrShowText.moveCmrTo(1);
            }
        } else if (GameCanvas.keyPressedz[6]) {
            this.isMoveQuest = false;
            GameCanvas.keyPressedz[6] = false;
            if (this.isQuest && !GameCanvas.isTouch) {
                this.countR = 5;
                indexTypeQuest = (indexTypeQuest + 1) % QuestTile.length;
                indexQuest = -1;
                movecmrQuest();
                return;
            }
            switch (this.indexMainTab) {
                case 2:
                    if (!isChangeSubTab) {
                        isFocusCharWearing = true;
                        isChangeSubTab = true;
                        selected = -1;
                    } else if (isFocusCharWearing) {
                        this.isShowInFoChar = true;
                        this.indexShowInfo = 0;
                        isFocusCharWearing = false;
                        this.isShowInFoChar = true;
                        isFocusDetailMenu = true;
                        selected = 0;
                        closeText();
                    } else if (!isFocusCharWearing && isFocusDetailMenu) {
                        closeText();
                        selected++;
                        if (selected >= 4) {
                            selected = 0;
                            isFocusDetailMenu = false;
                        } else {
                            mVector minfo = new mVector();
                            InfoTextShow in = new InfoTextShow(new String[]{T.infoTiemNang[selected]}, 0);
                            minfo.addElement(in);
                            setShowText(minfo, this.xyTiemNang[selected][0] + 12, this.xyTiemNang[selected][1] + 12, null, true, true);
                        }
                    }
                    if (!isFocusDetailMenu && isChangeSubTab && !isFocusCharWearing) {
                        if (!isChangeSubTab && selected == -1) isChangeSubTab = true;
                        if (isChangeSubTab) {
                            if (this.isShowInFoChar) {
                                this.isShowInFoChar = false;
                                selected = -1;
                            }
                            if (this.isUseCmr) {
                                closeText();
                                break;
                            }
                            if (this.indexMainTab != 5) {
                                int total = currnentTabDetail.length - 1;
                                if (this.isCharWearing) total = 15;
                                selected++;
                                setCmdCenter("updateKey 10");
                                this.cmdShowText.performAction();
                                if (selected > total) selected = 0;
                            }
                        }
                    }
                    break;
                case 0:
                case 1:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                    if (!isFocusDetailMenu) {
                        if (this.indexMainTab != 2) {
                            isFocusDetailMenu = true;
                            isChangeSubTab = true;
                            if (this.indexMainTab != 0 && !this.isHanhTrang) {
                                selected = 0;
                            } else if (this.indexMainTab == 0 || (this.indexMainTab == 1 && this.isHanhTrang)) {
                                selected = 0;
                                isChangeSubTab = false;
                            }
                        }
                        if (isTabQuest()) this.isQuest = true;
                        doSelectMainTabNotTouch();
                        break;
                    }
                    if (!isChangeSubTab && selected == -1) isChangeSubTab = true;
                    if (!isChangeSubTab) {
                        if (this.indexMainTab == 0 || (this.indexMainTab == 1 && this.isHanhTrang)) {
                            selected++;
                            int size = Char.inventory.size();
                            if (this.indexMainTab == 0) size = this.mShop.size();
                            if (selected > size - 1) selected = 0;
                            setCmdCenter("updateKey 11");
                        }
                        break;
                    }
                    if (this.isShowInFoChar) {
                        this.isShowInFoChar = false;
                        selected = -1;
                    }
                    if (this.isUseCmr) {
                        closeText();
                        break;
                    }
                    if ((this.isHanhTrang && this.indexMainTab == 1) || this.indexMainTab == 0) {
                        this.indexSubTab++;
                        if (this.indexSubTab > currnentTabDetail.length - 1)
                            this.indexSubTab = currnentTabDetail.length - 1;
                        if (this.indexMainTab == 0 && this.isMiniShop) {
                            this.indexTabMiniShop++;
                            if (this.indexTabMiniShop > currnentTabDetail.length - 1)
                                this.indexTabMiniShop = currnentTabDetail.length - 1;
                        }
                        this.focusTab = (byte) this.indexSubTab;
                        setSelectTab("updateKey 6");
                        setCmdCenter("updateKey 12");
                        this.countR = 5;
                        break;
                    }
                    if (this.indexMainTab != 5) {
                        int total = currnentTabDetail.length - 1;
                        if (this.isCharWearing) {
                            total = 15;
                        } else if (this.isSkill) {
                            total = GameScr.mainChar.getnSkill() - 1;
                        }
                        selected++;
                        if (selected > total) selected = 0;
                        if (this.isSkill) setCmdCenter("updateKey 13");
                        if (this.isQuest && isTabQuest()) {
                            this.countR = 5;
                            indexTypeQuest = (indexTypeQuest + 1) % QuestTile.length;
                            indexQuest = -1;
                            movecmrQuest();
                        }
                    }
                    break;
            }
            if (GameCanvas.isTouch && !this.isQuest) {
                isChangeSubTab = false;
                isFocusDetailMenu = false;
            }
        } else if (GameCanvas.keyPressedz[4]) {
            GameCanvas.keyPressedz[4] = false;
            this.isMoveQuest = false;
            actionKey4();
            if (GameCanvas.isTouch) {
                isChangeSubTab = false;
                isFocusDetailMenu = false;
            }
        } else if (GameCanvas.keyPressedz[8]) {
            GameCanvas.keyPressedz[8] = false;
            if (this.indexMainTab == 2 && isChangeSubTab) {
                if (isFocusDetailMenu) {
                    selected += 2;
                    if (selected > 3) {
                        selected = 0;
                        isFocusDetailMenu = false;
                    } else {
                        mVector minfo = new mVector();
                        InfoTextShow in = new InfoTextShow(new String[]{T.infoTiemNang[selected]}, 0);
                        minfo.addElement(in);
                        setShowText(minfo, this.xyTiemNang[selected][0] + 12, this.xyTiemNang[selected][1] + 12, null, true, true);
                    }
                } else if (isFocusCharWearing) {
                    if (this.isUseCmr) {
                        this.cmrShowText.moveCmrTo(1);
                    } else {
                        selected = 0;
                        isFocusCharWearing = false;
                    }
                } else if (!isFocusCharWearing && !isFocusDetailMenu) {
                    selected += 5;
                    if (selected > 14) {
                        selected = -1;
                        this.indexMainTab++;
                        if (this.indexMainTab > this.numtab - 1) {
                            this.indexMainTab = 0;
                            if (this.isMiniShop) this.indexSubTab = this.indexTabMiniShop;
                        }
                        reSetAllCmr();
                        setPosScroll(this.indexMainTab, -1);
                        setSelectTab("updateKey 8");
                        setCmdCenter("updateKey 14");
                        setTile();
                    } else {
                        setCmdCenter("updateKey 15");
                        this.cmdShowText.performAction();
                    }
                }
                return;
            }
            if (this.isQuest) {
                if (isChangeSubTab) {
                    isChangeSubTab = false;
                    isFocusDetailMenu = true;
                    selected = -1;
                } else {
                    selected++;
                    if (selected > this.cmrItem.nITEM - 1) {
                        this.cmrItem.moveTo(selected / (this.cmrItem.nITEM - 1) * 20);
                        selected = 0;
                    }
                    if (this.isMoveQuest) {
                        if (indexQuest < ListQuest[indexTypeQuest].size()) indexQuest++;
                        selected = indexQuest;
                        this.isMoveQuest = false;
                    }
                    indexQuest = selected;
                    closeText();
                }
                return;
            }
            if (!isFocusDetailMenu) {
                this.indexSubTab = 0;
                if (this.indexMainTab < this.numtab - 1) this.indexMainTab++;
                if (this.maptopaintMenuIcon[this.indexMainTab] == -1)
                    if (this.maptopaintMenuIcon[this.indexMainTab] == -1) {
                        this.indexPet = 0;
                        updatepetItem();
                    } else {
                        this.indexMainTab = 0;
                        if (this.isMiniShop) this.indexSubTab = this.indexTabMiniShop;
                    }
                this.isSkill = false;
                this.isQuest = false;
                selected = -1;
                reSetAllCmr();
                setPosScroll(this.indexMainTab, -1);
                setSelectTab("updateKey 81");
                setCmdCenter("updateKey 16");
                setTile();
                doSelectMainTabNotTouch();
            } else if (this.indexMainTab == 0 || (this.indexMainTab == 1 && this.isHanhTrang)) {
                if (!this.isUseCmr) {
                    if (!isChangeSubTab) {
                        selected += this.colum;
                        int size = Char.inventory.size();
                        if (this.indexMainTab == 0) size = this.mShop.size();
                        if (selected / size >= 1) {
                            this.cmrItem.moveTo(selected / this.colum * size);
                            selected = 0;
                        }
                    }
                    if (isChangeSubTab) {
                        isChangeSubTab = false;
                        selected = 0;
                    }
                    setCmdCenter("updateKey 17");
                } else {
                    this.cmrShowText.moveCmrTo(1);
                }
            } else if (this.isUseCmr) {
                this.cmrShowText.moveCmrTo(1);
            } else if (this.isShowInFoChar) {
                int to = 0;
                if (this.isAnimal) {
                    to = this.animalInfo.length - 1;
                } else if (this.infochar != null) {
                    to = this.totalInfoshow;
                }
                this.indexShowInfo++;
                if (this.indexShowInfo > to) this.indexShowInfo = 0;
            } else {
                int total = currnentTabDetail.length - 1;
                if (isPaintSub()) {
                    if (this.isSkill) {
                        total = GameScr.mainChar.getnSkill() - 1;
                        closeText();
                    } else if (this.isFeatures) {
                        total = 4;
                    } else if (this.isCharWearing) {
                        total = 15;
                    } else if (this.isQuest) {
                        total = this.totalLineQuest;
                    }
                    setCmdShowText();
                }
                if (this.isCharWearing || this.isAnimal) {
                    selected += 5;
                    if (selected > 15) selected = 0;
                    setCmdCenter("updateKey 18");
                    this.cmdShowText.performAction();
                } else if (this.isSkill) {
                    selected += 3;
                    if (selected > total) selected = 0;
                    if (!GameCanvas.isTouch) setCmdCenter("updateKey 19");
                } else {
                    if (this.indexMainTab != 5) {
                        selected += this.isFeatures ? 1 : this.maxNumW;
                    } else {
                        selected++;
                    }
                    if (selected > total) selected = 0;
                }
                if (isPaintSub() && this.isFeatures) if (selected == 0) {
                    setFocusTf(this.tfStrength);
                } else if (selected == 1) {
                    setFocusTf(this.tfAgility);
                } else if (selected == 2) {
                    setFocusTf(this.tfSpirit);
                } else if (selected == 3) {
                    setFocusTf(this.tfHealth);
                } else if (selected == 4) {
                    setFocusTf(this.tfLucky);
                }
            }
        } else if (GameCanvas.keyPressedz[2]) {
            if (this.indexMainTab == 12) this.indexMainTab = this.numtab - 1;
            this.indexPet = 0;
            GameCanvas.keyPressedz[2] = false;
            if (this.indexMainTab == 2) {
                boolean isNext = true;
                if (selected < 5 && selected > 1 && !isFocusDetailMenu) {
                    isFocusDetailMenu = true;
                    selected = 3;
                    isNext = false;
                } else if (!isFocusDetailMenu && selected < 2 && selected >= 0 && !isFocusCharWearing) {
                    isFocusCharWearing = true;
                    selected = -1;
                    isNext = false;
                } else if (selected >= 5 && !isFocusDetailMenu) {
                    selected -= 5;
                    setCmdCenter("updateKey 20");
                    this.cmdShowText.performAction();
                    isNext = false;
                } else if (isFocusDetailMenu) {
                    selected -= 2;
                    if (selected < 0) {
                        selected = -1;
                        isFocusCharWearing = false;
                    } else {
                        mVector minfo = new mVector();
                        InfoTextShow in = new InfoTextShow(new String[]{T.infoTiemNang[selected]}, 0);
                        minfo.addElement(in);
                        setShowText(minfo, this.xyTiemNang[selected][0] + 12, this.xyTiemNang[selected][1] + 12, null, true, true);
                        isNext = false;
                    }
                }
                if (!isNext) return;
            }
            if (this.isQuest) {
                selected--;
                if (this.isMoveQuest) {
                    if (indexQuest > 0) indexQuest--;
                    selected = indexQuest;
                    this.isMoveQuest = false;
                }
                indexQuest = selected;
                if (selected < 0) {
                    selected = 0;
                    isChangeSubTab = true;
                    isFocusDetailMenu = false;
                    this.cmrItem.selectedItem = -1;
                }
                closeText();
                return;
            }
            if (!isFocusDetailMenu) {
                boolean isSet = false;
                this.indexSubTab = 0;
                this.indexMainTab--;
                if (this.indexMainTab == 0) this.indexSubTab = this.indexTabMiniShop;
                if (this.indexMainTab < 0) {
                    this.indexMainTab = this.numtab - 1;
                    if (this.isMiniShop) isSet = true;
                    this.indexPet = 0;
                    this.indexMainTab = 12;
                    updatepetItem();
                }
                this.isSkill = false;
                this.isQuest = false;
                selected = -1;
                reSetAllCmr();
                setPosScroll(this.indexMainTab, -1);
                setCmdCenter("updateKey 21");
                setSelectTab("updateKey 2");
                setTile();
                doSelectMainTabNotTouch();
            } else if (this.indexMainTab == 0 || (this.indexMainTab == 1 && this.isHanhTrang)) {
                if (!this.isUseCmr) {
                    if (!isChangeSubTab) {
                        selected -= this.colum;
                        if (selected < 0) {
                            selected = -1;
                            isChangeSubTab = true;
                        }
                    }
                    setCmdCenter("updateKey 22");
                } else {
                    this.cmrShowText.moveCmrTo(-1);
                }
            } else if (this.isShowInFoChar) {
                int to = 0;
                if (this.isAnimal) {
                    to = this.animalInfo.length - 1;
                } else if (this.infochar != null) {
                    to = this.totalInfoshow;
                }
                this.indexShowInfo--;
                if (this.indexShowInfo < 0) this.indexShowInfo = to;
            } else {
                int total = currnentTabDetail.length - 1;
                if (this.isUseCmr) {
                    this.cmrShowText.moveCmrTo(-1);
                } else {
                    if (isPaintSub()) {
                        if (this.isSkill) {
                            total = GameScr.mainChar.getnSkill() - 1;
                            closeText();
                        } else if (this.isFeatures) {
                            total = 5;
                        } else if (this.isCharWearing) {
                            total = 15;
                        } else if (this.isQuest) {
                            total = this.totalLineQuest;
                        }
                        setCmdShowText();
                    }
                    if (this.isCharWearing || this.isAnimal) {
                        selected -= 5;
                        if (selected < 0) {
                            selected = -1;
                            this.isShowInFoChar = true;
                            closeText();
                            this.isAnimal = false;
                        } else {
                            setCmdCenter("updateKey 23");
                            this.cmdShowText.performAction();
                        }
                    } else if (this.isSkill) {
                        selected -= 3;
                        if (selected < 0) selected = total;
                        if (!GameCanvas.isTouch) setCmdCenter("updateKey 24");
                    } else {
                        if (this.indexMainTab != 5) {
                            selected -= this.isFeatures ? 1 : this.maxNumW;
                        } else {
                            selected--;
                        }
                        if (selected < 0) selected = total;
                    }
                    if (isPaintSub() && this.isFeatures) if (selected == 0) {
                        setFocusTf(this.tfStrength);
                    } else if (selected == 1) {
                        setFocusTf(this.tfAgility);
                    } else if (selected == 2) {
                        setFocusTf(this.tfSpirit);
                    } else if (selected == 3) {
                        setFocusTf(this.tfHealth);
                    } else if (selected == 4) {
                        setFocusTf(this.tfLucky);
                    }
                }
            }
        } else if (GameCanvas.isKeyPressed(5)) {
            switch (this.indexMainTab) {
                case 3:
                    this.center.performAction();
                    break;
                case 4:
                    this.center.performAction();
                    break;
            }
        }
        if (!GameCanvas.isTouch) if (this.isQuest) {
            this.cmrItem.moveTo(indexQuest * this.sizeH);
        } else {
            if (this.indexMainTab != 0 && !this.isHanhTrang) {
                int disY = 60;
                if (this.isShowFill) {
                    this.cmrItem.moveTo(selected * this.size);
                } else {
                    this.cmrItem.moveTo(selected / this.maxNumW * disY);
                }
            } else {
                this.cmrItem.moveTo(selected / this.colum * this.size);
            }
            if ((this.indexMainTab != 0 && !this.isHanhTrang) || isPaintSub()) this.cmrSubTab.moveTo(selected * 60);
            if (this.isShowInFoChar) this.cmrShowInfoMainChar.moveTo(this.indexShowInfo * 12);
        }
        super.updateKey();
    }

    public void actionKey4() {
        if (this.isQuest && !GameCanvas.isTouch) {
            if (isTabQuest()) {
                indexTypeQuest--;
                indexQuest = -1;
                if (indexTypeQuest < 0) {
                    indexTypeQuest = 0;
                    this.isQuest = false;
                    isFocusDetailMenu = false;
                    isChangeSubTab = false;
                    this.left = null;
                }
                movecmrQuest();
            }
            return;
        }
        switch (this.indexMainTab) {
            case 2:
            case 8:
            case 9:
            case 10:
            case 11:
                if (selected == 0 && !isFocusDetailMenu && !isFocusCharWearing) {
                    isFocusDetailMenu = true;
                    selected = 4;
                    closeText();
                } else if (isFocusCharWearing) {
                    isChangeSubTab = false;
                    isFocusCharWearing = false;
                    isFocusDetailMenu = false;
                    this.isShowInFoChar = false;
                    closeText();
                }
                if (isFocusDetailMenu) {
                    selected--;
                    if (selected < 0) {
                        selected = -1;
                        isFocusCharWearing = true;
                        isFocusDetailMenu = false;
                        break;
                    }
                    mVector minfo = new mVector();
                    InfoTextShow in = new InfoTextShow(new String[]{T.infoTiemNang[selected]}, 0);
                    minfo.addElement(in);
                    setShowText(minfo, this.xyTiemNang[selected][0] + 12, this.xyTiemNang[selected][1] + 12, null, true, true);
                    break;
                }
                if (isFocusCharWearing) {
                    isFocusCharWearing = false;
                    isFocusDetailMenu = false;
                    break;
                }
                if (selected >= 0) {
                    if (this.isUseCmr) {
                        closeText();
                        break;
                    }
                    closeText();
                    selected--;
                    if (selected < 0) {
                        isFocusDetailMenu = true;
                        break;
                    }
                    setCmdCenter("actionKey4 0");
                    this.cmdShowText.performAction();
                }
                break;
            case 0:
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                if (this.isUseCmr) {
                    closeText();
                    return;
                }
                if (!isChangeSubTab) {
                    if (isFocusDetailMenu && (this.indexMainTab == 0 || (this.indexMainTab == 1 && this.isHanhTrang))) {
                        if (selected % this.colum == 0) {
                            isFocusDetailMenu = false;
                            selected = -1;
                            if (!this.isMiniShop) {
                                if (this.isHanhTrang) currnentTabDetail = myInfo;
                                this.isHanhTrang = false;
                            }
                            setTile();
                            reSetAllCmr();
                        }
                        selected--;
                        setCmdCenter("actionKey4 1");
                    }
                    break;
                }
                if (this.isUseCmr) {
                    closeText();
                    break;
                }
                closeText();
                if ((this.indexMainTab == 3 || this.indexMainTab == 2 || this.indexMainTab == 8) && (this.isQuest || this.isCharWearing || this.isAnimal || this.isSkill) && !this.isHanhTrang) {
                    if (this.isShowInFoChar) {
                        selected = -1;
                        this.indexSubTab = 0;
                        this.isCharWearing = false;
                        this.isShowInFoChar = false;
                        isFocusDetailMenu = false;
                        this.isAnimal = false;
                        setCmdCenter("actionKey4 2");
                    } else {
                        if (this.isCharWearing || this.isAnimal) {
                            if (selected % 6 == 0) {
                                isFocusDetailMenu = false;
                                isChangeSubTab = false;
                                selected = 0;
                                this.indexSubTab = 0;
                                this.isCharWearing = this.isAnimal = false;
                            }
                        } else if (this.isSkill) {
                            if (selected % 3 == 0) {
                                isFocusDetailMenu = false;
                                isChangeSubTab = false;
                                selected = 0;
                                this.indexSubTab = 0;
                                this.isSkill = false;
                            }
                        } else if (this.isQuest && isTabQuest()) {
                            indexTypeQuest--;
                            indexQuest = -1;
                            if (indexTypeQuest < 0) {
                                indexTypeQuest = 0;
                                this.isQuest = false;
                                isFocusDetailMenu = false;
                                isChangeSubTab = false;
                                this.left = null;
                            }
                            movecmrQuest();
                        }
                        selected--;
                        setCmdCenter("actionKey4 3");
                    }
                    this.countL = 5;
                } else {
                    if (this.isFeatures) {
                        this.isFeatures = false;
                        selected = 0;
                        this.indexSubTab = 0;
                    } else if (this.isQuest) {
                        Cout.println(String.valueOf(isFocusDetailMenu) + " isQuest end  " + this.isQuest);
                        this.isQuest = false;
                        selected = 0;
                        this.indexSubTab = 0;
                    } else if (this.isQuestClan) {
                        this.isQuestClan = false;
                        selected = 0;
                        this.indexSubTab = 0;
                    }
                    if (this.indexMainTab == 0 || (this.indexMainTab == 1 && this.isHanhTrang)) {
                        this.indexSubTab--;
                        if (this.indexMainTab == 0 && this.isMiniShop) {
                            this.indexTabMiniShop--;
                            if (this.indexTabMiniShop < 0) this.indexTabMiniShop = 0;
                        }
                        if (this.indexSubTab < 0) {
                            isFocusDetailMenu = false;
                            isChangeSubTab = false;
                            selected = -1;
                            this.indexSubTab = 0;
                            if (!this.isMiniShop) {
                                if (this.isHanhTrang) currnentTabDetail = myInfo;
                                this.isHanhTrang = false;
                            }
                            setTile();
                            reSetAllCmr();
                        }
                        this.focusTab = (byte) this.indexSubTab;
                        setSelectTab("actionKey4 1");
                        setCmdCenter("actionKey4 4");
                    } else if (selected % this.maxNumW == 0 || this.indexMainTab == 5) {
                        isFocusDetailMenu = false;
                        isChangeSubTab = false;
                        selected = -1;
                        this.indexSubTab = 0;
                        this.isSkill = false;
                    } else {
                        selected--;
                        setSelectTab("actionKey4 2");
                        setCmdCenter("actionKey4 5");
                    }
                    this.countL = 5;
                }
                if (!isFocusDetailMenu) setTile();
                break;
        }
    }

    private void reSetAllCmr() {
        this.cmrItem.clear();
        this.cmrShowInfoMainChar.clear();
        this.cmrShowText.clear();
        this.cmrSubTab.clear();
    }

    public void setFocusTf(TField tf) {
        TField[] a = {this.tfStrength, this.tfAgility, this.tfHealth, this.tfLucky, this.tfSpirit};
        for (int i = 0; i < a.length; i++) {
            if (tf == a[i]) {
                tf.isFocus = true;
            } else {
                (a[i]).isFocus = false;
            }
        }
    }

    public void actionKey6() {
    }

    public void showQuest() {
        this.numItem = 4;
        this.isQuest = true;
        this.isSell = false;
        setQuestInfo();
        this.questInfo.removeAllElements();
        if (!GameCanvas.isTouch) this.left = new mCommand("Xem", this, -3);
        setImageCharWear();
        if (GameCanvas.isTouch) this.indexMainTab = 3;
        movecmrQuest();
        this.vHanhTrang.removeAllElements();
        this.vHanhTrang.addElement(cmdMapScr);
        SortCmdItem();
    }

    public void resetPopup() {
        this.isTextmua = false;
        cmdBuy.caption = T.Buy;
        if (!captionServer.equals("")) {
            cmdBuy.caption = captionServer;
        } else {
            cmdBuy.caption = T.Buy;
        }
        this.canbuy = false;
        if (this.isSkill) {
            this.vHanhTrang.removeAllElements();
            cmdCongDiem.caption = T.congDiem;
            this.vHanhTrang.addElement(cmdCongDiem);
            this.vHanhTrang.addElement(cmdGangphim);
            SortCmdItem();
            return;
        }
        if (this.isCharWearing) {
            this.vHanhTrang.removeAllElements();
            cmdcong1.caption = String.valueOf(T.congDiem) + " 1";
            cmdcong5.caption = String.valueOf(T.congDiem) + " 5";
            cmdcong10.caption = String.valueOf(T.congDiem) + " 10";
            if (Char.Diemtiemnang > 0) this.vHanhTrang.addElement(cmdcong1);
            if (Char.Diemtiemnang >= 5) this.vHanhTrang.addElement(cmdcong5);
            if (Char.Diemtiemnang >= 10) this.vHanhTrang.addElement(cmdcong10);
            SortCmdItem();
            return;
        }
        if (this.indexMainTab == 1) {
            this.vHanhTrang.removeAllElements();
            if (cmdban != null) cmdban.caption = T.ban;
            this.vHanhTrang.addElement(cmdban);
            this.vHanhTrang.addElement(cmdsudung);
            if (cmdvut != null) cmdvut.caption = T.vut;
            this.vHanhTrang.addElement(cmdvut);
            if (this.isgangphim) {
                this.vHanhTrang.addElement(cmdGangphim);
            } else {
                this.vHanhTrang.addElement(cmdbanNhieu);
            }
            SortCmdItem();
        }
    }

    public void updatePhiPhong() {
        if (GameCanvas.gameTick % 80 == 0 && this.laststar2 > 1) this.runStart2 = true;
        if (this.runStart2 && GameCanvas.gameTick % 2 == 0) {
            this.speedStart2++;
            if (this.speedStart2 > this.numItemStart2 + 2) {
                this.speedStart2 = 0;
                this.runStart2 = false;
            }
        }
        if (GameCanvas.gameTick % 80 == 0 && this.laststar > 1) this.runStart = true;
        if (this.runStart && GameCanvas.gameTick % 2 == 0) {
            this.speedStart++;
            if (this.speedStart > this.numItemStart + 2) {
                this.speedStart = 0;
                this.runStart = false;
            }
        }
        if (GameCanvas.isKeyPressed(12)) GameCanvas.menu.startAt(this.vHanhTrang, 2);
        if (this.Waitcreate) {
            this.beginChedo = true;
            this.wait--;
            if (this.wait < 0) {
                this.Waitcreate = false;
                this.wait = 25;
                this.beginChedo = false;
                if (typeresult == 0) effDapDo = new DataSkillEff(50, this.xcenter, this.ycenter);
                if (typeresult == 236) effDapDo = new DataSkillEff(236, this.xcenter, this.ycenter);
                this.listNum = null;
                this.listNum = new int[6];
                vecMaterial.removeAllElements();
                this.listItem = null;
                this.listItem = new Item[6];
            }
        }
        if (effDapDo != null) {
            effDapDo.update();
            if (effDapDo.wantDestroy) effDapDo = null;
        }
        if (GameCanvas.isTouch) for (int j = 0; j < this.vHanhTrang.size(); j++) {
            mCommand cmd = (mCommand) this.vHanhTrang.elementAt(j);
            if (cmd != null && getCmdPointerLast(cmd)) {
                GameCanvas.isPointerJustRelease[0] = false;
                cmd.performAction();
                break;
            }
        }
        if (!GameCanvas.isTouch) {
            if (GameCanvas.isKeyPressed(6)) {
                closeText();
                if (this.slDapdo1 < vecItemCreate.size() - 1) {
                    this.slDapdo1++;
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0)
                        this.scrDapdo.moveTo((this.slDapdo1 / 5 + 1) * this.sizeDapdo);
                }
                int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (sItem != null) {
                    showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    setCmdCenter("updateDapDo 0");
                }
            }
            if (GameCanvas.isKeyPressed(4)) {
                closeText();
                if (this.slDapdo1 > 0) {
                    this.slDapdo1--;
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        setCmdCenter("updateDapDo 0");
                    }
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0)
                        this.scrDapdo.moveTo(this.slDapdo1 / 5 * this.sizeDapdo);
                }
            }
        }
        ScrollResult r = this.scrDapdo.updateKey();
        this.scrDapdo.updatecm();
        this.f = (this.f + 1) % 3;
        int i;
        for (i = 0; i < this.vEffect.size(); i++) {
            Effect e = (Effect) this.vEffect.elementAt(i);
            if (e != null) {
                e.update();
                if (e.wantDestroy) this.vEffect.removeElement(e);
            }
        }
        if (GameCanvas.gameTick % 2 == 0 && this.beginChedo) {
            EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter);
            this.vEffect.addElement(ef);
        }
        if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74, 0) && this.scrDapdo.selectedItem != -1 && this.slDapdo1 != this.scrDapdo.selectedItem) {
            this.slDapdo1 = this.scrDapdo.selectedItem;
            selected = -1;
            this.FocusPhiPhong = -1;
            setCmdCenter("updateDapDo 0");
        }
        for (i = 0; i < this.postItem_PhiPhong_X.length; i++) {
            if (GameCanvas.isPointer(this.postItem_PhiPhong_X[i] + 2, this.postItem_PhiPhong_Y[i] + 2, 21, 21, 0)) {
                this.FocusPhiPhong = i;
                this.slDapdo1 = -1;
                int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                Item sItem = (Item) vecMaterial.elementAt(this.FocusPhiPhong);
                closeText();
                if (sItem != null) {
                    showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    this.vHanhTrang.removeAllElements();
                    this.vHanhTrang.addElement(cmdlayraPhiPhong);
                    SortCmdItem();
                }
            }
        }
        if (GameCanvas.isPointer(this.xcenter - 11, this.ycenter - 11, 21, 21, 0)) {
            int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
            int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            closeText();
            if (this.itemPP != null) {
                this.isTouchCenter = true;
                showItemInventoryInfo(this.itemPP, this.isSell, x0, y0, null);
                this.vHanhTrang.removeAllElements();
                this.vHanhTrang.addElement(cmdlayraPhiPhong);
                SortCmdItem();
            }
        }
    }

    public void updateCheDo() {
        if (GameCanvas.gameTick % 80 == 0 && this.laststar > 1) this.runStart = true;
        if (this.runStart && GameCanvas.gameTick % 2 == 0) {
            this.speedStart++;
            if (this.speedStart > this.numItemStart + 2) {
                this.speedStart = 0;
                this.runStart = false;
            }
        }
        if (GameCanvas.isKeyPressed(12)) GameCanvas.menu.startAt(this.vHanhTrang, 2);
        if (this.Waitcreate) {
            this.beginChedo = true;
            this.wait--;
            if (this.wait < 0) {
                this.Waitcreate = false;
                this.wait = 25;
                this.beginChedo = false;
                if (typeresult == 0) effDapDo = new DataSkillEff(50, this.xcenter, this.ycenter);
                if (typeresult == 236) effDapDo = new DataSkillEff(50, this.xcenter, this.ycenter);
                this.listNum = null;
                this.listNum = new int[6];
                vecMaterial.removeAllElements();
            }
        }
        if (effDapDo != null) {
            effDapDo.update();
            if (effDapDo.wantDestroy) effDapDo = null;
        }
        if (GameCanvas.isTouch) for (int j = 0; j < this.vHanhTrang.size(); j++) {
            mCommand cmd = (mCommand) this.vHanhTrang.elementAt(j);
            if (cmd != null && getCmdPointerLast(cmd)) {
                GameCanvas.isPointerJustRelease[0] = false;
                cmd.performAction();
                break;
            }
        }
        if (!GameCanvas.isTouch) {
            if (GameCanvas.isKeyPressed(6)) {
                closeText();
                if (this.slDapdo1 < vecItemCreate.size() - 1) {
                    this.slDapdo1++;
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0)
                        this.scrDapdo.moveTo((this.slDapdo1 / 5 + 1) * this.sizeDapdo);
                }
                int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (sItem != null) {
                    showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    setCmdCenter("updateDapDo 0");
                }
            }
            if (GameCanvas.isKeyPressed(4)) {
                closeText();
                if (this.slDapdo1 > 0) {
                    this.slDapdo1--;
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        setCmdCenter("updateDapDo 0");
                    }
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0)
                        this.scrDapdo.moveTo(this.slDapdo1 / 5 * this.sizeDapdo);
                }
            }
        }
        ScrollResult r = this.scrDapdo.updateKey();
        this.scrDapdo.updatecm();
        this.f = (this.f + 1) % 3;
        for (int i = 0; i < this.vEffect.size(); i++) {
            Effect e = (Effect) this.vEffect.elementAt(i);
            if (e != null) {
                e.update();
                if (e.wantDestroy) this.vEffect.removeElement(e);
            }
        }
        if (GameCanvas.gameTick % 2 == 0 && this.beginChedo) {
            EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter);
            this.vEffect.addElement(ef);
        }
        if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74, 0) && this.scrDapdo.selectedItem != -1 && this.slDapdo1 != this.scrDapdo.selectedItem) {
            this.slDapdo1 = this.scrDapdo.selectedItem;
            selected = -1;
            setCmdCenter("updateDapDo 0");
        }
    }

    public void updateChuyenHoa() {
        if (GameCanvas.gameTick % 80 == 0 && this.laststar > 1) this.runStart = true;
        if (this.runStart && GameCanvas.gameTick % 2 == 0) {
            this.speedStart++;
            if (this.speedStart > this.numItemStart + 2) {
                this.speedStart = 0;
                this.runStart = false;
            }
        }
        if (GameCanvas.isKeyPressed(12)) GameCanvas.menu.startAt(this.vHanhTrang, 2);
        if (this.Waitcreate) {
            this.beginChedo = true;
            this.wait--;
            if (this.wait < 0) {
                this.Waitcreate = false;
                this.wait = 25;
                this.beginChedo = false;
                effDapDo = new DataSkillEff(50, this.xcenter, this.ycenter - 30);
                itemChuyenHoa1 = null;
                itemChuyenHoa2 = null;
                this.listNum = null;
                this.listNum = new int[6];
                vecMaterial.removeAllElements();
            }
        }
        if (effDapDo != null) {
            effDapDo.update();
            if (effDapDo.wantDestroy) effDapDo = null;
        }
        if (GameCanvas.isTouch) for (int j = 0; j < this.vHanhTrang.size(); j++) {
            mCommand cmd = (mCommand) this.vHanhTrang.elementAt(j);
            if (cmd != null && getCmdPointerLast(cmd)) {
                GameCanvas.isPointerJustRelease[0] = false;
                cmd.performAction();
                break;
            }
        }
        if (!GameCanvas.isTouch) {
            if (GameCanvas.isKeyPressed(6)) {
                closeText();
                if (this.slDapdo1 < vecItemCreate.size() - 1) {
                    this.slDapdo1++;
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0)
                        this.scrDapdo.moveTo((this.slDapdo1 / 5 + 1) * this.sizeDapdo);
                }
                int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (sItem != null) {
                    showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    setCmdCenter("updateDapDo 0");
                }
            }
            if (GameCanvas.isKeyPressed(4)) {
                closeText();
                if (this.slDapdo1 > 0) {
                    this.slDapdo1--;
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        setCmdCenter("updateDapDo 0");
                    }
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0)
                        this.scrDapdo.moveTo(this.slDapdo1 / 5 * this.sizeDapdo);
                }
            }
        }
        ScrollResult r = this.scrDapdo.updateKey();
        this.scrDapdo.updatecm();
        this.f = (this.f + 1) % 3;
        for (int i = 0; i < this.vEffect.size(); i++) {
            Effect e = (Effect) this.vEffect.elementAt(i);
            if (e != null) {
                e.update();
                if (e.wantDestroy) this.vEffect.removeElement(e);
            }
        }
        if (GameCanvas.gameTick % 2 == 0 && this.beginChedo) {
            EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter - 25 + 10);
            this.vEffect.addElement(ef);
        }
        if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74, 0) && this.scrDapdo.selectedItem != -1 && this.slDapdo1 != this.scrDapdo.selectedItem) {
            this.tickChuyenhoa = -1;
            this.slDapdo1 = this.scrDapdo.selectedItem;
            selected = -1;
            setCmdCenter("updateDapDo 0");
        }
        if (GameCanvas.isPointer(this.xcenter - 11, this.ycenter - 11 - 30 + 5, 21, 21, 0)) {
            this.slDapdo1 = -1;
            if (itemChuyenHoa1 != null) {
                this.tickChuyenhoa = 0;
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                setShowText(itemChuyenHoa1.getInfoItemShow(null, true), x0, y0, null, true, false);
            }
        }
        if (GameCanvas.isPointer(this.xcenter - 42 - 11 + 10, this.ycenter - 11 + 30, 21, 21, 0)) {
            this.slDapdo1 = -1;
            if (itemChuyenHoa1 != null) {
                this.tickChuyenhoa = 1;
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                setShowText(itemChuyenHoa1.getInfoItemShow(null, true), x0, y0, null, true, false);
            }
        }
        if (GameCanvas.isPointer(this.xcenter + 42 - 11 - 10, this.ycenter - 11 + 30, 21, 21, 0)) {
            this.slDapdo1 = -1;
            if (itemChuyenHoa2 != null) {
                this.tickChuyenhoa = 2;
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                setShowText(itemChuyenHoa2.getInfoItemShow(null, true), x0, y0, null, true, false);
            }
        }
    }

    public void updatepetItem() {
        this.indexPet = 1;
        this.scrDapdo.moveTo(0);
        setPosWearing(false);
        isChangeSubTab = false;
        this.vHanhTrang.removeAllElements();
        this.isHanhTrang = false;
        closeText();
        this.cmdSelectItem.setXY(-100, -100);
        this.isSetXYCmdSelect = false;
        this.indexMainTab = 12;
        this.vHanhTrang.removeAllElements();
        mSound.playSound(26, mSound.volumeSound);
        selected = -1;
        this.indexSubTab = 0;
        this.isCharWearing = false;
        this.isShowInFoChar = false;
        isFocusDetailMenu = false;
        this.isAnimal = false;
        this.isQuest = false;
        this.isFeatures = false;
        this.isHanhTrang = false;
        this.isSkill = false;
        this.isShowFill = false;
        this.isSkillClan = false;
        this.isQuestClan = false;
        if (this.isMiniShop) this.isHanhTrang = true;
        reSetAllCmr();
        setPosScroll(this.indexMainTab, -1);
        setSelectTab("updatepetItem");
        setCmdCenter("updatepetItem 0");
        setTile();
        if (this.indexMainTab == 3) this.isSkill = true;
        if (this.indexMainTab == 4) showQuest();
        doChangeInfo(true);
        GameCanvas.isPointerJustRelease[0] = false;
        GameCanvas.isPointerClick[0] = false;
        this.FocusPet = -1;
        selected = -1;
        this.isFocusPetWearing = true;
        if (GameScr.mainChar.equip != null && GameScr.mainChar.equip[14] != null) {
            int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            showItemInventoryInfo(GameScr.mainChar.equip[14], this.isSell, x00, y0, null);
        }
        this.currentTileMainTab = "Thú nuôi";
    }

    public void updateMainTab() {
        if ((mSystem.isIos && mGraphics.zoomLevel < 4) || mSystem.isj2me) {
            ScrollResult rs = this.scrMainmenu.updateKey();
            this.scrMainmenu.updatecm();
            if (GameCanvas.isPointer(this.x, this.y + 45, 30, this.h - 47, 0) && this.scrMainmenu.selectedItem != -1 && this.indexMainTab != 9 && this.indexMainTab != 10 && this.indexMainTab != 8 && this.indexMainTab != 11) {
                int index = this.scrMainmenu.selectedItem;
                if (index < this.numtab) {
                    this.indexPet = 0;
                    if (this.maptopaintMenuIcon[index] != -1) {
                        isChangeSubTab = false;
                        this.vHanhTrang.removeAllElements();
                        this.isHanhTrang = false;
                        closeText();
                        this.cmdSelectItem.setXY(-100, -100);
                        this.isSetXYCmdSelect = false;
                        if (index != this.indexMainTab) mSound.playSound(26, mSound.volumeSound);
                        if (index != 5) this.indexMainTab = index;
                        if (this.indexMainTab > mainTab.length - 1) {
                            this.indexMainTab = 0;
                            if (this.isMiniShop) this.indexSubTab = this.indexTabMiniShop;
                        }
                        selected = -1;
                        this.indexSubTab = 0;
                        this.isCharWearing = false;
                        this.isShowInFoChar = false;
                        isFocusDetailMenu = false;
                        this.isAnimal = false;
                        this.isQuest = false;
                        this.isFeatures = false;
                        this.isHanhTrang = false;
                        this.isSkill = false;
                        this.isShowFill = false;
                        this.isSkillClan = false;
                        this.isQuestClan = false;
                        if (this.isMiniShop) this.isHanhTrang = true;
                        reSetAllCmr();
                        setPosScroll(this.indexMainTab, -1);
                        setSelectTab("updateMainTab 1");
                        setCmdCenter("updatepetItem 0");
                        setTile();
                        if (this.indexMainTab == 3) this.isSkill = true;
                        if (this.indexMainTab == 4) showQuest();
                        doChangeInfo(true);
                        GameCanvas.isPointerJustRelease[0] = false;
                        GameCanvas.isPointerClick[0] = false;
                    } else if (this.maptopaintMenuIcon[index] == -1 && isPet) {
                        this.indexPet = 1;
                        setPosWearing(false);
                        isChangeSubTab = false;
                        this.vHanhTrang.removeAllElements();
                        this.isHanhTrang = false;
                        closeText();
                        this.cmdSelectItem.setXY(-100, -100);
                        this.isSetXYCmdSelect = false;
                        this.indexMainTab = 12;
                        this.vHanhTrang.removeAllElements();
                        mSound.playSound(26, mSound.volumeSound);
                        selected = -1;
                        this.indexSubTab = 0;
                        this.isCharWearing = false;
                        this.isShowInFoChar = false;
                        isFocusDetailMenu = false;
                        this.isAnimal = false;
                        this.isQuest = false;
                        this.isFeatures = false;
                        this.isHanhTrang = false;
                        this.isSkill = false;
                        this.isShowFill = false;
                        this.isSkillClan = false;
                        this.isQuestClan = false;
                        if (this.isMiniShop) this.isHanhTrang = true;
                        reSetAllCmr();
                        setPosScroll(this.indexMainTab, -1);
                        setSelectTab("updateMainTab 2");
                        setCmdCenter("updatepetItem 0");
                        setTile();
                        if (this.indexMainTab == 3) this.isSkill = true;
                        if (this.indexMainTab == 4) showQuest();
                        doChangeInfo(true);
                        GameCanvas.isPointerJustRelease[0] = false;
                        GameCanvas.isPointerClick[0] = false;
                        this.FocusPet = -1;
                        selected = -1;
                        this.isFocusPetWearing = true;
                        if (GameScr.mainChar.equip != null && GameScr.mainChar.equip[14] != null) {
                            int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                            showItemInventoryInfo(GameScr.mainChar.equip[14], this.isSell, x00, y0, null);
                        }
                    }
                }
            }
        } else {
            int x0 = this.x + this.w - 7 - this.colum * this.size;
            int xTouch = this.x;
            int yTouch = this.y + this.sizeH + ((GameCanvas.w > 200) ? 10 : 8);
            int h0 = mainTab.length * this.size + 1;
            int w0 = x0 - this.x - 5;
            if (GameCanvas.isPointer(xTouch, yTouch, w0 + 12 - this.size, h0, 0) && GameCanvas.canTouch() && GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && this.indexMainTab != 9 && this.indexMainTab != 10 && this.indexMainTab != 8 && this.indexMainTab != 11) {
                int index = (GameCanvas.py[0] - yTouch) / this.size;
                this.indexPet = 0;
                if (this.maptopaintMenuIcon[index] != -1) {
                    isChangeSubTab = false;
                    this.vHanhTrang.removeAllElements();
                    this.isHanhTrang = false;
                    closeText();
                    this.cmdSelectItem.setXY(-100, -100);
                    this.isSetXYCmdSelect = false;
                    if (index != this.indexMainTab) mSound.playSound(26, mSound.volumeSound);
                    if (index != 5) this.indexMainTab = index;
                    if (this.indexMainTab > mainTab.length - 1) {
                        this.indexMainTab = 0;
                        if (this.isMiniShop) this.indexSubTab = this.indexTabMiniShop;
                    }
                    selected = -1;
                    this.indexSubTab = 0;
                    this.isCharWearing = false;
                    this.isShowInFoChar = false;
                    isFocusDetailMenu = false;
                    this.isAnimal = false;
                    this.isQuest = false;
                    this.isFeatures = false;
                    this.isHanhTrang = false;
                    this.isSkill = false;
                    this.isShowFill = false;
                    this.isSkillClan = false;
                    this.isQuestClan = false;
                    if (this.isMiniShop) this.isHanhTrang = true;
                    reSetAllCmr();
                    setPosScroll(this.indexMainTab, -1);
                    setSelectTab("updateMainTab 3");
                    setCmdCenter(" 0");
                    setTile();
                    if (this.indexMainTab == 3) this.isSkill = true;
                    if (this.indexMainTab == 4) showQuest();
                    doChangeInfo(true);
                    GameCanvas.isPointerJustRelease[0] = false;
                    GameCanvas.isPointerClick[0] = false;
                }
                if (this.maptopaintMenuIcon[index] == -1 && isPet) {
                    this.indexPet = 1;
                    setPosWearing(false);
                    isChangeSubTab = false;
                    this.vHanhTrang.removeAllElements();
                    this.isHanhTrang = false;
                    closeText();
                    this.cmdSelectItem.setXY(-100, -100);
                    this.isSetXYCmdSelect = false;
                    this.indexMainTab = 12;
                    this.vHanhTrang.removeAllElements();
                    mSound.playSound(26, mSound.volumeSound);
                    selected = -1;
                    this.indexSubTab = 0;
                    this.isCharWearing = false;
                    this.isShowInFoChar = false;
                    isFocusDetailMenu = false;
                    this.isAnimal = false;
                    this.isQuest = false;
                    this.isFeatures = false;
                    this.isHanhTrang = false;
                    this.isSkill = false;
                    this.isShowFill = false;
                    this.isSkillClan = false;
                    this.isQuestClan = false;
                    if (this.isMiniShop) this.isHanhTrang = true;
                    reSetAllCmr();
                    setPosScroll(this.indexMainTab, -1);
                    setSelectTab("updateMainTab 4");
                    setCmdCenter("updatemaintab 0");
                    setTile();
                    if (this.indexMainTab == 3) this.isSkill = true;
                    if (this.indexMainTab == 4) showQuest();
                    doChangeInfo(true);
                    GameCanvas.isPointerJustRelease[0] = false;
                    GameCanvas.isPointerClick[0] = false;
                    this.FocusPet = -1;
                    selected = -1;
                    this.isFocusPetWearing = true;
                    if (GameScr.mainChar.equip != null && GameScr.mainChar.equip[14] != null) {
                        int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                        int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                        showItemInventoryInfo(GameScr.mainChar.equip[14], this.isSell, x00, y0, null);
                    }
                }
            }
        }
    }

    public void update() {
        if (this.isSkill) {
            ScrollResult rs = this.scrSkill.updateKey();
            this.scrSkill.updatecm();
        }
        if (this.indexPet == 1) updatePetWearing();
        if (GameCanvas.isKeyPressed(13)) if (this.isShowText) {
            if (this.isSkill && selected > 0) isFocusDetailMenu = true;
            closeText();
        } else {
            doClose();
        }
        if (this.indexMainTab == 2 && selected == 14 && GameCanvas.isKeyPressed(5))
            GameService.gI().dochangeCharWearing();
        if (GameCanvas.isPointerClick[0] && !GameCanvas.isPointer(this.xShowText + this.wShowText - 81 + 2, this.y + this.hShowText + 36, mCommand.wButtonCmd, mCommand.hButtonCmd, 0) && this.isTextmua) {
            resetPopup();
            return;
        }
        if (this.isTextmua) {
            this.countTextmua++;
            if (this.countTextmua > 150) {
                this.countTextmua = 0;
                resetPopup();
            }
        }
        if (this.countL > 0) this.countL--;
        if (this.countR > 0) this.countR--;
        if (this.indexMainTab == 8) {
            this.isFocusClose = false;
            int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
            if (GameCanvas.isPointer(this.xShowText + this.wShowText - 21, (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 6, wc, wc, 0) && GameCanvas.canTouch()) {
                this.isFocusClose = true;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    doClose();
                    GameCanvas.isPointerClick[0] = false;
                }
            }
            updateDapDo();
            if (this.lastSCR != null) this.lastSCR.update();
            return;
        }
        if (this.indexMainTab == 11) {
            this.isFocusClose = false;
            int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
            if (GameCanvas.isPointer(this.xShowText + this.wShowText - 21, (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 6, wc, wc, 0) && GameCanvas.canTouch()) {
                this.isFocusClose = true;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    doClose();
                    GameCanvas.isPointerClick[0] = false;
                }
            }
            updatePhiPhong();
            if (this.lastSCR != null) this.lastSCR.update();
            return;
        }
        if (this.indexMainTab == 9) {
            this.isFocusClose = false;
            int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
            if (GameCanvas.isPointer(this.xShowText + this.wShowText - 21, (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 6, wc, wc, 0) && GameCanvas.canTouch()) {
                this.isFocusClose = true;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    doClose();
                    GameCanvas.isPointerClick[0] = false;
                }
            }
            updateCheDo();
            if (this.lastSCR != null) this.lastSCR.update();
            return;
        }
        if (this.indexMainTab == 10) {
            this.isFocusClose = false;
            int wc = mGraphics.getImageWidth(GameScr.imgButton[4]);
            if (GameCanvas.isPointer(this.xShowText + this.wShowText - 21, (this.y + this.sizeH - 14) / 2 + (this.y + this.sizeH - 14) / 2 - 6, wc, wc, 0) && GameCanvas.canTouch()) {
                this.isFocusClose = true;
                if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0]) {
                    doClose();
                    GameCanvas.isPointerClick[0] = false;
                }
            }
            updateChuyenHoa();
            if (this.lastSCR != null) this.lastSCR.update();
            return;
        }
        if (isPaintSub() && this.isFeatures) {
            this.tfAgility.update();
            this.tfSpirit.update();
            this.tfStrength.update();
            this.tfHealth.update();
            this.tfLucky.update();
        }
        if (this.isAnimal && charWearing != null && charWearing.imgAnimal != null) {
            if (GameCanvas.gameTick % charWearing.timeChangeFrame == 0) {
                this.countFrame++;
                if (this.countFrame > (this.arrFrame[(charWearing.numFrame == 3) ? 0 : 1]).length - 1)
                    this.countFrame = 0;
            }
            this.idFrame = this.arrFrame[(charWearing.numFrame == 3) ? 0 : 1][this.countFrame];
        }
        if (GameCanvas.gameTick % 3 == 0) {
            this.coutFc++;
            if (this.coutFc >= 2) this.coutFc = 0;
        }
        if (GameCanvas.gameTick % 80 == 0 && this.laststar2 > 1) this.runStart2 = true;
        if (this.runStart2 && GameCanvas.gameTick % 2 == 0) {
            this.speedStart2++;
            if (this.speedStart2 > this.numItemStart2 + 2) {
                this.speedStart2 = 0;
                this.runStart2 = false;
            }
        }
        if (GameCanvas.gameTick % 80 == 0 && this.laststar > 1) this.runStart = true;
        if (this.runStart && GameCanvas.gameTick % 2 == 0) {
            this.speedStart++;
            if (this.speedStart > this.numItemStart + 2) {
                this.speedStart = 0;
                this.runStart = false;
            }
        }
        if (GameCanvas.isTouch) for (int i = 0; i < this.vHanhTrang.size(); i++) {
            mCommand cmd = (mCommand) this.vHanhTrang.elementAt(i);
            if (cmd != null && getCmdPointerLast(cmd)) {
                GameCanvas.isPointerJustRelease[0] = false;
                cmd.performAction();
                break;
            }
        }
        if (this.isQuest && this.isMoveQuest) this.cmrItem.moveTo(indexQuest * this.sizeH);
        if (GameCanvas.isPointer(0, 0, GameCanvas.w, GameCanvas.h, 0)) this.isMoveQuest = false;
        if (this.indexMainTab == 2) charWearing.updateCharFrame();
        if (this.isSkill) if (GameCanvas.isKeyPressed(4)) {
            selected--;
            if (selected < 0) {
                selected = -1;
                isFocusDetailMenu = false;
            } else {
                setCmdCenter("update 1");
                setCmdShowText();
                if (selected > 1 && (selected + 1) % 3 == 0) this.scrSkill.moveTo((selected + 1) / 3 * 30);
            }
        } else if (GameCanvas.isKeyPressed(6)) {
            selected++;
            if (selected > GameScr.vec_skill.size() - 1) selected = GameScr.vec_skill.size() - 1;
            setCmdCenter("update 1");
            setCmdShowText();
            if (selected > 5 && (selected + 1) % 3 == 0) this.scrSkill.moveTo((selected + 1) / 3 * 30);
        }
        if (GameCanvas.isTouch) super.update();
        if (this.lastSCR != null) this.lastSCR.update();
    }

    public void updateDapDo() {
        if (GameCanvas.gameTick % 3 == 0) {
            this.coutFc++;
            if (this.coutFc >= 2) this.coutFc = 0;
        }
        if (GameCanvas.gameTick % 80 == 0 && this.laststar > 1) this.runStart = true;
        if (this.runStart && GameCanvas.gameTick % 2 == 0) {
            this.speedStart++;
            if (this.speedStart > this.numItemStart + 2) {
                this.speedStart = 0;
                this.runStart = false;
            }
        }
        if (effDapDo != null) {
            effDapDo.update();
            if (effDapDo.wantDestroy) effDapDo = null;
        }
        if (GameCanvas.isKeyPressed(12)) GameCanvas.menu.startAt(this.vHanhTrang, 2);
        if (GameCanvas.isTouch) for (int i = 0; i < this.vHanhTrang.size(); i++) {
            mCommand cmd = (mCommand) this.vHanhTrang.elementAt(i);
            if (cmd != null && getCmdPointerLast(cmd)) {
                GameCanvas.isPointerJustRelease[0] = false;
                cmd.performAction();
                break;
            }
        }
        ScrollResult r2 = this.cmrShowText.updateKey();
        this.cmrShowText.updatecm();
        if (this.numtab != 0) this.numtab = 0;
        if (!GameCanvas.isTouch) {
            if (GameCanvas.isKeyPressed(6)) {
                closeText();
                selected++;
                if (selected >= 4 && tabDapdo == 0) {
                    tabDapdo = 1;
                    this.slDapdo1 = 0;
                    int x01 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y01 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem1 = (Item) vecItemDapDo.elementAt(this.slDapdo1);
                    if (sItem1 != null) {
                        showItemInventoryInfo(sItem1, this.isSell, x01, y01, null);
                        setCmdCenter("updateDapDo 0");
                    }
                    return;
                }
                if (tabDapdo == 1 && this.slDapdo1 < vecItemDapDo.size() - 1) {
                    this.slDapdo1++;
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0)
                        this.scrDapdo.moveTo((this.slDapdo1 / 5 + 1) * this.sizeDapdo);
                }
                int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                Item sItem = (Item) vecItemDapDo.elementAt(this.slDapdo1);
                if (sItem != null) {
                    showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    setCmdCenter("updateDapDo 0");
                }
            }
            if (GameCanvas.isKeyPressed(4)) {
                closeText();
                if (selected > 0) selected--;
                if (selected < 4) tabDapdo = 0;
                if (tabDapdo == 1 && this.slDapdo1 > 0) {
                    this.slDapdo1--;
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemDapDo.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        setCmdCenter("updateDapDo 0");
                    }
                    if (this.slDapdo1 > 1 && this.slDapdo1 % 5 == 0)
                        this.scrDapdo.moveTo(this.slDapdo1 / 5 * this.sizeDapdo);
                }
            }
            if (GameCanvas.isKeyPressed(5)) this.center.performAction();
        }
        if (GameCanvas.isPointer(xposItem - this.size / 2, ypostItem - this.size / 2, this.size, this.size, 0) && GameCanvas.canTouch() && selected != 3) {
            closeText();
            selected = 3;
            int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            if (mItem != null) {
                showItemInventoryInfo(mItem, this.isSell, x0, y0, null);
                this.vHanhTrang.removeAllElements();
                if (this.numStone > 0 && itemStone != null)
                    this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                SortCmdItem();
                setStart();
            }
        }
        ScrollResult r = this.scrDapdo.updateKey();
        this.scrDapdo.updatecm();
        if (GameCanvas.isTouch && GameCanvas.isPointer(this.xWearing[0] - 20, this.yWearing[0] + 3, 142, 74, 0) && this.scrDapdo.selectedItem != -1 && this.slDapdo1 != this.scrDapdo.selectedItem) {
            this.slDapdo1 = this.scrDapdo.selectedItem;
            selected = -1;
            setCmdCenter("updateDapDo 0");
        }
        if (this.lastSCR != null) this.lastSCR.update();
    }

    public void switchToMe() {
        this.isMiniShop = false;
        super.switchToMe();
        init1();
        initName();
    }

    public void showListQuest() {
        this.indexMainTab = 4;
        showQuest();
        movecmrQuest1();
    }

    public void switchToMe(boolean isReset) {
        super.switchToMe(isReset);
        initName();
    }

    public void switchToMe(ScreenTeam lastSCR) {
        this.isMe = false;
        if (GameCanvas.currentScreen == this) {
            this.isMe = true;
            return;
        }
        this.isMiniShop = false;
        super.switchToMe(lastSCR);
        init1();
        this.vHanhTrang.removeAllElements();
        initName();
        if (isBeginQuest) showListQuest();
        if (isInven) switchTabInven();
        if (isCheDo && !GameCanvas.isTouch) this.left.caption = T.tuychon;
        if (isChuyenHoa && !GameCanvas.isTouch) this.left.caption = T.tuychon;
        if (isPhiPhong && !GameCanvas.isTouch) this.left.caption = T.tuychon;
    }

    public void switchTabInven() {
        this.indexMainTab = 1;
        selected = -1;
        this.indexSubTab = 0;
        this.isCharWearing = false;
        this.isShowInFoChar = false;
        isFocusDetailMenu = false;
        this.isAnimal = false;
        this.isQuest = false;
        this.isFeatures = false;
        this.isHanhTrang = false;
        this.isSkill = false;
        this.isShowFill = false;
        this.isSkillClan = false;
        this.isQuestClan = false;
        if (this.isMiniShop) this.isHanhTrang = true;
        reSetAllCmr();
        setPosScroll(this.indexMainTab, -1);
        setSelectTab("switchTabInven");
        setCmdCenter("switchTabInven");
        setTile();
        this.isFocusClose = false;
        if (GameCanvas.isTouch) doChangeInfo(true);
    }

    public void init1() {
        this.vEffect.removeAllElements();
        isShow = true;
        this.countTextmua = 0;
        this.indexPet = 0;
        this.maptopaintMenuIcon = new int[]{0, 1, 2, 3, 7, -1};
        mainTab = new String[]{"Cửa hàng", "Hành trang", "Trang bị", "Kỹ năng", "Nhiệm vụ", "Thú nuôi"};
        myInfo = new String[]{" Kỹ năng", "Nhiệm vụ"};
        bangHoi = new String[]{"Thông tin", "Tin nhắn", "Kỹ năng", " N.vụ", "Q.góp", "T.viên", "Top", "Chat", "Rời bang", "Giải tán", "Đăng ký"};
        hoat_dong = new String[]{"Cây thần", "Cổ vật", "T.c thủ", "T.đại gia", "T.server", "Sự kiện", "S.thú", "C.thành", "Q.chiến"};
        settings = new String[]{"T.kenhTG", "Auto"};
        tabHanhTrang = new String[]{"Túi 1", "Túi 2", " Túi 3"};
        feaTures = new String[]{"Sức mạnh ", "Khéo léo ", "Tinh thần ", "Sức khỏe ", "May mắn "};
        this.numtab = mainTab.length;
        if (GameCanvas.w <= 200) this.maxNumW = 2;
        this.cmrItem.clear();
        this.cmrSubTab.clear();
        this.cmrShowText.clear();
        this.cmrShowInfoMainChar.clear();
        this.size = 26;
        this.colum = 5;
        if (!GameCanvas.isTouch) {
            this.size = 26;
            if (GameCanvas.isScreenSize200) this.size = 22;
            this.w = this.size * (this.colum + 1) + 10;
            this.x = GameCanvas.w / 2 - this.w / 2;
            if (this.x < 2) this.x = 2;
        } else {
            this.size = 26;
            this.w = this.size * (this.colum + 2);
            this.x = GameCanvas.w / 2 - this.w;
        }
        this.w += 4;
        if (this.x < 2) this.x = 2;
        this.xbg = this.x + this.size + 8;
        if (GameCanvas.isTouch) {
            this.xShowText = this.x + this.w - 15;
            this.wShowText = this.w;
            if (this.xShowText + this.wShowText > GameCanvas.w - 2) this.wShowText = GameCanvas.w - this.xShowText;
            this.wShowText--;
        } else {
            this.wShowText = 4 * this.size;
        }
        this.h = this.size * (this.colum + 3);
        if (this.h > GameCanvas.h - hTab + 2) this.h = GameCanvas.h - hTab + (GameCanvas.isTouch ? 8 : 0);
        this.y = GameCanvas.h / 2 - this.h / 2;
        if (!GameCanvas.isTouch) {
            if (this.y + this.h > GameCanvas.h - hTab) this.y -= hTab;
            if (this.y < 2) this.y = 2;
        } else {
            if (this.y < 24) this.y = 24;
            if (this.y + this.h > GameCanvas.h) this.h = GameCanvas.h - this.y;
        }
        if (GameCanvas.isTouch) this.y -= 30;
        this.sizeH = this.size;
        if (GameCanvas.h > 200) this.sizeH = 35;
        if (GameCanvas.isTouch) {
            this.yShowText = this.y + this.sizeH;
            this.hShowText = this.h - this.sizeH;
        }
        this.isHanhTrang = false;
        isChangeSubTab = false;
        isFocusDetailMenu = false;
        this.isFeatures = false;
        selected = -1;
        this.indexMainTab = 0;
        currnentTabDetail = new String[]{""};
        this.isSkillClan = false;
        this.isQuestClan = false;
        this.isQuest = false;
        this.isMoveQuest = false;
        setPosScroll(this.indexMainTab, -1);
        setTile();
        if (mSystem.isIP && mGraphics.zoomLevel == 3) this.hIP = 15;
        if (isDapdo) switchTabDapDo(8);
        if (isCheDo) initCheDo();
        if (isChuyenHoa) initChuyenHoa();
        if (isPhiPhong) initPhiPhong();
        for (int i = 0; i < GameCanvas.gameScr.actors.size(); i++) {
            Actor ac = (Actor) GameCanvas.gameScr.actors.elementAt(i);
            if (ac != null && ac.catagory == 12 && ac.isMypet()) {
                this.mpet = new Pet(ac.getIDTem());
                break;
            }
        }
        cmdChoan = new mCommand(T.choan, this, 39);
        cmdChoan.setPos(-18, GameCanvas.h - 25);
    }

    public void initCheDo() {
        this.listNum = null;
        this.listNum = new int[6];
        vecMaterial.removeAllElements();
        setPosWearing(false);
        selected = -1;
        this.indexMainTab = 9;
        this.xcenter = this.x + this.wShowText / 2;
        this.ycenter = this.y + this.h / 2 + 15 + 2;
        int yShowText1 = this.yInfoWearing + 2;
        int xbg = this.x + this.size + 8;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        this.xcenter = xbg + ws / 2;
        this.ycenter = yShowText1 + hShowText1 / 2;
        this.r = 40;
        this.goc = 30;
        setCmdCenter("updateDapDo 0");
        cmdbovao = new mCommand(T.bovao, this, 29);
        cmdlayra = new mCommand(T.layra, this, 30);
        cmdtao = new mCommand(T.tao, this, 31);
        this.wait = 25;
        this.Waitcreate = false;
        idicon = -1;
        this.slDapdo1 = -1;
    }

    public void initPhiPhong() {
        this.listNum = null;
        this.itemPP = null;
        this.listNum = new int[6];
        vecMaterial.removeAllElements();
        setPosWearing(false);
        selected = -1;
        this.indexMainTab = 11;
        this.xcenter = this.x + this.wShowText / 2;
        this.ycenter = this.y + this.h / 2 + 15 + 2;
        int yShowText1 = this.yInfoWearing + 2;
        int xbg = this.x + this.size + 8;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        this.xcenter = xbg + ws / 2;
        this.ycenter = yShowText1 + hShowText1 / 2;
        this.r = 40;
        this.goc = 30;
        setCmdCenter("updateDapDo 0");
        cmdbovao = new mCommand(T.bovao, this, 35);
        cmdlayra = new mCommand(T.layra, this, 36);
        if (this.typePhiPhong == 0) cmdtao = new mCommand(T.tao, this, 37);
        if (this.typePhiPhong == 1) cmdtao = new mCommand(T.nangcap, this, 37);
        cmdlayraPhiPhong = new mCommand(T.layra, this, 38);
        this.wait = 25;
        this.Waitcreate = false;
        idicon = -1;
        this.slDapdo1 = -1;
        this.postItem_PhiPhong_X = new int[6];
        this.postItem_PhiPhong_Y = new int[6];
        for (int i = 0; i < this.postItem_PhiPhong_X.length; i++) {
            this.postItem_PhiPhong_X[i] = Util.cos(i * 60 + 30) * this.r / 1024 + xbg + ws / 2 - 12;
            this.postItem_PhiPhong_Y[i] = Util.sin(i * 60 + 30) * this.r / 1024 + yShowText1 + hShowText1 / 2 - 13;
        }
        this.listItem = new Item[6];
        this.FocusPhiPhong = -1;
    }

    public void initChuyenHoa() {
        this.tickChuyenhoa = -1;
        itemChuyenHoa0 = null;
        itemChuyenHoa1 = null;
        itemChuyenHoa2 = null;
        this.listNum = null;
        this.listNum = new int[6];
        vecMaterial.removeAllElements();
        setPosWearing(false);
        selected = -1;
        this.indexMainTab = 10;
        this.xcenter = this.x + this.wShowText / 2;
        this.ycenter = this.y + this.h / 2 + 15 + 2;
        int yShowText1 = this.yInfoWearing + 2;
        int xbg = this.x + this.size + 8;
        int hShowText1 = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0) - this.size + this.hIP / 2 + 20;
        int ws = 135;
        this.xcenter = xbg + ws / 2;
        this.ycenter = yShowText1 + hShowText1 / 2;
        this.xSao = this.xcenter;
        this.ySao = this.ycenter + 3;
        this.ycenter -= 10;
        setCmdCenter("updateDapDo 0");
        cmdbovao = new mCommand(T.bovao, this, 32);
        cmdtao = new mCommand(T.chuyenhoa, this, 33);
        this.wait = 25;
        this.Waitcreate = false;
        idicon = -1;
        this.slDapdo1 = -1;
    }

    public void setCmdShowText() {
        switch (this.indexMainTab) {
            case 3:
                if (this.isSkill) {
                    this.cmdShowText = new mCommand("", this, -1);
                    this.timeAuToShowText = 15;
                    this.beGinShowText = false;
                    isFocusDetailMenu = true;
                }
                break;
            case 2:
            case 8:
            case 9:
            case 10:
            case 11:
                this.cmdShowText = new mCommand("", this, -2);
                this.timeAuToShowText = 15;
                this.beGinShowText = false;
                break;
        }
    }

    public void doCastBuffToActor(MainChar actor1, Actor actor2, int skillIdEff, boolean buff2Friend) {
        mVector menu = new mVector();
        if (buff2Friend) {
            String a = String.valueOf(actor1.ID) + ":" + skillIdEff;
            menu.addElement(new mCommand("Cho mình", this, 43, a));
            String b = String.valueOf(actor2.ID) + ":" + skillIdEff;
            menu.addElement(new mCommand("Cho bạn", this, 44, b));
        } else {
            GameService.gI().useBuff(actor1.ID, (byte) 0, (byte) skillIdEff, (short) 0);
        }
        GameCanvas.menu.startAt(menu, 2);
    }

    public void doSelectItemCuongHoa() {
        if (this.slDapdo1 >= 0 && vecItemDapDo.size() > 0) {
            int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
            int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            Item sItem = (Item) vecItemDapDo.elementAt(this.slDapdo1);
            if (sItem != null) {
                if (sItem.pos == -1) {
                    mItem = sItem;
                    if (itemStone != null && this.numStone > 0) doSendCuongHoa(0);
                } else if (sItem.pos == 0) {
                    if (itemStone != null && itemStone.ID != sItem.ID) {
                        this.numStone = 1;
                        itemStone = sItem;
                    }
                    if (itemStone == null) {
                        itemStone = sItem;
                        this.numStone = 1;
                    }
                } else if (sItem.pos == 1) {
                    if (itemBaohiem != null && itemBaohiem.ID != sItem.ID) itemBaohiem = sItem;
                    if (itemBaohiem == null) itemBaohiem = sItem;
                } else if (sItem.pos == 2) {
                    if (itemBua != null && itemBua.ID != sItem.ID) itemBua = sItem;
                    if (itemBua == null) itemBua = sItem;
                }
                showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                this.vHanhTrang.removeAllElements();
                if (!isHopda) {
                    if (itemStone != null && this.numStone > 0 && mItem != null) {
                        if (sItem.pos == 0 || sItem.pos == 2) doSendCuongHoa(0);
                        this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                    }
                    if (sItem.pos == 0) {
                        this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                        if (this.numStone > 0) this.vHanhTrang.addElement(new mCommand(T.layra, this, 26));
                    }
                    if (sItem.pos == 1) this.vHanhTrang.addElement(new mCommand(T.layra, this, 27));
                    if (sItem.pos == 2) this.vHanhTrang.addElement(new mCommand(T.layra, this, 28));
                } else if (itemStone != null) {
                    doSendCuongHoa(0);
                    this.vHanhTrang.addElement(new mCommand(T.nangcap, this, 14));
                    this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                    if (this.numStone > 0) this.vHanhTrang.addElement(new mCommand(T.layra, this, 26));
                }
                SortCmdItem();
            }
        }
    }

    public void setCmdCenter(String pos) {
        this.cmdShowText = new mCommand("", (IActionListener) GameCanvas.instance, -100);
        closeText();
        switch (this.indexMainTab) {
            case 5:
                this.center = new mCommand("Chọn", this, 0, xCCmd, yCCmd);
                if (GameCanvas.isTouch) this.center.resetCmd();
                dosetIDCmdTouch("", 0);
                break;
            case 3:
                this.center = new mCommand(GameCanvas.isTouch ? "" : "Chọn", this, 0, xCCmd, yCCmd);
                if (this.isSkill) {
                    this.timeAuToShowText = 15;
                    this.beGinShowText = false;
                    if (GameCanvas.isTouch) {
                        this.cmdShowText = new mCommand("", this, -1);
                        this.center.resetCmd();
                        dosetIDCmdTouch("", 0);
                        break;
                    }
                    this.left = new mCommand("Xem", this, -101);
                    break;
                }
                if (this.isQuest) {
                    this.center = new mCommand("", this, 49);
                    break;
                }
                if (this.isAnimal) {
                    this.cmdShowText = new mCommand("", this, 53);
                    this.timeAuToShowText = 15;
                    this.beGinShowText = false;
                    this.center = new mCommand("Thay", this, -5, xCCmd, yCCmd);
                    if (GameCanvas.isTouch) this.center.resetCmd();
                    dosetIDCmdTouch("", -5);
                    break;
                }
                if (!this.isHanhTrang) if (this.isFeatures && GameCanvas.isTouch) {
                    this.center.resetCmd();
                    dosetIDCmdTouch("", 0);
                }
                break;
            case 0:
                this.center = new mCommand(GameCanvas.isTouch ? "" : T.view, this, 2);
                if (this.mShop.size() > 0 && GameCanvas.isTouch) {
                    int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) this.mShop.elementAt(selected);
                    if (sItem != null) {
                        showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.left = cmdBuy;
                        Cout.Debug("CMD BUY 111 ");
                    }
                }
                break;
            case 7:
                this.center = new mCommand("Chọn", this, 0, xCCmd, yCCmd);
                if (GameCanvas.isTouch) {
                    this.center.resetCmd();
                    dosetIDCmdTouch("", 0);
                }
                break;
            case 4:
                this.center = new mCommand("Chọn", this, 0, xCCmd, yCCmd);
                if (GameCanvas.isTouch) {
                    this.center.resetCmd();
                    dosetIDCmdTouch("", 0);
                }
                break;
            case 6:
                this.center = new mCommand("Chọn", this, 0, xCCmd, yCCmd);
                if (GameCanvas.isTouch) {
                    this.center.resetCmd();
                    dosetIDCmdTouch("", 0);
                }
                break;
            case 12:
                if (!isChangeSubTab && selected < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(selected);
                    if (cmd != null) this.cmdShowText = cmd;
                    dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand(T.view, this, 2);
                if (selected >= 0 && vecPetEat.size() > 0) {
                    int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecPetEat.elementAt(selected);
                    if (sItem != null) showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                }
                if (GameCanvas.isTouch) this.center.resetCmd();
                if (selected != -1) {
                    this.vHanhTrang.removeAllElements();
                    this.vHanhTrang.addElement(new mCommand(T.choan, this, 39));
                }
                SortCmdItem();
                break;
            case 1:
                if (!isChangeSubTab && selected < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(selected);
                    if (cmd != null) this.cmdShowText = cmd;
                    dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand(T.view, this, 2);
                if (GameCanvas.isTouch) {
                    if (selected >= 0 && Char.inventory.size() > 0) {
                        int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                        int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                        Item sItem = (Item) Char.inventory.elementAt(selected);
                        if (sItem != null) {
                            int type = sItem.getTypeItem();
                            Item itcp = null;
                            if (POS_ITEM_IN_EQUIP[type] > -1) itcp = GameScr.mainChar.equip[POS_ITEM_IN_EQUIP[type]];
                            showItemInventoryInfo(sItem, this.isSell, x0, y0, itcp);
                        }
                    }
                    this.center.resetCmd();
                }
                break;
            case 8:
                if (!isChangeSubTab && this.slDapdo1 < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(this.slDapdo1);
                    if (cmd != null) this.cmdShowText = cmd;
                    dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand("", this, 2);
                textPercent = "";
                if (this.slDapdo1 >= 0 && vecItemDapDo.size() > 0) {
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemDapDo.elementAt(this.slDapdo1);
                    if (sItem != null && GameCanvas.isTouch) {
                        if (sItem.pos == -1) {
                            mItem = sItem;
                            if (itemStone != null && this.numStone > 0) doSendCuongHoa(0);
                        } else if (sItem.pos == 0) {
                            if (itemStone != null && itemStone.ID != sItem.ID) {
                                this.numStone = 1;
                                itemStone = sItem;
                            }
                            if (itemStone == null) {
                                itemStone = sItem;
                                this.numStone = 1;
                            }
                        } else if (sItem.pos == 1) {
                            if (itemBaohiem != null && itemBaohiem.ID != sItem.ID) itemBaohiem = sItem;
                            if (itemBaohiem == null) itemBaohiem = sItem;
                        } else if (sItem.pos == 2) {
                            if (itemBua != null && itemBua.ID != sItem.ID) itemBua = sItem;
                            if (itemBua == null) itemBua = sItem;
                        }
                        showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        this.vHanhTrang.removeAllElements();
                        if (!isHopda) {
                            if (itemStone != null && this.numStone > 0 && mItem != null) {
                                if (sItem.pos == 0 || sItem.pos == 2) doSendCuongHoa(0);
                                this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                            }
                            if (sItem.pos == 0) {
                                this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                                if (this.numStone > 0) this.vHanhTrang.addElement(new mCommand(T.layra, this, 26));
                            }
                            if (sItem.pos == 1) this.vHanhTrang.addElement(new mCommand(T.layra, this, 27));
                            if (sItem.pos == 2) this.vHanhTrang.addElement(new mCommand(T.layra, this, 28));
                        } else if (itemStone != null) {
                            doSendCuongHoa(0);
                            this.vHanhTrang.addElement(new mCommand(T.nangcap, this, 14));
                            this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                            if (this.numStone > 0) this.vHanhTrang.addElement(new mCommand(T.layra, this, 26));
                        }
                        SortCmdItem();
                    }
                }
                if (GameCanvas.isTouch) this.center.resetCmd();
                break;
            case 10:
                if (!isChangeSubTab && this.slDapdo1 < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(this.slDapdo1);
                    if (cmd != null) this.cmdShowText = cmd;
                    dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand("", this, 2);
                textPercent = "";
                if (this.slDapdo1 >= 0 && vecItemCreate.size() > 0) {
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        this.vHanhTrang.removeAllElements();
                        if (sItem.pos != -1) {
                            this.vHanhTrang.addElement(cmdbovao);
                            this.vHanhTrang.addElement(cmdtao);
                        }
                        showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        SortCmdItem();
                    }
                }
                if (GameCanvas.isTouch) this.center.resetCmd();
                break;
            case 11:
                if (!isChangeSubTab && this.slDapdo1 < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(this.slDapdo1);
                    if (cmd != null) this.cmdShowText = cmd;
                    dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand("", this, 2);
                textPercent = "";
                if (this.slDapdo1 >= 0 && vecItemCreate.size() > 0) {
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        this.vHanhTrang.removeAllElements();
                        this.vHanhTrang.addElement(cmdbovao);
                        if (this.typePhiPhong == 1) this.vHanhTrang.addElement(cmdlayra);
                        this.vHanhTrang.addElement(cmdtao);
                        showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        SortCmdItem();
                    }
                }
                if (GameCanvas.isTouch) this.center.resetCmd();
                break;
            case 9:
                if (!isChangeSubTab && this.slDapdo1 < this.list.size()) {
                    mCommand cmd = (mCommand) this.list.elementAt(this.slDapdo1);
                    if (cmd != null) this.cmdShowText = cmd;
                    dosetIDCmdTouch("", 2);
                }
                this.timeAuToShowText = 10;
                this.beGinShowText = false;
                this.center = new mCommand("", this, 2);
                textPercent = "";
                if (this.slDapdo1 >= 0 && vecItemCreate.size() > 0) {
                    int x0 = this.x + this.size / 2 + this.slDapdo1 % this.colum * this.size + 16;
                    int y0 = 11 + this.y + this.slDapdo1 / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        this.vHanhTrang.removeAllElements();
                        if (sItem.pos != -1) {
                            this.vHanhTrang.addElement(cmdbovao);
                            this.vHanhTrang.addElement(cmdlayra);
                            this.vHanhTrang.addElement(cmdtao);
                        }
                        showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                        SortCmdItem();
                    }
                }
                if (GameCanvas.isTouch) this.center.resetCmd();
                break;
            case 2:
                if (selected == -1) {
                    if (!isFocusCharWearing) for (int i = 0; i < this.list.size(); i++) {
                        mCommand cmd = (mCommand) this.list.elementAt(i);
                        if (cmd != null && cmd.p != null && ((SetInfoData) cmd.p).xx == selected)
                            this.cmdShowText = cmd;
                    }
                    this.timeAuToShowText = 15;
                    this.beGinShowText = false;
                    this.center = new mCommand("Thay", this, -3, xCCmd, yCCmd);
                    if (GameCanvas.isTouch) this.center.resetCmd();
                    dosetIDCmdTouch("", -3);
                } else if (GameCanvas.isTouch) {
                    int x01 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                    int y01 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                    Item sItem1 = charWearing.equip[selected + this.indexWearing];
                    if (sItem1 != null) showItemInventoryInfo(sItem1, this.isSell, x01, y01, null);
                }
                this.vHanhTrang.removeAllElements();
                if (this.indexWearing <= 0) {
                    this.vHanhTrang.addElement(cmdTB2);
                } else if (this.indexWearing > 0) {
                    this.vHanhTrang.addElement(cmdTB1);
                }
                SortCmdItem();
                break;
        }
    }

    public void doChangeWearing() {
        if (selected == -1) {
            if (!isFocusCharWearing) for (int i = 0; i < this.list.size(); i++) {
                mCommand cmd = (mCommand) this.list.elementAt(i);
                if (cmd != null && cmd.p != null && ((SetInfoData) cmd.p).xx == selected) this.cmdShowText = cmd;
            }
            this.timeAuToShowText = 15;
            this.beGinShowText = false;
            this.center = new mCommand("Thay", this, -3, xCCmd, yCCmd);
            if (GameCanvas.isTouch) this.center.resetCmd();
            dosetIDCmdTouch("", -3);
        } else {
            int x01 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y01 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            Item sItem1 = charWearing.equip[selected + this.indexWearing];
            if (sItem1 != null) showItemInventoryInfo(sItem1, this.isSell, x01, y01, null);
        }
        this.vHanhTrang.removeAllElements();
        if (this.indexWearing <= 0) {
            this.vHanhTrang.addElement(cmdTB2);
        } else if (this.indexWearing > 0) {
            this.vHanhTrang.addElement(cmdTB1);
        }
        SortCmdItem();
    }

    private void setTextCharInfo() {
        this.infochar.removeAllElements();
        MainChar c = GameScr.mainChar;
        this.tile[0] = String.valueOf(c.level) + "+" + c.getPercent();
        this.infochar.addElement(new InfoTextShow(new String[]{c.name}, 0));
        this.infochar.addElement(new InfoTextShow(new String[]{"HP: " + c.hp + "/" + c.maxhp}, 0));
        this.infochar.addElement(new InfoTextShow(new String[]{"MP: " + c.mp + "/" + c.maxmp}, 0));
        int cout = 0, count2 = 0;
        for (int i = 0; i < GameScr.mainChar.options.size(); i++) {
            ItemOption itop = (ItemOption) GameScr.mainChar.options.elementAt(i);
            String[] option = itop.getInfoShow(0);
            for (int j = 0; j < option.length; j++) {
                this.infochar.addElement(new InfoTextShow(new String[]{option[j]}, itop.getColorPaint(false)));
            }
            count2 += option.length;
        }
        if (!GameCanvas.isTouch) this.wShowText = 5 * this.size;
    }

    public void doBuyShopSpecial() {
        this.left = new mCommand("Mua", this, 71);
        this.center = new mCommand("", this, 71);
        if (GameCanvas.isTouch) {
            this.left.caption = "";
            this.left.idAction = 1000;
            this.center.idAction = 1000;
        }
        dosetIDCmdTouch("", 71);
    }

    public void setPosScroll(int info_, int sl) {
        int num, i;
        this.indexMainTab = info_;
        selected = sl;
        switch (this.indexMainTab) {
            case 0:
                num = 0;
                if (!this.isMiniShop) {
                    for (int j = 0; j < Res.shopTemplate.size(); j++) {
                        GemTemp shop = (GemTemp) Res.shopTemplate.elementAt(j);
                        if (shop.shopType + 1 > num) num = shop.shopType + 1;
                    }
                    this.focusTab = 0;
                    setSelectTab("setPosScroll");
                    String[] tabShop = new String[5];
                    for (int k = 0; k < tabShop.length; k++)
                        tabShop[k] = "Quầy " + (k + 1);
                    currnentTabDetail = tabShop;
                }
                this.isSell = true;
                break;
            case 3:
                currnentTabDetail = myInfo;
                break;
            case 5:
                getInfoNap();
                currnentTabDetail = new String[this.inFoNap.size()];
                for (i = 0; i < this.inFoNap.size(); i++) {
                    mCommand cmd = (mCommand) this.inFoNap.elementAt(i);
                    currnentTabDetail[i] = cmd.caption;
                }
                break;
            case 7:
                currnentTabDetail = hoat_dong;
                break;
            case 4:
                currnentTabDetail = settings;
                break;
            case 6:
                currnentTabDetail = bangHoi;
                break;
            case 1:
                this.focusTab = 0;
                setSelectTab("setPosScroll 1");
                tabHanhTrang = new String[4];
                for (i = 0; i < 4; i++)
                    tabHanhTrang[i] = "Túi " + (i + 1);
                currnentTabDetail = tabHanhTrang;
                if (!this.isMiniShop) this.isSell = false;
                break;
        }
    }

    public int getCountPotion(int i) {
        return GameScr.mainChar.potions[i];
    }

    public mVector getInventori() {
        mVector menu = new mVector();
        int count = 0;
        int size5 = Char.inventory.size();
        for (int i = 0; i < size5; i++) {
            int ii = count;
            Item item = (Item) Char.inventory.elementAt(i);
            SetInfoData dt79 = new SetInfoData();
            dt79.itemInven1 = item;
            dt79.xx = ii;
            dt79.itemType = item.type;
            menu.addElement(new mCommand("", this, 79, dt79, dt79));
            count++;
        }
        return menu;
    }

    public mVector getWearing() {
        mVector menu = new mVector();
        int count = 0;
        if (charWearing.equip != null) {
            int size5 = charWearing.equip.length;
            for (int i = 0; i < size5; i++) {
                int ii = count;
                Item item = charWearing.equip[i];
                if (item != null) {
                    SetInfoData dt45 = new SetInfoData();
                    dt45.itemInven1 = item;
                    dt45.xx = ii;
                    menu.addElement(new mCommand("", this, 4, dt45, dt45));
                }
                count++;
            }
        }
        return menu;
    }

    private void doSetLeftSellWeapon(int type) {
        this.left = new mCommand("Mua", this, 117, (new StringBuilder(String.valueOf(type))).toString());
        this.center = new mCommand("", this, 117, (new StringBuilder(String.valueOf(type))).toString());
    }

    private void setListPotion() {
        this.list.removeAllElements();
        int size0 = GameScr.shop.size();
    }

    private void doSetLeftPotion() {
        this.left = new mCommand("Mua", this, 124);
        this.center = new mCommand("", this, 124);
    }

    private void setListSellGemItem(mVector shop) {
        this.list.removeAllElements();
        int size0 = shop.size();
        for (int i = 0; i < size0; i++) {
            GemTemp gem = (GemTemp) shop.elementAt(i);
            int ii = i;
            if (gem.isSell) {
                SetInfoData dt = new SetInfoData();
                dt.gemTempItem = gem;
                dt.xx = ii;
                this.list.addElement(new mCommand("", this, 122, dt, dt));
            }
        }
        Cout.Debug("BUY COMMAND 222");
    }

    private void dosetLeftSellGemItem() {
        this.left = new mCommand("Mua", this, 119);
        this.center = new mCommand("", this, 119);
        Cout.Debug("BUY COMMAND 333");
    }

    public void doSubTab() {
        if (this.list.size() > 42) {
            int c = 0;
            if (this.list.size() % 42 == 0) {
                c = this.list.size() / 42;
            } else if (this.list.size() % 42 != 0) {
                c = this.list.size() / 42 + 1;
            }
            currnentTabDetail = new String[c];
            byte b = index[0];
            index = new byte[c];
            for (int i = 0; i < 5; i++) {
                currnentTabDetail[i] = "Quầy " + (i + 1);
                index[i] = b;
            }
        }
    }

    private void setLeftKeepAndSellItem() {
        this.left = new mCommand((index[0] == 22) ? "Chuyển" : "Chọn", this, 31);
        this.center = new mCommand("", this, 31);
    }

    public void setLeftBuyItem() {
        this.left = new mCommand("Mua", this, 28);
        this.center = new mCommand("", this, 28);
        Cout.Debug("BUY COMMAND 111 ");
    }

    public void setMainTabSelect(String[] titlemaintab) {
        mainTab = titlemaintab;
        setTile();
    }

    public void resetAll() {
        this.list.removeAllElements();
        this.vHanhTrang.removeAllElements();
        this.left = new mCommand("", (IActionListener) GameCanvas.instance, 0);
        switch (this.indexMainTab) {
            case 0:
                this.isCharWearing = false;
                this.isHanhTrang = false;
                if (this.isMiniShop) {
                    this.isPaintMoney = true;
                    switch (index[this.indexSubTab]) {
                        case 24:
                            setInfo(getListBuyItem(), "resetAll 0");
                            setLeftBuyItem();
                            break;
                    }
                    break;
                }
                if (!this.isSkillClan) {
                    getShopTemplate();
                    setInfo(this.list, "resetAll 1");
                    doBuyShopSpecial();
                }
                break;
            case 3:
                this.isCharWearing = false;
                this.isHanhTrang = false;
                setPosWearing(true);
                break;
            case 1:
                setPosWearing(false);
                this.isHanhTrang = true;
                this.isPaintMoney = true;
                if (index[0] != 22 && index[0] != 23) setInfo(getInventori(), "resetAll 2");
                this.left = new mCommand("Chọn", this, 2);
                if (GameCanvas.isTouch) {
                    this.left.caption = "";
                    this.left.idAction = 1000;
                }
                break;
            case 2:
            case 8:
            case 9:
            case 10:
            case 11:
                setPosWearing(false);
                charWearing = (Char) GameScr.mainChar;
                setTextCharInfo();
                isFocusCharWearing = false;
                isChangeSubTab = false;
                isFocusDetailMenu = false;
                this.isCharWearing = true;
                setInfo(getWearing(), "resetAll 3");
                this.left = new mCommand("Chọn", this, 2);
                if (GameCanvas.isTouch) {
                    this.left.caption = "";
                    this.left.idAction = 1000;
                }
                break;
        }
    }

    public void resetAllInven() {
        if (this.indexMainTab != 1) return;
        this.list.removeAllElements();
        this.left = new mCommand("", (IActionListener) GameCanvas.instance, 0);
        switch (this.indexMainTab) {
            case 0:
                this.isCharWearing = false;
                this.isHanhTrang = false;
                if (this.isMiniShop) {
                    this.isPaintMoney = true;
                    switch (index[this.indexSubTab]) {
                        case 24:
                            setInfo(getListBuyItem(), "resetAllInven 0");
                            setLeftBuyItem();
                            break;
                    }
                    break;
                }
                if (!this.isSkillClan) {
                    getShopTemplate();
                    setInfo(this.list, "resetAllInven 1");
                    doBuyShopSpecial();
                }
                break;
            case 3:
                this.isCharWearing = false;
                this.isHanhTrang = false;
                setPosWearing(true);
                break;
            case 1:
                setPosWearing(false);
                this.isHanhTrang = true;
                this.isPaintMoney = true;
                if (index[0] != 22 && index[0] != 23) setInfo(getInventori(), "resetAllInven 2");
                this.left = new mCommand("Chọn", this, 2);
                if (GameCanvas.isTouch) {
                    this.left.caption = "";
                    this.left.idAction = 1000;
                }
                break;
            case 2:
            case 8:
            case 9:
            case 10:
            case 11:
                setPosWearing(false);
                charWearing = (Char) GameScr.mainChar;
                isFocusCharWearing = false;
                isChangeSubTab = false;
                isFocusDetailMenu = false;
                this.isCharWearing = true;
                setInfo(getWearing(), " resetAllInven 3");
                this.left = new mCommand("Chọn", this, 2);
                if (GameCanvas.isTouch) {
                    this.left.caption = "";
                    this.left.idAction = 1000;
                }
                break;
        }
    }

    public void setSelectTab(String pos) {
        this.countTextmua = 0;
        this.list.removeAllElements();
        this.left = new mCommand("", (IActionListener) GameCanvas.instance, 0);
        switch (this.indexMainTab) {
            case 0:
                this.isCharWearing = false;
                this.isHanhTrang = false;
                if (this.isMiniShop) {
                    this.isPaintMoney = true;
                    switch (index[this.indexSubTab]) {
                        case 24:
                            setInfo(getListBuyItem(), "setSelectTab 0");
                            setLeftBuyItem();
                            break;
                    }
                    break;
                }
                if (!this.isSkillClan) {
                    getShopTemplate();
                    setInfo(this.list, "setSelectTab 1");
                    doBuyShopSpecial();
                }
                break;
            case 3:
                this.isCharWearing = false;
                this.isHanhTrang = false;
                setPosWearing(true);
                break;
            case 1:
                if (selected > Char.inventory.size() - 1 || selected == -1) selected = 0;
                setPosWearing(false);
                this.isHanhTrang = true;
                this.isPaintMoney = true;
                if (index[0] != 22 && index[0] != 23) setInfo(getInventori(), "setSelectTab 2");
                this.left = new mCommand(T.chon, this, 2);
                if (GameCanvas.isTouch) {
                    this.left.caption = "";
                    this.left.idAction = 1000;
                }
                if (GameCanvas.isTouch) showinfonext();
                break;
            case 2:
            case 8:
            case 9:
            case 10:
            case 11:
                setPosWearing(false);
                charWearing = (Char) GameScr.mainChar;
                setTextCharInfo();
                isFocusCharWearing = false;
                isChangeSubTab = false;
                isFocusDetailMenu = false;
                this.isCharWearing = true;
                setInfo(getWearing(), "setSelectTab 3");
                this.left = new mCommand("Chọn", this, 2);
                if (GameCanvas.isTouch) {
                    this.left.caption = "";
                    this.left.idAction = 1000;
                }
                break;
        }
    }

    private void getShopTemplate() {
        this.list.removeAllElements();
        int size0 = Res.shopTemplate.size();
        for (int i = 0; i < size0; i++) {
            GemTemp shop = (GemTemp) Res.shopTemplate.elementAt(i);
            int ii = i;
            if (shop.shopType == this.focusTab && shop.isSell) {
                SetInfoData dt = new SetInfoData();
                dt.index = shop.id;
                dt.xx = ii;
                this.list.addElement(new mCommand("", this, 73, dt, dt));
            }
        }
    }

    public void setInfo(mVector list, String pos) {
        this.list = list;
        setCmdCenter("setInfo");
    }

    public void getInfoNap() {
        if (GameCanvas.gameScr.decriptNap.size() == 0) return;
        this.inFoNap = new mVector();
        for (int i = 0; i < GameCanvas.gameScr.decriptNap.size(); i++) {
            String syn = (String) GameCanvas.gameScr.syntaxNap.elementAt(i);
            String center = (String) GameCanvas.gameScr.centerNap.elementAt(i);
            String decription = (String) GameCanvas.gameScr.decriptNap.elementAt(i);
            String a = String.valueOf(syn) + ":" + center + ":" + decription;
            if (center.length() >= 4) {
                this.inFoNap.addElement(new mCommand((String) GameCanvas.gameScr.decriptNap.elementAt(i), this, 630, a));
            } else {
                this.inFoNap.addElement(new mCommand((String) GameCanvas.gameScr.decriptNap.elementAt(i), this, 660, a));
            }
        }
    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
        SetInfoData dt73;
        SetInfoData dt74;
        SetInfoData dt75;
        SetInfoData dt76;
        SetInfoData dt77;
        SetInfoData dt78;
        SetInfoData dt79;
        SetInfoData dt122;
        SetInfoData dt123;
        switch (idAction) {
            case 73:
                dt73 = (SetInfoData) paint;
                FontTeam.smallFontColor[3].drawString(g, (new StringBuilder(String.valueOf((dt73.index > 1) ? (Res.getShopByID((short) dt73.index)).value : 1))).toString(), x + 10 - ((this.size == 20) ? 4 : 0), y + 5 - ((this.size == 20) ? 5 : 0), 1, false);
                break;
            case 74:
                dt74 = (SetInfoData) paint;
                break;
            case 75:
                dt75 = (SetInfoData) paint;
                break;
            case 76:
                dt76 = (SetInfoData) paint;
                if (dt76.gemTempItem.isEff != -1) {
                    Res.paintRectColor(g, x - 9, y - 9, 17, 17, dt76.gemTempItem.count, GemTemp.color[dt76.gemTempItem.isEff], -260847);
                    dt76.gemTempItem.count += 3;
                    if (dt76.gemTempItem.count > 68) dt76.gemTempItem.count = 0;
                }
                break;
            case 77:
                dt77 = (SetInfoData) paint;
                break;
            case 78:
                dt78 = (SetInfoData) paint;
                break;
            case 79:
                dt79 = (SetInfoData) paint;
                break;
            case 122:
                dt122 = (SetInfoData) paint;
                break;
            case 123:
                dt123 = (SetInfoData) paint;
                break;
        }
    }

    public void showMaincharInfo() {
        if (isFocusCharWearing) {
            int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16 + this.size * 2;
            int y00 = 11 + this.y + selected / this.colum * this.size;
            mVector minfo = this.infochar;
            setShowText(minfo, x00, y00, null, true, true);
            this.numItemStart = 0;
        }
    }

    private void showItemInventoryInfo(Item item, boolean isSell, int x, int y, Item itCompare) {
        setShowText(item.getInfoItemShow(itCompare, true), x, y, null, true, false);
    }

    public void updateItemDapdo() {
        int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
        int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
        setShowText(mItem.getInfoItemShow(null, true), x0, y0, null, true, false);
        setStart();
    }

    public void doPutItemShop() {
        closeText();
    }

    public void doSelectedInventori() {
        mVector menu = new mVector();
        if (!GameCanvas.isTouch) closeText();
        for (int i = 0; i < Char.inventory.size(); i++) {
            if (i == selected) {
                Item it = (Item) Char.inventory.elementAt(i);
                SetInfoData dt79 = new SetInfoData();
                dt79.itemInven1 = it;
                dt79.xx = i;
                dt79.itemType = it.type;
                cmdban = new mCommand(T.ban, this, 102, dt79);
                cmdsudung = new mCommand(T.sudung, this, -3, it);
                cmdvut = new mCommand(T.vut, this, 108, it);
                menu.addElement(cmdban);
                menu.addElement(cmdsudung);
                menu.addElement(cmdvut);
                if (it.type != Item.TYPE_MP && it.type != Item.TYPE_HP && it.catagory != 4) {
                    menu.addElement(cmdbanNhieu);
                } else {
                    menu.addElement(cmdGangphim);
                }
                if (!GameCanvas.isTouch) GameCanvas.menu.startAt(menu, 2);
                break;
            }
        }
    }

    private boolean isWeapone(int type) {
        return (type >= 3 && type <= 7);
    }

    protected void doUseItem() {
        Item item = (Item) Char.inventory.elementAt(selected);
        if (item != null) {
            if (item.type == Item.TYPE_HP || item.type == Item.TYPE_MP) {
                if (item.type == Item.TYPE_HP && GameScr.mainChar.hp < GameScr.mainChar.maxhp) {
                    GameService.gI().useItem(item.ID);
                    return;
                }
                if (item.type == Item.TYPE_MP && GameScr.mainChar.mp < GameScr.mainChar.maxmp) {
                    GameService.gI().useItem(item.ID);
                    return;
                }
            }
            GameService.gI().useItem(item.ID);
        }
    }

    public void doBuyItem() {
        if (this.isMiniShop) this.focusTab = index[this.indexTabMiniShop];
        this.readyBuy.size();
        if (this.focusTab != 21) ;
    }

    protected void doGiveSkillToQuickSlot(int skillType) {
        mVector menuItems = new mVector();
        if (this.indexMainTab == 1) {
            Item item = (Item) Char.inventory.elementAt(selected);
            if (item != null && (item.type == Item.TYPE_HP || item.type == Item.TYPE_MP || item.catagory == 4)) {
                for (int i = 1; i < 5; i++) {
                    SetInfoData dt = new SetInfoData();
                    dt.ID = item.ID;
                    dt.isSkill = false;
                    dt.idIcon = item.idIcon;
                    dt.typePotion = item.type;
                    menuItems.addElement(new mCommand(String.valueOf(T.phimso) + " " + (1 + i), this, 42 + i, dt));
                }
                GameCanvas.menu.startAt(menuItems, 2);
            }
        } else {
            boolean buff = false;
            SkillTemplate skill = (SkillTemplate) GameScr.vec_skill.elementAt(selected);
            if (skill != null) {
                if (skill.type == SkillTemplate.TYPE_PASSIVE) return;
                if (skill.type == SkillTemplate.TYPE_BUFF) buff = true;
                for (int i = 0; i < 5; i++) {
                    int ii = i;
                    SetInfoData dt = new SetInfoData();
                    dt.index = skillType;
                    dt.iss = buff;
                    dt.xx = ii;
                    dt.isSkill = true;
                    menuItems.addElement(new mCommand(String.valueOf(T.phimso) + " " + (1 + i), this, 42 + i, dt));
                }
                GameCanvas.menu.startAt(menuItems, 2);
            }
        }
    }

    public boolean setSizeKeepItem(int i) {
        return false;
    }

    public mVector getListBuyItem() {
        mVector menu = new mVector();
        int size0 = this.sellItem.size();
        for (int i = 0; i < size0; i++) {
            int ii = i;
            menu.addElement(new mCommand("", this, 30, (new StringBuilder(String.valueOf(ii))).toString(), (new StringBuilder(String.valueOf(ii))).toString()));
        }
        return menu;
    }

    public void perform(int idAction, Object p) {
        Item ite79;
        SetInfoData dtshop;
        mVector menu;
        Item mit;
        int num1;
        Item it7;
        SetInfoData info8;
        mVector vt9;
        String str11;
        mVector v, currQuest, currQuest1;
        QuestInfo q, qq;
        Item ite29, ite30, itemch, ite38;
        int indexitem;
        SetInfoData dt42, dt43, dt44, dt45, dt46, dt92;
        Item item102;
        mVector menuItems;
        int i;
        Item i108;
        Char c;
        String str;
        int point;
        SetInfoData s336;
        mVector minfo = new mVector();
        InfoTextShow in = null;
        String inFo = null;
        String[] array = null;
        int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
        int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
        Cout.Debug(" idAction " + idAction);
        mSystem.println("perform mainmenu " + idAction);
        switch (idAction) {
            case 79:
                ite79 = (Item) Char.inventory.elementAt(selected);
                if (ite79 != null) {
                    int type = ite79.getTypeItem();
                    Item itcp = null;
                    this.isgangphim = false;
                    if (POS_ITEM_IN_EQUIP[type] > -1) itcp = GameScr.mainChar.equip[POS_ITEM_IN_EQUIP[type]];
                    showItemInventoryInfo(ite79, this.isSell, x0, y0, itcp);
                    if (GameCanvas.isTouch && p != null) {
                        this.vHanhTrang.removeAllElements();
                        cmdsudung = new mCommand(T.sudung, this, -3, ite79);
                        cmdvut = new mCommand(T.vut, this, 108, ite79);
                        cmdban = new mCommand(T.ban, this, 102, ite79);
                        this.vHanhTrang.addElement(cmdban);
                        this.vHanhTrang.addElement(cmdsudung);
                        this.vHanhTrang.addElement(cmdvut);
                        if (ite79.type != Item.TYPE_MP && ite79.type != Item.TYPE_HP && ite79.catagory != 4) {
                            this.vHanhTrang.addElement(cmdbanNhieu);
                        } else {
                            this.isgangphim = true;
                            this.vHanhTrang.addElement(cmdGangphim);
                        }
                        SortCmdItem();
                    }
                }
                break;
            case 4:
                if (p != null) {
                    SetInfoData setInfoData = (SetInfoData) p;
                    showItemInventoryInfo(setInfoData.itemInven1, this.isSell, x0, y0, null);
                }
                break;
            case 2:
                if (this.indexMainTab == 8 && !GameCanvas.isTouch) {
                    doSelectItemCuongHoa();
                    return;
                }
                if (this.indexMainTab != 2 && this.indexMainTab != 8 && this.indexMainTab != 9 && this.indexMainTab != 10 && this.indexMainTab != 11) {
                    doAction2();
                    break;
                }
                if (isFocusCharWearing) {
                    int x00 = this.x + this.size / 2 + selected % this.colum * this.size + 16 + this.size * 2;
                    int y00 = 11 + this.y + selected / this.colum * this.size;
                    minfo = this.infochar;
                    mVector listcolorpaint = new mVector();
                    for (int j = 0; j < GameScr.mainChar.options.size(); j++) {
                        ItemOption itop = (ItemOption) GameScr.mainChar.options.elementAt(j);
                        String[] option = itop.getInfoShow(0);
                        short[] colorindex = itop.getColorPaint();
                        for (int k = 0; k < colorindex.length; k++)
                            listcolorpaint.addElement((new StringBuilder(String.valueOf(colorindex[k]))).toString());
                    }
                    setShowText(minfo, x00, y00, null, true, true);
                    this.numItemStart = 0;
                    break;
                }
                if (!isFocusCharWearing && isFocusDetailMenu) {
                    if (selected < 4) dofireBasePoint();
                    break;
                }
                if (!isFocusCharWearing && !isFocusDetailMenu) {
                    doAction2();
                    doShowInfoItemWearing();
                }
                break;
            case -3:
                if (this.indexMainTab == 3) {
                    if (this.indexWearing <= 0) {
                        if (this.indexMainTab == 1) {
                            if (p != null) doUseItem();
                        } else if (isTabQuest()) {
                            String infoMain = "";
                            mVector mVector1 = ListQuest[indexTypeQuest];
                            QuestInfo questInfo = (QuestInfo) mVector1.elementAt(indexQuest);
                            if (questInfo != null) {
                                in = new InfoTextShow(new String[]{questInfo.name}, 0);
                                minfo.addElement(in);
                                String[] data = Util.split(questInfo.info, "\n");
                                for (int j = 0; j < data.length; j++) {
                                    byte color = (byte) (data[j].charAt(0) - 48);
                                    if (!isDegit(data[j].charAt(0))) {
                                        color = 0;
                                    } else {
                                        data[j] = data[j].substring(1);
                                    }
                                    in = new InfoTextShow(new String[]{data[j]}, color);
                                    minfo.addElement(in);
                                }
                                infoMain = String.valueOf(questInfo.name) + " \n" + questInfo.info;
                            }
                            if (!infoMain.equals(""))
                                setShowText(minfo, this.x + this.colum * this.size, this.y + this.size * 2, null, true, false);
                        }
                        if (this.isSkill) showInfoSkill("perform -3");
                        if (this.isCharWearing && selected >= 0 && selected < charWearing.equip.length - 1 && charWearing.equip[selected] != null)
                            showItemInventoryInfo(charWearing.equip[selected], this.isSell, x0, y0, null);
                        if (selected == 14) GameService.gI().dochangeCharWearing();
                    }
                    break;
                }
                if (this.indexMainTab == 1) {
                    if (p != null) doUseItem();
                } else if (isTabQuest()) {
                    String infoMain = "";
                    mVector mVector1 = ListQuest[indexTypeQuest];
                    QuestInfo questInfo = (QuestInfo) mVector1.elementAt(indexQuest);
                    if (questInfo != null) {
                        in = new InfoTextShow(new String[]{questInfo.name}, 0);
                        minfo.addElement(in);
                        String[] data = Util.split(questInfo.info, "\n");
                        for (int j = 0; j < data.length; j++) {
                            byte color = (byte) (data[j].charAt(0) - 48);
                            if (!isDegit(data[j].charAt(0))) {
                                color = 0;
                            } else {
                                data[j] = data[j].substring(1);
                            }
                            in = new InfoTextShow(new String[]{data[j]}, color);
                            minfo.addElement(in);
                        }
                        infoMain = String.valueOf(questInfo.name) + " \n" + questInfo.info;
                    }
                    if (!infoMain.equals(""))
                        setShowText(minfo, this.x + this.colum * this.size, this.y + this.size * 2, null, true, false);
                }
                if (this.isSkill) showInfoSkill("perform -3 2");
                if (this.isCharWearing && selected >= 0 && selected < charWearing.equip.length - 1 && charWearing.equip[selected] != null)
                    showItemInventoryInfo(charWearing.equip[selected], this.isSell, x0, y0, null);
                if (selected == 14) GameService.gI().dochangeCharWearing();
                break;
            case -2:
                dtshop = (SetInfoData) p;
                showItemInventoryInfo(dtshop.itemInven1, this.isSell, x0, y0, null);
                break;
            case -1:
                if (GameCanvas.isTouch) showInfoSkill("perform -1");
                break;
            case 0:
                doCmdCenter();
                if (this.isSkill && GameCanvas.isTouch) showInfoSkill("perform 0");
                break;
            case -101:
                if (this.isSkill) showInfoSkill("perform -101");
                break;
            case 1:
                doClose();
                break;
            case 3:
                menu = new mVector();
                menu.addElement(new mCommand("Chọn", this, 2));
                menu.addElement(new mCommand("Xem thêm", this, 4));
                GameCanvas.menu.startAt(menu, 0);
                if (this.isSkill) showInfoSkill("perform 3");
                break;
            case 5:
                mit = (Item) this.mShop.elementAt(selected);
                if (mit != null) {
                    if (!GameCanvas.isTouch) {
                        String str1 = String.valueOf(T.doyouwantbuy) + " " + mit.name + " " + T.withprice + " " + mit.priceShop + " " + Item.moneyType[mit.priceType] + " " + T.no + " ?";
                        if (!captionServer.equals("") && !infoBuySellServer.equals(""))
                            str1 = getInfoBuySell(captionServer, infoBuySellServer, mit);
                        GameCanvas.startYesNoDlg(str1, new mCommand("", this, 7));
                        break;
                    }
                    if (!this.canbuy) {
                        this.textHoimua = String.valueOf(T.doyouwantbuy) + " " + mit.name + " " + T.withprice + " " + mit.priceShop + " " + Item.moneyType[mit.priceType] + " " + T.no + " ?";
                        if (!captionServer.equals("") && !infoBuySellServer.equals(""))
                            this.textHoimua = getInfoBuySell(captionServer, infoBuySellServer, mit);
                        showTextmua();
                        break;
                    }
                    this.canbuy = false;
                    cmdBuy.caption = T.Buy;
                    GameService.gI().buyItem(mit.ID, this.idNPC_Shop, this.catNPC_Shop, mit.catagory, 1);
                    mSystem.println("send mua item to sv ");
                    this.isTextmua = false;
                    cmdBuy.caption = T.Buy;
                    this.canbuy = false;
                }
                break;
            case 6:
                num1 = 1;
                try {
                    num1 = Integer.parseInt(GameCanvas.inputDlg.tfInput.getText());
                } catch (Exception e) {
                    num1 = 0;
                }
                if (num1 > 0) {
                    Item mit1 = (Item) this.mShop.elementAt(selected);
                    if (mit1 != null) {
                        String str1 = String.valueOf(T.doyouwantbuy) + " " + num1 + " " + mit1.name + " " + T.withprice + " " + (num1 * mit1.priceShop) + " " + Item.moneyType[mit1.priceType] + " " + T.no + " ?";
                        SetInfoData info = new SetInfoData();
                        info.cat = mit1.catagory;
                        info.ID = mit1.ID;
                        info.num = num1;
                        GameCanvas.startYesNoDlg(str1, new mCommand("", this, 8, info));
                    }
                    break;
                }
                GameCanvas.endDlg();
                break;
            case 7:
                it7 = (Item) this.mShop.elementAt(selected);
                if (it7 != null) {
                    GameService.gI().buyItem(it7.ID, this.idNPC_Shop, this.catNPC_Shop, it7.catagory, 1);
                } else {
                    GameCanvas.endDlg();
                }
                GameCanvas.endDlg();
                break;
            case 8:
                info8 = (SetInfoData) p;
                GameService.gI().buyItem(info8.ID, this.idNPC_Shop, this.catNPC_Shop, info8.cat, info8.num);
                GameCanvas.endDlg();
                break;
            case 9:
                vt9 = (mVector) p;
                GameCanvas.menu.startAt(vt9, 0);
                break;
            case 10:
                GameService.gI().receiveQuest(0, 0, 3);
                this.questInfo.removeAllElements();
                break;
            case 11:
                str11 = GameCanvas.inputDlg.tfInput.getText();
                if (!str11.equals("")) {
                    GameCanvas.startYesNoDlg(T.chatngoc, new mCommand(T.OK, this, 12, str11));
                    break;
                }
                GameCanvas.endDlg();
                break;
            case 12:
                if (p != null) {
                    String strTG = (String) p;
                    GameService.gI().doChatWorld(strTG);
                    GameCanvas.endDlg();
                }
                break;
            case 13:
                if (this.slDapdo1 >= 0 && vecItemDapDo.size() > 0) {
                    Item sItem = (Item) vecItemDapDo.elementAt(this.slDapdo1);
                    if (sItem != null) {
                        if (sItem.pos == 0) {
                            if (itemStone == null) itemStone = sItem;
                            if (this.numStone >= 10 || (itemStone != null && this.numStone >= itemStone.quantity) || textPercent.equals("100,0%")) {
                                GameCanvas.addNotify(T.khongthebothem, (byte) 0);
                                return;
                            }
                            int sizee = 10;
                            if (isHopda) sizee = 5;
                            if (this.numStone < sizee && this.numStone < itemStone.quantity) this.numStone++;
                            this.vHanhTrang.removeAllElements();
                            if (itemStone != null && mItem != null) {
                                this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                                doSendCuongHoa(0);
                            }
                            if (isHopda) {
                                this.vHanhTrang.addElement(new mCommand(T.nangcap, this, 14));
                                doSendCuongHoa(0);
                            }
                            this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                            this.vHanhTrang.addElement(new mCommand(T.layra, this, 26));
                            SortCmdItem();
                        }
                        if (sItem.pos == 1 && itemBaohiem == null) {
                            itemBaohiem = sItem;
                            this.vHanhTrang.removeAllElements();
                            if (itemStone != null && this.numStone > 0 && mItem != null)
                                this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                            this.vHanhTrang.addElement(new mCommand(T.layra, this, 27));
                            SortCmdItem();
                        }
                        if (sItem.pos == 2 && itemBua == null) {
                            itemBua = sItem;
                            this.vHanhTrang.removeAllElements();
                            if (itemStone != null && this.numStone > 0 && mItem != null) {
                                doSendCuongHoa(0);
                                this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                            }
                            this.vHanhTrang.addElement(new mCommand(T.layra, this, 28));
                            SortCmdItem();
                        }
                    }
                }
                break;
            case 14:
                if (isHopda) {
                    doSendCuongHoa(4);
                    break;
                }
                doSendCuongHoa(1);
                break;
            case 15:
                if (GameCanvas.isTouch) {
                    GameCanvas.mapScr.switchToMe(this.lastSCR);
                    break;
                }
                v = new mVector();
                currQuest1 = ListQuest[indexTypeQuest];
                qq = (QuestInfo) currQuest1.elementAt(indexQuest);
                if (qq != null && qq.status == 2) v.addElement(cmdHuyQuest);
                v.addElement(cmdChangeMapScr);
                GameCanvas.menu.startAt(v, 0);
                break;
            case 17:
                currQuest = ListQuest[indexTypeQuest];
                q = (QuestInfo) currQuest.elementAt(indexQuest);
                GameService.gI().receiveQuestNew(2, q.idQuest, q.mainsub);
                this.vHanhTrang.removeElement(cmdHuyQuest);
                break;
            case 18:
                GameCanvas.mapScr.switchToMe(this.lastSCR);
                break;
            case 19:
                if (Char.Diemtiemnang > 0) {
                    if (!this.isTextmua) {
                        String ct1 = String.valueOf(T.hoicong) + " " + '\001' + " " + T.point + " " + T.nametiemnang[selected] + " " + T.khong + " ?";
                        this.textHoimua = ct1;
                        showTextmua();
                        this.vHanhTrang.removeElement(cmdcong5);
                        this.vHanhTrang.removeElement(cmdcong10);
                        cmdcong1.caption = T.yes;
                        SortCmdItem();
                        break;
                    }
                    GameService.gI().SkillPoint(1, selected, 1);
                    resetPopup();
                    break;
                }
                GameCanvas.addNotify(T.khongthecong, (byte) 0);
                break;
            case 20:
                if (Char.Diemtiemnang >= 5) {
                    if (!this.isTextmua) {
                        String ct2 = String.valueOf(T.hoicong) + " " + '\005' + " " + T.point + " " + T.nametiemnang[selected] + " " + T.khong + " ?";
                        this.textHoimua = ct2;
                        showTextmua();
                        this.vHanhTrang.removeElement(cmdcong1);
                        this.vHanhTrang.removeElement(cmdcong10);
                        cmdcong5.caption = T.yes;
                        SortCmdItem();
                        break;
                    }
                    GameService.gI().SkillPoint(1, selected, 5);
                    resetPopup();
                    break;
                }
                GameCanvas.addNotify(T.khongthecong, (byte) 0);
                break;
            case 21:
                if (Char.Diemtiemnang >= 10) {
                    if (!this.isTextmua) {
                        String ct3 = String.valueOf(T.hoicong) + " " + '\n' + " " + T.point + " " + T.nametiemnang[selected] + " " + T.khong + " ?";
                        this.textHoimua = ct3;
                        showTextmua();
                        this.vHanhTrang.removeElement(cmdcong5);
                        this.vHanhTrang.removeElement(cmdcong1);
                        cmdcong10.caption = T.yes;
                        SortCmdItem();
                        break;
                    }
                    GameService.gI().SkillPoint(1, selected, 10);
                    resetPopup();
                    break;
                }
                GameCanvas.addNotify(T.khongthecong, (byte) 0);
                break;
            case 22:
                GameService.gI().SkillPoint(1, selected, 1);
                GameCanvas.endDlg();
                break;
            case 23:
                GameService.gI().SkillPoint(1, selected, 5);
                GameCanvas.endDlg();
                break;
            case 24:
                GameService.gI().SkillPoint(1, selected, 10);
                GameCanvas.endDlg();
                break;
            case 25:
                GameService.gI().sellItem((byte) 0, (short) -1);
                break;
            case 26:
                textPercent = "";
                this.numStone--;
                if (this.numStone <= 0) {
                    this.numStone = 0;
                    itemStone = null;
                    this.vHanhTrang.removeAllElements();
                    this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                }
                if (itemStone != null && isHopda) doSendCuongHoa(0);
                if (itemStone != null && mItem != null) doSendCuongHoa(0);
                SortCmdItem();
                break;
            case 27:
                itemBaohiem = null;
                this.vHanhTrang.removeAllElements();
                if (itemStone != null && mItem != null && this.numStone > 0)
                    this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                SortCmdItem();
                break;
            case 28:
                itemBua = null;
                this.vHanhTrang.removeAllElements();
                if (itemStone != null && mItem != null && this.numStone > 0) {
                    this.vHanhTrang.addElement(new mCommand(T.cuonghoa, this, 14));
                    doSendCuongHoa(0);
                }
                if (itemStone != null && mItem != null) doSendCuongHoa(0);
                this.vHanhTrang.addElement(new mCommand(T.bovao, this, 13));
                SortCmdItem();
                break;
            case 29:
                ite29 = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (ite29 != null) {
                    if (!checkinVector(vecMaterial, ite29)) vecMaterial.addElement(ite29);
                    int p29 = getpost(vecMaterial, ite29);
                    if (p29 != -1) {
                        if (this.listNum[p29] == 0 && ite29.quantity >= this.miniItem) {
                            this.listNum[p29] = this.listNum[p29] + this.miniItem;
                            break;
                        }
                        if (this.listNum[p29] < this.miniItem && ite29.quantity >= this.miniItem) {
                            this.listNum[p29] = this.listNum[p29] + 1;
                            break;
                        }
                        GameCanvas.addNotify(T.khongthebothem, (byte) 0);
                    }
                }
                break;
            case 30:
                ite30 = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (ite30 != null) {
                    int p30 = getpost(vecMaterial, ite30);
                    if (p30 != -1 && this.listNum[p30] == this.miniItem) this.listNum[p30] = 0;
                    if (p30 != -1 && this.listNum[p30] > 0) this.listNum[p30] = this.listNum[p30] - 1;
                    if (p30 != -1 && this.listNum[p30] <= 0) vecMaterial.removeElementAt(p30);
                }
                break;
            case 31:
                if (!this.Waitcreate) {
                    if (this.listNum != null && this.listNum.length > 5 && vecMaterial.size() > 5) {
                        GameService.gI().doCreateItem(vecMaterial, this.listNum);
                        break;
                    }
                    GameCanvas.addNotify(T.khongthetao, (byte) 0);
                }
                break;
            case 32:
                itemch = (Item) vecItemCreate.elementAt(this.slDapdo1);
                if (itemch != null) {
                    if (itemch.plus >= 10) itemChuyenHoa1 = itemch;
                    if (itemch.plus >= 4 && itemch.plus < 10) itemChuyenHoa0 = itemch;
                    if (itemch.isPotion()) itemChuyenHoa2 = itemch;
                }
                break;
            case 33:
                if (itemChuyenHoa0 != null && itemChuyenHoa1 != null) {
                    this.tickChuyenhoa = -1;
                    short id3 = -1;
                    if (itemChuyenHoa2 != null) id3 = itemChuyenHoa2.ID;
                    GameService.gI().doUpgradeItem((byte) 5, itemChuyenHoa0.ID, itemChuyenHoa1.ID, id3);
                    break;
                }
                GameCanvas.addNotify(T.khongthechuyenhoa, (byte) 0);
                break;
            case 34:
                this.right.performAction();
                GameCanvas.endDlg();
                break;
            case 35:
                mSystem.println("perfom 35 ddd  " + this.typePhiPhong);
                if (this.typePhiPhong == 0) {
                    if (idicon != -1) idicon = -1;
                    Item ite35 = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (ite35 != null) {
                        if (!checkinVector(vecMaterial, ite35)) vecMaterial.addElement(ite35);
                        int p29 = getpost(vecMaterial, ite35);
                        if (p29 != -1) {
                            if (this.listNum[p29] == 0 && ite35.quantity >= this.miniItem) {
                                this.listNum[p29] = this.listNum[p29] + this.miniItem;
                                break;
                            }
                            if (this.listNum[p29] < this.miniItem && ite35.quantity >= this.miniItem) {
                                this.listNum[p29] = this.listNum[p29] + 1;
                                break;
                            }
                            GameCanvas.addNotify(T.khongthebothem, (byte) 0);
                        }
                    }
                    break;
                }
                if (this.typePhiPhong == 1) {
                    Item ite35 = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (ite35 != null) {
                        if (CheckList(vecMaterial, ite35)) {
                            GameCanvas.startOKDlg(T.bandabo);
                            return;
                        }
                        if (ite35.isPotion()) {
                            if (idicon != -1) {
                                if (!checkinVectorPhiPhong(vecMaterial, ite35)) {
                                    vecMaterial.addElement(ite35);
                                    int pos = getPost();
                                    if (pos != -1 && ite35.quantity >= this.miniItem) {
                                        this.listItem[pos] = ite35;
                                        this.listNum[pos] = this.miniItem;
                                        return;
                                    }
                                }
                                int p29 = getpostPhiPhong(vecMaterial, ite35);
                                if (p29 != -1) {
                                    if (this.listNum[p29] == 0 && ite35.quantity >= this.miniItem) {
                                        this.listNum[p29] = this.listNum[p29] + this.miniItem;
                                        break;
                                    }
                                    if (this.listNum[p29] < this.miniItem && ite35.quantity >= this.miniItem) {
                                        this.listNum[p29] = this.listNum[p29] + 1;
                                        break;
                                    }
                                    GameCanvas.addNotify(T.khongthebothem, (byte) 0);
                                }
                                break;
                            }
                            GameCanvas.startOKDlg(T.bophiphongvao);
                            break;
                        }
                        idicon = ite35.idIcon;
                        this.itemPP = ite35;
                        GameService.gI().doCreatephiphong(1, ite35, null);
                        vecMaterial.removeAllElements();
                        this.listNum = null;
                        this.listItem = new Item[6];
                        this.listNum = new int[6];
                    }
                }
                break;
            case 36:
                if (this.typePhiPhong == 0) {
                    Item ite36 = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (ite36 != null) {
                        int p30 = getpost(vecMaterial, ite36);
                        if (p30 != -1 && this.listNum[p30] == this.miniItem) this.listNum[p30] = 0;
                        if (p30 != -1 && this.listNum[p30] > 0) this.listNum[p30] = this.listNum[p30] - 1;
                        if (p30 != -1 && this.listNum[p30] <= 0) vecMaterial.removeElementAt(p30);
                    }
                    break;
                }
                if (this.typePhiPhong == 1) {
                    Item ite36 = (Item) vecItemCreate.elementAt(this.slDapdo1);
                    if (ite36 != null) {
                        int p30 = getpostPhiPhong(vecMaterial, ite36);
                        if (p30 != -1) {
                            vecMaterial.removeElement(ite36);
                            this.listNum[p30] = 0;
                            for (int j = 0; j < this.listItem.length; j++) {
                                if (this.listItem[j] != null && (this.listItem[j]).ID == ite36.ID) {
                                    this.listItem[j] = null;
                                    break;
                                }
                            }
                        }
                    }
                }
                break;
            case 37:
                if (this.typePhiPhong == 0) {
                    if (vecItemCreate.size() < 6) {
                        GameCanvas.addNotify(T.chuadunguyenlieu, (byte) 0);
                        break;
                    }
                    GameService.gI().doCreatephiphong(0, null, null);
                    break;
                }
                if (this.typePhiPhong == 1) {
                    for (int j = 0; j < this.listItem.length; j++) {
                        if (this.listItem[j] == null) {
                            GameCanvas.startOKDlg(T.chuadunguyenlieu);
                            return;
                        }
                    }
                    GameService.gI().doCreatephiphong_Array(2, this.itemPP, this.listItem);
                }
                break;
            case 38:
                ite38 = this.listItem[this.FocusPhiPhong];
                if (ite38 != null) {
                    int p30 = getpostPhiPhong(vecMaterial, ite38);
                    if (p30 != -1) {
                        vecMaterial.removeElement(ite38);
                        this.listNum[p30] = 0;
                        this.listNum[p30] = 0;
                        for (int j = 0; j < this.listItem.length; j++) {
                            if (this.listItem[j] != null && (this.listItem[j]).ID == ite38.ID) {
                                this.listItem[j] = null;
                                break;
                            }
                        }
                    }
                }
                closeText();
                break;
            case 39:
                indexitem = 0;
                if (selected >= 0) {
                    Item itempet = (Item) vecPetEat.elementAt(selected);
                    if (itempet != null) indexitem = itempet.ID;
                    GameService.gI().doPetEat((short) indexitem, (byte) 1);
                }
                break;
            case 40:
                doGiveSkillToQuickSlot(selected);
                break;
            case 42:
                dt42 = (SetInfoData) p;
                if (dt42.isSkill) {
                    MainChar.CheckQuicSlotSkill(dt42.index);
                    MainChar.mQuickslot[0].setIsSkill(dt42.index, dt42.iss);
                } else {
                    MainChar.CheckQuicSlotPotion((short) dt42.typePotion);
                    MainChar.mQuickslot[0].setIsPotion((short) dt42.idIcon);
                }
                Rms.saveQuickSlot();
                break;
            case 43:
                dt43 = (SetInfoData) p;
                if (dt43.isSkill) {
                    MainChar.CheckQuicSlotSkill(dt43.index);
                    MainChar.mQuickslot[1].setIsSkill(dt43.index, dt43.iss);
                } else {
                    MainChar.CheckQuicSlotPotion((short) dt43.typePotion);
                    MainChar.mQuickslot[1].setIsPotion((short) dt43.idIcon);
                }
                Rms.saveQuickSlot();
                break;
            case 44:
                dt44 = (SetInfoData) p;
                if (dt44.isSkill) {
                    MainChar.CheckQuicSlotSkill(dt44.index);
                    MainChar.mQuickslot[2].setIsSkill(dt44.index, dt44.iss);
                } else {
                    MainChar.CheckQuicSlotPotion((short) dt44.typePotion);
                    MainChar.mQuickslot[2].setIsPotion((short) dt44.idIcon);
                }
                Rms.saveQuickSlot();
                break;
            case 45:
                dt45 = (SetInfoData) p;
                if (dt45.isSkill) {
                    MainChar.CheckQuicSlotSkill(dt45.index);
                    MainChar.mQuickslot[3].setIsSkill(dt45.index, dt45.iss);
                } else {
                    MainChar.CheckQuicSlotPotion((short) dt45.typePotion);
                    MainChar.mQuickslot[3].setIsPotion((short) dt45.idIcon);
                }
                Rms.saveQuickSlot();
                break;
            case 46:
                dt46 = (SetInfoData) p;
                if (dt46.isSkill) {
                    MainChar.CheckQuicSlotSkill(dt46.index);
                    MainChar.mQuickslot[4].setIsSkill(dt46.index, dt46.iss);
                } else {
                    MainChar.CheckQuicSlotPotion((short) dt46.typePotion);
                    MainChar.mQuickslot[4].setIsPotion((short) dt46.idIcon);
                }
                Rms.saveQuickSlot();
                break;
            case 47:
                doSelectedSkill();
                break;
            case 48:
                closeText();
                this.indexWearing = 0;
                this.vHanhTrang.removeAllElements();
                this.vHanhTrang.addElement(cmdTB2);
                SortCmdItem();
                break;
            case 49:
                closeText();
                this.indexWearing = 15;
                this.vHanhTrang.removeAllElements();
                this.vHanhTrang.addElement(cmdTB1);
                SortCmdItem();
                break;
            case 62:
                GameCanvas.gameScr.gameService.kickOutParty(((PartyInfo) Char.party.elementAt(selected)).id, (byte) 0);
                break;
            case 63:
                GameCanvas.gameScr.gameService.kickOutParty(0, (byte) 1);
                break;
            case 64:
                GameCanvas.gameScr.gameService.kickOutParty(GameScr.mainChar.ID, (byte) 2);
                break;
            case 71:
                if (this.indexMainTab == 0) {
                    int aa71 = 0;
                    mVector mMenu = new mVector();
                    for (int j = 0; j < this.mShop.size(); j++) {
                        int ii = aa71;
                        Item item = (Item) this.mShop.elementAt(j);
                        SetInfoData dt71 = new SetInfoData();
                        dt71.itemInven1 = item;
                        dt71.xx = ii;
                        mMenu.addElement(new mCommand("", this, -2, dt71, dt71));
                        aa71++;
                    }
                    setInfo(mMenu, "perform 71");
                } else {
                    doAction2();
                }
                if (this.isSkill) showInfoSkill("perform 71");
                break;
            case 92:
                dt92 = (SetInfoData) p;
                GameCanvas.startYesNoDlg("Vật phẩm sẽ mất khi bạn bỏ ra đất. Bạn có muốn tiếp tục không?", new mCommand("", this, 93, dt92));
                break;
            case 102:
                item102 = (Item) Char.inventory.elementAt(selected);
                if (item102 != null) {
                    if (!this.isTextmua) {
                        String str1 = String.valueOf(T.doyouwant) + " " + T.sell + " " + item102.name + " " + T.withprice + " " + item102.priceShop + " " + Item.moneyType[item102.priceType];
                        this.textHoimua = str1;
                        this.vHanhTrang.removeAllElements();
                        cmdban.caption = T.yes;
                        this.vHanhTrang.addElement(cmdban);
                        showTextmua();
                        SortCmdItem();
                        break;
                    }
                    GameService.gI().sellItem((byte) 0, item102.ID);
                    resetPopup();
                }
                break;
            case 103:
                if (p != null) {
                    SetInfoData dt103 = (SetInfoData) p;
                    GameService.gI().sellItem((byte) 0, dt103.itemInven1.ID);
                    this.list.removeElementAt(dt103.index);
                }
                GameCanvas.endDlg();
                break;
            case 106:
                menuItems = new mVector();
                for (i = 0; i < 2; i++) {
                    int ii = i;
                    String a = String.valueOf(ii) + ":" + (String) p;
                    menuItems.addElement(new mCommand("Phím số " + (7 + 2 * i), this, 107, a));
                }
                GameCanvas.menu.startAt(menuItems, 2);
                break;
            case 107:
                inFo = (String) p;
                array = mSystem.getStringArray(inFo);
                Rms.saveQuickSlot();
                break;
            case 108:
                i108 = (Item) Char.inventory.elementAt(selected);
                if (i108 != null) GameService.gI().sellItem((byte) 1, i108.ID);
                break;
            case 109:
                GameCanvas.endDlg();
                break;
            case 110:
                c = (Char) GameCanvas.gameScr.findCharByID(GameCanvas.gameScr.saveIDViewInfoAnimal);
                if (c != null) {
                    GameCanvas.gameScr.gameService.requestSomeOneInfo(c.ID, (byte) 1);
                } else {
                    this.right.performAction();
                }
                GameCanvas.endDlg();
                break;
            case 113:
                str = GameCanvas.inputDlg.tfInput.getText();
                if (str.equals("")) {
                    GameCanvas.endDlg();
                    return;
                }
                try {
                    int value = Integer.parseInt(str);
                    if (value > Char.Diemtiemnang) {
                        GameCanvas.startOKDlg("Bạn chỉ còn " + Char.Diemtiemnang + " điểm tiềm năng");
                        return;
                    }
                    GameService.gI().SkillPoint(1, selected, value);
                    GameCanvas.endDlg();
                } catch (Exception e) {
                    GameCanvas.startOKDlg("Có lỗi xảy ra. Vui lòng chỉ nhập số.");
                }
                break;
            case 126:
                if (!MainUnity.isLowGraphics) {
                    MainUnity.isLowGraphics = true;
                } else if (MainUnity.isLowGraphics) {
                    MainUnity.isLowGraphics = false;
                }
                GameCanvas.endDlg();
                break;
            case 334:
                if (Char.Skill_Point > 0) {
                    SkillTemplate skill = (SkillTemplate) GameScr.vec_skill.elementAt(selected);
                    if (skill != null) {
                        if (!this.isTextmua) {
                            String ttt = String.valueOf(T.hoicong) + " " + T.point + " " + T.vao + " " + T.kinang + " " + skill.name + " " + T.khong + " ?";
                            this.textHoimua = ttt;
                            showTextmua();
                            cmdCongDiem.caption = T.yes;
                            this.vHanhTrang.removeElement(cmdGangphim);
                            SortCmdItem();
                            break;
                        }
                        GameService.gI().SkillPoint(0, selected, 1);
                        resetPopup();
                    }
                    break;
                }
                GameCanvas.addNotify(T.khongthecong, (byte) 0);
                break;
            case 335:
                GameService.gI().SkillPoint(0, selected, 1);
                GameCanvas.endDlg();
                break;
            case 336:
                point = 0;
                try {
                    point = Integer.parseInt(GameCanvas.inputDlg.tfInput.getText());
                } catch (Exception e) {
                    point = 0;
                }
                s336 = (SetInfoData) p;
                if (point <= 0 || point > Char.Skill_Point) {
                    GameCanvas.addNotify(T.khongthecong, (byte) 0);
                    GameCanvas.endDlg();
                    return;
                }
                if (s336 != null && point > 0) {
                    Char.Skill_Point = (short) (Char.Skill_Point - point);
                    GameService.gI().SkillPoint(0, s336.index, point);
                }
                GameCanvas.endDlg();
                break;
        }
    }

    private void doAction2() {
        if (!isChangeSubTab && selected > -1) if (index[0] == 22 || index[0] == 23) {
            doPutItemShop();
        } else {
            doSelectedInventori();
        }
    }

    private void doClose() {
        mSound.playSound(26, mSound.volumeSound);
        selected = 0;
        this.miniItem = 0;
        this.itemPP = null;
        this.indexPet = 0;
        isBeginQuest = false;
        isInven = false;
        isDapdo = false;
        isHopda = false;
        isCheDo = false;
        isChuyenHoa = false;
        isPhiPhong = false;
        this.listNum = null;
        this.listNum = new int[6];
        vecMaterial.removeAllElements();
        vecItemCreate.removeAllElements();
        vecItemDapDo.removeAllElements();
        isShow = false;
        idicon = -1;
        if (this.isShowFill) {
            this.isShowFill = false;
            selected = 0;
            closeText();
            return;
        }
        this.isMiniShop = false;
        this.listTemp.removeAllElements();
        this.listEp = null;
        doBuyItem();
        this.isShowFill = false;
        this.focusTab = 0;
        if (this.lastSCR == GameCanvas.gameScr) {
            GameCanvas.gameScr.isStartAutoAttack = this.isRestartAutoFight;
            this.isRestartAutoFight = false;
        }
        this.lastSCR.switchToMe();
        this.list.removeAllElements();
        this.isSell = false;
        this.cmrItem.clear();
        this.cmrSubTab.clear();
        this.cmrShowText.clear();
        this.indexShowInfo = 0;
        this.cmrShowInfoMainChar.clear();
        isChangeSubTab = false;
        isFocusDetailMenu = false;
        this.isLoad = false;
        this.questInfo = null;
        this.isSkill = false;
        closeText();
        selected = -1;
        this.isShowInFoChar = false;
        this.indexMainTab = 0;
        currnentTabDetail = new String[]{""};
        this.questInfo = null;
        this.infochar.removeAllElements();
        charWearing = null;
        this.isSell = false;
        this.indexMainTab = 0;
        this.indexShowInfo = 0;
        this.indexSubTab = 0;
    }

    public void questClan(String text1, byte finishQ) {
        this.questInfo = new mVector();
        String[] title = FontTeam.normalFont[0].splitFontBStrInLine(text1, 100);
        for (int i = 0; i < title.length; i++)
            this.questInfo.addElement(title[i]);
        GameCanvas.endDlg();
    }

    public String getInfoQuestPaint(int type) {
        String info = "";
        if (type == 4) try {
            Quest q = null;
            if (GameScr.allQuestCanReceive != null && indexTypeQuest == 0 && indexQuest < GameScr.allQuestCanReceive.size()) {
                q = (Quest) GameScr.allQuestCanReceive.elementAt(indexQuest);
            } else if (GameScr.allQuestWorking != null && indexTypeQuest == 1 && indexQuest < GameScr.allQuestWorking.size()) {
                q = (Quest) GameScr.allQuestWorking.elementAt(indexQuest);
            } else if (GameScr.allQuestFinish != null && indexTypeQuest == 2 && indexQuest < GameScr.allQuestFinish.size()) {
                q = (Quest) GameScr.allQuestFinish.elementAt(indexQuest);
            }
            if (q != null) {
                info = q.getInfoPaintScr();
                info = info.replace('Â', 'â');
                info = info.replace('Ô', 'ô');
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }

    private void setQuestInfo() {
        this.questInfo = new mVector();
        String infoMain = getInfoQuestPaint(0);
        String infoSub = getInfoQuestPaint(1);
        String infoClan = getInfoQuestPaint(2);
        if (!infoMain.equals("")) {
            String[] data = Util.split(infoMain, "|");
            for (int i = 0; i < data.length; i++)
                this.questInfo.addElement(data[i]);
        }
        if (!infoSub.equals("")) {
            String[] data = Util.split(infoSub, "|");
            for (int i = 0; i < data.length; i++)
                this.questInfo.addElement(data[i]);
        }
        if (!infoClan.equals("")) {
            String[] data = Util.split(infoClan, "|");
            for (int i = 0; i < data.length; i++)
                this.questInfo.addElement(data[i]);
        }
    }

    public void setShowTextItemCharWearing() {
    }

    public static mVector setGroupOption(mVector infoTextShow) {
        mVector result = new mVector();
        mHashtable temp = new mHashtable();
        mVector vidcolor = new mVector();
        int i;
        for (i = 0; i < infoTextShow.size(); i++) {
            InfoTextShow info = (InfoTextShow) infoTextShow.elementAt(i);
            mVector v = (mVector) temp.get((new StringBuilder(String.valueOf(info.color))).toString());
            if (v == null) {
                v = new mVector();
                temp.put((new StringBuilder(String.valueOf(info.color))).toString(), v);
                vidcolor.addElement((new StringBuilder(String.valueOf(info.color))).toString());
            }
            v.addElement(info);
        }
        while (vidcolor.size() > 0) {
            i = 0;
            int idcomin = 100;
            int color = 100;
            while (i < vidcolor.size()) {
                int crId = Integer.parseInt((String) vidcolor.elementAt(i));
                if (color > crId) {
                    color = crId;
                    idcomin = i;
                }
                i++;
            }
            if (idcomin != 100) {
                mVector v = (mVector) temp.get(vidcolor.elementAt(idcomin));
                if (v != null) for (int j = 0; j < v.size(); j++)
                    result.addElement(v.elementAt(j));
                vidcolor.removeElementAt(idcomin);
            }
        }
        return result;
    }

    public static mVector setGroupFull(mVector infoTextShow) {
        mVector result = new mVector();
        result.addElement(infoTextShow.elementAt(0));
        mHashtable temp = new mHashtable();
        mVector vidcolor = new mVector();
        int i;
        for (i = 1; i < infoTextShow.size(); i++) {
            InfoTextShow info = (InfoTextShow) infoTextShow.elementAt(i);
            mVector v = (mVector) temp.get((new StringBuilder(String.valueOf(info.color))).toString());
            if (v == null) {
                v = new mVector();
                temp.put((new StringBuilder(String.valueOf(info.color))).toString(), v);
                vidcolor.addElement((new StringBuilder(String.valueOf(info.color))).toString());
            }
            v.addElement(info);
        }
        while (vidcolor.size() > 0) {
            i = 0;
            int idcomin = 100;
            int color = 100;
            while (i < vidcolor.size()) {
                int crId = Integer.parseInt((String) vidcolor.elementAt(i));
                if (color > crId) {
                    color = crId;
                    idcomin = i;
                }
                i++;
            }
            if (idcomin != 100) {
                mVector v = (mVector) temp.get(vidcolor.elementAt(idcomin));
                if (v != null) for (int j = 0; j < v.size(); j++)
                    result.addElement(v.elementAt(j));
                vidcolor.removeElementAt(idcomin);
            }
        }
        return result;
    }

    private void setShowText(mVector text, int x0, int y0, String[] arr, boolean isTile, boolean isSetGroup) {
        if (text == null && arr == null) return;
        if (!this.isQuest) this.numItem = 0;
        this.numItemStart = 0;
        this.laststar = 0;
        this.speedStart = 0;
        this.runStart = false;
        this.isHalfstart = false;
        this.numItemStart2 = 0;
        this.laststar2 = 0;
        this.speedStart2 = 0;
        this.runStart2 = false;
        this.isHalfstart2 = false;
        closeText();
        this.isShowText = true;
        if (!GameCanvas.isTouch) {
            this.xShowText = x0 + this.size / 2;
            this.yShowText = y0 + this.size / 2;
        } else {
            this.yShowText = this.y + this.sizeH;
            this.hShowText = this.h - this.sizeH;
        }
        if (!GameCanvas.isTouch) {
            this.wShowText = 6 * this.size;
            if (this.indexMainTab == 3) {
                if (this.isCharWearing || this.isAnimal) {
                    this.wShowText = 3 * this.size;
                } else if (this.isSkill) {
                    this.wShowText = 4 * this.size;
                }
            } else if (this.indexMainTab == 2 || this.indexMainTab == 8 || this.indexMainTab == 9 || this.indexMainTab == 10 || this.indexMainTab == 11) {
                this.wShowText = 3 * this.size;
            }
            this.wShowText += 8;
        }
        this.totalInfoshow = 0;
        if (arr == null) {
            String[] data = null;
            int w0 = 0;
            if (isSetGroup) {
                this.showText = setGroupFull(text);
            } else {
                this.showText = text;
            }
            try {
                for (int i = 0; i < this.showText.size(); i++) {
                    InfoTextShow info = (InfoTextShow) text.elementAt(i);
                    if (info != null && info.info != null) {
                        if (info.info[0] != null) if (isTile && i == 0) {
                            data = mFont.tahoma_7b_black.splitFontArray(info.info[0], this.wShowText - 20);
                            info.setInfo(data, Item.getColorPaintName(info.color));
                        } else {
                            data = mFont.tahoma_7_white.splitFontArray(info.info[0], this.wShowText - 20);
                            info.setInfo(data, Item.getColorPaintOption(info.color));
                        }
                        this.totalInfoshow = (byte) (this.totalInfoshow + info.info.length);
                        int ww = info.getMaxWidth();
                        w0 = (w0 > ww) ? w0 : ww;
                    } else {
                        this.totalInfoshow = (byte) (this.totalInfoshow + 1);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!GameCanvas.isTouch) this.wShowText = w0 + 20;
        }
        boolean isitemPhiPhong = false;
        if (!GameCanvas.isTouch) {
            this.hShowText = this.totalInfoshow * this.disString + 6;
            if (this.hShowText > 140) this.hShowText = 140;
            if (this.xShowText + this.wShowText > GameCanvas.w) {
                int ww = this.xShowText + this.wShowText - GameCanvas.w;
                this.xShowText -= ww + 4;
            }
            if (this.xShowText < 4) this.xShowText = 4;
            if (this.yShowText + this.hShowText > GameCanvas.h - hTab) {
                int hh = this.yShowText + this.hShowText - GameCanvas.h - hTab;
                this.yShowText -= hh;
            }
            if (this.yShowText < 4) this.yShowText = 4;
            if (this.indexMainTab == 3 && (this.isCharWearing || this.isAnimal)) {
                this.yShowText = this.yInfoWearing + 2;
                this.xShowText = this.xInfoWearing;
                this.hShowText = this.hInfoWearing - 4 + (this.isAnimal ? this.size : 0);
            }
            this.hShowText += 20;
            if (this.hShowText < this.totalInfoshow * this.disString + 16) {
                this.isUseCmr = true;
                this.isShowFill = true;
            }
        }
        if (this.indexMainTab == 0) {
            Item item = (Item) this.mShop.elementAt(selected);
            if (item != null) {
                this.numItemStart = item.plus;
                if (item.getTypeItem() == 55) isitemPhiPhong = true;
            }
        }
        if (this.indexMainTab == 8) {
            Item item = (Item) vecItemDapDo.elementAt(this.slDapdo1);
            if (item != null) {
                this.numItemStart = item.plus;
                if (item.getTypeItem() == 55) isitemPhiPhong = true;
            }
        } else if (this.indexMainTab == 1) {
            Item item = (Item) Char.inventory.elementAt(selected);
            if (item != null) {
                this.numItemStart = item.plus;
                if (item.getTypeItem() == 55) isitemPhiPhong = true;
            }
        } else if (this.indexMainTab == 2) {
            if (selected > -1 && selected < charWearing.equip.length) {
                if (charWearing.equip[selected + this.indexWearing] != null)
                    this.numItemStart = (charWearing.equip[selected + this.indexWearing]).plus;
                if (charWearing.equip[selected + this.indexWearing] != null && charWearing.equip[selected + this.indexWearing].getTypeItem() == 55)
                    isitemPhiPhong = true;
            }
        } else if (this.indexMainTab == 10) {
            Item item = (Item) vecItemCreate.elementAt(this.slDapdo1);
            if (item != null) this.numItemStart = item.plus;
            if (this.tickChuyenhoa == 0 && itemChuyenHoa0 != null) this.numItemStart = itemChuyenHoa0.plus;
            if (this.tickChuyenhoa == 1 && itemChuyenHoa1 != null) this.numItemStart = itemChuyenHoa1.plus;
            if (this.tickChuyenhoa == 2 && itemChuyenHoa2 != null) this.numItemStart = itemChuyenHoa2.plus;
        } else if (this.indexMainTab == 11) {
            Item sItem = (Item) vecItemCreate.elementAt(this.slDapdo1);
            if (sItem != null) {
                this.numItemStart = sItem.plus;
                if (sItem.getTypeItem() == 55) isitemPhiPhong = true;
            }
            if (this.isTouchCenter) {
                if (this.itemPP != null) {
                    this.numItemStart = this.itemPP.plus;
                    if (this.itemPP.getTypeItem() == 55) isitemPhiPhong = true;
                }
                this.isTouchCenter = false;
            }
        }
        if (isitemPhiPhong) {
            if (this.numItemStart > 16) {
                this.numItemStart2 = this.numItemStart - 16;
                this.numItemStart = 16;
            }
            this.laststar2 = this.numItemStart2;
            if (this.numItemStart2 % 2 != 0) this.isHalfstart2 = true;
            this.numItemStart2 /= 2;
            if (this.laststar2 == 1) this.numItemStart2 = this.laststar2;
        }
        if (this.numItemStart >= 15 && !isitemPhiPhong) this.numItemStart = 16;
        this.laststar = this.numItemStart;
        if (this.numItemStart % 2 != 0) this.isHalfstart = true;
        this.numItemStart /= 2;
        if (this.laststar == 1) this.numItemStart = this.laststar;
    }

    public void closeText() {
        this.cmrShowText.clear();
        this.isShowText = false;
        this.isShowFill = false;
        if (!GameCanvas.isTouch) {
            this.wShowText = this.hShowText = 0;
            this.xShowText = this.xbg;
        }
        this.isUseCmr = false;
    }

    public void closeText_invent() {
        if (this.indexMainTab == 1) {
            this.cmrShowText.clear();
            this.isShowText = false;
            if (!GameCanvas.isTouch) {
                this.wShowText = this.hShowText = 0;
                this.xShowText = this.xbg;
            }
            this.isUseCmr = false;
        }
    }

    public void setImageCharWear() {
    }

    private void doSelectedSkill() {
        if (!GameCanvas.isTouch) {
            SkillTemplate sk = (SkillTemplate) GameScr.vec_skill.elementAt(selected);
            if (sk != null && Char.levelSkill[selected] >= 0) {
                mVector list1 = new mVector();
                if (Char.Skill_Point > 0) list1.addElement(cmdCongDiem);
                if (sk.type != SkillTemplate.TYPE_PASSIVE) list1.addElement(cmdGangphim);
                GameCanvas.menu.startAt(list1, 0);
            }
        }
    }

    private void dofireBasePoint() {
        if (Char.Diemtiemnang == 0) {
            GameCanvas.addNotify(T.khongthecong, (byte) 0);
            GameCanvas.endDlg();
            return;
        }
        GameCanvas.inputDlg.setInfo("Nhập số", new mCommand("", this, 113), 1, 10, true);
        GameCanvas.inputDlg.show();
    }

    public void movecmrQuest() {
        if (ListQuest != null && indexTypeQuest >= 0 && indexTypeQuest < ListQuest.length) {
            int mindex = -1;
            for (int i = 0; i < ListQuest[indexTypeQuest].size(); i++) {
                QuestInfo q = (QuestInfo) ListQuest[indexTypeQuest].elementAt(i);
                if (q != null && q.status != 0) {
                    indexQuest = i;
                    selected = i;
                    this.isMoveQuest = true;
                    mindex = i;
                    break;
                }
            }
            if (mindex == -1) {
                indexQuest = 0;
                selected = 0;
                this.isMoveQuest = true;
            }
            if (GameCanvas.isTouch) showinfoQuest();
        }
    }

    public void showinfoQuest() {
        if (ListQuest != null && indexTypeQuest > 0 && indexTypeQuest < ListQuest.length) {
            mVector currQuest = ListQuest[indexTypeQuest];
            QuestInfo q = (QuestInfo) currQuest.elementAt(selected);
            mVector minfo = new mVector();
            if (q != null) {
                InfoTextShow in = new InfoTextShow(new String[]{q.name}, 0);
                minfo.addElement(in);
                String[] data = Util.split(q.info, "\n");
                try {
                    for (int i = 0; i < data.length; i++) {
                        byte color = (byte) (data[i].charAt(0) - 48);
                        if (!isDegit(data[i].charAt(0))) {
                            color = 0;
                        } else {
                            data[i] = data[i].substring(1);
                        }
                        if (data[i].length() > 5) {
                            in = new InfoTextShow(new String[]{data[i]}, color);
                            minfo.addElement(in);
                        }
                    }
                } catch (Exception exception) {
                }
            }
            setShowText(minfo, this.xShowText, this.yShowText, null, true, false);
        }
    }

    public void movecmrQuest1() {
        if (ListQuest != null && indexTypeQuest >= 0 && indexTypeQuest < ListQuest.length) {
            int mindex = -1;
            for (int j = 0; j < ListQuest.length; j++) {
                for (int i = 0; i < ListQuest[j].size(); i++) {
                    QuestInfo q = (QuestInfo) ListQuest[j].elementAt(i);
                    if (q != null && q.status != 0) {
                        indexQuest = i;
                        selected = i;
                        this.isMoveQuest = true;
                        indexTypeQuest = j;
                        mindex = i;
                        return;
                    }
                }
            }
            if (mindex == -1) {
                indexQuest = 0;
                selected = 0;
                this.isMoveQuest = true;
            }
            if (GameCanvas.isTouch) showinfoQuest();
        }
    }

    public void doSelectMainTabNotTouch() {
        if (this.indexMainTab == 3) {
            this.isCharWearing = false;
            this.isHanhTrang = false;
            this.indexShowInfo = 0;
            setTile();
            this.isSkill = true;
            setPosWearing(true);
            isFocusDetailMenu = false;
            if (!GameCanvas.isTouch) this.left = new mCommand(T.view, this, -1);
            this.cmdShowText = new mCommand("", this, -1);
            this.timeAuToShowText = 15;
            this.beGinShowText = false;
            this.isShowFill = false;
            charWearing = (Char) GameScr.mainChar;
            setImageCharWear();
            selected = -1;
        } else if (this.indexMainTab == 4) {
            this.isSkill = false;
            this.numItem = 4;
            this.isSell = false;
            setTile();
            setQuestInfo();
            this.questInfo.removeAllElements();
            if (!GameCanvas.isTouch) this.left = new mCommand(T.info, this, -3);
            this.center = new mCommand(GameCanvas.isTouch ? "" : T.tuychon, this, 0, xCCmd, yCCmd);
            setImageCharWear();
            movecmrQuest();
        }
    }

    private void doCmdCenter() {
        mVector menu, menuItem;
        Cout.println(String.valueOf(selected) + " doCmdCenter " + this.indexMainTab);
        boolean isResetSelect = false;
        if (!isFocusDetailMenu) return;
        switch (this.indexMainTab) {
            case 5:
                if (selected >= 0 && selected < this.inFoNap.size()) {
                    mCommand cmd = (mCommand) this.inFoNap.elementAt(selected);
                    if (cmd != null) cmd.performAction();
                }
                break;
            case 3:
                this.isCharWearing = false;
                this.isHanhTrang = false;
                this.indexShowInfo = 0;
                if (this.isQuest) cmdMapScr.performAction();
                if (isPaintSub()) {
                    if (this.isSkill) {
                        doSelectedSkill();
                        break;
                    }
                    if (this.isFeatures) dofireBasePoint();
                    break;
                }
                setTile();
                switch (selected) {
                    case 0:
                        this.isSkill = true;
                        setPosWearing(true);
                        this.left = new mCommand("", this, -1);
                        this.cmdShowText = new mCommand("", this, -1);
                        this.timeAuToShowText = 15;
                        this.beGinShowText = false;
                        charWearing = (Char) GameScr.mainChar;
                        setImageCharWear();
                        selected = 0;
                        if (GameCanvas.isTouch) {
                            selected = -1;
                            this.center.resetCmd();
                            dosetIDCmdTouch("", 0);
                        }
                        break;
                    case 2:
                        this.isFeatures = true;
                        selected = 0;
                        setImageCharWear();
                        if (GameCanvas.isTouch) {
                            selected = -1;
                            this.center.resetCmd();
                            dosetIDCmdTouch("", 0);
                        }
                        break;
                    case 1:
                        this.numItem = 4;
                        this.isQuest = true;
                        this.isSell = false;
                        setQuestInfo();
                        this.questInfo.removeAllElements();
                        if (!GameCanvas.isTouch) this.left = new mCommand(T.info, this, -3);
                        this.center = new mCommand(GameCanvas.isTouch ? "" : T.tuychon, this, 0, xCCmd, yCCmd);
                        setImageCharWear();
                        movecmrQuest();
                        break;
                }
                break;
            case 7:
                switch (selected) {

                }
                break;
            case 6:
                if (bangHoi.length == 2) {
                    switch (selected) {

                    }
                    break;
                }
                switch (selected) {

                }
                break;
            case 2:
            case 8:
            case 9:
            case 10:
            case 11:
                Cout.println(" doCmdCenter trangbi");
                this.isSell = false;
                setPosWearing(false);
                setTextCharInfo();
                this.numItemStart = 0;
                this.isShowInFoChar = true;
                menu = new mVector();
                menuItem = menu;
                if (menu.size() == 1) {
                    this.left = (mCommand) menu.elementAt(0);
                } else if (menu.size() == 2) {
                    this.left = new mCommand("Chọn", this, 9, menuItem, xLCmd, yLCmd);
                }
                this.center = new mCommand("", this, -3, xCCmd, yCCmd);
                charWearing = (Char) GameScr.mainChar;
                setImageCharWear();
                break;
        }
        if (!this.isSkill && !this.isFeatures && this.indexMainTab != 5 && !this.isSkillClan && !isResetSelect) {
            selected = 0;
            if (this.isHanhTrang) selected = -1;
        }
    }

    public void RestItemHanhTrang() {
        setSelectTab("RestItemHanhTrang");
        GameCanvas.endDlg();
    }

    public void SortCmdItem() {
        for (int j = 0; j < this.vHanhTrang.size(); j++) {
            mCommand cmd = (mCommand) this.vHanhTrang.elementAt(j);
            if (cmd != null) {
                cmd.x = this.xShowText + this.wShowText - 81 - j * 81 + 2;
                cmd.y = this.y + this.hShowText + 36;
            }
        }
    }

    private void paintListQuest(mGraphics g, int x, int y, int ww, boolean isClan) {
        if (ListQuest != null && indexTypeQuest >= 0 && indexTypeQuest < ListQuest.length) {
            y += this.size + 4;
            mVector currQuest = ListQuest[indexTypeQuest];
            this.totalLineQuest = currQuest.size();
            this.cmrItem.setStyle(this.totalLineQuest + 1, this.sizeH, x + 3, y + 10, this.colum * this.size - 3, this.h - this.sizeH - this.size - 20, true, 1);
            g.ClipRec(x + 3 + 1, y + 10 + 1, this.colum * this.size - 3 - 2, this.h - this.sizeH - this.size - 20 - 2);
            this.cmrItem.setClip(g);
            for (int i = 0; i < currQuest.size(); i++) {
                QuestInfo q = (QuestInfo) currQuest.elementAt(i);
                if (i == indexQuest)
                    Res.paintFocus(g, x + 8, y + 5 + 8 + i * this.sizeH + 1, this.size * this.colum - 8);
                mFont f = mFont.tahoma_7b_white;
                if (q.status == 0) f = mFont.tahoma_7b_brown;
                String[] n = f.splitFontArray(q.name, this.size * this.colum - 10);
                int ys = 0;
                if (n.length > 1) ys = -10;
                for (int j = 0; j < n.length; j++)
                    f.drawString(g, n[j], x + this.size * this.colum / 2, y + 12 + i * this.sizeH + 15 + j * 20 + ys, 2, true);
                g.setColor(-3377408);
                if (i < currQuest.size() - 1)
                    g.fillRect(x + 8, y + 5 + (i + 1) * this.sizeH + 8, this.size * this.colum - 8, 1, true);
                if ((q.status == 1 || q.status == 2 || q.status == 4) && GameCanvas.gameTick / 4 % 4 == 0) {
                    String[] nr = mFont.tahoma_7b_red.splitFontArray(q.name, this.size * this.colum - 10);
                    int ysr = 0;
                    if (nr.length > 1) ysr = -10;
                    for (int k = 0; k < n.length; k++)
                        mFont.tahoma_7b_orange.drawString(g, nr[k], x + this.size * this.colum / 2, y + 12 + i * this.sizeH + 15 + k * 20 + ysr, 2, true);
                }
            }
            mGraphics.resetTransAndroid(g);
            g.restoreCanvas();
            GameCanvas.resetTrans(g);
            paintBgSub(g, this.cmrItem.xPos - 4, this.cmrItem.yPos - 10, this.cmrItem.width + 10, this.cmrItem.height + 10, false);
        }
    }

    public void showInfoSkill(String pos) {
        int xShowTextt = 0, yShowTextt = 0;
        this.indexPaintLineSkill = -1;
        mVector minfo = new mVector();
        try {
            if (selected >= 0 && selected < GameScr.vec_skill.size()) {
                SkillTemplate sk = (SkillTemplate) GameScr.vec_skill.elementAt(selected);
                int lvCharhientai = Char.levelSkill[selected];
                minfo.addElement(new InfoTextShow(new String[]{String.valueOf(sk.name) + ((lvCharhientai > 0) ? (" lv " + lvCharhientai) : "")}, 4));
                if (sk.decription != null && !sk.decription.equals(""))
                    minfo.addElement(new InfoTextShow(new String[]{sk.decription}, 0));
                minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.type) + ": " + T.typeSkill[sk.type]}, 0));
                this.indexPaintLineSkill = (selected > 0) ? (5 + ((sk.type == SkillTemplate.TYPE_BUFF) ? 1 : 0)) : 3;
                if (lvCharhientai > 0) {
                    if (sk.nTarget != null)
                        minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.somuctieu) + ": " + sk.nTarget[lvCharhientai - 1]}, 0));
                    if (selected > 0) {
                        minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.LvYeuCau) + ": " + sk.lvRequire[lvCharhientai - 1]}, 0));
                        if (sk.type != SkillTemplate.TYPE_PASSIVE)
                            minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.Tieuhaonoiluc) + ": " + sk.getmplose(lvCharhientai - 1)}, 0));
                    }
                    if (sk.type != SkillTemplate.TYPE_PASSIVE)
                        minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.Thoigianhoi) + ": " + Util.convertMilis2S(sk.getCoolDown(lvCharhientai - 1)) + "s"}, 0));
                    if (sk.type == SkillTemplate.TYPE_BUFF)
                        minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.Thoigianhotro) + ": " + Util.convertMilis2S(sk.timeLive[lvCharhientai - 1]) + "s"}, 0));
                }
                xShowTextt = this.xWearing[selected] - this.size / 2 + this.size;
                yShowTextt = this.yWearing[selected] + this.size;
                if (lvCharhientai < 0) {
                    minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.LvYeuCau) + ": " + sk.lvRequire[0]}, 0));
                    minfo.addElement(new InfoTextShow(new String[]{T.chuamo}, 0));
                    this.indexPaintLineSkill++;
                } else if (lvCharhientai == 0) {
                    minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.LvYeuCau) + ": " + sk.lvRequire[0]}, 0));
                    minfo.addElement(new InfoTextShow(new String[]{T.chuahoc}, 0));
                    this.indexPaintLineSkill++;
                } else {
                    mVector op = sk.getInfoOptions(lvCharhientai - 1);
                    for (int i = 0; i < op.size(); i++)
                        minfo.addElement(op.elementAt(i));
                }
                minfo.addElement(new InfoTextShow(null, 0));
                if (Char.levelSkill[selected] < 10 && selected != 0 && lvCharhientai >= 0) {
                    minfo.addElement(new InfoTextShow(new String[]{"Cấp kế: " + (lvCharhientai + 1)}, (GameScr.mainChar.level >= sk.lvRequire[lvCharhientai]) ? 4 : 6));
                    minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.LvYeuCau) + ": " + sk.lvRequire[lvCharhientai]}, 0));
                    if (sk.nTarget != null)
                        minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.somuctieu) + ": " + sk.nTarget[lvCharhientai]}, 0));
                    if (sk.type != SkillTemplate.TYPE_PASSIVE)
                        minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.Tieuhaonoiluc) + ": " + sk.getmplose(lvCharhientai)}, 0));
                    if (sk.type != SkillTemplate.TYPE_PASSIVE)
                        minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.Thoigianhoi) + ": " + Util.convertMilis2S(sk.coolDown[lvCharhientai]) + "s"}, 0));
                    if (sk.type == SkillTemplate.TYPE_BUFF)
                        minfo.addElement(new InfoTextShow(new String[]{String.valueOf(T.Thoigianhotro) + ": " + Util.convertMilis2S(sk.timeLive[lvCharhientai]) + "s"}, 0));
                    mVector info_next = sk.getInfoOptions(lvCharhientai);
                    for (int i = 0; i < info_next.size(); i++)
                        minfo.addElement(info_next.elementAt(i));
                } else {
                    this.indexPaintLineSkill = -1;
                }
                this.vHanhTrang.removeAllElements();
                if (GameCanvas.isTouch && GameCanvas.isTouch) {
                    if (Char.Skill_Point > 0 && selected >= 0 && Char.levelSkill[selected] >= 0 && Char.levelSkill[selected] < 10 && selected > 0)
                        this.vHanhTrang.addElement(cmdCongDiem);
                    if (Char.levelSkill[selected] > 0 && sk.type != SkillTemplate.TYPE_PASSIVE)
                        this.vHanhTrang.addElement(cmdGangphim);
                    SortCmdItem();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setShowText(minfo, xShowTextt, yShowTextt, null, true, false);
    }

    public boolean isScrMainMenu() {
        return true;
    }

    public void showinfonext() {
        if (selected < 0) return;
        if (Char.inventory.size() > 0) {
            int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
            int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
            Item it = (Item) Char.inventory.elementAt(selected);
            int type = it.getTypeItem();
            Item itcp = null;
            this.isgangphim = false;
            if (POS_ITEM_IN_EQUIP[type] > -1) itcp = GameScr.mainChar.equip[POS_ITEM_IN_EQUIP[type]];
            showItemInventoryInfo(it, this.isSell, x0, y0, itcp);
            if (GameCanvas.isTouch) {
                mVector m = getInventori();
                mCommand cm = (mCommand) m.elementAt(0);
                SetInfoData dt79 = (SetInfoData) cm.p;
                Item it1 = (Item) Char.inventory.elementAt(selected);
                this.vHanhTrang.removeAllElements();
                cmdsudung = new mCommand(T.sudung, this, -3, it1);
                cmdvut = new mCommand(T.vut, this, 108, it1);
                cmdban = new mCommand(T.ban, this, 102, dt79);
                this.vHanhTrang.addElement(cmdban);
                this.vHanhTrang.addElement(cmdsudung);
                this.vHanhTrang.addElement(cmdvut);
                if (it1.type != Item.TYPE_MP && it1.type != Item.TYPE_HP && it1.catagory != 4) {
                    this.vHanhTrang.addElement(new mCommand(T.bannhieu, this, 25, null));
                } else {
                    this.isgangphim = true;
                    this.vHanhTrang.addElement(cmdGangphim);
                }
                SortCmdItem();
            }
        }
    }

    public void doChangeInfo(boolean isMeTouch) {
        if (!isMeTouch) {
            if (this.isSkill) {
                showInfoSkill("doChangeInfo");
            } else if (!this.isCharWearing) {
                if (this.indexMainTab == 2) {
                    isFocusCharWearing = true;
                    setTextCharInfo();
                    showMaincharInfo();
                }
            }
        } else if (this.indexMainTab == 2) {
            isFocusCharWearing = true;
            showMaincharInfo();
        } else if (this.indexMainTab == 1) {
            if (Char.inventory.size() > 0) {
                if (selected == -1 || selected > Char.inventory.size() - 1) selected = 0;
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                Item it = (Item) Char.inventory.elementAt(selected);
                int type = it.getTypeItem();
                Item itcp = null;
                this.isgangphim = false;
                if (POS_ITEM_IN_EQUIP[type] > -1) itcp = GameScr.mainChar.equip[POS_ITEM_IN_EQUIP[type]];
                showItemInventoryInfo(it, this.isSell, x0, y0, itcp);
                if (GameCanvas.isTouch) {
                    mVector m = getInventori();
                    mCommand cm = (mCommand) m.elementAt(0);
                    SetInfoData dt79 = (SetInfoData) cm.p;
                    this.vHanhTrang.removeAllElements();
                    cmdban = new mCommand(T.ban, this, 102, dt79);
                    cmdsudung = new mCommand(T.sudung, this, -3, dt79.itemInven1);
                    cmdvut = new mCommand(T.vut, this, 108, dt79.itemInven1);
                    this.vHanhTrang.addElement(cmdban);
                    this.vHanhTrang.addElement(cmdsudung);
                    this.vHanhTrang.addElement(cmdvut);
                    if (dt79.itemType != Item.TYPE_MP && dt79.itemType != Item.TYPE_HP) {
                        this.vHanhTrang.addElement(cmdbanNhieu);
                    } else {
                        this.isgangphim = true;
                        this.vHanhTrang.addElement(cmdGangphim);
                    }
                    SortCmdItem();
                }
            }
        } else if (this.indexMainTab == 0) {
            if (this.mShop.size() > 0) {
                selected = 0;
                int x0 = this.x + this.size / 2 + selected % this.colum * this.size + 16;
                int y0 = 11 + this.y + selected / this.colum * this.size + this.size * 3 / 2 + this.size - 1;
                Item sItem = (Item) this.mShop.elementAt(selected);
                if (sItem != null) {
                    showItemInventoryInfo(sItem, this.isSell, x0, y0, null);
                    this.vHanhTrang.removeAllElements();
                    this.vHanhTrang.addElement(cmdBuy);
                    mCommand cmdD = (mCommand) this.vHanhTrang.elementAt(0);
                    if (!captionServer.equals("")) {
                        cmdD.caption = captionServer;
                    } else {
                        cmdD.caption = T.Buy;
                    }
                    this.canbuy = false;
                    SortCmdItem();
                    Cout.Debug("CMD BUY 333 ");
                }
            }
        } else if (this.isSkill) {
            selected = 0;
            showInfoSkill("doChangeInfo 1");
        }
    }

    public void addPotiontoQL() {
    }

    public void Upgraderesult(int type) {
        ID_CUONG_HOA = -1;
        if (type == 0) effDapDo = new DataSkillEff(50, xposItem, ypostItem);
        if (type == 1) effDapDo = new DataSkillEff(236, xposItem, ypostItem);
        this.numStone = 0;
        itemBaohiem = null;
        itemBua = null;
        itemStone = null;
        textPercent = "";
        xuCuongHoa = 0;
    }

    public void CreateItemresult(int type, int midicon) {
        typeresult = type;
        idicon = midicon;
        this.Waitcreate = true;
        xuCuongHoa = 0;
        for (int i = 0; i < 6; i++) {
            WeaponsLazer wp = new WeaponsLazer(Util.cos(i * 60 + this.goc) * this.r / 1024 + this.xcenter, Util.sin(i * 60 + this.goc) * this.r / 1024 + this.ycenter, this.xcenter, this.ycenter, 25, 1, this.sizeEff[i]);
            this.vEffect.addElement(wp);
        }
        EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter);
        this.vEffect.addElement(ef);
    }

    public void ChuyenHoaItemresult() {
        this.Waitcreate = true;
        WeaponsLazer wp = new WeaponsLazer(this.xcenter - 42 + 10, this.ycenter + 30, this.xcenter - 1, this.ycenter - 30 + 10, 25, 1, 3);
        this.vEffect.addElement(wp);
        if (itemChuyenHoa2 != null) {
            WeaponsLazer wp2 = new WeaponsLazer(this.xcenter + 42 - 10, this.ycenter + 30, this.xcenter + 1, this.ycenter - 30 + 10, 25, 1, 3);
            this.vEffect.addElement(wp2);
        }
        EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter - 25 + 10);
        this.vEffect.addElement(ef);
    }

    public void setStart() {
        if (mItem != null) {
            this.numItemStart = mItem.plus;
            if (this.numItemStart == 15) this.numItemStart = 16;
            this.laststar = this.numItemStart;
            if (this.numItemStart % 2 != 0) this.isHalfstart = true;
            this.numItemStart /= 2;
            if (this.laststar == 1) this.numItemStart = this.laststar;
        }
    }

    public void doSendCuongHoa(int type) {
        mVector vecItem = new mVector();
        if (mItem != null) vecItem.addElement(mItem);
        if (itemStone != null) vecItem.addElement(itemStone);
        if (itemBaohiem != null) vecItem.addElement(itemBaohiem);
        if (itemBua != null) vecItem.addElement(itemBua);
        GameService.gI().doUpgradeItem((byte) type, (byte) this.numStone, vecItem);
    }

    public boolean checkinVector(mVector vec, Item ite) {
        for (int i = 0; i < vec.size(); i++) {
            Item mit = (Item) vec.elementAt(i);
            if (mit != null && mit.idIcon == ite.idIcon) return true;
        }
        return false;
    }

    public boolean checkinVectorPhiPhong(mVector vec, Item ite) {
        for (int i = 0; i < vec.size(); i++) {
            Item mit = (Item) vec.elementAt(i);
            if (mit != null && mit.ID == ite.ID) return true;
        }
        return false;
    }

    public void CreatePhiPhongresult(int type, int midicon) {
        typeresult = type;
        idicon = midicon;
        this.Waitcreate = true;
        for (int i = 0; i < 6; i++) {
            WeaponsLazer wp = new WeaponsLazer(Util.cos(i * 60 + this.goc) * this.r / 1024 + this.xcenter, Util.sin(i * 60 + this.goc) * this.r / 1024 + this.ycenter, this.xcenter, this.ycenter, 25, 1, this.sizeEff[i]);
            this.vEffect.addElement(wp);
        }
        EffectEnd ef = new EffectEnd(0, this.xcenter, this.ycenter);
        this.vEffect.addElement(ef);
    }

    public int getpostPhiPhong(mVector vec, Item ite) {
        for (int i = 0; i < vec.size(); i++) {
            Item mit = (Item) vec.elementAt(i);
            if (mit != null && mit.ID == ite.ID) return i;
        }
        return -1;
    }

    public int getPost() {
        for (int i = 0; i < this.listItem.length; i++) {
            if (this.listItem[i] == null) return i;
        }
        return -1;
    }

    public boolean CheckList(mVector vec, Item ite) {
        for (int i = 0; i < vec.size(); i++) {
            Item mit = (Item) vec.elementAt(i);
            if (mit != null && mit.pos == ite.pos) return true;
        }
        return false;
    }

    public int getpost(mVector vec, Item ite) {
        for (int i = 0; i < vec.size(); i++) {
            Item mit = (Item) vec.elementAt(i);
            if (mit != null && mit.idIcon == ite.idIcon) return i;
        }
        return -1;
    }

    public String getPercent(int lvpercent) {
        return String.valueOf(lvpercent / 100) + "." + (lvpercent % 100) + "%";
    }

    public boolean isTabQuest() {
        if (GameCanvas.isTouch) return (this.indexMainTab == 3 && this.isQuest);
        return !((this.indexMainTab != 3 || !this.isQuest) && this.indexMainTab != 4);
    }
}

/*
 * Location: C:\NLTB\bin\!\MainMenu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version: 1.1.3
 */
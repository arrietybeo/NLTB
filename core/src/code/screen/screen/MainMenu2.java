package code.screen.screen;

import code.main.GameCanvas;
import code.model.IActionListener;
import code.model.Scroll;
import code.screen.Res;
import lib.mGraphics;

public class MainMenu2 extends ScreenTeam implements IActionListener {
   String currentTileMainTab = "";
   public static MainMenu2 me;
   public int x;
   public int y;
   public int w;
   public int h;
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
   int colum = 7;
   int xShowText;
   int yShowText;
   int wShowText;
   int hShowText;
   int countPop;
   int indexShowInfo;
   int xShowTextSkill;
   int yShowTextSkill;
   public static String[] mainTab;
   public static String[] myInfo;
   public static String[] bangHoi;
   public static String[] hoat_dong;
   public static String[] settings;
   public static String[] tabHanhTrang;
   public static String[] feaTures;
   public static String[] currnentTabDetail;
   boolean isFocusDetailMenu;
   boolean isChangeSubTab;
   Scroll cmrItem = new Scroll();
   Scroll cmrSubTab = new Scroll();
   Scroll cmrShowText = new Scroll();
   Scroll cmrShowInfoMainChar = new Scroll();
   boolean isMe;
   private boolean isHanhTrang;
   private boolean isFeatures;
   private boolean isMiniShop;
   byte maxNumW = 3;

   public static MainMenu2 gI() {
      return me == null ? (me = new MainMenu2()) : me;
   }

   public void paint(mGraphics g) {
      try {
         GameCanvas.resetTrans(g);

         int xbg;
         int www;
         for(xbg = GameCanvas.w % 32; xbg < GameCanvas.w + 32; xbg += 32) {
            for(www = GameCanvas.h % 32; www < GameCanvas.h + 32; www += 32) {
               g.drawImage(GameScr.imgBgMainMenu, GameCanvas.w - xbg, GameCanvas.h - www, mGraphics.TOP | mGraphics.LEFT, false);
            }
         }

         xbg = this.x + this.w - 7 - this.colum * this.size;
         Res.paintDlgDragonFullNew(g, this.x, this.y + this.sizeH, this.w, this.h - this.sizeH, 60, 60, GameScr.imgBk[0], false);
         if (GameCanvas.w > 200) {
            g.setColor(-9751532);
            g.drawRect(this.x, this.y + this.sizeH, xbg - 2 - this.x, this.h - this.sizeH, false);
            g.drawRect(this.x + 2, this.y + this.sizeH + 2, xbg - 2 - this.x - 4, this.h - this.sizeH - 4, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, xbg - 2, this.y + this.h, mGraphics.BOTTOM | mGraphics.RIGHT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x, this.y + this.h, mGraphics.BOTTOM | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x, this.y + this.sizeH, mGraphics.TOP | mGraphics.LEFT, false);
            g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, xbg - 2, this.y + this.sizeH, mGraphics.TOP | mGraphics.RIGHT, false);
            g.setColor(-4034289);
            g.drawRect(this.x + 1, this.y + this.sizeH + 1, xbg - 2 - this.x - 2, this.h - this.sizeH - 2, false);
         }

         GameCanvas.resetTrans(g);
         www = this.w + this.wShowText - 1;
         if (!GameCanvas.isTouch) {
            www = this.w - 1;
         }

         g.setColor(-9751532);
         g.drawRect(this.x, this.y, www, this.sizeH - 2, false);
         g.drawRect(this.x + 2, this.y + 2, www - 4, this.sizeH - 4, false);
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 7, this.x + www, this.y + this.sizeH - 2, mGraphics.BOTTOM | mGraphics.RIGHT, false);
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 1, this.x, this.y + this.sizeH - 2, mGraphics.BOTTOM | mGraphics.LEFT, false);
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 0, this.x, this.y, mGraphics.TOP | mGraphics.LEFT, false);
         g.drawRegion(GameScr.imgBoder[1], 0, 0, 8, 8, 2, this.x + www, this.y, mGraphics.TOP | mGraphics.RIGHT, false);
         g.setColor(-4034289);
         g.drawRect(this.x + 1, this.y + 1, www - 2, this.sizeH - 4, false);

         int i;
         for(i = 0; i < 7; ++i) {
            g.drawRegion(GameScr.imgBoder[5], 0, 25, 12, 25, 0, GameCanvas.hw - 42 + i * 12, this.y + this.sizeH / 2, mGraphics.VCENTER | mGraphics.LEFT, false);
         }

         g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 0, GameCanvas.hw - 44, this.y + this.sizeH / 2, mGraphics.VCENTER | mGraphics.LEFT, false);
         g.drawRegion(GameScr.imgBoder[5], 0, 0, 12, 25, 2, GameCanvas.hw + 44, this.y + this.sizeH / 2 + 1, mGraphics.VCENTER | mGraphics.RIGHT, false);
         FontTeam.fontTile.drawString(g, this.currentTileMainTab, GameCanvas.w / 2, this.y + this.sizeH / 2 - 6, 2, false);

         for(i = 0; i < mainTab.length; ++i) {
            g.drawRegion(GameScr.imgMainMenu, 0, i * (mGraphics.getImageHeight(GameScr.imgMainMenu) / 6), mGraphics.getImageWidth(GameScr.imgMainMenu), mGraphics.getImageHeight(GameScr.imgMainMenu) / 6, 0, this.x + this.size / 2 + 3, this.y + i * this.size + this.sizeH + this.size / 2 + (GameCanvas.w > 200 ? 10 : 8), mGraphics.VCENTER | mGraphics.HCENTER, false);
         }

         g.drawImage(GameScr.imgArrowMenu[this.isFocusDetailMenu ? 0 : 1], xbg - 5, this.y + this.indexMainTab * this.size + this.sizeH + this.size / 2 + 8, mGraphics.VCENTER | mGraphics.RIGHT, false);
      } catch (Exception var5) {
         var5.printStackTrace();
      }

      super.paint(g);
   }

   public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
   }

   public void perform(int idAction, Object p) {
   }

   public void switchToMe() {
      this.isMiniShop = false;
      super.switchToMe();
      this.init1();
      this.initName();
   }

   public void switchToMe(boolean isReset) {
      super.switchToMe(isReset);
      this.initName();
   }

   public void switchToMe(ScreenTeam lastSCR) {
      this.isMe = false;
      if (GameCanvas.currentScreen == this) {
         this.isMe = true;
      } else {
         this.isMiniShop = false;
         super.switchToMe(lastSCR);
         this.init1();
         this.initName();
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

   public void init1() {
      mainTab = new String[]{"Cửa hàng", "Bản thân", "Nạp xu", "Bang hội", "Hoạt động", "Cài đặt"};
      myInfo = new String[]{"H.trang", "Trang bị", "Tb thú", " Kỹ năng", "T.năng", "Nhiệm vụ"};
      bangHoi = new String[]{"Thông tin", "Tin nhắn", "Kỹ năng", " N.vụ", "Q.góp", "T.viên", "Top", "Chat", "Rời bang", "Giải tán", "Đăng ký"};
      hoat_dong = new String[]{"Cây thần", "Cổ vật", "T.c thủ", "T.đại gia", "T.server", "Sự kiện", "S.thú", "C.thành", "Q.chiến"};
      settings = new String[]{"Cấu hình", "Giao diện", "H.dẫn", "Âm thanh", "Lời mời", "Auto", "Focus"};
      tabHanhTrang = new String[]{"Túi 1", "Túi 2", " Túi 3"};
      feaTures = new String[]{"Sức mạnh ", "Khéo léo ", "Tinh thần ", "Sức khỏe ", "May mắn "};
      if (GameCanvas.w <= 200) {
         this.maxNumW = 2;
      }

      this.cmrItem.clear();
      this.cmrSubTab.clear();
      this.cmrShowText.clear();
      this.cmrShowInfoMainChar.clear();
      this.size = 26;
      this.colum = 6;
      if (!GameCanvas.isTouch) {
         this.size = 24;
         if (GameCanvas.isScreenSize200) {
            this.size = 22;
         }

         this.w = this.size * (this.colum + 2) + 2;
         this.x = GameCanvas.w / 2 - this.w / 2;
         if (this.x < 2) {
            this.x = 2;
         }
      } else {
         this.size = 26;
         this.w = this.size * (this.colum + 2);
         this.x = GameCanvas.w / 2 - this.w;
      }

      this.w += 4;
      if (this.x < 2) {
         this.x = 2;
      }

      if (GameCanvas.isTouch) {
         this.xShowText = this.x + this.w;
         this.wShowText = this.w - 4;
         if (this.xShowText + this.wShowText > GameCanvas.w - 2) {
            this.wShowText = GameCanvas.w - this.xShowText - 2;
         }
      }

      this.h = this.size * (this.colum + 2);
      if (this.h > GameCanvas.h - hTab + 2) {
         this.h = GameCanvas.h - hTab + (GameCanvas.isTouch ? 8 : 0);
      }

      this.y = GameCanvas.h / 2 - this.h / 2;
      if (!GameCanvas.isTouch) {
         if (this.y + this.h > GameCanvas.h - hTab) {
            this.y -= hTab;
         }

         if (this.y < 2) {
            this.y = 2;
         }
      } else {
         if (this.y < 24) {
            this.y = 24;
         }

         if (this.y + this.h > GameCanvas.h) {
            this.h = GameCanvas.h - this.y;
         }
      }

      this.sizeH = this.size;
      if (GameCanvas.h > 200) {
         this.sizeH = 35;
      }

      if (GameCanvas.isTouch) {
         this.yShowText = this.y + this.sizeH;
         this.hShowText = this.h - this.sizeH;
      }

      this.isHanhTrang = false;
      this.isChangeSubTab = false;
      this.isFocusDetailMenu = false;
      this.isFeatures = false;
      selected = -1;
      this.indexMainTab = 0;
      currnentTabDetail = new String[]{""};
   }

   public void setInfo(int i, boolean b, byte[] cs) {
   }
}

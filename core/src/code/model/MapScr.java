package code.model;

import code.main.GameCanvas;
import code.screen.Utils;
import code.screen.screen.GameScr;
import code.screen.screen.MainMenu;
import code.screen.screen.ScreenTeam;

import javax.microedition.lcdui.Image;

import lib.Tilemap;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.mFont;

public class MapScr extends ScreenTeam implements IActionListener {
    private static MapScr instance;
    boolean modeCurrentMap;
    public static Image imgMap = null;
    public static int mapW;
    public static int mapH;
    public static int mfx;
    public static int mfy;
    public static int mpoint;
    public static int lastmPoint;
    public static int tick3;
    public static int mcmtoX;
    public static int mcmtoY;
    public static int mcmvx;
    public static int mcmvy;
    public static int mcmdx;
    public static int mcmdy;
    public static int mcmx;
    public static int mcmy;
    public static int mcmxLim;
    public static int mcmyLim;
    public static int taskmapId;
    public static int npx;
    public static int npy;
    private static int dx = 0;
    private static int dy = 0;
    int tick;
    int tick2;
    public static int cmdH = 22;
    public static int TOP_CENTER;
    public static int TOP_LEFT;
    public static int TOP_RIGHT;
    public static int BOTTOM_HCENTER;
    public static int BOTTOM_LEFT;
    public static int BOTTOM_RIGHT;
    public static int VCENTER_HCENTER;
    public static int VCENTER_LEFT;
    public mVector infoQuest = new mVector();
    public static short[] x;
    public static short[] y;
    public static String[] mapNames;
    int maxPX;
    int maxPY;
    mCommand cmdClose;
    int xM;
    int yM;
    boolean trans = false;
    int lastX;
    int lastY;

    static {
        TOP_CENTER = mGraphics.TOP | mGraphics.HCENTER;
        TOP_LEFT = mGraphics.TOP | mGraphics.LEFT;
        TOP_RIGHT = mGraphics.TOP | mGraphics.RIGHT;
        BOTTOM_HCENTER = mGraphics.BOTTOM | mGraphics.HCENTER;
        BOTTOM_LEFT = mGraphics.BOTTOM | mGraphics.LEFT;
        BOTTOM_RIGHT = mGraphics.BOTTOM | mGraphics.RIGHT;
        VCENTER_HCENTER = mGraphics.VCENTER | mGraphics.HCENTER;
        VCENTER_LEFT = mGraphics.VCENTER | mGraphics.LEFT;
        x = new short[]{229, 229, 187, 171, 153, 153, 195, 186, 170, 168, 145, 146, 141, 54, 83, 61, 122, 100, 126, 111, 77, 101, 30, 38, 90, 45, 0, 0, 0, 161, 60, 0, 143, 209, 219, 119, 170, 23, 54, 0, 0, 233, 0, 0, 0, 76, 0, 0, 0, 102, 0, 0, 0, 0, 0, 0, 235, 194, 122, 68, 19, 1, 1, 1, 1, 1, 1, 94, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        y = new short[]{230, 206, 204, 221, 241, 50, 234, 102, 135, 189, 167, 137, 107, 61, 49, 94, 26, 15, 51, 144, 124, 200, 223, 183, 166, 23, 0, 0, 0, 84, 203, 0, 29, 37, 83, 98, 29, 67, 231, 0, 0, 43, 0, 0, 0, 154, 0, 0, 0, 122, 0, 0, 0, 0, 0, 0, 77, 22, 75, 215, 46, 1, 1, 1, 1, 1, 1, 88, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        mapNames = new String[]{"An Bình Trấn", "Trung Vạn Sơn", "Thượng Vạn Sơn", "Bến tàu An Bình", "Vũng Cầu", "Tuyết Sơn Trấn", "Hạ Vạn Sơn", "Hạ Bách Tùng", "Thác Cây Cô Đơn", "Bến tàu Cát Lở", "Bãi Nhộng", "Lại Sơn", "Hồ Vực", "Rừng Hà Bá", "Rừng Vũ Nữ", "Tĩnh Khâu", "Đông Mỹ", "Thác Băng", "Thạch Hàn", "Sa Kim", "Sa Thổ", "Hang Ma Trơi", "Vực Ngoằn Ngoèo", "Vực Biên Bức", "Ốc đảo Thanh Diệp", "Đầm Gai", "", "", "", "Thượng Bách Tùng", "Vực Song Hỏa Thụ", "", "Song Cầu", "Hắc vực", "Trúc Lâm Tự", "Vô Cực Đạo", "Thủy Liên Viện", "Ma Chu Cốc", "Hỏa Long Bang", "", "", "Trường Dã", "", "", "", "Cồn Khất Lang", "", "", "", "Cồn Khương Lang", "", "Ốc Đảo 1", "Đấu Trường", "", "", "", "Hậu sơn Trúc Lâm Tự", "Hậu sơn Thủy Liên Viện", "Hậu sơn Vô Cực Đạo", "Hậu sơn Hỏa Long Bang", "Hậu sơn Ma Chu Cốc", "Chợ", "", "", "", "Vượt ải", "Ốc Đảo", "Hắc Tuyền Lâm"};
    }

    public MapScr() {
        if (!GameCanvas.isTouch) {
            this.center = new mCommand(T.close, this, 0);
        }

    }

    protected void resetCMLim() {
        int wMap = 0;
        this.xM = (GameCanvas.w - wMap) / 2;
        this.yM = (GameCanvas.h - 20) / 2;
        if (this.xM < 0) {
            this.xM = 0;
        }

        if (this.yM < 0) {
            this.yM = 0;
        }

        if (this.modeCurrentMap) {
            mcmxLim = wMap + 20 - GameCanvas.w;
            mcmyLim = 40 - GameCanvas.h;
            this.maxPX = wMap + 20;
            this.maxPY = wMap + 40;
            if (this.maxPY < GameCanvas.h - 26) {
                this.maxPY = GameCanvas.h - 26;
            }

            if (this.maxPX < GameCanvas.w) {
                this.maxPX = GameCanvas.w;
            }

            mfx = this.xM + GameScr.mainChar.x / 12;
            mfy = this.yM + GameScr.mainChar.y / 12;
        } else {
            mcmxLim = 340 - GameCanvas.w;
            mcmyLim = 340 - GameCanvas.h;
            mfx = x[Tilemap.lv] + dx;
            mfy = y[Tilemap.lv] + dy;
            this.maxPX = 256 + dx;
            this.maxPY = 256 + dy;
        }

        this.maxPX -= 10;
        this.maxPY -= 10;
        if (mcmxLim < 0) {
            mcmxLim = 0;
        }

        if (mcmyLim < 0) {
            mcmyLim = 0;
        }

        mcmy = 0;
        mcmx = 0;
        mcmtoY = 0;
        mcmtoX = 0;
        mcmtoX = mfx - GameCanvas.hw;
        mcmtoY = mfy - GameCanvas.hh;
        npx = mfx;
        npy = mfy;
        lastmPoint = Tilemap.lv;
    }

    public static MapScr gI() {
        if (instance == null) {
            instance = new MapScr();
        }

        return instance;
    }

    public void switchToMe(ScreenTeam lastSCR) {
        this.lastSCR = lastSCR;
        if (!GameCanvas.isTouch)
            this.center = new mCommand(T.close, this, 0);
        if (imgMap == null) {
            imgMap = GameCanvas.loadImage("/m.png");
            mapW = mGraphics.getImageWidth(imgMap);
            mapH = mGraphics.getImageHeight(imgMap);
        }
        if (GameCanvas.isTouch) {
            int size = 0, colum = 0, w = 0, xx = 0, xbg = 0;
            int xShowText = 0, wShowText = 0, h = 0, yy = 0, sizeH = 0, yShowText = 0, hShowText = 0;
            size = 26;
            colum = 5;
            if (!GameCanvas.isTouch) {
                size = 26;
                if (GameCanvas.isScreenSize200)
                    size = 22;
                w = size * (colum + 1) + 10;
                xx = GameCanvas.w / 2 - w / 2;
                if (xx < 2)
                    xx = 2;
            } else {
                size = 26;
                w = size * (colum + 2);
                xx = GameCanvas.w / 2 - w;
            }
            w += 4;
            if (xx < 2)
                xx = 2;
            xbg = xx + size + 8;
            if (GameCanvas.isTouch) {
                xShowText = xx + w - 15;
                wShowText = w;
                if (xShowText + wShowText > GameCanvas.w - 2)
                    wShowText = GameCanvas.w - xShowText;
                wShowText--;
            } else {
                wShowText = 4 * size;
            }
            h = size * (colum + 3);
            if (h > GameCanvas.h - hTab + 2)
                h = GameCanvas.h - hTab + (GameCanvas.isTouch ? 8 : 0);
            yy = GameCanvas.h / 2 - h / 2;
            if (!GameCanvas.isTouch) {
                if (yy + h > GameCanvas.h - hTab)
                    yy -= hTab;
                if (yy < 2)
                    yy = 2;
            } else {
                if (yy < 24)
                    yy = 24;
                if (yy + h > GameCanvas.h)
                    h = GameCanvas.h - yy;
            }
            if (GameCanvas.isTouch)
                yy -= 30;
            sizeH = size;
            if (GameCanvas.h > 200)
                sizeH = 35;
            if (GameCanvas.isTouch) {
                yShowText = yy + sizeH;
                hShowText = h - sizeH;
            }
            this.cmdClose = new mCommand("", this, 0);
            this.cmdClose.setXY(xShowText + wShowText - 21, (yy + sizeH - 20) / 2 + 3);
            this.cmdClose.setindexImage(4);
        }
        if (GameCanvas.w > mapW)
            dx = GameCanvas.w / 2 - mapW / 2;
        if (GameCanvas.h > mapH)
            dy = GameCanvas.h / 2 - mapH / 2;
        resetCMLim();
        findMapNearestPoint();
        int lastid = -1;
        for (int i = 0; i < MainMenu.ListQuest.length; i++) {
            for (int j = 0; j < MainMenu.ListQuest[i].size(); j++) {
                QuestInfo q = (QuestInfo) MainMenu.ListQuest[i].elementAt(j);
                if (q != null && (
                        q.status == 1 || q.status == 2 || q.status == 4) &&
                        q.idMap != lastid) {
                    this.infoQuest.addElement(q);
                    lastid = q.idMap;
                }
            }
        }
        switchToMe();
    }

    public void paint(mGraphics g) {
        g.setColor(0);
        g.fillRect(0, 0, GameCanvas.w, GameCanvas.h, false);
        g.translate(10, 10);
        g.translate(-mcmx, -mcmy);
        if (GameCanvas.w > mapW && GameCanvas.h > mapH) {
            g.drawImage(imgMap, GameCanvas.hw, GameCanvas.hh, VCENTER_HCENTER, false);
        } else if (GameCanvas.w > mapW) {
            g.drawImage(imgMap, GameCanvas.hw, 0, TOP_CENTER, false);
        } else if (GameCanvas.h > mapH) {
            g.drawImage(imgMap, 0, GameCanvas.hh, VCENTER_LEFT, false);
        } else {
            g.drawImage(imgMap, 0, 0, 0, false);
        }
        for (int i = 0; i < this.infoQuest.size(); i++) {
            QuestInfo q = (QuestInfo) this.infoQuest.elementAt(i);
            if (q != null) {
                int fms = 0;
                if (this.tick > 50)
                    fms = 1;
                if (q.idMap >= 0 && q.idMap < x.length - 1)
                    g.drawRegion(GameScr.imgPointQuest, 0, fms * 19, 18, 19, 0, x[q.idMap] + dx + 20, y[q.idMap] + dy + 5, 3, false);
            }
        }
        if (Tilemap.lv < mapNames.length && Tilemap.lv >= 0) {
            int a = 0;
            if (x[Tilemap.lv] != 1 || y[Tilemap.lv] != 1) {
                a = (x[Tilemap.lv] < 100) ? 0 : ((x[Tilemap.lv] > 200) ? 1 : 2);
                int xN = x[Tilemap.lv];
                int yN = y[Tilemap.lv] + (mSystem.isPC ? 140 : 70);
                if (this.tick2 < 30)
                    GameScr.mainChar.paintCharHead(g, xN + dx + 1, yN, 0);
            }
            int yP = 0;
            if (x[Tilemap.lv] != 1 || y[Tilemap.lv] != 1) {
                yP = y[Tilemap.lv] - 20;
                mFont.tahoma_7b_black.drawString(g, mapNames[Tilemap.lv], x[Tilemap.lv] + dx + 1, y[Tilemap.lv] + dy - 20 + 1, 2, false);
                mFont.tahoma_7b_yellow.drawString(g, mapNames[Tilemap.lv], x[Tilemap.lv] + dx, y[Tilemap.lv] + dy - 20, 2, false);
            }
            if (Tilemap.lv != lastmPoint && lastmPoint != -1) {
                a = (x[lastmPoint] < 100) ? 0 : ((x[lastmPoint] > 200) ? 1 : 2);
                int xN = x[lastmPoint];
                int yN = y[lastmPoint] - 20;
                if (yN > yP && yN - yP < 30)
                    yN += 40;
                if (yN < yP && yP - yN < 20)
                    yN -= 5;
                mFont.tahoma_7b_black.drawString(g, mapNames[lastmPoint], xN + dx + 1, yN + dy + 1, a, false);
                mFont.tahoma_7b_yellow.drawString(g, mapNames[lastmPoint], xN + dx, yN + dy, a, false);
            }
        }
        if (!GameCanvas.isTouch) {
            g.drawImage(GameScr.imgPointMinimap, mfx - 2, mfy, 0, false);
        } else {
            int xN = x[lastmPoint] - 18;
            int yN = y[lastmPoint];
            g.drawImage(GameScr.imgPointMinimap, xN + dx + 1, yN + dy + 1, 0, false);
        }
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        if (GameCanvas.isTouch)
            this.cmdClose.paint(g);
        if (!GameCanvas.isTouch)
            super.paint(g);
    }

    public void update() {
        this.tick = (this.tick + 1) % 100;
        this.tick2 = (this.tick2 + 1) % 60;
        if (this.lastSCR != null) {
            this.lastSCR.update();
        }

        if (GameCanvas.isPointerClick[0] && this.getCmdPointerLast(this.cmdClose)) {
            GameCanvas.isPointerJustRelease[0] = false;
            this.cmdClose.performAction();
        } else {
            super.updateKey();
            ++tick3;
            if (tick3 > 10000) {
                tick3 = 0;
            }

            if (mcmx != mcmtoX || mcmy != mcmtoY) {
                mcmvx = mcmtoX - mcmx << 1;
                mcmvy = mcmtoY - mcmy << 1;
                mcmdx += mcmvx;
                mcmx += mcmdx >> 4;
                mcmdx &= 15;
                mcmdy += mcmvy;
                mcmy += mcmdy >> 4;
                mcmdy &= 15;
                if (mcmx < 0) {
                    mcmx = 0;
                }

                if (mcmx > mcmxLim) {
                    mcmx = mcmxLim;
                }

                if (mcmy < 0) {
                    mcmy = 0;
                }

                if (mcmy > mcmyLim) {
                    mcmy = mcmyLim;
                }
            }

            boolean move = false;
            if (GameCanvas.keyHold[2]) {
                mfy -= 4;
                if (mfy < MapScr.dy - 10) {
                    mfy = MapScr.dy - 10;
                }

                move = true;
            }

            if (GameCanvas.keyHold[8]) {
                mfy += 4;
                if (mfy > this.maxPY) {
                    mfy = this.maxPY;
                }

                move = true;
            }

            if (GameCanvas.keyHold[4]) {
                mfx -= 4;
                if (mfx < MapScr.dx - 10) {
                    mfx = MapScr.dx - 10;
                }

                move = true;
            }

            if (GameCanvas.keyHold[6]) {
                mfx += 4;
                if (mfx > this.maxPX) {
                    mfx = this.maxPX;
                }

                move = true;
            }

            if (move) {
                mcmtoX = mfx - GameCanvas.hw;
                mcmtoY = mfy - GameCanvas.hh;
                findMapNearestPoint();
            }

            if (GameCanvas.isPointerClick[0] && GameCanvas.py[0] < GameCanvas.h - cmdH) {
                GameCanvas.isPointerClick[0] = false;
                this.trans = true;
                this.lastX = GameCanvas.px[0];
                this.lastY = GameCanvas.py[0];
            } else if (GameCanvas.isPointerDown[0] && this.trans) {
                mcmtoX -= GameCanvas.px[0] - this.lastX;
                mcmtoY -= GameCanvas.py[0] - this.lastY;
                if (mcmtoX < 0) {
                    mcmtoX = 0;
                }

                if (mcmtoY < 0) {
                    mcmtoY = 0;
                }

                if (mcmtoX > mcmxLim) {
                    mcmtoX = mcmxLim;
                }

                if (mcmtoY > mcmyLim) {
                    mcmtoY = mcmyLim;
                }

                mcmx = mcmtoX;
                mcmy = mcmtoY;
                this.lastX = GameCanvas.px[0];
                this.lastY = GameCanvas.py[0];
            }

            if (GameCanvas.isPointerJustRelease[0]) {
                int dx = GameCanvas.pxLast[0] - GameCanvas.px[0];
                int dy = GameCanvas.pyLast[0] - GameCanvas.py[0];
                if (dx < 10 && dy < 10) {
                    mfx = mcmx + GameCanvas.pxLast[0] - 8;
                    mfy = mcmy + GameCanvas.pyLast[0] - 8;
                    findMapNearestPoint();
                    if (mpoint != -1) {
                        npx = mfx;
                        npy = mfy;
                        lastmPoint = mpoint;
                    }
                }

                this.trans = false;
                GameCanvas.isPointerJustRelease[0] = false;
            }

            if (!GameCanvas.isTouch && lastmPoint != mpoint) {
                lastmPoint = mpoint;
            }

            if (!GameCanvas.isTouch) {
                super.update();
            }

        }
    }

    private static void findMapNearestPoint() {
        mpoint = -1;
        int wp = 15;
        if (!GameCanvas.isTouch) {
            wp = 13;
        }

        for (int i = 0; i < x.length; ++i) {
            if (Utils.abs(mfx - (x[i] + dx)) < wp && Utils.abs(mfy - (y[i] + dy)) < wp) {
                mpoint = i;
                break;
            }
        }

    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }

    public void perform(int idAction, Object p) {
        switch (idAction) {
            case 0:
                MainMenu.isBeginQuest = true;
                MainMenu.gI().switchToMe(this.lastSCR);
                imgMap = null;
                break;
            case 1:
                this.modeCurrentMap = !this.modeCurrentMap;
                this.resetCMLim();
        }

    }

    public boolean isMapScr() {
        return true;
    }
}

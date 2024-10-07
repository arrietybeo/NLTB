package code.model;

import code.main.GameCanvas;
import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import code.screen.screen.ScreenTeam;

import javax.microedition.lcdui.Image;

import lib.mGraphics;
import lib.mSound;
import lib.mSystem;
import lib2.mFont;

public class mCommand {
    public String caption = "";
    public int idMap = -1;
    public boolean isSelect = false;
    public boolean isCmdmenu = false;
    public static int hButtonCmd = 30;
    public static int wButtonCmd = 80;
    public String[] subCaption;
    public IActionListener actionListener;
    public int idAction;
    public boolean isPlaySoundButton = true;
    public Image img;
    public int x = -1;
    public int y = -1;
    public int wCmd;
    public int hCmd;
    int lenCaption;
    public boolean isFocus;
    public Object p;
    public Object mpaint;
    private byte indexImg;
    public boolean isLine;
    public boolean isTime;
    public long timecountDown;

    public mCommand(String caption, IActionListener actionListener, int action, Object p, int x, int y) {
        this.wCmd = ScreenTeam.cmdW;
        this.hCmd = ScreenTeam.cmdH;
        this.lenCaption = 0;
        this.isFocus = false;
        this.indexImg = 0;
        this.caption = caption;
        this.idAction = action;
        this.actionListener = actionListener;
        this.p = p;
        this.x = x;
        this.y = y;
    }

    public void setindexImage(int index) {
        this.indexImg = (byte) index;
        if (this.indexImg == 1) {
            this.wCmd = 32;
            this.hCmd = 32;
        } else if (this.indexImg == 2) {
            this.wCmd = 60;
            this.hCmd = 59;
            this.subCaption = mFont.tahoma_7_white.splitFontArray(this.caption, this.wCmd - 15);
        } else if (this.indexImg == 3) {
            this.wCmd = 50;
            this.hCmd = 22;
        } else if (this.indexImg == 4) {
            this.wCmd = 21;
            this.hCmd = 21;
        } else if (this.indexImg == 5) {
            this.wCmd = 19;
            this.hCmd = 16;
        } else if (this.indexImg == 6) {
            this.wCmd = 20;
            this.hCmd = 20;
        }

    }

    public void setLine() {
        this.isLine = true;
        this.wCmd = FontTeam.fontTile.getWidth(this.caption);
        this.hCmd = (FontTeam.fontTile.getHeight() + FontTeam.fontTile.getHeight() / 3) * 2;
    }

    public mCommand(IActionListener actionListener) {
        this.wCmd = ScreenTeam.cmdW;
        this.hCmd = ScreenTeam.cmdH;
        this.lenCaption = 0;
        this.isFocus = false;
        this.indexImg = 0;
        this.x = -100;
        this.y = -100;
        this.actionListener = actionListener;
    }

    public mCommand(String caption, IActionListener actionListener, int action, int x, int y) {
        this.wCmd = ScreenTeam.cmdW;
        this.hCmd = ScreenTeam.cmdH;
        this.lenCaption = 0;
        this.isFocus = false;
        this.indexImg = 0;
        this.caption = caption;
        this.idAction = action;
        this.actionListener = actionListener;
        this.x = x;
        this.y = y;
    }

    public mCommand(String caption, IActionListener actionListener, int action, Object p) {
        this.wCmd = ScreenTeam.cmdW;
        this.hCmd = ScreenTeam.cmdH;
        this.lenCaption = 0;
        this.isFocus = false;
        this.indexImg = 0;
        this.caption = caption;
        this.idAction = action;
        this.actionListener = actionListener;
        this.p = p;
    }

    public mCommand(String caption, IActionListener actionListener, int action, Object p, Object mpaint) {
        this.wCmd = ScreenTeam.cmdW;
        this.hCmd = ScreenTeam.cmdH;
        this.lenCaption = 0;
        this.isFocus = false;
        this.indexImg = 0;
        this.caption = caption;
        this.idAction = action;
        this.actionListener = actionListener;
        this.p = p;
        this.mpaint = mpaint;
    }

    public mCommand(String caption, IActionListener actionListener, int action) {
        this.wCmd = ScreenTeam.cmdW;
        this.hCmd = ScreenTeam.cmdH;
        this.lenCaption = 0;
        this.isFocus = false;
        this.indexImg = 0;
        this.caption = caption;
        this.idAction = action;
        this.actionListener = actionListener;
    }

    public mCommand(String caption, IActionListener actionListener, int action, long timeCountdown) {
        this.wCmd = ScreenTeam.cmdW;
        this.hCmd = ScreenTeam.cmdH;
        this.lenCaption = 0;
        this.isFocus = false;
        this.indexImg = 0;
        this.caption = caption;
        this.idAction = action;
        this.actionListener = actionListener;
        this.timecountDown = timeCountdown;
        if (this.timecountDown - mSystem.currentTimeMillis() > 0L) {
            this.isTime = true;
        }

    }

    public void resetCmd() {
        this.idAction = 1000;
        this.caption = "";
    }

    public mCommand(String caption, int action, Object p) {
        this.wCmd = ScreenTeam.cmdW;
        this.hCmd = ScreenTeam.cmdH;
        this.lenCaption = 0;
        this.isFocus = false;
        this.indexImg = 0;
        this.caption = caption;
        this.idAction = action;
        this.p = p;
    }

    public mCommand(String caption, int action, Object p, Object mpaint) {
        this.wCmd = ScreenTeam.cmdW;
        this.hCmd = ScreenTeam.cmdH;
        this.lenCaption = 0;
        this.isFocus = false;
        this.indexImg = 0;
        this.caption = caption;
        this.idAction = action;
        this.p = p;
        this.mpaint = mpaint;
    }

    public mCommand(String caption, int action) {
        this.wCmd = ScreenTeam.cmdW;
        this.hCmd = ScreenTeam.cmdH;
        this.lenCaption = 0;
        this.isFocus = false;
        this.indexImg = 0;
        this.caption = caption;
        this.idAction = action;
    }

    public mCommand(String caption, int action, int x, int y) {
        this.wCmd = ScreenTeam.cmdW;
        this.hCmd = ScreenTeam.cmdH;
        this.lenCaption = 0;
        this.isFocus = false;
        this.indexImg = 0;
        this.caption = caption;
        this.idAction = action;
        this.x = x;
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void performAction() {
        try {
            GameCanvas.clearAllPointerEvent();
            mSound.playSound(26, mSound.volumeSound);
            if (this.timecountDown - mSystem.currentTimeMillis() <= 0L) {
                if (this.actionListener != null) {
                    this.actionListener.perform(this.idAction, this.p);
                } else {
                    GameCanvas.gameScr.perform(this.idAction, this.p);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void paint(mGraphics g, int x, int y) {
        if (this.actionListener != null) {
            this.actionListener.paint(g, this.idAction, x, y, this.mpaint);
        } else {
            GameCanvas.gameScr.paint(g, this.idAction, x, y, this.mpaint);
        }

    }

    public void paint(mGraphics g) {
        if (this.img != null) {
            g.drawImage(this.img, this.x, this.y, 0, false);
            return;
        }
        int w0 = mGraphics.getImageWidth(GameScr.imgButton[this.indexImg]);
        int h0 = mGraphics.getImageHeight(GameScr.imgButton[this.indexImg]) / 2;
        if (this.caption != "" || this.indexImg != 0)
            if (GameCanvas.isTouch && !this.isLine)
                g.drawRegion(GameScr.imgButton[this.indexImg], 0, h0 * (this.isFocus ? 1 : 0), w0, h0, 0, this.x, this.y, 0, false);
        byte align = 2;
        int xs = w0 / 2 - 1;
        int ys = h0 / 2 - 5;
        if (this.isLine) {
            align = 0;
            xs = 0;
            ys /= 2;
            mFont.tahoma_7b_black.drawString(g, this.caption, this.x + xs + 1, this.y + ys + 1, align, false);
            if (mSystem.isAndroid) {
                mFont.mfont_tile_Android.drawString(g, this.caption, this.x + xs, this.y + ys, align, false);
            } else {
                FontTeam.fontTile.drawString(g, this.caption, this.x + xs, this.y + ys, align, false);
            }
        } else {
            int xf = 0, yf = 0;
            if (this.isFocus) {
                xf = 1;
                yf = 1;
            }
            int yssf = 0;
            if (mSystem.isPC || mSystem.isIP)
                yssf = -1;
            if (this.indexImg == 2) {
                int ysub = 0;
                if (this.subCaption.length > 1)
                    ysub = 5 * this.subCaption.length;
                for (int i = 0; i < this.subCaption.length; i++)
                    FontTeam.fontTile.drawString(g, this.subCaption[i], this.x + xs + xf + 1, this.y + ys + yssf + yf + i * 15 - ysub, 2, false);
            } else {
                FontTeam.fontTile.drawString(g, this.caption, this.x + xs + xf, this.y + ys + yssf + yf + (mSystem.isPC ? 1 : 0), align, false);
            }
        }
    }

    public void paint(mGraphics g, boolean isClip) {
        if (this.img != null) {
            g.drawImage(this.img, this.x, this.y, 0, isClip);
            return;
        }
        int w0 = mGraphics.getImageWidth(GameScr.imgButton[this.indexImg]);
        int h0 = mGraphics.getImageHeight(GameScr.imgButton[this.indexImg]) / 2;
        if (this.caption != "" || this.indexImg != 0)
            if (GameCanvas.isTouch && !this.isLine)
                g.drawRegion(GameScr.imgButton[this.indexImg], 0, h0 * (this.isFocus ? 1 : 0), w0, h0, 0, this.x, this.y, 0, isClip);
        byte align = 2;
        int xs = w0 / 2 - 1;
        int ys = h0 / 2 - 5;
        if (this.isLine) {
            align = 0;
            xs = 0;
            ys /= 2;
            mFont.tahoma_7b_black.drawString(g, this.caption, this.x + xs + 1, this.y + ys + 1, align, isClip);
            if (mSystem.isAndroid) {
                mFont.mfont_tile_Android.drawString(g, this.caption, this.x + xs, this.y + ys, align, isClip);
            } else {
                FontTeam.fontTile.drawString(g, this.caption, this.x + xs, this.y + ys, align, isClip);
            }
        } else {
            int xf = 0, yf = 0;
            if (this.isFocus) {
                xf = 1;
                yf = 1;
            }
            int yssf = 0;
            if (mSystem.isPC || mSystem.isIP)
                yssf = -1;
            if (this.indexImg == 2) {
                int ysub = 0;
                if (this.subCaption.length > 1)
                    ysub = 5 * this.subCaption.length;
                for (int i = 0; i < this.subCaption.length; i++)
                    FontTeam.fontTile.drawString(g, this.subCaption[i], this.x + xs + xf + 1, this.y + ys + yssf + yf + i * 15 - ysub, 2, isClip);
            } else {
                FontTeam.fontTile.drawString(g, this.caption, this.x + xs + xf, this.y + ys + yssf + yf + (mSystem.isPC ? 1 : 0), align, isClip);
            }
        }
    }

    public void paintMenu2(mGraphics g) {
        if (this.img != null) {
            g.drawImage(this.img, this.x, this.y, 0, false);
            return;
        }
        int w0 = mGraphics.getImageWidth(GameScr.imgButton[this.indexImg]);
        int h0 = mGraphics.getImageHeight(GameScr.imgButton[this.indexImg]) / 2;
        if (this.caption != "" || this.indexImg != 0)
            g.drawRegion(GameScr.imgButton[this.indexImg], 0, h0 * (this.isFocus ? 1 : 0), w0, h0, 0, this.x, this.y, 0, false);
        byte align = 2;
        int xs = w0 / 2 - 1;
        int ys = h0 / 2 - 5;
        if (this.isLine) {
            align = 0;
            xs = 0;
            ys /= 2;
            mFont.tahoma_7b_black.drawString(g, this.caption, this.x + xs + 1, this.y + ys + 1, align, false);
            if (mSystem.isAndroid) {
                mFont.mfont_tile_Android.drawString(g, this.caption, this.x + xs, this.y + ys, align, false);
            } else {
                FontTeam.fontTile.drawString(g, this.caption, this.x + xs, this.y + ys, align, false);
            }
        } else {
            int xf = 0, yf = 0;
            if (this.isFocus) {
                xf = 1;
                yf = 1;
            }
            int timecount = 0;
            if (this.timecountDown - mSystem.currentTimeMillis() > 0L)
                timecount = (int) ((this.timecountDown - mSystem.currentTimeMillis()) / 1000L);
            if (timecount <= 0 && this.isTime)
                timecount = 1;
            int yssf = 0;
            if (mSystem.isPC || mSystem.isIP)
                yssf = -1;
            if (this.indexImg == 2 && this.subCaption != null) {
                int ysub = 0;
                if (this.subCaption.length > 1)
                    ysub = ((this.subCaption.length % 2 == 0) ? 3 : 5) * this.subCaption.length;
                for (int i = 0; i < this.subCaption.length; i++) {
                    if (timecount > 0) {
                        if (i == this.subCaption.length - 1) {
                            mFont.tahoma_7b_white.drawString(g, String.valueOf(this.subCaption[i]) + timecount, this.x + xs + xf + 1, this.y + ys + yssf + yf + i * 16 - ysub, 2, false);
                        } else {
                            mFont.tahoma_7b_white.drawString(g, this.subCaption[i], this.x + xs + xf + 1, this.y + ys + yssf + yf + i * 17 - ysub, 2, false);
                        }
                    } else {
                        FontTeam.fontTile.drawString(g, this.subCaption[i], this.x + xs + xf + 1, this.y + ys + yssf + yf + i * 15 - ysub, 2, false);
                    }
                }
            } else {
                FontTeam.fontTile.drawString(g, this.caption, this.x + xs + xf, this.y + ys + yssf + yf, align, false);
            }
        }
    }

    public boolean isPointerPressInside() {
        this.isFocus = false;
        if (GameCanvas.isPointerHoldIn(this.x, this.y, this.wCmd, this.hCmd, 0)) {
            if (GameCanvas.isPointerDown[0]) {
                this.isFocus = true;
            }

            if (GameCanvas.isPointerJustRelease[0] && GameCanvas.isPointerClick[0] && GameCanvas.canTouch()) {
                return true;
            }
        }

        return false;
    }
}

package code.model;

import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mSystem;
import lib2.mFont;

public class CharCountDown {
    private long maxTime;
    private int w;
    public boolean WantDestroy;
    public String tile = "";
    long timesv;
    int timeper;
    int per;

    public CharCountDown(long time, String tile) {
        this.tile = tile;
        this.maxTime = time;
        this.w = 60;
        this.timeper = (int) (this.maxTime - mSystem.currentTimeMillis());
    }

    public void paint(mGraphics g, int x, int y) {
        int numpercen = 0;
        long cur = this.maxTime - mSystem.currentTimeMillis();
        numpercen = (int) (60L - cur * this.w / this.timeper);
        g.drawRegion(GameScr.imghealth[2], 0, 9, 62, 9, 0, x, y, 0, false);
        g.drawRegion(GameScr.imghealth[1], 0, 7, numpercen, 7, 0, x, y + 1, 0, false);
        mFont.tahoma_7_black.drawString(g, this.tile, x + this.w / 2 + 1, y - 10 + 1 - 3, 2, false);
        mFont.tahoma_7_white.drawString(g, this.tile, x + this.w / 2, y - 10 - 3, 2, false);
        FontTeam.numberSmall_white.drawString(g, this.per + "%", x + this.w / 2, y, 2, false);
    }

    public void update() {
        long cur = this.maxTime - mSystem.currentTimeMillis();
        this.per = (int) (100L - cur * 100L / (long) this.timeper);
        if (this.maxTime - mSystem.currentTimeMillis() <= 0L) {
            this.WantDestroy = true;
        }

    }

    public static String converSecon2minutes(int time) {
        int sec = time % 60;
        int min = time / 60 % 60;
        return min <= 0 ? String.valueOf(sec) : min + ":" + sec;
    }
}

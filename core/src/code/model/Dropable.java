package code.model;

import code.main.GameCanvas;
import code.screen.screen.GameScr;
import lib.mSystem;

public abstract class Dropable extends Actor {
    public static final byte STATE_STAND = 0;
    public static final byte STATE_DROP = 1;
    public static final byte STATE_FLY = 2;
    public short item_template_id;
    public boolean isSendGet = false;
    int state;
    short xTo;
    short yTo;
    short deltaH;
    short g;
    static short DROP_POWER = 6;
    public int itemClass;
    long timeStart;

    public void setType(short template_id) {
        this.item_template_id = template_id;
        this.timeStart = mSystem.currentTimeMillis();
    }

    public void startFlyTo(short xTo, short yTo) {
        this.xTo = xTo;
        this.yTo = yTo;
        this.state = 2;
        this.deltaH = 0;
        this.g = DROP_POWER;
    }

    public void startDropFrom(short xFrom, short yFrom, short xTo, short yTo) {
        this.x = xFrom;
        this.y = yFrom;
        this.xTo = xTo;
        this.yTo = yTo;
        this.state = 1;
        this.deltaH = 0;
        this.g = DROP_POWER;
        this.timeStart = mSystem.currentTimeMillis();
    }

    public void setPosTo(short x, short y) {
        this.x = x;
        this.y = (short) (y - 4 + GameCanvas.r.nextInt() % 8);
    }

    public void update() {
        long now = mSystem.currentTimeMillis();
        if (this.catagory == 3 || this.catagory == 6) {
            if (now - this.timeStart > 25000L)
                this.wantDestroy = true;
        } else if (this.catagory == 4) {
            int time = 15000;
            time = (this.item_template_id >= 10) ? 60000 : time;
            if (now - this.timeStart > time)
                this.wantDestroy = true;
        }
        if (this.state == 1 || this.state == 2) {
            this.x = (short) (this.x + (short) (this.xTo - this.x >> 2));
            this.y = (short) (this.y + (short) (this.yTo - this.y >> 2));
            if (this.g >= -DROP_POWER) {
                this.deltaH = (short) (this.deltaH + this.g);
                this.g = (short) (this.g - 1);
            }
            if ((GameScr.abs(this.x - this.xTo) < 4 || GameScr.abs(this.y - this.yTo) < 4) && this.deltaH <= 1) {
                this.x = this.xTo;
                this.y = this.yTo;
                this.deltaH = 0;
                this.g = 0;
                if (this.state == 2)
                    this.wantDestroy = true;
                this.state = 0;
            }
        }
    }

    public boolean isItemCanGet() {
        return true;
    }

    public boolean isDropItem() {
        return true;
    }
}

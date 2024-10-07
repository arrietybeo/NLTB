package code.model.arrow;

import code.main.GameCanvas;
import code.model.Actor;
import code.screen.screen.GameScr;
import lib.mGraphics;

public class WeaponsMango extends IArrow {
    public int x;
    public int y;
    public int angleTo;
    public int angle;
    public int count;
    public int vangle;
    public int limCount;
    boolean isLR;

    public WeaponsMango(Actor acFr, Actor acTo, int vangle) {
        this.x = acFr.x;
        this.y = acFr.y;
        this.vangle = vangle;
        int rd = GameCanvas.abs(GameCanvas.r.nextInt(2));
        this.limCount = GameCanvas.abs(GameCanvas.r.nextInt(3)) + 1;
        this.isLR = rd == 0;
        this.angle = this.angleTo = this.isLR ? 0 : 360;
    }

    public void onArrowTouchTarget() {
    }

    public void paint(mGraphics g) {
    }

    public void set(int type, int x, int y, int power, byte effect, Actor owner, Actor target) {
    }

    public void setAngle(int angle) {
    }

    public void update() {
        if (this.isLR) {
            this.angleTo += 30;
            if (this.angleTo > 360) {
                this.angleTo -= 360;
            }

            if (this.angle != this.angleTo) {
                this.angle += this.vangle;
                if (this.angle > 360) {
                    this.angle -= 360;
                    ++this.count;
                }
            }
        } else {
            this.angleTo -= 30;
            if (this.angleTo < 0) {
                this.angleTo += 360;
            }

            if (this.angle != this.angleTo) {
                this.angle -= this.vangle;
                if (this.angle < 0) {
                    this.angle += 360;
                    ++this.count;
                }
            }
        }

        if (this.count >= this.limCount) {
            if (GameScr.arrowsUp.contains(this)) {
                GameScr.arrowsUp.removeElement(this);
            }

            this.count = 0;
        }

        int var10000 = this.angle % 30;
    }

    public void set(int type, int x, int y, int power, Actor owner, Actor target) {
    }

    public void set(int type, int x, int y, int power, Actor owner, Actor target, int eff) {
    }

    public void setIDHEAD(int idHead) {
    }
}

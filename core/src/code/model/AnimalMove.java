package code.model;

import code.main.GameCanvas;
import code.screen.Util;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;
import lib.mGraphics;
import lib.mSystem;

public class AnimalMove extends Char {
    public short status;
    public short dir;
    public short xTo;
    public short yTo;
    public short wImg = 1;
    public short hImg = 1;
    public short sumFrame;
    public short addY;
    public short idSameMyOwnerID;
    public Char myOwner = null;
    public byte v = 5;
    public byte vMax;
    public byte type;
    public byte imgID;
    public byte count;
    public byte idFrame;
    public int totalTime;
    public int timeMagic;
    public long timeStart;
    public String infoPet;
    byte index = 0;
    byte fLip;
    byte[] arr = new byte[]{-1, 0, 1, 0};
    byte[][][] arrFrameMove = new byte[][][]{{{0, 0, 0, 1, 1, 1, 2, 2, 2, 1, 1, 1}, {3, 3, 3, 4, 4, 4, 5, 5, 5, 4, 4, 4}, {6, 6, 6, 7, 7, 7, 8, 8, 8, 7, 7, 7}, {6, 6, 6, 7, 7, 7, 8, 8, 8, 7, 7, 7}}, {{0, 0, 1, 1, 2, 2, 1, 1, 3, 3}, {5, 5, 6, 6, 5, 5, 7, 7}, {9, 9, 10, 10, 9, 9, 11, 11}, {9, 9, 10, 10, 9, 9, 11, 11}}, {{0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}}, {{0, 0, 1, 1}, {2, 2, 3, 3}, {4, 4, 5, 5}, {4, 4, 5, 5}}, {{0, 0, 1, 1}, {2, 2, 3, 3}, {4, 4, 5, 5}, {4, 4, 5, 5}}};
    byte[][][] arrFrameStand = new byte[][][]{{{0, 0, 0, 1, 1, 1, 2, 2, 2, 1, 1, 1}, {3, 3, 3, 4, 4, 4, 5, 5, 5, 4, 4, 4}, {6, 6, 6, 7, 7, 7, 8, 8, 8, 7, 7, 7}, {6, 6, 6, 7, 7, 7, 8, 8, 8, 7, 7, 7}}, {{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, {4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5}, {8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9}, {8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9}}, {{0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 0, 0, 1, 1, 1, 1}}, {{0, 0, 1, 1}, {2, 2, 3, 3}, {4, 4, 5, 5}, {4, 4, 5, 5}}, {{0, 0, 1, 1}, {2, 2, 3, 3}, {4, 4, 5, 5}, {4, 4, 5, 5}}};

    public void setFrameType4() {
        if (this.type == 4) {
            for (int i = 0; i < this.arrFrameStand[this.type].length; ++i) {
                for (int j = 0; j < this.arrFrameStand[this.type][i].length; ++j) {
                    this.arrFrameStand[this.type][i] = new byte[this.sumFrame * 3];
                    this.arrFrameStand[this.type][i][0] = 0;
                    byte n = 0;

                    for (int k = 1; k < this.sumFrame * 3; ++k) {
                        this.arrFrameStand[this.type][i][k] = n;
                        if (k % 3 == 0) {
                            ++n;
                        }
                    }
                }
            }

            this.arrFrameMove[this.type] = this.arrFrameStand[this.type];
        }

    }

    public void setPosTo(short x, short y) {
        this.xTo = x;
        this.yTo = y;
    }

    public void update() {
        switch (this.status) {
            case 0:
                ++this.count;
                if (this.count > this.arrFrameStand[this.type][this.dir].length - 1) {
                    this.count = 0;
                }

                this.idFrame = this.arrFrameStand[this.type][this.dir][this.count];
                if (this.isOutRange()) {
                    this.status = 1;
                }

                this.vMax = 0;
                break;
            case 1:
                this.yTo = this.myOwner.y;
                if (this.type == 1) {
                    if (this.myOwner.dir == 2) {
                        this.xTo = (short) (this.myOwner.x + this.myOwner.width / 2 + 16);
                    } else if (this.myOwner.dir == 3) {
                        this.xTo = (short) (this.myOwner.x - this.myOwner.width / 2 - 10);
                    } else {
                        this.xTo = this.myOwner.x;
                        if (this.myOwner.dir == 1) {
                            this.yTo = (short) (this.myOwner.y + this.myOwner.height);
                        } else {
                            this.yTo = (short) (this.myOwner.y - this.myOwner.height - 10);
                        }
                    }
                } else if (this.myOwner.dir != 2 && this.myOwner.dir != 1) {
                    this.xTo = (short) (this.myOwner.x - this.myOwner.width / 2 - (this.type == 2 ? 5 : 10));
                } else {
                    this.xTo = (short) (this.myOwner.x + this.myOwner.width / 2 + (this.type == 2 ? 5 : 14));
                }

                boolean isMatchX = false;
                boolean isMatchY = false;
                int dx1 = GameCanvas.abs(this.x - this.xTo);
                int dy1 = GameCanvas.abs(this.y - this.yTo);
                if (dx1 <= this.v + this.vMax) {
                    this.x = this.xTo;
                    isMatchX = true;
                }

                if (dy1 <= this.v + this.vMax) {
                    this.y = this.yTo;
                    isMatchY = true;
                }

                if (!isMatchX || !isMatchY || this.myOwner.state != 0 && this.myOwner.state != 2) {
                    if (this.x < this.xTo) {
                        this.x = (short) (this.x + this.v + this.vMax);
                        this.dir = 3;
                    } else if (this.x > this.xTo) {
                        this.x = (short) (this.x - (this.v + this.vMax));
                        this.dir = 2;
                    } else if (this.y > this.yTo) {
                        this.y = (short) (this.y - (this.v + this.vMax));
                        this.dir = 1;
                    } else if (this.y < this.yTo) {
                        this.y = (short) (this.y + this.v + this.vMax);
                        this.dir = 0;
                    } else {
                        this.dir = this.myOwner.dir;
                    }

                    if (this.isOutRange()) {
                        this.vMax = (byte) (this.v / 2);
                    }

                    if (this.dir == 0 && this.y >= this.yTo) {
                        this.y = this.yTo;
                        this.vMax = 0;
                    }

                    ++this.count;
                    if (this.count > this.arrFrameMove[this.type][this.dir].length - 1) {
                        this.count = 0;
                    }

                    this.idFrame = this.arrFrameMove[this.type][this.dir][this.count];
                } else if (this.myOwner.ID != GameScr.mainChar.ID) {
                    this.status = 0;
                } else if (GameScr.mainChar.posTransRoad == null) {
                    this.status = 0;
                }
            case 2:
            default:
                break;
            case 3:
                if (this.timeMagic > 0) {
                    --this.timeMagic;
                }

                if (this.timeMagic <= 0) {
                    GameCanvas.gameScr.actors.removeElement(this);
                }

                if (this.timeMagic % 4 == 0) {
                    EffectManager.addHiEffect(this.x, this.y + this.addY - this.hImg / 2, this.type != 1 ? 11 : 23);
                }
        }

        if (this.type != 1 && GameCanvas.gameTick % 2 == 0) {
            ++this.index;
            if (this.index > this.arr.length - 1) {
                this.index = 0;
            }
        }

        if (this.myOwner._isDie && GameCanvas.gameScr.actors.contains(this)) {
            GameCanvas.gameScr.actors.removeElement(this);
        }

        if ((long) this.totalTime - (mSystem.currentTimeMillis() - this.timeStart) / 60000L <= 0L && this.status != 3) {
            this.status = 3;
            this.timeMagic = 20;
        }

        if (this.dir == 2) {
            this.fLip = 2;
        } else {
            this.fLip = 0;
        }

        super.update();
    }

    public boolean isOutRange() {
        if (this.myOwner == null) {
            return false;
        } else {
            int limRange = 64;
            int dx = 70;
            int dy = 70;
            if (this.type == 2) {
                limRange = 32;
                dy = 30;
                dx = 30;
            }

            if (this.myOwner.dir == 0) {
                limRange = 48;
            }

            return Util.distance(this.x, this.y, this.myOwner.x, this.myOwner.y) > limRange || Math.abs(this.x - this.myOwner.x) > dx && this.dir != 0 && this.dir != 1 || Math.abs(this.y - this.myOwner.y) > dy;
        }
    }

    public void paint(mGraphics g) {
        ImageIcon img = GameData.getImgIcon((short) (this.imgID + 5200));
        if (this.myOwner != null) {
            if (img != null && img.img != null) {
                if (this.hImg == 1) {
                    this.hImg = (short) (mGraphics.getImageHeight(img.img) / this.sumFrame);
                }

                if (this.wImg == 1) {
                    this.wImg = (short) mGraphics.getImageWidth(img.img);
                }

                if (this.timeMagic % 2 == 0) {
                    g.drawRegion(img.img, 0, this.hImg * this.idFrame, this.wImg, this.hImg, this.fLip, this.x, this.y + this.addY + this.arr[this.index] + (this.myOwner.useHorse == -1 && this.type != 1 ? 3 : 0), mGraphics.BOTTOM | mGraphics.HCENTER, false);
                }
            }

        }
    }
}

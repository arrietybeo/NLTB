package code.model;

import code.main.GameCanvas;
import lib.mGraphics;

public class Scroll {
    public int cmtoX;
    public int cmtoY;
    public int cmx;
    public int cmy;
    public int cmvx;
    public int cmvy;
    public int cmdx;
    public int cmdy;
    public int xPos;
    public int yPos;
    public int width;
    public int height;
    public int cmxLim;
    public int cmyLim;
    public static Scroll me;
    private int pointerDownTime;
    private int pointerDownFirstX;
    public int[] pointerDownLastX = new int[3];
    public boolean pointerIsDowning;
    public boolean isDownWhenRunning;
    private int cmRun;
    public int selectedItem;
    public int ITEM_SIZE;
    public int nITEM;
    public int ITEM_PER_LINE;
    public boolean styleUPDOWN = true;
    public boolean canScroll = true;
    public boolean FocusNew;
    int x;
    int y;
    int h;
    int color;
    int yScroll;
    int hScroll;

    public void clear() {
        this.cmtoX = 0;
        this.cmtoY = 0;
        this.cmx = 0;
        this.cmy = 0;
        this.cmvx = 0;
        this.cmvy = 0;
        this.cmdx = 0;
        this.cmdy = 0;
        this.cmxLim = 0;
        this.cmyLim = 0;
        this.width = 0;
        this.height = 0;
    }

    public void setClip(mGraphics g, int x, int y, int w, int h) {
        g.setClip(x, y, w, h - 1);
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.translate(-this.cmx, -this.cmy);
    }

    public void setClip(mGraphics g) {
        g.setClip(this.xPos + 1, this.yPos + 1, this.width - 2, this.height - 2);
        g.translate(-g.getTranslateX(), -g.getTranslateY());
        g.translate(-this.cmx, -this.cmy);
    }

    public ScrollResult updateKey() {
        return this.styleUPDOWN ? this.updateKeyScrollUpDown() : this.updateKeyScrollLeftRight();
    }

    public void setcanScroll(boolean iscroll) {
        this.canScroll = iscroll;
    }

    private ScrollResult updateKeyScrollUpDown() {
        int xTT = this.xPos;
        int yTT = this.yPos;
        int wTT = this.width;
        int hTT = this.height;
        if (GameCanvas.isPointerDown[0])
            if (!this.pointerIsDowning && GameCanvas.isPointerNj(xTT, yTT, wTT, hTT, 0)) {
                for (int i = 0; i < this.pointerDownLastX.length; i++)
                    this.pointerDownLastX[0] = GameCanvas.py[0];
                this.pointerDownFirstX = GameCanvas.py[0];
                this.pointerIsDowning = true;
                this.selectedItem = -1;
                this.isDownWhenRunning = (this.cmRun != 0);
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                this.pointerDownTime++;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.py[0] &&
                        !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                    if (this.ITEM_PER_LINE > 1) {
                        int selectedRow = (this.cmtoY + GameCanvas.py[0] - yTT) / this.ITEM_SIZE;
                        int selectedColumn = (this.cmtoX + GameCanvas.px[0] - xTT) / this.ITEM_SIZE;
                        this.selectedItem = selectedRow * this.ITEM_PER_LINE + selectedColumn;
                    } else {
                        this.selectedItem = (this.cmtoY + GameCanvas.py[0] - yTT) / this.ITEM_SIZE;
                    }
                }
                int dx = GameCanvas.py[0] - this.pointerDownLastX[0];
                if (dx != 0 && this.selectedItem != -1)
                    this.selectedItem = -1;
                for (int i = this.pointerDownLastX.length - 1; i > 0; i--)
                    this.pointerDownLastX[i] = this.pointerDownLastX[i - 1];
                this.pointerDownLastX[0] = GameCanvas.py[0];
                if (this.canScroll)
                    this.cmtoY -= dx;
                if (this.cmtoY < 0)
                    this.cmtoY = 0;
                if (this.cmtoY > this.cmyLim)
                    this.cmtoY = this.cmyLim;
                if (this.cmy < 0 || this.cmy > this.cmyLim)
                    dx /= 2;
                if (this.canScroll)
                    this.cmy -= dx;
            }
        boolean isFinish = false;
        if (GameCanvas.isPointerJustRelease[0] && this.pointerIsDowning) {
            int dx = GameCanvas.py[0] - this.pointerDownLastX[0];
            GameCanvas.isPointerJustRelease[0] = false;
            if (Math.abs(dx) < 20 && Math.abs(GameCanvas.py[0] - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning) {
                this.cmRun = 0;
                this.cmtoY = this.cmy;
                this.pointerDownFirstX = -1000;
                if (this.ITEM_PER_LINE > 1) {
                    int selectedRow = (this.cmtoY + GameCanvas.py[0] - yTT) / this.ITEM_SIZE;
                    int selectedColumn = (this.cmtoX + GameCanvas.px[0] - xTT) / this.ITEM_SIZE;
                    this.selectedItem = selectedRow * this.ITEM_PER_LINE + selectedColumn;
                } else {
                    this.selectedItem = (this.cmtoY + GameCanvas.py[0] - yTT) / this.ITEM_SIZE;
                }
                this.pointerDownTime = 0;
                isFinish = true;
            } else if (this.selectedItem != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                isFinish = true;
            } else if (this.selectedItem == -1 && !this.isDownWhenRunning) {
                if (this.cmy < 0) {
                    this.cmtoY = 0;
                } else if (this.cmy > this.cmyLim) {
                    this.cmtoY = this.cmyLim;
                } else {
                    int s = GameCanvas.py[0] - this.pointerDownLastX[0] +
                            this.pointerDownLastX[0] - this.pointerDownLastX[1] + this.pointerDownLastX[1] - this.pointerDownLastX[2];
                    if (s > 10) {
                        s = 10;
                    } else if (s < -10) {
                        s = -10;
                    } else {
                        s = 0;
                    }
                    this.cmRun = -s * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerJustRelease[0] = false;
        }
        ScrollResult r = new ScrollResult();
        r.selected = this.selectedItem;
        r.isFinish = isFinish;
        r.isDowning = this.pointerIsDowning;
        return r;
    }

    private ScrollResult updateKeyScrollLeftRight() {
        int xTT = this.xPos;
        int yTT = this.yPos;
        int wTT = this.width;
        int hTT = this.height;
        if (GameCanvas.isPointerDown[0])
            if (!this.pointerIsDowning && GameCanvas.isPointerNj(xTT, yTT, wTT, hTT, 0)) {
                for (int i = 0; i < this.pointerDownLastX.length; i++)
                    this.pointerDownLastX[0] = GameCanvas.px[0];
                this.pointerDownFirstX = GameCanvas.px[0];
                this.pointerIsDowning = true;
                this.selectedItem = -1;
                this.isDownWhenRunning = (this.cmRun != 0);
                this.cmRun = 0;
            } else if (this.pointerIsDowning) {
                this.pointerDownTime++;
                if (this.pointerDownTime > 5 && this.pointerDownFirstX == GameCanvas.px[0] && !this.isDownWhenRunning) {
                    this.pointerDownFirstX = -1000;
                    this.selectedItem = (this.cmtoX + GameCanvas.px[0] - xTT) / this.ITEM_SIZE;
                }
                int dx = GameCanvas.px[0] - this.pointerDownLastX[0];
                if (dx != 0 && this.selectedItem != -1)
                    this.selectedItem = -1;
                for (int i = this.pointerDownLastX.length - 1; i > 0; i--)
                    this.pointerDownLastX[i] = this.pointerDownLastX[i - 1];
                this.pointerDownLastX[0] = GameCanvas.px[0];
                this.cmtoX -= dx;
                if (this.cmtoX < 0)
                    this.cmtoX = 0;
                if (this.cmtoX > this.cmxLim)
                    this.cmtoX = this.cmxLim;
                if (this.cmx < 0 || this.cmx > this.cmxLim)
                    dx /= 2;
                this.cmx -= dx;
            }
        boolean isFinish = false;
        if (GameCanvas.isPointerJustRelease[0] && this.pointerIsDowning) {
            int dx = GameCanvas.px[0] - this.pointerDownLastX[0];
            GameCanvas.isPointerJustRelease[0] = false;
            if (Math.abs(dx) < 20 && Math.abs(GameCanvas.px[0] - this.pointerDownFirstX) < 20 && !this.isDownWhenRunning) {
                this.cmRun = 0;
                this.cmtoX = this.cmx;
                this.pointerDownFirstX = -1000;
                this.selectedItem = (this.cmtoX + GameCanvas.px[0] - xTT) / this.ITEM_SIZE;
                this.pointerDownTime = 0;
                isFinish = true;
            } else if (this.selectedItem != -1 && this.pointerDownTime > 5) {
                this.pointerDownTime = 0;
                isFinish = true;
            } else if (this.selectedItem == -1 && !this.isDownWhenRunning) {
                if (this.cmx < 0) {
                    this.cmtoX = 0;
                } else if (this.cmx > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    int s = GameCanvas.px[0] - this.pointerDownLastX[0] +
                            this.pointerDownLastX[0] - this.pointerDownLastX[1] + this.pointerDownLastX[1] - this.pointerDownLastX[2];
                    if (s > 10) {
                        s = 10;
                    } else if (s < -10) {
                        s = -10;
                    } else {
                        s = 0;
                    }
                    this.cmRun = -s * 100;
                }
            }
            this.pointerIsDowning = false;
            this.pointerDownTime = 0;
            GameCanvas.isPointerJustRelease[0] = false;
        }
        ScrollResult r = new ScrollResult();
        r.selected = this.selectedItem;
        r.isFinish = isFinish;
        r.isDowning = this.pointerIsDowning;
        return r;
    }

    public void updatecm() {
        int xTT = this.xPos;
        int yTT = this.yPos;
        int wTT = this.width;
        int hTT = this.height;
        if (GameCanvas.isPointerNj(xTT, yTT, wTT, hTT, 0) && GameCanvas.canTouch() && !this.FocusNew) {
            this.FocusNew = true;
        }

        if (this.cmRun != 0 && !this.pointerIsDowning) {
            if (this.styleUPDOWN) {
                this.cmtoY += this.cmRun / 100;
                if (this.cmtoY < 0) {
                    this.cmtoY = 0;
                } else if (this.cmtoY > this.cmyLim) {
                    this.cmtoY = this.cmyLim;
                } else {
                    this.cmy = this.cmtoY;
                }
            } else {
                this.cmtoX += this.cmRun / 100;
                if (this.cmtoX < 0) {
                    this.cmtoX = 0;
                } else if (this.cmtoX > this.cmxLim) {
                    this.cmtoX = this.cmxLim;
                } else {
                    this.cmx = this.cmtoX;
                }
            }

            this.cmRun = this.cmRun * 9 / 10;
            if (this.cmRun < 100 && this.cmRun > -100) {
                this.cmRun = 0;
            }
        }

        if (this.cmx != this.cmtoX && !this.pointerIsDowning) {
            this.cmvx = this.cmtoX - this.cmx << 2;
            this.cmdx += this.cmvx;
            this.cmx += this.cmdx >> 4;
            this.cmdx &= 15;
        }

        if (this.cmy != this.cmtoY && !this.pointerIsDowning) {
            this.cmvy = this.cmtoY - this.cmy << 2;
            this.cmdy += this.cmvy;
            this.cmy += this.cmdy >> 4;
            this.cmdy &= 15;
        }

    }

    public void setStyle(int nItem, int ITEM_SIZE, int xPos, int yPos, int width, int height, boolean styleUPDOWN, int ITEM_PER_LINE) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.ITEM_SIZE = ITEM_SIZE;
        this.nITEM = nItem;
        this.width = width;
        this.height = height;
        this.styleUPDOWN = styleUPDOWN;
        this.ITEM_PER_LINE = ITEM_PER_LINE;
        if (styleUPDOWN) {
            this.cmyLim = nItem * ITEM_SIZE - height;
        } else {
            this.cmxLim = nItem * ITEM_SIZE - width;
        }

        if (this.cmyLim < 0) {
            this.cmyLim = 0;
        }

        if (this.cmxLim < 0) {
            this.cmxLim = 0;
        }

    }

    public void setStyle(int nItem, int ITEM_SIZE, int SIZE_H, int xPos, int yPos, int width, int height, boolean styleUPDOWN, int ITEM_PER_LINE) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.ITEM_SIZE = ITEM_SIZE;
        this.nITEM = nItem;
        this.width = width;
        this.height = height;
        this.styleUPDOWN = styleUPDOWN;
        this.ITEM_PER_LINE = ITEM_PER_LINE;
        if (styleUPDOWN) {
            this.cmyLim = nItem * SIZE_H - height;
        } else {
            this.cmxLim = nItem * ITEM_SIZE - width;
        }

        if (this.cmyLim < 0) {
            this.cmyLim = 0;
        }

        if (this.cmxLim < 0) {
            this.cmxLim = 0;
        }

    }

    public void moveTo(int to) {
        if (this.styleUPDOWN) {
            to -= (this.height - this.ITEM_SIZE * 2) / 2;
            this.cmtoY = to;
            if (this.cmtoY < 0) {
                this.cmtoY = 0;
            }

            if (this.cmtoY > this.cmyLim) {
                this.cmtoY = this.cmyLim;
            }
        } else {
            to -= (this.width - this.ITEM_SIZE) / 2;
            this.cmtoX = to;
            if (this.cmtoX < 0) {
                this.cmtoX = 0;
            }

            if (this.cmtoX > this.cmxLim) {
                this.cmtoX = this.cmxLim;
            }
        }

    }

    public void moveCmrTo(int to) {
        if (to > 0) {
            this.cmtoY += this.ITEM_SIZE;
        } else if (to < 0) {
            this.cmtoY -= 20;
        }

        if (this.cmtoY < 0) {
            this.cmtoY = 0;
        }

        if (this.cmtoY > this.cmyLim) {
            this.cmtoY = this.cmyLim;
        }

    }

    public void setInfo(int x, int y, int h, int color) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.color = color;
        this.hScroll = h - GameCanvas.wOneItem;
    }

    public void paint(mGraphics g) {
        g.setColor(this.color);
        g.fillRect(this.x - 2, this.y - 1, 3, 1, true);
        g.fillRect(this.x - 3, this.y, 1, this.h - 1, true);
        g.fillRect(this.x + 1, this.y, 1, this.h - 1, true);
        g.fillRect(this.x - 2, this.y + this.h - 1, 3, 1, true);
        g.fillRect(this.x - 2, this.y + this.yScroll, 3, GameCanvas.wOneItem, true);
    }

    public void setYScrool(int yS, int yMax) {
        this.yScroll = yS * this.hScroll / yMax;
    }

    public static Scroll gI() {
        if (me == null) {
            me = new Scroll();
        }

        return me;
    }
}

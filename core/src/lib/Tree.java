package lib;

import code.main.GameCanvas;
import code.model.ImageIcon;
import code.model.ReadMessenge;
import code.model.TreeInfoNew;
import code.screen.Res;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;

public class Tree {
    public byte x;
    public byte y;
    short w = 0;
    short h = 0;
    public static mHashtable ALL_TREE_INFO = new mHashtable();
    public short type;

    public Tree(int x, int y, int type, boolean getImage) {
        this.x = (byte) x;
        this.y = (byte) y;
        if (ReadMessenge.listIDChangeTreeJava != null) {
            for (int i = 2; i < ReadMessenge.listIDChangeTreeJava.length; ++i) {
                if (type == ReadMessenge.listIDChangeTreeJava[i]) {
                    type = ReadMessenge.listIDChangeTreeJava[1];
                    break;
                }
            }
        }

        this.type = (short) type;
    }

    public void removeImage() {
        Res.removeTreeImage(this.type);
    }

    public Tree() {
    }

    public int getH() {
        ImageIcon img = GameData.getImgIcon((short) (this.type + Res.ID_ITEM_MAP));
        return img.getHeight();
    }

    public int getHeight() {
        ImageIcon img = GameData.getImgIcon((short) (this.type + Res.ID_ITEM_MAP));
        return img.getHeight();
    }

    public int getW() {
        ImageIcon img = GameData.getImgIcon((short) (this.type + Res.ID_ITEM_MAP));
        return img.getWidth();
    }

    public int getWidth() {
        ImageIcon img = GameData.getImgIcon((short) (this.type + Res.ID_ITEM_MAP));
        return img.getWidth();
    }

    public boolean isLow(int layer) {
        if (MainUnity.isJava) {
            mHashtable hash = null;
            if (layer == 1) {
                hash = Tilemap.treeLow1;
            } else if (layer == 2) {
                hash = Tilemap.treeLow2;
            } else if (layer == 3) {
                hash = Tilemap.treeLow3;
            }

            if (hash != null) {
                if (hash.get(String.valueOf(this.y * Tilemap.w + this.x)) != null) {
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    public void paint(mGraphics g) {
        try {
            if (this.type != 348 && this.type != 349 && this.type != 400 && this.type != 402 && this.type != 403 && this.type != 486) {
                code.model.TreeInfoNew tree = (code.model.TreeInfoNew) ALL_TREE_INFO.get(String.valueOf(this.type + Res.ID_ITEM_MAP));
                int xx = this.x << 4;
                int yy = this.y << 4;
                if (tree != null) {
                    xx += tree.dx;
                    yy += tree.dy;
                }

                if (tree != null) {
                    tree.paint(g, xx, yy, this.type);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean inBound(Tree t) {
        int x1 = this.getX();
        int x2 = this.getW() + x1;
        int y1 = this.getY();
        int y2 = this.getH() + y1;
        return t.x * Tilemap.size >= x1 && t.x * Tilemap.size <= x2 && t.y * Tilemap.size >= y1 && t.y * Tilemap.size <= y2;
    }

    public int getX() {
        code.model.TreeInfoNew tree = (code.model.TreeInfoNew) ALL_TREE_INFO.get(String.valueOf(this.type + Res.ID_ITEM_MAP));
        return (this.x << 4) + (tree != null ? tree.dx : 0);
    }

    public int getY() {
        int yy = 0;
        TreeInfoNew tree = (TreeInfoNew) ALL_TREE_INFO.get((new StringBuilder(String.valueOf(this.type + Res.ID_ITEM_MAP))).toString());
        yy = (this.y << 4) + ((tree != null) ? tree.dy : 0);
        return yy;
    }

    public void setPosTo(short x, short y) {
    }

    public boolean checkInCmx(int cmx) {
        code.model.TreeInfoNew tree = (code.model.TreeInfoNew) ALL_TREE_INFO.get(String.valueOf(this.type + Res.ID_ITEM_MAP));
        int xx = (this.x << 4) + (tree != null ? tree.dx : 0);
        if (xx < cmx - this.getWidth()) {
            return false;
        } else {
            return xx - this.getWidth() <= cmx + GameCanvas.w;
        }
    }

    public boolean inCamera(int cmx, int cmy) {
        code.model.TreeInfoNew tree = (code.model.TreeInfoNew) ALL_TREE_INFO.get(String.valueOf(this.type + Res.ID_ITEM_MAP));
        int xx = (this.x << 4) + (tree != null ? tree.dx : 0);
        int yy = (this.y << 4) + (tree != null ? tree.dy : 0);
        if (xx < GameScr.cmx - this.getWidth()) {
            return false;
        } else if (yy < GameScr.cmy - this.getHeight()) {
            return false;
        } else if (xx - this.getWidth() > cmx + GameCanvas.w) {
            return false;
        } else {
            return yy <= cmy + GameCanvas.h;
        }
    }

    public boolean isInScreen() {
        return this.inCamera(GameScr.cmx, GameScr.cmy);
    }

    public boolean isTree() {
        return true;
    }
}

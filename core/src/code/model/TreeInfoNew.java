package code.model;

import code.screen.Res;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;
import lib.mGraphics;

public class TreeInfoNew {
    public short dx;
    public short dy;

    public TreeInfoNew(int dx, int dy) {
        this.dx = (short) dx;
        this.dy = (short) dy;
    }

    public void paint(mGraphics g, int x, int y, int id) {
        ImageIcon img = GameData.getImgIcon((short) (id + Res.ID_ITEM_MAP));

        try {
            if (img != null && img.img != null) {
                if (GameScr.isInScreen(x, y, mGraphics.getImageWidth(img.img), mGraphics.getImageHeight(img.img))) {
                    g.drawImage(img.img, x, y, 0, false);
                }

                Res.maxHTree = Math.max(img.img.getHeight(), Res.maxHTree);
                Res.maxWTree = Math.max(img.img.getWidth(), Res.maxWTree);
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

    }
}

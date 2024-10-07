package code.model;

import code.effect.new_skill.EffectSkill;

import javax.microedition.lcdui.Image;

import lib.mGraphics;

public class FrameImage {
    public short frameWidth;
    public short frameHeight;
    public short nFrame;
    public Image imgFrame;

    public FrameImage(Image img, int width, int height) {
        this.imgFrame = img;
        this.frameWidth = (short) width;
        this.frameHeight = (short) height;
        this.nFrame = (short) (mGraphics.getImageHeight(img) / height);
    }

    public FrameImage(int id) {
        this.imgFrame = EffectSkill.getImage(id);
        this.frameWidth = (short) EffectSkill.getWidth(id);
        this.frameHeight = (short) EffectSkill.getHight(id);
        this.nFrame = (short) EffectSkill.getFrame(id);
    }

    public void drawFrame(int idx, int x, int y, int trans, int orthor, mGraphics g) {
        if (this.imgFrame != null && idx <= this.nFrame - 1) {
            g.drawRegion(this.imgFrame, 0, idx * this.frameHeight, this.frameWidth, this.frameHeight, trans, x, y, orthor, false);
        }

    }
}

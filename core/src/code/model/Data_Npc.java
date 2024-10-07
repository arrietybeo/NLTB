package code.model;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.util.Vector;
import javax.microedition.lcdui.Image;

import lib.mGraphics;
import lib.mVector;

public class Data_Npc {
    public Vector listFrame = new Vector();
    public Vector listAnima = new Vector();
    public SmallImage[] smallImage;
    public byte[] sequence;
    private short FrameWith;
    private short FrameHeight;
    public static byte[][] indexAction = new byte[][]{{0, 0, 1, 2, 3, 1, 1, 1, 1, 1, 0, 0, 0}, {4, 4, 5, 6, 7, 5, 5, 5, 5, 4, 4, 4, 4}};

    public Data_Npc(byte[] array) {
        this.setDataType1(array);
    }

    public void setDataType1(byte[] array) {
        short len = 0;
        try {
            DataInputStream dis = new DataInputStream(new ByteArrayInputStream(array));
            int nSmallImage = dis.readByte();
            this.smallImage = new SmallImage[nSmallImage];
            for (int i = 0; i < nSmallImage; i++)
                this.smallImage[i] = new SmallImage(dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte());
            this.FrameWith = (this.smallImage[0]).w;
            this.FrameHeight = (this.smallImage[0]).h;
            int nframe = dis.readShort();
            int j;
            for (j = 0; j < nframe; j++) {
                byte npart = dis.readByte();
                mVector listPart = new mVector();
                for (int k = 0; k < npart; k++) {
                    PartFrame part = new PartFrame(dis.readShort(), dis.readShort(), dis.readByte());
                    listPart.addElement(part);
                }
                this.listFrame.addElement(new FrameEff(listPart, null));
            }
            len = dis.readShort();
            this.sequence = new byte[len];
            for (j = 0; j < len; j++)
                this.sequence[j] = (byte) dis.readShort();
            len = dis.readByte();
            byte[] data = new byte[len];
            dis.read(data);
            Animation anim = new Animation(data);
            this.listAnima.addElement(anim);
            len = dis.readByte();
            data = new byte[len];
            dis.read(data);
            anim = new Animation(data);
            this.listAnima.addElement(anim);
            len = dis.readByte();
            data = new byte[len];
            dis.read(data);
            anim = new Animation(data);
            this.listAnima.addElement(anim);
            len = dis.readByte();
            data = new byte[len];
            dis.read(data);
            anim = new Animation(data);
            this.listAnima.addElement(anim);
            len = dis.readByte();
            data = new byte[len];
            dis.read(data);
            anim = new Animation(data);
            this.listAnima.addElement(anim);
            len = dis.readByte();
            data = new byte[len];
            dis.read(data);
            anim = new Animation(data);
            this.listAnima.addElement(anim);
            len = dis.readByte();
            data = new byte[len];
            dis.read(data);
            anim = new Animation(data);
            this.listAnima.addElement(anim);
            len = dis.readByte();
            data = new byte[len];
            dis.read(data);
            anim = new Animation(data);
            this.listAnima.addElement(anim);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Animation getAnim(int action, int way) {
        return (Animation) this.listAnima.elementAt(indexAction[way][action]);
    }

    public int getFrame(int f, int action, int way) {
        Animation anim = (Animation) this.listAnima.elementAt(indexAction[way][action]);
        return f < anim.frame.length ? anim.frame[f] : 0;
    }

    public void paintType2(mGraphics g, int idFrame, int x, int y, int way) {
    }

    public void paint(mGraphics g, int idFrame, int x, int y, int way, Image img) {
        if (img != null) {
            FrameEff frame = (FrameEff) this.listFrame.elementAt(idFrame);

            try {
                for (int i = 0; i < frame.listPartTop.size(); ++i) {
                    PartFrame part = (PartFrame) frame.listPartTop.elementAt(i);
                    SmallImage small = this.smallImage[part.idSmallImg];
                    int dx = part.dx;
                    int w = small.w;
                    int h = small.h;
                    int xx = small.x;
                    int yy = small.y;
                    if (xx > img.getWidth()) {
                        xx = 0;
                    }

                    if (yy > img.getHeight()) {
                        yy = 0;
                    }

                    if (xx + w > img.getWidth()) {
                        w = img.getWidth() - xx;
                    }

                    if (yy + h > img.getHeight()) {
                        h = img.getHeight() - yy;
                    }

                    if (way == 2) {
                        dx = -dx - w;
                    }

                    g.drawRegion(img, xx, yy, w, h, way, x + dx, y + part.dy, 0, false);
                }
            } catch (Exception var16) {
                var16.printStackTrace();
            }

        }
    }

    public short getWith() {
        return this.FrameWith;
    }

    public short getHeight() {
        return this.FrameHeight;
    }
}

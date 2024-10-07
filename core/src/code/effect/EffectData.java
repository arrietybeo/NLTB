package code.effect;

import code.main.GameCanvas;
import code.model.FrameEff;
import code.model.PartFrame;
import code.model.SmallImage;
import code.network.GameService;
import code.screen.Res;
import code.screen.screen.GameData;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import lib.Rms;
import lib.mSystem;
import lib.mVector;

public class EffectData {
    public byte[] data;
    public long timeremove;
    public long timeGetBack;
    public mVector listFrame = new mVector();
    public mVector listAnima = new mVector();
    public SmallImage[] smallImage;
    public byte[][] frameChar = new byte[4][];
    public byte[] sequence;
    public int fw;
    public int fh;
    public byte[] indexSplash = new byte[4];
    public boolean isLoadData = false;

    public void setdata(byte[] data) {
        if (!this.isLoadData) {
            this.loadData(data);
            this.isLoadData = true;
        }

    }

    public boolean loadFromRms(int idEff) {
        try {
            byte[] dataRms = Rms.loadRMS("eff" + idEff);
            if (dataRms != null) {
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(dataRms));
                short lenarr = (short) dis.available();
                byte[] data = new byte[lenarr];
                dis.read(data, 0, data.length);
                this.setdata(data);
                this.timeremove = mSystem.currentTimeMillis() + 60000L;
                GameData.listbyteData.put("" + idEff, this);

                try {
                    dis.close();
                } catch (Exception var8) {
                }

                this.timeGetBack = mSystem.currentTimeMillis() + 10000L;

                try {
                    dis.close();
                } catch (Exception var7) {
                }

                return true;
            }
        } catch (Exception var9) {
        }

        return false;
    }

    public void load(int idEff) {
        try {
            DataInputStream is = null;

            try {
                this.timeremove = mSystem.currentTimeMillis() + 60000L;
                if (mSystem.currentTimeMillis() - this.timeGetBack < 0L) {
                    return;
                }

                if (!this.loadFromRms(idEff)) {
                    is = mSystem.getResourceAsStream("/datahd/effskill/" + (idEff - Res.ID_START_SKILL));
                    if (is == null) {
                        EffectData data = new EffectData();
                        this.timeremove = mSystem.currentTimeMillis() + 60000L;
                        GameData.listbyteData.put("" + idEff, data);
                        GameService.gI().doGetByteData(idEff, "effectdata getBydeData " + idEff);
                        this.timeGetBack = mSystem.currentTimeMillis() + 10000L;
                        return;
                    }

                    DataInputStream dis = new DataInputStream(is);
                    short lenarr = (short) dis.available();
                    byte[] data = new byte[lenarr];
                    dis.read(data, 0, data.length);
                    this.setdata(data);
                    this.timeremove = mSystem.currentTimeMillis() + 60000L;
                    GameData.listbyteData.put("" + idEff, this);

                    try {
                        dis.close();
                    } catch (Exception var18) {
                    }

                    this.timeGetBack = mSystem.currentTimeMillis() + 10000L;
                    return;
                }
            } catch (Exception var19) {
                var19.printStackTrace();
                return;
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (Exception var17) {
                    var17.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void loadData(byte[] array) {
        if (array == null)
            return;
        DataInputStream dis = null;
        try {
            boolean isnew = true;
            this.listFrame.removeAllElements();
            this.smallImage = null;
            dis = new DataInputStream(new ByteArrayInputStream(array));
            int nSmallImage = dis.readByte();
            this.smallImage = new SmallImage[nSmallImage];
            for (int i = 0; i < nSmallImage; i++)
                this.smallImage[i] = new SmallImage(dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte(), dis.readUnsignedByte());
            int max_height = 0;
            int nframe = dis.readShort();
            for (int j = 0; j < nframe; j++) {
                byte npart = dis.readByte();
                mVector listParttop = new mVector();
                mVector listPartbottom = new mVector();
                for (int m = 0; m < npart; m++) {
                    PartFrame part = new PartFrame(dis.readShort(), dis.readShort(), dis.readByte());
                    if (isnew) {
                        part.flip = dis.readByte();
                        part.onTop = dis.readByte();
                    }
                    if (part.onTop == 0) {
                        listParttop.addElement(part);
                    } else {
                        listPartbottom.addElement(part);
                    }
                    if (max_height < GameCanvas.abs(part.dy))
                        max_height = GameCanvas.abs(part.dy);
                }
                this.listFrame.addElement(new FrameEff(listParttop, listPartbottom));
            }
            this.fw = (this.smallImage[0]).w;
            this.fh = (short) max_height;
            short len = 0;
            if (isnew) {
                len = (short) dis.readUnsignedByte();
            } else {
                len = dis.readShort();
            }
            this.sequence = new byte[len];
            int k;
            for (k = 0; k < len; k++)
                this.sequence[k] = (byte) dis.readShort();
            if (isnew) {
                dis.readByte();
                len = dis.readByte();
                this.frameChar[0] = new byte[len];
                for (k = 0; k < len; k++)
                    this.frameChar[0][k] = dis.readByte();
                len = dis.readByte();
                this.frameChar[1] = new byte[len];
                for (k = 0; k < len; k++)
                    this.frameChar[1][k] = dis.readByte();
                len = dis.readByte();
                this.frameChar[3] = new byte[len];
                for (k = 0; k < len; k++)
                    this.frameChar[3][k] = dis.readByte();
            }
            this.isLoadData = true;
            try {
                this.indexSplash[0] = (byte) ((this.frameChar[0]).length - 7);
                this.indexSplash[1] = (byte) ((this.frameChar[1]).length - 7);
                this.indexSplash[2] = (byte) ((this.frameChar[3]).length - 7);
                this.indexSplash[3] = (byte) ((this.frameChar[3]).length - 7);
            } catch (Exception exception) {
            }
            this.indexSplash[0] = dis.readByte();
            this.indexSplash[1] = dis.readByte();
            this.indexSplash[2] = dis.readByte();
            this.indexSplash[3] = this.indexSplash[2];
        } catch (Exception exception) {
            try {
                dis.close();
            } catch (Exception exception1) {
            }
        } finally {
            try {
                dis.close();
            } catch (Exception exception) {
            }
        }
    }
}

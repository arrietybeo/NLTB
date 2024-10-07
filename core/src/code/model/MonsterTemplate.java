package code.model;

import code.network.GameService;
import code.screen.Res;
import code.screen.screen.GameData;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.microedition.lcdui.Image;

import lib.Rms;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSystem;
import lib.mVector;

public class MonsterTemplate {
    public static mHashtable ALL_MONSTER_TEMPLATE = new mHashtable();

    public static List<MonsterTemplate> MONSTER = new ArrayList<>();

    public static MonsterTemplate getMonsterTemplate(int id) {
        for (MonsterTemplate monster : MONSTER) {
            if (monster.id == id) {
                return monster;
            }
        }
        return null;
    }


    public String name = "";
    byte typeMove = 0;
    short id = 0;
    short idImg = 0;
    byte moveType;
    byte speed;
    byte height;
    public static mHashtable ALL_DATE_NEW_MONSTER = new mHashtable();
    byte w;
    byte h;
    byte xCenter;
    byte yCenter;
    byte xadd;
    byte yadd;
    public short idEff;
    public byte idShadow;
    public byte[][][] fAttack = null;
    long timeGetData = 0L;

    public MonsterTemplate(int id, String name, int typeMove, int idImg, short idEff, byte idShadow) {
        this.id = (short) id;
        this.name = name;
        this.typeMove = (byte) typeMove;
        this.idImg = (short) idImg;
        this.idEff = idEff;
        this.idShadow = idShadow;
        this.speed = (byte) Res.random(3, 5);
    }

    public MonsterTemplate(int id, String name, int typeMove, int idImg) {
        this.id = (short) id;
        this.name = name;
        this.typeMove = (byte) typeMove;
        this.idImg = (short) idImg;
        this.speed = (byte) Res.random(3, 5);
    }

    public void loadDataFile() {
    }

    public MonsterTemplate() {
    }

    public void loadImage() {
        this.getImage(this.id);
    }

    public void paint(mGraphics g, int x, int y, int flip, int anchor, int f) {
    }

    public boolean getDataMonsTerFromRms(mVector dataMonsterNew) {
        try {
            byte[] dataRms = Rms.loadRMS("monst" + this.idImg);
            if (dataRms != null) {
                DataInputStream dis = null;
                dis = new DataInputStream(new ByteArrayInputStream(dataRms));
                byte len = 1;

                for (int i = 0; i < len; ++i) {
                    short lenarr = (short) dis.available();
                    byte[] data = new byte[lenarr];
                    dis.read(data, 0, data.length);
                    DataEffect eff = new DataEffect(data, this.idImg, true);
                    eff.name = "QUAI THU: " + this.id;
                    dataMonsterNew.addElement(eff);
                }

                ALL_DATE_NEW_MONSTER.put("" + this.id, dataMonsterNew);
                this.timeGetData = System.currentTimeMillis() + 10000L;
                return true;
            }
        } catch (Exception var9) {
        }

        return false;
    }

    public mVector getDataMonster() {
        mVector dataMonsterNew = new mVector();

        try {
            mVector temp = (mVector) ALL_DATE_NEW_MONSTER.get("" + this.id);
            if (temp != null) {
                dataMonsterNew = temp;
            } else if (mSystem.currentTimeMillis() - this.timeGetData >= 0L) {
                if (this.getDataMonsTerFromRms(dataMonsterNew)) {
                    return dataMonsterNew;
                }

                InputStream is = mSystem.getResourceAsStream("/datamons/" + this.idImg);
                DataInputStream dis = new DataInputStream(is);
                byte len = 1;

                for (int i = 0; i < len; ++i) {
                    short lenarr = (short) dis.available();
                    byte[] data = new byte[lenarr];
                    dis.read(data, 0, data.length);
                    DataEffect eff = new DataEffect(data, this.idImg, true);
                    eff.name = "QUAI THU: " + this.id;
                    dataMonsterNew.addElement(eff);
                }

                ALL_DATE_NEW_MONSTER.put("" + this.id, dataMonsterNew);
                this.timeGetData = System.currentTimeMillis() + 10000L;

                try {
                    dis.close();
                } catch (Exception var10) {
                }
            }
        } catch (Exception var11) {
            if (System.currentTimeMillis() - this.timeGetData >= 0L) {
                this.timeGetData = System.currentTimeMillis() + 10000L;
                GameService.gI().doGetByteData(this.id, "monstertemplate");
            }
        }

        return dataMonsterNew;
    }

    public void setDataMonster(byte[] data) {
        DataEffect eff = new DataEffect(data, this.idImg, true);
        eff.name = "QUAI THU: " + this.id;
        mVector dataMonsterNew = new mVector();
        dataMonsterNew.addElement(eff);
        ALL_DATE_NEW_MONSTER.put("" + this.id, dataMonsterNew);
    }

    public int getWith(int index) {
        return 0;
    }

    public Image getImage(int index) {
        ImageIcon img = GameData.getImgIcon(this.idImg);
        return img != null ? img.img : null;
    }

    public byte[][][] getFrameAttack() {
        return this.fAttack;
    }
}

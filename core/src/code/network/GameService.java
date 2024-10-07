package code.network;

import code.main.GameCanvas;
import code.main.GameMidlet;
import code.model.Actor;
import code.model.Item;
import code.model.MainChar;
import code.model.QuickSlot;
import code.model.ReadMessenge;
import code.screen.MenuLogin;
import code.screen.screen.GameScr;
import code.screen.screen.SetInfoData;

import java.io.IOException;

import lib.Cout;
import lib.Session_ME;
import lib.TCanvas;
import lib.Tilemap;
import lib.mGraphics;
import lib.mSystem;
import lib.mVector;
import lib2.ISession;
import lib2.Message;

public class GameService extends Cmd_message {
    public ISession session;
    protected static GameService instance;

    public static GameService gI() {
        if (instance == null) {
            instance = new GameService();
        }

        return instance;
    }

    public Message init(byte cmd) {
        return new Message(cmd);
    }

    public void doNapGoogle(String productId, String token, String orderID) {
        Message m = this.init((byte) -75);

        try {
            m.writer().writeUTF(productId);
            m.writer().writeUTF(token);
            m.writer().writeUTF(orderID);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var6) {
        }

    }

    public void sendGetMonsterTEmplate(int type, int idTemplate, String pos) {
        Message m = this.init((byte) 100);

        try {
            m.writer().writeByte(type);
            m.writer().writeShort(idTemplate);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var6) {
        }

    }

    public void buyItem(int idItem, int idSeller, int catSeller, int itemcat, int num) {
        Message msg = this.init((byte) 24);

        try {
            msg.writer().writeShort(idSeller);
            msg.writer().writeByte(catSeller);
            msg.writer().writeShort(idItem);
            msg.writer().writeByte(itemcat);
            msg.writer().writeShort(num);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (IOException var8) {
        }

    }

    public void doHuybanHang() {
        Message msg = this.init((byte) 24);

        try {
            msg.writer().writeShort(0);
            msg.writer().writeByte(127);
            msg.writer().writeShort(0);
            msg.writer().writeByte(0);
            msg.writer().writeShort(1);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (IOException var3) {
        }

    }

    public void useItem(short itemID) {
        Message m = this.init((byte) 29);

        try {
            m.writer().writeByte(0);
            m.writer().writeShort(itemID);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (IOException var4) {
        }

    }

    public void kickOutParty(int idUser, byte kickOrDel) {
        Message m = this.init((byte) 50);

        try {
            m.writer().writeByte(kickOrDel);
            m.writer().writeShort(idUser);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var5) {
        }

    }

    public void dellGemItem(short id) {
        Message m = this.init((byte) -23);

        try {
            m.writer().writeShort(id);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
    }

    public void sellItem(byte type, short itemID) {
        Message m = this.init((byte) 28);

        try {
            m.writer().writeByte(type);
            m.writer().writeShort(itemID);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (IOException var5) {
        }

    }

    public void doRequestActionCheDo(int type, short idItem) {
        Message m = new Message((byte) -52);

        try {
            m.writer().writeByte(type);
            m.writer().writeShort(idItem);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void doRequestTachNguyenLIeu(short idItem, int type, int idMaterial, int lock) {
        Message m = new Message((byte) -68);

        try {
            m.writer().writeByte(type);
            m.writer().writeShort(idItem);
            m.writer().writeShort(idMaterial);
            m.writer().writeByte(lock);
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void doRideAnimal(short id, byte typeAnimal) {
        Message m = this.init((byte) -45);

        try {
            m.writer().writeByte(typeAnimal);
            m.writer().writeShort(id);
        } catch (Exception var5) {
        }

        this.session.sendMessage(m);
    }

    public void sellGemItem(short realID, byte khoa) {
        Message m = this.init((byte) 78);

        try {
            m.writer().writeShort(realID);
            m.writer().writeByte(khoa);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (IOException var5) {
        }

    }

    public void doUseSpecialItem(int realID) {
        Message m = this.init((byte) 86);

        try {
            m.writer().writeShort(realID);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var4) {
        }

    }

    public void onSendInfoQuest(int type, int idQuest, int mainsub) {
        Cout.println("GUI THONG TIN NHIEM VU MOI onSendInfoQuest");
        Message m = new Message((byte) -64);

        try {
            m.writer().writeByte(type);
            m.writer().writeShort(idQuest);
            m.writer().writeByte(mainsub);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        this.session.sendMessage(m);
    }

    public void receiveQuest(int idNPC, int idQuest, int type) {
        Message m = this.init((byte) 52);

        try {
            m.writer().writeByte(type);
            m.writer().writeByte(idNPC);
            m.writer().writeShort(idQuest);
        } catch (Exception var6) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void receiveQuestNew(int type, int idQuest, int mainsub) {
        Message m = this.init((byte) 52);

        try {
            m.writer().writeByte(type);
            m.writer().writeShort(idQuest);
            m.writer().writeShort(mainsub);
        } catch (Exception var6) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void buyItemShopSpecial(int id) {
        Message m = this.init((byte) 85);

        try {
            m.writer().writeByte(id);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var4) {
        }

    }

    public void requestString(int type) {
        Message m = this.init((byte) -24);

        try {
            m.writer().writeByte(type);
        } catch (Exception var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void chat(String text) {
        Message m = this.init((byte) 27);

        try {
            m.writer().writeUTF(text);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (IOException var4) {
        }

    }

    public void getDropableFromGround(short iD) {
        try {
            Message m = this.init((byte) 18);
            m.writer().writeShort(iD);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (IOException var3) {
        }

    }

    public void setConfig(int type) {
        Message m = this.init((byte) 104);

        try {
            m.writer().writeByte(type);
            this.session.sendMessage(m);
        } catch (Exception var4) {
        }

    }

    public void chatToClan(String str) {
        Message m = this.init((byte) -18);

        try {
            m.writer().writeUTF(str);
        } catch (IOException var4) {
        }

        this.session.sendMessage(m);
    }

    public void registerClan() {
        Message m = this.init((byte) -9);
        this.session.sendMessage(m);
    }

    public void questClan(int type) {
        Message m = this.init((byte) -37);

        try {
            m.writer().writeByte(type);
        } catch (Exception var4) {
        }

        this.session.sendMessage(m);
    }

    public void requestClanList(int idClan, byte page) {
        Message m = this.init((byte) -7);

        try {
            m.writer().writeByte(page);
            m.writer().writeShort(idClan);
        } catch (IOException var5) {
        }

        this.session.sendMessage(m);
    }

    public void requestClanInfo(int idClan) {
    }

    public void sendMsgPrivate(String name, String text) {
        Message m = this.init((byte) -5);

        try {
            m.writer().writeUTF(name);
            m.writer().writeUTF(text);
        } catch (IOException var5) {
        }

        this.session.sendMessage(m);
    }

    public void requestTopStronger_Righer(int type, int page) {
    }

    public void useBuff(short id, byte skillType, byte eff, short dam) {
        Message m = this.init((byte) 51);

        try {
            m.writer().writeByte(1);
            m.writer().writeShort(id);
            m.writer().writeByte(skillType);
            m.writer().writeByte(eff);
            m.writer().writeShort(dam);
        } catch (Exception var7) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void comeHomeWhenDie() {
        Message m = this.init((byte) 31);
        this.session.sendMessage(m);
        m.cleanup();
    }

    public void comeHomeWhenDieHack(short id) {
        Message m = this.init((byte) 31);

        try {
            m.writer().writeShort(id);
        } catch (Exception var4) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void requestNPCInfo(int type) {
        Message m = this.init((byte) 23);

        try {
            m.writer().writeShort(type);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (IOException var4) {
        }

    }

    public void requestSomeOneInfo(short id, byte type) {
        Message m = this.init((byte) -22);

        try {
            m.writer().writeShort(id);
        } catch (IOException var5) {
        }

        this.session.sendMessage(m);
    }

    public void sendInfoFruit(int index, int type) {
        Message m = this.init((byte) -66);

        try {
            m.writer().writeInt(index);
            m.writer().writeInt(type);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var5) {
        }

    }

    public void doRequestWeapone(int type, int weaponType, int weaponStyle, byte weaponIndex) {
        Message m = this.init((byte) -27);

        try {
            m.writer().writeByte(type);
            m.writer().writeByte(weaponType);
            m.writer().writeByte(weaponStyle);
            m.writer().writeByte(weaponIndex);
        } catch (Exception var7) {
        }

        this.session.sendMessage(m);
    }

    public void doRequestImageMonster(int idTemplate, byte palate, byte sPalate) {
        Message m = new Message((byte) -47);

        try {
            m.writer().writeByte(palate);
            m.writer().writeByte(sPalate);
            m.writer().writeShort(idTemplate);
        } catch (Exception var6) {
        }

        this.session.sendMessage(m);
    }

    public void moveChar(int x, int y) {
        if (!Tilemap.isOfflineMap) {
            if (!Tilemap.tileTypeAtPixel(x, y, 2) && y / 16 * Tilemap.w + x / 16 < Tilemap.type.length) {
                Message m = this.init((byte) 4);

                try {
                    m.writer().writeShort(x);
                    m.writer().writeShort(y);
                } catch (Exception var5) {
                }

                this.session.sendMessage(m);
                m.cleanup();
            }
        }
    }

    public void login(String username, String pass, String version) {
        Message m = this.init((byte) 1);

        try {
            m.writer().writeUTF(username);
            m.writer().writeUTF(pass);
            m.writer().writeUTF(version);
            if (!mSystem.isIos && !mSystem.isPC) {
                m.writer().writeByte(mGraphics.zoomLevel);
            } else {
                m.writer().writeByte(2);
            }

            GameMidlet.client_Provider.trim();
            m.writer().writeUTF(GameMidlet.client_Provider);
            m.writer().writeUTF(GameMidlet.provider);
            m.writer().writeUTF(GameMidlet.agent);
            m.writer().writeByte(TCanvas.ClientType);
            m.writer().writeByte(ReadMessenge.versionImage);
            m.writer().writeByte(GameScr.SERVER_ID[MenuLogin.indexServer]);
            this.session.sendMessage(m);
            m.cleanup();
            GameScr.mainChar.removeAllEff();
            GameCanvas.gameScr.cmdDisconect = null;
            GameScr.isDisConect = false;
            GameScr.isMeLogin = false;
            GameScr.timeReconnect = 0L;
        } catch (IOException var6) {
            var6.printStackTrace();
        }

    }

    public void setSession(ISession gi) {
        this.session = gi;
    }

    public void setClientType() {
        Message m = this.init((byte) -1);

        try {
            m.writer().writeByte(mGraphics.zoomLevel);
        } catch (Exception var3) {
        }

        this.session.sendMessage(m);
        m.cleanup();
    }

    public void sendChangeMapOK() {
        Message m = this.init((byte) 12);
        this.session.sendMessage(m);
        m.cleanup();
    }

    public void finishIntro() {
        try {
            Message m = this.init((byte) 13);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var2) {
        }

    }

    public void doSelectCharName(int gender, String name) {
        try {
            Message m = this.init((byte) 13);
            m.writer().writeByte(gender);
            m.writer().writeUTF(name);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var4) {
        }

    }

    public void createChar(String charName, byte classChar, byte gender) {
        Message m = this.init((byte) 14);

        try {
            m.writer().writeUTF(charName);
            m.writer().writeByte(classChar);
            m.writer().writeByte(gender);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var6) {
        }

    }

    public void moveChar(short moveToX, short moveToY) {
        if (moveToX != 0 || moveToY != 0) {
            Message m = this.init((byte) 4);

            try {
                m.writer().writeShort(moveToX);
                m.writer().writeShort(moveToY);
                this.session.sendMessage(m);
                m.cleanup();
            } catch (Exception var5) {
            }

        }
    }

    public void requestItemInfo(short id) {
    }

    public void requestMonsterInfo(short id) {
        Message m = this.init((byte) 7);

        try {
            m.writer().writeShort(id);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var4) {
        }

    }

    public void doGetImgIcon(short id, String pos) {
        if (Session_ME.gI().isConnected()) {
            Message m = new Message((byte) -51);

            try {
                m.writer().writeShort(id);
            } catch (Exception var5) {
                var5.printStackTrace();
            }

            this.session.sendMessage(m);
        }

    }

    public void doGetByteData(int id, String pos) {
        if (Session_ME.gI().isConnected()) {
            Message m = new Message((byte) -52);

            try {
                m.writer().writeShort(id);
                this.session.sendMessage(m);
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

    }

    public void requestCharInfo(int id) {
        Message m = this.init((byte) 5);

        try {
            m.writer().writeShort(id);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var4) {
        }

    }

    public void attackPlayer(mVector target, int idskill, int ideff) {
        Message m = this.init((byte) 6);

        try {
            m.writer().writeByte(target.size());
            m.writer().writeByte(idskill);

            for (int i = 0; i < target.size(); ++i) {
                Actor ac = (Actor) target.elementAt(i);
                m.writer().writeShort(ac.ID);
            }

            m.writer().writeByte(ideff);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var7) {
        }

    }

    public void attackMonster(mVector target, int idskill, int idEff) {
        Message m = this.init((byte) 9);

        try {
            m.writer().writeByte(target.size());
            m.writer().writeByte(idskill);

            for (int i = 0; i < target.size(); ++i) {
                Actor ac = (Actor) target.elementAt(i);
                m.writer().writeShort(ac.ID);
            }

            m.writer().writeByte(idEff);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var7) {
        }

    }

    public void Dynamic_Menu(short idNPC, byte idMenu, byte index) {
        Message m = this.init((byte) -30);

        try {
            m.writer().writeShort(idNPC);
            m.writer().writeByte(idMenu);
            m.writer().writeByte(index);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var6) {
        }

    }

    public void OnPopUp_Server(short id, byte type, byte value) {
        Message m = this.init((byte) -32);

        try {
            m.writer().writeShort(id);
            m.writer().writeByte(type);
            m.writer().writeByte(value);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var6) {
        }

    }

    public void doChatWorld(String str) {
        Message m = this.init((byte) 71);

        try {
            m.writer().writeUTF(str);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var4) {
        }

    }

    public void TexBox(short idNPC, short idMenu, String[] minfo) {
        Message m = this.init((byte) -31);

        try {
            m.writer().writeShort(idNPC);
            m.writer().writeShort(idMenu);
            byte size = (byte) minfo.length;
            m.writer().writeByte(size);

            for (int i = 0; i < size; ++i) {
                m.writer().writeUTF(minfo[i]);
            }

            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var7) {
        }

    }

    public void use_Buff(int idskill) {
        Message m = this.init((byte) 89);

        try {
            m.writer().writeByte(idskill);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var4) {
        }

    }

    public void doChat(String name, String info) {
        Message m = this.init((byte) -73);

        try {
            m.writer().writeUTF(name);
            m.writer().writeUTF(info);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var5) {
        }

    }

    public void SkillPoint(int type, int idSkill, int num) {
        Message m = this.init((byte) -72);

        try {
            m.writer().writeByte(type);
            m.writer().writeByte(idSkill);
            m.writer().writeShort(num);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var6) {
        }

    }

    public void Friend(byte type, String namechar) {
        Message m = this.init((byte) 101);

        try {
            m.writer().writeByte(type);
            m.writer().writeUTF(namechar);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var5) {
        }

    }

    public void changePK(byte typePK) {
        Message m = this.init((byte) 49);

        try {
            m.writer().writeByte(typePK);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var4) {
        }

    }

    public void ListInfo(byte type, String title, byte indexAction, short indexlist) {
        Message m = this.init((byte) 50);

        try {
            m.writer().writeByte(type);
            m.writer().writeUTF(title);
            m.writer().writeByte(indexAction);
            m.writer().writeShort(indexlist);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var7) {
        }

    }

    public void napDiamond() {
        try {
            Message m = this.init((byte) -56);
            this.session.sendMessage(m);
            m.cleanup();
        } catch (Exception var2) {
        }

    }

    public void doCreateParty(byte type, short idChar, short typeOk, String name) {
        try {
            Message msg = this.init((byte) 48);
            msg.writer().writeByte(type);
            if (type != 0) {
                if (type == 1) {
                    msg.writer().writeShort(idChar);
                } else if (type == 2) {
                    msg.writer().writeShort(typeOk);
                    msg.writer().writeUTF(name);
                } else if (type == 3) {
                    msg.writer().writeShort(idChar);
                } else if (type != 4) {
                }
            }

            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var6) {
        }

    }

    public void dosendThachdau(byte type, short id) {
        try {
            Message msg = this.init((byte) 54);
            msg.writer().write(type);
            msg.writer().writeShort(id);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var4) {
        }

    }

    public void startCountdown(int cat, int id) {
        Message msg = this.init((byte) 60);

        try {
            msg.writer().writeByte(cat);
            msg.writer().writeShort(id);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var5) {
        }

    }

    public void autoGetitem(int type, mVector vec) {
        Message msg = this.init((byte) -59);

        try {
            if (type == 0) {
                msg.writer().writeByte(type);
                msg.writer().writeByte(vec.size());

                for (int i = 0; i < vec.size(); ++i) {
                    SetInfoData sdt = (SetInfoData) vec.elementAt(i);
                    if (sdt != null) {
                        msg.writer().writeByte(sdt.index);
                    }
                }
            }

            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var6) {
        }

    }

    public void RequestImgBigmap(byte id) {
        Message msg = this.init((byte) -74);

        try {
            msg.writer().writeByte(id);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var4) {
        }

    }

    public void doRequestDataChar(int version, int idChunk) {
        Message msg = this.init((byte) -125);

        try {
            msg.writer().writeByte(version);
            msg.writer().writeShort(idChunk);
            if (!mSystem.isIos && !mSystem.isPC) {
                msg.writer().writeByte(mGraphics.zoomLevel);
            } else {
                msg.writer().writeByte(2);
            }

            msg.writer().writeByte(TCanvas.ClientType);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var5) {
        }

    }

    public void doReciveItem(int id) {
        Message msg = this.init((byte) 63);

        try {
            msg.writer().writeInt(id);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var4) {
        }

    }

    public void doUpgradeItem(byte type, byte numStone, mVector vecItem) {
        Message msg = this.init((byte) 67);

        try {
            msg.writer().writeByte(type);
            msg.writer().writeByte(numStone);
            byte size = (byte) vecItem.size();
            msg.writer().writeByte(size);

            for (int i = 0; i < size; ++i) {
                Item ite = (Item) vecItem.elementAt(i);
                if (ite != null) {
                    msg.writer().writeShort(ite.ID);
                }
            }

            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var8) {
        }

    }

    public void doUpgradeItem(byte type, short id0, short id1, short id2) {
        Message msg = this.init((byte) 67);

        try {
            msg.writer().writeByte(type);
            msg.writer().writeShort(id0);
            msg.writer().writeShort(id1);
            msg.writer().writeShort(id2);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var7) {
        }

    }

    public void Clan(byte type, short idChar) {
        Message msg = this.init((byte) 66);

        try {
            msg.writer().writeByte(type);
            switch (type) {
                case 1:
                    msg.writer().writeShort(idChar);
                case 0:
                default:
                    this.session.sendMessage(msg);
                    msg.cleanup();
            }
        } catch (Exception var5) {
        }

    }

    public void OkClan(byte type, String name, short idChar) {
        Message msg = this.init((byte) 66);

        try {
            msg.writer().writeByte(type);
            msg.writer().writeShort(idChar);
            msg.writer().writeUTF(name);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var6) {
        }

    }

    public void RequestInfoClan(byte index, int type) {
        Message msg = this.init((byte) 66);

        try {
            msg.writer().writeByte(type);
            msg.writer().writeByte(index);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var5) {
        }

    }

    public void DonateClan() {
        Message msg = this.init((byte) 66);

        try {
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var3) {
        }

    }

    public void SloganClan() {
        Message msg = this.init((byte) 66);

        try {
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var3) {
        }

    }

    public void DeleteClan() {
        Message msg = this.init((byte) 66);

        try {
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var3) {
        }

    }

    public void doSendChangeQuickSlot() {
        Message msg = this.init((byte) 3);

        try {
            int size = MainChar.mQuickslot.length;
            msg.writer().writeByte(size);

            for (int i = 0; i < size; ++i) {
                QuickSlot ql = MainChar.mQuickslot[i];
                if (ql == null) {
                    msg.writer().writeByte(-1);
                } else {
                    msg.writer().writeByte(ql.idSkill);
                }
            }

            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var5) {
        }

    }

    public void dochangeCharWearing() {
        Message msg = this.init((byte) 15);

        try {
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var3) {
        }

    }

    public void doCreateItem(mVector vec, int[] arr) {
        Message msg = this.init((byte) 68);

        try {
            int size = vec.size();
            msg.writer().writeByte(size);

            for (int i = 0; i < size; ++i) {
                Item ite = (Item) vec.elementAt(i);
                if (ite != null) {
                    msg.writer().writeShort(ite.ID);
                    msg.writer().writeByte(arr[i]);
                }
            }

            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var7) {
        }

    }

    public void doBuyItemFashion(int type) {
        Message msg = this.init((byte) -124);

        try {
            msg.writer().writeShort(type);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var4) {
        }

    }

    public void doCreatephiphong(int type, Item item, mVector vec) {
        Message msg = this.init((byte) 70);

        try {
            msg.writer().writeByte(type);
            switch (type) {
                case 0:
                default:
                    break;
                case 1:
                    msg.writer().writeShort(item.ID);
                    break;
                case 2:
                    msg.writer().writeShort(item.ID);
                    int size = vec.size();
                    msg.writer().writeByte(size);

                    for (int i = 0; i < size; ++i) {
                        Item ite = (Item) vec.elementAt(i);
                        if (ite != null) {
                            msg.writer().writeShort(ite.ID);
                        }
                    }
            }

            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var8) {
        }

    }

    public void doCreatephiphong_Array(int type, Item item, Item[] vec) {
        Message msg = this.init((byte) 70);

        try {
            msg.writer().writeByte(type);
            switch (type) {
                case 0:
                default:
                    break;
                case 1:
                    msg.writer().writeShort(item.ID);
                    break;
                case 2:
                    msg.writer().writeShort(item.ID);
                    int size = vec.length;
                    msg.writer().writeByte(size);

                    for (int i = 0; i < size; ++i) {
                        if (vec[i] != null) {
                            msg.writer().writeShort(vec[i].ID);
                        }
                    }
            }

            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var7) {
        }

    }

    public void doPetEat(short iditem, byte type) {
        Message msg = this.init((byte) -21);

        try {
            msg.writer().writeByte(type);
            msg.writer().writeShort(iditem);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var5) {
        }

    }

    public void doNapApple(byte typeNap, String producID, String token) {
        Message msg = this.init((byte) -109);

        try {
            msg.writer().writeByte(typeNap);
            msg.writer().writeUTF(token);
            msg.writer().writeUTF(producID);
            this.session.sendMessage(msg);
            msg.cleanup();
        } catch (Exception var6) {
        }

    }
}

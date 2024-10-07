package code.network;

import code.main.GameCanvas;
import code.main.GameMidlet;
import code.model.IActionListener;
import code.screen.screen.FontTeam;
import code.screen.screen.GameScr;
import lib.Cout;
import lib.mGraphics;
import lib.mHashtable;
import lib.mSystem;
import lib2.IMessageHandler;
import lib2.Message;

public class MessageHandler extends Cmd_message implements IMessageHandler, IActionListener {
    protected static MessageHandler instance;
    public static mHashtable countmsg = new mHashtable();
    public int idB;
    public int idPopup;
    public short[] ar;

    public static MessageHandler gI() {
        if (instance == null) {
            instance = new MessageHandler();
        }

        return instance;
    }

    public void onConnectOK() {
        GameCanvas.gameScr.onConnectOK();
    }

    public void onConnectionFail() {
        GameCanvas.gameScr.onConnectFail();
    }

    public void onDisconnected() {
        GameCanvas.gameScr.onDisconnect("messagehandler");
    }

    public void onMessage(Message msg) {
        try {
            switch (msg.command) {
                case -126:
                    GameCanvas.readMessenge.onBaoTri(msg);
                    break;
                case -125:
                    GameCanvas.readMessenge.onCharData(msg);
                    break;
                case -124:
                    GameCanvas.readMessenge.onShopHair(msg);
                    break;
                case -75:
                    mSystem.handleMessage(msg);
                    break;
                case -74:
                    GameCanvas.readMessenge.onImgBigmap(msg);
                    break;
                case -73:
                    GameCanvas.readMessenge.onChat(msg);
                    break;
                case -72:
                    GameCanvas.readMessenge.huongdan(msg);
                    break;
                case -71:
                    GameCanvas.readMessenge.onDynamicEffect(msg);
                    break;
                case -57:
                    GameCanvas.readMessenge.onLoadAllImage(msg);
                    break;
                case -54:
                    GameCanvas.readMessenge.onUpdateAllImage(msg);
                    break;
                case -52:
                    GameCanvas.readMessenge.onByteDataServer(msg);
                    break;
                case -51:
                    GameCanvas.readMessenge.onImageFromServer(msg);
                    return;
                case -50:
                    GameCanvas.readMessenge.onsetInfoNPC_Server(msg);
                    break;
                case -39:
                    GameCanvas.readMessenge.onWeather(msg);
                    break;
                case -32:
                    GameCanvas.readMessenge.onPopupServer(msg);
                    break;
                case -31:
                    GameCanvas.readMessenge.TexBox_Server(msg);
                    break;
                case -30:
                    GameCanvas.readMessenge.onMenu_Option(msg);
                case -22:
                case 14:
                default:
                    break;
                case -21:
                    GameCanvas.readMessenge.onPetinfo(msg);
                    break;
                case 1:
                    GameCanvas.readMessenge.onLoginSuccess(msg);
                    if (mSystem.isAndroidStore() || mSystem.isIosAppStore()) {
                        mSystem.handleAllMessage();
                    }
                    break;
                case 2:
                    GameCanvas.readMessenge.onLogOut(msg);
                    break;
                case 3:
                    GameCanvas.readMessenge.onMainCharInfo(msg);
                    break;
                case 4:
                    GameCanvas.readMessenge.onMove(msg);
                    break;
                case 5:
                    GameCanvas.readMessenge.onCharInfo(msg);
                    break;
                case 6:
                    GameCanvas.readMessenge.onAttackPlayer(msg);
                    break;
                case 7:
                    GameCanvas.readMessenge.onMonsterInfo(msg);
                    break;
                case 8:
                    GameCanvas.readMessenge.charOut(msg);
                    break;
                case 9:
                    GameCanvas.readMessenge.onAttackMonster(msg);
                    break;
                case 10:
                    GameCanvas.readMessenge.onMonsterAttackPlayer(msg);
                    break;
                case 11:
                    GameCanvas.readMessenge.onNEwAccount(msg);
                    break;
                case 12:
                    GameCanvas.readMessenge.onMap(msg);
                    break;
                case 13:
                    GameCanvas.readMessenge.ongetCharList(msg);
                    break;
                case 15:
                    GameCanvas.readMessenge.onCharWearing(msg);
                    break;
                case 16:
                    GameCanvas.readMessenge.onCharInventory(msg);
                    break;
                case 17:
                    GameCanvas.readMessenge.onMosterDie_(msg);
                    break;
                case 18:
                    GameCanvas.readMessenge.onGetItem(msg);
                    break;
                case 23:
                    GameCanvas.readMessenge.ongetInfoNPC(msg);
                    break;
                case 25:
                    GameCanvas.readMessenge.onItemTemplate(msg);
                    if (mSystem.isAndroidStore() || mSystem.isIosAppStore()) {
                        mSystem.handleAllMessage();
                    }
                    break;
                case 26:
                    GameCanvas.readMessenge.onMonsterTemplate(msg);
                    break;
                case 27:
                    GameCanvas.readMessenge.chat(msg);
                    break;
                case 30:
                    GameCanvas.readMessenge.Set_XP(msg);
                    break;
                case 33:
                    GameCanvas.readMessenge.Level_Up(msg);
                    break;
                case 35:
                    GameCanvas.readMessenge.onSkillInfo(msg);
                    break;
                case 36:
                    GameCanvas.readMessenge.onUpdateHP_MP(msg);
                    break;
                case 37:
                    GameCanvas.readMessenge.onAlertPopUp(msg);
                    break;
                case 47:
                    GameCanvas.readMessenge.clearloadMap(msg);
                    break;
                case 48:
                    GameCanvas.readMessenge.onListParty(msg);
                    break;
                case 49:
                    GameCanvas.readMessenge.changPK(msg);
                    break;
                case 50:
                    GameCanvas.readMessenge.List_info(msg);
                    break;
                case 52:
                    GameCanvas.readMessenge.receiveQuest(msg);
                    break;
                case 53:
                    GameCanvas.readMessenge.addNotify(msg);
                    break;
                case 54:
                    GameCanvas.readMessenge.ThacDau(msg);
                    break;
                case 55:
                    GameCanvas.readMessenge.onListNPCsell(msg);
                    break;
                case 60:
                    GameCanvas.readMessenge.onCountDown(msg);
                    break;
                case 61:
                    GameCanvas.readMessenge.opentBox(msg);
                    break;
                case 62:
                    GameCanvas.readMessenge.onInfofromserver(msg);
                    break;
                case 63:
                    GameCanvas.readMessenge.onGiveItem(msg);
                    break;
                case 66:
                    GameCanvas.readMessenge.inviteClan(msg);
                    break;
                case 67:
                    GameCanvas.readMessenge.onUpgradeItem(msg);
                    break;
                case 68:
                    GameCanvas.readMessenge.onCreateItem(msg);
                    break;
                case 69:
                    GameCanvas.readMessenge.onPetattack(msg);
                    break;
                case 70:
                    GameCanvas.readMessenge.onGhepPhiPhong(msg);
                    break;
                case 71:
                    GameCanvas.readMessenge.onPetBuff(msg);
                    break;
                case 85:
                    GameCanvas.startOKDlg("Đã mua, món đồ đang ở trong hành trang.");
                    break;
                case 89:
                    GameCanvas.readMessenge.startBuff(msg);
                    break;
                case 90:
                    GameCanvas.readMessenge.onActorDie(msg);
                    break;
                case 101:
                    GameCanvas.readMessenge.Add_Friend(msg);
                    break;
                case 104:
                    try {
                        GameScr.loadConfig(msg.reader().readByte());
                        byte var2 = msg.reader().readByte();
                    } catch (Exception var3) {
                        Cout.println("LOI CHO NAY NE " + var3.toString());
                    }
            }
            System.out.println("msg.command: " + msg.command);
        } catch (Exception var4) {
            var4.printStackTrace();
            System.out.println(msg.command + " tai cmd nay");
            Cout.println(msg.command + " tai cmd nay");
        }

    }

    public int getIndexClassFromItemID(int itemID) {
        if (itemID >= 79 && itemID <= 113) {
            return (itemID - 79) / 7;
        } else if (itemID >= 174 && itemID <= 213) {
            return (itemID - 174) / 8;
        } else {
            return itemID >= 214 && itemID <= 263 ? (itemID - 214) / 10 : 0;
        }
    }

    public void perform(int idAction, Object p) {
        String inFo;
        switch (idAction) {
            case 0:
                short a = Short.parseShort((String) p);
                GameCanvas.endDlg();
                break;
            case 1:
                short a1 = Short.parseShort((String) p);
                GameCanvas.endDlg();
                break;
            case 2:
                GameCanvas.endDlg();
                break;
            case 3:
                GameCanvas.endDlg();
                break;
            case 4:
                inFo = (String) p;

                GameMidlet var10000;
                try {
                    var10000 = GameMidlet.instance;
                    GameMidlet.platformRequest(inFo);
                    Thread.sleep(500L);
                } catch (Exception var8) {
                }

                var10000 = GameMidlet.instance;
                GameMidlet.notifyDestroyed();
                break;
            case 5:
                GameCanvas.endDlg();
                break;
            case 6:
                inFo = (String) p;
                String[] array = FontTeam.splitString(inFo, ":");
                GameCanvas.startOKDlg("Tài khoản " + array[0] + " với M.Khẩu: " + array[1] + " đã được đăng ký.Vui lòng thoát game và chờ trong ít phút.");
                break;
            case 7:
                GameCanvas.startOKDlg("Có lỗi khi gửi tin nhắn đăng ký. Xin hãy thử lại");
            case 8:
            case 9:
        }

    }

    public void paint(mGraphics g, int idAction, int x, int y, Object paint) {
    }
}

package code.main;

import code.effect.new_skill.EffectSkill;
import code.network.GameService;
import code.network.MessageHandler;
import code.screen.Res;
import code.screen.screen.GameData;
import code.screen.screen.GameScr;
import code.screen.screen.MainMenu;
import code.screen.screen.ServerListScr;
import com.team.ngulong.MyGdxGame;
import lib.Context;
import lib.Rms;
import lib.Session_ME;
import lib.mSystem;

public class GameMidlet {
    public static String IP = "210.245.115.227";
    public static int PORT = 19129;
    public static GameCanvas gameCanvas;
    public static GameMidlet instance;
    public static String client_Provider = "0";
    public static String provider = "-1";
    public static String agent = "-1";
    public static String url = "";
    public static boolean isLoadAgentPro = false;
    public static int coutVerSion = 56;
    public static String linkGetHost = "Chua co";
    public static boolean isStartGame;
    public static String linkWeb = "http://5ltb.com/";
    public static boolean isKPAH = true;

    public GameMidlet() {
        instance = this;
        Session_ME.gI().setHandler(MessageHandler.gI());
        GameService.gI().setSession(Session_ME.gI());
        this.startApp();
    }

    public void initGame(Context c) {
        gameCanvas = new GameCanvas(c);
        this.initGame2();
    }

    public void initGame() {
        gameCanvas = new GameCanvas();
        GameCanvas.Connect();
        this.initGame2();
        this.load();
    }

    public void initGame2() {
        gameCanvas.start();
        Res.load.isInitGame = true;
    }

    public void load() {
        gameCanvas.init();
        gameCanvas.sizeChanged(0, 0);
        Res.loadTreeImage();
        Res.loadMonster();
        Res.loadPart2();
        EffectSkill.load();
        MainMenu.gI().initName();
        MainMenu.gI().setInfo(0, true, new byte[]{0, 9});
        Res.resetImgMonsTemp();
        GameData.removeAllImgTree();
        this.checkLogin();
        GameCanvas.isLoad = false;
        ServerListScr.addressSave = null;
        ServerListScr.portSave = -1;
        ServerListScr.addressSave = Rms.loadRMSString("addressSave");
        String temp = Rms.loadRMSString("portSave");
        if (temp != null) {
            ServerListScr.portSave = Short.parseShort(temp);
        }

        if (ServerListScr.addressSave != null && ServerListScr.portSave != -1) {
            GameCanvas.loginScr.tfUser.setText("");
            GameCanvas.loginScr.tfPass.setText("");
        }

    }

    public void checkLogin() {
        String user = "";
        String pass = "";
        String add = ServerListScr.address[ServerListScr.index];
        String port = String.valueOf(ServerListScr.port[ServerListScr.index]);
        user = GameCanvas.loginScr.tfUser.getText().toLowerCase().trim();
        pass = GameCanvas.loginScr.tfPass.getText();
        if (!user.equals("") && !pass.equals("")) {
            GameCanvas.loginScr.switchToMe();
            GameCanvas.loginScr.doLogin(user, pass, add, port);
        } else {
            if (user.equals("")) {
                user = Rms.loadRMSString(Rms.User_Quick_Play);
            }

            if (pass.equals("")) {
                pass = Rms.loadRMSString(Rms.Pass_Quick_Play);
            }

            if (user == null) {
                user = "";
            }

            if (pass == null) {
                pass = "";
            }

            if (user != null && pass != null && !user.equals("") && !pass.equals("")) {
                GameCanvas.loginScr.switchToMe();
                GameCanvas.loginScr.doLogin(user, pass, add, port);
            } else {
                if (user.equals("")) {
                    GameScr.playNew();
                } else {
                    GameCanvas.loginScr.switchToMe();
                }
            }
        }
    }

    protected void destroyApp(boolean arg0) {
    }

    protected void pauseApp() {
    }

    protected void startApp() {
        if (!isStartGame) {
            this.initGame();
            gameCanvas.displayMe(instance);
            isStartGame = true;
        }

    }

    public static void requestLink(String link) {
        try {
            platformRequest(link);
            destroy();
        } catch (Exception var2) {
        }

    }

    public static void destroy() {
        notifyDestroyed();
    }

    public static void notifyDestroyed() {
    }

    public static void exitApp() {
        MyGdxGame.exitApp();
    }

    public static void platformRequest(String link) {
    }

    public void exit() {
        GameCanvas.endDlg();
        gameCanvas.init();
        Session_ME.gI().close();
        ServerListScr.gI().switchToMe();
        mSystem.gcc();
    }
}

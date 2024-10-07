package lib2;

public class SoundMn {
    public static SoundMn gI;
    public static boolean isSound = true;
    public static int volume = 0;
    private static final int MAX_VOLUME = 10;
    public static final int AIR_SHIP = 0;
    public static int[] soundID;
    public static int FLY = 1;
    public static int GET_ITEM = 0;
    public static int MOVE = 1;
    public static int LOW_PUNCH = 2;
    public static int LOW_KICK = 3;
    public static int GROUND_HIT = 4;
    public static int JUMP = 5;
    public static int PANEL_OPEN = 6;
    public static int BUTTON_CLOSE = 7;
    public static int BUTTON_CLICK = 8;
    public static int MEDIUM_PUNCH = 9;
    public static int MEDIUM_KICK = 10;
    public static int PANEL_CLICK = 11;
    public static int EAT_PEAN = 12;
    public static int OPEN_DIALOG = 13;
    public static int NORMAL_KAME = 14;
    public static int NAMEK_KAME = 15;
    public static int XAYDA_KAME = 16;

    public static SoundMn gI() {
        if (gI == null) {
            gI = new SoundMn();
        }

        return gI;
    }

    public void loadSound(int mapID) {
    }

    public void charPunch(boolean isKick, float volumn) {
    }

    public void traidatKame() {
    }

    public void namekKame() {
    }

    public void xaydaKame() {
    }

    public void mobKame() {
    }

    public void charRun(float volumn) {
    }

    public void charFall() {
    }

    public void charJump() {
    }

    public void panelOpen() {
    }

    public void buttonClose() {
    }

    public void buttonClick() {
    }

    public void stopMove() {
    }

    public void charFly() {
    }

    public void stopFly() {
    }

    public void openMenu() {
    }

    public void panelClick() {
    }

    public void eatPeans() {
    }

    public void openDialog() {
    }

    public void update() {
    }

    public void explode_1() {
    }

    public void airShip() {
    }

    public void HP_MPup() {
    }

    public void getItem() {
    }

    public void rain() {
    }

    public void mobKame(int dartType) {
    }

    public void stopAll() {
    }

    public void explode_2() {
    }

    public void pauseAirShip() {
    }

    public void taitaoPause() {
    }

    public void thaiduonghasan() {
    }

    public void hoisinh() {
    }

    public boolean isPlayAirShip() {
        return false;
    }

    public void resumeAirShip() {
    }

    public boolean isPlayRain() {
        return false;
    }

    public void closeSound() {
    }

    public void openSound() {
    }
}

package lib;

import code.main.GameCanvas;

public class mSound {
    public static float volumeSound = 0.0F;
    public static float volumeMusic = 0.0F;
    private static final int MAX_VOLUME = 10;
    public static int[] soundID;
    public static int CurMusic = -1;
    public static boolean isMusic = true;
    public static boolean isSound = true;
    public static final byte MUSIC_LANG = 0;
    public static final byte MUSIC_THIENNHIEN = 1;
    public static final byte MUSIC_SA_MAC = 2;
    public static final byte MUSIC_TUYET = 3;
    public static final byte MUSIC_MACHU = 4;
    public static final byte MUSIC_TRUC_LAM = 5;
    public static final byte MUSIC_HOALONG = 6;
    public static final byte MUSIC_SAMAC = 8;
    public static final byte MUSIC_PHOBANG = 9;
    public static final byte SOUND_EFF_BUOCCHAN = 0;
    public static final byte SOUND_EFF_PLAYERDINUOC = 1;
    public static final byte SOUND_EFF_KIEM = 2;
    public static final byte SOUND_EFF_GAY = 3;
    public static final byte SOUND_EFF_PHI_TIEU = 4;
    public static final byte SOUND_EFF_DAO = 5;
    public static final byte SOUND_EFF_THIEU_LAM1 = 6;
    public static final byte SOUND_EFF_THIEU_LAM2 = 7;
    public static final byte SOUND_EFF_NGU_DOC_1 = 8;
    public static final byte SOUND_EFF_NGU_DOC_2 = 9;
    public static final byte SOUND_EFF_NGA_MI1 = 10;
    public static final byte SOUND_EFF_NGA_MI2 = 11;
    public static final byte SOUND_EFF_VO_DANG1 = 12;
    public static final byte SOUND_EFF_VO_DANG2 = 13;
    public static final byte SOUND_EFF_CAI_BANG1 = 14;
    public static final byte SOUND_EFF_CAI_BANG2 = 15;
    public static final byte SOUND_EFF_NPC_THO_REN = 16;
    public static final byte SOUND_EFF_MEO_KEU = 17;
    public static final byte SOUND_EFF_GA_KEU = 18;
    public static final byte SOUND_EFF_NUOC_CHAY = 19;
    public static final byte SOUND_EFF_THAC_CHAY = 20;
    public static final byte SOUND_EFF_NGU_DOC_4 = 21;
    public static final byte SOUND_EFF_THIEU_LAM_4 = 22;
    public static final byte SOUND_EFF_NGA_MI_4 = 23;
    public static final byte SOUND_EFF_VO_DANG_4 = 24;
    public static final byte SOUND_EFF_CAI_BANG_4 = 25;
    public static final byte SOUND_EFF_CLICK = 26;
    public static final byte SOUND_EFF_QUICK_MENU = 27;
    public static final byte SOUND_EFF_TALK_NPC = 28;
    public static final byte SOUND_EFF_EVENT = 29;
    public static final byte SOUND_EFF_CAI_BANG_5 = 30;
    public static final byte SOUND_EFF_NGA_MI_5 = 31;
    public static final byte SOUND_EFF_VO_DANG_5 = 32;
    public static final byte SOUND_EFF_NGU_DOC_5 = 33;
    public static final byte SOUND_EFF_THIEU_LAM_5 = 34;
    public static SoundSystem[] music;
    public static SoundSystem[] sound;
    public static boolean isEnableSound = true;

    public static void init(int sizeMusic, int sizeSound) {
        music = new SoundSystem[sizeMusic];

        int i;
        for (i = 0; i < music.length; ++i) {
            music[i] = new SoundSystem(String.valueOf(i), true);
        }

        sound = new SoundSystem[sizeSound];

        for (i = 0; i < sound.length; ++i) {
            sound[i] = new SoundSystem(String.valueOf(i), false);
        }

        System.gc();
    }

    public static int getSoundPoolSource(int index, String fileName) {
        return index;
    }

    public static void playSound(int id, float volume) {
        if (GameCanvas.menuSelectitem.isTabFocus[0]) {
            if (sound != null && sound[id] != null) {
                try {
                    sound[id].play(volume);
                } catch (IllegalStateException var3) {
                    var3.printStackTrace();
                }
            }

        }
    }

    public static void SetLoopSound(int id, float volume, int loop) {
    }

    public static void UnSetLoopAll() {
    }

    public static void playMus(int id, float volume, boolean isLoop) {
        if (GameCanvas.menuSelectitem.isTabFocus[1]) {
            if (music != null) {
                for (int i = 0; i < music.length; ++i) {
                    if (music[i] != null && music[i].isPlaying() && i != id) {
                        music[i].pause();
                    }
                }
            }

            if (id >= 0 && id <= music.length - 1) {
                try {
                    music[id].setVolume(volume, volume);
                    music[id].setLooping(isLoop);
                    music[id].play(volume);
                    CurMusic = id;
                } catch (IllegalStateException var4) {
                    var4.printStackTrace();
                }

            }
        }
    }

    public static void pauseMusic(int id) {
    }

    public static void pauseCurMusic() {
        for (int i = 0; i < music.length; ++i) {
            if (music[i].isPlaying()) {
                music[i].pause();
                CurMusic = i;
            }
        }

    }

    public static void resumeMusic(int id) {
    }

    public static void resumeAll() {
    }

    public static void releaseAll() {
    }

    public static void stopAll() {
    }

    public static void stopSoundAll() {
        if (sound != null) {
            for (int i = 0; i < sound.length; ++i) {
                if (sound[i] != null) {
                    sound[i].stop();
                }
            }
        }

    }

    public static void setVolume(int id, int index, int soundVolume) {
    }
}

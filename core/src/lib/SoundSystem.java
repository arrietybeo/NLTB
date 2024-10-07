package lib;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundSystem {
    public Sound objSound;
    public Music objMusic;
    public boolean isLoop;

    public SoundSystem(String name, boolean isLoop) {
        this.isLoop = isLoop;
        if (name.contains(".ogg")) {
            throw new IllegalArgumentException("ONLY SUPPORT FILE.WAV AMD ");
        } else {
            try {
                if (isLoop) {
                    name = "res/music/" + name + ".mp3";
                    this.objMusic = Gdx.audio.newMusic(Gdx.files.internal(name));
                    this.objMusic.setLooping(true);
                } else {
                    name = "res/sound/" + name + ".wav";
                    this.objSound = Gdx.audio.newSound(Gdx.files.internal(name));
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            }

        }
    }

    public void play(float volume) {
        if (this.isLoop) {
            if (this.objMusic != null) {
                this.objMusic.setVolume(volume);
                this.objMusic.play();
            }
        } else if (this.objSound != null) {
            this.objSound.play(volume);
        }

    }

    public boolean isPlaying() {
        return this.objMusic == null ? false : this.objMusic.isPlaying();
    }

    public void pause() {
        if (this.isLoop) {
            if (this.objMusic != null) {
                this.objMusic.pause();
            }
        } else if (this.objSound != null) {
            this.objSound.pause();
        }

    }

    public void stop() {
        if (this.isLoop) {
            if (this.objMusic != null) {
                this.objMusic.stop();
            }
        } else if (this.objSound != null) {
            this.objSound.stop();
        }

    }

    public void setLooping(boolean isLoop2) {
        if (this.objMusic != null) {
            this.objMusic.setLooping(isLoop2);
        }

    }

    public void setVolume(float volume, float volume2) {
        if (this.objMusic != null) {
            this.objMusic.setVolume(volume2);
        }

    }
}

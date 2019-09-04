package ballblast.controller.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Enumeration representing the game sounds.
 */
public enum Sound {

    /**
     * The background music.
     */
    THEME("theme.wav"),

    /**
     * The shot sound.
     */
    SHOT("shot.wav"),

    /**
     * The bounce sound.
     */
    BOUNCE("bounce.wav"),

    /**
     * The powerup sound.
     */
    POWERUP("powerup.wav");

    private static final String PATH = "res/sound/";
    private Clip clip;

    Sound(final String fileName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(PATH + fileName));
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays the sound.
     */
    public void playSound() {
        clip.setFramePosition(0);
        clip.start();
    }

    /**
     * Stops the sound.
     */
    public void stopSound() {
        clip.stop();
    }

    /**
     * Loops the sound.
     */
    public void loopSound() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * Pre-loads the sounds.
     */
    public static void loadSounds() {
        values();
    }

}

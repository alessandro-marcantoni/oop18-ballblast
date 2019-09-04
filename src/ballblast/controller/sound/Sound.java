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

    /**
     * 
     */
    public static final SoundHandler HANDLER = new SoundHandler() {
        @Override
        public void handleSound(final SoundTypes sound) {
            switch (sound) {
            case BOUNCE:
                Sound.BOUNCE.playSound();
                break;
            case SHOT:
                Sound.SHOT.playSound();
                break;
            case POWERUP:
                Sound.SHOT.playSound();
                break;
            default:
                break;
            }
        }
    };

    Sound(final String fileName) {
        try {
            final AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(PATH + fileName));
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

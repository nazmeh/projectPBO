package gui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

// Kelas turunan MusicPlayer yang menggunakan Java Sound
public class SoundMusicPlayer extends MusicPlayer {
    private Clip clip;

    @Override
    protected void playMusic(String musicFile) {
        try {
            File file = new File(musicFile);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            isPlaying = false;
            System.out.println("Music stopped");
        }
    }

    @Override
    protected String getMusicFile() {
        return "assets/gameover.wav"; // Gantilah dengan path sesuai dengan file musik Anda
    }
}

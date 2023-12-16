package gui;

public abstract class MusicPlayer {
    protected boolean isPlaying;

    public MusicPlayer() {
        this.isPlaying = false;
    }

    // Metode abstrak yang harus diimplementasikan oleh kelas turunannya
    protected abstract void playMusic(String musicFile);

    // Metode abstrak untuk menghentikan pemutaran musik
    protected abstract void stopMusic();

    // Metode untuk memulai pemutaran musik
    public void start() {
        if (!isPlaying) {
            playMusic(getMusicFile());
            isPlaying = true;
            System.out.println("Music started");
        } else {
            playMusic(getMusicFile());
            System.out.println("Music is already playing");
        }
        
    }

    // Metode abstrak untuk mendapatkan file musik yang akan diputar
    protected abstract String getMusicFile();
}

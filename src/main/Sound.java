package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * The Sound class handles playing audio clips in a game.
 */
public class Sound {
	
	/** The audio clip. */
	Clip clip;
	
    /** An array of URLs for different sound files. */
	URL soundURL[] = new URL[10];

	/**
     * Constructs a Sound object and initializes sound file URLs.
     */
	public Sound() {
		// GAME MAIN MUSIC
		soundURL[0] = getClass().getResource("/sound/GameMainSong.wav");

		// KEY SOUND EFFECT
		soundURL[1] = getClass().getResource("/sound/KeySound.wav");

		// HIT SOUND ( COLLITION SOUND )
		soundURL[2] = getClass().getResource("/sound/hitSound.wav");

	}
	
	/**
     * Sets the audio file to be played based on the given index.
     *
     * @param i The index of the sound file.
     */
	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);

		} catch (Exception e) {

		}
	}
	
	/**
     * Starts playing the audio clip.
     */
	public void play() {
		clip.start();
	}
	
	/**
     * Loops the audio clip continuously.
     */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
     * Stops playing the audio clip.
     */
	public void stop() {
		clip.stop();
	}
}

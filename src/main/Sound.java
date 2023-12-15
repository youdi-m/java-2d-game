package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	//variables to store .wav files
	Clip clip;
	URL soundURL[] = new URL[30];
	
	//grabbing sound files
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/BlueBoyAdventure.wav");
		soundURL[1] = getClass().getResource("/sound/coin.wav");
		soundURL[2] = getClass().getResource("/sound/fanfare.wav");
		soundURL[3] = getClass().getResource("/sound/powerup.wav");
		soundURL[4] = getClass().getResource("/sound/unlock.wav");
	}
	
	//setting sound files
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		}catch(Exception e) {
			
		}
	}
	
	//function to play sound clip
	public void play() {
		
		clip.start();
	}
	
	//function to loop a clip (like bg music)
	public void loop() {
		
		clip.loop(clip.LOOP_CONTINUOUSLY);
	}
	
	//function to stop clip
	public void stop() {
		
		clip.stop();
	}
}

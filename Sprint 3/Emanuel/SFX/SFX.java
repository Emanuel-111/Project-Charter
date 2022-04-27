package SFX;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SFX {
	
	File file; // The name of the music file (EXAMPLE: "/Music/Menu Theme.wav")
	
	AudioInputStream audioStream; // Finds the music format (.wav) and length (minutes and seconds)
	
	Clip clip; // Starts and stops the music
	
	/***************************
	 * Plays a song using the path
	 * to where the music is.
	 * 
	 * @param sfx - sound effect 
	 **************************/
	public void playSFX(String sfx)
	{
		// Takes path and makes a file 
		try
		{
			file = new File(sfx);
			
			// Finds the format and length of the song
			audioStream = AudioSystem.getAudioInputStream(file);
			
			// Grabs the 
			clip = AudioSystem.getClip();
			
			// Music is placed in the clip
			clip.open(audioStream);
			
			// Clip now starts the song
			clip.start();
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Music is not working");
		}
	}
	/*
	 * Stops the sound effect when necessary
	 */
	public void stopSFX()
	{
		// Clips stops the music
		clip.stop();
		
		// Clip removes the song
		clip.close();
	}

}

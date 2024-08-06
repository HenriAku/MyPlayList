/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.MyPlayList.metier;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServiceMusic 
{
	private final String REP = "src/main/resources/music";
	private Clip  audioClip;
	private long  timeStop ;
	private long  timeMusic;
	private float dB       ;

	public ServiceMusic()
	{
		this.timeStop  = 0;
		this.timeMusic = 0;
		this.dB		   = (float) (Math.log(0.1) / Math.log(10) * 20); // 0.1 pour 10%
	}

	public long getTimeMusic() 			{return this.timeMusic;}
	public void setTimeMusic(long time) {this.timeMusic = time;}
	public void setDB       (float dB)  {this.dB 		= (float) (Math.log(dB/20) / Math.log(10) * 20)  ;}


	public List<String> readRep()
	{
		List<String> lstName = new ArrayList<String>();

		File musicDir = new File(this.REP);

		// Ajoute dans les fichier dans un tableau
		File[] musicFiles = musicDir.listFiles((dir, name) -> name.endsWith(".wav"));
		// Met le nom du fichier dans la list
		for (File file : musicFiles) 
			lstName.add(file.getName());
		
		return lstName;
	}

	public void readMusic(String name)
	{
		try {
			File audioFile = new File(this.REP + "/" + name);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);

			this.audioClip = (Clip) AudioSystem.getLine(info);
			this.audioClip.open(audioStream);

			FloatControl volumeControl = (FloatControl) this.audioClip.getControl(FloatControl.Type.MASTER_GAIN);
			volumeControl.setValue(this.dB);

			this.timeMusic = this.audioClip.getMicrosecondLength();
			this.audioClip.setMicrosecondPosition(this.timeStop);
			this.audioClip.start();

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public boolean pauseMusic()
	{
        if (this.audioClip != null && this.audioClip.isRunning()) 
		{
            this.timeStop = this.audioClip.getMicrosecondPosition();
            this.audioClip.stop();
			this.audioClip.close();
			return true;
        }
		return false;
    }

    public void stopMusic() 
	{
        if (this.audioClip != null) 
		{
            this.timeStop = 0;
            this.audioClip.stop();
            this.audioClip.close();
        }
    }
}

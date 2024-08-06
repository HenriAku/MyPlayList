/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.MyPlayList.metier;

import com.MyPlayList.metier.GestionFichier;
import com.MyPlayList.metier.ServiceMusic;
import com.MyPlayList.metier.Music;
import java.util.List;

import javax.swing.plaf.multi.MultiScrollBarUI;

import java.util.ArrayList;

public class Metier 
{
	private GestionFichier gestion   ;
	private ServiceMusic   service   ;
	private List<Music>    lstMusics ;
	private List<Music>    lstSave   ; 
	private String  	   musicCours;
	private boolean		   pause     ;

	public Metier()
	{
		this.gestion   = new GestionFichier  ();
		this.service   = new ServiceMusic    ();
		this.lstMusics = new ArrayList<Music>();
		this.lstSave   = new ArrayList<Music>();
		this.musicCours= "";
		this.pause     = false;
		
		this.lstMusics = this.gestion.loadMusics();
		this.setLstSave();
	}

	public List<Music> getLstMusics () {return lstMusics				  ;}
	public List<Music> getLstSave   () {return lstSave			          ;}
	public long 	   getTimeMusic () {return this.service.getTimeMusic();}
	public String      getMusicCours() {return this.musicCours            ;}
	public boolean     getPause     () {return this.pause				  ;}

	public void setTimeMusic (long time) {this.service.setTimeMusic(time);}
	public void setMusicCours(String m ) {this.musicCours = m			 ;}
	public void setPause     () 		 {this.pause      = false	     ;}
	public void setDB        (float dB ) {this.service.setDB(dB)         ;}


	public void saveMusics () {this.gestion.saveMusics             (this.lstMusics ) ;}
	public void readMusic  () {this.service.readMusic              (this.musicCours) ;}
	public void stopMusic  () {this.service.stopMusic              (               ) ;}
	public void pauseMusic () {this.pause = this.service.pauseMusic(               ) ;}



	public void setLstSave()
	{
		for (Music music : lstMusics) 
			this.lstSave.add(music);
	}

	public void resetLst()
	{
		this.lstMusics.clear();
		for (Music music : lstSave) 
			this.lstMusics.add(music);
	}

	public void readRep ()
	{
		List<String> titre = this.service.readRep();

		// Utilisez un ensemble pour un accès rapide
		List<String> titresExistants = new ArrayList<String>();
		for (Music music : this.lstMusics) 
			titresExistants.add(music.getTitre());

		for (String t : titre) 
		{
			if (!titresExistants.contains(t)) 
			{
				this.lstMusics.add(new Music(t, false));
				this.lstSave  .add(new Music(t, false));
				titresExistants.add(t); // Ajoutez ce titre à l'ensemble pour éviter les doublons
			}
		}
	}

	public void research(String name)
	{
		this.resetLst();
		if (name.length() != 0) 
		{
			for (int i = 0; i < this.lstMusics.size(); i++) 
			{
				if (!this.lstMusics.get(i).getTitre().toLowerCase().contains(name.toLowerCase()))
				{
					this.lstMusics.remove(i);
					i--;
				}
			}
		}
	}

	public boolean isLiked()
	{
		boolean like = false;
		for (Music music : lstMusics) 
		{
			if (music.getTitre().equals(this.musicCours))
				like = music.isLike();
		}
		return like;
	}

	public void changedLike(String titre)
	{
		for (Music music : lstSave) 
		{
			if (music.getTitre().equals(titre)) 
				music.setLike();
		}
		this.saveMusics();
	}
}

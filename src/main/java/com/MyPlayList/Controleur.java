package com.MyPlayList;

import com.MyPlayList.metier.*;
import com.MyPlayList.ihm.*;
import java.util.List;
import java.util.ArrayList;

public class Controleur 
{
	private Metier   metier;
	private FrameApp frameApp ;

	public Controleur()
	{
		this.metier    = new Metier  ()    ;
		this.frameApp  = new FrameApp(this);
	}

	public List<Music> getLstMusics () {return this.metier.getLstMusics ();}
	public long 	   getTimeMusic () {return this.metier.getTimeMusic ();}
	public String      getMusicCours() {return this.metier.getMusicCours();}
	public boolean 	   getPause     () {return this.metier.getPause     ();}
	public boolean     isLiked      () {return this.metier.isLiked      ();}

	public void setTimeMusic (long time)   {this.metier.setTimeMusic (time );}
	public void setDB		 (float dB )   {this.metier.setDB        (dB   );}
	public void setPause     () 		   {this.metier.setPause     (     );}
	public void setMusicCours(String m )   {this.metier.setMusicCours(m    );}
	public void changedLike  (String titre){this.metier.changedLike  (titre);}

	public void    saveMusics ()            {this.metier.saveMusics(    ) ;}
	public void    readMusic  () 			{this.metier.readMusic (    ) ;}
	public void    pauseMusic () 			{this.metier.pauseMusic(    ) ;}
	public void    stopMusic  () 			{this.metier.stopMusic (    ) ;}
	public void    readRep    () 			{this.metier.readRep   (    ) ;}
	public void    research   (String name) {this.metier.research  (name) ;}

	public void majInfo () {this.frameApp.majInfo ();}
	public void majTable() {this.frameApp.majTable();}

	public static void main(String[] args) 
	{
		new Controleur();
	}
}

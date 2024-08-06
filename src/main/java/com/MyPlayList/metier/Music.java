/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.MyPlayList.metier;

public class Music
{
	private static int nbMusic = 1;

	private int		num   ;
	private String  titre ;
	private boolean like  ;

	public Music(String titre, boolean like)
	{
		this.num    = Music.nbMusic++;
		this.titre  = titre ;
		this.like   = like  ;
	}

	/********************/
	/*		  Get		*/
	/********************/

	public int     getNum   () {return this.num   ;}
	public String  getTitre () {return this.titre ;}
	public boolean isLike   () {return this.like  ;}

	/********************/
	/*		  Set		*/
	/********************/

	public void setNum   (int     num   ) {this.num    = num   ;}
	public void setTitre (String  titre ) {this.titre  = titre ;}
	public void setLike  ()
	{
		if (this.like  == false) 
			this.like = true ;
		else
			this.like = false;
	}

	public String toString()
	{
		return 	"Titre  : " + this.titre  + "\n"+
				"num    : " + this.num    + "\n";
	}
	
}

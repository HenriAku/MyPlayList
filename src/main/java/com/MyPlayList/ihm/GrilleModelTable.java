package com.MyPlayList.ihm;

import com.MyPlayList.Controleur;
import com.MyPlayList.metier.Music;
import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GrilleModelTable extends AbstractTableModel
{
	private Controleur ctrl;
	private String[] tabEntete;
	private Object[][] taObjects;

	public GrilleModelTable(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.tabEntete = new String[] {"", "Titre", "Like"};
		this.taObjects = new Object[this.ctrl.getLstMusics().size()][3];

		List<Music> lstMusics = this.ctrl.getLstMusics();

		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("images/Play.png"));
		for (int i = 0; i < lstMusics.size(); i++) 
		{
			this.taObjects[i][0] = icon;
			this.taObjects[i][1] = lstMusics.get(i).getTitre();
			if (lstMusics.get(i).isLike()) 
				this.taObjects[i][2] = new ImageIcon(getClass().getClassLoader().getResource("images/like2.png"));
			else
				this.taObjects[i][2] = new ImageIcon(getClass().getClassLoader().getResource("images/like1.png"));
		}
	}

	public String[]   getTabEntetes ()                 { return this.tabEntete                 ; }
	public Object[][] getTabDonnees ()                 { return this.taObjects                 ; }
	public int        getColumnCount()                 { return this.tabEntete.length          ; } // Retourne le nombre de colonnes
	public int        getRowCount   ()                 { return this.taObjects.length          ; } // Retourne le nombre de lignes
	public String     getColumnName (int col)          { return this.tabEntete[col]            ; } // Retourne le nom de la colonne
	public Object     getValueAt    (int row, int col) { return this.taObjects[row][col]       ; } // Retourne l'objet à la ligne et colonne données
	public Class<?>   getColumnClass(int c) 		   { return getValueAt(0, c).getClass(); }
}

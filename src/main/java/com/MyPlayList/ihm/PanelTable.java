package com.MyPlayList.ihm;

import com.MyPlayList.Controleur;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.Color;

public class PanelTable extends JPanel
{
	private Controleur ctrl;
	private JTable table;

	public PanelTable(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		/*********************************/
		/*    Creation des composants    */
		/*********************************/

		JScrollPane spGrilleDonnees;

		this.table = new JTable(new GrilleModelTable(ctrl));
		this.table.setFillsViewportHeight(true);

		// Modifie la taille des cellules
		this.table.setRowHeight(50);
		this.table.getColumnModel().getColumn(0).setMaxWidth(40);
		this.table.getColumnModel().getColumn(1).setMaxWidth(300);
		this.table.getColumnModel().getColumn(2).setMaxWidth(40);

		this.table.setShowGrid(false); // Enlève le contour des cellules
		this.table.setIntercellSpacing(new Dimension(0, 0));
		this.table.setBorder(BorderFactory.createLineBorder(Color.black)); // Crée une bordure noire autour du tableau

		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e)
			{
				if (!e.getValueIsAdjusting()) {
					int row = table.getSelectedRow();
					int col = table.getSelectedColumn();

					if (col == 0 || col == 1) 
					{
						ctrl.stopMusic();
						ctrl.setMusicCours((String) table.getValueAt(row, 1));
						ctrl.readMusic();
						ctrl.majInfo();
					}

					if (col == 2) 
					{
						ctrl.getLstMusics().get(row).setLike();
						ctrl.majTable();
						ctrl.saveMusics();
					}
				}
			}
		});

		spGrilleDonnees = new JScrollPane(this.table);


		/*************************************/
		/*  Positionnement des composants    */
		/*************************************/
		this.add(spGrilleDonnees, BorderLayout.CENTER);
	}

	public void majTable() 
	{
		this.table.setModel(new GrilleModelTable(ctrl));
		this.table.setRowHeight(50);
		this.table.getColumnModel().getColumn(0).setMaxWidth(40);
		this.table.getColumnModel().getColumn(1).setMaxWidth(300);
		this.table.getColumnModel().getColumn(2).setMaxWidth(40);
	}
	public int getLigneSelectioner() { return this.table.getSelectedRow(); }
}

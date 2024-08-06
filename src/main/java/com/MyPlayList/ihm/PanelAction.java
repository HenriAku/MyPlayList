package com.MyPlayList.ihm;

import com.MyPlayList.ihm.FrameDrop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.MyPlayList.Controleur;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Color;

public class PanelAction extends JPanel implements ActionListener
{
	private Controleur ctrl ; 
	private FrameApp   frame;

	private JTextField txtResearch;
	private JButton    btnResearch;
	private JButton    btnAdd;

	public PanelAction(Controleur ctrl, FrameApp frame)
	{
		this.ctrl  = ctrl ;
		this.frame = frame;

		this.setLayout(new GridLayout(1,3));

		/*********************************/
		/*    Creation des composants    */
		/*********************************/
		this.txtResearch = new JTextField();
		this.txtResearch.setMaximumSize(new Dimension(300,32));

		this.btnResearch = new JButton(new ImageIcon(getClass().getResource("/images/Loupe.png")));
		this.btnResearch.setMaximumSize(new Dimension(32,32));
		this.btnResearch.setBorderPainted(false);
		this.btnResearch.setContentAreaFilled(false);
		this.btnResearch.setBackground(Color.WHITE);

		this.btnAdd = new JButton(new ImageIcon(getClass().getResource("/images/Add.png")));
		this.btnAdd.setMaximumSize(new Dimension(32,32));
		this.btnAdd.setBorderPainted(false);
		this.btnAdd.setContentAreaFilled(false);
		this.btnAdd.setBackground(Color.WHITE);

		/*********************************/
		/* Positionnement des composants */
		/*********************************/
		this.add(this.txtResearch);
		this.add(this.btnResearch);
		this.add(this.btnAdd     );

		/*********************************/
		/*   Activation des composants   */
		/*********************************/

		this.btnResearch.addActionListener(this);
		this.btnAdd     .addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnResearch) 
		{
			this.ctrl.research(this.txtResearch.getText());
			this.ctrl.majTable();
		}

		if (e.getSource() == this.btnAdd) 
		{
			new FrameDrop(this.ctrl);
		}
	}
}

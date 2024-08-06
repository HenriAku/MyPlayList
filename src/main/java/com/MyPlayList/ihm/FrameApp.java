package com.MyPlayList.ihm;

import com.MyPlayList.Controleur;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FrameApp extends JFrame
{
	private Controleur  ctrl      ;
	private PanelTable  panelTable;
	private PanelInfo   panelInfo ;
	private PanelAction panelA    ;

	public FrameApp(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle ("MyPlayList"   );
		this.setSize  (500, 470);
		this.setLayout(null         );

		/*********************************/
		/*    Creation des composants    */
		/*********************************/
		this.panelTable = new PanelTable (this.ctrl      );
		this.panelInfo  = new PanelInfo  (this.ctrl, this);
		this.panelA     = new PanelAction(this.ctrl, this);

		/*********************************/
		/* Positionnement des composants */
		/*********************************/
		this.panelA    .setBounds(80, 20 , 360, 32 ); 
		this.panelTable.setBounds(50 , 100, 380, 200); 
		this.panelInfo .setBounds(10 , 320, 450, 110); 

		this.add(this.panelA    );
		this.add(this.panelTable);
		this.add(this.panelInfo );

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible  (true         );
		this.setResizable(false);
	}

	public void majTable() {this.panelTable.majTable();}
	public void majInfo () {this.panelInfo.majInfo  ();}
}

package com.MyPlayList.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import com.MyPlayList.Controleur;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class PanelInfo extends JPanel implements ActionListener, AdjustmentListener
{
    private Controleur ctrl; 
    private FrameApp frame;

    private JButton    btnPlay;
    private JLabel     lblName;
    private JButton    btnLike;
    private JScrollBar sbSon  ;

    public PanelInfo(Controleur ctrl, FrameApp frame)
    {
        this.ctrl = ctrl;
        this.frame = frame;

        this.setLayout(new GridLayout(3, 1, 10, 10));

        /*********************************/
        /*    Creation des composants    */
        /*********************************/
        JPanel panelTop = new JPanel();
        panelTop.setLayout(new GridLayout(1, 3, 10, 10));

        JPanel panelSb  = new JPanel();
        panelSb.setLayout(new BoxLayout(panelSb, BoxLayout.X_AXIS));

        this.btnPlay = new JButton (new ImageIcon(getClass().getResource("/images/Play.png")));
        this.btnPlay.setMaximumSize(new Dimension(32, 32)                            );
        this.btnPlay.setBorderPainted    (false);
        this.btnPlay.setContentAreaFilled(false);
        this.btnPlay.setBackground(Color.WHITE);

        this.lblName = new JLabel  ("pas de titre",SwingConstants.CENTER);
        this.lblName.setMaximumSize(new Dimension(300, 32)      );

        this.btnLike = new JButton (new ImageIcon(getClass().getResource("/images/like1.png")));
        this.btnLike.setMaximumSize(new Dimension(32, 32)                             );
        this.btnLike.setBorderPainted    (false);
        this.btnLike.setContentAreaFilled(false);
        this.btnLike.setBackground(Color.WHITE);

        this.sbSon = new JScrollBar(JScrollBar.HORIZONTAL, 1, 1, 0, 21);
        Dimension sbSize = new Dimension(20, 15); 
        this.sbSon.setPreferredSize(sbSize);
        this.sbSon.setMinimumSize  (sbSize);
        this.sbSon.setMaximumSize  (sbSize);

        /*********************************/
        /* Positionnement des composants */
        /*********************************/
        panelTop.add(this.btnPlay);
        panelTop.add(this.lblName);
        panelTop.add(this.btnLike);
        panelSb .add(this.sbSon  );

        this.add(panelTop);
		this.add(new JLabel("Volume : ", SwingConstants.CENTER));
        this.add(panelSb);

        /*********************************/
        /*   Activation des composants   */
        /*********************************/
        this.btnPlay.addActionListener    (this);
        this.btnLike.addActionListener    (this);
		this.sbSon  .addAdjustmentListener(this);
    }

	public void adjustmentValueChanged(AdjustmentEvent e)
	{
		float res = (float) this.sbSon.getValue();
		if (!this.ctrl.getPause()) 
		{
			this.ctrl.setDB(res);
			this.actionPerformed(new ActionEvent(this.btnPlay, ActionEvent.ACTION_PERFORMED, null));
			this.actionPerformed(new ActionEvent(this.btnPlay, ActionEvent.ACTION_PERFORMED, null));
		}
	}

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == this.btnPlay) 
        {
            if (this.ctrl.getPause()) 
            {
                this.btnPlay.setIcon(new ImageIcon(getClass().getResource("/images/Play.png")));
                this.ctrl.setPause();
                this.ctrl.readMusic();
            }
            else
            {
                this.btnPlay.setIcon(new ImageIcon(getClass().getResource("/images/Pause.png")));
                this.ctrl.pauseMusic();
            }
            
        }

        if (e.getSource() == this.btnLike) 
        {
            if (this.ctrl.isLiked())
                this.btnLike.setIcon(new ImageIcon(getClass().getResource("/images/like1.png")));
            else
                this.btnLike.setIcon(new ImageIcon(getClass().getResource("/images/like2.png")));
            
            this.ctrl.changedLike(this.lblName.getText());
            this.frame.majTable();
			this.ctrl.saveMusics();

        }
    }

    public void majInfo()
    {
		this.ctrl.setPause();
        this.lblName.setText(this.ctrl.getMusicCours());
        if (this.ctrl.isLiked()) 
            this.btnLike.setIcon(new ImageIcon(getClass().getResource("/images/like2.png")));
        else
            this.btnLike.setIcon(new ImageIcon(getClass().getResource("/images/like1.png")));
		this.btnPlay.setIcon(new ImageIcon(getClass().getResource("/images/Play.png")));
	}
}

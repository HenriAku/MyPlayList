package com.MyPlayList.ihm;

import com.MyPlayList.Controleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import javax.swing.*;

public class FrameDrop extends JFrame implements DropTargetListener 
{
	private static final String REP = "src/main/resources/music/";
	private JLabel lblDrop;
	private Controleur ctrl;

	public FrameDrop(Controleur ctrl) 
	{
		this.ctrl = ctrl;

		this.setTitle("Add our Music");
		this.setSize(300,200);


		this.lblDrop = new JLabel("Déposez les fichiers ici", SwingConstants.CENTER);
		this.lblDrop .setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		this.add(this.lblDrop , BorderLayout.CENTER);

		new DropTarget(this, this);

		this.setVisible(true);
	}

	public void dragEnter(DropTargetDragEvent dtde) {dtde.acceptDrag(DnDConstants.ACTION_COPY);}

	public void dragOver         (DropTargetDragEvent dtde) {}
	public void dropActionChanged(DropTargetDragEvent dtde) {}
	public void dragExit         (DropTargetEvent     dte ) {}

	public void drop(DropTargetDropEvent dtde) 
	{
		try {
			Transferable transferable = dtde.getTransferable();     //Recupere les donner
			if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) 
			{
				dtde.acceptDrop(DnDConstants.ACTION_COPY);
				List<File> droppedFiles = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
				boolean allFilesAreWav = true; // Check si c'est des .wav

				for (File file : droppedFiles) 
				{
					if (!file.getName().toLowerCase().endsWith(".wav")) 
					{
						allFilesAreWav = false;
						JOptionPane.showMessageDialog(this, "Le fichier " + file.getName() + " n'est pas au format WAV et sera ignoré.");
					} 
					else 
					{
						// Déplacer chaque fichier WAV vers le répertoire cible
						File targetFile = new File(REP + file.getName());
						Files.copy(file.toPath(), targetFile.toPath());
					}
				}

				if (allFilesAreWav) 
					this.lblDrop.setForeground(Color.GREEN);

				dtde.dropComplete(true);
			} 
			else 
			{
				this.lblDrop.setForeground(Color.RED);
				dtde.rejectDrop();
			}
			this.ctrl.readRep();
			this.ctrl.majTable();
			this.ctrl.saveMusics();
		} catch (Exception ex) {
			ex.printStackTrace();
			dtde.dropComplete(false);
		}
	}
}

/*******************************************/
/* 				@author Henri 			   */ 
/*				@Alias  Aku				   */
/*				@date  23/07/2024		   */
/*******************************************/
package com.MyPlayList.metier;

import com.MyPlayList.metier.Music;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

public class GestionFichier 
{
	private final String FILE = "src/main/resources/Save.json";

	public List<Music> loadMusics()
	{
		Gson gson = new Gson();
		try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("/Save.json")))
		{
			return gson.fromJson(reader, new TypeToken<List<Music>>(){}.getType()); //lis le fichier
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void saveMusics(List<Music> lstM)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); // Utilisation de GsonBuilder pour une meilleure lisibilité

        try (Writer writer = new FileWriter(this.FILE)) 
        {
            gson.toJson(lstM, writer);   //ecrit les oeuvre grace au get et attrribut
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Erreur lors de la sauvegarde des oeuvres : " + e.getMessage());
        } catch (JsonIOException e) {
            e.printStackTrace();
            System.err.println("Erreur JSON lors de la sauvegarde des oeuvres : " + e.getMessage());
        }
	}
}

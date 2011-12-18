package btlshp.turns;

import java.io.File;
import java.io.IOException;

import btlshp.entities.Map;
import java.io.*;
public abstract class Turn {
	public long writeOut(String filePath){
		ObjectOutputStream objOut = null;
		FileOutputStream fileOut = null;
		File f = null;
		try {
			fileOut = new FileOutputStream(filePath +"/game.ser");
			objOut = new ObjectOutputStream(fileOut);
			
			objOut.writeObject(this);
			objOut.close();
			f = new File(filePath+"/game.ser");
			
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
		
		return f.lastModified();
	}
	public abstract void setMap(Map m);
	/**
	 * @returns true if the move object represents a successful move, false otherwise.
	 */
	abstract boolean wasSuccessful();

	/**
	 * Executes a given move object representing a move from the other player.
	 * @throws IllegalStateException If the turn was not successful.
	 */
	public abstract void executeTurn();
}

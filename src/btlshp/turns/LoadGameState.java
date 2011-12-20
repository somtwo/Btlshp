package btlshp.turns;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import btlshp.entities.Map;

class LoadGameState extends Turn implements Serializable{

	private static final long serialVersionUID = 4870988534077315987L;
	private String filePath;
	
	/**
	 * 
	 * @param f location of saved game file
	 */
	public LoadGameState(String f){
		filePath = f;
	}
	
	@Override
	public void executeTurn(Map map) {
			FileInputStream fileIn = null;
			ObjectInputStream objIn = null;
			try{
				fileIn = new FileInputStream(filePath);
				objIn = new ObjectInputStream(fileIn);
				fileIn.close();
			}catch(IOException e){
				System.err.println(e.getMessage());
			}
			
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Implementation IO dependent
		return true;
	}
	@Override
	public String toString() {
		return "loadGameState";
	}
}
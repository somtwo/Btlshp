package btlshp.turns;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import btlshp.entities.Map;

class SaveGameState extends Turn implements Serializable{
	
	private static final long serialVersionUID = 2354631655310067958L;
	private Map saveGame;
	
	public SaveGameState(Map map)
	{
		saveGame = map;
	}
	@Override
	public void executeTurn()  {
		// TODO Auto-generated method stub
		ObjectOutputStream objOut = null;
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("..Dropbox/Btlshp/game.dat");
			objOut = new ObjectOutputStream(fileOut);
			
			objOut.writeObject(saveGame);
			objOut.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String toString(){
		return "saveGameState";
	}
	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
	
}
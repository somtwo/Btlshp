package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Map;

class JoinGame extends Turn implements Serializable{
	private static final long serialVersionUID = 6638148998058324549L;
	public JoinGame(){
	}
	
	@Override
	public void executeTurn(Map map) {
		// This doesn't need to do anything
	}

	@Override
	public boolean wasSuccessful() {
		return true;
	}
}
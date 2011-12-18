package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Map;

class JoinGame extends Turn implements Serializable{
	private Map m;
	private static final long serialVersionUID = 6638148998058324549L;
	public JoinGame(Map m){
		this.m = m;
	}
	@Override
	public void executeTurn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasSuccessful() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setMap(Map m) {
		// TODO Auto-generated method stub
		
	}
	
}
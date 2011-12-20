package btlshp.turns;

import java.io.Serializable;

import btlshp.entities.Map;

class Pass extends Turn implements Serializable{
	private static final long serialVersionUID = 3359745261868508357L;

	@Override
	public void executeTurn(Map map) {//Does no work
	}
	
	@Override
	public boolean wasSuccessful() {
		//Always returns true because a pass turn cannot fail
		return true;
	}
	
	@Override
	public String toString(){
		return "pass";
	}
}
package btlshp.entities;

public class Base extends Construct {
	
	Player         pl;
	
	/**
	* Constructor for Base
	* Returns the Base Constructed
	* @param owner   Player the base belongs to.
	* @param blocks  
	*/
	Base(Player owner, ConstructBlock blocks[]) {
		
	}
	
	/**
	* Determines if this base is in condition to conduct repairs
	* @return
	*/
	boolean canRepair() {
		return false;
	}


	Base(Player owner) {
	}
}

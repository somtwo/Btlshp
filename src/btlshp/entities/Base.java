package btlshp.entities;

public class Base extends Construct {
	/**
	* Determines if this base is in condition to conduct repairs
	* @return
	*/
	boolean canRepair() {
		return true;
	}

	/**
	* Constructor for Base
	* Returns the Base Constructed
	* @param owner   Player the base belongs to owner.
	*/
	Base(Player owner) {
	}
}

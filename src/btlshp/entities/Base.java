package btlshp.entities;

public class Base extends Construct {
	/**
	* Determines if this base is in condition to conduct repairs
	* @return
	*/
	boolean canRepair() {
		return false;
	}

	/**
	* Constructor for Base
	* Returns the Base Constructed
	* @param owner   Player the base belongs to.
	*/
	Base(Player owner) {
	}
}

package btlshp.enums;


public enum GraphicId {
	None(""),
	Mine("mine"),
	Reef("reef"),
	Ship("ship"),
	Base("base"),
	ArmoredShip("armr"),
	DestroyedShip("dams"),
	DestroyedBase("damb");
	
	private final String name;
	
	GraphicId(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}

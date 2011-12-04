package btlshp.enums;

public enum GraphicAlliance {
	Friendly("f"),
	Unfriendly("u"),
	Neutral("");
	
	private final String suffix;
	GraphicAlliance(String suffix) {
		this.suffix = suffix;
	}
	
	public String getSuffix() {
		return suffix;
	}
}

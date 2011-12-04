package btlshp.enums;

public enum GraphicPart {
	Head("1"),
	Tail("3"),
	Middle("2"),
	None("");
	
	private final String suffix;
	GraphicPart(String suffix) {
		this.suffix = suffix;
	}
	
	public String getSuffix() {
		return suffix;
	}
}

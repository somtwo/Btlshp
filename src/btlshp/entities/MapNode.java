package btlshp.entities;

public class MapNode {
	public static enum NodeFlag {
		HasRadar(0x01),
		HasSonar(0x02);
		
		private final int flag;
		NodeFlag(int f) {
			this.flag = f;
		}
		
		public boolean isChecked(int flags) {
			return (flags & flag) == flag;
		}
		
		public int check(int flags) {
			return flags | flag;
		}
		
		public int uncheck(int flags) {
			return flags & (~flag);
		}
	}
	
	
	public  Block block;
	private int   flags;
	
	public MapNode() {			//changed to public for MapTest.java Junit test
		clearFlags();
	}
	
	public void clearFlags() {
		flags = 0;
	}
	
	
	public boolean hasRadar() {
		return NodeFlag.HasRadar.isChecked(flags);
	}
	
	
	public boolean hasSonar() {
		return NodeFlag.HasSonar.isChecked(flags);
	}
	
	
	public void setHasRadar(boolean hasRadar) {
		if(hasRadar)
			flags = NodeFlag.HasRadar.check(flags);
		else
			flags = NodeFlag.HasRadar.uncheck(flags);
	}
	
	
	public void setHasSonar(boolean hasSonar) {
		if(hasSonar)
			flags = NodeFlag.HasSonar.check(flags);
		else
			flags = NodeFlag.HasSonar.uncheck(flags);
	}
}

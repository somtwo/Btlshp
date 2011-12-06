package btlshp.entities;

import java.io.Serializable;

public class MapNode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -723816002578041567L;

	public static enum NodeFlag {
		HasRadar(0x01),
		HasSonar(0x02),
		HasExplosion(0x04),
		ActionArea(0x08),
		ActionSquare(0x10),
		BadAction(0x20);
		
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
		
		
		public int setChecked(boolean checked, int flags) {
			if(checked)
				return flags | flag;
			else
				return flags & ~flag;
		}
		
		public int uncheck(int flags) {
			return flags & (~flag);
		}
	}
	
	
	public  Block block;
	private int   flags;
	
	public MapNode() {
		flags = 0;
	}
	
	public void clearFlags() {
		boolean explosion = NodeFlag.HasExplosion.isChecked(flags);
		
		flags = 0;
		if(explosion) {
			flags = NodeFlag.HasExplosion.check(flags);
		}
	}
	
	
	public boolean hasRadar() {
		return NodeFlag.HasRadar.isChecked(flags);
	}
	
	public boolean hasSonar() {
		return NodeFlag.HasSonar.isChecked(flags);
	}
	
	public boolean hasExplosion() {
		return NodeFlag.HasExplosion.isChecked(flags);
	}
	
	public boolean actionArea() {
		return NodeFlag.ActionArea.isChecked(flags);
	}
	
	public boolean actionSquare() {
		return NodeFlag.ActionSquare.isChecked(flags);
	}
	
	public boolean badAction() {
		return NodeFlag.BadAction.isChecked(flags);
	}
	
	
	public void hasRadar(boolean checked) {
		flags = NodeFlag.HasRadar.setChecked(checked, flags);
	}
	
	public void hasSonar(boolean checked) {
		flags = NodeFlag.HasSonar.setChecked(checked, flags);
	}
	
	public void hasExplosion(boolean checked) {
		flags = NodeFlag.HasExplosion.setChecked(checked, flags);
	}
	
	public void actionArea(boolean checked) {
		flags = NodeFlag.ActionArea.setChecked(checked, flags);
	}
	
	public void actionSquare(boolean checked) {
		flags = NodeFlag.ActionSquare.setChecked(checked, flags);
	}
	
	public void badAction(boolean checked) {
		flags = NodeFlag.BadAction.setChecked(checked, flags);
	}
}

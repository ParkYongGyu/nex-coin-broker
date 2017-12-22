package net.nexg.exchange.communication.message;

public class Layout {

	public Layout() {
		
	}
	
	private String key;
	private int index;
	private int length;
	private String type;
	private boolean fill;
	private String fillchar;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isFill() {
		return fill;
	}
	public void setFill(boolean fill) {
		this.fill = fill;
	}
	public String getFillchar() {
		return fillchar;
	}
	public void setFillchar(String fillchar) {
		this.fillchar = fillchar;
	}
	
	

}

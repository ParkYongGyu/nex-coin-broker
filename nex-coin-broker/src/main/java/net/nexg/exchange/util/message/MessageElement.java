package net.nexg.exchange.util.message;


public class MessageElement {

	public static final int  CHAR 		= 1;
	public static final int  NUMERIC 	= 2;
	 
	private int dataType;
	private int 	length;
	private String	content;
	private String  keyword; // �� �޼����� IDENTITY �� ����Ű�� Ű ( �� : I002_RES_ACCOUNT_NO   - I002 �������� ���¹�ȣ )
	private int      msgIdx = -1;  
	private int 	offset = -1;
	
	public MessageElement(String keyword, int msgTypeConstant, int bLength) {
		this.dataType = msgTypeConstant;
		this.length = bLength;
		this.keyword = keyword;
	}
	
	public MessageElement(String keyword, int msgTypeConstant, int bLength, String content) throws Exception{
		this.dataType = msgTypeConstant;
		this.length = bLength;
		this.keyword = keyword;
		setContent(content);
	}
	
	public MessageElement(int msgTypeConstant, int bLength) {
		this.dataType = msgTypeConstant;
		this.length = bLength;
	}
	
	public MessageElement(int msgTypeConstant, int bLength, String content) throws Exception{
		this.dataType = msgTypeConstant;
		this.length = bLength;
		setContent(content);
	}
	
	public int getDataType(){
		return dataType;
	}
	
	public int getSize(){
		return length;
	}
	
	public void setContent(String str) throws Exception{
		if(str == null) str = ""; //throw new NullPointerException("Null ���� ���Խ��ϴ�."); 
		if(getDataType() == this.CHAR)
			this.content = MessageUtil.getNonNumericData(str, getSize());
		else if(getDataType() == this.NUMERIC)
			this.content = MessageUtil.getNumericData(str, getSize());
		else{
			throw new Exception("���ǵ��� ���� ������� Ÿ���Դϴ�.");
		}
	}
	
	public String getContent(){
		return content;
	}

	public String getKeyword() {
		return keyword == null ? "" : keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	/**
	 * @return the msgIdx
	 */
	protected int getMsgIdx() {
		return msgIdx;
	}

	/**
	 * @param msgIdx the msgIdx to set
	 */
	protected void setMsgIdx(int msgIdx) {
		this.msgIdx = msgIdx;
	}
	
	/**
	 * @return the offset
	 */
	protected int getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	protected void setOffset(int offset) {
		this.offset = offset;
	}
	
}
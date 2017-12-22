package net.nexg.exchange.util.message;


import java.util.ArrayList;
import java.util.Hashtable;


public class Message{ 
	private int orgMessageSize = 0;
	private int inputMessageSize = 0;
	private StringBuffer contentBuffer = new StringBuffer();
	private MessageElement[] elements = null;	
	private Hashtable keyset = new Hashtable();
	
	private boolean modifyFlag = false;
	
	public Message(int messageTotalLength, MessageElement[] props) throws Exception{
		try{
			this.orgMessageSize = messageTotalLength;													//정의한 메세지의 전체 길이 입력
			elements = props;
			init();
			setData();
			validate();
		}catch(NullPointerException npe){
			npe.printStackTrace();
			throw new NullPointerException(npe.getMessage());
		}catch(Exception e){
			throw e;
		}
	}
	private void init() throws Exception {
		for(int i = 0; i < elements.length; i++){
			if(!elements[i].getKeyword().equals("")){
				if(keyset.containsKey(elements[i].getKeyword())){
					throw new Exception("이미 정의된 키값입니다. 키워드를 변경하여 주십시요.");
				}
				keyset.put(elements[i].getKeyword(), "1");
			}
		}
	}
	
	private void setData() throws Exception {
		inputMessageSize = 0;
		contentBuffer.setLength(0);
		
		for(int i = 0; i < elements.length; i++){
			
			if(elements[i] == null)				throw new NullPointerException("메세지 구성요소가 정의되지 않았습니다.");
			
			//if(elements[i].getContent() == null) 	throw new NullPointerException("메세지 구성요소의 내용이 정의되지 않았습니다.");
			//091020 메세지 초기화 시 값이 없어도 셋팅이 가능하도록 변경.
			if(elements[i].getContent() == null) 	elements[i].setContent("");
			
			elements[i].setOffset(inputMessageSize);
			inputMessageSize += elements[i].getContent().getBytes().length;							//들어온 메세지의 길이 체크
			
			contentBuffer.append(elements[i].getContent());
		}	
	}
	
	public void validate() throws Exception{
		if(orgMessageSize != inputMessageSize)
			throw new Exception("설정된 메세지 길이("+ orgMessageSize +")와 입력된 메세지 길이("+ inputMessageSize +")가 같지 않습니다.");		
	}
	
	public String getCompletedContent() throws Exception {
		if(modifyFlag) {
			setData();
			modifyFlag = false;
		}
		
		return contentBuffer.toString();
	}
	
	public MessageElement[] getElements(){
		return elements;
	}
	
	public String getContent(int idx){
		return elements[idx].getContent();		
	}
	
	public String getContent(String keyword){
		return getMessageElementByName(keyword).getContent();		
	}
	
	public void setContent(int idx, String value) throws Exception{
		modifyFlag = true;
		elements[idx].setContent(value);
	}
	
	public void setContent(String keyword, String value) throws Exception {
		modifyFlag = true;
		getMessageElementByName(keyword).setContent(value);
	}
	
	public String getConvertContent(int idx){
		if(elements[idx].getDataType() == MessageElement.NUMERIC)
			return Long.toString(Long.parseLong(elements[idx].getContent()));
		else{
			return elements[idx].getContent().trim();
		}
	}
	
	public String getConvertContent(String keyword){
		MessageElement tmp = getMessageElementByName(keyword);
		if(tmp.getDataType() == MessageElement.NUMERIC)
			return Long.toString(Long.parseLong(tmp.getContent()));
		else{
			return tmp.getContent().trim();
		}	
	}
	
	public static Message getMessageFromString(String readData, MessageElement[] mes) throws Exception{
		int offset = 0;
		for(int i = 0; i < mes.length; i++){
			mes[i].setContent(MessageUtil.cutByte(readData.getBytes(), offset, mes[i].getSize()));
			offset += mes[i].getSize();
		}
				
		return new Message(offset, mes);
	}
	
	private MessageElement getMessageElementByName(String mes_key) {
		for(int i = 0; i < elements.length; i++){
			if(elements[i].getKeyword().equals(mes_key)){
				elements[i].setMsgIdx(i);
				return elements[i];
			}
		}
		return null;
	}
	
	private int getMessageElementIdxByName(String mes_key) {
		for(int i = 0; i < elements.length; i++){
			if(elements[i].getKeyword().equals(mes_key)){
				return i;
			}
		}
		return -1;
	}
	
	/*
	public Record toRecord() throws Exception {
		Record result = new Record();
		for(int i = 0; i < elements.length; i++){
			if(elements[i].getKeyword().equals("")) throw new Exception("키워드가 정의되지 않은 메세지는 Record 화 할 수 없습니다.");
			result.set(elements[i].getKeyword(), elements[i].getContent());
		}
		return result;
	}
	*/
	
	/**
	 * 메세지 스트럭처에서 정의된 메세지의 길이를 알아내기 위한 메소드.
	 * @param props
	 * @return
	 */
	public static int getElementsTotalLength(MessageElement[] props){
		int result = 0;
		for(int i = 0; i < props.length; i++){
			result += props[i].getSize();
		}
		return result;
	}
	
	public String toString() {
		return toString(0);
	}
	
	public String toString(int indent) {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getName() + "@toString(){\n");
		String indent_str = null;
		try{
			indent_str = MessageUtil.getNonNumericData("", indent);
		}catch(Exception e){
			indent_str = "";
		}
		for(int i = 0; i < this.getElements().length; i++) {
			
			sb.append(indent_str);
			sb.append("IDX." + i + " : " );
			sb.append("SIZE[" + this.getElements()[i].getSize() + "]");
			sb.append(",\t OFFSET[" + this.getElements()[i].getOffset() + "]");
			sb.append("\t KEY["+ elements[i].getKeyword() +"] => ["+ this.getContent(i) +"]");
			sb.append("\n");
		}
		sb.append("}\n");
		return sb.toString(); 
	}
	
	


}

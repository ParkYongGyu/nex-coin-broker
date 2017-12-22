package net.nexg.exchange.communication.message;

import java.util.ArrayList;
import java.util.List;

public class Protocol {

	public Protocol() {
		
	}

	private String command;
	private int target_instance_id;
	private List<Layout> layout_list = new ArrayList<Layout>();
	
	

	public String getCommand() {
		return command;
	}


	public void setCommand(String command) {
		this.command = command;
	}


	public int getTarget_instance_id() {
		return target_instance_id;
	}


	public void setTarget_instance_id(int target_instance_id) {
		this.target_instance_id = target_instance_id;
	}


	public List<Layout> getLayout_list() {
		return layout_list;
	}


	public void setLayout_list(List<Layout> layout_list) {
		this.layout_list = layout_list;
	}
	
	
}

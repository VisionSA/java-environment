package com.bizitglobal.tarjetafiel.planificadoremail.negocio;

public class WspEnvio {
	
	private String chatPlatform;
	private String	chatChannelNumber;
	private String platformContactId;
	private String	ruleNameOrId;
	private Contacts params;
	
	
	public String getChatPlatform() {
		return chatPlatform;
	}
	public void setChatPlatform(String chatPlatform) {
		this.chatPlatform = chatPlatform;
	}
	public String getChatChannelNumber() {
		return chatChannelNumber;
	}
	public void setChatChannelNumber(String chatChannelNumber) {
		this.chatChannelNumber = chatChannelNumber;
	}
	public String getPlatformContactId() {
		return platformContactId;
	}
	public void setPlatformContactId(String platformContactId) {
		this.platformContactId = platformContactId;
	}
	public String getRuleNameOrId() {
		return ruleNameOrId;
	}
	public void setRuleNameOrId(String ruleNameOrId) {
		this.ruleNameOrId = ruleNameOrId;
	}
	public Contacts getParams() {
		return params;
	}
	public void setParams(Contacts params) {
		this.params = params;
	}
	

}

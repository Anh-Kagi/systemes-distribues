package fr.polytech.projet.naturalthescattering.controller.results;

public class GenericResult {
	private boolean success;
	private String reason;
	
	public GenericResult() {
		this(false, "");
	}
	
	public GenericResult(boolean success) {
		this(success, "");
	}
	
	public GenericResult(boolean success, String reason) {
		setSuccess(success);
		setReason(reason);
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public boolean getSuccess() {
		return this.success;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getReason() {
		return this.reason;
	}
}

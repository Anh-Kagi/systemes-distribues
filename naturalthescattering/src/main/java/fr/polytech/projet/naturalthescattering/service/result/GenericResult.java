package fr.polytech.projet.naturalthescattering.service.result;

public class GenericResult<S extends Enum<S> & GenericStatus> {
	private S status;
	
	public GenericResult() {
		this(null);
	}
	
	public GenericResult(S status) {
		setStatus(status);
	}
	
	public void setStatus(S status) {
		this.status = status;
	}
	
	public S getStatus() {
		return this.status;
	}
}

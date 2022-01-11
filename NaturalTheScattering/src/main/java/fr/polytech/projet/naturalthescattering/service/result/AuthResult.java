package fr.polytech.projet.naturalthescattering.service.result;

import org.springframework.http.HttpStatus;

public class AuthResult {
	public static enum RegisterStatus implements GenericStatus {
		OK(HttpStatus.CREATED),
		EMPTY_USERNAME(HttpStatus.BAD_REQUEST),
		EMPTY_PASSWORD(HttpStatus.BAD_REQUEST),
		EMPTY_USERNAME_AND_PASSWORD(HttpStatus.BAD_REQUEST),
		USERNAME_TAKEN(HttpStatus.FORBIDDEN);
		
		private HttpStatus status;
		
		private RegisterStatus(HttpStatus status) {
			this.status = status;
		}
		
		public HttpStatus getStatus() {
			return this.status;
		}
	}
	public static class Register extends GenericResult<RegisterStatus> {}
}

package fr.polytech.projet.naturalthescattering.service.result;

import org.springframework.http.HttpStatus;

public abstract class SelfResult {
	public static enum ChMdpStatus implements GenericStatus {
		OK(HttpStatus.OK),
		PASSWORDS_DONT_MATCH(HttpStatus.BAD_REQUEST);

		private HttpStatus status;
		
		private ChMdpStatus(HttpStatus status) {
			this.status = status;
		}
		
		@Override
		public HttpStatus getStatus() {
			return this.status;
		}
	}
	
	public static class ChMdp extends GenericResult<ChMdpStatus> {}
	
	public static enum DeleteStatus implements GenericStatus {
		OK(HttpStatus.OK),
		PASSWORD_INVALID(HttpStatus.FORBIDDEN);
		
		private HttpStatus status;

		private DeleteStatus(HttpStatus status) {
			this.status = status;
		}
		
		@Override
		public HttpStatus getStatus() {
			return this.status;
		}
	}
	
	public static class Delete extends GenericResult<DeleteStatus> {}
}

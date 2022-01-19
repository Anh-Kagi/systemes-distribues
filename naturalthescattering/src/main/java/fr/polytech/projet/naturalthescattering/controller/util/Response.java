package fr.polytech.projet.naturalthescattering.controller.util;

import org.springframework.http.ResponseEntity;

import fr.polytech.projet.naturalthescattering.service.result.GenericResult;
import fr.polytech.projet.naturalthescattering.service.result.GenericStatus;

public abstract class Response {
	public static <T extends GenericResult<E>, E extends Enum<E> & GenericStatus> ResponseEntity<T> create(T result) {
		return new ResponseEntity<T>(result, result.getStatus().getStatus());
	}
}

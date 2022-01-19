package fr.polytech.projet.naturalthescattering.service.result;

import org.springframework.http.HttpStatus;

public abstract interface GenericStatus {
	public HttpStatus getStatus();
}
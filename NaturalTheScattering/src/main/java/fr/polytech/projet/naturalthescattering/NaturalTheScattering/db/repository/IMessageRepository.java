package fr.polytech.projet.naturalthescattering.NaturalTheScattering.db.repository;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.projet.naturalthescattering.NaturalTheScattering.db.Message;

public interface IMessageRepository extends CrudRepository<Message, Long> {
}

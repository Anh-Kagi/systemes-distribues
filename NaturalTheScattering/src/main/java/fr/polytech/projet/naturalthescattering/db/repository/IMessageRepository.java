package fr.polytech.projet.naturalthescattering.db.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.polytech.projet.naturalthescattering.db.Message;
import fr.polytech.projet.naturalthescattering.db.Thread;

public interface IMessageRepository extends JpaRepository<Message, Long> {
	public Stream<Message> findByThreadOrderByDateAsc(Thread thread);
}

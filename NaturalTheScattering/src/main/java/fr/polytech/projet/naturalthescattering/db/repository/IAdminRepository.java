package fr.polytech.projet.naturalthescattering.db.repository;

import org.springframework.data.repository.CrudRepository;

import fr.polytech.projet.naturalthescattering.db.Admin;

public interface IAdminRepository extends CrudRepository<Admin, Long> {
}

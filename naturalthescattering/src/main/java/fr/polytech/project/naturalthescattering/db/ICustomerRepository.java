package fr.polytech.project.naturalthescattering.db;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
	List<Customer> findByLastName(String lastName);
	Customer findById(long id);
}
package fr.polytech.project.naturalthescattering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import fr.polytech.project.naturalthescattering.db.Customer;
import fr.polytech.project.naturalthescattering.db.Gender;
import fr.polytech.project.naturalthescattering.db.ICustomerRepository;

@SpringBootApplication
public class NaturalTheScatteringApplication 
{
	@Autowired
	private ICustomerRepository repository;
	private static final Logger log = LoggerFactory.getLogger(NaturalTheScatteringApplication.class);
	
	@Bean
	public CommandLineRunner demo(RepositoryRestConfiguration config) {
		return (args) -> {
			config.exposeIdsFor(Customer.class);
			
			// save a few customers
			repository.save(new Customer("Jack", "Bauer", Gender.Male));
			repository.save(new Customer("Chloe", "O'Brian", Gender.Female));
			repository.save(new Customer("Kim", "Bauer", Gender.Female));
			repository.save(new Customer("David", "Palmer", Gender.Male));
			repository.save(new Customer("Michelle", "Dessler", Gender.Female));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findById(1L);
			log.info("Customer found with findById(1L):");
			log.info("--------------------------------");
			log.info(customer == null ? "none" : customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
			
			//repository.deleteAll();
			//log.info("deleted all");
		};
	}
	
    public static void main( String[] args )
    {
    	SpringApplication.run(NaturalTheScatteringApplication.class, args);
    }
}

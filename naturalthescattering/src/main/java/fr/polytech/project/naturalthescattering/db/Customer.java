package fr.polytech.project.naturalthescattering.db;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private String firstName;
  private String lastName;
  @Enumerated(EnumType.STRING)
  private Gender gender;

  protected Customer() {}

  public Customer(String firstName, String lastName, Gender gender) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.gender = gender;
  }

  @Override
  public String toString() {
    return String.format(
        "Customer[id=%d, firstName='%s', lastName='%s', gender='%s']",
        id, firstName, lastName, gender);
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
  
  public Gender getGender() {
	  return gender;
  }
}
package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findByLastNameShouldReturnStudent() throws Exception {
        this.entityManager.persist(new Customer("Erik", "Carlsten", "eshizzle@foo.bar"));
        Customer customer = this.customerRepository.findByLastName("Carlsten");
        assertThat(customer.getFirstName()).isEqualTo("Erik");
        assertThat(customer.getLastName()).isEqualTo("Carlsten");
    }

    @Test
    public void findByLastNameShouldReturnNullWhenNoStudent() throws Exception {
        this.entityManager.persist(new Customer("Erik", "Carlsten", "eshizzle@foo.bar"));
        Customer customer = this.customerRepository.findByLastName("Stuber");
        assertThat(customer).isNull();
    }

    @Test
    public void findAllByOrderByLastNameShouldReturnListSortedByLastName() throws Exception {
        entityManager.persist(new Customer("Captain", "Solo", "fake2@fro.boz"));
        entityManager.persist(new Customer("Captain", "Hook", "fake@fro.boz"));

        List<Customer> customers = customerRepository.findAllByOrderByLastName();
        assertThat(customers.size()).isEqualTo(2);

        Customer firstCustomer = customers.get(0);
        Customer secondCustomer = customers.get(1);

        assertThat(firstCustomer.getLastName()).isEqualTo("Hook");
        assertThat(secondCustomer.getLastName()).isEqualTo("Solo");
    }

    @Test
    public void findAllByOrderByLastNameShouldReturnEmptyListWhenNoDataExists() throws Exception {
        List<Customer> customers = customerRepository.findAllByOrderByLastName();
        assertThat(customers).isEmpty();
    }

}
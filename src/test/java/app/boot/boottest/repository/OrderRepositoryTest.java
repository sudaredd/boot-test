package app.boot.boottest.repository;

import app.boot.boottest.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Container
    static PostgreSQLContainer database = new PostgreSQLContainer("postgres:12")
        .withDatabaseName("springboot")
        .withUsername("springboot")
        .withPassword("springboot");

    @DynamicPropertySource
    static void setDataSourceProperty(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
    }

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void findAllContainingMacBookPro() {

        orderRepository.save(createOrder("42", """
               [{"name": "MacBook Pro", "amount" : 1042}, {"name": "iPhone Pro", "amount" : 42}]
            """));

        orderRepository.save(createOrder("43", """
               [{"name": "Kindle", "amount" : 13}, {"name": "MacBook Pro", "amount" : 1000}]
            """));

        orderRepository.save(createOrder("44", "[]"));

        var orders = orderRepository.findAllContainingMacBookPro();

        assertEquals(2, orders.size());
    }

    private Order createOrder(String trackingNumber, String items) {
        return new Order(trackingNumber, items);
    }

    @Test
    void findAllByTrackingNumber() {
    }
}
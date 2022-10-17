package app.boot.boottest.repository;

import app.boot.boottest.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(properties = {
    "spring.test.database.replace=NONE",
    "spring.datasource.url=jdbc:tc:postgresql:12:///springboot"
})
class OrderRepositoryShortTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Sql("/scripts/INIT_THREE_ORDERS.sql")
    void findAllContainingMacBookPro() {

        var orders = orderRepository.findAllContainingMacBookPro();

        assertEquals(2, orders.size());
    }
}
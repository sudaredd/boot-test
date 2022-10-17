package app.boot.boottest.repository;

import app.boot.boottest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = """
      SELECT *
      FROM orders
      WHERE items @> '[{"name": "MacBook Pro"}]';
    """, nativeQuery = true)
    List<Order> findAllContainingMacBookPro();

    List<Order> findAllByTrackingNumber(String trackingNumber);
}

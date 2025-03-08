package com.example.orderrepo.repository;

import com.example.orderrepo.enums.OrderStatus;
import com.example.orderrepo.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Modifying
    @Query(value = """
            update order_sc.order o
            set o.status = :status
            where o.id = :orderId
            """, nativeQuery = true)
    void changeOrderStatus(@Param("orderId") UUID orderId, @Param("status") OrderStatus status);

}

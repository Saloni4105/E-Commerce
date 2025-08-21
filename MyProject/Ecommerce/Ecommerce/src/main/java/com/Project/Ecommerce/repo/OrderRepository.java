package com.Project.Ecommerce.repo;

import com.Project.Ecommerce.model.Orders;
import com.Project.Ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders,Long> {

    @Query("SELECT o FROM Orders o JOIN FETCH o.user ")
    List<Orders> findAllOrdersWithUsers();

    List<Orders> findByUser(User user);
}

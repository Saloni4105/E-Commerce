package com.Project.Ecommerce.service;

import com.Project.Ecommerce.dto.OrderDTO;
import com.Project.Ecommerce.dto.OrderItemDTO;
import com.Project.Ecommerce.model.OrderItems;
import com.Project.Ecommerce.model.Orders;
import com.Project.Ecommerce.model.Product;
import com.Project.Ecommerce.model.User;
import com.Project.Ecommerce.repo.OrderRepository;
import com.Project.Ecommerce.repo.ProductRepository;
import com.Project.Ecommerce.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {
        User user= userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));

        Orders order= new Orders();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("Pending");
        order.setTotalAmount(totalAmount);

        List<OrderItems> orderItems= new ArrayList<>();
        List<OrderItemDTO> orderItemDTOS= new ArrayList<>();

        for(Map.Entry<Long, Integer> entry: productQuantities.entrySet())
        {
            Product product= productRepository.findById(entry.getKey())
            .orElseThrow(()-> new RuntimeException("Product not found"));

            OrderItems orderItem= new OrderItems();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(entry.getValue());
            orderItems.add(orderItem);

            orderItemDTOS.add(new OrderItemDTO(product.getName(), product.getPrice(),entry.getValue()));
        }
        order.setOrderItems(orderItems);
        Orders saveOrder= orderRepository.save(order);
        return new OrderDTO(saveOrder.getId(), saveOrder.getTotalAmount(),
                saveOrder.getStatus(), saveOrder.getOrderDate(), orderItemDTOS);
    }

    public List<OrderDTO> getAllOrders() {
        List<Orders> orders= orderRepository.findAllOrdersWithUsers();
        return orders.stream().map(this::convertTODTO).collect(Collectors.toList());
    }

    private OrderDTO convertTODTO(Orders orders) {
        List<OrderItemDTO> OrderItems= orders.getOrderItems().stream()
                .map(item ->new OrderItemDTO(
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity())).collect(Collectors.toList());

        return new OrderDTO(
                orders.getId(),
                orders.getTotalAmount(),
                orders.getStatus(),
                orders.getOrderDate(),
                orders.getUser()!=null ? orders.getUser().getName() : "Unknown",
                orders.getUser()!= null ? orders.getUser().getEmail(): "Unknown",
                OrderItems
        );
    }

    public List<OrderDTO> getOrderByUser(Long userId) {
        Optional<User> userOp= userRepository.findById(userId);
        if(userOp.isEmpty())
        {
            throw new RuntimeException("user not found");
        }
        User user= userOp.get();
        List<Orders> ordersList=  orderRepository.findByUser(user);
        return ordersList.stream().map(this:: convertTODTO).collect(Collectors.toList());
    }
}

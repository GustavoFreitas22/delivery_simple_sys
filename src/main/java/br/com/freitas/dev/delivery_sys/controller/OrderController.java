package br.com.freitas.dev.delivery_sys.controller;

import br.com.freitas.dev.delivery_sys.model.Order;
import br.com.freitas.dev.delivery_sys.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = repository.getAllOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        for (Order order: orders) {
            long id = order.getId();
            order.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).getOrderById(id)).withSelfRel());
        }

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<List<Order>> getOrderByClientId(@PathVariable("id") long id) {
        List<Order> orders = repository.getAllOrdersByClientId(id);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) {
        Order order = repository.getOrderById(id);
        if (order == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        order.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).getAllOrders()).withRel("List all orders"));

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewOrder(@RequestBody Order order) {
        try {
            Boolean created = repository.createNewOrder(new Order(order.getDescription(), order.getClientId()));
            if (!created) {
                return new ResponseEntity<>("Error to create a new order!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Order created with success!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") long id, @RequestBody Order order) {
        Order baseOrder = repository.getOrderById(id);

        if (baseOrder == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        baseOrder.setId(id);
        baseOrder.setDescription(order.getDescription());
        baseOrder.setClientId(order.getClientId());

        Order updatedOrder = repository.updateOrder(baseOrder);

        if (updatedOrder == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("id") long id) {
        Boolean deleted = repository.deleteOrderById(id);
        if (!deleted) {
            return new ResponseEntity<>("Order dont deleted!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Order Deleted with success!", HttpStatus.OK);
    }
}

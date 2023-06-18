package br.com.freitas.dev.delivery_sys.repository;

import br.com.freitas.dev.delivery_sys.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository {
    List<Order> getAllOrders();

    List<Order> getAllOrdersByClientId(long id);

    Order getOrderById(long id);

    Boolean createNewOrder(Order newOrder);

    Order updateOrder(Order order);

    Boolean deleteOrderById(long id);
}

package br.com.freitas.dev.delivery_sys.repository.impl;

import br.com.freitas.dev.delivery_sys.model.Order;
import br.com.freitas.dev.delivery_sys.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Order> getAllOrders() {
        return jdbcTemplate.query("SELECT * from \"ORDER\"", BeanPropertyRowMapper.newInstance(Order.class));
    }

    @Override
    public List<Order> getAllOrdersByClientId(long id) {
        try {
            return jdbcTemplate.query("SELECT * FROM \"ORDER\" WHERE CLIENT_ID=?",
                    BeanPropertyRowMapper.newInstance(Order.class), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            log.error("Orders not find!");
            return null;
        }
    }

    @Override
    public Order getOrderById(long id) {
        try {
            Order order = jdbcTemplate.queryForObject("SELECT * FROM \"ORDER\" WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Order.class), id);
            return order;
        } catch (IncorrectResultSizeDataAccessException e) {
            log.error("Order not find!");
            return null;
        }
    }

    @Override
    public Boolean createNewOrder(Order newOrder) {
        int rowsAdded = jdbcTemplate.update("INSERT INTO \"ORDER\" (DESCRIPTION, CLIENT_ID) VALUES(?,?)",
                newOrder.getDescription(), newOrder.getClientId());

        if (rowsAdded == 0) {
            log.error("Order dont added");
            return false;
        }
        return true;
    }

    @Override
    public Order updateOrder(Order order) {
        int rowsModificaded = jdbcTemplate.update("UPDATE \"ORDER\" SET DESCRIPTION=?, CLIENT_ID=? WHERE ID=?",
                order.getDescription(), order.getClientId(), order.getId());

        if (rowsModificaded == 0) {
            log.error("Order dont updated");
            return null;
        }
        return order;
    }

    @Override
    public Boolean deleteOrderById(long id) {
        int rowsDeleted = jdbcTemplate.update("DELETE FROM \"ORDER\" WHERE id=?", id);
        if (rowsDeleted == 0) {
            log.error("Error to delete order by id=${}", id);
            return false;
        }
        return true;
    }
}

package br.com.freitas.dev.delivery_sys.repository.impl;

import br.com.freitas.dev.delivery_sys.model.Delivery;
import br.com.freitas.dev.delivery_sys.model.Order;
import br.com.freitas.dev.delivery_sys.repository.DeliveryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Delivery getDeliveryById(long id) {
        try {
            Delivery delivery = jdbcTemplate.queryForObject("SELECT * FROM DELIVERY WHERE ID=?",
                    BeanPropertyRowMapper.newInstance(Delivery.class), id);
            return delivery;
        } catch (IncorrectResultSizeDataAccessException e) {
            log.error("Delivery not find!");
            return null;
        }
    }

    @Override
    public List<Delivery> getDeliveryByOrderId(long id) {
        try {
            return jdbcTemplate.query("SELECT * FROM DELIVERY WHERE ORDER_ID=?",
                    BeanPropertyRowMapper.newInstance(Delivery.class), id);
        } catch (IncorrectResultSizeDataAccessException e) {
            log.error("Delivery not find!");
            return null;
        }
    }

    @Override
    public List<Delivery> getAllDelivery() {
        return jdbcTemplate.query("SELECT * from DELIVERY", BeanPropertyRowMapper.newInstance(Delivery.class));
    }

    @Override
    public Boolean createNewDelivery(Delivery delivery) {
        System.out.println(delivery.getDate());
        System.out.println(delivery.getOrderId());
        int rowsAdded = jdbcTemplate.update("INSERT INTO DELIVERY (\"DATE\", ORDER_ID) VALUES(?,?)",
                delivery.getDate(), delivery.getOrderId());

        if (rowsAdded == 0) {
            log.error("Delivery dont added");
            return false;
        }
        return true;
    }

    @Override
    public Delivery updateDelivery(Delivery delivery) {
        int rowsModificaded = jdbcTemplate.update("UPDATE DELIVERY SET \"DATE\"=?, ORDER_ID=? WHERE ID=?",
                delivery.getDate(), delivery.getOrderId(), delivery.getId());
        if (rowsModificaded == 0) {
            log.error("Delivery dont added");
            return null;
        }

        return delivery;
    }

    @Override
    public Boolean deleteDeliveryById(long id) {
        int rowsDeleted = jdbcTemplate.update("DELETE FROM DELIVERY WHERE id=?", id);
        if (rowsDeleted == 0) {
            log.error("Error to delete order by id=${}", id);
            return false;
        }
        return true;
    }
}

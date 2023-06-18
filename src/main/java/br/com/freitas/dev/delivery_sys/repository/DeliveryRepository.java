package br.com.freitas.dev.delivery_sys.repository;

import br.com.freitas.dev.delivery_sys.model.Delivery;

import java.sql.Date;
import java.util.List;

public interface DeliveryRepository {
    Delivery getDeliveryById(long id);

    List<Delivery> getDeliveryByOrderId(long id);

    List<Delivery> getAllDelivery();

    Boolean createNewDelivery(Delivery delivery);

    Delivery updateDelivery(Delivery delivery);

    Boolean deleteDeliveryById(long id);
}

package br.com.freitas.dev.delivery_sys.controller;

import br.com.freitas.dev.delivery_sys.model.Delivery;
import br.com.freitas.dev.delivery_sys.repository.impl.DeliveryRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("delivery")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class DeliveryController {

    @Autowired
    private DeliveryRepositoryImpl repository;

    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDelivery() {
        List<Delivery> deliveries = repository.getAllDelivery();
        if (deliveries.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (Delivery delivery : deliveries) {
            long id = delivery.getId();
            delivery.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryController.class).getDeliveryById(id)).withSelfRel());
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getDeliveryById(@PathVariable("id") long id) {
        Delivery delivery = repository.getDeliveryById(id);
        if (delivery == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        delivery.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(DeliveryController.class).getAllDelivery()).withRel("List with all deliveries"));

        return new ResponseEntity<>(delivery, HttpStatus.OK);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<List<Delivery>> getDeliveryByOrderId(@PathVariable("id") long id) {
        List<Delivery> deliveries = repository.getDeliveryByOrderId(id);
        if (deliveries.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(deliveries, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewDelivery(@RequestBody Delivery delivery) {
        try {
            Boolean created = repository.createNewDelivery(new Delivery(delivery.getDate(), delivery.getOrderId()));
            if (!created) {
                return new ResponseEntity<>("Error to create a new delivery!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Delivery created with success!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delivery> updateDelivery(@PathVariable("id") long id, @RequestBody Delivery delivery) {
        Delivery baseDelivery = repository.getDeliveryById(id);

        if (baseDelivery == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        baseDelivery.setId(id);
        baseDelivery.setDate(delivery.getDate());
        baseDelivery.setOrderId(delivery.getOrderId());

        Delivery updateDelivery = repository.updateDelivery(baseDelivery);

        if (updateDelivery == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updateDelivery, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeliveryById(@PathVariable("id") long id) {
        Boolean deleted = repository.deleteDeliveryById(id);
        if (!deleted) {
            return new ResponseEntity<>("Delivery dont deleted!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Delivery Deleted with success!", HttpStatus.OK);
    }
}

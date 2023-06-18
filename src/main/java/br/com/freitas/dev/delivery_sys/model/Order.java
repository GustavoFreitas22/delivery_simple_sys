package br.com.freitas.dev.delivery_sys.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order extends RepresentationModel<Order> {
    private long id;
    private String description;
    private long clientId;

    public Order(String description, long clientId) {
        this.description = description;
        this.clientId = clientId;
    }
}

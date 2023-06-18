package br.com.freitas.dev.delivery_sys.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private long id;
    private String description;
    private long client_id;

    public Order(String description, long client_id) {
        this.description = description;
        this.client_id = client_id;
    }
}

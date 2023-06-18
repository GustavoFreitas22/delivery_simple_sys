package br.com.freitas.dev.delivery_sys.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery extends RepresentationModel<Delivery> {
    private long id;
    private LocalDate date;
    private long orderId;

    public Delivery(LocalDate date, long orderId) {
        this.date = date;
        this.orderId = orderId;
    }
}

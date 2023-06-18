package br.com.freitas.dev.delivery_sys.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    private long id;
    private LocalDate date;
    private long orderId;

    public Delivery(LocalDate date, long orderId) {
        this.date = date;
        this.orderId = orderId;
    }
}

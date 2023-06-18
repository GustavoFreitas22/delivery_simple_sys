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
public class Client extends RepresentationModel<Client> {
    private long id;
    private String name;
    private String address;

    public Client(String name, String address) {
        this.name = name;
        this.address = address;
    }
}

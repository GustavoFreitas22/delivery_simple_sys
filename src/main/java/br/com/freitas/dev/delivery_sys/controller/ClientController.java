package br.com.freitas.dev.delivery_sys.controller;

import br.com.freitas.dev.delivery_sys.model.Client;
import br.com.freitas.dev.delivery_sys.repository.impl.ClientRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.core.WebHandler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("client")
@CrossOrigin(value = "*", allowedHeaders = "*")
public class ClientController {

    @Autowired
    ClientRepositoryImpl repository;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = repository.getAllClients();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        for (Client client: clients) {
            long id = client.getId();
            client.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).getClientById(id)).withSelfRel());
        }
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable long id) {
        Client client = repository.getClientById(id);
        if (client == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        client.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ClientController.class).getAllClients()).withRel("List with all clients"));
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createClient(@RequestBody Client client) {
        try {
            Boolean created = repository.createNewClient(new Client(client.getName(), client.getAddress()));
            if (!created) {
                return new ResponseEntity<>("Error to create a new client!", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>("Client created with success!", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> editClient(@PathVariable("id") long id, @RequestBody Client client) {

        Client baseClient = repository.getClientById(id);

        if (baseClient == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        baseClient.setId(id);
        baseClient.setName(client.getName());
        baseClient.setAddress(client.getAddress());

        Client updatedClient = repository.updateClient(baseClient);

        if (updatedClient == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClientById(@PathVariable("id") long id){
        Boolean deleted = repository.deleteClientById(id);
        if (!deleted){
            return new ResponseEntity<>("Client dont deleted!", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Client Deleted with success!", HttpStatus.OK);
    }
}

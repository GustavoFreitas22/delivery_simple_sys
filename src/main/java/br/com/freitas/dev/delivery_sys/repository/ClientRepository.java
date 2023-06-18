package br.com.freitas.dev.delivery_sys.repository;

import br.com.freitas.dev.delivery_sys.model.Client;

import java.util.List;

public interface ClientRepository {

    List<Client> getAllClients();

    Client getClientById(long id);

    Boolean createNewClient(Client client);

    Client updateClient(Client client);

    Boolean deleteClientById(long id);
}

package br.com.freitas.dev.delivery_sys.repository;

import br.com.freitas.dev.delivery_sys.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class ClientRepositoryImpl implements ClientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Client> getAllClients() {
        return jdbcTemplate.query("SELECT * from CLIENT", BeanPropertyRowMapper.newInstance(Client.class));
    }

    @Override
    public Client getClientById(long id) {
        try {
            Client client = jdbcTemplate.queryForObject("SELECT * FROM CLIENT WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Client.class), id);
            return client;
        } catch (IncorrectResultSizeDataAccessException e) {
            log.error("Client not find!");
            return null;
        }
    }

    @Override
    public Boolean createNewClient(Client client) {
        int rowsAdded = jdbcTemplate.update("INSERT INTO CLIENT (NAME, ADDRESS) VALUES(?,?)",
                client.getName(), client.getAddress());

        if (rowsAdded == 0) {
            log.error("Client dont added");
            return false;
        }
        return true;
    }

    @Override
    public Client updateClient(Client client) {
        int rowsModificaded = jdbcTemplate.update("UPDATE CLIENT SET NAME=?, ADDRESS=? WHERE ID=?",
                client.getName(), client.getAddress(), client.getId());

        if (rowsModificaded == 0) {
            log.error("Client dont modificated");
            return null;
        }
        return client;
    }

    @Override
    public Boolean deleteClientById(long id) {
        int rowsDeleted = jdbcTemplate.update("DELETE FROM CLIENT WHERE id=?", id);
        if (rowsDeleted==0){
            log.error("Error to delete client by id=${}", id);
            return false;
        }
        return true;
    }
}

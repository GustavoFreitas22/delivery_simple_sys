package br.com.freitas.dev.delivery_sys.repository.impl;

import br.com.freitas.dev.delivery_sys.model.User;
import br.com.freitas.dev.delivery_sys.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByLogin(String login) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM \"USER\" WHERE LOGIN=?",
                    BeanPropertyRowMapper.newInstance(User.class), login);
            return user;
        } catch (IncorrectResultSizeDataAccessException e) {
            log.error("Login not find!");
            return null;
        }
    }

    @Override
    public Boolean createNewUser(User user) {
        int rowsAdded = jdbcTemplate.update("INSERT INTO \"USER\" (LOGIN, PASSWORD) VALUES(?,?)",
                user.getLogin(), user.getPassword());

        if (rowsAdded == 0) {
            log.error("User dont added");
            return false;
        }
        return true;
    }
}

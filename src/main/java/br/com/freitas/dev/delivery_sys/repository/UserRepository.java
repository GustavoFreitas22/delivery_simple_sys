package br.com.freitas.dev.delivery_sys.repository;

import br.com.freitas.dev.delivery_sys.model.User;

public interface UserRepository {
    User getUserByLogin(String login);

    Boolean createNewUser(User user);
}

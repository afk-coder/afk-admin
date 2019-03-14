package net.fux.auth.service;

import net.fux.auth.entity.User;

/**
 * Created by fuxj on 2019/3/6
 */
public interface UserService {

    User getUser(String username);
}

package com.system.web.service;

import com.system.web.domain.User;
import com.system.web.infrastructure.common.PageSearchModel;
import com.system.web.infrastructure.common.PagedList;

public interface UserService {
    PagedList<User> getPagedList(PageSearchModel pageSearchModel);

    User getByUsername(String username);

    int addUser(User user);

    int updateUser(User user);
}

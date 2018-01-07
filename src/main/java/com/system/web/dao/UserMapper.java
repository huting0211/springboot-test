package com.system.web.dao;

import com.system.web.domain.User;
import com.system.web.infrastructure.common.PageSearchModel;
import com.system.web.infrastructure.common.SearchModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getPagedList(PageSearchModel pagedSearchModel);

    int getCountByCondition(SearchModel searchModel);

    User getByUsername(String username);

    int addUser(User user);

    int updateUser(User user);
}

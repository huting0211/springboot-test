package com.system.web.service.impl;

import com.system.web.dao.UserMapper;
import com.system.web.domain.User;
import com.system.web.infrastructure.common.PageSearchModel;
import com.system.web.infrastructure.common.PagedList;
import com.system.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public PagedList<User> getPagedList(PageSearchModel pageSearchModel) {
        int totalRecords = userMapper.getCountByCondition(pageSearchModel);
        List<User> userList = userMapper.getPagedList(pageSearchModel);
        PagedList<User> pagedList = new PagedList<>();
        pagedList.setList(userList);
        pagedList.setPageIndex(pageSearchModel.getPageIndex());
        pagedList.setPageSize(pageSearchModel.getPageSize());
        pagedList.setTotalRecords(totalRecords);
        return pagedList;
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }


}

package com.cyzs.mapper;

import entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xiaoH
 * @create 2019-06-10-18:29
 */
public interface UserMapper {
    User getOne(Integer id);
    User getOne(@Param("id") Integer id,@Param("username") String username);
    void addUser(User user);
    void deleteUserById(Integer id);
    void updateUser(User user);
    User getUserAndDept(Integer id);
    List<User> getUsersById(Integer id);
    void deleteByIds(@Param("ids") List<Integer> ids);
    boolean addUsers(@Param("users") List<User> users);
}

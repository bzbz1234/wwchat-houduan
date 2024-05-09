package com.example.demo.Service;

import com.example.demo.user.MyUser;

import java.util.List;

public interface MyUserService {
    List<MyUser> findAll();

    void save(MyUser user);

    /*
     * 删除用户
     */
    void delete(MyUser user);

    /*
     * 根据post查找用户
     */
    List<MyUser> findUserByPost(String post);


    /*
     * 根据post查找用户
     */
    List<MyUser> findUserByCompany(String company);

    /*
     * 更改用户信息
     */
    int update(MyUser user);
}

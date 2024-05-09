package com.example.demo.repository;

import com.example.demo.user.MyUser;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MyUserRepository {
    List<MyUser> findAll();

    void save(MyUser user);

    void delete(MyUser user);

    List<MyUser> findUserByPost(String post);

    List<MyUser> findUserByCompany(String company);

    int update(MyUser user);


}

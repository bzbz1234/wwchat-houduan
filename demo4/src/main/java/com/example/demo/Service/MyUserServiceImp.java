package com.example.demo.Service;

import com.example.demo.repository.MyUserRepository;
import com.example.demo.user.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserServiceImp implements MyUserService {
    @Autowired
    private MyUserRepository myUserRepository;

    public List<MyUser> findAll() {
        return myUserRepository.findAll();
    }

    public void save(MyUser user) {
        myUserRepository.save(user);
    }

    public void delete(MyUser user) {
        myUserRepository.delete(user);
    }

    public List<MyUser> findUserByPost(String post) {
        return myUserRepository.findUserByPost(post);
    }

    public List<MyUser> findUserByCompany(String company) {
        return myUserRepository.findUserByCompany(company);
    }

    public int update(MyUser user) {
        return myUserRepository.update(user);
    }

}

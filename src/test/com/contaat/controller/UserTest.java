package com.contaat.controller;

import com.contaat.entity.Users;
import com.contaat.mapper.UsersMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author 胡字蒙.
 * @Date 2018/11/26   11:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void TestSelect(){
        System.out.println("-----------------selectAll method Test-----------------");
        List<Users> userList = usersMapper.selectList(null);
        Assert.assertEquals(userList.size(), userList.size());

        for(Users users:userList){
            System.out.println(users);
        }
    }

}

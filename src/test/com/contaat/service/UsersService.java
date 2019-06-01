package com.contaat.service;

import org.springframework.stereotype.Service;

/**
 * Created by admin on 2018/11/23.
 */
@Service
public class UsersService {
   /* @Autowired
    private UsersMapper usersMapper;
   *//* @Transactional 事物*//*
    public String insert (String usename ,String mome){
        usersMapper.insert(usename,mome);
        System.out.println("insertUserResult:{}"+usename);
        return usename;
    }

    public Users find (int id){
        Users users =  usersMapper.findId(id);
        return users;
    }

    public List<Users> find(){
        List<Users> users = usersMapper.find();
        return users;
    }
    public PageInfo findpage(int page,int pagesize){
        *//*mysql 查询limit    orcalet  伪列  sqlservice top*//*
        //PageHelper 实现分页
        PageHelper.startPage(page,pagesize); //底层原理改写语句
        List<Users> users = usersMapper.find();
        PageInfo<Users> usersPageInfo = new PageInfo<Users>(users);
        return usersPageInfo;
    }*/
}

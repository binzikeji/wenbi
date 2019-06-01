package com.contaat.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2018/11/23.
 */
@RestController
public class TaUsers {

   /* @Autowired
    private UsersService usersService;
    @RequestMapping(value = "/save")
    public String save(){
        String usename = "胡";
        String mome="133";
        String code =  usersService.insert(usename,mome);
        return code;
    }
    @RequestMapping(value = "/findId")
    public Users findId(){
        Users users = usersService.find(101);
        return users;
    }
    @RequestMapping(value = "/find")
    public List<Users> find(){
       List<Users> users = usersService.find();
       return users;
    }
    @RequestMapping(value = "/findpage")
    public  PageInfo<Users> findpage(){
        PageInfo<Users> users = usersService.findpage(1,2);
        return users;
    }*/
}

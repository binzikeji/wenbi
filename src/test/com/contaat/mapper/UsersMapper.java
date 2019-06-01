package com.contaat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.contaat.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 胡字蒙.
 * @Date 2018/11/23   16:06
 */

public interface UsersMapper extends BaseMapper<Users> {

   /* @Select("SELECT * FROM USERS WHERE id = #{id}")
    Users findId(@Param("id") int id);
    @Insert("INSERT INTO USERS(usename,mome) VALUE(#{usename},#{mome})")
    void insert (@Param("usename") String usename , @Param("mome") String mome);
    @Select("SELECT * FROM USERS")
    List<Users> find();*/
}

package com.contaat.sys.service.impl;

import com.contaat.sys.entity.User;
import com.contaat.sys.mapper.UserMapper;
import com.contaat.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author 胡胡道人
 * @since 2018-12-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

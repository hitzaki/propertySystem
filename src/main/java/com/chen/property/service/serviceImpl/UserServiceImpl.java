package com.chen.property.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.property.mapper.UserMapper;
import com.chen.property.pojo.User;
import com.chen.property.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(timeout = 10)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}

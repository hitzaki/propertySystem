package com.chen.property.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.property.mapper.AdminMapper;
import com.chen.property.pojo.Admin;
import com.chen.property.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(timeout = 10)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
}

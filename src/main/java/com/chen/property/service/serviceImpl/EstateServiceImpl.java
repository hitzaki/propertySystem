package com.chen.property.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.property.mapper.EstateMapper;
import com.chen.property.pojo.Estate;
import com.chen.property.service.EstateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(timeout = 10)
public class EstateServiceImpl extends ServiceImpl<EstateMapper, Estate> implements EstateService {
}

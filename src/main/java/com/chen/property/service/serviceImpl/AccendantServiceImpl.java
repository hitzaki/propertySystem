package com.chen.property.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.property.mapper.AccendantMapper;
import com.chen.property.pojo.Accendant;
import com.chen.property.service.AccendantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(timeout = 10)
public class AccendantServiceImpl extends ServiceImpl<AccendantMapper, Accendant> implements AccendantService {
}

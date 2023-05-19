package com.chen.property.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.property.mapper.StandardMapper;
import com.chen.property.pojo.Standard;
import com.chen.property.service.StandardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(timeout = 10)
public class StandardServiceImpl extends ServiceImpl<StandardMapper, Standard> implements StandardService {
}

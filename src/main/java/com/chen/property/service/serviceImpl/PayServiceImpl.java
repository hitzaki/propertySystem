package com.chen.property.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.property.mapper.PayMapper;
import com.chen.property.pojo.Pay;
import com.chen.property.service.PayService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(timeout = 10)
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements PayService {
}

package com.chen.property.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.property.mapper.RepairMapper;
import com.chen.property.pojo.Repair;
import com.chen.property.service.RepairService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(timeout = 10)
public class RepairServiceImpl extends ServiceImpl<RepairMapper, Repair> implements RepairService {
}

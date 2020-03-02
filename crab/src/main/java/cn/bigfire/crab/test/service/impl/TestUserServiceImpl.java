package cn.bigfire.crab.test.service.impl;

import cn.bigfire.crab.test.entity.TestUser;
import cn.bigfire.crab.test.mapper.TestUserMapper;
import cn.bigfire.crab.test.service.TestUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @ IDE    ：IntelliJ IDEA.
* @ Author ：dahuo
* @ Date   ：2020-01-11
* @ Desc   ： 服务实现类
*/
@Service
public class TestUserServiceImpl extends ServiceImpl<TestUserMapper, TestUser> implements TestUserService {

}

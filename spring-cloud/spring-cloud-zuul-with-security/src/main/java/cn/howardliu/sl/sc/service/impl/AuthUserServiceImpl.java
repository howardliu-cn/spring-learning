package cn.howardliu.sl.sc.service.impl;

import cn.howardliu.sl.sc.mapper.AuthUserMapper;
import cn.howardliu.sl.sc.pojo.AuthUserPojo;
import cn.howardliu.sl.sc.service.AuthUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <br/>create at 16-3-4
 *
 * @author liuxh
 * @since 1.0.0
 */
@Service("authUserService")
public class AuthUserServiceImpl implements AuthUserService {
    private static final Logger logger = LoggerFactory.getLogger(AuthUserServiceImpl.class);
    @Autowired
    private AuthUserMapper authUserMapper;

    @Override
    public AuthUserPojo getUserInfo(String userId) {
        return authUserMapper.getUserById(userId);
    }
}

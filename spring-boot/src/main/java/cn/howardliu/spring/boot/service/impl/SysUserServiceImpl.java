package cn.howardliu.spring.boot.service.impl;

import cn.howardliu.spring.boot.mapper.SysUserMapper;
import cn.howardliu.spring.boot.domain.SysUser;
import cn.howardliu.spring.boot.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>created at 17-4-10
 *
 * @author liuxh
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> listAll() {
        return sysUserMapper.listAll("all");
    }
}

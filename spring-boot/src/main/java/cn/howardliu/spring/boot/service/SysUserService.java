package cn.howardliu.spring.boot.service;

import cn.howardliu.spring.boot.domain.SysUser;

import java.util.List;

/**
 * <br>created at 17-4-10
 *
 * @author liuxh
 * @version 1.0.0
 * @since 1.0.0
 */
public interface SysUserService {
    List<SysUser> listAll();
}

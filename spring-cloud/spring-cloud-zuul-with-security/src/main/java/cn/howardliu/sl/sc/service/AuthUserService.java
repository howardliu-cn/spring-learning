package cn.howardliu.sl.sc.service;

import cn.howardliu.sl.sc.pojo.AuthUserPojo;

/**
 * <br/>create at 16-3-4
 *
 * @author liuxh
 * @since 1.0.0
 */
public interface AuthUserService {
    AuthUserPojo getUserInfo(String userId);
}

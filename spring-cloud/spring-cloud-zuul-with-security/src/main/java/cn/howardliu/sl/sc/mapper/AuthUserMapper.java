package cn.howardliu.sl.sc.mapper;

import cn.howardliu.sl.sc.pojo.AuthUserPojo;
import org.springframework.stereotype.Repository;

/**
 * <br/>create at 16-2-25
 *
 * @author liuxh
 * @since 1.0.0
 */
@Repository
public interface AuthUserMapper {
    AuthUserPojo loadUserByUsername(String username);

    AuthUserPojo getUserById(String userId);
}

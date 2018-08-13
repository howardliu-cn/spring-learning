package cn.howardliu.sl.sc.mapper;


import cn.howardliu.sl.sc.pojo.AuthGroupPojo;
import cn.howardliu.sl.sc.pojo.AuthUserPojo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br/>create at 16-2-25
 *
 * @author liuxh
 * @since 1.0.0
 */
@Repository
public interface AuthGroupMapper {
    List<AuthGroupPojo> loadUserGroups(AuthUserPojo user);

    List<AuthGroupPojo> listGroupsByUserId(String userId);
}

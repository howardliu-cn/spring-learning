package cn.howardliu.sl.sc.mapper;

import cn.howardliu.sl.sc.pojo.AuthAuthorityPojo;
import cn.howardliu.sl.sc.pojo.AuthGroupPojo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br/>create at 16-2-25
 *
 * @author liuxh
 * @since 1.0.0
 */
@Repository
public interface AuthAuthorityMapper {
    List<AuthAuthorityPojo> loadAuthorityOfGroups(List<AuthGroupPojo> groups);

    List<String> loadAllAuthorities();

    List<AuthAuthorityPojo> listAuthoritiesByGroupId(String groupId);
}

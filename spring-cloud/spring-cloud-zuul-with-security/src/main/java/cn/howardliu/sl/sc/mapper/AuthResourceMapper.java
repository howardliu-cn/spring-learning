package cn.howardliu.sl.sc.mapper;

import cn.howardliu.sl.sc.pojo.AuthResourcePojo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br/>create at 16-2-25
 *
 * @author liuxh
 * @since 1.0.0
 */
@Repository
public interface AuthResourceMapper {
    List<String> loadResourcesByAuthority(String authority);

    List<AuthResourcePojo> listResourcesByAuthorityId(String authorityId);
}

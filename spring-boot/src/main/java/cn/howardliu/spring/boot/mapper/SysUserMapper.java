package cn.howardliu.spring.boot.mapper;

import cn.howardliu.spring.boot.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>created at 17-4-10
 *
 * @author liuxh
 * @version 1.0.0
 * @since 1.0.0
 */
public interface SysUserMapper {
    List<SysUser> listAll(@Param("username") String username);
}

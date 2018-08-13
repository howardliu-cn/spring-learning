package cn.howardliu.sl.sc.service.impl;

import cn.howardliu.sl.sc.mapper.AuthAuthorityMapper;
import cn.howardliu.sl.sc.mapper.AuthGroupMapper;
import cn.howardliu.sl.sc.mapper.AuthUserMapper;
import cn.howardliu.sl.sc.pojo.AuthAuthorityPojo;
import cn.howardliu.sl.sc.pojo.AuthGroupPojo;
import cn.howardliu.sl.sc.pojo.AuthUserPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 扩展Spring Security中关于用户权限的控制
 * <br/>create at 15-7-3
 *
 * @author liuxh
 * @since 1.0.0
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AuthUserMapper userMapper;
    @Autowired
    private AuthGroupMapper groupMapper;
    @Autowired
    private AuthAuthorityMapper authorityMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUserPojo userFound = userMapper.loadUserByUsername(username);
        if (userFound == null) {
            logger.debug("Query returned no results for user '" + username + "'");
            throw new UsernameNotFoundException("Username " + username + " not found");
        } else {
            List<AuthGroupPojo> groups = this.groupMapper.loadUserGroups(userFound);
            if (groups.size() > 0) {
                groups.stream()
                        .filter(group -> group.getGroupName().startsWith("ROLE_"))
                        .forEach(group -> userFound.getAuthorities()
                                .add(new SimpleGrantedAuthority(group.getGroupName())));
                List<AuthAuthorityPojo> authorities = this.authorityMapper.loadAuthorityOfGroups(groups);
                for (AuthAuthorityPojo authority : authorities) {
                    userFound.getAuthorities().add(new SimpleGrantedAuthority(authority.getAuthority()));
                }
            }
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("userInfo", userFound);
            return userFound;
        }
    }
}

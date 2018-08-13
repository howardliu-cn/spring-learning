package cn.howardliu.sl.sc.service.impl;

import cn.howardliu.sl.sc.mapper.AuthAuthorityMapper;
import cn.howardliu.sl.sc.mapper.AuthResourceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。 此类在初始化时，应该取到所有资源及其对应角色的定义。
 * <br/>create at 16-2-25
 *
 * @author liuxh
 * @since 1.0.0
 */
@Component("filterInvocationSecurityMetadataSource")
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
    private static final Logger logger = LoggerFactory.getLogger(FilterInvocationSecurityMetadataSourceImpl.class);
    private static final PathMatcher pathMatcher = new AntPathMatcher();
    private static final Map<String, Collection<ConfigAttribute>> resourceMap = new ConcurrentHashMap<>();
    @Autowired
    private AuthAuthorityMapper authorityMapper;
    @Autowired
    private AuthResourceMapper resourceMapper;

    @PostConstruct
    public void loadResources() {
        // 提取系统中的所有权限
        List<String> authorities = authorityMapper.loadAllAuthorities();
        // 资源为key， 权限为value。资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问。
        for (String auth : authorities) {
            ConfigAttribute ca = new SecurityConfig(auth);
            List<String> resources = resourceMapper.loadResourcesByAuthority(auth);
            for (String resource : resources) {
                // 判断资源文件和权限的对应关系，如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中
                Collection<ConfigAttribute> caList = resourceMap.get(resource);
                if (caList == null) {
                    caList = Collections.synchronizedSet(new HashSet<>());
                    caList.add(ca);
                    resourceMap.put(resource, caList);
                } else {
                    caList.add(ca);
                }
            }
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if(resourceMap.isEmpty()) {
            loadResources();
        }
        String url = ((FilterInvocation) o).getRequestUrl();
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            url = url.substring(0, firstQuestionMarkIndex);
        }
        for (String resURL : resourceMap.keySet()) {
            if (pathMatcher.match(resURL, url)) {
                return resourceMap.get(resURL);
            }
        }
        //throw new AccessDeniedException("you don't allow to get content");
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        loadResources();
        Set<ConfigAttribute> allAttributes = new HashSet<>();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

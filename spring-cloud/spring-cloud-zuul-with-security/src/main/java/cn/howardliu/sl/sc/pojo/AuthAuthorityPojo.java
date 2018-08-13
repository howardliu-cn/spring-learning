package cn.howardliu.sl.sc.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/>create at 16-2-25
 *
 * @author liuxh
 * @since 1.0.0
 */
public class AuthAuthorityPojo {
    private Long authorityId;
    private String authority;
    private String authorityDesc;
    private Boolean enabled;
    private List<AuthResourcePojo> resources = new ArrayList<>();

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public String getAuthorityDesc() {
        return authorityDesc;
    }

    public void setAuthorityDesc(String authorityDesc) {
        this.authorityDesc = authorityDesc;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<AuthResourcePojo> getResources() {
        return resources;
    }

    public void setResources(List<AuthResourcePojo> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "AuthAuthorityPojo{" +
                "authorityId=" + authorityId +
                ", authority='" + authority + '\'' +
                ", authorityDesc='" + authorityDesc + '\'' +
                ", enabled=" + enabled +
                ", resources=" + resources +
                '}';
    }
}

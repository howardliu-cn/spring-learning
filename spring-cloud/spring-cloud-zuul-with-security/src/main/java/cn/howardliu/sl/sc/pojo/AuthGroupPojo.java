package cn.howardliu.sl.sc.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/>create at 16-2-25
 *
 * @author liuxh
 * @since 1.0.0
 */
public class AuthGroupPojo {
    private Long groupId;
    private String groupName;
    private String groupDesc;
    private String enabled;
    private List<AuthAuthorityPojo> authorities = new ArrayList<>();

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public List<AuthAuthorityPojo> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthAuthorityPojo> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "AuthGroupPojo{" +
                "groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", groupDesc='" + groupDesc + '\'' +
                ", enabled='" + enabled + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}

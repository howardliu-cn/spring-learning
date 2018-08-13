package cn.howardliu.sl.sc.pojo;

/**
 * <br/>create at 16-2-25
 *
 * @author liuxh
 * @since 1.0.0
 */
public class AuthAuthorityResourcePojo {
    private Long id;
    private Long authorityId;
    private Long resourceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}

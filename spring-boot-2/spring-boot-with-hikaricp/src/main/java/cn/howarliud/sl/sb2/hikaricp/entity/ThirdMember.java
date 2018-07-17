package cn.howarliud.sl.sb2.hikaricp.entity;

/**
 * <br>created at 18-7-13
 *
 * @author liuxh
 * @since 0.1.0
 */
public class ThirdMember {
    private String uid;
    private String unionid;
    private String openid;
    private String nickname;
    private String avatar;

    public ThirdMember() {
    }

    public ThirdMember(String uid, String openid) {
        this.uid = uid;
        this.openid = openid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

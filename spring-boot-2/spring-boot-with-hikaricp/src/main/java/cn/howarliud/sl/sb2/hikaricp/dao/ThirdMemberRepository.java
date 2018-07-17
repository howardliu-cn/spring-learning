package cn.howarliud.sl.sb2.hikaricp.dao;

import cn.howarliud.sl.sb2.hikaricp.entity.ThirdMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <br>created at 18-7-13
 *
 * @author liuxh
 * @since 0.1.0
 */
@Repository
public class ThirdMemberRepository {
    private static final Logger logger = LoggerFactory.getLogger(ThirdMemberRepository.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ThirdMember> findAll() {
        return jdbcTemplate.query(
                "select a.openid openid, b.uid uid from third_member a left join member b on a.uid = b.uid where b.source = 2 and a.unionid = ''",
                (rs, rowNum) -> new ThirdMember(rs.getString("uid"), rs.getString("openid"))
        );
    }

    public int update(ThirdMember thirdMember) {
        return jdbcTemplate.update("update third_member set unionid=?, nickname=?, avatar=? where uid=?",
                thirdMember.getUnionid(), thirdMember.getNickname(), thirdMember.getAvatar(), thirdMember.getUid());
    }

    public List<ThirdMember> findAllForUnionid() {
        return jdbcTemplate.query("select a.openid openid, b.uid uid from third_member a left join member b on a.uid=b.uid where unionid is null or unionid = ''",
                (rs, rowNum) -> new ThirdMember(rs.getString("uid"), rs.getString("openid"))
        );
    }

    public int updateUnionid(ThirdMember thirdMember) {
        return jdbcTemplate.update("update third_member set unionid=? where uid=?",
                thirdMember.getUnionid(), thirdMember.getUid());
    }
}

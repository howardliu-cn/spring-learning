package cn.howarliud.sl.sb2.hikaricp.dao;

import cn.howarliud.sl.sb2.hikaricp.entity.ShopMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * <br>created at 18-6-27
 *
 * @author liuxh
 * @since 0.1.0
 */
@Repository
public class ShopMapRepository {
    private static final Logger logger = LoggerFactory.getLogger(ShopMapRepository.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ShopMap> findAll() {
        return jdbcTemplate.query(
                "SELECT id, longitude, latitude FROM shopmap",
                (rs, rowNum) -> new ShopMap(rs.getString("id"),
                        rs.getBigDecimal("longitude"), rs.getBigDecimal("latitude"))
        );
    }

    public int update(String id, BigDecimal longitude, BigDecimal latitude) {
        return jdbcTemplate.update("update shopmap set longitude=?, latitude=? where id=?", longitude, latitude, id);
    }
}

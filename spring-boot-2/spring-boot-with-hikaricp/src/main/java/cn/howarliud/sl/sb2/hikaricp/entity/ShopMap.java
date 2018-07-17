package cn.howarliud.sl.sb2.hikaricp.entity;

import java.math.BigDecimal;

/**
 * <br>created at 18-6-27
 *
 * @author liuxh
 * @since 0.1.0
 */
public class ShopMap {
    private String id;
    private BigDecimal longitude;
    private BigDecimal latitude;

    public ShopMap() {
    }

    public ShopMap(String id, BigDecimal longitude, BigDecimal latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}

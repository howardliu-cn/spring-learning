package cn.howarliud.sl.sb2.hikaricp.controller;

import cn.howarliud.sl.sb2.hikaricp.dao.ShopMapRepository;
import cn.howarliud.sl.sb2.hikaricp.entity.ShopMap;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <br>created at 18-6-27
 *
 * @author liuxh
 * @since 0.1.0
 */
@RestController
public class ShopMapController {
    private static final Logger logger = LoggerFactory.getLogger(ShopMapController.class);
    @Autowired
    private ShopMapRepository shopMapRepository;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("update")
    public ResponseEntity<String> update() {
        List<ShopMap> all = shopMapRepository.findAll();
        Map<String, String> params = new HashMap<>();
        for (ShopMap shopMap : all) {
            params.put("locations", shopMap.getLongitude() + "," + shopMap.getLatitude());
            String response = restTemplate.getForObject(
                    "http://restapi.amap.com/v3/assistant/coordinate/convert?locations={locations}&coordsys=baidu&key=30ee2dd2e553f7d6fbbbf9fe1ec361fd",
                    String.class, params);
            JSONObject json = JSON.parseObject(response);
            if (json.containsKey("status") && "1".equals(json.getString("status"))) {
                String[] locations = json.getString("locations").split(",");
                BigDecimal longitude = new BigDecimal(locations[0]);
                BigDecimal latitude = new BigDecimal(locations[1]);
                shopMapRepository.update(shopMap.getId(), longitude, latitude);
            }
        }
        return new ResponseEntity<>("update success!",  HttpStatus.OK);
    }
}

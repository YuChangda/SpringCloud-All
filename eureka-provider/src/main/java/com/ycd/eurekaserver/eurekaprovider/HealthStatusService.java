package com.ycd.eurekaserver.eurekaprovider;

import org.apache.logging.log4j.message.ReusableMessage;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * Created by ycd on 2020/6/26 10:39 上午
 */
@Service
public class HealthStatusService implements HealthIndicator {
    private Boolean status = true;

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public Health health() {
        if (status) {
            return new Health.Builder().up().build();
        }
        return new Health.Builder().down().build();

    }
    public String getStatus() {
        // TODO Auto-generated method stub
        return this.status.toString();
    }
}

package chengweiou.universe.wormhole.base.jwt;
// package chengweiou.universe.andromeda.base.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "jwt")
@Component
@Data
public class JwtConfig {
    private String issuer;
    // token，过期了无法请求
    private Long expMinute;
    private String sign;
    // refresh token，用来获得新的token
    private Long refreshExpMinute;
}

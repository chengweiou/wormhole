package chengweiou.universe.wormhole.base.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import chengweiou.universe.blackhole.util.GsonUtil;
import chengweiou.universe.blackhole.util.LogUtil;
import chengweiou.universe.wormhole.base.jwt.Account;
import chengweiou.universe.wormhole.base.jwt.JwtUtil;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GlobalFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest from = exchange.getRequest();
        ServerHttpRequest.Builder toBuilder = from.mutate();
        if (from.getHeaders().containsKey("Authorization")) {
            Account loginAccount = null;
            String token = null;
            try {
                String authorization = from.getHeaders().get("Authorization").get(0);
                token = authorization.substring("Bearer ".length());
                loginAccount = jwtUtil.verify(token);
            } catch (Exception e) {
                LogUtil.i("try to use unauth token: " + token);
            }

            if (loginAccount != null) {
                toBuilder.header("loginAccount", GsonUtil.create().toJson(loginAccount));
            }
        }
        ServerHttpRequest to = toBuilder.build();
        return chain.filter(exchange.mutate().request(to).build());
    }
}

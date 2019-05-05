package chengweiou.universe.wormhole.filter;


import chengweiou.universe.blackhole.util.LogUtil;
import chengweiou.universe.wormhole.init.jwt.config.Account;
import chengweiou.universe.wormhole.init.jwt.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthFilter implements GatewayFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        LogUtil.d("------------auth-------------");
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        Account account = Account.NULL;
        if (token != null
                && token.startsWith("Bearer ")) {
            account = jwtUtil.verify(token.substring("Bearer ".length()));
        }
        ServerHttpRequest.Builder builder = exchange.getRequest().mutate();
        if (!account.isNull()) builder.header("personId", account.getPersonId());
        ServerHttpRequest mutateRequest = builder.build();
        ServerWebExchange mutateExchange = exchange.mutate().request(mutateRequest).build();
        return chain.filter(mutateExchange);
    }
}

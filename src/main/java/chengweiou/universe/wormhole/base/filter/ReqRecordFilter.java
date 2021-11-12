package chengweiou.universe.wormhole.base.filter;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.wormhole.model.entity.ReqRecord;
import chengweiou.universe.wormhole.service.reqrecord.ReqRecordTask;
import eu.bitwalker.useragentutils.UserAgent;
import reactor.core.publisher.Mono;

@Component
public class ReqRecordFilter implements GlobalFilter {
    @Autowired
    private ReqRecordTask task;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest from = exchange.getRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(from.getHeaders().get("User-Agent").stream().collect(Collectors.joining(",")));
        // todo exception那边放一个
        ReqRecord e = Builder
            .set("url", from.getMethodValue() + " "+ from.getPath())
            .set("ip", from.getRemoteAddress().getAddress().getHostAddress())
            .set("duration", 0)
            .set("os", userAgent.getOperatingSystem().getName())
            .set("device", userAgent.getOperatingSystem().getDeviceType().toString())
            .set("browser", userAgent.getBrowser().getName() + " " + userAgent.getBrowserVersion()==null ? userAgent.getBrowserVersion().getVersion():"")
            .set("status", exchange.getResponse().getRawStatusCode())
            .to(new ReqRecord());

        long startTime = Instant.now().toEpochMilli();
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            long duration = Instant.now().toEpochMilli() - startTime;
            e.setDuration(duration);
            task.save(e);
        }));
    }

}

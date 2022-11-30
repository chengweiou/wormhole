package chengweiou.universe.wormhole.base.handler;

import java.nio.charset.StandardCharsets;

import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;

import chengweiou.universe.blackhole.model.BasicRestCode;
import chengweiou.universe.blackhole.model.Rest;
import chengweiou.universe.blackhole.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
@Slf4j
public class GlobalErrorWebExceptionHandler implements ErrorWebExceptionHandler {

  @Override
  public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
    ServerHttpResponse response = exchange.getResponse();
    Rest rest = Rest.fail(BasicRestCode.FAIL);
    String msg = (ex instanceof ResponseStatusException) ? ((ResponseStatusException) ex).getStatusCode().toString() + " " + ex.getMessage() : ex.getMessage();
    rest.setMessage(msg);
    String body = GsonUtil.create().toJson(rest);
    log.error(msg, ex);
    DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(StandardCharsets.UTF_8));
    return exchange.getResponse().writeWith(Mono.just(buffer));
  }

}

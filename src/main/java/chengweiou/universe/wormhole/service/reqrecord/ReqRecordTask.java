package chengweiou.universe.wormhole.service.reqrecord;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.wormhole.model.entity.ReqRecord;

@Component
public class ReqRecordTask {
    @Autowired
    private ReqRecordDio dio;
    // @Autowired
    // private UserAgentUtil userAgentUtil;
    // @Autowired
    // private JwtUtil jwtUtil;

    @Async
    public CompletableFuture<Boolean> save(ReqRecord e) {
        try {
            dio.save(e);
            return CompletableFuture.completedFuture(true);
        } catch (FailException ex) {
            return CompletableFuture.completedFuture(false);
        }
    }

}

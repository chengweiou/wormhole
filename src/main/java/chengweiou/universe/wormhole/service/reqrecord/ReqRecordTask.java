package chengweiou.universe.wormhole.service.reqrecord;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
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
    public Future<Boolean> save(ReqRecord e) {
        try {
            dio.save(e);
            return new AsyncResult<>(true);
        } catch (FailException ex) {
            return new AsyncResult<>(false);
        }
    }

}

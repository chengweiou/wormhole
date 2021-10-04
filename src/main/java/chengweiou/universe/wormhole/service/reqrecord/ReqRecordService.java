package chengweiou.universe.wormhole.service.reqrecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.wormhole.model.SearchCondition;
import chengweiou.universe.wormhole.model.entity.ReqRecord;

@Service
public class ReqRecordService {
    @Autowired
    private ReqRecordDio dio;

    public void save(ReqRecord e) throws FailException {
        dio.save(e);
    }

    public void delete(ReqRecord e) throws FailException {
        dio.delete(e);
    }

    public long update(ReqRecord e) {
        return dio.update(e);
    }

    public ReqRecord findById(ReqRecord e) {
        return dio.findById(e);
    }

    public long count(SearchCondition searchCondition, ReqRecord sample) {
        return dio.count(searchCondition, sample);
    }
    public List<ReqRecord> find(SearchCondition searchCondition, ReqRecord sample) {
        return dio.find(searchCondition, sample);
    }

}

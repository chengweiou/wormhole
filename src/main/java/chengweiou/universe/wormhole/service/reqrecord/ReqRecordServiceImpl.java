package chengweiou.universe.wormhole.service.reqrecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.wormhole.model.SearchCondition;
import chengweiou.universe.wormhole.model.entity.ReqRecord;

@Service
public class ReqRecordServiceImpl implements ReqRecordService {
    @Autowired
    private ReqRecordDio dio;

    @Override
    public void save(ReqRecord e) throws FailException {
        dio.save(e);
    }

    @Override
    public void delete(ReqRecord e) throws FailException {
        dio.delete(e);
    }

    @Override
    public long update(ReqRecord e) {
        return dio.update(e);
    }

    @Override
    public ReqRecord findById(ReqRecord e) {
        return dio.findById(e);
    }

    @Override
    public long count(SearchCondition searchCondition, ReqRecord sample) {
        return dio.count(searchCondition, sample);
    }
    @Override
    public List<ReqRecord> find(SearchCondition searchCondition, ReqRecord sample) {
        return dio.find(searchCondition, sample);
    }

}

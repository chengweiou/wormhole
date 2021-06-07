package chengweiou.universe.wormhole.service.reqrecord;

import java.util.List;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.wormhole.model.SearchCondition;
import chengweiou.universe.wormhole.model.entity.ReqRecord;

public interface ReqRecordService {
    void save(ReqRecord e) throws FailException;
    void delete(ReqRecord e) throws FailException;

    long update(ReqRecord e);

    ReqRecord findById(ReqRecord e);

    long count(SearchCondition searchCondition, ReqRecord sample);
    List<ReqRecord> find(SearchCondition searchCondition, ReqRecord sample);

}

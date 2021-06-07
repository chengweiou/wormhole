package chengweiou.universe.wormhole.service.reqrecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.wormhole.dao.ReqRecordDao;
import chengweiou.universe.wormhole.model.SearchCondition;
import chengweiou.universe.wormhole.model.entity.ReqRecord;

@Component
public class ReqRecordDio {
    @Autowired
    private ReqRecordDao dao;

    public void save(ReqRecord e) throws FailException {
        e.fillNotRequire();
        e.createAt();
        e.updateAt();
        long count = dao.save(e);
        if (count != 1) throw new FailException();
    }

    public void delete(ReqRecord e) throws FailException {
        long count = dao.delete(e);
        if (count != 1) throw new FailException();
    }

    public long update(ReqRecord e) {
        e.updateAt();
        return dao.update(e);
    }

    public ReqRecord findById(ReqRecord e) {
        ReqRecord result = dao.findById(e);
        return result != null ? result : ReqRecord.NULL;
    }

    public long count(SearchCondition searchCondition, ReqRecord sample) {
        return dao.count(searchCondition, sample);
    }
    public List<ReqRecord> find(SearchCondition searchCondition, ReqRecord sample) {
        searchCondition.setDefaultSort("updateAt");
        return dao.find(searchCondition, sample);
    }
}

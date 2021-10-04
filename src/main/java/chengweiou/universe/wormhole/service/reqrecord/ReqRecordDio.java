package chengweiou.universe.wormhole.service.reqrecord;

import java.util.List;
import java.util.stream.Collectors;

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
        ReqRecord.Dto dto = e.toDto();
        long count = dao.save(dto);
        if (count != 1) throw new FailException();
        e.setId(dto.getId());
    }

    public void delete(ReqRecord e) throws FailException {
        long count = dao.delete(e.toDto());
        if (count != 1) throw new FailException();
    }

    public long update(ReqRecord e) {
        e.updateAt();
        return dao.update(e.toDto());
    }

    public ReqRecord findById(ReqRecord e) {
        ReqRecord.Dto result = dao.findById(e.toDto());
        if (result == null) return ReqRecord.NULL;
        return result.toBean();
    }

    public long count(SearchCondition searchCondition, ReqRecord sample) {
        return dao.count(searchCondition, sample!=null ? sample.toDto() : null);
    }

    public List<ReqRecord> find(SearchCondition searchCondition, ReqRecord sample) {
        searchCondition.setDefaultSort("updateAt");
        List<ReqRecord.Dto> dtoList = dao.find(searchCondition, sample!=null ? sample.toDto() : null);
        List<ReqRecord> result = dtoList.stream().map(e -> e.toBean()).collect(Collectors.toList());
        return result;
    }

}

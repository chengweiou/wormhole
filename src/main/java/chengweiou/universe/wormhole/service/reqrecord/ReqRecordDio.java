package chengweiou.universe.wormhole.service.reqrecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chengweiou.universe.blackhole.dao.BaseDio;
import chengweiou.universe.blackhole.dao.BaseSQL;
import chengweiou.universe.blackhole.model.AbstractSearchCondition;
import chengweiou.universe.wormhole.dao.ReqRecordDao;
import chengweiou.universe.wormhole.model.entity.ReqRecord;
import chengweiou.universe.wormhole.model.entity.ReqRecord.Dto;

@Component
public class ReqRecordDio extends BaseDio<ReqRecord, ReqRecord.Dto> {
    @Autowired
    private ReqRecordDao dao;
    @Override
    protected ReqRecordDao getDao() { return dao; }
    @Override
    protected Class getTClass() { return ReqRecord.class; };
    @Override
    protected String getDefaultSort() { return "createAt"; };
    @Override
    protected String baseFind(AbstractSearchCondition searchCondition, Dto sample) {
        return new BaseSQL() {{
            if (searchCondition.getK() != null) WHERE("""
                (url LIKE #{searchCondition.like.k} or ip LIKE #{searchCondition.like.k})
                """);
                if (sample != null) {
                }
        }}.toString();
    }
}

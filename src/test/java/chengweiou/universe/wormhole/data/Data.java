package chengweiou.universe.wormhole.data;


import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chengweiou.universe.wormhole.model.SearchCondition;
import chengweiou.universe.wormhole.model.entity.ReqRecord;
import chengweiou.universe.wormhole.service.reqrecord.ReqRecordDio;

@Component
public class Data {

    @Autowired
    private ReqRecordDio reqRecordDio;
    public List<ReqRecord> reqRecordList;

    public void init() {
        reqRecordList = reqRecordDio.find(new SearchCondition(), null).stream().sorted(Comparator.comparingLong(ReqRecord::getId)).toList();
    }
}

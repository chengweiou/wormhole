package chengweiou.universe.wormhole.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import chengweiou.universe.wormhole.base.dao.BaseDao;
import chengweiou.universe.wormhole.model.entity.ReqRecord.Dto;

@Repository
@Mapper
public interface ReqRecordDao extends BaseDao<Dto> {

    class Sql {
    }
}

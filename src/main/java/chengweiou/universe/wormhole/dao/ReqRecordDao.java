package chengweiou.universe.wormhole.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import chengweiou.universe.wormhole.base.dao.BaseDao;
import chengweiou.universe.wormhole.model.SearchCondition;
import chengweiou.universe.wormhole.model.entity.ReqRecord.Dto;

@Repository
@Mapper
public interface ReqRecordDao extends BaseDao<Dto> {
    @SelectProvider(type = Sql.class, method = "count")
    long count(@Param("searchCondition") SearchCondition searchCondition, @Param("sample") Dto sample);

    @SelectProvider(type = Sql.class, method = "find")
    List<Dto> find(@Param("searchCondition") SearchCondition searchCondition, @Param("sample") Dto sample);

    class Sql {

        public String count(@Param("searchCondition") SearchCondition searchCondition, @Param("sample") Dto sample) {
            return baseFind(searchCondition, sample).SELECT("count(*)").toString();
        }
        public String find(@Param("searchCondition") SearchCondition searchCondition, @Param("sample") Dto sample) {
            return baseFind(searchCondition, sample).SELECT("*").toString().concat(searchCondition.getOrderBy()).concat(searchCondition.getSqlLimit());
        }
        private SQL baseFind(SearchCondition searchCondition, Dto sample) {
            return new SQL() {{
                FROM("reqRecord");
                if (searchCondition.getK() != null) WHERE("url LIKE #{searchCondition.like.k}")
                        .OR().WHERE("ip LIKE #{searchCondition.like.k}");
                if (sample != null) {
                }
            }};
        }
    }
}

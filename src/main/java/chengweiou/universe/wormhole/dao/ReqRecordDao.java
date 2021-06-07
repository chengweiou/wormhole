package chengweiou.universe.wormhole.dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import chengweiou.universe.wormhole.model.SearchCondition;
import chengweiou.universe.wormhole.model.entity.ReqRecord;

@Repository
@Mapper
public interface ReqRecordDao {
    @Insert("insert into reqRecord(url, ip, duration, os, device, browser, status, createAt, updateAt) " +
            "values(#{url}, #{ip}, #{duration}, #{os}, #{device}, #{browser}, #{status}, #{createAt}, #{updateAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    long save(ReqRecord e);

    @Delete("delete from reqRecord where id=#{id}")
    long delete(ReqRecord e);

    @UpdateProvider(type = Sql.class, method = "update")
    long update(ReqRecord e);

    @Select("select * from reqRecord where id=#{id}")
    ReqRecord findById(ReqRecord e);

    @SelectProvider(type = Sql.class, method = "count")
    long count(@Param("searchCondition") SearchCondition searchCondition, @Param("sample") ReqRecord sample);

    @SelectProvider(type = Sql.class, method = "find")
    List<ReqRecord> find(@Param("searchCondition") SearchCondition searchCondition, @Param("sample") ReqRecord sample);

    class Sql {

        public String update(final ReqRecord e) {
            return new SQL() {{
                UPDATE("reqRecord");
                if (e.getUrl() != null) SET("url = #{url}");
                if (e.getIp() != null) SET("ip = #{ip}");
                if (e.getDuration() != null) SET("duration = #{duration}");
                if (e.getOs() != null) SET("os = #{os}");
                if (e.getDevice() != null) SET("device = #{device}");
                if (e.getBrowser() != null) SET("browser = #{browser}");
                if (e.getStatus() != null) SET("status = #{status}");
                SET("updateAt = #{updateAt}");
                WHERE("id=#{id}");
            }}.toString();
        }

        public String count(@Param("searchCondition")final SearchCondition searchCondition, @Param("sample")final ReqRecord sample) {
            return new SQL() {{
                SELECT("count(*)"); FROM("reqRecord");
                if (searchCondition.getK() != null) WHERE("url LIKE #{searchCondition.like.k}")
                        .OR().WHERE("ip LIKE #{searchCondition.like.k}");
                if (sample != null) {
                }
            }}.toString();
        }

        public String find(@Param("searchCondition")final SearchCondition searchCondition, @Param("sample")final ReqRecord sample) {
            return new SQL() {{
                SELECT("*"); FROM("reqRecord");
                if (searchCondition.getK() != null) WHERE("url LIKE #{searchCondition.like.k}")
                        .OR().WHERE("ip LIKE #{searchCondition.like.k}");
                if (sample != null) {
                }
            }}.toString().concat(searchCondition.getOrderBy()).concat(searchCondition.getSqlLimit());
        }
    }
}

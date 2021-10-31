package chengweiou.universe.wormhole.model.entity;

import org.springframework.beans.BeanUtils;

import chengweiou.universe.blackhole.model.NullObj;
import chengweiou.universe.blackhole.model.entity.DtoEntity;
import chengweiou.universe.blackhole.model.entity.ServiceEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ReqRecord extends ServiceEntity {
    private String url;
    private String ip;
    private Long duration;
    private String os;
    private String device;
    private String browser;
    private String status;

    public void fillNotRequire() {
        duration = duration!=null ? duration : 0;
        os = os!=null ? os : "";
        device = device!=null ? device : "";
        browser = browser!=null ? browser : "";
        status = status!=null ? status : "";
    }

    public static final ReqRecord NULL = new Null();
    private static class Null extends ReqRecord implements NullObj {
    }
    public Dto toDto() {
        Dto result = new Dto();
        BeanUtils.copyProperties(this, result);
        return result;
    }
    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Dto extends DtoEntity {
        private String url;
        private String ip;
        private Long duration;
        private String os;
        private String device;
        private String browser;
        private String status;

        public ReqRecord toBean() {
            ReqRecord result = new ReqRecord();
            BeanUtils.copyProperties(this, result);
            return result;
        }
    }
}

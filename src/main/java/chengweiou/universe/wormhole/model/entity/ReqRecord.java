package chengweiou.universe.wormhole.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;

import chengweiou.universe.blackhole.model.NotNullObj;
import chengweiou.universe.blackhole.model.NullObj;
import lombok.Data;

@Data
public class ReqRecord implements NotNullObj, Serializable {
    private Long id;
    private String url;
    private String ip;
    private Long duration;
    private String os;
    private String device;
    private String browser;
    private String status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public void fillNotRequire() {
        duration = duration!=null ? duration : 0;
        os = os!=null ? os : "";
        device = device!=null ? device : "";
        browser = browser!=null ? browser : "";
        status = status!=null ? status : "";
    }

    public void createAt() {
        createAt = LocalDateTime.now(ZoneId.of("UTC"));
    }
    public void updateAt() {
        updateAt = LocalDateTime.now(ZoneId.of("UTC"));
    }

    public static final ReqRecord NULL = new Null();
    private static class Null extends ReqRecord implements NullObj {
    }
}

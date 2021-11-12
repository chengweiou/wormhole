package chengweiou.universe.wormhole.base.jwt;


import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.blackhole.model.NotNullObj;
import chengweiou.universe.blackhole.model.NullObj;
import lombok.Data;

@Data
public class Account implements NotNullObj, Serializable {
    private Long id;
    private AccountType type;
    private String username;
    @JsonIgnore
    private String password;
    private Person person;
    private Boolean active;
    private String extra;
    private Instant createAt;
    private Instant updateAt;
    public void fillNotRequire() {
        type = type!=null ? type : AccountType.NORMAL;
        active = active!=null ? active : person != null;
        person = person!=null ? person : Builder.set("id", 0).to(new Person());
        extra = extra!=null ? extra : "";
    }

    public void createAt() {
        createAt = Instant.now();
    }
    public void updateAt() {
        updateAt = Instant.now();
    }

    public static final Account NULL = new Null();
    private static class Null extends Account implements NullObj {
    }
}

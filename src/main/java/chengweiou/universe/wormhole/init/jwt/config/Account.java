package chengweiou.universe.wormhole.init.jwt.config;


import chengweiou.universe.blackhole.model.NotNullObj;
import chengweiou.universe.blackhole.model.NullObj;

import java.io.Serializable;

public class Account implements NotNullObj, Serializable {
    private Long id;
    private String personId;
    private String extra;
    public static final Account NULL = new Null();
    private static class Null extends Account implements NullObj {
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", personId='" + personId + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}

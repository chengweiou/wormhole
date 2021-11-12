package chengweiou.universe.wormhole.base.jwt;

import java.io.Serializable;

import chengweiou.universe.blackhole.model.NotNullObj;
import chengweiou.universe.blackhole.model.NullObj;
import lombok.Data;

@Data
public class Person implements NotNullObj, Serializable {
    private Long id;
    public static final Person NULL = new Person.Null();
    public static class Null extends Person implements NullObj {
    }
}

package chengweiou.universe.wormhole.model;

import java.util.List;

import chengweiou.universe.blackhole.model.AbstractSearchCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SearchCondition extends AbstractSearchCondition {
    private List<String> accountIdList;
}

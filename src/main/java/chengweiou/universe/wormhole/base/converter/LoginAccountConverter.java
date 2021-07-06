package chengweiou.universe.wormhole.base.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import chengweiou.universe.blackhole.util.GsonUtil;
import chengweiou.universe.wormhole.base.jwt.Account;

@Component
public class LoginAccountConverter implements Converter<String, Account> {
    @Override
    public Account convert(String source) {
        return GsonUtil.create().fromJson(source, Account.class);
    }
}

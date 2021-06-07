package chengweiou.universe.wormhole.base.converter;


import com.google.gson.Gson;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import chengweiou.universe.wormhole.base.jwt.Account;

@Component
public class LoginAccountConverter implements Converter<String, Account> {
    @Override
    public Account convert(String source) {
        return new Gson().fromJson(source, Account.class);
    }
}
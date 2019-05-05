package chengweiou.universe.wormhole.init.jwt.config;

import chengweiou.universe.blackhole.model.Builder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Autowired
    private JwtConfig config;

    public Account verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(config.getSign());
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(config.getIssuer())
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            jwt.getClaim("");
            return Builder
                    .set("personId", jwt.getClaim("personId").asString())
                    .set("extra", jwt.getClaim("extra").asString())
                    .to(new Account());
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            return Account.NULL;
        }
    }
}

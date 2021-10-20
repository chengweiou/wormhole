package chengweiou.universe.wormhole.base.jwt;

import java.io.IOException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.annotation.PostConstruct;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import chengweiou.universe.blackhole.exception.UnauthException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.blackhole.util.LogUtil;

@Component
public class JwtUtil {
    @Autowired
    private JwtConfig config;


    public String sign(Account account) {
        Algorithm algorithm = useRsa ? Algorithm.RSA256(rsaPublicKey, rsaPrivateKey) : Algorithm.HMAC512(config.getSign());
        return sign(account, algorithm);
    }
    private String sign(Account account, Algorithm algorithm) {
        try {
            Date expiresAt = Date.from(LocalDateTime.now(ZoneId.of("UTC")).plus(config.getExpMinute(), ChronoUnit.MINUTES).atZone(ZoneId.of("UTC")).toInstant());
            return JWT.create()
                .withIssuer(config.getIssuer())
                .withClaim("personId", account.getPerson().getId())
                .withClaim("extra", account.getExtra())
                .withExpiresAt(expiresAt)
                .sign(algorithm);
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            LogUtil.e("fail to sign jwt", exception);
            return "";
        }
    }

    public Account verify(String token) throws UnauthException {
        if (token == null) throw new UnauthException();
        Algorithm algorithm = useRsa ? Algorithm.RSA256(rsaPublicKey, rsaPrivateKey) : Algorithm.HMAC512(config.getSign());
        return verify(token, algorithm);
    }
    private Account verify(String token, Algorithm algorithm) throws UnauthException {
        try {
            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(config.getIssuer())
                .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return Builder
                    .set("person", Builder.set("id", jwt.getClaim("personId").asLong()).to(new Person()))
                    .set("extra", jwt.getClaim("extra").asString())
                    .to(new Account());
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            throw new UnauthException();
        }
    }

    private boolean useRsa;
    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;
    /**
     * now using rsa generate from https://www.devglan.com/online-tools/rsa-encryption-decryption
     * other website generate is not working, maybe because of the format
     * By default, the private key is generated in PKCS#8 format and the public key is generated in X.509 format.
     */
    @PostConstruct
    public void init() throws IOException {
        useRsa = !config.getRsaPublicPath().isBlank() && !config.getRsaPrivatePath().isBlank();
        if (useRsa) {
            try {
                rsaPublicKey = (RSAPublicKey) JwtPemUtils.readPublicKeyFromFile(config.getRsaPublicPath(), "RSA");
                rsaPrivateKey = (RSAPrivateKey) JwtPemUtils.readPrivateKeyFromFile(config.getRsaPrivatePath(), "RSA");
            } catch (IOException exception) {
                LogUtil.e("fail to read jwt public | private key file.", exception);
                throw exception;
            }
        }
    }
}

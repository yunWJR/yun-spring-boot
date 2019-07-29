package com.yun.util.token;

import com.yun.util.common.StringUtil;
import com.yun.util.module.rsp.RspDataCodeType;
import com.yun.util.module.rsp.RspDataException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Decoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Date;

/**
 * The itemType Auth token util tokenParam.
 * @author: yun
 * @createdOn: 2018 /6/4 20:24.
 */
public class AuthTokenUtil {

    // region --Field

    private AuthTokenParam tokenParam;

    // endregion

    // region --Constructor

    /**
     * Instantiates a new Jwt tokenParam.
     */
    public AuthTokenUtil() {
    }

    /**
     * Instantiates a new Jwt tokenParam.
     * @param tokenParam the tokenParam
     */
    @Autowired
    public AuthTokenUtil(AuthTokenParam tokenParam) {
        this.tokenParam = tokenParam;
    }

    // endregion

    // region --static method

    // endregion   

    // region --Getter and Setter

    /**
     * Gets tokenParam.
     * @return the tokenParam
     */
    public AuthTokenParam getTokenParam() {
        return tokenParam;
    }

    // endregion

    // region --Public method

    /**
     * Sets tokenParam.
     * @param tokenParam the tokenParam
     */
    public void setTokenParam(AuthTokenParam tokenParam) {
        this.tokenParam = tokenParam;
    }

    /**
     * Create token string.
     * @param userId the userservice id
     * @return the string
     */
    public String createToken(String userId) {
        return this.createToken(new AuthTokenPayload(userId));
    }

    /**
     * Create token string.
     * @param payload the payload
     * @return the string
     */
    public String createToken(AuthTokenPayload payload) {
        return this.createToken(payload, tokenParam.getTokenIssuer(), tokenParam.getSecretKey(), tokenParam.getTtlMillis());
    }

    /**
     * Is valid token base rst bean.
     * @param token the token
     * @return the base rst bean
     */
    public AuthTokenPayload getValidToken(String token) {
        return getValidToken(token, tokenParam.getSecretKey());
    }

    // endregion

    // region --private method
    private String createToken(AuthTokenPayload payload, String issuer, String secretKey, long ttlMillis) {
        try {
            //指定签名的时候使用的签名算法，也就是header那部分。
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            // 生成JWT的时间
            long nowMillis = System.currentTimeMillis();
            Date now = new Date(nowMillis);

            // 加密 key
            SecretKey key = generalKey(secretKey);

            // builder，默认压缩
            JwtBuilder builder = Jwts
                    .builder()
                    .compressWith(CompressionCodecs.DEFLATE);

            // 添加自定义 claims
            // 自定义 claims，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的claim
            if (payload.getClaims() != null) {
                builder = builder.setClaims(payload.getClaims());
            }

            // 添加 userId 为 subject
            // //sub(Subject)：代表这个JWT的主体，即它的所有人
            if (payload.getUserId().length() > 0) {
                builder = builder.setSubject(payload.getUserId());
            }

            // 添加其他参数
            builder = builder
                    // .setId(null) //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                    .setIssuedAt(now) // iat: jwt的签发时间
                    .setAudience(issuer)
                    .signWith(signatureAlgorithm, key); // 设置签名使用的签名算法和签名使用的秘钥

            // 添加过期时间
            if (ttlMillis > 0) {
                long expMillis = nowMillis + ttlMillis;
                Date exp = new Date(expMillis);
                builder.setExpiration(exp);     //设置过期时间
            }

            // 生成 token
            String token = builder.compact();

            // 生成失败
            if (StringUtil.isNullOrEmpty(token)) {
                throw RspDataException.RstTypeErrBeanWithType(RspDataCodeType.TokenUnknown);
            }

            return token;
        } catch (Exception e) {
            e.printStackTrace();
            // 生成失败
            throw RspDataException.RstTypeErrBeanWithType(RspDataCodeType.TokenUnknown);
        }
    }

    private SecretKey generalKey(String key) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] encodedKey = decoder.decodeBuffer(key);
        SecretKey sKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return sKey;
    }

    private AuthTokenPayload getValidToken(String token, String secretKey) {
        try {
            SecretKey key = generalKey(secretKey);

            Jws<Claims> claims = Jwts
                    .parser() //得到DefaultJwtParser
                    .setSigningKey(key) //设置签名的秘钥
                    .parseClaimsJws(token); //设置需要解析的jwt

            // Algorithm algorithm = Algorithm.HMAC256(secretKey);
            //
            // JWTVerifier verifier = JWT.require(algorithm)
            //         .build();
            // DecodedJWT jwt = verifier.verify(token);

            if (claims != null) {
                AuthTokenPayload payload = new AuthTokenPayload(claims.getBody().getSubject(), claims.getBody());

                return payload;
            } else {
                throw RspDataException.RstTypeErrBeanWithType(RspDataCodeType.TokenError);
            }
        } catch (ExpiredJwtException expT) {
            // token过期
            throw RspDataException.RstTypeErrBeanWithType(RspDataCodeType.TokenExp);
        } catch (Exception e) {
            throw RspDataException.RstTypeErrBeanWithType(RspDataCodeType.TokenError);
        }
    }

    // endregion

    // region --Other

    // endregion
}

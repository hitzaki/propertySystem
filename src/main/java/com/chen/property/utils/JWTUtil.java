package com.chen.property.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chen.property.pojo.Info;

import java.util.Date;

public class JWTUtil {
    // 加盐、过期时间3天
    private static final String key = "I'll lose if I can crack it";
    private static long add = 3*24*3600*1000;


    // *****新建一个token
    public static String createToken(Info info){
        String token = JWT.create()
                .withClaim("ac", info.getAccount()) //有效负载
                .withClaim("na", info.getName())
                .withClaim("ty", info.getType())
                .withClaim("ph", info.getPhoto())
                .withExpiresAt(new Date(System.currentTimeMillis()+add)) //过期时间
                .sign(Algorithm.HMAC256(key)); //签名
        // 使用提供的算法类Algorithm.* 各种加密算法来进行, 然后添加一个密钥就可以了
        return token;
    }

    // *****从token中取数据，过期或错误等都返回null
    public static Info getInfo(String token){
        Info info = new Info();
        try {
            if(token==null)return null;
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256("!HMAC256 expression")).build();
            DecodedJWT verify = verifier.verify(token);
            info.setAccount(verify.getClaim("ac").asString());
            info.setName(verify.getClaim("na").asString());
            info.setType(verify.getClaim("ty").asString());
            info.setPhoto(verify.getClaim("ph").asString());
        } catch (Exception e) {
            return null;
        }
        return info;
    }

}

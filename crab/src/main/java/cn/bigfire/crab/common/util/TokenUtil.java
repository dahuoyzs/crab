package cn.bigfire.crab.common.util;

import cn.bigfire.crab.common.exception.SysException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/9/27  16:44
 * @ Desc   ：简单的token工具类
 * 依赖：com.auth0.java.jwt
 <dependency>
 <groupId>com.auth0</groupId>
 <artifactId>java-jwt</artifactId>
 <version>3.4.0</version>
 </dependency>
 */
public class TokenUtil {

    private static final String SECRET = "SESSION_SECRET_A1B2C3";//加密时用到的key

    private static final Logger logger = LoggerFactory.getLogger(TokenUtil.class);
    /**
     * 时间常量 单位秒
     */
    public static final int ONE_DAY_EXPIRE = 86400;        //1天
    public static final int ONE_WEEK_EXPIRE = 604800;      //7天
    public static final int ONE_MONTH_EXPIRE = 2592000;    //30天
    public static final int THREE_MONTH_EXPIRE = 7776000;  //90天
    /**
     * 根据一个字符串生成token 有效期默认7天
     * @param data         要生成token的附带信息
     * @return String      返回token
     */
    public static String createToken(Object data) {
        return createToken(data,ONE_WEEK_EXPIRE);
    }
    /**
     * 根据一个字符串生成token
     * @param data         要生成token的附带信息
     * @param expire       有效期时间 单位秒
     * @return String      返回token
     */
    public static String createToken(Object data, int expire) {

            Date expireDate = new Date(System.currentTimeMillis() + expire*1000);
            JWTCreator.Builder builder = JWT.create()
                    .withExpiresAt(expireDate)
                    .withClaim("data",data.toString())
                    .withClaim("alg", "HS256")
                    .withClaim("typ", "JWT")
                    .withClaim("isExpired","false");
            return builder.sign(Algorithm.HMAC256(SECRET)); //使用上面的加密算法进行签名，返回String，就是token
    }
    /**
     * 根据token解码出原字符串
     * @param token        要解码的token
     * @return String      返回原字符串信息
     */
    public static String verifyToken(String token) {
        try{
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims().get("data").asString();
        }catch (TokenExpiredException e){
//            e.printStackTrace();
            throw new SysException("token失效,请重新授权");
        }catch (JWTDecodeException e){
//            e.printStackTrace();
            throw new SysException("token错误,请重新授权");
        }
    }

    /**
     * 根据token解码出原信息,并转换成Long类型
     * @param token         要解码的token
     * @return Long         返回Long类型的原信息
     */
    public static Long getId(String token) {
        return Long.valueOf(verifyToken(token));
    }

    /**
     * 校验token是否失效
     * @param token         要校验的token
     * @return boolean      返回是否失效 true失效,false有效
     */
    public static boolean isTokenExpired(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return false;
        }catch (Exception e){
            logger.error(Console.where() + e.getMessage());
            Console.printStackTrace(e);
           return true;
        }
    }

}

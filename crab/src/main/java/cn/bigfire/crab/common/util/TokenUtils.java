package cn.bigfire.crab.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @ IDE    ：IntelliJ IDEA.
 * @ Date   ：2019/9/27  16:44
 * @ Desc   ：简单的token工具类
 * 依赖：io.jsonwebtoken.jjwt
 <dependency>
 <groupId>io.jsonwebtoken</groupId>
 <artifactId>jjwt</artifactId>
 <version>0.7.0</version>
 </dependency>
 */
public class TokenUtils {

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
    public static String createToken(Object data,int expire) {
        Date expireDate = new Date(new Date().getTime() + expire*1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(data.toString())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
    /**
     * 根据token解码出原字符串
     * @param token        要解码的token
     * @return String      返回原字符串信息
     */
    public static String verifyToken(String token) {
        Claims claims = getClaimByToken(token);
        return  claims.getSubject();
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
        return getClaimByToken(token).getExpiration().before(new Date());
    }
    public static Claims getClaimByToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



}

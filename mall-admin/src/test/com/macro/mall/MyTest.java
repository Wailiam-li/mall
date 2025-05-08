package com.macro.mall;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

/**
 * @author Liweiwei
 * @Date 2025/5/8 12:15
 * @Description :
 */
@SpringBootTest
public class MyTest {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    /**
     *  根据生成的token反解析得到用户相关信息
     */
    @Test
    public void  test(){
        String token1="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImNyZWF0ZWQiOjE3NDY2MjEyODIyNTksImV4cCI6MTc0NzIyNjA4Mn0.M9Wr-YDqtNkhAg35qzzOqqCccLLYWCxieqaHfMKGnPK2gErmMfCA1V_SrMVAQuNxyietvNBMEHS4g4sEfJv5aA";
        Jwt parse = Jwts.parser().setSigningKey(secret).parse(token1);
        System.out.println((Map<String, Object>) parse.getBody());
    }



    /**  PasswordEncoder的加密
     * PasswordEncoder 是 Spring Security 提供的一个接口，它定义了密码加密的方法。具体的加密算法是由其实现类决定的。常见的实现类有：
     * BCryptPasswordEncoder：使用 BCrypt 算法进行密码加密。
     * NoOpPasswordEncoder：不进行任何加密，直接返回原始密码（不推荐用于生产环境）。
     * Pbkdf2PasswordEncoder：使用 PBKDF2 算法进行密码加密。
     * SCryptPasswordEncoder：使用 SCrypt 算法进行密码加密。
     * 在实际开发中，最常用的是 BCryptPasswordEncoder，因为它提供了良好的安全性，并且能够自动处理盐值（salt）和哈希迭代次数。
     * 以下为 BCryptPasswordEncoder 的使用示例：
     */
    @Test
    public void  test2(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 原始密码
        String rawPassword = "123456";

        // 注 BCrypt 算法在加密过程中会自动引入随机“盐值（salt）”。这个盐值每次加密时都不同，从而导致最终生成的哈希值也不同，因此每次相同的字符串生成的密码串会不一样。
        String encodedPassword = passwordEncoder.encode(rawPassword);

        // 输出加密后的密码
        System.out.println("加密后的密码: " + encodedPassword);

        // 验证原始密码与加密后的密码是否匹配
        boolean isMatch = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("密码是否匹配: " + isMatch);
    }

@Test
    public void  test3(){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    String rawPassword = "123456";

    String hash1 = encoder.encode(rawPassword);
    String hash2 = encoder.encode(rawPassword);

    System.out.println(hash1); // 输出：$2a$10$abc... （假设）
    System.out.println(hash2); // 输出：$2a$10$xyz... （完全不同）

}

}

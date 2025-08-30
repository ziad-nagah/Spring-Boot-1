package com.eraasoft.spring.service.token;

import com.eraasoft.spring.help.JWTToken;
import com.eraasoft.spring.service.AccountService;
import com.eraasoft.spring.service.DTO.AccountDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class TokenHandler {
//    @Value("${token.secret}")
    private String secretKey;
//    @Value("${token.time}")
    private Duration time;
    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;

    public TokenHandler(JWTToken jwtToken) {
        secretKey = jwtToken.getSecret();
        time = jwtToken.getTime();
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        jwtBuilder = Jwts.builder().signWith(key);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }
    @Autowired
    private AccountService accountService;
    public String createToken(AccountDTO accountDTO){
        Date issueDate = new Date();
        Date exepirationDate = Date.from(issueDate.toInstant().plus(time));
        jwtBuilder.setSubject(accountDTO.getUserName());
        jwtBuilder.setIssuedAt(issueDate);
        jwtBuilder.setExpiration(exepirationDate);
        jwtBuilder.claim("address",accountDTO.getAddress());
        jwtBuilder.claim("roles",accountDTO.getRoles().stream().map(roleDTO -> roleDTO.getRoleName()).collect(Collectors.toList()));

        return jwtBuilder.compact();

    }
    public  AccountDTO validateToken(String token){
        if (jwtParser.isSigned(token)){
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            String userName = claims.getSubject();
            Date issueDate = claims.getIssuedAt();
            Date exepirationDate = claims.getExpiration();
           AccountDTO accountDTO =  accountService.getByUserName(userName);

            boolean isValidToken =  Objects.nonNull(accountDTO) &&
                                    exepirationDate.after(new Date()) &&
                                    issueDate.before(exepirationDate);
            if (!isValidToken){
                return null;
            }
            return accountDTO;

        }
        return null;
    }
}

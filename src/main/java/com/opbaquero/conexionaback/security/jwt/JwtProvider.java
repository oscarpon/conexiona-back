package com.opbaquero.conexionaback.security.jwt;

import com.nimbusds.jwt.*;
import com.opbaquero.conexionaback.security.dto.JwtDto;
import com.opbaquero.conexionaback.security.entity.PrincipalUser;
import com.opbaquero.conexionaback.security.entity.User;
import com.opbaquero.conexionaback.security.service.UserService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Autowired
    UserService userService = new UserService();

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        List<String> roles = principalUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        User userAct = userService.findByUserName(principalUser.getUsername());


        return Jwts.builder()
                .setSubject(principalUser.getUsername())
                .claim("roles", roles)
                .claim("account", userAct.getAccount().getId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public String getNombreUsuarioFromToken(String token){
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("token mal formado");
        }catch (UnsupportedJwtException e){
            logger.error("token no soportado");
        }catch (ExpiredJwtException e){
            logger.error("token expirado");
        }catch (IllegalArgumentException e){
            logger.error("token vacío");
        }catch (SignatureException e){
            logger.error("fail en la firma");
        }
        return false;
    }

    public String refreshToken(JwtDto jwtDto) throws ParseException {
        try{
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(jwtDto.getToken());
        }catch (ExpiredJwtException e){
            com.nimbusds.jwt.JWT jwt = JWTParser.parse(jwtDto.getToken());
            JWTClaimsSet claimsSet = jwt.getJWTClaimsSet();
            String userName = claimsSet.getSubject();
            List<String> roles = (List<String>) claimsSet.getClaim("roles");
            String account = (String) claimsSet.getClaim("account");

            return Jwts.builder()
                    .setSubject(userName)
                    .claim("roles", roles)
                    .claim("account", account)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expiration))
                    .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                    .compact();
        }
        return null;
    }

}

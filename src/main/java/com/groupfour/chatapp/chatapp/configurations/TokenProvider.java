package com.groupfour.chatapp.chatapp.configurations;

import com.groupfour.chatapp.chatapp.models.Constants;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <SomeType> SomeType getClaimFromToken(String token, Function<Claims, SomeType> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(Constants.SIGNING_KEY.getValue())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }


    public String generateToken(Authentication authentication) {
        final String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(Constants.AUTHORITIES_KEY.getValue(), authorities)
                .signWith(SignatureAlgorithm.HS256, Constants.SIGNING_KEY.getValue())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Constants.ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userName = getUserNameFromToken(token);
        return (
                userName.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }

    UsernamePasswordAuthenticationToken getAuthentication(final String token, final Authentication existingAuth, final UserDetails userDetails) {
        final JwtParser jwtParser = Jwts.parser().setSigningKey(Constants.SIGNING_KEY.getValue());
        final Jws<Claims> claimsJws = jwtParser.parseClaimsJws(token);
        final Claims claims = claimsJws.getBody();

        final Collection<? extends GrantedAuthority> authorities =
                Arrays
                        .stream(claims.get(Constants.AUTHORITES_KEY.getValue()).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

}

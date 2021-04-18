package ma.GymPro.config;

import io.jsonwebtoken.*;
import ma.GymPro.beans.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    //Generate the token
    @Value("${security.expiration_time}")
    private Integer EXPIRATION_TIME;
    @Value("${security.secret}")
    private String SECRET;
    public String generateToken(Authentication authentication){
        User user = (User)authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance(); // gets a calendar using the default time zone and locale.
        calendar.add(Calendar.MINUTE, EXPIRATION_TIME);
        Date expiryDate = calendar.getTime();



        String userId = Long.toString(user.getId());

        Map<String,Object> claims = new HashMap<>();
        claims.put("id", (Long.toString(user.getId())));
        claims.put("username", user.getProfil().getPrenom());
        claims.put("nom", user.getProfil().getNom());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    //Validate the token
    public boolean validateToken(String token, HttpServletRequest httpServletRequest){
        try{
             Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            System.out.println("Invalid JWT Signature");
            httpServletRequest.setAttribute("valid",ex.getMessage());
        }catch (MalformedJwtException ex){
            System.out.println("Invalid JWT Token");
            httpServletRequest.setAttribute("expired",ex.getMessage());
        }catch (ExpiredJwtException ex){
            System.out.println("Expired JWT token");
        }catch (UnsupportedJwtException ex){
            System.out.println("Unsupported JWT token");
        }catch (IllegalArgumentException ex){
            System.out.println("JWT claims string is empty");
        }
        return false;
    }


    //Get user Id from token

    public Long getUserIdFromJWT(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");
        Principal principal;

        return Long.parseLong(id);
    }
}

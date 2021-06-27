package ma.GymPro.config.websocket;

import ma.GymPro.beans.User;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HTTPHandshakeInterceptor implements HandshakeInterceptor {


    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
        if(request.getPrincipal()!=null)
            System.out.println("BEfore "+request.getPrincipal().getName());

        return true;
    }

    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        if(request.getPrincipal().getName()!=null)
            System.out.println(request.getPrincipal().getName());
    }

    /*public Principal setPrincipal(ServerHttpRequest request){
        String url = request.getURI().toString();
        Pattern p = Pattern.compile("[&?]token=([^&\\r\\n]*)");
        Matcher matcher = p.matcher(url);
        if (matcher.find())
        {
            Optional<User> optionalUser = Optional.ofNullable(jwt.validate(matcher.group(1)));
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList(user.getRole());
                user.setName(user.getUsername());
                return new UsernamePasswordAuthenticationToken(user.getName(), null, grantedAuthorities);
            }
        }
        return null;
    }
*/

}

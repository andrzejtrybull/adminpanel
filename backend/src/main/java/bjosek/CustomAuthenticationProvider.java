package bjosek;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    public boolean doAuthenticate(String name, String pass) {
        if ( name.equals("admin") && pass.equals("qqq")) {
            return true;
        }
        return false;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String name = authentication.getName();
        String pass = authentication.getCredentials().toString();

        System.out.println("authentication : " + name + " : " + pass);
        //tutaj albo należałoby ściągnąć z bazy użytkownika name i porównać jego pass
        //albo lepiej uruchomić metodę która to zrobi i zwróci zezwolenie na authentykacje
        if (doAuthenticate(name, pass)) {
            return new UsernamePasswordAuthenticationToken(name, pass, new ArrayList<>());
        } else {
            return null;
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

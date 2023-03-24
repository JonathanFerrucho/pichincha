package pichincha;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import pichincha.security.AuthenticationReq;
import pichincha.security.TokenInfo;
import pichincha.service.JwtUtilService;

@Log
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "publico", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicoService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService usuarioDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @PostMapping("/authenticate")
    public ResponseEntity<TokenInfo> authenticate(
            @RequestBody AuthenticationReq authenticationReq) {


        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
                        authenticationReq.getClave()));

        final UserDetails userDetails = usuarioDetailsService
                .loadUserByUsername(authenticationReq.getUsuario());

        final String jwt = jwtUtilService.generateToken(userDetails);

        TokenInfo tokenInfo = new TokenInfo(jwt);

        return ResponseEntity.ok(tokenInfo);
    }

}

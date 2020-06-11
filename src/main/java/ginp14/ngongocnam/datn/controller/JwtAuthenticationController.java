package ginp14.ngongocnam.datn.controller;

import ginp14.ngongocnam.datn.model.JwtRequest;
import ginp14.ngongocnam.datn.model.JwtResponse;
import ginp14.ngongocnam.datn.model.UserCriteria;
import ginp14.ngongocnam.datn.service.UserServiceImpl;
import ginp14.ngongocnam.datn.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private UserServiceImpl userServiceImpl;


    @PostMapping("/jwt/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userServiceImpl
                .loadUserByUsername(authenticationRequest.getUsername());

        if (userDetails == null) {
            return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        }

        if(userServiceImpl.findByUsername(authenticationRequest.getUsername()).getRole().getId() != 3) {
            return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
        }

        final String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse("Bearer " + token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

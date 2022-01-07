package com.opbaquero.conexionaback.security.service;

import com.opbaquero.conexionaback.security.entity.User;
import com.opbaquero.conexionaback.security.entity.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        User user = userService.getByNombreUsuario(nombreUsuario).get();
        return PrincipalUser.build(user);
    }
}

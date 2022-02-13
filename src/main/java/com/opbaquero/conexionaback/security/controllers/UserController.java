package com.opbaquero.conexionaback.security.controllers;

import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.models.service.dto.EmailDTO;
import com.opbaquero.conexionaback.models.service.interfaces.IAccountService;
import com.opbaquero.conexionaback.models.service.interfaces.IEmailService;
import com.opbaquero.conexionaback.security.dto.JwtDto;
import com.opbaquero.conexionaback.security.dto.UserLogin;
import com.opbaquero.conexionaback.security.dto.NewUser;
import com.opbaquero.conexionaback.security.entity.Rol;
import com.opbaquero.conexionaback.security.entity.User;
import com.opbaquero.conexionaback.security.enums.RolName;
import com.opbaquero.conexionaback.security.jwt.JwtProvider;
import com.opbaquero.conexionaback.security.service.RolService;
import com.opbaquero.conexionaback.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserController {
    Map<String, Object> response = new HashMap<>();

    @Autowired
    public IAccountService accountService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    IEmailService emailService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> index(){
        return userService.findAll();
    }

    @GetMapping("/list/{accountId}")
    @PreAuthorize("hasRole('ROLE_GESTOR')")
    public List<User> getUserByAccount(@PathVariable(value = "accountId") UUID accountId){
        return userService.findByAccount(accountId);
    }

    @PostMapping("/{accountId}/new")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN') || hasRole('ROLE_GESTOR')")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NewUser newUser, BindingResult bindingResult, @PathVariable (value = "accountId") UUID accountId){
        Account account = accountService.findOne(accountId);

        if(bindingResult.hasErrors()) {
            response.put("error", "Invalid Fields");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }if(userService.existsByNombreUsuario(newUser.getUserName())) {
            response.put("error", "Username already exists");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }if(userService.existsByEmail(newUser.getEmail())) {
            response.put("error", "Email already exists");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }if(account == null){
            response.put("error", "ACCOUNT DOESN'T EXISTS");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        User user =
                new User(newUser.getName(), newUser.getUserName(), newUser.getEmail(),
                        passwordEncoder.encode(newUser.getPassword()), newUser.getAccount());
        Set<Rol> roles = new HashSet<>();

        if(newUser.getRoles().contains("gestor"))
            roles.add(rolService.getByRolName(RolName.ROLE_GESTOR).get());
        if(newUser.getRoles().contains("tablet"))
            roles.add(rolService.getByRolName(RolName.ROLE_TABLET).get());
        if(newUser.getRoles().contains("reponedor"))
            roles.add(rolService.getByRolName(RolName.ROLE_REPONEDOR).get());
        user.setRoles(roles);
        user.setAccount(account);
        userService.save(user);
        response.put("Message", "User created");
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    @GetMapping("/all/{userId}")
    public User getUser(@PathVariable (value = "userId") UUID id){
        return userService.findOne(id);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody UserLogin userLogin, BindingResult bindingResult, HttpServletResponse response1){
        if(bindingResult.hasErrors()){
            response.put("error", "Incorrect fields");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByUserName(userLogin.getUserName());
       /* if(user.getAccount() != null){
            /*Cookie cookie = new Cookie("account", user.getAccount().getId().toString());
            cookie.isHttpOnly();
            response1.addCookie(cookie);
        }*/

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getUserName(), userLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        JwtDto jwtDto = new JwtDto(jwt);
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @DeleteMapping("/add/{userId}")
    public ResponseEntity<?> delete(@PathVariable (value = "userId")UUID id){
        Map<String, Object> response = new HashMap<>();
        try{
            userService.delete(id);
        }catch (DataAccessException e){
            response.put("message", "Error deleting a user");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "User deleted succesfully");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/update/password")
    public ResponseEntity<?> changeUserPassword(Locale locale, @RequestParam("newpassword") String newpassword,
                                                @RequestParam("oldpassword") String oldPassword){
        Map<String, Object> response = new HashMap<>();

        User user = userService.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        userService.changePassword(user, newpassword);
        response.put("message", "Password Updated");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam("userEmail") String userEmail){
        Map<String, Object> response = new HashMap<>();

        User user = userService.findByEmail(userEmail);
        if(user == null){
            response.put("error", "No se encuentra un usuario con ese email");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setTo(userEmail);
        emailDTO.setFrom("oscarponte97@gmail.com");
        emailDTO.setUserName(user.getUserName());
        emailDTO.setSubject("Nueva contraseña");
        byte[] array = new byte[8];
        new Random().nextBytes(array);
        StringBuilder newPassword = new StringBuilder();
        String givenString = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890abcdefghijklmnñopqrstuvwxyz";
        for(int i = 0; i < 12; i++){
            int index = (int) (givenString.length() * Math.random());
            newPassword.append(givenString.charAt(index));
        }
        emailDTO.setNewPassword(newPassword.toString());
        this.emailService.sendMailTemplate(emailDTO);
        userService.changePassword(user, newPassword.toString());
        response.put("message", "Email send");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/add/{userId}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable (value = "userId") UUID id){
        User currentUser = userService.findOne(id);
        Account account = accountService.findOne(currentUser.getAccount().getId());
        User userUpdated = null;
        Map<String, Object> response = new HashMap<>();
        if(currentUser == null){
            response.put("message", "USER DOESN'T EXIST");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try{
            currentUser.setAccount(account);
            currentUser.setUserName(user.getUserName());
            currentUser.setRoles(user.getRoles());
            currentUser.setEmail(user.getEmail());
            currentUser.setPassword(user.getPassword());
            currentUser.setName(user.getName());
            userUpdated = userService.save(currentUser);
        }catch (DataAccessException e){
            response.put("message", "Error updating database");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "User updated sucesfully");
        response.put("account", userUpdated);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<JwtDto> refreshToken(@RequestBody JwtDto jwtDto)throws ParseException {
        String token = jwtProvider.refreshToken(jwtDto);
        JwtDto jwt = new JwtDto(token);
        return new ResponseEntity(jwt, HttpStatus.OK);
    }

}

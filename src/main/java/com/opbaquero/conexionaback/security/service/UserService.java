package com.opbaquero.conexionaback.security.service;

import com.opbaquero.conexionaback.models.dao.IAccountDao;
import com.opbaquero.conexionaback.models.entity.Account;
import com.opbaquero.conexionaback.security.entity.PasswordReset;
import com.opbaquero.conexionaback.security.entity.User;
import com.opbaquero.conexionaback.security.repository.PasswordTokenRepository;
import com.opbaquero.conexionaback.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IAccountDao accountDao;

    @Autowired
    PasswordTokenRepository passwordTokenRepository;

    public Optional<User> getByNombreUsuario(String userName){
        return userRepository.findByUserName(userName);
    }

    public boolean existsByNombreUsuario(String userName){
        return userRepository.existsByUserName(userName);
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void delete (UUID id){userRepository.deleteById(id);}

    public void changePassword(User user, String newPassword){
        user.setPassword(passwordEncoder.encode(newPassword));
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName).orElse(null);
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElse(null);
    }

    public Account findByUsernameWithAccount(String username){ return userRepository.findByUsernameWithAccount(username);}

    public List<User> findAll(){
        List<User> listUsers = userRepository.findAll();
        List<User> listToret = new ArrayList<>();

        for(User u : listUsers){
            if(u.getAccount().getAccountName() != null){
                listToret.add(u);
            }
        }
        return listToret;
    }

    public List<User> findByAccount(UUID accountId){
        Account account = accountDao.findById(accountId).get();
        return userRepository.findByAccount(account);
    }

    public User findOne(UUID id){ return userRepository.findById(id).orElse(null); }

}

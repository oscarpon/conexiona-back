package com.opbaquero.conexionaback.security.entity;



import com.opbaquero.conexionaback.models.entity.Account;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    public UUID id;

    @NotNull
    public String name;

    @NotNull
    @Column(unique = true)
    public String userName;

    @NotNull
    public String email;

    @NotNull
    public String password;

    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id"))
    public Set<Rol> roles = new HashSet<>();

    @JoinColumn(name = "Fk_ACCOUNT", nullable = true)
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    public Account account;

    public User() {
    }

    public User(@NotNull String name, @NotNull String userName, @NotNull String email, @NotNull String password, Account account)
    {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.account = account;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Account getAccount() {
        Account acToret = new Account();
        if(account != null){
            return account;
        }else{
            return acToret;
        }
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}

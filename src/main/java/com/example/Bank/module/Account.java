package com.example.Bank.module;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "accounts")
@Entity
public class Account implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "ACCOUNT_EMAIL", length = 25, nullable = false, unique = true)
    private String email;
    @Column(name = "DATE_OF_CREATED", length = 25)
    private String dateOfCreated;

    @PrePersist
    @GeneratedValue(strategy = GenerationType.AUTO)
    private void init() {
        Calendar calendar = new GregorianCalendar();
        dateOfCreated = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
    }
    @Column(name = "ACCOUNT_FIRSTNAME", length = 50)
    private String firstName;
    @Column(name = "ACCOUNT_LASTNAME", length = 50)
    private String lastName;

    @Column(name = "ACCOUNT_COUNTRY", length = 50)
    private String country;
    @Column(name = "ACCOUNT_PASSWORD", length = 50)
    private String password;
    @Column(name = "ACCOUNT_CITY", length = 50)
    private String city;
    @Column(name = "AMOUNT_OF_MONEY", columnDefinition = "Decimal(10,2) default '0.00' ", nullable = true)
    private BigDecimal amountOfMoney;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transaction")
    @Column(name = "ACCOUNT_TRANSACTIONS")
    private List<Transaction> transactions = new ArrayList<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "ACCOUNT_ROLE", joinColumns = @JoinColumn(name = "account_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

//{
//        "id": 1,
//        "email": "user@host.com",
//        "creationDate": "2022-12-31",
//        "firstName": "Tomas",
//        "lastName": "Muller",
//        "country": "Germany",
//        "city": "Munich",
//        "amountOfMoney": 200,99,
//        "transactions": [5, 8, 32, 6]
//        }
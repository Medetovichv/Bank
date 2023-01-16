package com.example.Bank.module;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CLIENT, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}


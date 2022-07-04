package com.example.news.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum Role  implements GrantedAuthority {
    ROLE_USER("user"),
    ROLE_ADMIN("admin");

     private String name;

    @Override
    public String getAuthority() {
        return name();
    }
}

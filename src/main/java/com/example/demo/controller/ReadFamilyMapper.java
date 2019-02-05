package com.example.demo.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;

import java.util.Collection;

public interface ReadFamilyMapper {

    boolean isForRole(Collection<? extends GrantedAuthority> authorities);

    String readFamily(Model model);
}

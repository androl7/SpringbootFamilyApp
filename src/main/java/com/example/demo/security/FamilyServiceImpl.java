package com.example.demo.security;

import com.example.demo.entity.Family;
import com.example.demo.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyServiceImpl implements UserDetailsService {
    @Autowired
    FamilyRepository familyRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Family family = familyRepository.findByName(s);
        if (family == null) {
            throw new UsernameNotFoundException("Family not found");
        }
        return new FamilyDetailsImpl(family.getFamily_surname(), family.getPassword(), family.getValid(),getGrantedAuthorities(family));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Family family) {

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + family.getFamilyRole()));

        return authorities;
    }
}

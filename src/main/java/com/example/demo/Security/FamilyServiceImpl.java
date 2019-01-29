package com.example.demo.Security;

import com.example.demo.entity.Family;
import com.example.demo.repository.FamilyRepository;
import com.example.demo.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FamilyServiceImpl implements UserDetailsService {
    @Autowired
    FamilyRepository familyRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Family family = familyRepository.findByName(s);
        if (family == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new FamilyDetailsImpl(family.getFamily_surname(), family.getPassword(), family.getValid());
    }
}

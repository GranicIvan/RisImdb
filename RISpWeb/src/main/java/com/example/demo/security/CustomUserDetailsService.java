package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.KorisnikRepo;

import model.Korisnik;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private KorisnikRepo korisnikRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Korisnik user = korisnikRepo.findByMail(username);
		UserDetailsImpl userDetails = new UserDetailsImpl();
		if (user != null) {
			userDetails.setUsername(user.getMail());
			userDetails.setPassword(user.getSifra());
			userDetails.setRoles(user.getUlogas());
		}
		return userDetails;

	}

}

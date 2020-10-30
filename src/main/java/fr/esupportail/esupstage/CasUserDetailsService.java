/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.esupportail.esupstage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.esupportail.esupstage.domain.ldap.LdapUser;
import fr.esupportail.esupstage.domain.ldap.LdapUserRepository;
import lombok.extern.slf4j.Slf4j;

//@Service
@Slf4j
public class CasUserDetailsService implements AuthenticationUserDetailsService, UserDetailsService {

	@Autowired
	private LdapUserRepository ldapUserRepository;

	// @Value("${ldap.group.switch}")
	private String groupSwitch;

	@Override
	public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
		return getUserDetails(token.getName(), false);
	}

	/**
	 * Utilisé pour le switch
	 *
	 * @param token
	 * @return récupère les détails d'un utilisateur en fonction de l'identifiant fourni
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
		Optional<LdapUser> user = ldapUserRepository.findByAliasLogin(token);
		if (user.isEmpty()) {
			user = ldapUserRepository.findByLogin(token);
		}
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("User not found in LDAP");
		}
		return getUserDetails(user.get().getLogin(), true);
	}

	/**
	 * @param uid
	 * @return les détails de l'utilisateur
	 * @throws UsernameNotFoundException
	 */
	private UserDetails getUserDetails(String uid, boolean isSwitched) throws UsernameNotFoundException {
		List<GrantedAuthority> authorities = new ArrayList<>();

		log.info("Recuperation LdapUser uid = " + uid);

		final Optional<LdapUser> result = ldapUserRepository.findByLogin(uid);
		final LdapUser user = result.get();

		// Rôle switch
		if (user.getGroupes().contains(groupSwitch)) {
			authorities.add(new SimpleGrantedAuthority("ROLE_SWITCH"));
		}

		for (GrantedAuthority aut : authorities) {
			log.info(aut.toString());
		}

		return new UserInfos(uid, "password", user, authorities, isSwitched);
	}
}

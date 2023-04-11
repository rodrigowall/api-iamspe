package br.com.parede.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.parede.model.Role;
import br.com.parede.model.User;
import br.com.parede.model.dto.UserPwdDTO;
import br.com.parede.repository.RoleRepository;
import br.com.parede.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User getUserAuth() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return userRepository.findByEmail(((UserDetails) principal).getUsername());
		} else {
			return null;
		}
	}

	public Role findRole(int roleResponsavel) {
		return roleRepository.findById(roleResponsavel);
	}

	public String trocarSenha(UserPwdDTO user) {
		User usuario = userRepository.findById(user.getId()).get();

		if (bCryptPasswordEncoder.matches(user.getSenhaAtual(),
				(userRepository.findById(user.getId()).get()).getPassword())) {
			if (!(user.getNovaSenha().equals(user.getNovaSenhaConfirma()))) {
				return "Senhas diferentes, tente novamente";
			}
			if (user.getSenhaAtual().equals(user.getNovaSenha())) {
				return "A senha antiga é igual a nova senha, tente novamente";
			} else {
				usuario.setPassword(bCryptPasswordEncoder.encode(user.getNovaSenha()));
				userRepository.save(usuario);
				return "";
			}
		} else {
			return "Senha Antiga errada, tente novamente";
		}
	}

}

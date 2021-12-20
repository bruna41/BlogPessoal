package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario cadastrarUsuario (Usuario usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String senhaEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaEncoder);
		return repository.save(usuario);
		
	}
	
	public Optional<UserLogin> logar (Optional<UserLogin> usuarioL) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuario> usuario = repository.findByUsuario(usuarioL.get().getUsuario());
		
		if (usuario.isPresent()) {
			if (encoder.matches(usuarioL.get().getSenha(), usuario.get().getSenha())) {
				String auth = usuarioL.get().getUsuario() + ":" + usuarioL.get().getSenha();
				byte [] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String (encodeAuth);
				usuarioL.get().setToken(authHeader);
				usuarioL.get().setNome(usuario.get().getNome());
				return usuarioL;
			}
		}
		return null;
	}

}

package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository userRepositoryTest;
	
	@BeforeAll
	void start() {
		userRepositoryTest.save(new Usuario (0L, "Bruna Bergami", "bruna@email.com", "ae3eaf4", "1234567"));
		userRepositoryTest.save(new Usuario (0L, "Bianca Rocha", "bianca@email.com", "ae3eaf4", "1234567"));
		userRepositoryTest.save(new Usuario (0L, "Pedro Bergami", "pedro@email.com", "ae3eaf4", "1234567"));
		userRepositoryTest.save(new Usuario (0L, "Otavio Bergami", "otavio@email.com", "ae3eaf4", "1234567"));
		userRepositoryTest.save(new Usuario (0L, "Joel Moraes", "joel@email.com",  "ae3eaf4", "1234567"));
	}
	

	@Test
	@DisplayName ("Return one user")
	public void returnOneUser () {
		Optional<Usuario> usuario = userRepositoryTest.findByUsuario("bruna@email.com");
		assertTrue(usuario.get().getUsuario().equals("bruna@email.com"));
	}

	@Test
	@DisplayName ("Return three users")
	public void returnThreeUsers () {
		List<Usuario> listUser = userRepositoryTest.findAllByNomeContainingIgnoreCase("Bergami");
		assertEquals(3, listUser.size());
		assertTrue(listUser.get(0).getNome().equals("Bruna Bergami"));
		assertTrue(listUser.get(1).getNome().equals("Pedro Bergami"));
		assertTrue(listUser.get(2).getNome().equals("Otavio Bergami"));
		
	}
	
}

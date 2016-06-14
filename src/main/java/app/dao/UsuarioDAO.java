package app.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import app.model.Usuario;

public class UsuarioDAO {

	private EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("alternativowebPU");
	private EntityManager em = factory.createEntityManager();

	public boolean ValidaUsuario(String nomeUsuario, String senha) {

		try {
			Usuario usuario = (Usuario) em
					.createQuery(
							"SELECT u from Usuario u where u.nomeUsuario = :name and u.senha = :senha")
					.setParameter("name", nomeUsuario)
					.setParameter("senha", senha).getSingleResult();

			if( usuario.getNomeUsuario() != null && usuario.getSenha() != null ) {

				return true;

			}

		} catch (NoResultException e) {
			System.out.println("Login error -->" + e.getMessage());  
			return false;
		}
		return false;

	}

	public boolean inserirUsuario(Usuario usuario) {
		try {
			em.persist(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deletarUsuario(Usuario usuario) {
		try {
			em.remove(usuario);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
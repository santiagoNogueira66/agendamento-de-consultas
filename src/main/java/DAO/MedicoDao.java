package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import UTIL.JPAutil;
import entidades.Medico;

public class MedicoDao {
	//chama o hibernate
	static EntityManager em = JPAutil.criarEntityManager();


	public void salvar(Medico medico) {
        EntityManager em = JPAutil.criarEntityManager();
        try {
            em.getTransaction().begin(); //inicia a transação
            em.merge(medico); //prepara o salvamento
            em.getTransaction().commit(); //envia o salvamento
            em.close(); //fecha a transação
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;  
        } finally {
            em.close();
        }
    }

	public static List<Medico> listar() {
		Query q = em.createQuery("select m from Medico m"); //cria a query
		List<Medico> resultado = q.getResultList(); //obtem o resultado da query
		return resultado;
	}

	public void editar(Medico medico) {
		try {
			em.getTransaction().begin();
			em.merge(medico);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}

	public void excluir(Medico medico) {
		try {
			em.getTransaction().begin();
			medico = em.find(Medico.class, medico.getId());
			if (medico != null) {
				em.remove(medico);
				em.getTransaction().commit();
			} else {
				em.getTransaction().rollback();
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw e;
		}
	}
}
package DAO;

import entidades.Agenda;
import UTIL.JPAutil;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class AgendaDao {

	public static void salvar(Agenda agenda) {
		//chama o hibernate
		EntityManager em = JPAutil.criarEntityManager();
		em.getTransaction().begin(); //inicia a transação
		em.merge(agenda); //prepara o salvamento
		em.getTransaction().commit(); //envia o salvamento
		em.close(); //fecha a transação
	}

	public static List<Agenda> listar() {
		EntityManager em = JPAutil.criarEntityManager();
		Query q = em.createQuery("select a from Agenda a"); //cria a query
		List<Agenda> resultado = q.getResultList(); //obtem o resultado da query
		em.close();
		return resultado;
	}

	public static void editar(Agenda agenda) {
		EntityManager em = JPAutil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(agenda);
		em.flush(); //serve para enviar as alterações
		em.getTransaction().commit();
		em.close();
	}

	public static void excluir(Agenda agenda) {
		EntityManager em = JPAutil.criarEntityManager();
		em.getTransaction().begin();
		agenda = em.find(Agenda.class, agenda.getId());
		if (agenda != null) {
			em.remove(agenda);
		}
	}
    //cria uma query que retorna o medico, data e hora do agendamento e a clinica
	public static boolean existeAgendamento(Agenda agenda) {
		EntityManager em = JPAutil.criarEntityManager();
		try {
			String jpql = "SELECT a FROM Agenda a " + "WHERE a.clinica = :clinica "
					+ "AND a.dataHoraAgendamento = :dataHora " + "AND a.medico = :medico";

			TypedQuery<Agenda> query = em.createQuery(jpql, Agenda.class);
			query.setParameter("clinica", agenda.getClinica());
			query.setParameter("dataHora", agenda.getDataHoraAgendamento());
			query.setParameter("medico", agenda.getMedico());

			Agenda agendamento = query.getSingleResult();
			return agendamento != null;
		} catch (NoResultException e) {
			return false;
		}
	}

}
package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.event.RowEditEvent;

import DAO.AgendaDao;
import DAO.MedicoDao;
import entidades.Agenda;
import entidades.Medico;

@ManagedBean
@ViewScoped
public class AgendaBean {

	private Agenda agenda = new Agenda(); //instancia da classe agenda
	private List<Agenda> lista;
	private List<Medico> listaMedico;

	@PostConstruct
	public void init() {
		listaMedico = MedicoDao.listar(); //atribuindo a variavel lista medico ao metodo lista da medico dao
	}

	public void salvar() {
		try {
			if (!existeAgendamento()) {
				AgendaDao.salvar(agenda);
				agenda = new Agenda(); //cria um novo objeto do tipo agenda
				
				//mensagem pro usuario
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Agendamento salvo com sucesso"));
				
				//mensagem pro usuario
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"numero do protocolo " + agenda.getId()));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"ja existe um agendamento para a mesma data, hora, medico e clinica"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "erro", "erro" + e));
		}
	}

	public List<Agenda> getLista() {
		if (lista == null) { //se a lista for nula ele cria uma nova
			lista = AgendaDao.listar();
		}
		return lista;
	}

	public void excluir(Agenda agenda) {
		try {
			AgendaDao.excluir(agenda); 
			lista.remove(agenda); // Remove a agenda da lista local, serve para atualizar a pagina de listagem

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Agendamento excluído com sucesso"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao excluir agendamento"));
			e.printStackTrace();
		}
	}

	public void editar(RowEditEvent event /*paramentro do editar do xhtml*/) {
		Agenda agendaEditada = (Agenda) event.getObject(); //obtem o objeto a ser editado no caso agenda

		if (!existeAgendamento()) {

			AgendaDao.editar(agendaEditada);

			lista = null;

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "As alterações foram salvas com sucesso."));

		}

	}

	private boolean existeAgendamento() {
		return AgendaDao.existeAgendamento(agenda); //metodo que verifica se ja tem agendamentos para data, hora, clinica e medico
	}
    // modificadores de acesso getters e setters
	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public List<Medico> getListaMedico() {
		return listaMedico;
	}

	public void setListaMedico(List<Medico> listaMedico) {
		this.listaMedico = listaMedico;
	}

	public void setLista(List<Agenda> lista) {
		this.lista = lista;
	}
	
}
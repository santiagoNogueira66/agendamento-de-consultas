package bean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.event.RowEditEvent;
import DAO.MedicoDao;
import entidades.Medico;

@ManagedBean
public class MedicoBean {
    private Medico medico = new Medico();
    private List<Medico> lista;
    private List<SelectItem> medicoSelectItems;
    private MedicoDao medicoDao;
     
    
    public MedicoBean() {
        medicoDao = new MedicoDao(); // Inicializa o MedicoDao no construtor
    }

    public void salvar() {
        try {
            medicoDao.salvar(medico);
            medico = new Medico();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Medico salvo com sucesso"));
            lista = null; //serve para manter a lista sempre atulizada
            medicoSelectItems = null; //serve para manter a lista sempre atulizada
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Erro ao salvar medico: " + e.getMessage()));
        }
    }

    public List<SelectItem> getMedicoSelectItems() {
        if (medicoSelectItems == null) { // Verifica se a lista de SelectItem ainda não foi inicializada
            medicoSelectItems = new ArrayList<>(); // Inicializa a lista de SelectItem como um ArrayList vazio
            for (Medico medico : getLista()) { // Itera sobre a lista de médicos (supondo que 'getLista()' retorna essa lista)
                medicoSelectItems.add(new SelectItem(medico.getNome(), medico.getNome())); // Cria um novo SelectItem para cada médico, onde o valor é o próprio objeto 'Medico' e o rótulo é o nome do médico ('medico.getNome()')
            }
        }
        return medicoSelectItems;
    }

    public List<Medico> getLista() {
        if (lista == null) {
            lista = medicoDao.listar();
        }
        return lista;
    }

    public void editar(RowEditEvent event) {
        Medico medicoEditado = (Medico) event.getObject(); //obtem o objeto atulizado
        medicoDao.salvar(medicoEditado);
        medico = new Medico();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Medico editado com sucesso"));
        lista = null;
        medicoSelectItems = null;
    }

    public void excluir(Medico medico) {
        medicoDao.excluir(medico);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Medico excluido com sucesso"));
        lista = null;
        medicoSelectItems = null;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setLista(List<Medico> lista) {
        this.lista = lista;
    }

    public void setMedicoSelectItems(List<SelectItem> medicoSelectItems) {
        this.medicoSelectItems = medicoSelectItems;
    }
}
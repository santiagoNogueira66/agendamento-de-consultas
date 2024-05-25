package UTIL;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAutil {
    
	//fabrica de Entity Manager
    private static EntityManagerFactory emf;

    static {
        try {
        	//cria o persistece 
            emf = Persistence.createEntityManagerFactory("meu_persistence_unit");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial EntityManagerFactory creation failed: " + e);
        }
    }
    //metodo para criar Entity Manager
    public static EntityManager criarEntityManager() {
        return emf.createEntityManager();
    }
    /*EntityManager e o cara responsavel pela criação de querys e da tabela*/
    /*pai de santo: gerencia entidades*/
}

package es.upm.dit.isst.tfg.dao;

import java.util.List;

import javax.persistence.Query;
import org.hibernate.Session;

import es.upm.dit.isst.tfg.model.Professor;

public class ProfessorDAOImplementation implements ProfessorDAO{
	
	private static ProfessorDAOImplementation sfs;
	
	private ProfessorDAOImplementation() {		
	}
	
	public static ProfessorDAOImplementation getInstance() {
	    if( null == sfs ) 
	      sfs = new ProfessorDAOImplementation();
	    return sfs;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void create (Professor professor) {
	  Session session = SessionFactoryService.get().openSession();
	  session.beginTransaction();
	  session.save(professor);
	  session.getTransaction().commit();
	  session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Professor read (String email) {
	  Session session = SessionFactoryService.get().openSession();
	  session.beginTransaction();
	  Professor professor = session.get(Professor.class, email);
	  session.getTransaction().commit();
	  session.close();
	  return professor;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update (Professor professor) {
	  Session session = SessionFactoryService.get().openSession();
	  session.beginTransaction();
	  session.saveOrUpdate(professor);
	  session.getTransaction().commit();
	  session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void delete (Professor professor) {
	  Session session = SessionFactoryService.get().openSession();
	  session.beginTransaction();
	  session.delete(professor);
	  session.getTransaction().commit();
	  session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Professor> readAll () {
	  Session session = SessionFactoryService.get().openSession();
	  session.beginTransaction();
	  List<Professor> p = session.createQuery("from Professor").list();
	  session.getTransaction().commit();
	  session.close();
	  return p;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Professor login (String email, String password) {
	  Session session = SessionFactoryService.get().openSession();
	  Professor professor = null;
	  session.beginTransaction();
	  Query  q = session.createQuery("SELECT p FROM Professor p WHERE p.email = :email and p.password = :password");
	  q.setParameter("email", email);
	  q.setParameter("password", password);
	  List<Professor> profs = q.getResultList();
	  if (profs.size() > 0)
	  	professor = (Professor) (q.getResultList().get(0));
	  session.getTransaction().commit();
	  session.close();
	  return professor;
	}
	
}



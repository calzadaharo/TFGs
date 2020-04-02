package es.upm.dit.isst.tfg.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.isst.tfg.model.TFG;

public class TFGDAOImplementation implements TFGDAO{
	
	private static  TFGDAOImplementation sfs = null;
	  
	private TFGDAOImplementation() {
	  }
	

    public static TFGDAOImplementation getInstance() {
	   if( null == sfs ) 
	     sfs = new TFGDAOImplementation();
	   return sfs;
	  }
    
	@SuppressWarnings("unchecked")
	@Override
	public void create (TFG tfg) {
	  Session session = SessionFactoryService.get().openSession();
	  session.beginTransaction();
	  session.save(tfg);
	  session.getTransaction().commit();
	  session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TFG read (String email) {
	  Session session = SessionFactoryService.get().openSession();
	  session.beginTransaction();
	  TFG tfg = session.get(TFG.class, email);
	  session.getTransaction().commit();
	  session.close();
	  return tfg;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void update (TFG tfg) {
	  Session session = SessionFactoryService.get().openSession();
	  session.beginTransaction();
	  session.saveOrUpdate(tfg);
	  session.getTransaction().commit();
	  session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void delete (TFG tfg) {
	  Session session = SessionFactoryService.get().openSession();
	  session.beginTransaction();
	  session.delete(tfg);
	  session.getTransaction().commit();
	  session.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TFG> readAll () {
	  Session session = SessionFactoryService.get().openSession();
	  session.beginTransaction();
	  List<TFG> p = session.createQuery("from TFG").list();
	  session.getTransaction().commit();
	  session.close();
	  return p;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TFG login (String email, String password) {
	  Session session = SessionFactoryService.get().openSession();
	  TFG tfg = null;
	  session.beginTransaction();
	  Query q = session.createQuery("select p from TFG p where p.email = :email and p.password = :password");
	  q.setParameter("email", email);
	  q.setParameter("password", password);
	  List<TFG> tfgs = q.getResultList();
	  if (tfgs.size() > 0)
	  	tfg = (TFG) (q.getResultList().get(0));
	  session.getTransaction().commit();
	  session.close();
	  return tfg;
	}
}

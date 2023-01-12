/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pws.uas2020.model.jpa;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pws.uas2020.model.entity.Penduduk;
import pws.uas2020.model.jpa.exceptions.NonexistentEntityException;
import pws.uas2020.model.jpa.exceptions.PreexistingEntityException;

/**
 *
 * @author STRIX
 */
public class PendudukJpaController implements Serializable {

    public PendudukJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("pws_uas2020_jar_0.0.1-SNAPSHOTPU");

    public PendudukJpaController() {
    }
    
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Penduduk penduduk) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(penduduk);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPenduduk(penduduk.getId()) != null) {
                throw new PreexistingEntityException("Penduduk " + penduduk + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Penduduk penduduk) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            penduduk = em.merge(penduduk);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = penduduk.getId();
                if (findPenduduk(id) == null) {
                    throw new NonexistentEntityException("The penduduk with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Penduduk penduduk;
            try {
                penduduk = em.getReference(Penduduk.class, id);
                penduduk.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The penduduk with id " + id + " no longer exists.", enfe);
            }
            em.remove(penduduk);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Penduduk> findPendudukEntities() {
        return findPendudukEntities(true, -1, -1);
    }

    public List<Penduduk> findPendudukEntities(int maxResults, int firstResult) {
        return findPendudukEntities(false, maxResults, firstResult);
    }

    private List<Penduduk> findPendudukEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Penduduk.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Penduduk findPenduduk(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Penduduk.class, id);
        } finally {
            em.close();
        }
    }

    public int getPendudukCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Penduduk> rt = cq.from(Penduduk.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

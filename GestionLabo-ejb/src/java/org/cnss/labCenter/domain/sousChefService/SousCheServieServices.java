/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.sousChefService;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.SousChefService;

/**
 *
 * @author Administrateur
 */
@Stateful
public class SousCheServieServices implements ISousChefService, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterSousChefService(SousChefService u) {
        entityManager.persist(u);
    }
    
    @Override
     public List<SousChefService> listeSousChefService() {
        return entityManager.createQuery("select c from SousChefService c").getResultList();
    }
}

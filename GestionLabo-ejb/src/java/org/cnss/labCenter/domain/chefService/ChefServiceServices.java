/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.chefService;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.ChefService;

/**
 *
 * @author Administrateur
 */
@Stateful
public class ChefServiceServices implements IChefService, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    
    @Override
    public void ajouterChefService(ChefService u) {
        entityManager.persist(u);
    }
    

    @Override
    public List<ChefService> listeChefService() {
        return entityManager.createQuery("select c from ChefService c").getResultList();
    }

}

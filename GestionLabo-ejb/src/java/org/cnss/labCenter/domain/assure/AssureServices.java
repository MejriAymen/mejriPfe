/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.assure;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Assure;

/**
 *
 * @author Administrateur
 */
@Stateless
public class AssureServices implements IAssure, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterAssure(Assure assure) {
        entityManager.persist(assure);
    }

    @Override
    public void modifierAssure(Assure assure) {
        entityManager.merge(assure);

    }

    @Override
    public void supprimerAssure(Assure assure) {
        entityManager.remove(entityManager.find(Assure.class, assure.getIdAssure()));
    }

    @Override
    public List<Assure> listerAssure() {
       return entityManager.createQuery("select c from Assure c").getResultList();

    }

    @Override
    public Assure AssureConverter(int i) {
        return entityManager.find(Assure.class, i);
    }

   
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.malade;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Malade;

/**
 *
 * @author Administrateur
 */
@Stateless
public class MaladeServices implements IMalde, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterMalade(Malade malade) {
        entityManager.persist(malade);

    }

    @Override
    public void modifierMalade(Malade malade) {
        entityManager.merge(malade);

    }

    @Override
    public void supprimerMalade(Malade malade) {
        System.out.println(malade);
        entityManager.remove(malade);
    }

    @Override
    public List<Malade> listerMalade() {
        return entityManager.createQuery("select c from Malade c").getResultList();
    }

    @Override
    public Malade MaladeConverter(int i) {
        return entityManager.find(Malade.class, i);
    }

    @Override
    public void supprimerMalade(int nls) {
        Malade v = entityManager.find(Malade.class, nls);
        entityManager.remove(v);
    }
}

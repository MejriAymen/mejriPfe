/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.valeursUsuelles;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.ValeursUsuelles;

/**
 *
 * @author Mejri Aymen
 */
@Stateless
public class ValeursUsuellesServices implements IValeursUsuelles, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterValeursUsuelles(ValeursUsuelles valeursUsuelles) {
        entityManager.persist(valeursUsuelles);

    }

    @Override
    public void modifierValeursUsuelles(ValeursUsuelles valeursUsuelles) {
        entityManager.merge(valeursUsuelles);
    }

    @Override
    public void supprimerValeursUsuelles(ValeursUsuelles valeursUsuelles) {
        entityManager.remove(valeursUsuelles);
    }

    @Override
    public List<ValeursUsuelles> listerValeursUsuelles() {
        return entityManager.createQuery("select c from ValeursUsuelles c").getResultList();


    }

    @Override
    public ValeursUsuelles valeursUsuellesConverter(int i) {
        return entityManager.find(ValeursUsuelles.class, i);
    }
}

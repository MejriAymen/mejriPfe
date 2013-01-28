/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.medecin;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Medecin;

/**
 *
 * @author Mejri Aymen
 */
@Stateless
public class MedecinServices implements IMedecin, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterMedecin(Medecin medecin) {
        entityManager.persist(medecin);
    }

    @Override
    public void modifierMedecin(Medecin medecin) {
        entityManager.merge(medecin);
    }

    @Override
    public void supprimerMedecin(Medecin medecin) {
        entityManager.remove(medecin);
    }

    @Override
    public List<Medecin> listerMedecin() {
        return entityManager.createQuery("select c from Medecin c").getResultList();


    }

    @Override
    public Medecin medecinConverter(int i) {
        return entityManager.find(Medecin.class, i);
    }
}

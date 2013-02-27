/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.specialite;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Specialite;

/**
 *
 * @author Administrateur
 */
@Stateless
public class SpecialiteServices implements ISpecialite, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterAntibiotique(Specialite antibiotique) {
        entityManager.persist(antibiotique);
    }

    @Override
    public void modifierAntibiotique(Specialite antibiotique) {
        entityManager.merge(antibiotique);
    }

    @Override
    public Specialite antibiotiqueConverter(int number) {
        return entityManager.find(Specialite.class, number);
    }

    @Override
    public List<Specialite> listeAntibiotique() {
        return entityManager.createQuery("select c from Specialite c").getResultList();
    }

    @Override
    public void supprimerSpecialite(int nls) {
        Specialite v = entityManager.find(Specialite.class, nls);
        entityManager.remove(v);
    }
}

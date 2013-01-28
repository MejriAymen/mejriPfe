/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.antibiotique;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Antibiotique;

/**
 *
 * @author Administrateur
 */
@Stateless
public class AntibiotiqueServices implements IAntibiotique, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterAntibiotique(Antibiotique antibiotique) {
        entityManager.persist(antibiotique);
    }

    @Override
    public void modifierAntibiotique(Antibiotique antibiotique) {
        entityManager.merge(antibiotique);
    }

    @Override
    public Antibiotique antibiotiqueConverter(int number) {
        return entityManager.find(Antibiotique.class, number);
    }

    @Override
    public List<Antibiotique> listeAntibiotique() {
        List<Antibiotique> antibiotiques;
        antibiotiques = entityManager.createQuery("select c from Antibiotique c").getResultList();
        return antibiotiques;
    }

   
}

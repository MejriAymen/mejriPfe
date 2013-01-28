/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.convention;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Convention;

/**
 *
 * @author Administrateur
 */
@Stateless
public class ConventionServices implements IConvention, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterConvention(Convention convention) {
        entityManager.persist(convention);
    }

    @Override
    public void modifierConvention(Convention convention) {
        entityManager.merge(convention);
    }

    @Override
    public Convention ConventionConverter(int number) {
        return entityManager.find(Convention.class, number);
    }

    @Override
    public List<Convention> listeConvention() {
        List<Convention> conventions;
        conventions = entityManager.createQuery("select c from Convention c").getResultList();
        return conventions;
    }

    @Override
    public void supprimerConvention(Convention convention) {
        Convention a = entityManager.find(Convention.class, convention.getIdConvention());
        entityManager.remove(a);
    }
}

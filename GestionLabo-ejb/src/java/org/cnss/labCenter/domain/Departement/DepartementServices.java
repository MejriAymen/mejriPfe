/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.Departement;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Departement;

/**
 *
 * @author Administrateur
 */
@Stateless
public class DepartementServices implements IDepartement, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterService(Departement service) {
        entityManager.persist(service);
    }

    @Override
    public void modifierService(Departement service) {
        entityManager.merge(service);

    }

    @Override
    public void supprimerService(Departement service) {
        entityManager.remove(service);
    }

    @Override
    public List<Departement> listerService() {
        List<Departement> services;
        services = entityManager.createQuery("select c from Departement c").getResultList();
        return services;

    }

    @Override
    public Departement serviceConverter(int i) {
        return entityManager.find(Departement.class, i);
    }
}

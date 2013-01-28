/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.serviceLab;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Service;

/**
 *
 * @author Administrateur
 */
@Stateless
public class ServicesLabServices implements IServiceLab, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterService(Service service) {
        entityManager.persist(service);
    }

    @Override
    public void modifierService(Service service) {
        entityManager.merge(service);

    }

    @Override
    public void supprimerService(Service service) {
        entityManager.remove(service);
    }

    @Override
    public List<Service> listerService() {
        List<Service> services;
        services = entityManager.createQuery("select c from Service c").getResultList();
        return services;

    }

    @Override
    public Service serviceConverter(int i) {
        return entityManager.find(Service.class, i);
    }
}

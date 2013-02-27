/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.biologiste;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Biologiste;

/**
 *
 * @author Administrateur
 */
@Stateful
public class BiologisteServices implements IBiologiste, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterBiologiste(Biologiste u) {
        entityManager.persist(u);
    }
}

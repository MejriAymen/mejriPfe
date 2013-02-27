/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.secretaire;

import java.io.Serializable;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Secretaire;

/**
 *
 * @author Administrateur
 */
@Stateful
public class SecretaireServices implements ISecretaire, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterSecretaire(Secretaire u) {
        entityManager.persist(u);
    }
}
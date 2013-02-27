/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.resultat;

import java.io.Serializable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Resultat;

/**
 *
 * @author Mejri Aymen
 */
@Stateless
public class ResultatServices implements IResultat, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterResultat(Resultat resultat) {
        entityManager.persist(resultat);

    }

    @Override
    public void modifierResultat(Resultat resultat) {
        resultat = entityManager.find(Resultat.class, resultat.getIdResultat());
        entityManager.merge(resultat);
    }

    @Override
    public Resultat find(Resultat resultat) {
        return entityManager.find(Resultat.class, resultat.getIdResultat());
    }

    @Override
    public void supprimerResultat(int nls) {
        Resultat v = entityManager.find(Resultat.class, nls);
        entityManager.remove(v);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.Utilisateur;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Secretaire;
import org.cnss.labCenter.entities.Utilisateurs;

/**
 *
 * @author Administrateur
 */
@Stateful
public class UtilisateurServices implements IUtilisateur, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterProgramme(Utilisateurs u) {
        entityManager.persist(u);
    }

    @Override
    public void modifierProgramme(Utilisateurs u) {
        entityManager.merge(u);
    }

    @Override
    public Utilisateurs programmeConverter(int number) {
        return entityManager.find(Utilisateurs.class, number);
    }

    @Override
    public List<Utilisateurs> listeProgramme() {
        return entityManager.createQuery("select c from Utilisateurs c").getResultList();
    }

    @Override
    public Utilisateurs rechercherUtilisateur(String pass) {
        List<Utilisateurs> utilisateurs = entityManager.createQuery("select r from Utilisateurs r where  r.motPass='" + pass + "'  ").getResultList();
        if (utilisateurs.isEmpty()) {
            return null;
        } else if (utilisateurs.size() == 1) {
            return utilisateurs.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void supprimerUtilisateur(int nls) {
        Utilisateurs v = entityManager.find(Utilisateurs.class, nls);
        entityManager.remove(v);
    }
}

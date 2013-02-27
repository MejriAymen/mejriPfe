/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.dossierMedical;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.DossierMedicale;

/**
 *
 * @author Administrateur
 */
@Stateless
public class DossierMedicalServices implements IDossierMedical, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterDossierMedicale(DossierMedicale dossierMedicale) {
        entityManager.persist(dossierMedicale);
    }

    @Override
    public void modifierDossierMedicale(DossierMedicale dossierMedicale) {
        entityManager.merge(dossierMedicale);
    }

    @Override
    public DossierMedicale DossierMedicaleConverter(int number) {
        return entityManager.find(DossierMedicale.class, number);
    }

    @Override
    public List<DossierMedicale> listeDossierMedicale() {
        return entityManager.createQuery("select c from DossierMedicale c").getResultList();
    }

    @Override
    public void supprimerDossierMedical(int nls) {
        DossierMedicale v = entityManager.find(DossierMedicale.class, nls);
        entityManager.remove(v);
    }
}

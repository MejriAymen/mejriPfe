/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.consultation;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Consultation;

/**
 *
 * @author Administrateur
 */
@Stateless
public class ConsultationServices implements IConsultation, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterConsultation(Consultation consultation) {
        entityManager.persist(consultation);
    }

    @Override
    public void modifierConsultation(Consultation consultation) {
        entityManager.merge(consultation);

    }

    @Override
    public void supprimerConsultation(Consultation consultation) {
        entityManager.remove(consultation);
    }

    @Override
    public List<Consultation> listerConsultation() {
        return entityManager.createQuery("select c from Consultation c").getResultList();
    }

    @Override
    public Consultation ConsultationConverter(int i) {
        return entityManager.find(Consultation.class, i);
    }

        public void supprimerConsultation(int nls) {
        Consultation v = entityManager.find(Consultation.class, nls);
        entityManager.remove(v);
    }

}

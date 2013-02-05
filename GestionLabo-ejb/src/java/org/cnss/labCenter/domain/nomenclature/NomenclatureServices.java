/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.nomenclature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Nomenclature;

/**
 *
 * @author Administrateur
 */
@Stateless
public class NomenclatureServices implements INomenclature, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterNomenclature(Nomenclature nomenclature) {
        entityManager.persist(nomenclature);
    }

    @Override
    public void modifierNomenclature(Nomenclature nomenclature) {
        entityManager.merge(nomenclature);
    }

    @Override
    public Nomenclature NomenclatureConverter(int number) {
        return entityManager.find(Nomenclature.class, number);
    }

    @Override
    public List<Nomenclature> listeNomenclature() {
        List<Nomenclature> nomenclatures;
        nomenclatures = entityManager.createQuery("select c from Nomenclature c").getResultList();
        return nomenclatures;
    }

    @Override
    public List<Nomenclature> listeNomenclatureSansValeurUsuelle() {
        List<Nomenclature> nomenclatures = listeNomenclature();
          List<Nomenclature> nos= new ArrayList<Nomenclature>();
        for (Nomenclature nomenclature : nomenclatures) {
            if (nomenclature.getValeursUsuelles() == null) {
                nos.add(nomenclature);
            }
        }
        return nos;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.visite;

import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.cnss.labCenter.entities.Nomenclature;
import org.cnss.labCenter.entities.Visite;

/**
 *
 * @author Administrateur
 */
@Stateless
public class VisiteServices implements IVisite, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void ajouterVisite(Visite visite) {
        entityManager.persist(visite);
    }

    @Override
    public void modifierVisite(Visite visite) {
        entityManager.merge(visite);
    }

    @Override
    public Visite VisiteConverter(int number) {
        return entityManager.find(Visite.class, number);
    }

    @Override
    public List<Visite> listeVisite() {
        List<Visite> visites;
        visites = entityManager.createQuery("select DISTINCT c from Visite c GROUP BY c.numVis").getResultList();
        return visites;
    }

     

    @Override
    public void removeVisteNomenclature(Visite v, Nomenclature n) {

        n = entityManager.find(Nomenclature.class, n.getIdNomenc());
        v = entityManager.find(Visite.class, v.getIdVisite());
        n.getVisites().remove(v);
        entityManager.merge(n);
        entityManager.merge(v);
    }

    @Override
    public void supprimerVisite(int nls) {
        Visite v = entityManager.find(Visite.class, nls);


        entityManager.remove(v);

    }
}

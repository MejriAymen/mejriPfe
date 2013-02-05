/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.visite;

import java.io.Serializable;
import java.util.ArrayList;
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
        visites = entityManager.createQuery("select c from Visite c ").getResultList();
        return visites;
    }
    @Override
  public List<Visite> doListerVisite() {
        
        int i;
        
        List<Visite> vs = listeVisite();
        List<Visite> vs1 = new ArrayList<Visite>();
        if (!vs.isEmpty()) {
            String trouve = vs.get(0).getNumVis();
            vs1.add(vs.get(0));
            i = 0;
            for (Visite visto : vs) {
                i++;
                if (!visto.getNumVis().equals(trouve)) {
                    vs1.add(visto);
                    trouve = visto.getNumVis();
                }
            }
        }
        return vs1;
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

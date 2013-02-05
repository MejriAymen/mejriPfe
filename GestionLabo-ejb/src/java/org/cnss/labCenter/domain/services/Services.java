/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.services;

import java.io.Serializable;
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
public class Services implements IServices, Serializable {

    @PersistenceContext(unitName = "lab")
    EntityManager entityManager;

    @Override
    public void affectationVisteNomenclature(Visite v, Nomenclature n) {
        //v.getNomenclatures().add(n);
        n.getVisites().add(v);
        v.setNomenclature(n);
            }
}

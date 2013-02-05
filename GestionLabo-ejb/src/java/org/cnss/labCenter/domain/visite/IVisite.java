/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.visite;

import java.util.List;
import javax.ejb.Local;
import org.cnss.labCenter.entities.Nomenclature;
import org.cnss.labCenter.entities.Visite;

/**
 *
 * @author Administrateur
 */
@Local
public interface IVisite {

    public void ajouterVisite(Visite visite);

    public void modifierVisite(Visite visite);

    public Visite VisiteConverter(int number);

    public List<Visite> listeVisite();

    public void supprimerVisite(int n);

    public void removeVisteNomenclature(Visite v, Nomenclature n);

    public List<Visite> doListerVisite();

  
}

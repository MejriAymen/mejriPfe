/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.specialite;

import java.util.List;
import javax.ejb.Local;

import org.cnss.labCenter.entities.Specialite;

/**
 *
 * @author Administrateur
 */
@Local
public interface ISpecialite {

    public void ajouterAntibiotique(Specialite antibiotique);

    public void modifierAntibiotique(Specialite antibiotique);

    public Specialite antibiotiqueConverter(int number);

    public List<Specialite> listeAntibiotique();

    public void supprimerSpecialite(int nls);
}

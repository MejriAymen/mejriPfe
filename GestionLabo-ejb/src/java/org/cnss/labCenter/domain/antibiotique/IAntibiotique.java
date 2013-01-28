/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.antibiotique;

import java.util.List;
import javax.ejb.Local;

import org.cnss.labCenter.entities.Antibiotique;

/**
 *
 * @author Administrateur
 */
@Local
public interface IAntibiotique {

    public void ajouterAntibiotique(Antibiotique antibiotique);

    public void modifierAntibiotique(Antibiotique antibiotique);

    public Antibiotique antibiotiqueConverter(int number);

    public List<Antibiotique> listeAntibiotique();
}

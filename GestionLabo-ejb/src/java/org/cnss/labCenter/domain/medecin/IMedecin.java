/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.medecin;

import java.util.List;
import javax.ejb.Local;

import org.cnss.labCenter.entities.Medecin;

/**
 *
 * @author Administrateur
 */
@Local
public interface IMedecin {

    public void ajouterMedecin(Medecin medecin);

    public void modifierMedecin(Medecin medecin);

    public void supprimerMedecin(Medecin medecin);

    public List<Medecin> listerMedecin();

    public Medecin medecinConverter(int i);
}

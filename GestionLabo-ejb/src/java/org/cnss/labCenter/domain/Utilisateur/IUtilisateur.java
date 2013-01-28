/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.Utilisateur;

import java.util.List;
import javax.ejb.Local;

import org.cnss.labCenter.entities.Utilisateurs;

/**
 *
 * @author Administrateur
 */
@Local
public interface IUtilisateur {

    public void ajouterProgramme(Utilisateurs programme);

    public void modifierProgramme(Utilisateurs programme);

    public Utilisateurs programmeConverter(int number);

    public List<Utilisateurs> listeProgramme();

    public Utilisateurs rechercherUtilisateur(String pass);
}

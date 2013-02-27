/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.resultat;

import javax.ejb.Local;
import org.cnss.labCenter.entities.Resultat;


/**
 *
 * @author Administrateur
 */
@Local
public interface IResultat {

    public void ajouterResultat(Resultat resultat);

    public void modifierResultat(Resultat resultat);
    
    public Resultat find(Resultat resultat);

    public void supprimerResultat(int nls);


  
}

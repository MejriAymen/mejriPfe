/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.malade;

import java.util.List;
import javax.ejb.Local;
import org.cnss.labCenter.entities.Malade;


/**
 *
 * @author Administrateur
 */
@Local
public interface IMalde {

    public void ajouterMalade(Malade malade);

    public void modifierMalade(Malade malade);

    public void supprimerMalade(Malade malade);

    public List<Malade> listerMalade();

    public Malade MaladeConverter(int i);

    public void supprimerMalade(int nls);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.biologiste;

import javax.ejb.Local;
import org.cnss.labCenter.entities.Biologiste;

/**
 *
 * @author Administrateur
 */
@Local
public interface IBiologiste {

    public void ajouterBiologiste(Biologiste u);
}

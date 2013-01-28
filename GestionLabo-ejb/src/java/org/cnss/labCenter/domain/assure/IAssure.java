/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.assure;

import java.util.List;
import javax.ejb.Local;
import org.cnss.labCenter.entities.Assure;

/**
 *
 * @author Administrateur
 */
@Local
public interface IAssure {

    public void ajouterAssure(Assure assure);

    public void modifierAssure(Assure assure);

    public void supprimerAssure(Assure assure);

    public List<Assure> listerAssure();

    public Assure AssureConverter(int i);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.convention;

import java.util.List;
import javax.ejb.Local;
import org.cnss.labCenter.entities.Convention;

/**
 *
 * @author Administrateur
 */
@Local
public interface IConvention {

    public void ajouterConvention(Convention convention);

    public void modifierConvention(Convention convention);

    public Convention ConventionConverter(int number);

    public List<Convention> listeConvention();
    
    public void supprimerConvention(int nls);
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.valeursUsuelles;

import java.util.List;
import javax.ejb.Local;
import org.cnss.labCenter.entities.ValeursUsuelles;

/**
 *
 * @author Mejri Aymen
 */
@Local
public interface IValeursUsuelles {

    public void ajouterValeursUsuelles(ValeursUsuelles valeursUsuelles);

    public void modifierValeursUsuelles(ValeursUsuelles valeursUsuelles);

    public void supprimerValeursUsuelles(int nls);

    public List<ValeursUsuelles> listerValeursUsuelles();

    public ValeursUsuelles valeursUsuellesConverter(int i);
}

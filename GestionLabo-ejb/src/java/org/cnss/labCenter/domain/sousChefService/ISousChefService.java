/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.sousChefService;

import java.util.List;
import javax.ejb.Local;
import org.cnss.labCenter.entities.SousChefService;

/**
 *
 * @author Administrateur
 */
@Local
public interface ISousChefService {

    public void ajouterSousChefService(SousChefService sousChefService);

    public void supprimerSousChefService(SousChefService sousChefService);

    public void modifierSousChefService(SousChefService sousChefService);

    public List<SousChefService> listeSousChefService();
}

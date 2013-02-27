/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.chefService;

import java.util.List;
import javax.ejb.Local;

import org.cnss.labCenter.entities.ChefService;

/**
 *
 * @author Administrateur
 */
@Local
public interface IChefService {

    public void ajouterChefService(ChefService u);

    public List<ChefService> listeChefService();
  
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.dossierMedical;

import java.util.List;
import javax.ejb.Local;
import org.cnss.labCenter.entities.DossierMedicale;

/**
 *
 * @author Administrateur
 */
@Local
public interface IDossierMedical {

    public void ajouterDossierMedicale(DossierMedicale dossierMedicale);

    public void modifierDossierMedicale(DossierMedicale dossierMedicale);

    public DossierMedicale DossierMedicaleConverter(int number);

    public List<DossierMedicale> listeDossierMedicale();

    public void supprimerDossierMedical(int nls);
}

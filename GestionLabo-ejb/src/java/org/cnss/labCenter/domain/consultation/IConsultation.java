/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cnss.labCenter.domain.consultation;

import java.util.List;
import javax.ejb.Local;
import org.cnss.labCenter.entities.Assure;
import org.cnss.labCenter.entities.Consultation;

/**
 *
 * @author Administrateur
 */
@Local
public interface IConsultation {

    public void ajouterConsultation(Consultation consultation);

    public void modifierConsultation(Consultation consultation);

    public void supprimerConsultation(Consultation consultation);

    public List<Consultation> listerConsultation();

    public Consultation ConsultationConverter(int i);
}

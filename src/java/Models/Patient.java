/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Samaya
 */
public class Patient {
    private String patientFirstName;
    private String patientSecondName;
    private String patientClaim;

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientSecondName() {
        return patientSecondName;
    }

    public void setPatientSecondName(String patientSecondName) {
        this.patientSecondName = patientSecondName;
    }

    public String getPatientClaim() {
        return patientClaim;
    }

    public void setPatientClaim(String patientClaim) {
        this.patientClaim = patientClaim;
    }
    
 
}

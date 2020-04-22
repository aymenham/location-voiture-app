package Myclass;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by LENOVO on 03/03/2018.
 */

public class Car implements Serializable{

    private int matricule ;
    private String marque;
    private String modele ;
    private double tarif ;
    private String etat ;
    private double KM;
    private String image ;

    public Car(){


    }

    public Car(int matricule, String marque, String modele, double tarif, String  etat ,double KM , String image) {
        this.matricule = matricule;
        this.marque = marque;
        this.modele = modele;
        this.tarif = tarif;
        this.etat = etat;
        this.KM = KM;
        this.image = image;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public double getTarif() {
        return tarif;
    }

    public void setTarif(double tarif) {
        this.tarif = tarif;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getKM() {
        return KM;
    }

    public void setKM(double KM) {
        this.KM = KM;
    }


}

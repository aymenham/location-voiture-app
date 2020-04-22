package Myclass;

/**
 * Created by LENOVO on 16/03/2018.
 */

public class CarLocation  {

    private String  date_debut ;

    private  String date_fin ;

    private String NbrJours ;

    private Car car ;

    public CarLocation(String date_debut, String date_fin, String nbrJours, Car car) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        NbrJours = nbrJours;
        this.car = car;
    }


    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getNbrJours() {
        return NbrJours;
    }

    public void setNbrJours(String nbrJours) {
        NbrJours = nbrJours;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}

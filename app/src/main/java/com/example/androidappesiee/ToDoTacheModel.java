package com.example.androidappesiee;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ToDoTacheModel {

    private int ID ;
    private String nomTache;
    private String description;
    private String typeDeTache;
    private Calendar deadline = Calendar.getInstance();
    private String repetition;
    private int priorite;
    private Boolean effectuee;

    //ToString method pour affichier plus lisiblement les informations contenues dans une tache

    @Override
    public String toString() {
        return "ToDoTacheModel{" +
                "ID=" + ID +
                ", nomTache='" + nomTache + '\'' +
                ", description='" + description + '\'' +
                ", typeDeTache='" + typeDeTache + '\'' +
                ", deadline=" + deadline +
                ", repetition='" + repetition + '\'' +
                ", priorite=" + priorite +
                ", effectuee=" + effectuee +
                '}';
    }


    //Getter and setter

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNomTache() {
        return nomTache;
    }

    public void setNomTache(String nomTache) {
        this.nomTache = nomTache;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeDeTache() {
        return typeDeTache;
    }

    public void setTypeDeTache(String typeDeTache) {
        this.typeDeTache = typeDeTache;
    }



    public String getRepetition() {
        return repetition;
    }

    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }

    public int getPriorite() {
        return priorite;
    }

    public void setPriorite(int priorite) {
        this.priorite = priorite;
    }

    public Boolean getEffectuee() {
        return effectuee;
    }

    public void setEffectuee(Boolean effectuee) {
        this.effectuee = effectuee;
    }
}

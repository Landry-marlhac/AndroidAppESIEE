package com.example.androidappesiee;

//Classe objet pour les taches avec tous les attributs utiles et qui le seront pour des améliorations
public class ToDoTacheModel {

    private int ID;
    private String nomTache;
    private String description;
    private String typeDeTache;
    private int priorite;
    private Boolean effectuee;

    public ToDoTacheModel(int ID, String nomTache, String description, String typeDeTache, int priorite, Boolean effectuee) {
        this.ID = ID;
        this.nomTache = nomTache;
        this.description = description;
        this.typeDeTache = typeDeTache;
        this.priorite = priorite;
        this.effectuee = effectuee;
    }


    //ToString method pour affichier plus lisiblement les informations contenues dans une tache

    @Override
    public String toString() {
        return "ToDoTacheModel{" +
                "ID=" + ID +
                ", nomTache='" + nomTache + '\'' +
                ", description='" + description + '\'' +
                ", typeDeTache='" + typeDeTache + '\'' +
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

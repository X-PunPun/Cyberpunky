package Modelo;

//este es un modelo de como funcionario una tabla de stadisticas. dando un valor ramdom al dado lanzado
public class Estadisticas {

    //variables de ramdom dado 10, reflejo txt, habilidad arma txt
    protected String id, apodo, clase,reflejo, habilidadarma, tc, humanidad;
    
    public Estadisticas() {

    }

    //creacion de un objeto
    public Estadisticas(String id, String apodo, String clase, String reflejo, String habilidadarma, String tc, String humanidad) {
        this.id=id;
        this.apodo=apodo;
        this.clase=clase;
        this.reflejo = reflejo;
        this.habilidadarma = habilidadarma;
        this.tc=tc;
        this.humanidad=humanidad;
    }

    //mutadores y accesadores

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getReflejo() {
        return reflejo;
    }

    public void setReflejo(String reflejo) {
        this.reflejo = reflejo;
    }

    public String getHabilidadarma() {
        return habilidadarma;
    }

    public void setHabilidadarma(String habilidadarma) {
        this.habilidadarma = habilidadarma;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getHumanidad() {
        return humanidad;
    }

    public void setHumanidad(String humanidad) {
        this.humanidad = humanidad;
    }
    
}

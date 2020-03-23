package com.example.proyectomenu;

public class datos {

    private String id;
    private String fecha;
    private String msg;

    public datos(){
    }

    public datos(String id, String fecha, String msg) {
        this.id = id;
        this.fecha = fecha;
        this.msg = msg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

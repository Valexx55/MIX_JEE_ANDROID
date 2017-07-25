package com.example.vale.adminbebe.dto;


/**
 * @author vale
 *
 *Clase que encapsula la información de un mensaje en la aplicación para ser
 * transmitida entre el cliente y el servidor (la foto en BASE64 como String)
 *
 */

public class BuenosDias {



    //la foto codificada en BASE64
    private String foto;
    private String mensaje;
    private String fecha;


    public BuenosDias (String mensaje, String fecha, String foto_64)
    {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.foto = foto_64;

    }


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

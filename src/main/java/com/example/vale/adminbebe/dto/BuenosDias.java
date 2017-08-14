package com.example.vale.adminbebe.dto;

import java.io.Serializable;

/**
 * @author vale
 *
 *Clase que encapsula la información de un mensaje en la aplicación para ser
 * transmitida entre el cliente y el servidor (la foto en BASE64 como String)
 *
 */

public class BuenosDias implements Serializable {



    /**
	 * 
	 */
	private static final long serialVersionUID = -6181349799519810200L;
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
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "FECHA " + fecha + " MENSAJE " + mensaje + " FOTO " + foto;
    }
    
    @Override
    public boolean equals(Object obj) {
    	boolean bdev = false;
    	
    		BuenosDias bb2 = (BuenosDias)obj;
    		bdev = this.fecha.equals(bb2.fecha)&&this.mensaje.equals(bb2.mensaje)&&this.foto.equals(bb2.foto);
    	
    	return bdev;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.intro.app.tallernetworking;

/**
 *
 * @author Z470
 */
public class FunctionServerException extends Exception{
    
    public static final String MAL_FORMED_FUNCTION = "La funcion ingresada no es valida.";
    
    public FunctionServerException(String msg){
		super(msg);
	}

}

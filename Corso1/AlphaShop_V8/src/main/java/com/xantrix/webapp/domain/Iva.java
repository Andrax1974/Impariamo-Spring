package com.xantrix.webapp.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Iva implements Serializable
{
	 
	private static final long serialVersionUID = 4157166221401288115L;
	
	private int id;
	private String descrizione;
	private int aliquota;
	

}

package com.xantrix.webapp.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class FamAssort implements Serializable
{
	private static final long serialVersionUID = 3788120361600509595L;
	
	private int id;
	private String descrizione;
	
}

package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LOGINS")
public class Logins implements Serializable
{
	private static final long serialVersionUID = -4168405130687672809L;
	
	@Id
	@Basic(optional = false)
	private String series;
	
	@Basic(optional = false)
	private String username;
	
	@Basic(optional = false)
	private String token;
	
	@Temporal(TemporalType.TIME)
	private Date last_used;
	
	public Logins()
	{}
	
	public String getUserName()
	{
		return username;
	}
	
	public void setUserName(String userName)
	{
		this.username = userName;
	}
	
	public String getSeries()
	{
		return series;
	}
	
	public void setSeries(String series)
	{
		this.series = series;
	}
	
	public String getToken()
	{
		return token;
	}
	
	public void setToken(String token)
	{
		this.token = token;
	}
	
	public Date getLast_used()
	{
		return last_used;
	}
	
	public void setLast_used(Date last_used)
	{
		this.last_used = last_used;
	}
	
	
	

}

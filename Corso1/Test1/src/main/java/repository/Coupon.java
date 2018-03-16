package com.xantrix.webapp.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
/*
@Entity
@Table(name = "Books", uniqueConstraints = {
@UniqueConstraint(name = "Books_ISBNs", columnNames = { "isbn" })
},
indexes = {
@Index(name = "Books_Titles", columnList = "title")
})
public class Coupon implements Serializable
{
	private static final long serialVersionUID = 5878656577198475664L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Basic(optional = false)
	private String isbn;
	
	@Basic(optional = false)
	private String title;
	
	@Basic(optional = false)
	private String author;
	
	@Basic
	private double price;
	
	@Basic(optional = false)
	private String publisher;
	
	@Temporal(TemporalType.DATE)
	@Column(name="EM_DATE")
	private Date Emission;
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public String getIsbn()
	{
		return isbn;
	}
	
	public void setIsbn(String isbn)
	{
		this.isbn = isbn;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public double getPrice()
	{
		return price;
	}
	
	public void setPrice(double price)
	{
		this.price = price;
	}
	
	public String getPublisher()
	{
		return publisher;
	}
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	public Date getEmission()
	{
		return Emission;
	}

	public void setEmission(Date emission)
	{
		Emission = emission;
	}
	
	
	

}
*/
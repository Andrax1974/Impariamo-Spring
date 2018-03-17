package com.xantrix.webapp.controller;

public class PagingData
{
	private int PageNum;
	private boolean IsSelected;
	
	public PagingData(int pageNum, boolean isSelected)
	{
		PageNum = pageNum;
		IsSelected = isSelected;
	}

	public int getPageNum()
	{
		return PageNum;
	}

	public void setPageNum(int pageNum)
	{
		PageNum = pageNum;
	}

	public boolean isIsSelected()
	{
		return IsSelected;
	}

	public void setIsSelected(boolean isSelected)
	{
		IsSelected = isSelected;
	}
	
	
}

package com.xantrix.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.domain.FamAssort;
import com.xantrix.webapp.repository.FamAssRepository;

@Service
public class FamAssortServiceImpl implements FamAssortService
{
	@Autowired 
	FamAssRepository famAssortRepository;
	
	@Override
	public List<FamAssort> SelFamAssort()
	{
		return famAssortRepository.SelFamAssort();
	}

}

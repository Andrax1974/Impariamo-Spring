package com.xantrix.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.domain.Iva;
import com.xantrix.webapp.repository.IvaRepository;

@Service
public class IvaServiceImpl implements IvaService
{

	@Autowired
	IvaRepository ivaRepository;
	
	@Override
	public List<Iva> SelIva()
	{
		return ivaRepository.SelIva();
	}

}

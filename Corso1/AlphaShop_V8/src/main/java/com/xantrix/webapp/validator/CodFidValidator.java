package com.xantrix.webapp.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CodFidValidator implements ConstraintValidator<CodFid, String>
{
	private String codFidPrefix;

	@Override
	public void initialize(CodFid CodFidelity)
	{
		codFidPrefix = CodFidelity.matrice();
	}

	@Override
	public boolean isValid(String InsertCodFid, ConstraintValidatorContext theConstraintValidatorContext)
	{

		boolean retVal = false;

		if (InsertCodFid != null)
		{
			retVal = codFidPrefix.equals(InsertCodFid.substring(0, 3));
		} 
		 
		return retVal;
	}
}

package com.wipro.eb.service;

import com.wipro.eb.entity.Commercial;
import com.wipro.eb.entity.Domestic;
import com.wipro.eb.exception.InvalidConnectionException;
import com.wipro.eb.exception.InvalidReadingException;

public class ConnectionService 
{
	public boolean validate(int currentReading, int previousReading, String type) throws InvalidReadingException, InvalidConnectionException
	{
		int flag1=0;
		int flag2=0;
		if(type.equals("Domestic") || type.equals("Commercial"))
				flag1=1;
		else
			throw new InvalidConnectionException();
		
		if(currentReading < previousReading || (previousReading < 0 || currentReading < 0))
			throw new InvalidReadingException();
		else
			flag2=1;
		
		if(flag1==1 && flag2==1)
			return true;
		else
			return false;
	}
	public float calculateBillAmt(int currentReading, int previousReading, String type) 
	{
		try
		{
			boolean val = validate(currentReading, previousReading, type);
			if(val)
			{
				if(type.equals("Domestic"))
				{
					float[] slabs = {(float) 2.3, (float) 4.2, (float) 5.5};
					Domestic d = new Domestic(currentReading, previousReading, slabs);
					return d.computeBill();
				}
				else
				{
					float[] slabs = {(float) 5.2,(float) 6.8,(float) 8.3};
					Commercial c = new Commercial(currentReading, previousReading, slabs);
					return c.computeBill();
				}
			}
		}
		catch(InvalidReadingException e)
		{
			return -1;
		}
		catch(InvalidConnectionException e)
		{
			return -2;
		}
		return 0;
	}
	public String generateBill(int currentReading, int previousReading, String type)
	{
		float val = calculateBillAmt(currentReading, previousReading, type);
		if(val==-1)
			return "Incorrect Reading";
		else if(val==-2)
			return "Invalid ConnectionType";
		else
			return "Amount to be paid:"+val;
	}
}

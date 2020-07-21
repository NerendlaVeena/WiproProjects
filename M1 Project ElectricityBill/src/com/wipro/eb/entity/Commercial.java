package com.wipro.eb.entity;

public class Commercial extends Connection
{
	public Commercial(int currentReading, int previousReading,float slabs[])
	{
		super(currentReading, previousReading, slabs);
	}

	@Override
	public float computeBill() 
	{
		float bill=0;
		int c = getCurrentReading();
		int p = getPreviousReading();
		float[] slabs = getSlabs();
		int diff=c-p;
		if(diff>=50)
		{
			bill+=(slabs[0]*50);
			diff-=50;
			if(diff>=50)
			{
				bill+=(slabs[1]*50);
				diff-=50;
				bill+=(slabs[2]*diff);
			}
			else
			{
				bill+=(diff*slabs[1]);
			}
		}
		else
		{
			bill+=(slabs[0]*diff);
		}
		float ed;
		if(bill<5000)
		{
			ed=(float) (bill*0.02);
		}
		else if(bill>=5000 && bill<10000)
		{
			ed=(float) (bill*0.06);
		}
		else
		{
			ed=(float) (bill*0.09);
		}
		return bill+ed;
	}

}


import java.util.Comparator;

public class intComparator<E> implements Comparator<E>
{
	private int countBits(E a)
	{
		if(a == null)
			throw new RuntimeException("nullarg");
		try{
			int numOnes = 0;
			int tmp = ((Integer) a).intValue();
			while(tmp!=0)
			{
				int bit = tmp & 1;
				if(bit == 1)
					numOnes++;
				tmp = tmp >> 1;
			}
			return numOnes;
		}
		catch(ClassCastException e){
			throw new RuntimeException("Not an int");
		}
	}

	/* ALTERNATIVE

	private int countBits2(E a)
	{
		if(a == null)
			throw new RuntimeException("nullarg");
		try{
			int numOnes = 0;
			String str = Integer.toBinaryString(Integer a)
			for(int i = 0; i < str.length(); i++)
			{
				if(str.charAt(0) = '1')
					numOnes++;
			}
			return numOnes;
		}
		catch(ClassCastException e){
			throw new RuntimeException("Not an int");
		}
	}

	*/

	public int compare(E a, E b)
	{
		int A = countBits(a);
		int B = countBits(b);
		return(A-B);
	}
}


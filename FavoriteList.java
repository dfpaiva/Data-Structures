import java.util.Random;

public class FavoriteList<E>
{
	// Below is inner class - Entry
	protected static class Entry<E>
	{
		private E value;
		private int count;

		public Entry(E v)
		{
			count = 1;
			value = v;
		}

		public E getValue()
		{
			return value;
		}

		public int getCount()
		{
			return count;
		}

		public int incrementCount()
		{
			return ++count;
		}

		public String toString()
		{
			return "[" + count + "," + value + "]";
		}
	}		// End of inner class

	protected PositionList<Entry<E>> fList;

	public FavoriteList()
	{
		fList = new NodePositionList<Entry<E>>();
	}

	public int size()
	{
		return fList.size();
	}

	public boolean isEmpty()
	{
		return fList.isEmpty();
	}

	public void access(E e)
	{
		if(fList.isEmpty())
		{
			fList.addFirst(new Entry<E>(e));
		}
		else
		{
			Position<Entry<E>> p = findPosition(e);
			if (p != null)
				p.element().incrementCount();	// if found, increment count
			else
			{
				fList.addLast(new Entry<E>(e));		// if not found, add entry to the end
				p = fList.last();
			}
			moveUp(p);	// move entry to final position
		}
	}

	public void remove(E e)
	{
		Position<Entry<E>> p = findPosition(e);
		if (p != null)
		{
			fList.remove(p);
		}
	}

	public Iterable<E> top(int k)
	{
		if (k < 0 || k > size())
			throw new IllegalArgumentException("Invalid argument");

		PositionList<E> T = new NodePositionList<E>();
		int i = 0;
		for (Entry<E> e : fList)
		{
			if (i++ >= k)
				break;	// all k entries have been added
			T.addLast(e.getValue());
		}
		return T;	// returns the value
	}

	protected Position<Entry<E>> findPosition(E e)
	{
		for (Position<Entry<E>> p : fList.positions())
			if (value(p).equals(e))
				return p;	// return the found pos
		return null;	// not found
	}

	protected void moveUp(Position<Entry<E>> p)
	{
		Entry<E> e = p.element();
		int c = count(p);
		while (p != fList.first())
		{
			Position<Entry<E>> prev = fList.prev(p);
			if (c <= count(prev))
				break;		// entry is where it's supposed to be
			fList.set(p, prev.element());	// move down previous entry
			p = prev;
		}
		fList.set(p, e);	// store entry in final position
	}

	protected E value(Position<Entry<E>> p)
	{
		return (p.element()).getValue();
	}

	protected int count(Position<Entry<E>> p)
	{
		return (p.element()).getCount();
	}

	public String toString()
	{
		return fList.toString();
	}
}
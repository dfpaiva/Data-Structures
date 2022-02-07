// Similar to FavoriteList except entries moved to front. Thus only need to deal with methods moveUp and top in this class

public class FavoriteListMTF<E> extends FavoriteList<E>
{
	protected void moveUp(Position<Entry<E>> p)
	{
		fList.addFirst(fList.remove(p));
	}

	public Iterable<E> top(int k)
	{
		if(k < 0 || k > size())
			throw new IllegalArgumentException("Invalid argument");

		PositionList<E> pl = new NodePositionList<E>();	// k list
		if(!isEmpty())
		{
			PositionList<Entry<E>> temp = new NodePositionList<Entry<E>>();	// copy entries into this later on

			for(Entry<E> e: fList)
				temp.addLast(e);

			for(int i = 0; i < k; i++)	// find k elements, going through each
			{
				Position<Entry<E>> top = null;	// the entry with largest count
				int accessCount = -1;	// access count of top entry
				for(Position<Entry<E>> p: temp.positions())	// go through temp entry
				{
					int count = count(p);
					if(count > accessCount)	// compare access counts
					{
						accessCount = count;	// replace if you found higher access count
						top = p;				// change to new top
					}
				}
				pl.addLast(value(top));	// add top entry
				temp.remove(top);	// remove top entry from temp and go through loop again
			}
		}
		return pl;
	}
}
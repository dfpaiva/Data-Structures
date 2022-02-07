public interface Vector<E>
{
	public E elemAtRank(int r) throws InvalidRankException;
	public E replaceAtRank(int r, E e) throws InvalidRankException;
	public void insertAtRank(int r, E e) throws InvalidRankException;
	public E removeAtRank(int r) throws InvalidRankException;
	public int size();
	public boolean isEmpty();
}
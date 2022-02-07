public interface Sequence<E> extends Deque<E>, Vector<E>, PositionList<E>
{
	public Position<E> atRank(int r) throws InvalidRankException;
	public int rankOf(Position<E> p) throws InvalidPositionException;
}
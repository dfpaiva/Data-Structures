import java.util.Iterator;

public interface PositionList<E> extends Iterable<E>
{
	public int size();
  	public boolean isEmpty();

  	public Position<E> first() throws EmptyListException;
  	public Position<E> last() throws EmptyListException;

  	public Position<E> prev(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
  	public Position<E> next(Position<E> p) throws InvalidPositionException, BoundaryViolationException;

  	public void addFirst(E e);
  	public void addLast(E e);

  	public void addBefore(Position<E> p, E e) throws InvalidPositionException;
  	public void addAfter(Position<E> p, E e) throws InvalidPositionException;

  	public E remove(Position<E> p) throws InvalidPositionException;
  	public E set(Position<E> p, E e) throws InvalidPositionException;

	// Below are iterable and iterator. Not really needed, but they're useful for toString methods in NodePositionList and even if you were to use FavoriteList
  	public Iterable<Position<E>> positions();
  	public Iterator<E> iterator();
}

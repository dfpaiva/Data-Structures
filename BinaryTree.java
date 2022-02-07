public interface BinaryTree<E> extends Tree<E>
{
	public Position<E> left(Position<E> p) throws InvalidPositionException, BoundaryViolationException;
	public Position<E> right(Position<E> p) throws InvalidPositionException, BoundaryViolationException;

	public boolean hasLeft(Position<E> p) throws InvalidPositionException;
	public boolean hasRight(Position<E> p) throws InvalidPositionException;
}
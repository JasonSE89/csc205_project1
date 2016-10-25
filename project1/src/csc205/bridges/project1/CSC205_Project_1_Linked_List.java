package csc205.bridges.project1;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.UnaryOperator;

import bridges.base.DLelement;

/** This class implements a Linked List using a doubly-linked, non-circular list
 *  with a dummy header node and a reference to the last element in the List.
 *  The class also contains a reference to the most recently accessed node 
 *  (ie, this is a "finger", or an "iterator")
 */

public class CSC205_Project_1_Linked_List<E> implements java.util.List<E>
{
    DLelement<E> header;

    
    /** Constructor method */
	public CSC205_Project_1_Linked_List() {

		header = new DLelement<E>();
		header.setLabel("dummy");
		header.setValue( (E) new Object() );
	}

	/**
	 * Returns the number of elements in this list. 
	 *
	 * @return  the number of elements in this list
	 */
	public int size()
	{ 
		throw (new UnsupportedOperationException());
	}

	/**
	 * @return a reference to the dummy header node in the List
	 */
	public DLelement<E> getDummyHeader() 
	{
		return header;
	}

	/**
	 * @return a reference to the node pointed to by the Finger,
	 * which will be the header node when the list is empty
	 * 
	 */
	public DLelement<E> getFinger() 
	{
		throw (new UnsupportedOperationException());
	}

	/**
	 * Appends the specified element to the end of this list. 
	 *
	 * @param item element to be appended to this list 
	 * @return  true if this collection changed as a result of the call 
	 *         (as specified by Collection.add(E))
	 */
	public boolean add( E item ) {

		throw (new UnsupportedOperationException() );
	}


	/**
	 * Finds and returns the element at the specified position in this list. 
	 *
	 * @param index the index of the element to be retrieved
	 * @return  the element at the specified position 
	 * @throws IndexOutOfBoundsException  if the index is out of range (index < 0 || index >= size())
	 */  
	public E get ( int index ) {

		throw (new UnsupportedOperationException() );
	}

	/**
	 * Finds and returns the element at the specified position in this list. 
	 *
	 * @param index the index of the element to be retrieved
	 * @return  the element at the specified position 
	 * @throws IndexOutOfBoundsException  if the index is out of range (index < 0 || index >= size())
	 */  
	public E set ( int index, E newValue ) {

		throw (new UnsupportedOperationException() );
	}


	/**
	 * Removes the element at the specified position in this list. 
	 * Shifts any subsequent elements to the left (subtracts one from 
	 * their indices). Returns the element that was removed from the list. 
	 *
	 * @param index the index of the element to be removed 
	 * @return  the element previously at the specified position 
	 * @throws IndexOutOfBoundsException  if the index is out of range (index < 0 || index >= size())
	 */  
	public E remove ( int index ) {

		throw (new UnsupportedOperationException() );
	}

	/**
	 * Adds a new element at the specified position in this list. 
	 * Shifts any subsequent elements to the right.  Returns the element that was removed from the list. 
	 *
	 * @param index the index of the element to be removed  
	 * @param newValue the value of the item to be added 
	 * @throws IndexOutOfBoundsException  if the index is out of range (index < 0 || index >= size())
	 */  
	public void add ( int index, E newValue ) {

		throw (new UnsupportedOperationException() );
	}


	/** 
	 * @return  the contents of this List as a String
	 */  
	public String toString ( ) {

		throw (new UnsupportedOperationException() );
	}	



	public int indexOf ( Object target ) {

		throw (new UnsupportedOperationException() );
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public void clear() {
		throw (new UnsupportedOperationException() );

	}

	@Override
	public boolean contains(Object o) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public boolean isEmpty() {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public Iterator<E> iterator() {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public int lastIndexOf(Object o) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public ListIterator<E> listIterator() {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public boolean remove(Object o) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public void replaceAll(UnaryOperator<E> operator) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public void sort(Comparator<? super E> c) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public Spliterator<E> spliterator() {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public Object[] toArray() {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw (new UnsupportedOperationException() );
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw ( new UnsupportedOperationException());
	}
}


package com.darronschall.util
{
public interface Iterator
{
	/** 
	 * @return <code>true</code> if the iteration has more elements, false otherwise.
	 */
	function hasNext():Boolean;	
	
	/** 
	 * @return The next element in the iteration. 
	 */
	function next():*;
	
}
}
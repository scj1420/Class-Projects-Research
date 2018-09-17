#ifndef CIRCULARDLLINTERFACE_H_
#define CIRCULARDLLINTERFACE_H_

#include <string>

using namespace std;


/*
 * Warning: Do not modify this document in any way, including its name.  Consequences of doing so
 * include the inability to compile with the grading test driver.  As stated in the Midterm packet,
 * any section of code that does not compile will not be graded.
 */
 
/**
 * The CircularDLLInterface defines basic operations performed on a circular double-linked list.
 * Your implementation of this interface must be a circular double-linked list.
 *
 * For this list, the word "empty" is used as the return value of for at(index) when the list is empty.
 * You may check and reject the word "empty" if you would like to make sure it is never a value 
 * in your list, but it is not necessary.  We will not attempt to insert the word "empty".
 */
class CircularDLLInterface {
public:
	CircularDLLInterface(){};
	virtual ~CircularDLLInterface(){};

	/**
	 * Adds the given name to the beginning of the list so that at(0) == name.
	 * After the operation, the size should have increased by 1.
	 *
	 * returns true if successful.
	 * returns false and does not add the name if the same name already exists.
	 */
	virtual bool insertHead(string name) = 0;

	/**
	 * Adds the given name to the end of the list so that at(-1) == name.
	 * After the operation, the size should have increased by 1.
	 *
	 * returns true if successful, false otherwise.
	 * returns false and does not add the name if the same name already exists.
	 */
	virtual bool insertTail(string name) = 0;

	/**
	 * Inserts the given name into the given index of the list so that at(index) == name.  
	 * All values should shift forward one index to accommodate the new value.  
	 * After the operation, the size should have increased by 1.
	 * 
	 * SEE NOTE FOR THE AT() FUNCTION
	 *
	 * returns true if insert was successful.
	 * returns false  and does not add the name if the same name already exists.
	 */
	virtual bool insertAt(string name, int index)  = 0;

	/**
	 * Removes the value at the beginning of the list.  After this operation
	 * size() should return a value 1 less than before and all values should
	 * shift 1 index backward.
	 *
	 * returns true if the removal occurred successfully.
	 * returns false if attempting to remove from an empty list.
	 */
	virtual bool removeHead() = 0;

	/**
	 * Removes the value at the end of the list.  After this operation
	 * size() should return a value 1 less than its previous value and 
     * all positive index values should remain unchanged.
	 *
	 * returns true if the removal occurred successfully.
	 * returns false if attempting to remove from an empty list.
	 */
	virtual bool removeTail() = 0;

	/**
	 * Removes the value at the given index measured from the beginning.
	 * index 0 is the beginning of the list.  After this removal the list size
	 * should be 1 less and all following values should shift backward by 1.
	 * 
	 * SEE NOTE FOR THE AT() FUNCTION
	 *
	 * returns true if the removal occurred successfully.
	 * returns false attempting to remove from an empty list.
	 */
	virtual bool removeAt(int index) = 0;

	/**
	 * Removes all nodes from the list and resets the state of the data structure
	 * to empty.  size() should return 0 after this operation.
	 */
	virtual void clear() = 0;

	/**
	 * As a reminder, the size() and the at() functions are used to read the contents
	 * of your list.  Without these we will not be able to determine the functionality
	 * of you list.
	 *
	 * returns the number of items in the list.
	 */
	virtual int size() = 0;

	/**
	 * Index 0 corresponds to the string stored at the head of the list.
	 * Positive and negative indices will be given.
	 * 
	 * For a positive index n, return the string n steps forward around the circle.
	 * For a negative index -n, return the string -n steps backward around the circle.
	 * There is neither a maximum nor a minimum bound on possible indices,
	 * so you may cycle through the list multiple times to find the requested string.
	 *
	 * returns the string "empty" if the list is empty (see note at top of class),
	 * Succeeds in all other cases.
	 */
	virtual string at(int index) = 0;
};

#endif /* CIRCULARDLLINTERFACE_H_ */


# linked_lists.py
"""Volume 2: Linked Lists.
<Name>
<Class>
<Date>
"""


# Problem 1
class Node:
    """A basic node class for storing data."""
    def __init__(self, data):
        """Store 'data' in the 'value' attribute. Raise TypeError if data
        isn't int, float, or string
        """
        if type(data) != int and type(data) != float and type(data) != str:
            raise TypeError("Data must be int, float, or string.")
        self.value = data


class LinkedListNode(Node):
    """A node class for doubly linked lists. Inherits from the 'Node' class.
    Contains references to the next and previous nodes in the linked list.
    """
    def __init__(self, data):
        """Store 'data' in the 'value' attribute and initialize
        attributes for the next and previous nodes in the list.
        """
        Node.__init__(self, data)       # Use inheritance to set self.value.
        self.next = None
        self.prev = None


class LinkedList:
    """Doubly linked list data structure class.

    Attributes:
        head (LinkedListNode): the first node in the list.
        tail (LinkedListNode): the last node in the list.
    """
    def __init__(self):
        """Initialize the 'head' and 'tail' attributes by setting
        them to 'None', since the list is empty initially.
        """
        self.head = None
        self.tail = None
        self.size = 0


    def append(self, data):
        """Append a new node containing 'data' to the end of the list."""
        # Create a new node to store the input data.
        new_node = LinkedListNode(data)
        if self.head is None:
            # If the list is empty, assign the head and tail attributes to
            # new_node, since it becomes the first and last node in the list.
            self.head = new_node
            self.tail = new_node
            self.size += 1
        else:
            # If the list is not empty, place new_node after the tail.
            self.tail.next = new_node               # tail --> new_node
            new_node.prev = self.tail               # tail <-- new_node
            # Now the last node in the list is new_node, so reassign the tail.
            self.tail = new_node
            self.size += 1

    # Problem 2
    def find(self, data):
        """Return the first node in the list containing 'data'.
        If no such node exists, raise a ValueError.

        Examples:
            >>> l = LinkedList()
            >>> for i in [1,3,5,7,9]:
            ...     l.append(i)
            ...
            >>> node = l.find(5)
            >>> node.value
            5
            >>> l.find(10)
            ValueError: <message>
        """
        if self.size == 0:
            raise ValueError("Data doesn't exist.")

        current = self.head
        while current.value != data:
            if current == self.tail:
                raise ValueError("Data doesn't exist.")
            current = current.next


        return current

    # Problem 3
    def __len__(self):
        """Return the number of nodes in the list.

        Examples:
            >>> l = LinkedList()
            >>> for i in [1,3,5]:
            ...     l.append(i)
            ...
            >>> len(l)
            3
            >>> l.append(7)
            >>> len(l)
            4
        """
        return self.size

    # Problem 3
    def __str__(self):
        """String representation: the same as a standard Python list.

        Examples:
            >>> l1 = LinkedList()   |   >>> l2 = LinkedList()
            >>> for i in [1,3,5]:   |   >>> for i in ['a','b',"c"]:
            ...     l1.append(i)    |   ...     l2.append(i)
            ...                     |   ...
            >>> print(l1)           |   >>> print(l2)
            [1, 3, 5]               |   ['a', 'b', 'c']
        """
        s = []
        if self.size == 0:
            return str(s)
        temp = self.head
        s.append(temp.value)
        while temp != self.tail:
            temp = temp.next
            s.append(temp.value)

        return str(s)

    # Problem 4
    def remove(self, data):
        """Remove the first node in the list containing 'data'. Return nothing.

        Raises:
            ValueError: if the list is empty, or does not contain 'data'.

        Examples:
            >>> print(l1)       |   >>> print(l2)
            [1, 3, 5, 7, 9]     |   [2, 4, 6, 8]
            >>> l1.remove(5)    |   >>> l2.remove(10)
            >>> l1.remove(1)    |   ValueError: <message>
            >>> l1.remove(9)    |   >>> l3 = LinkedList()
            >>> print(l1)       |   >>> l3.remove(10)
            [3, 7]              |   ValueError: <message>
        """
        if self.size == 0:
            raise ValueError("The list is empty.")
        temp = self.find(data)
        if temp == self.head:
            if self.size == 1:
                self.head = None
                self.tail = None
                self.size -= 1
                return
            self.head = temp.next
            self.head.prev = None
            self.size -= 1

        elif temp == self.tail:
            self.tail = temp.prev
            self.tail.next = None
            self.size -= 1

        else:
            temp.prev.next = temp.next
            temp.next.prev = temp.prev
            self.size -= 1

    # Problem 5
    def insert(self, data, place):
        """Insert a node containing 'data' immediately before the first node
        in the list containing 'place'. Return nothing.

        Raises:
            ValueError: if the list is empty, or does not contain 'place'.

        Examples:
            >>> print(l1)           |   >>> print(l1)
            [1, 3, 7]               |   [1, 3, 5, 7, 7]
            >>> l1.insert(7,7)      |   >>> l1.insert(3, 2)
            >>> print(l1)           |   ValueError: <message>
            [1, 3, 7, 7]            |
            >>> l1.insert(5,7)      |   >>> l2 = LinkedList()
            >>> print(l1)           |   >>> l2.insert(10,10)
            [1, 3, 5, 7, 7]         |   ValueError: <message>
        """
        if self.size == 0:
            raise ValueError("The list is empty.")

        new_node = LinkedListNode(data)
        temp = self.find(place)
        if temp == self.head:
            new_node.next = temp
            temp.prev = new_node
            self.head = new_node
            self.size += 1

        else:
            new_node.next = temp
            new_node.prev = temp.prev
            temp.prev.next = new_node
            temp.prev = new_node
            self.size += 1


# Problem 6: Write a Deque class.
class Deque(LinkedList):

    def __init__(self):
        LinkedList.__init__(self)

    def pop(self):
        if self.size == 1:
            old_tail = self.tail
            self.tail = None
            self.head = None
            self.size -= 1
            return old_tail.value

        old_tail = self.tail
        temp = self.tail.prev
        temp.next = None
        self.tail = temp
        self.size -= 1
        return old_tail.value

    def popleft(self):
        if self.size == 1:
            old_head = self.head
            self.tail = None
            self.head = None
            self.size -= 1
            return old_head.value

        old_head = self.head
        temp = self.head.next
        temp.prev = None
        self.head = temp
        self.size -= 1
        return old_head.value

    def appendleft(self, data):
        new_node = LinkedListNode(data)
        if self.head is None:
            # If the list is empty, assign the head and tail attributes to
            # new_node, since it becomes the first and last node in the list.
            self.head = new_node
            self.tail = new_node
            self.size += 1
        else:
            self.head.prev = new_node      
            new_node.next = self.head
            self.head = new_node
            self.size += 1

    def remove(*args, **kwargs):
        raise NotImplementedError("Use pop() or popleft() for removal")

    def insert(*args, **kwargs):
        raise NotImplementedError("Use append() or appendleft() for insertion")


# Problem 7
def prob7(infile, outfile):
    """Reverse the file 'infile' by line and write the results to 'outfile'."""
    lines = Deque()
    try:
        with open(infile, 'r') as myfile:
            for line in myfile:
                lines.append(line.strip('\n'))

    except (FileNotFoundError, TypeError, OSError) as e:
        print("Enter a valid file name.")

    with open(outfile, 'w') as outfile:
        for i in range(len(lines)):
            outfile.write(lines.pop() + '\n')







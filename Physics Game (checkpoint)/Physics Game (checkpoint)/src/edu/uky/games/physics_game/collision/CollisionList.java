package edu.uky.games.physics_game.collision;

import java.util.Comparator;

/**
 * A special kind of linked list that stores the edges of the axis-aligned
 * bounding boxes for rigid bodies along a single dimension.
 * 
 * @author Stephen G. Ware
 */
public class CollisionList {

	/** The first node in the list */
	private ListNode first = null;
	
	/** The length of the list */
	private int length = 0;
	
	/**
	 * Add an edge to the list.
	 * 
	 * @param node the edge node to add
	 */
	public void add(ListNode node) {
		node.next = first;
		first = node;
		length++;
	}
	
	@Override
	public String toString() {
		String str = "List (";
		ListNode current = first;
		while(current != null) {
			str += " " + current;
			current = current.next;
		}
		return str + " )";
	}
	
	/**
	 * Sorts the edges in this list from lowest to highest according to
	 * {@link ListNode#getValue()}.  This implementation uses Bubble Sort
	 * because it is simple to implement, but a more advanced physics engine
	 * would probably use Insertion Sort, which performs will on lists that
	 * are already mostly sorted.
	 */
	public void sort() {
		if(length <= 1)
			return;
		for(int i=length-1; i>=0; i--) {
			ListNode previous = null;
			ListNode current = first;
			ListNode next = current.next;
			boolean changed = false;
			for(int j=0; j<i; j++) {
				if(comparator.compare(current, next) > 0) {
					swap(previous, current, next);
					changed = true;
					previous = next;
				}
				else {
					previous = current;
					current = next;
				}
				next = current.next;
			}
			if(!changed)
				return;
		}
	}
	
	/** The comparator used to compare list node values */
	private final Comparator<ListNode> comparator = new Comparator<ListNode>() {

		@Override
		public int compare(ListNode node1, ListNode node2){
			double difference = node1.getValue() - node2.getValue();
			if(difference < 0)
				return -1;
			else if(difference == 0)
				return node1.body.id - node2.body.id;
			else
				return 1;
		}
	};
	
	/**
	 * Swap two nodes.
	 * 
	 * @param previous the node before current
	 * @param current the first node to be swapped
	 * @param next the second node to be swapped (must be the node immediately after current)
	 */
	private final void swap(ListNode previous, ListNode current, ListNode next) {
		current.next = next.next;
		if(previous == null)
			first = next;
		else
			previous.next = next;
		next.next = current;
	}
}

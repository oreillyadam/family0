

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test() {
		MyLinkList list = new MyLinkList();
	list.add(15);
	list.add(16);
	list.add(10);
			
	list.traverse();
	System.out.println("Does 10 exist in the list" + list .findNode(10));
	System.out.println("Does 15 exist in the list" + list .findNode(15));
	System.out.println("Does 16 exist in the list" + list .findNode(16));
	System.out.println("Does 20 exist in the list" + list .findNode(20));
	System.out.println("Does 1 exist in the list" + list .findNode(1));
	
	System.out.println("Deleting 16");
	list.deleteNode(16);
	list.traverse();
	System.out.println("Does 16 exist in the list " + list .findNode(16));

	
	System.out.println("done");

		
	}

}

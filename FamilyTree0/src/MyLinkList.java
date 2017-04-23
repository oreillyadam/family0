 

public class MyLinkList {
	
	Node head;
	Node tail;
	int size =0;
	
	
	
	public void add(int data){
		Node node = new Node(data);
		if(tail == null){
			head = node;
			tail = node;
		}
		else
		{
			tail.nextNode = node;
			tail=node;
			
		}
		size++;
	
}
	public Node deleteNode(int data)
	{
		/**
			scenario 1: empty list
			scenario 2: non-empty list
			scenario 3: delete element at tail
			scenario 4: delete element at head
			scenario 5: delete element in middle
			
		**/
		Node nodeToReturn = null;
		
		if(size == 0){
			return null;
		}
		if(size == 1){
			head = null;
			tail = null;
			size--;
			return nodeToReturn;
		}
			Node nodeBeforeNodeToDelete = findNodeBefore(data);
			//casse where we need to delete head
			if(nodeBeforeNodeToDelete.Data==0){
				head = head.nextNode;
				size--;
			}
			
			else if (nodeBeforeNodeToDelete !=null)
			{
				if(tail.Data == data)
				{
					nodeBeforeNodeToDelete.nextNode = null;
					tail =nodeBeforeNodeToDelete;
				}
				else
				{
					nodeBeforeNodeToDelete.nextNode = nodeBeforeNodeToDelete.nextNode.nextNode;
				}
				size--;
			}
			return null;
			
		}
		
	
	
	public Node findNodeBefore(int data)
	{
		 
		/**
		 * returns empty node if match is found at head
		 * if null is returned match not found
		 * returns populated node if data found
		 */
	 
		
 		if(head.Data==data)
			return new Node();
 		Node node = head;
 		while(node.nextNode != null){
			
			if(node.nextNode.Data==data)
				return node;
			node=node.nextNode;
			
		}
		return null;
	}

	
	public Node findNode(int data)
	{
		//scenario 1: empty list
		//scenario 2: non-empty list
		
		if(head == null)
			return null;
		
		//check 1st element to see if match
		if(head.Data==data)
			return head;
		//assign node as iterator 
		Node node = head;
		//iterate through list
		while(node.nextNode != null){
			node=node.nextNode;
			if(node.Data==data)
				return node;
			
		}
		return null;
	}

	void traverse()
	{
		if(head != null){
			Node node = head;
			System.out.println(node);
			while(node.nextNode != null)
			{
				node = node.nextNode;
				System.out.println(node);
			}
		}
	}
}

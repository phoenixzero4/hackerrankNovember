package prep.softwareengineeringprepkit.onePassRemovalOfKthNodeFromEnd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class SinglyLinkedListNode {
    public int data;
    public SinglyLinkedListNode next;

    public SinglyLinkedListNode(int nodeData) {
        this.data = nodeData;
        this.next = null;
    }
}

class SinglyLinkedList {
    public SinglyLinkedListNode head;
    public SinglyLinkedListNode tail;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void insertNode(int nodeData) {
        SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

        if (this.head == null) {
            this.head = node;
           
        } else {
            this.tail.next = node;
        }

        this.tail = node;
        
    }
}

class SinglyLinkedListPrintHelper {
    public static void printList(SinglyLinkedListNode node, String sep) {
        while (node != null) {
            System.err.print(node.data +" ");
            node = node.next;
        }
    }
}

class Result {

	/*
	 * Complete the 'removeKthNodeFromEnd' function below.
	 *
	 * The function is expected to return an INTEGER_SINGLY_LINKED_LIST. The
	 * function accepts following parameters: 1. INTEGER_SINGLY_LINKED_LIST head 2.
	 * INTEGER k
	 */

	/*
	 * For your reference:
	 *
	 * SinglyLinkedListNode { int data; SinglyLinkedListNode next; }
	 *
	 */

	public static SinglyLinkedListNode removeK(SinglyLinkedListNode head, int k){
	
			int size = 0;
			SinglyLinkedListNode c = head;
			
			while(c != null) {
				size++;
				c = c.next;
			}
			
		 if (k < 0 || k >= size) {
			    return head;
			}
		 
			int runner = size - k - 1;
			if (runner == 0) {
			    return head.next;
			}
			c = head;
			for (int i = 0; i < runner - 1; i++) {
			    c = c.next;
			}

			if (c.next != null) {
			    c.next = c.next.next;
			}

			return head;
			}
	
	public static SinglyLinkedListNode removeKthNodeFromEnd(SinglyLinkedListNode head, int k) {
		
		int size = 0;
		int runner = 0;
		
		if(head.next == null) {
			size++;
			return head;
		}
		SinglyLinkedListNode t = head;
		while( t.next != null) {
			size++;
			runner++;
			
			if(runner == size - k -1) {
				SinglyLinkedListNode c = t;
				c.next = t.next.next;
				t = c;
			}else {
				t = t.next;
			}		
		}
	return head;
	}
}

public class Solution {
	public static void main(String[] args) throws IOException {

		BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

		SinglyLinkedList list = new SinglyLinkedList();

		int headCount = Integer.parseInt(b.readLine().trim());

		IntStream.range(0, headCount).forEach(i -> {
			try {
				int headItem = Integer.parseInt(b.readLine().trim());

				list.insertNode(headItem);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}

		});

		int k = Integer.parseInt(b.readLine().trim());

	//	SinglyLinkedListNode result = Result.removeKthNodeFromEnd(list.head, k);
		SinglyLinkedListNode result2 = Result.removeK(list.head, k);
		
		

	//	SinglyLinkedListPrintHelper.printList(result, " ");
		SinglyLinkedListPrintHelper.printList(result2, " ");
		System.out.println();

		b.close();

	}
}

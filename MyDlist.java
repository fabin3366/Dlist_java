
import java.io.*;
import java.util.*;

public class MyDlist extends DList{
	/* Default constructor, to create a empty doubly linked list in MyDlist
	 * By calling parent class's default constructor.
	 */
	public MyDlist(){
		super();
	}
	
	/* construct doubly linked from System.in or a File
	 * Up to parameter f is "stdin" or not
	 * if read from System.in, then a line is a node
	 * otherwise, a string is a node.
	 */
	public MyDlist(String f){
		super();
		if(f.equals("stdin")){
		    Scanner in = new Scanner(System.in);
		    while (in.hasNextLine()){
		    	String new_element = in.nextLine();
		    	// empty line denotes end of input.
		    	if (0 == new_element.length()){
		    		break;
		    	} 
				addLast(new DNode(new_element, null, null));
		    }
		    in.close();
		}
		else{
			try{
				Scanner in = new Scanner(new File(f));
				while (in.hasNext()){
					addLast(new DNode(in.next(), null, null));
				}
				in.close();
			}
			catch (FileNotFoundException e) {
			} 
		}
	}
	
	// print MyDlist's members, each line for a node.
	public void printList() {
		DNode v = header.getNext();
	    while (v != trailer) {
	      System.out.println(v.getElement());
	      v = v.getNext();
	    }
	}	
	
	//copy a list to current list, the order of two lists are the same.
	public static MyDlist cloneList(MyDlist u){
		MyDlist copy_list = new MyDlist();
		DNode v = u.header.getNext();
	    while (v != u.trailer) {
	    	copy_list.addLast(new DNode(v.getElement(), null, null));
	      v = v.getNext();
	    }
		return copy_list;
	}
	
	/* functional method, to remove duplicate elements in a list
	 * return non-duplicate version of this list.
	 */
	public static MyDlist remove_dup(MyDlist u){
		MyDlist no_dup_list = new MyDlist();
		DNode v = u.header.getNext();
		boolean mark = false;
		while (v != u.trailer) {
			DNode w = v.getNext();
			while(w != u.trailer) {
				if ((v.getElement()).equals(w.getElement())) {
					mark = true;
				}
				w = w.getNext();
			}
			if (!mark) {
				no_dup_list.addLast(new DNode(v.getElement(), null, null));
			}
		    v = v.getNext();
		    mark = false;
		}
		return no_dup_list;
	}
	
	/* union two list's distinct elements together into a list
	 * firstly concatenate them, and then call method remove_dup
	 * time complexity is O(n^2).
	 */
	public static MyDlist union(MyDlist u, MyDlist v){
        MyDlist union_list = cloneList(u);
		DNode v_node = v.header.getNext();
		while (v_node != v.trailer) {
			union_list.addLast(new DNode(v_node.getElement(), null, null));
			v_node = v_node.getNext();
		}
		union_list = remove_dup(union_list);
		return union_list;
	}
	
	/* intersect two list's distinct elements together into a list
	 * firstly check parameters have duplicates or not
	 * (if parameters ensure no duplicates first step is not necessary).
	 * secondly traversing both list node to find unique elements add to result list
	 * time complexity is O(n^2).
	 */
	public static MyDlist intersection(MyDlist u, MyDlist v){
		MyDlist temp_u = remove_dup(u);
		MyDlist temp_v = remove_dup(v);
		MyDlist intersection_list = new MyDlist();
		DNode v_node = temp_v.header.getNext();
		while (v_node != temp_v.trailer) {
			DNode u_node = temp_u.header.getNext();
			while(u_node != temp_u.trailer) {
				if ((v_node.getElement()).equals(u_node.getElement())) {
					intersection_list.addLast(new DNode(v_node.getElement(), null, null));
					break;
				}
				u_node = u_node.getNext();
			}
		    v_node = v_node.getNext();
		}
		return intersection_list;
	}
}

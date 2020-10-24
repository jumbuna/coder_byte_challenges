import java.util.*; 
import java.io.*;

class Main {

	public static String TreeConstructor(String[] strArr) {
		boolean return_value = true;
		
		HashMap<Integer, Integer[]> parent_children_map = new HashMap<>();
		
		int parent_as_int, child_as_int;
		String parent_child_pair, parent_as_string, child_as_string;
		String[] parent_child_array;
		HashSet<String> tree_elements = new HashSet<>();
		Integer[] temp;
		for(int i = 0; i < strArr.length; i++) {
			parent_child_pair = strArr[i];
			parent_child_pair = parent_child_pair.substring(1, parent_child_pair.length()-1);
			parent_child_array = parent_child_pair.split(",");
			parent_as_string = parent_child_array[1];
			child_as_string = parent_child_array[0];
			parent_as_int = Integer.parseInt(parent_as_string);
			child_as_int = Integer.parseInt(child_as_string);
			if(tree_elements.contains(child_as_string)) {
				return_value = false;
				break;
			}
			if(parent_children_map.containsKey(parent_as_int)) {
				temp = parent_children_map.get(parent_as_int);
				if(child_as_int < parent_as_int && temp[0] == null) {
					temp[0] = child_as_int;
					tree_elements.add(child_as_string);
				} else if(child_as_int > parent_as_int && temp[1] == null) {
					temp[1] = child_as_int;
					tree_elements.add(child_as_string);
				} else {
					return_value = false;
					break;
				}
			} else {
				temp = new Integer[2];
				if(parent_as_int > child_as_int) {
					temp[0] = child_as_int;
				} else {
					temp[1] = child_as_int;
				}
				parent_children_map.put(parent_as_int, temp);
				tree_elements.add(child_as_string);
				
			}
		}
		
		
		return return_value ? "true" : "false";
	}

	public static void main (String[] args) {  
		// keep this function call here     
		Scanner s = new Scanner(System.in);
		System.out.print(TreeConstructor(new String[] {"(1,2)", "(2,4)", "(7,4)"})); 
	}

}
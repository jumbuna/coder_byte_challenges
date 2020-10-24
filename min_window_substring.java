import java.util.*; 
import java.io.*;

class Main {

	public static String MinWindowSubstring(String[] strArr) {
		
		HashMap<Character, Integer> pattern_map = new HashMap<>();
		int pattern_unique_chars = 0;
		
		String pattern = strArr[1];
		String long_text = strArr[0];
		
		int pattern_length = pattern.length();
		int long_text_length = long_text.length();
		
		char temp;
		
		for(int i = 0; i < pattern_length; i++) {
			temp = pattern.charAt(i);
			if(pattern_map.containsKey(temp)) {
				pattern_map.put(temp, pattern_map.get(temp)+1);
			} else {
				pattern_map.put(temp, 1);
				pattern_unique_chars++;
			}
		}
		
		int sliding_window_start = 0;
		int sliding_window_end = pattern_length-1;
		int sliding_window_unique_chars = 0;
		
		HashMap<Character, Integer> sliding_window_substring_map = new HashMap<>();
		
		for(int i = 0; i < pattern_length; i++) {
			temp = long_text.charAt(i);
			if(!pattern_map.containsKey(temp)) {
				continue;
			}
			if(sliding_window_substring_map.containsKey(temp)) {
				sliding_window_substring_map.put(temp, sliding_window_substring_map.get(temp)+1);
			} else {
				sliding_window_substring_map.put(temp, 1);
				sliding_window_unique_chars++;
			}
		}
		
		while(sliding_window_unique_chars < pattern_unique_chars) {
			++sliding_window_end;
			temp = long_text.charAt(sliding_window_end);
			if(pattern_map.containsKey(temp)) {
				if(sliding_window_substring_map.containsKey(temp)) {
					sliding_window_substring_map.put(temp, sliding_window_substring_map.get(temp)+1);
				} else {
					sliding_window_substring_map.put(temp, 1);
					sliding_window_unique_chars++;
				}
			}
		}
		
		for(; sliding_window_end-sliding_window_start+1 >= pattern_length; sliding_window_start++) {
			temp = long_text.charAt(sliding_window_start);
			if(pattern_map.containsKey(temp)) {
				if(sliding_window_substring_map.get(temp) > pattern_map.get(temp)) {
					sliding_window_substring_map.put(temp, sliding_window_substring_map.get(temp)-1);
				} else {
					break;
				}
			}
		}
		
		String return_string = long_text.substring(sliding_window_start, sliding_window_end+1);
		return return_string;
	}

	public static void main (String[] args) {  
		// keep this function call here     
		Scanner s = new Scanner(System.in);
		System.out.print(MinWindowSubstring(new String[] {"aaffhkksemckelloe", "fhea"})); 
	}

}
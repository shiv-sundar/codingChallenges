package codingChallenges;

import java.util.ArrayList;
import java.util.HashMap;

public class Intuit {
	private class Node {
		public ArrayList<Node> children = new ArrayList<Node>();
		public String key;
		public ArrayList<Node> parent = new ArrayList<Node>();
	}
	
	HashMap<String, Node> nodes = new HashMap<>();
	
	public void constructTree(String[] left, String[] right) {
		for (int x = 0; x < left.length; x++) {
			if (!nodes.containsKey(left[x])) {
				Node node = new Node();
				node.key = left[x];
				Node node2;
				if (nodes.containsKey(right[x])) {
					node2 = (Node) nodes.get(right[x]);
				}
				
				else {
					node2 = new Node();
					node2.key = right[x];
				}
				
				node2.parent.add(node);
				nodes.put(right[x], node2);
				node.children.add(node2);
				nodes.put(left[x], node);
			}
			
			else {
				Node node = nodes.get(left[x]);
				Node node2;
				if (nodes.containsKey(right[x])) {
					node2 = (Node) nodes.get(right[x]);
				}
				
				else {
					node2 = new Node();
					node2.key = right[x];
				}
				
				node2.parent.add(node);
				nodes.put(right[x], node2);
				node.children.add(node2);
			}
		}
	}
	
	public HashMap<String, Node> findLeaves(Node node) {
		HashMap<String, Node> toRet = new HashMap<String, Node>();
		if (node.children.size() == 0) {
			toRet.put(node.key, node);
			return toRet;
		}
		
		for (Node node2 : node.children) {
			toRet.putAll(findLeaves(node2));
		}
		
		return toRet;
	}
	
	public void printLeaves() {
		for (Node node : nodes.values()) {
			HashMap<String, Node> toRet = new HashMap<String, Node>();
			if (node.parent.size() == 0) {
				String leaves = "";
				System.out.print(node.key + ": ");
				for (Node node2 : node.children) {
					toRet.putAll(findLeaves(node2));;
				}
				
				for (Node node2 : toRet.values()) {
					leaves += node2.key + ", ";
				}
				
				leaves = leaves.substring(0, leaves.length() - 2);
				System.out.println(leaves);
			}
		}
	}
	
	public static void main(String[] args) {
		String[] left = new String[10];
		String[] right = new String[10];
		left[0] = "A";
		left[1] = "A";
		left[2] = "B";
		left[3] = "C";
		left[4] = "E";
		left[5] = "F";
		left[6] = "J";
		left[7] = "E";
		left[8] = "G";
		left[9] = "G";
		right[0] = "B";
		right[1] = "C";
		right[2] = "K";
		right[3] = "K";
		right[4] = "L";
		right[5] = "G";
		right[6] = "M";
		right[7] = "F";
		right[8] = "H";
		right[9] = "I";
		Intuit test = new Intuit();
		test.constructTree(left, right);
		test.printLeaves();
	}
}

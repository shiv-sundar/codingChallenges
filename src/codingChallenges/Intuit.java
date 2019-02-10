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
		left[2] = "Q";
		left[3] = "Q";
		left[4] = "D";
		left[5] = "F";
		left[6] = "F";
		left[7] = "L";
		left[8] = "T";
		left[9] = "T";
		right[0] = "Q";
		right[1] = "D";
		right[2] = "E";
		right[3] = "F";
		right[4] = "F";
		right[5] = "K";
		right[6] = "C";
		right[7] = "T";
		right[8] = "M";
		right[9] = "N";
		Intuit test = new Intuit();
		test.constructTree(left, right);
		test.printLeaves();
	}
}

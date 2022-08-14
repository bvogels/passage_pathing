package PassagePathing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Graph {
	
	public static int paths;
	private static Stack<Node> searchPath;


	private final AdjacencyMatrix adjacencies;
	public ArrayList<Node> nodes;
	public HashSet<String> builtNodes;
	public Node start;

	public Graph(AdjacencyMatrix m) {
		searchPath = new Stack<Node>();
		adjacencies = m;
		builtNodes = new HashSet<String>();
		paths = 0;
		start = new Node(true, "start", false);
		buildGraph("start", start);
	}

	private void buildGraph(String nodeName, Node newNode) {
		builtNodes.add(nodeName);
		if (!nodeName.equals("start")) {
			if (Character.isUpperCase(nodeName.charAt(0))) {
				newNode.name = nodeName;
				newNode.isBigCave = true;
			} else {
				newNode.name = nodeName;
				newNode.visited = false;
				newNode.isBigCave = false;
			}
		}
		for (String connection : adjacencies.matrix.get(nodeName)) {
			if (!builtNodes.contains(connection)) {
				Node m = new Node();
				newNode.connections.add(m);
				buildGraph(connection, m);
			} else {
				Node c = findNode(connection, start);
				searchPath.clear();
				newNode.connections.add(c);
			}
		}
	}

	private Node findNode(String existingNodeName, Node startNode) {
		Set<Node> searchedNodes = new HashSet<Node>();
		searchPath.push(startNode);
		while (!searchPath.isEmpty()) {
			Node currentNode = searchPath.pop();
			if (currentNode.name.equals(existingNodeName)) {
				return currentNode;
			}
			searchedNodes.add(currentNode);
			for (Node existingNode : currentNode.connections) {
				if (!searchedNodes.contains(existingNode))
					searchPath.push(existingNode);
			}
		}
		return null;
	}
	
	public void findAllPaths(Node node) {
		if (node.name.equals("end")) {
			paths++;
			node.visited = false;
		} else {	
			for (Node nextNode : node.connections) {				
				if (!nextNode.isBigCave && nextNode.visited) {
					continue;
				}
				if (nextNode.equals(node.nextInPath)) {
					continue;
				}
				if (!nextNode.isBigCave) {
					nextNode.visited = true;
				}				
				node.nextInPath = nextNode;
				findAllPaths(nextNode);			
			}
			node.nextInPath = null;
			if (!node.isBigCave) {
				node.visited = false;
			}
		}
	}
}

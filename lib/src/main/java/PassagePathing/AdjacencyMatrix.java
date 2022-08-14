package PassagePathing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class AdjacencyMatrix {

	public HashMap<String, ArrayList<String>> matrix;
	public Stack<Node> searchPath;

	public AdjacencyMatrix() {
		matrix = new HashMap<String, ArrayList<String>>();
		searchPath = new Stack<Node>();
	}

	private void loadData() throws IOException {
		File data = new File("");
		FileReader fr = new FileReader(data);
		BufferedReader br = new BufferedReader(fr);
		createMatrix(br);
		fr.close();
	}

	private void createMatrix(BufferedReader br) throws IOException {
		String line;
		while ((line = br.readLine()) != null) {
			int hyphen = line.indexOf("-");        	
			matrix.computeIfAbsent(line.substring(0, hyphen), k -> new ArrayList<>())
			.add(line.substring(hyphen+1, line.length()));
			matrix.computeIfAbsent(line.substring(hyphen+1, line.length()), k -> new ArrayList<>())
			.add(line.substring(0, hyphen));
		}
	}


	public static void main(String[] args) throws IOException {
		AdjacencyMatrix a = new AdjacencyMatrix();
		a.loadData();
		System.out.println(a.matrix);
		Graph g = new Graph(a);
		g.findAllPaths(g.start);
		System.out.println("Number of paths in current graph: " + Graph.paths);
	}
}

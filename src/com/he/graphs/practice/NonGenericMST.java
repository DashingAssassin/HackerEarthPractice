package com.he.graphs.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/**
 * This class is practice of MST algorithm for Graphs
 * 
 * @author nikhil
 *
 */
class MSTNode {
	private int x;

	public MSTNode(int x) {
		this.x = x;
	}

	public int getNode() {
		return this.x;
	}

	public void setNode(int x) {
		this.x = x;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MSTNode other = (MSTNode) obj;
		if (x != other.x)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [x=" + x + "]";
	}

}

class MSTEdge implements Comparable<MSTEdge> {
	private MSTNode start;
	private MSTNode end;
	private long weight;

	public MSTEdge(MSTNode start, MSTNode end, long weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public MSTNode getStart() {
		return start;
	}

	public void setStart(MSTNode start) {
		this.start = start;
	}

	public MSTNode getEnd() {
		return end;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public long getWeight() {
		return this.weight;
	}

	public void setEnd(MSTNode end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + (int) (weight ^ (weight >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MSTEdge other = (MSTEdge) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Edge [start=" + start + ", end=" + end + "]";
	}

	public static <T extends Number> MSTNode root(Map<MSTNode, MSTNode> id, MSTNode value) {

		if (id.containsKey(value)) {
			while (!id.get(value).equals(value)) {
				id.put(id.get(value), id.get(id.get(value)));
				// id.put(value, id.get(value));
				value = id.get(value);
			}
		}

		return value;
	}

	public static <T extends Number> void union(Map<MSTNode, MSTNode> id, MSTNode x, MSTNode y) {
		MSTNode p = root(id, x);
		MSTNode q = root(id, y);
		id.put(p, q);
	}

	public static <T extends Number> long kruskals(MSTEdge[] id, Map<MSTNode, MSTNode> idMap) {
		long minCost = 0;
		for (MSTEdge edge : id) {
			MSTNode first = edge.getStart();
			MSTNode end = edge.getEnd();
			if (root(idMap, first) != root(idMap, end)) {
				minCost += ((Number) edge.getWeight()).longValue();
				union(idMap, first, end);

			}

		}

		return minCost;
	}

	@Override
	public int compareTo(MSTEdge o) {
		return ((Long) this.weight).compareTo((Long) (o.getWeight()));
	}
}

public class NonGenericMST {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pw = new PrintWriter(System.out, false);

	public static void main(String[] args) throws Exception {
		// For Disjoint you need an array of the nodes and initialize method , root and
		// union
		//long time1 = System.currentTimeMillis();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int nodes = Integer.parseInt(st.nextToken());
		int edges = Integer.parseInt(st.nextToken());
		MSTNode[] nodesArray = new MSTNode[nodes + 1];
		MSTEdge[] edgesArray = new MSTEdge[edges];
		Map<MSTNode, MSTNode> idMap = new HashMap<>();
		for (int i = 1; i < nodesArray.length; i++) {
			nodesArray[i] = new MSTNode(i);
			idMap.put(nodesArray[i], nodesArray[i]);
		}
		for (int i = 0; i < edges; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			long weight = Integer.parseInt(st.nextToken());
			edgesArray[i] = new MSTEdge(nodesArray[x], nodesArray[y], weight);
		}
		Arrays.sort(edgesArray);
		pw.println(MSTEdge.kruskals(edgesArray, idMap));
		pw.flush();
		//long time2 = System.currentTimeMillis();
//		System.out.println("Total Time taken= " + (time1 - time2) / 1000 + " secs");

	}

}

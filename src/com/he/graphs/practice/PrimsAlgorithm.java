package com.he.graphs.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * This class is practice of MST algorithm for Graphs
 * 
 * @author nikhil
 *
 */
class PrimsNode {
	private int x;

	public PrimsNode(int x) {
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
		PrimsNode other = (PrimsNode) obj;
		if (x != other.x)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Node [x=" + x + "]";
	}

}

class PrimsEdge implements Comparable<PrimsEdge> {
	private PrimsNode start;
	private PrimsNode end;
	private long weight;

	public PrimsEdge(PrimsNode start, PrimsNode end, long weight) {
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public PrimsNode getStart() {
		return start;
	}

	public void setStart(PrimsNode start) {
		this.start = start;
	}

	public PrimsNode getEnd() {
		return end;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public long getWeight() {
		return this.weight;
	}

	public void setEnd(PrimsNode end) {
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
		PrimsEdge other = (PrimsEdge) obj;
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

	@Override
	public int compareTo(PrimsEdge o) {
		return ((Long) this.weight).compareTo((Long) (o.getWeight()));
	}
}

public class PrimsAlgorithm {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pw = new PrintWriter(System.out, false);

	public static void main(String[] args) throws Exception {

		StringTokenizer st = new StringTokenizer(br.readLine());
		int nodes = Integer.parseInt(st.nextToken());
		int edges = Integer.parseInt(st.nextToken());
		PriorityQueue<PrimsEdge> pq = new PriorityQueue<>();
		// PrimsEdge[] primsEdge = new PrimsEdge[edges + 1];
		PriorityQueue<PrimsEdge>[] primsEdges = new PriorityQueue[edges + 1];
		for (int i = 0; i < edges; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			long weight = Integer.parseInt(st.nextToken());
			if (null == primsEdges[x]) {
				primsEdges[x] = new PriorityQueue<>();
			}
			if (null == primsEdges[y]) {
				primsEdges[y] = new PriorityQueue<>();
			}
			PrimsEdge primEd1 = new PrimsEdge(new PrimsNode(x), new PrimsNode(y), weight);
			PrimsEdge primEd2 = new PrimsEdge(new PrimsNode(y), new PrimsNode(x), weight);
			primsEdges[x].add(primEd1);
			primsEdges[y].add(primEd2);
		}

		long minCost = 0;
		boolean[] marked = new boolean[nodes + 1];
		PrimsEdge pg = new PrimsEdge(new PrimsNode(1), new PrimsNode(1), 0);
		pq.add(pg);
		while (!pq.isEmpty()) {
			PrimsEdge edge = pq.poll();
			PrimsNode a = edge.getStart();
			PrimsNode b = edge.getEnd();
			if (!marked[b.getNode()]) {
			//	System.out.println("Weight befor addition of Node = " + b.getNode() + "  is=" + minCost
			//			+ "  weight to be added=" + edge.getWeight());
				minCost += edge.getWeight();
				marked[b.getNode()] = true;
				//System.out.println("Weight after addition of Node = " + b.getNode() + "  is=" + minCost
				//		+ "  added weight=" + edge.getWeight());
				PriorityQueue<PrimsEdge> pgEdges = primsEdges[b.getNode()];
				if (null != pgEdges) {
					for (PrimsEdge primsEdge : pgEdges) {
						if (!marked[primsEdge.getEnd().getNode()]) {
							pq.add(primsEdge);
						}
					}
//					pgEdges.forEach(y -> {
//						if (!marked[y.getEnd().getNode()]) {
//							pq.add(y);
//						}
//					});
				}
			}

		}
		pw.println(minCost);
		pw.flush();

	}

}

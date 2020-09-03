package com.he.graphs.shortestpath.practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

class BellmanNode {
	private int x;

	public BellmanNode(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BellmanNode other = (BellmanNode) obj;
		return x == other.x;
	}

	@Override
	public String toString() {
		return "BellmanNode [x=" + x + "]";
	}

}

class BellmanFordEdge {
	private BellmanNode start;
	private BellmanNode end;
	private int weight;

	public BellmanFordEdge(BellmanNode start, BellmanNode end, int weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}

	public BellmanFordEdge() {
		super();
	}

	public BellmanNode getStart() {
		return start;
	}

	public void setStart(BellmanNode start) {
		this.start = start;
	}

	public BellmanNode getEnd() {
		return end;
	}

	public void setEnd(BellmanNode end) {
		this.end = end;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		return Objects.hash(end, start, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BellmanFordEdge other = (BellmanFordEdge) obj;
		return Objects.equals(end, other.end) && Objects.equals(start, other.start)
				&& Objects.equals(weight, other.weight);
	}

	@Override
	public String toString() {
		return "BellmanFordEdge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
	}

}

public class BellManFordAlgorithm {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pw = new PrintWriter(System.out, false);

	public static void main(String[] args) throws Exception {
		long time1 = System.currentTimeMillis();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int[] distance = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		List<BellmanFordEdge>[] belmanEdges = new LinkedList[n + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			System.out.println("total value of i=" + i);
			BellmanFordEdge bfe = new BellmanFordEdge(new BellmanNode(a), new BellmanNode(b), weight);
			if (null == belmanEdges[a]) {
				belmanEdges[a] = new LinkedList<>();
			}
			belmanEdges[a].add(bfe);
		}
		distance[0] = 0;
		distance[1] = 0;
		for (int i = 1; i < n; i++) {
			int j = 1;
			while (null != belmanEdges[j]) {
				List<BellmanFordEdge> edges = belmanEdges[i];
				if (null != edges) {
					for (BellmanFordEdge bellmanFordEdge : edges) {
						if (distance[bellmanFordEdge.getStart().getX()]
								+ bellmanFordEdge.getWeight() < distance[bellmanFordEdge.getEnd().getX()]) {
							distance[bellmanFordEdge.getEnd().getX()] = distance[bellmanFordEdge.getStart().getX()]
									+ bellmanFordEdge.getWeight();
						}
					}
				}
				j++;
			}

		}

		for (int i = 2; i < distance.length; i++) {
			pw.print(distance[i] + " ");
		}
		pw.println();
		pw.flush();
		long time2 = System.currentTimeMillis();
		System.out.println("time taken  = " + (time2 - time1) + "   seconds");
	}
}

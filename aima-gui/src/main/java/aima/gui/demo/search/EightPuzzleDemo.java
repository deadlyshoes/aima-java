package aima.gui.demo.search;

import aima.core.agent.Action;
import aima.core.environment.eightpuzzle.*;
import aima.core.search.agent.SearchAgent;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.problem.Problem;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.informed.GreedyBestFirstSearch;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.search.uninformed.UniformCostSearch;

import java.util.List;
import java.util.Properties;

/**
 * @author Ravi Mohan
 * @author Ruediger Lunde
 * 
 */

public class EightPuzzleDemo {
	private static EightPuzzleBoard[] boardWithThreeMoveSolutions =
		 {new EightPuzzleBoard(new int[] {1, 4, 2, 7, 5, 8, 3, 0, 6}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 3, 4, 8, 6, 5, 7}),
					new EightPuzzleBoard(new int[] {1, 2, 6, 0, 4, 8, 5, 3, 7}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 3, 6, 7, 4, 5, 8}),
					new EightPuzzleBoard(new int[] {1, 2, 5, 3, 4, 8, 6, 0, 7}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 3, 8, 4, 7, 5, 6}),
					new EightPuzzleBoard(new int[] {1, 2, 6, 0, 4, 3, 5, 7, 8}),
					new EightPuzzleBoard(new int[] {1, 2, 5, 4, 8, 3, 6, 0, 7}),
					new EightPuzzleBoard(new int[] {1, 2, 5, 8, 3, 0, 4, 6, 7}),
					new EightPuzzleBoard(new int[] {1, 2, 5, 6, 3, 8, 4, 0, 7}),
					new EightPuzzleBoard(new int[] {1, 2, 5, 7, 8, 3, 4, 0, 6}),
					new EightPuzzleBoard(new int[] {1, 2, 5, 6, 3, 0, 4, 7, 8}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 5, 3, 4, 6, 7, 8}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 5, 3, 8, 6, 4, 7}),
					new EightPuzzleBoard(new int[] {1, 2, 5, 4, 6, 7, 8, 0, 3}),
					new EightPuzzleBoard(new int[] {1, 2, 5, 8, 3, 7, 4, 0, 6}),
					new EightPuzzleBoard(new int[] {4, 6, 1, 5, 8, 2, 3, 0, 7}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 5, 8, 3, 7, 4, 6}),
					new EightPuzzleBoard(new int[] {4, 2, 1, 7, 8, 3, 5, 0, 6}),
					new EightPuzzleBoard(new int[] {3, 5, 2, 0, 8, 4, 6, 1, 7}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 6, 4, 7, 8, 3, 5}),
					new EightPuzzleBoard(new int[] {3, 1, 4, 2, 8, 5, 6, 0, 7}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 6, 7, 4, 5, 3, 8}),
					new EightPuzzleBoard(new int[] {2, 8, 3, 4, 5, 6, 7, 0, 1}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 7, 3, 4, 5, 6, 8}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 7, 3, 8, 5, 4, 6}),
					new EightPuzzleBoard(new int[] {2, 6, 3, 5, 1, 0, 4, 7, 8}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 7, 5, 6, 3, 4, 8}),
					new EightPuzzleBoard(new int[] {2, 5, 3, 6, 4, 7, 8, 0, 1}),
					new EightPuzzleBoard(new int[] {0, 1, 2, 7, 8, 3, 6, 4, 5}),
					new EightPuzzleBoard(new int[] {1, 6, 3, 8, 7, 2, 4, 0, 5}),
					new EightPuzzleBoard(new int[] {1, 2, 5, 3, 4, 0, 6, 7, 8})};

	private static EightPuzzleBoard random1 =
			new EightPuzzleBoard(new int[] { 1, 4, 2, 7, 5, 8, 3, 0, 6 });

//	private static EightPuzzleBoard extreme =
//			new EightPuzzleBoard(new int[] { 0, 8, 7, 6, 5, 4, 3, 2, 1 });

	public static void main(String[] args) {
		int n = 32;
		
		double maxQueueSize = 0;
		double pathCost = 0;
		double nodesExpanded = 0;
		 
		for (int i = 0; i < n; i++) {
			System.out.println(i + 1);
			EightPuzzleBoard initialState = boardWithThreeMoveSolutions[i];
			//System.out.println("Initial State:\n" + initialState);
			//eightPuzzleDLSDemo(initialState);
			Properties prop = eightPuzzleDLSDemo(initialState);
			System.out.println(prop);
			//maxQueueSize += Double.parseDouble(prop.getProperty("maxQueueSize"));
			pathCost += Double.parseDouble(prop.getProperty("pathCost"));
			nodesExpanded += Double.parseDouble(prop.getProperty("nodesExpanded"));
		}
		
		maxQueueSize /= n;
		pathCost /= n;
		nodesExpanded /= n;
		
		System.out.println("nodesExpanded: " + nodesExpanded);
		System.out.println("pathCost: " + pathCost);
		System.out.println("maxQueueSize: " + maxQueueSize);
	}

	private static Properties eightPuzzleDLSDemo(EightPuzzleBoard initialState) {
		System.out.println("\nEightPuzzleDemo recursive DLS (100)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initialState);
			SearchForActions<EightPuzzleBoard, Action> search = new DepthLimitedSearch<>(100);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			
			return agent.getInstrumentation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Properties eightPuzzleBFSGraphDemo(EightPuzzleBoard initialState) {
		//System.out.println("\nEightPuzzleDemo BFS Graph");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initialState);
			SearchForActions<EightPuzzleBoard, Action> search = new BreadthFirstSearch<>(new GraphSearch<>());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			
			return agent.getInstrumentation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Properties eightPuzzleBFSTreeDemo(EightPuzzleBoard initialState) {
		//System.out.println("\nEightPuzzleDemo BFS Graph");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initialState);
			SearchForActions<EightPuzzleBoard, Action> search = new BreadthFirstSearch<>(new TreeSearch<>());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			System.out.println("");
			printInstrumentation(agent.getInstrumentation());
			return agent.getInstrumentation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Properties eightPuzzleDFSGraphDemo(EightPuzzleBoard initialState) {
		//System.out.println("\nEightPuzzleDemo BFS Graph");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initialState);
			SearchForActions<EightPuzzleBoard, Action> search = new DepthFirstSearch<>(new GraphSearch<>());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			
			return agent.getInstrumentation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Properties eightPuzzleDFSTreeDemo(EightPuzzleBoard initialState) {
		//System.out.println("\nEightPuzzleDemo BFS Graph");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initialState);
			SearchForActions<EightPuzzleBoard, Action> search = new DepthFirstSearch<>(new TreeSearch<>());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			
			return agent.getInstrumentation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Properties eightPuzzleUCSGraphDemo(EightPuzzleBoard initialState) {
		//System.out.println("\nEightPuzzleDemo BFS Graph");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initialState);
			SearchForActions<EightPuzzleBoard, Action> search = new UniformCostSearch<>(new GraphSearch<>());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			
			return agent.getInstrumentation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Properties eightPuzzleUCSTreeDemo(EightPuzzleBoard initialState) {
		//System.out.println("\nEightPuzzleDemo BFS Graph");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initialState);
			SearchForActions<EightPuzzleBoard, Action> search = new UniformCostSearch<>(new TreeSearch<>());
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			
			return agent.getInstrumentation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Properties eightPuzzleIDLSDemo(EightPuzzleBoard initialState) {
		System.out.println("\nEightPuzzleDemo Iterative DLS");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(initialState);
			SearchForActions<EightPuzzleBoard, Action> search = new IterativeDeepeningSearch<>();
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			
			return agent.getInstrumentation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*private static void eightPuzzleGreedyBestFirstDemo() {
		System.out.println("\nEightPuzzleDemo Greedy Best First Search (MisplacedTileHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SearchForActions<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions::getNumberOfMisplacedTiles);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleGreedyBestFirstManhattanDemo() {
		System.out.println("\nEightPuzzleDemo Greedy Best First Search (ManhattanHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(boardWithThreeMoveSolution);
			SearchForActions<EightPuzzleBoard, Action> search = new GreedyBestFirstSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions::getManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void eightPuzzleAStarDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (MisplacedTileHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions::getNumberOfMisplacedTiles);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleSimulatedAnnealingDemo() {
		System.out.println("\nEightPuzzleDemo Simulated Annealing Search");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SimulatedAnnealingSearch<EightPuzzleBoard, Action> search = new SimulatedAnnealingSearch<>
					(EightPuzzleFunctions::getManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			System.out.println("Final State:\n" + search.getLastState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void eightPuzzleAStarManhattanDemo() {
		System.out.println("\nEightPuzzleDemo AStar Search (ManhattanHeursitic)");
		try {
			Problem<EightPuzzleBoard, Action> problem = new BidirectionalEightPuzzleProblem(random1);
			SearchForActions<EightPuzzleBoard, Action> search = new AStarSearch<>
					(new GraphSearch<>(), EightPuzzleFunctions::getManhattanDistance);
			SearchAgent<Object, EightPuzzleBoard, Action> agent = new SearchAgent<>(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */
	private static void printInstrumentation(Properties properties) {
		properties.keySet().stream().map(key -> key + "=" + properties.get(key)).forEach(System.out::println);
	}

	private static void printActions(List<Action> actions) {
		actions.forEach(System.out::println);
	}
}
package aima.gui.demo.agent;

import aima.core.agent.Agent;
import aima.core.agent.EnvironmentListener;
import aima.core.agent.impl.DynamicPercept;
import aima.core.agent.impl.SimpleEnvironmentView;
import aima.core.environment.map.*;
import aima.core.search.framework.Metrics;
import aima.core.search.framework.SearchForActions;
import aima.core.search.framework.qsearch.GraphSearch;
import aima.core.search.framework.qsearch.TreeSearch;
import aima.core.search.informed.AStarSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import aima.core.search.uninformed.UniformCostSearch;

/**
 * Demonstrates how different kinds of search algorithms perform in a route finding scenario.
 * @author Ruediger Lunde
 */
public class MapAgentDemo {
	public static void main(String[] args) {
		double maxQueueSize = 0;
		double nodesExpanded = 0;
		double pathCost = 0;
		
		int n = 32;
		String initial_states_romania[] = {	SimplifiedRoadMapOfRomania.ORADEA,
			SimplifiedRoadMapOfRomania.ZERIND,
			SimplifiedRoadMapOfRomania.ARAD,
			SimplifiedRoadMapOfRomania.TIMISOARA,
			SimplifiedRoadMapOfRomania.LUGOJ,
			SimplifiedRoadMapOfRomania.MEHADIA,
			SimplifiedRoadMapOfRomania.DOBRETA,
			SimplifiedRoadMapOfRomania.SIBIU,
			SimplifiedRoadMapOfRomania.RIMNICU_VILCEA,
			SimplifiedRoadMapOfRomania.CRAIOVA,
			SimplifiedRoadMapOfRomania.FAGARAS,
			SimplifiedRoadMapOfRomania.PITESTI,
			SimplifiedRoadMapOfRomania.GIURGIU,
			SimplifiedRoadMapOfRomania.BUCHAREST,
			SimplifiedRoadMapOfRomania.NEAMT,
			SimplifiedRoadMapOfRomania.URZICENI,
			SimplifiedRoadMapOfRomania.IASI,
			SimplifiedRoadMapOfRomania.VASLUI,
			SimplifiedRoadMapOfRomania.HIRSOVA,
			SimplifiedRoadMapOfRomania.EFORIE };

		
		for (int i = 0; i < 20; i++) {
			ExtendableMap map = new ExtendableMap();
			SimplifiedRoadMapOfRomania.initMap(map);
			MapEnvironment env = new MapEnvironment(map);
			EnvironmentListener<Object, Object> envView = new SimpleEnvironmentView();
			env.addEnvironmentListener(envView);
	
			String agentLoc = initial_states_romania[i];
			String destination = SimplifiedRoadMapOfRomania.BUCHAREST;
	
			SearchForActions<String, MoveToAction> search;
			search = new DepthLimitedSearch<>(100);
			// search = new IterativeDeepeningSearch<>();
			// search = new UniformCostSearch<>(new TreeSearch<>());
			// search = new UniformCostSearch<>(new GraphSearch<>());
			// search = new AStarSearch<>(new GraphSearch<>(), MapFunctions.createSLDHeuristicFunction(destination, map));
	
			Agent<DynamicPercept, MoveToAction> agent;
			agent = new SimpleMapAgent(map, search, destination);
			// agent = new MapAgent(map, search, destination);
	
			env.addAgent(agent, agentLoc);
			env.stepUntilDone();
			envView.notify(search.getMetrics().toString());
			
			Metrics metrics = search.getMetrics();
			maxQueueSize += metrics.getInt("maxQueueSize");
			nodesExpanded += metrics.getInt("nodesExpanded");
			pathCost += metrics.getDouble("pathCost");
		}
		
		String initial_states_australia[] = { SimplifiedRoadMapOfAustralia.ADELAIDE,
			SimplifiedRoadMapOfAustralia.ALBANY,
			SimplifiedRoadMapOfAustralia.ALICE_SPRINGS,
			SimplifiedRoadMapOfAustralia.BRISBANE,
			SimplifiedRoadMapOfAustralia.BROKEN_HILL,
			SimplifiedRoadMapOfAustralia.BROOME,
			SimplifiedRoadMapOfAustralia.CAIRNS,
			SimplifiedRoadMapOfAustralia.CAMARVON,
			SimplifiedRoadMapOfAustralia.CANBERRA,
			SimplifiedRoadMapOfAustralia.CHARLEVILLE,
			SimplifiedRoadMapOfAustralia.COOBER_PEDY,
			SimplifiedRoadMapOfAustralia.DARWIN };
		
		for (int i = 0; i < 12; i++) {
			ExtendableMap map = new ExtendableMap();
			SimplifiedRoadMapOfAustralia.initMap(map);
			MapEnvironment env = new MapEnvironment(map);
			EnvironmentListener<Object, Object> envView = new SimpleEnvironmentView();
			env.addEnvironmentListener(envView);
	
			String agentLoc = initial_states_romania[i];
			String destination = SimplifiedRoadMapOfAustralia.WYNDHAM;
	
			SearchForActions<String, MoveToAction> search;
			search = new DepthLimitedSearch<>(100);
			// search = new IterativeDeepeningSearch<>();
			// search = new UniformCostSearch<>(new TreeSearch<>());
			// search = new UniformCostSearch<>(new GraphSearch<>());
			// search = new AStarSearch<>(new GraphSearch<>(), MapFunctions.createSLDHeuristicFunction(destination, map));
	
			Agent<DynamicPercept, MoveToAction> agent;
			agent = new SimpleMapAgent(map, search, destination);
			// agent = new MapAgent(map, search, destination);
	
			env.addAgent(agent, agentLoc);
			env.stepUntilDone();
			envView.notify(search.getMetrics().toString());
			
			Metrics metrics = search.getMetrics();
			maxQueueSize += metrics.getInt("maxQueueSize");
			nodesExpanded += metrics.getInt("nodesExpanded");
			pathCost += metrics.getDouble("pathCost");
		}
		
		System.out.println("");
		System.out.println("média de nós expandidos: " + (nodesExpanded / n));
		System.out.println("média de custo do caminho: " + (pathCost / n));
		System.out.println("média de tamanho máximo da fila: " + (maxQueueSize / n));
	}
}

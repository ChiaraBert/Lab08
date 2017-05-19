package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	UndirectedGraph<Country,DefaultEdge> grafo;

	public Model() {
	grafo = new SimpleGraph<Country,DefaultEdge>(DefaultEdge.class);
	}

	public String calcolaConfini(int anno) {
		String s = "";	
		for(Country c: grafo.vertexSet()){
			s+=c.getStateName()+" "+grafo.degreeOf(c)+" ";
		}
		return s;
	}

	public void creaGrafo(int anno) {
		List<Country> paesi = new ArrayList<Country>(); 
		BordersDAO b = new BordersDAO();
		
		for(Border bw :b.getCountryPairs(anno)){
			if(!paesi.contains(bw.getC1()))
			paesi.add(bw.getC1());
		}
		
		Graphs.addAllVertices(grafo, paesi);
		
		for(Border br : b.getCountryPairs(anno)){
			grafo.addEdge(br.getC1(), br.getC2());
		}
		
	}
	
	public int calcolaConnessioni(){
		List<Country> paesiConn = new ArrayList<Country>();
		ConnectivityInspector<Country,DefaultEdge> connessioni = new ConnectivityInspector<Country,DefaultEdge> (grafo);
		
		for(Country c: grafo.vertexSet())
			connessioni.connectedSetOf(c);
			
		return connessioni.connectedSets().size();
	 
	}

	}

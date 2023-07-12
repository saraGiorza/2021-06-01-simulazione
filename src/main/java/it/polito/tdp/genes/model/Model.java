package it.polito.tdp.genes.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao = new GenesDao();
	private Graph<Genes, DefaultWeightedEdge> grafo;
	
	public void creaGrafo() {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		//vertici
		Graphs.addAllVertices(grafo, dao.getGeniEssenziali());
		
		//archi
		//per ogni coppia cerco se c'è in interaction
		for(Genes g1: grafo.vertexSet()) {
			for(Genes g2: grafo.vertexSet()) {
				if(!g1.equals(g2)) {
					float corr = dao.interazione(g1.getGeneId(), g2.getGeneId());
					if(corr != -1) { //in teoria se non c'e' correlazione il peso risulta 0
						//controllo se i due geni sono sullo stesso cromosoma
						
						if(g1.getChromosome() == g2.getChromosome()) {
							Graphs.addEdge(grafo, g1, g2, (2*corr));
						}
						else {
							Graphs.addEdge(grafo, g1, g2, corr);
							}
					}
				}				
			}
		}		
	}
	
	public String infoGrafo() {

		// se il grafo non è stato creato ritorna stringa vuota
		try {
			return "Grafo creato con " + grafo.vertexSet().size() + " vertici " + grafo.edgeSet().size() + " archi";
		} catch (NullPointerException npe) {
			return "";
		}

	}
	
	public Set<Genes> vertici(){
		return grafo.vertexSet();
	}
	
	public String geniAdiacenti(Genes g) {
		String result = "";
		result+= "\nGeni adiacenti a " + g.getGeneId() + "\n";
		List<Genes> vicini = Graphs.neighborListOf(grafo, g);
		List<Arco> archi = new ArrayList<>();
		for(Genes g1: vicini) {
			float peso = (float) grafo.getEdgeWeight(grafo.getEdge(g, g1));
			Arco a = new Arco(g, g1, peso);
			archi.add(a);
		}
		
		Collections.sort(archi);
		for(Arco a: archi) {
			result+= a.getG2() + "   " + a.getPeso() + "\n";			
		}
		
		return result;
	}
	
	
}

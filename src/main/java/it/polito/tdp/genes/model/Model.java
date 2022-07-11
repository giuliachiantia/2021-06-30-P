package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph<String,DefaultWeightedEdge>grafo;
	private List<String> vertici;
	private List<String> best;
	private int pesoMax;
	
	public Model() {
		this.dao=new GenesDao();
		this.vertici=new ArrayList<>(dao.getAllLocalization());
	}
	public List<String> getLoc(){
		return dao.getAllLocalization();
	}
	
	public void creaGrafo() {
		this.grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(this.grafo, this.vertici);
		for(Adiacenza a: dao.getArchi()) {
			Graphs.addEdge(this.grafo, a.getL1(), a.getL2(), a.getPeso());
		}
	}
	
	public List<Adiacenza> getAdiacenti(String loc){
		List <Adiacenza> list = new ArrayList<>();
		for(String s: Graphs.neighborListOf(this.grafo, loc)) {
			DefaultWeightedEdge e=this.grafo.getEdge(s, loc);
			double peso= this.grafo.getEdgeWeight(e);
			Adiacenza a= new Adiacenza(loc, s, (int) peso);
			list.add(a);

		}
		return list;
	}
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	//ricorsione
	
	/*public List<String> camminoBest(String localizzazione){
		this.best= new ArrayList<String>();
		this.best.add(localizzazione);
		int peso=0;
		int pesop=0;
		List<String>parziale= new ArrayList<String>();
		parziale.add(localizzazione);
		cerca(parziale, peso, pesop);
		
		return best;
		
	}

	private void cerca(List<String> parziale,  int peso, int pesop) {
		if(pesop>peso) {
			peso=pesop;
			this.best= new ArrayList<String>(parziale);
			return;
		}
		for(String si: Graphs.neighborListOf(this.grafo, parziale.get(parziale.size()-1))) {
			if(!parziale.contains(si)) {
				parziale.add(si);
				DefaultWeightedEdge e= this.grafo.getEdge(parziale.get(parziale.size()-2), si);
				if(e!=null) {
				pesop+=this.grafo.getEdgeWeight(e);
				cerca(parziale, peso, pesop);
				pesop-=this.grafo.getEdgeWeight(e);
				}
			}
		}
		
		
	
	}*/
	public String trovaPercorso(String partenza) {
		String stampa = "";
		pesoMax = 0;
		List<String> parziale = new ArrayList<>();
		best = new ArrayList<>();
		parziale.add(partenza);
		cerca(parziale);
		
		for(String nextString : best) {
			stampa += nextString + "\n";
		}
		
		stampa += "\nPeso massimo: " + pesoMax;
		return stampa;
	}

	private void cerca(List<String> parziale) {
		if(pesoMax < calcolaPeso(parziale)) {
			best = new ArrayList<>(parziale);
			pesoMax = calcolaPeso(parziale);
		}
		
		for(String next : Graphs.neighborListOf(grafo, parziale.get(parziale.size()-1))) {
			if(!parziale.contains(next)) {
				parziale.add(next);
				cerca(parziale);
				parziale.remove(next);
			}
		}
	}

	private int calcolaPeso(List<String> parziale) {
		int peso = 0;
		for(int i = 1; i < parziale.size(); i++) {
			peso += grafo.getEdgeWeight(grafo.getEdge(parziale.get(i-1), parziale.get(i)));
		}
		return peso;
	}
	 

}
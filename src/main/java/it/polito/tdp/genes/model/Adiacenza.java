package it.polito.tdp.genes.model;

public class Adiacenza implements Comparable <Adiacenza>{
	
	private String l1;
	private String l2;
	private Integer peso;
	public Adiacenza(String l1, String l2, Integer peso) {
		super();
		this.l1 = l1;
		this.l2 = l2;
		this.peso = peso;
	}
	public String getL1() {
		return l1;
	}
	public void setL1(String l1) {
		this.l1 = l1;
	}
	public String getL2() {
		return l2;
	}
	public void setL2(String l2) {
		this.l2 = l2;
	}
	public Integer getPeso() {
		return peso;
	}
	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(Adiacenza o) {
		// TODO Auto-generated method stub
		return this.peso.compareTo(o.getPeso());
	}
	@Override
	public String toString() {
		return l1+"-" + l2 + ":" + peso;
	}
	
	
	
	

}

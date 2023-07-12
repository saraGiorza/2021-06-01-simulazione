package it.polito.tdp.genes.model;

public class Arco implements Comparable<Arco>{

	private Genes g1;
	private Genes g2;
	private float peso;
	public Arco(Genes g1, Genes g2, float peso) {
		super();
		this.g1 = g1;
		this.g2 = g2;
		this.peso = peso;
	}
	public Genes getG1() {
		return g1;
	}
	public void setG1(Genes g1) {
		this.g1 = g1;
	}
	public Genes getG2() {
		return g2;
	}
	public void setG2(Genes g2) {
		this.g2 = g2;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	@Override
	public int compareTo(Arco o) {
		if(this.getPeso() > o.getPeso()) {
			return -1;
		}
		else {
			return 1;
		}
	}
	
	
}

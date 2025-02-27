package model;

public class Carro {
	private String nome;
	private String marca;
	private int ano;
	private String cor;

	// Construtor correto
	public Carro(String nome, String marca, int ano, String cor) {
		this.nome = nome;
		this.marca = marca;
		this.ano = ano;
		this.cor = cor;
	}

	// Getters
	public String getnome() {
		return nome;
	}

	public String getmarca() {
		return marca;
	}

	public int getano() {
		return ano;
	}

	public String getcor() {
		return cor;
	}

	public void setnome(String nome) {
		this.nome = nome;
	}

	public void setmarca(String marca) {
		this.marca = marca;
	}

	public void setano(int ano) {
		this.ano = ano;
	}

	public void setcor(String cor) {
		this.cor = cor;
	}

	@Override
	public String toString() {
		return "Carro [nome = " + nome + ", marca = " + marca + ", ano =" + ano + ", cor =" + cor + "]";
	}
}

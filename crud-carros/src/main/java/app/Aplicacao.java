package app;

import java.util.List;

import dao.CarroDAO;
import model.Carro;

public class Aplicacao {

	public static void main(String[] args) throws Exception {

		CarroDAO carroDAO = new CarroDAO();

		System.out.println("\n\n==== Inserir carro === ");
		Carro carro = new Carro("Civic", "Honda", 2020, "Prata");
		if (carroDAO.inserirCarro(carro)) {
			System.out.println("Inserção com sucesso -> " + carro.toString());
		}

		System.out.println("\n\n==== Mostrar carros === ");
		List<Carro> carros = carroDAO.listarCarros();
		for (Carro u : carros) {
			System.out.println(u.toString());
		}

		System.out.println("\n\n==== Atualizar carro === ");
		carroDAO.atualizarCarro(carro);

		System.out.println("\n\n==== Excluir carro (Nome " + carro.getnome() + ") === ");
		carroDAO.excluirCarro(carro.getnome());
	}
}

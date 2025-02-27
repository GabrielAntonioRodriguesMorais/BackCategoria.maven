package com.ti2cc;

import dao.CarroDAO;
import model.Carro;
import java.util.List;

public class Principal {

    public static void main(String[] args) {
        CarroDAO dao = new CarroDAO(); // Usa CarroDAO em vez de DAO

        // Inserir um carro na tabela
        Carro carro1 = new Carro("Valkyrie", "Aston Martin", 2022, "Verde");
        if (dao.inserirCarro(carro1)) {
            System.out.println("Inserção com sucesso -> " + carro1.toString());
        }

        Carro carro2 = new Carro("Chiron", "Bugatti", 2021, "Azul");
        if (dao.inserirCarro(carro2)) {
            System.out.println("Inserção com sucesso -> " + carro2.toString());
        }

        // Listar todos os carros
        System.out.println("==== Lista de Carros ====");
        List<Carro> carros = dao.listarCarros();
        for (Carro carro : carros) {
            System.out.println(carro.toString());
        }

        // Atualizar um carro
        carro2.setano(2023);
        carro2.setcor("Preto");
        if (dao.atualizarCarro(carro2)) {
            System.out.println("Atualização realizada com sucesso!");
        }

        // Listar carros após atualização
        System.out.println("==== Lista de Carros Após Atualização ====");
        carros = dao.listarCarros();
        for (Carro carro : carros) {
            System.out.println(carro.toString());
        }

        // Excluir um carro
        if (dao.excluirCarro("Valkyrie")) {
            System.out.println("Carro Valkyrie excluído com sucesso!");
        }

        // Listar carros após exclusão
        System.out.println("==== Lista de Carros Após Exclusão ====");
        carros = dao.listarCarros();
        for (Carro carro : carros) {
            System.out.println(carro.toString());
        }

        dao.close();
    }
}

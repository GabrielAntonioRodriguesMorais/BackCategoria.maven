package dao;

import java.sql.*;

public class DAO {
	protected Connection conexao;

	public DAO() {
		conexao = null;
	}

	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "exercicio2";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta + "/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao != null);
			if (status) {
				System.out.println("Conexão efetuada com o PostgreSQL!");
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Erro: Driver não encontrado! " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Erro ao conectar com o PostgreSQL: " + e.getMessage());
		}

		return status;
	}

	public boolean close() {
		boolean status = false;

		try {
			if (conexao != null) {
				conexao.close();
				status = true;
				System.out.println("Conexão fechada com sucesso.");
			}
		} catch (SQLException e) {
			System.err.println("Erro ao fechar conexão: " + e.getMessage());
		}
		return status;
	}
}

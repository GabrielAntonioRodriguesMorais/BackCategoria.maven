package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Carro;

public class CarroDAO extends DAO {

    public CarroDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    public boolean inserirCarro(Carro carro) {
        boolean status = false;
        try {
            if (getCarro(carro.getnome()) != null) {
                System.out.println("Erro: O carro já existe no banco de dados.");
                return false;
            }

            Statement st = conexao.createStatement();
            String sql = "INSERT INTO Carro (nome, marca, ano, cor) VALUES ('"
                    + carro.getnome() + "', '" + carro.getmarca() + "', "
                    + carro.getano() + ", '" + carro.getcor() + "');";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }


    public Carro getCarro(String nome) {
        Carro carro = null;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Carro WHERE nome = '" + nome + "'";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                carro = new Carro(rs.getString("nome"), rs.getString("marca"),
                        rs.getInt("ano"), rs.getString("cor"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return carro;
    }

    public List<Carro> listarCarros() {
        List<Carro> carros = new ArrayList<>();
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM Carro";
            System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Carro c = new Carro(rs.getString("nome"), rs.getString("marca"),
                        rs.getInt("ano"), rs.getString("cor"));
                carros.add(c);
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return carros;
    }

    public boolean atualizarCarro(Carro carro) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "UPDATE Carro SET marca = '" + carro.getmarca() + "', ano = "
                    + carro.getano() + ", cor = '" + carro.getcor() + "'"
                    + " WHERE nome = '" + carro.getnome() + "'";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }

    public boolean excluirCarro(String nome) {
        boolean status = false;
        try {
            Statement st = conexao.createStatement();
            String sql = "DELETE FROM Carro WHERE nome = '" + nome + "'";
            System.out.println(sql);
            st.executeUpdate(sql);
            st.close();
            status = true;
        } catch (SQLException u) {
            throw new RuntimeException(u);
        }
        return status;
    }
}

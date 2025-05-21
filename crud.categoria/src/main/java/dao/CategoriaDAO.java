package dao;

import java.sql.*;
import java.util.*;

import dao.DAO;
import model.Categoria;

public class CategoriaDAO extends DAO {

    public CategoriaDAO() {
        super();
        conectar();
    }

    public void finalize() {
        close();
    }

    private boolean empresaExiste(String cnpj) {
        boolean existe = false;
        try {
            String sql = "SELECT 1 FROM \"Empresa\" WHERE \"CNPJ\" = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, cnpj);
            ResultSet rs = st.executeQuery();
            existe = rs.next();
            st.close();
        } catch (SQLException e) {
            System.err.println("Erro ao verificar CNPJ da empresa: " + e.getMessage());
        }
        return existe;
    }

    public boolean inserir(Categoria c) {
        boolean status = false;
        if (!empresaExiste(c.getEmpresaCNPJ())) {
            System.out.println("Erro: Empresa com CNPJ " + c.getEmpresaCNPJ() + " não existe.");
            return false;
        }

        try {
            String sql = "INSERT INTO \"Categoria\" (\"nome\", \"qntd_categoria\", \"Empresa_CNPJ\") VALUES (?, ?, ?)";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, c.getNome());
            st.setInt(2, c.getQntdCategoria());
            st.setString(3, c.getEmpresaCNPJ());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir categoria: " + e.getMessage());
        }
        return status;
    }

    public Categoria get(int id) {
        Categoria c = null;
        try {
            String sql = "SELECT * FROM \"Categoria\" WHERE \"id\" = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                c = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("qntd_categoria"),
                        rs.getString("Empresa_CNPJ"));
            }
            st.close();
        } catch (SQLException e) {
            System.err.println("Erro ao buscar categoria: " + e.getMessage());
        }
        return c;
    }

    public List<Categoria> getAll() {
        List<Categoria> categorias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM \"Categoria\" ORDER BY \"id\"";
            PreparedStatement st = conexao.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("qntd_categoria"),
                        rs.getString("Empresa_CNPJ"));
                categorias.add(c);
            }
            st.close();
        } catch (SQLException e) {
            System.err.println("Erro ao listar categorias: " + e.getMessage());
        }
        return categorias;
    }

    public boolean atualizar(Categoria c) {
        boolean status = false;
        try {
            String sql = "UPDATE \"Categoria\" SET \"nome\" = ?, \"qntd_categoria\" = ?, \"Empresa_CNPJ\" = ? WHERE \"id\" = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setString(1, c.getNome());
            st.setInt(2, c.getQntdCategoria());
            st.setString(3, c.getEmpresaCNPJ());
            st.setInt(4, c.getId());
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar categoria: " + e.getMessage());
        }
        return status;
    }

    public boolean excluir(int id) {
        boolean status = false;
        try {
            String sql = "DELETE FROM \"Categoria\" WHERE \"id\" = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
            status = true;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir categoria: " + e.getMessage());
        }
        return status;
    }

    public int contarProdutosPorCategoria(int idCategoria) {
        int quantidade = 0;
        try {
            String sql = "SELECT COUNT(*) FROM \"Produto\" WHERE \"id_categoria\" = ?";
            PreparedStatement st = conexao.prepareStatement(sql);
            st.setInt(1, idCategoria);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                quantidade = rs.getInt(1);
            }
            st.close();
        } catch (SQLException e) {
            System.err.println("Erro ao contar produtos: " + e.getMessage());
        }
        return quantidade;
    }
}
package com.ti2cc;

import static spark.Spark.*;
import com.google.gson.*;
import dao.CategoriaDAO;
import model.Categoria;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) {
        port(4567);
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Gson gson = new Gson();

        get("/categorias", (req, res) -> {
            res.type("application/json");
            return gson.toJson(categoriaDAO.getAll());
        });

        get("/categorias/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id"));
            Categoria categoria = categoriaDAO.get(id);
            return categoria != null ? gson.toJson(categoria) : "{\"erro\":\"Categoria não encontrada\"}";
        });

        post("/categorias", (req, res) -> {
            res.type("application/json");
            Categoria c = gson.fromJson(req.body(), Categoria.class);
            boolean ok = categoriaDAO.inserir(c);
            return ok ? gson.toJson(c) : "{\"erro\":\"Erro ao inserir\"}";
        });

        put("/categorias/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id"));
            Categoria c = gson.fromJson(req.body(), Categoria.class);
            c.setId(id);
            boolean ok = categoriaDAO.atualizar(c);
            return ok ? gson.toJson(c) : "{\"erro\":\"Erro ao atualizar\"}";
        });

        delete("/categorias/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id"));
            boolean ok = categoriaDAO.excluir(id);
            return ok ? "{\"mensagem\":\"Categoria excluída\"}" : "{\"erro\":\"Erro ao excluir\"}";
        });
    }
}

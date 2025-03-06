package com.fatec.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatec.controller.utils.ServletUtil;
import com.fatec.model.entidades.Endereco;
import com.fatec.service.EnderecoService;

public class EnderecoController extends HttpServlet {

    private static EnderecoService enderecoService = new EnderecoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {

            String parametroIdCliente = req.getParameter("idcliente");

            if (parametroIdCliente != null){
                int id = Integer.parseInt(parametroIdCliente);

                ServletUtil.retornarRespostaJson(
                    resp,
                    enderecoService.consultarByIDCliente(id),
                    HttpServletResponse.SC_OK
                );
                return;
            }

            throw new Exception("Necessário id do cliente para consultar endereço!");

        } catch (Exception e) {
            ServletUtil.estourarErro(resp, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Endereco enderecoToAdd = ServletUtil.jsonToObject(req, Endereco.class);
            ServletUtil.retornarRespostaJson(
                resp,
                enderecoService.inserir(enderecoToAdd),
                HttpServletResponse.SC_CREATED
            );
        } catch (Exception e) {
            ServletUtil.estourarErro(resp, e);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexoes.ConexaoMySql;
import java.util.ArrayList;
import model.ModelCliente;
import model.ModelVendas;
import model.ModelVendasCliente;

/**
 *
 * @author Administrador
 */
public class DAOVendasCliente extends ConexaoMySql {
    
    public ArrayList<ModelVendasCliente> getListaVendasClienteDAO(){
        //instanciar os conteúdo, chamar os conteúdo
        ArrayList<ModelVendasCliente> listaModelVendasClientes = new ArrayList<>();
        ModelVendas modelVendas = new ModelVendas();
        ModelCliente modelCliente = new ModelCliente();
        ModelVendasCliente modelVendasCliente = new ModelVendasCliente();
        try {
            this.conectar();
            this.executarSQL(
                "SELECT "
                    + "tbl_vendas.pk_id_vendas,"
                    + "tbl_vendas.fk_cliente,"
                    + "tbl_vendas.ven_data_venda,"
                    + "tbl_vendas.ven_valor_liquido,"
                    + "tbl_vendas.ven_valor_bruto,"
                    + "tbl_vendas.ven_desconto,"
                    + "tbl_cliente.pk_id_cliente,"
                    + "tbl_cliente.cli_nome,"
                    + "tbl_cliente.cli_endereco,"
                    + "tbl_cliente.cli_bairro,"
                    + "tbl_cliente.cli_cidade,"
                    + "tbl_cliente.cli_uf,"
                    + "tbl_cliente.cli_cep,"
                    + "tbl_cliente.cli_telefone"
                 + " FROM "
                     + " tbl_vendas INNER JOIN tbl_cliente "
                        + "ON  tbl_cliente.pk_id_cliente = tbl_vendas.fk_cliente; "//O inner join está comparando os valores onde identificado (tbl_vendas.fk_cliente) 
                + ";"                                                            // é igual ao valor da chave estrangeira (tbl_vendas.fk_cliente).
            );

            while(this.getResultSet().next()){
                //Venda
                modelVendas = new ModelVendas(); //Ele recebe uma nova venda
                modelCliente = new ModelCliente(); //Ele recebe um novo cliente
                modelVendasCliente = new ModelVendasCliente();  //Ele recebe uma nova venda e cliente
                //setar os valores da sql declara logo acima
                modelVendas.setIdVenda(this.getResultSet().getInt(1));
                modelVendas.setCliente(this.getResultSet().getInt(2));
                modelVendas.setVenDataVenda(this.getResultSet().getDate(3));
                modelVendas.setVenValorLiquido(this.getResultSet().getDouble(4));
                modelVendas.setVenValorBruto(this.getResultSet().getDouble(5));
                modelVendas.setVenDesconto(this.getResultSet().getDouble(6));
                //Cliente
                //setar os valores da sql declara logo acima
                modelCliente.setIdCliente(this.getResultSet().getInt(7));
                modelCliente.setCliNome(this.getResultSet().getString(8));
                modelCliente.setCliEndereco(this.getResultSet().getString(9));
                modelCliente.setCliBairro(this.getResultSet().getString(10));
                modelCliente.setCliCidade(this.getResultSet().getString(11));
                modelCliente.setCliUf(this.getResultSet().getString(12));
                modelCliente.setCliCep(this.getResultSet().getString(13));
                modelCliente.setCliTelefone(this.getResultSet().getString(14));
                //Setar cliente e venda, modelVendasCliente está recebendo valore de modelVendas e modelCliente
                modelVendasCliente.setModelVendas(modelVendas);
                modelVendasCliente.setModelCliente(modelCliente);
               
               listaModelVendasClientes.add(modelVendasCliente);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.fecharConexao();
        }
        return listaModelVendasClientes;
    }
}

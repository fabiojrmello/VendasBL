/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexoes.ConexaoMySql;
import java.util.ArrayList;
import model.ModelProdutos;

/**
 *
 * @author Administrador
 */
public class DaoProdutos extends ConexaoMySql {

    //Inicio do metodo SALVAR
    /**
     * Cadastrar um produto no banco
     *
     * @param pModelProdutos
     * @return
     */
    public int salvarProdutosDAO(ModelProdutos pModelProdutos) {
        try {
            this.conectar(); // Abrir a conexao com o banco de dados
            return this.insertSQL("INSERT INTO tbl_produto (" //Inserir dados no banco de dados
                    + "pro_nome,"
                    + "pro_valor,"
                    + "pro_estoque"
                    + ") VALUES ("
                    + "'" + pModelProdutos.getProNome() + "',"
                    + "'" + pModelProdutos.getProValor() + "',"
                    + "'" + pModelProdutos.getProEstoque() + "'"
                    + ");"
            );
        } catch (Exception e) {  // Ele verifica se o usuario colocou dados, se ele digitar 0 vai dar erro, se ele digitar um numero maior que zero, vai conseguir acessar.
            e.printStackTrace(); // Ele vai imprimir no console
            return 0;
        } finally { //Fechar a conexao com o banco de dados.
            this.fecharConexao();
        }
    }
    //Fim do metodo SALVAR

    //Inicio do metodo EXCLUIR 
    /**
     * Excluir um produto do banco
     *
     * @param pIdProduto
     * @return boolean
     */
    public boolean excluirProdutoDAO(int pIdProduto) {
        try {
            this.conectar(); // Abrir a conexao com o banco de dados
            return this.executarUpdateDeleteSQL(
                    "DELETE FROM tbl_produto WHERE pk_id_produto = '" + pIdProduto + "'"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
    //Fim do metodo EXCLUIR

    /**
     * Alterar dados do produtos
     *
     * @param pModelProdutos
     * @return boolean
     */
    //Inicio do metodo ALTERAR
    public boolean alterarProdutoDAO(ModelProdutos pModelProdutos) {
        try {
            this.conectar();
            return this.executarUpdateDeleteSQL(
                    "UPDATE tbl_produto SET "
                    + "pro_nome = '" + pModelProdutos.getProNome() + "',"
                    + "pro_valor = '" + pModelProdutos.getProValor() + "',"
                    + "pro_estoque = '" + pModelProdutos.getProEstoque() + "'"
                    + " WHERE pk_id_produto = '" + pModelProdutos.getIdProduto() + "'" //IdProduto é paramentro a onde eu estou dizendo que vai ser alterado os dados.
            );
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
    //Fim do metodo ALTERAR

    /**
     * retorna um produto pelo o codigo
     *
     * @param pIdProduto
     * @return modelProdutos
     */
    //Inicio do retorna um registro
    public ModelProdutos retornarProdutoDAO(int pIdProduto) {
        ModelProdutos modelProdutos = new ModelProdutos();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "pk_id_produto, "
                    + "pro_nome, "
                    + "pro_valor, "
                    + "pro_estoque "
                    + "FROM tbl_produto WHERE pk_id_produto = '" + pIdProduto + "';");
            while (this.getResultSet().next()) {
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));//esse 1 está referenciando ao pk_id_produto e segui assim com os demais
                modelProdutos.setProNome(this.getResultSet().getString(2));
                modelProdutos.setProValor(this.getResultSet().getDouble(3));
                modelProdutos.setProEstoque(this.getResultSet().getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelProdutos;
    }
    //Fim do retorna um registro

    /**
     * retorna um produto pelo o codigo
     *
     * @param pNomeProduto
     * @return modelProdutos
     */
    //Inicio do retorna um registro
    public ModelProdutos retornarProdutoDAO(String pNomeProduto) {
        ModelProdutos modelProdutos = new ModelProdutos();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "pk_id_produto, "
                    + "pro_nome, "
                    + "pro_valor, "
                    + "pro_estoque "
                    + "FROM tbl_produto WHERE pro_nome = '" + pNomeProduto + "';");
            while (this.getResultSet().next()) {
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));//esse 1 está referenciando ao pk_id_produto e segui assim com os demais
                modelProdutos.setProNome(this.getResultSet().getString(2));
                modelProdutos.setProValor(this.getResultSet().getDouble(3));
                modelProdutos.setProEstoque(this.getResultSet().getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return modelProdutos;
    }
    //Fim do retorna um registro

    //Incio do retorna uma lista de produto
    /**
     * Retornar uma lista completa de produtos
     *
     * @return listaModelProdutos
     */
    public ArrayList<ModelProdutos> retornarListaProdutosDAO() {
        ArrayList<ModelProdutos> listaModelProdutos = new ArrayList<>();
        ModelProdutos modelProdutos = new ModelProdutos();
        try {
            this.conectar();
            this.executarSQL("SELECT "
                    + "pk_id_produto, "
                    + "pro_nome, "
                    + "pro_valor, "
                    + "pro_estoque "
                    + "FROM tbl_produto;");

            while (this.getResultSet().next()) {
                modelProdutos = new ModelProdutos();
                modelProdutos.setIdProduto(this.getResultSet().getInt(1));//esse 1 está referenciando ao pk_id_produto e segui assim com os demais
                modelProdutos.setProNome(this.getResultSet().getString(2));
                modelProdutos.setProValor(this.getResultSet().getDouble(3));
                modelProdutos.setProEstoque(this.getResultSet().getInt(4));
                listaModelProdutos.add(modelProdutos);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.fecharConexao();
        }
        return listaModelProdutos;
    }
    //Fim do retorna uma lista de produto

    /**
     * Alterar estoque de produtos no banco
     * @param pListaModelProdutos
     * @return 
     */
    public boolean alterarEstoqueProdutosDAO(ArrayList<ModelProdutos> pListaModelProdutos) {
        try {
            this.conectar();
            for (int i = 0; i < pListaModelProdutos.size(); i++) {
                this.executarUpdateDeleteSQL(
                        "UPDATE tbl_produto SET "      
                        + "pro_estoque = '" + pListaModelProdutos.get(i).getProEstoque() + "'"
                        + " WHERE pk_id_produto = '" + pListaModelProdutos.get(i).getIdProduto() + "'" //IdProduto é paramentro a onde eu estou dizendo que vai ser alterado os dados.
                );
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            this.fecharConexao();
        }
    }
}

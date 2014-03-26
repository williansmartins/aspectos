package br.com.fiap.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.fiap.dao.ProdutoDAOJDBC;
import br.com.fiap.vo.ProdutoVO;

public class CRUDProdutos
{
    ProdutoDAOJDBC dao = new ProdutoDAOJDBC();
    List<ProdutoVO> lista = new ArrayList<ProdutoVO>();
    
    @Test
    public void inserirProduto( )
    {
	ProdutoVO produto = new ProdutoVO();
	produto.setNome( "Opala" );
	produto.setDescricao( "6cc" );
	produto.setPreco( 15000.00 );
	produto.setQuantidade( 1 );
	produto.setIdCliente( Long.valueOf( 1 ) );
	produto.setData( Calendar.getInstance() );

	List<ProdutoVO> lista = new ArrayList<ProdutoVO>();
	lista.add( produto );

	dao.salvar( produto );

    }
    
    @Test
    public void buscarProdutos( )
    {
	for ( ProdutoVO o : dao.listarPorNome( "Opala" ) )
	{
	    System.out.println(o);
	    lista.add( o );
	}
	Assert.assertEquals( lista.size() > 0, true );
	lista.clear();
    }
    
    @Test
    public void excluirProduto( )
    {
	for ( ProdutoVO o : dao.listarPorNome( "Opala" ) )
	{
	    dao.remover( o );
	}
	Assert.assertEquals( dao.listarPorNome( "Opala" ).size() == 0, true );
	
    }
    
    
    @Test
    public void atualizarProdutoExistente( )
    {
	inserirProduto();
	lista.clear();

	for ( ProdutoVO o : dao.listarPorNome( "Opala" ) )
	{
	    lista.add( o );
	}
	
	ProdutoVO produtoVO = lista.get(0);
	produtoVO.setNome( "Fit" );
	dao.salvar( produtoVO );
	
	Assert.assertEquals( dao.listarPorNome( "Fit" ).size() > 0, true );
	lista.clear();
    }
}
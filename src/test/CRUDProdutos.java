package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import br.com.fiap.dao.ProdutoDAOJDBC;
import br.com.fiap.vo.ProdutoVO;

public class CRUDProdutos
{
    ProdutoDAOJDBC produtoDAO = new ProdutoDAOJDBC();
    
    @Test
    public void cadastrarProduto( )
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

	produtoDAO.salvar( produto );

    }
    
    @Test
    public void buscarProduto( )
    {
	for ( ProdutoVO o : produtoDAO.listarPorNome( "Honda" ) )
	{
	    System.out.println(o);
	}
    }
}
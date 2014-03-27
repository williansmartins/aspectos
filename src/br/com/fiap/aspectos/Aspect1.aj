package br.com.fiap.aspectos;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import br.com.fiap.dao.CompraDAOJDBC;
import br.com.fiap.dao.ProdutoDAOJDBC;
import br.com.fiap.model.Compra;
import br.com.fiap.model.Item;
import br.com.fiap.util.ValidaData;

public aspect
Aspect1
{
    before( Compra compra ) : call(* br.com.fiap.gui.tela1.finalizarCompra(Compra)) && args(compra){
	ProdutoDAOJDBC dao = new ProdutoDAOJDBC();
	CompraDAOJDBC daoCompra = new CompraDAOJDBC();

	double valorDaCompra = compra.getValor() == null ? 0 : compra.getValor().doubleValue();

	//
	if ( valorDaCompra > 0 & valorDaCompra <= 500 )
	{
	    System.out.println( "Compra com valor maior que 0 e menor de 500." );
	}

	//
	for ( Item item : compra.getItens() )
	{
	    double valorDoItem = item.getProduto().getPreco() == null ? 0 : item.getProduto().getPreco().doubleValue();
	    if ( valorDoItem < 1000 && dao.listarPorNome( item.getProduto().getNome() ).size() > 1 )
	    {
		System.out.println( "desconto de R$ 75,00 aplicado ao produto: " + item.getProduto() );
	    }

	}

	// Aplicar 10% de desconto nas compras
	List<Compra> listaDeCompra = daoCompra.buscarPorCliente( compra.getCliente().getCodigo() );

	for ( Iterator iterator = listaDeCompra.iterator(); iterator.hasNext(); )
	{
	    Compra compra2 = (Compra) iterator.next();

	    if ( new ValidaData().isWithinRange( compra2.getData() ) )
	    {
		System.out.println( "Aplicando desconto de 10% nesta compra" );
	    }else{
		System.out.println( "Não será aplicado desconto de 10% nesta compra" );
	    }

	}

    }

}
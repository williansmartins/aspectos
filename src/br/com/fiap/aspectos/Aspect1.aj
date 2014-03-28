package br.com.fiap.aspectos;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import br.com.fiap.dao.CompraDAOJDBC;
import br.com.fiap.dao.ProdutoDAOJDBC;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Compra;
import br.com.fiap.model.Item;
import br.com.fiap.util.ClienteSimuladorBancoDados;
import br.com.fiap.util.ValidaData;

public aspect
Aspect1
{
    before( Compra compra ) : call(* br.com.fiap.gui.tela1.finalizarCompra(Compra)) && args(compra){
	ProdutoDAOJDBC dao = new ProdutoDAOJDBC();
	CompraDAOJDBC daoCompra = new CompraDAOJDBC();
	List<Compra> listaDeCompra = daoCompra.buscarPorCliente( compra.getCliente().getCodigo() );
	double valorDaCompra = compra.getValor() == null ? 0 : compra.getValor().doubleValue();

	// 1. Imprimir apenas as Compras maior que zero e menor que R$ 500,00
	if ( valorDaCompra > 0 & valorDaCompra <= 500 )
	{
	    System.out.println( "Compra com valor maior que 0 e menor de 500." );
	}

	// 2. menos que R$ 1.000,00 pelo menos duas unidades desconto de R$
	// 75,00
	for ( Item item : compra.getItens() )
	{
	    double valorDoItem = item.getProduto().getPreco() == null ? 0 : item.getProduto().getPreco().doubleValue();
	    if ( valorDoItem < 1000 && dao.listarPorNome( item.getProduto().getNome() ).size() > 1 )
	    {
		System.out.println( "desconto de R$ 75,00 aplicado ao produto: " + item.getProduto() );
	    }

	}

	// 3. Para os Clientes que possuem pelo menos uma compra realizada em
	// Fevereiro de 2013, nas próximas compras a partir de hoje aplique um
	// desconto de 10% em suas novas compras
	for ( Iterator iterator = listaDeCompra.iterator(); iterator.hasNext(); )
	{
	    Compra c = (Compra) iterator.next();

	    if ( new ValidaData().isWithinRange( c.getData() ) )
	    {
		System.out.println( "Aplicando desconto de 10% nesta compra" );
	    } else
	    {
		System.out.println( "Não será aplicado desconto de 10% nesta compra" );
	    }

	}

	// 4. Excluir todos os Clientes que realizaram apenas uma compra nos
	// últimos 5 anos
	// Pegar todas as compras
	List<Compra> todasCompras = daoCompra.listarTudo();

	// pegar o distinct dos clientes
	Set clientesDistinct = new HashSet<Cliente>();
	for ( Compra c : todasCompras )
	{
	    clientesDistinct.add( c.getCliente() );
	}

	// iterar sobre clientes, ver se a ultima compra dele faz mais de 5 anos
	for ( Iterator iterator = clientesDistinct.iterator(); iterator.hasNext(); )
	{
	    Cliente object = (Cliente) iterator.next();
	    List<Compra> comprasDoClienteAtual = daoCompra.buscarPorCliente( object.getCodigo() );
	    
	    Compra ultimaCompra = comprasDoClienteAtual.get( comprasDoClienteAtual.size() - 1 );
	    
	    if ( !( new ValidaData().comprouEm5Anos( ultimaCompra.getData() ) ) )
	    {
		// excluir Cliente
		ClienteSimuladorBancoDados.removerCliente( Long.parseLong( object.getCodigo().toString() ) );
		System.out.println( "Cliente excluído por não comprar a 5 anos." );
	    }
	}

    }

}
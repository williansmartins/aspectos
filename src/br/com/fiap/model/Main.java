package br.com.fiap.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main( String[] args )
    {
	List<Compra> lista = new ArrayList();
	Compra compra1 = new Compra();
	compra1.setAtendente( "Maria das Dores" );
	Cliente cliente1 = new Cliente();
	compra1.setCliente( cliente1 );
	compra1.setValor( new BigDecimal( 500 ) );
	lista.add( compra1 );
	lista.add( compra1 );
	lista.add( compra1 );
	lista.add( compra1 );

	// topico1
	new Main().imprimeComprasComValorMaiorQueZeroeMenoQue500( lista );
    }

    public void imprimeComprasComValorMaiorQueZeroeMenoQue500( List<Compra> lista )
    {
	for ( Compra compra : lista )
	{
	    double valor = compra.getValor() == null ? 0 : compra.getValor().doubleValue();

	    if ( valor > 0 & valor <= 500 )
	    {
		System.out.println( "Compra com valor maior que 0 e menor de 500: " + compra );
	    }
	}
    }

    public void aplicarDescontoDe75ParaProdutosMenorQue1000ComMaisDeUmaUnidade( List<Produto> lista )
    {
	for ( Produto produto : lista )
	{
	    if ( produto.getPreco().doubleValue() < 1000 && produto.getQuantidadeEmEstoque() > 1 )
	    {
		System.out.println( "desconto aplicado ao produto: " + produto );
	    }
	}
    }
}
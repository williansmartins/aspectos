package br.com.fiap.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import br.com.fiap.vo.ProdutoVO;

public class Item extends EntityDefault
{
    private static final long serialVersionUID = 1L;
    private BigInteger quantidade;
    private BigDecimal valor;
    private String situacao;
    private Reserva reserva;
    private Compra compra;
    private ProdutoVO produto;

    public BigInteger getQuantidade( )
    {
	return quantidade;
    }

    public void setQuantidade( BigInteger quantidade )
    {
	this.quantidade = quantidade;
    }

    public BigDecimal getValor( )
    {
	return valor;
    }

    public void setValor( BigDecimal valor )
    {
	this.valor = valor;
    }

    public String getSituacao( )
    {
	return situacao;
    }

    public void setSituacao( String situacao )
    {
	this.situacao = situacao;
    }

    public Reserva getReserva( )
    {
	return reserva;
    }

    public void setReserva( Reserva reserva )
    {
	this.reserva = reserva;
    }

    public Compra getCompra( )
    {
	return compra;
    }

    public void setCompra( Compra compra )
    {
	this.compra = compra;
    }

    public ProdutoVO getProduto( )
    {
	return produto;
    }

    public void setProduto( ProdutoVO produto )
    {
	this.produto = produto;
    }

}

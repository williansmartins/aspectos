package br.com.fiap.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Compra extends EntityDefault
{
    private static final long serialVersionUID = 1L;
    private BigInteger numero;
    private Date data;
    private String atendente;
    private String responsavel;
    private String situacao;
    private BigDecimal valor;
    private Reserva reserva;
    private Cliente cliente;
    private List<Item> itens;

    public List<Item> getItens( )
    {
	return itens;
    }

    public void setItens( List<Item> itens )
    {
	this.itens = itens;
    }

    public BigInteger getNumero( )
    {
	return numero;
    }

    public void setNumero( BigInteger numero )
    {
	this.numero = numero;
    }

    public Date getData( )
    {
	return data;
    }

    public void setData( Date data )
    {
	this.data = data;
    }

    public String getAtendente( )
    {
	return atendente;
    }

    public void setAtendente( String atendente )
    {
	this.atendente = atendente;
    }

    public String getResponsavel( )
    {
	return responsavel;
    }

    public void setResponsavel( String responsavel )
    {
	this.responsavel = responsavel;
    }

    public String getSituacao( )
    {
	return situacao;
    }

    public void setSituacao( String situacao )
    {
	this.situacao = situacao;
    }

    public BigDecimal getValor( )
    {
	BigDecimal valor = new BigDecimal( 0 );
	for ( Item item : this.getItens() )
	{
	    valor = valor.add( item.getProduto().getPreco() );
	}
	return valor;
    }

    public void setValor( BigDecimal valor )
    {
	this.valor = valor;
    }

    public Reserva getReserva( )
    {
	return reserva;
    }

    public void setReserva( Reserva reserva )
    {
	this.reserva = reserva;
    }

    public Cliente getCliente( )
    {
	return cliente;
    }

    public void setCliente( Cliente cliente )
    {
	this.cliente = cliente;
    }

}

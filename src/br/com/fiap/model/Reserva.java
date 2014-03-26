package br.com.fiap.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class Reserva extends EntityDefault
{
    private static final long serialVersionUID = 1L;
    private BigInteger codigo;
    private Date data;
    private String atendente;
    private String situacao;
    private BigDecimal valor;
    private Cliente cliente;

    public BigInteger getCodigo( )
    {
	return codigo;
    }

    public void setCodigo( BigInteger codigo )
    {
	this.codigo = codigo;
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
	return valor;
    }

    public void setValor( BigDecimal valor )
    {
	this.valor = valor;
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

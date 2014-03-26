package br.com.fiap.model;

import java.math.BigInteger;
import java.util.List;

public class Cliente extends Usuario
{
    private static final long serialVersionUID = 1L;
    private BigInteger codigo;
    private String nome;
    private String endereco;
    private String telefone;
    private String situacao;
    private List<Reserva> reservas;
    private List<Compra> compras;

    public BigInteger getCodigo( )
    {
	return codigo;
    }

    public void setCodigo( BigInteger codigo )
    {
	this.codigo = codigo;
    }

    public String getNome( )
    {
	return nome;
    }

    public void setNome( String nome )
    {
	this.nome = nome;
    }

    public String getEndereco( )
    {
	return endereco;
    }

    public void setEndereco( String endereco )
    {
	this.endereco = endereco;
    }

    public String getTelefone( )
    {
	return telefone;
    }

    public void setTelefone( String telefone )
    {
	this.telefone = telefone;
    }

    public String getSituacao( )
    {
	return situacao;
    }

    public void setSituacao( String situacao )
    {
	this.situacao = situacao;
    }

    public List<Reserva> getReservas( )
    {
	return reservas;
    }

    public void setReservas( List<Reserva> reservas )
    {
	this.reservas = reservas;
    }

    public List<Compra> getCompras( )
    {
	return compras;
    }

    public void setCompras( List<Compra> compras )
    {
	this.compras = compras;
    }
}

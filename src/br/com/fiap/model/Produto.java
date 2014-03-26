package br.com.fiap.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Produto extends EntityDefault
{
    private static final long serialVersionUID = 1L;
    private BigInteger codigo;
    private String descricao;
    private BigDecimal preco;
    private int QuantidadeEmEstoque;

    public void setQuantidadeEmEstoque( int quantidadeEmEstoque )
    {
        QuantidadeEmEstoque = quantidadeEmEstoque;
    }

    public BigInteger getCodigo( )
    {
	return codigo;
    }

    public void setCodigo( BigInteger codigo )
    {
	this.codigo = codigo;
    }

    public String getDescricao( )
    {
	return descricao;
    }

    public void setDescricao( String descricao )
    {
	this.descricao = descricao;
    }

    public BigDecimal getPreco( )
    {
	return preco;
    }
    
        public void setPreco( BigDecimal preco )
    {
	this.preco = preco;
    }

    public int getQuantidadeEmEstoque( )
    {
	return this.getQuantidadeEmEstoque();
    }

}

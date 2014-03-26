package br.com.fiap.vo;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ProdutoVO
{

    private Long id;
    private String nome;
    private String descricao;
    private Integer quantidade;
    private Double preco;
    private Calendar data;
    private String dataFormatada;
    private Long idCliente;
    private String nomeCliente;
    private Boolean isDataErro;

    private static final NumberFormat numberFmt = NumberFormat.getNumberInstance( new Locale( "pt", "BR" ) );
    private static final SimpleDateFormat format = new SimpleDateFormat( "dd/MM/yyyy", new Locale( "pt", "BR" ) );

    public ProdutoVO()
    {}

    public ProdutoVO( Long id, String nome, String descricao, Integer quantidade, Double preco, Calendar data, Long idCliente,
	    String nomeCliente )
    {
	this.id = id;
	this.nome = nome;
	this.descricao = descricao;
	this.quantidade = quantidade;
	this.preco = preco;
	this.data = data;
	this.idCliente = idCliente;
	this.nomeCliente = nomeCliente;
    }

    public Long getId( )
    {
	return id;
    }

    public void setId( Long id )
    {
	this.id = id;
    }

    public String getNome( )
    {
	return nome;
    }

    public void setNome( String nome )
    {
	this.nome = nome;
    }

    public String getDescricao( )
    {
	return descricao;
    }

    public void setDescricao( String descricao )
    {
	this.descricao = descricao;
    }

    public Integer getQuantidade( )
    {
	return quantidade;
    }

    public void setQuantidade( Integer quantidade )
    {
	this.quantidade = quantidade;
    }

    public Double getPreco( )
    {
	return preco;
    }

    public void setPreco( Double preco )
    {
	this.preco = preco;
    }

    public Long getIdCliente( )
    {
	return idCliente;
    }

    public void setIdCliente( Long idCliente )
    {
	this.idCliente = idCliente;
    }

    public String getNomeCliente( )
    {
	return nomeCliente;
    }

    public Calendar getData( )
    {
	return data;
    }

    public void setData( Calendar data )
    {
	this.data = data;
    }

    public void setNomeCliente( String nomeCliente )
    {
	this.nomeCliente = nomeCliente;
    }

    public static String convertPrecoToString( double preco )
    {
	return numberFmt.format( preco );
    }

    public static double formatStringToPreco( String strPreco ) throws ParseException
    {
	return numberFmt.parse( strPreco ).doubleValue();
    }

    public String getDataFormatada( )
    {
	if ( dataFormatada != null )
	{
	    dataFormatada = format.format( data.getTime() );
	}
	return dataFormatada;
    }

    public Boolean getIsDataErro( )
    {
	return isDataErro;
    }

    public void setIsDataErro( Boolean isDataErro )
    {
	this.isDataErro = isDataErro;
    }

    public String toString( )
    {
	return "[ " + this.nome + " - " + this.descricao + " - " + this.quantidade + " - " + this.preco + " - "
		+ this.dataFormatada + " - " + this.nomeCliente + "]";
    }
}
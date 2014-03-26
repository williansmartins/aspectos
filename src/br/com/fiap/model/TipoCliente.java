package br.com.fiap.model;

public enum TipoCliente
{
    CLIENTE_FISICO(1, "Fisico"), CLIENTE_JURIDICO(2, "Juridico");
    private int codigo;
    private String descricao;

    TipoCliente( int codigo, String descricao )
    {
	this.codigo = codigo;
	this.descricao = descricao;
    }

    public int getCodigo( )
    {
	return codigo;
    }

    public void setCodigo( int codigo )
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

}

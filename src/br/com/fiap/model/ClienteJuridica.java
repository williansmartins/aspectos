package br.com.fiap.model;

public class ClienteJuridica extends Cliente
{
    private static final long serialVersionUID = 1L;
    private String cnpj;
    private String endereco;
    private String ie;

    public String getCnpj( )
    {
	return cnpj;
    }

    public void setCnpj( String cnpj )
    {
	this.cnpj = cnpj;
    }

    public String getEndereco( )
    {
	return endereco;
    }

    public void setEndereco( String endereco )
    {
	this.endereco = endereco;
    }

    public String getIe( )
    {
	return ie;
    }

    public void setIe( String ie )
    {
	this.ie = ie;
    }

}

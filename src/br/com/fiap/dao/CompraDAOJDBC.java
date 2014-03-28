package br.com.fiap.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.fiap.exception.PersistenceException;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Compra;
import br.com.fiap.util.ClienteSimuladorBancoDados;
import br.com.fiap.vo.ProdutoVO;

public class CompraDAOJDBC implements ICompraDAO
{

    // private final static String INSERT_COMPRA =
    // "INSERT INTO COMPRA (NOME, DESCRICAO, PRECO, QUANTIDADE, DATA, ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?)";
    // private final static String UPDATE_COMPRA =
    // "UPDATE COMPRA SET NOME = ?, DESCRICAO = ?, PRECO = ?, QUANTIDADE = ?, DATA = ?, ID_CLIENTE = ? WHERE id = ?";
    // private final static String DELETE_COMPRA =
    // "DELETE FROM COMPRA WHERE ID = ?";

    // private final static String GET_COMPRAS_BY_NOME =
    // "SELECT * FROM COMPRA WHERE NOME like ?";
    private final static String GET_ALL_COMPRAS = "SELECT * FROM COMPRAS";
    private final static String GET_COMPRA_BY_CLIENTE = "SELECT * FROM COMPRAS WHERE CLIENTE_ID = ?";

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    Compra objeto = null;

    public CompraDAOJDBC()
    {

    }

    @Override
    public List<Compra> buscarPorCliente( BigInteger bigInteger ) throws PersistenceException
    {
	List<Compra> lista = new ArrayList<Compra>();
	if ( bigInteger == null || bigInteger.intValue() <= 0 )
	{
	    throw new PersistenceException( "Informe o id válido para fazer a busca." );
	}

	try
	{
	    conn = ConnectionManager.getConnection();
	    stmt = createStatementWithLog( conn, GET_COMPRA_BY_CLIENTE );
	    stmt.setInt( 1, Integer.parseInt( bigInteger + "" ) );

	    rs = stmt.executeQuery();

	    if ( rs.next() )
	    {
		int cliente_id = rs.getInt( "cliente_id" );
		Date data = rs.getDate( "data" );
		Cliente c = new Cliente();
		c.setCodigo( new BigInteger( cliente_id + "" ) );
		
		objeto = new Compra();
		objeto.setCliente( c );
		objeto.setData( data );
		
		lista.add( objeto );
	    }
	    return lista;
	} catch ( SQLException e )
	{
	    String errorMsg = "Erro ao buscar o objeto.";
	    System.out.println( errorMsg + e.getMessage() );
	    throw new PersistenceException( errorMsg, e );
	} finally
	{
	    ConnectionManager.closeAll( conn, stmt, rs );
	}
    }

    private static PreparedStatement createStatementWithLog( Connection conn, String sql ) throws SQLException
    {
	if ( conn == null )
	{
	    return null;
	}
	return conn.prepareStatement( sql );
    }

    @Override
    public void salvar( Compra objeto )
    {
	// TODO Auto-generated method stub
    }

    @Override
    public void remover( Compra objeto )
    {
	// TODO Auto-generated method stub
    }

    @Override
    public List<Compra> listarTudo( )
    {
	List<Compra> lista = new ArrayList<Compra>();

	try
	{
	    conn = ConnectionManager.getConnection();
	    stmt = createStatementWithLog( conn, GET_ALL_COMPRAS );

	    rs = stmt.executeQuery();

	    while ( rs.next() )
	    {
		int cliente_id = rs.getInt( "cliente_id" );
		Date data = rs.getDate( "data" );
		Cliente c = new Cliente();
		c.setCodigo( new BigInteger( cliente_id + "" ) );
		
		objeto = new Compra();
		objeto.setCliente( c );
		objeto.setData( data );
		
		lista.add( objeto );
	    }
	    return lista;
	} catch ( SQLException e )
	{
	    String errorMsg = "Erro ao buscar o objeto.";
	    System.out.println( errorMsg + e.getMessage() );
	    throw new PersistenceException( errorMsg, e );
	} finally
	{
	    ConnectionManager.closeAll( conn, stmt, rs );
	}
    }
}
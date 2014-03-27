package br.com.fiap.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.exception.PersistenceException;
import br.com.fiap.model.Cliente;
import br.com.fiap.model.Compra;

public class CompraDAOJDBC implements ICompraDAO
{

    // private final static String INSERT_COMPRA =
    // "INSERT INTO COMPRA (NOME, DESCRICAO, PRECO, QUANTIDADE, DATA, ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?)";
    // private final static String UPDATE_COMPRA =
    // "UPDATE COMPRA SET NOME = ?, DESCRICAO = ?, PRECO = ?, QUANTIDADE = ?, DATA = ?, ID_CLIENTE = ? WHERE id = ?";
    // private final static String DELETE_COMPRA =
    // "DELETE FROM COMPRA WHERE ID = ?";

    // private final static String GET_ALL_COMPRAS = "SELECT * FROM COMPRA";
    // private final static String GET_COMPRAS_BY_NOME =
    // "SELECT * FROM COMPRA WHERE NOME like ?";
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

    // @Override
    // public void salvar( Compra objeto ) throws PersistenceException
    // {
    //
    // if ( objeto == null )
    // {
    // throw new PersistenceException(
    // "Tentativa de inserção de objeto inexistente." );
    // }
    // try
    // {
    // conn = ConnectionManager.getConnection();
    // if ( objeto.getNumero() == null )
    // {
    // stmt = getStatementInsert( conn, objeto );
    // } else
    // {
    // stmt = getStatementUpdate( conn, objeto );
    // }
    // stmt.executeUpdate();
    // conn.commit();
    // System.out.println( "objeto salvo com sucesso." );
    // } catch ( SQLException e )
    // {
    // try
    // {
    // conn.rollback();
    // } catch ( Exception sx )
    // {
    // String errorMsg = "Erro ao salvar o objeto.";
    // System.out.println( errorMsg + e.getMessage() );
    // throw new PersistenceException( errorMsg, e );
    // }
    // } finally
    // {
    // ConnectionManager.closeAll( conn, stmt );
    // }
    // }

    // private PreparedStatement getStatementInsert( Connection conn, Compra
    // objeto ) throws SQLException
    // {
    // stmt = createStatementWithLog( conn, INSERT_COMPRA );
    // stmt.setString( 1, objeto.getNome() );
    // stmt.setString( 2, objeto.getDescricao() );
    // stmt.setDouble( 3, objeto.getPreco() );
    // stmt.setInt( 4, objeto.getQuantidade() );
    // stmt.setDate( 5, new Date( objeto.getData().getTimeInMillis() ) );
    // stmt.setLong( 6, objeto.getIdCliente() );
    // return stmt;
    // }

    // private PreparedStatement getStatementUpdate( Connection conn, Compra m )
    // throws SQLException
    // {
    // stmt = createStatementWithLog( conn, UPDATE_COMPRA );
    // stmt.setString( 1, m.getNome() );
    // stmt.setString( 2, m.getDescricao() );
    // stmt.setDouble( 3, m.getPreco() );
    // stmt.setInt( 4, m.getQuantidade() );
    // stmt.setDate( 5, new Date( m.getData().getTimeInMillis() ) );
    // stmt.setLong( 6, m.getIdCliente() );
    // stmt.setLong( 7, m.getId() );
    // return stmt;
    // }

    // @Override
    // public void remover( Compra m ) throws PersistenceException
    // {
    //
    // if ( m == null || m.getId() == null )
    // {
    // throw new PersistenceException( "Informe o objeto para exclusao." );
    // }
    // try
    // {
    // conn = ConnectionManager.getConnection();
    // stmt = createStatementWithLog( conn, DELETE_COMPRA );
    // stmt.setLong( 1, m.getId() );
    // stmt.executeUpdate();
    // conn.commit();
    // System.out.println( "objeto excluido com sucesso." );
    // } catch ( SQLException e )
    // {
    // try
    // {
    // conn.rollback();
    // } catch ( Exception sx )
    // {
    // String errorMsg = "Erro ao excluir o objeto.";
    // System.out.println( errorMsg + e.getMessage() );
    // throw new PersistenceException( errorMsg, e );
    // }
    // } finally
    // {
    // ConnectionManager.closeAll( conn, stmt );
    // }
    // }

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
	// TODO Auto-generated method stub
	return null;
    }
}
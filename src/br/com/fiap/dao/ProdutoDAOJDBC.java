package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import br.com.fiap.exception.PersistenceException;
import br.com.fiap.util.ClienteSimuladorBancoDados;
import br.com.fiap.vo.ProdutoVO;

public class ProdutoDAOJDBC implements IProdutoDAO
{

    private final static String INSERT_PRODUTO = "INSERT INTO PRODUTO (NOME, DESCRICAO, PRECO, QUANTIDADE, DATA, ID_CLIENTE) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_PRODUTO = "UPDATE PRODUTO SET NOME = ?, DESCRICAO = ?, PRECO = ?, QUANTIDADE = ?, DATA = ?, ID_CLIENTE = ? WHERE id = ?";
    private final static String DELETE_PRODUTO = "DELETE FROM PRODUTO WHERE ID = ?";

    private final static String GET_ALL_PRODUTOS = "SELECT * FROM PRODUTO";
    private final static String GET_PRODUTOS_BY_NOME = "SELECT * FROM PRODUTO WHERE NOME like ?";
    private final static String GET_PRODUTO_BY_ID = "SELECT * FROM PRODUTO WHERE ID = ?";

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;
    ProdutoVO m = null;

    @Override
    public void salvar( ProdutoVO produto ) throws PersistenceException
    {

	if ( produto == null )
	{
	    throw new PersistenceException( "Tentativa de inserção de produto inexistente." );
	}
	try
	{
	    conn = ConnectionManager.getConnection();
	    if ( produto.getId() == null )
	    {
		stmt = getStatementInsert( conn, produto );
	    } else
	    {
		stmt = getStatementUpdate( conn, produto );
	    }
	    stmt.executeUpdate();
	    conn.commit();
	    System.out.println( "Produto salvo com sucesso." );
	} catch ( SQLException e )
	{
	    try
	    {
		conn.rollback();
	    } catch ( Exception sx )
	    {
		String errorMsg = "Erro ao salvar o produto.";
		System.out.println( errorMsg + e.getMessage() );
		throw new PersistenceException( errorMsg, e );
	    }
	} finally
	{
	    ConnectionManager.closeAll( conn, stmt );
	}
    }

    private PreparedStatement getStatementInsert( Connection conn, ProdutoVO m ) throws SQLException
    {
	stmt = createStatementWithLog( conn, INSERT_PRODUTO );
	stmt.setString( 1, m.getNome() );
	stmt.setString( 2, m.getDescricao() );
	stmt.setDouble( 3, m.getPreco() );
	stmt.setInt( 4, m.getQuantidade() );
	stmt.setDate( 5, new Date( m.getData().getTimeInMillis() ) );
	stmt.setLong( 6, m.getIdCliente() );
	return stmt;
    }

    private PreparedStatement getStatementUpdate( Connection conn, ProdutoVO m ) throws SQLException
    {
	stmt = createStatementWithLog( conn, UPDATE_PRODUTO );
	stmt.setString( 1, m.getNome() );
	stmt.setString( 2, m.getDescricao() );
	stmt.setDouble( 3, m.getPreco() );
	stmt.setInt( 4, m.getQuantidade() );
	stmt.setDate( 5, new Date( m.getData().getTimeInMillis() ) );
	stmt.setLong( 6, m.getIdCliente() );
	stmt.setLong( 7, m.getId() );
	return stmt;
    }

    @Override
    public void remover( ProdutoVO m ) throws PersistenceException
    {

	if ( m == null || m.getId() == null )
	{
	    throw new PersistenceException( "Informe o produto para exclusao." );
	}
	try
	{
	    conn = ConnectionManager.getConnection();
	    stmt = createStatementWithLog( conn, DELETE_PRODUTO );
	    stmt.setLong( 1, m.getId() );
	    stmt.executeUpdate();
	    conn.commit();
	    System.out.println( "Produto excluido com sucesso." );
	} catch ( SQLException e )
	{
	    try
	    {
		conn.rollback();
	    } catch ( Exception sx )
	    {
		String errorMsg = "Erro ao excluir o produto.";
		System.out.println( errorMsg + e.getMessage() );
		throw new PersistenceException( errorMsg, e );
	    }
	} finally
	{
	    ConnectionManager.closeAll( conn, stmt );
	}
    }

    @Override
    public ProdutoVO buscarPorId( Long id ) throws PersistenceException
    {

	if ( id == null || id.intValue() <= 0 )
	{
	    throw new PersistenceException( "Informe o id válido para fazer a busca." );
	}

	try
	{
	    conn = ConnectionManager.getConnection();
	    stmt = createStatementWithLog( conn, GET_PRODUTO_BY_ID );
	    stmt.setLong( 1, id );
	    rs = stmt.executeQuery();

	    if ( rs.next() )
	    {
		String nome = rs.getString( "NOME" );
		String descricao = rs.getString( "DESCRICAO" );
		Integer quantidade = rs.getInt( "QUANTIDADE" );
		Double preco = rs.getDouble( "PRECO" );
		Calendar data = Calendar.getInstance();
		data.setTime( rs.getDate( "DATA" ) );
		Long idCliente = rs.getLong( "ID_CLIENTE" );
		String nomeCliente = ClienteSimuladorBancoDados.cliente.get( idCliente );
		m = new ProdutoVO( id, nome, descricao, quantidade, preco, data, idCliente, nomeCliente );
	    }
	    return m;
	} catch ( SQLException e )
	{
	    String errorMsg = "Erro ao buscar o produto.";
	    System.out.println( errorMsg + e.getMessage() );
	    throw new PersistenceException( errorMsg, e );
	} finally
	{
	    ConnectionManager.closeAll( conn, stmt, rs );
	}
    }

    @Override
    public List<ProdutoVO> listarTodos( ) throws PersistenceException
    {
	try
	{
	    conn = ConnectionManager.getConnection();
	    stmt = createStatementWithLog( conn, GET_ALL_PRODUTOS );
	    rs = stmt.executeQuery();
	    return toProdutos( rs );
	} catch ( SQLException e )
	{
	    String errorMsg = "Erro ao consultar todos os produtos.";
	    System.out.println( errorMsg + e.getMessage() );
	    throw new PersistenceException( errorMsg, e );
	} finally
	{
	    ConnectionManager.closeAll( conn, stmt, rs );
	}
    }

    @SuppressWarnings("unchecked")
    public List<ProdutoVO> listarPorNome( String nome ) throws PersistenceException
    {
	if ( nome == null || nome.isEmpty() )
	{
	    return Collections.EMPTY_LIST;
	}

	try
	{
	    conn = ConnectionManager.getConnection();
	    stmt = createStatementWithLog( conn, GET_PRODUTOS_BY_NOME );
	    stmt.setString( 1, nome + "%" );
	    rs = stmt.executeQuery();

	    return toProdutos( rs );
	} catch ( SQLException e )
	{
	    String errorMsg = "Erro ao consultar os produtos por nome.";
	    System.out.println( errorMsg + e.getMessage() );
	    throw new PersistenceException( errorMsg, e );
	} finally
	{
	    ConnectionManager.closeAll( conn, stmt, rs );
	}
    }

    private List<ProdutoVO> toProdutos( ResultSet rs ) throws SQLException
    {
	List<ProdutoVO> lista = new ArrayList<ProdutoVO>();
	while ( rs.next() )
	{
	    Long id = rs.getLong( "ID" );
	    String nome = rs.getString( "NOME" );
	    String descricao = rs.getString( "DESCRICAO" );
	    Integer quantidade = rs.getInt( "QUANTIDADE" );
	    Double preco = rs.getDouble( "PRECO" );
	    Calendar data = Calendar.getInstance();
	    data.setTime( rs.getDate( "DATA" ) );
	    Long idCliente = rs.getLong( "ID_CLIENTE" );
	    String nomeCliente = ClienteSimuladorBancoDados.cliente.get( idCliente );

	    lista.add( new ProdutoVO( id, nome, descricao, quantidade, preco, data, idCliente, nomeCliente ) );
	}
	return lista;
    }

    private static PreparedStatement createStatementWithLog( Connection conn, String sql ) throws SQLException
    {
	if ( conn == null )
	{
	    return null;
	}
	return conn.prepareStatement( sql );
    }
}
package br.com.fiap.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.fiap.exception.PersistenceException;

public class ConnectionManager
{

    private static final String STR_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE = "exercicioaop";
    private static final String STR_CON = "jdbc:mysql://localhost:3306/" + DATABASE;
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection( ) throws PersistenceException
    {
	Connection conn = null;
	try
	{
	    Class.forName( STR_DRIVER );
	    conn = DriverManager.getConnection( STR_CON, USER, PASSWORD );
	    conn.setAutoCommit( false );
//	    System.out.println( "Abrindo conexão com banco de dados." );
	    return conn;
	} catch ( ClassNotFoundException e )
	{
	    String errorMsg = "Driver não encontrado";
	    throw new PersistenceException( errorMsg, e );
	} catch ( SQLException e )
	{
	    String errorMsg = "Erro ao obter a conexão";
	    throw new PersistenceException( errorMsg, e );
	}
    }

    public static void closeAll( Connection conn )
    {
	try
	{
	    if ( conn != null )
	    {
		conn.close();
//		System.out.println( "Fechando a conexão com banco de dados." );
	    }
	} catch ( Exception e )
	{
	    System.out.println( "Erro ao fechar a conexão com o banco de dados." + e.getMessage() );
	}
    }

    public static void closeAll( Connection conn, Statement stmt )
    {
	try
	{
	    if ( conn != null )
	    {
		closeAll( conn );
	    }
	    if ( stmt != null )
	    {
		stmt.close();
	    }
	} catch ( Exception e )
	{
	    System.out.println( "Erro ao fechar o Statement." + e.getMessage() );
	}
    }

    public static void closeAll( Connection conn, Statement stmt, ResultSet rs )
    {
	try
	{
	    if ( conn != null || stmt != null )
	    {
		closeAll( conn, stmt );
	    }
	    if ( rs != null )
	    {
		rs.close();
	    }
	} catch ( Exception e )
	{
	    System.out.println( "Erro ao fechar o ResultSet." + e.getMessage() );
	}
    }

}

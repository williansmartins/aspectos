package br.com.fiap.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class ClienteSimuladorBancoDados
{

    public static Map<Long, String> cliente = new HashMap<Long, String>();
    static
    {
	cliente.put( Long.valueOf( 1 ), "Pedro Alvares Cabral" );
	cliente.put( Long.valueOf( 2 ), "Zumbi dos Palmares" );
	cliente.put( Long.valueOf( 3 ), "Poderoso Lampião" );
	cliente.put( Long.valueOf( 4 ), "Lins Futebol Clube" );
	cliente.put( Long.valueOf( 5 ), "Penapolense Futebol Clube" );
    }

    public static Map<Long, String> getCliente( )
    {
        return cliente;
    }

    public static void setCliente( Map<Long, String> cliente )
    {
        ClienteSimuladorBancoDados.cliente = cliente;
    }

    public static Map<String, Integer> descontosCliente = new HashMap<String, Integer>();
    static
    {
	descontosCliente.put( cliente.get( 1l ), 10 );
	descontosCliente.put( cliente.get( 2l ), 11 );
	descontosCliente.put( cliente.get( 3l ), 12 );
    }

    private static final String PF = "Pessoa Física";
    private static final String PJ = "Pessoa Jurídica";

    public static ArrayList<String> consultarTodosTiposClientes( )
    {
	ArrayList<String> tipoCliente = new ArrayList<String>();
	tipoCliente.add( PF );
	tipoCliente.add( PJ );
	Collections.sort( tipoCliente );
	;
	return tipoCliente;
    }

    public static String[] consultarClientesPorTiposDeClientes( String tipoCliente )
    {
	HashMap<String, String[]> clientes = new HashMap<String, String[]>();
	clientes.put( PF, new String[] { cliente.get( 1l ), cliente.get( 2l ), cliente.get( 3l ) } );
	clientes.put( PJ, new String[] { cliente.get( 4l ), cliente.get( 5l ) } );
	return clientes.get( tipoCliente );
    }
}
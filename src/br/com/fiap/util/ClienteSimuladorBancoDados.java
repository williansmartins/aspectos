package br.com.fiap.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por simular consultas no banco de dados.
 * Cada método retorna valores como e tivessem executado uma
 * consulta no banco de dados.
 */
public abstract class ClienteSimuladorBancoDados {
	
	public static Map<Long, String> cliente = new HashMap<Long, String>();
	static {
		cliente.put(Long.valueOf(1), "Fabiano Rodrigues");
		cliente.put(Long.valueOf(2), "Alberto Cerqueira");
		cliente.put(Long.valueOf(3), "Alex Mas");
		cliente.put(Long.valueOf(4), "Banco Bradesco SA");
		cliente.put(Long.valueOf(5), "Banco Itau Unibanco");
		cliente.put(Long.valueOf(6), "Banco do Brasil");
	}
	
	public static Map<String, Integer> descontosCliente = new HashMap<String, Integer>();
	static {
		descontosCliente.put("Fabiano Rodrigues", 12);
		descontosCliente.put("Alberto Cerqueira", 11);
		descontosCliente.put("Alex Mas", 10);
	}
	
	private static final String PF = "Pessoa Física";
	private static final String PJ = "Pessoa Jurídica";
	
	/**
	 * Método responsável por simular uma consulta ao banco 
	 * de dados retornando todos os Tipos de Clientes encontrados.
	 * @return ArrayList<String> tipoClientes - Tipos de Clientes encontrados.
	 */
	public static ArrayList<String> consultarTodosTiposClientes(){
		ArrayList<String> tipoCliente = new ArrayList<String>();
		tipoCliente.add(PF);
		tipoCliente.add(PJ);
		Collections.sort(tipoCliente);;
		return tipoCliente;
	}
	
	/**
	 * Método responsável por simular uma consulta ao banco 
	 * retornando todos os Clientes encontrados com base num Tipo de Cliente.
	 * @param String tipoCliente - Tipos de Clientes a ter os Clientes consultados.
	 * @return HashMap<String, String> clientes - Clientes encontrados.
	 */
	public static String[] consultarClientesPorTiposDeClientes(String tipoCliente){
		HashMap<String, String[]> clientes = new HashMap<String, String[]>();
		clientes.put(PF, new String[]{"Fabiano Rodrigues", "Alberto Cerqueira", "Alex Mas"});
		clientes.put(PJ, new String[]{"Banco Bradesco SA", "Banco Itau Unibanco", "Banco do Brasil"});
		return clientes.get(tipoCliente);
	}
}
package br.com.fiap.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.com.fiap.dao.ProdutoDAOJDBC;
import br.com.fiap.model.Compra;
import br.com.fiap.model.Item;
import br.com.fiap.util.ClienteSimuladorBancoDados;
import br.com.fiap.vo.ProdutoVO;

public class tela1 extends JFrame
{

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNroPedido;
    private JTextField txtQuantidade;
    private JTable tbProdutos;
    Compra compra = new Compra();
	List<ProdutoVO> produtos = new ArrayList();
    JComboBox<ProdutoVO> cbProduto = new JComboBox<ProdutoVO>();
    private JTextField txtTotal;
    String tipoDeCliente;
    double total = 0;
	
    
    public static void main( String[] args )
    {
	EventQueue.invokeLater( new Runnable()
	{
	    public void run( )
	    {
		try
		{
		    tela1 frame = new tela1();
		    frame.setVisible( true );
		    frame.setTitle( "Trabalho professor Marcos Macedo - Venda de Produtos" );
		    frame.setLocationRelativeTo( null );
		} catch ( Exception e )
		{
		    e.printStackTrace();
		}
	    }
	} );
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public tela1()
    {
	cbProduto.setRenderer( new ListCellRenderer<Object>()
	{
	    public Component getListCellRendererComponent( JList<?> list, Object value, int index, boolean isSelected,
		    boolean cellHasFocus )
	    {
		ProdutoVO p = new ProdutoVO();
		p = (ProdutoVO) value;
		final JLabel renderer = new JLabel( p.getDescricao() );
		return renderer;
	    }
	} );

	setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	setBounds( 100, 100, 673, 500 );
	contentPane = new JPanel();
	contentPane.setBackground( Color.GRAY );
	contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
	setContentPane( contentPane );
	contentPane.setLayout( null );

	// txtNroPedido = new JTextField();
	// txtNroPedido.setHorizontalAlignment( SwingConstants.CENTER );
	// txtNroPedido.setText( "1" );
	// txtNroPedido.setBounds( 100, 26, 86, 20 );
	// contentPane.add( txtNroPedido );
	// txtNroPedido.setColumns( 10 );

	// JLabel lblNewLabel = new JLabel( "Nro. Pedido:" );
	// lblNewLabel.setBounds( 10, 31, 68, 14 );
	// contentPane.add( lblNewLabel );

	JLabel lblTipoCliente = new JLabel( "Tipo Cliente:" );
	lblTipoCliente.setBounds( 10, 62, 74, 14 );
	contentPane.add( lblTipoCliente );

	JLabel lblCliente = new JLabel( "Cliente:" );
	lblCliente.setBounds( 10, 99, 68, 14 );
	contentPane.add( lblCliente );

	JLabel lblProduto = new JLabel( "Produto:" );
	lblProduto.setBounds( 10, 134, 68, 14 );
	contentPane.add( lblProduto );

	final JComboBox<?> cbTipoCliente = new JComboBox();
	final JComboBox cbCliente = new JComboBox();

	cbTipoCliente.addActionListener( new ActionListener()
	{

	    @Override
	    public void actionPerformed( ActionEvent e )
	    {
		JComboBox combo = (JComboBox) e.getSource();
		tipoDeCliente = combo.getSelectedItem().toString();
		trazerClientes( cbCliente );
		cbCliente.enable();
	    }
	} );

	cbTipoCliente.setModel( new DefaultComboBoxModel( new String[] { "Pessoa Física", "Pessoa Jurídica" } ) );
	cbTipoCliente.setBounds( 100, 57, 129, 22 );
	contentPane.add( cbTipoCliente );

	cbCliente.setBounds( 100, 95, 400, 22 );
	cbCliente.disable();
	contentPane.add( cbCliente );

	cbProduto.setBounds( 100, 130, 400, 22 );

	contentPane.add( cbProduto );

	JButton btnAdicionarProduto = new JButton( "Adicionar produto" );
	JButton btnFinalizarCompra = new JButton( "Finalizar compra" );

	btnFinalizarCompra.addActionListener( new ActionListener()
	{

	    @Override
	    public void actionPerformed( ActionEvent arg0 )
	    {
	    	metodo( getCompra() );
	    }
	} );

	btnAdicionarProduto.addActionListener( new ActionListener()
	{
	    public void actionPerformed( ActionEvent arg0 )
	    {

		ProdutoVO produtoSelecionado = (ProdutoVO) cbProduto.getSelectedItem();
		double preco = produtoSelecionado.getPreco().doubleValue() * Double.parseDouble( txtQuantidade.getText() );
		
		DefaultTableModel model = (DefaultTableModel) tbProdutos.getModel();
		model.addRow( new Object[] { produtoSelecionado.getId(), produtoSelecionado.getDescricao(),
			produtoSelecionado.getPreco(), 
			txtQuantidade.getText(), 
			0,
			preco } );
		
		total += preco;
		txtTotal.setText( "R$ " + Double.toString( total ) );
		
		compra.setValor(new BigDecimal(total));
		System.out.println(compra);
		//		if ( hasProductInItem( compra.getItens(), produtoSelecionado ) )
//		{
//
//		} else
//		{
//		    Item i = new Item();
//		    i.setProduto( produtoSelecionado );
//		    compra.getItens().add( i );
//		}

	    }

//	    private boolean hasProductInItem( List<Item> itens, Produto p )
//	    {
//		for ( Item i : itens )
//		{
//		    if ( i.getProduto().getCodigo().equals( p.getCodigo() ) )
//		    {
//			i.getQuantidade().add( new BigInteger( "1" ) );
//			return true;
//		    }
//		}
//		return false;
//	    }

	} );
	btnAdicionarProduto.setBounds( 10, 175, 178, 23 );
	btnFinalizarCompra.setBounds( 10, 430, 178, 23 );

	contentPane.add( btnAdicionarProduto );
	contentPane.add( btnFinalizarCompra );

	txtQuantidade = new JTextField();
	txtQuantidade.setHorizontalAlignment( SwingConstants.CENTER );
	txtQuantidade.setText( "1" );
	txtQuantidade.setColumns( 10 );
	txtQuantidade.setBounds( 576, 131, 28, 20 );
	contentPane.add( txtQuantidade );

	JLabel lblQuantidade = new JLabel( "Quantidade:" );
	lblQuantidade.setBounds( 501, 134, 68, 14 );
	contentPane.add( lblQuantidade );

	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds( 10, 237, 625, 183 );
	contentPane.add( scrollPane );

	tbProdutos = new JTable();
	scrollPane.setViewportView( tbProdutos );

	tbProdutos.setModel( new DefaultTableModel( new Object[][] {}, new String[] { "Id produto", "Descrição",
		"Valor unit\u00E1rio", "QTD", "Desconto", "Total" } ) );

	txtTotal = new JTextField();
	txtTotal.setText( "0" );
	txtTotal.setHorizontalAlignment( SwingConstants.LEFT );
	txtTotal.setColumns( 10 );
	txtTotal.setBounds( 475, 198, 160, 20 );
	contentPane.add( txtTotal );

	JLabel lblTotal = new JLabel( "TOTAL GERAL:" );
	lblTotal.setHorizontalAlignment( SwingConstants.RIGHT );
	lblTotal.setBounds( 372, 201, 99, 14 );
	contentPane.add( lblTotal );
	
   
	

	// for ( Integer i = 0; i < 10; i++ )
	// {
	// Produto p = new Produto();
	// p.setCodigo( new BigInteger( i.toString() ) );
	// p.setDescricao( "Livro - " + i );
	// p.setPreco( new BigDecimal( 45.5 ) );
	// produtos.add( p );
	// }

	ProdutoDAOJDBC dao = new ProdutoDAOJDBC();

	compra.setItens( new ArrayList<Item>() );

	for ( ProdutoVO p : dao.listarTodos() )
	{
	    cbProduto.addItem( p );
	}

    }

    void metodo(Compra compra2) {
    	System.out.println(">> " + compra2.getValor());
	}

	private void trazerClientes( JComboBox cbCliente )
    {
	String[] consultarClientesPorTiposDeClientes = ClienteSimuladorBancoDados
		.consultarClientesPorTiposDeClientes( tipoDeCliente );
	cbCliente.setModel( new DefaultComboBoxModel( consultarClientesPorTiposDeClientes ) );
    }
	
    public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}
}
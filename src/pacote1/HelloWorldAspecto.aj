package pacote1;

import br.com.fiap.model.Compra;

public aspect
HelloWorldAspecto {

	before(Compra compra) : call(* br.com.fiap.gui.tela1.metodo(Compra)) && args(compra){
		System.out.println(compra.getValor());
		
	}
	
	
//	@Pointcut("execution(* *.actionPerformed(*)) && args(actionEvent) && if()")
//	public static boolean button1Pointcut(ActionEvent actionEvent) {
//		JButton button = (JButton) actionEvent.getSource();
//		System.out.println("opa:" + button);
//		return (actionEvent.getSource() == btnFinalizarCompra);
//	}
//
//	@Before("button1Pointcut(actionEvent)")
//	public void beforeButton1Pointcut(ActionEvent actionEvent) {
//		System.out.println("...");
//	}


}
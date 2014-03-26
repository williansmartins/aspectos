package pacote1;

public aspect
HelloWorldAspecto
{
    pointcut exemplo1( ) : initialization( public pacote1.HelloWorld.new(..));
    
    pointcut exemplo2( ) : call( public * pacote1.HelloWorld.*(..) ) ;

    pointcut exemplo3( ) : call( public * pacote1.HelloWorld.sayHello(..) ) ;
    
    pointcut exemplo4( ) : call( public * br.com.fiap.gui.tela1.addActionListener(..) ) ;

    before( ) : exemplo1( ) {
	System.out.println( "antes de inicializar uma classe" );
    }

    before( ) : exemplo2( ) {
	System.out.println( "antes qualquer m�todo da classes HelloWorld" );
    }

    after( ) : exemplo3( ) {
	System.out.println( "ap�s o m�todo sayHello de HelloWorld" );
    }
    
    after( ) : exemplo4( ) {
	System.out.println( "................." );
    }
}
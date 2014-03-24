package pacote1;

public class HelloWorld
{
    public void sayHello( )
    {
	System.out.println( "Hello!" );
    }

    public void metodo( )
    {
	System.out.println( "metodo!" );
    }

    public static void main( String args[] )
    {
	new HelloWorld().sayHello();
    }
}
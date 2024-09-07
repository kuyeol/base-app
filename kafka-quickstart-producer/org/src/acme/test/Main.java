package src.acme.test;

public class Main
{

  public static void main( String[] args )
    {
      Model model = new Model();

      model.setAge( 12 );
      model.setName( "John Doe" );
      model.setState( Model.State.HELLO );
      System.out.println( model );
    }





}

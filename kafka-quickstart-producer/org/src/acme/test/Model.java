package src.acme.test;

public class Model
{

  private String name;

  private int age;


  public State getState()
    {
      return state;
    }


  public void setState( State state )
    {
      this.state = state;
    }


  private State state;

  public Model(){ }


  public Model( String name, int age )
    {
      this.name = name;
      this.age  = age;
    }


  public String getName()
    {
      return name;
    }


  public void setName( String name )
    {
      this.name = name;
    }


  public int getAge()
    {
      return age;
    }


  public void setAge( int age )
    {
      this.age = age;
    }


  @Override
  public String toString()
    {
      return "Model{" + "name='" + name + '\'' + ", age=" + age + ", state=" + state + '}';
    }


  public enum State
  {
    HI,
    HELLO
  }





}

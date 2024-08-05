

# Summary : 
## `@Autowired`

> `@Target`
>({ElementType.`CONSTRUCTOR`, ElementType.`METHOD`, ElementType.`PARAMETER`, ElementType.`FIELD`, ElementType.
 `ANNOTATION_TYPE`})
>  - 위 타겟의 코드 자동 생성함
>  - 사용 하는 클래스에서 가져올 클래스의 인스턴스화 코드를 자동 생성을 해준다
>  - 스프링에서 선택사항으로 제안하는 어너테이션
> 

> [!Caution]
> 
> 주의 사항 :
>> - 명시적으로 작성되지 않은 코드로 인해 스파게티 코드의 위험 증가
>>  - 오류 발생 시 인지 하기 어려움 
>  
> 결론 :
>> - 알수 없는 코드 버그를 만들지 않으려면 명시적으로 코드 작성 해야하겠다
> >  - 효율 vs 정확 






```java

  /* 자동주입 사용 코드

   */

  @Component
  public class Multiplier {
    public int multiply(int a, int b) {
      return a * b;
    }
  }

  @Component
  public class Calculator {

    //--  곱셈 연산 클래스를 계산기 클래스에서 인스턴스 생성
    //

    @Autowired
    private Multiplier multiplier;

    public int add(int a, int b) {
      return a + b;
    }

    public int multiply(int a, int b) {
      return multiplier.multiply(a, b);
    }
  }

  //--


```
```java




  /*
 Do not using Autowired
 어노테이션이 생략 된 코드
   */

  @Component
  public class Multiplier {
    public int multiply(int a, int b) {
      return a * b;
    }
  }

  @Component
  public class Calculator {

    //private 사용해 초기화 동인 어노테이션만 제거됨
    private Multiplier multiplier;

    // 클래스 생성자에 곱셈 클래스 정의
    public Calculator(Multiplier multiplier) {
      this.multiplier = multiplier;
    }

    public int add(int a, int b) {
      return a + b;
    }

    public int multiply(int a, int b) {
      return multiplier.multiply(a, b);
    }
  }

```
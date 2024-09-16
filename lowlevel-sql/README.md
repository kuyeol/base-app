# Low Level- Reactive Sql Client



데이터 스트림
소스 : 메세지 생성 발행
싱크 :메세지 수신 소비
프로세서 :  메세지 소비 -> 생성 -> 발행

스프링 예제

이벤트 생성 발행

마이크로서비스 구성

1.이벤트 생성 앱
 - 대출 신청

> public Supplier<Loan> summitLoan(){
> return () -> {
> String name;
> Long amount;
> Loan loan =new Loan(uuid,name,amount);
> return loan;



2.이벤트 소비 앱
 - 신청 승인

> public interface LoanProcessor{
> String APPLICATIONS_IN = "output";
> String APPROVED_OUT = "approved";
> String DECLINED_OUT = "declined";
> 
> @Input(APPLICATIONS_IN)
> SubscribableChannel sourceOfLoanApplications();
> 
> @Output(APPROVED_OUT)
> MessageChannel approved();
> 
> @Output(DECLINED_OUT)
> MessageChannel declined();
> }


> public void checkLoan(Loan loan)  
> {   
>   /**
>   *MAX_AMOUNT 보다 크면 거절  */
> 
> > if ( loan.getAmount() > `MAX_AMOUNT`)
> >   {
> >       
> >  loan.setStatus(Status.DECLINED.name());   
> >  processor.declined().send(message(loan));     
> >    }
> 
> > else
> >  {
> >
> >      loan.setStatus(Status.APPROVED.name());
> >      processor.approved().send(message(loan));
> >   }
> 
> }
>         







# TODO :

> [!Note]
> - Pagenationg
> - sort




# Client Qeury & Hibernate Panache Query

1. Using PgPool Call
  - PgPool Inject
  - PgPool Instance return & using query transfer query

2. Using PanacheEntity Extends
  - Entitiy extends PanacheEntity
  - Entity return PanacheEntity method & @ApplicationScoped
    









</BR></BR></BR></BR>

# QUARKUS DEFUALT TEXT
<details> 

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/lowlevel-sql-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- Reactive PostgreSQL client ([guide](https://quarkus.io/guides/reactive-sql-clients)): Connect to the PostgreSQL
  database using the reactive pattern
 </details>

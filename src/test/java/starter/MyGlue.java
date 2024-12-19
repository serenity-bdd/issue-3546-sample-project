package starter;

import static java.lang.String.join;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.core.exceptions.IgnoreStepException;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Consequence;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;

import java.util.Arrays;
import java.util.List;

public class MyGlue {

  class MyActor extends Actor {
    public MyActor(String name) {
      super(name);
    }

    public void myShould(Consequence<?>... consequences) {
      for (Consequence<?> consequence : consequences) {
        check(consequence);
      }
      throwSummaryExceptionFrom();
    }

    private void throwSummaryExceptionFrom() {
      throw new AssertionFailedError("Oh crap!");
    }

    private <T> void check(Consequence<T> consequence) {

//      ConsequenceCheckReporter reporter = new ConsequenceCheckReporter(eventBusInterface, consequence);
      try {
//        reporter.startQuestion(this);
          consequence.evaluateFor(this);
      } catch (IgnoreStepException e) {
      }
    }

  }

  @Given("prepare something")
  public void prepare_something() {
    System.out.println("preparing something");
  }

  @When("do something")
  public void do_something() {
    System.out.println("do something");
  }

  @When("do something wrong")
  public void do_something_wrong() {
    Actor actor = Actor.named("Alice").whoCan(CallAnApi.at("http://www.example.com"));
//    MyActor actor = new MyActor("Alice");
    actor.can(CallAnApi.at("http://www.example.com"));
    sendRequest(actor);
//    actor.myShould(seeThatResponse("message", response -> response.statusCode(500)));
    actor.should(seeThatResponse("message", response -> response.statusCode(500)));
  }

  void fail() {
    Assertions.assertTrue(false);
  }

  private <T extends Actor> void sendRequest(T actor) {
    actor.attemptsTo(Get.resource("/"));
  }
}

package starter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.serenitybdd.screenplay.rest.interactions.Get;
import org.junit.jupiter.api.Assertions;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class MyGlue {

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
    actor.can(CallAnApi.at("http://www.example.com"));
    sendRequest(actor);
    actor.should(seeThatResponse("message", response -> response.statusCode(500)));
  }

  private <T extends Actor> void sendRequest(T actor) {
    actor.attemptsTo(Get.resource("/"));
  }
}

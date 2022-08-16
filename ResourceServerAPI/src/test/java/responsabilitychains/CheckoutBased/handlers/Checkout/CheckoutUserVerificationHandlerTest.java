package responsabilitychains.CheckoutBased.handlers.Checkout;

import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.ChainResponse;
import dev.ahernandez.checkoutresourceapi.logic.responsabilitychains.checkoutbased.handlers.checkout.CheckoutUserVerificationHandler;
import dev.ahernandez.checkoutresourceapi.persistence.model.Checkout;
import dev.ahernandez.checkoutresourceapi.persistence.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;

@DisplayName("CheckoutUserVerificationHandler Test")
class CheckoutUserVerificationHandlerTest {

    private CheckoutUserVerificationHandler verificationHandler;

    @BeforeEach
    void setup(){
        verificationHandler = new CheckoutUserVerificationHandler();
    }

    @Test
    @DisplayName("Success CheckoutUserVerificationHandler")
    void successHandler(){
        Checkout checkout = new Checkout();
        User user = new User();
        user.setUserId("");
        user.setFirstName("");
        user.setLastName("");
        user.setMail("");
        user.setPhone("");
        checkout.setUser(user);

        ChainResponse actual = verificationHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(true, "", checkout);

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail CheckoutUserVerificationHandler, user null")
    void nullUserHandler(){
        Checkout checkout = new Checkout();

        ChainResponse actual = verificationHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "User not specified");

        assertThat(actual, samePropertyValuesAs(expected));
    }

    @Test
    @DisplayName("Fail CheckoutUserVerificationHandler, user not complete")
    void userDataNotCompleteHandler(){
        Checkout checkout = new Checkout();
        checkout.setUser(new User());

        ChainResponse actual = verificationHandler.handle(checkout);

        ChainResponse expected = new ChainResponse(false, "User data not complete");

        assertThat(actual, samePropertyValuesAs(expected));
    }
}

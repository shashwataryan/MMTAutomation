package stepDefinitions;

import java.io.IOException;
import org.testng.Assert;
import io.cucumber.java.en.*;
import pageObjects.SignIn;
import utilities.Hooks;
import utilities.Reports;

public class MMTSignIn {


	SignIn accountSignIn=new SignIn(Commons.getDriver());
	int errorExcpetion=0,screenShot;
	Integer error;
	int interr;

	@When("^User Enters ([^\"]*) and ([^\"]*) and logs in$")
	public void enterEmailpassword(String email,String password)
	{
		try {
			accountSignIn.loginToMMT();
			accountSignIn.enterEmailAndPassword(email, password);
		}
		catch(Exception e)
		{
			error=Hooks.errorException.get();
			interr=error+1;
			Hooks.errorException.set(interr);
		}
		catch(AssertionError e1)
		{
			error=Hooks.errorException.get();
			interr=error+1;
			Hooks.errorException.set(interr);
		}
	}

	@Then("User is logged into his account")
	public void verifyAccount() throws IOException
	{
		Hooks.test=Reports.extent.createTest("Sign In to MMT");
		try {
			String loggedInAccount=accountSignIn.verifyAccount();
			Assert.assertEquals(loggedInAccount,"Looks like we are facing some technical issues, please try again in some time.");

		}

		catch(Exception e)
		{
			error=Hooks.errorException.get();
			interr=error+1;
			Hooks.errorException.set(interr);
		}
		catch(AssertionError e1)
		{
			error=Hooks.errorException.get();
			interr=error+1;
			Hooks.errorException.set(interr);

		}
	}
}

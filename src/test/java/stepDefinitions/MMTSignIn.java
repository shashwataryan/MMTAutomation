package stepDefinitions;

import java.io.IOException;
import org.testng.Assert;
import io.cucumber.java.en.*;
import pageObjects.SignIn;
import utilities.Hooks;
import utilities.Reports;

public class MMTSignIn {


	SignIn accountSignIn=new SignIn(Commons.driver);
	int errorExcpetion=0,screenShot;
	

	@When("^User Enters ([^\"]*) and ([^\"]*) and logs in$")
	public void enterEmailpassword(String email,String password)
	{
		try {
			accountSignIn.loginToMMT();
			accountSignIn.enterEmailAndPassword(email, password);
		}
		catch(Exception e)
		{
			Hooks.errorException++;
		}
		catch(AssertionError e1)
		{
			Hooks.errorException++;
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
			Hooks.errorException++;
		}
		catch(AssertionError e1)
		{
			Hooks.errorException++;

		}
	}
}

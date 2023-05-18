package stepDefinitions;

import java.io.IOException;
import org.testng.Assert;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.en.*;
import pageObjects.SignIn;
import utilities.Reports;

public class MMTSignIn {


	SignIn accountSignIn=new SignIn(Commons.getDriver());
	int errorExcpetion=0,screenShot;
	public ExtentTest test;

	@When("^User Enters ([^\"]*) and ([^\"]*) and logs in$")
	public void enterEmailpassword(String email,String password)
	{
		try {
			accountSignIn.loginToMMT();
			accountSignIn.enterEmailAndPassword(email, password);
		}
		catch(Exception e)
		{
			errorExcpetion++;
		}
		catch(AssertionError e1)
		{
			errorExcpetion++;
		}
	}

	@Then("User is logged into his account")
	public void verifyAccount() throws IOException
	{
		test=Reports.extent.createTest("Sign In to MMT");
		try {
			String loggedInAccount=accountSignIn.verifyAccount();
			Assert.assertEquals(loggedInAccount,"Looks like we are facing some technical issues, please try again in some time.");

		}

		catch(Exception e)
		{
			errorExcpetion++;
		}
		catch(AssertionError e1)
		{
			errorExcpetion++;
		}

		finally {
			if(errorExcpetion==0)
				test.log(Status.PASS, "MMT Sign In checked for negative case");
			else
			{
				test.log(Status.FAIL,"SigIn for Negative case failed");
				screenShot=Commons.getSS();
				test.addScreenCaptureFromPath(".\\test-output\\Test"+screenShot+".png", "ScreenShot for failed test case");
			}

			if(errorExcpetion!=0)
				Assert.fail();
		}
	}
}

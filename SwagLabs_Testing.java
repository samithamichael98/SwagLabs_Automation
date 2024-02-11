package Assignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class SwagLabs_Testing {
	
	public String baseURL = "https://www.saucedemo.com/";
	public String driverpath = "F:\\SLIIT\\Assignment\\Third Party Resource Files\\Chrome Driver\\V119.exe";
	public WebDriver driver;
	public String Expected_Text;
	public String Actual_Text;
	public String pagetitle;
	public int cartQuantityValue;
	public int Actual_Qty;
	public String itemPriceElement;
	public String itemNameElement;
	public String itemDescriptionElement;
	
	
	@BeforeTest
	public void beforeTest() {
	
	System.setProperty("webdriver.chrome.driver",driverpath);
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get(baseURL);
	
	}
	
	//Test case Section
	
	
	@Test (priority =1)
	public void userlogin() throws  InterruptedException{

	System.out.println("************************************ Test Case ************************************ \n\n");
	System.out.println("************************************  TC 001 ************************************* \n\n");
			
	System.out.println("------------------------ TC 001-01 - Verify that the User can logging with incorrect user Name and Password \n");
	
	driver.findElement(By.id("user-name")).sendKeys("standarduser");
	driver.findElement(By.id("password")).sendKeys("secretsauce");
	
	driver.findElement(By.id("login-button")).click();
	
	String errorMessage = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
	Actual_Text = "Epic sadface: Username and password do not match any user in this service";
	
	if (errorMessage.equals(Actual_Text)) {
		
		System.out.println("---- TC 001-01 - Passed ----- ");
		System.out.println("Unsucessfully Logging to the Swag Labs");
		System.out.println("Test Passed: User cannot  logging to system with-out correct user name and password \n");
		
	}
		else {
			System.out.println("---- TC 001-01 - Failed -----");
			System.out.println("Test Failed: User can  logging to system with-out  correct user name and password \n");
			System.out.println("Sucessfully Logging to the Swag Labs");
		}
	
	driver.navigate().refresh();
	
	Thread.sleep(3000);
		
	System.out.println("------------------------ TC 001-02 - User logging with correct user Name and Password\n");
		
	driver.findElement(By.id("user-name")).sendKeys("standard_user");
	driver.findElement(By.id("password")).sendKeys("secret_sauce");
	
	driver.findElement(By.id("login-button")).click();
	
	pagetitle = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[1]/div[2]/div")).getText();
	Actual_Text = "Swag Labs";
	
	
	if (pagetitle.equals(Actual_Text)) {
		
		System.out.println("---- TC 001-02 - Passed -----");
		System.out.println("Test Passed: Sucessfully Logging to the "+ pagetitle );
		System.out.println("Test Passed: User can logging to system with correct user name and password");
	}
		else {
			System.out.println("----TC 001-02 - Failed -----");
			System.out.println("Test Failed: Logging Unsucessfull");
			System.out.println("Test Failed: User can not logging to system with correct user name and password");
		}
	

	
	
		itemPriceElement = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[2]/div")).getText();
		itemNameElement = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
		itemDescriptionElement = driver.findElement(By.xpath("//*[@id=\"inventory_container\"]/div/div[1]/div[2]/div[1]/div")).getText();	
	}

	@Test (priority =2)
	public void AddtoCart() throws  InterruptedException{
		
		Expected_Text= "Products";
		Actual_Text = driver.findElement(By.className("title")).getText();
		

		System.out.println("\n\n\n************************************  TC 002 ************************************* \n\n");
		
		System.out.println("------------------------ TC 002-01 Verify the page title \n");
			
		if (Expected_Text.equals(Actual_Text)) {
			
			System.out.println("---- TC 002-01 - Passed -----");
			System.out.println("Test Passed: we are in the "+ Actual_Text + "Page");
			}
			else {
				System.out.println("----TC 002 - Failed -----");
				System.out.println("Test Failed: we are in the "+ Actual_Text + "Page");
			}
		
		Thread.sleep(1000);
		
		driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		
		System.out.println("\n------------------------ TC 002-02 - Verify that the selected item can added to the shopping cart");
		
		
	 if (driver.getPageSource().contains("Remove")) {
		 
		 System.out.println("---- TC 002-02 - Passed -----");
	 	 System.out.println("Test Passed: sauce-labs-backpack 'Add to cart' button clicked successfully."); 
	 	 System.out.println("Test Passed: Item sucessfully added to the shopping cart"); 
	 	} 
	 
	 else { 
		 System.out.println("----TC 002-02 - Failed -----");
		 System.out.println("Test Failed: Item not added to cart."); 
	 
	 	}	 
		
	}
	
	
	@Test (priority =3)
	public void CartValidation() throws  InterruptedException{
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
		
		System.out.println("\n\n\n************************************  TC 003 ************************************* \n\n");
		
		System.out.println("\n------------------------  TC 003-01 Verify that the item details are correct in the shopping cart");
		
		Expected_Text= "Your Cart";
		Actual_Text = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
		
		if (Expected_Text.equals(Actual_Text)) {
			
			System.out.println("\n ----  TC 003-01 -  Passed -----");
			System.out.println("Test Passed: we are in the "+ Actual_Text + " Page");
			}
			else {
				System.out.println("----TC 003-01 - Failed -----");
				System.out.println("Test Failed: we are in the "+ Actual_Text + " Page");
			}
		
		System.out.println("\n------------------------ TC 003-02 Verify that the Item QTY");
		
		Actual_Qty = 1;
		WebElement Expected_Qtyt = driver.findElement(By.className("cart_quantity"));
		String cartQuantityText = Expected_Qtyt.getText();
		cartQuantityValue = Integer.parseInt(cartQuantityText);
		
		
		 if (cartQuantityValue == Actual_Qty)  {
			 
			 System.out.println("\n ---- TC 003-02 - Passed -----");
		 	 System.out.println("Test Passed: Quantity is matched, QTY = "+ cartQuantityValue); 
		 	} 
		 
		 else { 
			 System.out.println("\n ---- TC 003-02 - Failed -----");
			 System.out.println("Test Failed: Quantity is not matched, QTY = "+ cartQuantityValue); 	
	}
		 
			System.out.println("\n------------------------ TC 003-03 Verify that the Item Price");
			
			String Expected_Price = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[2]/div")).getText();
	
			if (Expected_Price.equals(itemPriceElement))  {
				 
				 System.out.println("\n ---- TC 003-02 - Passed -----"); 
				 System.out.println("Test Passed: Price is matched, Price = "+ Expected_Price); 
		 	}  
			 
			 else { 
				 System.out.println("\n ----TC 003-02- Failed -----");
				 System.out.println("Test Failed: Price is not matched, Price = "+  Expected_Price);
		}
			
			System.out.println("\n------------------------ TC 003-04 Verify that the Item Name");
			
			String Expected_ItemName = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
	
			if (Expected_ItemName.equals(itemNameElement))  {
				 
				 System.out.println("\n ---- TC 003-04 - Passed -----"); 
				 System.out.println("Test Passed: Item Name is matched, Item Name = "+ Expected_ItemName); 
		 	}  
			 
			 else { 
				 System.out.println("\n ----TC 003-04- Failed -----");
				 System.out.println("Test Failed: Item Name is not matched, Item Name = "+ Expected_ItemName); 
		}
			
			System.out.println("\n------------------------ TC 003-05 Verify that the Item Desciption");
			
			String Expected_ItemDescription = driver.findElement(By.xpath("//*[@id=\"cart_contents_container\"]/div/div[1]/div[3]/div[2]/div[1]")).getText();
	
			if (Expected_ItemDescription.equals(itemDescriptionElement))  {
				 
				 System.out.println("\n ---- 003-05 - Passed -----"); 
				 System.out.println("Test Passed: Item Description is matched, Item Description = "+ Expected_ItemDescription); 
		 	}  
			 
			 else { 
				 System.out.println("\n ----003-05 - Failed -----");
				 System.out.println("Test Failed:Item Description is not matched, Item Description = "+ Expected_ItemDescription); 
		}
			
			
			System.out.println("\n------------------------ TC 003-06 Check that te buttton lable change as the Remove");
			
			Actual_Text = driver.findElement(By.xpath("//*[@id=\"remove-sauce-labs-backpack\"]")).getText();
	
			if (Actual_Text.contains("Remove")) {
				 
				 System.out.println("\n ---- TC 003-06 - Passed -----");
			 	 System.out.println("Test Passed: Button labeled as  Remove "); 
			 	} 
			 
			 else { 
				 System.out.println("\n ---- TC 003-06 - Failed -----");
				 System.out.println("Test Failed: Button doesn't labeled as  Remove"); 
			 
			 	}

}
	
	@Test (priority =4)
	public void AddYourDetails() throws InterruptedException{
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();
		
		System.out.println("\n\n\n************************************  TC 004 ************************************* \n\n");
		
		System.out.println("\n------------------------ TC 004-01 Verify that the we are navigate to the Checkout: Your Information Page");
		
		Expected_Text= "Checkout: Your Information";
		Actual_Text = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
		
		if (Expected_Text.equals(Actual_Text)) {
			
			System.out.println("\n ---- TC 004-01 - Passed -----");
			System.out.println("Test Passed: we are in the "+ Actual_Text + " Page");
			}
			else {
				System.out.println("\n ---- TC 004-01 - Failed -----");
				System.out.println("Test Failed: we are in the "+ Actual_Text + "Page");
			}
		
		driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("Samitha");
		driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys("Michael");
		driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys("61230");
		
		Thread.sleep(3000);
		
		String input_text1 = driver.findElement(By.xpath("//*[@id=\"first-name\"]")).getAttribute("value");
		String input_text2 = driver.findElement(By.xpath("//*[@id=\"last-name\"]")).getAttribute("value");
		String input_text3 = driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).getAttribute("value");
		
		System.out.println("\n------------------------ TC 004-02 Verify that the all required all filled");
		
		if(input_text1.isEmpty() && input_text2.isEmpty() && input_text3.isEmpty()) {
			
			System.out.println("\n ----TC 004-02 - Failed -----");
			System.out.println("Test Failed : All requird fileds are not filled");
		}
		
		else {
			System.out.println("\n ----TC 004-02 - Passed -----");
			System.out.println("Test Passed : All requird fileds are filled");
			System.out.println(input_text1);
			System.out.println(input_text2);
			System.out.println(input_text3);
		}
		
	}	
	
	@SuppressWarnings("unused")
	@Test (priority =5)
	public void OverViewValidation() throws InterruptedException{
		
		driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
		
		System.out.println("\n\n\n************************************  TC 005 ************************************* \n\n");
		System.out.println("\n------------------------ TC 005-01 Verify that the we are redirect to the Checkout: Overview page");
		
		
		Expected_Text= "Checkout: Overview";
		Actual_Text = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
		
		if (Expected_Text.equals(Actual_Text)) {
			
			System.out.println("\n ---- TC 005-01 - Passed -----");
			System.out.println("Test Passed: we are in the "+ Actual_Text + " Page");
			}
			else {
				System.out.println("\n ---- TC 005-01 - Failed -----");
				System.out.println("Test Failed: we are in the "+ Actual_Text + "Page");
			}
		
		
		System.out.println("\n------------------------ TC 005-02 Verify that Item QTY in  Checkout: Overview page");
		
		Actual_Qty = 1;
		WebElement Expected_Qtyt = driver.findElement(By.className("cart_quantity"));
		String cartQuantityText = Expected_Qtyt.getText();
		cartQuantityValue = Integer.parseInt(cartQuantityText);
		
		
		 if (cartQuantityValue == Actual_Qty)  {
			 
			 System.out.println("\n ---- 005-02 - Passed -----");
		 	 System.out.println("Test Passed: Item quantity is matched, QTY = "+ cartQuantityValue); 
		 	} 
		 
		 else { 
			 System.out.println("---- 005-02 - Failed -----");
			 System.out.println("Test Failed: Item quantity is not matched, QTY = "+ cartQuantityValue); 	
	}
		 
		 	Thread.sleep(3000);
		 
		 	System.out.println("\n------------------------ TC 005-03 Verify that Item price in  Checkout: Overview page\n");
			
			String Expected_Price = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[6]")).getText();
			
			itemPriceElement = "Item total: "+itemPriceElement;
				
			if (itemPriceElement.equals(Expected_Price))  {
				 
				 System.out.println("---- TC 005-03 - Passed -----"); 
				 System.out.println("Test Passed: Price is matched, Price = "+ Expected_Price); 
		 	}  
			 
			 else { 
				 System.out.println("----TC 005-03- Failed -----");
				 System.out.println("Test Failed: Proce is not matched, Price = "+  Expected_Price); 
		}
			
			System.out.println("\n------------------------ TC 005-04 Verify that Item Name in  Checkout: Overview page");
			
			String Expected_ItemName = driver.findElement(By.xpath("//*[@id=\"item_4_title_link\"]/div")).getText();
			
	
			if (Expected_ItemName.equals(itemNameElement))  {
				 
				 System.out.println("\n---- TC 005-04 - Passed -----"); 
				 System.out.println("Test Passed: Item Nmae is matched, Item Name = "+ Expected_ItemName); 
		 	}  
			 
			 else { 
				 System.out.println("\n---- TC 005-04 Failed -----");
				 System.out.println("Test Failed: Item Name is not matched, Item Name = "+ Expected_ItemName); 
		}
			
			System.out.println("\n------------------------ TC 005-05 Verify that Item Description in  Checkout: Overview page");
			
			String Expected_ItemDescription = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[1]/div[3]/div[2]/div[1]")).getText();
	
			if (Expected_ItemDescription.equals(itemDescriptionElement))  {
				 
				 System.out.println("\n---- TC 005-05 - Passed -----"); 
				 System.out.println("Test Passed: Item Description is matched, Item Description = "+ Expected_ItemDescription); 
		 	}  
			 
			 else { 
				 System.out.println("\n---- TC 005-05 - Failed -----");
				 System.out.println("Test Failed: Item Description is not matched, Item Description = "+ Expected_ItemDescription); 
		}
			
			
			System.out.println("\n------------------------ TC 005-06 Verify that Payment Information in  Checkout: Overview page");
			
			String PaymentInformation = driver.findElement(By.className("summary_value_label")).getText();
			
			if(PaymentInformation.isEmpty()) {
				
				
				System.out.println("\n ---- TC 005-06 - Failed -----");
				System.out.println("est Failed: Payment Information not Found");
			}
			
			else {
				System.out.println("\n ---- TC 005-06 - Passed -----");
				System.out.println("Test Passed: Payment Information founded = "+ PaymentInformation); 
			
			}
			
			System.out.println("\n------------------------ TC 005-07 Verify that Shipping Information in  Checkout: Overview page");
			
			String ShippingInformation = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[4]")).getText();
			
			if(ShippingInformation.isEmpty()) {
				
				
				System.out.println("\n ---- TC 005-07 - Failed -----");
				System.out.println("Test Failed: ShippingInformation not Found");
			}
			
			else {
				System.out.println("\n ---- TC 005-07 - Passed -----");
				System.out.println("Test Passed: ShippingInformation founded = "+ ShippingInformation); 
			
			}
			
			System.out.println("\n------------------------ TC 005-08 Verify that Item Total in  Checkout: Overview page");
			
			WebElement ItemTotal = driver.findElement(By.className("summary_subtotal_label"));
			String ItemTot= ItemTotal.getText();
			String numericPart = ItemTot.replaceAll("[^\\d.]+", "");
			double Item_total = Double.parseDouble(numericPart);
			
			@SuppressWarnings("removal")
			Double tot= new Double(Item_total);
			
			if(tot == null) {
				System.out.println("\n ---- TC 005-08 - Failed -----");
				System.out.println("Test Failed: Item total not Found");
			}
			else {
				System.out.println("\n ---- TC 005-08 - Passed -----");
				System.out.println("Test Passed: Item total = " + Item_total);
			}
			
			
			System.out.println("\n------------------------ TC 005-09 Verify that Tax amount in Checkout: Overview page");
			
			WebElement tax = driver.findElement(By.className("summary_tax_label"));
			String tax1= tax.getText();
			String tax2 = tax1.replaceAll("[^\\d.]+", "");
			double TotTax = Double.parseDouble(tax2);
			
			@SuppressWarnings("removal")
			Double tax3= new Double(TotTax);
			
			if(tax3 == null) {
				System.out.println("\n ----  TC 005-09 - Failed -----");
				System.out.println("Test Failed: Tax not Found");
			}
			else {
				System.out.println("----  TC 005-09 - Passed -----");
				System.out.println("Test Passed: Tax amount is founded = " + TotTax);
			}
			
			Thread.sleep(1000);
			
			System.out.println("\n------------------------ TC 005-10 Verify that Total amount in Checkout: Overview page");
			
			WebElement Total = driver.findElement(By.xpath("//*[@id=\"checkout_summary_container\"]/div/div[2]/div[8]"));
			String tot_1= Total.getText();
			String tot_2 = tot_1.replaceAll("[^\\d.]+", "");
			double Total_1 = Double.parseDouble(tot_2);;
			
			double Actual_tot =Item_total + TotTax;
		
			if(Actual_tot == Total_1) {
				System.out.println("---- TC 005-10 - Passed -----");
				System.out.println("Price Total Test Passed: Total = " + Actual_tot);
			}
			else {
				System.out.println("---- TC 005-10 - Failed -----");
				System.out.println("Price Total - Tax  Failed: Total incorrect ");
			}
			
	}
	

	
	@Test (priority =6)
	public void CheckOutValidation() throws InterruptedException{
		
		Thread.sleep(3000);
		
		driver.findElement(By.id("finish")).click();
		
		
		System.out.println("\n\n\n************************************  TC 005 ************************************* \n\n");
		
		Expected_Text= "Checkout: Complete!";
		Actual_Text = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
		
		if (Expected_Text.equals(Actual_Text)) {
			
			System.out.println("---- TC 005 - Passed -----");
			System.out.println("we are in the "+ Actual_Text + " Page");
			}
			else {
				System.out.println("---- TC 005- Failed -----");
				System.out.println("we are in the "+ Actual_Text + "Page");
			}
		
		
		Actual_Text = driver.findElement(By.xpath("//*[@id=\"checkout_complete_container\"]/h2")).getText();

		System.out.println("\n\n\n************************************  TC 006 ************************************* \n\n");
		
		Expected_Text = "Thank you for your order!";
		
		if (Expected_Text.equals(Actual_Text)) {
			
			System.out.println("---- TC 023 - Passed -----");
			System.out.println("Sucessfully Compelete Order ");
			}
			else {
				System.out.println("----TC 023 - Failed -----");
				System.out.println("Unfortunately, There is an error");
			}	
	}
	
		@Test (priority =7)
		public void ReturntoHomeutValidation() throws InterruptedException{
		
		Thread.sleep(3000);
		
		System.out.println("\n\n\n************************************  TC 007 ************************************* \n\n");
		
		driver.findElement(By.xpath("//*[@id=\"back-to-products\"]")).click();
		
		Expected_Text = "Products";
		
		Actual_Text = driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/span")).getText();
		
		
		if (Expected_Text.equals(Actual_Text)) {
			
			System.out.println("---- TC 023 - Passed -----");
			System.out.println("Sucessfully redirect to Product Page");
			}
			else {
				System.out.println("----TC 023 - Failed -----");
				System.out.println("Unsucessfully redirect to Product Page");
			}	
	}
	
}

	


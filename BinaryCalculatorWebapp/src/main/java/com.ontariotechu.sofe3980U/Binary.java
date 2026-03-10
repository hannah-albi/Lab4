package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary
{
	private String number="0";  // string containing the binary value '0' or '1'
	/**
	* A constructor that generates a binary object.
	*
	* @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	*/
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}
	
		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}
	
		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}
	
		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);
	
		
		if (this.number.isEmpty()) { // replace empty strings with a single zero
			this.number = "0";
		}

	}
	/**
	* Return the binary value of the variable
	*
	* @return the binary value in a string format.
	*/
	public String getValue()
	{
		return this.number;
	}
	/**
	* Adding two binary variables. For more information, visit <a href="https://www.wikihow.com/Add-Binary-Numbers"> Add-Binary-Numbers </a>.
	*
	* @param num1 The first addend object
	* @param num2 The second addend object
	* @return A binary variable with a value of <i>num1+num2</i>.
	*/
	public static Binary add(Binary num1,Binary num2)
	{
		// the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;
		//initial variable
		int carry=0;
		String num3="";  // the binary value of the sum
		while(ind1>=0 ||  ind2>=0 || carry!=0) // loop until all digits are processed
		{
			int sum=carry; // previous carry
			if(ind1>=0){ // if num1 has a digit to add
				sum += (num1.number.charAt(ind1)=='1')? 1:0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if(ind2>=0){ // if num2 has a digit to add
				sum += (num2.number.charAt(ind2)=='1')? 1:0; // convert the digit to int and add it to sum
				ind2--; //update ind2
			}
			carry=sum/2; // the new carry
			sum=sum%2;  // the resultant digit
			num3 =( (sum==0)? "0":"1")+num3; //convert sum to string and append it to num3
		}
		Binary result=new Binary(num3);  // create a binary object with the calculated value.
		return result;
		
	}

	//Adding the method to perform bitwise OR to two binary values
	public static Binary or(Binary num1, Binary num2){

		//Using similar structure as the add method that was provided
		//the index of the first digit of each number
		int ind1=num1.number.length()-1;
		int ind2=num2.number.length()-1;

		//the binary value after applying the bitwise logical OR
		//Initialzing empty string for result
		String num3="";

		while(ind1 >= 0 || ind2 >= 0){
			int currentBit1 = 0;
			int currentBit2 = 0;

			if(ind1 >= 0){
				//Using the conditional operator (condition ? value if true : value if false)
				//instead of if-else statements
				currentBit1 = (num1.number.charAt(ind1) == '1') ? 1 : 0;
				ind1--;
			}

			if(ind2 >= 0){
				//Using the conditional operator (condition ? value if true : value if false)
				//instead of if-else statements
				currentBit2 = (num2.number.charAt(ind2) == '1') ? 1 : 0;
				ind2--;
			}

			//Using the conditional operator (condition ? value if true : value if false)
			//instead of if-else statements
			int result = (currentBit1 == 1 || currentBit2 == 1) ? 1 : 0;

			//Converting the result to a string and storing it in the empty num3 string created earlier
			//Using the conditional operator (condition ? value if true : value if false)
			//instead of if-else statements
			num3 = (result == 0 ? "0" : "1") + num3;
		}

		//Returning a Binary object with the bitwise OR value 
		return new Binary(num3);
	}

	//Using the same structure and mostly the same code to perform bitwise AND 
	//as I did for bitwise OR
	public static Binary and(Binary num1, Binary num2){

		 int ind1 = num1.number.length() - 1;
   		 int ind2 = num2.number.length() - 1;

		String num3 = "";

		while(ind1 >= 0 || ind2 >=0){
			int currentBit1 = 0;
			int currentBit2 = 0;
		
			if(ind1 >= 0){
				//Using the conditional operator (condition ? value if true : value if false)
				//instead of if-else statements
				currentBit1 = (num1.number.charAt(ind1) == '1') ? 1 : 0;
				ind1--;
			}

			if(ind2 >= 0){
				//Using the conditional operator (condition ? value if true : value if false)
				//instead of if-else statements
				currentBit2 = (num2.number.charAt(ind2) == '1') ? 1 : 0;
				ind2--;
			}

			//Using the conditional operator (condition ? value if true : value if false)
			//instead of if-else statements
			int result = (currentBit1 == 1 && currentBit2 == 1) ? 1 : 0; //Main difference between bitwise OR method
			                                                             // and bitwise AND method

			//Converting the result to a string and storing it in the empty num3 string created earlier
			//Using the conditional operator (condition ? value if true : value if false)
			//instead of if-else statements
			num3 = (result == 0 ? "0" : "1") + num3;
		}

		//Returning a Binary object with the bitwise AND value 
		return new Binary(num3);

	}

	public static Binary multiply(Binary num1, Binary num2){
		
		//result will store the result of the multiplication of the 
		//two binary values
		Binary result = new Binary("0");

		//starting at the LSB
		//shift stores the number of left shifts
		int shift = 0;

		//Using a for loop to iterate through num2 from the LSB to the MSB
		for(int i = num2.number.length()- 1; i >= 0; i--){
			//Adding when the current bit is equal to 1
			if(num2.number.charAt(i) == '1'){
				//Shifting num1 by appending zeros
				String adjustMult = num1.number;
				for (int j = 0; j < shift; j++){
					adjustMult += "0";
			}
			//Using the previous add method to add to the result
			result = Binary.add(result, new Binary(adjustMult));

		}

		//Moving to the next bit position
		shift++;
	}
		//Returning the multiplication result
		return result;
	}

	
}	

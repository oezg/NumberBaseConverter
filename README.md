# Number Base Converter

In daily life, we mostly use the decimal numeral system, but still, there are many other ways to represent numbers. For example, when working with computers, you'll probably deal with binary, octal, and hexadecimal numbers. It's really nice to have a tool that can help you easily and correctly convert numbers from one system to another.

If the user needs to restart the program each time after converting just one number, it is very inconvenient. I fixed that and made my program prompt the user for more numbers until the user is ready to quit.

Also, I added the reverse conversion: from binary, octal, and hexadecimal systems to decimal. Converting from octal, hexadecimal, or any other system to decimal works almost the same way as with binary numbers, only the base is different.

Ideally, users want to convert numbers in different bases, not only from or to the decimal system. I added support for converting numbers from any given source base to any target base. As there are 26 Latin letters and 10 digits, the maximum base is 26 + 10 = 36. So, the target and source base will be between 2 and 36.

Also, it might be more convenient for users if the program didn't ask for the base before each conversion and instead remembered the previously input base. This way, the users will have to do much less typing when they need to convert a bunch of numbers from base A to base B.

To convert a number from the source base to the target base, I first convert it to the decimal system and then convert the decimal number to the target base.

Furthermore, fractional numbers can also be converted from one base to another. To convert a fractional number from one base to another, I split the number into two parts: integer and fractional. Subsequently I convert each part from their base to decimal independently and then convert them (once again, separately) to the target base. Finally, I combine both parts and get the final result!

The most challenging part is converting the decimal fractional part to the target base. Since I already know how to convert fractions from decimal to binary, it is not a problem for me! This is my algorithm for converting fractions from decimal to any base, M:
1. Remove the dot and everything before the dot. (Converting whole numbers has a different algorithm)
2. Count the number of digits to the right of the dot, save the count to n.
3. Multiply by M 
4. Divide by the nth power of 10 (Integer division)
5. Write the result of the integer division
6. Take the remainder by the nth power of 10
7a. If the remainder is zero, the conversion is finished
7b. Else go back to step 3.

Input numbers might be larger than one would expect, so I use BigInteger or BigDecimal instead of Long or Double to avoid errors.

My program has a two-level menu:

- On the first level, the user sees the following prompt: Enter two numbers in format: {source base} {target base} (To quit type /exit). Then, they input two numbers separated by a single space: source base and target base. The user also has the option to use the /exit command to quit the program.
- On the second level, the user sees another prompt: Enter number in base {user source base} to convert to base {user target base} (To go back type /back), and inputs the number in the source base. The program outputs the message Conversion result: followed by the number in the target base. Then, the program asks for the new number to convert from the previously provided source base to the target base. To change the bases, the user can choose the BACK command and return to the first level menu to make the necessary changes.

## Example
```Enter two numbers in format: {source base} {target base} (To quit type /exit) > 10 2
Enter number in base 10 to convert to base 2 (To go back type /back) > 11
Conversion result: 1011

Enter number in base 10 to convert to base 2 (To go back type /back) > 18
Conversion result: 10010

Enter number in base 10 to convert to base 2 (To go back type /back) > 127
Conversion result: 1111111

Enter number in base 10 to convert to base 2 (To go back type /back) > 189344562689000108753301247
Conversion result: 1001110010011111010001010100111110001111101101011010101101001001111110100010111011111111

Enter number in base 10 to convert to base 2 (To go back type /back) > /back

Enter two numbers in format: {source base} {target base} (To quit type /exit) > 36 10
Enter number in base 36 to convert to base 10 (To go back type /back) > abcde
Conversion result: 17325410

Enter number in base 36 to convert to base 10 (To go back type /back) > 13a0
Conversion result: 50904

Enter number in base 36 to convert to base 10 (To go back type /back) > az
Conversion result: 395

Enter number in base 36 to convert to base 10 (To go back type /back) > /back

Enter two numbers in format: {source base} {target base} (To quit type /exit) > 10 7
Enter number in base 10 to convert to base 7 (To go back type /back) > 0.234
Conversion result: 0.14315

Enter number in base 10 to convert to base 7 (To go back type /back) > 10.234
Conversion result: 13.14315

Enter number in base 10 to convert to base 7 (To go back type /back) > /back

Enter two numbers in format: {source base} {target base} (To quit type /exit) > 35 17
Enter number in base 35 to convert to base 17 (To go back type /back) > af.xy
Conversion result: 148.g88a8

Enter number in base 35 to convert to base 17 (To go back type /back) > aaaa.0
Conversion result: 54e36.00000

Enter number in base 35 to convert to base 17 (To go back type /back) > /back

Enter two numbers in format: {source base} {target base} (To quit type /exit) > 21 10
Enter number in base 21 to convert to base 10 (To go back type /back) > 4242
Conversion result: 38012

Enter number in base 21 to convert to base 10 (To go back type /back) > 4242.13a
Conversion result: 38012.05550

Enter number in base 21 to convert to base 10 (To go back type /back) > /back

Enter two numbers in format: {source base} {target base} (To quit type /exit) > /exit```


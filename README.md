PaymentTracker program
homework

Program that keeps a record of payments. Each payment includes a currency and an amount.
The program outputs a list of all the currency and amounts to the console once per minute. The input can
be typed into the command line, and optionally also be loaded from a file when starting up.

Sample input:

USD 1000
HKD 100
USD -100
RMB 2000
HKD 200

Sample output:

Current Balance:
USD 900
RMB 2000
HKD 300

Detailed requirements:

When program runs, a filename can be optionally specified ($file_with_payments_path). The format of the file will be one or more
lines with Currency Code Amount like in the Sample Input above, where the currency may be any
3 letter code, such as USD, HKD, RMB, NZD, GBP etc. After reading the file program writes the initial balance to the console.
The user can then enter more lines into the console by
typing a currency and amount and pressing enter. Once per minute, the output showing the net amounts of each
currency is displayed. If the net amount is 0, that currency is not displayed.

Program expects only Integer values as amounts.
Doesn't matter if the currency values are upper or lower case, the program transforms all the values to upper case.
The balance for the some currency may have a negative value.
In case of wrong parsing of the input string program returns the error message with the problematic string like:

Impossible to parse as a payment:a2c 1234

and doesn't count this string as a payment.

To quit the program, user should type 'quit' (case independent) and enter.

To build program:
 - JDK 1.8 needed;
 -  be sure that JAVA_HOME is set to the JDK 8 directory
 - type "mvn clean package"

To run program:
 - the built jar ($build_jar)is placed in /target directory
 - type "java -jar /target/${built_jar}" or "java -jar /target/${built_jar} ${file_with_payments_path}"


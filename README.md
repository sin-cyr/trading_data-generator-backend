# Java back-end application for FDM IRC Pod

<br/>

<div align="center">
  <img src="https://blogs.kent.ac.uk/soc-employability/files/2018/08/fdm-logos.png"/>
</div>

## Paired front-end GitLab Project
  - https://git.fdmgroup.com/irc-sfrt/trading_data_generator_frontend

## Paired database GitLab Project
  - https://git.fdmgroup.com/irc-sfrt/trading-datagenerator-db

## Current state (stable - unrefactored)
Able to recieve http requests from the frontend project (linked above)
Able to execute requested queries on database (linked above)
Able to return result sets to frontend project

## Useful resources
* Introduction to JPA
  - https://www.tutorialspoint.com/jpa/jpa_introduction.htm
* Introduction to MVC
  - https://www.tutorialspoint.com/mvc_framework/mvc_framework_introduction.htm
* Debugging with Eclipse
  - https://www.tutorialspoint.com/eclipse/eclipse_debugging_program.htm
* Debugging with IntelliJ
  -  https://www.tutorialspoint.com/intellij_idea/intellij_idea_debugging.htm

<br/>

## Setup
<b> To get the application running locally: </b>

<b> Using Eclipse: </b>
* Download Eclipse at 
  - https://www.eclipse.org/downloads/

<b> Using IntelliJ: </b>
* Download IntelliJ at
  - https://www.jetbrains.com/idea/

## Testing

<b> Testing with Eclipse: <b>
* Once you have written one or two test methods, run your JUnit test case. 

* There are two ways to do this. One way is to click the Run button in the top toolbar (it looks like a green "Play" symbol). 

* A menu will drop down; choose to run the class as a JUnit Test.

* The other way is to right-click your JUnit test case class and choose Run As → JUnit Test.

* A new pane will appear showing the test results for each method. 

* You should see a green bar if all of the tests passed, or a red bar if any of the tests failed. 

* If any tests fail, you can view the details about the failure by clicking on the failed test's name/icon and looking at the details in the pane below.


<b> Testing with IntelliJ<b>
* Place the caret at the test class to run all tests in that class, or at the test method, and press Ctrl+Shift+F10. 

* Alternatively, click the gutter icon next to the test class or test method.

* The gutter icon changes depending on the state of your test:

* The the Run button gutter icon marks new tests.

* The the Run test icon gutter icon marks successful tests.

* The the Rerun the Run button gutter icon marks failed tests.

* To run all tests in a folder, select this folder in the Project tool window and press Ctrl+Shift+F10 or select Run Tests in 'folder' from the context menu.

* Use the following options on the Run toolbar of the Test Runner tab:

* Click the Stop button or press Ctrl+F2 to terminate the process immediately.

* Click the Exit button to terminate the process gracefully, allowing shutdown hooks to run.


## Debugging

<b> Debugging with Eclipse </b>
## For a full debugging tutorial see "Debugging with Eclipse" under "Useful Resources"

* A Java program can be debugged simply by right clicking on the Java editor class file from Package explorer.

* Select Debug As → Java Application or use the shortcut Alt + Shift + D, J instead.

* A breakpoint is a signal that tells the debugger to temporarily suspend execution of your program at a certain point in the code.

* To define a breakpoint in your source code, right-click in the left margin in the Java editor and select Toggle Breakpoint. 

* Alternatively, you can double-click on this position.

* The Eclipse Platform helps developers debug by providing buttons in the toolbar and key binding shortcuts to control program execution.


<b> Debugging with IntelliJ</b>
## For a full debugging tutorial see "Debugging with IntelliJ" under "Useful Resources"

* There are a number of ways to start the debugger:

* You can click on the Run icon in the gutter area and select the Debug option.

* You can invoke context actions on the class or main method by using Alt+Enter and choose the Debug action.

* You can also start it from the Run menu, or by pressing Shift F9.

* A breakpoint will stop the execution of your program, so that you can analyze the state of your code.

* To set a breakpoint on a line of code, click in the gutter area or use the shortcut Ctrl+F8 ( Win/Linux) or ⌘ F8 (macOS).

* The debug window displays important information when your application suspends execution on a breakpoint, like frames, threads, console window, step action icons, variables pane, and much more.

* If you close the Debug Window by mistake, you can always reopen it using the shortcut Alt+5 ( Win/Linux) or ⌘ 5 (macOS). 

* You can also access it using the ‘Search everywhere’ feature (shortcut: Shift+Shift), using Find Action (shortcut: Ctrl+Shift+A for Win/Linux and ⇧ ⌘ A for macOS), and by searching for ‘Debug’.


<b> When it seems all hope is lost and the bug has won: </b>

* Go onto Google
* Type in <em>`[Every keyword related to your problem such as error messages] Stack Overflow`<em/> or <em>`[Every keyword related to your problem such as error messages] Reddit`<em/>
* Copy/paste the top rated answer or the top rated comment of the top rated answer
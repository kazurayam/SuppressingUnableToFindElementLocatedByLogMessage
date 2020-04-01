# Suppressing Unable To Find Element Located By Log Message

This project is a small Katalon Studio project for demonstration purose.
You can download the zip from the [Releases page](https://github.com/kazurayam/SuppressingUnableToFindElementLocatedByLogMessage/releases) and open it with your local Katalon Studio.

This project was developed using Katalon Studio ver7.2.1.

This project was developed to propose a solution to an discussion raised in the Katalon User Furum

- https://forum.katalon.com/t/suppressing-unable-to-find-element-located-by-log-message/41227/

# Problem to solve

When you run [Test Cases/TC2](https://github.com/kazurayam/SuppressingUnableToFindElementLocatedByLogMessage/blob/master/Scripts/TC2%20verifyElementPresent%20failed%20to%20find%20a%20button/Script1585703719500.groovy), you will see the following messages in the Console:

```
2020-04-01 10:55:28.585 DEBUG fyElementPresent failed to find a button - 2: navigateToUrl("https://katalon-demo-cura.herokuapp.com/")
2020-04-01 10:55:32.748 DEBUG fyElementPresent failed to find a button - 3: verifyElementPresent(findTestObject("Object Repository/Page_CURA Healthcare Service/a_no_such_button"), 5, OPTIONAL)
2020-04-01 10:55:40.701 WARN  c.k.k.core.keyword.internal.KeywordMain  - com.kms.katalon.core.webui.exception.WebElementNotFoundException: Web element with id: 'Object Repository/Page_CURA Healthcare Service/a_no_such_button' located by 'By.xpath: id("btn-no-such-button")' not found (Root cause: com.kms.katalon.core.exception.StepFailedException: com.kms.katalon.core.webui.exception.WebElementNotFoundException: Web element with id: 'Object Repository/Page_CURA Healthcare Service/a_no_such_button' located by 'By.xpath: id("btn-no-such-button")' not found
	at com.kms.katalon.core.webui.keyword.internal.WebUIKeywordMain.stepFailed(WebUIKeywordMain.groovy:64)
	at com.kms.katalon.core.webui.keyword.builtin.VerifyElementPresentKeyword$_verifyElementPresent_closure1.doCall(VerifyElementPresentKeyword.groovy:85)
	at com.kms.katalon.core.webui.keyword.builtin.VerifyElementPresentKeyword$_verifyElementPresent_closure1.call(VerifyElementPresentKeyword.groovy)
	at com.kms.katalon.core.webui.keyword.internal.WebUIKeywordMain.runKeyword(WebUIKeywordMain.groovy:20)
	at com.kms.katalon.core.webui.keyword.builtin.VerifyElementPresentKeyword.verifyElementPresent(VerifyElementPresentKeyword.groovy:92)
	at com.kms.katalon.core.webui.keyword.builtin.VerifyElementPresentKeyword.execute(VerifyElementPresentKeyword.groovy:68)
	at com.kms.katalon.core.keyword.internal.KeywordExecutor.executeKeywordForPlatform(KeywordExecutor.groovy:72)
	at com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords.verifyElementPresent(WebUiBuiltInKeywords.groovy:1418)
	at com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords$verifyElementPresent$1.call(Unknown Source)
	at TC2 verifyElementPresent failed to find a button.run(TC2 verifyElementPresent failed to find a button:11)
	at com.kms.katalon.core.main.ScriptEngine.run(ScriptEngine.java:194)
	at com.kms.katalon.core.main.ScriptEngine.runScriptAsRawText(ScriptEngine.java:119)
	at com.kms.katalon.core.main.TestCaseExecutor.runScript(TestCaseExecutor.java:337)
	at com.kms.katalon.core.main.TestCaseExecutor.doExecute(TestCaseExecutor.java:328)
	at com.kms.katalon.core.main.TestCaseExecutor.processExecutionPhase(TestCaseExecutor.java:307)
	at com.kms.katalon.core.main.TestCaseExecutor.accessMainPhase(TestCaseExecutor.java:299)
	at com.kms.katalon.core.main.TestCaseExecutor.execute(TestCaseExecutor.java:233)
	at com.kms.katalon.core.main.TestCaseMain.runTestCase(TestCaseMain.java:114)
	at com.kms.katalon.core.main.TestCaseMain.runTestCase(TestCaseMain.java:105)
	at com.kms.katalon.core.main.TestCaseMain$runTestCase$0.call(Unknown Source)
	at TempTestCase1585706119073.run(TempTestCase1585706119073.groovy:23)
)
2020-04-01 10:55:40.771 DEBUG fyElementPresent failed to find a button - 4: closeBrowser()
2020-04-01 10:55:41.006 INFO  c.k.katalon.core.main.TestCaseExecutor   - END Test Cases/TC2 verifyElementPresent failed to find a button
```

The originator of the forum discussion wants to suppress this warnings because he can accept element's absense.

# Solution

As described in [View and Customize Execution Log / Logs configuration](https://docs.katalon.com/katalon-studio/docs/working-with-execution-log.html#customize-console-log) in Katalon Document, by editing `Include/config/log.properties` file, you can configure the log level for Katalon classes. You can make Katalon classes less verbose.

# Description

## Demo

Try running [Test Cases/TC3](Scripts/TC3 waitForElementPresent, log level is tuned to make it less verbose/Script1585706100932.groovy). You will see following far-less verbose message in the Console.

```
2020-04-01 11:11:13.961 DEBUG g level is tuned to make it less verbose - 2: navigateToUrl("https://katalon-demo-cura.herokuapp.com/")
2020-04-01 11:11:18.176 DEBUG g level is tuned to make it less verbose - 3: waitForElementPresent(findTestObject("Object Repository/Page_CURA Healthcare Service/a_no_such_button"), 5, OPTIONAL)
2020-04-01 11:11:23.573 DEBUG g level is tuned to make it less verbose - 4: closeBrowser()
```

Please find [Include/config/log.properties](Include/config/log.properties). In there I added 2 lines to configure log level of Katalon classes.

```
logging.level.com.kms.katalon.core.webui.common.WebUiCommonHelper=WARN
logging.level.com.kms.katalon.core.webui.keyword.builtin.WaitForElementPresentKeyword=ERROR
```

These 2 lines drastically changed the verbosity of the console messages.

## But how to write log.properties? Which FQDN to be tuned?

Try commenting out the 2 lines in the log.properties file, and run TC3. You will see the following noisy message:

```
2020-04-01 11:17:04.215 DEBUG g level is tuned to make it less verbose - 2: navigateToUrl("https://katalon-demo-cura.herokuapp.com/")
2020-04-01 11:17:07.428 DEBUG g level is tuned to make it less verbose - 3: waitForElementPresent(findTestObject("Object Repository/Page_CURA Healthcare Service/a_no_such_button"), 5, OPTIONAL)
2020-04-01 11:17:12.834 INFO  c.k.k.c.webui.common.WebUiCommonHelper   - Unable to find the element located by 'By.xpath: id("btn-no-such-button")'. Please recheck the objects properties to make sure the desired element is located. 
2020-04-01 11:17:12.850 WARN  k.k.c.w.k.b.WaitForElementPresentKeyword - Object 'Object Repository/Page_CURA Healthcare Service/a_no_such_button' is not present after 5 second(s)
2020-04-01 11:17:12.853 DEBUG g level is tuned to make it less verbose - 4: closeBrowser()
```

Here I found 2 names:
- `c.k.k.c.webui.common.WebUiCommonHelper`
- `k.k.c.w.k.b.WaitForElementPresentKeyword`

Well, `c.k.k.c` and `k.k.c.w.k.b` must be some mystified abbreviation. By looking at the [source](https://github.com/katalon-studio/katalon-studio-testing-framework) I know the FQDN of Katalon classes should be
- `com.kms.katalon.core.webui.common.WebUiCommonHelper`
- `com.kms.katalon.core.webui.keyword.builtin.WaitForElementPresentKeyword`

So I changed the `log.properties` to assign higher log levels to these 2 classes, to let them quiet.

## why `waitForElementPresent` instead of `verifyElementPresent`?

I personally prefer `verifyElementPreset` keyword behave as is because I use it frequently and I use it in a situation where I do NOT accept element absense.

I personally do use `waitForElementPresent` keyword less frequently. And I think that the name `wait for ...` is better describing a situation where I DO accept element absense.



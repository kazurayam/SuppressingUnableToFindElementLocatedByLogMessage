import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

WebUI.openBrowser('')

WebUI.navigateToUrl('https://katalon-demo-cura.herokuapp.com/')

WebUI.verifyElementPresent(
	findTestObject('Object Repository/Page_CURA Healthcare Service/a_Make Appointment'),
	5, FailureHandling.OPTIONAL)

WebUI.closeBrowser()



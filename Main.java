package wsc;

import java.net.URL;

import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.LoginResult;
import com.sforce.soap.enterprise.SessionHeader;
import com.sforce.soap.enterprise.SforceServiceLocator;
import com.sforce.soap.enterprise.SoapBindingStub;
import com.sforce.soap.schemas._class.QuoteXMLWebservice.QuoteXMLWebserviceBindingStub;
import com.sforce.soap.schemas._class.QuoteXMLWebservice.QuoteXMLWebserviceServiceLocator;

public class Main {

	static final String USERNAME = "cpq529@apttusu.com9";
	static final String PASSWORD = "Apttustrain14";
	static EnterpriseConnection connection;

	public static void main(String[] args) throws Exception {



		SoapBindingStub bind = (SoapBindingStub) new SforceServiceLocator().getSoap();
		LoginResult lr = bind.login(USERNAME, PASSWORD);

		SessionHeader sh = new SessionHeader();

		sh.setSessionId(lr.getSessionId());



		QuoteXMLWebserviceServiceLocator locator = new QuoteXMLWebserviceServiceLocator();

		URL url = new URL(locator.getQuoteXMLWebserviceAddress());

		QuoteXMLWebserviceBindingStub stub = new QuoteXMLWebserviceBindingStub(url, locator);


		stub.setHeader(locator.getQuoteXMLWebserviceAddress(), "SessionHeader", sh);

		String quoteXmlOutput = stub.retrieveQuoteXML("a0c1a000000lO29AAE");
		String orderXmlOutput = stub.retrieveOrderXML("a3S1a0000004PU5EAM");

		String salesUpdateResult = stub.updateSalesDocumentNumber("a0c1a000000lO29AAE", "12345");

		String orderUpdateResult = stub.updateOrderNumber("a0c1a000000lO29AAE", "67890");

		System.out.println("Quote XML Output:");
		System.out.println(quoteXmlOutput);
		
		System.out.println("Order XML Output:");
		System.out.println(orderXmlOutput);

		System.out.println("salesUpdateResult:");
		System.out.println(salesUpdateResult);

		System.out.println("orderUpdateResult:");
		System.out.println(orderUpdateResult);

	}



}
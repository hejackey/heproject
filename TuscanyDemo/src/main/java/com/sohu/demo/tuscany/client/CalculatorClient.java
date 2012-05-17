package com.sohu.demo.tuscany.client;

import org.apache.tuscany.sca.host.embedded.SCADomain;
import org.apache.tuscany.sca.node.SCAClient;
import org.apache.tuscany.sca.node.SCANode;
import org.apache.tuscany.sca.node.SCANodeFactory;


import com.sohu.demo.tuscany.service.CalculatorService;


public class CalculatorClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*SCADomain scaDomain = SCADomain.newInstance("Calculator.composite");
        CalculatorService calculatorService = scaDomain.getService(CalculatorService.class, "CalculatorServiceComponent");

        System.out.println("3 + 2=" + calculatorService.add(3, 2));

        scaDomain.close();*/
        SCANodeFactory factory = SCANodeFactory.newInstance();
        SCANode node = factory.createSCANodeFromClassLoader("Calculator.composite", CalculatorClient.class.getClassLoader());
        node.start();
        
        CalculatorService calculatorService = ((SCAClient)node).getService(CalculatorService.class, "CalculatorServiceComponent");
        
        // Calculate
        System.out.println("3 + 2=" + calculatorService.add(3, 2));
     /*   System.out.println("3 - 2=" + calculatorService.substract(3, 2));
        System.out.println("3 * 2=" + calculatorService.multiply(3, 2));
        System.out.println("3 / 2=" + calculatorService.divide(3, 2));
*/
        node.stop();
	}

}

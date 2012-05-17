package com.sohu.demo.tuscany.service.impl;

import com.sohu.demo.tuscany.service.AddService;
import com.sohu.demo.tuscany.service.CalculatorService;
import com.sohu.demo.tuscany.service.DivideService;
import com.sohu.demo.tuscany.service.SubstractService;

public class CalculatorServiceImpl implements CalculatorService {
	private AddService addService;
    private SubstractService subtractService;
    private MultiplyService multiplyService;
    private DivideService divideService;
    
	public double add(double d1, double d2) {
		return addService.add(d1, d2);
	}

	public double substract(double d1, double d2) {
		return subtractService.substract(d1, d2);
	}

	public double multiply(double d1, double d2) {
		return multiplyService.multiply(d1, d2);
	}

	public double divide(double d1, double d2) {
		return divideService.divide(d1, d2);
	}

	public AddService getAddService() {
		return addService;
	}

	public void setAddService(AddService addService) {
		this.addService = addService;
	}

	public SubstractService getSubtractService() {
		return subtractService;
	}

	public void setSubtractService(SubstractService subtractService) {
		this.subtractService = subtractService;
	}

	public MultiplyService getMultiplyService() {
		return multiplyService;
	}

	public void setMultiplyService(MultiplyService multiplyService) {
		this.multiplyService = multiplyService;
	}

	public DivideService getDivideService() {
		return divideService;
	}

	public void setDivideService(DivideService divideService) {
		this.divideService = divideService;
	}

}

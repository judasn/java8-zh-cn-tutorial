package com.youmeek.java.sample.main.interfaces;


public class NewInterfaceFeatureImpl implements NewInterfaceFeature {


	@Override
	public void oldMethod() {
		System.out.println("--------------------------------接口 oldMethod 方法");
	}

	public static void main(String[] args) {
		NewInterfaceFeature newInterfaceFeature = new NewInterfaceFeatureImpl();
		newInterfaceFeature.oldMethod();
		newInterfaceFeature.newDefaultMethod();
		NewInterfaceFeature.newStaticMethod();
	}
	
}

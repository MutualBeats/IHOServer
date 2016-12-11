package test;

public class Test {
	
	public static void main(String[] args) {
		RMIHelperCopyright helper = RMIHelperCopyright.getInstance();
		System.out.println(helper.address);
		helper.run();
		
		helper.off();
		helper.run();
		helper.off();
	}
	
}

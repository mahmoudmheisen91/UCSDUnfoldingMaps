
public class ArrayLocation {
	private double coords[]; // it is ok because main is here not in different class/file
	
	public ArrayLocation(double[] coords) {
		this.coords = coords;
	}
	
	public static void main(String[] args) {
		double array[] = {5, 0.0};
		ArrayLocation loc = new ArrayLocation(array);
		
		array[0] = -12.2;
		array[1] = -10;
		
		System.out.println(loc.coords[0]); // will print -12.2
		
	}
}

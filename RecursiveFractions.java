public interface RecursiveFractions {
	int[][] initialFareySequence();

	int[] prepend(int x, int[] y); // creates {x, y[0], y[1], ..., y[n]}

	long[] prepend(long x, long[] y); // creates {x, y[0], y[1], ..., y[n]}

	int dec(int n); // decrement: n-1

	int div(int n, int d); // int division: n/d

	int mod(int n, int d); // modulo: n%d

	double div(double n, double d); // double division: n/d

	double sub(double n, double d); // double subtraction: n-d
}
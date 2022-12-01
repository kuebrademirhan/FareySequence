import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class RecursiveFractionsPublicTest {
	// ========== SYSTEM ==========
	protected static final String EX_NAME_FareySequence = "FareySequence";
	protected static final String CLASS_NAME_FareySequence = "FareySequence";
	protected static final String METHOD_NAME_computeFareySequence = "computeFareySequence";
	// -----
	protected static final String EX_NAME_RegularContinuedFraction_convertRationalNumber = "RegularContinuedFraction.convertRationalNumber";
	protected static final String EX_NAME_RegularContinuedFraction_convertPseudoIrrationalNumber = "RegularContinuedFraction.convertPseudoIrrationalNumber";
	protected static final String CLASS_NAME_RegularContinuedFraction = "RegularContinuedFraction";
	protected static final String METHOD_NAME_convertRationalNumber = "convertRationalNumber";
	protected static final String METHOD_NAME_convertPseudoIrrationalNumber = "convertPseudoIrrationalNumber";
	// --------------------
	// ========== TEST-DATA ==========
	private static final Random RND = new Random(4711_0815_666L);

	// ========== PUBLIC TEST ==========
	@Test(timeout = 3666)
	public void pubTest__FareySequence__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		int[][] expectedF6 = {{0, 1}, {1, 6}, {1, 5}, {1, 4}, {1, 3}, {2, 5}, {1, 2}, {3, 5}, {2, 3}, {3, 4}, {4, 5}, {5, 6}, {1, 1}};
		check_FareySequence(6, expectedF6);
		int[][] expectedF7 = {{0, 1}, {1, 7}, {1, 6}, {1, 5}, {1, 4}, {2, 7}, {1, 3}, {2, 5}, {3, 7}, {1, 2}, {4, 7}, {3, 5}, {2, 3}, {5, 7}, {3, 4}, {4, 5}, {5, 6}, {6, 7}, {1, 1}};
		check_FareySequence(7, expectedF7);
		for (int pass = 0; pass < 42; pass++) {
			check_FareySequence(1 + RND.nextInt(42), null);
		}
	}

	// ----------------------------------------
	@Test(timeout = 2666)
	public void pubTest__RegularContinuedFraction__convertRationalNumber__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		check_RegularContinuedFraction_convertRationalNumber(77708431, 2640858, new int[]{29, 2, 2, 1, 5, 1, 4, 1, 1, 2, 1, 6, 1, 10, 2, 2, 3});
		check_RegularContinuedFraction_convertRationalNumber(17, 10, new int[]{1, 1, 2, 3});
		for (int pass = 0; pass < 42; pass++) {
			int n = RND.nextInt(4711_0815), d = RND.nextInt(4711_0815), ggt = ggT(n, d);
			check_RegularContinuedFraction_convertRationalNumber(n / ggt, d / ggt, null);
		}
	}

	// ----------------------------------------
	@Test(timeout = 2666)
	public void pubTest__RegularContinuedFraction__convertPseudoIrrationalNumber__RECURSION_CHECK__THIS_TEST_IS_VERY_IMPORTANT__IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		// https://de.wikipedia.org/wiki/Kreiszahl#Kettenbruchentwicklung
		check_RegularContinuedFraction_convertPseudoIrrationalNumber(Math.PI, 13, new long[]{3, 7, 15, 1, 292, 1, 1, 1, 2, 1, 3, 1, 14});
		// https://de.wikipedia.org/wiki/Eulersche_Zahl#Kettenbruchentwicklungen
		check_RegularContinuedFraction_convertPseudoIrrationalNumber(Math.E, 20, new long[]{2, 1, 2, 1, 1, 4, 1, 1, 6, 1, 1, 8, 1, 1, 10, 1, 1, 12, 1, 1});
		for (int pass = 0; pass < 42; pass++) {
			double x = RND.nextDouble();
			int l = 42 + RND.nextInt(42);
			check_RegularContinuedFraction_convertPseudoIrrationalNumber(x, l, null);
		}
	}

	// ========== HELPER ==========
	protected static void check_FareySequence(int n, int[][] expected) {
		String msg = CLASS_NAME_FareySequence + "." + METHOD_NAME_computeFareySequence + "(..., " + n + ") ";
		BigBrother bb = new BigBrother();
		int[][] actual = FareySequence.computeFareySequence(bb, n);
		assertEquals(msg + "ILLEGALLY USED ARITHMETIC HELPER OPERATIONS.", 0, bb.arithmeticOperationsCallCounter);
		assertEquals(msg + "USED WRONG NUMBER OF CALLS TO HELPER METHOD dec.", n - 1, bb.decCallCounter);
		assertNotNull(msg + "returned null.", actual);
		assertEquals(msg + "returned array with wrong length.", cardinality(n), actual.length);
		for (int i = 0; i < actual.length; i++) {
			assertNotNull(msg + "returned entry null at index [" + i + "].", actual[i]);
			assertEquals(msg + "returned sub-array with wrong length at index [" + i + "].", 2, actual[i].length);
			assertTrue(msg + "returned wrong entry (numerator is negative) at index [" + i + "]: (" + actual[i][0] + "/" + actual[i][1] + ")", 0 <= actual[i][0]);
			assertTrue(msg + "returned wrong entry (numerator too big) at index [" + i + "]: (" + actual[i][0] + "/" + actual[i][1] + ")", actual[i][0] <= n);
			assertTrue(msg + "returned wrong entry (denominator is not positive) at index [" + i + "]: (" + actual[i][0] + "/" + actual[i][1] + ")", 0 < actual[i][1]);
			assertTrue(msg + "returned wrong entry (denominator too big) at index [" + i + "]: (" + actual[i][0] + "/" + actual[i][1] + ")", actual[i][1] <= n);
			assertEquals(msg + "returned wrong entry (fraction not reduced) at index [" + i + "]: (" + actual[i][0] + "/" + actual[i][1] + ")", 1, ggT(actual[i][0], actual[i][1]));
			assertTrue(msg + "returned wrong entry (fraction not between 0 and 1) at index [" + i + "]: (" + actual[i][0] + "/" + actual[i][1] + ")", actual[i][0] <= actual[i][1]);
			if (i < actual.length - 1) {
				assertTrue(msg + "returned entries in wrong order (not ascending) at indices [" + i + "] vs. [" + (i + 1) + "].", actual[i][0] * actual[i + 1][1] < actual[i + 1][0] * actual[i][1]);
			}
			if (expected != null) {
				assertArrayEquals(msg + "returned wrong entry at index [" + i + "].", expected[i], actual[i]);
			}
		}
		assertEquals(msg + "has wrong base case.", 1, bb.stackTraceLog.size());
		assertEquals(msg + "has wrong recursion depth.", n, bb.stackTraceLog.get(0).intValue());
	}

	protected static void check_RegularContinuedFraction_convertRationalNumber(int n, int d, int[] expected) {
		String msg = CLASS_NAME_RegularContinuedFraction + "." + METHOD_NAME_convertRationalNumber + "(..., " + n + ", " + d + ") hat ";
		BigBrother bb = new BigBrother();
		int[] actual = RegularContinuedFraction.convertRationalNumber(bb, n, d);
		assertNotNull(msg + "returned null.", actual);
		assertNotEquals(msg + "returned empty array.", 0, actual.length);
		assertNotEquals(msg + "returned 0 as last entry (denominator 0 triggers division-by-zero).", 0, actual[actual.length - 1]);
		int actZ = 1, actN = 0;
		for (int i = actual.length - 1; i >= 0; i--) {
			actZ = actN + actual[i] * (actN = actZ);
		}
		assertEquals(msg + "returned wrong regular continued fraction (does not generate initial numerator).", n, actZ);
		assertEquals(msg + "returned wrong regular continued fraction (does not generate initial denominator).", d, actN);
		if (expected != null) {
			assertArrayEquals(msg + "returned wrong regular continued fraction.", expected, actual);
			assertEquals(msg + "has wrong base case.", expected.length - 1, bb.stackTraceLog.size());
			assertEquals(msg + "has wrong recursion depth.", expected.length - 1, bb.stackTraceLog.get(0).intValue());
		}
	}

	protected static void check_RegularContinuedFraction_convertPseudoIrrationalNumber(double x, int l, long[] expected) {
		String msg = CLASS_NAME_RegularContinuedFraction + "." + METHOD_NAME_convertPseudoIrrationalNumber + "(..., " + x + ", " + l + ") hat ";
		BigBrother bb = new BigBrother();
		long[] actual = RegularContinuedFraction.convertPseudoIrrationalNumber(bb, x, l);
		assertNotNull(msg + "returned null.", actual);
		assertNotEquals(msg + "returned empty array.", 0, actual.length);
		assertNotEquals(msg + "returned 0 as last entry (denominator 0 triggers division-by-zero).", 0, actual[actual.length - 1]);
		if (expected != null) {
			assertArrayEquals(msg + "returned wrong regular continued fraction.", expected, actual);
			assertEquals(msg + "has wrong base case.", expected.length - 1, bb.stackTraceLog.size());
			assertEquals(msg + "has wrong recursion depth.", expected.length - 1, bb.stackTraceLog.get(0).intValue());
		} else {
			double actX = Double.MAX_VALUE;
			for (int i = actual.length - 1; i >= 0; i--) {
				actX = actual[i] + 1 / actX;
			}
			assertEquals(msg + "returned wrong regular continued fraction (does not even approximately generate initial value).", x, actX, x * 1e-13);
		}
	}

	// ========== Big Brother ==========
	private static final class BigBrother implements RecursiveFractions {
		private final LinkedList<Integer> stackTraceLog = new LinkedList<>();
		private long decCallCounter = 0;
		private long arithmeticOperationsCallCounter = 0;

		@Override
		public int[][] initialFareySequence() {
			log(CLASS_NAME_FareySequence, METHOD_NAME_computeFareySequence);
			return new int[][]{{0, 1}, {1, 1}};
		}

		@Override
		public int[] prepend(int x, int[] y) {
			log(CLASS_NAME_RegularContinuedFraction, METHOD_NAME_convertRationalNumber);
			int[] z = new int[y.length + 1];
			z[0] = x;
			System.arraycopy(y, 0, z, 1, y.length);
			return z;
		}

		@Override
		public long[] prepend(long x, long[] y) {
			log(CLASS_NAME_RegularContinuedFraction, METHOD_NAME_convertPseudoIrrationalNumber);
			long[] z = new long[y.length + 1];
			z[0] = x;
			System.arraycopy(y, 0, z, 1, y.length);
			return z;
		}

		@Override
		public int dec(int n) {
			decCallCounter++;
			return n - 1;
		}

		@Override
		public int div(int n, int d) {
			arithmeticOperationsCallCounter++;
			return n / d;
		}

		@Override
		public int mod(int n, int d) {
			arithmeticOperationsCallCounter++;
			return n % d;
		}

		@Override
		public double div(double n, double d) {
			arithmeticOperationsCallCounter++;
			return n / d;
		}

		@Override
		public double sub(double n, double d) {
			arithmeticOperationsCallCounter++;
			return n - d;
		}

		private void log(String className, String methodeName) {
			int stackDepth = 0;
			StackTraceElement[] st = Thread.currentThread().getStackTrace();
			for (StackTraceElement ste : st) {
				if (ste.getClassName().equals(className)) {
					if (ste.getMethodName().equals(methodeName)) {
						stackDepth++;
					}
				}
			}
			stackTraceLog.add(stackDepth);
		}
	}

	protected static int ggT(int a, int b) {
		return (b == 0) ? a : ggT(b, a % b);
	}

	private static int phi(int n) {
		int phi = 0;
		for (int i = 1; i <= n; i++) {
			if (ggT(n, i) == 1) {
				phi++;
			}
		}
		return phi;
	}

	private static int cardinality(int n) {
		return n <= 1 ? 2 : cardinality(n - 1) + phi(n);
	}
}
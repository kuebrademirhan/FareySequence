public class RegularContinuedFraction {
    public static int[] convertRationalNumber(RecursiveFractions rf,int n,int d){
        double r=rf.div(n,(double)d);
            if (rf.sub(r , rf.div(n,d)) == 0) {
                int[] temp = new int[]{rf.div(n, d)};
                return temp;

            } else {
                int b=rf.div(n,d);
                double r1=rf.div(1,rf.sub(r,b));
                int[] res=convertRationalNumber(rf,d,rf.mod(n,b*d));
                //int b = (int)rf.div(n, d);
                // double r1=rf.div(1,rf.sub(rf.div((double)n,(double)d),b));
                // r1=1/r-bo=
               // return rf.prepend(b, convertRationalNumber(rf,1,(int)rf.sub(rf.div((double)n,(double)d),(double)b)));
                return rf.prepend(b,res);
            }
        }


    public static long[] convertPseudoIrrationalNumber(RecursiveFractions rf,double x,int l){
        return null;
    }
}

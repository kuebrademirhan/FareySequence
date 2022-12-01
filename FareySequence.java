public class FareySequence {
    public static int[][] computeFareySequence(RecursiveFractions rf,int n){
        int [][] temp;
        int [][] result=new int [0][0];
        int counter=0;
        if(n==1){
            return rf.initialFareySequence();


        }else{
            temp= computeFareySequence(rf, rf.dec(n));
            for(int i=0;i< temp.length;i++){
                    if(i+1<temp.length&&(temp[i][1]+temp[i+1][1])==n )
                    {
                        //rf.mod((temp[i][1]+temp[i+1][1]),(temp[i][0]+temp[i+1][0]))!=0
                        counter++;
                    }
            }
// werte nicht überschneiden
            result=new int[temp.length+counter][2];
            int j=0; // für temp.length
            for(int i=0;i<temp.length;i++){
                result[j]=temp[i];
                j++;
                if(i+1<temp.length&&(temp[i][1]+temp[i+1][1])==n) {
                    result[j]=new int[]{(temp[i][0]+temp[i+1][0]),n};
                    j++;

                }



                /**
                 *  if(i+1<temp.length&&(temp[i][1]+temp[i+1][1])==n) {
                 *                    result[i]=new int[]{temp[j][0],temp[j][1]};
                 *                    result[i+1]=new int[]{(temp[j][0]+temp[j+1][0]),n};
                 *                    result[i+2]=new int []{temp[j+1][0],temp[j+1][1]};
                 *
                 *                    i++;
                 *                    j+=1;
                 *
                 *                 }else if (j< temp.length ){
                 *                     result[i]=new int[]{temp[j][0],temp[j][1]};
                 *                     j++;
                 *                 }
                 */
            }


            }



        return result ;
    }


}

/**
 *
 * @author Ivan Kozlov
 */
public class MethodSlider {
    MergeSort mergesort = new MergeSort();
    
    public double[][] ShiftCalculator(int w, int h, int[][] p){
            double[][] pas = new double[p.length][3];
            for(int i=0;i<p.length;i++){
                pas[i][0]=p[i][0];
                pas[i][1]=p[i][1];
                pas[i][2]=0;
            }
        
            return pas;
    }
    
    
    
    public void OutputSlider(String s, int w, int h, int n_p, int[][] p){
            double [][] output = ShiftCalculator(w,h,p);
            System.out.println("placement model: "+ s);
            System.out.println("width: "+w);
            System.out.println("height: "+h);
            System.out.println("number of points: "+n_p);
            System.out.println("nubmers of labels: "+n_p);
            for(int i=0;i<n_p;i++){
                System.out.println(output[i][0]+" "+output[i][1]+" "+output[i][2]);
            }
    }
}

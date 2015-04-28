/**
 *
 * @author Ivan Kozlov
 */
public class MethodSlider {
    
    public int[][] ShiftCalculator(int w, int h, int n_p,int[][] p){
        int[][] pas = new int[p.length][3];
        for(int i=0;i<p.length;i++){
          pas[i][0]=p[i][0];
          pas[i][1]=p[i][1];
          pas[i][2]=0;
        }
        
    return pas;}
    
    
    
    public void OutputSlider(String s, int w, int h, int n_p, int[][] p){
        System.out.println("placement model: "+ s);
            System.out.println("width: "+w);
            System.out.println("height: "+h);
            System.out.println("number of points: "+n_p);
            System.out.println("nubmers of labels: "+n_p);
            for(int i=0;i<n_p;i++){
                System.out.println(p[i][0]+" "+p[i][1]+" 0.5");
            }
    }
}

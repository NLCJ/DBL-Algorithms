/**
 *
 * @author Ivan Kozlov
 */
public class MethodSlider {
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

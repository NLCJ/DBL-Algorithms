/**
 *
 * @author Ivan Kozlov
 */
public class Method2Pos {
    MergeSort mergesort = new MergeSort();
    
    public void Output2Pos(String s, int w, int h, int n_p, int[][] p){
        System.out.println("placement model: "+ s);
            System.out.println("width: "+w);
            System.out.println("height: "+h);
            System.out.println("number of points: "+n_p);
            System.out.println("nubmers of labels: "+n_p);
            for(int i=0;i<n_p;i++){
                System.out.println(p[i][0]+" "+p[i][1]+" NE");
            }
    }
    
}

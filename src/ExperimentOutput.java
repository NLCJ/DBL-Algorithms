
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author s127333
 */
public class ExperimentOutput {
    private ArrayList<String> placementModel = new ArrayList<>();
    private ArrayList<String> test = new ArrayList<>();
    private ArrayList<String> distribution = new ArrayList<>();
    private ArrayList<Integer> numberOfPoints = new ArrayList<>();
    private ArrayList<Long> runningTime = new ArrayList<>();    
    protected static ExperimentOutput EO;
    
    public static ExperimentOutput getExperimentOutput(){
        if(EO == null){
            EO = new ExperimentOutput();
        }
        return EO;
    }
    
    public void quadTreeArrays(String testType, long totalTime){
        placementModel.add(MainReader.placement_model);
        test.add(testType);
        distribution.add("even");
        numberOfPoints.add(MainReader.points.length);
        runningTime.add(totalTime);
    }
    
    public void closeExperiment(){
        for (int i = 0; i < placementModel.size(); i++){
            System.out.println(placementModel.get(i));
            System.out.println(test.get(i));
            System.out.println(distribution.get(i));
            System.out.println(numberOfPoints.get(i));
            System.out.println(runningTime.get(i));
        }
    }
}
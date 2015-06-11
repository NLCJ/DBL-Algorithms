
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    private String currentExperiment;
    private ArrayList<String> placementModel = new ArrayList<>();
    private ArrayList<String> test = new ArrayList<>();
    private ArrayList<String> distribution = new ArrayList<>();
    private ArrayList<Integer> numberOfPoints = new ArrayList<>();
    private ArrayList<Long> runningTime = new ArrayList<>();
    private ArrayList<Integer> MaxPoints = new ArrayList<>();
    private ArrayList<Integer> MaxLevel = new ArrayList<>();
    private ArrayList<Integer> labelDimensions = new ArrayList<>();
    private ArrayList<Integer> labelsPlaced = new ArrayList<>();
    private ArrayList<Integer> mapNumber = new ArrayList<>();
    protected static ExperimentOutput EO;
    
    public static ExperimentOutput getExperimentOutput(){
        if(EO == null){
            EO = new ExperimentOutput();
        }
        return EO;
    }
    
    public void quadTreeArrays(String testType, long totalTime){
        currentExperiment = "QuadTree";
        placementModel.add(MainReader.placement_model);
        test.add(testType);
        distribution.add(MainReader.distribution);
        numberOfPoints.add(MainReader.points.length);
        runningTime.add(totalTime);
        MaxPoints.add(MainReader.MAXPOINTS);
        MaxLevel.add(MainReader.MAXLEVEL);
    }
    
    public void modelArrays(long totalTime){
        currentExperiment = MainReader.placement_model;
        placementModel.add(MainReader.placement_model);
        distribution.add(MainReader.distribution);
        labelDimensions.add(MainReader.height);
        numberOfPoints.add(MainReader.points.length);
        mapNumber.add(MainReader.mapNumber);
        runningTime.add(totalTime);
        labelsPlaced.add(MainReader.numberLabels);
    }
    
    public void closeExperiment() throws FileNotFoundException{
        PrintWriter printer;
        if (currentExperiment == "QuadTree"){
            printer = new PrintWriter("D:\\Documents\\NetBeansProjects\\Peach-is-sooo-sorry\\Experimental Data\\Results\\QuadTree.txt");
            printer.println("model testType distribution numberOfPoints runningTime MaxPoints MaxLevel");
            
            for (int i = 0; i < placementModel.size(); i++){
                printer.println(placementModel.get(i)+" "+test.get(i)+" "+distribution.get(i)+" "+numberOfPoints.get(i)+" "+runningTime.get(i)+" "+MaxPoints.get(i)+" "+MaxLevel.get(i));
                //System.out.println(placementModel.get(i)+" "+test.get(i)+" "+distribution.get(i)+" "+numberOfPoints.get(i)+" "+runningTime.get(i)+" "+MaxPoints.get(i)+" "+MaxLevel.get(i));
            }
            printer.close();
        }
        
        else if (currentExperiment == "1slider" || currentExperiment == "2pos" || currentExperiment == "4pos"){
            printer = new PrintWriter("D:\\Documents\\NetBeansProjects\\Peach-is-sooo-sorry\\Experimental Data\\Results\\"+currentExperiment+".txt");
            printer.println("model distribution labelDimension numberOfPoints mapNumber runningTime labelsPlaced");
            
            for (int j = 0; j < placementModel.size(); j++){
                printer.println(placementModel.get(j)+" "+distribution.get(j)+" "+labelDimensions.get(j)+" "+numberOfPoints.get(j)+" "+mapNumber.get(j)+" "+runningTime.get(j)+" "+labelsPlaced.get(j));
            }
        }
        
        else{
            System.out.println("You have either not performed a valid test, or did not save your data in the arrays.");
        }
    }
}
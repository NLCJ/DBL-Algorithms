
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Janice Conquet
 */
public class QuadTree {
    //max tree level
    //max points in one node
    //where the current level is stored
    public int level;
    //list of points
    public List points;
    //nodes of the QuadTree
    public QuadTree[] node;
    //the boundaries of the quadtree's node
    public int xmin, xmax, ymin, ymax;
    
    public QuadTree(int lvl, int minx, int maxx, int miny, int maxy){
        level = lvl; //the level of the node
        points = new ArrayList(); //new list for points on this node
        node = new QuadTree[4]; //the 4 children NW NE SW SE
        xmin = minx;
        xmax = maxx;
        ymin = miny;
        ymax = maxy;
    }
    
    public void clear(){
        points.clear();//clear the list of points for this node
        
        //for each child check if the child is empty (equals to null)
        for(int i = 0; i< node.length; i++){
            //if the child node at i is not null 
            //call the clear method on the child node[i] (recursion) and set node[i] to null
            if(node[i] != null){
                node[i].clear();
                node[i] = null;
            }
        }
    }
    
    public void split(){
        //calculate the width and height of the child nodes
        int width = (xmax - xmin)/2;
        int height = (ymax - ymin)/2;
        
        //create a new QuadTree object for each child 
        node[0] = new QuadTree(level + 1, xmin, xmin + width, ymin + height, ymax); //the NW child
        node[1] = new QuadTree(level + 1, xmin + width, xmax, ymin + height, ymax); //the NE child
        node[2] = new QuadTree(level + 1, xmin, xmin + width, ymin, ymin + height); //the SW child
        node[3] = new QuadTree(level + 1, xmin + width, xmax, ymin, ymin + height); //the SE child
    }
    
    public int getIndex(Point p){
        //calculate the width and height of the child nodes
        int width = (xmax - xmin)/2;
        int height = (ymax - ymin)/2;
        
        String pos = p.getPosition();
        
        if(pos.equals("NW")){
            
        }
        
        return -1;
    }
    
    public void insert(){
        
    }
    
    public List retrieve(){
        return null;
    }
}


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
    public int MAXLEVEL = 5;
    //max points in one node
    public int MAXPOINTS = 5;
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
        //top and bot boolean to see if a label is in the top or bottom child nodes
        boolean top = false;
        boolean bot = false;
        //the position of the point (NW, NE, SW, SE, slider)
        String pos = p.getPosition();
        
        //-----top or bottom part
        
        //if the pos is NW or NE 
        if(pos.equals("NW") || pos.equals("NE") || pos.equals("slider")){
            //check if the Y is bigger than the node height and the Y+height is bigger than the node height
            //if so then it is within the top part of the child nodes
            if(p.getY() > height && p.getY()+p.getHeight() > height){
                top = true;
            }
            //else check if the Y is less than the node height and Y+height is less than the node height
            //if so then it is within the bottom part of the child nodes
            else if(p.getY() < height && p.getY()+p.getHeight() < height){
                bot = true;
            }
        }
        
        //if the pos is SW or SE 
        if(pos.equals("SW") || pos.equals("SE") ){
            //check if the Y is bigger than the node height and the Y-height is bigger than the node height
            //if so then it is within the top part of the child nodes
            if(p.getY() > height && p.getY()-p.getHeight() > height){
                top = true;
            }
            //else check if the Y is less than the node height and Y-height is less than the node height
            //if so then it is within the bottom part of the child nodes
            else if(p.getY() < height && p.getY()-p.getHeight() < height){
                bot = true;
            }
        }
        
        //-------right or left part
        
        //if the position is NW or SW
        if(pos.equals("NW") || pos.equals("SW")){
            //check if X is less than the node width and X-width is less than the node width
            if(p.getX() < width && p.getX()-p.getWidth() < width){
                //if top is true then the label is in the top left child node and thus has index 1
                if(top)
                    return 1;
                //if bot is true then the label is in the bottom left child node and thus has index 3
                if(bot)
                    return 3;
            }
            //check if X is bigger than the node width and X-width is bigger than the node width
            if(p.getX() > width && p.getX()-p.getWidth() > width){
                //if top is true then the label is in the top right child node and thus has index 2
                if(top)
                    return 2;
                //if bot is true then the label is in the bottom right child node and thus has index 4
                if(bot)
                    return 4;
            }
        }
        //if the position is NE or SE
        if(pos.equals("NE") || pos.equals("SE")){
            //check id X is less than node width and X+width is less than the node width
            if(p.getX() < width && p.getX()+p.getWidth() < width){
                //if top is true then the label is in the top left child node and thus has index 1
                if(top)
                    return 1;
                //if bot is true then the label is in the bottom left child node and thus has index 3
                if(bot)
                    return 3;
            }
            //check if X is bigger than node width and X+width is bigger than node width 
            if(p.getX() > width && p.getX()+p.getWidth() > width){
                //if top is true then the label is in the top right child node and thus has index 2
                if(top)
                    return 2;
                //if bot is true then the label is in the top right child node and thus has index 4
                if(bot)
                    return 4;
            }
        }
        
        if(pos.equals("slider")){
            //check if X is less than node width and X+slider is less than node width
            if(p.getX() < width && p.getX()+p.getSlider() < width){
                //if top is true then the label is in the top left child node and thus has index 1
                if(top)
                    return 1;
                //if bot is true then the label is in the bottom left child node and thus has index 3
                if(bot)
                    return 3;
            }
            //check if X is bigger than node width and X+slider is bigger than node width 
            if(p.getX() > width && p.getX()+p.getSlider() > width){
                //if top is true then the label is in the top right child node and thus has index 2
                if(top)
                    return 2;
                //if bot is true then the label is in the top right child node and thus has index 4
                if(bot)
                    return 4;
            }
        }
        //if label doesn't fit in any child node, return -1
        return -1;
    }
    
    public void insert(Point p){
        //check if the parent doesn't have a child
        //if so get the index of Point p
        if(node[0] != null){
            int index = getIndex(p);
            //if index of Point p is not -1 
            //then insert Point p in the child with the corresponding index and return
            if(index != -1){
                node[index].insert(p);
                return;
            }
        }
        //add Point p to the list of points
        points.add(p);
        
        //if the points size is bigger than the maximum allowed points and less than the max allowed levels
        if(points.size() > MAXPOINTS && level < MAXLEVEL){
            //if there are no child nodes, create them by calling the split method
            if(node[0] == null){
                split();
            }
            //for each point in the points list
            for(int i = 0; i < points.size(); i++){
                //get the point from the list and get the index of that point
                Point pp = (Point)points.get(i);
                int index = getIndex(pp);
                //if the index of Point pp is not -1
                if(index != -1){
                    //remove the Point pp from the points list 
                    //and insert it in the child node with the corresponding index
                    pp = (Point)points.remove(i);
                    node[index].insert(pp);
                }
            }
        }
    }
    
    public List retrieve(List potentialCollisionPoints, Point p){
        //get the index of Point p
        int index = getIndex(p);
        //if index is not -1 and there exists a child node
        //then get the list of potential collisions from the child node with the corresponding index
        if(index != -1 && node[0] != null){
            node[index].retrieve(potentialCollisionPoints,p);
        }
        //add all the points in the list of points to the potential collisions list and return the list
        //(in this specific node all the points can potentially collide with Point p)
        potentialCollisionPoints.addAll(points);
        for (int i = 0; i < potentialCollisionPoints.size(); i++) {
            Point j = (Point)potentialCollisionPoints.get(i);
            System.out.println(j.getX());
        }
        return potentialCollisionPoints;
    }
}

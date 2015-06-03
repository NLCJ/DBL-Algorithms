import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Janice Conquet
 */
public class QuadTree {

    //max tree level
    public int MAXLEVEL = 10;
    //max labels in one node
    public int MAXPOINTS = 5;
    //where the current level is stored
    public int level;
    //list of points
    public List points;
    //nodes of the QuadTree
    public QuadTree[] node;
    //the boundaries of the quadtree's node
    public int xmin, xmax, ymin, ymax;

    public QuadTree(int lvl, int minx, int maxx, int miny, int maxy) {
        level = lvl; //the level of the node
        points = new ArrayList<>(); //new list for points on this node
        node = new QuadTree[4]; //the 4 children NW NE SW SE
        xmin = minx; //the minimum x of the quadtree frame
        xmax = maxx; //the maximum x of the quadtree frame
        ymin = miny; //the minimum y of the quadtree frame
        ymax = maxy; //the maximum y of the quadtree frame
    }

    public void clear() {
        points.clear();//clear the list of points for this node

        //for each child check if the child is empty (equals to null)
        for (int i = 0; i < node.length; i++) {
            //if the child node at i is not null 
            //call the clear method on the child node[i] (recursion) and set node[i] to null
            if (node[i] != null) {
                node[i].clear();
                node[i] = null;
            }
        }
    }

    public void split() {
        //calculate the width and height of the child nodes
        int width = (xmax - xmin) / 2;
        int height = (ymax - ymin) / 2;

        //create a new QuadTree object for each child 
        node[0] = new QuadTree(level + 1, xmin, xmin + width, ymin + height, ymax); //the NW child
        node[1] = new QuadTree(level + 1, xmin + width, xmax, ymin + height, ymax); //the NE child
        node[2] = new QuadTree(level + 1, xmin, xmin + width, ymin, ymin + height); //the SW child
        node[3] = new QuadTree(level + 1, xmin + width, xmax, ymin, ymin + height); //the SE child
    }

    public int getIndex(Point p) {
        //calculate the width and height of the child nodes
        int width = (xmax - xmin) / 2;
        int height = (ymax - ymin) / 2;

        //top and bot boolean to see if a label is in the top or bottom child nodes
        boolean top = false;
        boolean bot = false;
        //the position of the point (NW, NE, SW, SE, slider)
        //Placement pos = p.getPlacement();
        //the placement model of the set of points
        String model = MainReader.placement_model;

        //-----top or bottom part
        if(model.equals("4pos")){
            
            //check if the Y is bigger than the node height and the Y+height is bigger than the node height
            //if so then it is within the top part of the child nodes
            if (p.getY() - MainReader.height > height && p.getY() + MainReader.height > height) {
                top = true;
            } //else check if the Y is less than the node height and Y+height is less than the node height
            //if so then it is within the bottom part of the child nodes
            else if (p.getY() - MainReader.height < height && p.getY() + MainReader.height < height) {
                bot = true;
            }
        }
        if(model.equals("2pos") || model.equals("1slider")){
            if(p.getY() > height && p.getY() + MainReader.height > height){
                top = true;
            }
            else if(p.getY() < height && p.getY() + MainReader.height < height){
                bot = true;
            }
        }
        
        /*
        //if the pos is NW or NE or slider
        if (pos == Placement.NE || pos == Placement.NW || pos == Placement.Slider) {
            //check if the Y is bigger than the node height and the Y+height is bigger than the node height
            //if so then it is within the top part of the child nodes
            if ((l.getAnchor().getY() > height) && (l.getAnchor().getY() + MainReader.height > height)) {
                top = true;
            } //else check if the Y is less than the node height and Y+height is less than the node height
            //if so then it is within the bottom part of the child nodes
            else if ((l.getAnchor().getY() < height) && (l.getAnchor().getY() + MainReader.height < height)) {
                bot = true;
            }
        }

        //if the pos is SW or SE 
        if (pos == Placement.SW || pos == Placement.SE) {
            //check if the Y is bigger than the node height and the Y-height is bigger than the node height
            //if so then it is within the top part of the child nodes
            if (l.getAnchor().getY() > height && l.getAnchor().getY() - MainReader.height > height) {
                top = true;
            } //else check if the Y is less than the node height and Y-height is less than the node height
            //if so then it is within the bottom part of the child nodes
            else if (l.getAnchor().getY() < height && l.getAnchor().getY() - MainReader.height < height) {
                bot = true;
            }
        }*/

        //-------right or left part
        if(p.getX() + MainReader.width < width && p.getX() - MainReader.width < width){
            if(top){
               return 0; 
            }
            if(bot){
                return 2;
            }
        }
        
        if(p.getX() + MainReader.width > width && p.getX() - MainReader.width > width){
            if(top){
                return 1;
            }
            if(bot){
                return 3;
            }
        }
        
        /*//if the position is NW or SW
        if (pos == Placement.NW || pos == Placement.SW) {
            //check if X is less than the node width and X-width is less than the node width
            if ((l.getAnchor().getX() < width) && (l.getAnchor().getX() - MainReader.width < width)) {
                //if top is true then the label is in the top left child node and thus has index 0
                if (top) {
                    return 0;
                }
                //if bot is true then the label is in the bottom left child node and thus has index 2
                if (bot) {
                    return 2;
                }
            }
            //check if X is bigger than the node width and X-width is bigger than the node width
            if ((l.getAnchor().getX() > width) && (l.getAnchor().getX() - MainReader.width > width)) {
                //if top is true then the label is in the top right child node and thus has index 1
                if (top) {
                    return 1;
                }
                //if bot is true then the label is in the bottom right child node and thus has index 3
                if (bot) {
                    return 3;
                }
            }
        }
        //if the position is NE or SE
        if (pos == Placement.NE || pos == Placement.SE) {
            //check id X is less than node width and X+width is less than the node width
            if ((l.getAnchor().getX() < width) && (l.getAnchor().getX() + MainReader.width < width)) {
                //if top is true then the label is in the top left child node and thus has index 0
                if (top) {
                    return 0;
                }
                //if bot is true then the label is in the bottom left child node and thus has index 2
                if (bot) {
                    return 2;
                }
            }
            //check if X is bigger than node width and X+width is bigger than node width 
            if ((l.getAnchor().getX() > width) && (l.getAnchor().getX() + MainReader.width > width)) {
                //if top is true then the label is in the top right child node and thus has index 1
                if (top) {
                    return 1;
                }
                //if bot is true then the label is in the top right child node and thus has index 3
                if (bot) {
                    return 3;
                }
            }
        }

        if (pos == Placement.Slider) {
            //check if X is less than node width and X+slider is less than node width
            if (l.getAnchor().getX() < width && l.getAnchor().getX() + l.getShift() * MainReader.width < width) {
                //TODO check if this is correct, p.getSlider() seems to be a/b * width here
                //if top is true then the label is in the top left child node and thus has index 0
                if (top) {
                    return 0;
                }
                //if bot is true then the label is in the bottom left child node and thus has index 2
                if (bot) {
                    return 2;
                }
            }
            //check if X is bigger than node width and X+slider is bigger than node width 
            if (l.getAnchor().getX() > width && l.getAnchor().getX() + l.getShift() * MainReader.width > width) {
                //TODO check if this is correct, p.getSlider() seems to be a/b * width here
                //if top is true then the label is in the top right child node and thus has index 1
                if (top) {
                    return 1;
                }
                //if bot is true then the label is in the top right child node and thus has index 4
                if (bot) {
                    return 3;
                }
            }
        }*/
        //if point p doesn't fit in any child node, return -1
        return -1;
    }

    public void insert(Point p) {
        //check if the parent doesn't have a child
        //if so get the index of point p
        if (node[0] != null) {
            int index = getIndex(p);
            //if index of point p is not -1 
            //then insert point p in the child with the corresponding index and return (recursion)
            if (index != -1) {
                node[index].insert(p);
                return;
            }
        }
        //add point p to the list of points
        points.add(p);

        //if the points' size is bigger than the maximum allowed points and less than the max allowed levels
        if (points.size() > MAXPOINTS && level < MAXLEVEL) {
            //if there are no child nodes, create them by calling the split method
            if (node[0] == null) {
                split();
            }
            //while i is less than the labels size
            int i = 0;
            while (i < points.size()) {
                //get the point from the list and get the index of that point
                Point p2 = (Point) points.get(i);
                int index = getIndex(p2);

                //if the index of Label lp is not -1
                if (index != -1) {
                    //remove the point at i from the points list 
                    //and insert it in the child node with the corresponding index
                    p2 = (Point) points.remove(i);
                    node[index].insert(p2);
                } //else add 1 to i
                else {
                    i++;
                }
            }
        }
    }

    public List retrieve(List<Point> potentialCollisionPoints, Point p) {
        //get the index of point p
        int index = getIndex(p);
        //System.out.println("point: " + p + " index: " + index);
        //if index is not -1 and there exists a child node
        //then get the list of potential collisions from the child node with the corresponding index (recursion)
        if (index != -1 && node[0] != null) {
            node[index].retrieve(potentialCollisionPoints, p);
        }
        
        //System.out.println("node[0]: " + node[0]);
        
        if(index == -1 && node[0] != null){
            //System.out.println("In -1 if statement");
            List<Point> pc = new ArrayList<>();
            pc = pointsInChild(pc,node);
            //for(int j = 0; j < pc.size(); j++){
            //    System.out.println("point in child: " + pc.get(j));
            //}
            potentialCollisionPoints.addAll(pc);
            //for(int j = 0; j < potentialCollisionPoints.size(); j++){
            //    System.out.println("point in pot: " + potentialCollisionPoints.get(j));
            //}
        }
        
        //System.out.println("point: " + p + " index: " + index);
        //for(int i = 0; i < points.size(); i++){
        //    System.out.println(points.get(i));
        //}
        //System.out.println();
        
        /*if(p.getX() == 914 && p.getY() == 9303){
            for(int i = 0; i < points.size(); i++){
                Point temp = (Point) points.get(i);
                if(temp.getX() == p.getX())
                    System.out.println(points.get(i));
            }
            //System.out.println();
        }*/
        
        //add all the points in the list of points to the potential collisions list and return the list
        //(in this node all the points' labels in the array points can potentially collide with point p's label)
        potentialCollisionPoints.addAll(points);
        return potentialCollisionPoints;
    }
    
    public List pointsInChild(List pc, QuadTree[] child){
        for(int i = 0; i < child.length; i++){
            if(child[i].node[0] != null){
                child[i].pointsInChild(pc, child[i].node);
            }
            pc.addAll(child[i].points);
        }
        
        return pc;
    }
}
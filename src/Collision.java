
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
public class Collision {
    public List collisions ;
    
    public Collision(){
       collisions = new ArrayList(); 
    }
    
    public boolean collide(Point p, Point pp){
        String posp = p.getPosition();
        String pospp = pp.getPosition();
        int xp = p.getX();
        int xpp = pp.getX();
        int yp = p.getY();
        int ypp = p.getY();
        
        //------ p pos = NW
        
        if(posp.equals("NW")){
            if(pospp.equals("NW")){
                if(xp > xpp){
                    int gap = (xp - p.getWidth()) - xpp;
                    if(gap < 0){
                        if(yp > ypp){
                            int ygap = yp- (ypp + pp.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                        if(yp < ypp){
                            int ygap = ypp - (yp + p.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                    }
                }
                if(xp < xpp){
                    int gap = (xpp - pp.getWidth()) - xp;
                    if(gap < 0){
                        if(yp > ypp){
                            int ygap = yp - (ypp + pp.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                        if(yp < ypp){
                            int ygap = ypp - (yp + p.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                    }
                }
            }
            if(pospp.equals("NE")){
                if(xp > xpp){
                    int gap = (xp - p.getWidth()) - (xpp + pp.getWidth());
                    if(gap < 0){
                        if(yp > ypp){
                            int ygap = yp- (ypp + pp.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                        if(yp < ypp){
                            int ygap = ypp - (yp + p.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                    }
                }
                
                if(xp < xpp){
                    int gap = xpp - xp;
                    if(gap < 0){
                        if(yp > ypp){
                            int ygap = yp - (ypp + pp.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                        if(yp > ypp){
                            int ygap = ypp - (yp + p.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                    }
                }
            }
            if(pospp.equals("SW")){
                if(xp > xpp){
                    int gap = (xp - p.getWidth()) - xpp;
                    if(gap < 0){
                        if(yp > ypp){
                            int ygap = yp- ypp;
                            if(ygap < 0){
                                return true;
                            }
                        }
                        if(yp < ypp){
                            int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                    }
                }
                if(xp < xpp){
                    int gap = (xpp - pp.getWidth()) - xp;
                    if(gap < 0){
                        if(yp > ypp){
                            int ygap = yp- ypp;
                            if(ygap < 0){
                                return true;
                            }
                        }
                        if(yp < ypp){
                            int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                    }
                }
            }
            if(pospp.equals("SE")){
                if(xp > xpp){
                    int gap = (xp - p.getWidth()) - (xpp + pp.getWidth());
                    if(yp > ypp){
                            int ygap = yp- ypp;
                            if(ygap < 0){
                                return true;
                            }
                        }
                    if(yp < ypp){
                        int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                        if(ygap < 0){
                            return true;
                        }
                    }
                }
                
                if(xp < xpp){
                    int gap = xpp - xp;
                    if(gap < 0){
                        if(yp > ypp){
                            int ygap = yp - ypp;
                            if(ygap < 0){
                                return true;
                            }
                        }
                        if(yp > ypp){
                            int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        
        //------ p pos = NE
        
        if(posp.equals("NE")){
            if(pospp.equals("NW")){
                if(xp > xpp){
                    int gap = xp - xpp;
                    if(gap < 0){
                        if(yp > ypp){
                            int ygap = yp- (ypp + pp.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                        if(yp < ypp){
                            int ygap = ypp - (yp + p.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                    }
                }
                if(xp < xpp){
                    int gap = (xpp - pp.getWidth()) - (xp + p.getWidth());
                    if(gap < 0){
                        if(yp > ypp){
                            int ygap = yp - (ypp + pp.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                        if(yp < ypp){
                            int ygap = ypp - (yp + p.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                    }
                }
            }
            if(pospp.equals("NE")){
                if(xp > xpp){
                    int gap = xp - (xpp + pp.getWidth());
                    if(yp > ypp){
                            int ygap = yp - (ypp + pp.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                    }
                    if(yp < ypp){
                        int ygap = ypp - (yp + p.getHeight());
                        if(ygap < 0){
                            return true;
                        }
                    }
                }
                
                if(xp < xpp){
                    int gap = xpp - (xp + p.getWidth());
                    if(gap < 0){
                        if(yp > ypp){
                            int ygap = yp - (ypp + pp.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                        if(yp > ypp){
                            int ygap = ypp - (yp + p.getHeight());
                            if(ygap < 0){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        
        
        
        return false;
    }
    
    public List allCollisions(List potential, Point p){
        
        for(int i = 0; i < potential.size(); i++){
            Point pp = (Point)potential.get(i);
            
            if(collide(p, pp)){
                collisions.add(pp);
            }
        }
        return null;
    }
}

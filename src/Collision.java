
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Point> collisions;

    public Collision() {
        collisions = new ArrayList<>();
    }

    public boolean collide(Point p, Point pp) {
        String posp = p.getPosition();
        String pospp = pp.getPosition();
        int xp = p.getX();
        int xpp = pp.getX();
        int yp = p.getY();
        int ypp = p.getY();

        if (p.equals(pp)) {
            return false;
        }

        //------ p pos = NW
        if (Arrays.asList(posp).contains("NW")) {
            if (Arrays.asList(pospp).contains("NW")) {
                if (xp >= xpp) {
                    int gap = (xp - p.getWidth()) - xpp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
                if (xp < xpp) {
                    int gap = (xpp - pp.getWidth()) - xp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (Arrays.asList(pospp).contains("NE")) {
                if (xp >= xpp) {
                    int gap = (xp - p.getWidth()) - (xpp + pp.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }

                if (xp < xpp) {
                    int gap = xpp - xp;
                    if (gap < 0) {
                        if (yp > ypp) {
                            int ygap = yp - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp > ypp) {
                            int ygap = ypp - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (pospp.equals("SW")) {
                if (xp >= xpp) {
                    int gap = (xp - p.getWidth()) - xpp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
                if (xp < xpp) {
                    int gap = (xpp - pp.getWidth()) - xp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (pospp.equals("SE")) {
                if (xp >= xpp) {
                    int gap = (xp - p.getWidth()) - (xpp + pp.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }

                if (xp < xpp) {
                    int gap = xpp - xp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp > ypp) {
                            int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        //------ p pos = NE
        if (Arrays.asList(posp).contains("NE")) {
            if (Arrays.asList(pospp).contains("NW")) {
                if (xp >= xpp) {
                    int gap = xp - xpp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
                if (xp < xpp) {
                    int gap = (xpp - pp.getWidth()) - (xp + p.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (Arrays.asList(pospp).contains("NE")) {
                if (xp >= xpp) {
                    int gap = xp - (xpp + pp.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }

                if (xp < xpp) {
                    int gap = xpp - (xp + p.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp > ypp) {
                            int ygap = ypp - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (pospp.equals("SW")) {
                if (xp >= xpp) {
                    int gap = xp - xpp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
                if (xp < xpp) {
                    int gap = (xpp - pp.getWidth()) - (xp + p.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (pospp.equals("SE")) {
                if (xp >= xpp) {
                    int gap = xp - (xpp + pp.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }

                if (xp < xpp) {
                    int gap = xpp - (xp + p.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = yp - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp > ypp) {
                            int ygap = (ypp - pp.getHeight()) - (yp + p.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        //------ p pos = SW
        if (posp.equals("SW")) {
            if (pospp.equals("NW")) {
                if (xp >= xpp) {
                    int gap = (xp - p.getWidth()) - xpp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
                if (xp < xpp) {
                    int gap = (xpp - pp.getWidth()) - xp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (pospp.equals("NE")) {
                if (xp >= xpp) {
                    int gap = (xp - p.getWidth()) - (xpp + pp.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }

                if (xp < xpp) {
                    int gap = xpp - xp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (pospp.equals("SW")) {
                if (xp >= xpp) {
                    int gap = (xp - p.getWidth()) - xpp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
                if (xp < xpp) {
                    int gap = (xpp - pp.getWidth()) - xp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (pospp.equals("SE")) {
                if (xp >= xpp) {
                    int gap = (xp - p.getWidth()) - (xpp + pp.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }

                if (xp < xpp) {
                    int gap = xpp - xp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        //------ p pos = SE
        if (posp.equals("SE")) {
            if (pospp.equals("NW")) {
                if (xp >= xpp) {
                    int gap = xp - xpp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
                if (xp < xpp) {
                    int gap = (xpp - pp.getWidth()) - (xp + p.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (pospp.equals("NE")) {
                if (xp >= xpp) {
                    int gap = xp - (xpp + pp.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = ypp - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }

                if (xp < xpp) {
                    int gap = xpp - (xp + p.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - (ypp + pp.getHeight());
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp > ypp) {
                            int ygap = ypp - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (pospp.equals("SW")) {
                if (xp >= xpp) {
                    int gap = xp - xpp;
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
                if (xp < xpp) {
                    int gap = (xpp - pp.getWidth()) - (xp + p.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
            if (pospp.equals("SE")) {
                if (xp >= xpp) {
                    int gap = xp - (xpp + pp.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }

                if (xp < xpp) {
                    int gap = xpp - (xp + p.getWidth());
                    if (gap < 0) {
                        if (yp >= ypp) {
                            int ygap = (yp - p.getHeight()) - ypp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                        if (yp < ypp) {
                            int ygap = (ypp - pp.getHeight()) - yp;
                            if (ygap < 0) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        //-------p pos = slider 
        if (posp.equals("slider")) {
            if (xp >= xpp) {
                double gap = (xp - (p.getWidth() - p.getSlider())) - (xpp + pp.getSlider());
                if (gap < 0) {
                    if (yp >= ypp) {
                        double ygap = yp - (ypp + pp.getHeight());
                        if (ygap < 0) {
                            return true;
                        }
                    }
                    if (yp < ypp) {
                        double ygap = ypp - (yp + p.getHeight());
                        if (ygap < 0) {
                            return true;
                        }
                    }
                }
            }
            if (xp < xpp) {
                double gap = (xpp - (pp.getWidth() - pp.getSlider())) - (xp + p.getSlider());
                if (gap < 0) {
                    if (yp >= ypp) {
                        double ygap = yp - (ypp + pp.getHeight());
                        if (ygap < 0) {
                            return true;
                        }
                    }
                    if (yp < ypp) {
                        double ygap = ypp - (yp + p.getHeight());
                        if (ygap < 0) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public List allCollisions(List potential, Point p) {

        for (Object potential1 : potential) {
            Point pp = (Point) potential1;
            if (collide(p, pp)) {
                collisions.add(pp);
            }
        }
//        for (int i = 0; i < collisions.size(); i++) {
//            System.out.println(collisions.get(i).getX() + " x " + collisions.get(i).getY());
//        }
        return collisions;
    }
}

import java.awt.geom.*; // for Point2D.Double
import java.util.ArrayList; // for ArrayList
import java.util.concurrent.TimeUnit;

import gpdraw.*; // for DrawingTool


public class IrregularPolygon {
    private ArrayList<Point2D.Double> myPolygon = new ArrayList<Point2D.Double>();

    // constructor
    public IrregularPolygon() {}

    // public methods
    public void add(Point2D.Double aPoint)
    {
        // TODO: Add a point to the IrregularPolygon.
        myPolygon.add(aPoint);
    }

    public double perimeter() {
        double perimeter = 0.0;
        if (myPolygon.size() < 2) return 0.0;
        
        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size()); 
            perimeter += current.distance(next);
        }
        
        return perimeter;
    }

    public double area() {
        
        if (myPolygon.size() < 3) return 0.0; 
        
        double sum1 = 0.0, sum2 = 0.0;
        for (int i = 0; i < myPolygon.size(); i++) {
            Point2D.Double current = myPolygon.get(i);
            Point2D.Double next = myPolygon.get((i + 1) % myPolygon.size());
            sum1 += current.x * next.y;
            sum2 += current.y * next.x;
        }
        
        return 0.5 * Math.abs(sum1 - sum2);
    }

    public void draw() {
        
        try {
            if (myPolygon.isEmpty()) return; 
            
            DrawingTool pen = new DrawingTool(new SketchPad(500, 500));
            Point2D.Double first = myPolygon.get(0);
            pen.up();
            pen.move(first.getX(), first.getY());
            pen.down();
            
            for (Point2D.Double point : myPolygon) {
                pen.move(point.getX(), point.getY());
            }
            pen.move(first.getX(), first.getY()); 
        } catch (java.awt.HeadlessException e) {
            System.out.println("Exception: No graphics support available.");
        }
    }
}
import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints
{
    LineSegment[] lineSegments;
    
    public FastCollinearPoints(Point[] points)
    {
        ValidateArguments(points);
        
        ArrayList<LineSegment> colinearSegments = new ArrayList<LineSegment>(); // Use array list so we don't need to manage array resizing
        
        for (int i = 0; i < points.length; i++)
        {
            Point nextPoint = points[i];
            Arrays.sort(points, nextPoint.slopeOrder());
            
            /*
            for (int j = 0; j < points.length; j++)
            {
                System.out.println(points[j].slopeTo(points[i]));
            }
            System.out.println();
            */
            
            // Create array list that keeps track of consecutive slopes
            Point first = null;
            Point last = null;
            int count = 0;
            
            for (int j = 0; j < points.length; j++)
            {
                if (i == j)
                {
                    continue;
                }
                
                if (first == null)
                {
                    first = points[j];
                }
                
                if (first.slopeTo(points[i]) == points[j].slopeTo(points[i]))
                {
                    count++;
                }
                else
                {
                    if (count >= 4)
                    {
                        colinearSegments.add(new LineSegment(first, last));
                    }
                    
                    first = points[j];
                    count = 0;
                }
                
                last = points[j];
            }
            
            // edge case
            if (count == points.length)
            {
                colinearSegments.add(new LineSegment(first, last));
            }
        }
        
        lineSegments = colinearSegments.toArray(new LineSegment[colinearSegments.size()]);
    }
    
    private void ValidateArguments(Point[] points)
    {
        if (points == null)
        {
            throw new java.lang.IllegalArgumentException("null argument");
        }
        
       // repeated points or null points
        for (int i = 0; i < points.length; i++)
        {
            if (points[i] == null)
            {
                throw new java.lang.IllegalArgumentException("null point");
            }
            
            for (int j = 0; j < points.length; j++)
            {
                if (i != j & points[i] == points[j])
                {
                    throw new java.lang.IllegalArgumentException("duplicate point");
                }
            }
        }
    }
    
    public int numberOfSegments()
    {
        return lineSegments.length;
    }
    
    public LineSegment[] segments()
    {
        return lineSegments;
    }
    
    public static void main(String[] args) 
    {
        // read the n points from a file --------------
        
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) 
        {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        //----------------------------------------------
        
        // Debug ------
        /*
        Point[] points = new Point[12];
        points[0] = new Point(0, 0);
        points[7] = new Point(1, 2);
        points[1] = new Point(1, 3);
        points[3] = new Point(1, 4);
        points[5] = new Point(1, 5);
        points[4] = new Point(1, 6);
        points[2] = new Point(1, 7);
        points[6] = new Point(1, 8);
        points[8] = new Point(1, 1);
        points[9] = new Point(2, 2);
        points[10] = new Point(3, 3);
        points[11] = new Point(4, 4);
        */
        // ------------

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) 
        {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) 
        {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}

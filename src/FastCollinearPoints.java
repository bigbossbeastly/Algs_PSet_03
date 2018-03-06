import java.util.ArrayList;
import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints
{
    private LineSegment[] lineSegments;
    
    public FastCollinearPoints(Point[] points)
    {
        ValidateArguments(points);
        Point[] pointCopy = Arrays.copyOf(points, points.length);
        ArrayList<LineSegment> colinearSegments = new ArrayList<LineSegment>(); // Use array list so we don't need to manage array resizing
        
        for (int i = 0; i < pointCopy.length; i++)
        {
            Arrays.sort(pointCopy, pointCopy[i].slopeOrder());
            ArrayList<Point> collinearPoints = new ArrayList<Point>();
            
            for (int j = 0; j < pointCopy.length; j++)
            {
                
                if (i == j)
                {
                    continue;
                }
                
                if (collinearPoints.isEmpty()) 
                {
                    collinearPoints.add(points[j]);
                } 
                else if (points[i].slopeTo(points[j - 1]) == points[i].slopeTo(points[j])) 
                {
                    collinearPoints.add(points[j]);
                } 
                else if (collinearPoints.size() > 2) 
                {
                    collinearPoints.add(points[i]);
                    Point[] finalPoints = collinearPoints.toArray(new Point[collinearPoints.size()]);
                    //Arrays.sort(finalPoints);
                    colinearSegments.add(new LineSegment(finalPoints[0], finalPoints[finalPoints.length-1]));
                    break;
                } 
                else 
                {
                    collinearPoints.clear();
                    collinearPoints.add(points[j]);
                }
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
            for (int j = 0; j < points.length; j++)
            {
                if (points[i] == null || points[j] == null)
                {
                    throw new java.lang.IllegalArgumentException("null point");
                }
                
                if (i != j && points[i].compareTo(points[j]) == 0)
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
        return Arrays.copyOf(lineSegments, lineSegments.length);
    }
    
    public static void main(String[] args) 
    {
        // read the n points from a file --------------
        /*
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) 
        {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        */
        //----------------------------------------------
        
        // Debug ------
        
        Point[] points = new Point[8];
        points[0] = new Point(1, 1);
        points[1] = new Point(2, 2);
        points[2] = new Point(3, 3);
        points[3] = new Point(4, 4);
        points[4] = new Point(5, 5);
        points[5] = new Point(6, 6);
        points[6] = new Point(7, 7);
        points[7] = new Point(8, 8);

        
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


/*

if (first == null)
{
    first = pointCopy[j];
}

if (first.slopeTo(pointCopy[i]) == pointCopy[j].slopeTo(pointCopy[i]))
{
    count++;
}

if (count >= 3)
{
    if (points[i].compareTo(first) == -1)
    {
        first = points[i];
    }
    else if (points[i].compareTo(last) == 1)
    {
        last = points[i];
    }
    
    lastSegment = new LineSegment(first, last);
}

if (first.slopeTo(pointCopy[i]) != pointCopy[j].slopeTo(pointCopy[i]) || j == pointCopy.length - 1)
{
    if (lastSegment != null)
    {
        colinearSegments.add(lastSegment);
    }
    
    first = pointCopy[j];
    count = 0;
}

last = pointCopy[j];

*/

import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints
{
    private LineSegment[] lineSegments;
    
    public BruteCollinearPoints(Point[] points)
    {
        ValidateArguments(points);
        Point[] pointCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointCopy); // Sort so start & end are easier to find
        
        ArrayList<LineSegment> colinearSegments = new ArrayList<LineSegment>(); // Use array list so we don't need to manage array resizing
        
        for (int i = 0; i < pointCopy.length - 3; i++)
        {
            for (int j = i + 1; j < pointCopy.length - 2; j++)
            {
                for (int k = j + 1; k < pointCopy.length - 1; k++)
                {
                    for (int l = k + 1; l < pointCopy.length; l++)
                    {
                        if (pointCopy[i].slopeTo(pointCopy[j]) == pointCopy[i].slopeTo(pointCopy[k]) && pointCopy[i].slopeTo(pointCopy[k]) == pointCopy[i].slopeTo(pointCopy[l]))
                        {
                            colinearSegments.add(new LineSegment(pointCopy[i], pointCopy[l]));
                        }
                    }
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
            if (points[i] == null)
            {
                throw new java.lang.IllegalArgumentException("null point");
            }
            
            for (int j = 0; j < points.length; j++)
            {
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
}

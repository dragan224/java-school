package algorithms_api;

import java.util.ArrayList;

import data_types.Point;
import data_types.Triangle;

/**
 * Klasa za racunanje Delonijeve trijangulacije n tackaga. O(n^4) vremenu!
 * Videti: https://en.wikipedia.org/wiki/Delaunay_triangulation
 */
public class Triangulation {
	
	/**
     * Racuna Deluanulajevu triangulaciju n-tacaka. https://en.wikipedia.org/wiki/Delaunay_triangulation
     *
     * @param points lista tacaka od kojih se pravi triangulacija.
     */
	public Triangulation(ArrayList<Point> points) {
		int N = points.size();
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    boolean isValidTriangle = true;
                    for (int a = 0; a < N; a++) {
                        if (a == i || a == j || a == k) continue;
                        if (isInside(points.get(a), points.get(i), points.get(j), points.get(k))) {
                           isValidTriangle = false;
                           break;
                        }
                    }
                    if (isValidTriangle) {
                    	triangles.add(new Triangle(points.get(i), points.get(j), points.get(k)));
                    }
                }
            }
        }
	}
	
	private ArrayList<Triangle> triangles = new ArrayList<>();
	
	public ArrayList<Triangle> getTriangles() {
		return triangles;
	}

	/**
     * Provarava a li je tacka P unutra kruga koji sadrzi tacke A, B i C.
     *
     * @param P tacka koju proveramo da li se nalazi u krugu
     * @param A krug je opisan ovim tackama
     * @param B krug je opisan ovim tackama
     * @param C krug je opisan ovim tackama
     * @return true/false u zavisnosti da li se tacka nalazi u krugu.
     */
	public static boolean isInside(Point P, Point A, Point B, Point C) {
		double[][] det = new double[][]{
			{A.getX(), A.getY(), A.getX()*A.getX() + A.getY()*A.getY(), 1},
			{B.getX(), B.getY(), B.getX()*B.getX() + B.getY()*B.getY(), 1},
			{C.getX(), C.getY(), C.getX()*C.getX() + C.getY()*C.getY(), 1},
			{P.getX(), P.getY(), P.getX()*P.getX() + P.getY()*P.getY(), 1}};
		
		return solveDterminant(det, 4) >= 0;
	}
	
	// racuna determinantu
	private static double solveDterminant(double A[][], int N)
    {
        double det=0;
        if(N == 1)
        {
            det = A[0][0];
        }
        else if (N == 2)
        {
            det = A[0][0]*A[1][1] - A[1][0]*A[0][1];
        }
        else
        {
            det=0;
            for(int j1=0;j1<N;j1++)
            {
                double[][] m = new double[N-1][];
                for(int k=0;k<(N-1);k++)
                {
                    m[k] = new double[N-1];
                }
                for(int i=1;i<N;i++)
                {
                    int j2=0;
                    for(int j=0;j<N;j++)
                    {
                        if(j == j1)
                            continue;
                        m[i-1][j2] = A[i][j];
                        j2++;
                    }
                }
                det += Math.pow(-1.0,1.0+j1+1.0)* A[0][j1] * solveDterminant(m, N-1);
            }
        }
        return det;
    }
 
}

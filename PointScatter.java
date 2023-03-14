/******************************************************************************
*  A Teaching GA					  Developed by Hal Stringer & Annie Wu, UCF
*  Version 2, January 18, 2004
*******************************************************************************/

import java.io.*;
import java.util.*;
import java.text.*;

public class PointScatter extends FitnessFunction{

/*******************************************************************************
*                            INSTANCE VARIABLES                                *
*******************************************************************************/

ArrayList<Double> lat_array = new ArrayList<>(Arrays.asList(33.209438, 36.068681, 32.23564, 34.07088, 40.00694, 41.82176, 39.68103, 29.63288, 
    33.94043, 46.7238, 40.09912, 39.18024, 41.66831, 38.95349, 38.02704, 30.21929, 
    44.89914, 38.99041, 42.38694, 42.29421, 44.97101, 34.36461, 38.93641, 46.85461, 
    40.82068, 39.54427, 43.13827, 40.74213, 35.08663, 40.90988, 35.90504, 47.92654, 
    39.323, 35.19599, 44.04455, 39.94934, 41.4873, 33.99288, 42.79137, 35.95164, 
    30.28522, 40.76281, 44.47374, 38.04106, 47.65435, 39.65369, 43.08027, 41.31423));

ArrayList<Double> long_array = new ArrayList<>(Arrays.asList(-87.54149, -94.17601, -110.95174, -118.44685, -105.26639, -72.24278, -75.75402, 
    -82.34901, -83.37305, -117.02044, -88.23852, -86.50935, -91.57953, -95.26309, 
    -84.50484, -92.04138, -68.66637, -76.94386, -72.52991, -83.71004, -93.23144, 
    -89.53963, -92.3297, -113.9655, -96.70048, -119.8163, -70.93238, -74.17903, 
    -106.6202, -73.12155, -79.04775, -97.07212, -82.10268, -97.44571, -123.0717, 
    -75.18964, -71.53446, -81.02675, -96.92542, -83.93088, -97.73389, -111.8368, 
    -73.19415, -78.5055, -122.308, -79.95745, -89.43096, -105.5643));



/*******************************************************************************
*                            STATIC VARIABLES                                  *
*******************************************************************************/


/*******************************************************************************
*                              CONSTRUCTORS                                    *
*******************************************************************************/

	public PointScatter(){
		name = "PointScatter Problem";
	}

/*******************************************************************************
*                                MEMBER METHODS                                *
*******************************************************************************/

//  COMPUTE A CHROMOSOME'S RAW FITNESS *************************************

	public void doRawFitness(Chromo X){
		X.rawFitness = run_all(X);
	}

    // run all function:
    
    public double run_all(Chromo X){
        return fitness(X);
    }

    public double distance(double lat1, double lat2, double lon1, double lon2){
        // convert to radians
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        // distance
        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2),2);
                
        double c = 2 * Math.asin(Math.sqrt(a));
    
        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;
    
        // calculate the result
        return(c * r);
    }
 
    public double fitness(Chromo X){
        // ArrayList<Integer> chromosome = new ArrayList<Integer>();
        // Random rand = new Random(); 
        // int upperbound = 48;
            
        // while (chromosome.size() < 48){
            // int int_random = rand.nextInt(upperbound);
            // if (chromosome.contains(int_random)) {
                // continue;
            // }else {
                // chromosome.add(int_random);
            // }

        // }
        // ArrayList<Double> distances_list = new ArrayList<Double>();
        // double distances_sum;
        double distances_sum = 0;
        double lat1 = 0;
        double lon1 = 0;
        double lat2 = 0;
        double lon2 = 0;
        for (int i = 0; i <= 47; i++){
                
            if (i != 47) {

                lat1 = lat_array.get(X.chromo.get(i));
                lon1 = long_array.get(X.chromo.get(i));
                lat2 = lat_array.get(X.chromo.get(i + 1));
                lon2 = long_array.get(X.chromo.get(i + 1));
            } else {
                lat1 = lat_array.get(X.chromo.get(i));
                lon1 = long_array.get(X.chromo.get(i));
                lat2 = lat_array.get(X.chromo.get(0));
                lon2 = long_array.get(X.chromo.get(0));
            }
            distances_sum += distance(lat1, lon1, lat2, lon2); 

        }
        return(distances_sum);
    }

 

//  PRINT OUT AN INDIVIDUAL GENE TO THE SUMMARY FILE *********************************

	public void doPrintGenes(Chromo X, FileWriter output) throws java.io.IOException{

		for (int i=0; i<Parameters.numGenes; i++){
			Hwrite.right(X.getGeneAlpha(i),11,output);
		}
		output.write("   RawFitness");
		output.write("\n        ");
		for (int i=0; i<Parameters.numGenes; i++){
			Hwrite.right(X.getPosIntGeneValue(i),11,output);
		}
		Hwrite.right((int) X.rawFitness,13,output);
		output.write("\n\n");
		return;
	}

/*******************************************************************************
*                             STATIC METHODS                                   *
*******************************************************************************/

}   // End of OneMax.java ******************************************************


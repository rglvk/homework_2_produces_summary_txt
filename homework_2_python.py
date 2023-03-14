import random
from math import radians, cos, sin, asin, sqrt
# a_chromosome = [i for i in range(48)]
# print(a_chromosome)



def make_chromosome():
    chromosome = []
    
    while len(chromosome) < 48:
        a = random.randint(0,47)
        if a in chromosome:
            continue
        else:
            chromosome.append(a)
    return chromosome # , len(a_list)

the_chromosome = make_chromosome



lat_list = [33.209438, 36.068681, 32.23564, 34.07088, 40.00694, 41.82176, 39.68103, 29.63288, 
 33.94043, 46.7238, 40.09912, 39.18024, 41.66831, 38.95349, 38.02704, 30.21929, 
 44.89914, 38.99041, 42.38694, 42.29421, 44.97101, 34.36461, 38.93641, 46.85461, 
 40.82068, 39.54427, 43.13827, 40.74213, 35.08663, 40.90988, 35.90504, 47.92654, 
 39.323, 35.19599, 44.04455, 39.94934, 41.4873, 33.99288, 42.79137, 35.95164, 
 30.28522, 40.76281, 44.47374, 38.04106, 47.65435, 39.65369, 43.08027, 41.31423]


long_list = [-87.54149, -94.17601, -110.95174, -118.44685, -105.26639, -72.24278, -75.75402, 
 -82.34901, -83.37305, -117.02044, -88.23852, -86.50935, -91.57953, -95.26309, 
 -84.50484, -92.04138, -68.66637, -76.94386, -72.52991, -83.71004, -93.23144, 
 -89.53963, -92.3297, -113.9655, -96.70048, -119.8163, -70.93238, -74.17903, 
 -106.6202, -73.12155, -79.04775, -97.07212, -82.10268, -97.44571, -123.0717, 
 -75.18964, -71.53446, -81.02675, -96.92542, -83.93088, -97.73389, -111.8368, 
 -73.19415, -78.5055, -122.308, -79.95745, -89.43096, -105.5643]

def fitness():
    chromosome = []
    
    while len(chromosome) < 48:
        a = random.randint(0,47)
        if a in chromosome:
            continue
        else:
            chromosome.append(a)
    
    distances_list = []
    for j in range(47):
        i = chromosome[j]
        k = i - 1
        lat1 = lat_list[k]
        lon1 = long_list[k]
        lat2 = lat_list[k+1]
        lon2 = long_list[k+1]
        the_distance = distance(lat1, lon1, lat2, lon2)
        distances_list.append(the_distance)

    the_sum = sum(distances_list)
    return(the_sum)
    

def distance(lat1, lat2, lon1, lon2):
     
    # The math module contains a function named
    # radians which converts from degrees to radians.
    lon1 = radians(lon1)
    lon2 = radians(lon2)
    lat1 = radians(lat1)
    lat2 = radians(lat2)
      
    # Haversine formula
    dlon = lon2 - lon1
    dlat = lat2 - lat1
    a = sin(dlat / 2)**2 + cos(lat1) * cos(lat2) * sin(dlon / 2)**2
 
    c = 2 * asin(sqrt(a))
    
    # Radius of earth in kilometers. Use 3956 for miles
    r = 6371
      
    # calculate the result
    return(c * r)

print(fitness())
Find an index of maximum occurring element with equal probability

Given an array of integers, find the most occurring element of the array and
 return any one of its indexes randomly with equal probability.
arr[] = [-1, 4, 9, 7, 7, 2, 7, 3, 0, 9, 6, 5, 7, 8, 9]

static void findRandomIndexOfMax(int arr[], int n) 
{ 
    // freq store frequency of each element in the array 
    HashMap<Integer, Integer> mp = new HashMap<Integer, Integer>(); 
    for (int i = 0; i < n; i++) 
        if(mp.containsKey(arr[i])) 
        { 
            mp.put(arr[i], mp.get(arr[i]) + 1); 
        } 
        else
        { 
            mp.put(arr[i], 1); 
        } 
  
    int max_element = Integer.MIN_VALUE; // stores max occurring element 
  
    // stores count of max occurring element 
    int max_so_far = Integer.MIN_VALUE; 
  
    // traverse each pair in map and find maximum 
    // occurring element and its frequency 
    for (Map.Entry<Integer, Integer> p : mp.entrySet())  
    { 
        if (p.getValue() > max_so_far) 
        { 
            max_so_far = p.getValue(); 
            max_element = p.getKey(); 
        } 
    } 
      
    // generate a random number between [1, max_so_far] 
    int r = (int) ((new Random().nextInt(max_so_far) % max_so_far) + 1); 
  
    // traverse array again and return index of rth 
    // occurrence of max element 
    for (int i = 0, count = 0; i < n; i++) 
    { 
        if (arr[i] == max_element) 
            count++; 
  
        // print index of rth occurrence of max element 
        if (count == r) 
        { 
            System.out.print("Element with maximum frequency present "
                +"at index " + i +"\n"); 
            break; 
        } 
    } 
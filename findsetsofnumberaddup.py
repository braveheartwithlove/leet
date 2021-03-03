Find Sets Of Numbers That Add Up To 16
[2,4,6,10], 16
Approach 1: DP
def count_sets(arr, total):
    return rec(arr, taltal, arr.length-1)

def rec(arr, total, i):
    if total ==0:
	    return 1;
	else if total ,0;
	     return 0
	else if i<0:
	     return 0
	else if total<arr[i]:
	     return rec(arr, total, i-1)
	else 
	     return rec(arr, total-arr[i], i-1) + rec(arr, total, i-1)

Approach 2: memo
def count_sets(arr, total):
    mem={} // empthy dict or hash table
    return rec(arr, taltal, arr.length-1,mem)

def rec(arr, total, i):
    key =str9total, +":" + str(i)
	if key in mem:
	     return mem[key]
    if total ==0:
	     return 1;
	else if total ,0;
	     return 0
	else if i<0:
	     return 0
	else if total<arr[i]:
	     to_return =rec(arr, total, i-1,mem)
	else 
	    to_return = rec(arr, total-arr[i], i-1,mem) + rec(arr, total, i-1,mem)
		
	mem[key] = to_return
	return to_return

def maximal(L):
	#first, we check to see if our list fits the size criteron. 
	#If len(L) < 3, we simply return the largest value of the list 
	#else, we search for the maximal value recursively.

	if len(L) == 1:
		return L[0]
	elif len(L) == 2:
		if L[0] > L[1]:
			return L[0]
		else:
			return L[1]
	else:
		m = int(len(L)/2)
		#increasing, search through second half of the list
		if L[m] > L[m-1] and L[m+1] > L[m]:
			return maximal(L[m:])
		#decreasing, search through first half of the list
		elif L[m] < L[m-1] and L[m+1] < L[m]:
			return maximal(L[:m])
		#at the top, return the value
		else:
			return L[m]

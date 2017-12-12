#1.18
def find_smallest(L):
	smallest = L[0]
	index = 0
	for i in range(1, len(L)):
		if smallest > L[i]:
			smallest = L[i]
			index = i

	return index

#1.19
def selection_sort(L):
	if len(L) == 0:
		return L
	for i in range(len(L)-1):
		j = find_smallest(L[i:]) + i
		temp = L[i]
		L[i] = L[j]
		L[j] = temp

	return L
		
#1.20
def brute_force(L):
	distance = list()
	distance_tuple = list()

	for i in range(len(L)):
		for j in range(i+1, len(L)):
			temp = (L[i][0]-L[j][0])**2 + (L[i][1]-L[j][1])**2
			temp_tuple = (temp, i, j)
			distance.append(temp)
			distance_tuple.append(temp_tuple)

	t = distance_tuple[find_smallest(distance)]

	return (L[t[1]],L[t[2]])

#1.21
def prob121():
	import numpy as np
	import time

	for k in range(1, 12):
		A = np.random.rand(2**k, 2**k)
		B = np.random.rand(2**k, 2**k)
		X = np.random.rand(2**k, 1)
		t1 = time.time()
		(A@B)@B
		t2 = time.time()
		time1 = t2 - t1
		print("(AB)X, " + str(k) +"th iteration: " + str(time1))
		A@(B@X)
		t3 = time.time()
		time2 = t3 - t2
		print("A(BX), " + str(k) +"th iteration: " + str(time2))
		print("ratio: " + str(time1/time2))

#1.22
def prob122():
	import numpy as np
	import time

	for k in range(1, 12):
		I = np.eye(2**k)
		U = np.random.rand(2**k, 1)
		V = np.random.rand(1, 2**k)
		X = np.random.rand(2**k, 1)
		t1 = time.time()
		(I + U@V)@X
		t2 = time.time()
		time1 = t2 - t1
		print("(I + UV.T)X, " + str(k) +"th iteration: " + str(time1))
		X + U@(V@X)
		t3 = time.time()
		time2 = t3 - t2
		print("X+U(V.T*X)" + str(k) +"th iteration: " + str(time2))
		print("ratio: " + str(time1/time2))


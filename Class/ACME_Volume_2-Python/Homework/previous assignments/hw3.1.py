import time

'''Took the average time taken from 1000 iterations'''
def problem3_1():

	def t_append():
		t1 = time.time()
		l = []
		for i in range(10000):
			l.append(i)
		t2 = time.time()
		return t2-t1

	total = 0
	for i in range(1000):
		total += t_append()

	app = total/1000
	print("Average time took to append:", str(app))

	def t_prepend():
		t2 = time.time()
		m = []
		for i in range(10000):
			m.insert(0,i)

		t3 = time.time()
		return t3-t2

	tot2 = 0
	for i in range(1000):
		tot2 += t_prepend()

	prep = tot2/1000
	print("Average time took to prepend:", str(prep))

	print("Average ratio:", str(prep/app))

def problem3_4():
	import numpy as np

	A = [[0,0,0,1,0,0],
		[1,0,0,0,0,0],
		[1,0,0,0,1,1],
		[0,1,1,0,0,0],
		[0,0,0,0,0,1],
		[0,0,0,1,0,0]]
	A = np.array(A)
	return A@A@A@A

def problem3_5():
	import numpy as np

	A = [[0,1,1,0,0],
		[1,0,1,1,0],
		[1,1,0,1,1],
		[0,1,1,0,1],
		[0,0,1,1,0]]
	A = np.array(A)
	return A@A@A
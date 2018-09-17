def naive_F(n):
	if n < 3:
		return 1
	else:
		return naive_F(n-1) + naive_F(n-2)

def memo_F(n, store=[1,1]):
	if n < 3:
		return 1
	else:
		memo_F(n-1, store)
		if len(store)+1 == n:
			store.append(store[len(store)-1] + store[len(store)-2])
		return store[n-1]

def iter_F(n):
	l = [1,1]
	for i in range(2,n):
		temp = l[1]
		l[1] = l[1] + l[0]
		l[0] = temp

	return l[1]

#Takes around 15 seconds
def compareF():
	import time
	from matplotlib import pyplot as plt
	ti = time.time()
	t_naive = []
	t_memo = []
	t_iter = []
	size = range(10000)
	for n in size:
		t1 = time.time()
		# After around this point, the naive method will take too long
		if n < 30:
			naive_F(n)
		t2 = time.time()
		# The memoization method reaches maximum recursion depth after around this point
		if n < 900:
			memo_F(n)
		t3 = time.time()
		iter_F(n)
		t4 = time.time()
		if n < 30:
			t_naive.append(t2-t1)
		if n < 900:
			t_memo.append(t3-t2)
		t_iter.append(t4-t3)

	plt.ion()
	plt.loglog(size[:30], t_naive, basex=2, basey=2, label="Naive recursion")
	plt.loglog(size[:900], t_memo, basex=2, basey=2, label="Memoization")
	plt.loglog(size, t_iter, basex=2, basey=2, label="Iteration")
	plt.legend(loc="upper left")
	tf = time.time()
	print("Time taken for the operation: " + str(tf-ti))

def naive_C(A, return_type="count"):
	m = []
	def min(A):
		m = -1
		for i in range(len(A)):
			if A[i] == None:
				continue
			else:
				if m == -1:
					m = A[i]
				elif A[i] < m:
					m = A[i]
		return m, A.index(m)

	def _step(A, m):

		if A == 100:
			m.append("dollar")
			return 1
		elif A == 50:
			m.append("half dollar")
			return 1
		elif A == 25:
			m.append("quarter")
			return 1
		elif A == 10:
			m.append("dime")
			return 1
		elif A == 5:
			m.append("nickel")
			return 1
		elif A == 1:
			m.append("penny")
			return 1
		else:
			l1,l2,l3,l4,l5,l6 = [],[],[],[],[],[]
			v = {0:100, 1:50, 2:25, 3:10, 4:5, 5:1}
			c1,c2,c3,c4,c5,c6 = None,None,None,None,None,None
			if A > 100:
				l1.append("dollar")
				c1 = _step(A - 100, l1)
			if A > 50:
				l2.append("half dollar")
				c2 = _step(A - 50, l2)
			if A > 25:
				l3.append("quarter")
				c3 = _step(A - 25, l3)
			if A > 10:
				l4.append("dime")
				c4 = _step(A - 10, l4)
			if A > 5:
				l5.append("nickel")
				c5 = _step(A - 5, l5)
			if A > 1:
				l6.append("penny")
				c6 = _step(A - 1, l6)
			c = min([c1,c2,c3,c4,c5,c6])
			l = [l1,l2,l3,l4,l5,l6]
			m += l[c[1]]
			return len(m)

	if return_type == "count":
		return _step(int(A*100), m)
	else:
		_step(int(A*100), m)
		return m

def memo_C(A, return_type="count"):
	m = {1:["penny"], 5:["nickel"], 10:["dime"], 25:["quarter"], 50:["half dollar"], 100:["dollar"]}
	def min(A):
		m = -1
		for i in range(len(A)):
			if A[i] == None:
				continue
			else:
				if m == -1:
					m = A[i]
				elif A[i] < m:
					m = A[i]
		return m, A.index(m)

	def _step(A, m):

		if m.get(A) != None:
			return len(m.get(A))
		else:
			v = {0:100, 1:50, 2:25, 3:10, 4:5, 5:1}
			c1,c2,c3,c4,c5,c6 = None,None,None,None,None,None
			if A > 100:
				c1 = _step(A - 100, m)
			if A > 50:
				c2 = _step(A - 50, m)
			if A > 25:
				c3 = _step(A - 25, m)
			if A > 10:
				c4 = _step(A - 10, m)
			if A > 5:
				c5 = _step(A - 5, m)
			if A > 1:
				c6 = _step(A - 1, m)
			c = min([c1,c2,c3,c4,c5,c6])
			m[A] = m.get(A-v[c[1]]) + m.get(v[c[1]])
			return len(m[A])

	if return_type == "count":
		return _step(int(A*100), m)
	else:
		_step(int(A*100), m)
		return m[int(A*100)]

def iter_C(A, return_type="count"):

	def min_l(A):
		m = A[-1]
		for i in range(len(A)):
			if len(A[i]) == 0:
				continue
			else:
				if len(m) > len(A[i]):
					m = A[i]
		return m

	A = int(A*100)
	m = {0:[]}
	for i in range(1, A+1):	
		l1,l2,l3,l4,l5,l6 = [],[],[],[],[],[]
		if i >= 100:
			l1 += (m[i-100])
			l1.append("dollar")
		if i >= 50:
			l2 += (m[i-50])
			l2.append("half dollar")
		if i >= 25:
			l3 += (m[i-25])
			l3.append("quarter")
		if i >= 10:
			l4 += (m[i-10])
			l4.append("dime")
		if i >= 5:
			l5 += (m[i-5])
			l5.append("nickel")

		l6 += m[i-1]
		l6.append("penny")

		m[i] = min_l([l1,l2,l3,l4,l5,l6])

	if return_type == 'count':
		return len(m[A])
	else:
		return m[A]

# Takes around 30 seconds
def compareC():
	import time
	from matplotlib import pyplot as plt
	ti = time.time()
	t_naive = []
	t_memo = []
	t_iter = []
	size = range(1,2000)
	for n in size:
		n = float(n)/100
		# After around this point, the naive method will take too long
		if n < 0.50:
			t0 = time.time()
			naive_C(n)
		t1 = time.time()
		memo_C(n)
		t2 = time.time()
		iter_C(n)
		t3 = time.time()
		if n <= 0.50:
			t_naive.append(t1-t0)
		t_memo.append(t2-t1)
		t_iter.append(t3-t2)

	plt.ion()
	plt.loglog(size[:50], t_naive, label="Naive recursion", basex=2, basey=2)
	plt.loglog(size, t_memo, label="Memoization", basex=2, basey=2)
	plt.loglog(size, t_iter, label="Iteration", basex=2, basey=2)
	plt.legend(loc="upper left")
	tf = time.time()
	print("Time taken for the operation: " + str(tf-ti))

def prob4_4ii(x,y):
	import numpy as np
	if type(x) == np.ndarray:
		x = x.tolist()
	if type(y) == np.ndarray:
		y = y.tolist()
	def _step(x,y):
		if len(x) == 0 or len(y) == 0:
			return 0
		else:
			l = []
			for i in range(len(x)):
				if x[i] == y[-1]:
					l.append(_step(x[:i], y[:-1]) + 1)
					l.append(_step(x[i+1:], y))
			if len(l) == 0:
				return _step(x, y[:-1])
			else:
				return np.max(l)

	return _step(x,y)

#1.14
def prob14():
	n = 2**53 + 1
	print("n = 2^53 + 1, the smallest positive integer that cannot be expressed in IEEE 754\n")
	print("n-3: " + str(float(n-3)))
	print("n-2: " + str(float(n-2)))
	print("n-1: " + str(float(n-1)))
	print("n: " + str(float(n)))
	print("n+1: " + str(float(n+1)))
	print("n+2: " + str(float(n+2)))
	print("n+3: " + str(float(n+3)))

#1.16
def prob16():
	forward = sum(1000/n for n in range(1,10**7+1))
	backward = sum(1000/n for n in range(10**7,0,-1))
	print("forward sum: " + str(forward))
	print("backward sum: " + str(backward))


def prob17():
	import numpy as np
	from matplotlib import pyplot as plt

	x = np.linspace(-3e-15, 3e-15, 1000)
	y1 = x
	y2 = (1-x) -1
	y3 = ((1-x)-1)/x
	plt.plot(x,y1,'blue')
	plt.plot(x,y2,'green')
	plt.plot(x,y3,'red')
	plt.show()
	plt.clf()


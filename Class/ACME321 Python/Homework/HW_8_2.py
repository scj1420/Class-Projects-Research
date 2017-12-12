import numpy as np
import matplotlib.pyplot as plt

def LeakageAmplitude(N, y, k):
	numer = np.sin(np.pi*(y-k))
	denom = N*np.sin((np.pi*(y-k))/N)

	return abs(numer/denom)

def prob8_14():
	N = 100
	c = np.random.uniform(-N, N, size=5)
	plt.ion()

	for y in c:
		x = np.arange(N)
		fx = [LeakageAmplitude(N, y, k) for k in x]
		plt.plot(x, fx, label='y = ' + str(y))

	plt.legend()
	plt.show()


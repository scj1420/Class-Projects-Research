import scipy.stats as st
import matplotlib.pyplot as plt
import numpy as np
from math import sqrt

def prob6_15(n=8):
	x = np.arange(0,1,0.001)
	plt.ion()
	avebeta = []
	aveunif = []
	for i in range(1000):
		randombeta = []
		randomunif = []
		for j in range(n):
			randomunif.append(np.random.uniform())
			randombeta.append(np.random.beta(1/2, 1/2))
		avebeta.append(sum(randombeta)/len(randombeta))
		aveunif.append(sum(randomunif)/len(randomunif))

	ax1 = plt.subplot(121)
	ax1.plot(x, st.beta(1/2,1/2).pdf(x))
	ax1.plot(x, st.norm(1/2,sqrt(1/8)/sqrt(n)).pdf(x), label="n = "+str(n))
	ax1.hist(avebeta, normed=True)
	ax1.set_title("Beta(1/2,1/2)")
	ax1.legend()

	ax2 = plt.subplot(122)
	ax2.plot(np.arange(-0.1,1.1,0.001), st.uniform.pdf(np.arange(-0.1,1.1,0.001)))
	ax2.plot(x, st.norm(1/2,sqrt(1/12)/sqrt(n)).pdf(x), label="n = "+str(n))
	ax2.hist(aveunif, normed=True)
	ax2.set_title("Unif([0,1]")
	ax2.legend()

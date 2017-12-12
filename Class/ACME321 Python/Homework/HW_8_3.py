"""Math 320
Seong-Eun Cho
Section 1
12/3/17
"""
import numpy as np
import matplotlib.pyplot as plt

def convolve(f, g):
	h = []
	for k in range(len(f)):
		h_k = 0
		for j in range(len(f)):
			h_k += f[k-j]*g[j]
		h.append(h_k)
	return np.array(h)

def prob8_19separateplot():
	f = np.linspace(0, 2*np.pi, 1000)
	g = np.sin(f)
	plt.ion()

	ax1 = plt.subplot(221)
	ax1.plot(f, f)
	ax1.set_title("f")
	ax2 = plt.subplot(222)
	ax2.plot(f, g)
	ax2.set_title("g")
	ax3 = plt.subplot(223)
	ax3.plot(f, convolve(f,g)/1000)
	ax3.set_title("f*g")
	ax4 = plt.subplot(224)
	ax4.plot(f, f*g)
	ax4.set_title("f•g")
	plt.show()

def prob8_19sameplot():
	f = np.linspace(0, 2*np.pi, 1000)
	g = np.sin(f)
	plt.ion()
	plt.plot(f, f, label="f")
	plt.plot(f, g, label='g')
	plt.plot(f, convolve(f,g)/1000, label='f*g')
	plt.plot(f, f*g, label='f•g')
	plt.legend()
	plt.show()
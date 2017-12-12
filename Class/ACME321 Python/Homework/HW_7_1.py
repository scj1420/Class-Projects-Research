"""Math 320
Seong-Eun Cho
Section 1
11/20/2017
"""
import numpy as np
import scipy.stats as st
import matplotlib.pyplot as plt
import math

def prob7_1():
	x = np.linspace(0,10,1000)
	k_2 = []
	k_4 = []
	k_6 = []
	for i in range(10**2):
		k_2.append(np.random.randn())
	for i in range(10**4):
		k_4.append(np.random.randn())
	for i in range(10**6):
		k_6.append(np.random.randn())

	bins1 = np.linspace(0,10,20)
	bins3 = np.linspace(0,10,100)

	k_2 = np.array(k_2)**2
	k_4 = np.array(k_4)**2
	k_6 = np.array(k_6)**2

	plt.ion()
	ax1 = plt.subplot(131)
	ax1.plot(x, st.chi2.pdf(x,1))
	ax1.hist(k_2, bins1, normed=True)
	ax1.set_title("k = 2")

	ax2 = plt.subplot(132)
	ax2.plot(x, st.chi2.pdf(x,1))
	ax2.hist(k_4, bins1, normed=True)
	ax2.set_title("k = 4")

	ax2 = plt.subplot(133)
	ax2.plot(x, st.chi2.pdf(x,1))
	ax2.hist(k_6, bins3, normed=True)
	ax2.set_title("k = 6")

	print("Monte Carlo")
	for k in [k_2, k_4, k_6]:
		print("k = " + str(len(k)))
		print("cdf:")
		for x in [0.5, 1.0, 1.5]:
			val = len(k[np.where(k<x)])/len(k)
			print("x = " + str(x) + ": " + str(val))
		mu = np.average(k)
		print("E[X] = " + str(mu))
		var = sum([(xi - mu)**2 for xi in k])/len(k)
		print("Var(X) = " + str(var))
		print()

	print("Built-in")
	print("cdf:")
	for x in [0.5, 1.0, 1.5]:
		print("x = " + str(x) + ": " + str(st.chi2.cdf(x,1)))
	print("E[X] = " + str(st.chi2.mean(1)))
	print("Var(X) = " + str(st.chi2.var(1)))

def prob7_2():
	k_2 = [(np.random.uniform(low=-1), np.random.uniform(low=-1)) for i in range(100)]
	k_4 = [(np.random.uniform(low=-1), np.random.uniform(low=-1)) for i in range(10**4)]
	k_6 = [(np.random.uniform(low=-1), np.random.uniform(low=-1)) for i in range(10**6)]

	for k in [k_2, k_4, k_6]:
		print("k = " + str(len(k)))
		count = 0
		for x in k:
			if x[0]**2 + x[1]**2 <= 1.0:
				count += 1
		print("pi = " + str(4*count/len(k)))
		mu = np.average(np.array(k))
		var = sum([(xi - mu)**2 for xi in k])/len(k)
		var = math.sqrt(var[0]**2 + var[1]**2)
		SEM = math.sqrt(var/len(k))
		print("SEM = " + str(SEM))
		print()

def prob7_3():
	X1 = [np.exp(np.cos(np.random.uniform(high=2.0)**2)) for i in range(10**5)]
	mu1 = np.average(X1)
	A1 = 2*mu1
	print("Part i)")
	print("area = " + str(A1))
	var1 = sum([(xi - mu1)**2 for xi in X1])/len(X1)
	SEM1 = math.sqrt(var1/len(X1))
	print("SEM = " + str(SEM1))
	print()

	print("Part ii)")
	X2 = [(np.random.uniform(high=2.0), np.random.uniform(high=3.0)) for i in range(5*10**4)]
	count = 0
	for x in X2:
		if x[1] <= np.exp(np.cos(x[0]**2)):
			count += 1
	print("area = " + str(6*count/len(X2)))
	mu = np.average(np.array(X2))
	var = sum([(xi - mu)**2 for xi in X2])/len(X2)
	var = math.sqrt(var[0]**2 + var[1]**2)
	SEM = math.sqrt(var/len(X2))
	print("SEM = " + str(SEM))

def prob7_4():
	count = 0
	for i in range(100000):
		x = np.random.beta(2,5)
		y = np.random.beta(20, 55)
		if x < y:
			count += 1

	print("probability is around " + str(count/100000))

def prob7_5():
	n = 100000
	samples = []
	for x in range(n):
		score = 0
		for i in range(10):
			r = np.random.randint(4)
			if r == 0:
				score += 1
			elif r == 1:
				score += 1
			elif r == 2:
				score += 2
			elif r == 4:
				score -= 1
		samples.append(score)

	samples = np.array(samples)
	print("probability with n= " + str(n) + " : " + str(len(samples[np.where(samples < 0)])/len(samples)))







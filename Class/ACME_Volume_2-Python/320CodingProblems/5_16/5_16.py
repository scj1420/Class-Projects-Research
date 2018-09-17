import numpy as np
from matplotlib import pyplot as plt

def prob5_16():
	X = np.arange(10, 0, -0.1)
	Y = np.arange(0.1, 5.1, 0.1)
	false_positive = []
	false_negative = []
	disease_prevalence = []
	for n in X:
		n = n / 100
		P = (1-n)*(0.004)/((1-n)*(0.004)+(n)*(0.996))
		false_positive.append(P)
		P = (1-n)*(0.996)/((n)*(0.004)+(1-n)*(0.996))
		false_negative.append(P)
	for n in Y:
		n = n/100
		P = (0.95)*(n)/((0.95)*(n) + (0.05)*(1-n))
		disease_prevalence.append(P)

	plt.ion()
	plt.subplot(131)
	plt.ylabel("probability")
	plt.xlabel("false rate")
	plt.plot(X, false_positive, label="false positive")
	plt.title("false positive")
	plt.subplot(132)
	plt.plot(X, false_negative, label="false negative")
	plt.title("false negative")
	plt.subplot(133)
	plt.plot(Y, disease_prevalence)
	plt.title("disease prevalence")

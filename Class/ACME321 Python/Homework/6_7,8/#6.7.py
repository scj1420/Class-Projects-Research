#6.7
import numpy as np

def prob6_7():
	mu = []
	for i in range(100):
		mu.append(np.random.binomial(1000, 0.5))
	return mu

def prob6_7part2():
	mu_hat = np.array(prob6_7())/1000
	mu = 0.5
	d = abs(mu_hat - mu)
	return d

def prob6_8():
	mu = []
	for i in range(100):
		l = np.random.beta(1, 9, size=1000)
		mu.append(np.mean(l))
	return mu
def prob6_8part2():
	mu_hat = np.array(prob6_8())/1000
	mu = 0.1
	d = abs(mu_hat - mu)
	return d
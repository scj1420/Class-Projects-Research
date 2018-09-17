def subtraction_algorithm(l1, l2):

	while len(l1) < len(l2):
		l1 = [0] + l1
	while len(l2) < len(l1):
		l2 = [0] + l2

	l3 = []

	for i in range(len(l1)-1, -1, -1):
		if l1[i] < l2[i]:
			l1[i-1] -= 1
			l1[i] += 10
			l3.insert(0, l1[i] - l2[i])
		else:
			l3.insert(0, l1[i] - l2[i])

	return l3


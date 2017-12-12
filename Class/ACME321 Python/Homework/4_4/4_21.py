def knapsack_prob(W, Items):
	# Sort the list Items by weight
	Items = sorted(Items, key=lambda item: item[0])
	Items_set = dict(Items)
	max_values = []
	for i in range(W+1):
		current_l = []
		l1 = [item for item in Items if item[0] == i]
		current_l += l1
		for j in range(1, len(max_values)):
			current_l.append((i, max_values[j][1] + max_values[i-j][1]))
		if len(current_l) != 0:
			max_values.append(max(current_l, key=lambda item:item[1]))
		else: max_values.append((i, 0))
		print(str(max_values[i]))

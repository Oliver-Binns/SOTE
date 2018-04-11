import os
from subprocess import check_output
import matplotlib.pyplot as plt

class_file = open("ClassComplexity.csv")

os.chdir("mason/mason")

packages = ["sim.engine", "sim.field.grid", "sim.field"]
revisions = [[],[],[]]
complexity = [[],[],[]]

for line in class_file:
	class_name, oc, wmc = line.split(",")
	file_name = class_name.replace('"', "").replace(".", "/")

	file_name += ".java"

	if not os.path.isfile(file_name):
		#print(file_name)
		continue

	log = check_output(["git", "shortlog", "-s", file_name])

	count = 0
	for line in log.split("\n"):
		split_line = line.split("\t")[0]
		if split_line is not "":
			count += int(split_line)

	for (index, package) in enumerate(packages):
		if package in class_name:
			print(package)
			revisions[index].append(count)
			complexity[index].append(int(wmc))
			break

max_revisions = 0
max_complexity = 0
color = ["red", "green", "blue"]

for index, package in enumerate(packages):
	plt.scatter(revisions[index], complexity[index], color=color[index], label=package)
	max_revisions = max(max(revisions[index]), max_revisions)
	max_complexity = max(max(complexity[index]), max_complexity)

plt.xlabel("Number of File Changes")
plt.ylabel("Complexity (WMC)")
plt.xlim(0, (max_revisions + 5))
plt.ylim(0, (max_complexity + 5))
plt.legend()
plt.show()

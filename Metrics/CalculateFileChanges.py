import os
from subprocess import check_output
import matplotlib.pyplot as plt

class_file = open("ClassComplexity.csv")

os.chdir("mason/mason")

revisions = []
complexity = []

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

	revisions.append(count)
	complexity.append(int(wmc))

print(revisions)
print(complexity)


plt.scatter(revisions, complexity)
plt.xlabel("Number of File Changes")
plt.ylabel("Complexity (WMC)")
plt.xlim(0, max(revisions) + 5)
plt.ylim(0, max(complexity) + 5)
plt.show()
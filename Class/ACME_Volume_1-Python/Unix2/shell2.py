# shell2.py
"""Volume 3: Unix Shell 2.
<Name>
<Class>
<Date>
"""
import os
from glob import glob
import subprocess
import re
# Problem 5
def grep(target_string, file_pattern):
    """Find all files in the current directory or its subdirectories that
    match the file pattern, then determine which ones contain the target
    string.

    Parameters:
        target_string (str): A string to search for in the files whose names
            match the file_pattern.
        file_pattern (str): Specifies which files to search.
    """
    match = []
    for file in glob("**/" + file_pattern, recursive=True):
        with open(file) as current:
            for line in current.readlines():
                if target_string in line:
                    match.append(file)
                    break
    return match

# Problem 6
def largest_files(n):
    """Return a list of the n largest files in the current directory or its
    subdirectories (from largest to smallest).
    """
    out_list = []
    files = subprocess.check_output(["ls", "-sR"]).decode()
    files = files.split('\n')
    for file in files:
        temp = tuple(file.lstrip().split(' '))
        if len(temp) == 2 and temp[0].isdigit():
            tup = (int(temp[0]), temp[1])
            out_list.append(tup)
    files = [t[1] for t in sorted(out_list)[::-1][:n]]
    print(files)

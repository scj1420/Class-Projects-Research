#!/Users/Seong-EunCho/anaconda/bin/python
import subprocess

subprocess.call("find .. -type f | wc -l", shell=True)

import requests
import json
import re

response = requests.get("https://test.pypi.org/pypi/selenium/json")
data = response.json()

versions = data['releases'].keys()
sorted_versions = sorted(versions, key=lambda s: [int(part) if part.isdigit() else part for part in re.split(r'(\d+)', s)])
latest_version = sorted_versions[-1]

print(latest_version)
import subprocess
import json
import argparse

def get_latest_nightly_version(package_type, package_name):
    path_packages_api = f"orgs/seleniumhq/packages/{package_type}/{package_name}/versions"
    accept_header = "Accept: application/vnd.github+json"
    version_header = "X-GitHub-Api-Version: 2022-11-28"

    gh_api_command = [
        "gh", "api", "-H", accept_header, "-H", version_header, path_packages_api
    ]

    result = subprocess.run(gh_api_command, capture_output=True, text=True)
    if result.returncode != 0:
        raise Exception(f"Error executing gh api command: {result.stderr}")

    versions = json.loads(result.stdout)
    latest_version = versions[0]['name']
    return latest_version

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Get the latest nightly version of a package.')
    parser.add_argument('package_type', type=str, help='The type of the package')
    parser.add_argument('package_name', type=str, help='The name of the package')

    args = parser.parse_args()
    package_type = args.package_type
    package_name = args.package_name

    print(get_latest_nightly_version(package_type, package_name))
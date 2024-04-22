#!/bin/bash

set -e

PACKAGE_TYPE="$1"
PACKAGE_NAME="$2"

PATH_PACKAGES_API="orgs/seleniumhq/packages/$PACKAGE_TYPE/$PACKAGE_NAME/versions"
ACCEPT_HEADER="Accept: application/vnd.github+json"
VERSION_HEADER="X-GitHub-Api-Version: 2022-11-28"

gh api -H "$ACCEPT_HEADER" -H "$VERSION_HEADER" $PATH_PACKAGES_API | jq -r '.[0].name'

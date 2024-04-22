$PACKAGE_TYPE = $args[0]
$PACKAGE_NAME = $args[1]

$PATH_PACKAGES_API = "orgs/seleniumhq/packages/$PACKAGE_TYPE/$PACKAGE_NAME/versions"
$ACCEPT_HEADER = "Accept: application/vnd.github+json"
$VERSION_HEADER = "X-GitHub-Api-Version: 2022-11-28"

$ghApiCommand = "gh api -H `"$ACCEPT_HEADER`" -H `"$VERSION_HEADER`" $PATH_PACKAGES_API | jq '.[0].name'"
Invoke-Expression -Command $ghApiCommand

#!/bin/bash
set -eo pipefail

VERSION_STR="$1"
NEW_VERSION=$(echo "$VERSION_STR" | cut -d. -f2)
OLD_VERSION=$((NEW_VERSION - 1))

OLD_BLOG=$(find website_and_docs/content/blog -mindepth 2 -maxdepth 2 -iname "selenium-4-$OLD_VERSION-released.md" | head -1)
if [ -z "$OLD_BLOG" ]; then
    echo "Could not find previous release blog post for 4.$OLD_VERSION" >&2
    exit 1
fi

SINCE_COMMIT_DATE=$(gh api repos/seleniumhq/selenium/commits/selenium-4.${OLD_VERSION}.0 --jq '.commit.committer.date')
UNTIL_COMMIT_DATE=$(gh api repos/seleniumhq/selenium/commits/selenium-4.${NEW_VERSION}.0 --jq '.commit.committer.date')

if date -j -f "%Y-%m-%dT%H:%M:%SZ" "$UNTIL_COMMIT_DATE" "+%Y" >/dev/null 2>&1; then
    # BSD/macOS date
    NEW_BLOG_YEAR=$(date -j -f "%Y-%m-%dT%H:%M:%SZ" "$UNTIL_COMMIT_DATE" "+%Y")
    NEW_BLOG_DATE=$(date -j -f "%Y-%m-%dT%H:%M:%SZ" "$UNTIL_COMMIT_DATE" "+%Y-%m-%d")
    NEW_RELEASE_DATE_HUMAN=$(LC_ALL=C date -j -f "%Y-%m-%dT%H:%M:%SZ" "$UNTIL_COMMIT_DATE" "+%B %e, %Y" | tr -s ' ')
elif date -u -d "$UNTIL_COMMIT_DATE" "+%Y" >/dev/null 2>&1; then
    # GNU date
    NEW_BLOG_YEAR=$(date -u -d "$UNTIL_COMMIT_DATE" "+%Y")
    NEW_BLOG_DATE=$(date -u -d "$UNTIL_COMMIT_DATE" "+%F")
    NEW_RELEASE_DATE_HUMAN=$(LC_ALL=C date -u -d "$UNTIL_COMMIT_DATE" "+%B %e, %Y" | tr -s ' ')
else
    echo "Could not parse commit date '$UNTIL_COMMIT_DATE' with either BSD or GNU date" >&2
    exit 1
fi

if sed --version >/dev/null 2>&1; then
    # GNU sed
    SED_IN_PLACE=(-i)
else
    # BSD/macOS sed
    SED_IN_PLACE=(-i '')
fi

FILES=(
    "website_and_docs/layouts/partials/selenium-clients-and-webdriver-bindings.html"
    "website_and_docs/layouts/downloads/list.html"
)

for FILE_PATH in "${FILES[@]}"; do
    sed "${SED_IN_PLACE[@]}" -E "s/4\.$OLD_VERSION\.[0-9]+/4.$NEW_VERSION.0/g" "$FILE_PATH"
    sed "${SED_IN_PLACE[@]}" -E "s/(4\.$NEW_VERSION\.0) \([A-Za-z]+ [0-9]+, [0-9]{4}\)/\1 ($NEW_RELEASE_DATE_HUMAN)/g" "$FILE_PATH"
    sed "${SED_IN_PLACE[@]}" -E "s/(4\.$NEW_VERSION\.0 Released on) [A-Za-z]+ [0-9]+, [0-9]{4}/\1 $NEW_RELEASE_DATE_HUMAN/g" "$FILE_PATH"
done

NEW_BLOG="website_and_docs/content/blog/$NEW_BLOG_YEAR/selenium-4-$NEW_VERSION-released.md"
mkdir -p "website_and_docs/content/blog/$NEW_BLOG_YEAR"
cp "$OLD_BLOG" "$NEW_BLOG"
git add "$NEW_BLOG"

sed "${SED_IN_PLACE[@]}" "s/4\.$OLD_VERSION/4\.$NEW_VERSION/g" "$NEW_BLOG"
sed "${SED_IN_PLACE[@]}" -E "s/^date: [0-9]{4}-[0-9]{2}-[0-9]{2}$/date: $NEW_BLOG_DATE/" "$NEW_BLOG"

echo "New blog post: $NEW_BLOG (date: $NEW_BLOG_DATE)"
echo

to_gh_user() {
    LC_ALL=C sort -u | sed 's#.*#{{< gh-user "https://api.github.com/users/&" >}}#'
}

print_contributors() {
    gh api --paginate --method GET "/repos/seleniumhq/$1/commits" -f since="$SINCE_COMMIT_DATE" -f until="$UNTIL_COMMIT_DATE" -f per_page=100 \
    --jq '.[].author.login // empty' | to_gh_user
}

# Docker Selenium publishes its own release for each Selenium version, tagged 4.N.x-YYYYMMDD,
# usually a few days after the Selenium release. Its contributors are the commits between the
# last release for the previous version and the first release for the new one.
DOCKER_TAGS=$(gh api --paginate repos/seleniumhq/docker-selenium/releases --jq '.[].tag_name' | grep -E '^4\.[0-9]+\.[0-9]+-[0-9]{8}$' | sort -V)
OLD_DOCKER_TAG=$(echo "$DOCKER_TAGS" | grep -E "^4\.$OLD_VERSION\." | tail -1 || true)
NEW_DOCKER_TAG=$(echo "$DOCKER_TAGS" | grep -E "^4\.$NEW_VERSION\." | head -1 || true)

print_docker_contributors() {
    if [ -n "$OLD_DOCKER_TAG" ] && [ -n "$NEW_DOCKER_TAG" ]; then
        gh api --paginate "repos/seleniumhq/docker-selenium/compare/$OLD_DOCKER_TAG...$NEW_DOCKER_TAG?per_page=100" \
        --jq '.commits[].author.login // empty' | to_gh_user
    else
        print_contributors docker-selenium
    fi
}

if [ -n "$OLD_DOCKER_TAG" ] && [ -n "$NEW_DOCKER_TAG" ]; then
    echo "Docker Selenium release: $NEW_DOCKER_TAG (previous: $OLD_DOCKER_TAG)"
else
    echo "WARNING: no Docker Selenium release found for 4.$NEW_VERSION (or 4.$OLD_VERSION) yet." >&2
    echo "WARNING: Docker contributors below use the Selenium tag dates instead." >&2
fi
echo

CONTRIBUTORS=$(mktemp)
trap 'rm -f "$CONTRIBUTORS"' EXIT
{
echo "Selenium Contributors"
print_contributors selenium

echo
echo "Docs Contributors"
print_contributors seleniumhq.github.io

echo
echo "Docker Contributors"
print_docker_contributors
} | tee "$CONTRIBUTORS"

echo
scripts/github-users.sh "$CONTRIBUTORS"

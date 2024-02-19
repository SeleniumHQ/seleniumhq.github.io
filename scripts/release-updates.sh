#!/bin/bash

VERSION_STR="$1"
NEW_VERSION=$(echo "$VERSION_STR" | cut -d. -f2)
OLD_VERSION=$((NEW_VERSION - 1))

FILES=(
    "website_and_docs/layouts/partials/selenium-clients-and-webdriver-bindings.html"
    "website_and_docs/layouts/downloads/list.html"
)

for FILE_PATH in "${FILES[@]}"; do
    sed -i '' -E "s/4\.$OLD_VERSION\.[0-9]+/4.$NEW_VERSION.0/g" "$FILE_PATH"
done

OLD_BLOG="website_and_docs/content/blog/2024/selenium-4-$OLD_VERSION-released.md"
NEW_BLOG="website_and_docs/content/blog/2024/selenium-4-$NEW_VERSION-released.md"
cp "$OLD_BLOG" "$NEW_BLOG"
git add "$NEW_BLOG"

sed -i '' "s/4\.$OLD_VERSION/4\.$NEW_VERSION/g" "$NEW_BLOG"

echo "Selenium Contributors"
gh api --method GET /repos/seleniumhq/selenium/commits -f since=selenium-4.${OLD_VERSION}.0 -f per_page=1000 \
--jq 'map(.author.login) | unique | sort | map("{{< gh-user \"https://api.github.com/users/" + . + "\" >}}") | .[]'

COMMIT_DATE=$(gh api repos/seleniumhq/selenium/commits/selenium-4.${OLD_VERSION}.0 --jq '.commit.committer.date')

echo
echo "Docs Contributors"
gh api --method GET /repos/seleniumhq/seleniumhq.github.io/commits -f since="$COMMIT_DATE" -f per_page=1000 \
--jq 'map(.author.login) | unique | sort | map("{{< gh-user \"https://api.github.com/users/" + . + "\" >}}") | .[]'

echo
echo "Docker Contributors"
gh api --method GET /repos/seleniumhq/docker-selenium/commits -f since="$COMMIT_DATE" -f per_page=1000 \
--jq 'map(.author.login) | unique | sort | map("{{< gh-user \"https://api.github.com/users/" + . + "\" >}}") | .[]'

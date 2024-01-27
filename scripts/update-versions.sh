#!/bin/bash

VERSION_STR="$1"
NEW_VERSION=$(echo "$VERSION_STR" | cut -d. -f2)
OLD_VERSION=$((NEW_VERSION - 1))
NEXT_VERSION=$((NEW_VERSION + 1))

FILES=(
    "examples/java/build.gradle"
    "examples/dotnet/SeleniumDocs/SeleniumDocs.csproj"
    "examples/python/requirements.txt"
    "examples/kotlin/pom.xml"
    "examples/java/pom.xml"
    "examples/javascript/package.json"
    "examples/ruby/Gemfile"
)

for FILE_PATH in "${FILES[@]}"; do
    if [[ "$FILE_PATH" == "examples/ruby/Gemfile" ]]; then
        sed -i '' -E "s/4\.$NEW_VERSION\.0/4.$NEXT_VERSION.0/g" "$FILE_PATH"
    fi

    sed -i '' -E "s/4\.$OLD_VERSION\.[0-9]+/4.$NEW_VERSION.0/g" "$FILE_PATH"
done

pushd examples/ruby
bundle install
popd

pushd examples/javascript
npm install
popd
#!/usr/bin/env bash

if [[ -z "$1" || -z "$2" || -z "$3"  || -z "$4" ]]; then
    echo -e "fatal: too few args\n"
    echo -e "usage:\n  update-versions.sh <old_version> <new_version> <old_cdp_version> <new_cdp_version>"
    echo -e "example:\n  ./update-versions.sh 4.35.0 4.38.0 137 142"
    exit 1
fi


if [[ "$1" != *.*.* || "$2" != *.*.* ]] ; then
    echo "fatal: use major.minor.patch version numbers (e.g.: 4.38.0)"
    exit 1
fi

OLD_VERSION="$1"
NEW_VERSION="$2"
OLD_CDP_VERSION="$3"
NEW_CDP_VERSION="$4"

FILES=(
    "examples/java/build.gradle"
    "examples/dotnet/SeleniumDocs/SeleniumDocs.csproj"
    "examples/python/requirements.txt"
    "examples/kotlin/pom.xml"
    "examples/java/pom.xml"
    "examples/javascript/package.json"
    "examples/ruby/Gemfile"
    "examples/ruby/spec/drivers/remote_webdriver_spec.rb"
)

# replace Selenium version
for FILE_PATH in "${FILES[@]}"; do
    echo $FILE_PATH
    if [[ ! -f ${FILE_PATH} ]]; then
        echo "can't find file for replacement!"
    fi
    perl -i -pe "s/${OLD_VERSION}/${NEW_VERSION}/g" "${FILE_PATH}"
done


# replace CDP version
find ./examples -type f \
    '(' -name "*.cs" -o -name "*.java" -o -name "*.js" -o -name "*.py" -o -name "*.rb" ')' \
    -exec perl -i -pe "s/v${OLD_CDP_VERSION}/v${NEW_CDP_VERSION}/g" {} \;

pushd examples/ruby
bundle install
popd

pushd examples/javascript
npm install
popd
